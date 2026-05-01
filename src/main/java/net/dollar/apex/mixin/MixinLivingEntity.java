package net.dollar.apex.mixin;

import net.dollar.apex.util.IFullSetEffectArmor;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public abstract class MixinLivingEntity extends Entity {
    public MixinLivingEntity(EntityType<?> type, Level world) {
        super(type, world);
    }

    @Shadow public abstract ItemStack getItemBySlot(EquipmentSlot var1);

    /**
     * Injects at tail of LivingEntity.canHaveStatusEffect() to prevent receiving certain status effects while
     * 	wearing a full set of effect-preventing end-game tier armor.
     * @param effect Effect instance trying to be applied
     * @param cir Returnable callback info of Boolean type, used for replacing return value
     */
    @Inject(at = @At("TAIL"), method = "canBeAffected", cancellable = true)
    private void injectTailCanHaveStatusEffect(MobEffectInstance effect, CallbackInfoReturnable<Boolean> cir) {
        //HERE, checks if the LivingEntity is wearing a full set of end-game tier armor, which may prevent
        //  certain status effects from being applied. If so, check if the StatusEffectInstance matches.
        boolean value = true;   //Default allow any status effect

        //Implement base function checks for consistency.
        if (this.getType().is(EntityTypeTags.IMMUNE_TO_INFESTED)) {
            value = !effect.is(MobEffects.INFESTED);
        }
        if (this.getType().is(EntityTypeTags.IMMUNE_TO_OOZING)) {
            value = !effect.is(MobEffects.OOZING);
        }
        if (this.getType().is(EntityTypeTags.IGNORES_POISON_AND_REGEN)) {
            if (effect.is(MobEffects.REGENERATION) || effect.is(MobEffects.POISON)) {
                value = false;
            }
        }

        //If value has been set to false already, must remain false because is not valid otherwise. Then,
        // if the equipped chestplate item is an effect-preventing armor, check if the effect is valid.
        if (value && this.getItemBySlot(EquipmentSlot.CHEST).getItem() instanceof IFullSetEffectArmor specialArmor) {
            value = specialArmor.canReceiveEffect(effect.getEffect(), (LivingEntity)(Object)this);

            //ModMain.LOGGER.info("Return value: " + value);
        }

        //Return the variable because only successfully supports setReturnValue() once.
        cir.setReturnValue(value);
    }
}
