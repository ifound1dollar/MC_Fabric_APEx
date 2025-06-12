package net.dollar.apex.item.custom.equipment;

import net.dollar.apex.item.ModItems;
import net.dollar.apex.util.IFullSetEffectArmor;
import net.dollar.apex.util.ModItemUtils;
import net.minecraft.component.type.TooltipDisplayComponent;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.text.Text;

import java.util.function.BiConsumer;
import java.util.function.BiPredicate;
import java.util.function.Consumer;

public class ModEndgameArmorItem extends Item implements IFullSetEffectArmor {
    private final BiPredicate<RegistryEntry<StatusEffect>, LivingEntity> canReceiveEffectMethod;
    private final BiConsumer<Consumer<Text>, ModItemUtils.EquipmentType> tooltipMethod;

    /**
     * Instantiates a new endgame-tier armor item.
     * @param tier EndgameTier determining which effect to be prevented with a full set of armor
     * @param settings Item.Settings associated with the armor item, should have already called .armor()
     */
    public ModEndgameArmorItem(ModItemUtils.EndgameTier tier, Item.Settings settings) {
        super(settings);

        // Set proper method references to BiConsumers.
        switch (tier) {
            case COBALT_STEEL: {
                canReceiveEffectMethod = (effect, wearer) -> {
                    // Check for correct equipment, then set isFullSet accordingly.
                    boolean isFullSet;
                    boolean hasHelm = wearer.getEquippedStack(EquipmentSlot.HEAD).getItem() == ModItems.COBALT_STEEL_HELMET;
                    boolean hasChest = wearer.getEquippedStack(EquipmentSlot.CHEST).getItem() == ModItems.COBALT_STEEL_CHESTPLATE;
                    boolean hasLegs = wearer.getEquippedStack(EquipmentSlot.LEGS).getItem() == ModItems.COBALT_STEEL_LEGGINGS;
                    boolean hasBoots = wearer.getEquippedStack(EquipmentSlot.FEET).getItem() == ModItems.COBALT_STEEL_BOOTS;
                    isFullSet = hasHelm && hasChest && hasLegs && hasBoots;

                    // Return true or false depending on whether full set, and whether effect should be prevented.
                    return !(isFullSet && (effect == StatusEffects.SLOWNESS || effect == StatusEffects.MINING_FATIGUE));
                };
                tooltipMethod = ModItemUtils::appendCobaltSteelEquipmentTooltip;
                break;
            }
            case INFUSED_GEMSTONE: {
                canReceiveEffectMethod = (effect, wearer) -> {
                    boolean isFullSet;
                    boolean hasHelm = wearer.getEquippedStack(EquipmentSlot.HEAD).getItem() == ModItems.INFUSED_GEMSTONE_HELMET;
                    boolean hasChest = wearer.getEquippedStack(EquipmentSlot.CHEST).getItem() == ModItems.INFUSED_GEMSTONE_CHESTPLATE;
                    boolean hasLegs = wearer.getEquippedStack(EquipmentSlot.LEGS).getItem() == ModItems.INFUSED_GEMSTONE_LEGGINGS;
                    boolean hasBoots = wearer.getEquippedStack(EquipmentSlot.FEET).getItem() == ModItems.INFUSED_GEMSTONE_BOOTS;
                    isFullSet = hasHelm && hasChest && hasLegs && hasBoots;
                    return !(isFullSet && (effect == StatusEffects.POISON || effect == StatusEffects.WITHER));
                };
                tooltipMethod = ModItemUtils::appendInfusedGemstoneEquipmentTooltip;
                break;
            }
            case TUNGSTEN_CARBIDE: {
                canReceiveEffectMethod = (effect, wearer) -> {
                    boolean isFullSet;
                    boolean hasHelm = wearer.getEquippedStack(EquipmentSlot.HEAD).getItem() == ModItems.TUNGSTEN_CARBIDE_HELMET;
                    boolean hasChest = wearer.getEquippedStack(EquipmentSlot.CHEST).getItem() == ModItems.TUNGSTEN_CARBIDE_CHESTPLATE;
                    boolean hasLegs = wearer.getEquippedStack(EquipmentSlot.LEGS).getItem() == ModItems.TUNGSTEN_CARBIDE_LEGGINGS;
                    boolean hasBoots = wearer.getEquippedStack(EquipmentSlot.FEET).getItem() == ModItems.TUNGSTEN_CARBIDE_BOOTS;
                    isFullSet = hasHelm && hasChest && hasLegs && hasBoots;
                    return !(isFullSet && (effect == StatusEffects.WEAKNESS || effect == StatusEffects.LEVITATION));
                };
                tooltipMethod = ModItemUtils::appendTungstenCarbideEquipmentTooltip;
                break;
            }
            default: throw new IllegalStateException("Unexpected value: " + tier);
        }
    }



    /**
     * IFullSetEffectArmor interface method that prevents an effect from being applied if a full set is worn.
     * @param effect Effect trying to be applied
     * @return Whether the effect can be applied to this armor's wearer
     */
    @Override
    public boolean canReceiveEffect(RegistryEntry<StatusEffect> effect, LivingEntity wearer) {
        return canReceiveEffectMethod.test(effect, wearer);
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
        tooltipMethod.accept(textConsumer, ModItemUtils.EquipmentType.ARMOR);
    }
}
