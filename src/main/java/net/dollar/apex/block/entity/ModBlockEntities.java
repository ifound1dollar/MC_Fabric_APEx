package net.dollar.apex.block.entity;

import net.dollar.apex.ModMain;

public class ModBlockEntities {
    //THIS IS ONLY HERE FOR REFERENCE TO MAKE BLOCK ENTITIES LATER
//    public static final BlockEntityType<ModSpectralLanternBlockEntity> SPECTRAL_LANTERN_BLOCK_ENTITY =
//            Registry.register(Registries.BLOCK_ENTITY_TYPE,
//                    new Identifier(ModMain.MOD_ID, "spectral_lantern_block_entity"),
//                    FabricBlockEntityTypeBuilder.create(ModSpectralLanternBlockEntity::new, ModBlocks.SPECTRAL_LANTERN).build());





    /**
     * Handles registering all mod tile entities.
     */
    public static void registerModTileEntities() {
        ModMain.LOGGER.info("Registering Mod TileEntities for " + ModMain.MOD_ID);
    }
}
