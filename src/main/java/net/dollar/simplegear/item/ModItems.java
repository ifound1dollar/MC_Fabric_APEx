package net.dollar.simplegear.item;

import net.dollar.simplegear.ModMain;
import net.dollar.simplegear.item.custom.ModBattleaxeItem;
import net.dollar.simplegear.item.custom.ModCollectorItem;
import net.dollar.simplegear.item.custom.ModGildedBronzeArmorItem;
import net.dollar.simplegear.item.custom.ModPaxelItem;
import net.dollar.simplegear.item.custom.bow.ModInfusedGemstoneBowItem;
import net.dollar.simplegear.item.custom.bow.ModCobaltSteelBowItem;
import net.dollar.simplegear.item.custom.bow.ModTungstenCarbideBowItem;
import net.dollar.simplegear.item.custom.tungstencarbide.*;
import net.dollar.simplegear.item.custom.cobaltsteel.*;
import net.dollar.simplegear.item.custom.crossbow.ModInfusedGemstoneCrossbowItem;
import net.dollar.simplegear.item.custom.crossbow.ModCobaltSteelCrossbowItem;
import net.dollar.simplegear.item.custom.crossbow.ModTungstenCarbideCrossbowItem;
import net.dollar.simplegear.item.custom.infusedgemstone.*;
import net.dollar.simplegear.util.ModArmorMaterials;
import net.dollar.simplegear.util.ModSmithingUpgradeItemHelper;
import net.dollar.simplegear.util.ModToolMaterials;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItems {
    //region Raw Items, Gems, Compounds
    public static final Item RUBY = registerItem("ruby",
            new Item(new FabricItemSettings()));
    public static final Item SAPPHIRE = registerItem("sapphire",
            new Item(new FabricItemSettings()));
    public static final Item COBALT_SHARD = registerItem("cobalt_shard",
            new Item(new FabricItemSettings()));
    public static final Item PHOSPHATE_POWDER = registerItem("phosphate_powder",
            new Item(new FabricItemSettings()));
    public static final Item RAW_TIN = registerItem("raw_tin",
            new Item(new FabricItemSettings()));
    public static final Item TIN_INGOT = registerItem("tin_ingot",
            new Item(new FabricItemSettings()));
    public static final Item BRONZE_COMPOUND = registerItem("bronze_compound",
            new Item(new FabricItemSettings()));
    public static final Item BRONZE_INGOT = registerItem("bronze_ingot",
            new Item(new FabricItemSettings()));
    public static final Item STEEL_COMPOUND = registerItem("steel_compound",
            new Item(new FabricItemSettings()));
    public static final Item STEEL_INGOT = registerItem("steel_ingot",
            new Item(new FabricItemSettings()));
    public static final Item RAW_TUNGSTEN = registerItem("raw_tungsten",
            new Item(new FabricItemSettings()));
    public static final Item TUNGSTEN_INGOT = registerItem("tungsten_ingot",
            new Item(new FabricItemSettings()));
    //endregion

    //Region End-game upgrade items
    public static final Item MOLTEN_CORE = registerItem("molten_core",
            new Item(new FabricItemSettings().fireproof()));
    public static final Item HANDFUL_OF_STARDUST = registerItem("handful_of_stardust",
            new Item(new FabricItemSettings()));
    public static final Item COMPOUND_GEMSTONE = registerItem("compound_gemstone",
            new Item(new FabricItemSettings()));
    public static final Item INFUSED_GEMSTONE = registerItem("infused_gemstone",
            new ModInfusedGemstoneItem(new FabricItemSettings()));
    public static final Item COBALT_STEEL_INGOT = registerItem("cobalt_steel_ingot",
            new ModCobaltSteelIngotItem(new FabricItemSettings()));
    public static final Item TUNGSTEN_CARBIDE_INGOT = registerItem("tungsten_carbide_ingot",
            new ModTungstenCarbideIngotItem(new FabricItemSettings()));
    //endregion

    //region Upgrade Templates
    public static final Item BASIC_UPGRADE_TEMPLATE = registerItem("basic_upgrade_template",
            new Item(new FabricItemSettings()));
    public static final Item COBALT_UPGRADE_TEMPLATE = registerItem("cobalt_upgrade_smithing_template",
            ModSmithingUpgradeItemHelper.createCobaltUpgradeTemplate());
    public static final Item INFUSION_UPGRADE_TEMPLATE = registerItem("infusion_upgrade_smithing_template",
            ModSmithingUpgradeItemHelper.createInfusionUpgradeTemplate());
    public static final Item CARBIDE_UPGRADE_TEMPLATE = registerItem("carbide_upgrade_smithing_template",
            ModSmithingUpgradeItemHelper.createCarbideUpgradeTemplate());
    //endregion

    //region Bows/Crossbows 
    public static final Item INFUSED_GEMSTONE_BOW = registerItem("infused_gemstone_bow",
            new ModInfusedGemstoneBowItem(new FabricItemSettings().maxDamage(1200)));   //Base durability (maxDamage) is 384
    public static final Item INFUSED_GEMSTONE_CROSSBOW = registerItem("infused_gemstone_crossbow",
            new ModInfusedGemstoneCrossbowItem(new FabricItemSettings().maxDamage(1500)));  //Base durability (maxDamage) is 465
    public static final Item COBALT_STEEL_BOW = registerItem("cobalt_steel_bow",
            new ModCobaltSteelBowItem(new FabricItemSettings().maxDamage(1200)));
    public static final Item COBALT_STEEL_CROSSBOW = registerItem("cobalt_steel_crossbow",
            new ModCobaltSteelCrossbowItem(new FabricItemSettings().maxDamage(1500)));
    public static final Item TUNGSTEN_CARBIDE_BOW = registerItem("tungsten_carbide_bow",
            new ModTungstenCarbideBowItem(new FabricItemSettings().maxDamage(1200)));
    public static final Item TUNGSTEN_CARBIDE_CROSSBOW = registerItem("tungsten_carbide_crossbow",
            new ModTungstenCarbideCrossbowItem(new FabricItemSettings().maxDamage(1500)));
    //endregion

    //region Axes
    public static final Item BRONZE_AXE = registerItem("bronze_axe",
            new AxeItem(ModToolMaterials.BRONZE, 6.0f, -3.1f,
                    new FabricItemSettings()));
    public static final Item GILDED_BRONZE_AXE = registerItem("gilded_bronze_axe",
            new AxeItem(ModToolMaterials.GILDED_BRONZE, 6.0f, -2.8f,
                    new FabricItemSettings()));
    public static final Item INFUSED_GEMSTONE_AXE = registerItem("infused_gemstone_axe",
            new ModInfusedGemstoneAxeItem(ModToolMaterials.INFUSED_GEMSTONE, 5.0f, -2.9f,
                    new FabricItemSettings())); //Faster, Netherite = 5.0f, -3.0f
    public static final Item COBALT_STEEL_AXE = registerItem("cobalt_steel_axe",
            new ModCobaltSteelAxeItem(ModToolMaterials.COBALT_STEEL, 5.0f, -2.7f,
                    new FabricItemSettings())); //Very fast, Netherite = 5.0f, -3.0f
    public static final Item TUNGSTEN_CARBIDE_AXE = registerItem("tungsten_carbide_axe",
            new ModTungstenCarbideAxeItem(ModToolMaterials.TUNGSTEN_CARBIDE, 6.0f, -3.2f,
                    new FabricItemSettings())); //Slower, Netherite = 5.0f, -3.0f
    //endregion

    //region Battleaxes
    public static final Item DIAMOND_BATTLEAXE = registerItem("diamond_battleaxe",
            new ModBattleaxeItem(ToolMaterials.DIAMOND, 5, -3.0f,
                    new FabricItemSettings()));
    public static final Item NETHERITE_BATTLEAXE = registerItem("netherite_battleaxe",
            new ModBattleaxeItem(ToolMaterials.NETHERITE, 5, -3.0f,
                    new FabricItemSettings().fireproof())); //Equivalent to Axe stats
    public static final Item COBALT_STEEL_BATTLEAXE = registerItem("cobalt_steel_battleaxe",
            new ModCobaltSteelBattleaxeItem(ModToolMaterials.COBALT_STEEL, 5, -2.7f,
                    new FabricItemSettings())); //Faster, Netherite = 5.0f, -3.0f
    public static final Item INFUSED_GEMSTONE_BATTLEAXE = registerItem("infused_gemstone_battleaxe",
            new ModInfusedGemstoneBattleaxeItem(ModToolMaterials.INFUSED_GEMSTONE, 5, -2.9f,
                    new FabricItemSettings())); //Very fast, Netherite = 5.0f, -3.0f
    public static final Item TUNGSTEN_CARBIDE_BATTLEAXE = registerItem("tungsten_carbide_battleaxe",
            new ModTungstenCarbideBattleaxeItem(ModToolMaterials.TUNGSTEN_CARBIDE, 6, -3.2f,
                    new FabricItemSettings())); //Slower, Netherite = 5.0f, -3.0f
    //endregion

    //region Hoes
    public static final Item BRONZE_HOE = registerItem("bronze_hoe",
            new HoeItem(ModToolMaterials.BRONZE, -2, -1.0f,
                    new FabricItemSettings()));
    public static final Item GILDED_BRONZE_HOE = registerItem("gilded_bronze_hoe",
            new HoeItem(ModToolMaterials.GILDED_BRONZE, -2, 0.0f,
                    new FabricItemSettings()));
    public static final Item INFUSED_GEMSTONE_HOE = registerItem("infused_gemstone_hoe",
            new ModInfusedGemstoneHoeItem(ModToolMaterials.INFUSED_GEMSTONE, -2, -1.0f,
                    new FabricItemSettings())); //Faster, Netherite = -4, 0.0f
    public static final Item COBALT_STEEL_HOE = registerItem("cobalt_steel_hoe",
            new ModCobaltSteelHoeItem(ModToolMaterials.COBALT_STEEL, -2, 0.0f,
                    new FabricItemSettings())); //Very fast, Netherite = -4, 0.0f
    public static final Item TUNGSTEN_CARBIDE_HOE = registerItem("tungsten_carbide_hoe",
            new ModTungstenCarbideHoeItem(ModToolMaterials.TUNGSTEN_CARBIDE, -3, -2.0f,
                    new FabricItemSettings())); //Slower, Netherite = -4, 0.0f
    //endregion

    //region Paxels
    public static final Item DIAMOND_PAXEL = registerItem("diamond_paxel",
            new ModPaxelItem(ToolMaterials.DIAMOND, 2.0f, -2.9f,
                    new FabricItemSettings()));
    public static final Item NETHERITE_PAXEL = registerItem("netherite_paxel",
            new ModPaxelItem(ToolMaterials.NETHERITE, 2.0f, -2.9f,
                    new FabricItemSettings().fireproof())); //Rough average between Axe, Pickaxe, and Shovel stats
    public static final Item COBALT_STEEL_PAXEL = registerItem("cobalt_steel_paxel",
            new ModCobaltSteelPaxelItem(ModToolMaterials.COBALT_STEEL, 2.0f, -2.6f,
                    new FabricItemSettings())); //Faster, Netherite = 2.0f, -2.9f
    public static final Item INFUSED_GEMSTONE_PAXEL = registerItem("infused_gemstone_paxel",
            new ModInfusedGemstonePaxelItem(ModToolMaterials.INFUSED_GEMSTONE, 2.0f, -2.8f,
                    new FabricItemSettings())); //Very fast, Netherite = 2.0f, -2.9f
    public static final Item TUNGSTEN_CARBIDE_PAXEL = registerItem("tungsten_carbide_paxel",
            new ModTungstenCarbidePaxelItem(ModToolMaterials.TUNGSTEN_CARBIDE, 2.5f, -3.1f,
                    new FabricItemSettings())); //Slower, Netherite = 2.0f, -2.9f
    //endregion

    //region Pickaxes
    public static final Item BRONZE_PICKAXE = registerItem("bronze_pickaxe",
            new PickaxeItem(ModToolMaterials.BRONZE, 1, -2.8f,
                    new FabricItemSettings()));
    public static final Item GILDED_BRONZE_PICKAXE = registerItem("gilded_bronze_pickaxe",
            new PickaxeItem(ModToolMaterials.GILDED_BRONZE, 1, -2.6f,
                    new FabricItemSettings()));
    public static final Item INFUSED_GEMSTONE_PICKAXE = registerItem("infused_gemstone_pickaxe",
            new ModInfusedGemstonePickaxeItem(ModToolMaterials.INFUSED_GEMSTONE, 1, -2.7f,
                    new FabricItemSettings())); //Faster, Netherite = 1, -2.8f
    public static final Item COBALT_STEEL_PICKAXE = registerItem("cobalt_steel_pickaxe",
            new ModCobaltSteelPickaxeItem(ModToolMaterials.COBALT_STEEL, 1, -2.5f,
                    new FabricItemSettings())); //Slower, Netherite = 1, -2.8f
    public static final Item TUNGSTEN_CARBIDE_PICKAXE = registerItem("tungsten_carbide_pickaxe",
            new ModTungstenCarbidePickaxeItem(ModToolMaterials.TUNGSTEN_CARBIDE, 1, -3.0f,
                    new FabricItemSettings())); //Very fast, Netherite = 1, -2.8f
    //endregion

    //region Shovel
    public static final Item BRONZE_SHOVEL = registerItem("bronze_shovel",
            new ShovelItem(ModToolMaterials.BRONZE, 1.5f, -3.0f,
                    new FabricItemSettings()));
    public static final Item GILDED_BRONZE_SHOVEL = registerItem("gilded_bronze_shovel",
            new ShovelItem(ModToolMaterials.GILDED_BRONZE, 1.5f, -2.8f,
                    new FabricItemSettings()));
    public static final Item INFUSED_GEMSTONE_SHOVEL = registerItem("infused_gemstone_shovel",
            new ModInfusedGemstoneShovelItem(ModToolMaterials.INFUSED_GEMSTONE, 2.0f, -2.9f,
                    new FabricItemSettings())); //Faster, Netherite = 1.5f, -3.0f
    public static final Item COBALT_STEEL_SHOVEL = registerItem("cobalt_steel_shovel",
            new ModCobaltSteelShovelItem(ModToolMaterials.COBALT_STEEL, 1.5f, -2.7f,
                    new FabricItemSettings())); //Very fast, Netherite = 1.5f, -3.0f
    public static final Item TUNGSTEN_CARBIDE_SHOVEL = registerItem("tungsten_carbide_shovel",
            new ModTungstenCarbideShovelItem(ModToolMaterials.TUNGSTEN_CARBIDE, 2.0f, -3.2f,
                    new FabricItemSettings())); //Slower, Netherite = 1.5f, -3.0f
    //endregion

    //region Sword
    public static final Item BRONZE_SWORD = registerItem("bronze_sword",
            new SwordItem(ModToolMaterials.BRONZE, 3, -2.4f,
                    new FabricItemSettings()));
    public static final Item GILDED_BRONZE_SWORD = registerItem("gilded_bronze_sword",
            new SwordItem(ModToolMaterials.GILDED_BRONZE, 3, -2.2f,
                    new FabricItemSettings()));
    public static final Item INFUSED_GEMSTONE_SWORD = registerItem("infused_gemstone_sword",
            new ModInfusedGemstoneSwordItem(ModToolMaterials.INFUSED_GEMSTONE, 3, -2.3f,
                    new FabricItemSettings())); //Faster, Netherite = 3, -2.4f
    public static final Item COBALT_STEEL_SWORD = registerItem("cobalt_steel_sword",
            new ModCobaltSteelSwordItem(ModToolMaterials.COBALT_STEEL, 3, -2.0f,
                    new FabricItemSettings())); //Very fast, Netherite = 3, -2.4f
    public static final Item TUNGSTEN_CARBIDE_SWORD = registerItem("tungsten_carbide_sword",
            new ModTungstenCarbideSwordItem(ModToolMaterials.TUNGSTEN_CARBIDE, 3, -2.6f,
                    new FabricItemSettings())); //Slower, Netherite = 3, -2.4f
    //endregion

    //region Bronze armor
    public static final Item BRONZE_HELMET = registerItem("bronze_helmet",
            new ArmorItem(ModArmorMaterials.BRONZE, ArmorItem.Type.HELMET, new FabricItemSettings()));
    public static final Item BRONZE_CHESTPLATE = registerItem("bronze_chestplate",
            new ArmorItem(ModArmorMaterials.BRONZE, ArmorItem.Type.CHESTPLATE, new FabricItemSettings()));
    public static final Item BRONZE_LEGGINGS = registerItem("bronze_leggings",
            new ArmorItem(ModArmorMaterials.BRONZE, ArmorItem.Type.LEGGINGS, new FabricItemSettings()));
    public static final Item BRONZE_BOOTS = registerItem("bronze_boots",
            new ArmorItem(ModArmorMaterials.BRONZE, ArmorItem.Type.BOOTS, new FabricItemSettings()));
    //endregion

    //region Gilded Bronze armor
    public static final Item GILDED_BRONZE_HELMET = registerItem("gilded_bronze_helmet",
            new ModGildedBronzeArmorItem(ModArmorMaterials.GILDED_BRONZE, ArmorItem.Type.HELMET,
                    new FabricItemSettings()));
    public static final Item GILDED_BRONZE_CHESTPLATE = registerItem("gilded_bronze_chestplate",
            new ModGildedBronzeArmorItem(ModArmorMaterials.GILDED_BRONZE, ArmorItem.Type.CHESTPLATE,
                    new FabricItemSettings()));
    public static final Item GILDED_BRONZE_LEGGINGS = registerItem("gilded_bronze_leggings",
            new ModGildedBronzeArmorItem(ModArmorMaterials.GILDED_BRONZE, ArmorItem.Type.LEGGINGS,
                    new FabricItemSettings()));
    public static final Item GILDED_BRONZE_BOOTS = registerItem("gilded_bronze_boots",
            new ModGildedBronzeArmorItem(ModArmorMaterials.GILDED_BRONZE, ArmorItem.Type.BOOTS,
                    new FabricItemSettings()));
    //endregion

    //region Cobalt-Steel armor
    public static final Item COBALT_STEEL_HELMET = registerItem("cobalt_steel_helmet",
            new ModCobaltSteelArmorItem(ModArmorMaterials.COBALT_STEEL, ArmorItem.Type.HELMET,
                    new FabricItemSettings()));
    public static final Item COBALT_STEEL_CHESTPLATE = registerItem("cobalt_steel_chestplate",
            new ModCobaltSteelArmorItem(ModArmorMaterials.COBALT_STEEL, ArmorItem.Type.CHESTPLATE,
                    new FabricItemSettings()));
    public static final Item COBALT_STEEL_LEGGINGS = registerItem("cobalt_steel_leggings",
            new ModCobaltSteelArmorItem(ModArmorMaterials.COBALT_STEEL, ArmorItem.Type.LEGGINGS,
                    new FabricItemSettings()));
    public static final Item COBALT_STEEL_BOOTS = registerItem("cobalt_steel_boots",
            new ModCobaltSteelArmorItem(ModArmorMaterials.COBALT_STEEL, ArmorItem.Type.BOOTS,
                    new FabricItemSettings()));
    //endregion

    //region Infused Gemstone armor
    public static final Item INFUSED_GEMSTONE_HELMET = registerItem("infused_gemstone_helmet",
            new ModInfusedGemstoneArmorItem(ModArmorMaterials.INFUSED_GEMSTONE, ArmorItem.Type.HELMET,
                    new FabricItemSettings()));
    public static final Item INFUSED_GEMSTONE_CHESTPLATE = registerItem("infused_gemstone_chestplate",
            new ModInfusedGemstoneArmorItem(ModArmorMaterials.INFUSED_GEMSTONE, ArmorItem.Type.CHESTPLATE,
                    new FabricItemSettings()));
    public static final Item INFUSED_GEMSTONE_LEGGINGS = registerItem("infused_gemstone_leggings",
            new ModInfusedGemstoneArmorItem(ModArmorMaterials.INFUSED_GEMSTONE, ArmorItem.Type.LEGGINGS,
                    new FabricItemSettings()));
    public static final Item INFUSED_GEMSTONE_BOOTS = registerItem("infused_gemstone_boots",
            new ModInfusedGemstoneArmorItem(ModArmorMaterials.INFUSED_GEMSTONE, ArmorItem.Type.BOOTS,
                    new FabricItemSettings()));
    //endregion

    //region Infused Gemstone armor
    public static final Item TUNGSTEN_CARBIDE_HELMET = registerItem("tungsten_carbide_helmet",
            new ModTungstenCarbideArmorItem(ModArmorMaterials.TUNGSTEN_CARBIDE, ArmorItem.Type.HELMET,
                    new FabricItemSettings()));
    public static final Item TUNGSTEN_CARBIDE_CHESTPLATE = registerItem("tungsten_carbide_chestplate",
            new ModTungstenCarbideArmorItem(ModArmorMaterials.TUNGSTEN_CARBIDE, ArmorItem.Type.CHESTPLATE,
                    new FabricItemSettings()));
    public static final Item TUNGSTEN_CARBIDE_LEGGINGS = registerItem("tungsten_carbide_leggings",
            new ModTungstenCarbideArmorItem(ModArmorMaterials.TUNGSTEN_CARBIDE, ArmorItem.Type.LEGGINGS,
                    new FabricItemSettings()));
    public static final Item TUNGSTEN_CARBIDE_BOOTS = registerItem("tungsten_carbide_boots",
            new ModTungstenCarbideArmorItem(ModArmorMaterials.TUNGSTEN_CARBIDE, ArmorItem.Type.BOOTS,
                    new FabricItemSettings()));
    //endregion

    //region Collector items
    //Set maxCount here instead of in the item class.
    public static final Item COLLECTOR_OBSIDIAN_DUST = registerItem("collector_obsidian_dust",
            new ModCollectorItem(new FabricItemSettings().maxCount(1), "1"));
    public static final Item COLLECTOR_KATHLEENS_LOST_DIADEM = registerItem("collector_kathleens_lost_diadem",
            new ModCollectorItem(new FabricItemSettings().maxCount(1), "2"));
    public static final Item COLLECTOR_POTION_OF_EVERLASTING_YOUTH = registerItem("collector_potion_of_everlasting_youth",
            new ModCollectorItem(new FabricItemSettings().maxCount(1), "3"));
    public static final Item COLLECTOR_SLIGHTLY_OVERCOOKED_CHICKEN = registerItem("collector_slightly_overcooked_chicken",
            new ModCollectorItem(new FabricItemSettings().maxCount(1), "4"));
    //endregion




    /**
     * Adds items (specified in-method) to the Ingredients creative mode tab.
     * @param entries The Entries object to add the new Items to
     */
    private static void addItemsToIngredientItemGroup(FabricItemGroupEntries entries) {
        //REMEMBER TO ADD EACH ITEM HERE
        //entries.add(RUBY);
        //entries.add(SAPPHIRE);
        //entries.add(INFUSED_GEMSTONE);
        //entries.add(MOLTEN_CORE);
    }

    /**
     * Adds items (specified in-method) to the Ingredients creative mode tab.
     * @param entries The Entries object to add the new Items to
     */
    private static void addItemsToCombatItemGroup(FabricItemGroupEntries entries) {
        //REMEMBER TO ADD EACH ITEM HERE
        //entries.add(STEEL_BOW);
        //entries.add(STEEL_CROSSBOW);
    }

    /**
     * Adds items (specified in-method) to the Ingredients creative mode tab.
     * @param entries The Entries object to add the new Items to
     */
    private static void addItemsToMiscItemGroup(FabricItemGroupEntries entries) {
        //REMEMBER TO ADD EACH ITEM HERE
        //entries.add(KATHLEENS_LOST_DIADEM);
    }

    /**
     * Registers a single item
     * @param name String identifier for the name
     * @param item Actual Item object to register
     * @return The registered Minecraft Item
     */
    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, new Identifier(ModMain.MOD_ID, name), item);
    }

    /**
     * Handles registering all mod items.
     */
    public static void registerModItems() {
        ModMain.LOGGER.info("Registering Mod Items for " + ModMain.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(ModItems::addItemsToIngredientItemGroup);
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register(ModItems::addItemsToCombatItemGroup);
        //TODO: FIGURE OUT WHICH TAB IS THE MISC. ONE THEN REPLACE INGREDIENTS
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(ModItems::addItemsToMiscItemGroup);
    }
}
