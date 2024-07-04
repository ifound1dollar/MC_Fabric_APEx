package net.dollar.apex.world;

import net.dollar.apex.ModMain;
import net.dollar.apex.block.ModBlocks;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.structure.rule.RuleTest;
import net.minecraft.structure.rule.TagMatchRuleTest;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.gen.feature.OreFeatureConfig;

import java.util.List;

public class ModConfiguredFeatures {
    public static final RegistryKey<ConfiguredFeature<?, ?>> RUBY_ORE_KEY = registerKey("ruby_ore");
    public static final RegistryKey<ConfiguredFeature<?, ?>> SAPPHIRE_ORE_KEY = registerKey("sapphire_ore");
    public static final RegistryKey<ConfiguredFeature<?, ?>> COBALT_ORE_KEY = registerKey("cobalt_ore");
    public static final RegistryKey<ConfiguredFeature<?, ?>> PHOSPHATE_ORE_KEY = registerKey("phosphate_ore");
    public static final RegistryKey<ConfiguredFeature<?, ?>> TIN_ORE_KEY = registerKey("tin_ore");
    public static final RegistryKey<ConfiguredFeature<?, ?>> TIN_ORE_SMALL_KEY = registerKey("tin_ore_small");
    public static final RegistryKey<ConfiguredFeature<?, ?>> TUNGSTEN_ORE_KEY = registerKey("tungsten_ore");



    /**
     * Performs registration of new ConfiguredFeatures.
     * @param context context required within register function
     */
    public static void bootstrap(Registerable<ConfiguredFeature<?, ?>> context) {
        //Define blocks to potentially replace using vanilla tags.
        RuleTest stoneReplaceables = new TagMatchRuleTest(BlockTags.STONE_ORE_REPLACEABLES);
        RuleTest deepslateReplaceables = new TagMatchRuleTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES);

        //Create OreFeatureConfigs for each ore, replacing blocks in vanilla tags with modded ores.
        List<OreFeatureConfig.Target> rubyOres = List.of(
                OreFeatureConfig.createTarget(stoneReplaceables, ModBlocks.RUBY_ORE.getDefaultState()),
                OreFeatureConfig.createTarget(deepslateReplaceables, ModBlocks.DEEPSLATE_RUBY_ORE.getDefaultState())
        );
        List<OreFeatureConfig.Target> sapphireOres = List.of(
                OreFeatureConfig.createTarget(stoneReplaceables, ModBlocks.SAPPHIRE_ORE.getDefaultState()),
                OreFeatureConfig.createTarget(deepslateReplaceables, ModBlocks.DEEPSLATE_SAPPHIRE_ORE.getDefaultState())
        );
        List<OreFeatureConfig.Target> cobaltOres = List.of(
                OreFeatureConfig.createTarget(stoneReplaceables, ModBlocks.COBALT_ORE.getDefaultState()),
                OreFeatureConfig.createTarget(deepslateReplaceables, ModBlocks.DEEPSLATE_COBALT_ORE.getDefaultState())
        );
        List<OreFeatureConfig.Target> phosphateOres = List.of(
                OreFeatureConfig.createTarget(stoneReplaceables, ModBlocks.PHOSPHATE_ORE.getDefaultState()),
                OreFeatureConfig.createTarget(deepslateReplaceables, ModBlocks.DEEPSLATE_PHOSPHATE_ORE.getDefaultState())
        );
        List<OreFeatureConfig.Target> tinOres = List.of(
                OreFeatureConfig.createTarget(stoneReplaceables, ModBlocks.TIN_ORE.getDefaultState()),
                OreFeatureConfig.createTarget(deepslateReplaceables, ModBlocks.DEEPSLATE_TIN_ORE.getDefaultState())
        );
        List<OreFeatureConfig.Target> tungstenOres = List.of(
                OreFeatureConfig.createTarget(stoneReplaceables, ModBlocks.TUNGSTEN_ORE.getDefaultState()),
                OreFeatureConfig.createTarget(deepslateReplaceables, ModBlocks.DEEPSLATE_TUNGSTEN_ORE.getDefaultState())
        );

        //Vein size 3 is Emerald equivalent.
        register(context, RUBY_ORE_KEY, Feature.ORE, new OreFeatureConfig(rubyOres,
                3));
        register(context, SAPPHIRE_ORE_KEY, Feature.ORE, new OreFeatureConfig(sapphireOres,
                3));

        //Vein size 12 is regular Diamond equivalent, 4 is small Diamond equivalent.
        register(context, COBALT_ORE_KEY, Feature.ORE, new OreFeatureConfig(cobaltOres, 4));
        register(context, TUNGSTEN_ORE_KEY, Feature.ORE, new OreFeatureConfig(tungstenOres, 12, 0.33f));

        //Vein size 7 is Lapis equivalent.
        register(context, PHOSPHATE_ORE_KEY, Feature.ORE, new OreFeatureConfig(phosphateOres, 7));

        //Vein size 9 is Iron equivalent, 10 is Copper equivalent.
        register(context, TIN_ORE_KEY, Feature.ORE, new OreFeatureConfig(tinOres, 8));
        register(context, TIN_ORE_SMALL_KEY, Feature.ORE, new OreFeatureConfig(tinOres, 4));    //Half of regular
    }

    /**
     * Registers a ConfiguredFeature key with the given name and returns the generated RegistryKey.
     * @param name Name corresponding to the key to be created
     * @return The generated RegistryKey
     */
    public static RegistryKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return RegistryKey.of(RegistryKeys.CONFIGURED_FEATURE, Identifier.of(ModMain.MOD_ID, name));
    }

    private static <FC extends FeatureConfig, F extends Feature<FC>> void register(
            Registerable<ConfiguredFeature<?, ?>> context, RegistryKey<ConfiguredFeature<?, ?>> key,
            F feature, FC configuration) {
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }
}
