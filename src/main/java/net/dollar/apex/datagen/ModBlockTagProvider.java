package net.dollar.apex.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagProvider extends FabricTagProvider.BlockTagProvider {
    public ModBlockTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }



    @Override
    protected void configure(RegistryWrapper.WrapperLookup arg) {
//        getOrCreateTagBuilder(TagKey.of(RegistryKeys.BLOCK, new Identifier("c", "ores")))
//                .add(ModBlocks.COBALT_ORE).add(ModBlocks.DEEPSLATE_COBALT_ORE)
//                .add(ModBlocks.PHOSPHATE_ORE).add(ModBlocks.DEEPSLATE_PHOSPHATE_ORE)
//                .add(ModBlocks.RUBY_ORE).add(ModBlocks.DEEPSLATE_RUBY_ORE)
//                .add(ModBlocks.SAPPHIRE_ORE).add(ModBlocks.DEEPSLATE_SAPPHIRE_ORE)
//                .add(ModBlocks.TIN_ORE).add(ModBlocks.DEEPSLATE_TIN_ORE)
//                .add(ModBlocks.TUNGSTEN_ORE).add(ModBlocks.DEEPSLATE_TUNGSTEN_ORE)
//                .forceAddTag(BlockTags.REDSTONE_ORES)
//                .forceAddTag(BlockTags.COPPER_ORES)
//                .forceAddTag(BlockTags.GOLD_ORES)
//                .forceAddTag(BlockTags.IRON_ORES)
//                .forceAddTag(BlockTags.COAL_ORES)
//                .forceAddTag(BlockTags.EMERALD_ORES)
//                .forceAddTag(BlockTags.LAPIS_ORES)
//                .forceAddTag(BlockTags.DIAMOND_ORES)
//                .forceAddTag(TagKey.of(RegistryKeys.BLOCK, new Identifier("c", "quartz_ores")));
//
//        getOrCreateTagBuilder(TagKey.of(RegistryKeys.BLOCK, new Identifier("minecraft", "mineable/pickaxe")))
//                .add(ModBlocks.RUBY_BLOCK)
//                .add(ModBlocks.RUBY_ORE)
//                .add(ModBlocks.DEEPSLATE_RUBY_ORE)
//                .add(ModBlocks.SAPPHIRE_BLOCK)
//                .add(ModBlocks.SAPPHIRE_ORE)
//                .add(ModBlocks.DEEPSLATE_SAPPHIRE_ORE)
//                .add(ModBlocks.DECORATIVE_AMETHYST_BLOCK)
//                .add(ModBlocks.COBALT_ORE)
//                .add(ModBlocks.DEEPSLATE_COBALT_ORE)
//                .add(ModBlocks.PHOSPHATE_ORE)
//                .add(ModBlocks.DEEPSLATE_PHOSPHATE_ORE)
//                .add(ModBlocks.TIN_BLOCK)
//                .add(ModBlocks.RAW_TIN_BLOCK)
//                .add(ModBlocks.TIN_ORE)
//                .add(ModBlocks.DEEPSLATE_TIN_ORE)
//                .add(ModBlocks.TUNGSTEN_BLOCK)
//                .add(ModBlocks.RAW_TUNGSTEN_BLOCK)
//                .add(ModBlocks.TUNGSTEN_ORE)
//                .add(ModBlocks.DEEPSLATE_TUNGSTEN_ORE)
//                .add(ModBlocks.BRONZE_BLOCK)
//                .add(ModBlocks.STEEL_BLOCK);
    }
}
