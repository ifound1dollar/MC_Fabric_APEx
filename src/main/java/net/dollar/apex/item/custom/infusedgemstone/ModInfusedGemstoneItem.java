package net.dollar.apex.item.custom.infusedgemstone;

import net.dollar.apex.util.IInfusedGemstoneItem;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * Used specifically for the Infused Diamond item, which has enchantment glint and custom hover text.
 */
public class ModInfusedGemstoneItem extends Item implements IInfusedGemstoneItem {
    public ModInfusedGemstoneItem(Settings settings) {
        super(settings);
    }



    /**
     * Gets whether this Item should render with enchantment glint (true).
     * @param stack ItemStack of this Item
     * @return Whether this item has enchantment glint
     */
    @Override
    public boolean hasGlint(ItemStack stack) {
        return true;
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
        tooltip.add(Text.translatable("tooltip.infused_gemstone"));
    }
}
