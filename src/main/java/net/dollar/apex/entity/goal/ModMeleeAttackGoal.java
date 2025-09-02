package net.dollar.apex.entity.goal;

import java.util.EnumSet;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.ai.pathing.Path;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.predicate.entity.EntityPredicates;
import net.minecraft.util.Hand;

public class ModMeleeAttackGoal extends Goal {
    protected final PathAwareEntity mob;
    private final double speed;
    private final boolean pauseWhenMobIdle;
    private Path path;
    private double targetX;
    private double targetY;
    private double targetZ;
    private int updateCountdownTicks;
    private int cooldown;
    private final int attackIntervalTicks;
    private long lastUpdateTime;
    private final long MAX_ATTACK_TIME;

    /**
     * Instantiates a new ModMeleeAttackGoal, which is functionally similar to MeleeAttackGoal but
     *  supports explicitly setting attack speed on construction.
     * @param mob PathAwareEntity that this Goal is attached to
     * @param speed Movement speed of mob when actively attacking a target
     * @param pauseWhenMobIdle Whether to pause while the mob is idle (should be false)
     * @param attackIntervalTicks Minimum number of ticks between each attack attempt (attack speed)
     */
    public ModMeleeAttackGoal(PathAwareEntity mob, double speed, boolean pauseWhenMobIdle, int attackIntervalTicks) {
        this.mob = mob;
        this.speed = speed;
        this.pauseWhenMobIdle = pauseWhenMobIdle;
        this.setControls(EnumSet.of(Goal.Control.MOVE, Goal.Control.LOOK));

        this.attackIntervalTicks = attackIntervalTicks;
        this.MAX_ATTACK_TIME = attackIntervalTicks;
    }



    /**
     * Returns whether this Goal is ready to be used. Returns true if the mob has a valid target
     *  which it can attack.
     * @return Whether the Goal can be used.
     */
    @Override
    public boolean canStart() {
        long l = this.mob.getWorld().getTime();
        if (l - this.lastUpdateTime < MAX_ATTACK_TIME) {
            return false;
        } else {
            this.lastUpdateTime = l;
            LivingEntity livingEntity = this.mob.getTarget();
            if (livingEntity == null) {
                return false;
            } else if (!livingEntity.isAlive()) {
                return false;
            } else {
                this.path = this.mob.getNavigation().findPathTo(livingEntity, 0);
                return ((this.path != null) || this.mob.isInAttackRange(livingEntity));
            }
        }
    }

    /**
     * Gets whether this Goal can continue being used, returning true if the mob still
     *  has an attackable target.
     * @return Whether this Goal can continue being used.
     */
    @Override
    public boolean shouldContinue() {
        LivingEntity livingEntity = this.mob.getTarget();
        if (livingEntity == null) {
            return false;
        } else if (!livingEntity.isAlive()) {
            return false;
        } else if (!this.pauseWhenMobIdle) {
            return !this.mob.getNavigation().isIdle();
        } else {
            return this.mob.isInPositionTargetRange(livingEntity.getBlockPos())
                    && !(livingEntity instanceof PlayerEntity playerEntity
                    && (playerEntity.isSpectator() || playerEntity.isCreative()));
        }
    }

    /**
     * Starts executing this Goal. Begins movement toward the target and begins attacking it.
     */
    @Override
    public void start() {
        this.mob.getNavigation().startMovingAlong(this.path, this.speed);
        this.mob.setAttacking(true);
        this.updateCountdownTicks = 0;
        this.cooldown = 0;
    }

    /**
     * Stops execution of this Goal. Nullifies target, stops attacking, and stops navigation.
     */
    @Override
    public void stop() {
        LivingEntity livingEntity = this.mob.getTarget();
        if (!EntityPredicates.EXCEPT_CREATIVE_OR_SPECTATOR.test(livingEntity)) {
            this.mob.setTarget(null);
        }

        this.mob.setAttacking(false);
        this.mob.getNavigation().stop();
    }

    /**
     * Gets whether this Goal should run every tick. Overridden to always return true.
     * @return True if this Goal should run every tick (overridden to always return true)
     */
    @Override
    public boolean shouldRunEveryTick() {
        return true;
    }

    /**
     * Runs per-tick operations for this Goal. Used to move toward and actually attack
     *  the target.
     */
    @Override
    public void tick() {
        LivingEntity livingEntity = this.mob.getTarget();
        if (livingEntity != null) {
            this.mob.getLookControl().lookAt(livingEntity, 30.0F, 30.0F);
            this.updateCountdownTicks = Math.max(this.updateCountdownTicks - 1, 0);
            if ((this.pauseWhenMobIdle || this.mob.getVisibilityCache().canSee(livingEntity))
                    && this.updateCountdownTicks <= 0
                    && (
                    this.targetX == 0.0 && this.targetY == 0.0 && this.targetZ == 0.0
                            || livingEntity.squaredDistanceTo(this.targetX, this.targetY, this.targetZ) >= 1.0
                            || this.mob.getRandom().nextFloat() < 0.05F
            )) {
                this.targetX = livingEntity.getX();
                this.targetY = livingEntity.getY();
                this.targetZ = livingEntity.getZ();
                this.updateCountdownTicks = 4 + this.mob.getRandom().nextInt(7);
                double d = this.mob.squaredDistanceTo(livingEntity);
                if (d > 1024.0) {
                    this.updateCountdownTicks += 10;
                } else if (d > 256.0) {
                    this.updateCountdownTicks += 5;
                }

                if (!this.mob.getNavigation().startMovingTo(livingEntity, this.speed)) {
                    this.updateCountdownTicks += 15;
                }

                this.updateCountdownTicks = this.getTickCount(this.updateCountdownTicks);
            }

            this.cooldown = Math.max(this.cooldown - 1, 0);
            this.attack(livingEntity);
        }
    }

    /**
     * Actually attempts to attack the target, checking whether an attack is possible and
     *  then resetting attack cooldown and actually attacking if true.
     * @param target The LivingEntity being attacked
     */
    protected void attack(LivingEntity target) {
        if (this.canAttack(target)) {
            this.resetCooldown();
            this.mob.swingHand(Hand.MAIN_HAND);
            this.mob.tryAttack(getServerWorld(this.mob), target);
        }
    }

    /**
     * Resets the cooldown of this Goal. The cooldown of this Goal is the attack cooldown.
     */
    protected void resetCooldown() {
        this.cooldown = this.getTickCount(attackIntervalTicks);
    }

    /**
     * Gets whether the cooldown of this Goal has completed fully. The cooldown of this Goal
     *  is the attack cooldown.
     * @return True if the cooldown has completed and an attack is ready to be used again
     */
    protected boolean isCooledDown() {
        return this.cooldown <= 0;
    }

    /**
     * Gets whether the mob this Goal is attached to can attack the passed-in LivingEntity.
     * @param target The LivingEntity in question
     * @return True if the LivingEntity can be attacked (Goal not on cooldown and target is visible and in range)
     */
    protected boolean canAttack(LivingEntity target) {
        return this.isCooledDown() && this.mob.isInAttackRange(target) && this.mob.getVisibilityCache().canSee(target);
    }
}
