package net.dollar.apex;

import net.dollar.apex.entity.ModEntities;
import net.dollar.apex.entity.client.*;
import net.dollar.apex.util.ModModelPredicateProvider;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;

public class ModMainClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		ModModelPredicateProvider.registerModModels();

		EntityRendererRegistry.register(ModEntities.OBSIDIAN_GOLEM, ObsidianGolemRenderer::new);
		EntityModelLayerRegistry.registerModelLayer(ModModelLayers.OBSIDIAN_GOLEM, ObsidianGolemModel::getTexturedModelData);

		EntityRendererRegistry.register(ModEntities.MYSTERIOUS_SPECTER, MysteriousSpecterRenderer::new);
		EntityModelLayerRegistry.registerModelLayer(ModModelLayers.MYSTERIOUS_SPECTER, MysteriousSpecterModel::getTexturedModelData);
	}
}