package net.dollar.apex.entity.client;

import net.dollar.apex.ModMain;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.util.Identifier;

public class ModModelLayers {
    public static final EntityModelLayer OBSIDIAN_GOLEM =
            new EntityModelLayer(new Identifier(ModMain.MOD_ID, "obsidian_golem"), "main");
    public static final EntityModelLayer MYSTERIOUS_SPECTER =
            new EntityModelLayer(new Identifier(ModMain.MOD_ID, "mysterious_specter"), "main");
}
