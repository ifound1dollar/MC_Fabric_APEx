package net.dollar.apex.datagen;

import net.dollar.apex.block.ModBlocks;
import net.dollar.apex.item.ModItems;
import net.dollar.apex.util.ModEquipmentAssetKeys;
import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.client.data.BlockStateModelGenerator;
import net.minecraft.client.data.ItemModelGenerator;
import net.minecraft.client.data.Models;

public class ModModelProvider extends FabricModelProvider {
    public ModModelProvider(FabricDataOutput output) {
        super(output);
    }



    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.RUBY_ORE);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.DEEPSLATE_RUBY_ORE);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.RUBY_BLOCK);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.SAPPHIRE_ORE);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.DEEPSLATE_SAPPHIRE_ORE);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.SAPPHIRE_BLOCK);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.DECORATIVE_AMETHYST_BLOCK);

        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.COBALT_BLOCK);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.COBALT_ORE);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.DEEPSLATE_COBALT_ORE);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.PHOSPHATE_ORE);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.DEEPSLATE_PHOSPHATE_ORE);

        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.TIN_BLOCK);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.RAW_TIN_BLOCK);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.TIN_ORE);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.DEEPSLATE_TIN_ORE);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.TUNGSTEN_BLOCK);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.RAW_TUNGSTEN_BLOCK);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.TUNGSTEN_ORE);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.DEEPSLATE_TUNGSTEN_ORE);

        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.BRONZE_BLOCK);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.STEEL_BLOCK);
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        //GENERATED
        itemModelGenerator.register(ModItems.FERTILIZER, Models.GENERATED);

        itemModelGenerator.register(ModItems.RUBY, Models.GENERATED);
        itemModelGenerator.register(ModItems.SAPPHIRE, Models.GENERATED);
        itemModelGenerator.register(ModItems.COBALT_SHARD, Models.GENERATED);
        itemModelGenerator.register(ModItems.PHOSPHATE_POWDER, Models.GENERATED);
        itemModelGenerator.register(ModItems.RAW_TIN, Models.GENERATED);
        itemModelGenerator.register(ModItems.TIN_INGOT, Models.GENERATED);
        itemModelGenerator.register(ModItems.TIN_NUGGET, Models.GENERATED);
        itemModelGenerator.register(ModItems.RAW_TUNGSTEN, Models.GENERATED);
        itemModelGenerator.register(ModItems.TUNGSTEN_INGOT, Models.GENERATED);
        itemModelGenerator.register(ModItems.TUNGSTEN_NUGGET, Models.GENERATED);
        itemModelGenerator.register(ModItems.BRONZE_COMPOUND, Models.GENERATED);
        itemModelGenerator.register(ModItems.BRONZE_INGOT, Models.GENERATED);
        itemModelGenerator.register(ModItems.BRONZE_NUGGET, Models.GENERATED);
        itemModelGenerator.register(ModItems.STEEL_COMPOUND, Models.GENERATED);
        itemModelGenerator.register(ModItems.STEEL_INGOT, Models.GENERATED);
        itemModelGenerator.register(ModItems.STEEL_NUGGET, Models.GENERATED);

        itemModelGenerator.register(ModItems.HANDFUL_OF_STARDUST, Models.GENERATED);
        itemModelGenerator.register(ModItems.MOLTEN_CORE, Models.GENERATED);
        itemModelGenerator.register(ModItems.COBALT_STEEL_INGOT, Models.GENERATED);
        itemModelGenerator.register(ModItems.INFUSED_GEMSTONE, Models.GENERATED);
        itemModelGenerator.register(ModItems.TUNGSTEN_CARBIDE_INGOT, Models.GENERATED);

        itemModelGenerator.register(ModItems.BASIC_UPGRADE_TEMPLATE, Models.GENERATED);
        itemModelGenerator.register(ModItems.CARBIDE_UPGRADE_TEMPLATE, Models.GENERATED);
        itemModelGenerator.register(ModItems.COBALT_UPGRADE_TEMPLATE, Models.GENERATED);
        itemModelGenerator.register(ModItems.INFUSION_UPGRADE_TEMPLATE, Models.GENERATED);

        itemModelGenerator.register(ModItems.TROPHY_OBSIDIAN_DUST, Models.GENERATED);
        itemModelGenerator.register(ModItems.TROPHY_OMINOUS_LETTER, Models.GENERATED);
