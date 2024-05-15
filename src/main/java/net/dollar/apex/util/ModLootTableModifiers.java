package net.dollar.apex.util;

import net.dollar.apex.item.ModItems;
import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import net.minecraft.item.Items;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.condition.RandomChanceLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.util.Identifier;

/**
 * Handles all loot table modification, can be ANY type of loot table (blocks, chests, entities, etc.).
 */
public class ModLootTableModifiers {
    //Declare Identifiers here, then use them below within modifyLootTables().

    //region COMMON / LOWER QUALITY STRUCTURE CHESTS (rarely includes basic templates)
    private static final Identifier VILLAGE_ARMORER_ID = new Identifier("chests/village/village_armorer");
    private static final Identifier VILLAGE_MASON_ID = new Identifier("chests/village/village_mason");
    private static final Identifier VILLAGE_TOOLSMITH_ID = new Identifier("chests/village/village_toolsmith");
    private static final Identifier VILLAGE_WEAPONSMITH_ID = new Identifier("chests/village/village_weaponsmith");
    private static final Identifier IGLOO_CHEST_ID = new Identifier("chests/igloo_chest");
    private static final Identifier SHIPWRECK_SUPPLY_ID = new Identifier("chests/shipwreck_supply");
    //endregion

    //region UNCOMMON / MODERATE QUALITY STRUCTURE CHESTS (includes basic templates, NO complete templates)
    private static final Identifier ABANDONED_MINESHAFT_ID = new Identifier("chests/abandoned_mineshaft");
    private static final Identifier BURIED_TREASURE_ID = new Identifier("chests/buried_treasure");
    private static final Identifier DESERT_PYRAMID_ID = new Identifier("chests/desert_pyramid");
    private static final Identifier JUNGLE_TEMPLE_ID = new Identifier("chests/jungle_temple");
    private static final Identifier NETHER_BRIDGE_ID = new Identifier("chests/nether_bridge");
    private static final Identifier PILLAGER_OUTPOST_ID = new Identifier("chests/pillager_outpost");
    private static final Identifier RUINED_PORTAL_ID = new Identifier("chests/ruined_portal");
    private static final Identifier SHIPWRECK_TREASURE_ID = new Identifier("chests/shipwreck_treasure");
    private static final Identifier SIMPLE_DUNGEON_ID = new Identifier("chests/simple_dungeon");
    private static final Identifier UNDERWATER_RUIN_BIG_ID = new Identifier("chests/underwater_ruin_big");
    private static final Identifier UNDERWATER_RUIN_SMALL_ID = new Identifier("chests/underwater_ruin_small");
    //endregion

    //region RARE / HIGH QUALITY STRUCTURE CHESTS (can include complete templates)
    private static final Identifier ANCIENT_CITY_ID = new Identifier("chests/ancient_city");
    private static final Identifier ANCIENT_CITY_ICE_BOX_ID = new Identifier("chests/ancient_city_ice_box");
    private static final Identifier BASTION_BRIDGE_ID = new Identifier("chests/bastion_bridge");
    private static final Identifier BASTION_HOGLIN_STABLE_ID = new Identifier("chests/bastion_hoglin_stable");
    private static final Identifier BASTION_OTHER_ID = new Identifier("chests/bastion_other");
    private static final Identifier BASTION_TREASURE_ID = new Identifier("chests/bastion_treasure");
    private static final Identifier END_CITY_TREASURE_ID = new Identifier("chests/end_city_treasure");
    private static final Identifier STRONGHOLD_CORRIDOR_ID = new Identifier("chests/stronghold_corridor");
    private static final Identifier STRONGHOLD_CROSSING_ID = new Identifier("chests/stronghold_crossing");
    private static final Identifier STRONGHOLD_LIBRARY_ID = new Identifier("chests/stronghold_library");
    private static final Identifier WOODLAND_MANSION_ID = new Identifier("chests/woodland_mansion");
    //endregion



