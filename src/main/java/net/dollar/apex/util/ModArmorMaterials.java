package net.dollar.apex.util;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.Util;
import net.minecraft.world.item.equipment.ArmorMaterial;
import net.minecraft.world.item.equipment.ArmorType;
import java.util.EnumMap;

/**
 * Defines ArmorMaterials for each of the new equipment tiers (Bronze, Gilded Bronze,
 *  Cobalt-Steel, Infused Gemstone, and Tungsten-Carbide).
 */
public interface ModArmorMaterials {
    ArmorMaterial BRONZE = new ArmorMaterial(15, Util.make(
            new EnumMap<>(ArmorType.class), (map) -> {
                map.put(ArmorType.BOOTS, 2);
                map.put(ArmorType.LEGGINGS, 5);
                map.put(ArmorType.CHESTPLATE, 6);
                map.put(ArmorType.HELMET, 2);
                map.put(ArmorType.BODY, 5);
            }), 9, SoundEvents.ARMOR_EQUIP_IRON, 0.0f, 0.0f,
            ModTags.Items.COMMON_BRONZE_INGOTS, ModEquipmentAssetKeys.BRONZE);
    ArmorMaterial GILDED_BRONZE = new ArmorMaterial(23, Util.make(
            new EnumMap<>(ArmorType.class), (map) -> {
                map.put(ArmorType.BOOTS, 3);
                map.put(ArmorType.LEGGINGS, 5);
                map.put(ArmorType.CHESTPLATE, 6);
                map.put(ArmorType.HELMET, 3);
                map.put(ArmorType.BODY, 9);
            }), 25, SoundEvents.ARMOR_EQUIP_GOLD, 1.0f, 0.0f,
            ItemTags.REPAIRS_GOLD_ARMOR, ModEquipmentAssetKeys.GILDED_BRONZE);
    ArmorMaterial COBALT_STEEL = new ArmorMaterial(39, Util.make(
            new EnumMap<>(ArmorType.class), (map) -> {
                map.put(ArmorType.BOOTS, 3);
                map.put(ArmorType.LEGGINGS, 6);
                map.put(ArmorType.CHESTPLATE, 8);
                map.put(ArmorType.HELMET, 3);
                map.put(ArmorType.BODY, 11);
            }), 20, SoundEvents.ARMOR_EQUIP_DIAMOND, 3.0f, 0.1f,
            ModTags.Items.MOD_REPAIRS_COBALT_STEEL_EQUIPMENT, ModEquipmentAssetKeys.COBALT_STEEL);
    ArmorMaterial INFUSED_GEMSTONE = new ArmorMaterial(37, Util.make(
            new EnumMap<>(ArmorType.class), (map) -> {
                map.put(ArmorType.BOOTS, 3);
                map.put(ArmorType.LEGGINGS, 6);
                map.put(ArmorType.CHESTPLATE, 8);
                map.put(ArmorType.HELMET, 3);
                map.put(ArmorType.BODY, 11);
            }), 28, SoundEvents.ARMOR_EQUIP_DIAMOND, 3.0f, 0.1f,
            ModTags.Items.MOD_REPAIRS_INFUSED_GEMSTONE_EQUIPMENT, ModEquipmentAssetKeys.INFUSED_GEMSTONE);
    ArmorMaterial TUNGSTEN_CARBIDE = new ArmorMaterial(41, Util.make(
            new EnumMap<>(ArmorType.class), (map) -> {
                map.put(ArmorType.BOOTS, 3);
                map.put(ArmorType.LEGGINGS, 6);
                map.put(ArmorType.CHESTPLATE, 8);
                map.put(ArmorType.HELMET, 3);
                map.put(ArmorType.BODY, 11);
            }), 15, SoundEvents.ARMOR_EQUIP_NETHERITE, 3.0f, 0.1f,
            ModTags.Items.MOD_REPAIRS_TUNGSTEN_CARBIDE_EQUIPMENT, ModEquipmentAssetKeys.TUNGSTEN_CARBIDE);
}