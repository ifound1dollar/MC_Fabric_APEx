package net.dollar.apex.util;

import net.minecraft.item.equipment.ArmorMaterial;
import net.minecraft.item.equipment.EquipmentType;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Util;

import java.util.EnumMap;

public interface ModArmorMaterials {
    ArmorMaterial BRONZE = new ArmorMaterial(15, Util.make(
            new EnumMap<>(EquipmentType.class), (map) -> {
                map.put(EquipmentType.BOOTS, 2);
                map.put(EquipmentType.LEGGINGS, 5);
                map.put(EquipmentType.CHESTPLATE, 6);
                map.put(EquipmentType.HELMET, 2);
                map.put(EquipmentType.BODY, 5);
            }), 9, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 0.0f, 0.0f,
            ModTags.Items.COMMON_BRONZE_INGOTS, ModEquipmentModels.BRONZE);
    ArmorMaterial GILDED_BRONZE = new ArmorMaterial(23, Util.make(
            new EnumMap<>(EquipmentType.class), (map) -> {
                map.put(EquipmentType.BOOTS, 3);
                map.put(EquipmentType.LEGGINGS, 5);
                map.put(EquipmentType.CHESTPLATE, 6);
                map.put(EquipmentType.HELMET, 3);
                map.put(EquipmentType.BODY, 9);
            }), 25, SoundEvents.ITEM_ARMOR_EQUIP_GOLD, 1.0f, 0.0f,
            ItemTags.REPAIRS_GOLD_ARMOR, ModEquipmentModels.GILDED_BRONZE);
    ArmorMaterial COBALT_STEEL = new ArmorMaterial(39, Util.make(
            new EnumMap<>(EquipmentType.class), (map) -> {
                map.put(EquipmentType.BOOTS, 3);
                map.put(EquipmentType.LEGGINGS, 6);
                map.put(EquipmentType.CHESTPLATE, 8);
                map.put(EquipmentType.HELMET, 3);
                map.put(EquipmentType.BODY, 11);
            }), 20, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 3.0f, 0.1f,
            ModTags.Items.MOD_REPAIRS_COBALT_STEEL_EQUIPMENT, ModEquipmentModels.COBALT_STEEL);
    ArmorMaterial INFUSED_GEMSTONE = new ArmorMaterial(37, Util.make(
            new EnumMap<>(EquipmentType.class), (map) -> {
                map.put(EquipmentType.BOOTS, 3);
                map.put(EquipmentType.LEGGINGS, 6);
                map.put(EquipmentType.CHESTPLATE, 8);
                map.put(EquipmentType.HELMET, 3);
                map.put(EquipmentType.BODY, 11);
            }), 28, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 3.0f, 0.1f,
            ModTags.Items.MOD_REPAIRS_INFUSED_GEMSTONE_EQUIPMENT, ModEquipmentModels.INFUSED_GEMSTONE);
    ArmorMaterial TUNGSTEN_CARBIDE = new ArmorMaterial(41, Util.make(
            new EnumMap<>(EquipmentType.class), (map) -> {
                map.put(EquipmentType.BOOTS, 3);
                map.put(EquipmentType.LEGGINGS, 6);
                map.put(EquipmentType.CHESTPLATE, 8);
                map.put(EquipmentType.HELMET, 3);
                map.put(EquipmentType.BODY, 11);
            }), 15, SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE, 3.0f, 0.1f,
            ModTags.Items.MOD_REPAIRS_TUNGSTEN_CARBIDE_EQUIPMENT, ModEquipmentModels.TUNGSTEN_CARBIDE);
}