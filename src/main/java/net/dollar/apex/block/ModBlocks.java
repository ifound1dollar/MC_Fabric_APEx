package net.dollar.apex.block;

import net.dollar.apex.ModMain;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.ExperienceDroppingBlock;
import net.minecraft.block.MapColor;
import net.minecraft.block.enums.NoteBlockInstrument;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.intprovider.ConstantIntProvider;
import net.minecraft.util.math.intprovider.UniformIntProvider;

public class ModBlocks {
    public static final Block RUBY_BLOCK = registerBlock("ruby_block",
            new Block(AbstractBlock.Settings.create().mapColor(MapColor.BRIGHT_RED)
                    .instrument(NoteBlockInstrument.BIT).requiresTool()
                    .strength(5.0f, 6.0f).sounds(BlockSoundGroup.METAL)));
    public static final Block SAPPHIRE_BLOCK = registerBlock("sapphire_block",
            new Block(AbstractBlock.Settings.create().mapColor(MapColor.LAPIS_BLUE)
                    .instrument(NoteBlockInstrument.BIT).requiresTool()
                    .strength(5.0f, 6.0f).sounds(BlockSoundGroup.METAL)));
    public static final Block DECORATIVE_AMETHYST_BLOCK = registerBlock("decorative_amethyst_block",
            new Block(AbstractBlock.Settings.create().mapColor(MapColor.PURPLE)
                    .instrument(NoteBlockInstrument.BIT).requiresTool()
                    .strength(5.0f, 6.0f).sounds(BlockSoundGroup.METAL)));


    public static final Block TIN_BLOCK = registerBlock("tin_block",
            new Block(AbstractBlock.Settings.create().mapColor(MapColor.LIGHT_GRAY)
                    .instrument(NoteBlockInstrument.IRON_XYLOPHONE).requiresTool().strength(5.0f, 6.0f)));
    public static final Block RAW_TIN_BLOCK = registerBlock("raw_tin_block",
            new Block(AbstractBlock.Settings.create().mapColor(MapColor.RAW_IRON_PINK)
                    .instrument(NoteBlockInstrument.BASEDRUM).requiresTool().strength(5.0f, 6.0f)));
    public static final Block TIN_ORE = registerBlock("tin_ore",
            new ExperienceDroppingBlock(ConstantIntProvider.create(0), AbstractBlock.Settings.create()
                    .mapColor(MapColor.STONE_GRAY).instrument(NoteBlockInstrument.BASEDRUM).requiresTool()
                    .strength(3.0f, 3.0f)));
    public static final Block DEEPSLATE_TIN_ORE = registerBlock("deepslate_tin_ore",
            new ExperienceDroppingBlock(ConstantIntProvider.create(0), AbstractBlock.Settings.create()
                    .mapColor(MapColor.DEEPSLATE_GRAY).instrument(NoteBlockInstrument.BASEDRUM)
                    .strength(4.5f, 3.0f).sounds(BlockSoundGroup.DEEPSLATE)));


    public static final Block BRONZE_BLOCK = registerBlock("bronze_block",
            new Block(AbstractBlock.Settings.create().mapColor(MapColor.TERRACOTTA_ORANGE)
                    .instrument(NoteBlockInstrument.IRON_XYLOPHONE).requiresTool().strength(5.0f, 6.0f)));
    public static final Block STEEL_BLOCK = registerBlock("steel_block",
            new Block(AbstractBlock.Settings.create().mapColor(MapColor.STONE_GRAY)
                    .instrument(NoteBlockInstrument.IRON_XYLOPHONE).requiresTool().strength(5.0f, 6.0f)));
    public static final Block COBALT_BLOCK = registerBlock("cobalt_block",
            new Block(AbstractBlock.Settings.create().mapColor(MapColor.LAPIS_BLUE)
                    .instrument(NoteBlockInstrument.IRON_XYLOPHONE).requiresTool().strength(5.0f, 6.0f)));


    public static final Block TUNGSTEN_BLOCK = registerBlock("tungsten_block",
            new Block(AbstractBlock.Settings.create().mapColor(MapColor.DEEPSLATE_GRAY)
                    .instrument(NoteBlockInstrument.IRON_XYLOPHONE).requiresTool().strength(5.0f, 6.0f)));
    public static final Block RAW_TUNGSTEN_BLOCK = registerBlock("raw_tungsten_block",
            new Block(AbstractBlock.Settings.create().mapColor(MapColor.DEEPSLATE_GRAY)
                    .instrument(NoteBlockInstrument.BASEDRUM).requiresTool().strength(5.0f, 6.0f)));
    public static final Block TUNGSTEN_ORE = registerBlock("tungsten_ore",
            new ExperienceDroppingBlock(ConstantIntProvider.create(0), AbstractBlock.Settings.create()
                    .mapColor(MapColor.STONE_GRAY).instrument(NoteBlockInstrument.BASEDRUM).requiresTool()
                    .strength(3.0f, 3.0f)));
    public static final Block DEEPSLATE_TUNGSTEN_ORE = registerBlock("deepslate_tungsten_ore",
            new ExperienceDroppingBlock(ConstantIntProvider.create(0), AbstractBlock.Settings.create()
                    .mapColor(MapColor.DEEPSLATE_GRAY).instrument(NoteBlockInstrument.BASEDRUM)
                    .strength(4.5f, 3.0f).sounds(BlockSoundGroup.DEEPSLATE)));


