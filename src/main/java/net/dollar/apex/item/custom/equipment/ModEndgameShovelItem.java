package net.dollar.apex.item.custom.equipment;

import net.dollar.apex.util.ModItemUtils;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ShovelItem;
import net.minecraft.world.item.ToolMaterial;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class ModEndgameShovelItem extends ShovelItem {
    private final Consumer<LivingEntity> onHitMethod;
    private final BiConsumer<Consumer<Component>, ModItemUtils.EquipmentType> tooltipMethod;

    /**
     * Instantiates a new ShovelItem for one of the new endgame tiers.
     * @param material ToolMaterial for this ShovelItem
     * @param attackDamage Attack damage of this shovel
     * @param attackSpeed Attack speed of this shovel
     * @param settings Item.Settings associated with this shovel, should already have called .shovel()
     * @param tier EndgameTier determining on-hit behavior and tooltip text
     */
    public ModEndgameShovelItem(ToolMaterial material, float attackDamage, float attackSpeed,
                                ModItemUtils.EndgameTier tier, Item.Properties settings) {
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
    public void hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
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
    public void appendHoverText(ItemStack stack, TooltipContext context, TooltipDisplay displayComponent, Consumer<Component> textConsumer, TooltipFlag type) {
        tooltipMethod.accept(textConsumer, ModItemUtils.EquipmentType.TOOL);
    }
}
