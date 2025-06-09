package net.dollar.apex.item.custom.tungstencarbide;

import net.dollar.apex.util.ModItemUtils;
import net.minecraft.component.type.TooltipDisplayComponent;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;

import java.util.function.Consumer;

public class ModTungstenCarbideToolItem extends Item {
    public ModTungstenCarbideToolItem(Settings settings) {
        super(settings);
    }



    /**
     * Performs normal post-hit operations but with chance to apply additional effect(s).
     * @param stack ItemStack of this Item
     * @param target Attacked (target) living entity
     * @param attacker Attacker (user) living entity
     */
    @Override
    public void postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        ModItemUtils.applyTungstenCarbideOnHit(target);
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
    public void appendTooltip(ItemStack stack, TooltipContext context, TooltipDisplayComponent displayComponent, Consumer<Text> textConsumer, TooltipType type) {
        ModItemUtils.appendTungstenCarbideEquipmentTooltip(textConsumer, ModItemUtils.EquipmentType.TOOL);
    }
}
