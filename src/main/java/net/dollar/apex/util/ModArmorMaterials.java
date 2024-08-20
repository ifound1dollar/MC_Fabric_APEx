package net.dollar.apex.util;

import net.dollar.apex.ModMain;
import net.dollar.apex.item.ModItems;
import net.minecraft.item.*;
import net.minecraft.recipe.Ingredient;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;

import java.util.EnumMap;
import java.util.List;
import java.util.function.Supplier;

public class ModArmorMaterials {
    public static final RegistryEntry<ArmorMaterial> BRONZE = register("bronze", Util.make(
            new EnumMap<>(ArmorItem.Type.class), (map) -> {
                map.put(ArmorItem.Type.BOOTS, 2);
                map.put(ArmorItem.Type.LEGGINGS, 5);
                map.put(ArmorItem.Type.CHESTPLATE, 6);
                map.put(ArmorItem.Type.HELMET, 2);
                map.put(ArmorItem.Type.BODY, 5);
            }), 9, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 0.0f, 0.0f,
            () -> Ingredient.ofItems(ModItems.BRONZE_INGOT));
    public static final RegistryEntry<ArmorMaterial> GILDED_BRONZE = register("gilded_bronze", Util.make(
            new EnumMap<>(ArmorItem.Type.class), (map) -> {
                map.put(ArmorItem.Type.BOOTS, 3);
                map.put(ArmorItem.Type.LEGGINGS, 5);
                map.put(ArmorItem.Type.CHESTPLATE, 6);
                map.put(ArmorItem.Type.HELMET, 3);
                map.put(ArmorItem.Type.BODY, 9);
            }), 25, SoundEvents.ITEM_ARMOR_EQUIP_GOLD, 1.0f, 0.0f,
            () -> Ingredient.ofItems(Items.GOLD_INGOT));
    public static final RegistryEntry<ArmorMaterial> COBALT_STEEL = register("cobalt_steel", Util.make(
            new EnumMap<>(ArmorItem.Type.class), (map) -> {
                map.put(ArmorItem.Type.BOOTS, 3);
                map.put(ArmorItem.Type.LEGGINGS, 6);
                map.put(ArmorItem.Type.CHESTPLATE, 8);
                map.put(ArmorItem.Type.HELMET, 3);
                map.put(ArmorItem.Type.BODY, 11);
            }), 20, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 3.0f, 0.1f,
            () -> Ingredient.ofItems(ModItems.COBALT_STEEL_INGOT));
    public static final RegistryEntry<ArmorMaterial> INFUSED_GEMSTONE = register("infused_gemstone.json", Util.make(
                    new EnumMap<>(ArmorItem.Type.class), (map) -> {
                        map.put(ArmorItem.Type.BOOTS, 3);
                        map.put(ArmorItem.Type.LEGGINGS, 6);
                        map.put(ArmorItem.Type.CHESTPLATE, 8);
                        map.put(ArmorItem.Type.HELMET, 3);
                        map.put(ArmorItem.Type.BODY, 11);
                    }), 25, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 3.0f, 0.05f,
            () -> Ingredient.ofItems(ModItems.INFUSED_GEMSTONE));
    public static final RegistryEntry<ArmorMaterial> TUNGSTEN_CARBIDE = register("tungsten_carbide", Util.make(
                    new EnumMap<>(ArmorItem.Type.class), (map) -> {
                        map.put(ArmorItem.Type.BOOTS, 3);
                        map.put(ArmorItem.Type.LEGGINGS, 6);
                        map.put(ArmorItem.Type.CHESTPLATE, 8);
                        map.put(ArmorItem.Type.HELMET, 3);
                        map.put(ArmorItem.Type.BODY, 11);
                    }), 15, SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE, 3.0f, 0.1f,
            () -> Ingredient.ofItems(ModItems.TUNGSTEN_CARBIDE_INGOT));



    public ModArmorMaterials() {

    }



    private static RegistryEntry<ArmorMaterial> register(String id, EnumMap<ArmorItem.Type, Integer> defense,
                                                         int enchantability, RegistryEntry<SoundEvent> equipSound,
                                                         float toughness, float knockbackResistance,
                                                         Supplier<Ingredient> repairIngredient) {
        List<ArmorMaterial.Layer> list = List.of(new ArmorMaterial.Layer(Identifier.of(ModMain.MOD_ID, id)));
        return register(id, defense, enchantability, equipSound, toughness, knockbackResistance, repairIngredient, list);
    }

    private static RegistryEntry<ArmorMaterial> register(String id, EnumMap<ArmorItem.Type, Integer> defense,
                                                         int enchantability, RegistryEntry<SoundEvent> equipSound,
                                                         float toughness, float knockbackResistance,
                                                         Supplier<Ingredient> repairIngredient,
                                                         List<ArmorMaterial.Layer> layers) {
        EnumMap<ArmorItem.Type, Integer> enumMap = new EnumMap<>(ArmorItem.Type.class);
        ArmorItem.Type[] var9 = ArmorItem.Type.values();

        for (ArmorItem.Type type : var9) {
            enumMap.put(type, defense.get(type));
        }

        return Registry.registerReference(Registries.ARMOR_MATERIAL, Identifier.of(ModMain.MOD_ID, id),
                new ArmorMaterial(enumMap, enchantability, equipSound, repairIngredient, layers, toughness, knockbackResistance));
    }
}