package net.dollar.simplegear;

import net.dollar.simplegear.block.ModBlocks;
import net.dollar.simplegear.block.entity.ModBlockEntities;
import net.dollar.simplegear.item.ModItemGroups;
import net.dollar.simplegear.item.ModItems;
import net.dollar.simplegear.util.ModLootTableModifiers;
import net.dollar.simplegear.util.ModModelPredicateProvider;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.registry.FuelRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ModMainClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		ModModelPredicateProvider.registerModModels();
	}
}