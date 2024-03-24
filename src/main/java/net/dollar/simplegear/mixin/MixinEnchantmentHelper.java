package net.dollar.simplegear.mixin;

import net.dollar.simplegear.item.custom.ModBattleaxeItem;
import net.dollar.simplegear.util.IInfusedGemstoneItem;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnchantmentLevelEntry;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.ArrayList;
import java.util.List;

@Mixin(EnchantmentHelper.class)
public class MixinEnchantmentHelper {
    /**
     * Injects at final return of EnchantmentHelper.getPossibleEntries() to add Mending and weapon enchantments
     *  to list IF the passed-in item is Infused Gemstone or a Battleaxe (respectively).
     * @param power Power of the enchanting source (typically table)
     * @param stack ItemStack attempting to be enchanted
     * @param treasureAllowed Whether treasure is allowed on this item
     * @param cir Returnable callback info of List:EnchantmentLevelEntry type, used for replacing return value
     */
    @Inject(at = @At("RETURN"), method = "getPossibleEntries", cancellable = true)
    private static void injectReturnGetPossibleEntries(int power, ItemStack stack, boolean treasureAllowed,
                                                       CallbackInfoReturnable<List<EnchantmentLevelEntry>> cir) {
        //HERE, checks if the ItemStack trying to be enchanted is an Infused Gemstone item. If so, it should
        //  be allowed to receive Mending at an Enchanting Table.
        List<EnchantmentLevelEntry> list = cir.getReturnValue();

        //If the passed-in ItemStack is an Infused Gemstone item, add Mending as a list of valid enchantments.
        if (stack.getItem() instanceof IInfusedGemstoneItem) {
            //If within enchanting table power range, add mending as a possible enchantment to receive.
            if (power >= Enchantments.MENDING.getMinPower(1) && power <= Enchantments.MENDING.getMaxPower(1)) {
                list.add(new EnchantmentLevelEntry(Enchantments.MENDING, 1));   //Has to be level 1, apparently.
            }
        }

        //If the passed-in ItemStack is a Battleaxe, add most weapon enchantments.
        if (stack.getItem() instanceof ModBattleaxeItem) {
            //Declare ArrayList of ONLY weapon enchantments, then iterate through each and add to pool.
            ArrayList<Enchantment> enchantments = new ArrayList<>() {
                {
                    add(Enchantments.SHARPNESS);
                    add(Enchantments.BANE_OF_ARTHROPODS);
                    add(Enchantments.SMITE);
                    add(Enchantments.KNOCKBACK);
                    add(Enchantments.FIRE_ASPECT);
                    add(Enchantments.LOOTING);
                }
            };
            block0: for (Enchantment enchantment : enchantments) {
                //Iterate in range of all valid levels for this enchantment.
                for (int i = enchantment.getMaxLevel(); i > enchantment.getMinLevel() - 1; --i) {
                    //Skip if outside enchanting table power range, else add the enchantment then return to outer loop.
                    if (power < enchantment.getMinPower(i) || power > enchantment.getMaxPower(i)) {
                        continue;
                    }

                    list.add(new EnchantmentLevelEntry(enchantment, i));
                    continue block0;  //Continue to OUTER block (adds only the highest possible enchantment level).
                }
            }
        }

        //Return the variable because only successfully supports setReturnValue() once.
        cir.setReturnValue(list);
    }
}
