package net.dollar.apex.entity.custom;

import net.dollar.apex.item.ModItems;
import net.minecraft.block.BlockState;
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
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.TimeHelper;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.UUID;

public class MysteriousSpecterEntity extends HostileEntity implements Angerable {
    private static final UniformIntProvider ANGER_TIME_RANGE = TimeHelper.betweenSeconds(20, 39);
    private int angerTime;
    @Nullable
    private UUID angryAt;

    private int ticksSinceLastAttack = 0;
    private int auraCounterTicks = 60;
    private final int textureID;

    public MysteriousSpecterEntity(EntityType<? extends HostileEntity> entityType, World world) {
        super(entityType, world);

        //Set textureID to a value between 0-4, which is used to determine which texture to render.
        textureID = world.random.nextInt(5);
    }



    /**
     * Gets the textureID field (in range of 0-4), which will determine which texture to load for this
     *  Entity instance.
     * @return The textureID field value
     */
    public int getTextureID() {
        return textureID;
    }

    /**
     * Register mob goals (AI).
     */
    @Override
    protected void initGoals() {
        this.goalSelector.add(1, new MeleeAttackGoal(this, 1.0, true));
        this.goalSelector.add(2, new WanderNearTargetGoal(this, 0.9, 32.0f));
        this.goalSelector.add(2, new WanderAroundPointOfInterestGoal(this, 0.6, false));
        //this.goalSelector.add(7, new LookAtEntityGoal(this, PlayerEntity.class, 6.0f));
        this.goalSelector.add(8, new LookAroundGoal(this));
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
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.25)
                .add(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE, 1.0)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 15.0)
                .add(EntityAttributes.GENERIC_ATTACK_KNOCKBACK, 0.5)
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
        //Verify it has been at least 20 ticks (1 second) since last attack.
        if (ticksSinceLastAttack < 20) { return false; }

        //Actual attack operation done here.
        this.getWorld().sendEntityStatus(this, EntityStatuses.PLAY_ATTACK_SOUND);
        float f = this.getAttackDamage();
        float g = (int)f > 0 ? f / 2.0f + (float)this.random.nextInt((int)f) : f;
        boolean bl = target.damage(this.getDamageSources().mobAttack(this), g);

        //If damaging target was successful.
        if (bl) {
            //Immediately reset attack counter and movement speed buff.
            ticksSinceLastAttack = 0;
            resetMovementSpeed();

            //REST OF BASE FUNCTION HERE.
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
                    //Increase Wither level based on missing Health (split into 3 parts, 33% HP each).
                    livingEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.WITHER, 81,
                            calcWitherStrength()));
                }
            }
        }

        this.playSound(SoundEvents.ENTITY_PLAYER_ATTACK_STRONG, 1.0f, 1.0f);
        return bl;
    }

    /**
     * Calculates strength of Wither effect to apply to targets based on missing Health.
     * @return The calculated Wither effect strength as an integer
     */
    private int calcWitherStrength() {
        //EXAMPLE: At 25% Health: 1.0 - .25 = 0.75 | 0.75 * 3.0 = 2.25 | floor(2.25) = 2
        double strength = 1.0 - (this.getHealth() / this.getMaxHealth());   //Inverted missing Health
        strength *= 3.0;                                                    //Convert to range 0-3
        strength = Math.floor(strength);                                    //Floor, guarantees between 0-2
        return (int)strength;   //Convert to integer before returning
    }

    /**
     * Performs taking damage operations, also updating visual cracks on the Entity.
     * @param source Source of damage being taken
     * @param amount Amount of damage to take
     * @return Whether taking damage operation was successful
     */
    @Override
    public boolean damage(DamageSource source, float amount) {
        return super.damage(source, amount);
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return SoundEvents.ENTITY_BLAZE_AMBIENT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.ENTITY_BLAZE_DEATH;
    }

    @Override
    protected void playStepSound(BlockPos pos, BlockState state) {
        //PLAY NO STEP SOUND.
//        this.playSound(SoundEvents.ENTITY_IRON_GOLEM_STEP, 1.0f, 1.0f);
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
     * Performs per-tick operations of this Entity. Here, checks if this Entity has been unable to attack for
     *  at least 3 seconds. If it hasn't, rolls a chance each tick to blind and slow all nearby LivingEntities
     *  then teleport toward its affected target.
     */
    @Override
    public void tick() {
        super.tick();

        //Decrement aura counter, then if <= 0, do aura and reset counter.
        auraCounterTicks--;
        if (auraCounterTicks <= 0) {
            applyWeaknessHungerAura();
            auraCounterTicks = 60;
        }

        //If there is no target, ensure that ticksSinceLastAttack remains at 0 and return.
        if (this.getTarget() == null) {
            ticksSinceLastAttack = 0;
            return;
        }

        //If valid target, increment ticksSinceLastAttack.
        ticksSinceLastAttack++;

        //Then, if unable to attack for at least 3 seconds, roll chance per tick to do special attack.
        if (ticksSinceLastAttack >= 60) {
            if (random.nextInt(100) == 0) {
                //Roll 1% chance each tick to perform special attack.
                weakenAndSlowNearbyEntities();

                //Increase movement speed temporarily.
                increaseMovementSpeedTemporarily();
            }
        }
    }

    /**
     * Applies the Weakness and Hunger effect to all nearby Entities.
     */
    private void applyWeaknessHungerAura() {
        double radius = 16.0;
        double x = this.getX();
        double y = this.getY();
        double z = this.getZ();
        List<Entity> entities = this.getWorld().getOtherEntities(this,
                new Box(x - radius, y - radius, z - radius,
                        x + radius, y + radius, z + radius));

        for (Entity entity : entities) {
            if (entity instanceof LivingEntity livingEntity) {
                //Do not apply effect to Mysterious Specters.
                if (livingEntity instanceof MysteriousSpecterEntity) {
                    continue;
                }

                //Apply lowest-level Weakness and Hunger to each entity for 10 seconds.
                livingEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.WEAKNESS, 200, 0));
                livingEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.HUNGER, 200, 0));
            }
        }
    }

    /**
     * Applies Blindness and Slowness effects to all nearby LivingEntities and plays aggressive sound.
     */
    private void weakenAndSlowNearbyEntities() {
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
                //Slow and Weaken ALL nearby LivingEntities regardless of whether angry at.
                livingEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 60, 1));
                livingEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.DARKNESS, 60));

                //Knockback the LivingEntity with half default strength.
                //KNOCKBACK NOT WORKING FOR SOME REASON
