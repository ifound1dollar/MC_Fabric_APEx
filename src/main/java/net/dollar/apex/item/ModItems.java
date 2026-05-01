package net.dollar.apex.item;

import net.dollar.apex.ModMain;
import net.dollar.apex.entity.ModEntities;
import net.dollar.apex.item.custom.ModCustomItem;
import net.dollar.apex.item.custom.equipment.*;
import net.dollar.apex.item.custom.ranged.ModEndgameBowItem;
import net.dollar.apex.item.custom.ranged.ModEndgameCrossbowItem;
import net.dollar.apex.util.*;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.BoneMealItem;
import net.minecraft.world.item.HoeItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ShovelItem;
import net.minecraft.world.item.SpawnEggItem;
import net.minecraft.world.item.ToolMaterial;
import net.minecraft.world.item.equipment.ArmorType;

public class ModItems {
    //region Misc.
    public static final Item OBSIDIAN_GOLEM_SPAWN_EGG = registerItem("obsidian_golem_spawn_egg",
            new SpawnEggItem(new Item.Properties()
                    .spawnEgg(ModEntities.OBSIDIAN_GOLEM)
                    .setId(generateItemKey("obsidian_golem_spawn_egg"))));
    public static final Item MYSTERIOUS_SPECTER_SPAWN_EGG = registerItem("mysterious_specter_spawn_egg",
            new SpawnEggItem(new Item.Properties()
                    .spawnEgg(ModEntities.MYSTERIOUS_SPECTER)
                    .setId(generateItemKey("mysterious_specter_spawn_egg"))));
    public static final Item FERTILIZER = registerItem("fertilizer",
            new BoneMealItem((new Item.Properties().setId(generateItemKey("fertilizer")))));
    //endregion

    //region Trophy items
    //Set maxCount here instead of in the item class.
    public static final Item TROPHY_OBSIDIAN_DUST = registerItem("trophy_obsidian_dust",
            new ModCustomItem(new Item.Properties()
                    .setId(generateItemKey("trophy_obsidian_dust"))
                    .stacksTo(1), "tooltip.trophy_item", false));
    public static final Item TROPHY_OMINOUS_LETTER = registerItem("trophy_ominous_letter",
            new ModCustomItem(new Item.Properties()
                    .setId(generateItemKey("trophy_ominous_letter"))
                    .stacksTo(1), "tooltip.trophy_item", false));

    //endregion

    //region Raw Items, Gems, Compounds
    public static final Item RUBY = registerItem("ruby",
            new Item(new Item.Properties().setId(generateItemKey("ruby"))));
    public static final Item SAPPHIRE = registerItem("sapphire",
            new Item(new Item.Properties().setId(generateItemKey("sapphire"))));
    public static final Item COBALT_SHARD = registerItem("cobalt_shard",
            new Item(new Item.Properties().setId(generateItemKey("cobalt_shard"))));
    public static final Item PHOSPHATE_POWDER = registerItem("phosphate_powder",
            new Item(new Item.Properties().setId(generateItemKey("phosphate_powder"))));
    public static final Item RAW_TIN = registerItem("raw_tin",
            new Item(new Item.Properties().setId(generateItemKey("raw_tin"))));
    public static final Item TIN_INGOT = registerItem("tin_ingot",
            new Item(new Item.Properties().setId(generateItemKey("tin_ingot"))));
    public static final Item TIN_NUGGET = registerItem("tin_nugget",
            new Item(new Item.Properties().setId(generateItemKey("tin_nugget"))));
    public static final Item BRONZE_COMPOUND = registerItem("bronze_compound",
            new Item(new Item.Properties().setId(generateItemKey("bronze_compound"))));
    public static final Item BRONZE_INGOT = registerItem("bronze_ingot",
            new Item(new Item.Properties().setId(generateItemKey("bronze_ingot"))));
    public static final Item BRONZE_NUGGET = registerItem("bronze_nugget",
            new Item(new Item.Properties().setId(generateItemKey("bronze_nugget"))));
    public static final Item STEEL_COMPOUND = registerItem("steel_compound",
            new Item(new Item.Properties().setId(generateItemKey("steel_compound"))));
    public static final Item STEEL_INGOT = registerItem("steel_ingot",
            new Item(new Item.Properties().setId(generateItemKey("steel_ingot"))));
    public static final Item STEEL_NUGGET = registerItem("steel_nugget",
            new Item(new Item.Properties().setId(generateItemKey("steel_nugget"))));
    public static final Item RAW_TUNGSTEN = registerItem("raw_tungsten",
            new Item(new Item.Properties().setId(generateItemKey("raw_tungsten"))));
    public static final Item TUNGSTEN_INGOT = registerItem("tungsten_ingot",
            new Item(new Item.Properties().setId(generateItemKey("tungsten_ingot"))));
    public static final Item TUNGSTEN_NUGGET = registerItem("tungsten_nugget",
            new Item(new Item.Properties().setId(generateItemKey("tungsten_nugget"))));
    //endregion

