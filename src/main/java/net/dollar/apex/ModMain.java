package net.dollar.apex;

import net.dollar.apex.block.ModBlocks;
import net.dollar.apex.block.entity.ModBlockEntities;
import net.dollar.apex.item.ModItemGroups;
import net.dollar.apex.item.ModItems;
import net.dollar.apex.util.ModLootTableModifiers;
import net.dollar.apex.world.gen.ModWorldGeneration;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.registry.FuelRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ModMain implements ModInitializer {
	public static final String MOD_ID = "apex";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModItems.registerModItems();
		ModItemGroups.registerItemGroups();
		ModBlocks.registerModBlocks();
		ModBlockEntities.registerModTileEntities();
		//ModModelPredicateProvider.registerModModels();  //Must be done in ModMainClient
		ModLootTableModifiers.modifyLootTables();

		ModWorldGeneration.generateModWorldGen();



		registerEventHandlers();



		handleFuelRegistries();
	}

	/**
	 * Handle creation of all fuel registries, allowing items to be burned for fuel in furnaces and smokers.
	 */
	void handleFuelRegistries() {
		FuelRegistry.INSTANCE.add(ModItems.MOLTEN_CORE, 30000);	//25 minutes
	}

	void registerEventHandlers() {
//		ServerLivingEntityEvents.ALLOW_DAMAGE.register((entity, source, amount) -> {
//			//Item worn on chest is the item
//
//			return true;
//		});
	}
}