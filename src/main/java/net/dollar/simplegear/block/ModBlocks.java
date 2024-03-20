package net.dollar.simplegear.block;

import net.dollar.simplegear.ModMain;
import net.dollar.simplegear.block.custom.ModSpectralLanternBlock;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.ExperienceDroppingBlock;
import net.minecraft.block.MapColor;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.intprovider.UniformIntProvider;

public class ModBlocks {
    public static final Block SPECTRAL_LANTERN = registerBlock("spectral_lantern",
            new ModSpectralLanternBlock(FabricBlockSettings.copyOf(Blocks.LANTERN).nonOpaque()));


    public static final Block RUBY_BLOCK = registerBlock("ruby_block",
            new Block(FabricBlockSettings.copyOf(Blocks.EMERALD_BLOCK)
                    .strength(6.0f).requiresTool().mapColor(MapColor.BRIGHT_RED)));
    public static final Block SAPPHIRE_BLOCK = registerBlock("sapphire_block",
            new Block(FabricBlockSettings.copyOf(Blocks.EMERALD_BLOCK)
                    .strength(6.0f).requiresTool().mapColor(MapColor.LAPIS_BLUE)));
    public static final Block DECORATIVE_AMETHYST_BLOCK = registerBlock("decorative_amethyst_block",
            new Block(FabricBlockSettings.copyOf(Blocks.EMERALD_BLOCK)
                    .strength(6.0f).requiresTool().mapColor(MapColor.PURPLE)));


    public static final Block TIN_BLOCK = registerBlock("tin_block",
            new Block(FabricBlockSettings.copyOf(Blocks.IRON_BLOCK)
                    .strength(6.0f).requiresTool().mapColor(MapColor.LIGHT_GRAY)));
    public static final Block RAW_TIN_BLOCK = registerBlock("raw_tin_block",
            new Block(FabricBlockSettings.copyOf(Blocks.RAW_IRON_BLOCK)
                    .strength(5.0f).requiresTool().mapColor(MapColor.LIGHT_GRAY)));
    public static final Block TIN_ORE = registerBlock("tin_ore",
            new Block(FabricBlockSettings.copyOf(Blocks.IRON_ORE)
                    .strength(3.0f).requiresTool().mapColor(MapColor.STONE_GRAY)));
    public static final Block DEEPSLATE_TIN_ORE = registerBlock("deepslate_tin_ore",
            new Block(FabricBlockSettings.copyOf(Blocks.DEEPSLATE_IRON_ORE)
                    .strength(4.5f).requiresTool().mapColor(MapColor.DEEPSLATE_GRAY)));


    public static final Block BRONZE_BLOCK = registerBlock("bronze_block",
            new Block(FabricBlockSettings.copyOf(Blocks.IRON_BLOCK)
                    .strength(6.0f).requiresTool().mapColor(MapColor.TERRACOTTA_ORANGE)));
    public static final Block STEEL_BLOCK = registerBlock("steel_block",
            new Block(FabricBlockSettings.copyOf(Blocks.IRON_BLOCK)
                    .strength(6.0f).requiresTool().mapColor(MapColor.STONE_GRAY)));


    public static final Block TUNGSTEN_BLOCK = registerBlock("tungsten_block",
            new Block(FabricBlockSettings.copyOf(Blocks.IRON_BLOCK)
                    .strength(6.0f).requiresTool().mapColor(MapColor.DEEPSLATE_GRAY)));
    public static final Block RAW_TUNGSTEN_BLOCK = registerBlock("raw_tungsten_block",
            new Block(FabricBlockSettings.copyOf(Blocks.RAW_IRON_BLOCK)
                    .strength(5.0f).requiresTool().mapColor(MapColor.DEEPSLATE_GRAY)));
    public static final Block TUNGSTEN_ORE = registerBlock("tungsten_ore",
            new Block(FabricBlockSettings.copyOf(Blocks.IRON_ORE)
                    .strength(3.0f).requiresTool().mapColor(MapColor.STONE_GRAY)));
    public static final Block DEEPSLATE_TUNGSTEN_ORE = registerBlock("deepslate_tungsten_ore",
            new Block(FabricBlockSettings.copyOf(Blocks.DEEPSLATE_IRON_ORE)
                    .strength(4.5f).requiresTool().mapColor(MapColor.DEEPSLATE_GRAY)));


    public static final Block RUBY_ORE = registerBlock("ruby_ore",
            new ExperienceDroppingBlock(FabricBlockSettings.copyOf(Blocks.EMERALD_ORE)
                    .strength(3.0f).requiresTool().mapColor(MapColor.STONE_GRAY),
                    UniformIntProvider.create(3, 7)));
    public static final Block DEEPSLATE_RUBY_ORE = registerBlock("deepslate_ruby_ore",
            new ExperienceDroppingBlock(FabricBlockSettings.copyOf(Blocks.DEEPSLATE_EMERALD_ORE)
                    .strength(4.5f).requiresTool().mapColor(MapColor.DEEPSLATE_GRAY),
                    UniformIntProvider.create(3, 7)));
    public static final Block SAPPHIRE_ORE = registerBlock("sapphire_ore",
            new ExperienceDroppingBlock(FabricBlockSettings.copyOf(Blocks.EMERALD_ORE)
                    .strength(3.0f).requiresTool().mapColor(MapColor.STONE_GRAY),
                    UniformIntProvider.create(3, 7)));
    public static final Block DEEPSLATE_SAPPHIRE_ORE = registerBlock("deepslate_sapphire_ore",
            new ExperienceDroppingBlock(FabricBlockSettings.copyOf(Blocks.DEEPSLATE_EMERALD_ORE)
                    .strength(4.5f).requiresTool().mapColor(MapColor.DEEPSLATE_GRAY),
                    UniformIntProvider.create(3, 7)));
    public static final Block COBALT_ORE = registerBlock("cobalt_ore",
            new ExperienceDroppingBlock(FabricBlockSettings.copyOf(Blocks.IRON_ORE)
                    .strength(3.0f).requiresTool().mapColor(MapColor.STONE_GRAY),
                    UniformIntProvider.create(2, 4)));
    public static final Block DEEPSLATE_COBALT_ORE = registerBlock("deepslate_cobalt_ore",
            new ExperienceDroppingBlock(FabricBlockSettings.copyOf(Blocks.DEEPSLATE_IRON_ORE)
                    .strength(4.5f).requiresTool().mapColor(MapColor.DEEPSLATE_GRAY),
                    UniformIntProvider.create(2, 4)));
    public static final Block PHOSPHOR_ORE = registerBlock("phosphor_ore",
            new ExperienceDroppingBlock(FabricBlockSettings.copyOf(Blocks.IRON_ORE)
                    .strength(3.0f).requiresTool().mapColor(MapColor.STONE_GRAY),
                    UniformIntProvider.create(0, 2)));  //Equivalent to Coal
    public static final Block DEEPSLATE_PHOSPHOR_ORE = registerBlock("deepslate_phosphor_ore",
            new ExperienceDroppingBlock(FabricBlockSettings.copyOf(Blocks.DEEPSLATE_IRON_ORE)
                    .strength(4.5f).requiresTool().mapColor(MapColor.DEEPSLATE_GRAY),
                    UniformIntProvider.create(0, 2)));  //Equivalent to Coal



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
