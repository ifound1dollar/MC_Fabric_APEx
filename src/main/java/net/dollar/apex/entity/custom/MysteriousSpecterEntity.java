package net.dollar.apex.entity.custom;

import net.dollar.apex.entity.goal.ModMeleeAttackGoal;
import net.dollar.apex.entity.goal.ModStareOrMoveGoal;
import net.dollar.apex.item.ModItems;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.goal.LookAroundGoal;
import net.minecraft.entity.ai.goal.RevengeGoal;
import net.minecraft.entity.ai.goal.UniversalAngerGoal;
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
import net.minecraft.predicate.entity.EntityPredicates;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.storage.ReadView;
import net.minecraft.storage.WriteView;
import net.minecraft.util.TimeHelper;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeKeys;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.UUID;

public class MysteriousSpecterEntity extends HostileEntity implements Angerable {
    private static final UniformIntProvider ANGER_TIME_RANGE = TimeHelper.betweenSeconds(20, 39);
    private int angerTime;
    @Nullable
    private UUID angryAt;

    private int ticksSinceLastAttack = 0;
    private static final int DEFAULT_LAST_ATTACK_TICKS_THRESHOLD = 100;
    private int auraCounterTicks = 60;
    private int abilityCooldownTicks;
    private static final int DEFAULT_ABILITY_COOLDOWN_TICKS = 100;

    public MysteriousSpecterEntity(EntityType<? extends HostileEntity> entityType, World world) {
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

        this.targetSelector.add(2, new RevengeGoal(this));
        this.targetSelector.add(4, new UniversalAngerGoal<>(this, false));

        this.goalSelector.add(3, new ModStareOrMoveGoal(this, PlayerEntity.class, 10.0f,
                0.666d, 0.001f));
    }

    /**
     * Creates mob attributes like HEALTH, FOLLOW RANGE, etc.
     * @return The newly created DefaultAttributeContainer
     */
    public static DefaultAttributeContainer.Builder createAttributes() {
        return MobEntity.createMobAttributes()
                .add(EntityAttributes.MAX_HEALTH, 120)
                .add(EntityAttributes.MOVEMENT_SPEED, 0.25)
                .add(EntityAttributes.KNOCKBACK_RESISTANCE, 1.0)
                .add(EntityAttributes.ATTACK_DAMAGE, 12.0)
                .add(EntityAttributes.ATTACK_KNOCKBACK, 0.5)
                .add(EntityAttributes.FOLLOW_RANGE, 30f)
                .add(EntityAttributes.STEP_HEIGHT, 1);
    }

