package net.dollar.simplegear.mixin;

import net.dollar.simplegear.ModMain;
import net.dollar.simplegear.util.IFullSetEffectArmor;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public abstract class MixinLivingEntity {
    @Shadow public abstract ItemStack getEquippedStack(EquipmentSlot var1);

    /**
     * Injects at tail of LivingEntity.canHaveStatusEffect() to prevent receiving certain status effects while
     * 	wearing a full set of effect-preventing end-game tier armor.
     * @param effect Effect instance trying to be applied
     * @param cir Returnable callback info of Boolean type, used for replacing return value
     */
    @Inject(at = @At("TAIL"), method = "canHaveStatusEffect", cancellable = true)
    private void injectTailCanHaveStatusEffect(StatusEffectInstance effect, CallbackInfoReturnable<Boolean> cir) {
        //HERE, checks if the LivingEntity is wearing a full set of end-game tier armor, which may prevent
        //  certain status effects from being applied. If so, check if the StatusEffectInstance matches.
        boolean value = true;   //Default allow any status effect

        //If the equipped chestplate item is an effect-preventing armor, check if the effect is valid.
        if (this.getEquippedStack(EquipmentSlot.CHEST).getItem() instanceof IFullSetEffectArmor specialArmor) {
            value = specialArmor.canReceiveEffect(effect.getEffectType());

            ModMain.LOGGER.info("Return value: " + value);
        }

        //Return the variable because only successfully supports setReturnValue() once.
        cir.setReturnValue(value);
    }
}
