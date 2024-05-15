package net.dollar.apex.item.custom;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ModCollectorItem extends Item {
    private final String numberString;

    /**
     * Constructs a new ModCollectorItem object and stores a string corresponding to an index number.
     * @param settings Item settings
     * @param numberString Collector item number (as string) that will be appended to the tooltip
     */
    public ModCollectorItem(Settings settings, String numberString) {
        super(settings);
        this.numberString = numberString;
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
        tooltip.add(Text.literal("§6Collector item #" + numberString));
    }
}
