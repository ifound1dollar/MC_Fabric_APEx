package net.dollar.apex.datagen;

import net.dollar.apex.block.ModBlocks;
import net.dollar.apex.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.Item;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.ApplyBonusLootFunction;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;

public class ModLootTableProvider extends FabricBlockLootTableProvider {
    public ModLootTableProvider(FabricDataOutput dataOutput) {
        super(dataOutput);
    }



    @Override
    public void generate() {
        addDrop(ModBlocks.DECORATIVE_AMETHYST_BLOCK);
        addDrop(ModBlocks.COBALT_BLOCK);
        addDrop(ModBlocks.RUBY_BLOCK);
        addDrop(ModBlocks.SAPPHIRE_BLOCK);
        addDrop(ModBlocks.TIN_BLOCK);
        addDrop(ModBlocks.RAW_TIN_BLOCK);
        addDrop(ModBlocks.TUNGSTEN_BLOCK);
        addDrop(ModBlocks.RAW_TUNGSTEN_BLOCK);
        addDrop(ModBlocks.BRONZE_BLOCK);
        addDrop(ModBlocks.STEEL_BLOCK);

        addDrop(ModBlocks.COBALT_ORE, oreDrops(ModBlocks.COBALT_ORE, ModItems.COBALT_SHARD));
        addDrop(ModBlocks.DEEPSLATE_COBALT_ORE, oreDrops(ModBlocks.DEEPSLATE_COBALT_ORE, ModItems.COBALT_SHARD));
        addDrop(ModBlocks.RUBY_ORE, oreDrops(ModBlocks.RUBY_ORE, ModItems.RUBY));
        addDrop(ModBlocks.DEEPSLATE_RUBY_ORE, oreDrops(ModBlocks.DEEPSLATE_RUBY_ORE, ModItems.RUBY));
        addDrop(ModBlocks.SAPPHIRE_ORE, oreDrops(ModBlocks.SAPPHIRE_ORE, ModItems.SAPPHIRE));
        addDrop(ModBlocks.DEEPSLATE_SAPPHIRE_ORE, oreDrops(ModBlocks.DEEPSLATE_SAPPHIRE_ORE, ModItems.SAPPHIRE));
        addDrop(ModBlocks.TIN_ORE, oreDrops(ModBlocks.TIN_ORE, ModItems.RAW_TIN));
        addDrop(ModBlocks.DEEPSLATE_TIN_ORE, oreDrops(ModBlocks.DEEPSLATE_TIN_ORE, ModItems.RAW_TIN));
        addDrop(ModBlocks.TUNGSTEN_ORE, oreDrops(ModBlocks.TUNGSTEN_ORE, ModItems.RAW_TUNGSTEN));
        addDrop(ModBlocks.DEEPSLATE_TUNGSTEN_ORE, oreDrops(ModBlocks.DEEPSLATE_TUNGSTEN_ORE, ModItems.RAW_TUNGSTEN));

        addDrop(ModBlocks.PHOSPHATE_ORE, multiOreDrops(ModBlocks.PHOSPHATE_ORE, ModItems.PHOSPHATE_POWDER,
                2.0f, 5.0f));   //Equivalent to copper
        addDrop(ModBlocks.DEEPSLATE_PHOSPHATE_ORE, multiOreDrops(ModBlocks.DEEPSLATE_PHOSPHATE_ORE, ModItems.PHOSPHATE_POWDER,
                2.0f, 5.0f));   //Equivalent to copper
    }



    /**
     * Generates a loot table for a Block that should drop multiple of the same item.
     * @param drop The Block dropped if mined with Silk Touch
     * @param item The Item to drop normally (without Silk Touch)
     * @param min The minimum number of drops
     * @param max The maximum number of drops
     * @return The newly generated loot table builder
     */
    public LootTable.Builder multiOreDrops(Block drop, Item item, float min, float max) {
        return dropsWithSilkTouch(drop, this.applyExplosionDecay(drop, ItemEntry.builder(item)
                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(min, max)))
                .apply(ApplyBonusLootFunction.oreDrops(Enchantments.FORTUNE))));
    }
}
