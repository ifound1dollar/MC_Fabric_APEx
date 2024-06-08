package net.dollar.apex.item.custom;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * Used to create Items with special information and functionality like custom tooltips, explosion immunity,
 *  etc. Convenience class to avoid creating custom classes for any item that needs a tooltip, for example.
 */
public class ModCustomItem extends Item {
    private final String tooltipLang;
    private final boolean hasGlint;
    private final boolean isFireImmune;



    /**
     * Creates a custom Item instance with Settings (as usual), but with extra parameters for
     *  commonly-relevant data like custom tooltips, whether it has enchantment glint, etc.
     * @param settings FabricItemSettings for this Item
     * @param tooltipLang String pointing to the lang entry for this Item's custom tooltip
     * @param hasGlint Whether this item should have enchantment glint
     * @param isFireImmune Whether this item (Entity when dropped) should be Fire immune
     */
    public ModCustomItem(Settings settings, String tooltipLang, boolean hasGlint, boolean isFireImmune) {
        super(settings);
        this.tooltipLang = tooltipLang;
        this.hasGlint = hasGlint;
        this.isFireImmune = isFireImmune;
    }



    /**
     * Gets whether Entities of this Item are fireproof (true).
     * @return Whether this Item is fireproof
     */
    @Override
    public boolean isFireproof() {
        return isFireImmune;
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
     * Appends text to the Item's hover tooltip (lore).
     * @param stack ItemStack corresponding to this Item
     * @param world Active world
     * @param tooltip List of tooltip texts to show
     * @param context TooltipContext denoting data like simple or advanced
     */
    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        tooltip.add(Text.translatable(tooltipLang));
    }
}
