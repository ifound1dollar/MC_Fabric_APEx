package net.dollar.apex.world.gen;

import net.dollar.apex.entity.ModEntities;
import net.dollar.apex.entity.custom.MysteriousSpecterEntity;
import net.dollar.apex.entity.custom.ObsidianGolemEntity;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.SpawnPlacementTypes;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.level.levelgen.Heightmap;

public class ModEntityGeneration {
    public static void addSpawns() {
        //Regular mob weights (Skeleton, Spider, etc.) are 100 but don't have heightmap restrictions.
        BiomeModifications.addSpawn(BiomeSelectors.foundInOverworld(), MobCategory.MONSTER,
                ModEntities.OBSIDIAN_GOLEM, 150, 1, 1);
        SpawnPlacements.register(ModEntities.OBSIDIAN_GOLEM, SpawnPlacementTypes.ON_GROUND,
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, ObsidianGolemEntity::checkObsidianGolemSpawnRules);

        BiomeModifications.addSpawn(BiomeSelectors.foundInOverworld(), MobCategory.MONSTER,
                ModEntities.MYSTERIOUS_SPECTER, 33, 1, 1);
        SpawnPlacements.register(ModEntities.MYSTERIOUS_SPECTER, SpawnPlacementTypes.ON_GROUND,
                Heightmap.Types.WORLD_SURFACE, MysteriousSpecterEntity::checkMysteriousSpecterSpawnRules);
    }
}
