package net.dollar.apex.util;

import net.dollar.apex.item.ModItems;
import net.fabricmc.fabric.api.loot.v3.LootTableEvents;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;

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
        LootTableEvents.MODIFY.register((id, tablebuilder, source, registries) -> {
            //ROLLS -> NUMBER OF UNIQUE ROLLS (WHICH CAN EACH GENERATE ITEMS)
            //CONDITIONALLY -> CHANCE TO DROP IN RANGE OF 0.0 - 1.0
            //WITH -> THE ITEMS THAT MAY GENERATE
            //APPLY:CREATE -> NUMBER OF ITEMS THAT WILL GENERATE PER ROLL

            if (BuiltInLootTables.ABANDONED_MINESHAFT.equals(id)) abandonedMineshaftBuilder(tablebuilder);

            if (BuiltInLootTables.ANCIENT_CITY.equals(id)) ancientCityBuilder(tablebuilder);

            if (BuiltInLootTables.BASTION_BRIDGE.equals(id)) bastionBridgeBuilder(tablebuilder);

            if (BuiltInLootTables.BASTION_HOGLIN_STABLE.equals(id)) bastionHoglinStableBuilder(tablebuilder);

            if (BuiltInLootTables.BASTION_OTHER.equals(id)) bastionOtherBuilder(tablebuilder);

            if (BuiltInLootTables.BASTION_TREASURE.equals(id)) bastionTreasureBuilder(tablebuilder);

            if (BuiltInLootTables.BURIED_TREASURE.equals(id)) buriedTreasureBuilder(tablebuilder);

            if (BuiltInLootTables.DESERT_PYRAMID.equals(id)) desertPyramidBuilder(tablebuilder);

            if (BuiltInLootTables.END_CITY_TREASURE.equals(id)) endCityTreasureBuilder(tablebuilder);

            if (BuiltInLootTables.IGLOO_CHEST.equals(id)) iglooChestBuilder(tablebuilder);

            if (BuiltInLootTables.JUNGLE_TEMPLE.equals(id)) jungleTempleBuilder(tablebuilder);

            if (BuiltInLootTables.NETHER_BRIDGE.equals(id)) netherBridgeBuilder(tablebuilder);

            if (BuiltInLootTables.PILLAGER_OUTPOST.equals(id)) pillagerOutpostBuilder(tablebuilder);

            if (BuiltInLootTables.RUINED_PORTAL.equals(id)) ruinedPortalBuilder(tablebuilder);

            if (BuiltInLootTables.SHIPWRECK_SUPPLY.equals(id)) shipwreckSupplyBuilder(tablebuilder);

            if (BuiltInLootTables.SHIPWRECK_TREASURE.equals(id)) shipwreckTreasureBuilder(tablebuilder);

            if (BuiltInLootTables.SIMPLE_DUNGEON.equals(id)) simpleDungeonBuilder(tablebuilder);

            if (BuiltInLootTables.STRONGHOLD_CORRIDOR.equals(id)) strongholdCorridorBuilder(tablebuilder);

            if (BuiltInLootTables.STRONGHOLD_CROSSING.equals(id)) strongholdCrossingBuilder(tablebuilder);

            if (BuiltInLootTables.STRONGHOLD_LIBRARY.equals(id)) strongholdLibraryBuilder(tablebuilder);

            if (BuiltInLootTables.UNDERWATER_RUIN_BIG.equals(id)) underwaterRuinBigBuilder(tablebuilder);

            if (BuiltInLootTables.UNDERWATER_RUIN_BIG.equals(id)) underwaterRuinSmallBuilder(tablebuilder);

            if (BuiltInLootTables.VILLAGE_ARMORER.equals(id)) villageArmorerBuilder(tablebuilder);

            if (BuiltInLootTables.VILLAGE_TOOLSMITH.equals(id)) villageToolsmithBuilder(tablebuilder);

            if (BuiltInLootTables.VILLAGE_WEAPONSMITH.equals(id)) villageWeaponsmithBuilder(tablebuilder);

            if (BuiltInLootTables.WOODLAND_MANSION.equals(id)) woodlandMansionBuilder(tablebuilder);
        });
    }



    static void abandonedMineshaftBuilder(LootTable.Builder builder) {
        //BASIC TEMPLATE, RARE
        LootPool.Builder poolBuilder = LootPool.lootPool()
                .setRolls(ConstantValue.exactly(1))    //Number of rolls
                .when(LootItemRandomChanceCondition.randomChance(0.2f)) //Chance to drop 0.0-1.0
                .add(LootItem.lootTableItem(ModItems.BASIC_UPGRADE_TEMPLATE))
                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0f, 1.0f)).build()); //Range of items that will drop PER ROLL
        builder.pool(poolBuilder.build());

        //FINISHED TEMPLATES, VERY RARE
        poolBuilder = LootPool.lootPool()
                .setRolls(ConstantValue.exactly(1))    //Number of rolls
                .when(LootItemRandomChanceCondition.randomChance(0.1f)) //Chance to drop 0.0-1.0
                .add(LootItem.lootTableItem(ModItems.COBALT_UPGRADE_TEMPLATE))
                .add(LootItem.lootTableItem(ModItems.INFUSION_UPGRADE_TEMPLATE))
                .add(LootItem.lootTableItem(ModItems.CARBIDE_UPGRADE_TEMPLATE))
                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0f, 1.0f)).build()); //Range of items that will drop
        builder.pool(poolBuilder.build());

        //UPGRADE INGREDIENTS, COMMON (MULTIPLE ROLLS, SINGLE ITEM PER STACK)
        poolBuilder = LootPool.lootPool()
                .setRolls(ConstantValue.exactly(2))    //Number of rolls
                .when(LootItemRandomChanceCondition.randomChance(0.4f)) //Chance to drop 0.0-1.0
                .add(LootItem.lootTableItem(ModItems.COBALT_SHARD))
                .add(LootItem.lootTableItem(ModItems.RUBY))
                .add(LootItem.lootTableItem(ModItems.SAPPHIRE))
                .add(LootItem.lootTableItem(ModItems.STEEL_INGOT))
                .add(LootItem.lootTableItem(ModItems.TUNGSTEN_INGOT))
                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0f, 1.0f)).build()); //Range of items that will drop
        builder.pool(poolBuilder.build());

        //BASIC INGREDIENTS, COMMON (MULTIPLE ROLLS, SINGLE ITEM PER STACK)
        poolBuilder = LootPool.lootPool()
                .setRolls(ConstantValue.exactly(2))    //Number of rolls
                .when(LootItemRandomChanceCondition.randomChance(0.4f)) //Chance to drop 0.0-1.0
                .add(LootItem.lootTableItem(ModItems.BRONZE_INGOT))
                .add(LootItem.lootTableItem(ModItems.PHOSPHATE_POWDER))
                .add(LootItem.lootTableItem(ModItems.TIN_INGOT))
                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0f, 2.0f)).build()); //Range of items that will drop
        builder.pool(poolBuilder.build());
    }

    static void ancientCityBuilder(LootTable.Builder builder) {
        //BASIC, UNCOMMON
        LootPool.Builder poolBuilder = LootPool.lootPool()
                .setRolls(ConstantValue.exactly(1))    //Number of rolls
                .when(LootItemRandomChanceCondition.randomChance(0.3f)) //Chance to drop 0.0-1.0
                .add(LootItem.lootTableItem(ModItems.BASIC_UPGRADE_TEMPLATE))
                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0f, 1.0f)).build()); //Range of items that will drop
        builder.pool(poolBuilder.build());

        //FINISHED (INCLUDE NETHERITE TEMPLATE HERE TOO), COMMON
        poolBuilder = LootPool.lootPool()
                .setRolls(ConstantValue.exactly(1))    //Number of rolls
                .when(LootItemRandomChanceCondition.randomChance(0.4f)) //Chance to drop 0.0-1.0
                .add(LootItem.lootTableItem(ModItems.COBALT_UPGRADE_TEMPLATE))
                .add(LootItem.lootTableItem(ModItems.INFUSION_UPGRADE_TEMPLATE))
                .add(LootItem.lootTableItem(ModItems.CARBIDE_UPGRADE_TEMPLATE))
                .add(LootItem.lootTableItem(Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE))
                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0f, 1.0f)).build()); //Range of items that will drop
        builder.pool(poolBuilder.build());

        //UPGRADE INGREDIENTS, RARE (MULTIPLE ROLLS, SINGLE ITEM PER STACK)
        poolBuilder = LootPool.lootPool()
                .setRolls(ConstantValue.exactly(2))    //Number of rolls
                .when(LootItemRandomChanceCondition.randomChance(0.2f)) //Chance to drop 0.0-1.0
                .add(LootItem.lootTableItem(ModItems.COBALT_SHARD))
                .add(LootItem.lootTableItem(ModItems.RUBY))
                .add(LootItem.lootTableItem(ModItems.SAPPHIRE))
                .add(LootItem.lootTableItem(ModItems.STEEL_INGOT))
                .add(LootItem.lootTableItem(ModItems.TUNGSTEN_INGOT))
                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0f, 1.0f)).build()); //Range of items that will drop
        builder.pool(poolBuilder.build());
    }

    static void bastionBridgeBuilder(LootTable.Builder builder) {
        //BASIC, RARE
        LootPool.Builder poolBuilder = LootPool.lootPool()
                .setRolls(ConstantValue.exactly(1))    //Number of rolls
                .when(LootItemRandomChanceCondition.randomChance(0.2f)) //Chance to drop 0.0-1.0
                .add(LootItem.lootTableItem(ModItems.BASIC_UPGRADE_TEMPLATE))
                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0f, 1.0f)).build()); //Range of items that will drop
        builder.pool(poolBuilder.build());

        //FINISHED, UNCOMMON
        poolBuilder = LootPool.lootPool()
                .setRolls(ConstantValue.exactly(1))    //Number of rolls
                .when(LootItemRandomChanceCondition.randomChance(0.3f)) //Chance to drop 0.0-1.0
                .add(LootItem.lootTableItem(ModItems.COBALT_UPGRADE_TEMPLATE))
                .add(LootItem.lootTableItem(ModItems.INFUSION_UPGRADE_TEMPLATE))
                .add(LootItem.lootTableItem(ModItems.CARBIDE_UPGRADE_TEMPLATE))
                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0f, 1.0f)).build()); //Range of items that will drop
        builder.pool(poolBuilder.build());
    }

    static void bastionHoglinStableBuilder(LootTable.Builder builder) {
        //BASIC, RARE
        LootPool.Builder poolBuilder = LootPool.lootPool()
                .setRolls(ConstantValue.exactly(1))    //Number of rolls
                .when(LootItemRandomChanceCondition.randomChance(0.2f)) //Chance to drop 0.0-1.0
                .add(LootItem.lootTableItem(ModItems.BASIC_UPGRADE_TEMPLATE))
                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0f, 1.0f)).build()); //Range of items that will drop
        builder.pool(poolBuilder.build());
    }

    static void bastionOtherBuilder(LootTable.Builder builder) {
        //BASIC, RARE
        LootPool.Builder poolBuilder = LootPool.lootPool()
                .setRolls(ConstantValue.exactly(1))    //Number of rolls
                .when(LootItemRandomChanceCondition.randomChance(0.2f)) //Chance to drop 0.0-1.0
                .add(LootItem.lootTableItem(ModItems.BASIC_UPGRADE_TEMPLATE))
                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0f, 1.0f)).build()); //Range of items that will drop
        builder.pool(poolBuilder.build());

        //FINISHED, UNCOMMON
        poolBuilder = LootPool.lootPool()
                .setRolls(ConstantValue.exactly(1))    //Number of rolls
                .when(LootItemRandomChanceCondition.randomChance(0.3f)) //Chance to drop 0.0-1.0
                .add(LootItem.lootTableItem(ModItems.COBALT_UPGRADE_TEMPLATE))
                .add(LootItem.lootTableItem(ModItems.INFUSION_UPGRADE_TEMPLATE))
                .add(LootItem.lootTableItem(ModItems.CARBIDE_UPGRADE_TEMPLATE))
                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0f, 1.0f)).build()); //Range of items that will drop
        builder.pool(poolBuilder.build());
    }

    static void bastionTreasureBuilder(LootTable.Builder builder) {
        //BASIC, UNCOMMON
        LootPool.Builder poolBuilder = LootPool.lootPool()
                .setRolls(ConstantValue.exactly(1))    //Number of rolls
                .when(LootItemRandomChanceCondition.randomChance(0.3f)) //Chance to drop 0.0-1.0
                .add(LootItem.lootTableItem(ModItems.BASIC_UPGRADE_TEMPLATE))
                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0f, 1.0f)).build()); //Range of items that will drop
        builder.pool(poolBuilder.build());

        //FINISHED, COMMON
        poolBuilder = LootPool.lootPool()
                .setRolls(ConstantValue.exactly(1))    //Number of rolls
                .when(LootItemRandomChanceCondition.randomChance(0.4f)) //Chance to drop 0.0-1.0
                .add(LootItem.lootTableItem(ModItems.COBALT_UPGRADE_TEMPLATE))
                .add(LootItem.lootTableItem(ModItems.INFUSION_UPGRADE_TEMPLATE))
                .add(LootItem.lootTableItem(ModItems.CARBIDE_UPGRADE_TEMPLATE))
                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0f, 1.0f)).build()); //Range of items that will drop
        builder.pool(poolBuilder.build());
    }

    static void buriedTreasureBuilder(LootTable.Builder builder) {
        //BASIC, RARE
        LootPool.Builder poolBuilder = LootPool.lootPool()
                .setRolls(ConstantValue.exactly(1))    //Number of rolls
                .when(LootItemRandomChanceCondition.randomChance(0.2f)) //Chance to drop 0.0-1.0
                .add(LootItem.lootTableItem(ModItems.BASIC_UPGRADE_TEMPLATE))
                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0f, 1.0f)).build()); //Range of items that will drop
        builder.pool(poolBuilder.build());

        //UPGRADE INGREDIENTS, UNCOMMON (MULTIPLE ROLLS, SINGLE ITEM PER STACK)
        poolBuilder = LootPool.lootPool()
                .setRolls(ConstantValue.exactly(2))    //Number of rolls
                .when(LootItemRandomChanceCondition.randomChance(0.3f)) //Chance to drop 0.0-1.0
                .add(LootItem.lootTableItem(ModItems.COBALT_SHARD))
                .add(LootItem.lootTableItem(ModItems.RUBY))
                .add(LootItem.lootTableItem(ModItems.SAPPHIRE))
                .add(LootItem.lootTableItem(ModItems.STEEL_INGOT))
                .add(LootItem.lootTableItem(ModItems.TUNGSTEN_INGOT))
                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0f, 1.0f)).build()); //Range of items that will drop
        builder.pool(poolBuilder.build());

        //BASIC INGREDIENTS, UNCOMMON (MULTIPLE ROLLS, SINGLE ITEM PER STACK)
        poolBuilder = LootPool.lootPool()
                .setRolls(ConstantValue.exactly(2))    //Number of rolls
                .when(LootItemRandomChanceCondition.randomChance(0.3f)) //Chance to drop 0.0-1.0
                .add(LootItem.lootTableItem(ModItems.BRONZE_INGOT))
                .add(LootItem.lootTableItem(ModItems.PHOSPHATE_POWDER))
                .add(LootItem.lootTableItem(ModItems.TIN_INGOT))
                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0f, 2.0f)).build()); //Range of items that will drop
        builder.pool(poolBuilder.build());
    }

    private static void desertPyramidBuilder(LootTable.Builder tablebuilder) {
        //BASIC, RARE (REMEMBER: 4 chests)
        LootPool.Builder poolBuilder = LootPool.lootPool()
                .setRolls(ConstantValue.exactly(1))    //Number of rolls
                .when(LootItemRandomChanceCondition.randomChance(0.2f)) //Chance to drop 0.0-1.0
                .add(LootItem.lootTableItem(ModItems.BASIC_UPGRADE_TEMPLATE))
                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0f, 1.0f)).build()); //Range of items that will drop
        tablebuilder.pool(poolBuilder.build());

        //UPGRADE INGREDIENTS, RARE (MULTIPLE ROLLS, SINGLE ITEM PER STACK)
        poolBuilder = LootPool.lootPool()
                .setRolls(ConstantValue.exactly(2))    //Number of rolls
                .when(LootItemRandomChanceCondition.randomChance(0.2f)) //Chance to drop 0.0-1.0
                .add(LootItem.lootTableItem(ModItems.RUBY))
                .add(LootItem.lootTableItem(ModItems.SAPPHIRE))
                .add(LootItem.lootTableItem(ModItems.STEEL_INGOT))
                .add(LootItem.lootTableItem(ModItems.TUNGSTEN_INGOT))
                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0f, 1.0f)).build()); //Range of items that will drop
        tablebuilder.pool(poolBuilder.build());

        //BASIC INGREDIENTS, UNCOMMON (MULTIPLE ROLLS, SINGLE ITEM PER STACK) (REMEMBER: 4 chests)
        poolBuilder = LootPool.lootPool()
                .setRolls(ConstantValue.exactly(2))    //Number of rolls
                .when(LootItemRandomChanceCondition.randomChance(0.3f)) //Chance to drop 0.0-1.0
                .add(LootItem.lootTableItem(ModItems.BRONZE_INGOT))
                .add(LootItem.lootTableItem(ModItems.PHOSPHATE_POWDER))
                .add(LootItem.lootTableItem(ModItems.TIN_INGOT))
                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0f, 2.0f)).build()); //Range of items that will drop
        tablebuilder.pool(poolBuilder.build());
    }

    private static void endCityTreasureBuilder(LootTable.Builder tablebuilder) {
        //BASIC, UNCOMMON
        LootPool.Builder poolBuilder = LootPool.lootPool()
                .setRolls(ConstantValue.exactly(1))    //Number of rolls
                .when(LootItemRandomChanceCondition.randomChance(0.3f)) //Chance to drop 0.0-1.0
                .add(LootItem.lootTableItem(ModItems.BASIC_UPGRADE_TEMPLATE))
                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0f, 1.0f)).build()); //Range of items that will drop
        tablebuilder.pool(poolBuilder.build());

        //FINISHED (INCLUDE NETHERITE TEMPLATE HERE TOO), RARE
        poolBuilder = LootPool.lootPool()
                .setRolls(ConstantValue.exactly(1))    //Number of rolls
                .when(LootItemRandomChanceCondition.randomChance(0.2f)) //Chance to drop 0.0-1.0
                .add(LootItem.lootTableItem(ModItems.COBALT_UPGRADE_TEMPLATE))
                .add(LootItem.lootTableItem(ModItems.INFUSION_UPGRADE_TEMPLATE))
                .add(LootItem.lootTableItem(ModItems.CARBIDE_UPGRADE_TEMPLATE))
                .add(LootItem.lootTableItem(Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE))
                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0f, 1.0f)).build()); //Range of items that will drop
        tablebuilder.pool(poolBuilder.build());

        //UPGRADE INGREDIENTS, RARE (MULTIPLE ROLLS, SINGLE ITEM PER STACK)
        poolBuilder = LootPool.lootPool()
                .setRolls(ConstantValue.exactly(2))    //Number of rolls
                .when(LootItemRandomChanceCondition.randomChance(0.2f)) //Chance to drop 0.0-1.0
                .add(LootItem.lootTableItem(ModItems.COBALT_SHARD))
                .add(LootItem.lootTableItem(ModItems.RUBY))
                .add(LootItem.lootTableItem(ModItems.SAPPHIRE))
                .add(LootItem.lootTableItem(ModItems.STEEL_INGOT))
                .add(LootItem.lootTableItem(ModItems.TUNGSTEN_INGOT))
                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0f, 1.0f)).build()); //Range of items that will drop
        tablebuilder.pool(poolBuilder.build());
    }

    private static void iglooChestBuilder(LootTable.Builder tablebuilder) {
        //BASIC, VERY RARE
        LootPool.Builder poolBuilder = LootPool.lootPool()
                .setRolls(ConstantValue.exactly(1))    //Number of rolls
                .when(LootItemRandomChanceCondition.randomChance(0.1f)) //Chance to drop 0.0-1.0
                .add(LootItem.lootTableItem(ModItems.BASIC_UPGRADE_TEMPLATE))
                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0f, 1.0f)).build()); //Range of items that will drop
        tablebuilder.pool(poolBuilder.build());

        //UPGRADE INGREDIENTS, RARE (MULTIPLE ROLLS, SINGLE ITEM PER STACK)
        poolBuilder = LootPool.lootPool()
                .setRolls(ConstantValue.exactly(2))    //Number of rolls
                .when(LootItemRandomChanceCondition.randomChance(0.2f)) //Chance to drop 0.0-1.0
                .add(LootItem.lootTableItem(ModItems.COBALT_SHARD))
                .add(LootItem.lootTableItem(ModItems.RUBY))
                .add(LootItem.lootTableItem(ModItems.SAPPHIRE))
                .add(LootItem.lootTableItem(ModItems.STEEL_INGOT))
                .add(LootItem.lootTableItem(ModItems.TUNGSTEN_INGOT))
                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0f, 1.0f)).build()); //Range of items that will drop
        tablebuilder.pool(poolBuilder.build());

        //BASIC INGREDIENTS, UNCOMMON (MULTIPLE ROLLS, SINGLE ITEM PER STACK)
        poolBuilder = LootPool.lootPool()
                .setRolls(ConstantValue.exactly(2))    //Number of rolls
                .when(LootItemRandomChanceCondition.randomChance(0.3f)) //Chance to drop 0.0-1.0
                .add(LootItem.lootTableItem(ModItems.BRONZE_INGOT))
                .add(LootItem.lootTableItem(ModItems.PHOSPHATE_POWDER))
                .add(LootItem.lootTableItem(ModItems.TIN_INGOT))
                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0f, 2.0f)).build()); //Range of items that will drop
        tablebuilder.pool(poolBuilder.build());
    }

    private static void jungleTempleBuilder(LootTable.Builder tablebuilder) {
        //BASIC, RARE
        LootPool.Builder poolBuilder = LootPool.lootPool()
                .setRolls(ConstantValue.exactly(1))    //Number of rolls
                .when(LootItemRandomChanceCondition.randomChance(0.2f)) //Chance to drop 0.0-1.0
                .add(LootItem.lootTableItem(ModItems.BASIC_UPGRADE_TEMPLATE))
                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0f, 1.0f)).build()); //Range of items that will drop
        tablebuilder.pool(poolBuilder.build());

        //UPGRADE INGREDIENTS, UNCOMMON (MULTIPLE ROLLS, SINGLE ITEM PER STACK)
        poolBuilder = LootPool.lootPool()
                .setRolls(ConstantValue.exactly(2))    //Number of rolls
                .when(LootItemRandomChanceCondition.randomChance(0.3f)) //Chance to drop 0.0-1.0
                .add(LootItem.lootTableItem(ModItems.COBALT_SHARD))
                .add(LootItem.lootTableItem(ModItems.RUBY))
                .add(LootItem.lootTableItem(ModItems.SAPPHIRE))
                .add(LootItem.lootTableItem(ModItems.STEEL_INGOT))
                .add(LootItem.lootTableItem(ModItems.TUNGSTEN_INGOT))
                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0f, 1.0f)).build()); //Range of items that will drop
        tablebuilder.pool(poolBuilder.build());

        //BASIC INGREDIENTS, COMMON (MULTIPLE ROLLS, SINGLE ITEM PER STACK)
        poolBuilder = LootPool.lootPool()
                .setRolls(ConstantValue.exactly(2))    //Number of rolls
                .when(LootItemRandomChanceCondition.randomChance(0.4f)) //Chance to drop 0.0-1.0
                .add(LootItem.lootTableItem(ModItems.BRONZE_INGOT))
                .add(LootItem.lootTableItem(ModItems.PHOSPHATE_POWDER))
                .add(LootItem.lootTableItem(ModItems.TIN_INGOT))
                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0f, 2.0f)).build()); //Range of items that will drop
        tablebuilder.pool(poolBuilder.build());
    }

    private static void netherBridgeBuilder(LootTable.Builder tablebuilder) {
        //BASIC, UNCOMMON
        LootPool.Builder poolBuilder = LootPool.lootPool()
                .setRolls(ConstantValue.exactly(1))    //Number of rolls
                .when(LootItemRandomChanceCondition.randomChance(0.3f)) //Chance to drop 0.0-1.0
                .add(LootItem.lootTableItem(ModItems.BASIC_UPGRADE_TEMPLATE))
                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0f, 1.0f)).build()); //Range of items that will drop
        tablebuilder.pool(poolBuilder.build());

        //FINISHED (INCLUDE NETHERITE TEMPLATE HERE TOO), VERY RARE
        poolBuilder = LootPool.lootPool()
                .setRolls(ConstantValue.exactly(1))    //Number of rolls
                .when(LootItemRandomChanceCondition.randomChance(0.1f)) //Chance to drop 0.0-1.0
                .add(LootItem.lootTableItem(ModItems.COBALT_UPGRADE_TEMPLATE))
                .add(LootItem.lootTableItem(ModItems.INFUSION_UPGRADE_TEMPLATE))
                .add(LootItem.lootTableItem(ModItems.CARBIDE_UPGRADE_TEMPLATE))
                .add(LootItem.lootTableItem(Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE))
                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0f, 1.0f)).build()); //Range of items that will drop
        tablebuilder.pool(poolBuilder.build());
    }

    private static void pillagerOutpostBuilder(LootTable.Builder tablebuilder) {
        //BASIC, UNCOMMON
        LootPool.Builder poolBuilder = LootPool.lootPool()
                .setRolls(ConstantValue.exactly(1))    //Number of rolls
                .when(LootItemRandomChanceCondition.randomChance(0.3f)) //Chance to drop 0.0-1.0
                .add(LootItem.lootTableItem(ModItems.BASIC_UPGRADE_TEMPLATE))
                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0f, 1.0f)).build()); //Range of items that will drop
        tablebuilder.pool(poolBuilder.build());

        //UPGRADE INGREDIENTS, COMMON (MULTIPLE ROLLS, SINGLE ITEM PER STACK)
        poolBuilder = LootPool.lootPool()
                .setRolls(ConstantValue.exactly(2))    //Number of rolls
                .when(LootItemRandomChanceCondition.randomChance(0.4f)) //Chance to drop 0.0-1.0
                .add(LootItem.lootTableItem(ModItems.COBALT_SHARD))
                .add(LootItem.lootTableItem(ModItems.RUBY))
                .add(LootItem.lootTableItem(ModItems.SAPPHIRE))
                .add(LootItem.lootTableItem(ModItems.STEEL_INGOT))
                .add(LootItem.lootTableItem(ModItems.TUNGSTEN_INGOT))
                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0f, 1.0f)).build()); //Range of items that will drop
        tablebuilder.pool(poolBuilder.build());

        //BASIC INGREDIENTS, UNCOMMON (MULTIPLE ROLLS, SINGLE ITEM PER STACK)
        poolBuilder = LootPool.lootPool()
                .setRolls(ConstantValue.exactly(2))    //Number of rolls
                .when(LootItemRandomChanceCondition.randomChance(0.3f)) //Chance to drop 0.0-1.0
                .add(LootItem.lootTableItem(ModItems.BRONZE_INGOT))
                .add(LootItem.lootTableItem(ModItems.PHOSPHATE_POWDER))
                .add(LootItem.lootTableItem(ModItems.TIN_INGOT))
                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0f, 2.0f)).build()); //Range of items that will drop
        tablebuilder.pool(poolBuilder.build());
    }

    private static void ruinedPortalBuilder(LootTable.Builder tablebuilder) {
        //BASIC, RARE
        LootPool.Builder poolBuilder = LootPool.lootPool()
                .setRolls(ConstantValue.exactly(1))    //Number of rolls
                .when(LootItemRandomChanceCondition.randomChance(0.2f)) //Chance to drop 0.0-1.0
                .add(LootItem.lootTableItem(ModItems.BASIC_UPGRADE_TEMPLATE))
                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0f, 1.0f)).build()); //Range of items that will drop
        tablebuilder.pool(poolBuilder.build());

        //UPGRADE INGREDIENTS, UNCOMMON (MULTIPLE ROLLS, SINGLE ITEM PER STACK)
        poolBuilder = LootPool.lootPool()
                .setRolls(ConstantValue.exactly(2))    //Number of rolls
                .when(LootItemRandomChanceCondition.randomChance(0.3f)) //Chance to drop 0.0-1.0
                .add(LootItem.lootTableItem(ModItems.COBALT_SHARD))
                .add(LootItem.lootTableItem(ModItems.RUBY))
                .add(LootItem.lootTableItem(ModItems.SAPPHIRE))
                .add(LootItem.lootTableItem(ModItems.STEEL_INGOT))
                .add(LootItem.lootTableItem(ModItems.TUNGSTEN_INGOT))
                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0f, 1.0f)).build()); //Range of items that will drop
        tablebuilder.pool(poolBuilder.build());

        //BASIC INGREDIENTS, UNCOMMON (MULTIPLE ROLLS, SINGLE ITEM PER STACK)
        poolBuilder = LootPool.lootPool()
                .setRolls(ConstantValue.exactly(2))    //Number of rolls
                .when(LootItemRandomChanceCondition.randomChance(0.3f)) //Chance to drop 0.0-1.0
                .add(LootItem.lootTableItem(ModItems.BRONZE_INGOT))
                .add(LootItem.lootTableItem(ModItems.PHOSPHATE_POWDER))
                .add(LootItem.lootTableItem(ModItems.TIN_INGOT))
                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0f, 2.0f)).build()); //Range of items that will drop
        tablebuilder.pool(poolBuilder.build());
    }

    private static void shipwreckSupplyBuilder(LootTable.Builder tablebuilder) {
        //BASIC, VERY RARE
        LootPool.Builder poolBuilder = LootPool.lootPool()
                .setRolls(ConstantValue.exactly(1))    //Number of rolls
                .when(LootItemRandomChanceCondition.randomChance(0.1f)) //Chance to drop 0.0-1.0
                .add(LootItem.lootTableItem(ModItems.BASIC_UPGRADE_TEMPLATE))
                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0f, 1.0f)).build()); //Range of items that will drop
        tablebuilder.pool(poolBuilder.build());

        //UPGRADE INGREDIENTS, RARE (MULTIPLE ROLLS, SINGLE ITEM PER STACK)
        poolBuilder = LootPool.lootPool()
                .setRolls(ConstantValue.exactly(2))    //Number of rolls
                .when(LootItemRandomChanceCondition.randomChance(0.2f)) //Chance to drop 0.0-1.0
                .add(LootItem.lootTableItem(ModItems.COBALT_SHARD))
                .add(LootItem.lootTableItem(ModItems.RUBY))
                .add(LootItem.lootTableItem(ModItems.SAPPHIRE))
                .add(LootItem.lootTableItem(ModItems.STEEL_INGOT))
                .add(LootItem.lootTableItem(ModItems.TUNGSTEN_INGOT))
                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0f, 1.0f)).build()); //Range of items that will drop
        tablebuilder.pool(poolBuilder.build());

        //BASIC INGREDIENTS, COMMON (MULTIPLE ROLLS, SINGLE ITEM PER STACK)
        poolBuilder = LootPool.lootPool()
                .setRolls(ConstantValue.exactly(2))    //Number of rolls
                .when(LootItemRandomChanceCondition.randomChance(0.4f)) //Chance to drop 0.0-1.0
                .add(LootItem.lootTableItem(ModItems.BRONZE_INGOT))
                .add(LootItem.lootTableItem(ModItems.PHOSPHATE_POWDER))
                .add(LootItem.lootTableItem(ModItems.TIN_INGOT))
                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0f, 2.0f)).build()); //Range of items that will drop
        tablebuilder.pool(poolBuilder.build());
    }

    private static void shipwreckTreasureBuilder(LootTable.Builder tablebuilder) {
        //BASIC, RARE
        LootPool.Builder poolBuilder = LootPool.lootPool()
                .setRolls(ConstantValue.exactly(1))    //Number of rolls
                .when(LootItemRandomChanceCondition.randomChance(0.2f)) //Chance to drop 0.0-1.0
                .add(LootItem.lootTableItem(ModItems.BASIC_UPGRADE_TEMPLATE))
                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0f, 1.0f)).build()); //Range of items that will drop
        tablebuilder.pool(poolBuilder.build());

        //UPGRADE INGREDIENTS, UNCOMMON (MULTIPLE ROLLS, SINGLE ITEM PER STACK)
        poolBuilder = LootPool.lootPool()
                .setRolls(ConstantValue.exactly(2))    //Number of rolls
                .when(LootItemRandomChanceCondition.randomChance(0.3f)) //Chance to drop 0.0-1.0
                .add(LootItem.lootTableItem(ModItems.COBALT_SHARD))
                .add(LootItem.lootTableItem(ModItems.RUBY))
                .add(LootItem.lootTableItem(ModItems.SAPPHIRE))
                .add(LootItem.lootTableItem(ModItems.STEEL_INGOT))
                .add(LootItem.lootTableItem(ModItems.TUNGSTEN_INGOT))
                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0f, 1.0f)).build()); //Range of items that will drop
        tablebuilder.pool(poolBuilder.build());

        //BASIC INGREDIENTS, UNCOMMON (MULTIPLE ROLLS, SINGLE ITEM PER STACK)
        poolBuilder = LootPool.lootPool()
                .setRolls(ConstantValue.exactly(2))    //Number of rolls
                .when(LootItemRandomChanceCondition.randomChance(0.3f)) //Chance to drop 0.0-1.0
                .add(LootItem.lootTableItem(ModItems.BRONZE_INGOT))
                .add(LootItem.lootTableItem(ModItems.PHOSPHATE_POWDER))
                .add(LootItem.lootTableItem(ModItems.TIN_INGOT))
                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0f, 2.0f)).build()); //Range of items that will drop
        tablebuilder.pool(poolBuilder.build());
    }

    private static void simpleDungeonBuilder(LootTable.Builder tablebuilder) {
        //BASIC, RARE
        LootPool.Builder poolBuilder = LootPool.lootPool()
                .setRolls(ConstantValue.exactly(1))    //Number of rolls
                .when(LootItemRandomChanceCondition.randomChance(0.2f)) //Chance to drop 0.0-1.0
                .add(LootItem.lootTableItem(ModItems.BASIC_UPGRADE_TEMPLATE))
                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0f, 1.0f)).build()); //Range of items that will drop
        tablebuilder.pool(poolBuilder.build());

        //FINISHED, (INCLUDE NETHERITE TEMPLATE HERE TOO) VERY RARE
        poolBuilder = LootPool.lootPool()
                .setRolls(ConstantValue.exactly(1))    //Number of rolls
                .when(LootItemRandomChanceCondition.randomChance(0.1f)) //Chance to drop 0.0-1.0
                .add(LootItem.lootTableItem(ModItems.COBALT_UPGRADE_TEMPLATE))
                .add(LootItem.lootTableItem(ModItems.INFUSION_UPGRADE_TEMPLATE))
                .add(LootItem.lootTableItem(ModItems.CARBIDE_UPGRADE_TEMPLATE))
                .add(LootItem.lootTableItem(Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE))
                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0f, 1.0f)).build()); //Range of items that will drop
        tablebuilder.pool(poolBuilder.build());

        //UPGRADE INGREDIENTS, UNCOMMON (MULTIPLE ROLLS, SINGLE ITEM PER STACK)
        poolBuilder = LootPool.lootPool()
                .setRolls(ConstantValue.exactly(2))    //Number of rolls
                .when(LootItemRandomChanceCondition.randomChance(0.3f)) //Chance to drop 0.0-1.0
                .add(LootItem.lootTableItem(ModItems.COBALT_SHARD))
                .add(LootItem.lootTableItem(ModItems.RUBY))
                .add(LootItem.lootTableItem(ModItems.SAPPHIRE))
                .add(LootItem.lootTableItem(ModItems.STEEL_INGOT))
                .add(LootItem.lootTableItem(ModItems.TUNGSTEN_INGOT))
                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0f, 1.0f)).build()); //Range of items that will drop
        tablebuilder.pool(poolBuilder.build());

        //BASIC INGREDIENTS, UNCOMMON (MULTIPLE ROLLS, SINGLE ITEM PER STACK)
        poolBuilder = LootPool.lootPool()
                .setRolls(ConstantValue.exactly(2))    //Number of rolls
                .when(LootItemRandomChanceCondition.randomChance(0.3f)) //Chance to drop 0.0-1.0
                .add(LootItem.lootTableItem(ModItems.BRONZE_INGOT))
                .add(LootItem.lootTableItem(ModItems.PHOSPHATE_POWDER))
                .add(LootItem.lootTableItem(ModItems.TIN_INGOT))
                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0f, 2.0f)).build()); //Range of items that will drop
        tablebuilder.pool(poolBuilder.build());
    }

    private static void strongholdCorridorBuilder(LootTable.Builder tablebuilder) {
        //BASIC, UNCOMMON
        LootPool.Builder poolBuilder = LootPool.lootPool()
                .setRolls(ConstantValue.exactly(1))    //Number of rolls
                .when(LootItemRandomChanceCondition.randomChance(0.3f)) //Chance to drop 0.0-1.0
                .add(LootItem.lootTableItem(ModItems.BASIC_UPGRADE_TEMPLATE))
                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0f, 1.0f)).build()); //Range of items that will drop
        tablebuilder.pool(poolBuilder.build());

        //FINISHED (INCLUDE NETHERITE TEMPLATE HERE TOO), VERY RARE
        poolBuilder = LootPool.lootPool()
                .setRolls(ConstantValue.exactly(1))    //Number of rolls
                .when(LootItemRandomChanceCondition.randomChance(0.1f)) //Chance to drop 0.0-1.0
                .add(LootItem.lootTableItem(ModItems.COBALT_UPGRADE_TEMPLATE))
                .add(LootItem.lootTableItem(ModItems.INFUSION_UPGRADE_TEMPLATE))
                .add(LootItem.lootTableItem(ModItems.CARBIDE_UPGRADE_TEMPLATE))
                .add(LootItem.lootTableItem(Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE))
                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0f, 1.0f)).build()); //Range of items that will drop
        tablebuilder.pool(poolBuilder.build());

        //UPGRADE INGREDIENTS, UNCOMMON (MULTIPLE ROLLS, SINGLE ITEM PER STACK)
        poolBuilder = LootPool.lootPool()
                .setRolls(ConstantValue.exactly(2))    //Number of rolls
                .when(LootItemRandomChanceCondition.randomChance(0.3f)) //Chance to drop 0.0-1.0
                .add(LootItem.lootTableItem(ModItems.COBALT_SHARD))
                .add(LootItem.lootTableItem(ModItems.RUBY))
                .add(LootItem.lootTableItem(ModItems.SAPPHIRE))
                .add(LootItem.lootTableItem(ModItems.STEEL_INGOT))
                .add(LootItem.lootTableItem(ModItems.TUNGSTEN_INGOT))
                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0f, 1.0f)).build()); //Range of items that will drop
        tablebuilder.pool(poolBuilder.build());
    }

    private static void strongholdCrossingBuilder(LootTable.Builder tablebuilder) {
        //BASIC, UNCOMMON
        LootPool.Builder poolBuilder = LootPool.lootPool()
                .setRolls(ConstantValue.exactly(1))    //Number of rolls
                .when(LootItemRandomChanceCondition.randomChance(0.3f)) //Chance to drop 0.0-1.0
                .add(LootItem.lootTableItem(ModItems.BASIC_UPGRADE_TEMPLATE))
                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0f, 1.0f)).build()); //Range of items that will drop
        tablebuilder.pool(poolBuilder.build());

        //FINISHED (INCLUDE NETHERITE TEMPLATE HERE TOO), VERY RARE
        poolBuilder = LootPool.lootPool()
                .setRolls(ConstantValue.exactly(1))    //Number of rolls
                .when(LootItemRandomChanceCondition.randomChance(0.1f)) //Chance to drop 0.0-1.0
                .add(LootItem.lootTableItem(ModItems.COBALT_UPGRADE_TEMPLATE))
                .add(LootItem.lootTableItem(ModItems.INFUSION_UPGRADE_TEMPLATE))
                .add(LootItem.lootTableItem(ModItems.CARBIDE_UPGRADE_TEMPLATE))
                .add(LootItem.lootTableItem(Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE))
                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0f, 1.0f)).build()); //Range of items that will drop
        tablebuilder.pool(poolBuilder.build());

        //UPGRADE INGREDIENTS, UNCOMMON (MULTIPLE ROLLS, SINGLE ITEM PER STACK)
        poolBuilder = LootPool.lootPool()
                .setRolls(ConstantValue.exactly(2))    //Number of rolls
                .when(LootItemRandomChanceCondition.randomChance(0.3f)) //Chance to drop 0.0-1.0
                .add(LootItem.lootTableItem(ModItems.COBALT_SHARD))
                .add(LootItem.lootTableItem(ModItems.RUBY))
                .add(LootItem.lootTableItem(ModItems.SAPPHIRE))
                .add(LootItem.lootTableItem(ModItems.STEEL_INGOT))
                .add(LootItem.lootTableItem(ModItems.TUNGSTEN_INGOT))
                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0f, 1.0f)).build()); //Range of items that will drop
        tablebuilder.pool(poolBuilder.build());
    }

    private static void strongholdLibraryBuilder(LootTable.Builder tablebuilder) {
        //BASIC, UNCOMMON
        LootPool.Builder poolBuilder = LootPool.lootPool()
                .setRolls(ConstantValue.exactly(1))    //Number of rolls
                .when(LootItemRandomChanceCondition.randomChance(0.3f)) //Chance to drop 0.0-1.0
                .add(LootItem.lootTableItem(ModItems.BASIC_UPGRADE_TEMPLATE))
                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0f, 1.0f)).build()); //Range of items that will drop
        tablebuilder.pool(poolBuilder.build());

        //FINISHED (INCLUDE NETHERITE TEMPLATE HERE TOO), VERY RARE
        poolBuilder = LootPool.lootPool()
                .setRolls(ConstantValue.exactly(1))    //Number of rolls
                .when(LootItemRandomChanceCondition.randomChance(0.1f)) //Chance to drop 0.0-1.0
                .add(LootItem.lootTableItem(ModItems.COBALT_UPGRADE_TEMPLATE))
                .add(LootItem.lootTableItem(ModItems.INFUSION_UPGRADE_TEMPLATE))
                .add(LootItem.lootTableItem(ModItems.CARBIDE_UPGRADE_TEMPLATE))
                .add(LootItem.lootTableItem(Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE))
                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0f, 1.0f)).build()); //Range of items that will drop
        tablebuilder.pool(poolBuilder.build());

        //UPGRADE INGREDIENTS, UNCOMMON (MULTIPLE ROLLS, SINGLE ITEM PER STACK)
        poolBuilder = LootPool.lootPool()
                .setRolls(ConstantValue.exactly(2))    //Number of rolls
                .when(LootItemRandomChanceCondition.randomChance(0.3f)) //Chance to drop 0.0-1.0
                .add(LootItem.lootTableItem(ModItems.COBALT_SHARD))
                .add(LootItem.lootTableItem(ModItems.RUBY))
                .add(LootItem.lootTableItem(ModItems.SAPPHIRE))
                .add(LootItem.lootTableItem(ModItems.STEEL_INGOT))
                .add(LootItem.lootTableItem(ModItems.TUNGSTEN_INGOT))
                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0f, 1.0f)).build()); //Range of items that will drop
        tablebuilder.pool(poolBuilder.build());
    }

    private static void underwaterRuinBigBuilder(LootTable.Builder tablebuilder) {
        //BASIC, RARE
        LootPool.Builder poolBuilder = LootPool.lootPool()
                .setRolls(ConstantValue.exactly(1))    //Number of rolls
                .when(LootItemRandomChanceCondition.randomChance(0.2f)) //Chance to drop 0.0-1.0
                .add(LootItem.lootTableItem(ModItems.BASIC_UPGRADE_TEMPLATE))
                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0f, 1.0f)).build()); //Range of items that will drop
        tablebuilder.pool(poolBuilder.build());

        //UPGRADE INGREDIENTS, UNCOMMON (MULTIPLE ROLLS, SINGLE ITEM PER STACK)
        poolBuilder = LootPool.lootPool()
                .setRolls(ConstantValue.exactly(2))    //Number of rolls
                .when(LootItemRandomChanceCondition.randomChance(0.3f)) //Chance to drop 0.0-1.0
                .add(LootItem.lootTableItem(ModItems.COBALT_SHARD))
                .add(LootItem.lootTableItem(ModItems.RUBY))
                .add(LootItem.lootTableItem(ModItems.SAPPHIRE))
                .add(LootItem.lootTableItem(ModItems.STEEL_INGOT))
                .add(LootItem.lootTableItem(ModItems.TUNGSTEN_INGOT))
                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0f, 1.0f)).build()); //Range of items that will drop
        tablebuilder.pool(poolBuilder.build());

        //BASIC INGREDIENTS, UNCOMMON (MULTIPLE ROLLS, SINGLE ITEM PER STACK)
        poolBuilder = LootPool.lootPool()
                .setRolls(ConstantValue.exactly(2))    //Number of rolls
                .when(LootItemRandomChanceCondition.randomChance(0.3f)) //Chance to drop 0.0-1.0
                .add(LootItem.lootTableItem(ModItems.BRONZE_INGOT))
                .add(LootItem.lootTableItem(ModItems.PHOSPHATE_POWDER))
                .add(LootItem.lootTableItem(ModItems.TIN_INGOT))
                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0f, 2.0f)).build()); //Range of items that will drop
        tablebuilder.pool(poolBuilder.build());
    }

    private static void underwaterRuinSmallBuilder(LootTable.Builder tablebuilder) {
        //BASIC, RARE
        LootPool.Builder poolBuilder = LootPool.lootPool()
                .setRolls(ConstantValue.exactly(1))    //Number of rolls
                .when(LootItemRandomChanceCondition.randomChance(0.2f)) //Chance to drop 0.0-1.0
                .add(LootItem.lootTableItem(ModItems.BASIC_UPGRADE_TEMPLATE))
                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0f, 1.0f)).build()); //Range of items that will drop
        tablebuilder.pool(poolBuilder.build());

        //UPGRADE INGREDIENTS, UNCOMMON (MULTIPLE ROLLS, SINGLE ITEM PER STACK)
        poolBuilder = LootPool.lootPool()
                .setRolls(ConstantValue.exactly(2))    //Number of rolls
                .when(LootItemRandomChanceCondition.randomChance(0.3f)) //Chance to drop 0.0-1.0
                .add(LootItem.lootTableItem(ModItems.COBALT_SHARD))
                .add(LootItem.lootTableItem(ModItems.RUBY))
                .add(LootItem.lootTableItem(ModItems.SAPPHIRE))
                .add(LootItem.lootTableItem(ModItems.STEEL_INGOT))
                .add(LootItem.lootTableItem(ModItems.TUNGSTEN_INGOT))
                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0f, 1.0f)).build()); //Range of items that will drop
        tablebuilder.pool(poolBuilder.build());

        //BASIC INGREDIENTS, UNCOMMON (MULTIPLE ROLLS, SINGLE ITEM PER STACK)
        poolBuilder = LootPool.lootPool()
                .setRolls(ConstantValue.exactly(2))    //Number of rolls
                .when(LootItemRandomChanceCondition.randomChance(0.3f)) //Chance to drop 0.0-1.0
                .add(LootItem.lootTableItem(ModItems.BRONZE_INGOT))
                .add(LootItem.lootTableItem(ModItems.PHOSPHATE_POWDER))
                .add(LootItem.lootTableItem(ModItems.TIN_INGOT))
                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0f, 2.0f)).build()); //Range of items that will drop
        tablebuilder.pool(poolBuilder.build());
    }

    private static void villageArmorerBuilder(LootTable.Builder tablebuilder) {
        //BASIC, VERY RARE
        LootPool.Builder poolBuilder = LootPool.lootPool()
                .setRolls(ConstantValue.exactly(1))    //Number of rolls
                .when(LootItemRandomChanceCondition.randomChance(0.1f)) //Chance to drop 0.0-1.0
                .add(LootItem.lootTableItem(ModItems.BASIC_UPGRADE_TEMPLATE))
                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0f, 1.0f)).build()); //Range of items that will drop
        tablebuilder.pool(poolBuilder.build());

        //UPGRADE INGREDIENTS, RARE (MULTIPLE ROLLS, SINGLE ITEM PER STACK)
        poolBuilder = LootPool.lootPool()
                .setRolls(ConstantValue.exactly(2))    //Number of rolls
                .when(LootItemRandomChanceCondition.randomChance(0.2f)) //Chance to drop 0.0-1.0
                .add(LootItem.lootTableItem(ModItems.COBALT_SHARD))
                .add(LootItem.lootTableItem(ModItems.RUBY))
                .add(LootItem.lootTableItem(ModItems.SAPPHIRE))
                .add(LootItem.lootTableItem(ModItems.STEEL_INGOT))
                .add(LootItem.lootTableItem(ModItems.TUNGSTEN_INGOT))
                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0f, 1.0f)).build()); //Range of items that will drop
        tablebuilder.pool(poolBuilder.build());

        //BASIC INGREDIENTS, COMMON (MULTIPLE ROLLS, SINGLE ITEM PER STACK)
        poolBuilder = LootPool.lootPool()
                .setRolls(ConstantValue.exactly(2))    //Number of rolls
                .when(LootItemRandomChanceCondition.randomChance(0.4f)) //Chance to drop 0.0-1.0
                .add(LootItem.lootTableItem(ModItems.BRONZE_INGOT))
                .add(LootItem.lootTableItem(ModItems.PHOSPHATE_POWDER))
                .add(LootItem.lootTableItem(ModItems.TIN_INGOT))
                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0f, 2.0f)).build()); //Range of items that will drop
        tablebuilder.pool(poolBuilder.build());
    }

    private static void villageToolsmithBuilder(LootTable.Builder tablebuilder) {
        //BASIC, VERY RARE
        LootPool.Builder poolBuilder = LootPool.lootPool()
                .setRolls(ConstantValue.exactly(1))    //Number of rolls
                .when(LootItemRandomChanceCondition.randomChance(0.1f)) //Chance to drop 0.0-1.0
                .add(LootItem.lootTableItem(ModItems.BASIC_UPGRADE_TEMPLATE))
                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0f, 1.0f)).build()); //Range of items that will drop
        tablebuilder.pool(poolBuilder.build());

        //UPGRADE INGREDIENTS, RARE (MULTIPLE ROLLS, SINGLE ITEM PER STACK)
        poolBuilder = LootPool.lootPool()
                .setRolls(ConstantValue.exactly(2))    //Number of rolls
                .when(LootItemRandomChanceCondition.randomChance(0.2f)) //Chance to drop 0.0-1.0
                .add(LootItem.lootTableItem(ModItems.COBALT_SHARD))
                .add(LootItem.lootTableItem(ModItems.RUBY))
                .add(LootItem.lootTableItem(ModItems.SAPPHIRE))
                .add(LootItem.lootTableItem(ModItems.STEEL_INGOT))
                .add(LootItem.lootTableItem(ModItems.TUNGSTEN_INGOT))
                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0f, 1.0f)).build()); //Range of items that will drop
        tablebuilder.pool(poolBuilder.build());

        //BASIC INGREDIENTS, COMMON (MULTIPLE ROLLS, SINGLE ITEM PER STACK)
        poolBuilder = LootPool.lootPool()
                .setRolls(ConstantValue.exactly(2))    //Number of rolls
                .when(LootItemRandomChanceCondition.randomChance(0.4f)) //Chance to drop 0.0-1.0
                .add(LootItem.lootTableItem(ModItems.BRONZE_INGOT))
                .add(LootItem.lootTableItem(ModItems.PHOSPHATE_POWDER))
                .add(LootItem.lootTableItem(ModItems.TIN_INGOT))
                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0f, 2.0f)).build()); //Range of items that will drop
        tablebuilder.pool(poolBuilder.build());
    }

    private static void villageWeaponsmithBuilder(LootTable.Builder tablebuilder) {
        //BASIC, VERY RARE
        LootPool.Builder poolBuilder = LootPool.lootPool()
                .setRolls(ConstantValue.exactly(1))    //Number of rolls
                .when(LootItemRandomChanceCondition.randomChance(0.1f)) //Chance to drop 0.0-1.0
                .add(LootItem.lootTableItem(ModItems.BASIC_UPGRADE_TEMPLATE))
                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0f, 1.0f)).build()); //Range of items that will drop
        tablebuilder.pool(poolBuilder.build());

        //UPGRADE INGREDIENTS, RARE (MULTIPLE ROLLS, SINGLE ITEM PER STACK)
        poolBuilder = LootPool.lootPool()
                .setRolls(ConstantValue.exactly(2))    //Number of rolls
                .when(LootItemRandomChanceCondition.randomChance(0.2f)) //Chance to drop 0.0-1.0
                .add(LootItem.lootTableItem(ModItems.COBALT_SHARD))
                .add(LootItem.lootTableItem(ModItems.RUBY))
                .add(LootItem.lootTableItem(ModItems.SAPPHIRE))
                .add(LootItem.lootTableItem(ModItems.STEEL_INGOT))
                .add(LootItem.lootTableItem(ModItems.TUNGSTEN_INGOT))
                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0f, 1.0f)).build()); //Range of items that will drop
        tablebuilder.pool(poolBuilder.build());

        //BASIC INGREDIENTS, COMMON (MULTIPLE ROLLS, SINGLE ITEM PER STACK)
        poolBuilder = LootPool.lootPool()
                .setRolls(ConstantValue.exactly(2))    //Number of rolls
                .when(LootItemRandomChanceCondition.randomChance(0.4f)) //Chance to drop 0.0-1.0
                .add(LootItem.lootTableItem(ModItems.BRONZE_INGOT))
                .add(LootItem.lootTableItem(ModItems.PHOSPHATE_POWDER))
                .add(LootItem.lootTableItem(ModItems.TIN_INGOT))
                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0f, 2.0f)).build()); //Range of items that will drop
        tablebuilder.pool(poolBuilder.build());
    }

    private static void woodlandMansionBuilder(LootTable.Builder tablebuilder) {
        //BASIC, UNCOMMON
        LootPool.Builder poolBuilder = LootPool.lootPool()
                .setRolls(ConstantValue.exactly(1))    //Number of rolls
                .when(LootItemRandomChanceCondition.randomChance(0.3f)) //Chance to drop 0.0-1.0
                .add(LootItem.lootTableItem(ModItems.BASIC_UPGRADE_TEMPLATE))
                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0f, 1.0f)).build()); //Range of items that will drop
        tablebuilder.pool(poolBuilder.build());

        //FINISHED (INCLUDE NETHERITE TEMPLATE HERE TOO), COMMON
        poolBuilder = LootPool.lootPool()
                .setRolls(ConstantValue.exactly(1))    //Number of rolls
                .when(LootItemRandomChanceCondition.randomChance(0.4f)) //Chance to drop 0.0-1.0
                .add(LootItem.lootTableItem(ModItems.COBALT_UPGRADE_TEMPLATE))
                .add(LootItem.lootTableItem(ModItems.INFUSION_UPGRADE_TEMPLATE))
                .add(LootItem.lootTableItem(ModItems.CARBIDE_UPGRADE_TEMPLATE))
                .add(LootItem.lootTableItem(Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE))
                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0f, 1.0f)).build()); //Range of items that will drop
        tablebuilder.pool(poolBuilder.build());

        //UPGRADE INGREDIENTS, UNCOMMON (MULTIPLE ROLLS, SINGLE ITEM PER STACK)
        poolBuilder = LootPool.lootPool()
                .setRolls(ConstantValue.exactly(2))    //Number of rolls
                .when(LootItemRandomChanceCondition.randomChance(0.3f)) //Chance to drop 0.0-1.0
                .add(LootItem.lootTableItem(ModItems.COBALT_SHARD))
                .add(LootItem.lootTableItem(ModItems.RUBY))
                .add(LootItem.lootTableItem(ModItems.SAPPHIRE))
                .add(LootItem.lootTableItem(ModItems.STEEL_INGOT))
                .add(LootItem.lootTableItem(ModItems.TUNGSTEN_INGOT))
                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0f, 1.0f)).build()); //Range of items that will drop
        tablebuilder.pool(poolBuilder.build());
    }
}
