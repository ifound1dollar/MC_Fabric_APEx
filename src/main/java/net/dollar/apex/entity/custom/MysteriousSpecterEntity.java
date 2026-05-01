package net.dollar.apex.entity.custom;

import net.dollar.apex.entity.goal.ModMeleeAttackGoal;
import net.dollar.apex.entity.goal.ModStareOrMoveGoal;
import net.dollar.apex.item.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.RandomSource;
import net.minecraft.util.TimeUtil;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityReference;
import net.minecraft.world.entity.EntitySelector;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.NeutralMob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.ResetUniversalAngerTargetGoal;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.minecraft.world.phys.AABB;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class MysteriousSpecterEntity extends Monster implements NeutralMob {
    private static final UniformInt ANGER_TIME_RANGE = TimeUtil.rangeOfSeconds(20, 39);
    private long angerTime;
    @Nullable
    private EntityReference<LivingEntity> angryAt;

    private int ticksSinceLastAttack = 0;
    private static final int DEFAULT_LAST_ATTACK_TICKS_THRESHOLD = 100;
    private int auraCounterTicks = 60;
    private int abilityCooldownTicks;
    private static final int DEFAULT_ABILITY_COOLDOWN_TICKS = 100;

    public MysteriousSpecterEntity(EntityType<? extends Monster> entityType, Level world) {
        super(entityType, world);

        abilityCooldownTicks = DEFAULT_ABILITY_COOLDOWN_TICKS;
    }



    /**
     * Register mob goals (AI).
     */
    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(1, new ModMeleeAttackGoal(this, 1.0, false,
                40));
        this.goalSelector.addGoal(8, new RandomLookAroundGoal(this));

        this.targetSelector.addGoal(2, new HurtByTargetGoal(this));
        this.targetSelector.addGoal(4, new ResetUniversalAngerTargetGoal<>(this, false));

        this.goalSelector.addGoal(3, new ModStareOrMoveGoal(this, Player.class, 10.0f,
                0.666d, 0.001f));
    }

    /**
     * Creates mob attributes like HEALTH, FOLLOW RANGE, etc.
     * @return The newly created DefaultAttributeContainer
     */
    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 120)
                .add(Attributes.MOVEMENT_SPEED, 0.25)
                .add(Attributes.KNOCKBACK_RESISTANCE, 1.0)
                .add(Attributes.ATTACK_DAMAGE, 12.0)
                .add(Attributes.ATTACK_KNOCKBACK, 0.5)
                .add(Attributes.FOLLOW_RANGE, 30f)
                .add(Attributes.STEP_HEIGHT, 1);
    }

    /**
     * Gets the attack Box for this mob. Overridden to expand on the X and Z axes slightly.
     * @param attackRange Attack range of this mob
     * @return The attack Box for this mob.
     */
    @Override
    protected AABB getAttackBoundingBox(double attackRange) {
        return super.getAttackBoundingBox(attackRange).inflate(0.1d, 0.0d, 0.1d);
    }

    /**
     * Decreases air supply while the mob is underwater (infinite breath underwater).
     * @param air Original air supply value
     * @return The updated air supply value
     */
    @Override
    protected int decreaseAirSupply(int air) {
        return air;
    }

    /**
     * Perform per-tick movement operations.
     */
    @Override
    public void aiStep() {
        super.aiStep();

        if (!this.level().isClientSide()) {
            this.updatePersistentAnger((ServerLevel)this.level(), true);
        }
    }

    @Override
    protected void addAdditionalSaveData(ValueOutput view) {
        super.addAdditionalSaveData(view);
        this.addPersistentAngerSaveData(view);
    }

    @Override
    public void readAdditionalSaveData(ValueInput view) {
        super.readAdditionalSaveData(view);
        this.readPersistentAngerSaveData(this.level(), view);
    }

    /**
     * Begins counting persistent anger, chosen at random within range, called when attacked by a LivingEntity.
     */
    @Override
    public void startPersistentAngerTimer() {
        this.setTimeToRemainAngry(ANGER_TIME_RANGE.sample(this.random));
    }

    /**
     * Sets the current anger time.
     * @param angerTime New value to set
     */
    @Override
    public void setPersistentAngerEndTime(long angerTime) {
        this.angerTime = angerTime;
    }

    /**
     * Gets the current anger time.
     * @return The current anger time
     */
    @Override
    public long getPersistentAngerEndTime() {
        return this.angerTime;
    }

    /**
     * Sets the current anger target via UUID.
     * @param angryAt UUID (nullable) of new anger target
     */
    @Override
    public void setPersistentAngerTarget(@org.jspecify.annotations.Nullable EntityReference<LivingEntity> angryAt) {
        this.angryAt = angryAt;
    }

    /**
     * Gets the current anger target by UUID.
     * @return The current anger target's UUID (nullable)
     */
    @Override
    public @org.jspecify.annotations.Nullable EntityReference<LivingEntity> getPersistentAngerTarget() {
        return this.angryAt;
    }

    /**
     * Attempts to perform attack operations against the target.
     * @param target Target being attacked by this Entity
     * @return Whether the attack was successfully performed
     */
    @Override
    public boolean doHurtTarget(ServerLevel world, Entity target) {
        attackStrengthTicker = 0;

        // If default attack operation was successful, do special attack effects.
        if (super.doHurtTarget(world, target)) {
            //Immediately reset attack counter and movement speed buff.
            resetMovementSpeed();

            //After applying damage effects and knockback, do special Mysterious Specter attack behaviors.
            if (target instanceof LivingEntity livingEntity) {
                //Roll 50% chance to Wither the target here.
                if (random.nextInt(100) < 50) {
                    //Increase Wither level based on missing Health (split into 3 parts, 33% HP each).
                    livingEntity.addEffect(
                            new MobEffectInstance(MobEffects.WITHER, 81, calcWitherStrength(),
                                    false, false, true));
                }
            }

            // Heal the Mysterious Specter for 1 heart (2 health) on each successful attack.
            this.setHealth(this.getHealth() + 2.0f);

            return true;
        }

        return false;
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

    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return SoundEvents.BLAZE_AMBIENT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.BLAZE_DEATH;
    }

    @Override
    protected @Nullable SoundEvent getAmbientSound() {
        return switch (getRandom().nextInt(5)) {
            case 0 -> SoundEvents.BLAZE_AMBIENT;
            case 1 -> SoundEvents.HUSK_AMBIENT;
            case 2 -> SoundEvents.ZOMBIE_VILLAGER_AMBIENT;
            case 3 -> SoundEvents.GHAST_AMBIENT;
            case 4 -> SoundEvents.WARDEN_TENDRIL_CLICKS;
            default -> null;    // Should never reach default case.
        };
    }

    @Override
    public int getAmbientSoundInterval() {
        return 300;     // Default is 80.
    }

    @Override
    protected float getSoundVolume() {
        return 0.666f;  // Default is 1.0f.
    }

    @Override
    protected void playAttackSound() {
        this.makeSound(SoundEvents.RAVAGER_ATTACK);
    }

    /**
     * Performs per-tick operations of this Entity. Here, checks if this Entity has been unable to attack for
     *  at least 3 seconds. If it hasn't, rolls a chance each tick to blind and slow all nearby LivingEntities
     *  then teleport toward its affected target.
     */
    @Override
    public void tick() {
        super.tick();

        // Only run tick behavior on server.
        if (!(this.level() instanceof ServerLevel serverWorld)) return;

        //Decrement aura counter, then if <= 0, do aura and reset counter.
        auraCounterTicks--;
        if (auraCounterTicks <= 0) {
            applyWeaknessHungerAura();
            auraCounterTicks = 60;
        }

        //If there is no target, ensure that ticksSinceLastAttack remains at 0 and return.
        if (this.getTarget() == null) {
            attackStrengthTicker = 0;
            return;
        }

        // If valid target, increment ticksSinceLastAttack and decrement abilityCooldownTicks.
        attackStrengthTicker++;
        abilityCooldownTicks--;

        // Then, if unable to attack for at least 3 seconds and ability not on cooldown, try special ability.
        if (attackStrengthTicker >= DEFAULT_LAST_ATTACK_TICKS_THRESHOLD && abilityCooldownTicks <= 0) {
            if (random.nextInt(100) == 0) {
                //Roll 1% chance each tick to perform special attack.
                blindAndSlowNearbyPlayers();
                increaseMovementSpeedTemporarily();

                // If not able to attack for 7.5 seconds, apply Wither also.
                if (attackStrengthTicker >= 150) {
                    witherAndDamageNearbyPlayers(serverWorld);
                }

                abilityCooldownTicks = DEFAULT_ABILITY_COOLDOWN_TICKS;
            }
        }
    }

    /**
     * Applies the Weakness and Hunger effect to all nearby PlayerEntities.
     */
    private void applyWeaknessHungerAura() {
        double radius = 10.0;
        double x = this.getX();
        double y = this.getY();
        double z = this.getZ();
        List<Player> players = this.level().getEntitiesOfClass(Player.class,
                new AABB(x - radius, y - radius, z - radius,
                        x + radius, y + radius, z + radius), EntitySelector.NO_CREATIVE_OR_SPECTATOR);

        for (Player player : players) {
            // Apply lowest-level Weakness and Hunger to each player for 10 seconds.
            player.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 200, 0,
                    false, false, true));
            player.addEffect(new MobEffectInstance(MobEffects.HUNGER, 200, 0,
                    false, false, true));
        }
    }

    /**
     * Applies Darkness and Slowness effects to all nearby PlayerEntities and plays aggressive sound.
     */
    private void blindAndSlowNearbyPlayers() {
        //Store xyz coordinates and get all entities within radius of this Entity.
        double radius = 24.0;
        double x = this.getX();
        double y = this.getY();
        double z = this.getZ();
        List<Player> players = this.level().getEntitiesOfClass(Player.class,
                new AABB(x - radius, y - radius, z - radius,
                        x + radius, y + radius, z + radius), EntitySelector.NO_CREATIVE_OR_SPECTATOR);

        //Play aggressive sound, then apply effects to all nearby LivingEntities.
        switch (random.nextInt(3)) {
            case 0 -> this.makeSound(SoundEvents.ENDERMAN_SCREAM);
            case 1 -> this.makeSound(SoundEvents.WARDEN_ANGRY);
            default -> this.makeSound(SoundEvents.RAVAGER_ROAR);
        }
        for (Player player : players) {
            // Blind (Darkness) and Slow ALL nearby players that are not creative or spectator mode.
            player.addEffect(new MobEffectInstance(MobEffects.SLOWNESS, 60, 1,
                    false, false, true));
            player.addEffect(new MobEffectInstance(MobEffects.DARKNESS, 60, 0,
                    false, false, true));
        }
    }

    /**
     * Applies Wither effect to each nearby PlayerEntity.
     * @param serverWorld Active ServerWorld this mob currently exists within.
     */
    private void witherAndDamageNearbyPlayers(ServerLevel serverWorld) {
        //Store xyz coordinates and get all entities within radius of this Entity.
        double radius = 24.0;
        double x = this.getX();
        double y = this.getY();
        double z = this.getZ();
        List<Player> players = this.level().getEntitiesOfClass(Player.class,
                new AABB(x - radius, y - radius, z - radius,
                        x + radius, y + radius, z + radius), EntitySelector.NO_CREATIVE_OR_SPECTATOR);

//        // Wither effect intensity should scale up with duration.
//        // Should increase by one level per 6 seconds, -1 to apply intensity 0 at first.
//        int intensity = (ticksSinceLastAttack / 120) - 1;
//        intensity = Math.min(intensity, 2);     // Cap at intensity 2 (Level 3 Wither).

        // Apply effect to each player, strength clamped to mob health percentage.
        int intensity = calcWitherStrength();
        for (Player player : players) {
            player.addEffect(new MobEffectInstance(MobEffects.WITHER, 81, intensity,
                    false, false, true));

            // Also deal instant damage for parity with Obsidian Golem special attack.
            player.hurtServer(serverWorld, this.damageSources().mobAttack(this), 5.0f);
        }
    }

    /**
     * Increases Entity's movement speed for a duration using the Speed status effect.
     */
    private void increaseMovementSpeedTemporarily() {
        // Add Speed effect at Level 3 (20% * level), so 60% bonus speed, for 1200 ticks (60 seconds).
        // This will upgrade an existing lower-strength Speed effect, if active.
        this.addEffect(new MobEffectInstance(MobEffects.SPEED, 1200, 2,
                false, false));

        // Also remove Slowness effect if active.
        this.removeEffect(MobEffects.SLOWNESS);
    }

    /**
     * Resets Entity's movement speed back to base by removing the Speed status effect.
     */
    private void resetMovementSpeed() {
        //Remove Speed status effect, if active.
        if (this.hasEffect(MobEffects.SPEED)) {
            this.removeEffect(MobEffects.SPEED);
        }
    }


    /**
     * Gets whether this Entity can receive a specific status effect.
     * @param effect Effect attempting to be applied
     * @return Whether this Entity can receive the StatusEffect
     */
    @Override
    public boolean canBeAffected(MobEffectInstance effect) {
        Holder<MobEffect> statusEffect = effect.getEffect();
        return statusEffect != MobEffects.POISON && statusEffect != MobEffects.WITHER;
    }

    /**
     * Defines behavior for dropping loot from this Entity.
     * @param damageSource Source of damage that killed this Entity
     * @param causedByPlayer Whether this Entity was killed by a Player
     */
    @Override
    protected void dropFromLootTable(ServerLevel world, DamageSource damageSource, boolean causedByPlayer) {
        if (!causedByPlayer) return;

        //Below is copied almost entirely from WitherEntity.
        ItemEntity itemEntity = this.spawnAtLocation(world, ModItems.HANDFUL_OF_STARDUST);
        if (itemEntity != null) {
            itemEntity.setExtendedLifetime();    //This is extended lifetime before despawn
        }

        //Will drop a trophy item if slain with specific circumstances.
        if (damageSource.getEntity() instanceof Player playerEntity) {
            ItemStack heldItem = playerEntity.getItemBySlot(EquipmentSlot.MAINHAND);

            //If heldItem is an endgame-tier Hoe.
            if (heldItem.getItem() == Items.NETHERITE_HOE ||
                    heldItem.getItem() == ModItems.COBALT_STEEL_HOE ||
                    heldItem.getItem() == ModItems.INFUSED_GEMSTONE_HOE ||
                    heldItem.getItem() == ModItems.TUNGSTEN_CARBIDE_HOE) {
                //Drop Obsidian Dust collector item and give it a long despawn delay.
                ItemEntity collectorItem = this.spawnAtLocation(world, ModItems.TROPHY_OMINOUS_LETTER);
                if (collectorItem != null) {
                    collectorItem.setExtendedLifetime();
                }
            }
        }

    }

    /**
     * Gets experience drop from this Entity on death.
     * @return Amount of XP to drop
     */
    @Override
    public int getBaseExperienceReward(ServerLevel world) {
        return 50;
    }

    /**
     * Gets the distance that this Entity can fall without taking damage.
     * @return This Entity's safe falling distance
     */
    @Override
    public int getMaxFallDistance() {
        return 10;
    }

    /**
     * Gets whether this Entity is fire immune (true).
     * @return Whether this Entity is fire immune
     */
    @Override
    public boolean fireImmune() {
        return true;
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
                                                       ServerLevelAccessor serverWorldAccess, EntitySpawnReason spawnReason,
                                                       BlockPos blockPos, RandomSource random) {
        // Return false if biome at attempted spawn location is mushroom island.
        if (serverWorldAccess.getBiome(blockPos).is(Biomes.MUSHROOM_FIELDS)) return false;

        //Only allow spawn above a certain y-level (62 is sea level).
        if (blockPos.getY() < 62) {
            return false;
        }

        //Calls the default mob spawn check, ignoring light levels entirely. Use canSpawnInDark instead.
        return checkMonsterSpawnRules(mysteriousSpecterEntityType, serverWorldAccess, spawnReason, blockPos, random);
    }
}
