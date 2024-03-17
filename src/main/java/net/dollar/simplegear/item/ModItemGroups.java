package net.dollar.simplegear.item;

import net.dollar.simplegear.ModMain;
import net.dollar.simplegear.block.ModBlocks;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModItemGroups {
    //Contains all items from the mod that should show up in the Simple Gearing Expansion tab.
    public static final ItemGroup MAIN_GROUP = Registry.register(Registries.ITEM_GROUP,
            new Identifier(ModMain.MOD_ID, "main"),
            FabricItemGroup.builder().displayName(Text.translatable("itemgroup.main"))
                    .icon(() -> new ItemStack(ModItems.INFUSED_GEMSTONE)).entries((displayContext, entries) -> {
                        //Raw Items, Gems, Compounds
                        entries.add(ModItems.RUBY);
                        entries.add(ModItems.SAPPHIRE);
                        entries.add(ModItems.COBALT_DUST);
                        entries.add(ModItems.PHOSPHOR_DUST);
                        entries.add(ModItems.RAW_TIN);
                        entries.add(ModItems.TIN_INGOT);
                        entries.add(ModItems.BRONZE_COMPOUND);
                        entries.add(ModItems.BRONZE_INGOT);
                        entries.add(ModItems.STEEL_COMPOUND);
                        entries.add(ModItems.STEEL_INGOT);
                        entries.add(ModItems.RAW_TUNGSTEN);
                        entries.add(ModItems.TUNGSTEN_INGOT);

                        //Endgame upgrade items
                        entries.add(ModItems.MOLTEN_CORE);
                        entries.add(ModItems.HANDFUL_OF_STARDUST);
                        entries.add(ModItems.COMPOUND_GEMSTONE);
                        entries.add(ModItems.INFUSED_GEMSTONE);
                        entries.add(ModItems.COBALT_STEEL_INGOT);
                        entries.add(ModItems.TUNGSTEN_CARBIDE_INGOT);

                        //Bronze equipment
                        entries.add(ModItems.BRONZE_AXE);
                        entries.add(ModItems.BRONZE_HOE);
                        entries.add(ModItems.BRONZE_PICKAXE);
                        entries.add(ModItems.BRONZE_SHOVEL);
                        entries.add(ModItems.BRONZE_SWORD);
                        entries.add(ModItems.BRONZE_HELMET);
                        entries.add(ModItems.BRONZE_CHESTPLATE);
                        entries.add(ModItems.BRONZE_LEGGINGS);
                        entries.add(ModItems.BRONZE_BOOTS);

                        //Gilded Bronze equipment
                        entries.add(ModItems.GILDED_BRONZE_AXE);
                        entries.add(ModItems.GILDED_BRONZE_HOE);
                        entries.add(ModItems.GILDED_BRONZE_PICKAXE);
                        entries.add(ModItems.GILDED_BRONZE_SHOVEL);
                        entries.add(ModItems.GILDED_BRONZE_SWORD);
                        entries.add(ModItems.GILDED_BRONZE_HELMET);
                        entries.add(ModItems.GILDED_BRONZE_CHESTPLATE);
                        entries.add(ModItems.GILDED_BRONZE_LEGGINGS);
                        entries.add(ModItems.GILDED_BRONZE_BOOTS);

                        //Cobalt-Steel equipment
                        entries.add(ModItems.COBALT_STEEL_BOW);
                        entries.add(ModItems.COBALT_STEEL_CROSSBOW);
                        entries.add(ModItems.COBALT_STEEL_AXE);
                        entries.add(ModItems.COBALT_STEEL_HOE);
                        entries.add(ModItems.COBALT_STEEL_PICKAXE);
                        entries.add(ModItems.COBALT_STEEL_SHOVEL);
                        entries.add(ModItems.COBALT_STEEL_SWORD);
                        entries.add(ModItems.COBALT_STEEL_HELMET);
                        entries.add(ModItems.COBALT_STEEL_CHESTPLATE);
                        entries.add(ModItems.COBALT_STEEL_LEGGINGS);
                        entries.add(ModItems.COBALT_STEEL_BOOTS);

                        //Infused Gemstone equipment
                        entries.add(ModItems.INFUSED_GEMSTONE_BOW);
                        entries.add(ModItems.INFUSED_GEMSTONE_CROSSBOW);
                        entries.add(ModItems.INFUSED_GEMSTONE_AXE);
                        entries.add(ModItems.INFUSED_GEMSTONE_HOE);
                        entries.add(ModItems.INFUSED_GEMSTONE_PICKAXE);
                        entries.add(ModItems.INFUSED_GEMSTONE_SHOVEL);
                        entries.add(ModItems.INFUSED_GEMSTONE_SWORD);
                        entries.add(ModItems.INFUSED_GEMSTONE_HELMET);
                        entries.add(ModItems.INFUSED_GEMSTONE_CHESTPLATE);
                        entries.add(ModItems.INFUSED_GEMSTONE_LEGGINGS);
                        entries.add(ModItems.INFUSED_GEMSTONE_BOOTS);

                        //Tungsten-Carbide equipment
                        entries.add(ModItems.TUNGSTEN_CARBIDE_BOW);
                        entries.add(ModItems.TUNGSTEN_CARBIDE_CROSSBOW);
                        entries.add(ModItems.TUNGSTEN_CARBIDE_AXE);
                        entries.add(ModItems.TUNGSTEN_CARBIDE_HOE);
                        entries.add(ModItems.TUNGSTEN_CARBIDE_PICKAXE);
                        entries.add(ModItems.TUNGSTEN_CARBIDE_SHOVEL);
                        entries.add(ModItems.TUNGSTEN_CARBIDE_SWORD);
                        entries.add(ModItems.TUNGSTEN_CARBIDE_HELMET);
                        entries.add(ModItems.TUNGSTEN_CARBIDE_CHESTPLATE);
                        entries.add(ModItems.TUNGSTEN_CARBIDE_LEGGINGS);
                        entries.add(ModItems.TUNGSTEN_CARBIDE_BOOTS);

                        //Collector items
                        entries.add(ModItems.COLLECTOR_OBSIDIAN_DUST);
                        entries.add(ModItems.COLLECTOR_KATHLEENS_LOST_DIADEM);
                        entries.add(ModItems.COLLECTOR_POTION_OF_EVERLASTING_YOUTH);
                        entries.add(ModItems.COLLECTOR_SLIGHTLY_OVERCOOKED_CHICKEN);



                        entries.add(ModBlocks.SPECTRAL_LANTERN);
                    }).build());


    /**
     * Handles registering custom item group(s).
     */
    public static void registerItemGroups() {
        ModMain.LOGGER.info("Registering Item Groups for " + ModMain.MOD_ID);
    }
}
