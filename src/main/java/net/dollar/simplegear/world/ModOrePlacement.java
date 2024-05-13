package net.dollar.simplegear.world;

import net.minecraft.world.gen.placementmodifier.*;

import java.util.List;

public class ModOrePlacement {
    /**
     * Returns List of PlacementModifiers used to generate ore block placements in the world.
     * @param countModifier PlacementModifier applied to ore count
     * @param heightModifier PlacementModifier applied to ore height
     * @return Generated List of PlacementModifiers
     */
    public static List<PlacementModifier> modifiers(PlacementModifier countModifier, PlacementModifier heightModifier) {
        return List.of(countModifier, SquarePlacementModifier.of(), heightModifier, BiomePlacementModifier.of());
    }

    /**
     * Returns List of PlacementModifiers where each item is guaranteed to generate a placement.
     * @param count Number of placements to generate
     * @param heightModifier Determines height range to generate
     * @return Generated List of PlacementModifiers
     */
    public static List<PlacementModifier> modifiersWithCount(int count, PlacementModifier heightModifier) {
        return modifiers(CountPlacementModifier.of(count), heightModifier);
    }

    /**
     * Returns List of PlacementModifiers where each item has only a chance to generate a placement.
     * @param chance Chance to generate a placement (1 / chance)
     * @param heightModifier Determines height range to generate
     * @return Generated List of PlacementModifiers
     */
    public static List<PlacementModifier> modifiersWithRarity(int chance, PlacementModifier heightModifier) {
        return modifiers(RarityFilterPlacementModifier.of(chance), heightModifier);
    }
}
