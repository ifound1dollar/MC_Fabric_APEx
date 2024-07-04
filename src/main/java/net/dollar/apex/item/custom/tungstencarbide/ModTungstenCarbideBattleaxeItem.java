package net.dollar.apex.item.custom.tungstencarbide;

import net.dollar.apex.item.custom.ModBattleaxeItem;
import net.dollar.apex.util.ModToolMaterials;
import net.dollar.apex.util.ModUtils;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolMaterial;
import net.minecraft.text.Text;

import java.util.List;

public class ModTungstenCarbideBattleaxeItem extends ModBattleaxeItem {
    public ModTungstenCarbideBattleaxeItem(ToolMaterial material, int attackDamage, float attackSpeed) {
        super(material, new Item.Settings()
                .attributeModifiers(ModBattleaxeItem.createAttributeModifiers(
                        ModToolMaterials.TUNGSTEN_CARBIDE, attackDamage, attackSpeed))
                .fireproof());
    }



    /**
     * Performs normal post-hit operations but with chance to apply additional effect(s).
     * @param stack ItemStack of this Item
     * @param target Attacked (target) living entity
     * @param attacker Attacker (user) living entity
     * @return Whether attack was successfully performed
     */
    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        ModUtils.applyTungstenCarbideOnHit(target);
        return super.postHit(stack, target, attacker);
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
        ModUtils.appendTungstenCarbideEquipmentTooltip(tooltip, ModUtils.EquipmentType.TOOL);
    }
}
