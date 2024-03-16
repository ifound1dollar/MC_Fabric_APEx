package net.dollar.simplegear.item.custom.netherite;

import net.dollar.simplegear.util.IDamageHandlingArmor;
import net.dollar.simplegear.util.ModUtils;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.text.Text;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ModNetheriteArmorItem extends ArmorItem implements IDamageHandlingArmor {
    boolean isFullSet;

    public ModNetheriteArmorItem(ArmorMaterial material, Type type, Settings settings) {
        super(material, type, settings);
    }



    /**
     * Checks each tick if the player has a full set of Infused Gemstone armor, setting isFullSet if so.
     * @param stack The ItemStack associated with this Object
     * @param world Active world
     * @param entity The entity holding the item; usually a player
     * @param slot The slot this item is equipped in
     * @param selected Whether the item is in the selected hotbar slot
     */
    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        //Do nothing if on client side OR if not chestplate (isFullSet will never be true on clients).
        if (world.isClient() || slot != 38) { return; }   //Slot 38 corresponds to chestplate slot

        //Check for correct equipment, then set isFullSet accordingly
        if (entity instanceof PlayerEntity player) {
            boolean hasBoots = player.getEquippedStack(EquipmentSlot.FEET).getItem() == Items.NETHERITE_BOOTS.asItem();
            boolean hasLegs = player.getEquippedStack(EquipmentSlot.LEGS).getItem() == Items.NETHERITE_LEGGINGS.asItem();
            boolean hasChest = player.getEquippedStack(EquipmentSlot.CHEST).getItem() == Items.NETHERITE_CHESTPLATE.asItem();
            boolean hasHelm = player.getEquippedStack(EquipmentSlot.HEAD).getItem() == Items.NETHERITE_HELMET.asItem();
            isFullSet = hasBoots && hasLegs && hasChest && hasHelm;
        } else {
            //If not player, always false.
            isFullSet = false;
        }
    }

    /**
     * Intercepts the damage taken operation to reduce Magic damage taken.
     * @param entity Attacked LivingEntity (wearer)
     * @param slot EquipmentSlot of this item
     * @param source Source of damage to be dealt
     * @param amount Initial damage amount
     * @return Updated damage amount
     */
    @Override
    public float onDamaged(LivingEntity entity, EquipmentSlot slot, DamageSource source, float amount) {
        //If not chestplate OR not full set, do not alter damage.
        if (slot != EquipmentSlot.CHEST || !isFullSet) { return amount; }

        //If taking damage from Fire source, reduce damage taken.
        if (ModUtils.getDamageCategory(source) == ModUtils.DamageCategory.FIRE) {
            //TODO: RE-IMPLEMENT CONFIGS
            //return amount * (1 - ((float)ModCommonConfigs.NETHERITE_FIRE_DAMAGE_REDUCTION.get() / 100));
            return amount * 0.67f;
        }
        return amount;  //if reaches here, return original amount
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
        ModUtils.appendNetheriteEquipmentTooltip(tooltip, true);
    }
}
