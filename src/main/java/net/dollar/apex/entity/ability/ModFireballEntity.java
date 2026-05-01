package net.dollar.apex.entity.ability;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.hurtingprojectile.SmallFireball;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;

public class ModFireballEntity extends SmallFireball {
    /**
     * Instantiates a ModFireballEntity object, which derives from SmallFireballEntity and
     *  behaves almost identically. Overridden to slightly reduce burn time when a fireball
     *  hits an Entity, and does not spawn fire when a fireball hits a block.
     * @param world World this fireball is being spawned within
     * @param owner LivingEntity spawning this fireball
     * @param velocity Velocity (on construction) of this fireball entity; remains constant
     */
    public ModFireballEntity(Level world, LivingEntity owner, Vec3 velocity) {
        super(world, owner, velocity);
    }


    @Override
    protected void onHitEntity(EntityHitResult entityHitResult) {
        // This method is directly copied from SmallFireballEntity, but setOnFireFor() duration has
        //  been set to 4s (from 5s). Also, the local Entity variables have been renamed.
        super.onHitEntity(entityHitResult);

        if (this.level() instanceof ServerLevel serverWorld) {
            Entity hitEntity = entityHitResult.getEntity();
            Entity ownerEntity = this.getOwner();

            int i = hitEntity.getRemainingFireTicks();
            hitEntity.igniteForSeconds(4.0f);
            DamageSource damageSource = this.damageSources().fireball(this, ownerEntity);

            if (!hitEntity.hurtServer(serverWorld, damageSource, 5.0F)) {
                hitEntity.setRemainingFireTicks(i);
            } else {
                EnchantmentHelper.doPostAttackEffects(serverWorld, hitEntity, damageSource);
            }
        }
    }

    @Override
    protected void onHitBlock(BlockHitResult blockHitResult) {
        // Do nothing on block hit, do not want to start a fire (CAN WE MAKE NON-SPREADING FIRE???)
    }
}
