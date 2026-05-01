package net.dollar.apex.entity.client;

import net.dollar.apex.ModMain;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.Identifier;

@Environment(EnvType.CLIENT)
public class ModModelLayers {
    public static final ModelLayerLocation OBSIDIAN_GOLEM =
            new ModelLayerLocation(Identifier.fromNamespaceAndPath(ModMain.MOD_ID, "obsidian_golem"), "main");
    public static final ModelLayerLocation MYSTERIOUS_SPECTER =
            new ModelLayerLocation(Identifier.fromNamespaceAndPath(ModMain.MOD_ID, "mysterious_specter"), "main");
}