    /**
     * Registers loot table modifiers. Each registry will inject a single modifier into a single loot table
     *  identifier. Each defines the number of rolls, the chance to generate, the items in the pool, and
     *  the minimum and maximum number of items that can generate.
     */
    public static void modifyLootTables() {
        LootTableEvents.MODIFY.register((resourceManager, lootManager, id, tablebuilder, source) -> {
            //ROLLS -> NUMBER OF UNIQUE ROLLS (WHICH CAN EACH GENERATE ITEMS)
            //CONDITIONALLY -> CHANCE TO DROP IN RANGE OF 0.0 - 1.0
            //WITH -> THE ITEMS THAT MAY GENERATE
            //APPLY:CREATE -> NUMBER OF ITEMS THAT WILL GENERATE PER ROLL

            if (ABANDONED_MINESHAFT_ID.equals(id)) abandonedMineshaftBuilder(tablebuilder);

            if (ANCIENT_CITY_ID.equals(id)) ancientCityBuilder(tablebuilder);

            if (BASTION_BRIDGE_ID.equals(id)) bastionBridgeBuilder(tablebuilder);

            if (BASTION_HOGLIN_STABLE_ID.equals(id)) bastionHoglinStableBuilder(tablebuilder);

            if (BASTION_OTHER_ID.equals(id)) bastionOtherBuilder(tablebuilder);

            if (BASTION_TREASURE_ID.equals(id)) bastionTreasureBuilder(tablebuilder);

            if (BURIED_TREASURE_ID.equals(id)) buriedTreasureBuilder(tablebuilder);

            if (DESERT_PYRAMID_ID.equals(id)) desertPyramidBuilder(tablebuilder);

            if (END_CITY_TREASURE_ID.equals(id)) endCityTreasureBuilder(tablebuilder);

            if (JUNGLE_TEMPLE_ID.equals(id)) jungleTempleBuilder(tablebuilder);

            if (NETHER_BRIDGE_ID.equals(id)) netherBridgeBuilder(tablebuilder);

            if (PILLAGER_OUTPOST_ID.equals(id)) pillagerOutpostBuilder(tablebuilder);

            if (RUINED_PORTAL_ID.equals(id)) ruinedPortalBuilder(tablebuilder);

            if (SHIPWRECK_TREASURE_ID.equals(id)) shipwreckTreasureBuilder(tablebuilder);

            if (SIMPLE_DUNGEON_ID.equals(id)) simpleDungeonBuilder(tablebuilder);

            if (STRONGHOLD_CORRIDOR_ID.equals(id)) strongholdCorridorBuilder(tablebuilder);

            if (STRONGHOLD_CROSSING_ID.equals(id)) strongholdCrossingBuilder(tablebuilder);

            if (STRONGHOLD_LIBRARY_ID.equals(id)) strongholdLibraryBuilder(tablebuilder);

            if (UNDERWATER_RUIN_BIG_ID.equals(id)) underwaterRuinBigBuilder(tablebuilder);

            if (UNDERWATER_RUIN_SMALL_ID.equals(id)) underwaterRuinSmallBuilder(tablebuilder);

            if (VILLAGE_ARMORER_ID.equals(id)) villageArmorerBuilder(tablebuilder);

            if (VILLAGE_TOOLSMITH_ID.equals(id)) villageToolsmithBuilder(tablebuilder);

            if (VILLAGE_WEAPONSMITH_ID.equals(id)) villageWeaponsmithBuilder(tablebuilder);

            if (WOODLAND_MANSION_ID.equals(id)) woodlandMansionBuilder(tablebuilder);
        });
    }



