package net.dollar.apex.item.custom.equipment;

import net.dollar.apex.util.ModItemUtils;
import net.minecraft.component.type.TooltipDisplayComponent;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class ModEndgameToolItem extends Item {
    private final Consumer<LivingEntity> onHitMethod;
    private final BiConsumer<Consumer<Text>, ModItemUtils.EquipmentType> tooltipMethod;

    /**
     * Instantiates a new generic non-weapon tool item for the passed-in EndgameTier.
     * @param tier EndgameTier determining on-hit behavior and tooltip text
     * @param settings Item.Settings associated with this tool, should already have called .tool()
     */
    public ModEndgameToolItem(ModItemUtils.EndgameTier tier, Settings settings) {
        super(settings);

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