    //Region End-game upgrade items
    public static final Item MOLTEN_CORE = registerItem("molten_core",
            new ModCustomItem(new Item.Properties()
                    .setId(generateItemKey("molten_core"))
                    .fireResistant(), "tooltip.molten_core", false));
    public static final Item HANDFUL_OF_STARDUST = registerItem("handful_of_stardust",
            new ModCustomItem(new Item.Properties()
                    .setId(generateItemKey("handful_of_stardust"))
                    .fireResistant(), "tooltip.handful_of_stardust", false));
    public static final Item INFUSED_GEMSTONE = registerItem("infused_gemstone",
            new ModCustomItem(new Item.Properties()
                    .setId(generateItemKey("infused_gemstone"))
                    .fireResistant(), "tooltip.infused_gemstone", true));
    public static final Item COBALT_STEEL_INGOT = registerItem("cobalt_steel_ingot",
            new ModCustomItem(new Item.Properties()
                    .setId(generateItemKey("cobalt_steel_ingot"))
                    .fireResistant(), "tooltip.cobalt_steel_ingot", false));
    public static final Item TUNGSTEN_CARBIDE_INGOT = registerItem("tungsten_carbide_ingot",
            new ModCustomItem(new Item.Properties()
                    .setId(generateItemKey("tungsten_carbide_ingot"))
                    .fireResistant(), "tooltip.tungsten_carbide_ingot", false));
    //endregion

    //region Upgrade Templates
    public static final Item BASIC_UPGRADE_TEMPLATE = registerItem("basic_upgrade_template",
            new Item(new Item.Properties()
                    .setId(generateItemKey("basic_upgrade_template"))));
    public static final Item COBALT_UPGRADE_TEMPLATE = registerItem("cobalt_upgrade_smithing_template",
            ModSmithingUpgradeItemHelper.createCobaltUpgradeTemplate());
    public static final Item INFUSION_UPGRADE_TEMPLATE = registerItem("infusion_upgrade_smithing_template",
            ModSmithingUpgradeItemHelper.createInfusionUpgradeTemplate());
    public static final Item CARBIDE_UPGRADE_TEMPLATE = registerItem("carbide_upgrade_smithing_template",
            ModSmithingUpgradeItemHelper.createCarbideUpgradeTemplate());
    //endregion

    //region Bows/Crossbows 
    public static final Item COBALT_STEEL_BOW = registerItem("cobalt_steel_bow",
            new ModEndgameBowItem(ModItemUtils.EndgameTier.COBALT_STEEL, new Item.Properties()
                    .setId(generateItemKey("cobalt_steel_bow"))
                    .durability(1350)                // Default maxDamage is 384
                    .enchantable(18)    // Matches tool tiers exactly
                    .fireResistant()));
    public static final Item COBALT_STEEL_CROSSBOW = registerItem("cobalt_steel_crossbow",
            new ModEndgameCrossbowItem(ModItemUtils.EndgameTier.COBALT_STEEL, new Item.Properties()
                    .setId(generateItemKey("cobalt_steel_crossbow"))
                    .durability(1650)                // Default maxDamage is 465
                    .enchantable(18)
                    .fireResistant()));
    public static final Item INFUSED_GEMSTONE_BOW = registerItem("infused_gemstone_bow",
            new ModEndgameBowItem(ModItemUtils.EndgameTier.INFUSED_GEMSTONE, new Item.Properties()
                    .setId(generateItemKey("infused_gemstone_bow"))
                    .durability(1200)
                    .enchantable(25)    // Matches tool tiers exactly
                    .fireResistant()));
    public static final Item INFUSED_GEMSTONE_CROSSBOW = registerItem("infused_gemstone_crossbow",
            new ModEndgameCrossbowItem(ModItemUtils.EndgameTier.INFUSED_GEMSTONE, new Item.Properties()
                    .setId(generateItemKey("infused_gemstone_crossbow"))
                    .durability(1500)
                    .fireResistant()));
    public static final Item TUNGSTEN_CARBIDE_BOW = registerItem("tungsten_carbide_bow",
            new ModEndgameBowItem(ModItemUtils.EndgameTier.TUNGSTEN_CARBIDE, new Item.Properties()
                    .setId(generateItemKey("tungsten_carbide_bow"))
                    .durability(1500)
                    .enchantable(15)    // Matches tool tiers exactly
                    .fireResistant()));
    public static final Item TUNGSTEN_CARBIDE_CROSSBOW = registerItem("tungsten_carbide_crossbow",
            new ModEndgameCrossbowItem(ModItemUtils.EndgameTier.TUNGSTEN_CARBIDE, new Item.Properties()
                    .setId(generateItemKey("tungsten_carbide_crossbow"))
                    .durability(1800)
                    .enchantable(15)
                    .fireResistant()));
    //endregion

