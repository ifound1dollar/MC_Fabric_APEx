package net.dollar.simplegear.util;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;

/**
 * Implemented by end-game armor tiers to determine whether certain effects can be applied to the wearer.
 */
public interface IFullSetEffectArmor {
    boolean canReceiveEffect(StatusEffect effect, LivingEntity wearer);
}
