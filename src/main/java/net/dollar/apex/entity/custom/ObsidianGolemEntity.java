package net.dollar.apex.entity.custom;

import net.dollar.apex.item.ModItems;
import net.minecraft.block.BlockState;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.Angerable;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.passive.Cracks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.TimeHelper;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.SpawnHelper;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.UUID;

import static net.minecraft.entity.passive.Cracks.IRON_GOLEM;

public class ObsidianGolemEntity extends HostileEntity implements Angerable {
    private int attackTicksLeft;
    private static final UniformIntProvider ANGER_TIME_RANGE = TimeHelper.betweenSeconds(20, 39);
    private int angerTime;
    @Nullable
    private UUID angryAt;

    private int ticksSinceLastAttack = 0;
    private int teleportDelayTicks = 0;

    public ObsidianGolemEntity(EntityType<? extends HostileEntity> entityType, World world) {
        super(entityType, world);
    }



    /**
     * Register mob goals (AI).
     */
    @Override
    protected void initGoals() {
        this.goalSelector.add(1, new MeleeAttackGoal(this, 1.0, true));
        this.goalSelector.add(2, new WanderNearTargetGoal(this, 0.9, 32.0f));
        this.goalSelector.add(2, new WanderAroundPointOfInterestGoal(this, 0.6, false));
        this.goalSelector.add(7, new LookAtEntityGoal(this, PlayerEntity.class, 6.0f));
        this.goalSelector.add(8, new LookAroundGoal(this));
        this.targetSelector.add(2, new RevengeGoal(this));
        //this.targetSelector.add(3, new ActiveTargetGoal<PlayerEntity>(this, PlayerEntity.class, 10, true, false, this::shouldAngerAt));
        //this.targetSelector.add(3, new ActiveTargetGoal<MobEntity>(this, MobEntity.class, 5, false, false, entity -> entity instanceof Monster && !(entity instanceof CreeperEntity)));
        this.targetSelector.add(4, new UniversalAngerGoal<>(this, false));
    }