    //region Axes
    public static final Item BRONZE_AXE = registerItem("bronze_axe",
            new AxeItem(ModToolMaterials.BRONZE, 6.0f, -3.1f,
                    new Item.Properties()
                            .setId(generateItemKey("bronze_axe"))));
    public static final Item GILDED_BRONZE_AXE = registerItem("gilded_bronze_axe",
            new AxeItem(ModToolMaterials.GILDED_BRONZE, 6.0f, -2.9f,
                    new Item.Properties()
                            .setId(generateItemKey("gilded_bronze_axe"))));
    public static final Item COBALT_STEEL_AXE = registerItem("cobalt_steel_axe",
            new ModEndgameAxeItem(ModToolMaterials.COBALT_STEEL, 5.0f, -2.7f,
                    ModItemUtils.EndgameTier.COBALT_STEEL, new Item.Properties()
                            .setId(generateItemKey("cobalt_steel_axe"))
                            .fireResistant())); //Very fast, Netherite = 5.0f, -3.0f
    public static final Item INFUSED_GEMSTONE_AXE = registerItem("infused_gemstone_axe",
            new ModEndgameAxeItem(ModToolMaterials.INFUSED_GEMSTONE, 5.0f, -2.9f,
                    ModItemUtils.EndgameTier.INFUSED_GEMSTONE, new Item.Properties()
                            .setId(generateItemKey("infused_gemstone_axe"))
                            .fireResistant())); //Faster, Netherite = 5.0f, -3.0f
    public static final Item TUNGSTEN_CARBIDE_AXE = registerItem("tungsten_carbide_axe",
            new ModEndgameAxeItem(ModToolMaterials.TUNGSTEN_CARBIDE, 6.0f, -3.2f,
                    ModItemUtils.EndgameTier.TUNGSTEN_CARBIDE, new Item.Properties()
                            .setId(generateItemKey("tungsten_carbide_axe"))
                            .fireResistant())); //Slower, Netherite = 5.0f, -3.0f
    //endregion

