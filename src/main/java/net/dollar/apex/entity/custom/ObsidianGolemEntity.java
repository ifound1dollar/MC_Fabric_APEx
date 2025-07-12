package net.dollar.apex.entity.custom;

import net.dollar.apex.entity.ability.ModFireballEntity;
import net.dollar.apex.entity.goal.ModMeleeAttackGoal;
import net.dollar.apex.item.ModItems;
import net.minecraft.block.BlockState;
import net.minecraft.enchantment.Enchantment;
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
import net.minecraft.predicate.entity.EntityPredicates;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
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
    private static final int DEFAULT_LAST_ATTACK_TICKS_THRESHOLD = 100;
    private int abilityCooldownTicks;
    private static final int DEFAULT_ABILITY_COOLDOWN_TICKS = 100;

    public ObsidianGolemEntity(EntityType<? extends HostileEntity> entityType, World world) {
        super(entityType, world);

        abilityCooldownTicks = DEFAULT_ABILITY_COOLDOWN_TICKS;
    }



    /**
     * Register mob goals (AI).
     */
    @Override
    protected void initGoals() {
        this.goalSelector.add(1, new ModMeleeAttackGoal(this, 1.0, false,
                40));
        this.goalSelector.add(8, new LookAroundGoal(this));
        this.goalSelector.add(9, new WanderAroundFarGoal(this, 0.6));

        this.targetSelector.add(2, new RevengeGoal(this));
        this.targetSelector.add(4, new UniversalAngerGoal<>(this, false));
    }

    /**
     * Creates mob attributes like HEALTH, FOLLOW RANGE, etc.
     * @return The newly created DefaultAttributeContainer
     */
    public static DefaultAttributeContainer.Builder createAttributes() {
        return MobEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 120)
                .add(EntityAttributes.GENERIC_ARMOR, 20)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.25)
                .add(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE, 1.0)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 12.0)
                .add(EntityAttributes.GENERIC_ATTACK_KNOCKBACK, 1.0)
                .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 30f)
                .add(EntityAttributes.GENERIC_STEP_HEIGHT, 1);
    }

    /**
     * Gets the attack Box for this mob. Overridden to expand on the X and Z axes somewhat.
     * @return The attack Box for this mob.
     */
    @Override
    protected Box getAttackBox() {
        return super.getAttackBox().expand(0.2d, 0.0d, 0.2d);
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
     * Attempts to perform attack operations against the target.
     * * @param world Server World this Entity exists in
     * @param target Target being attacked by this Entity
     * @return Whether the attack was successfully performed
     */
    @Override
    public boolean tryAttack(Entity target) {
        ticksSinceLastAttack = 0;
        attackTicksLeft = 10;

        // If default attack operation was successful, do special attack effects.
        if (super.tryAttack(target)) {
            ticksSinceLastAttack = 0;

            //After applying damage effects and knockback, do special Obsidian Golem attack behaviors.
            if (target instanceof LivingEntity livingEntity) {
                // Roll chance to set target on fire based on % missing HP (loosely corresponds to crackiness).
                if (random.nextFloat() > (this.getHealth() / this.getMaxHealth()) - 0.25f) {
                    livingEntity.setOnFireFor(4);   // 100% chance at 25% Health because of -0.25f above
                }
            }

            return true;
        }

        return false;
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
            this.playSound(SoundEvents.ENTITY_IRON_GOLEM_DAMAGE);
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
            this.playSound(SoundEvents.ENTITY_IRON_GOLEM_ATTACK);
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
    protected @Nullable SoundEvent getAmbientSound() {
        return SoundEvents.ENTITY_RAVAGER_AMBIENT;
    }

    @Override
    public int getMinAmbientSoundDelay() {
        return 300;     // Default is 80.
    }

    @Override
    protected void playAttackSound() {
        this.playSound(SoundEvents.ENTITY_IRON_GOLEM_ATTACK);
    }

    @Override
    protected void playStepSound(BlockPos pos, BlockState state) {
        this.playSound(SoundEvents.ENTITY_IRON_GOLEM_STEP);
    }

    /**
     * This method implementation directly copied from IronGolemEntity.
     * @param world The current WorldView (includes world position)
     * @return Whether this Entity can spawn at a given location
     */
    @Override
    public boolean canSpawn(WorldView world) {
        BlockPos blockPos = this.getBlockPos();
        BlockPos blockPos2 = blockPos.down();
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

    /**
     * Performs per-tick operations of this Entity. Here, checks if this Entity has been unable to attack for
     *  at least 3 seconds. If it hasn't, rolls a chance each tick to use special ability.
     */
    @Override
    public void tick() {
        super.tick();

        // Only run tick behavior on server.
        if (!(this.getWorld() instanceof ServerWorld)) return;

        //If there is no target, ensure that ticksSinceLastAttack remains at 0 and return.
        if (this.getTarget() == null) {
            ticksSinceLastAttack = 0;
            return;
        }

        // If valid target, increment ticksSinceLastAttack and decrement abilityCooldownTicks.
        ticksSinceLastAttack++;
        abilityCooldownTicks--;

        // Then, if unable to attack for at least 3 seconds and ability not on cooldown, try special ability.
        if (ticksSinceLastAttack >= DEFAULT_LAST_ATTACK_TICKS_THRESHOLD && abilityCooldownTicks <= 0) {
            if (random.nextInt(100) == 0) {
                // Roll 1% chance each tick to perform special attack.
                rangedAttackNearbyPlayers();

                abilityCooldownTicks = DEFAULT_ABILITY_COOLDOWN_TICKS;
            }
        }
    }

    /**
     * Perform special ranged attack against all nearby PlayerEntities.
     */
    private void rangedAttackNearbyPlayers() {
        double radius = 24.0;
        double x = this.getX();
        double y = this.getY();
        double z = this.getZ();
        List<PlayerEntity> players = this.getWorld().getEntitiesByClass(PlayerEntity.class,
                new Box(x - radius, y - radius, z - radius,
                        x + radius, y + radius, z + radius), EntityPredicates.EXCEPT_CREATIVE_OR_SPECTATOR);

        // Play aggressive sound at full volume, then perform special ability.
        this.playSound(SoundEvents.ENTITY_RAVAGER_ROAR);
        for (PlayerEntity player : players) {
            // Slow all nearby players at Level 3 intensity (45%) for 3s.
            player.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 60,
                    2, false, false, true));

            // Shoot a fireball at the player always, but if not visible, immediately damage and set on fire.
            shootFireballAtPlayer(player);
            if (!this.getVisibilityCache().canSee(player)) {
                player.damage(this.getDamageSources().mobAttackNoAggro(this),
                        5.0f);      // Same damage as fireball.
                player.setOnFireFor(4);     // Same duration as fireball.
            }
        }
    }

    /**
     * Attempts to shoot a fireball at a visible PlayerEntity. Pulled largely from blaze fireball goal.
     * @param player PlayerEntity to attempt to shoot the fireball at
     */
    private void shootFireballAtPlayer(PlayerEntity player) {
        double xDist = player.getX() - this.getX();
        double yDist = player.getBodyY(0.5) - this.getBodyY(0.5);
        double zDist = player.getZ() - this.getZ();

        // Create fireball velocity vector, then create fireball and shoot it at the PlayerEntity.
        Vec3d vec3d = new Vec3d(xDist, yDist, zDist);
        ModFireballEntity modFireballEntity = new ModFireballEntity(this.getWorld(), this, vec3d.normalize());
        modFireballEntity.setPosition(
                modFireballEntity.getX(),
                this.getBodyY(0.5) + 0.5,
                modFireballEntity.getZ());
        this.getWorld().spawnEntity(modFireballEntity);
    }


    /**
     * Gets whether this Entity can receive a specific status effect.
     * @param effect Effect attempting to be applied
     * @return Whether this Entity can receive the StatusEffect
     */
    @Override
    public boolean canHaveStatusEffect(StatusEffectInstance effect) {
        RegistryEntry<StatusEffect> statusEffect = effect.getEffectType();
        return statusEffect != StatusEffects.POISON;
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
            RegistryWrapper.Impl<Enchantment> impl = this.getWorld().getRegistryManager().getWrapperOrThrow(RegistryKeys.ENCHANTMENT);
            if (heldItem.getItem() == ModItems.TUNGSTEN_CARBIDE_BATTLEAXE &&
                    EnchantmentHelper.getLevel(impl.getOrThrow(Enchantments.SHARPNESS), heldItem) >= 5) {
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
        int y = blockPos.getY();
        if (y >= 0) {
            return false;
        } else if (y >= -24) {
            // Effectively reduce spawn rate by 50% above y = -24.
            return random.nextBoolean()
                    && canMobSpawn(obsidianGolemEntityEntityType, serverWorldAccess, spawnReason, blockPos, random);
        }

        //Calls the default mob spawn check, ignoring light levels entirely. Use canSpawnInDark instead.
        return canMobSpawn(obsidianGolemEntityEntityType, serverWorldAccess, spawnReason, blockPos, random);
    }
}
