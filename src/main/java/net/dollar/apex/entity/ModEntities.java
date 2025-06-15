package net.dollar.apex.entity;

import net.dollar.apex.ModMain;
import net.dollar.apex.entity.custom.MysteriousSpecterEntity;
import net.dollar.apex.entity.custom.ObsidianGolemEntity;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

/**
 * Class responsible for defining and registering mob entities for this mod.
 */
public class ModEntities {
    public static final EntityType<ObsidianGolemEntity> OBSIDIAN_GOLEM = Registry.register(Registries.ENTITY_TYPE,
            new Identifier(ModMain.MOD_ID, "obsidian_golem"),
            FabricEntityTypeBuilder.create(SpawnGroup.MONSTER, ObsidianGolemEntity::new)
                    .dimensions(EntityDimensions.fixed(1.4f, 2.7f)).build());

    public static final EntityType<MysteriousSpecterEntity> MYSTERIOUS_SPECTER = Registry.register(Registries.ENTITY_TYPE,
            new Identifier(ModMain.MOD_ID, "mysterious_specter"),
            FabricEntityTypeBuilder.create(SpawnGroup.MONSTER, MysteriousSpecterEntity::new)
                    .dimensions(EntityDimensions.fixed(0.6f, 1.8f)).build());



    public static void register() {
        ModMain.LOGGER.info("Registering Entities for " + ModMain.MOD_ID);
    }
}
