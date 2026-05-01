package net.dollar.apex.datagen;

import net.dollar.apex.block.ModBlocks;
import net.dollar.apex.item.ModItems;
import net.dollar.apex.util.ModEquipmentAssetKeys;
import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.model.ModelTemplates;

public class ModModelProvider extends FabricModelProvider {
    public ModModelProvider(FabricDataOutput output) {
        super(output);
    }



    @Override
    public void generateBlockStateModels(BlockModelGenerators blockStateModelGenerator) {
        blockStateModelGenerator.createTrivialCube(ModBlocks.RUBY_ORE);
        blockStateModelGenerator.createTrivialCube(ModBlocks.DEEPSLATE_RUBY_ORE);
        blockStateModelGenerator.createTrivialCube(ModBlocks.RUBY_BLOCK);
        blockStateModelGenerator.createTrivialCube(ModBlocks.SAPPHIRE_ORE);
        blockStateModelGenerator.createTrivialCube(ModBlocks.DEEPSLATE_SAPPHIRE_ORE);
        blockStateModelGenerator.createTrivialCube(ModBlocks.SAPPHIRE_BLOCK);
        blockStateModelGenerator.createTrivialCube(ModBlocks.DECORATIVE_AMETHYST_BLOCK);

        blockStateModelGenerator.createTrivialCube(ModBlocks.COBALT_BLOCK);
        blockStateModelGenerator.createTrivialCube(ModBlocks.COBALT_ORE);
        blockStateModelGenerator.createTrivialCube(ModBlocks.DEEPSLATE_COBALT_ORE);
        blockStateModelGenerator.createTrivialCube(ModBlocks.PHOSPHATE_ORE);
        blockStateModelGenerator.createTrivialCube(ModBlocks.DEEPSLATE_PHOSPHATE_ORE);

        blockStateModelGenerator.createTrivialCube(ModBlocks.TIN_BLOCK);
        blockStateModelGenerator.createTrivialCube(ModBlocks.RAW_TIN_BLOCK);
        blockStateModelGenerator.createTrivialCube(ModBlocks.TIN_ORE);
        blockStateModelGenerator.createTrivialCube(ModBlocks.DEEPSLATE_TIN_ORE);
        blockStateModelGenerator.createTrivialCube(ModBlocks.TUNGSTEN_BLOCK);
        blockStateModelGenerator.createTrivialCube(ModBlocks.RAW_TUNGSTEN_BLOCK);
        blockStateModelGenerator.createTrivialCube(ModBlocks.TUNGSTEN_ORE);
        blockStateModelGenerator.createTrivialCube(ModBlocks.DEEPSLATE_TUNGSTEN_ORE);

        blockStateModelGenerator.createTrivialCube(ModBlocks.BRONZE_BLOCK);
        blockStateModelGenerator.createTrivialCube(ModBlocks.STEEL_BLOCK);
    }

