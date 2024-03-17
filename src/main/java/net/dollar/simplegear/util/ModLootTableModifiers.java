package net.dollar.simplegear.util;

import net.dollar.simplegear.item.ModItems;
import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.condition.RandomChanceLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.function.SetDamageLootFunction;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.util.Identifier;

/**
 * Handles all loot table modification, can be ANY type of loot table (blocks, chests, entities, etc.).
 */
public class ModLootTableModifiers {
    //Declare Identifiers here, then use them below within modifyLootTables().

    //RARE STRUCTURES TO GENERATE LOOT
    private static final Identifier VILLAGE_ARMORER_ID = new Identifier("chests/village/village_armorer");
    private static final Identifier VILLAGE_MASON_ID = new Identifier("chests/village/village_mason");
    private static final Identifier VILLAGE_TOOLSMITH_ID = new Identifier("chests/village/village_toolsmith");
    private static final Identifier VILLAGE_WEAPONSMITH_ID = new Identifier("chests/village/village_weaponsmith");
    private static final Identifier BURIED_TREASURE_ID = new Identifier("chests/buried_treasure");
    private static final Identifier IGLOO_CHEST_ID = new Identifier("chests/igloo_chest");
    private static final Identifier SHIPWRECK_SUPPLY_ID = new Identifier("chests/shipwreck_supply");

    //UNCOMMON STRUCTURES TO GENERATE LOOT
    private static final Identifier BASTION_HOGLIN_STABLE_ID = new Identifier("chests/bastion_hoglin_stable");
    private static final Identifier BASTION_OTHER_ID = new Identifier("chests/bastion_other");
    private static final Identifier DESERT_PYRAMID_ID = new Identifier("chests/desert_pyramid");
    private static final Identifier END_CITY_TREASURE_ID = new Identifier("chests/end_city_treasure");
    private static final Identifier JUNGLE_TEMPLE_ID = new Identifier("chests/jungle_temple");
    private static final Identifier PILLAGER_OUTPOST_ID = new Identifier("chests/pillager_outpost");
    private static final Identifier SHIPWRECK_TREASURE_ID = new Identifier("chests/shipwreck_treasure");
    private static final Identifier STRONGHOLD_CROSSING_ID = new Identifier("chests/stronghold_crossing");
    private static final Identifier STRONGHOLD_LIBRARY_ID = new Identifier("chests/stronghold_library");
    private static final Identifier UNDERWATER_RUIN_BIG_ID = new Identifier("chests/underwater_ruin_big");
    private static final Identifier UNDERWATER_RUIN_SMALL_ID = new Identifier("chests/underwater_ruin_small");

    //COMMON STRUCTURES TO GENERATE LOOT
    private static final Identifier ANCIENT_CITY_ID = new Identifier("chests/ancient_city");
    private static final Identifier ANCIENT_CITY_ICE_BOX_ID = new Identifier("chests/ancient_city_ice_box");
    private static final Identifier ABANDONED_MINESHAFT_ID = new Identifier("chests/abandoned_mineshaft");
    private static final Identifier BASTION_BRIDGE_ID = new Identifier("chests/bastion_bridge");
    private static final Identifier BASTION_TREASURE_ID = new Identifier("chests/bastion_treasure");
    private static final Identifier NETHER_BRIDGE_ID = new Identifier("chests/nether_bridge");
    private static final Identifier RUINED_PORTAL_ID = new Identifier("chests/ruined_portal");
    private static final Identifier SIMPLE_DUNGEON_ID = new Identifier("chests/simple_dungeon");
    private static final Identifier STRONGHOLD_CORRIDOR_ID = new Identifier("chests/stronghold_corridor");
    private static final Identifier WOODLAND_MANSION_ID = new Identifier("chests/woodland_mansion");
    private static final Identifier SPECTRAL_SHRINE_ID = new Identifier("simplegear",
            "chests/spectral_shrine");



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

