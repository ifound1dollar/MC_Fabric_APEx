package net.dollar.apex.item;

import net.dollar.apex.ModMain;
import net.dollar.apex.block.ModBlocks;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class ModItemGroups {
    //Contains all items from the mod that should show up in the Simple Gearing Expansion tab.
    public static final CreativeModeTab MAIN_GROUP = Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB,
            Identifier.fromNamespaceAndPath(ModMain.MOD_ID, "main"),
            FabricItemGroup.builder().title(Component.translatable("itemgroup.main"))
                    .icon(() -> new ItemStack(ModItems.INFUSED_GEMSTONE)).displayItems((displayContext, entries) -> {
                        //Ores and Raw Blocks
                        entries.accept(ModBlocks.COBALT_ORE);
                        entries.accept(ModBlocks.DEEPSLATE_COBALT_ORE);
                        entries.accept(ModBlocks.COBALT_BLOCK);
                        entries.accept(ModBlocks.PHOSPHATE_ORE);
                        entries.accept(ModBlocks.DEEPSLATE_PHOSPHATE_ORE);
                        entries.accept(ModBlocks.RUBY_ORE);
                        entries.accept(ModBlocks.DEEPSLATE_RUBY_ORE);
                        entries.accept(ModBlocks.RUBY_BLOCK);
                        entries.accept(ModBlocks.SAPPHIRE_ORE);
                        entries.accept(ModBlocks.DEEPSLATE_SAPPHIRE_ORE);
                        entries.accept(ModBlocks.SAPPHIRE_BLOCK);
                        entries.accept(ModBlocks.TIN_ORE);
                        entries.accept(ModBlocks.DEEPSLATE_TIN_ORE);
                        entries.accept(ModBlocks.RAW_TIN_BLOCK);
                        entries.accept(ModBlocks.TIN_BLOCK);
                        entries.accept(ModBlocks.TUNGSTEN_ORE);
                        entries.accept(ModBlocks.DEEPSLATE_TUNGSTEN_ORE);
                        entries.accept(ModBlocks.RAW_TUNGSTEN_BLOCK);
                        entries.accept(ModBlocks.TUNGSTEN_BLOCK);


                        //Crafted Blocks
                        entries.accept(ModBlocks.DECORATIVE_AMETHYST_BLOCK);
                        entries.accept(ModBlocks.BRONZE_BLOCK);
                        entries.accept(ModBlocks.STEEL_BLOCK);

                        //Misc. Blocks
                        //SOMETHING HERE



                        //Misc. Items
                        entries.accept(ModItems.OBSIDIAN_GOLEM_SPAWN_EGG);
                        entries.accept(ModItems.MYSTERIOUS_SPECTER_SPAWN_EGG);
                        entries.accept(ModItems.FERTILIZER);

                        //Raw Items, Gems, Compounds
                        entries.accept(ModItems.COBALT_SHARD);
                        entries.accept(ModItems.PHOSPHATE_POWDER);
                        entries.accept(ModItems.RUBY);
                        entries.accept(ModItems.SAPPHIRE);
                        entries.accept(ModItems.RAW_TIN);
                        entries.accept(ModItems.TIN_INGOT);
                        entries.accept(ModItems.TIN_NUGGET);
                        entries.accept(ModItems.RAW_TUNGSTEN);
                        entries.accept(ModItems.TUNGSTEN_INGOT);
                        entries.accept(ModItems.TUNGSTEN_NUGGET);
                        entries.accept(ModItems.BRONZE_COMPOUND);
                        entries.accept(ModItems.BRONZE_INGOT);
                        entries.accept(ModItems.BRONZE_NUGGET);
                        entries.accept(ModItems.STEEL_COMPOUND);
                        entries.accept(ModItems.STEEL_INGOT);
                        entries.accept(ModItems.STEEL_NUGGET);

                        //Endgame upgrade items
                        entries.accept(ModItems.HANDFUL_OF_STARDUST);
                        entries.accept(ModItems.MOLTEN_CORE);
                        entries.accept(ModItems.COBALT_STEEL_INGOT);
                        entries.accept(ModItems.INFUSED_GEMSTONE);
                        entries.accept(ModItems.TUNGSTEN_CARBIDE_INGOT);

                        //Upgrade templates
                        entries.accept(ModItems.BASIC_UPGRADE_TEMPLATE);
                        entries.accept(ModItems.COBALT_UPGRADE_TEMPLATE);
                        entries.accept(ModItems.INFUSION_UPGRADE_TEMPLATE);
                        entries.accept(ModItems.CARBIDE_UPGRADE_TEMPLATE);

                        //Bronze equipment
                        entries.accept(ModItems.BRONZE_AXE);
                        entries.accept(ModItems.BRONZE_HOE);
                        entries.accept(ModItems.BRONZE_PICKAXE);
                        entries.accept(ModItems.BRONZE_SHOVEL);
                        entries.accept(ModItems.BRONZE_SWORD);
                        entries.accept(ModItems.BRONZE_HELMET);
                        entries.accept(ModItems.BRONZE_CHESTPLATE);
                        entries.accept(ModItems.BRONZE_LEGGINGS);
                        entries.accept(ModItems.BRONZE_BOOTS);

                        //Gilded Bronze equipment
                        entries.accept(ModItems.GILDED_BRONZE_AXE);
                        entries.accept(ModItems.GILDED_BRONZE_HOE);
                        entries.accept(ModItems.GILDED_BRONZE_PICKAXE);
                        entries.accept(ModItems.GILDED_BRONZE_SHOVEL);
                        entries.accept(ModItems.GILDED_BRONZE_SWORD);
                        entries.accept(ModItems.GILDED_BRONZE_HELMET);
                        entries.accept(ModItems.GILDED_BRONZE_CHESTPLATE);
                        entries.accept(ModItems.GILDED_BRONZE_LEGGINGS);
                        entries.accept(ModItems.GILDED_BRONZE_BOOTS);

                        //Diamond equipment
                        entries.accept(ModItems.DIAMOND_BATTLEAXE);
                        entries.accept(ModItems.DIAMOND_PAXEL);

                        //Netherite equipment
                        entries.accept(ModItems.NETHERITE_BATTLEAXE);
                        entries.accept(ModItems.NETHERITE_PAXEL);

                        //Cobalt-Steel equipment
                        entries.accept(ModItems.COBALT_STEEL_BOW);
                        entries.accept(ModItems.COBALT_STEEL_CROSSBOW);
                        entries.accept(ModItems.COBALT_STEEL_AXE);
                        entries.accept(ModItems.COBALT_STEEL_BATTLEAXE);
                        entries.accept(ModItems.COBALT_STEEL_HOE);
                        entries.accept(ModItems.COBALT_STEEL_PAXEL);
                        entries.accept(ModItems.COBALT_STEEL_PICKAXE);
                        entries.accept(ModItems.COBALT_STEEL_SHOVEL);
                        entries.accept(ModItems.COBALT_STEEL_SWORD);
                        entries.accept(ModItems.COBALT_STEEL_HELMET);
                        entries.accept(ModItems.COBALT_STEEL_CHESTPLATE);
                        entries.accept(ModItems.COBALT_STEEL_LEGGINGS);
                        entries.accept(ModItems.COBALT_STEEL_BOOTS);

                        //Infused Gemstone equipment
                        entries.accept(ModItems.INFUSED_GEMSTONE_BOW);
                        entries.accept(ModItems.INFUSED_GEMSTONE_CROSSBOW);
                        entries.accept(ModItems.INFUSED_GEMSTONE_AXE);
                        entries.accept(ModItems.INFUSED_GEMSTONE_BATTLEAXE);
                        entries.accept(ModItems.INFUSED_GEMSTONE_HOE);
                        entries.accept(ModItems.INFUSED_GEMSTONE_PAXEL);
                        entries.accept(ModItems.INFUSED_GEMSTONE_PICKAXE);
                        entries.accept(ModItems.INFUSED_GEMSTONE_SHOVEL);
                        entries.accept(ModItems.INFUSED_GEMSTONE_SWORD);
                        entries.accept(ModItems.INFUSED_GEMSTONE_HELMET);
                        entries.accept(ModItems.INFUSED_GEMSTONE_CHESTPLATE);
                        entries.accept(ModItems.INFUSED_GEMSTONE_LEGGINGS);
                        entries.accept(ModItems.INFUSED_GEMSTONE_BOOTS);

                        //Tungsten-Carbide equipment
                        entries.accept(ModItems.TUNGSTEN_CARBIDE_BOW);
                        entries.accept(ModItems.TUNGSTEN_CARBIDE_CROSSBOW);
                        entries.accept(ModItems.TUNGSTEN_CARBIDE_AXE);
                        entries.accept(ModItems.TUNGSTEN_CARBIDE_BATTLEAXE);
                        entries.accept(ModItems.TUNGSTEN_CARBIDE_HOE);
                        entries.accept(ModItems.TUNGSTEN_CARBIDE_PAXEL);
                        entries.accept(ModItems.TUNGSTEN_CARBIDE_PICKAXE);
                        entries.accept(ModItems.TUNGSTEN_CARBIDE_SHOVEL);
                        entries.accept(ModItems.TUNGSTEN_CARBIDE_SWORD);
                        entries.accept(ModItems.TUNGSTEN_CARBIDE_HELMET);
                        entries.accept(ModItems.TUNGSTEN_CARBIDE_CHESTPLATE);
                        entries.accept(ModItems.TUNGSTEN_CARBIDE_LEGGINGS);
                        entries.accept(ModItems.TUNGSTEN_CARBIDE_BOOTS);

                        //Trophy items
                        entries.accept(ModItems.TROPHY_OBSIDIAN_DUST);
                        entries.accept(ModItems.TROPHY_OMINOUS_LETTER);
//                        entries.add(ModItems.COLLECTOR_POTION_OF_EVERLASTING_YOUTH);
//                        entries.add(ModItems.COLLECTOR_SLIGHTLY_OVERCOOKED_CHICKEN);
                    }).build());


    /**
     * Handles registering custom item group(s).
     */
    public static void registerItemGroups() {
        ModMain.LOGGER.info("Registering Item Groups for " + ModMain.MOD_ID);
    }
}
