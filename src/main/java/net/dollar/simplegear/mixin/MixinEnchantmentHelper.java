package net.dollar.simplegear.mixin;

import net.dollar.simplegear.util.IInfusedGemstoneItem;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnchantmentLevelEntry;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

@Mixin(EnchantmentHelper.class)
public class MixinEnchantmentHelper {
    /**
     * Injects at final return of EnchantmentHelper.getPossibleEntries() to add Mending to list IF the passed-in
     * 	item is an instanceof IInfusedGemstoneItem.
     * @param power Power of the enchanting source (typically table)
     * @param stack ItemStack attempting to be enchanted
     * @param treasureAllowed Whether treasure is allowed on this item
     * @param cir Returnable callback info of List:EnchantmentLevelEntry type, used for replacing return value
     */
    @Inject(at = @At("RETURN"), method = "getPossibleEntries", cancellable = true)
    private static void injectTailWearsGoldArmor(int power, ItemStack stack, boolean treasureAllowed,
                                                 CallbackInfoReturnable<List<EnchantmentLevelEntry>> cir) {
        //HERE, checks if the ItemStack trying to be enchanted is an Infused Gemstone item. If so, it should
        //  be allowed to receive Mending at an Enchanting Table.
        List<EnchantmentLevelEntry> list = cir.getReturnValue();

        //If the passed-in ItemStack is an Infused Gemstone item, add Mending as a list of valid enchantments.
        if (stack.getItem() instanceof IInfusedGemstoneItem) {
            //Theoretically, the list should not already have an instance of Mending within it (no duplicates here).
            list.add(new EnchantmentLevelEntry(Enchantments.MENDING, 1));   //Has to be level 1 apparently.
        }

        //Return the variable because only successfully supports setReturnValue() once.
        cir.setReturnValue(list);
    }
}