    //region Battleaxes
    public static final Item DIAMOND_BATTLEAXE = registerItem("diamond_battleaxe",
            new Item(new Item.Properties()
                    .sword(ToolMaterial.DIAMOND, 5.0f, -3.0f)
                    .setId(generateItemKey("diamond_battleaxe"))));
    public static final Item NETHERITE_BATTLEAXE = registerItem("netherite_battleaxe",
            new Item(new Item.Properties()
                    .sword(ToolMaterial.NETHERITE, 5.0f, -3.0f)
                    .setId(generateItemKey("netherite_battleaxe"))
                    .fireResistant()));
    public static final Item COBALT_STEEL_BATTLEAXE = registerItem("cobalt_steel_battleaxe",
            new ModEndgameToolItem(ModItemUtils.EndgameTier.COBALT_STEEL,
                    new Item.Properties()
                            .sword(ModToolMaterials.COBALT_STEEL, 5.0f, -2.7f)
                            .setId(generateItemKey("cobalt_steel_battleaxe"))
                            .fireResistant()));     //Faster, Netherite = 5.0f, -3.0f
    public static final Item INFUSED_GEMSTONE_BATTLEAXE = registerItem("infused_gemstone_battleaxe",
            new ModEndgameToolItem(ModItemUtils.EndgameTier.INFUSED_GEMSTONE,
                    new Item.Properties()
                            .sword(ModToolMaterials.INFUSED_GEMSTONE, 5.0f, -2.9f)
                            .setId(generateItemKey("infused_gemstone_battleaxe"))
                            .fireResistant()));     //Very fast, Netherite = 5.0f, -3.0f
    public static final Item TUNGSTEN_CARBIDE_BATTLEAXE = registerItem("tungsten_carbide_battleaxe",
            new ModEndgameToolItem(ModItemUtils.EndgameTier.TUNGSTEN_CARBIDE,
                    new Item.Properties()
                            .sword(ModToolMaterials.TUNGSTEN_CARBIDE, 6.0f, -3.2f)
                            .setId(generateItemKey("tungsten_carbide_battleaxe"))
                            .fireResistant()));     //Slower, Netherite = 5.0f, -3.0f
    //endregion

    //region Hoes
    public static final Item BRONZE_HOE = registerItem("bronze_hoe",
            new HoeItem(ModToolMaterials.BRONZE, -2.0f, -1.0f,
                    new Item.Properties()
                            .setId(generateItemKey("bronze_hoe"))));
    public static final Item GILDED_BRONZE_HOE = registerItem("gilded_bronze_hoe",
            new HoeItem(ModToolMaterials.GILDED_BRONZE, -2.0f, -0.0f,
                    new Item.Properties()
                            .setId(generateItemKey("gilded_bronze_hoe"))));
    public static final Item COBALT_STEEL_HOE = registerItem("cobalt_steel_hoe",
            new ModEndgameHoeItem(ModToolMaterials.COBALT_STEEL, -2.0f, 0.0f,
                    ModItemUtils.EndgameTier.COBALT_STEEL, new Item.Properties()
                            .setId(generateItemKey("cobalt_steel_hoe"))
                            .fireResistant())); //Very fast, Netherite = -4, 0.0f
    public static final Item INFUSED_GEMSTONE_HOE = registerItem("infused_gemstone_hoe",
            new ModEndgameHoeItem(ModToolMaterials.INFUSED_GEMSTONE, -2.0f, -1.0f,
                    ModItemUtils.EndgameTier.INFUSED_GEMSTONE, new Item.Properties()
                            .setId(generateItemKey("infused_gemstone_hoe"))
                            .fireResistant())); //Faster, Netherite = -4, 0.0f
    public static final Item TUNGSTEN_CARBIDE_HOE = registerItem("tungsten_carbide_hoe",
            new ModEndgameHoeItem(ModToolMaterials.TUNGSTEN_CARBIDE, -3.0f, -2.0f,
                    ModItemUtils.EndgameTier.TUNGSTEN_CARBIDE, new Item.Properties()
                            .setId(generateItemKey("tungsten_carbide_hoe"))
                            .fireResistant())); //Slower, Netherite = -4, 0.0f
    //endregion