//                livingEntity.takeKnockback(0.5f, MathHelper.sin(this.getYaw() * ((float)Math.PI / 180)),
//                        -MathHelper.cos(this.getYaw() * ((float)Math.PI / 180)));
            }
        }
    }

    /**
     * Increases Entity's movement speed for a duration using the Speed status effect.
     */
    private void increaseMovementSpeedTemporarily() {
        //Add Speed effect at level 5 (20% * level), so double speed, for 1200 ticks (60 seconds).
        if (!this.hasStatusEffect(StatusEffects.SPEED)) {
            this.addStatusEffect(new StatusEffectInstance(StatusEffects.SPEED, 1200, 4));
        }
    }

    /**
     * Resets Entity's movement speed back to base by removing the Speed status effect.
     */
    private void resetMovementSpeed() {
        //Remove Speed status effect, if active.
        if (this.hasStatusEffect(StatusEffects.SPEED)) {
            this.removeStatusEffect(StatusEffects.SPEED);
        }
    }


    /**
     * Gets whether this Entity can receive a specific status effect.
     * @param effect Effect attempting to be applied
     * @return Whether this Entity can receive the StatusEffect
     */
    @Override
    public boolean canHaveStatusEffect(StatusEffectInstance effect) {
        StatusEffect statusEffect = effect.getEffectType();
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
        ItemEntity itemEntity = this.dropItem(ModItems.HANDFUL_OF_STARDUST);
        if (itemEntity != null) {
            itemEntity.setCovetedItem();    //This is extended lifetime before despawn
        }

        //Will drop a trophy item if slain with specific circumstances.
        if (damageSource.getAttacker() instanceof PlayerEntity playerEntity) {
            ItemStack heldItem = playerEntity.getEquippedStack(EquipmentSlot.MAINHAND);

            //If heldItem is an endgame-tier Hoe.
            if (heldItem.getItem() == Items.NETHERITE_HOE ||
                    heldItem.getItem() == ModItems.COBALT_STEEL_HOE ||
                    heldItem.getItem() == ModItems.INFUSED_GEMSTONE_HOE ||
                    heldItem.getItem() == ModItems.TUNGSTEN_CARBIDE_HOE) {
                //Drop Obsidian Dust collector item and give it a long despawn delay.
                ItemEntity collectorItem = this.dropItem(ModItems.TROPHY_OMINOUS_LETTER);
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
     * @param mysteriousSpecterEntityType EntityType of MysteriousSpecterEntity (this)
     * @param serverWorldAccess Active ServerWorldAccess (world)
     * @param spawnReason Type of mob spawn (NATURAL)
     * @param blockPos Position of spawn attempt being queried
     * @param random Convenient random
     * @return Whether the spawn attempt is successful
     */
    public static boolean checkMysteriousSpecterSpawnRules(EntityType<MysteriousSpecterEntity> mysteriousSpecterEntityType,
                                                       ServerWorldAccess serverWorldAccess, SpawnReason spawnReason,
                                                       BlockPos blockPos, Random random) {
        //Only allow spawn above a certain y-level (62 is sea level).
        if (blockPos.getY() < 62) {
            return false;
        }

        //Calls the default mob spawn check, ignoring light levels entirely. Use canSpawnInDark instead.
        return canSpawnInDark(mysteriousSpecterEntityType, serverWorldAccess, spawnReason, blockPos, random);
    }
}
