package net.dollar.apex.mixin;

import net.dollar.apex.util.IFullSetEffectArmor;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.tag.EntityTypeTags;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public abstract class MixinLivingEntity extends Entity {
    public MixinLivingEntity(EntityType<?> type, World world) {
        super(type, world);
    }

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

        //Implement base function checks for consistency.
        if (this.getType().isIn(EntityTypeTags.IMMUNE_TO_INFESTED)) {
            value = !effect.equals(StatusEffects.INFESTED);
        }
        if (this.getType().isIn(EntityTypeTags.IMMUNE_TO_OOZING)) {
            value = !effect.equals(StatusEffects.OOZING);
        }
        if (this.getType().isIn(EntityTypeTags.IGNORES_POISON_AND_REGEN)) {
            if (effect.equals(StatusEffects.REGENERATION) || effect.equals(StatusEffects.POISON)) {
                value = false;
            }
        }

        //If value has been set to false already, must remain false because is not valid otherwise. Then,
        // if the equipped chestplate item is an effect-preventing armor, check if the effect is valid.
        if (value && this.getEquippedStack(EquipmentSlot.CHEST).getItem() instanceof IFullSetEffectArmor specialArmor) {
            value = specialArmor.canReceiveEffect(effect.getEffectType(), (LivingEntity)(Object)this);

            //ModMain.LOGGER.info("Return value: " + value);
        }

        //Return the variable because only successfully supports setReturnValue() once.
        cir.setReturnValue(value);
    }
}
