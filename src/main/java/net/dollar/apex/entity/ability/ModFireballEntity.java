package net.dollar.apex.entity.ability;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.projectile.SmallFireballEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class ModFireballEntity extends SmallFireballEntity {
    public ModFireballEntity(World world, LivingEntity owner, Vec3d velocity) {
        super(world, owner, velocity);
    }


    @Override
    protected void onEntityHit(EntityHitResult entityHitResult) {
        // This method is directly copied from SmallFireballEntity, but setOnFireFor() duration has
        //  been set to 4s (from 5s). Also, the local Entity variables have been renamed.
        super.onEntityHit(entityHitResult);

        if (this.getWorld() instanceof ServerWorld serverWorld) {
            Entity hitEntity = entityHitResult.getEntity();
            Entity ownerEntity = this.getOwner();

            int i = hitEntity.getFireTicks();
            hitEntity.setOnFireFor(4.0f);
            DamageSource damageSource = this.getDamageSources().fireball(this, ownerEntity);

            if (!hitEntity.damage(serverWorld, damageSource, 5.0F)) {
                hitEntity.setFireTicks(i);
            } else {
                EnchantmentHelper.onTargetDamaged(serverWorld, hitEntity, damageSource);
            }
        }
    }

    @Override
    protected void onBlockHit(BlockHitResult blockHitResult) {
        // Do nothing on block hit, do not want to start a fire (CAN WE MAKE NON-SPREADING FIRE???)
    }
}
