package net.dollar.simplegear.item.custom.cobaltsteel;

import net.dollar.simplegear.item.ModItems;
import net.dollar.simplegear.util.IFullSetEffectArmor;
import net.dollar.simplegear.util.ModUtils;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.registry.tag.DamageTypeTags;
import net.minecraft.text.Text;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ModCobaltSteelArmorItem extends ArmorItem implements IFullSetEffectArmor {
    boolean isFullSet;

    public ModCobaltSteelArmorItem(ArmorMaterial material, Type type, Settings settings) {
        super(material, type, settings);
    }



    /**
     * Checks each tick if the player has a full set of Cobalt-Steel armor, setting isFullSet if so.
     * @param stack The ItemStack associated with this Object
     * @param world Active world
     * @param entity The entity holding the item; usually a player
     * @param slot The slot this item is equipped in
     * @param selected Whether the item is in the selected hotbar slot
     */
    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        //Do nothing if on client side OR if not chestplate (isFullSet will never be true on clients).
        if (world.isClient() || slot != 2) {
            //Slot 2 corresponds to chestplate slot
            return;
        }

        //ModMain.LOGGER.info("Slot corresponding to this armor item: " + slot + " | " + stack.getName());

        //Check for correct equipment, then set isFullSet accordingly
        if (entity instanceof PlayerEntity player) {
            boolean hasHelm = player.getEquippedStack(EquipmentSlot.HEAD).getItem() == ModItems.COBALT_STEEL_HELMET;
            boolean hasChest = player.getEquippedStack(EquipmentSlot.CHEST).getItem() == ModItems.COBALT_STEEL_CHESTPLATE;
            boolean hasLegs = player.getEquippedStack(EquipmentSlot.LEGS).getItem() == ModItems.COBALT_STEEL_LEGGINGS;
            boolean hasBoots = player.getEquippedStack(EquipmentSlot.FEET).getItem() == ModItems.COBALT_STEEL_BOOTS;
            isFullSet = hasHelm && hasChest && hasLegs && hasBoots;
        } else {
            //If not player, always false.
            isFullSet = false;
        }
    }

    /**
     * IFullSetEffectArmor interface method that prevents an effect from being applied if a full set is worn.
     * @param effect Effect trying to be applied
     * @return Whether the effect can be applied to this armor's wearer
     */
    @Override
    public boolean canReceiveEffect(StatusEffect effect) {
        //Can receive effect UNLESS full set and effect is slowness.
        //ModMain.LOGGER.info("Full set: " + isFullSet + " | Effect: " + effect.getName());
        return !(isFullSet && effect == StatusEffects.SLOWNESS);
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
     * Gets whether Entities of this Item can be damaged by a specific DamageSource (false for fire and explosion).
     * @param source DamageSource being checked
     * @return Whether this Item can be damaged by the DamageSource
     */
    @Override
    public boolean damage(DamageSource source) {
        return !(source.isIn(DamageTypeTags.IS_FIRE) || source.isIn(DamageTypeTags.IS_EXPLOSION));
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
        ModUtils.appendCobaltSteelEquipmentTooltip(tooltip, ModUtils.EquipmentType.ARMOR);
    }
}
