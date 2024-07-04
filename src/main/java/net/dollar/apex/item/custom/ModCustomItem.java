package net.dollar.apex.item.custom;

import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;

import java.util.List;

/**
 * Used to create Items with special information and functionality like custom tooltips, explosion immunity,
 *  etc. Convenience class to avoid creating custom classes for any item that needs a tooltip, for example.
 */
public class ModCustomItem extends Item {
    private final String tooltipLang;
    private final boolean hasGlint;



    /**
     * Creates a custom Item instance with Settings (as usual), but with extra parameters for
     *  commonly-relevant data like custom tooltips, whether it has enchantment glint, etc.
     * @param settings FabricItemSettings for this Item
     * @param tooltipLang String pointing to the lang entry for this Item's custom tooltip
     * @param hasGlint Whether this item should have enchantment glint
     */
    public ModCustomItem(Settings settings, String tooltipLang, boolean hasGlint) {
        super(settings);
        this.tooltipLang = tooltipLang;
        this.hasGlint = hasGlint;
    }



    /**
     * Gets whether this Item should render with enchantment glint (true).
     * @param stack ItemStack of this Item
     * @return Whether this item has enchantment glint
     */
    @Override
    public boolean hasGlint(ItemStack stack) {
        return this.hasGlint;
    }

    /**
     * Appends text to the Item's hover tooltip.
     * @param stack ItemStack corresponding to this item
     * @param context TooltipContext
     * @param tooltip List of tooltip texts to render
     * @param type TooltipType determining data like simple or advanced
     */
    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        tooltip.add(Text.translatable(tooltipLang));
    }
}
