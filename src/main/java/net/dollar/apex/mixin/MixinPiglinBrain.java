package net.dollar.apex.mixin;

import net.dollar.apex.util.ModArmorMaterials;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.PiglinBrain;
import net.minecraft.item.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PiglinBrain.class)
public class MixinPiglinBrain {
    /**
     * Injects at tail of PiglinBrain.wearsGoldArmor() to allow items of the Gilded Bronze ArmorMaterial
     * 	to pacify piglins.
     * @param entity Entity whose items are being checked
     * @param cir Returnable callback info of Boolean type, used for replacing return value
     */
    @Inject(at = @At("TAIL"), method = "wearsGoldArmor", cancellable = true)
    private static void injectTailWearsGoldArmor(LivingEntity entity, CallbackInfoReturnable<Boolean> cir) {
        //HERE, checks if the player (entity) is wearing Gilded Bronze armor. If so, alters return value
        //  to true, following similar behavior to the original function.
        boolean value = false;

        Iterable<ItemStack> iterable = entity.getArmorItems();  //CAN RETURN EMPTY ITEMS WHICH ARE NOT ARMORITEMS
        for (ItemStack itemStack : iterable) {
            //Set value to true and break if at least one Gilded Bronze armor item is detected.
            Item item = itemStack.getItem();
            if (item instanceof ArmorItem armorItem && armorItem.getMaterial() == ModArmorMaterials.GILDED_BRONZE) {
                value = true;
                break;
            }
        }

        //Return the variable because only successfully supports setReturnValue() once.
        cir.setReturnValue(value);
    }
}
