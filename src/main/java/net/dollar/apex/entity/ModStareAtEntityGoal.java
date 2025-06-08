package net.dollar.apex.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.TargetPredicate;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.predicate.entity.EntityPredicates;
import org.jetbrains.annotations.Nullable;

import java.util.EnumSet;

public class ModStareAtEntityGoal extends Goal {
    protected final MobEntity mob;
    @Nullable
    protected Entity target;
    protected final float range;
    protected final Class<? extends LivingEntity> targetType;
    protected final TargetPredicate targetPredicate;

    public ModStareAtEntityGoal(MobEntity mob, Class<? extends LivingEntity> targetType, float range) {
        this.mob = mob;
        this.targetType = targetType;
        this.range = range;
        setControls(EnumSet.of(Control.LOOK));
        if (targetType == PlayerEntity.class) {
            targetPredicate = TargetPredicate.createNonAttackable().setBaseMaxDistance(range).setPredicate((entity) -> EntityPredicates.rides(mob).test(entity));
        } else {
            targetPredicate = TargetPredicate.createNonAttackable().setBaseMaxDistance(range);
        }
    }



    public boolean canStart() {
        if (this.mob.getTarget() != null) {
            this.target = this.mob.getTarget();
        }

        if (this.targetType == PlayerEntity.class) {
            target = mob.getWorld().getClosestPlayer(targetPredicate, mob, mob.getX(), mob.getEyeY(), mob.getZ());
        } else {
            target = mob.getWorld().getClosestEntity(mob.getWorld().getEntitiesByClass(
                    targetType, mob.getBoundingBox().expand(range, 3.0F, range),
                    (livingEntity) -> true), targetPredicate,
                    mob, mob.getX(), mob.getEyeY(), mob.getZ());
        }

        return target != null;
    }

    public boolean shouldContinue() {
        // Should always continue unless the target is null, dead, or too far.
        if (target == null || !target.isAlive()) return false;

        // Return the inverse of whether the target mob is within range (false if out of range).
        return !(mob.squaredDistanceTo(target) > (double) (range * range));
    }

    public void start() {
        // Filler comment
    }

    public void stop() {
        target = null;
    }

    public void tick() {
        if (target != null && target.isAlive()) {
            double lookTargetEyeY = target.getEyeY();
            mob.getLookControl().lookAt(target.getX(), lookTargetEyeY, target.getZ());
        }
    }
}
