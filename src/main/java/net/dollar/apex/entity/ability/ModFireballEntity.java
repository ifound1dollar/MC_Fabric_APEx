package net.dollar.apex.entity.ability;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.SmallFireballEntity;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class ModFireballEntity extends SmallFireballEntity {
    /**
     * Instantiates a ModFireballEntity object, which derives from SmallFireballEntity and
     *  behaves almost identically. Overridden to slightly reduce burn time when a fireball
     *  hits an Entity, and does not spawn fire when a fireball hits a block.
     * @param world World this fireball is being spawned within
     * @param owner LivingEntity spawning this fireball
     * @param velocity Velocity (on construction) of this fireball entity; remains constant
     */
    public ModFireballEntity(World world, LivingEntity owner, Vec3d velocity) {
        super(world, owner, velocity.x, velocity.y, velocity.z);
    }


    @Override
    protected void onEntityHit(EntityHitResult entityHitResult) {
        // This method is directly copied from SmallFireballEntity, but setOnFireFor() duration has
        //  been set to 4s (from 5s). Also, the local Entity variables have been renamed.
        super.onEntityHit(entityHitResult);

        if (this.getWorld().isClient) {
            return;
        }

        Entity entity = entityHitResult.getEntity();
        Entity entity2 = this.getOwner();
        int i = entity.getFireTicks();
        entity.setOnFireFor(4);

        if (!entity.damage(this.getDamageSources().fireball(this, entity2), 5.0f)) {
            entity.setFireTicks(i);
        } else if (entity2 instanceof LivingEntity) {
            this.applyDamageEffects((LivingEntity)entity2, entity);
        }
    }

    @Override
    protected void onBlockHit(BlockHitResult blockHitResult) {
        // Do nothing on block hit, do not want to start a fire (CAN WE MAKE NON-SPREADING FIRE???)
    }
}
