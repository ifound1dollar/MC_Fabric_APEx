package net.dollar.apex.util;

import net.dollar.apex.ModMain;
import net.minecraft.util.Identifier;

public interface ModEquipmentModels {
    Identifier BRONZE = Identifier.of(ModMain.MOD_ID, "bronze");
    Identifier GILDED_BRONZE = Identifier.of(ModMain.MOD_ID, "gilded_bronze");
    Identifier COBALT_STEEL = Identifier.of(ModMain.MOD_ID, "cobalt_steel");
    Identifier INFUSED_GEMSTONE = Identifier.of(ModMain.MOD_ID, "infused_gemstone");
    Identifier TUNGSTEN_CARBIDE = Identifier.of(ModMain.MOD_ID, "tungsten_carbide");
}
