package net.dollar.simplegear.item;

import net.dollar.simplegear.ModMain;
import net.dollar.simplegear.block.ModBlocks;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.MilkBucketItem;
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
                        entries.add(ModItems.RUBY);
                        entries.add(ModItems.SAPPHIRE);

                        //Endgame upgrade items
                        entries.add(ModItems.HANDFUL_OF_STARDUST);
                        entries.add(ModItems.COMPOUND_GEMSTONE);
                        entries.add(ModItems.INFUSED_GEMSTONE);
                        entries.add(ModItems.MOLTEN_CORE);
                        entries.add(ModItems.COBALT_STEEL_INGOT);
                        entries.add(ModItems.TUNGSTEN_CARBIDE_INGOT);

                        //Collector items
                        entries.add(ModItems.KATHLEENS_LOST_DIADEM);

                        //Bows/Crossbows
                        entries.add(ModItems.INFUSED_GEMSTONE_BOW);
                        entries.add(ModItems.INFUSED_GEMSTONE_CROSSBOW);
                        entries.add(ModItems.COBALT_STEEL_BOW);
                        entries.add(ModItems.COBALT_STEEL_CROSSBOW);
                        entries.add(ModItems.TUNGSTEN_CARBIDE_BOW);
                        entries.add(ModItems.TUNGSTEN_CARBIDE_CROSSBOW);

                        //Pickaxes
                        entries.add(ModItems.INFUSED_GEMSTONE_PICKAXE);
                        entries.add(ModItems.COBALT_STEEL_PICKAXE);
                        entries.add(ModItems.TUNGSTEN_CARBIDE_PICKAXE);



                        entries.add(ModBlocks.SPECTRAL_LANTERN);
                    }).build());


    /**
     * Handles registering custom item group(s).
     */
    public static void registerItemGroups() {
        ModMain.LOGGER.info("Registering Item Groups for " + ModMain.MOD_ID);
    }
}
