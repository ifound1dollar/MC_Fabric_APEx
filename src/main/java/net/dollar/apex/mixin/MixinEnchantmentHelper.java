package net.dollar.apex.mixin;

import net.dollar.apex.item.custom.ModBattleaxeItem;
import net.dollar.apex.item.custom.ModBronzeArmorItem;
import net.dollar.apex.item.custom.ModGildedBronzeArmorItem;
import net.dollar.apex.item.custom.bow.ModCobaltSteelBowItem;
import net.dollar.apex.item.custom.bow.ModInfusedGemstoneBowItem;
import net.dollar.apex.item.custom.bow.ModTungstenCarbideBowItem;
import net.dollar.apex.item.custom.cobaltsteel.ModCobaltSteelArmorItem;
import net.dollar.apex.item.custom.crossbow.ModCobaltSteelCrossbowItem;
import net.dollar.apex.item.custom.crossbow.ModInfusedGemstoneCrossbowItem;
import net.dollar.apex.item.custom.crossbow.ModTungstenCarbideCrossbowItem;
import net.dollar.apex.item.custom.infusedgemstone.ModInfusedGemstoneArmorItem;
import net.dollar.apex.item.custom.tungstencarbide.ModTungstenCarbideArmorItem;
import net.dollar.apex.util.MixinUtils;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnchantmentLevelEntry;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.resource.featuretoggle.FeatureSet;
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
     * @param enabledFeatures FeatureSet
     * @param level Power/Level of the enchanting source (typically table)
     * @param stack ItemStack attempting to be enchanted
     * @param treasureAllowed Whether treasure is allowed on this item
     * @param cir Returnable callback info of List:EnchantmentLevelEntry type, used for replacing return value
     */
    @Inject(at = @At("RETURN"), method = "getPossibleEntries", cancellable = true)
    private static void injectReturnGetPossibleEntries(FeatureSet enabledFeatures, int level, ItemStack stack, boolean treasureAllowed,
                                                       CallbackInfoReturnable<List<EnchantmentLevelEntry>> cir) {
        //HERE, checks if the ItemStack trying to be enchanted is an Infused Gemstone item. If so, it should
        //  be allowed to receive Mending at an Enchanting Table.
        List<EnchantmentLevelEntry> list = cir.getReturnValue();


        Item item = stack.getItem();
        if (item instanceof ModBattleaxeItem) {
            //If the passed-in ItemStack is a Battleaxe, add most weapon enchantments.
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
                    if (level < enchantment.getMinPower(i) || level > enchantment.getMaxPower(i)) {
                        continue;
                    }

                    list.add(new EnchantmentLevelEntry(enchantment, i));
                    continue block0;  //Continue to OUTER block (adds only the highest possible enchantment level).
                }
            }
        } else if (item instanceof ModCobaltSteelBowItem ||
                item instanceof ModInfusedGemstoneBowItem ||
                item instanceof ModTungstenCarbideBowItem) {
            //If the passed-in ItemStack is a new Bow, add bow enchantments.
            ArrayList<Enchantment> enchantments = new ArrayList<>() {
                {
                    add(Enchantments.FLAME);
                    add(Enchantments.INFINITY);
                    add(Enchantments.POWER);
                    add(Enchantments.PUNCH);
                    add(Enchantments.UNBREAKING);
                }
            };

            block0:
            for (Enchantment enchantment : enchantments) {
                //Iterate in range of all valid levels for this enchantment.
                for (int i = enchantment.getMaxLevel(); i > enchantment.getMinLevel() - 1; --i) {
                    //Skip if outside enchanting table power range, else add the enchantment then return to outer loop.
                    if (level < enchantment.getMinPower(i) || level > enchantment.getMaxPower(i)) {
                        continue;
                    }

                    list.add(new EnchantmentLevelEntry(enchantment, i));
                    continue block0;  //Continue to OUTER block (adds only the highest possible enchantment level).
                }
            }
        } else if (item instanceof ModCobaltSteelCrossbowItem ||
                item instanceof ModInfusedGemstoneCrossbowItem ||
                item instanceof ModTungstenCarbideCrossbowItem) {
            //If the passed-in ItemStack is a new Crossbow, add crossbow enchantments.
            ArrayList<Enchantment> enchantments = new ArrayList<>() {
                {
                    add(Enchantments.MULTISHOT);
                    add(Enchantments.PIERCING);
                    add(Enchantments.QUICK_CHARGE);
                    add(Enchantments.UNBREAKING);
                }
            };

            block0: for (Enchantment enchantment : enchantments) {
                //Iterate in range of all valid levels for this enchantment.
                for (int i = enchantment.getMaxLevel(); i > enchantment.getMinLevel() - 1; --i) {
                    //Skip if outside enchanting table power range, else add the enchantment then return to outer loop.
                    if (level < enchantment.getMinPower(i) || level > enchantment.getMaxPower(i)) {
                        continue;
                    }

                    list.add(new EnchantmentLevelEntry(enchantment, i));
                    continue block0;  //Continue to OUTER block (adds only the highest possible enchantment level).
                }
            }
        } else if (item instanceof ModBronzeArmorItem ||
                item instanceof ModGildedBronzeArmorItem ||
                item instanceof ModCobaltSteelArmorItem ||
                item instanceof ModInfusedGemstoneArmorItem ||
                item instanceof ModTungstenCarbideArmorItem) {
            //If the passed-in ItemStack is a new Armor item, add armor enchantments.
            //Retrieve list of enchantments for the armor type from helper method.
            ArrayList<Enchantment> enchantments = MixinUtils.getEnchantmentsForArmorItem(item);

            block0: for (Enchantment enchantment : enchantments) {
                //Iterate in range of all valid levels for this enchantment.
                for (int i = enchantment.getMaxLevel(); i > enchantment.getMinLevel() - 1; --i) {
                    //Skip if outside enchanting table power range, else add the enchantment then return to outer loop.
                    if (level < enchantment.getMinPower(i) || level > enchantment.getMaxPower(i)) {
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
