package net.dollar.apex.util;

import net.dollar.apex.ModMain;
import net.minecraft.item.equipment.EquipmentAsset;
import net.minecraft.item.equipment.EquipmentAssetKeys;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.Identifier;

/**
 * Contains a RegistryKey<EquipmentAsset> for each armor equipment tier. Required in order to
 *  successfully load worn equipment textures.
 */
public interface ModEquipmentAssetKeys {
    RegistryKey<EquipmentAsset> BRONZE = register("bronze");
    RegistryKey<EquipmentAsset> GILDED_BRONZE = register("gilded_bronze");
    RegistryKey<EquipmentAsset> COBALT_STEEL = register("cobalt_steel");
    RegistryKey<EquipmentAsset> INFUSED_GEMSTONE = register("infused_gemstone");
    RegistryKey<EquipmentAsset> TUNGSTEN_CARBIDE = register("tungsten_carbide");

    static RegistryKey<EquipmentAsset> register(String name) {
        return RegistryKey.of(EquipmentAssetKeys.REGISTRY_KEY, Identifier.of(ModMain.MOD_ID, name));
    }
}