    //region Paxels
    public static final Item DIAMOND_PAXEL = registerItem("diamond_paxel",
            new Item(new Item.Properties()
                    .tool(ToolMaterial.DIAMOND, ModTags.Blocks.MOD_PAXEL_MINEABLE,
                            2.0f, -2.9f, 0.0f)
                    .setId(generateItemKey("diamond_paxel"))));
    public static final Item NETHERITE_PAXEL = registerItem("netherite_paxel",
            new Item(new Item.Properties()
                    .tool(ToolMaterial.NETHERITE, ModTags.Blocks.MOD_PAXEL_MINEABLE,
                            2.0f, -2.9f, 0.0f)
                    .setId(generateItemKey("netherite_paxel"))
                    .fireResistant()));     //Rough average between Axe, Pickaxe, and Shovel stats
    public static final Item COBALT_STEEL_PAXEL = registerItem("cobalt_steel_paxel",
            new ModCobaltSteelPaxelOrPickaxeItem(new Item.Properties()
                    .tool(ModToolMaterials.COBALT_STEEL, ModTags.Blocks.MOD_PAXEL_MINEABLE,
                            2.0f, -2.6f, 0.0f)
                    .setId(generateItemKey("cobalt_steel_paxel"))
                    .fireResistant()));     //Very fast, Netherite = 2.0f, -2.9f
    public static final Item INFUSED_GEMSTONE_PAXEL = registerItem("infused_gemstone_paxel",
            new ModEndgameToolItem(ModItemUtils.EndgameTier.INFUSED_GEMSTONE,
                    new Item.Properties()
                            .tool(ModToolMaterials.INFUSED_GEMSTONE, ModTags.Blocks.MOD_PAXEL_MINEABLE,
                                    2.0f, -2.8f, 0.0f)
                            .setId(generateItemKey("infused_gemstone_paxel"))
                            .fireResistant()));     //Faster, Netherite = 2.0f, -2.9f
    public static final Item TUNGSTEN_CARBIDE_PAXEL = registerItem("tungsten_carbide_paxel",
            new ModEndgameToolItem(ModItemUtils.EndgameTier.TUNGSTEN_CARBIDE,
                    new Item.Properties()
                            .tool(ModToolMaterials.TUNGSTEN_CARBIDE, ModTags.Blocks.MOD_PAXEL_MINEABLE,
                                    2.5f, -3.1f, 0.0f)
                            .setId(generateItemKey("tungsten_carbide_paxel"))
                            .fireResistant()));     //Slower, Netherite = 2.0f, -2.9f
    //endregion

    //region Pickaxes
    public static final Item BRONZE_PICKAXE = registerItem("bronze_pickaxe",
            new Item(new Item.Properties()
                    .pickaxe(ModToolMaterials.BRONZE, 1.0f, -2.8f)
                    .setId(generateItemKey("bronze_pickaxe"))));
    public static final Item GILDED_BRONZE_PICKAXE = registerItem("gilded_bronze_pickaxe",
            new Item(new Item.Properties()
                    .pickaxe(ModToolMaterials.GILDED_BRONZE, 1.0f, -2.6f)
                    .setId(generateItemKey("gilded_bronze_pickaxe"))));
    public static final Item COBALT_STEEL_PICKAXE = registerItem("cobalt_steel_pickaxe",
            new ModCobaltSteelPaxelOrPickaxeItem(new Item.Properties()
                    .pickaxe(ModToolMaterials.COBALT_STEEL, 1.0f, -2.5f)
                    .setId(generateItemKey("cobalt_steel_pickaxe"))
                    .fireResistant()));     //Very fast, Netherite = 1, -2.8f
    public static final Item INFUSED_GEMSTONE_PICKAXE = registerItem("infused_gemstone_pickaxe",
            new ModEndgameToolItem(ModItemUtils.EndgameTier.INFUSED_GEMSTONE,
                    new Item.Properties()
                            .pickaxe(ModToolMaterials.INFUSED_GEMSTONE, 1.0f, -2.7f)
                            .setId(generateItemKey("infused_gemstone_pickaxe"))
                            .fireResistant()));     //Faster, Netherite = 1, -2.8f
    public static final Item TUNGSTEN_CARBIDE_PICKAXE = registerItem("tungsten_carbide_pickaxe",
            new ModEndgameToolItem(ModItemUtils.EndgameTier.TUNGSTEN_CARBIDE,
                    new Item.Properties()
                            .pickaxe(ModToolMaterials.TUNGSTEN_CARBIDE, 1.0f, -3.0f)
                            .setId(generateItemKey("tungsten_carbide_pickaxe"))
                            .fireResistant()));     //Slower, Netherite = 1, -2.8f
    //endregion

