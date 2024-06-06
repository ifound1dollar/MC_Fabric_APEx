package net.dollar.apex.item.custom.tungstencarbide;

import net.dollar.apex.item.ModItems;
import net.dollar.apex.util.IFullSetEffectArmor;
import net.dollar.apex.util.ModUtils;
import net.minecraft.client.item.TooltipType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.text.Text;

import java.util.List;

public class ModTungstenCarbideArmorItem extends ArmorItem implements IFullSetEffectArmor {
    public ModTungstenCarbideArmorItem(RegistryEntry<ArmorMaterial> material, Type type, Settings settings) {
        super(material, type, settings);
    }



    /**
     * IFullSetEffectArmor interface method that prevents an effect from being applied if a full set is worn.
     * @param effect Effect trying to be applied
     * @return Whether the effect can be applied to this armor's wearer
     */
    @Override
    public boolean canReceiveEffect(RegistryEntry<StatusEffect> effect, LivingEntity wearer) {
        //Can receive effect UNLESS full set and effect is weakness.
        boolean isFullSet = false;

        //Check for correct equipment, then set isFullSet accordingly
        if (wearer instanceof PlayerEntity player) {
            boolean hasHelm = player.getEquippedStack(EquipmentSlot.HEAD).getItem() == ModItems.TUNGSTEN_CARBIDE_HELMET;
            boolean hasChest = player.getEquippedStack(EquipmentSlot.CHEST).getItem() == ModItems.TUNGSTEN_CARBIDE_CHESTPLATE;
            boolean hasLegs = player.getEquippedStack(EquipmentSlot.LEGS).getItem() == ModItems.TUNGSTEN_CARBIDE_LEGGINGS;
            boolean hasBoots = player.getEquippedStack(EquipmentSlot.FEET).getItem() == ModItems.TUNGSTEN_CARBIDE_BOOTS;
            isFullSet = hasHelm && hasChest && hasLegs && hasBoots;
        }

        return !(isFullSet && (effect == StatusEffects.SLOWNESS || effect == StatusEffects.LEVITATION));
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
        ModUtils.appendInfusedGemstoneEquipmentTooltip(tooltip, ModUtils.EquipmentType.TOOL);
    }
}
