package net.dollar.apex.entity.goal;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.FuzzyTargeting;
import net.minecraft.entity.ai.NoPenaltyTargeting;
import net.minecraft.entity.ai.TargetPredicate;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.predicate.entity.EntityPredicates;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.EnumSet;

/**
 * This goal prioritizes staring at a target class when within range, else will do random strolls.
 *  Based off of LookAtEntityGoal and WanderAroundFarGoal.
 */
public class ModStareOrMoveGoal extends Goal {
    private enum State { IDLE, LOOKING, MOVING }

    protected final PathAwareEntity mob;
    private State state = State.IDLE;

    @Nullable
    protected Entity lookTarget;
    protected final float lookRange;
    private final double lookRangeSquared;
    protected final Class<? extends LivingEntity> lookTargetType;
    protected final TargetPredicate lookTargetPredicate;

    public static final int DEFAULT_INTERVAL = 120;
    protected double wantedX;
    protected double wantedY;
    protected double wantedZ;
    protected final double moveSpeedModifier;
    protected int interval;
    protected boolean moveIgnoringChance;
    private final boolean checkNoActionTime;
    private final float moveProbability;

    /**
     * Constructs a new ModStareOrMoveGoal instance, which causes the mob to look at a target
     *  mob type indefinitely when valid and in range, else the mob makes random strolls.
     * @param mob PathAwareEntity this Goal is attached to
     * @param targetType LivingEntity class to look at (mob type)
     * @param range Range that the mob will start looking at the target
     * @param moveSpeedModifier Speed modifier for mob movement
     * @param moveProbability Probability each tick that movement will start (only while not looking)
     */
    public ModStareOrMoveGoal(PathAwareEntity mob, Class<? extends LivingEntity> targetType, float range,
                              double moveSpeedModifier, float moveProbability) {
        this.mob = mob;
        setControls(EnumSet.of(Control.LOOK, Control.MOVE));

        // Looking
        this.lookTargetType = targetType;
        this.lookRange = range;
        this.lookRangeSquared = ((double)lookRange * lookRange);
        this.lookTargetPredicate = (targetType == PlayerEntity.class) ?
                TargetPredicate.createNonAttackable().setBaseMaxDistance(range)
                        .setPredicate(entity -> EntityPredicates.rides(mob).test(entity)) :
                TargetPredicate.createNonAttackable().setBaseMaxDistance(range);

        // Moving
        this.moveSpeedModifier = moveSpeedModifier;
        this.moveProbability = moveProbability;
        this.interval = DEFAULT_INTERVAL;
        this.checkNoActionTime = true;
    }



    /**
     * Returns whether this Goal is ready to be used. Returns true if the mob can do EITHER action,
     *  looking or moving. Prioritizes looking.
     * @return Whether the Goal can be used.
     */
    @Override
    public boolean canStart() {
        // Seek a valid look target, and return true immediately if one is found.
        if (findNewLookTarget()) return true;

        // Else if no valid look target, check if a random stroll can be started.
        if (!moveIgnoringChance) {
            if (checkNoActionTime && mob.getDespawnCounter() >= 100) {
                return false;
            }

            if (mob.getRandom().nextInt(toGoalTicks(interval)) != 0) {
                return false;
            }
        }

        // Get a random position for movement, and set fields if successful.
        Vec3d vec3 = getRandomWanterTarget();
        if (vec3 == null) {
            return false;
        } else {
            wantedX = vec3.x;
            wantedY = vec3.y;
            wantedZ = vec3.z;
            moveIgnoringChance = false;
            return true;
        }
    }

    /**
     * Seeks out a new lookTarget Entity, setting the field directly. Returns whether a new target
     *  was found.
     * @return True if a new lookTarget Entity was found.
     */
    protected boolean findNewLookTarget() {
        World world = mob.getWorld();

        if (lookTargetType == PlayerEntity.class) {
            lookTarget = world.getClosestPlayer(
                    lookTargetPredicate, mob, mob.getX(), mob.getEyeY(), mob.getZ());
        } else {
            lookTarget = world.getClosestEntity(world.getEntitiesByClass(lookTargetType,
                            mob.getBoundingBox().expand(lookRange, 3.0, lookRange),
                            p_148124_ -> true),
                    lookTargetPredicate, mob, mob.getX(), mob.getEyeY(), mob.getZ());
        }

        return (lookTarget != null);
    }

    /**
     * Checks whether the current lookTarget Entity is valid. Checks nullity, if alive, and distance.
     * @return True if the lookTarget Entity is valid.
     */
    protected boolean checkLookTargetIsValid() {
        return (lookTarget != null && lookTarget.isAlive()
                && (mob.squaredDistanceTo(lookTarget) <= lookRangeSquared));
    }

    /**
     * Gets a random 3-vector position to be used as a movement target. Prioritizes avoiding water,
     *  else chooses a default random position.
     * @return The generated 3-vector world position, or null if none is available.
     */
    @Nullable
    protected Vec3d getRandomWanterTarget() {
        // If in water, try to find position on land, else find default position.
        if (mob.isTouchingWater()) {
            Vec3d vec3 = FuzzyTargeting.find(mob, 15, 7);
            if (vec3 == null) {
                vec3 = NoPenaltyTargeting.find(mob, 10, 7);
            }

            return vec3;
        }

        // Else seek random position if passes probability check (water-avoiding only if fails, otherwise default).
        return (mob.getRandom().nextFloat() >= moveProbability) ?
                FuzzyTargeting.find(mob, 15, 7) :
                NoPenaltyTargeting.find(this.mob, 10, 7);
    }

    /**
     * Gets whether this Goal can continue being used, returning true if EITHER looking or
     *  movement can continue. Prioritizes looking.
     * @return Whether this Goal can continue being used.
     */
    @Override
    public boolean shouldContinue() {
        if (state == State.LOOKING) {
            // While LOOKING, return whether the lookAt target is still valid.
            return checkLookTargetIsValid();

        } else if (state == State.MOVING) {
            // If MOVING but a valid lookAt target was found, return false to stop MOVING and start LOOKING.
            if (findNewLookTarget()) {
                return false;
            } else {
                // Else MOVING but no valid look target, so return whether movement is done.
                return !mob.getNavigation().isIdle();
            }
        }

        // Else state is IDLE, so always return false (this method should never be called while IDLE).
        return false;
    }

    /**
     * Starts this Goal. If there is a valid look target, begins looking. If no valid look
     *  target, begins movement navigation.
     */
    @Override
    public void start() {
        // Start looking if valid lookAt target, else start movement navigation.
        if (lookTarget != null && lookTarget.isAlive()) {
            state = State.LOOKING;
        } else {
            mob.getNavigation().startMovingTo(wantedX, wantedY, wantedZ, moveSpeedModifier);
            state = State.MOVING;
        }
    }

    /**
     * Stops this Goal. Stops both looking and movement by default.
     */
    @Override
    public void stop() {
        // Nullify lookAt to stop looking, and stop navigation (movement).
        this.lookTarget = null;
        this.mob.getNavigation().stop();
        super.stop();

        state = State.IDLE;
    }

    /**
     * Runs per-tick operations for this Goal. This method is only necessary for looking, as
     *  movement uses a navigation component to move (not this Goal directly).
     */
    @Override
    public void tick() {
        // Only if state is LOOKING and lookAt Entity is valid.
        if (state == State.LOOKING && lookTarget != null && lookTarget.isAlive()) {
            double lookTargetEyeY = lookTarget.getEyeY();
            mob.getLookControl().lookAt(lookTarget.getX(), lookTargetEyeY, lookTarget.getZ());
        }
    }
}