    //region Shovel
    public static final Item BRONZE_SHOVEL = registerItem("bronze_shovel",
            new ShovelItem(ModToolMaterials.BRONZE, 1.5f, -3.0f,
                    new Item.Properties()
                            .setId(generateItemKey("bronze_shovel"))));
    public static final Item GILDED_BRONZE_SHOVEL = registerItem("gilded_bronze_shovel",
            new ShovelItem(ModToolMaterials.GILDED_BRONZE, 1.5f, -2.8f,
                    new Item.Properties()
                            .setId(generateItemKey("gilded_bronze_shovel"))));
    public static final Item COBALT_STEEL_SHOVEL = registerItem("cobalt_steel_shovel",
            new ModEndgameShovelItem(ModToolMaterials.COBALT_STEEL, 1.5f, -2.7f,
                    ModItemUtils.EndgameTier.COBALT_STEEL, new Item.Properties()
                            .setId(generateItemKey("cobalt_steel_shovel"))
                            .fireResistant())); //Very fast, Netherite = 1.5f, -3.0f
    public static final Item INFUSED_GEMSTONE_SHOVEL = registerItem("infused_gemstone_shovel",
            new ModEndgameShovelItem(ModToolMaterials.INFUSED_GEMSTONE, 2.0f, -2.9f,
                    ModItemUtils.EndgameTier.INFUSED_GEMSTONE, new Item.Properties()
                            .setId(generateItemKey("infused_gemstone_shovel"))
                            .fireResistant())); //Faster, Netherite = 1.5f, -3.0f
    public static final Item TUNGSTEN_CARBIDE_SHOVEL = registerItem("tungsten_carbide_shovel",
            new ModEndgameShovelItem(ModToolMaterials.TUNGSTEN_CARBIDE, 2.0f, -3.2f,
                    ModItemUtils.EndgameTier.TUNGSTEN_CARBIDE, new Item.Properties()
                            .setId(generateItemKey("tungsten_carbide_shovel"))
                            .fireResistant())); //Slower, Netherite = 1.5f, -3.0f
    //endregion

    //region Sword
    public static final Item BRONZE_SWORD = registerItem("bronze_sword",
            new Item(new Item.Properties()
                    .sword(ModToolMaterials.BRONZE, 3.0f, -2.4f)
                    .setId(generateItemKey("bronze_sword"))));
    public static final Item GILDED_BRONZE_SWORD = registerItem("gilded_bronze_sword",
            new Item(new Item.Properties()
                    .sword(ModToolMaterials.GILDED_BRONZE, 3.0f, -2.2f)
                    .setId(generateItemKey("gilded_bronze_sword"))));
    public static final Item COBALT_STEEL_SWORD = registerItem("cobalt_steel_sword",
            new ModEndgameToolItem(ModItemUtils.EndgameTier.COBALT_STEEL,
                    new Item.Properties()
                            .sword(ModToolMaterials.COBALT_STEEL, 3.0f, -2.0f)
                            .setId(generateItemKey("cobalt_steel_sword"))
                            .fireResistant()));     //Very fast, Netherite = 3, -2.4f
    public static final Item INFUSED_GEMSTONE_SWORD = registerItem("infused_gemstone_sword",
            new ModEndgameToolItem(ModItemUtils.EndgameTier.INFUSED_GEMSTONE,
                    new Item.Properties()
                            .sword(ModToolMaterials.INFUSED_GEMSTONE, 3.0f, -2.3f)
                            .setId(generateItemKey("infused_gemstone_sword"))
                            .fireResistant()));     //Faster, Netherite = 3, -2.4f
    public static final Item TUNGSTEN_CARBIDE_SWORD = registerItem("tungsten_carbide_sword",
            new ModEndgameToolItem(ModItemUtils.EndgameTier.TUNGSTEN_CARBIDE,
                    new Item.Properties()
                            .sword(ModToolMaterials.TUNGSTEN_CARBIDE, 3.0f, -2.6f)
                            .setId(generateItemKey("tungsten_carbide_sword"))
                            .fireResistant()));     //Slower, Netherite = 3, -2.4f
    //endregion

    //region Bronze armor
    public static final Item BRONZE_HELMET = registerItem("bronze_helmet",
            new Item(new Item.Properties()
                    .humanoidArmor(ModArmorMaterials.BRONZE, ArmorType.HELMET)
                    .setId(generateItemKey("bronze_helmet"))));
    public static final Item BRONZE_CHESTPLATE = registerItem("bronze_chestplate",
            new Item(new Item.Properties()
                    .humanoidArmor(ModArmorMaterials.BRONZE, ArmorType.CHESTPLATE)
                    .setId(generateItemKey("bronze_chestplate"))));
    public static final Item BRONZE_LEGGINGS = registerItem("bronze_leggings",
            new Item(new Item.Properties()
                    .humanoidArmor(ModArmorMaterials.BRONZE, ArmorType.LEGGINGS)
                    .setId(generateItemKey("bronze_leggings"))));
    public static final Item BRONZE_BOOTS = registerItem("bronze_boots",
            new Item(new Item.Properties()
                    .humanoidArmor(ModArmorMaterials.BRONZE, ArmorType.BOOTS)
                    .setId(generateItemKey("bronze_boots"))));
    //endregion

