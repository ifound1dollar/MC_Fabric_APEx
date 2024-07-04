package net.dollar.apex.item.custom;

import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;

import java.util.List;

public class ModTrophyItem extends Item {
    private final String numberString;

    /**
     * Constructs a new ModCollectorItem object and stores a string corresponding to an index number.
     * @param settings Item settings
     * @param numberString Collector item number (as string) that will be appended to the tooltip
     */
    public ModTrophyItem(Settings settings, String numberString) {
        super(settings);
        this.numberString = numberString;
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
        tooltip.add(Text.literal("§6Trophy item #" + numberString));
    }
}
