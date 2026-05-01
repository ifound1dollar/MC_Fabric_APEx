package net.dollar.apex.util;

import net.minecraft.core.Holder;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.LivingEntity;

/**
 * Implemented by end-game armor tiers to determine whether certain effects can be applied to the wearer.
 */
public interface IFullSetEffectArmor {
    boolean canReceiveEffect(Holder<MobEffect> effect, LivingEntity wearer);
}