    //region Gilded Bronze armor
    public static final Item GILDED_BRONZE_HELMET = registerItem("gilded_bronze_helmet",
            new ModCustomItem(new Item.Properties()
                    .humanoidArmor(ModArmorMaterials.GILDED_BRONZE, ArmorType.HELMET)
                    .setId(generateItemKey("gilded_bronze_helmet")),
                    "tooltip.gilded_bronze_armor", false));
    public static final Item GILDED_BRONZE_CHESTPLATE = registerItem("gilded_bronze_chestplate",
            new ModCustomItem(new Item.Properties()
                    .humanoidArmor(ModArmorMaterials.GILDED_BRONZE, ArmorType.CHESTPLATE)
                    .setId(generateItemKey("gilded_bronze_chestplate")),
                    "tooltip.gilded_bronze_armor", false));
    public static final Item GILDED_BRONZE_LEGGINGS = registerItem("gilded_bronze_leggings",
            new ModCustomItem(new Item.Properties()
                    .humanoidArmor(ModArmorMaterials.GILDED_BRONZE, ArmorType.LEGGINGS)
                    .setId(generateItemKey("gilded_bronze_leggings")),
                    "tooltip.gilded_bronze_armor", false));
    public static final Item GILDED_BRONZE_BOOTS = registerItem("gilded_bronze_boots",
            new ModCustomItem(new Item.Properties()
                    .humanoidArmor(ModArmorMaterials.GILDED_BRONZE, ArmorType.BOOTS)
                    .setId(generateItemKey("gilded_bronze_boots")),
                    "tooltip.gilded_bronze_armor", false));
    //endregion

    //region Cobalt-Steel armor
    public static final Item COBALT_STEEL_HELMET = registerItem("cobalt_steel_helmet",
            new ModEndgameArmorItem(ModItemUtils.EndgameTier.COBALT_STEEL,
                    new Item.Properties()
                            .humanoidArmor(ModArmorMaterials.COBALT_STEEL, ArmorType.HELMET)
                            .setId(generateItemKey("cobalt_steel_helmet"))));
    public static final Item COBALT_STEEL_CHESTPLATE = registerItem("cobalt_steel_chestplate",
            new ModEndgameArmorItem(ModItemUtils.EndgameTier.COBALT_STEEL,
                    new Item.Properties()
                            .humanoidArmor(ModArmorMaterials.COBALT_STEEL, ArmorType.CHESTPLATE)
                            .setId(generateItemKey("cobalt_steel_chestplate"))));
    public static final Item COBALT_STEEL_LEGGINGS = registerItem("cobalt_steel_leggings",
            new ModEndgameArmorItem(ModItemUtils.EndgameTier.COBALT_STEEL,
                    new Item.Properties()
                            .humanoidArmor(ModArmorMaterials.COBALT_STEEL, ArmorType.LEGGINGS)
                            .setId(generateItemKey("cobalt_steel_leggings"))));
    public static final Item COBALT_STEEL_BOOTS = registerItem("cobalt_steel_boots",
            new ModEndgameArmorItem(ModItemUtils.EndgameTier.COBALT_STEEL,
                    new Item.Properties()
                            .humanoidArmor(ModArmorMaterials.COBALT_STEEL, ArmorType.BOOTS)
                            .setId(generateItemKey("cobalt_steel_boots"))));
    //endregion

