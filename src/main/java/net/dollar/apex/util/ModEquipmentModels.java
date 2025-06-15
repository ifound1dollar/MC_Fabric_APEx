package net.dollar.apex.util;

import net.dollar.apex.ModMain;
import net.minecraft.util.Identifier;

/**
 * Contains an Identifier for each armor equipment tier. Required in order to
 *  successfully load worn equipment textures.
 */
public interface ModEquipmentModels {
    Identifier BRONZE = Identifier.of(ModMain.MOD_ID, "bronze");
    Identifier GILDED_BRONZE = Identifier.of(ModMain.MOD_ID, "gilded_bronze");
    Identifier COBALT_STEEL = Identifier.of(ModMain.MOD_ID, "cobalt_steel");
    Identifier INFUSED_GEMSTONE = Identifier.of(ModMain.MOD_ID, "infused_gemstone");
    Identifier TUNGSTEN_CARBIDE = Identifier.of(ModMain.MOD_ID, "tungsten_carbide");
}
