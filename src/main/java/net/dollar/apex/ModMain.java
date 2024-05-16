package net.dollar.apex;

import net.dollar.apex.block.ModBlocks;
import net.dollar.apex.block.entity.ModBlockEntities;
import net.dollar.apex.entity.ModEntities;
import net.dollar.apex.entity.custom.ObsidianGolemEntity;
import net.dollar.apex.item.ModItemGroups;
import net.dollar.apex.item.ModItems;
import net.dollar.apex.util.ModLootTableModifiers;
import net.dollar.apex.world.gen.ModWorldGeneration;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
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
		handleEntityRegistries();
	}



	/**
	 * Handle creation of all fuel registries, allowing items to be burned for fuel in furnaces and smokers.
	 */
	void handleFuelRegistries() {
		FuelRegistry.INSTANCE.add(ModItems.MOLTEN_CORE, 30000);	//25 minutes
	}

	/**
	 * Handles registering of all entities, specifically to call functions to create mob attributes.
	 */
	private void handleEntityRegistries() {
		FabricDefaultAttributeRegistry.register(ModEntities.OBSIDIAN_GOLEM, ObsidianGolemEntity.createAttributes());
	}

	void registerEventHandlers() {
//		ServerLivingEntityEvents.ALLOW_DAMAGE.register((entity, source, amount) -> {
//			//Item worn on chest is the item
//
//			return true;
//		});
	}
}