package net.dollar.apex;

import net.dollar.apex.util.ModModelPredicateProvider;
import net.fabricmc.api.ClientModInitializer;

public class ModMainClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		ModModelPredicateProvider.registerModModels();
	}
}