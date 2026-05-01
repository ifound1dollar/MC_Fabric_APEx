package net.dollar.apex.datagen;

import net.dollar.apex.block.ModBlocks;
import net.dollar.apex.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import java.util.concurrent.CompletableFuture;

public class ModLootTableProvider extends FabricBlockLootTableProvider {
    public ModLootTableProvider(FabricDataOutput dataOutput, CompletableFuture<HolderLookup.Provider> registryLookup) {
        super(dataOutput, registryLookup);
    }



    @Override
    public void generate() {
        dropSelf(ModBlocks.DECORATIVE_AMETHYST_BLOCK);
        dropSelf(ModBlocks.COBALT_BLOCK);
        dropSelf(ModBlocks.RUBY_BLOCK);
        dropSelf(ModBlocks.SAPPHIRE_BLOCK);
        dropSelf(ModBlocks.TIN_BLOCK);
        dropSelf(ModBlocks.RAW_TIN_BLOCK);
        dropSelf(ModBlocks.TUNGSTEN_BLOCK);
        dropSelf(ModBlocks.RAW_TUNGSTEN_BLOCK);
        dropSelf(ModBlocks.BRONZE_BLOCK);
        dropSelf(ModBlocks.STEEL_BLOCK);

        add(ModBlocks.COBALT_ORE, createOreDrop(ModBlocks.COBALT_ORE, ModItems.COBALT_SHARD));
        add(ModBlocks.DEEPSLATE_COBALT_ORE, createOreDrop(ModBlocks.DEEPSLATE_COBALT_ORE, ModItems.COBALT_SHARD));
        add(ModBlocks.RUBY_ORE, createOreDrop(ModBlocks.RUBY_ORE, ModItems.RUBY));
        add(ModBlocks.DEEPSLATE_RUBY_ORE, createOreDrop(ModBlocks.DEEPSLATE_RUBY_ORE, ModItems.RUBY));
        add(ModBlocks.SAPPHIRE_ORE, createOreDrop(ModBlocks.SAPPHIRE_ORE, ModItems.SAPPHIRE));
        add(ModBlocks.DEEPSLATE_SAPPHIRE_ORE, createOreDrop(ModBlocks.DEEPSLATE_SAPPHIRE_ORE, ModItems.SAPPHIRE));
        add(ModBlocks.TIN_ORE, createOreDrop(ModBlocks.TIN_ORE, ModItems.RAW_TIN));
        add(ModBlocks.DEEPSLATE_TIN_ORE, createOreDrop(ModBlocks.DEEPSLATE_TIN_ORE, ModItems.RAW_TIN));
        add(ModBlocks.TUNGSTEN_ORE, createOreDrop(ModBlocks.TUNGSTEN_ORE, ModItems.RAW_TUNGSTEN));
        add(ModBlocks.DEEPSLATE_TUNGSTEN_ORE, createOreDrop(ModBlocks.DEEPSLATE_TUNGSTEN_ORE, ModItems.RAW_TUNGSTEN));

        add(ModBlocks.PHOSPHATE_ORE, multiOreDrops(ModBlocks.PHOSPHATE_ORE, ModItems.PHOSPHATE_POWDER,
                2.0f, 5.0f));   //Equivalent to copper
        add(ModBlocks.DEEPSLATE_PHOSPHATE_ORE, multiOreDrops(ModBlocks.DEEPSLATE_PHOSPHATE_ORE, ModItems.PHOSPHATE_POWDER,
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
        HolderLookup.RegistryLookup<Enchantment> impl = this.registries.lookupOrThrow(Registries.ENCHANTMENT);
        return createSilkTouchDispatchTable(drop, this.applyExplosionDecay(drop, LootItem.lootTableItem(item)
                .apply(SetItemCountFunction.setCount(UniformGenerator.between(min, max)))
                .apply(ApplyBonusCount.addOreBonusCount(impl.getOrThrow(Enchantments.FORTUNE)))));
    }
}
