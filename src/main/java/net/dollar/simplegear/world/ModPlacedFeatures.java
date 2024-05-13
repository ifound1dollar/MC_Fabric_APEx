package net.dollar.simplegear.world;

import net.dollar.simplegear.ModMain;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.PlacedFeature;
import net.minecraft.world.gen.placementmodifier.*;

import java.util.List;

public class ModPlacedFeatures {
    public static final RegistryKey<PlacedFeature> RUBY_ORE_PLACED_KEY = registerKey("ruby_ore_placed");
    public static final RegistryKey<PlacedFeature> SAPPHIRE_ORE_PLACED_KEY = registerKey("sapphire_ore_placed");
    public static final RegistryKey<PlacedFeature> COBALT_ORE_PLACED_KEY = registerKey("cobalt_ore_placed");
    public static final RegistryKey<PlacedFeature> PHOSPHATE_ORE_PLACED_KEY = registerKey("phosphate_ore_placed");
    public static final RegistryKey<PlacedFeature> TIN_ORE_PLACED_KEY = registerKey("tin_ore_placed");
    public static final RegistryKey<PlacedFeature> TIN_ORE_SMALL_PLACED_KEY = registerKey("tin_ore_small_placed");
    public static final RegistryKey<PlacedFeature> TUNGSTEN_ORE_PLACED_KEY = registerKey("tungsten_ore_placed");



    /**
     * Performs registration of new PlacedFeatures.
     * @param context context required within register function
     */
    public static void bootstrap(Registerable<PlacedFeature> context) {
        var registryEntryLookup = context.getRegistryLookup(RegistryKeys.CONFIGURED_FEATURE);

        //IMPORTANT: First param is vein count per chunk, second two are height range (minimum, maximum).
        //Trapezoid height distribution means the median between the two values is the greatest distribution.

        //Both as rare as small Diamond, BUT triangle base is 16 higher (however very small veins).
        register(context, RUBY_ORE_PLACED_KEY, registryEntryLookup.getOrThrow(ModConfiguredFeatures.RUBY_ORE_KEY),
                ModOrePlacement.modifiersWithCount(8, //Veins per chunk
                        HeightRangePlacementModifier.trapezoid(
                                YOffset.aboveBottom(-64),
                                YOffset.aboveBottom(80))));
        register(context, SAPPHIRE_ORE_PLACED_KEY, registryEntryLookup.getOrThrow(ModConfiguredFeatures.SAPPHIRE_ORE_KEY),
                ModOrePlacement.modifiersWithCount(8, //Veins per chunk
                        HeightRangePlacementModifier.trapezoid(
                                YOffset.aboveBottom(-64),
                                YOffset.aboveBottom(80))));

        //Less frequent than Diamond, but smaller veins and higher.
        register(context, COBALT_ORE_PLACED_KEY, registryEntryLookup.getOrThrow(ModConfiguredFeatures.COBALT_ORE_KEY),
                ModOrePlacement.modifiersWithCount(4, //Veins per chunk
                        HeightRangePlacementModifier.trapezoid(
                                YOffset.fixed(-48),
                                YOffset.fixed(32)))); //Largest concentration at -8

        //Less frequent than coal (20/chunk) and lower.
        register(context, PHOSPHATE_ORE_PLACED_KEY, registryEntryLookup.getOrThrow(ModConfiguredFeatures.PHOSPHATE_ORE_KEY),
                ModOrePlacement.modifiersWithCount(10, //Veins per chunk
                        HeightRangePlacementModifier.trapezoid(
                                YOffset.fixed(-16),
                                YOffset.fixed(80))));

        //Slightly less frequent than Iron, and slightly smaller veins.
        register(context, TIN_ORE_PLACED_KEY, registryEntryLookup.getOrThrow(ModConfiguredFeatures.TIN_ORE_KEY),
                ModOrePlacement.modifiersWithCount(6, //Veins per chunk
                        HeightRangePlacementModifier.trapezoid(
                                YOffset.fixed(16),
                                YOffset.fixed(96))));
        register(context, TIN_ORE_SMALL_PLACED_KEY, registryEntryLookup.getOrThrow(ModConfiguredFeatures.TIN_ORE_SMALL_KEY),
                ModOrePlacement.modifiersWithCount(7, //Veins per chunk
                        HeightRangePlacementModifier.trapezoid(
                                YOffset.fixed(-16),
                                YOffset.fixed(48))));   //Maximum distribution at 16

        //Less frequent than Diamond (more common than gems), but larger veins and slightly higher.
        register(context, TUNGSTEN_ORE_PLACED_KEY, registryEntryLookup.getOrThrow(ModConfiguredFeatures.TUNGSTEN_ORE_KEY),
                ModOrePlacement.modifiersWithCount(8, //Veins per chunk
                        HeightRangePlacementModifier.trapezoid(
                                YOffset.aboveBottom(-48),   //Maximum distribution at 16 above bottom (-48)
                                YOffset.aboveBottom(80))));
    }



    /**
     * Registers a PlacedFeature key with the given name and returns the generated RegistryKey.
     * @param name Name corresponding to the key to be created
     * @return The generated RegistryKey
     */
    public static RegistryKey<PlacedFeature> registerKey(String name) {
        return RegistryKey.of(RegistryKeys.PLACED_FEATURE, new Identifier(ModMain.MOD_ID, name));
    }

    private static void register(Registerable<PlacedFeature> context, RegistryKey<PlacedFeature> key,
                                 RegistryEntry<ConfiguredFeature<?, ?>> config, List<PlacementModifier> modifiers) {
        context.register(key, new PlacedFeature(config, List.copyOf(modifiers)));
    }
}
