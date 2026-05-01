package net.dollar.apex.entity.custom;

import net.dollar.apex.entity.ability.ModFireballEntity;
import net.dollar.apex.entity.goal.ModMeleeAttackGoal;
import net.dollar.apex.item.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
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
import net.minecraft.world.entity.Crackiness;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityEvent;
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
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.ResetUniversalAngerTargetGoal;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.NaturalSpawner;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

import java.util.List;

import static net.minecraft.world.entity.Crackiness.GOLEM;

public class ObsidianGolemEntity extends Monster implements NeutralMob {
    private int attackTicksLeft;
    private static final UniformInt ANGER_TIME_RANGE = TimeUtil.rangeOfSeconds(20, 39);
    private long angerTime;
    @Nullable
    private EntityReference<LivingEntity> angryAt;

    private int attackStrengthTicker = 0;
    private static final int DEFAULT_LAST_ATTACK_TICKS_THRESHOLD = 100;
    private int abilityCooldownTicks;
    private static final int DEFAULT_ABILITY_COOLDOWN_TICKS = 100;

    public ObsidianGolemEntity(EntityType<? extends Monster> entityType, Level world) {
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
        this.goalSelector.addGoal(9, new WaterAvoidingRandomStrollGoal(this, 0.6));

        this.targetSelector.addGoal(2, new HurtByTargetGoal(this));
        this.targetSelector.addGoal(4, new ResetUniversalAngerTargetGoal<>(this, false));
    }