    static void abandonedMineshaftBuilder(LootTable.Builder builder) {
        //BASIC TEMPLATE, UNCOMMON
        LootPool.Builder poolBuilder = LootPool.builder()
                .rolls(ConstantLootNumberProvider.create(1))    //Number of rolls
                .conditionally(RandomChanceLootCondition.builder(0.33f)) //Chance to drop 0.0-1.0
                .with(ItemEntry.builder(ModItems.BASIC_UPGRADE_TEMPLATE))
                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)).build()); //Range of items that will drop PER ROLL
        builder.pool(poolBuilder.build());

        //FINISHED TEMPLATES, VERY RARE
        poolBuilder = LootPool.builder()
                .rolls(ConstantLootNumberProvider.create(1))    //Number of rolls
                .conditionally(RandomChanceLootCondition.builder(0.1f)) //Chance to drop 0.0-1.0
                .with(ItemEntry.builder(ModItems.COBALT_UPGRADE_TEMPLATE))
                .with(ItemEntry.builder(ModItems.INFUSION_UPGRADE_TEMPLATE))
                .with(ItemEntry.builder(ModItems.CARBIDE_UPGRADE_TEMPLATE))
                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)).build()); //Range of items that will drop
        builder.pool(poolBuilder.build());

        //UPGRADE INGREDIENTS, COMMON (MULTIPLE ROLLS, SINGLE ITEM PER STACK)
        poolBuilder = LootPool.builder()
                .rolls(ConstantLootNumberProvider.create(2))    //Number of rolls
                .conditionally(RandomChanceLootCondition.builder(0.5f)) //Chance to drop 0.0-1.0
                .with(ItemEntry.builder(ModItems.RUBY))
                .with(ItemEntry.builder(ModItems.SAPPHIRE))
                .with(ItemEntry.builder(ModItems.STEEL_INGOT))
                .with(ItemEntry.builder(ModItems.COBALT_SHARD))
                .with(ItemEntry.builder(ModItems.TUNGSTEN_INGOT))
                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)).build()); //Range of items that will drop
        builder.pool(poolBuilder.build());
    }

    static void ancientCityBuilder(LootTable.Builder builder) {
        //BASIC, UNCOMMON
        LootPool.Builder poolBuilder = LootPool.builder()
                .rolls(ConstantLootNumberProvider.create(1))    //Number of rolls
                .conditionally(RandomChanceLootCondition.builder(0.33f)) //Chance to drop 0.0-1.0
                .with(ItemEntry.builder(ModItems.BASIC_UPGRADE_TEMPLATE))
                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)).build()); //Range of items that will drop
        builder.pool(poolBuilder.build());

        //FINISHED (INCLUDE NETHERITE TEMPLATE HERE TOO), COMMON
        poolBuilder = LootPool.builder()
                .rolls(ConstantLootNumberProvider.create(1))    //Number of rolls
                .conditionally(RandomChanceLootCondition.builder(0.5f)) //Chance to drop 0.0-1.0
                .with(ItemEntry.builder(ModItems.COBALT_UPGRADE_TEMPLATE))
                .with(ItemEntry.builder(ModItems.INFUSION_UPGRADE_TEMPLATE))
                .with(ItemEntry.builder(ModItems.CARBIDE_UPGRADE_TEMPLATE))
                .with(ItemEntry.builder(Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE))
                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)).build()); //Range of items that will drop
        builder.pool(poolBuilder.build());
    }

    static void bastionBridgeBuilder(LootTable.Builder builder) {
        //BASIC, RARE
        LootPool.Builder poolBuilder = LootPool.builder()
                .rolls(ConstantLootNumberProvider.create(1))    //Number of rolls
                .conditionally(RandomChanceLootCondition.builder(0.2f)) //Chance to drop 0.0-1.0
                .with(ItemEntry.builder(ModItems.BASIC_UPGRADE_TEMPLATE))
                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)).build()); //Range of items that will drop
        builder.pool(poolBuilder.build());

        //FINISHED, UNCOMMON
        poolBuilder = LootPool.builder()
                .rolls(ConstantLootNumberProvider.create(1))    //Number of rolls
                .conditionally(RandomChanceLootCondition.builder(0.33f)) //Chance to drop 0.0-1.0
                .with(ItemEntry.builder(ModItems.COBALT_UPGRADE_TEMPLATE))
                .with(ItemEntry.builder(ModItems.INFUSION_UPGRADE_TEMPLATE))
                .with(ItemEntry.builder(ModItems.CARBIDE_UPGRADE_TEMPLATE))
                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)).build()); //Range of items that will drop
        builder.pool(poolBuilder.build());
    }

    static void bastionHoglinStableBuilder(LootTable.Builder builder) {
        //BASIC, RARE
        LootPool.Builder poolBuilder = LootPool.builder()
                .rolls(ConstantLootNumberProvider.create(1))    //Number of rolls
                .conditionally(RandomChanceLootCondition.builder(0.2f)) //Chance to drop 0.0-1.0
                .with(ItemEntry.builder(ModItems.BASIC_UPGRADE_TEMPLATE))
                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)).build()); //Range of items that will drop
        builder.pool(poolBuilder.build());
    }

    static void bastionOtherBuilder(LootTable.Builder builder) {
        //BASIC, RARE
        LootPool.Builder poolBuilder = LootPool.builder()
                .rolls(ConstantLootNumberProvider.create(1))    //Number of rolls
                .conditionally(RandomChanceLootCondition.builder(0.2f)) //Chance to drop 0.0-1.0
                .with(ItemEntry.builder(ModItems.BASIC_UPGRADE_TEMPLATE))
                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)).build()); //Range of items that will drop
        builder.pool(poolBuilder.build());

        //FINISHED, UNCOMMON
        poolBuilder = LootPool.builder()
                .rolls(ConstantLootNumberProvider.create(1))    //Number of rolls
                .conditionally(RandomChanceLootCondition.builder(0.33f)) //Chance to drop 0.0-1.0
                .with(ItemEntry.builder(ModItems.COBALT_UPGRADE_TEMPLATE))
                .with(ItemEntry.builder(ModItems.INFUSION_UPGRADE_TEMPLATE))
                .with(ItemEntry.builder(ModItems.CARBIDE_UPGRADE_TEMPLATE))
                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)).build()); //Range of items that will drop
        builder.pool(poolBuilder.build());
    }

    static void bastionTreasureBuilder(LootTable.Builder builder) {
        //BASIC, UNCOMMON
        LootPool.Builder poolBuilder = LootPool.builder()
                .rolls(ConstantLootNumberProvider.create(1))    //Number of rolls
                .conditionally(RandomChanceLootCondition.builder(0.33f)) //Chance to drop 0.0-1.0
                .with(ItemEntry.builder(ModItems.BASIC_UPGRADE_TEMPLATE))
                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)).build()); //Range of items that will drop
        builder.pool(poolBuilder.build());

        //FINISHED, COMMON
        poolBuilder = LootPool.builder()
                .rolls(ConstantLootNumberProvider.create(1))    //Number of rolls
                .conditionally(RandomChanceLootCondition.builder(0.5f)) //Chance to drop 0.0-1.0
                .with(ItemEntry.builder(ModItems.COBALT_UPGRADE_TEMPLATE))
                .with(ItemEntry.builder(ModItems.INFUSION_UPGRADE_TEMPLATE))
                .with(ItemEntry.builder(ModItems.CARBIDE_UPGRADE_TEMPLATE))
                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)).build()); //Range of items that will drop
        builder.pool(poolBuilder.build());
    }

    static void buriedTreasureBuilder(LootTable.Builder builder) {
        //BASIC, RARE
        LootPool.Builder poolBuilder = LootPool.builder()
                .rolls(ConstantLootNumberProvider.create(1))    //Number of rolls
                .conditionally(RandomChanceLootCondition.builder(0.2f)) //Chance to drop 0.0-1.0
                .with(ItemEntry.builder(ModItems.BASIC_UPGRADE_TEMPLATE))
                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)).build()); //Range of items that will drop
        builder.pool(poolBuilder.build());

        //UPGRADE INGREDIENTS, UNCOMMON (MULTIPLE ROLLS, SINGLE ITEM PER STACK)
        poolBuilder = LootPool.builder()
                .rolls(ConstantLootNumberProvider.create(2))    //Number of rolls
                .conditionally(RandomChanceLootCondition.builder(0.33f)) //Chance to drop 0.0-1.0
                .with(ItemEntry.builder(ModItems.RUBY))
                .with(ItemEntry.builder(ModItems.SAPPHIRE))
                .with(ItemEntry.builder(ModItems.STEEL_INGOT))
                .with(ItemEntry.builder(ModItems.TUNGSTEN_INGOT))
                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)).build()); //Range of items that will drop
        builder.pool(poolBuilder.build());
    }

    private static void desertPyramidBuilder(LootTable.Builder tablebuilder) {
        //BASIC, VERY RARE (REMEMBER: 4 chests)
        LootPool.Builder poolBuilder = LootPool.builder()
                .rolls(ConstantLootNumberProvider.create(1))    //Number of rolls
                .conditionally(RandomChanceLootCondition.builder(0.1f)) //Chance to drop 0.0-1.0
                .with(ItemEntry.builder(ModItems.BASIC_UPGRADE_TEMPLATE))
                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)).build()); //Range of items that will drop
        tablebuilder.pool(poolBuilder.build());

        //UPGRADE INGREDIENTS, VERY RARE (MULTIPLE ROLLS, SINGLE ITEM PER STACK)
        poolBuilder = LootPool.builder()
                .rolls(ConstantLootNumberProvider.create(2))    //Number of rolls
                .conditionally(RandomChanceLootCondition.builder(0.1f)) //Chance to drop 0.0-1.0
                .with(ItemEntry.builder(ModItems.RUBY))
                .with(ItemEntry.builder(ModItems.SAPPHIRE))
                .with(ItemEntry.builder(ModItems.STEEL_INGOT))
                .with(ItemEntry.builder(ModItems.TUNGSTEN_INGOT))
                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)).build()); //Range of items that will drop
        tablebuilder.pool(poolBuilder.build());
    }

    private static void endCityTreasureBuilder(LootTable.Builder tablebuilder) {
        //BASIC, UNCOMMON
        LootPool.Builder poolBuilder = LootPool.builder()
                .rolls(ConstantLootNumberProvider.create(1))    //Number of rolls
                .conditionally(RandomChanceLootCondition.builder(0.33f)) //Chance to drop 0.0-1.0
                .with(ItemEntry.builder(ModItems.BASIC_UPGRADE_TEMPLATE))
                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)).build()); //Range of items that will drop
        tablebuilder.pool(poolBuilder.build());

        //FINISHED (INCLUDE NETHERITE TEMPLATE HERE TOO), RARE
        poolBuilder = LootPool.builder()
                .rolls(ConstantLootNumberProvider.create(1))    //Number of rolls
                .conditionally(RandomChanceLootCondition.builder(0.2f)) //Chance to drop 0.0-1.0
                .with(ItemEntry.builder(ModItems.COBALT_UPGRADE_TEMPLATE))
                .with(ItemEntry.builder(ModItems.INFUSION_UPGRADE_TEMPLATE))
                .with(ItemEntry.builder(ModItems.CARBIDE_UPGRADE_TEMPLATE))
                .with(ItemEntry.builder(Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE))
                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)).build()); //Range of items that will drop
        tablebuilder.pool(poolBuilder.build());

        //UPGRADE INGREDIENTS, COMMON (MULTIPLE ROLLS, SINGLE ITEM PER STACK)
        poolBuilder = LootPool.builder()
                .rolls(ConstantLootNumberProvider.create(2))    //Number of rolls
                .conditionally(RandomChanceLootCondition.builder(0.5f)) //Chance to drop 0.0-1.0
                .with(ItemEntry.builder(ModItems.RUBY))
                .with(ItemEntry.builder(ModItems.SAPPHIRE))
                .with(ItemEntry.builder(ModItems.STEEL_INGOT))
                .with(ItemEntry.builder(ModItems.TUNGSTEN_INGOT))
                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)).build()); //Range of items that will drop
        tablebuilder.pool(poolBuilder.build());
    }

    private static void jungleTempleBuilder(LootTable.Builder tablebuilder) {
        //BASIC, UNCOMMON
        LootPool.Builder poolBuilder = LootPool.builder()
                .rolls(ConstantLootNumberProvider.create(1))    //Number of rolls
                .conditionally(RandomChanceLootCondition.builder(0.33f)) //Chance to drop 0.0-1.0
                .with(ItemEntry.builder(ModItems.BASIC_UPGRADE_TEMPLATE))
                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)).build()); //Range of items that will drop
        tablebuilder.pool(poolBuilder.build());

        //UPGRADE INGREDIENTS, UNCOMMON (MULTIPLE ROLLS, SINGLE ITEM PER STACK)
        poolBuilder = LootPool.builder()
                .rolls(ConstantLootNumberProvider.create(2))    //Number of rolls
                .conditionally(RandomChanceLootCondition.builder(0.33f)) //Chance to drop 0.0-1.0
                .with(ItemEntry.builder(ModItems.RUBY))
                .with(ItemEntry.builder(ModItems.SAPPHIRE))
                .with(ItemEntry.builder(ModItems.STEEL_INGOT))
                .with(ItemEntry.builder(ModItems.TUNGSTEN_INGOT))
                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)).build()); //Range of items that will drop
        tablebuilder.pool(poolBuilder.build());
    }

    private static void netherBridgeBuilder(LootTable.Builder tablebuilder) {
        //BASIC, UNCOMMON
        LootPool.Builder poolBuilder = LootPool.builder()
                .rolls(ConstantLootNumberProvider.create(1))    //Number of rolls
                .conditionally(RandomChanceLootCondition.builder(0.33f)) //Chance to drop 0.0-1.0
                .with(ItemEntry.builder(ModItems.BASIC_UPGRADE_TEMPLATE))
                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)).build()); //Range of items that will drop
        tablebuilder.pool(poolBuilder.build());

        //FINISHED (INCLUDE NETHERITE TEMPLATE HERE TOO), VERY RARE
        poolBuilder = LootPool.builder()
                .rolls(ConstantLootNumberProvider.create(1))    //Number of rolls
                .conditionally(RandomChanceLootCondition.builder(0.1f)) //Chance to drop 0.0-1.0
                .with(ItemEntry.builder(ModItems.COBALT_UPGRADE_TEMPLATE))
                .with(ItemEntry.builder(ModItems.INFUSION_UPGRADE_TEMPLATE))
                .with(ItemEntry.builder(ModItems.CARBIDE_UPGRADE_TEMPLATE))
                .with(ItemEntry.builder(Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE))
                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)).build()); //Range of items that will drop
        tablebuilder.pool(poolBuilder.build());
    }

    private static void pillagerOutpostBuilder(LootTable.Builder tablebuilder) {
        //BASIC, COMMON
        LootPool.Builder poolBuilder = LootPool.builder()
                .rolls(ConstantLootNumberProvider.create(1))    //Number of rolls
                .conditionally(RandomChanceLootCondition.builder(0.5f)) //Chance to drop 0.0-1.0
                .with(ItemEntry.builder(ModItems.BASIC_UPGRADE_TEMPLATE))
                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)).build()); //Range of items that will drop
        tablebuilder.pool(poolBuilder.build());

        //UPGRADE INGREDIENTS, COMMON (MULTIPLE ROLLS, SINGLE ITEM PER STACK)
        poolBuilder = LootPool.builder()
                .rolls(ConstantLootNumberProvider.create(2))    //Number of rolls
                .conditionally(RandomChanceLootCondition.builder(0.5f)) //Chance to drop 0.0-1.0
                .with(ItemEntry.builder(ModItems.RUBY))
                .with(ItemEntry.builder(ModItems.SAPPHIRE))
                .with(ItemEntry.builder(ModItems.STEEL_INGOT))
                .with(ItemEntry.builder(ModItems.TUNGSTEN_INGOT))
                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)).build()); //Range of items that will drop
        tablebuilder.pool(poolBuilder.build());
    }

    private static void ruinedPortalBuilder(LootTable.Builder tablebuilder) {
        //BASIC, UNCOMMON
        LootPool.Builder poolBuilder = LootPool.builder()
                .rolls(ConstantLootNumberProvider.create(1))    //Number of rolls
                .conditionally(RandomChanceLootCondition.builder(0.33f)) //Chance to drop 0.0-1.0
                .with(ItemEntry.builder(ModItems.BASIC_UPGRADE_TEMPLATE))
                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)).build()); //Range of items that will drop
        tablebuilder.pool(poolBuilder.build());

        //UPGRADE INGREDIENTS, UNCOMMON (MULTIPLE ROLLS, SINGLE ITEM PER STACK)
        poolBuilder = LootPool.builder()
                .rolls(ConstantLootNumberProvider.create(2))    //Number of rolls
                .conditionally(RandomChanceLootCondition.builder(0.33f)) //Chance to drop 0.0-1.0
                .with(ItemEntry.builder(ModItems.RUBY))
                .with(ItemEntry.builder(ModItems.SAPPHIRE))
                .with(ItemEntry.builder(ModItems.STEEL_INGOT))
                .with(ItemEntry.builder(ModItems.TUNGSTEN_INGOT))
                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)).build()); //Range of items that will drop
        tablebuilder.pool(poolBuilder.build());
    }

    private static void shipwreckTreasureBuilder(LootTable.Builder tablebuilder) {
        //BASIC, UNCOMMON
        LootPool.Builder poolBuilder = LootPool.builder()
                .rolls(ConstantLootNumberProvider.create(1))    //Number of rolls
                .conditionally(RandomChanceLootCondition.builder(0.33f)) //Chance to drop 0.0-1.0
                .with(ItemEntry.builder(ModItems.BASIC_UPGRADE_TEMPLATE))
                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)).build()); //Range of items that will drop
        tablebuilder.pool(poolBuilder.build());

        //UPGRADE INGREDIENTS, UNCOMMON (MULTIPLE ROLLS, SINGLE ITEM PER STACK)
        poolBuilder = LootPool.builder()
                .rolls(ConstantLootNumberProvider.create(2))    //Number of rolls
                .conditionally(RandomChanceLootCondition.builder(0.33f)) //Chance to drop 0.0-1.0
                .with(ItemEntry.builder(ModItems.RUBY))
                .with(ItemEntry.builder(ModItems.SAPPHIRE))
                .with(ItemEntry.builder(ModItems.STEEL_INGOT))
                .with(ItemEntry.builder(ModItems.TUNGSTEN_INGOT))
                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)).build()); //Range of items that will drop
        tablebuilder.pool(poolBuilder.build());
    }

    private static void simpleDungeonBuilder(LootTable.Builder tablebuilder) {
        //BASIC, UNCOMMON
        LootPool.Builder poolBuilder = LootPool.builder()
                .rolls(ConstantLootNumberProvider.create(1))    //Number of rolls
                .conditionally(RandomChanceLootCondition.builder(0.33f)) //Chance to drop 0.0-1.0
                .with(ItemEntry.builder(ModItems.BASIC_UPGRADE_TEMPLATE))
                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)).build()); //Range of items that will drop
        tablebuilder.pool(poolBuilder.build());

        //FINISHED, VERY RARE
        poolBuilder = LootPool.builder()
                .rolls(ConstantLootNumberProvider.create(1))    //Number of rolls
                .conditionally(RandomChanceLootCondition.builder(0.1f)) //Chance to drop 0.0-1.0
                .with(ItemEntry.builder(ModItems.COBALT_UPGRADE_TEMPLATE))
                .with(ItemEntry.builder(ModItems.INFUSION_UPGRADE_TEMPLATE))
                .with(ItemEntry.builder(ModItems.CARBIDE_UPGRADE_TEMPLATE))
                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)).build()); //Range of items that will drop
        tablebuilder.pool(poolBuilder.build());

        //UPGRADE INGREDIENTS, UNCOMMON (MULTIPLE ROLLS, SINGLE ITEM PER STACK)
        poolBuilder = LootPool.builder()
                .rolls(ConstantLootNumberProvider.create(2))    //Number of rolls
                .conditionally(RandomChanceLootCondition.builder(0.33f)) //Chance to drop 0.0-1.0
                .with(ItemEntry.builder(ModItems.RUBY))
                .with(ItemEntry.builder(ModItems.SAPPHIRE))
                .with(ItemEntry.builder(ModItems.STEEL_INGOT))
                .with(ItemEntry.builder(ModItems.TUNGSTEN_INGOT))
                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)).build()); //Range of items that will drop
        tablebuilder.pool(poolBuilder.build());
    }

    private static void strongholdCorridorBuilder(LootTable.Builder tablebuilder) {
        //BASIC, UNCOMMON
        LootPool.Builder poolBuilder = LootPool.builder()
                .rolls(ConstantLootNumberProvider.create(1))    //Number of rolls
                .conditionally(RandomChanceLootCondition.builder(0.33f)) //Chance to drop 0.0-1.0
                .with(ItemEntry.builder(ModItems.BASIC_UPGRADE_TEMPLATE))
                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)).build()); //Range of items that will drop
        tablebuilder.pool(poolBuilder.build());

        //FINISHED (INCLUDE NETHERITE TEMPLATE HERE TOO), VERY RARE
        poolBuilder = LootPool.builder()
                .rolls(ConstantLootNumberProvider.create(1))    //Number of rolls
                .conditionally(RandomChanceLootCondition.builder(0.1f)) //Chance to drop 0.0-1.0
                .with(ItemEntry.builder(ModItems.COBALT_UPGRADE_TEMPLATE))
                .with(ItemEntry.builder(ModItems.INFUSION_UPGRADE_TEMPLATE))
                .with(ItemEntry.builder(ModItems.CARBIDE_UPGRADE_TEMPLATE))
                .with(ItemEntry.builder(Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE))
                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)).build()); //Range of items that will drop
        tablebuilder.pool(poolBuilder.build());

        //UPGRADE INGREDIENTS, COMMON (MULTIPLE ROLLS, SINGLE ITEM PER STACK)
        poolBuilder = LootPool.builder()
                .rolls(ConstantLootNumberProvider.create(2))    //Number of rolls
                .conditionally(RandomChanceLootCondition.builder(0.5f)) //Chance to drop 0.0-1.0
                .with(ItemEntry.builder(ModItems.RUBY))
                .with(ItemEntry.builder(ModItems.SAPPHIRE))
                .with(ItemEntry.builder(ModItems.STEEL_INGOT))
                .with(ItemEntry.builder(ModItems.TUNGSTEN_INGOT))
                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)).build()); //Range of items that will drop
        tablebuilder.pool(poolBuilder.build());
    }

    private static void strongholdCrossingBuilder(LootTable.Builder tablebuilder) {
        //BASIC, UNCOMMON
        LootPool.Builder poolBuilder = LootPool.builder()
                .rolls(ConstantLootNumberProvider.create(1))    //Number of rolls
                .conditionally(RandomChanceLootCondition.builder(0.33f)) //Chance to drop 0.0-1.0
                .with(ItemEntry.builder(ModItems.BASIC_UPGRADE_TEMPLATE))
                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)).build()); //Range of items that will drop
        tablebuilder.pool(poolBuilder.build());

        //FINISHED (INCLUDE NETHERITE TEMPLATE HERE TOO), VERY RARE
        poolBuilder = LootPool.builder()
                .rolls(ConstantLootNumberProvider.create(1))    //Number of rolls
                .conditionally(RandomChanceLootCondition.builder(0.1f)) //Chance to drop 0.0-1.0
                .with(ItemEntry.builder(ModItems.COBALT_UPGRADE_TEMPLATE))
                .with(ItemEntry.builder(ModItems.INFUSION_UPGRADE_TEMPLATE))
                .with(ItemEntry.builder(ModItems.CARBIDE_UPGRADE_TEMPLATE))
                .with(ItemEntry.builder(Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE))
                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)).build()); //Range of items that will drop
        tablebuilder.pool(poolBuilder.build());

        //UPGRADE INGREDIENTS, COMMON (MULTIPLE ROLLS, SINGLE ITEM PER STACK)
        poolBuilder = LootPool.builder()
                .rolls(ConstantLootNumberProvider.create(2))    //Number of rolls
                .conditionally(RandomChanceLootCondition.builder(0.5f)) //Chance to drop 0.0-1.0
                .with(ItemEntry.builder(ModItems.RUBY))
                .with(ItemEntry.builder(ModItems.SAPPHIRE))
                .with(ItemEntry.builder(ModItems.STEEL_INGOT))
                .with(ItemEntry.builder(ModItems.TUNGSTEN_INGOT))
                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)).build()); //Range of items that will drop
        tablebuilder.pool(poolBuilder.build());
    }

    private static void strongholdLibraryBuilder(LootTable.Builder tablebuilder) {
        //BASIC, UNCOMMON
        LootPool.Builder poolBuilder = LootPool.builder()
                .rolls(ConstantLootNumberProvider.create(1))    //Number of rolls
                .conditionally(RandomChanceLootCondition.builder(0.33f)) //Chance to drop 0.0-1.0
                .with(ItemEntry.builder(ModItems.BASIC_UPGRADE_TEMPLATE))
                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)).build()); //Range of items that will drop
        tablebuilder.pool(poolBuilder.build());

        //FINISHED (INCLUDE NETHERITE TEMPLATE HERE TOO), VERY RARE
        poolBuilder = LootPool.builder()
                .rolls(ConstantLootNumberProvider.create(1))    //Number of rolls
                .conditionally(RandomChanceLootCondition.builder(0.1f)) //Chance to drop 0.0-1.0
                .with(ItemEntry.builder(ModItems.COBALT_UPGRADE_TEMPLATE))
                .with(ItemEntry.builder(ModItems.INFUSION_UPGRADE_TEMPLATE))
                .with(ItemEntry.builder(ModItems.CARBIDE_UPGRADE_TEMPLATE))
                .with(ItemEntry.builder(Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE))
                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)).build()); //Range of items that will drop
        tablebuilder.pool(poolBuilder.build());

        //UPGRADE INGREDIENTS, COMMON (MULTIPLE ROLLS, SINGLE ITEM PER STACK)
        poolBuilder = LootPool.builder()
                .rolls(ConstantLootNumberProvider.create(2))    //Number of rolls
                .conditionally(RandomChanceLootCondition.builder(0.5f)) //Chance to drop 0.0-1.0
                .with(ItemEntry.builder(ModItems.RUBY))
                .with(ItemEntry.builder(ModItems.SAPPHIRE))
                .with(ItemEntry.builder(ModItems.STEEL_INGOT))
                .with(ItemEntry.builder(ModItems.TUNGSTEN_INGOT))
                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)).build()); //Range of items that will drop
        tablebuilder.pool(poolBuilder.build());
    }

    private static void underwaterRuinBigBuilder(LootTable.Builder tablebuilder) {
        //BASIC, UNCOMMON
        LootPool.Builder poolBuilder = LootPool.builder()
                .rolls(ConstantLootNumberProvider.create(1))    //Number of rolls
                .conditionally(RandomChanceLootCondition.builder(0.33f)) //Chance to drop 0.0-1.0
                .with(ItemEntry.builder(ModItems.BASIC_UPGRADE_TEMPLATE))
                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)).build()); //Range of items that will drop
        tablebuilder.pool(poolBuilder.build());

        //UPGRADE INGREDIENTS, UNCOMMON (MULTIPLE ROLLS, SINGLE ITEM PER STACK)
        poolBuilder = LootPool.builder()
                .rolls(ConstantLootNumberProvider.create(2))    //Number of rolls
                .conditionally(RandomChanceLootCondition.builder(0.33f)) //Chance to drop 0.0-1.0
                .with(ItemEntry.builder(ModItems.RUBY))
                .with(ItemEntry.builder(ModItems.SAPPHIRE))
                .with(ItemEntry.builder(ModItems.STEEL_INGOT))
                .with(ItemEntry.builder(ModItems.TUNGSTEN_INGOT))
                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)).build()); //Range of items that will drop
        tablebuilder.pool(poolBuilder.build());
    }

    private static void underwaterRuinSmallBuilder(LootTable.Builder tablebuilder) {
        //BASIC, UNCOMMON
        LootPool.Builder poolBuilder = LootPool.builder()
                .rolls(ConstantLootNumberProvider.create(1))    //Number of rolls
                .conditionally(RandomChanceLootCondition.builder(0.33f)) //Chance to drop 0.0-1.0
                .with(ItemEntry.builder(ModItems.BASIC_UPGRADE_TEMPLATE))
                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)).build()); //Range of items that will drop
        tablebuilder.pool(poolBuilder.build());

        //UPGRADE INGREDIENTS, UNCOMMON (MULTIPLE ROLLS, SINGLE ITEM PER STACK)
        poolBuilder = LootPool.builder()
                .rolls(ConstantLootNumberProvider.create(2))    //Number of rolls
                .conditionally(RandomChanceLootCondition.builder(0.33f)) //Chance to drop 0.0-1.0
                .with(ItemEntry.builder(ModItems.RUBY))
                .with(ItemEntry.builder(ModItems.SAPPHIRE))
                .with(ItemEntry.builder(ModItems.STEEL_INGOT))
                .with(ItemEntry.builder(ModItems.TUNGSTEN_INGOT))
                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)).build()); //Range of items that will drop
        tablebuilder.pool(poolBuilder.build());
    }

    private static void villageArmorerBuilder(LootTable.Builder tablebuilder) {
        //BASIC, VERY RARE
        LootPool.Builder poolBuilder = LootPool.builder()
                .rolls(ConstantLootNumberProvider.create(1))    //Number of rolls
                .conditionally(RandomChanceLootCondition.builder(0.1f)) //Chance to drop 0.0-1.0
                .with(ItemEntry.builder(ModItems.BASIC_UPGRADE_TEMPLATE))
                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)).build()); //Range of items that will drop
        tablebuilder.pool(poolBuilder.build());

        //UPGRADE INGREDIENTS, RARE (MULTIPLE ROLLS, SINGLE ITEM PER STACK)
        poolBuilder = LootPool.builder()
                .rolls(ConstantLootNumberProvider.create(2))    //Number of rolls
                .conditionally(RandomChanceLootCondition.builder(0.2f)) //Chance to drop 0.0-1.0
                .with(ItemEntry.builder(ModItems.RUBY))
                .with(ItemEntry.builder(ModItems.SAPPHIRE))
                .with(ItemEntry.builder(ModItems.STEEL_INGOT))
                .with(ItemEntry.builder(ModItems.TUNGSTEN_INGOT))
                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)).build()); //Range of items that will drop
        tablebuilder.pool(poolBuilder.build());
    }

    private static void villageToolsmithBuilder(LootTable.Builder tablebuilder) {
        //BASIC, VERY RARE
        LootPool.Builder poolBuilder = LootPool.builder()
                .rolls(ConstantLootNumberProvider.create(1))    //Number of rolls
                .conditionally(RandomChanceLootCondition.builder(0.1f)) //Chance to drop 0.0-1.0
                .with(ItemEntry.builder(ModItems.BASIC_UPGRADE_TEMPLATE))
                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)).build()); //Range of items that will drop
        tablebuilder.pool(poolBuilder.build());

        //UPGRADE INGREDIENTS, RARE (MULTIPLE ROLLS, SINGLE ITEM PER STACK)
        poolBuilder = LootPool.builder()
                .rolls(ConstantLootNumberProvider.create(2))    //Number of rolls
                .conditionally(RandomChanceLootCondition.builder(0.2f)) //Chance to drop 0.0-1.0
                .with(ItemEntry.builder(ModItems.RUBY))
                .with(ItemEntry.builder(ModItems.SAPPHIRE))
                .with(ItemEntry.builder(ModItems.STEEL_INGOT))
                .with(ItemEntry.builder(ModItems.TUNGSTEN_INGOT))
                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)).build()); //Range of items that will drop
        tablebuilder.pool(poolBuilder.build());
    }

    private static void villageWeaponsmithBuilder(LootTable.Builder tablebuilder) {
        //BASIC, VERY RARE
        LootPool.Builder poolBuilder = LootPool.builder()
                .rolls(ConstantLootNumberProvider.create(1))    //Number of rolls
                .conditionally(RandomChanceLootCondition.builder(0.1f)) //Chance to drop 0.0-1.0
                .with(ItemEntry.builder(ModItems.BASIC_UPGRADE_TEMPLATE))
                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)).build()); //Range of items that will drop
        tablebuilder.pool(poolBuilder.build());

        //UPGRADE INGREDIENTS, RARE (MULTIPLE ROLLS, SINGLE ITEM PER STACK)
        poolBuilder = LootPool.builder()
                .rolls(ConstantLootNumberProvider.create(2))    //Number of rolls
                .conditionally(RandomChanceLootCondition.builder(0.2f)) //Chance to drop 0.0-1.0
                .with(ItemEntry.builder(ModItems.RUBY))
                .with(ItemEntry.builder(ModItems.SAPPHIRE))
                .with(ItemEntry.builder(ModItems.STEEL_INGOT))
                .with(ItemEntry.builder(ModItems.TUNGSTEN_INGOT))
                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)).build()); //Range of items that will drop
        tablebuilder.pool(poolBuilder.build());
    }

    private static void woodlandMansionBuilder(LootTable.Builder tablebuilder) {
        //BASIC, UNCOMMON
        LootPool.Builder poolBuilder = LootPool.builder()
                .rolls(ConstantLootNumberProvider.create(1))    //Number of rolls
                .conditionally(RandomChanceLootCondition.builder(0.33f)) //Chance to drop 0.0-1.0
                .with(ItemEntry.builder(ModItems.BASIC_UPGRADE_TEMPLATE))
                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)).build()); //Range of items that will drop
        tablebuilder.pool(poolBuilder.build());

        //FINISHED (INCLUDE NETHERITE TEMPLATE HERE TOO), COMMON
        poolBuilder = LootPool.builder()
                .rolls(ConstantLootNumberProvider.create(1))    //Number of rolls
                .conditionally(RandomChanceLootCondition.builder(0.5f)) //Chance to drop 0.0-1.0
                .with(ItemEntry.builder(ModItems.COBALT_UPGRADE_TEMPLATE))
                .with(ItemEntry.builder(ModItems.INFUSION_UPGRADE_TEMPLATE))
                .with(ItemEntry.builder(ModItems.CARBIDE_UPGRADE_TEMPLATE))
                .with(ItemEntry.builder(Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE))
                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)).build()); //Range of items that will drop
        tablebuilder.pool(poolBuilder.build());

        //UPGRADE INGREDIENTS, COMMON (MULTIPLE ROLLS, SINGLE ITEM PER STACK)
        poolBuilder = LootPool.builder()
                .rolls(ConstantLootNumberProvider.create(2))    //Number of rolls
                .conditionally(RandomChanceLootCondition.builder(0.5f)) //Chance to drop 0.0-1.0
                .with(ItemEntry.builder(ModItems.RUBY))
                .with(ItemEntry.builder(ModItems.SAPPHIRE))
                .with(ItemEntry.builder(ModItems.STEEL_INGOT))
                .with(ItemEntry.builder(ModItems.TUNGSTEN_INGOT))
                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)).build()); //Range of items that will drop
        tablebuilder.pool(poolBuilder.build());
    }
}
