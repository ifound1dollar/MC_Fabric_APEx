package net.dollar.apex;

import net.dollar.apex.entity.ModEntities;
import net.dollar.apex.entity.client.*;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.ModelLayerRegistry;

public class ModMainClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		EntityRendererRegistry.register(ModEntities.OBSIDIAN_GOLEM, ObsidianGolemRenderer::new);
		ModelLayerRegistry.registerModelLayer(ModModelLayers.OBSIDIAN_GOLEM, ObsidianGolemModel::getTexturedModelData);

		EntityRendererRegistry.register(ModEntities.MYSTERIOUS_SPECTER, MysteriousSpecterRenderer::new);
		ModelLayerRegistry.registerModelLayer(ModModelLayers.MYSTERIOUS_SPECTER, MysteriousSpecterModel::getTexturedModelData);
	}
}