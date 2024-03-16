package net.dollar.simplegear.item;

import net.dollar.simplegear.ModMain;
import net.dollar.simplegear.item.custom.ModCollectorItem;
import net.dollar.simplegear.item.custom.bow.ModInfusedGemstoneBowItem;
import net.dollar.simplegear.item.custom.bow.ModNetheriteBowItem;
import net.dollar.simplegear.item.custom.bow.ModCobaltSteelBowItem;
import net.dollar.simplegear.item.custom.bow.ModTungstenCarbideBowItem;
import net.dollar.simplegear.item.custom.crossbow.ModInfusedGemstoneCrossbowItem;
import net.dollar.simplegear.item.custom.crossbow.ModNetheriteCrossbowItem;
import net.dollar.simplegear.item.custom.crossbow.ModCobaltSteelCrossbowItem;
import net.dollar.simplegear.item.custom.crossbow.ModTungstenCarbideCrossbowItem;
import net.dollar.simplegear.item.custom.infused.ModInfusedGemstoneItem;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItems {
    public static final Item RUBY = registerItem("ruby", new Item(new FabricItemSettings()));
    public static final Item SAPPHIRE = registerItem("sapphire", new Item(new FabricItemSettings()));
    public static final Item INFUSED_GEMSTONE = registerItem("infused_gemstone", new ModInfusedGemstoneItem(
            new FabricItemSettings()));


    public static final Item MOLTEN_CORE = registerItem("molten_core", new Item(
            new FabricItemSettings().fireproof()));


    //Set maxCount here instead of in the item class.
    public static final Item KATHLEENS_LOST_DIADEM = registerItem("collector_kathleens_lost_diadem",
            new ModCollectorItem(new FabricItemSettings().maxCount(1), "1"));


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
