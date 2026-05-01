package net.dollar.apex.item.custom;

import java.util.function.Consumer;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;

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
    public ModCustomItem(Properties settings, String tooltipLang, boolean hasGlint) {
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
    public boolean isFoil(ItemStack stack) {
        return this.hasGlint;
    }

    /**
     * Appends text to the Item's hover tooltip.
     * @param stack ItemStack corresponding to this item
     * @param context TooltipContext
     * @param displayComponent TooltipDisplayComponent associated with this tooltip
     * @param textConsumer Consumer of tooltip texts to render
     * @param type TooltipType determining data like simple or advanced
     */
    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, TooltipDisplay displayComponent, Consumer<Component> textConsumer, TooltipFlag type) {
        textConsumer.accept(Component.translatable(tooltipLang));
    }
}
