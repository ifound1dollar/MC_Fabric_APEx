package net.dollar.apex.block;

import net.dollar.apex.ModMain;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DropExperienceBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;

public class ModBlocks {
    public static final Block RUBY_BLOCK = registerBlock("ruby_block",
            new Block(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.FIRE)
                    .instrument(NoteBlockInstrument.BIT)
                    .requiresCorrectToolForDrops()
                    .strength(5.0f, 6.0f)
                    .sound(SoundType.METAL)
                    .setId(generateBlockKey("ruby_block"))));
    public static final Block SAPPHIRE_BLOCK = registerBlock("sapphire_block",
            new Block(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.LAPIS)
                    .instrument(NoteBlockInstrument.BIT)
                    .requiresCorrectToolForDrops()
                    .strength(5.0f, 6.0f)
                    .sound(SoundType.METAL)
                    .setId(generateBlockKey("sapphire_block"))));
    public static final Block DECORATIVE_AMETHYST_BLOCK = registerBlock("decorative_amethyst_block",
            new Block(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.COLOR_PURPLE)
                    .instrument(NoteBlockInstrument.BIT)
                    .requiresCorrectToolForDrops()
                    .strength(5.0f, 6.0f)
                    .sound(SoundType.METAL)
                    .setId(generateBlockKey("decorative_amethyst_block"))));


    public static final Block TIN_BLOCK = registerBlock("tin_block",
            new Block(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.COLOR_LIGHT_GRAY)
                    .instrument(NoteBlockInstrument.IRON_XYLOPHONE)
                    .requiresCorrectToolForDrops()
                    .strength(5.0f, 6.0f)
                    .sound(SoundType.METAL)
                    .setId(generateBlockKey("tin_block"))));
    public static final Block RAW_TIN_BLOCK = registerBlock("raw_tin_block",
            new Block(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.RAW_IRON)
                    .instrument(NoteBlockInstrument.BASEDRUM)
                    .requiresCorrectToolForDrops()
                    .strength(5.0f, 6.0f)
                    .sound(SoundType.METAL)
                    .setId(generateBlockKey("raw_tin_block"))));
    public static final Block TIN_ORE = registerBlock("tin_ore",
            new DropExperienceBlock(ConstantInt.of(0), BlockBehaviour.Properties.of()
                    .mapColor(MapColor.STONE)
                    .instrument(NoteBlockInstrument.BASEDRUM)
                    .requiresCorrectToolForDrops()
                    .strength(3.0f, 3.0f)
                    .sound(SoundType.STONE)
                    .setId(generateBlockKey("tin_ore"))));
    public static final Block DEEPSLATE_TIN_ORE = registerBlock("deepslate_tin_ore",
            new DropExperienceBlock(ConstantInt.of(0), BlockBehaviour.Properties.of()
                    .mapColor(MapColor.DEEPSLATE)
                    .instrument(NoteBlockInstrument.BASEDRUM)
                    .requiresCorrectToolForDrops()
                    .strength(4.5f, 3.0f)
                    .sound(SoundType.DEEPSLATE)
                    .setId(generateBlockKey("deepslate_tin_ore"))));


    public static final Block BRONZE_BLOCK = registerBlock("bronze_block",
            new Block(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.TERRACOTTA_ORANGE)
                    .instrument(NoteBlockInstrument.IRON_XYLOPHONE)
                    .requiresCorrectToolForDrops()
                    .strength(5.0f, 6.0f)
                    .sound(SoundType.METAL)
                    .setId(generateBlockKey("bronze_block"))));
    public static final Block STEEL_BLOCK = registerBlock("steel_block",
            new Block(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.STONE)
                    .instrument(NoteBlockInstrument.IRON_XYLOPHONE)
                    .requiresCorrectToolForDrops()
                    .strength(5.0f, 6.0f)
                    .sound(SoundType.METAL)
                    .setId(generateBlockKey("steel_block"))));
    public static final Block COBALT_BLOCK = registerBlock("cobalt_block",
            new Block(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.LAPIS)
                    .instrument(NoteBlockInstrument.IRON_XYLOPHONE)
                    .requiresCorrectToolForDrops()
                    .strength(5.0f, 6.0f)
                    .sound(SoundType.METAL)
                    .setId(generateBlockKey("cobalt_block"))));


    public static final Block TUNGSTEN_BLOCK = registerBlock("tungsten_block",
            new Block(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.DEEPSLATE)
                    .instrument(NoteBlockInstrument.IRON_XYLOPHONE)
                    .requiresCorrectToolForDrops()
                    .strength(5.0f, 6.0f)
                    .setId(generateBlockKey("tungsten_block"))));
    public static final Block RAW_TUNGSTEN_BLOCK = registerBlock("raw_tungsten_block",
            new Block(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.DEEPSLATE)
                    .instrument(NoteBlockInstrument.BASEDRUM)
                    .requiresCorrectToolForDrops()
                    .strength(5.0f, 6.0f)
                    .setId(generateBlockKey("raw_tungsten_block"))));
    public static final Block TUNGSTEN_ORE = registerBlock("tungsten_ore",
            new DropExperienceBlock(ConstantInt.of(0), BlockBehaviour.Properties.of()
                    .mapColor(MapColor.STONE)
                    .instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops()
                    .requiresCorrectToolForDrops()
                    .strength(3.0f, 3.0f)
                    .sound(SoundType.STONE)
                    .setId(generateBlockKey("tungsten_ore"))));
    public static final Block DEEPSLATE_TUNGSTEN_ORE = registerBlock("deepslate_tungsten_ore",
            new DropExperienceBlock(ConstantInt.of(0), BlockBehaviour.Properties.of()
                    .mapColor(MapColor.DEEPSLATE)
                    .instrument(NoteBlockInstrument.BASEDRUM)
                    .requiresCorrectToolForDrops()
                    .strength(4.5f, 3.0f)
                    .sound(SoundType.DEEPSLATE)
                    .setId(generateBlockKey("deepslate_tungsten_ore"))));


    public static final Block RUBY_ORE = registerBlock("ruby_ore",
            new DropExperienceBlock(UniformInt.of(4, 8), BlockBehaviour.Properties.of()
                    .mapColor(MapColor.STONE)
                    .instrument(NoteBlockInstrument.BASEDRUM)
                    .requiresCorrectToolForDrops()
                    .strength(3.0f, 3.0f)
                    .sound(SoundType.STONE)
                    .setId(generateBlockKey("ruby_ore"))));  //Diamond is 3, 7
    public static final Block DEEPSLATE_RUBY_ORE = registerBlock("deepslate_ruby_ore",
            new DropExperienceBlock(UniformInt.of(4, 8), BlockBehaviour.Properties.of()
                    .mapColor(MapColor.DEEPSLATE)
                    .instrument(NoteBlockInstrument.BASEDRUM)
                    .requiresCorrectToolForDrops()
                    .strength(4.5f, 3.0f)
                    .sound(SoundType.DEEPSLATE)
                    .setId(generateBlockKey("deepslate_ruby_ore"))));  //Diamond is 3, 7
    public static final Block SAPPHIRE_ORE = registerBlock("sapphire_ore",
            new DropExperienceBlock(UniformInt.of(4, 8), BlockBehaviour.Properties.of()
                    .mapColor(MapColor.STONE)
                    .instrument(NoteBlockInstrument.BASEDRUM)
                    .requiresCorrectToolForDrops()
                    .strength(3.0f, 3.0f)
                    .sound(SoundType.STONE)
                    .setId(generateBlockKey("sapphire_ore"))));  //Diamond is 3, 7
    public static final Block DEEPSLATE_SAPPHIRE_ORE = registerBlock("deepslate_sapphire_ore",
            new DropExperienceBlock(UniformInt.of(4, 8), BlockBehaviour.Properties.of()
                    .mapColor(MapColor.DEEPSLATE)
                    .instrument(NoteBlockInstrument.BASEDRUM)
                    .requiresCorrectToolForDrops()
                    .strength(4.5f, 3.0f)
                    .sound(SoundType.DEEPSLATE)
                    .setId(generateBlockKey("deepslate_sapphire_ore"))));  //Diamond is 3, 7
    public static final Block COBALT_ORE = registerBlock("cobalt_ore",
            new DropExperienceBlock(UniformInt.of(3, 6), BlockBehaviour.Properties.of()
                    .mapColor(MapColor.STONE)
                    .instrument(NoteBlockInstrument.BASEDRUM)
                    .requiresCorrectToolForDrops()
                    .strength(3.0f, 3.0f)
                    .sound(SoundType.STONE)
                    .setId(generateBlockKey("cobalt_ore"))));  //Diamond is 3, 7
    public static final Block DEEPSLATE_COBALT_ORE = registerBlock("deepslate_cobalt_ore",
            new DropExperienceBlock(UniformInt.of(3, 6), BlockBehaviour.Properties.of()
                    .mapColor(MapColor.DEEPSLATE)
                    .instrument(NoteBlockInstrument.BASEDRUM)
                    .requiresCorrectToolForDrops()
                    .strength(4.5f, 3.0f)
                    .sound(SoundType.DEEPSLATE)
                    .setId(generateBlockKey("deepslate_cobalt_ore"))));  //Diamond is 3, 7
    public static final Block PHOSPHATE_ORE = registerBlock("phosphate_ore",
            new DropExperienceBlock(UniformInt.of(1, 3), BlockBehaviour.Properties.of()
                    .mapColor(MapColor.STONE)
                    .instrument(NoteBlockInstrument.BASEDRUM)
                    .requiresCorrectToolForDrops()
                    .strength(3.0f, 3.0f)
                    .sound(SoundType.STONE)
                    .setId(generateBlockKey("phosphate_ore"))));  //Coal is 0, 2
    public static final Block DEEPSLATE_PHOSPHATE_ORE = registerBlock("deepslate_phosphate_ore",
            new DropExperienceBlock(UniformInt.of(1, 3), BlockBehaviour.Properties.of()
                    .mapColor(MapColor.DEEPSLATE)
                    .instrument(NoteBlockInstrument.BASEDRUM)
                    .requiresCorrectToolForDrops()
                    .strength(4.5f, 3.0f)
                    .sound(SoundType.DEEPSLATE)
                    .setId(generateBlockKey("deepslate_phosphate_ore"))));  //Coal is 0, 2




    private static ResourceKey<Item> generateItemKey(String name) {
        return ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(ModMain.MOD_ID, name));
    }

    private static ResourceKey<Block> generateBlockKey(String name) {
        return ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(ModMain.MOD_ID, name));
    }

    /**
     * Registers new Block and calls helper method to generate corresponding Item.
     * @param name Name of new Block
     * @param block Actual Block
     * @return Registered Block object
     */
    private static Block registerBlock(String name, Block block) {
        registerBlockItem(name, block);
        return Registry.register(BuiltInRegistries.BLOCK, Identifier.fromNamespaceAndPath(ModMain.MOD_ID, name), block);
    }

    /**
     * Registers a new Item corresponding to a newly registered Block.
     * @param name Name of new Block
     * @param block Actual Block (previously generated)
     * @return Registered Item from Block
     */
    private static Item registerBlockItem(String name, Block block) {
        return Registry.register(BuiltInRegistries.ITEM, Identifier.fromNamespaceAndPath(ModMain.MOD_ID, name),
                new BlockItem(block, new Item.Properties().setId(generateItemKey(name))));
    }

    /**
     * Handles registering all new ModBlocks.
     */
    public static void registerModBlocks() {
        ModMain.LOGGER.info("Registering ModBlocks for " + ModMain.MOD_ID);
    }
}
