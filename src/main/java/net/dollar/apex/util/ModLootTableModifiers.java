package net.dollar.apex.util;

import net.dollar.apex.item.ModItems;
import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import net.minecraft.item.Items;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.LootTables;
import net.minecraft.loot.condition.RandomChanceLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;

/**
 * Handles all loot table modification, can be ANY type of loot table (blocks, chests, entities, etc.).
 */
public class ModLootTableModifiers {
    /**
     * Registers loot table modifiers. Each registry will inject a single modifier into a single loot table
     *  identifier. Each defines the number of rolls, the chance to generate, the items in the pool, and
     *  the minimum and maximum number of items that can generate.
     */
    public static void modifyLootTables() {
        LootTableEvents.MODIFY.register((id, tablebuilder, source) -> {
            //ROLLS -> NUMBER OF UNIQUE ROLLS (WHICH CAN EACH GENERATE ITEMS)
            //CONDITIONALLY -> CHANCE TO DROP IN RANGE OF 0.0 - 1.0
            //WITH -> THE ITEMS THAT MAY GENERATE
            //APPLY:CREATE -> NUMBER OF ITEMS THAT WILL GENERATE PER ROLL

            if (LootTables.ABANDONED_MINESHAFT_CHEST.equals(id)) abandonedMineshaftBuilder(tablebuilder);

            if (LootTables.ANCIENT_CITY_CHEST.equals(id)) ancientCityBuilder(tablebuilder);

            if (LootTables.BASTION_BRIDGE_CHEST.equals(id)) bastionBridgeBuilder(tablebuilder);

            if (LootTables.BASTION_HOGLIN_STABLE_CHEST.equals(id)) bastionHoglinStableBuilder(tablebuilder);

            if (LootTables.BASTION_OTHER_CHEST.equals(id)) bastionOtherBuilder(tablebuilder);

            if (LootTables.BASTION_TREASURE_CHEST.equals(id)) bastionTreasureBuilder(tablebuilder);

            if (LootTables.BURIED_TREASURE_CHEST.equals(id)) buriedTreasureBuilder(tablebuilder);

            if (LootTables.DESERT_PYRAMID_CHEST.equals(id)) desertPyramidBuilder(tablebuilder);

            if (LootTables.END_CITY_TREASURE_CHEST.equals(id)) endCityTreasureBuilder(tablebuilder);

            if (LootTables.IGLOO_CHEST_CHEST.equals(id)) iglooChestBuilder(tablebuilder);

            if (LootTables.JUNGLE_TEMPLE_CHEST.equals(id)) jungleTempleBuilder(tablebuilder);

            if (LootTables.NETHER_BRIDGE_CHEST.equals(id)) netherBridgeBuilder(tablebuilder);

            if (LootTables.PILLAGER_OUTPOST_CHEST.equals(id)) pillagerOutpostBuilder(tablebuilder);

            if (LootTables.RUINED_PORTAL_CHEST.equals(id)) ruinedPortalBuilder(tablebuilder);

            if (LootTables.SHIPWRECK_SUPPLY_CHEST.equals(id)) shipwreckSupplyBuilder(tablebuilder);

            if (LootTables.SHIPWRECK_TREASURE_CHEST.equals(id)) shipwreckTreasureBuilder(tablebuilder);

            if (LootTables.SIMPLE_DUNGEON_CHEST.equals(id)) simpleDungeonBuilder(tablebuilder);

            if (LootTables.STRONGHOLD_CORRIDOR_CHEST.equals(id)) strongholdCorridorBuilder(tablebuilder);

            if (LootTables.STRONGHOLD_CROSSING_CHEST.equals(id)) strongholdCrossingBuilder(tablebuilder);

            if (LootTables.STRONGHOLD_LIBRARY_CHEST.equals(id)) strongholdLibraryBuilder(tablebuilder);

            if (LootTables.UNDERWATER_RUIN_BIG_CHEST.equals(id)) underwaterRuinBigBuilder(tablebuilder);

            if (LootTables.UNDERWATER_RUIN_BIG_CHEST.equals(id)) underwaterRuinSmallBuilder(tablebuilder);

            if (LootTables.VILLAGE_ARMORER_CHEST.equals(id)) villageArmorerBuilder(tablebuilder);

            if (LootTables.VILLAGE_TOOLSMITH_CHEST.equals(id)) villageToolsmithBuilder(tablebuilder);

            if (LootTables.VILLAGE_WEAPONSMITH_CHEST.equals(id)) villageWeaponsmithBuilder(tablebuilder);

            if (LootTables.WOODLAND_MANSION_CHEST.equals(id)) woodlandMansionBuilder(tablebuilder);
        });
    }



