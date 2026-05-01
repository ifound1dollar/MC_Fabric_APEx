package net.dollar.apex.entity;

import net.dollar.apex.ModMain;
import net.dollar.apex.entity.custom.MysteriousSpecterEntity;
import net.dollar.apex.entity.custom.ObsidianGolemEntity;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;

/**
 * Class responsible for defining and registering mob entities for this mod.
 */
public class ModEntities {
    private static final ResourceKey<EntityType<?>> obsidianGolemKey = ResourceKey.create(
            Registries.ENTITY_TYPE, Identifier.fromNamespaceAndPath(ModMain.MOD_ID, "obsidian_golem"));
    private static final ResourceKey<EntityType<?>> mysteriousSpecterKey = ResourceKey.create(
            Registries.ENTITY_TYPE, Identifier.fromNamespaceAndPath(ModMain.MOD_ID, "mysterious_specter"));

    public static final EntityType<ObsidianGolemEntity> OBSIDIAN_GOLEM = Registry.register(BuiltInRegistries.ENTITY_TYPE,
            Identifier.fromNamespaceAndPath(ModMain.MOD_ID, "obsidian_golem"),
            EntityType.Builder.of(ObsidianGolemEntity::new, MobCategory.MONSTER)
                    .sized(1.4f, 2.7f)     // Matches Iron Golem
                    .notInPeaceful()
                    .build(obsidianGolemKey));

    public static final EntityType<MysteriousSpecterEntity> MYSTERIOUS_SPECTER = Registry.register(BuiltInRegistries.ENTITY_TYPE,
            Identifier.fromNamespaceAndPath(ModMain.MOD_ID, "mysterious_specter"),
            EntityType.Builder.of(MysteriousSpecterEntity::new, MobCategory.MONSTER)
                    .sized(0.6f, 1.8f)
                    .notInPeaceful()
                    .build(mysteriousSpecterKey));



    public static void register() {
        ModMain.LOGGER.info("Registering Entities for " + ModMain.MOD_ID);
    }
}