    @Override
    public void generateItemModels(ItemModelGenerators itemModelGenerator) {
        //GENERATED
        itemModelGenerator.generateFlatItem(ModItems.FERTILIZER, ModelTemplates.FLAT_ITEM);

        itemModelGenerator.generateFlatItem(ModItems.RUBY, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.SAPPHIRE, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.COBALT_SHARD, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.PHOSPHATE_POWDER, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.RAW_TIN, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.TIN_INGOT, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.TIN_NUGGET, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.RAW_TUNGSTEN, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.TUNGSTEN_INGOT, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.TUNGSTEN_NUGGET, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.BRONZE_COMPOUND, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.BRONZE_INGOT, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.BRONZE_NUGGET, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.STEEL_COMPOUND, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.STEEL_INGOT, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.STEEL_NUGGET, ModelTemplates.FLAT_ITEM);

        itemModelGenerator.generateFlatItem(ModItems.HANDFUL_OF_STARDUST, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.MOLTEN_CORE, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.COBALT_STEEL_INGOT, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.INFUSED_GEMSTONE, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.TUNGSTEN_CARBIDE_INGOT, ModelTemplates.FLAT_ITEM);

        itemModelGenerator.generateFlatItem(ModItems.BASIC_UPGRADE_TEMPLATE, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.CARBIDE_UPGRADE_TEMPLATE, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.COBALT_UPGRADE_TEMPLATE, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.INFUSION_UPGRADE_TEMPLATE, ModelTemplates.FLAT_ITEM);

        itemModelGenerator.generateFlatItem(ModItems.TROPHY_OBSIDIAN_DUST, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.TROPHY_OMINOUS_LETTER, ModelTemplates.FLAT_ITEM);
//        itemModelGenerator.register(ModItems.COLLECTOR_POTION_OF_EVERLASTING_YOUTH, Models.GENERATED);
//        itemModelGenerator.register(ModItems.COLLECTOR_SLIGHTLY_OVERCOOKED_CHICKEN, Models.GENERATED);



        //HANDHELD
        itemModelGenerator.generateFlatItem(ModItems.BRONZE_AXE, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.BRONZE_HOE, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.BRONZE_PICKAXE, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.BRONZE_SHOVEL, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.BRONZE_SWORD, ModelTemplates.FLAT_HANDHELD_ITEM);

        itemModelGenerator.generateFlatItem(ModItems.GILDED_BRONZE_AXE, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.GILDED_BRONZE_HOE, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.GILDED_BRONZE_PICKAXE, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.GILDED_BRONZE_SHOVEL, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.GILDED_BRONZE_SWORD, ModelTemplates.FLAT_HANDHELD_ITEM);

        itemModelGenerator.generateFlatItem(ModItems.DIAMOND_BATTLEAXE, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.DIAMOND_PAXEL, ModelTemplates.FLAT_HANDHELD_ITEM);

        itemModelGenerator.generateFlatItem(ModItems.NETHERITE_BATTLEAXE, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.NETHERITE_PAXEL, ModelTemplates.FLAT_HANDHELD_ITEM);

        itemModelGenerator.generateFlatItem(ModItems.COBALT_STEEL_AXE, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.COBALT_STEEL_BATTLEAXE, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.COBALT_STEEL_HOE, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.COBALT_STEEL_PAXEL, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.COBALT_STEEL_PICKAXE, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.COBALT_STEEL_SHOVEL, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.COBALT_STEEL_SWORD, ModelTemplates.FLAT_HANDHELD_ITEM);

        itemModelGenerator.generateFlatItem(ModItems.INFUSED_GEMSTONE_AXE, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.INFUSED_GEMSTONE_BATTLEAXE, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.INFUSED_GEMSTONE_HOE, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.INFUSED_GEMSTONE_PAXEL, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.INFUSED_GEMSTONE_PICKAXE, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.INFUSED_GEMSTONE_SHOVEL, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.INFUSED_GEMSTONE_SWORD, ModelTemplates.FLAT_HANDHELD_ITEM);

        itemModelGenerator.generateFlatItem(ModItems.TUNGSTEN_CARBIDE_AXE, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.TUNGSTEN_CARBIDE_BATTLEAXE, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.TUNGSTEN_CARBIDE_HOE, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.TUNGSTEN_CARBIDE_PAXEL, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.TUNGSTEN_CARBIDE_PICKAXE, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.TUNGSTEN_CARBIDE_SHOVEL, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.TUNGSTEN_CARBIDE_SWORD, ModelTemplates.FLAT_HANDHELD_ITEM);

        // BOW/CROSSBOW
        itemModelGenerator.generateBow(ModItems.COBALT_STEEL_BOW);
        itemModelGenerator.generateBow(ModItems.INFUSED_GEMSTONE_BOW);
        itemModelGenerator.generateBow(ModItems.TUNGSTEN_CARBIDE_BOW);
        itemModelGenerator.generateCrossbow(ModItems.COBALT_STEEL_CROSSBOW);
        itemModelGenerator.generateCrossbow(ModItems.INFUSED_GEMSTONE_CROSSBOW);
        itemModelGenerator.generateCrossbow(ModItems.TUNGSTEN_CARBIDE_CROSSBOW);

        // ARMORS
        itemModelGenerator.generateTrimmableItem(ModItems.BRONZE_HELMET, ModEquipmentAssetKeys.BRONZE,
                ItemModelGenerators.TRIM_PREFIX_HELMET, false);
        itemModelGenerator.generateTrimmableItem(ModItems.BRONZE_CHESTPLATE, ModEquipmentAssetKeys.BRONZE,
                ItemModelGenerators.TRIM_PREFIX_CHESTPLATE, false);
        itemModelGenerator.generateTrimmableItem(ModItems.BRONZE_LEGGINGS, ModEquipmentAssetKeys.BRONZE,
                ItemModelGenerators.TRIM_PREFIX_LEGGINGS, false);
        itemModelGenerator.generateTrimmableItem(ModItems.BRONZE_BOOTS, ModEquipmentAssetKeys.BRONZE,
                ItemModelGenerators.TRIM_PREFIX_BOOTS, false);

        itemModelGenerator.generateTrimmableItem(ModItems.GILDED_BRONZE_HELMET, ModEquipmentAssetKeys.GILDED_BRONZE,
                ItemModelGenerators.TRIM_PREFIX_HELMET, false);
        itemModelGenerator.generateTrimmableItem(ModItems.GILDED_BRONZE_CHESTPLATE, ModEquipmentAssetKeys.GILDED_BRONZE,
                ItemModelGenerators.TRIM_PREFIX_CHESTPLATE, false);
        itemModelGenerator.generateTrimmableItem(ModItems.GILDED_BRONZE_LEGGINGS, ModEquipmentAssetKeys.GILDED_BRONZE,
                ItemModelGenerators.TRIM_PREFIX_LEGGINGS, false);
        itemModelGenerator.generateTrimmableItem(ModItems.GILDED_BRONZE_BOOTS, ModEquipmentAssetKeys.GILDED_BRONZE,
                ItemModelGenerators.TRIM_PREFIX_BOOTS, false);

        itemModelGenerator.generateTrimmableItem(ModItems.COBALT_STEEL_HELMET, ModEquipmentAssetKeys.COBALT_STEEL,
                ItemModelGenerators.TRIM_PREFIX_HELMET, false);
        itemModelGenerator.generateTrimmableItem(ModItems.COBALT_STEEL_CHESTPLATE, ModEquipmentAssetKeys.COBALT_STEEL,
                ItemModelGenerators.TRIM_PREFIX_CHESTPLATE, false);
        itemModelGenerator.generateTrimmableItem(ModItems.COBALT_STEEL_LEGGINGS, ModEquipmentAssetKeys.COBALT_STEEL,
                ItemModelGenerators.TRIM_PREFIX_LEGGINGS, false);
        itemModelGenerator.generateTrimmableItem(ModItems.COBALT_STEEL_BOOTS, ModEquipmentAssetKeys.COBALT_STEEL,
                ItemModelGenerators.TRIM_PREFIX_BOOTS, false);

        itemModelGenerator.generateTrimmableItem(ModItems.INFUSED_GEMSTONE_HELMET, ModEquipmentAssetKeys.INFUSED_GEMSTONE,
                ItemModelGenerators.TRIM_PREFIX_HELMET, false);
        itemModelGenerator.generateTrimmableItem(ModItems.INFUSED_GEMSTONE_CHESTPLATE, ModEquipmentAssetKeys.INFUSED_GEMSTONE,
                ItemModelGenerators.TRIM_PREFIX_CHESTPLATE, false);
        itemModelGenerator.generateTrimmableItem(ModItems.INFUSED_GEMSTONE_LEGGINGS, ModEquipmentAssetKeys.INFUSED_GEMSTONE,
                ItemModelGenerators.TRIM_PREFIX_LEGGINGS, false);
        itemModelGenerator.generateTrimmableItem(ModItems.INFUSED_GEMSTONE_BOOTS, ModEquipmentAssetKeys.INFUSED_GEMSTONE,
                ItemModelGenerators.TRIM_PREFIX_BOOTS, false);

        itemModelGenerator.generateTrimmableItem(ModItems.TUNGSTEN_CARBIDE_HELMET, ModEquipmentAssetKeys.TUNGSTEN_CARBIDE,
                ItemModelGenerators.TRIM_PREFIX_HELMET, false);
        itemModelGenerator.generateTrimmableItem(ModItems.TUNGSTEN_CARBIDE_CHESTPLATE, ModEquipmentAssetKeys.TUNGSTEN_CARBIDE,
                ItemModelGenerators.TRIM_PREFIX_CHESTPLATE, false);
        itemModelGenerator.generateTrimmableItem(ModItems.TUNGSTEN_CARBIDE_LEGGINGS, ModEquipmentAssetKeys.TUNGSTEN_CARBIDE,
                ItemModelGenerators.TRIM_PREFIX_LEGGINGS, false);
        itemModelGenerator.generateTrimmableItem(ModItems.TUNGSTEN_CARBIDE_BOOTS, ModEquipmentAssetKeys.TUNGSTEN_CARBIDE,
                ItemModelGenerators.TRIM_PREFIX_BOOTS, false);

        // SPAWN EGGS
        itemModelGenerator.generateFlatItem(ModItems.OBSIDIAN_GOLEM_SPAWN_EGG, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.MYSTERIOUS_SPECTER_SPAWN_EGG, ModelTemplates.FLAT_ITEM);
    }
}
