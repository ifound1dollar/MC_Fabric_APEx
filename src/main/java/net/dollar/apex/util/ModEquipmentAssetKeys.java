package net.dollar.apex.util;

import net.dollar.apex.ModMain;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.equipment.EquipmentAsset;
import net.minecraft.world.item.equipment.EquipmentAssets;

/**
 * Contains a RegistryKey<EquipmentAsset> for each armor equipment tier. Required in order to
 *  successfully load worn equipment textures.
 */
public interface ModEquipmentAssetKeys {
    ResourceKey<EquipmentAsset> BRONZE = register("bronze");
    ResourceKey<EquipmentAsset> GILDED_BRONZE = register("gilded_bronze");
    ResourceKey<EquipmentAsset> COBALT_STEEL = register("cobalt_steel");
    ResourceKey<EquipmentAsset> INFUSED_GEMSTONE = register("infused_gemstone");
    ResourceKey<EquipmentAsset> TUNGSTEN_CARBIDE = register("tungsten_carbide");

    static ResourceKey<EquipmentAsset> register(String name) {
        return ResourceKey.create(EquipmentAssets.ROOT_ID, Identifier.fromNamespaceAndPath(ModMain.MOD_ID, name));
    }
}
