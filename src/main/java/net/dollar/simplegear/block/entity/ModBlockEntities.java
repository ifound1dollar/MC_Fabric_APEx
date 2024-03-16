package net.dollar.simplegear.block.entity;

import net.dollar.simplegear.ModMain;
import net.dollar.simplegear.block.ModBlocks;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModBlockEntities {
    public static final BlockEntityType<ModSpectralLanternBlockEntity> SPECTRAL_LANTERN_BLOCK_ENTITY =
            Registry.register(Registries.BLOCK_ENTITY_TYPE,
                    new Identifier(ModMain.MOD_ID, "spectral_lantern_block_entity"),
                    FabricBlockEntityTypeBuilder.create(ModSpectralLanternBlockEntity::new, ModBlocks.SPECTRAL_LANTERN).build());





    /**
     * Handles registering all mod tile entities.
     */
    public static void registerModTileEntities() {
        ModMain.LOGGER.info("Registering Mod TileEntities for " + ModMain.MOD_ID);
    }
}