    //region Infused Gemstone armor
    public static final Item INFUSED_GEMSTONE_HELMET = registerItem("infused_gemstone_helmet",
            new ModEndgameArmorItem(ModItemUtils.EndgameTier.INFUSED_GEMSTONE,
                    new Item.Properties()
                            .humanoidArmor(ModArmorMaterials.INFUSED_GEMSTONE, ArmorType.HELMET)
                            .setId(generateItemKey("infused_gemstone_helmet"))));
    public static final Item INFUSED_GEMSTONE_CHESTPLATE = registerItem("infused_gemstone_chestplate",
            new ModEndgameArmorItem(ModItemUtils.EndgameTier.INFUSED_GEMSTONE,
                    new Item.Properties()
                            .humanoidArmor(ModArmorMaterials.INFUSED_GEMSTONE, ArmorType.CHESTPLATE)
                            .setId(generateItemKey("infused_gemstone_chestplate"))));
    public static final Item INFUSED_GEMSTONE_LEGGINGS = registerItem("infused_gemstone_leggings",
            new ModEndgameArmorItem(ModItemUtils.EndgameTier.INFUSED_GEMSTONE,
                    new Item.Properties()
                            .humanoidArmor(ModArmorMaterials.INFUSED_GEMSTONE, ArmorType.LEGGINGS)
                            .setId(generateItemKey("infused_gemstone_leggings"))));
    public static final Item INFUSED_GEMSTONE_BOOTS = registerItem("infused_gemstone_boots",
            new ModEndgameArmorItem(ModItemUtils.EndgameTier.INFUSED_GEMSTONE,
                    new Item.Properties()
                            .humanoidArmor(ModArmorMaterials.INFUSED_GEMSTONE, ArmorType.BOOTS)
                            .setId(generateItemKey("infused_gemstone_boots"))));
    //endregion

    //region Infused Gemstone armor
    public static final Item TUNGSTEN_CARBIDE_HELMET = registerItem("tungsten_carbide_helmet",
            new ModEndgameArmorItem(ModItemUtils.EndgameTier.TUNGSTEN_CARBIDE,
                    new Item.Properties()
                            .humanoidArmor(ModArmorMaterials.TUNGSTEN_CARBIDE, ArmorType.HELMET)
                            .setId(generateItemKey("tungsten_carbide_helmet"))));  //41 durability multiplier
    public static final Item TUNGSTEN_CARBIDE_CHESTPLATE = registerItem("tungsten_carbide_chestplate",
            new ModEndgameArmorItem(ModItemUtils.EndgameTier.TUNGSTEN_CARBIDE,
                    new Item.Properties()
                            .humanoidArmor(ModArmorMaterials.TUNGSTEN_CARBIDE, ArmorType.CHESTPLATE)
                            .setId(generateItemKey("tungsten_carbide_chestplate"))));
    public static final Item TUNGSTEN_CARBIDE_LEGGINGS = registerItem("tungsten_carbide_leggings",
            new ModEndgameArmorItem(ModItemUtils.EndgameTier.TUNGSTEN_CARBIDE,
                    new Item.Properties()
                            .humanoidArmor(ModArmorMaterials.TUNGSTEN_CARBIDE, ArmorType.LEGGINGS)
                            .setId(generateItemKey("tungsten_carbide_leggings"))));
    public static final Item TUNGSTEN_CARBIDE_BOOTS = registerItem("tungsten_carbide_boots",
            new ModEndgameArmorItem(ModItemUtils.EndgameTier.TUNGSTEN_CARBIDE,
                    new Item.Properties()
                            .humanoidArmor(ModArmorMaterials.TUNGSTEN_CARBIDE, ArmorType.BOOTS)
                            .setId(generateItemKey("tungsten_carbide_boots"))));
    //endregion





//    /**
//     * Adds items (specified in-method) to the Ingredients creative mode tab.
//     * @param entries The Entries object to add the new Items to
//     */
//    private static void addItemsToIngredientItemGroup(FabricItemGroupEntries entries) {
//        //REMEMBER TO ADD EACH ITEM HERE
//        //entries.add(RUBY);
//        //entries.add(SAPPHIRE);
//        //entries.add(INFUSED_GEMSTONE);
//        //entries.add(MOLTEN_CORE);
//    }

    private static ResourceKey<Item> generateItemKey(String name) {
        return ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(ModMain.MOD_ID, name));
    }

    /**
     * Registers a single item
     * @param name String identifier for the name
     * @param item Actual Item object to register
     * @return The registered Minecraft Item
     */
    private static Item registerItem(String name, Item item) {
        return Registry.register(BuiltInRegistries.ITEM, Identifier.fromNamespaceAndPath(ModMain.MOD_ID, name), item);
    }

    /**
     * Handles registering all mod items.
     */
    public static void registerModItems() {
        ModMain.LOGGER.info("Registering Mod Items for " + ModMain.MOD_ID);

        //ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(ModItems::addItemsToIngredientItemGroup);
    }
}