    public static final Block RUBY_ORE = registerBlock("ruby_ore",
            new ExperienceDroppingBlock(UniformIntProvider.create(4, 8), AbstractBlock.Settings.create()
                    .mapColor(MapColor.STONE_GRAY).instrument(NoteBlockInstrument.BASEDRUM).requiresTool()
                    .strength(3.0f, 3.0f)));  //Diamond is 3, 7
    public static final Block DEEPSLATE_RUBY_ORE = registerBlock("deepslate_ruby_ore",
            new ExperienceDroppingBlock(UniformIntProvider.create(4, 8), AbstractBlock.Settings.create()
                    .mapColor(MapColor.DEEPSLATE_GRAY).instrument(NoteBlockInstrument.BASEDRUM)
                    .strength(4.5f, 3.0f).sounds(BlockSoundGroup.DEEPSLATE)));  //Diamond is 3, 7
    public static final Block SAPPHIRE_ORE = registerBlock("sapphire_ore",
            new ExperienceDroppingBlock(UniformIntProvider.create(4, 8), AbstractBlock.Settings.create()
                    .mapColor(MapColor.STONE_GRAY).instrument(NoteBlockInstrument.BASEDRUM).requiresTool()
                    .strength(3.0f, 3.0f)));  //Diamond is 3, 7
    public static final Block DEEPSLATE_SAPPHIRE_ORE = registerBlock("deepslate_sapphire_ore",
            new ExperienceDroppingBlock(UniformIntProvider.create(4, 8), AbstractBlock.Settings.create()
                    .mapColor(MapColor.DEEPSLATE_GRAY).instrument(NoteBlockInstrument.BASEDRUM)
                    .strength(4.5f, 3.0f).sounds(BlockSoundGroup.DEEPSLATE)));  //Diamond is 3, 7
    public static final Block COBALT_ORE = registerBlock("cobalt_ore",
            new ExperienceDroppingBlock(UniformIntProvider.create(3, 6), AbstractBlock.Settings.create()
                    .mapColor(MapColor.STONE_GRAY).instrument(NoteBlockInstrument.BASEDRUM).requiresTool()
                    .strength(3.0f, 3.0f)));  //Diamond is 3, 7
    public static final Block DEEPSLATE_COBALT_ORE = registerBlock("deepslate_cobalt_ore",
            new ExperienceDroppingBlock(UniformIntProvider.create(3, 6), AbstractBlock.Settings.create()
                    .mapColor(MapColor.DEEPSLATE_GRAY).instrument(NoteBlockInstrument.BASEDRUM)
                    .strength(4.5f, 3.0f).sounds(BlockSoundGroup.DEEPSLATE)));  //Diamond is 3, 7
    public static final Block PHOSPHATE_ORE = registerBlock("phosphate_ore",
            new ExperienceDroppingBlock(UniformIntProvider.create(1, 3), AbstractBlock.Settings.create()
                    .mapColor(MapColor.STONE_GRAY).instrument(NoteBlockInstrument.BASEDRUM).requiresTool()
                    .strength(3.0f, 3.0f)));  //Coal is 0, 2
    public static final Block DEEPSLATE_PHOSPHATE_ORE = registerBlock("deepslate_phosphate_ore",
            new ExperienceDroppingBlock(UniformIntProvider.create(1, 3), AbstractBlock.Settings.create()
                    .mapColor(MapColor.DEEPSLATE_GRAY).instrument(NoteBlockInstrument.BASEDRUM)
                    .strength(4.5f, 3.0f).sounds(BlockSoundGroup.DEEPSLATE)));  //Coal is 0, 2



    /**
     * Registers new Block and calls helper method to generate corresponding Item.
     * @param name Name of new Block
     * @param block Actual Block
     * @return Registered Block object
     */
    private static Block registerBlock(String name, Block block) {
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK, Identifier.of(ModMain.MOD_ID, name), block);
    }

    /**
     * Registers a new Item corresponding to a newly registered Block.
     * @param name Name of new Block
     * @param block Actual Block (previously generated)
     * @return Registered Item from Block
     */
    private static Item registerBlockItem(String name, Block block) {
        return Registry.register(Registries.ITEM, Identifier.of(ModMain.MOD_ID, name),
                new BlockItem(block, new Item.Settings()));
    }

    /**
     * Handles registering all new ModBlocks.
     */
    public static void registerModBlocks() {
        ModMain.LOGGER.info("Registering ModBlocks for " + ModMain.MOD_ID);
    }
}
