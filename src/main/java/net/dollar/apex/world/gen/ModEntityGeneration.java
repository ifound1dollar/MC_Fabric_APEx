package net.dollar.apex.world.gen;

import net.dollar.apex.entity.ModEntities;
import net.dollar.apex.entity.custom.MysteriousSpecterEntity;
import net.dollar.apex.entity.custom.ObsidianGolemEntity;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.SpawnLocationTypes;
import net.minecraft.entity.SpawnRestriction;
import net.minecraft.world.Heightmap;

public class ModEntityGeneration {
    public static void addSpawns() {
        //Regular mob weights (Skeleton, Spider, etc.) are 100 but don't have heightmap restrictions.
        BiomeModifications.addSpawn(BiomeSelectors.foundInOverworld(), SpawnGroup.MONSTER,
                ModEntities.OBSIDIAN_GOLEM, 150, 1, 1);
        SpawnRestriction.register(ModEntities.OBSIDIAN_GOLEM, SpawnLocationTypes.ON_GROUND,
                Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, ObsidianGolemEntity::checkObsidianGolemSpawnRules);

        BiomeModifications.addSpawn(BiomeSelectors.foundInOverworld(), SpawnGroup.MONSTER,
                ModEntities.MYSTERIOUS_SPECTER, 150, 1, 1);
        SpawnRestriction.register(ModEntities.MYSTERIOUS_SPECTER, SpawnLocationTypes.ON_GROUND,
                Heightmap.Type.WORLD_SURFACE, MysteriousSpecterEntity::checkMysteriousSpecterSpawnRules);
    }
}
