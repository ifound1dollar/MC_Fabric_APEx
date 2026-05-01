package net.dollar.apex.world;

import net.dollar.apex.ModMain;
import net.dollar.apex.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;
import java.util.List;

public class ModConfiguredFeatures {
    public static final ResourceKey<ConfiguredFeature<?, ?>> RUBY_ORE_KEY = registerKey("ruby_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> SAPPHIRE_ORE_KEY = registerKey("sapphire_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> COBALT_ORE_KEY = registerKey("cobalt_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> PHOSPHATE_ORE_KEY = registerKey("phosphate_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> TIN_ORE_KEY = registerKey("tin_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> TIN_ORE_SMALL_KEY = registerKey("tin_ore_small");
    public static final ResourceKey<ConfiguredFeature<?, ?>> TUNGSTEN_ORE_KEY = registerKey("tungsten_ore");



    /**
     * Performs registration of new ConfiguredFeatures.
     * @param context context required within register function
     */
    public static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> context) {
        //Define blocks to potentially replace using vanilla tags.
        RuleTest stoneReplaceables = new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES);
        RuleTest deepslateReplaceables = new TagMatchTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES);

        //Create OreFeatureConfigs for each ore, replacing blocks in vanilla tags with modded ores.
        List<OreConfiguration.TargetBlockState> rubyOres = List.of(
                OreConfiguration.target(stoneReplaceables, ModBlocks.RUBY_ORE.defaultBlockState()),
                OreConfiguration.target(deepslateReplaceables, ModBlocks.DEEPSLATE_RUBY_ORE.defaultBlockState())
        );
        List<OreConfiguration.TargetBlockState> sapphireOres = List.of(
                OreConfiguration.target(stoneReplaceables, ModBlocks.SAPPHIRE_ORE.defaultBlockState()),
                OreConfiguration.target(deepslateReplaceables, ModBlocks.DEEPSLATE_SAPPHIRE_ORE.defaultBlockState())
        );
        List<OreConfiguration.TargetBlockState> cobaltOres = List.of(
                OreConfiguration.target(stoneReplaceables, ModBlocks.COBALT_ORE.defaultBlockState()),
                OreConfiguration.target(deepslateReplaceables, ModBlocks.DEEPSLATE_COBALT_ORE.defaultBlockState())
        );
        List<OreConfiguration.TargetBlockState> phosphateOres = List.of(
                OreConfiguration.target(stoneReplaceables, ModBlocks.PHOSPHATE_ORE.defaultBlockState()),
                OreConfiguration.target(deepslateReplaceables, ModBlocks.DEEPSLATE_PHOSPHATE_ORE.defaultBlockState())
        );
        List<OreConfiguration.TargetBlockState> tinOres = List.of(
                OreConfiguration.target(stoneReplaceables, ModBlocks.TIN_ORE.defaultBlockState()),
                OreConfiguration.target(deepslateReplaceables, ModBlocks.DEEPSLATE_TIN_ORE.defaultBlockState())
        );
        List<OreConfiguration.TargetBlockState> tungstenOres = List.of(
                OreConfiguration.target(stoneReplaceables, ModBlocks.TUNGSTEN_ORE.defaultBlockState()),
                OreConfiguration.target(deepslateReplaceables, ModBlocks.DEEPSLATE_TUNGSTEN_ORE.defaultBlockState())
        );

        //Vein size 3 is Emerald equivalent.
        register(context, RUBY_ORE_KEY, Feature.ORE, new OreConfiguration(rubyOres,
                3));
        register(context, SAPPHIRE_ORE_KEY, Feature.ORE, new OreConfiguration(sapphireOres,
                3));

        //Vein size 12 is regular Diamond equivalent, 4 is small Diamond equivalent.
        register(context, COBALT_ORE_KEY, Feature.ORE, new OreConfiguration(cobaltOres, 4));
        register(context, TUNGSTEN_ORE_KEY, Feature.ORE, new OreConfiguration(tungstenOres, 12, 0.33f));

        //Vein size 7 is Lapis equivalent.
        register(context, PHOSPHATE_ORE_KEY, Feature.ORE, new OreConfiguration(phosphateOres, 7));

        //Vein size 9 is Iron equivalent, 10 is Copper equivalent.
        register(context, TIN_ORE_KEY, Feature.ORE, new OreConfiguration(tinOres, 8));
        register(context, TIN_ORE_SMALL_KEY, Feature.ORE, new OreConfiguration(tinOres, 4));    //Half of regular
    }

    /**
     * Registers a ConfiguredFeature key with the given name and returns the generated RegistryKey.
     * @param name Name corresponding to the key to be created
     * @return The generated RegistryKey
     */
    public static ResourceKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, Identifier.fromNamespaceAndPath(ModMain.MOD_ID, name));
    }

    private static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(
            BootstrapContext<ConfiguredFeature<?, ?>> context, ResourceKey<ConfiguredFeature<?, ?>> key,
            F feature, FC configuration) {
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }
}
