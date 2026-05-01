package net.dollar.apex.world;

import net.minecraft.world.level.levelgen.placement.BiomeFilter;
import net.minecraft.world.level.levelgen.placement.CountPlacement;
import net.minecraft.world.level.levelgen.placement.InSquarePlacement;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;
import net.minecraft.world.level.levelgen.placement.RarityFilter;
import java.util.List;

public class ModOrePlacement {
    /**
     * Returns List of PlacementModifiers used to generate ore block placements in the world.
     * @param countModifier PlacementModifier applied to ore count
     * @param heightModifier PlacementModifier applied to ore height
     * @return Generated List of PlacementModifiers
     */
    public static List<PlacementModifier> modifiers(PlacementModifier countModifier, PlacementModifier heightModifier) {
        return List.of(countModifier, InSquarePlacement.spread(), heightModifier, BiomeFilter.biome());
    }

    /**
     * Returns List of PlacementModifiers where each item is guaranteed to generate a placement.
     * @param count Number of placements to generate
     * @param heightModifier Determines height range to generate
     * @return Generated List of PlacementModifiers
     */
    public static List<PlacementModifier> modifiersWithCount(int count, PlacementModifier heightModifier) {
        return modifiers(CountPlacement.of(count), heightModifier);
    }

    /**
     * Returns List of PlacementModifiers where each item has only a chance to generate a placement.
     * @param chance Chance to generate a placement (1 / chance)
     * @param heightModifier Determines height range to generate
     * @return Generated List of PlacementModifiers
     */
    public static List<PlacementModifier> modifiersWithRarity(int chance, PlacementModifier heightModifier) {
        return modifiers(RarityFilter.onAverageOnceEvery(chance), heightModifier);
    }
}
