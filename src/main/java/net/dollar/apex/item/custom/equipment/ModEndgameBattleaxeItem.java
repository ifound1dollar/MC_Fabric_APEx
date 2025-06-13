package net.dollar.apex.item.custom.equipment;

import net.dollar.apex.item.custom.ModBattleaxeItem;
import net.dollar.apex.util.ModItemUtils;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolMaterial;
import net.minecraft.text.Text;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class ModEndgameBattleaxeItem extends ModBattleaxeItem {
    private final Consumer<LivingEntity> onHitMethod;
    private final BiConsumer<List<Text>, ModItemUtils.EquipmentType> tooltipMethod;

    public ModEndgameBattleaxeItem(ToolMaterial material, int attackDamage, float attackSpeed,
                             ModItemUtils.EndgameTier tier, Settings settings) {
        super(material, attackDamage, attackSpeed, settings);

        // Set proper method references to both Consumers.
        switch (tier) {
            case COBALT_STEEL -> {
                onHitMethod = ModItemUtils::applyCobaltSteelOnHit;
                tooltipMethod = ModItemUtils::appendCobaltSteelEquipmentTooltip;
            }
            case INFUSED_GEMSTONE -> {
                onHitMethod = ModItemUtils::applyInfusedGemstoneOnHit;
                tooltipMethod = ModItemUtils::appendInfusedGemstoneEquipmentTooltip;
            }
            case TUNGSTEN_CARBIDE -> {
                onHitMethod = ModItemUtils::applyTungstenCarbideOnHit;
                tooltipMethod = ModItemUtils::appendTungstenCarbideEquipmentTooltip;
            }
            default -> throw new IllegalStateException("Unexpected value: " + tier);
        }
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
        onHitMethod.accept(target);
        return super.postHit(stack, target, attacker);
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
        tooltipMethod.accept(tooltip, ModItemUtils.EquipmentType.TOOL);
    }
}