    /**
     * Creates mob attributes like HEALTH, FOLLOW RANGE, etc.
     * @return The newly created DefaultAttributeContainer
     */
    public static DefaultAttributeContainer.Builder createAttributes() {
        return MobEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 120)
                .add(EntityAttributes.GENERIC_ARMOR, 5)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.25)
                .add(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE, 1.0)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 15.0)
                .add(EntityAttributes.GENERIC_ATTACK_KNOCKBACK, 1.0)
                .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 30f);
    }

    /**
     * Decreases air supply while the mob is underwater (infinite breath underwater).
     * @param air Original air supply value
     * @return The updated air supply value
     */
    @Override
    protected int getNextAirUnderwater(int air) {
        return air;
    }

    /**
     * Perform per-tick movement operations.
     */
    @Override
    public void tickMovement() {
        super.tickMovement();
        if (this.attackTicksLeft > 0) {
            --this.attackTicksLeft;
        }

        if (!this.getWorld().isClient) {
            this.tickAngerLogic((ServerWorld)this.getWorld(), true);
        }
    }

    @Override
    public void writeCustomDataToNbt(NbtCompound nbt) {
        super.writeCustomDataToNbt(nbt);
        this.writeAngerToNbt(nbt);
    }

    @Override
    public void readCustomDataFromNbt(NbtCompound nbt) {
        super.readCustomDataFromNbt(nbt);
        this.readAngerFromNbt(this.getWorld(), nbt);
    }

    /**
     * Begins counting persistent anger, chosen at random within range, called when attacked by a LivingEntity.
     */
    @Override
    public void chooseRandomAngerTime() {
        this.setAngerTime(ANGER_TIME_RANGE.get(this.random));
    }

    /**
     * Sets the current anger time.
     * @param angerTime New value to set
     */
    @Override
    public void setAngerTime(int angerTime) {
        this.angerTime = angerTime;
    }

    /**
     * Gets the current anger time.
     * @return The current anger time
     */
    @Override
    public int getAngerTime() {
        return this.angerTime;
    }

    /**
     * Sets the current anger target via UUID.
     * @param angryAt UUID (nullable) of new anger target
     */
    @Override
    public void setAngryAt(@Nullable UUID angryAt) {
        this.angryAt = angryAt;
    }

    /**
     * Gets the current anger target by UUID.
     * @return The current anger target's UUID (nullable)
     */
    @Override
    @Nullable
    public UUID getAngryAt() {
        return this.angryAt;
    }

    /**
     * Returns the attack damage of this Entity.
     * @return The Entity's attack damage
     */
    private float getAttackDamage() {
        return (float)this.getAttributeValue(EntityAttributes.GENERIC_ATTACK_DAMAGE);
    }

    /**
     * Attempts to perform attack operations against the target.
     * @param target Target being attacked by this Entity
     * @return Whether the attack was successfully performed
     */
    @Override
    public boolean tryAttack(Entity target) {
        //Verify it has been at least 30 ticks (1.5 seconds) since last attack.
        if (ticksSinceLastAttack < 30) { return false; }

        //Actual attack operation done here.
        this.attackTicksLeft = 10;
        this.getWorld().sendEntityStatus(this, EntityStatuses.PLAY_ATTACK_SOUND);
        float f = this.getAttackDamage();
        float g = (int)f > 0 ? f / 2.0f + (float)this.random.nextInt((int)f) : f;
        boolean bl = target.damage(this.getDamageSources().mobAttack(this), g);

        //If damaging target was successful.
        if (bl) {
            double d;
            if (target instanceof LivingEntity livingEntity) {
                d = livingEntity.getAttributeValue(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE);
            } else {
                d = 0.0;
            }
            double d2 = d;
            double e = Math.max(0.0, 1.0 - d2);
            target.setVelocity(target.getVelocity().add(0.0, (double)0.4f * e, 0.0));
            this.applyDamageEffects(this, target);

            //After applying damage effects and knockback, do special Obsidian Golem attack behaviors.
            if (target instanceof LivingEntity livingEntity) {
                //Roll chance to apply a negative effect to target here.
                if (random.nextInt(100) < 50) {
                    //Apply one of two effects.
                    if (random.nextBoolean()) {
                        //Only level 1 slow, 15%/level (30% was too punishing).
                        livingEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 80, 0));
                    } else {
                        //Only weakness 1 (4 fewer points of damage).
                        livingEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.WEAKNESS, 80, 0));
                    }
                }

                //Also roll chance to set target on fire based on % missing HP (loosely corresponds to crackiness).
                if (random.nextFloat() > (this.getHealth() / this.getMaxHealth()) - 0.1f) {
                    livingEntity.setOnFireFor(4);   //100% chance at 10% Health because of -0.1f above
                }
            }
        }

        //Set ticks since last attack to 0.
        ticksSinceLastAttack = 0;

        this.playSound(SoundEvents.ENTITY_IRON_GOLEM_ATTACK, 1.0f, 1.0f);
        return bl;
    }

    /**
     * Performs taking damage operations, also updating visual cracks on the Entity.
     * @param source Source of damage being taken
     * @param amount Amount of damage to take
     * @return Whether taking damage operation was successful
     */
    @Override
    public boolean damage(DamageSource source, float amount) {
        Cracks.CrackLevel crack = this.getCrack();
        boolean bl = super.damage(source, amount);
        if (bl && this.getCrack() != crack) {
            this.playSound(SoundEvents.ENTITY_IRON_GOLEM_DAMAGE, 1.0f, 1.0f);
        }

        return bl;
    }

    /**
     * Gets Crack enum value based on % Health remaining.
     * @return Cracks.CrackLevel value that determines render visuals
     */
    public Cracks.CrackLevel getCrack() {
        return IRON_GOLEM.getCrackLevel(this.getHealth() / this.getMaxHealth());
    }

    @Override
    public void handleStatus(byte status) {
        if (status == EntityStatuses.PLAY_ATTACK_SOUND) {
            this.attackTicksLeft = 10;
            this.playSound(SoundEvents.ENTITY_IRON_GOLEM_ATTACK, 1.0f, 1.0f);
        } else {
            super.handleStatus(status);
        }
    }

    /**
     * Gets the number of attack ticks remaining when attacking.
     * @return Current attack animation ticks remaining
     */
    public int getAttackTicksLeft() {
        return this.attackTicksLeft;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return SoundEvents.ENTITY_IRON_GOLEM_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.ENTITY_IRON_GOLEM_DEATH;
    }

    @Override
    protected void playStepSound(BlockPos pos, BlockState state) {
        this.playSound(SoundEvents.ENTITY_IRON_GOLEM_STEP, 1.0f, 1.0f);
    }

    /**
     * Performs any operations immediately on death.
     * @param damageSource Source of damage that killed this Entity
     */
    @Override
    public void onDeath(DamageSource damageSource) {
        super.onDeath(damageSource);
    }

    /**
     * Gets whether this Entity can spawn at the passed-in WorldView location. Altered to disallow spawns
     *  above y = -16.
     * @param world The current WorldView (includes world position)
     * @return Whether this Entity can spawn at a given location
     */
    @Override
    public boolean canSpawn(WorldView world) {
        BlockPos blockPos = this.getBlockPos();
        BlockPos blockPos2 = blockPos.down();

        //Only valid spawn below y = -16.
        //if (blockPos.getY() >= -16) return false;

        //Remainder of original function below.
        BlockState blockState = world.getBlockState(blockPos2);
        if (blockState.hasSolidTopSurface(world, blockPos2, this)) {
            for (int i = 1; i < 3; ++i) {
                BlockState blockState2;
                BlockPos blockPos3 = blockPos.up(i);
                if (SpawnHelper.isClearForSpawn(world, blockPos3, blockState2 = world.getBlockState(blockPos3), blockState2.getFluidState(), EntityType.IRON_GOLEM)) continue;
                return false;
            }
            return SpawnHelper.isClearForSpawn(world, blockPos, world.getBlockState(blockPos), Fluids.EMPTY.getDefaultState(), EntityType.IRON_GOLEM) && world.doesNotIntersectEntities(this);
        }
        return false;
    }

    @Override
    public Vec3d getLeashOffset() {
        return new Vec3d(0.0, 0.875f * this.getStandingEyeHeight(), this.getWidth() * 0.4f);
    }





    /**
     * Performs per-tick operations of this Entity. Here, checks if this Entity has been unable to attack for
     *  at least 3 seconds. If it hasn't, rolls a chance each tick to blind and slow all nearby LivingEntities
     *  then teleport toward its affected target.
     */
    @Override
    public void tick() {
        super.tick();

        //If there is no target, ensure that ticksSinceLastAttack remains at 0 and return.
        if (this.getTarget() == null) {
            ticksSinceLastAttack = 0;
            return;
        }

        //If valid target, increment ticksSinceLastAttack and decrement teleportDelayTicks.
        ticksSinceLastAttack++;
        teleportDelayTicks--;

        //Then, if unable to attack for at least 3 seconds, roll chance per tick to do special attack.
        if (ticksSinceLastAttack >= 60 && teleportDelayTicks <= 0) {
            if (random.nextInt(100) == 0) {
                //Roll 1% chance each tick to perform special attack.
                blindAndSlowNearbyEntities();

                //Teleport directly on top of target if unable to attack for more than 12s (240 ticks),
                //  else teleport near the target.
                teleportTowardTarget(this.getTarget(), ticksSinceLastAttack >= 240);
            }
        }
    }

    /**
     * Applies Blindness and Slowness effects to all nearby LivingEntities and plays aggressive sound.
     */
    private void blindAndSlowNearbyEntities() {
        //Store xyz coordinates and get all entities within radius of this Entity.
        double radius = 24.0;
        double x = this.getX();
        double y = this.getY();
        double z = this.getZ();
        List<Entity> entities = this.getWorld().getOtherEntities(this,
                new Box(x - radius, y - radius, z - radius,
                        x + radius, y + radius, z + radius));

        //Play aggressive sound, then apply effects to all nearby LivingEntities.
        this.playSound(SoundEvents.ENTITY_RAVAGER_ROAR, 1.0f, 1.0f);
        for (Entity entity : entities) {
            if (entity instanceof LivingEntity livingEntity) {
                //Blind and slow ALL nearby LivingEntities regardless of whether angry at.
                livingEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.BLINDNESS, 60));
                livingEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 60, 1));
            }
        }
    }

    /**
     * Teleports this Entity either toward or directly on top of the target, determined by parameter.
     * @param target LivingEntity target of this Entity
     * @param onTop Whether to teleport directly on top of the target
     */
    private void teleportTowardTarget(LivingEntity target, boolean onTop) {
        if (onTop) {
            //Teleport directly on top of target, +- 0.5 blocks.
            teleport((random.nextDouble() - 0.5D) + target.getX(),
                    target.getY() + 0.5D,
                    (random.nextDouble() - 0.5D) + target.getZ());
        } else {
            //Else teleport to within 5 blocks of the target.
            teleport((random.nextDouble() - 0.5D) + target.getX() + (random.nextInt(10) - 5),
                    target.getY() + 2.5D,
                    (random.nextDouble() - 0.5D) + target.getZ() + (random.nextInt(10) - 5));
        }

        //Should delay any attack after teleporting by 0.5s to not be overpowered.
        if (this.isInAttackRange(target)) {
            ticksSinceLastAttack = 20;  //Will attack at 30, so in 10 ticks
        }

        //Finally, set teleportDelayTicks to 100 (5 seconds) to prevent teleport spam.
        teleportDelayTicks = 100;
    }


    /**
     * Gets whether this Entity can receive a specific status effect.
     * @param effect Effect attempting to be applied
     * @return Whether this Entity can receive the StatusEffect
     */
    @Override
    public boolean canHaveStatusEffect(StatusEffectInstance effect) {
        RegistryEntry<StatusEffect> statusEffect = effect.getEffectType();
        return statusEffect != StatusEffects.POISON && statusEffect != StatusEffects.WITHER;
    }

    /**
     * Defines behavior for dropping loot from this Entity.
     * @param damageSource Source of damage that killed this Entity
     * @param causedByPlayer Whether this Entity was killed by a Player
     */
    @Override
    protected void dropLoot(DamageSource damageSource, boolean causedByPlayer) {
        if (!causedByPlayer) return;

        //Below is copied almost entirely from WitherEntity.
        ItemEntity itemEntity = this.dropItem(ModItems.MOLTEN_CORE);
        if (itemEntity != null) {
            itemEntity.setCovetedItem();    //This is extended lifetime before despawn
        }

        //Will drop a collector item if slain with specific circumstances.
        if (damageSource.getAttacker() instanceof PlayerEntity playerEntity) {
            ItemStack heldItem = playerEntity.getEquippedStack(EquipmentSlot.MAINHAND);

            //If heldItem is a Tungsten-Carbide Battleaxe with Sharpness V.
            if (heldItem.getItem() == ModItems.TUNGSTEN_CARBIDE_BATTLEAXE &&
                    EnchantmentHelper.getLevel(Enchantments.SHARPNESS, heldItem) >= 5) {
                //Drop Obsidian Dust collector item and give it a long despawn delay.
                ItemEntity collectorItem = this.dropItem(ModItems.TROPHY_OBSIDIAN_DUST);
                if (collectorItem != null) {
                    collectorItem.setCovetedItem();
                }
            }
        }

    }

    /**
     * Gets experience drop from this Entity on death.
     * @return Amount of XP to drop
     */
    @Override
    public int getXpToDrop() {
        return 50;
    }

    /**
     * Gets the distance that this Entity can fall without taking damage.
     * @return This Entity's safe falling distance
     */
    @Override
    public int getSafeFallDistance() {
        return 10;
    }

    /**
     * Gets whether this Entity is fire immune (true).
     * @return Whether this Entity is fire immune
     */
    @Override
    public boolean isFireImmune() {
        return true;
    }

    /**
     * Gets whether this Entity should always render its display name.
     * @return Whether it should render its display name
     */
    @Override
    public boolean shouldRenderName() {
        return false;
    }

    /**
     * Checks whether a spawn attempt is valid, specifically whether it is below a specific y-value.
     * @param obsidianGolemEntityEntityType EntityType of ObsidianGolemEntity (this)
     * @param serverWorldAccess Active ServerWorldAccess (world)
     * @param spawnReason Type of mob spawn (NATURAL)
     * @param blockPos Position of spawn attempt being queried
     * @param random Convenient random
     * @return Whether the spawn attempt is successful
     */
    public static boolean checkObsidianGolemSpawnRules(EntityType<ObsidianGolemEntity> obsidianGolemEntityEntityType,
                                                       ServerWorldAccess serverWorldAccess, SpawnReason spawnReason,
                                                       BlockPos blockPos, Random random) {
        //Only allow spawn below a certain y-level.
        if (blockPos.getY() >= 0) {
            return false;
        }

        //Calls the default mob spawn check, ignoring light levels entirely. Use canSpawnInDark instead.
        return canMobSpawn(obsidianGolemEntityEntityType, serverWorldAccess, spawnReason, blockPos, random);
    }
}
