package net.dollar.apex.item.custom.equipment;

import net.dollar.apex.util.ModItemUtils;
import net.minecraft.component.type.TooltipDisplayComponent;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.HoeItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolMaterial;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class ModEndgameHoeItem extends HoeItem {
    private final Consumer<LivingEntity> onHitMethod;
    private final BiConsumer<Consumer<Text>, ModItemUtils.EquipmentType> tooltipMethod;

    /**
     * Instantiates a new HoeItem for one of the new endgame tiers.
     * @param material ToolMaterial for this HoeItem
     * @param attackDamage Attack damage of this hoe
     * @param attackSpeed Attack speed of this hoe
     * @param settings Item.Settings associated with this hoe, should already have called .hoe()
     * @param tier EndgameTier determining on-hit behavior and tooltip text
     */
    public ModEndgameHoeItem(ToolMaterial material, float attackDamage, float attackSpeed,
                             ModItemUtils.EndgameTier tier, Item.Settings settings) {
        super(material, attackDamage, attackSpeed, settings);

        // Set proper method references to both Consumers.
        switch (tier) {
            case COBALT_STEEL: {
                onHitMethod = ModItemUtils::applyCobaltSteelOnHit;
                tooltipMethod = ModItemUtils::appendCobaltSteelEquipmentTooltip;
                break;
            } case INFUSED_GEMSTONE: {
                onHitMethod = ModItemUtils::applyInfusedGemstoneOnHit;
                tooltipMethod = ModItemUtils::appendInfusedGemstoneEquipmentTooltip;
                break;
            } case TUNGSTEN_CARBIDE: {
                onHitMethod = ModItemUtils::applyTungstenCarbideOnHit;
                tooltipMethod = ModItemUtils::appendTungstenCarbideEquipmentTooltip;
                break;
            }
            default:
                throw new IllegalStateException("Unexpected value: " + tier);
        }
    }



    /**
     * Performs normal post-hit operations but with chance to apply additional effect(s).
     * @param stack ItemStack of this Item
     * @param target Attacked (target) living entity
     * @param attacker Attacker (user) living entity
     */
    @Override
    public void postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        onHitMethod.accept(target);
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
    public void appendTooltip(ItemStack stack, TooltipContext context, TooltipDisplayComponent displayComponent,
                              Consumer<Text> textConsumer, TooltipType type) {
        tooltipMethod.accept(textConsumer, ModItemUtils.EquipmentType.TOOL);
    }
}