    /**
     * Creates mob attributes like HEALTH, FOLLOW RANGE, etc.
     * @return The newly created DefaultAttributeContainer
     */
    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 120)
                .add(Attributes.ARMOR, 20)
                .add(Attributes.MOVEMENT_SPEED, 0.25)
                .add(Attributes.KNOCKBACK_RESISTANCE, 1.0)
                .add(Attributes.ATTACK_DAMAGE, 12.0)
                .add(Attributes.ATTACK_KNOCKBACK, 1.0)
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
        return super.getAttackBoundingBox(attackRange).inflate(0.2d, 0.0d, 0.2d);
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
        if (this.attackTicksLeft > 0) {
            --this.attackTicksLeft;
        }

        if (!this.level().isClientSide()) {
            this.updatePersistentAnger((ServerLevel)this.level(), true);
        }
    }

    @Override
    public void addAdditionalSaveData(ValueOutput view) {
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
     * * @param world Server World this Entity exists in
     * @param target Target being attacked by this Entity
     * @return Whether the attack was successfully performed
     */
    @Override
    public boolean doHurtTarget(ServerLevel world, Entity target) {
        attackStrengthTicker = 0;
        attackTicksLeft = 10;

        // If default attack operation was successful, do special attack effects.
        if (super.doHurtTarget(world, target)) {
            attackStrengthTicker = 0;

            //After applying damage effects and knockback, do special Obsidian Golem attack behaviors.
            if (target instanceof LivingEntity livingEntity) {
                // Roll chance to set target on fire based on % missing HP (loosely corresponds to crackiness).
                if (random.nextFloat() > (this.getHealth() / this.getMaxHealth()) - 0.25f) {
                    livingEntity.igniteForSeconds(4);   // 100% chance at 25% Health because of -0.25f above
                }
            }

            return true;
        }

        return false;
    }

    /**
     * Performs taking damage operations, also updating visual cracks on the Entity.
     * @param world Server World this Entity exists in
     * @param source Source of damage being taken
     * @param amount Amount of damage to take
     * @return Whether taking damage operation was successful
     */
    @Override
    public boolean hurtServer(ServerLevel world, DamageSource source, float amount) {
        Crackiness.Level crack = this.getCrack();
        boolean bl = super.hurtServer(world, source, amount);
        if (bl && this.getCrack() != crack) {
            this.makeSound(SoundEvents.IRON_GOLEM_DAMAGE);
        }

        return bl;
    }

    /**
     * Gets Crack enum value based on % Health remaining.
     * @return Cracks.CrackLevel value that determines render visuals
     */
    public Crackiness.Level getCrack() {
        return GOLEM.byFraction(this.getHealth() / this.getMaxHealth());
    }

    @Override
    public void handleEntityEvent(byte status) {
        if (status == EntityEvent.START_ATTACKING) {
            this.attackTicksLeft = 10;
            this.makeSound(SoundEvents.IRON_GOLEM_ATTACK);
        } else {
            super.handleEntityEvent(status);
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
        return SoundEvents.IRON_GOLEM_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.IRON_GOLEM_DEATH;
    }

    @Override
    protected @Nullable SoundEvent getAmbientSound() {
        return switch (getRandom().nextInt(3)) {
            case 0 -> SoundEvents.RAVAGER_AMBIENT;
            case 1 -> SoundEvents.CREAKING_AMBIENT;
            case 2 -> SoundEvents.CREAKING_TWITCH;
            default -> null;    // Should never reach default case.
        };
    }

    @Override
    public int getAmbientSoundInterval() {
        return 300;     // Default is 80.
    }

    @Override
    protected void playAttackSound() {
        this.makeSound(SoundEvents.IRON_GOLEM_ATTACK);
    }

    @Override
    protected void playStepSound(BlockPos pos, BlockState state) {
        this.makeSound(SoundEvents.IRON_GOLEM_STEP);
    }

    /**
     * Gets whether this Entity can spawn at the passed-in WorldView location. Copied directly from
     *  IronGolemEntity.
     * @param world The current WorldView (includes world position)
     * @return Whether this Entity can spawn at a given location
     */
    @Override
    public boolean checkSpawnObstruction(LevelReader world) {
        BlockPos blockPos = this.blockPosition();
        BlockPos blockPos2 = blockPos.below();
        BlockState blockState = world.getBlockState(blockPos2);
        if (blockState.entityCanStandOn(world, blockPos2, this)) {
            for (int i = 1; i < 3; ++i) {
                BlockState blockState2;
                BlockPos blockPos3 = blockPos.above(i);
                if (NaturalSpawner.isValidEmptySpawnBlock(world, blockPos3, blockState2 = world.getBlockState(blockPos3), blockState2.getFluidState(), EntityType.IRON_GOLEM)) continue;
                return false;
            }
            return NaturalSpawner.isValidEmptySpawnBlock(world, blockPos, world.getBlockState(blockPos), Fluids.EMPTY.defaultFluidState(), EntityType.IRON_GOLEM) && world.isUnobstructed(this);
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
        if (!(this.level() instanceof ServerLevel serverWorld)) return;

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
                // Roll 1% chance each tick to perform special attack.
                rangedAttackNearbyPlayers(serverWorld);

                abilityCooldownTicks = DEFAULT_ABILITY_COOLDOWN_TICKS;
            }
        }
    }

    /**
     * Perform special ranged attack against all nearby PlayerEntities.
     * @param serverWorld Active ServerWorld this mob currently exists within.
     */
    private void rangedAttackNearbyPlayers(ServerLevel serverWorld) {
        double radius = 24.0;
        double x = this.getX();
        double y = this.getY();
        double z = this.getZ();
        List<Player> players = this.level().getEntitiesOfClass(Player.class,
                new AABB(x - radius, y - radius, z - radius,
                        x + radius, y + radius, z + radius), EntitySelector.NO_CREATIVE_OR_SPECTATOR);

        // Play aggressive sound at full volume, then perform special ability.
        this.makeSound(SoundEvents.RAVAGER_ROAR);
        for (Player player : players) {
            // Slow all nearby players at Level 3 intensity (45%) for 3s.
            player.addEffect(new MobEffectInstance(MobEffects.SLOWNESS, 60,
                    2, false, false, true));

            // Shoot a fireball at the player always, but if not visible, immediately damage and set on fire.
            shootFireballAtPlayer(player);
            if (!this.getSensing().hasLineOfSight(player)) {
                player.hurtServer(serverWorld, this.damageSources().noAggroMobAttack(this),
                        5.0f);      // Same damage as fireball.
                player.igniteForSeconds(4);     // Same duration as fireball.
            }
        }
    }

    /**
     * Attempts to shoot a fireball at a visible PlayerEntity. Pulled largely from blaze fireball goal.
     * @param player PlayerEntity to attempt to shoot the fireball at
     */
    private void shootFireballAtPlayer(Player player) {
        double xDist = player.getX() - this.getX();
        double yDist = player.getY(0.5) - this.getY(0.5);
        double zDist = player.getZ() - this.getZ();

        // Create fireball velocity vector, then create fireball and shoot it at the PlayerEntity.
        Vec3 vec3d = new Vec3(xDist, yDist, zDist);
        ModFireballEntity modFireballEntity = new ModFireballEntity(this.level(), this, vec3d.normalize());
        modFireballEntity.setPos(
                modFireballEntity.getX(),
                this.getY(0.5) + 0.5,
                modFireballEntity.getZ());
        this.level().addFreshEntity(modFireballEntity);
    }

    /**
     * Gets whether this Entity can receive a specific status effect.
     * @param effect Effect attempting to be applied
     * @return Whether this Entity can receive the StatusEffect
     */
    @Override
    public boolean canBeAffected(MobEffectInstance effect) {
        Holder<MobEffect> statusEffect = effect.getEffect();
        return statusEffect != MobEffects.POISON;
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
        ItemEntity itemEntity = this.spawnAtLocation(world, ModItems.MOLTEN_CORE);
        if (itemEntity != null) {
            itemEntity.setExtendedLifetime();    //This is extended lifetime before despawn
        }

        //Will drop a collector item if slain with specific circumstances.
        if (damageSource.getEntity() instanceof Player playerEntity) {
            ItemStack heldItem = playerEntity.getItemBySlot(EquipmentSlot.MAINHAND);

            //If heldItem is a Tungsten-Carbide Battleaxe with Sharpness V.
            HolderLookup.RegistryLookup<Enchantment> impl = this.level().registryAccess().lookupOrThrow(Registries.ENCHANTMENT);
            if (heldItem.getItem() == ModItems.TUNGSTEN_CARBIDE_BATTLEAXE &&
                    EnchantmentHelper.getItemEnchantmentLevel(impl.getOrThrow(Enchantments.SHARPNESS), heldItem) >= 5) {
                //Drop Obsidian Dust collector item and give it a long despawn delay.
                ItemEntity collectorItem = this.spawnAtLocation(world, ModItems.TROPHY_OBSIDIAN_DUST);
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
     * @param obsidianGolemEntityEntityType EntityType of ObsidianGolemEntity (this)
     * @param serverWorldAccess Active ServerWorldAccess (world)
     * @param spawnReason Type of mob spawn (NATURAL)
     * @param blockPos Position of spawn attempt being queried
     * @param random Convenient random
     * @return Whether the spawn attempt is successful
     */
    public static boolean checkObsidianGolemSpawnRules(EntityType<ObsidianGolemEntity> obsidianGolemEntityEntityType,
                                                       ServerLevelAccessor serverWorldAccess, EntitySpawnReason spawnReason,
                                                       BlockPos blockPos, RandomSource random) {
        // Return false if biome at attempted spawn location is mushroom island.
        if (serverWorldAccess.getBiome(blockPos).is(Biomes.MUSHROOM_FIELDS)) return false;

        //Only allow spawn below a certain y-level.
        int y = blockPos.getY();
        if (y >= 0) {
            return false;
        } else if (y >= -24) {
            // Effectively reduce spawn rate by 50% when above y=-24.
            return random.nextBoolean()
                    && checkMobSpawnRules(obsidianGolemEntityEntityType, serverWorldAccess, spawnReason, blockPos, random);
        }

        // Calls the default mob spawn check, ignoring light levels entirely. Use canSpawnInDark instead.
        return checkMobSpawnRules(obsidianGolemEntityEntityType, serverWorldAccess, spawnReason, blockPos, random);
    }
}
