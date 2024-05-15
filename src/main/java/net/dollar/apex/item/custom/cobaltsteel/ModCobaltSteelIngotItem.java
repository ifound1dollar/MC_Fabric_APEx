package net.dollar.apex.item.custom.cobaltsteel;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ModCobaltSteelIngotItem extends Item {
    public ModCobaltSteelIngotItem(Settings settings) {
        super(settings);
    }



    /**
     * Gets whether Entities of this Item are fireproof (true).
     * @return Whether this Item is fireproof
     */
    @Override
    public boolean isFireproof() {
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
        tooltip.add(Text.translatable("tooltip.cobalt_steel_ingot"));
    }
}