    /**
     * Gets the attack Box for this mob. Overridden to expand on the X and Z axes slightly.
     * @return The attack Box for this mob.
     */
    @Override
    protected Box getAttackBox() {
        return super.getAttackBox().expand(0.1d, 0.0d, 0.1d);
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
    protected void writeCustomData(WriteView view) {
        super.writeCustomData(view);
        this.writeAngerToData(view);
    }

    @Override
    public void readCustomData(ReadView view) {
        super.readCustomData(view);
        this.readAngerFromData(this.getWorld(), view);
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
     * @param target Target being attacked by this Entity
     * @return Whether the attack was successfully performed
     */
    @Override
    public boolean tryAttack(ServerWorld world, Entity target) {
        ticksSinceLastAttack = 0;

        // If default attack operation was successful, do special attack effects.
        if (super.tryAttack(world, target)) {
            //Immediately reset attack counter and movement speed buff.
            resetMovementSpeed();

            //After applying damage effects and knockback, do special Mysterious Specter attack behaviors.
            if (target instanceof LivingEntity livingEntity) {
                //Roll 50% chance to Wither the target here.
                if (random.nextInt(100) < 50) {
                    //Increase Wither level based on missing Health (split into 3 parts, 33% HP each).
                    livingEntity.addStatusEffect(
                            new StatusEffectInstance(StatusEffects.WITHER, 81, calcWitherStrength(),
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
        return SoundEvents.ENTITY_BLAZE_AMBIENT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.ENTITY_BLAZE_DEATH;
    }

    @Override
    protected @Nullable SoundEvent getAmbientSound() {
        return switch (getRandom().nextInt(5)) {
            case 0 -> SoundEvents.ENTITY_BLAZE_AMBIENT;
            case 1 -> SoundEvents.ENTITY_HUSK_AMBIENT;
            case 2 -> SoundEvents.ENTITY_ZOMBIE_VILLAGER_AMBIENT;
            case 3 -> SoundEvents.ENTITY_GHAST_AMBIENT;
            case 4 -> SoundEvents.ENTITY_WARDEN_TENDRIL_CLICKS;
            default -> null;    // Should never reach default case.
        };
    }

    @Override
    public int getMinAmbientSoundDelay() {
        return 300;     // Default is 80.
    }

    @Override
    protected float getSoundVolume() {
        return 0.666f;  // Default is 1.0f.
    }

    @Override
    protected void playAttackSound() {
        this.playSound(SoundEvents.ENTITY_RAVAGER_ATTACK);
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
        if (!(this.getWorld() instanceof ServerWorld serverWorld)) return;

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

        // If valid target, increment ticksSinceLastAttack and decrement abilityCooldownTicks.
        ticksSinceLastAttack++;
        abilityCooldownTicks--;

        // Then, if unable to attack for at least 3 seconds and ability not on cooldown, try special ability.
        if (ticksSinceLastAttack >= DEFAULT_LAST_ATTACK_TICKS_THRESHOLD && abilityCooldownTicks <= 0) {
            if (random.nextInt(100) == 0) {
                //Roll 1% chance each tick to perform special attack.
                blindAndSlowNearbyPlayers();
                increaseMovementSpeedTemporarily();

                // If not able to attack for 7.5 seconds, apply Wither also.
                if (ticksSinceLastAttack >= 150) {
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
        List<PlayerEntity> players = this.getWorld().getEntitiesByClass(PlayerEntity.class,
                new Box(x - radius, y - radius, z - radius,
                        x + radius, y + radius, z + radius), EntityPredicates.EXCEPT_CREATIVE_OR_SPECTATOR);

        for (PlayerEntity player : players) {
            // Apply lowest-level Weakness and Hunger to each player for 10 seconds.
            player.addStatusEffect(new StatusEffectInstance(StatusEffects.WEAKNESS, 200, 0,
                    false, false, true));
            player.addStatusEffect(new StatusEffectInstance(StatusEffects.HUNGER, 200, 0,
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
        List<PlayerEntity> players = this.getWorld().getEntitiesByClass(PlayerEntity.class,
                new Box(x - radius, y - radius, z - radius,
                        x + radius, y + radius, z + radius), EntityPredicates.EXCEPT_CREATIVE_OR_SPECTATOR);

        //Play aggressive sound, then apply effects to all nearby LivingEntities.
        switch (random.nextInt(3)) {
            case 0 -> this.playSound(SoundEvents.ENTITY_ENDERMAN_SCREAM);
            case 1 -> this.playSound(SoundEvents.ENTITY_WARDEN_ANGRY);
            default -> this.playSound(SoundEvents.ENTITY_RAVAGER_ROAR);
        }
        for (PlayerEntity player : players) {
            // Blind (Darkness) and Slow ALL nearby players that are not creative or spectator mode.
            player.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 60, 1,
                    false, false, true));
            player.addStatusEffect(new StatusEffectInstance(StatusEffects.DARKNESS, 60, 0,
                    false, false, true));
        }
    }

    /**
     * Applies Wither effect to each nearby PlayerEntity.
     * @param serverWorld Active ServerWorld this mob currently exists within.
     */
    private void witherAndDamageNearbyPlayers(ServerWorld serverWorld) {
        //Store xyz coordinates and get all entities within radius of this Entity.
        double radius = 24.0;
        double x = this.getX();
        double y = this.getY();
        double z = this.getZ();
        List<PlayerEntity> players = this.getWorld().getEntitiesByClass(PlayerEntity.class,
                new Box(x - radius, y - radius, z - radius,
                        x + radius, y + radius, z + radius), EntityPredicates.EXCEPT_CREATIVE_OR_SPECTATOR);

//        // Wither effect intensity should scale up with duration.
//        // Should increase by one level per 6 seconds, -1 to apply intensity 0 at first.
//        int intensity = (ticksSinceLastAttack / 120) - 1;
//        intensity = Math.min(intensity, 2);     // Cap at intensity 2 (Level 3 Wither).

        // Apply effect to each player, strength clamped to mob health percentage.
        int intensity = calcWitherStrength();
        for (PlayerEntity player : players) {
            player.addStatusEffect(new StatusEffectInstance(StatusEffects.WITHER, 81, intensity,
                    false, false, true));

            // Also deal instant damage for parity with Obsidian Golem special attack.
            player.damage(serverWorld, this.getDamageSources().mobAttack(this), 5.0f);
        }
    }

    /**
     * Increases Entity's movement speed for a duration using the Speed status effect.
     */
    private void increaseMovementSpeedTemporarily() {
        // Add Speed effect at Level 3 (20% * level), so 60% bonus speed, for 1200 ticks (60 seconds).
        // This will upgrade an existing lower-strength Speed effect, if active.
        this.addStatusEffect(new StatusEffectInstance(StatusEffects.SPEED, 1200, 2,
                false, false));

        // Also remove Slowness effect if active.
        this.removeStatusEffect(StatusEffects.SLOWNESS);
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
        RegistryEntry<StatusEffect> statusEffect = effect.getEffectType();
        return statusEffect != StatusEffects.POISON && statusEffect != StatusEffects.WITHER;
    }

    /**
     * Defines behavior for dropping loot from this Entity.
     * @param damageSource Source of damage that killed this Entity
     * @param causedByPlayer Whether this Entity was killed by a Player
     */
    @Override
    protected void dropLoot(ServerWorld world, DamageSource damageSource, boolean causedByPlayer) {
        if (!causedByPlayer) return;

        //Below is copied almost entirely from WitherEntity.
        ItemEntity itemEntity = this.dropItem(world, ModItems.HANDFUL_OF_STARDUST);
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
                ItemEntity collectorItem = this.dropItem(world, ModItems.TROPHY_OMINOUS_LETTER);
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
    public int getExperienceToDrop(ServerWorld world) {
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
        // Return false if biome at attempted spawn location is mushroom island.
        if (serverWorldAccess.getBiome(blockPos).matchesKey(BiomeKeys.MUSHROOM_FIELDS)) return false;

        //Only allow spawn above a certain y-level (62 is sea level).
        if (blockPos.getY() < 62) {
            return false;
        }

        //Calls the default mob spawn check, ignoring light levels entirely. Use canSpawnInDark instead.
        return canSpawnInDark(mysteriousSpecterEntityType, serverWorldAccess, spawnReason, blockPos, random);
    }

    /**
     * Gets whether this mob should not exist in peaceful mode. Returns true here.
     * @return Returns true if mob not allowed, false otherwise
     */
    @Override
    protected boolean isDisallowedInPeaceful() {
        return super.isDisallowedInPeaceful();
    }
}
