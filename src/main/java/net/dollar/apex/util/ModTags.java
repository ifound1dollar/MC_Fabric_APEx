package net.dollar.apex.util;

import net.dollar.apex.ModMain;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class ModTags {
    public static class Blocks {
        //region apex tags
        public static final TagKey<Block> MOD_PAXEL_MINEABLE = TagKey.create(Registries.BLOCK,
                Identifier.fromNamespaceAndPath(ModMain.MOD_ID, "paxel_mineable"));
        //endregion

        //region common tags
        public static final TagKey<Block> COMMON_ORES = TagKey.create(Registries.BLOCK,
                Identifier.fromNamespaceAndPath("c", "ores"));
        //endregion

        //region minecraft tags
        public static final TagKey<Block> MINECRAFT_MINEABLE_AXE = TagKey.create(Registries.BLOCK,
                Identifier.fromNamespaceAndPath("minecraft", "mineable/axe"));
        public static final TagKey<Block> MINECRAFT_MINEABLE_HOE = TagKey.create(Registries.BLOCK,
                Identifier.fromNamespaceAndPath("minecraft", "mineable/hoe"));
        public static final TagKey<Block> MINECRAFT_MINEABLE_PICKAXE = TagKey.create(Registries.BLOCK,
                Identifier.fromNamespaceAndPath("minecraft", "mineable/pickaxe"));
        public static final TagKey<Block> MINECRAFT_MINEABLE_SHOVEL = TagKey.create(Registries.BLOCK,
                Identifier.fromNamespaceAndPath("minecraft", "mineable/shovel"));
        public static final TagKey<Block> MINECRAFT_BEACON_BASE_BLOCKS = TagKey.create(Registries.BLOCK,
                Identifier.fromNamespaceAndPath("minecraft", "beacon_base_blocks"));
        public static final TagKey<Block> MINECRAFT_NEEDS_DIAMOND_TOOL = TagKey.create(Registries.BLOCK,
                Identifier.fromNamespaceAndPath("minecraft", "needs_diamond_tool"));
        public static final TagKey<Block> MINECRAFT_NEEDS_IRON_TOOL = TagKey.create(Registries.BLOCK,
                Identifier.fromNamespaceAndPath("minecraft", "needs_iron_tool"));
        public static final TagKey<Block> MINECRAFT_NEEDS_STONE_TOOL = TagKey.create(Registries.BLOCK,
                Identifier.fromNamespaceAndPath("minecraft", "needs_stone_tool"));
        //endregion
    }

    public static class Items {
        //region apex tags
        public static final TagKey<Item> MOD_TOOLS_COBALT_STEEL = TagKey.create(Registries.ITEM,
                Identifier.fromNamespaceAndPath(ModMain.MOD_ID, "equipment_cobalt_steel"));
        public static final TagKey<Item> MOD_TOOLS_INFUSED_GEMSTONE = TagKey.create(Registries.ITEM,
                Identifier.fromNamespaceAndPath(ModMain.MOD_ID, "equipment_infused_gemstone"));
        public static final TagKey<Item> MOD_TOOLS_TUNGSTEN_CARBIDE = TagKey.create(Registries.ITEM,
                Identifier.fromNamespaceAndPath(ModMain.MOD_ID, "equipment_tungsten_carbide"));

        public static final TagKey<Item> MOD_REPAIRS_COBALT_STEEL_EQUIPMENT = TagKey.create(Registries.ITEM,
                Identifier.fromNamespaceAndPath(ModMain.MOD_ID, "repairs_cobalt_steel_equipment"));
        public static final TagKey<Item> MOD_REPAIRS_INFUSED_GEMSTONE_EQUIPMENT = TagKey.create(Registries.ITEM,
                Identifier.fromNamespaceAndPath(ModMain.MOD_ID, "repairs_cobalt_steel_equipment"));
        public static final TagKey<Item> MOD_REPAIRS_TUNGSTEN_CARBIDE_EQUIPMENT = TagKey.create(Registries.ITEM,
                Identifier.fromNamespaceAndPath(ModMain.MOD_ID, "repairs_cobalt_steel_equipment"));
        //endregion

        //region common tags
        public static final TagKey<Item> COMMON_AMETHYST = TagKey.create(Registries.ITEM,
                Identifier.fromNamespaceAndPath("c", "amethyst"));
        public static final TagKey<Item> COMMON_BATTLEAXES = TagKey.create(Registries.ITEM,
                Identifier.fromNamespaceAndPath("c", "battleaxes"));
        public static final TagKey<Item> COMMON_BOOTS = TagKey.create(Registries.ITEM,
                Identifier.fromNamespaceAndPath("c", "boots"));
        public static final TagKey<Item> COMMON_BOWS = TagKey.create(Registries.ITEM,
                Identifier.fromNamespaceAndPath("c", "bows"));
        public static final TagKey<Item> COMMON_BRONZE_INGOTS = TagKey.create(Registries.ITEM,
                Identifier.fromNamespaceAndPath("c", "bronze_ingots"));
        public static final TagKey<Item> COMMON_CHESTPLATES = TagKey.create(Registries.ITEM,
                Identifier.fromNamespaceAndPath("c", "chestplates"));
        public static final TagKey<Item> COMMON_CHESTS = TagKey.create(Registries.ITEM,
                Identifier.fromNamespaceAndPath("c", "chests"));
        public static final TagKey<Item> COMMON_COPPER_INGOTS = TagKey.create(Registries.ITEM,
                Identifier.fromNamespaceAndPath("c", "copper_ingots"));
        public static final TagKey<Item> COMMON_CROSSBOWS = TagKey.create(Registries.ITEM,
                Identifier.fromNamespaceAndPath("c", "crossbows"));
        public static final TagKey<Item> COMMON_DEEPSLATES = TagKey.create(Registries.ITEM,
                Identifier.fromNamespaceAndPath("c", "deepslates"));
        public static final TagKey<Item> COMMON_DIAMONDS = TagKey.create(Registries.ITEM,
                Identifier.fromNamespaceAndPath("c", "diamonds"));
        public static final TagKey<Item> COMMON_EMERALDS = TagKey.create(Registries.ITEM,
                Identifier.fromNamespaceAndPath("c", "emeralds"));
        public static final TagKey<Item> COMMON_GEMS = TagKey.create(Registries.ITEM,
                Identifier.fromNamespaceAndPath("c", "gems"));
        public static final TagKey<Item> COMMON_GOLD_INGOTS = TagKey.create(Registries.ITEM,
                Identifier.fromNamespaceAndPath("c", "gold_ingots"));
        public static final TagKey<Item> COMMON_HELMETS = TagKey.create(Registries.ITEM,
                Identifier.fromNamespaceAndPath("c", "helmets"));
        public static final TagKey<Item> COMMON_INGOTS = TagKey.create(Registries.ITEM,
                Identifier.fromNamespaceAndPath("c", "ingots"));
        public static final TagKey<Item> COMMON_IRON_INGOTS = TagKey.create(Registries.ITEM,
                Identifier.fromNamespaceAndPath("c", "iron_ingots"));
        public static final TagKey<Item> COMMON_LEGGINGS = TagKey.create(Registries.ITEM,
                Identifier.fromNamespaceAndPath("c", "leggings"));
        public static final TagKey<Item> COMMON_NETHERITE_INGOTS = TagKey.create(Registries.ITEM,
                Identifier.fromNamespaceAndPath("c", "netherite_ingots"));
        public static final TagKey<Item> COMMON_NETHERRACKS = TagKey.create(Registries.ITEM,
                Identifier.fromNamespaceAndPath("c", "netherracks"));
        public static final TagKey<Item> COMMON_ORES = TagKey.create(Registries.ITEM,
                Identifier.fromNamespaceAndPath("c", "ores"));
        public static final TagKey<Item> COMMON_PAXELS = TagKey.create(Registries.ITEM,
                Identifier.fromNamespaceAndPath("c", "paxels"));
        public static final TagKey<Item> COMMON_RAW_MATERIALS = TagKey.create(Registries.ITEM,
                Identifier.fromNamespaceAndPath("c", "raw_materials"));
        public static final TagKey<Item> COMMON_RUBIES = TagKey.create(Registries.ITEM,
                Identifier.fromNamespaceAndPath("c", "rubies"));
        public static final TagKey<Item> COMMON_SAPPHIRES = TagKey.create(Registries.ITEM,
                Identifier.fromNamespaceAndPath("c", "sapphires"));
        public static final TagKey<Item> COMMON_STEEL_INGOTS = TagKey.create(Registries.ITEM,
                Identifier.fromNamespaceAndPath("c", "steel_ingots"));
        public static final TagKey<Item> COMMON_STONES = TagKey.create(Registries.ITEM,
                Identifier.fromNamespaceAndPath("c", "stones"));
        public static final TagKey<Item> COMMON_TIN_INGOTS = TagKey.create(Registries.ITEM,
                Identifier.fromNamespaceAndPath("c", "tin_ingots"));
        public static final TagKey<Item> COMMON_TUNGSTEN_INGOTS = TagKey.create(Registries.ITEM,
                Identifier.fromNamespaceAndPath("c", "tungsten_ingots"));
        public static final TagKey<Item> COMMON_WOODEN_RODS = TagKey.create(Registries.ITEM,
                Identifier.fromNamespaceAndPath("c", "wooden_rods"));

        //endregion

        //region minecraft tags
        public static final TagKey<Item> MINECRAFT_AXES = TagKey.create(Registries.ITEM,
                Identifier.fromNamespaceAndPath("minecraft", "axes"));
        public static final TagKey<Item> MINECRAFT_BEACON_PAYMENT_ITEMS = TagKey.create(Registries.ITEM,
                Identifier.fromNamespaceAndPath("minecraft", "beacon_payment_items"));
        public static final TagKey<Item> MINECRAFT_HOES = TagKey.create(Registries.ITEM,
                Identifier.fromNamespaceAndPath("minecraft", "hoes"));
        public static final TagKey<Item> MINECRAFT_PICKAXES = TagKey.create(Registries.ITEM,
                Identifier.fromNamespaceAndPath("minecraft", "pickaxes"));
        public static final TagKey<Item> MINECRAFT_PIGLIN_LOVED = TagKey.create(Registries.ITEM,
                Identifier.fromNamespaceAndPath("minecraft", "piglin_loved"));
        public static final TagKey<Item> MINECRAFT_SHOVELS = TagKey.create(Registries.ITEM,
                Identifier.fromNamespaceAndPath("minecraft", "shovels"));
        public static final TagKey<Item> MINECRAFT_SWORDS = TagKey.create(Registries.ITEM,
                Identifier.fromNamespaceAndPath("minecraft", "swords"));
        public static final TagKey<Item> MINECRAFT_TOOLS = TagKey.create(Registries.ITEM,
                Identifier.fromNamespaceAndPath("minecraft", "tools"));
        public static final TagKey<Item> MINECRAFT_TRIMMABLE_ARMOR = TagKey.create(Registries.ITEM,
                Identifier.fromNamespaceAndPath("minecraft", "trimmable_armor"));
        //endregion
    }

    public static class EntityTypes {
        //region minecraft tags
        public static final TagKey<EntityType<?>> MINECRAFT_FALL_DAMAGE_IMMUNE = TagKey.create(Registries.ENTITY_TYPE,
                Identifier.fromNamespaceAndPath("minecraft", "fall_damage_immune"));
        public static final TagKey<EntityType<?>> MINECRAFT_FREEZE_HURTS_EXTRA = TagKey.create(Registries.ENTITY_TYPE,
                Identifier.fromNamespaceAndPath("minecraft", "freeze_hurts_extra_types"));
        //endregion
    }

}
