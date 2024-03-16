package net.dollar.simplegear.block;

import net.dollar.simplegear.ModMain;
import net.dollar.simplegear.block.custom.ModSpectralLanternBlock;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModBlocks {
    public static final Block SPECTRAL_LANTERN = registerBlock("spectral_lantern",
            new ModSpectralLanternBlock(FabricBlockSettings.copyOf(Blocks.LANTERN).nonOpaque()));





    /**
     * Registers new Block and calls helper method to generate corresponding Item.
     * @param name Name of new Block
     * @param block Actual Block
     * @return Registered Block object
     */
    private static Block registerBlock(String name, Block block) {
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK, new Identifier(ModMain.MOD_ID, name), block);
    }

    /**
     * Registers a new Item corresponding to a newly registered Block.
     * @param name Name of new Block
     * @param block Actual Block (previously generated)
     * @return Registered Item from Block
     */
    private static Item registerBlockItem(String name, Block block) {
        return Registry.register(Registries.ITEM, new Identifier(ModMain.MOD_ID, name),
                new BlockItem(block, new FabricItemSettings()));
    }

    /**
     * Handles registering all new ModBlocks.
     */
    public static void registerModBlocks() {
        ModMain.LOGGER.info("Registering ModBlocks for " + ModMain.MOD_ID);
    }
}
