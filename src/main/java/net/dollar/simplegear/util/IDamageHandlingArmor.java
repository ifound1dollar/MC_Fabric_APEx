package net.dollar.simplegear.util;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;

/**
 * Implemented by end-game armor tiers to modify damage taken from specific sources.
 */
public interface IDamageHandlingArmor {
    default float onDamaged(LivingEntity entity, EquipmentSlot slot, DamageSource source, float amount) {
        return amount;
    }
}