    static void abandonedMineshaftBuilder(LootTable.Builder builder) {
        //BASIC TEMPLATE, RARE
        LootPool.Builder poolBuilder = LootPool.builder()
                .rolls(ConstantLootNumberProvider.create(1))    //Number of rolls
                .conditionally(RandomChanceLootCondition.builder(0.2f)) //Chance to drop 0.0-1.0
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
                .conditionally(RandomChanceLootCondition.builder(0.4f)) //Chance to drop 0.0-1.0
                .with(ItemEntry.builder(ModItems.COBALT_SHARD))
                .with(ItemEntry.builder(ModItems.RUBY))
                .with(ItemEntry.builder(ModItems.SAPPHIRE))
                .with(ItemEntry.builder(ModItems.STEEL_INGOT))
                .with(ItemEntry.builder(ModItems.TUNGSTEN_INGOT))
                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)).build()); //Range of items that will drop
        builder.pool(poolBuilder.build());

        //BASIC INGREDIENTS, COMMON (MULTIPLE ROLLS, SINGLE ITEM PER STACK)
        poolBuilder = LootPool.builder()
                .rolls(ConstantLootNumberProvider.create(2))    //Number of rolls
                .conditionally(RandomChanceLootCondition.builder(0.4f)) //Chance to drop 0.0-1.0
                .with(ItemEntry.builder(ModItems.BRONZE_INGOT))
                .with(ItemEntry.builder(ModItems.PHOSPHATE_POWDER))
                .with(ItemEntry.builder(ModItems.TIN_INGOT))
                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 2.0f)).build()); //Range of items that will drop
        builder.pool(poolBuilder.build());
    }

    static void ancientCityBuilder(LootTable.Builder builder) {
        //BASIC, UNCOMMON
        LootPool.Builder poolBuilder = LootPool.builder()
                .rolls(ConstantLootNumberProvider.create(1))    //Number of rolls
                .conditionally(RandomChanceLootCondition.builder(0.3f)) //Chance to drop 0.0-1.0
                .with(ItemEntry.builder(ModItems.BASIC_UPGRADE_TEMPLATE))
                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)).build()); //Range of items that will drop
        builder.pool(poolBuilder.build());

        //FINISHED (INCLUDE NETHERITE TEMPLATE HERE TOO), COMMON
        poolBuilder = LootPool.builder()
                .rolls(ConstantLootNumberProvider.create(1))    //Number of rolls
                .conditionally(RandomChanceLootCondition.builder(0.4f)) //Chance to drop 0.0-1.0
                .with(ItemEntry.builder(ModItems.COBALT_UPGRADE_TEMPLATE))
                .with(ItemEntry.builder(ModItems.INFUSION_UPGRADE_TEMPLATE))
                .with(ItemEntry.builder(ModItems.CARBIDE_UPGRADE_TEMPLATE))
                .with(ItemEntry.builder(Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE))
                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)).build()); //Range of items that will drop
        builder.pool(poolBuilder.build());

        //UPGRADE INGREDIENTS, RARE (MULTIPLE ROLLS, SINGLE ITEM PER STACK)
        poolBuilder = LootPool.builder()
                .rolls(ConstantLootNumberProvider.create(2))    //Number of rolls
                .conditionally(RandomChanceLootCondition.builder(0.2f)) //Chance to drop 0.0-1.0
                .with(ItemEntry.builder(ModItems.COBALT_SHARD))
                .with(ItemEntry.builder(ModItems.RUBY))
                .with(ItemEntry.builder(ModItems.SAPPHIRE))
                .with(ItemEntry.builder(ModItems.STEEL_INGOT))
                .with(ItemEntry.builder(ModItems.TUNGSTEN_INGOT))
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
                .conditionally(RandomChanceLootCondition.builder(0.3f)) //Chance to drop 0.0-1.0
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
                .conditionally(RandomChanceLootCondition.builder(0.3f)) //Chance to drop 0.0-1.0
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
                .conditionally(RandomChanceLootCondition.builder(0.3f)) //Chance to drop 0.0-1.0
                .with(ItemEntry.builder(ModItems.BASIC_UPGRADE_TEMPLATE))
                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)).build()); //Range of items that will drop
        builder.pool(poolBuilder.build());

        //FINISHED, COMMON
        poolBuilder = LootPool.builder()
                .rolls(ConstantLootNumberProvider.create(1))    //Number of rolls
                .conditionally(RandomChanceLootCondition.builder(0.4f)) //Chance to drop 0.0-1.0
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
                .conditionally(RandomChanceLootCondition.builder(0.3f)) //Chance to drop 0.0-1.0
                .with(ItemEntry.builder(ModItems.COBALT_SHARD))
                .with(ItemEntry.builder(ModItems.RUBY))
                .with(ItemEntry.builder(ModItems.SAPPHIRE))
                .with(ItemEntry.builder(ModItems.STEEL_INGOT))
                .with(ItemEntry.builder(ModItems.TUNGSTEN_INGOT))
                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)).build()); //Range of items that will drop
        builder.pool(poolBuilder.build());

        //BASIC INGREDIENTS, UNCOMMON (MULTIPLE ROLLS, SINGLE ITEM PER STACK)
        poolBuilder = LootPool.builder()
                .rolls(ConstantLootNumberProvider.create(2))    //Number of rolls
                .conditionally(RandomChanceLootCondition.builder(0.3f)) //Chance to drop 0.0-1.0
                .with(ItemEntry.builder(ModItems.BRONZE_INGOT))
                .with(ItemEntry.builder(ModItems.PHOSPHATE_POWDER))
                .with(ItemEntry.builder(ModItems.TIN_INGOT))
                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 2.0f)).build()); //Range of items that will drop
        builder.pool(poolBuilder.build());
    }

    private static void desertPyramidBuilder(LootTable.Builder tablebuilder) {
        //BASIC, RARE (REMEMBER: 4 chests)
        LootPool.Builder poolBuilder = LootPool.builder()
                .rolls(ConstantLootNumberProvider.create(1))    //Number of rolls
                .conditionally(RandomChanceLootCondition.builder(0.2f)) //Chance to drop 0.0-1.0
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

        //BASIC INGREDIENTS, UNCOMMON (MULTIPLE ROLLS, SINGLE ITEM PER STACK) (REMEMBER: 4 chests)
        poolBuilder = LootPool.builder()
                .rolls(ConstantLootNumberProvider.create(2))    //Number of rolls
                .conditionally(RandomChanceLootCondition.builder(0.3f)) //Chance to drop 0.0-1.0
                .with(ItemEntry.builder(ModItems.BRONZE_INGOT))
                .with(ItemEntry.builder(ModItems.PHOSPHATE_POWDER))
                .with(ItemEntry.builder(ModItems.TIN_INGOT))
                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 2.0f)).build()); //Range of items that will drop
        tablebuilder.pool(poolBuilder.build());
    }

    private static void endCityTreasureBuilder(LootTable.Builder tablebuilder) {
        //BASIC, UNCOMMON
        LootPool.Builder poolBuilder = LootPool.builder()
                .rolls(ConstantLootNumberProvider.create(1))    //Number of rolls
                .conditionally(RandomChanceLootCondition.builder(0.3f)) //Chance to drop 0.0-1.0
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

        //UPGRADE INGREDIENTS, RARE (MULTIPLE ROLLS, SINGLE ITEM PER STACK)
        poolBuilder = LootPool.builder()
                .rolls(ConstantLootNumberProvider.create(2))    //Number of rolls
                .conditionally(RandomChanceLootCondition.builder(0.2f)) //Chance to drop 0.0-1.0
                .with(ItemEntry.builder(ModItems.COBALT_SHARD))
                .with(ItemEntry.builder(ModItems.RUBY))
                .with(ItemEntry.builder(ModItems.SAPPHIRE))
                .with(ItemEntry.builder(ModItems.STEEL_INGOT))
                .with(ItemEntry.builder(ModItems.TUNGSTEN_INGOT))
                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)).build()); //Range of items that will drop
        tablebuilder.pool(poolBuilder.build());
    }

    private static void iglooChestBuilder(LootTable.Builder tablebuilder) {
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
                .with(ItemEntry.builder(ModItems.COBALT_SHARD))
                .with(ItemEntry.builder(ModItems.RUBY))
                .with(ItemEntry.builder(ModItems.SAPPHIRE))
                .with(ItemEntry.builder(ModItems.STEEL_INGOT))
                .with(ItemEntry.builder(ModItems.TUNGSTEN_INGOT))
                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)).build()); //Range of items that will drop
        tablebuilder.pool(poolBuilder.build());

        //BASIC INGREDIENTS, UNCOMMON (MULTIPLE ROLLS, SINGLE ITEM PER STACK)
        poolBuilder = LootPool.builder()
                .rolls(ConstantLootNumberProvider.create(2))    //Number of rolls
                .conditionally(RandomChanceLootCondition.builder(0.3f)) //Chance to drop 0.0-1.0
                .with(ItemEntry.builder(ModItems.BRONZE_INGOT))
                .with(ItemEntry.builder(ModItems.PHOSPHATE_POWDER))
                .with(ItemEntry.builder(ModItems.TIN_INGOT))
                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 2.0f)).build()); //Range of items that will drop
        tablebuilder.pool(poolBuilder.build());
    }

    private static void jungleTempleBuilder(LootTable.Builder tablebuilder) {
        //BASIC, RARE
        LootPool.Builder poolBuilder = LootPool.builder()
                .rolls(ConstantLootNumberProvider.create(1))    //Number of rolls
                .conditionally(RandomChanceLootCondition.builder(0.2f)) //Chance to drop 0.0-1.0
                .with(ItemEntry.builder(ModItems.BASIC_UPGRADE_TEMPLATE))
                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)).build()); //Range of items that will drop
        tablebuilder.pool(poolBuilder.build());

        //UPGRADE INGREDIENTS, UNCOMMON (MULTIPLE ROLLS, SINGLE ITEM PER STACK)
        poolBuilder = LootPool.builder()
                .rolls(ConstantLootNumberProvider.create(2))    //Number of rolls
                .conditionally(RandomChanceLootCondition.builder(0.3f)) //Chance to drop 0.0-1.0
                .with(ItemEntry.builder(ModItems.COBALT_SHARD))
                .with(ItemEntry.builder(ModItems.RUBY))
                .with(ItemEntry.builder(ModItems.SAPPHIRE))
                .with(ItemEntry.builder(ModItems.STEEL_INGOT))
                .with(ItemEntry.builder(ModItems.TUNGSTEN_INGOT))
                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)).build()); //Range of items that will drop
        tablebuilder.pool(poolBuilder.build());

        //BASIC INGREDIENTS, COMMON (MULTIPLE ROLLS, SINGLE ITEM PER STACK)
        poolBuilder = LootPool.builder()
                .rolls(ConstantLootNumberProvider.create(2))    //Number of rolls
                .conditionally(RandomChanceLootCondition.builder(0.4f)) //Chance to drop 0.0-1.0
                .with(ItemEntry.builder(ModItems.BRONZE_INGOT))
                .with(ItemEntry.builder(ModItems.PHOSPHATE_POWDER))
                .with(ItemEntry.builder(ModItems.TIN_INGOT))
                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 2.0f)).build()); //Range of items that will drop
        tablebuilder.pool(poolBuilder.build());
    }

    private static void netherBridgeBuilder(LootTable.Builder tablebuilder) {
        //BASIC, UNCOMMON
        LootPool.Builder poolBuilder = LootPool.builder()
                .rolls(ConstantLootNumberProvider.create(1))    //Number of rolls
                .conditionally(RandomChanceLootCondition.builder(0.3f)) //Chance to drop 0.0-1.0
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
        //BASIC, UNCOMMON
        LootPool.Builder poolBuilder = LootPool.builder()
                .rolls(ConstantLootNumberProvider.create(1))    //Number of rolls
                .conditionally(RandomChanceLootCondition.builder(0.3f)) //Chance to drop 0.0-1.0
                .with(ItemEntry.builder(ModItems.BASIC_UPGRADE_TEMPLATE))
                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)).build()); //Range of items that will drop
        tablebuilder.pool(poolBuilder.build());

        //UPGRADE INGREDIENTS, COMMON (MULTIPLE ROLLS, SINGLE ITEM PER STACK)
        poolBuilder = LootPool.builder()
                .rolls(ConstantLootNumberProvider.create(2))    //Number of rolls
                .conditionally(RandomChanceLootCondition.builder(0.4f)) //Chance to drop 0.0-1.0
                .with(ItemEntry.builder(ModItems.COBALT_SHARD))
                .with(ItemEntry.builder(ModItems.RUBY))
                .with(ItemEntry.builder(ModItems.SAPPHIRE))
                .with(ItemEntry.builder(ModItems.STEEL_INGOT))
                .with(ItemEntry.builder(ModItems.TUNGSTEN_INGOT))
                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)).build()); //Range of items that will drop
        tablebuilder.pool(poolBuilder.build());

        //BASIC INGREDIENTS, UNCOMMON (MULTIPLE ROLLS, SINGLE ITEM PER STACK)
        poolBuilder = LootPool.builder()
                .rolls(ConstantLootNumberProvider.create(2))    //Number of rolls
                .conditionally(RandomChanceLootCondition.builder(0.3f)) //Chance to drop 0.0-1.0
                .with(ItemEntry.builder(ModItems.BRONZE_INGOT))
                .with(ItemEntry.builder(ModItems.PHOSPHATE_POWDER))
                .with(ItemEntry.builder(ModItems.TIN_INGOT))
                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 2.0f)).build()); //Range of items that will drop
        tablebuilder.pool(poolBuilder.build());
    }

    private static void ruinedPortalBuilder(LootTable.Builder tablebuilder) {
        //BASIC, RARE
        LootPool.Builder poolBuilder = LootPool.builder()
                .rolls(ConstantLootNumberProvider.create(1))    //Number of rolls
                .conditionally(RandomChanceLootCondition.builder(0.2f)) //Chance to drop 0.0-1.0
                .with(ItemEntry.builder(ModItems.BASIC_UPGRADE_TEMPLATE))
                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)).build()); //Range of items that will drop
        tablebuilder.pool(poolBuilder.build());

        //UPGRADE INGREDIENTS, UNCOMMON (MULTIPLE ROLLS, SINGLE ITEM PER STACK)
        poolBuilder = LootPool.builder()
                .rolls(ConstantLootNumberProvider.create(2))    //Number of rolls
                .conditionally(RandomChanceLootCondition.builder(0.3f)) //Chance to drop 0.0-1.0
                .with(ItemEntry.builder(ModItems.COBALT_SHARD))
                .with(ItemEntry.builder(ModItems.RUBY))
                .with(ItemEntry.builder(ModItems.SAPPHIRE))
                .with(ItemEntry.builder(ModItems.STEEL_INGOT))
                .with(ItemEntry.builder(ModItems.TUNGSTEN_INGOT))
                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)).build()); //Range of items that will drop
        tablebuilder.pool(poolBuilder.build());

        //BASIC INGREDIENTS, UNCOMMON (MULTIPLE ROLLS, SINGLE ITEM PER STACK)
        poolBuilder = LootPool.builder()
                .rolls(ConstantLootNumberProvider.create(2))    //Number of rolls
                .conditionally(RandomChanceLootCondition.builder(0.3f)) //Chance to drop 0.0-1.0
                .with(ItemEntry.builder(ModItems.BRONZE_INGOT))
                .with(ItemEntry.builder(ModItems.PHOSPHATE_POWDER))
                .with(ItemEntry.builder(ModItems.TIN_INGOT))
                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 2.0f)).build()); //Range of items that will drop
        tablebuilder.pool(poolBuilder.build());
    }

    private static void shipwreckSupplyBuilder(LootTable.Builder tablebuilder) {
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
                .with(ItemEntry.builder(ModItems.COBALT_SHARD))
                .with(ItemEntry.builder(ModItems.RUBY))
                .with(ItemEntry.builder(ModItems.SAPPHIRE))
                .with(ItemEntry.builder(ModItems.STEEL_INGOT))
                .with(ItemEntry.builder(ModItems.TUNGSTEN_INGOT))
                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)).build()); //Range of items that will drop
        tablebuilder.pool(poolBuilder.build());

        //BASIC INGREDIENTS, COMMON (MULTIPLE ROLLS, SINGLE ITEM PER STACK)
        poolBuilder = LootPool.builder()
                .rolls(ConstantLootNumberProvider.create(2))    //Number of rolls
                .conditionally(RandomChanceLootCondition.builder(0.4f)) //Chance to drop 0.0-1.0
                .with(ItemEntry.builder(ModItems.BRONZE_INGOT))
                .with(ItemEntry.builder(ModItems.PHOSPHATE_POWDER))
                .with(ItemEntry.builder(ModItems.TIN_INGOT))
                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 2.0f)).build()); //Range of items that will drop
        tablebuilder.pool(poolBuilder.build());
    }

    private static void shipwreckTreasureBuilder(LootTable.Builder tablebuilder) {
        //BASIC, RARE
        LootPool.Builder poolBuilder = LootPool.builder()
                .rolls(ConstantLootNumberProvider.create(1))    //Number of rolls
                .conditionally(RandomChanceLootCondition.builder(0.2f)) //Chance to drop 0.0-1.0
                .with(ItemEntry.builder(ModItems.BASIC_UPGRADE_TEMPLATE))
                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)).build()); //Range of items that will drop
        tablebuilder.pool(poolBuilder.build());

        //UPGRADE INGREDIENTS, UNCOMMON (MULTIPLE ROLLS, SINGLE ITEM PER STACK)
        poolBuilder = LootPool.builder()
                .rolls(ConstantLootNumberProvider.create(2))    //Number of rolls
                .conditionally(RandomChanceLootCondition.builder(0.3f)) //Chance to drop 0.0-1.0
                .with(ItemEntry.builder(ModItems.COBALT_SHARD))
                .with(ItemEntry.builder(ModItems.RUBY))
                .with(ItemEntry.builder(ModItems.SAPPHIRE))
                .with(ItemEntry.builder(ModItems.STEEL_INGOT))
                .with(ItemEntry.builder(ModItems.TUNGSTEN_INGOT))
                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)).build()); //Range of items that will drop
        tablebuilder.pool(poolBuilder.build());

        //BASIC INGREDIENTS, UNCOMMON (MULTIPLE ROLLS, SINGLE ITEM PER STACK)
        poolBuilder = LootPool.builder()
                .rolls(ConstantLootNumberProvider.create(2))    //Number of rolls
                .conditionally(RandomChanceLootCondition.builder(0.3f)) //Chance to drop 0.0-1.0
                .with(ItemEntry.builder(ModItems.BRONZE_INGOT))
                .with(ItemEntry.builder(ModItems.PHOSPHATE_POWDER))
                .with(ItemEntry.builder(ModItems.TIN_INGOT))
                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 2.0f)).build()); //Range of items that will drop
        tablebuilder.pool(poolBuilder.build());
    }

    private static void simpleDungeonBuilder(LootTable.Builder tablebuilder) {
        //BASIC, RARE
        LootPool.Builder poolBuilder = LootPool.builder()
                .rolls(ConstantLootNumberProvider.create(1))    //Number of rolls
                .conditionally(RandomChanceLootCondition.builder(0.2f)) //Chance to drop 0.0-1.0
                .with(ItemEntry.builder(ModItems.BASIC_UPGRADE_TEMPLATE))
                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)).build()); //Range of items that will drop
        tablebuilder.pool(poolBuilder.build());

        //FINISHED, (INCLUDE NETHERITE TEMPLATE HERE TOO) VERY RARE
        poolBuilder = LootPool.builder()
                .rolls(ConstantLootNumberProvider.create(1))    //Number of rolls
                .conditionally(RandomChanceLootCondition.builder(0.1f)) //Chance to drop 0.0-1.0
                .with(ItemEntry.builder(ModItems.COBALT_UPGRADE_TEMPLATE))
                .with(ItemEntry.builder(ModItems.INFUSION_UPGRADE_TEMPLATE))
                .with(ItemEntry.builder(ModItems.CARBIDE_UPGRADE_TEMPLATE))
                .with(ItemEntry.builder(Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE))
                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)).build()); //Range of items that will drop
        tablebuilder.pool(poolBuilder.build());

        //UPGRADE INGREDIENTS, UNCOMMON (MULTIPLE ROLLS, SINGLE ITEM PER STACK)
        poolBuilder = LootPool.builder()
                .rolls(ConstantLootNumberProvider.create(2))    //Number of rolls
                .conditionally(RandomChanceLootCondition.builder(0.3f)) //Chance to drop 0.0-1.0
                .with(ItemEntry.builder(ModItems.COBALT_SHARD))
                .with(ItemEntry.builder(ModItems.RUBY))
                .with(ItemEntry.builder(ModItems.SAPPHIRE))
                .with(ItemEntry.builder(ModItems.STEEL_INGOT))
                .with(ItemEntry.builder(ModItems.TUNGSTEN_INGOT))
                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)).build()); //Range of items that will drop
        tablebuilder.pool(poolBuilder.build());

        //BASIC INGREDIENTS, UNCOMMON (MULTIPLE ROLLS, SINGLE ITEM PER STACK)
        poolBuilder = LootPool.builder()
                .rolls(ConstantLootNumberProvider.create(2))    //Number of rolls
                .conditionally(RandomChanceLootCondition.builder(0.3f)) //Chance to drop 0.0-1.0
                .with(ItemEntry.builder(ModItems.BRONZE_INGOT))
                .with(ItemEntry.builder(ModItems.PHOSPHATE_POWDER))
                .with(ItemEntry.builder(ModItems.TIN_INGOT))
                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 2.0f)).build()); //Range of items that will drop
        tablebuilder.pool(poolBuilder.build());
    }

    private static void strongholdCorridorBuilder(LootTable.Builder tablebuilder) {
        //BASIC, UNCOMMON
        LootPool.Builder poolBuilder = LootPool.builder()
                .rolls(ConstantLootNumberProvider.create(1))    //Number of rolls
                .conditionally(RandomChanceLootCondition.builder(0.3f)) //Chance to drop 0.0-1.0
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

        //UPGRADE INGREDIENTS, UNCOMMON (MULTIPLE ROLLS, SINGLE ITEM PER STACK)
        poolBuilder = LootPool.builder()
                .rolls(ConstantLootNumberProvider.create(2))    //Number of rolls
                .conditionally(RandomChanceLootCondition.builder(0.3f)) //Chance to drop 0.0-1.0
                .with(ItemEntry.builder(ModItems.COBALT_SHARD))
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
                .conditionally(RandomChanceLootCondition.builder(0.3f)) //Chance to drop 0.0-1.0
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

        //UPGRADE INGREDIENTS, UNCOMMON (MULTIPLE ROLLS, SINGLE ITEM PER STACK)
        poolBuilder = LootPool.builder()
                .rolls(ConstantLootNumberProvider.create(2))    //Number of rolls
                .conditionally(RandomChanceLootCondition.builder(0.3f)) //Chance to drop 0.0-1.0
                .with(ItemEntry.builder(ModItems.COBALT_SHARD))
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
                .conditionally(RandomChanceLootCondition.builder(0.3f)) //Chance to drop 0.0-1.0
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

        //UPGRADE INGREDIENTS, UNCOMMON (MULTIPLE ROLLS, SINGLE ITEM PER STACK)
        poolBuilder = LootPool.builder()
                .rolls(ConstantLootNumberProvider.create(2))    //Number of rolls
                .conditionally(RandomChanceLootCondition.builder(0.3f)) //Chance to drop 0.0-1.0
                .with(ItemEntry.builder(ModItems.COBALT_SHARD))
                .with(ItemEntry.builder(ModItems.RUBY))
                .with(ItemEntry.builder(ModItems.SAPPHIRE))
                .with(ItemEntry.builder(ModItems.STEEL_INGOT))
                .with(ItemEntry.builder(ModItems.TUNGSTEN_INGOT))
                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)).build()); //Range of items that will drop
        tablebuilder.pool(poolBuilder.build());
    }

    private static void underwaterRuinBigBuilder(LootTable.Builder tablebuilder) {
        //BASIC, RARE
        LootPool.Builder poolBuilder = LootPool.builder()
                .rolls(ConstantLootNumberProvider.create(1))    //Number of rolls
                .conditionally(RandomChanceLootCondition.builder(0.2f)) //Chance to drop 0.0-1.0
                .with(ItemEntry.builder(ModItems.BASIC_UPGRADE_TEMPLATE))
                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)).build()); //Range of items that will drop
        tablebuilder.pool(poolBuilder.build());

        //UPGRADE INGREDIENTS, UNCOMMON (MULTIPLE ROLLS, SINGLE ITEM PER STACK)
        poolBuilder = LootPool.builder()
                .rolls(ConstantLootNumberProvider.create(2))    //Number of rolls
                .conditionally(RandomChanceLootCondition.builder(0.3f)) //Chance to drop 0.0-1.0
                .with(ItemEntry.builder(ModItems.COBALT_SHARD))
                .with(ItemEntry.builder(ModItems.RUBY))
                .with(ItemEntry.builder(ModItems.SAPPHIRE))
                .with(ItemEntry.builder(ModItems.STEEL_INGOT))
                .with(ItemEntry.builder(ModItems.TUNGSTEN_INGOT))
                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)).build()); //Range of items that will drop
        tablebuilder.pool(poolBuilder.build());

        //BASIC INGREDIENTS, UNCOMMON (MULTIPLE ROLLS, SINGLE ITEM PER STACK)
        poolBuilder = LootPool.builder()
                .rolls(ConstantLootNumberProvider.create(2))    //Number of rolls
                .conditionally(RandomChanceLootCondition.builder(0.3f)) //Chance to drop 0.0-1.0
                .with(ItemEntry.builder(ModItems.BRONZE_INGOT))
                .with(ItemEntry.builder(ModItems.PHOSPHATE_POWDER))
                .with(ItemEntry.builder(ModItems.TIN_INGOT))
                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 2.0f)).build()); //Range of items that will drop
        tablebuilder.pool(poolBuilder.build());
    }

    private static void underwaterRuinSmallBuilder(LootTable.Builder tablebuilder) {
        //BASIC, RARE
        LootPool.Builder poolBuilder = LootPool.builder()
                .rolls(ConstantLootNumberProvider.create(1))    //Number of rolls
                .conditionally(RandomChanceLootCondition.builder(0.2f)) //Chance to drop 0.0-1.0
                .with(ItemEntry.builder(ModItems.BASIC_UPGRADE_TEMPLATE))
                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)).build()); //Range of items that will drop
        tablebuilder.pool(poolBuilder.build());

        //UPGRADE INGREDIENTS, UNCOMMON (MULTIPLE ROLLS, SINGLE ITEM PER STACK)
        poolBuilder = LootPool.builder()
                .rolls(ConstantLootNumberProvider.create(2))    //Number of rolls
                .conditionally(RandomChanceLootCondition.builder(0.3f)) //Chance to drop 0.0-1.0
                .with(ItemEntry.builder(ModItems.COBALT_SHARD))
                .with(ItemEntry.builder(ModItems.RUBY))
                .with(ItemEntry.builder(ModItems.SAPPHIRE))
                .with(ItemEntry.builder(ModItems.STEEL_INGOT))
                .with(ItemEntry.builder(ModItems.TUNGSTEN_INGOT))
                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)).build()); //Range of items that will drop
        tablebuilder.pool(poolBuilder.build());

        //BASIC INGREDIENTS, UNCOMMON (MULTIPLE ROLLS, SINGLE ITEM PER STACK)
        poolBuilder = LootPool.builder()
                .rolls(ConstantLootNumberProvider.create(2))    //Number of rolls
                .conditionally(RandomChanceLootCondition.builder(0.3f)) //Chance to drop 0.0-1.0
                .with(ItemEntry.builder(ModItems.BRONZE_INGOT))
                .with(ItemEntry.builder(ModItems.PHOSPHATE_POWDER))
                .with(ItemEntry.builder(ModItems.TIN_INGOT))
                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 2.0f)).build()); //Range of items that will drop
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
                .with(ItemEntry.builder(ModItems.COBALT_SHARD))
                .with(ItemEntry.builder(ModItems.RUBY))
                .with(ItemEntry.builder(ModItems.SAPPHIRE))
                .with(ItemEntry.builder(ModItems.STEEL_INGOT))
                .with(ItemEntry.builder(ModItems.TUNGSTEN_INGOT))
                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)).build()); //Range of items that will drop
        tablebuilder.pool(poolBuilder.build());

        //BASIC INGREDIENTS, COMMON (MULTIPLE ROLLS, SINGLE ITEM PER STACK)
        poolBuilder = LootPool.builder()
                .rolls(ConstantLootNumberProvider.create(2))    //Number of rolls
                .conditionally(RandomChanceLootCondition.builder(0.4f)) //Chance to drop 0.0-1.0
                .with(ItemEntry.builder(ModItems.BRONZE_INGOT))
                .with(ItemEntry.builder(ModItems.PHOSPHATE_POWDER))
                .with(ItemEntry.builder(ModItems.TIN_INGOT))
                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 2.0f)).build()); //Range of items that will drop
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
                .with(ItemEntry.builder(ModItems.COBALT_SHARD))
                .with(ItemEntry.builder(ModItems.RUBY))
                .with(ItemEntry.builder(ModItems.SAPPHIRE))
                .with(ItemEntry.builder(ModItems.STEEL_INGOT))
                .with(ItemEntry.builder(ModItems.TUNGSTEN_INGOT))
                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)).build()); //Range of items that will drop
        tablebuilder.pool(poolBuilder.build());

        //BASIC INGREDIENTS, COMMON (MULTIPLE ROLLS, SINGLE ITEM PER STACK)
        poolBuilder = LootPool.builder()
                .rolls(ConstantLootNumberProvider.create(2))    //Number of rolls
                .conditionally(RandomChanceLootCondition.builder(0.4f)) //Chance to drop 0.0-1.0
                .with(ItemEntry.builder(ModItems.BRONZE_INGOT))
                .with(ItemEntry.builder(ModItems.PHOSPHATE_POWDER))
                .with(ItemEntry.builder(ModItems.TIN_INGOT))
                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 2.0f)).build()); //Range of items that will drop
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
                .with(ItemEntry.builder(ModItems.COBALT_SHARD))
                .with(ItemEntry.builder(ModItems.RUBY))
                .with(ItemEntry.builder(ModItems.SAPPHIRE))
                .with(ItemEntry.builder(ModItems.STEEL_INGOT))
                .with(ItemEntry.builder(ModItems.TUNGSTEN_INGOT))
                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)).build()); //Range of items that will drop
        tablebuilder.pool(poolBuilder.build());

        //BASIC INGREDIENTS, COMMON (MULTIPLE ROLLS, SINGLE ITEM PER STACK)
        poolBuilder = LootPool.builder()
                .rolls(ConstantLootNumberProvider.create(2))    //Number of rolls
                .conditionally(RandomChanceLootCondition.builder(0.4f)) //Chance to drop 0.0-1.0
                .with(ItemEntry.builder(ModItems.BRONZE_INGOT))
                .with(ItemEntry.builder(ModItems.PHOSPHATE_POWDER))
                .with(ItemEntry.builder(ModItems.TIN_INGOT))
                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 2.0f)).build()); //Range of items that will drop
        tablebuilder.pool(poolBuilder.build());
    }

    private static void woodlandMansionBuilder(LootTable.Builder tablebuilder) {
        //BASIC, UNCOMMON
        LootPool.Builder poolBuilder = LootPool.builder()
                .rolls(ConstantLootNumberProvider.create(1))    //Number of rolls
                .conditionally(RandomChanceLootCondition.builder(0.3f)) //Chance to drop 0.0-1.0
                .with(ItemEntry.builder(ModItems.BASIC_UPGRADE_TEMPLATE))
                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)).build()); //Range of items that will drop
        tablebuilder.pool(poolBuilder.build());

        //FINISHED (INCLUDE NETHERITE TEMPLATE HERE TOO), COMMON
        poolBuilder = LootPool.builder()
                .rolls(ConstantLootNumberProvider.create(1))    //Number of rolls
                .conditionally(RandomChanceLootCondition.builder(0.4f)) //Chance to drop 0.0-1.0
                .with(ItemEntry.builder(ModItems.COBALT_UPGRADE_TEMPLATE))
                .with(ItemEntry.builder(ModItems.INFUSION_UPGRADE_TEMPLATE))
                .with(ItemEntry.builder(ModItems.CARBIDE_UPGRADE_TEMPLATE))
                .with(ItemEntry.builder(Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE))
                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)).build()); //Range of items that will drop
        tablebuilder.pool(poolBuilder.build());

        //UPGRADE INGREDIENTS, UNCOMMON (MULTIPLE ROLLS, SINGLE ITEM PER STACK)
        poolBuilder = LootPool.builder()
                .rolls(ConstantLootNumberProvider.create(2))    //Number of rolls
                .conditionally(RandomChanceLootCondition.builder(0.3f)) //Chance to drop 0.0-1.0
                .with(ItemEntry.builder(ModItems.COBALT_SHARD))
                .with(ItemEntry.builder(ModItems.RUBY))
                .with(ItemEntry.builder(ModItems.SAPPHIRE))
                .with(ItemEntry.builder(ModItems.STEEL_INGOT))
                .with(ItemEntry.builder(ModItems.TUNGSTEN_INGOT))
                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)).build()); //Range of items that will drop
        tablebuilder.pool(poolBuilder.build());
    }
}
