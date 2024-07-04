package net.dollar.apex.util;

import net.dollar.apex.ModMain;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public class ModTags {
    public static class Blocks {
        //region apex tags
        public static final TagKey<Block> MOD_PAXEL_MINEABLE = TagKey.of(RegistryKeys.BLOCK,
                Identifier.of(ModMain.MOD_ID, "paxel_mineable"));
        //endregion

        //region common tags
        public static final TagKey<Block> COMMON_ORES = TagKey.of(RegistryKeys.BLOCK,
                Identifier.of("c", "ores"));
        //endregion

        //region minecraft tags
        public static final TagKey<Block> MINECRAFT_MINEABLE_AXE = TagKey.of(RegistryKeys.BLOCK,
                Identifier.of("minecraft", "mineable/axe"));
        public static final TagKey<Block> MINECRAFT_MINEABLE_HOE = TagKey.of(RegistryKeys.BLOCK,
                Identifier.of("minecraft", "mineable/hoe"));
        public static final TagKey<Block> MINECRAFT_MINEABLE_PICKAXE = TagKey.of(RegistryKeys.BLOCK,
                Identifier.of("minecraft", "mineable/pickaxe"));
        public static final TagKey<Block> MINECRAFT_MINEABLE_SHOVEL = TagKey.of(RegistryKeys.BLOCK,
                Identifier.of("minecraft", "mineable/shovel"));
        public static final TagKey<Block> MINECRAFT_BEACON_BASE_BLOCKS = TagKey.of(RegistryKeys.BLOCK,
                Identifier.of("minecraft", "beacon_base_blocks"));
        public static final TagKey<Block> MINECRAFT_NEEDS_DIAMOND_TOOL = TagKey.of(RegistryKeys.BLOCK,
                Identifier.of("minecraft", "needs_diamond_tool"));
        public static final TagKey<Block> MINECRAFT_NEEDS_IRON_TOOL = TagKey.of(RegistryKeys.BLOCK,
                Identifier.of("minecraft", "needs_iron_tool"));
        public static final TagKey<Block> MINECRAFT_NEEDS_STONE_TOOL = TagKey.of(RegistryKeys.BLOCK,
                Identifier.of("minecraft", "needs_stone_tool"));
        //endregion
    }

    public static class Items {
        //region apex tags
        public static final TagKey<Item> MOD_TOOLS_COBALT_STEEL = TagKey.of(RegistryKeys.ITEM,
                Identifier.of(ModMain.MOD_ID, "tools_cobalt_steel"));
        public static final TagKey<Item> MOD_TOOLS_INFUSED_GEMSTONE = TagKey.of(RegistryKeys.ITEM,
                Identifier.of(ModMain.MOD_ID, "tools_infused_gemstone"));
        public static final TagKey<Item> MOD_TOOLS_TUNGSTEN_CARBIDE = TagKey.of(RegistryKeys.ITEM,
                Identifier.of(ModMain.MOD_ID, "tools_tungsten_carbide"));
        //endregion

        //region common tags
        public static final TagKey<Item> COMMON_AMETHYST = TagKey.of(RegistryKeys.ITEM,
                Identifier.of("c", "amethyst"));
        public static final TagKey<Item> COMMON_BATTLEAXES = TagKey.of(RegistryKeys.ITEM,
                Identifier.of("c", "battleaxes"));
        public static final TagKey<Item> COMMON_BOOTS = TagKey.of(RegistryKeys.ITEM,
                Identifier.of("c", "boots"));
        public static final TagKey<Item> COMMON_BOWS = TagKey.of(RegistryKeys.ITEM,
                Identifier.of("c", "bows"));
        public static final TagKey<Item> COMMON_BRONZE_INGOTS = TagKey.of(RegistryKeys.ITEM,
                Identifier.of("c", "bronze_ingots"));
        public static final TagKey<Item> COMMON_CHESTPLATES = TagKey.of(RegistryKeys.ITEM,
                Identifier.of("c", "chestplates"));
        public static final TagKey<Item> COMMON_CHESTS = TagKey.of(RegistryKeys.ITEM,
                Identifier.of("c", "chests"));
        public static final TagKey<Item> COMMON_COPPER_INGOTS = TagKey.of(RegistryKeys.ITEM,
                Identifier.of("c", "copper_ingots"));
        public static final TagKey<Item> COMMON_CROSSBOWS = TagKey.of(RegistryKeys.ITEM,
                Identifier.of("c", "crossbows"));
        public static final TagKey<Item> COMMON_DEEPSLATES = TagKey.of(RegistryKeys.ITEM,
                Identifier.of("c", "deepslates"));
        public static final TagKey<Item> COMMON_DIAMONDS = TagKey.of(RegistryKeys.ITEM,
                Identifier.of("c", "diamonds"));
        public static final TagKey<Item> COMMON_EMERALDS = TagKey.of(RegistryKeys.ITEM,
                Identifier.of("c", "emeralds"));
        public static final TagKey<Item> COMMON_GEMS = TagKey.of(RegistryKeys.ITEM,
                Identifier.of("c", "gems"));
        public static final TagKey<Item> COMMON_GOLD_INGOTS = TagKey.of(RegistryKeys.ITEM,
                Identifier.of("c", "gold_ingots"));
        public static final TagKey<Item> COMMON_HELMETS = TagKey.of(RegistryKeys.ITEM,
                Identifier.of("c", "helmets"));
        public static final TagKey<Item> COMMON_INGOTS = TagKey.of(RegistryKeys.ITEM,
                Identifier.of("c", "ingots"));
        public static final TagKey<Item> COMMON_IRON_INGOTS = TagKey.of(RegistryKeys.ITEM,
                Identifier.of("c", "iron_ingots"));
        public static final TagKey<Item> COMMON_LEGGINGS = TagKey.of(RegistryKeys.ITEM,
                Identifier.of("c", "leggings"));
        public static final TagKey<Item> COMMON_NETHERITE_INGOTS = TagKey.of(RegistryKeys.ITEM,
                Identifier.of("c", "netherite_ingots"));
        public static final TagKey<Item> COMMON_NETHERRACKS = TagKey.of(RegistryKeys.ITEM,
                Identifier.of("c", "netherracks"));
        public static final TagKey<Item> COMMON_ORES = TagKey.of(RegistryKeys.ITEM,
                Identifier.of("c", "ores"));
        public static final TagKey<Item> COMMON_PAXELS = TagKey.of(RegistryKeys.ITEM,
                Identifier.of("c", "paxels"));
        public static final TagKey<Item> COMMON_RAW_MATERIALS = TagKey.of(RegistryKeys.ITEM,
                Identifier.of("c", "raw_materials"));
        public static final TagKey<Item> COMMON_RUBIES = TagKey.of(RegistryKeys.ITEM,
                Identifier.of("c", "rubies"));
        public static final TagKey<Item> COMMON_SAPPHIRES = TagKey.of(RegistryKeys.ITEM,
                Identifier.of("c", "sapphires"));
        public static final TagKey<Item> COMMON_STEEL_INGOTS = TagKey.of(RegistryKeys.ITEM,
                Identifier.of("c", "steel_ingots"));
        public static final TagKey<Item> COMMON_STONES = TagKey.of(RegistryKeys.ITEM,
                Identifier.of("c", "stones"));
        public static final TagKey<Item> COMMON_TIN_INGOTS = TagKey.of(RegistryKeys.ITEM,
                Identifier.of("c", "tin_ingots"));
        public static final TagKey<Item> COMMON_TUNGSTEN_INGOTS = TagKey.of(RegistryKeys.ITEM,
                Identifier.of("c", "tungsten_ingots"));
        public static final TagKey<Item> COMMON_WOODEN_RODS = TagKey.of(RegistryKeys.ITEM,
                Identifier.of("c", "wooden_rods"));

        //endregion

        //region minecraft tags
        public static final TagKey<Item> MINECRAFT_AXES = TagKey.of(RegistryKeys.ITEM,
                Identifier.of("minecraft", "axes"));
        public static final TagKey<Item> MINECRAFT_BEACON_PAYMENT_ITEMS = TagKey.of(RegistryKeys.ITEM,
                Identifier.of("minecraft", "beacon_payment_items"));
        public static final TagKey<Item> MINECRAFT_HOES = TagKey.of(RegistryKeys.ITEM,
                Identifier.of("minecraft", "hoes"));
        public static final TagKey<Item> MINECRAFT_PICKAXES = TagKey.of(RegistryKeys.ITEM,
                Identifier.of("minecraft", "pickaxes"));
        public static final TagKey<Item> MINECRAFT_PIGLIN_LOVED = TagKey.of(RegistryKeys.ITEM,
                Identifier.of("minecraft", "piglin_loved"));
        public static final TagKey<Item> MINECRAFT_SHOVELS = TagKey.of(RegistryKeys.ITEM,
                Identifier.of("minecraft", "shovels"));
        public static final TagKey<Item> MINECRAFT_SWORDS = TagKey.of(RegistryKeys.ITEM,
                Identifier.of("minecraft", "swords"));
        public static final TagKey<Item> MINECRAFT_TOOLS = TagKey.of(RegistryKeys.ITEM,
                Identifier.of("minecraft", "tools"));
        public static final TagKey<Item> MINECRAFT_TRIMMABLE_ARMOR = TagKey.of(RegistryKeys.ITEM,
                Identifier.of("minecraft", "trimmable_armor"));
        //endregion
    }

    public static class EntityTypes {
        //region minecraft tags
        public static final TagKey<EntityType<?>> MINECRAFT_FALL_DAMAGE_IMMUNE = TagKey.of(RegistryKeys.ENTITY_TYPE,
                Identifier.of("minecraft", "fall_damage_immune"));
        public static final TagKey<EntityType<?>> MINECRAFT_FREEZE_HURTS_EXTRA = TagKey.of(RegistryKeys.ENTITY_TYPE,
                Identifier.of("minecraft", "freeze_hurts_extra_types"));
        //endregion
    }

}