            if (ABANDONED_MINESHAFT_ID.equals(id)) {
                //BASIC, COMMON
                LootPool.Builder poolBuilder = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(2))    //Number of rolls
                        .conditionally(RandomChanceLootCondition.builder(0.5f)) //Chance to drop 0.0-1.0
                        .with(ItemEntry.builder(ModItems.BASIC_UPGRADE_TEMPLATE))
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)).build()); //Range of items that will drop PER ROLL
                tablebuilder.pool(poolBuilder.build());

                //FINISHED, RARE
                poolBuilder = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1))    //Number of rolls
                        .conditionally(RandomChanceLootCondition.builder(0.5f)) //Chance to drop 0.0-1.0
                        .with(ItemEntry.builder(ModItems.COBALT_UPGRADE_TEMPLATE))
                        .with(ItemEntry.builder(ModItems.INFUSION_UPGRADE_TEMPLATE))
                        .with(ItemEntry.builder(ModItems.CARBIDE_UPGRADE_TEMPLATE))
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)).build()); //Range of items that will drop
                tablebuilder.pool(poolBuilder.build());
            }

            if (ANCIENT_CITY_ID.equals(id)) {
                //BASIC, COMMON
                LootPool.Builder poolBuilder = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(2))    //Number of rolls
                        .conditionally(RandomChanceLootCondition.builder(0.5f)) //Chance to drop 0.0-1.0
                        .with(ItemEntry.builder(ModItems.BASIC_UPGRADE_TEMPLATE))
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)).build()); //Range of items that will drop
                tablebuilder.pool(poolBuilder.build());

                //FINISHED, COMMON
                poolBuilder = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(2))    //Number of rolls
                        .conditionally(RandomChanceLootCondition.builder(0.5f)) //Chance to drop 0.0-1.0
                        .with(ItemEntry.builder(ModItems.COBALT_UPGRADE_TEMPLATE))
                        .with(ItemEntry.builder(ModItems.INFUSION_UPGRADE_TEMPLATE))
                        .with(ItemEntry.builder(ModItems.CARBIDE_UPGRADE_TEMPLATE))
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)).build()); //Range of items that will drop
                tablebuilder.pool(poolBuilder.build());
            }

            if (ANCIENT_CITY_ICE_BOX_ID.equals(id)) {
                //BASIC, COMMON
                LootPool.Builder poolBuilder = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(2))    //Number of rolls
                        .conditionally(RandomChanceLootCondition.builder(0.5f)) //Chance to drop 0.0-1.0
                        .with(ItemEntry.builder(ModItems.BASIC_UPGRADE_TEMPLATE))
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)).build()); //Range of items that will drop
                tablebuilder.pool(poolBuilder.build());

                //FINISHED, RARE
                poolBuilder = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1))    //Number of rolls
                        .conditionally(RandomChanceLootCondition.builder(0.5f)) //Chance to drop 0.0-1.0
                        .with(ItemEntry.builder(ModItems.COBALT_UPGRADE_TEMPLATE))
                        .with(ItemEntry.builder(ModItems.INFUSION_UPGRADE_TEMPLATE))
                        .with(ItemEntry.builder(ModItems.CARBIDE_UPGRADE_TEMPLATE))
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)).build()); //Range of items that will drop
                tablebuilder.pool(poolBuilder.build());
            }

            if (BASTION_BRIDGE_ID.equals(id)) {
                //BASIC, UNCOMMON
                LootPool.Builder poolBuilder = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(2))    //Number of rolls
                        .conditionally(RandomChanceLootCondition.builder(0.4f)) //Chance to drop 0.0-1.0
                        .with(ItemEntry.builder(ModItems.BASIC_UPGRADE_TEMPLATE))
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)).build()); //Range of items that will drop
                tablebuilder.pool(poolBuilder.build());

                //FINISHED, UNCOMMON
                poolBuilder = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(2))    //Number of rolls
                        .conditionally(RandomChanceLootCondition.builder(0.4f)) //Chance to drop 0.0-1.0
                        .with(ItemEntry.builder(ModItems.COBALT_UPGRADE_TEMPLATE))
                        .with(ItemEntry.builder(ModItems.INFUSION_UPGRADE_TEMPLATE))
                        .with(ItemEntry.builder(ModItems.CARBIDE_UPGRADE_TEMPLATE))
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)).build()); //Range of items that will drop
                tablebuilder.pool(poolBuilder.build());
            }

            if (BASTION_OTHER_ID.equals(id)) {
                //BASIC, UNCOMMON
                LootPool.Builder poolBuilder = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(2))    //Number of rolls
                        .conditionally(RandomChanceLootCondition.builder(0.4f)) //Chance to drop 0.0-1.0
                        .with(ItemEntry.builder(ModItems.BASIC_UPGRADE_TEMPLATE))
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)).build()); //Range of items that will drop
                tablebuilder.pool(poolBuilder.build());

                //FINISHED, UNCOMMON
                poolBuilder = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(2))    //Number of rolls
                        .conditionally(RandomChanceLootCondition.builder(0.4f)) //Chance to drop 0.0-1.0
                        .with(ItemEntry.builder(ModItems.COBALT_UPGRADE_TEMPLATE))
                        .with(ItemEntry.builder(ModItems.INFUSION_UPGRADE_TEMPLATE))
                        .with(ItemEntry.builder(ModItems.CARBIDE_UPGRADE_TEMPLATE))
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)).build()); //Range of items that will drop
                tablebuilder.pool(poolBuilder.build());
            }

            if (BASTION_TREASURE_ID.equals(id)) {
                //BASIC, UNCOMMON
                LootPool.Builder poolBuilder = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(2))    //Number of rolls
                        .conditionally(RandomChanceLootCondition.builder(0.4f)) //Chance to drop 0.0-1.0
                        .with(ItemEntry.builder(ModItems.BASIC_UPGRADE_TEMPLATE))
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)).build()); //Range of items that will drop
                tablebuilder.pool(poolBuilder.build());

                //FINISHED, COMMON
                poolBuilder = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(2))    //Number of rolls
                        .conditionally(RandomChanceLootCondition.builder(0.5f)) //Chance to drop 0.0-1.0
                        .with(ItemEntry.builder(ModItems.COBALT_UPGRADE_TEMPLATE))
                        .with(ItemEntry.builder(ModItems.INFUSION_UPGRADE_TEMPLATE))
                        .with(ItemEntry.builder(ModItems.CARBIDE_UPGRADE_TEMPLATE))
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)).build()); //Range of items that will drop
                tablebuilder.pool(poolBuilder.build());
            }

            if (BURIED_TREASURE_ID.equals(id)) {
                //BASIC, UNCOMMON
                LootPool.Builder poolBuilder = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(2))    //Number of rolls
                        .conditionally(RandomChanceLootCondition.builder(0.4f)) //Chance to drop 0.0-1.0
                        .with(ItemEntry.builder(ModItems.BASIC_UPGRADE_TEMPLATE))
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)).build()); //Range of items that will drop
                tablebuilder.pool(poolBuilder.build());

                //FINISHED, RARE
                poolBuilder = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1))    //Number of rolls
                        .conditionally(RandomChanceLootCondition.builder(0.5f)) //Chance to drop 0.0-1.0
                        .with(ItemEntry.builder(ModItems.COBALT_UPGRADE_TEMPLATE))
                        .with(ItemEntry.builder(ModItems.INFUSION_UPGRADE_TEMPLATE))
                        .with(ItemEntry.builder(ModItems.CARBIDE_UPGRADE_TEMPLATE))
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)).build()); //Range of items that will drop
                tablebuilder.pool(poolBuilder.build());
            }

            if (DESERT_PYRAMID_ID.equals(id)) {
                //BASIC, UNCOMMON
                LootPool.Builder poolBuilder = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(2))    //Number of rolls
                        .conditionally(RandomChanceLootCondition.builder(0.4f)) //Chance to drop 0.0-1.0
                        .with(ItemEntry.builder(ModItems.BASIC_UPGRADE_TEMPLATE))
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)).build()); //Range of items that will drop
                tablebuilder.pool(poolBuilder.build());

                //FINISHED, RARE
                poolBuilder = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1))    //Number of rolls
                        .conditionally(RandomChanceLootCondition.builder(0.5f)) //Chance to drop 0.0-1.0
                        .with(ItemEntry.builder(ModItems.COBALT_UPGRADE_TEMPLATE))
                        .with(ItemEntry.builder(ModItems.INFUSION_UPGRADE_TEMPLATE))
                        .with(ItemEntry.builder(ModItems.CARBIDE_UPGRADE_TEMPLATE))
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)).build()); //Range of items that will drop
                tablebuilder.pool(poolBuilder.build());
            }

            if (END_CITY_TREASURE_ID.equals(id)) {
                //BASIC, UNCOMMON
                LootPool.Builder poolBuilder = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(2))    //Number of rolls
                        .conditionally(RandomChanceLootCondition.builder(0.4f)) //Chance to drop 0.0-1.0
                        .with(ItemEntry.builder(ModItems.BASIC_UPGRADE_TEMPLATE))
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)).build()); //Range of items that will drop
                tablebuilder.pool(poolBuilder.build());

                //FINISHED, COMMON
                poolBuilder = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(2))    //Number of rolls
                        .conditionally(RandomChanceLootCondition.builder(0.5f)) //Chance to drop 0.0-1.0
                        .with(ItemEntry.builder(ModItems.COBALT_UPGRADE_TEMPLATE))
                        .with(ItemEntry.builder(ModItems.INFUSION_UPGRADE_TEMPLATE))
                        .with(ItemEntry.builder(ModItems.CARBIDE_UPGRADE_TEMPLATE))
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)).build()); //Range of items that will drop
                tablebuilder.pool(poolBuilder.build());
            }

            if (JUNGLE_TEMPLE_ID.equals(id)) {
                //BASIC, UNCOMMON
                LootPool.Builder poolBuilder = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(2))    //Number of rolls
                        .conditionally(RandomChanceLootCondition.builder(0.4f)) //Chance to drop 0.0-1.0
                        .with(ItemEntry.builder(ModItems.BASIC_UPGRADE_TEMPLATE))
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)).build()); //Range of items that will drop
                tablebuilder.pool(poolBuilder.build());

                //FINISHED, RARE
                poolBuilder = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1))    //Number of rolls
                        .conditionally(RandomChanceLootCondition.builder(0.5f)) //Chance to drop 0.0-1.0
                        .with(ItemEntry.builder(ModItems.COBALT_UPGRADE_TEMPLATE))
                        .with(ItemEntry.builder(ModItems.INFUSION_UPGRADE_TEMPLATE))
                        .with(ItemEntry.builder(ModItems.CARBIDE_UPGRADE_TEMPLATE))
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)).build()); //Range of items that will drop
                tablebuilder.pool(poolBuilder.build());
            }

            if (NETHER_BRIDGE_ID.equals(id)) {
                //BASIC, UNCOMMON
                LootPool.Builder poolBuilder = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(2))    //Number of rolls
                        .conditionally(RandomChanceLootCondition.builder(0.4f)) //Chance to drop 0.0-1.0
                        .with(ItemEntry.builder(ModItems.BASIC_UPGRADE_TEMPLATE))
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)).build()); //Range of items that will drop
                tablebuilder.pool(poolBuilder.build());

                //FINISHED, RARE
                poolBuilder = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1))    //Number of rolls
                        .conditionally(RandomChanceLootCondition.builder(0.5f)) //Chance to drop 0.0-1.0
                        .with(ItemEntry.builder(ModItems.COBALT_UPGRADE_TEMPLATE))
                        .with(ItemEntry.builder(ModItems.INFUSION_UPGRADE_TEMPLATE))
                        .with(ItemEntry.builder(ModItems.CARBIDE_UPGRADE_TEMPLATE))
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)).build()); //Range of items that will drop
                tablebuilder.pool(poolBuilder.build());
            }

            if (PILLAGER_OUTPOST_ID.equals(id)) {
                //BASIC, COMMON
                LootPool.Builder poolBuilder = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(2))    //Number of rolls
                        .conditionally(RandomChanceLootCondition.builder(0.5f)) //Chance to drop 0.0-1.0
                        .with(ItemEntry.builder(ModItems.BASIC_UPGRADE_TEMPLATE))
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)).build()); //Range of items that will drop
                tablebuilder.pool(poolBuilder.build());

                //FINISHED, RARE
                poolBuilder = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1))    //Number of rolls
                        .conditionally(RandomChanceLootCondition.builder(0.5f)) //Chance to drop 0.0-1.0
                        .with(ItemEntry.builder(ModItems.COBALT_UPGRADE_TEMPLATE))
                        .with(ItemEntry.builder(ModItems.INFUSION_UPGRADE_TEMPLATE))
                        .with(ItemEntry.builder(ModItems.CARBIDE_UPGRADE_TEMPLATE))
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)).build()); //Range of items that will drop
                tablebuilder.pool(poolBuilder.build());
            }

            if (RUINED_PORTAL_ID.equals(id)) {
                //BASIC, UNCOMMON
                LootPool.Builder poolBuilder = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(2))    //Number of rolls
                        .conditionally(RandomChanceLootCondition.builder(0.4f)) //Chance to drop 0.0-1.0
                        .with(ItemEntry.builder(ModItems.BASIC_UPGRADE_TEMPLATE))
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)).build()); //Range of items that will drop
                tablebuilder.pool(poolBuilder.build());
            }

            if (SHIPWRECK_TREASURE_ID.equals(id)) {
                //BASIC, UNCOMMON
                LootPool.Builder poolBuilder = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(2))    //Number of rolls
                        .conditionally(RandomChanceLootCondition.builder(0.4f)) //Chance to drop 0.0-1.0
                        .with(ItemEntry.builder(ModItems.BASIC_UPGRADE_TEMPLATE))
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)).build()); //Range of items that will drop
                tablebuilder.pool(poolBuilder.build());
            }

            if (SIMPLE_DUNGEON_ID.equals(id)) {
                //BASIC, UNCOMMON
                LootPool.Builder poolBuilder = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(2))    //Number of rolls
                        .conditionally(RandomChanceLootCondition.builder(0.4f)) //Chance to drop 0.0-1.0
                        .with(ItemEntry.builder(ModItems.BASIC_UPGRADE_TEMPLATE))
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)).build()); //Range of items that will drop
                tablebuilder.pool(poolBuilder.build());

                //FINISHED, UNCOMMON
                poolBuilder = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(2))    //Number of rolls
                        .conditionally(RandomChanceLootCondition.builder(0.4f)) //Chance to drop 0.0-1.0
                        .with(ItemEntry.builder(ModItems.COBALT_UPGRADE_TEMPLATE))
                        .with(ItemEntry.builder(ModItems.INFUSION_UPGRADE_TEMPLATE))
                        .with(ItemEntry.builder(ModItems.CARBIDE_UPGRADE_TEMPLATE))
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)).build()); //Range of items that will drop
                tablebuilder.pool(poolBuilder.build());
            }

            if (STRONGHOLD_CORRIDOR_ID.equals(id)) {
                //BASIC, UNCOMMON
                LootPool.Builder poolBuilder = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(2))    //Number of rolls
                        .conditionally(RandomChanceLootCondition.builder(0.4f)) //Chance to drop 0.0-1.0
                        .with(ItemEntry.builder(ModItems.BASIC_UPGRADE_TEMPLATE))
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)).build()); //Range of items that will drop
                tablebuilder.pool(poolBuilder.build());
            }

            if (STRONGHOLD_CROSSING_ID.equals(id)) {
                //BASIC, UNCOMMON
                LootPool.Builder poolBuilder = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(2))    //Number of rolls
                        .conditionally(RandomChanceLootCondition.builder(0.4f)) //Chance to drop 0.0-1.0
                        .with(ItemEntry.builder(ModItems.BASIC_UPGRADE_TEMPLATE))
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)).build()); //Range of items that will drop
                tablebuilder.pool(poolBuilder.build());
            }

            if (STRONGHOLD_LIBRARY_ID.equals(id)) {
                //BASIC, UNCOMMON
                LootPool.Builder poolBuilder = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(2))    //Number of rolls
                        .conditionally(RandomChanceLootCondition.builder(0.4f)) //Chance to drop 0.0-1.0
                        .with(ItemEntry.builder(ModItems.BASIC_UPGRADE_TEMPLATE))
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)).build()); //Range of items that will drop
                tablebuilder.pool(poolBuilder.build());
            }

            if (UNDERWATER_RUIN_BIG_ID.equals(id)) {
                //BASIC, UNCOMMON
                LootPool.Builder poolBuilder = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(2))    //Number of rolls
                        .conditionally(RandomChanceLootCondition.builder(0.4f)) //Chance to drop 0.0-1.0
                        .with(ItemEntry.builder(ModItems.BASIC_UPGRADE_TEMPLATE))
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)).build()); //Range of items that will drop
                tablebuilder.pool(poolBuilder.build());

                //FINISHED, RARE
                poolBuilder = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1))    //Number of rolls
                        .conditionally(RandomChanceLootCondition.builder(0.5f)) //Chance to drop 0.0-1.0
                        .with(ItemEntry.builder(ModItems.COBALT_UPGRADE_TEMPLATE))
                        .with(ItemEntry.builder(ModItems.INFUSION_UPGRADE_TEMPLATE))
                        .with(ItemEntry.builder(ModItems.CARBIDE_UPGRADE_TEMPLATE))
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)).build()); //Range of items that will drop
                tablebuilder.pool(poolBuilder.build());
            }

            if (UNDERWATER_RUIN_SMALL_ID.equals(id)) {
                //BASIC, UNCOMMON
                LootPool.Builder poolBuilder = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(2))    //Number of rolls
                        .conditionally(RandomChanceLootCondition.builder(0.4f)) //Chance to drop 0.0-1.0
                        .with(ItemEntry.builder(ModItems.BASIC_UPGRADE_TEMPLATE))
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)).build()); //Range of items that will drop
                tablebuilder.pool(poolBuilder.build());

                //FINISHED, RARE
                poolBuilder = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1))    //Number of rolls
                        .conditionally(RandomChanceLootCondition.builder(0.5f)) //Chance to drop 0.0-1.0
                        .with(ItemEntry.builder(ModItems.COBALT_UPGRADE_TEMPLATE))
                        .with(ItemEntry.builder(ModItems.INFUSION_UPGRADE_TEMPLATE))
                        .with(ItemEntry.builder(ModItems.CARBIDE_UPGRADE_TEMPLATE))
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)).build()); //Range of items that will drop
                tablebuilder.pool(poolBuilder.build());
            }

            if (VILLAGE_ARMORER_ID.equals(id)) {
                //BASIC, RARE
                LootPool.Builder poolBuilder = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1))    //Number of rolls
                        .conditionally(RandomChanceLootCondition.builder(0.5f)) //Chance to drop 0.0-1.0
                        .with(ItemEntry.builder(ModItems.BASIC_UPGRADE_TEMPLATE))
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)).build()); //Range of items that will drop
                tablebuilder.pool(poolBuilder.build());
            }

            if (VILLAGE_MASON_ID.equals(id)) {
                //BASIC, RARE
                LootPool.Builder poolBuilder = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1))    //Number of rolls
                        .conditionally(RandomChanceLootCondition.builder(0.5f)) //Chance to drop 0.0-1.0
                        .with(ItemEntry.builder(ModItems.BASIC_UPGRADE_TEMPLATE))
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)).build()); //Range of items that will drop
                tablebuilder.pool(poolBuilder.build());
            }

            if (VILLAGE_TOOLSMITH_ID.equals(id)) {
                //BASIC, RARE
                LootPool.Builder poolBuilder = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1))    //Number of rolls
                        .conditionally(RandomChanceLootCondition.builder(0.5f)) //Chance to drop 0.0-1.0
                        .with(ItemEntry.builder(ModItems.BASIC_UPGRADE_TEMPLATE))
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)).build()); //Range of items that will drop
                tablebuilder.pool(poolBuilder.build());
            }

            if (VILLAGE_WEAPONSMITH_ID.equals(id)) {
                //BASIC, RARE
                LootPool.Builder poolBuilder = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1))    //Number of rolls
                        .conditionally(RandomChanceLootCondition.builder(0.5f)) //Chance to drop 0.0-1.0
                        .with(ItemEntry.builder(ModItems.BASIC_UPGRADE_TEMPLATE))
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)).build()); //Range of items that will drop
                tablebuilder.pool(poolBuilder.build());
            }

            if (WOODLAND_MANSION_ID.equals(id)) {
                //BASIC, UNCOMMON
                LootPool.Builder poolBuilder = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(2))    //Number of rolls
                        .conditionally(RandomChanceLootCondition.builder(0.4f)) //Chance to drop 0.0-1.0
                        .with(ItemEntry.builder(ModItems.BASIC_UPGRADE_TEMPLATE))
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)).build()); //Range of items that will drop
                tablebuilder.pool(poolBuilder.build());

                //FINISHED, COMMON
                poolBuilder = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(2))    //Number of rolls
                        .conditionally(RandomChanceLootCondition.builder(0.5f)) //Chance to drop 0.0-1.0
                        .with(ItemEntry.builder(ModItems.COBALT_UPGRADE_TEMPLATE))
                        .with(ItemEntry.builder(ModItems.INFUSION_UPGRADE_TEMPLATE))
                        .with(ItemEntry.builder(ModItems.CARBIDE_UPGRADE_TEMPLATE))
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)).build()); //Range of items that will drop
                tablebuilder.pool(poolBuilder.build());
            }
        });
    }
}
