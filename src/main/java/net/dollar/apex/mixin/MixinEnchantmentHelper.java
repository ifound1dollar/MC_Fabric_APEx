package net.dollar.apex.mixin;

import net.dollar.apex.item.custom.ModBattleaxeItem;
import net.dollar.apex.util.IInfusedGemstoneItem;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnchantmentLevelEntry;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.entry.RegistryEntry;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;
import java.util.stream.Stream;

@Deprecated
@Mixin(EnchantmentHelper.class)
public abstract class MixinEnchantmentHelper {
//    @Shadow public abstract ItemStack enchant(Random random, ItemStack stack, int level, DynamicRegistryManager dynamicRegistryManager, Optional<? extends RegistryEntryList<Enchantment>> enchantments);

    /**
     * Injects at final return of EnchantmentHelper.getPossibleEntries() to add Mending and weapon enchantments
     *  to list IF the passed-in item is Infused Gemstone or a Battleaxe (respectively).
     * @param level Power/Level of the enchanting source (typically table)
     * @param stack ItemStack attempting to be enchanted
     * @param possibleEnchantments Stream of ALL Enchantment RegistryEntries
     * @param cir Returnable callback info of List:EnchantmentLevelEntry type, used for replacing return value
     */
    @Inject(at = @At("RETURN"), method = "getPossibleEntries", cancellable = true)
    private static void injectReturnGetPossibleEntries(int level, ItemStack stack, Stream<RegistryEntry<Enchantment>> possibleEnchantments, CallbackInfoReturnable<List<EnchantmentLevelEntry>> cir) {
        //HERE, checks if the ItemStack trying to be enchanted is an Infused Gemstone item. If so, it should
        //  be allowed to receive Mending at an Enchanting Table.
        List<EnchantmentLevelEntry> list = cir.getReturnValue();

        //If the passed-in ItemStack is an Infused Gemstone item, add Mending as a list of valid enchantments.
        if (stack.getItem() instanceof IInfusedGemstoneItem) {
            possibleEnchantments.forEach((enchantmentx) -> {
                //If this iteration's RegistryEntry<Enchantment> (stored in enchantmentx) matches the MENDING RegistryKey.
                if (enchantmentx.matchesKey(Enchantments.MENDING)) {
                    Enchantment enchantment = enchantmentx.value();
                    //If within enchanting table power range, add mending as a possible enchantment to receive.
                    if (level >= enchantment.getMaxPower(1) && level <= enchantment.getMinPower(1)) {
                        list.add(new EnchantmentLevelEntry(enchantmentx, 1));   //'enchantmentx' is the mending RegistryEntry here.
                    }
                }
            });
        }

        //If the passed-in ItemStack is a Battleaxe, manually add most weapon enchantments.
        if (stack.getItem() instanceof ModBattleaxeItem) {
//            possibleEnchantments.forEach((enchantmentx) -> {
//                //If this iteration's RegistryEntry<Enchantment> (stored in enchantmentx) matches the MENDING RegistryKey.
//                if (enchantmentx.matchesKey(Enchantments.MENDING)) {
//                    Enchantment enchantment = enchantmentx.value();
//                    //If within enchanting table power range, add mending as a possible enchantment to receive.
//                    if (level >= enchantment.getMaxPower(1) && level <= enchantment.getMinPower(1)) {
//                        list.add(new EnchantmentLevelEntry(enchantmentx, 1));   //'enchantmentx' is the mending RegistryEntry here.
//                    }
//                }
//            });
        }

        //REMOVE MANUALLY ADDING ENCHANTMENTS TO BATTLEAXE; NOW UTILIZES TAGS. Added each sword enchantment
        //  (excluding Sweeping Edge) to Battleaxes by adding new Battleaxes to corresponding tags.

//        //If the passed-in ItemStack is a Battleaxe, add most weapon enchantments.
//        if (stack.getItem() instanceof ModBattleaxeItem) {
//            //Declare ArrayList of ONLY weapon enchantments, then iterate through each and add to pool.
//            ArrayList<Enchantment> enchantments = new ArrayList<>() {
//                {
//                    add(Enchantments.SHARPNESS);
//                    add(Enchantments.BANE_OF_ARTHROPODS);
//                    add(Enchantments.SMITE);
//                    add(Enchantments.KNOCKBACK);
//                    add(Enchantments.FIRE_ASPECT);
//                    add(Enchantments.LOOTING);
//                }
//            };
//            block0: for (Enchantment enchantment : enchantments) {
//                //Iterate in range of all valid levels for this enchantment.
//                for (int i = enchantment.getMaxLevel(); i > enchantment.getMinLevel() - 1; --i) {
//                    //Skip if outside enchanting table power range, else add the enchantment then return to outer loop.
//                    if (level < enchantment.getMinPower(i) || level > enchantment.getMaxPower(i)) {
//                        continue;
//                    }
//
//                    list.add(new EnchantmentLevelEntry(enchantment, i));
//                    continue block0;  //Continue to OUTER block (adds only the highest possible enchantment level).
//                }
//            }
//        }

        //Return the variable because only successfully supports setReturnValue() once.
        cir.setReturnValue(list);
    }
}
