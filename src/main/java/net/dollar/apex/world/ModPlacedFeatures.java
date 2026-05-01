package net.dollar.apex.world;

import net.dollar.apex.ModMain;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.HeightRangePlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;
import java.util.List;

public class ModPlacedFeatures {
    public static final ResourceKey<PlacedFeature> RUBY_ORE_PLACED_KEY = registerKey("ruby_ore_placed");
    public static final ResourceKey<PlacedFeature> SAPPHIRE_ORE_PLACED_KEY = registerKey("sapphire_ore_placed");
    public static final ResourceKey<PlacedFeature> COBALT_ORE_PLACED_KEY = registerKey("cobalt_ore_placed");
    public static final ResourceKey<PlacedFeature> PHOSPHATE_ORE_PLACED_KEY = registerKey("phosphate_ore_placed");
    public static final ResourceKey<PlacedFeature> TIN_ORE_PLACED_KEY = registerKey("tin_ore_placed");
    public static final ResourceKey<PlacedFeature> TIN_ORE_SMALL_PLACED_KEY = registerKey("tin_ore_small_placed");
    public static final ResourceKey<PlacedFeature> TUNGSTEN_ORE_PLACED_KEY = registerKey("tungsten_ore_placed");



    /**
     * Performs registration of new PlacedFeatures.
     * @param context context required within register function
     */
    public static void bootstrap(BootstrapContext<PlacedFeature> context) {
        var registryEntryLookup = context.lookup(Registries.CONFIGURED_FEATURE);

        //IMPORTANT: First param is vein count per chunk, second two are height range (minimum, maximum).
        //Trapezoid height distribution means the median between the two values is the greatest distribution.

        //Both as rare as small Diamond, BUT triangle base is 16 higher (however very small veins).
        register(context, RUBY_ORE_PLACED_KEY, registryEntryLookup.getOrThrow(ModConfiguredFeatures.RUBY_ORE_KEY),
                ModOrePlacement.modifiersWithCount(9, //Veins per chunk
                        HeightRangePlacement.triangle(
                                VerticalAnchor.aboveBottom(-64),
                                VerticalAnchor.aboveBottom(80))));
        register(context, SAPPHIRE_ORE_PLACED_KEY, registryEntryLookup.getOrThrow(ModConfiguredFeatures.SAPPHIRE_ORE_KEY),
                ModOrePlacement.modifiersWithCount(9, //Veins per chunk
                        HeightRangePlacement.triangle(
                                VerticalAnchor.aboveBottom(-64),
                                VerticalAnchor.aboveBottom(80))));

        //Less frequent than Diamond, but smaller veins and higher.
        register(context, COBALT_ORE_PLACED_KEY, registryEntryLookup.getOrThrow(ModConfiguredFeatures.COBALT_ORE_KEY),
                ModOrePlacement.modifiersWithCount(3, //Veins per chunk
                        HeightRangePlacement.triangle(
                                VerticalAnchor.absolute(-48),
                                VerticalAnchor.absolute(32)))); //Largest concentration at -8

        //Less frequent than coal (20/chunk) and lower.
        register(context, PHOSPHATE_ORE_PLACED_KEY, registryEntryLookup.getOrThrow(ModConfiguredFeatures.PHOSPHATE_ORE_KEY),
                ModOrePlacement.modifiersWithCount(8, //Veins per chunk
                        HeightRangePlacement.triangle(
                                VerticalAnchor.absolute(-16),
                                VerticalAnchor.absolute(80))));

        //Slightly less frequent than Iron, and slightly smaller veins.
        register(context, TIN_ORE_PLACED_KEY, registryEntryLookup.getOrThrow(ModConfiguredFeatures.TIN_ORE_KEY),
                ModOrePlacement.modifiersWithCount(7, //Veins per chunk
                        HeightRangePlacement.triangle(
                                VerticalAnchor.absolute(-16),
                                VerticalAnchor.absolute(112))));   //Maximum distribution at 48
        register(context, TIN_ORE_SMALL_PLACED_KEY, registryEntryLookup.getOrThrow(ModConfiguredFeatures.TIN_ORE_SMALL_KEY),
                ModOrePlacement.modifiersWithCount(6, //Veins per chunk
                        HeightRangePlacement.triangle(
                                VerticalAnchor.absolute(-32),
                                VerticalAnchor.absolute(64))));   //Maximum distribution at 16

        //Less frequent than Diamond (more common than gems), but larger veins and slightly higher.
        register(context, TUNGSTEN_ORE_PLACED_KEY, registryEntryLookup.getOrThrow(ModConfiguredFeatures.TUNGSTEN_ORE_KEY),
                ModOrePlacement.modifiersWithCount(3, //Veins per chunk
                        HeightRangePlacement.triangle(
                                VerticalAnchor.aboveBottom(-48),   //Maximum distribution at 16 above bottom (-48)
                                VerticalAnchor.aboveBottom(80))));
    }



    /**
     * Registers a PlacedFeature key with the given name and returns the generated RegistryKey.
     * @param name Name corresponding to the key to be created
     * @return The generated RegistryKey
     */
    public static ResourceKey<PlacedFeature> registerKey(String name) {
        return ResourceKey.create(Registries.PLACED_FEATURE, Identifier.fromNamespaceAndPath(ModMain.MOD_ID, name));
    }

    private static void register(BootstrapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key,
                                 Holder<ConfiguredFeature<?, ?>> config, List<PlacementModifier> modifiers) {
        context.register(key, new PlacedFeature(config, List.copyOf(modifiers)));
    }
}
