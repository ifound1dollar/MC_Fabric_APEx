package net.dollar.apex.datagen;

import net.dollar.apex.block.ModBlocks;
import net.dollar.apex.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Models;

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
    }
}
