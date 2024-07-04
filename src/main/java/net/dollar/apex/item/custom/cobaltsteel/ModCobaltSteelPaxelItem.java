package net.dollar.apex.item.custom.cobaltsteel;

import net.dollar.apex.item.custom.ModPaxelItem;
import net.dollar.apex.util.ModToolMaterials;
import net.dollar.apex.util.ModUtils;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.MiningToolItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.text.Text;

import java.util.List;

public class ModCobaltSteelPaxelItem extends ModPaxelItem {
    public ModCobaltSteelPaxelItem(ToolMaterial material, float attackDamage, float attackSpeed) {
        super(material, new Item.Settings()
                .attributeModifiers(MiningToolItem.createAttributeModifiers(
                        ModToolMaterials.COBALT_STEEL, attackDamage, attackSpeed))
                .fireproof());
    }

    /**
     * Gets the mining speed of this Tool, depending on the Block being mined.
     * @param stack Stack corresponding to this Tool
     * @param state BlockState corresponding to Block attempting to be mined
     * @return The calculated mining speed
     */
    @Override
    public float getMiningSpeed(ItemStack stack, BlockState state) {
        float baseVal = super.getMiningSpeed(stack, state);

        //If the block being mined is Deepslate, increase mining speed by a further 100% (allows instant
        //  mining with Cobalt Steel Paxel/Pickaxe w/Efficiency V & Haste II : results in total mining speed
        //  of 92.4, needs 90).
        return (state.getBlock() == Blocks.DEEPSLATE) ? baseVal * 2.0f : baseVal;
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
        ModUtils.applyCobaltSteelOnHit(target);
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
        ModUtils.appendCobaltSteelEquipmentTooltip(tooltip, ModUtils.EquipmentType.TOOL);
    }
}