//        itemModelGenerator.register(ModItems.COLLECTOR_POTION_OF_EVERLASTING_YOUTH, Models.GENERATED);
//        itemModelGenerator.register(ModItems.COLLECTOR_SLIGHTLY_OVERCOOKED_CHICKEN, Models.GENERATED);



        //HANDHELD
        itemModelGenerator.register(ModItems.BRONZE_AXE, Models.HANDHELD);
        itemModelGenerator.register(ModItems.BRONZE_HOE, Models.HANDHELD);
        itemModelGenerator.register(ModItems.BRONZE_PICKAXE, Models.HANDHELD);
        itemModelGenerator.register(ModItems.BRONZE_SHOVEL, Models.HANDHELD);
        itemModelGenerator.register(ModItems.BRONZE_SWORD, Models.HANDHELD);

        itemModelGenerator.register(ModItems.GILDED_BRONZE_AXE, Models.HANDHELD);
        itemModelGenerator.register(ModItems.GILDED_BRONZE_HOE, Models.HANDHELD);
        itemModelGenerator.register(ModItems.GILDED_BRONZE_PICKAXE, Models.HANDHELD);
        itemModelGenerator.register(ModItems.GILDED_BRONZE_SHOVEL, Models.HANDHELD);
        itemModelGenerator.register(ModItems.GILDED_BRONZE_SWORD, Models.HANDHELD);

        itemModelGenerator.register(ModItems.DIAMOND_BATTLEAXE, Models.HANDHELD);
        itemModelGenerator.register(ModItems.DIAMOND_PAXEL, Models.HANDHELD);

        itemModelGenerator.register(ModItems.NETHERITE_BATTLEAXE, Models.HANDHELD);
        itemModelGenerator.register(ModItems.NETHERITE_PAXEL, Models.HANDHELD);

        itemModelGenerator.register(ModItems.COBALT_STEEL_AXE, Models.HANDHELD);
        itemModelGenerator.register(ModItems.COBALT_STEEL_BATTLEAXE, Models.HANDHELD);
        itemModelGenerator.register(ModItems.COBALT_STEEL_HOE, Models.HANDHELD);
        itemModelGenerator.register(ModItems.COBALT_STEEL_PAXEL, Models.HANDHELD);
        itemModelGenerator.register(ModItems.COBALT_STEEL_PICKAXE, Models.HANDHELD);
        itemModelGenerator.register(ModItems.COBALT_STEEL_SHOVEL, Models.HANDHELD);
        itemModelGenerator.register(ModItems.COBALT_STEEL_SWORD, Models.HANDHELD);

        itemModelGenerator.register(ModItems.INFUSED_GEMSTONE_AXE, Models.HANDHELD);
        itemModelGenerator.register(ModItems.INFUSED_GEMSTONE_BATTLEAXE, Models.HANDHELD);
        itemModelGenerator.register(ModItems.INFUSED_GEMSTONE_HOE, Models.HANDHELD);
        itemModelGenerator.register(ModItems.INFUSED_GEMSTONE_PAXEL, Models.HANDHELD);
        itemModelGenerator.register(ModItems.INFUSED_GEMSTONE_PICKAXE, Models.HANDHELD);
        itemModelGenerator.register(ModItems.INFUSED_GEMSTONE_SHOVEL, Models.HANDHELD);
        itemModelGenerator.register(ModItems.INFUSED_GEMSTONE_SWORD, Models.HANDHELD);

        itemModelGenerator.register(ModItems.TUNGSTEN_CARBIDE_AXE, Models.HANDHELD);
        itemModelGenerator.register(ModItems.TUNGSTEN_CARBIDE_BATTLEAXE, Models.HANDHELD);
        itemModelGenerator.register(ModItems.TUNGSTEN_CARBIDE_HOE, Models.HANDHELD);
        itemModelGenerator.register(ModItems.TUNGSTEN_CARBIDE_PAXEL, Models.HANDHELD);
        itemModelGenerator.register(ModItems.TUNGSTEN_CARBIDE_PICKAXE, Models.HANDHELD);
        itemModelGenerator.register(ModItems.TUNGSTEN_CARBIDE_SHOVEL, Models.HANDHELD);
        itemModelGenerator.register(ModItems.TUNGSTEN_CARBIDE_SWORD, Models.HANDHELD);

        // BOW/CROSSBOW
        itemModelGenerator.registerBow(ModItems.COBALT_STEEL_BOW);
        itemModelGenerator.registerBow(ModItems.INFUSED_GEMSTONE_BOW);
        itemModelGenerator.registerBow(ModItems.TUNGSTEN_CARBIDE_BOW);
        itemModelGenerator.registerCrossbow(ModItems.COBALT_STEEL_CROSSBOW);
        itemModelGenerator.registerCrossbow(ModItems.INFUSED_GEMSTONE_CROSSBOW);
        itemModelGenerator.registerCrossbow(ModItems.TUNGSTEN_CARBIDE_CROSSBOW);

        // ARMORS
        itemModelGenerator.registerArmor(ModItems.BRONZE_HELMET, ModEquipmentAssetKeys.BRONZE,
                "helmet", false);
        itemModelGenerator.registerArmor(ModItems.BRONZE_CHESTPLATE, ModEquipmentAssetKeys.BRONZE,
                "chestplate", false);
        itemModelGenerator.registerArmor(ModItems.BRONZE_LEGGINGS, ModEquipmentAssetKeys.BRONZE,
                "leggings", false);
        itemModelGenerator.registerArmor(ModItems.BRONZE_BOOTS, ModEquipmentAssetKeys.BRONZE,
                "boots", false);

        itemModelGenerator.registerArmor(ModItems.GILDED_BRONZE_HELMET, ModEquipmentAssetKeys.GILDED_BRONZE,
                "helmet", false);
        itemModelGenerator.registerArmor(ModItems.GILDED_BRONZE_CHESTPLATE, ModEquipmentAssetKeys.GILDED_BRONZE,
                "chestplate", false);
        itemModelGenerator.registerArmor(ModItems.GILDED_BRONZE_LEGGINGS, ModEquipmentAssetKeys.GILDED_BRONZE,
                "leggings", false);
        itemModelGenerator.registerArmor(ModItems.GILDED_BRONZE_BOOTS, ModEquipmentAssetKeys.GILDED_BRONZE,
                "boots", false);

        itemModelGenerator.registerArmor(ModItems.COBALT_STEEL_HELMET, ModEquipmentAssetKeys.COBALT_STEEL,
                "helmet", false);
        itemModelGenerator.registerArmor(ModItems.COBALT_STEEL_CHESTPLATE, ModEquipmentAssetKeys.COBALT_STEEL,
                "chestplate", false);
        itemModelGenerator.registerArmor(ModItems.COBALT_STEEL_LEGGINGS, ModEquipmentAssetKeys.COBALT_STEEL,
                "leggings", false);
        itemModelGenerator.registerArmor(ModItems.COBALT_STEEL_BOOTS, ModEquipmentAssetKeys.COBALT_STEEL,
                "boots", false);

        itemModelGenerator.registerArmor(ModItems.INFUSED_GEMSTONE_HELMET, ModEquipmentAssetKeys.INFUSED_GEMSTONE,
                "helmet", false);
        itemModelGenerator.registerArmor(ModItems.INFUSED_GEMSTONE_CHESTPLATE, ModEquipmentAssetKeys.INFUSED_GEMSTONE,
                "chestplate", false);
        itemModelGenerator.registerArmor(ModItems.INFUSED_GEMSTONE_LEGGINGS, ModEquipmentAssetKeys.INFUSED_GEMSTONE,
                "leggings", false);
        itemModelGenerator.registerArmor(ModItems.INFUSED_GEMSTONE_BOOTS, ModEquipmentAssetKeys.INFUSED_GEMSTONE,
                "boots", false);

        itemModelGenerator.registerArmor(ModItems.TUNGSTEN_CARBIDE_HELMET, ModEquipmentAssetKeys.TUNGSTEN_CARBIDE,
                "helmet", false);
        itemModelGenerator.registerArmor(ModItems.TUNGSTEN_CARBIDE_CHESTPLATE, ModEquipmentAssetKeys.TUNGSTEN_CARBIDE,
                "chestplate", false);
        itemModelGenerator.registerArmor(ModItems.TUNGSTEN_CARBIDE_LEGGINGS, ModEquipmentAssetKeys.TUNGSTEN_CARBIDE,
                "leggings", false);
        itemModelGenerator.registerArmor(ModItems.TUNGSTEN_CARBIDE_BOOTS, ModEquipmentAssetKeys.TUNGSTEN_CARBIDE,
                "boots", false);

        // SPAWN EGGS
        itemModelGenerator.registerSpawnEgg(ModItems.OBSIDIAN_GOLEM_SPAWN_EGG, 0x12031E, 0xED4D0E);
        itemModelGenerator.registerSpawnEgg(ModItems.MYSTERIOUS_SPECTER_SPAWN_EGG, 0xE3E3E3, 0xB8B8B8);
    }
}
