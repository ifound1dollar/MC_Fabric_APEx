package net.dollar.apex.datagen;

import net.dollar.apex.ModMain;
import net.dollar.apex.block.ModBlocks;
import net.dollar.apex.item.ModItems;
import net.dollar.apex.util.ModTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.SmithingTransformRecipeBuilder;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import java.util.List;
import java.util.concurrent.CompletableFuture;


public class ModRecipeProvider extends FabricRecipeProvider {
    @Override
    public String getName() {
        //TODO: Verify this is correct, advancement provider uses this and is just "Advancements"
        return "ModRecipes";
    }

    private enum ToolType { AXE, BATTLEAXE, HOE, PAXEL, PICKAXE, SHOVEL, SWORD }

    private static final List<ItemLike> COBALT_SMELTABLES = List.of(
            ModBlocks.COBALT_ORE, ModBlocks.DEEPSLATE_COBALT_ORE);
    private static final List<ItemLike> PHOSPHATE_SMELTABLES = List.of(
            ModBlocks.PHOSPHATE_ORE, ModBlocks.DEEPSLATE_PHOSPHATE_ORE);
    private static final List<ItemLike> RUBY_SMELTABLES = List.of(
            ModBlocks.RUBY_ORE, ModBlocks.DEEPSLATE_RUBY_ORE);
    private static final List<ItemLike> SAPPHIRE_SMELTABLES = List.of(
            ModBlocks.SAPPHIRE_ORE, ModBlocks.DEEPSLATE_SAPPHIRE_ORE);
    private static final List<ItemLike> TIN_SMELTABLES = List.of(
            ModItems.RAW_TIN, ModBlocks.TIN_ORE, ModBlocks.DEEPSLATE_TIN_ORE);
    private static final List<ItemLike> TUNGSTEN_SMELTABLES = List.of(
            ModItems.RAW_TUNGSTEN, ModBlocks.TUNGSTEN_ORE, ModBlocks.DEEPSLATE_TUNGSTEN_ORE);
    private static final List<ItemLike> BRONZE_SMELTABLES = List.of(
            ModItems.BRONZE_COMPOUND);
    private static final List<ItemLike> STEEL_SMELTABLES = List.of(
            ModItems.STEEL_COMPOUND);

    private static final List<ItemLike> BRONZE_NUGGET_SMELTABLE_TOOLS = List.of(
            ModItems.BRONZE_AXE, ModItems.BRONZE_HOE, ModItems.BRONZE_PICKAXE,
            ModItems.BRONZE_SHOVEL, ModItems.BRONZE_SWORD, ModItems.BRONZE_HELMET,
            ModItems.BRONZE_CHESTPLATE, ModItems.BRONZE_LEGGINGS, ModItems.BRONZE_BOOTS
    );
    private static final List<ItemLike> GOLD_NUGGET_SMELTABLE_TOOLS = List.of(
            ModItems.GILDED_BRONZE_AXE, ModItems.GILDED_BRONZE_HOE, ModItems.GILDED_BRONZE_PICKAXE,
            ModItems.GILDED_BRONZE_SHOVEL, ModItems.GILDED_BRONZE_SWORD, ModItems.GILDED_BRONZE_HELMET,
            ModItems.GILDED_BRONZE_CHESTPLATE, ModItems.GILDED_BRONZE_LEGGINGS, ModItems.GILDED_BRONZE_BOOTS
    );



    public ModRecipeProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected RecipeProvider createRecipeProvider(HolderLookup.Provider registries, RecipeOutput exporter) {
        return new RecipeProvider(registries, exporter) {
            @Override
            public void buildRecipes() {
                //TODO: Fix fromTag() to use RegistryEntryList<Item>
                
                
                //region SMELTING AND BLASTING
                oreSmelting(COBALT_SMELTABLES, RecipeCategory.MISC, ModItems.COBALT_SHARD,
                        0.9f, 200, "cobalt_shard"); //Diamond is 1.0
                oreBlasting(COBALT_SMELTABLES, RecipeCategory.MISC, ModItems.COBALT_SHARD,
                        0.9f, 100, "cobalt_shard");
                oreSmelting(PHOSPHATE_SMELTABLES, RecipeCategory.MISC, ModItems.PHOSPHATE_POWDER,
                        0.2f, 200, "phosphate_powder"); //Coal is 0.1
                oreBlasting(PHOSPHATE_SMELTABLES, RecipeCategory.MISC, ModItems.PHOSPHATE_POWDER,
                        0.2f, 100, "phosphate_powder");
                oreSmelting(RUBY_SMELTABLES, RecipeCategory.MISC, ModItems.RUBY,
                        1.2f, 200, "ruby");         //Diamond/Emerald is 1.0
                oreBlasting(RUBY_SMELTABLES, RecipeCategory.MISC, ModItems.RUBY,
                        1.2f, 100, "ruby");
                oreSmelting(SAPPHIRE_SMELTABLES, RecipeCategory.MISC, ModItems.SAPPHIRE,
                        1.2f, 200, "sapphire");     //Diamond/Emerald is 1.0
                oreBlasting(SAPPHIRE_SMELTABLES, RecipeCategory.MISC, ModItems.SAPPHIRE,
                        1.2f, 100, "sapphire");
                oreSmelting(TIN_SMELTABLES, RecipeCategory.MISC, ModItems.TIN_INGOT,
                        0.7f, 200, "tin_ingot");    //Iron is 0.7
                oreBlasting(TIN_SMELTABLES, RecipeCategory.MISC, ModItems.TIN_INGOT,
                        0.7f, 100, "tin_ingot");
                oreSmelting(TUNGSTEN_SMELTABLES, RecipeCategory.MISC, ModItems.TUNGSTEN_INGOT,
                        1.0f, 200, "tungsten_ingot");   //Diamond is 1.0
                oreBlasting(TUNGSTEN_SMELTABLES, RecipeCategory.MISC, ModItems.TUNGSTEN_INGOT,
                        1.0f, 100, "tungsten_ingot");
                oreSmelting(BRONZE_SMELTABLES, RecipeCategory.MISC, ModItems.BRONZE_INGOT,
                        0.7f, 200, "bronze_ingot");    //Iron is 0.7
                oreBlasting(BRONZE_SMELTABLES, RecipeCategory.MISC, ModItems.BRONZE_INGOT,
                        0.7f, 100, "bronze_ingot");
                oreSmelting(STEEL_SMELTABLES, RecipeCategory.MISC, ModItems.STEEL_INGOT,
                        0.9f, 200, "steel_ingot");   //Diamond is 1.0
                oreBlasting(STEEL_SMELTABLES, RecipeCategory.MISC, ModItems.STEEL_INGOT,
                        0.9f, 100, "steel_ingot");

                oreSmelting(BRONZE_NUGGET_SMELTABLE_TOOLS, RecipeCategory.MISC, ModItems.BRONZE_NUGGET,
                        0.1f, 200, "bronze_nugget");   //Diamond is 1.0
                oreBlasting(BRONZE_NUGGET_SMELTABLE_TOOLS, RecipeCategory.MISC, ModItems.BRONZE_NUGGET,
                        0.1f, 100, "bronze_nugget");
                oreSmelting(GOLD_NUGGET_SMELTABLE_TOOLS, RecipeCategory.MISC, Items.GOLD_NUGGET,
                        0.1f, 200, "gold_nugget");   //Diamond is 1.0
                oreBlasting(GOLD_NUGGET_SMELTABLE_TOOLS, RecipeCategory.MISC, Items.GOLD_NUGGET,
                        0.1f, 100, "gold_nugget");
                //endregion

                //region STORAGE BLOCKS
                //NOTE: FIRST IS FOR BLOCK->ITEM, SECOND IS FOR ITEM->BLOCK
                nineBlockStorageRecipes(RecipeCategory.BUILDING_BLOCKS, ModItems.RUBY,
                        RecipeCategory.DECORATIONS, ModBlocks.RUBY_BLOCK);
                nineBlockStorageRecipes(RecipeCategory.BUILDING_BLOCKS, ModItems.SAPPHIRE,
                        RecipeCategory.DECORATIONS, ModBlocks.SAPPHIRE_BLOCK);
                nineBlockStorageRecipes(RecipeCategory.BUILDING_BLOCKS, Items.AMETHYST_SHARD,
                        RecipeCategory.DECORATIONS, ModBlocks.DECORATIVE_AMETHYST_BLOCK);
                nineBlockStorageRecipes(RecipeCategory.BUILDING_BLOCKS, ModItems.COBALT_SHARD,
                        RecipeCategory.DECORATIONS, ModBlocks.COBALT_BLOCK);
                nineBlockStorageRecipes(RecipeCategory.BUILDING_BLOCKS, ModItems.TIN_INGOT,
                        RecipeCategory.DECORATIONS, ModBlocks.TIN_BLOCK);
                nineBlockStorageRecipes(RecipeCategory.BUILDING_BLOCKS, ModItems.RAW_TIN,
                        RecipeCategory.DECORATIONS, ModBlocks.RAW_TIN_BLOCK);
                nineBlockStorageRecipes(RecipeCategory.BUILDING_BLOCKS, ModItems.TUNGSTEN_INGOT,
                        RecipeCategory.DECORATIONS, ModBlocks.TUNGSTEN_BLOCK);
                nineBlockStorageRecipes(RecipeCategory.BUILDING_BLOCKS, ModItems.RAW_TUNGSTEN,
                        RecipeCategory.DECORATIONS, ModBlocks.RAW_TUNGSTEN_BLOCK);
                nineBlockStorageRecipes(RecipeCategory.BUILDING_BLOCKS, ModItems.BRONZE_INGOT,
                        RecipeCategory.DECORATIONS, ModBlocks.BRONZE_BLOCK);
                nineBlockStorageRecipes(RecipeCategory.BUILDING_BLOCKS, ModItems.STEEL_INGOT,
                        RecipeCategory.DECORATIONS, ModBlocks.STEEL_BLOCK);
                //endregion

                //region NUGGETS (cannot use compacting recipes helper because duplicate ingot recipe names)
                shapeless(RecipeCategory.MISC, ModItems.TIN_NUGGET, 9)
                        .requires(tag(ModTags.Items.COMMON_TIN_INGOTS), 1)
                        .unlockedBy("has_tin_ingot", has(ModTags.Items.COMMON_TIN_INGOTS))
                        .save(output, ResourceKey.create(
                                Registries.RECIPE, Identifier.fromNamespaceAndPath(ModMain.MOD_ID, "tin_nugget_from_ingot")));
                shapeless(RecipeCategory.MISC, ModItems.TIN_INGOT, 1)
                        .requires(Ingredient.of(ModItems.TIN_NUGGET), 9)
                        .unlockedBy("has_tin_nugget", has(ModItems.TIN_NUGGET))
                        .save(output, ResourceKey.create(
                                Registries.RECIPE, Identifier.fromNamespaceAndPath(ModMain.MOD_ID, "tin_ingot_from_nugget")));

                //RegistryEntryList.of(registries, ModTags.Items.COMMON_TUNGSTEN_INGOTS);
                shapeless(RecipeCategory.MISC, ModItems.TUNGSTEN_NUGGET, 9)
                        .requires(tag(ModTags.Items.COMMON_TUNGSTEN_INGOTS), 1)
                        .unlockedBy("has_tungsten_ingot", has(ModTags.Items.COMMON_TUNGSTEN_INGOTS))
                        .save(output, ResourceKey.create(
                                Registries.RECIPE, Identifier.fromNamespaceAndPath(ModMain.MOD_ID, "tungsten_nugget_from_ingot")));
                shapeless(RecipeCategory.MISC, ModItems.TUNGSTEN_INGOT, 1)
                        .requires(Ingredient.of(ModItems.TUNGSTEN_NUGGET), 9)
                        .unlockedBy("has_tungsten_nugget", has(ModItems.TUNGSTEN_NUGGET))
                        .save(output, ResourceKey.create(
                                Registries.RECIPE, Identifier.fromNamespaceAndPath(ModMain.MOD_ID, "tungsten_ingot_from_nugget")));

                shapeless(RecipeCategory.MISC, ModItems.BRONZE_NUGGET, 9)
                        .requires(tag(ModTags.Items.COMMON_BRONZE_INGOTS), 1)
                        .unlockedBy("has_bronze_ingot", has(ModTags.Items.COMMON_BRONZE_INGOTS))
                        .save(output, ResourceKey.create(
                                Registries.RECIPE, Identifier.fromNamespaceAndPath(ModMain.MOD_ID, "bronze_nugget_from_ingot")));
                shapeless(RecipeCategory.MISC, ModItems.BRONZE_INGOT, 1)
                        .requires(Ingredient.of(ModItems.BRONZE_NUGGET), 9)
                        .unlockedBy("has_bronze_nugget", has(ModItems.BRONZE_NUGGET))
                        .save(output, ResourceKey.create(
                                Registries.RECIPE, Identifier.fromNamespaceAndPath(ModMain.MOD_ID, "bronze_ingot_from_nugget")));

                shapeless(RecipeCategory.MISC, ModItems.STEEL_NUGGET, 9)
                        .requires(tag(ModTags.Items.COMMON_STEEL_INGOTS), 1)
                        .unlockedBy("has_steel_ingot", has(ModTags.Items.COMMON_STEEL_INGOTS))
                        .save(output, ResourceKey.create(
                                Registries.RECIPE, Identifier.fromNamespaceAndPath(ModMain.MOD_ID, "steel_nugget_from_ingot")));
                shapeless(RecipeCategory.MISC, ModItems.STEEL_INGOT, 1)
                        .requires(Ingredient.of(ModItems.STEEL_NUGGET), 9)
                        .unlockedBy("has_steel_nugget", has(ModItems.STEEL_NUGGET))
                        .save(output, ResourceKey.create(
                                Registries.RECIPE, Identifier.fromNamespaceAndPath(ModMain.MOD_ID, "steel_ingot_from_nugget")));
                //endregion

                //region PHOSPHATE POWDER RECIPES
                shapeless(RecipeCategory.MISC, Items.GUNPOWDER, 2)
                        .requires(Ingredient.of(ModItems.PHOSPHATE_POWDER), 1)
                        .requires(tag(ItemTags.COALS), 1)
                        .unlockedBy("has_phosphate_powder", has(ModItems.PHOSPHATE_POWDER))
                        .unlockedBy("has_coal", has(ItemTags.COALS))
                        .save(output, ResourceKey.create(
                                Registries.RECIPE, Identifier.fromNamespaceAndPath(ModMain.MOD_ID, "gunpowder_from_phosphate_coal")));
                shapeless(RecipeCategory.MISC, ModItems.FERTILIZER, 2)
                        .requires(Ingredient.of(ModItems.PHOSPHATE_POWDER), 1)
                        .requires(Ingredient.of(Items.ROTTEN_FLESH), 1)
                        .unlockedBy("has_phosphate_powder", has(ModItems.PHOSPHATE_POWDER))
                        .unlockedBy("has_rotten_flesh", has(Items.ROTTEN_FLESH))
                        .save(output, ResourceKey.create(
                                Registries.RECIPE, Identifier.parse(getSimpleRecipeName(ModItems.FERTILIZER))));
                //endregion

                //region IRON-REPLACEMENT TIN RECIPES
                shaped(RecipeCategory.MISC, Items.BUCKET, 1)
                        .define('d', ModTags.Items.COMMON_TIN_INGOTS)
                        .pattern("d d")
                        .pattern(" d ")
                        .unlockedBy("has_tin_ingot", has(ModTags.Items.COMMON_TIN_INGOTS))
                        .save(output, ResourceKey.create(
                                Registries.RECIPE, Identifier.fromNamespaceAndPath(ModMain.MOD_ID, "bucket_from_tin_ingot")));
                shaped(RecipeCategory.MISC, Items.SHEARS, 1)
                        .define('d', ModTags.Items.COMMON_TIN_INGOTS)
                        .pattern(" d")
                        .pattern("d ")
                        .unlockedBy("has_tin_ingot", has(ModTags.Items.COMMON_TIN_INGOTS))
                        .save(output, ResourceKey.create(
                                Registries.RECIPE, Identifier.fromNamespaceAndPath(ModMain.MOD_ID, "shears_from_tin_ingot")));
                shaped(RecipeCategory.MISC, Items.LANTERN, 1)
                        .define('d', ModItems.TIN_NUGGET)
                        .define('i', Items.TORCH)
                        .pattern("ddd")
                        .pattern("did")
                        .pattern("ddd")
                        .unlockedBy("has_tin_nugget", has(ModItems.TIN_NUGGET))
                        .save(output, ResourceKey.create(
                                Registries.RECIPE, Identifier.fromNamespaceAndPath(ModMain.MOD_ID, "lantern_from_tin_nugget")));
                shaped(RecipeCategory.MISC, Items.SOUL_LANTERN, 1)
                        .define('d', ModItems.TIN_NUGGET)
                        .define('i', Items.SOUL_TORCH)
                        .pattern("ddd")
                        .pattern("did")
                        .pattern("ddd")
                        .unlockedBy("has_tin_nugget", has(ModItems.TIN_NUGGET))
                        .save(output, ResourceKey.create(
                                Registries.RECIPE, Identifier.fromNamespaceAndPath(ModMain.MOD_ID, "soul_lantern_from_tin_nugget")));
                shaped(RecipeCategory.MISC, Items.TRIPWIRE_HOOK, 2)
                        .define('d', ModTags.Items.COMMON_TIN_INGOTS)
                        .define('i', ModTags.Items.COMMON_WOODEN_RODS)
                        .define('n', ItemTags.PLANKS)
                        .pattern("d")
                        .pattern("i")
                        .pattern("n")
                        .unlockedBy("has_tin_ingot", has(ModTags.Items.COMMON_TIN_INGOTS))
                        .save(output, ResourceKey.create(
                                Registries.RECIPE, Identifier.fromNamespaceAndPath(ModMain.MOD_ID, "tripwire_hook_from_tin_ingot")));
                shaped(RecipeCategory.MISC, Items.HOPPER, 1)
                        .define('d', ModTags.Items.COMMON_TIN_INGOTS)
                        .define('i', ModTags.Items.COMMON_CHESTS)
                        .pattern("d d")
                        .pattern("did")
                        .pattern(" d ")
                        .unlockedBy("has_tin_ingot", has(ModTags.Items.COMMON_TIN_INGOTS))
                        .save(output, ResourceKey.create(
                                Registries.RECIPE, Identifier.fromNamespaceAndPath(ModMain.MOD_ID, "hopper_from_tin_ingot")));
                shapeless(RecipeCategory.MISC, Items.FLINT_AND_STEEL, 1)
                        .requires(tag(ModTags.Items.COMMON_TIN_INGOTS), 1)
                        .requires(Ingredient.of(Items.FLINT), 1)
                        .unlockedBy("has_tin_ingot", has(ModTags.Items.COMMON_TIN_INGOTS))
                        .save(output, ResourceKey.create(
                                Registries.RECIPE, Identifier.fromNamespaceAndPath(ModMain.MOD_ID, "flint_and_steel_from_tin_ingot")));
                shaped(RecipeCategory.MISC, Items.COMPASS, 1)
                        .define('d', ModTags.Items.COMMON_TIN_INGOTS)
                        .define('i', Items.REDSTONE)
                        .pattern(" d ")
                        .pattern("did")
                        .pattern(" d ")
                        .unlockedBy("has_tin_ingot", has(ModTags.Items.COMMON_TIN_INGOTS))
                        .save(output, ResourceKey.create(
                                Registries.RECIPE, Identifier.fromNamespaceAndPath(ModMain.MOD_ID, "compass_from_tin_ingot")));
                shaped(RecipeCategory.MISC, Items.IRON_CHAIN, 1)
                        .define('d', ModItems.TIN_NUGGET)
                        .define('i', ModTags.Items.COMMON_TIN_INGOTS)
                        .pattern("d")
                        .pattern("i")
                        .pattern("d")
                        .unlockedBy("has_tin_ingot", has(ModTags.Items.COMMON_TIN_INGOTS))
                        .unlockedBy("has_tin_nugget", has(ModItems.TIN_NUGGET))
                        .save(output, ResourceKey.create(
                                Registries.RECIPE, Identifier.fromNamespaceAndPath(ModMain.MOD_ID, "chain_from_tin_ingot_and_nugget")));
                shaped(RecipeCategory.MISC, Items.CAULDRON, 1)
                        .define('d', ModTags.Items.COMMON_TIN_INGOTS)
                        .pattern("d d")
                        .pattern("d d")
                        .pattern("ddd")
                        .unlockedBy("has_tin_ingot", has(ModTags.Items.COMMON_TIN_INGOTS))
                        .save(output, ResourceKey.create(
                                Registries.RECIPE, Identifier.fromNamespaceAndPath(ModMain.MOD_ID, "cauldron_from_tin_ingot")));
                //endregion

                //region COMPOUNDS AND ENDGAME INGREDIENT ITEMS (shapeless)
                shapeless(RecipeCategory.MISC, ModItems.BRONZE_COMPOUND, 3)
                        .requires(tag(ModTags.Items.COMMON_COPPER_INGOTS), 3)
                        .requires(tag(ModTags.Items.COMMON_TIN_INGOTS), 1)
                        .unlockedBy("has_copper_ingot", has(ModTags.Items.COMMON_COPPER_INGOTS))
                        .unlockedBy("has_tin_ingot", has(ModTags.Items.COMMON_TIN_INGOTS))
                        .save(output, ResourceKey.create(
                                Registries.RECIPE, Identifier.parse(getSimpleRecipeName(ModItems.BRONZE_COMPOUND))));
                shapeless(RecipeCategory.MISC, ModItems.STEEL_COMPOUND, 1)
                        .requires(tag(ModTags.Items.COMMON_IRON_INGOTS), 1)
                        .requires(Items.COAL, 1)
                        .unlockedBy("has_iron_ingot", has(ModTags.Items.COMMON_IRON_INGOTS))
                        .unlockedBy("has_coal", has(Items.COAL))
                        .save(output, ResourceKey.create(
                                Registries.RECIPE, Identifier.parse(getSimpleRecipeName(ModItems.STEEL_COMPOUND))));
                shapeless(RecipeCategory.MISC, ModItems.INFUSED_GEMSTONE, 1)
                        .requires(tag(ModTags.Items.COMMON_AMETHYST), 1)
                        .requires(tag(ModTags.Items.COMMON_DIAMONDS), 1)
                        .requires(tag(ModTags.Items.COMMON_EMERALDS), 1)
                        .requires(tag(ModTags.Items.COMMON_RUBIES), 1)
                        .requires(tag(ModTags.Items.COMMON_SAPPHIRES), 1)
                        .requires(ModItems.HANDFUL_OF_STARDUST, 1)
                        .unlockedBy("has_amethyst_shard", has(ModTags.Items.COMMON_AMETHYST))
                        .unlockedBy("has_diamond", has(ModTags.Items.COMMON_DIAMONDS))
                        .unlockedBy("has_emerald", has(ModTags.Items.COMMON_EMERALDS))
                        .unlockedBy("has_ruby", has(ModTags.Items.COMMON_RUBIES))
                        .unlockedBy("has_sapphire", has(ModTags.Items.COMMON_SAPPHIRES))
                        .unlockedBy("has_handful_of_stardust", has(ModItems.HANDFUL_OF_STARDUST))
                        .save(output, ResourceKey.create(
                                Registries.RECIPE, Identifier.parse(getSimpleRecipeName(ModItems.INFUSED_GEMSTONE))));

                shaped(RecipeCategory.MISC, ModItems.COBALT_STEEL_INGOT, 1)
                        .define('d', ModTags.Items.COMMON_STEEL_INGOTS)
                        .define('i', ModItems.COBALT_SHARD)
                        .define('n', ModItems.MOLTEN_CORE)
                        .pattern("did")
                        .pattern("ini")
                        .pattern("did")
                        .unlockedBy("has_steel_ingot", has(ModTags.Items.COMMON_STEEL_INGOTS))
                        .unlockedBy("has_cobalt_shard", has(ModItems.COBALT_SHARD))
                        .unlockedBy("has_molten_core", has(ModItems.MOLTEN_CORE))
                        .save(output, ResourceKey.create(
                                Registries.RECIPE, Identifier.fromNamespaceAndPath(ModMain.MOD_ID, "cobalt_steel_ingot")));
                shaped(RecipeCategory.MISC, ModItems.COBALT_STEEL_INGOT, 1)
                        .define('d', ModTags.Items.COMMON_STEEL_INGOTS)
                        .define('i', ModItems.COBALT_SHARD)
                        .define('n', ModItems.MOLTEN_CORE)
                        .pattern("idi")
                        .pattern("dnd")
                        .pattern("idi")
                        .unlockedBy("has_steel_ingot", has(ModTags.Items.COMMON_STEEL_INGOTS))
                        .unlockedBy("has_cobalt_shard", has(ModItems.COBALT_SHARD))
                        .unlockedBy("has_molten_core", has(ModItems.MOLTEN_CORE))
                        .save(output, ResourceKey.create(
                                Registries.RECIPE, Identifier.fromNamespaceAndPath(ModMain.MOD_ID, "cobalt_steel_ingot_reversed")));

                shaped(RecipeCategory.MISC, ModItems.TUNGSTEN_CARBIDE_INGOT, 1)
                        .define('d', ModTags.Items.COMMON_TUNGSTEN_INGOTS)
                        .define('i', Items.COAL)
                        .define('n', ModItems.MOLTEN_CORE)
                        .pattern("did")
                        .pattern("ini")
                        .pattern("did")
                        .unlockedBy("has_tungsten_ingot", has(ModTags.Items.COMMON_TUNGSTEN_INGOTS))
                        .unlockedBy("has_coal", has(Items.COAL))
                        .unlockedBy("has_molten_core", has(ModItems.MOLTEN_CORE))
                        .save(output, ResourceKey.create(
                                Registries.RECIPE, Identifier.fromNamespaceAndPath(ModMain.MOD_ID, "tungsten_carbide_ingot")));
                shaped(RecipeCategory.MISC, ModItems.TUNGSTEN_CARBIDE_INGOT, 1)
                        .define('d', ModTags.Items.COMMON_TUNGSTEN_INGOTS)
                        .define('i', Items.COAL)
                        .define('n', ModItems.MOLTEN_CORE)
                        .pattern("idi")
                        .pattern("dnd")
                        .pattern("idi")
                        .unlockedBy("has_tungsten_ingot", has(ModTags.Items.COMMON_TUNGSTEN_INGOTS))
                        .unlockedBy("has_coal", has(Items.COAL))
                        .unlockedBy("has_molten_core", has(ModItems.MOLTEN_CORE))
                        .save(output, ResourceKey.create(
                                Registries.RECIPE, Identifier.fromNamespaceAndPath(ModMain.MOD_ID, "tungsten_carbide_ingot_reversed")));
                //endregion

                //region UPGRADE TEMPLATES
                shaped(RecipeCategory.MISC, Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE, 1)
                        .define('d', ModItems.BASIC_UPGRADE_TEMPLATE)
                        .define('i', ModTags.Items.COMMON_NETHERRACKS)
                        .define('n', ModTags.Items.COMMON_DIAMONDS)
                        .pattern(" d ")
                        .pattern("nin")
                        .pattern(" n ")
                        .unlockedBy("has_basic_upgrade_template", has(ModItems.BASIC_UPGRADE_TEMPLATE))
                        .unlockedBy("has_diamond", has(ModTags.Items.COMMON_DIAMONDS))
                        .save(output, ResourceKey.create(
                                Registries.RECIPE, Identifier.fromNamespaceAndPath(ModMain.MOD_ID, "netherite_upgrade_smithing_template_from_basic")));
                shaped(RecipeCategory.MISC, ModItems.COBALT_UPGRADE_TEMPLATE, 2)
                        .define('d', ModItems.COBALT_UPGRADE_TEMPLATE)
                        .define('i', ModTags.Items.COMMON_STONES)
                        .define('n', ModTags.Items.COMMON_STEEL_INGOTS)
                        .pattern("ndn")
                        .pattern("nin")
                        .pattern("nnn")
                        .unlockedBy("has_cobalt_upgrade_smithing_template", has(ModItems.COBALT_UPGRADE_TEMPLATE))
                        .unlockedBy("has_steel_ingot", has(ModTags.Items.COMMON_STEEL_INGOTS))
                        .save(output, ResourceKey.create(
                                Registries.RECIPE, Identifier.parse(getSimpleRecipeName(ModItems.COBALT_UPGRADE_TEMPLATE))));
                shaped(RecipeCategory.MISC, ModItems.COBALT_UPGRADE_TEMPLATE, 1)
                        .define('d', ModItems.BASIC_UPGRADE_TEMPLATE)
                        .define('i', ModTags.Items.COMMON_STONES)
                        .define('n', ModTags.Items.COMMON_STEEL_INGOTS)
                        .pattern(" d ")
                        .pattern("nin")
                        .pattern(" n ")
                        .unlockedBy("has_basic_upgrade_template", has(ModItems.BASIC_UPGRADE_TEMPLATE))
                        .unlockedBy("has_steel_ingot", has(ModTags.Items.COMMON_STEEL_INGOTS))
                        .save(output, ResourceKey.create(
                                Registries.RECIPE, Identifier.fromNamespaceAndPath(ModMain.MOD_ID, "cobalt_upgrade_smithing_template_from_basic")));
                shaped(RecipeCategory.MISC, ModItems.INFUSION_UPGRADE_TEMPLATE, 2)
                        .define('d', ModItems.INFUSION_UPGRADE_TEMPLATE)
                        .define('i', ModTags.Items.COMMON_DEEPSLATES)
                        .define('n', ModTags.Items.COMMON_DIAMONDS)
                        .pattern("ndn")
                        .pattern("nin")
                        .pattern("nnn")
                        .unlockedBy("has_infusion_upgrade_smithing_template", has(ModItems.INFUSION_UPGRADE_TEMPLATE))
                        .unlockedBy("has_diamond", has(ModTags.Items.COMMON_DIAMONDS))
                        .save(output, ResourceKey.create(
                                Registries.RECIPE, Identifier.parse(getSimpleRecipeName(ModItems.INFUSION_UPGRADE_TEMPLATE))));
                shaped(RecipeCategory.MISC, ModItems.INFUSION_UPGRADE_TEMPLATE, 1)
                        .define('d', ModItems.BASIC_UPGRADE_TEMPLATE)
                        .define('i', ModTags.Items.COMMON_DEEPSLATES)
                        .define('n', ModTags.Items.COMMON_DIAMONDS)
                        .pattern(" d ")
                        .pattern("nin")
                        .pattern(" n ")
                        .unlockedBy("has_basic_upgrade_template", has(ModItems.BASIC_UPGRADE_TEMPLATE))
                        .unlockedBy("has_diamond", has(ModTags.Items.COMMON_DIAMONDS))
                        .save(output, ResourceKey.create(
                                Registries.RECIPE, Identifier.fromNamespaceAndPath(ModMain.MOD_ID, "infusion_upgrade_smithing_template_from_basic")));
                shaped(RecipeCategory.MISC, ModItems.CARBIDE_UPGRADE_TEMPLATE, 2)
                        .define('d', ModItems.CARBIDE_UPGRADE_TEMPLATE)
                        .define('i', Items.OBSIDIAN)
                        .define('n', ModTags.Items.COMMON_TUNGSTEN_INGOTS)
                        .pattern("ndn")
                        .pattern("nin")
                        .pattern("nnn")
                        .unlockedBy("has_carbide_upgrade_smithing_template", has(ModItems.CARBIDE_UPGRADE_TEMPLATE))
                        .unlockedBy("has_tungsten_ingot", has(ModTags.Items.COMMON_TUNGSTEN_INGOTS))
                        .save(output, ResourceKey.create(
                                Registries.RECIPE, Identifier.parse(getSimpleRecipeName(ModItems.CARBIDE_UPGRADE_TEMPLATE))));
                shaped(RecipeCategory.MISC, ModItems.CARBIDE_UPGRADE_TEMPLATE, 1)
                        .define('d', ModItems.BASIC_UPGRADE_TEMPLATE)
                        .define('i', Items.OBSIDIAN)
                        .define('n', ModTags.Items.COMMON_TUNGSTEN_INGOTS)
                        .pattern(" d ")
                        .pattern("nin")
                        .pattern(" n ")
                        .unlockedBy("has_basic_upgrade_template", has(ModItems.BASIC_UPGRADE_TEMPLATE))
                        .unlockedBy("has_tungsten_ingot", has(ModTags.Items.COMMON_TUNGSTEN_INGOTS))
                        .save(output, ResourceKey.create(
                                Registries.RECIPE, Identifier.fromNamespaceAndPath(ModMain.MOD_ID, "carbide_upgrade_smithing_template_from_basic")));
                //endregion

                //region VANILLA TIER BATTLEAXES AND PAXELS
                toolRecipeBuilder(output, ToolType.BATTLEAXE, ModTags.Items.COMMON_DIAMONDS, ModItems.DIAMOND_BATTLEAXE,
                        "has_diamond");
                toolRecipeBuilder(output, ToolType.PAXEL, ModTags.Items.COMMON_DIAMONDS, ModItems.DIAMOND_PAXEL,
                        "has_diamond");
                smithingUpgradeRecipeBuilder(output, Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE, ModItems.DIAMOND_BATTLEAXE,
                        Items.NETHERITE_INGOT, RecipeCategory.COMBAT, ModItems.NETHERITE_BATTLEAXE,
                        "has_netherite_upgrade_smithing_template", "has_netherite_ingot");
                smithingUpgradeRecipeBuilder(output, Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE, ModItems.DIAMOND_PAXEL,
                        Items.NETHERITE_INGOT, RecipeCategory.TOOLS, ModItems.NETHERITE_PAXEL,
                        "has_netherite_upgrade_smithing_template", "has_netherite_ingot");
                //endregion

                //region BOWS
                smithingUpgradeRecipeBuilder(output, ModItems.COBALT_UPGRADE_TEMPLATE, Items.BOW,
                        ModItems.COBALT_STEEL_INGOT, RecipeCategory.COMBAT, ModItems.COBALT_STEEL_BOW,
                        "has_cobalt_upgrade_smithing_template", "has_cobalt_steel_ingot");
                smithingUpgradeRecipeBuilder(output, ModItems.INFUSION_UPGRADE_TEMPLATE, Items.BOW,
                        ModItems.INFUSED_GEMSTONE, RecipeCategory.COMBAT, ModItems.INFUSED_GEMSTONE_BOW,
                        "has_infusion_upgrade_smithing_template", "has_infused_gemstone");
                smithingUpgradeRecipeBuilder(output, ModItems.CARBIDE_UPGRADE_TEMPLATE, Items.BOW,
                        ModItems.TUNGSTEN_CARBIDE_INGOT, RecipeCategory.COMBAT, ModItems.TUNGSTEN_CARBIDE_BOW,
                        "has_carbide_upgrade_smithing_template", "has_tungsten_carbide_ingot");
                //endregion

                //region CROSSBOWS
                smithingUpgradeRecipeBuilder(output, ModItems.COBALT_UPGRADE_TEMPLATE, Items.CROSSBOW,
                        ModItems.COBALT_STEEL_INGOT, RecipeCategory.COMBAT, ModItems.COBALT_STEEL_CROSSBOW,
                        "has_cobalt_upgrade_smithing_template", "has_cobalt_steel_ingot");
                smithingUpgradeRecipeBuilder(output, ModItems.INFUSION_UPGRADE_TEMPLATE, Items.CROSSBOW,
                        ModItems.INFUSED_GEMSTONE, RecipeCategory.COMBAT, ModItems.INFUSED_GEMSTONE_CROSSBOW,
                        "has_infusion_upgrade_smithing_template", "has_infused_gemstone");
                smithingUpgradeRecipeBuilder(output, ModItems.CARBIDE_UPGRADE_TEMPLATE, Items.CROSSBOW,
                        ModItems.TUNGSTEN_CARBIDE_INGOT, RecipeCategory.COMBAT, ModItems.TUNGSTEN_CARBIDE_CROSSBOW,
                        "has_carbide_upgrade_smithing_template", "has_tungsten_carbide_ingot");
                //endregion

                //region BRONZE EQUIPMENT
                armorRecipeBuilder(output, EquipmentSlot.HEAD, ModTags.Items.COMMON_BRONZE_INGOTS, ModItems.BRONZE_HELMET,
                        "has_bronze_ingot");
                armorRecipeBuilder(output, EquipmentSlot.CHEST, ModTags.Items.COMMON_BRONZE_INGOTS, ModItems.BRONZE_CHESTPLATE,
                        "has_bronze_ingot");
                armorRecipeBuilder(output, EquipmentSlot.LEGS, ModTags.Items.COMMON_BRONZE_INGOTS, ModItems.BRONZE_LEGGINGS,
                        "has_bronze_ingot");
                armorRecipeBuilder(output, EquipmentSlot.FEET, ModTags.Items.COMMON_BRONZE_INGOTS, ModItems.BRONZE_BOOTS,
                        "has_bronze_ingot");

                toolRecipeBuilder(output, ToolType.AXE, ModTags.Items.COMMON_BRONZE_INGOTS, ModItems.BRONZE_AXE,
                        "has_bronze_ingot");
                toolRecipeBuilder(output, ToolType.HOE, ModTags.Items.COMMON_BRONZE_INGOTS, ModItems.BRONZE_HOE,
                        "has_bronze_ingot");
                toolRecipeBuilder(output, ToolType.PICKAXE, ModTags.Items.COMMON_BRONZE_INGOTS, ModItems.BRONZE_PICKAXE,
                        "has_bronze_ingot");
                toolRecipeBuilder(output, ToolType.SHOVEL, ModTags.Items.COMMON_BRONZE_INGOTS, ModItems.BRONZE_SHOVEL,
                        "has_bronze_ingot");
                toolRecipeBuilder(output, ToolType.SWORD, ModTags.Items.COMMON_BRONZE_INGOTS, ModItems.BRONZE_SWORD,
                        "has_bronze_ingot");
                //endregion

                //region GILDED BRONZE EQUIPMENT
                shaped(RecipeCategory.MISC, ModItems.GILDED_BRONZE_HELMET, 1)
                        .define('d', ModTags.Items.COMMON_GOLD_INGOTS)
                        .define('i', ModItems.BRONZE_HELMET)
                        .pattern("ddd")
                        .pattern("did")
                        .pattern("ddd")
                        .unlockedBy("has_bronze_helmet", has(ModItems.BRONZE_HELMET))
                        .save(output, ResourceKey.create(
                                Registries.RECIPE, Identifier.parse(getSimpleRecipeName(ModItems.GILDED_BRONZE_HELMET))));
                shaped(RecipeCategory.MISC, ModItems.GILDED_BRONZE_CHESTPLATE, 1)
                        .define('d', ModTags.Items.COMMON_GOLD_INGOTS)
                        .define('i', ModItems.BRONZE_CHESTPLATE)
                        .pattern("ddd")
                        .pattern("did")
                        .pattern("ddd")
                        .unlockedBy("has_bronze_chestplate", has(ModItems.BRONZE_CHESTPLATE))
                        .save(output, ResourceKey.create(
                                Registries.RECIPE, Identifier.parse(getSimpleRecipeName(ModItems.GILDED_BRONZE_CHESTPLATE))));
                shaped(RecipeCategory.MISC, ModItems.GILDED_BRONZE_LEGGINGS, 1)
                        .define('d', ModTags.Items.COMMON_GOLD_INGOTS)
                        .define('i', ModItems.BRONZE_LEGGINGS)
                        .pattern("ddd")
                        .pattern("did")
                        .pattern("ddd")
                        .unlockedBy("has_bronze_leggings", has(ModItems.BRONZE_LEGGINGS))
                        .save(output, ResourceKey.create(
                                Registries.RECIPE, Identifier.parse(getSimpleRecipeName(ModItems.GILDED_BRONZE_LEGGINGS))));
                shaped(RecipeCategory.MISC, ModItems.GILDED_BRONZE_BOOTS, 1)
                        .define('d', ModTags.Items.COMMON_GOLD_INGOTS)
                        .define('i', ModItems.BRONZE_BOOTS)
                        .pattern("ddd")
                        .pattern("did")
                        .pattern("ddd")
                        .unlockedBy("has_bronze_boots", has(ModItems.BRONZE_BOOTS))
                        .save(output, ResourceKey.create(
                                Registries.RECIPE, Identifier.parse(getSimpleRecipeName(ModItems.GILDED_BRONZE_BOOTS))));

                shaped(RecipeCategory.MISC, ModItems.GILDED_BRONZE_AXE, 1)
                        .define('d', ModTags.Items.COMMON_GOLD_INGOTS)
                        .define('i', ModItems.BRONZE_AXE)
                        .pattern("ddd")
                        .pattern("did")
                        .pattern("ddd")
                        .unlockedBy("has_bronze_axe", has(ModItems.BRONZE_AXE))
                        .save(output, ResourceKey.create(
                                Registries.RECIPE, Identifier.parse(getSimpleRecipeName(ModItems.GILDED_BRONZE_AXE))));
                shaped(RecipeCategory.MISC, ModItems.GILDED_BRONZE_HOE, 1)
                        .define('d', ModTags.Items.COMMON_GOLD_INGOTS)
                        .define('i', ModItems.BRONZE_HOE)
                        .pattern("ddd")
                        .pattern("did")
                        .pattern("ddd")
                        .unlockedBy("has_bronze_hoe", has(ModItems.BRONZE_HOE))
                        .save(output, ResourceKey.create(
                                Registries.RECIPE, Identifier.parse(getSimpleRecipeName(ModItems.GILDED_BRONZE_HOE))));
                shaped(RecipeCategory.MISC, ModItems.GILDED_BRONZE_PICKAXE, 1)
                        .define('d', ModTags.Items.COMMON_GOLD_INGOTS)
                        .define('i', ModItems.BRONZE_PICKAXE)
                        .pattern("ddd")
                        .pattern("did")
                        .pattern("ddd")
                        .unlockedBy("has_bronze_pickaxe", has(ModItems.BRONZE_PICKAXE))
                        .save(output, ResourceKey.create(
                                Registries.RECIPE, Identifier.parse(getSimpleRecipeName(ModItems.GILDED_BRONZE_PICKAXE))));
                shaped(RecipeCategory.MISC, ModItems.GILDED_BRONZE_SHOVEL, 1)
                        .define('d', ModTags.Items.COMMON_GOLD_INGOTS)
                        .define('i', ModItems.BRONZE_SHOVEL)
                        .pattern("ddd")
                        .pattern("did")
                        .pattern("ddd")
                        .unlockedBy("has_bronze_shovel", has(ModItems.BRONZE_SHOVEL))
                        .save(output, ResourceKey.create(
                                Registries.RECIPE, Identifier.parse(getSimpleRecipeName(ModItems.GILDED_BRONZE_SHOVEL))));
                shaped(RecipeCategory.MISC, ModItems.GILDED_BRONZE_SWORD, 1)
                        .define('d', ModTags.Items.COMMON_GOLD_INGOTS)
                        .define('i', ModItems.BRONZE_SWORD)
                        .pattern("ddd")
                        .pattern("did")
                        .pattern("ddd")
                        .unlockedBy("has_bronze_sword", has(ModItems.BRONZE_SWORD))
                        .save(output, ResourceKey.create(
                                Registries.RECIPE, Identifier.parse(getSimpleRecipeName(ModItems.GILDED_BRONZE_SWORD))));
                //endregion

                //region COBALT-STEEL EQUIPMENT
                smithingUpgradeRecipeBuilder(output, ModItems.COBALT_UPGRADE_TEMPLATE, Items.DIAMOND_HELMET,
                        ModItems.COBALT_STEEL_INGOT, RecipeCategory.COMBAT, ModItems.COBALT_STEEL_HELMET,
                        "has_cobalt_upgrade_smithing_template", "has_cobalt_steel_ingot");
                smithingUpgradeRecipeBuilder(output, ModItems.COBALT_UPGRADE_TEMPLATE, Items.DIAMOND_CHESTPLATE,
                        ModItems.COBALT_STEEL_INGOT, RecipeCategory.COMBAT, ModItems.COBALT_STEEL_CHESTPLATE,
                        "has_cobalt_upgrade_smithing_template", "has_cobalt_steel_ingot");
                smithingUpgradeRecipeBuilder(output, ModItems.COBALT_UPGRADE_TEMPLATE, Items.DIAMOND_LEGGINGS,
                        ModItems.COBALT_STEEL_INGOT, RecipeCategory.COMBAT, ModItems.COBALT_STEEL_LEGGINGS,
                        "has_cobalt_upgrade_smithing_template", "has_cobalt_steel_ingot");
                smithingUpgradeRecipeBuilder(output, ModItems.COBALT_UPGRADE_TEMPLATE, Items.DIAMOND_BOOTS,
                        ModItems.COBALT_STEEL_INGOT, RecipeCategory.COMBAT, ModItems.COBALT_STEEL_BOOTS,
                        "has_cobalt_upgrade_smithing_template", "has_cobalt_steel_ingot");

                smithingUpgradeRecipeBuilder(output, ModItems.COBALT_UPGRADE_TEMPLATE, Items.DIAMOND_AXE,
                        ModItems.COBALT_STEEL_INGOT, RecipeCategory.TOOLS, ModItems.COBALT_STEEL_AXE,
                        "has_cobalt_upgrade_smithing_template", "has_cobalt_steel_ingot");
                smithingUpgradeRecipeBuilder(output, ModItems.COBALT_UPGRADE_TEMPLATE, ModItems.DIAMOND_BATTLEAXE,
                        ModItems.COBALT_STEEL_INGOT, RecipeCategory.COMBAT, ModItems.COBALT_STEEL_BATTLEAXE,
                        "has_cobalt_upgrade_smithing_template", "has_cobalt_steel_ingot");
                smithingUpgradeRecipeBuilder(output, ModItems.COBALT_UPGRADE_TEMPLATE, Items.DIAMOND_HOE,
                        ModItems.COBALT_STEEL_INGOT, RecipeCategory.TOOLS, ModItems.COBALT_STEEL_HOE,
                        "has_cobalt_upgrade_smithing_template", "has_cobalt_steel_ingot");
                smithingUpgradeRecipeBuilder(output, ModItems.COBALT_UPGRADE_TEMPLATE, ModItems.DIAMOND_PAXEL,
                        ModItems.COBALT_STEEL_INGOT, RecipeCategory.TOOLS, ModItems.COBALT_STEEL_PAXEL,
                        "has_cobalt_upgrade_smithing_template", "has_cobalt_steel_ingot");
                smithingUpgradeRecipeBuilder(output, ModItems.COBALT_UPGRADE_TEMPLATE, Items.DIAMOND_PICKAXE,
                        ModItems.COBALT_STEEL_INGOT, RecipeCategory.TOOLS, ModItems.COBALT_STEEL_PICKAXE,
                        "has_cobalt_upgrade_smithing_template", "has_cobalt_steel_ingot");
                smithingUpgradeRecipeBuilder(output, ModItems.COBALT_UPGRADE_TEMPLATE, Items.DIAMOND_SHOVEL,
                        ModItems.COBALT_STEEL_INGOT, RecipeCategory.TOOLS, ModItems.COBALT_STEEL_SHOVEL,
                        "has_cobalt_upgrade_smithing_template", "has_cobalt_steel_ingot");
                smithingUpgradeRecipeBuilder(output, ModItems.COBALT_UPGRADE_TEMPLATE, Items.DIAMOND_SWORD,
                        ModItems.COBALT_STEEL_INGOT, RecipeCategory.COMBAT, ModItems.COBALT_STEEL_SWORD,
                        "has_cobalt_upgrade_smithing_template", "has_cobalt_steel_ingot");
                //endregion

                //region INFUSED GEMSTONE EQUIPMENT
                smithingUpgradeRecipeBuilder(output, ModItems.INFUSION_UPGRADE_TEMPLATE, Items.DIAMOND_HELMET,
                        ModItems.INFUSED_GEMSTONE, RecipeCategory.COMBAT, ModItems.INFUSED_GEMSTONE_HELMET,
                        "has_infusion_upgrade_smithing_template", "has_infused_gemstone");
                smithingUpgradeRecipeBuilder(output, ModItems.INFUSION_UPGRADE_TEMPLATE, Items.DIAMOND_CHESTPLATE,
                        ModItems.INFUSED_GEMSTONE, RecipeCategory.COMBAT, ModItems.INFUSED_GEMSTONE_CHESTPLATE,
                        "has_infusion_upgrade_smithing_template", "has_infused_gemstone");
                smithingUpgradeRecipeBuilder(output, ModItems.INFUSION_UPGRADE_TEMPLATE, Items.DIAMOND_LEGGINGS,
                        ModItems.INFUSED_GEMSTONE, RecipeCategory.COMBAT, ModItems.INFUSED_GEMSTONE_LEGGINGS,
                        "has_infusion_upgrade_smithing_template", "has_infused_gemstone");
                smithingUpgradeRecipeBuilder(output, ModItems.INFUSION_UPGRADE_TEMPLATE, Items.DIAMOND_BOOTS,
                        ModItems.INFUSED_GEMSTONE, RecipeCategory.COMBAT, ModItems.INFUSED_GEMSTONE_BOOTS,
                        "has_infusion_upgrade_smithing_template", "has_infused_gemstone");

                smithingUpgradeRecipeBuilder(output, ModItems.INFUSION_UPGRADE_TEMPLATE, Items.DIAMOND_AXE,
                        ModItems.INFUSED_GEMSTONE, RecipeCategory.TOOLS, ModItems.INFUSED_GEMSTONE_AXE,
                        "has_infusion_upgrade_smithing_template", "has_infused_gemstone");
                smithingUpgradeRecipeBuilder(output, ModItems.INFUSION_UPGRADE_TEMPLATE, ModItems.DIAMOND_BATTLEAXE,
                        ModItems.INFUSED_GEMSTONE, RecipeCategory.COMBAT, ModItems.INFUSED_GEMSTONE_BATTLEAXE,
                        "has_infusion_upgrade_smithing_template", "has_infused_gemstone");
                smithingUpgradeRecipeBuilder(output, ModItems.INFUSION_UPGRADE_TEMPLATE, Items.DIAMOND_HOE,
                        ModItems.INFUSED_GEMSTONE, RecipeCategory.TOOLS, ModItems.INFUSED_GEMSTONE_HOE,
                        "has_infusion_upgrade_smithing_template", "has_infused_gemstone");
                smithingUpgradeRecipeBuilder(output, ModItems.INFUSION_UPGRADE_TEMPLATE, ModItems.DIAMOND_PAXEL,
                        ModItems.INFUSED_GEMSTONE, RecipeCategory.TOOLS, ModItems.INFUSED_GEMSTONE_PAXEL,
                        "has_infusion_upgrade_smithing_template", "has_infused_gemstone");
                smithingUpgradeRecipeBuilder(output, ModItems.INFUSION_UPGRADE_TEMPLATE, Items.DIAMOND_PICKAXE,
                        ModItems.INFUSED_GEMSTONE, RecipeCategory.TOOLS, ModItems.INFUSED_GEMSTONE_PICKAXE,
                        "has_infusion_upgrade_smithing_template", "has_infused_gemstone");
                smithingUpgradeRecipeBuilder(output, ModItems.INFUSION_UPGRADE_TEMPLATE, Items.DIAMOND_SHOVEL,
                        ModItems.INFUSED_GEMSTONE, RecipeCategory.TOOLS, ModItems.INFUSED_GEMSTONE_SHOVEL,
                        "has_infusion_upgrade_smithing_template", "has_infused_gemstone");
                smithingUpgradeRecipeBuilder(output, ModItems.INFUSION_UPGRADE_TEMPLATE, Items.DIAMOND_SWORD,
                        ModItems.INFUSED_GEMSTONE, RecipeCategory.COMBAT, ModItems.INFUSED_GEMSTONE_SWORD,
                        "has_infusion_upgrade_smithing_template", "has_infused_gemstone");
                //endregion

                //region TUNGSTEN-CARBIDE EQUIPMENT
                smithingUpgradeRecipeBuilder(output, ModItems.CARBIDE_UPGRADE_TEMPLATE, Items.DIAMOND_HELMET,
                        ModItems.TUNGSTEN_CARBIDE_INGOT, RecipeCategory.COMBAT, ModItems.TUNGSTEN_CARBIDE_HELMET,
                        "has_carbide_upgrade_smithing_template", "has_tungsten_carbide_ingot");
                smithingUpgradeRecipeBuilder(output, ModItems.CARBIDE_UPGRADE_TEMPLATE, Items.DIAMOND_CHESTPLATE,
                        ModItems.TUNGSTEN_CARBIDE_INGOT, RecipeCategory.COMBAT, ModItems.TUNGSTEN_CARBIDE_CHESTPLATE,
                        "has_carbide_upgrade_smithing_template", "has_tungsten_carbide_ingot");
                smithingUpgradeRecipeBuilder(output, ModItems.CARBIDE_UPGRADE_TEMPLATE, Items.DIAMOND_LEGGINGS,
                        ModItems.TUNGSTEN_CARBIDE_INGOT, RecipeCategory.COMBAT, ModItems.TUNGSTEN_CARBIDE_LEGGINGS,
                        "has_carbide_upgrade_smithing_template", "has_tungsten_carbide_ingot");
                smithingUpgradeRecipeBuilder(output, ModItems.CARBIDE_UPGRADE_TEMPLATE, Items.DIAMOND_BOOTS,
                        ModItems.TUNGSTEN_CARBIDE_INGOT, RecipeCategory.COMBAT, ModItems.TUNGSTEN_CARBIDE_BOOTS,
                        "has_carbide_upgrade_smithing_template", "has_tungsten_carbide_ingot");

                smithingUpgradeRecipeBuilder(output, ModItems.CARBIDE_UPGRADE_TEMPLATE, Items.DIAMOND_AXE,
                        ModItems.TUNGSTEN_CARBIDE_INGOT, RecipeCategory.TOOLS, ModItems.TUNGSTEN_CARBIDE_AXE,
                        "has_carbide_upgrade_smithing_template", "has_tungsten_carbide_ingot");
                smithingUpgradeRecipeBuilder(output, ModItems.CARBIDE_UPGRADE_TEMPLATE, ModItems.DIAMOND_BATTLEAXE,
                        ModItems.TUNGSTEN_CARBIDE_INGOT, RecipeCategory.COMBAT, ModItems.TUNGSTEN_CARBIDE_BATTLEAXE,
                        "has_carbide_upgrade_smithing_template", "has_tungsten_carbide_ingot");
                smithingUpgradeRecipeBuilder(output, ModItems.CARBIDE_UPGRADE_TEMPLATE, Items.DIAMOND_HOE,
                        ModItems.TUNGSTEN_CARBIDE_INGOT, RecipeCategory.TOOLS, ModItems.TUNGSTEN_CARBIDE_HOE,
                        "has_carbide_upgrade_smithing_template", "has_tungsten_carbide_ingot");
                smithingUpgradeRecipeBuilder(output, ModItems.CARBIDE_UPGRADE_TEMPLATE, ModItems.DIAMOND_PAXEL,
                        ModItems.TUNGSTEN_CARBIDE_INGOT, RecipeCategory.TOOLS, ModItems.TUNGSTEN_CARBIDE_PAXEL,
                        "has_carbide_upgrade_smithing_template", "has_tungsten_carbide_ingot");
                smithingUpgradeRecipeBuilder(output, ModItems.CARBIDE_UPGRADE_TEMPLATE, Items.DIAMOND_PICKAXE,
                        ModItems.TUNGSTEN_CARBIDE_INGOT, RecipeCategory.TOOLS, ModItems.TUNGSTEN_CARBIDE_PICKAXE,
                        "has_carbide_upgrade_smithing_template", "has_tungsten_carbide_ingot");
                smithingUpgradeRecipeBuilder(output, ModItems.CARBIDE_UPGRADE_TEMPLATE, Items.DIAMOND_SHOVEL,
                        ModItems.TUNGSTEN_CARBIDE_INGOT, RecipeCategory.TOOLS, ModItems.TUNGSTEN_CARBIDE_SHOVEL,
                        "has_carbide_upgrade_smithing_template", "has_tungsten_carbide_ingot");
                smithingUpgradeRecipeBuilder(output, ModItems.CARBIDE_UPGRADE_TEMPLATE, Items.DIAMOND_SWORD,
                        ModItems.TUNGSTEN_CARBIDE_INGOT, RecipeCategory.COMBAT, ModItems.TUNGSTEN_CARBIDE_SWORD,
                        "has_carbide_upgrade_smithing_template", "has_tungsten_carbide_ingot");
                //endregion
            }

            /**
             * Helper to automatically generate shaped recipes for the four armor slots.
             * @param exporter RecipeExporter that exports the recipe
             * @param slot This armor piece's EquipmentSlot
             * @param ingredient Crafting ingredient
             * @param result Crafting result
             * @param hasString String in "has_[item]" format that defines how the recipe is unlocked
             */
            private void armorRecipeBuilder(RecipeOutput exporter, EquipmentSlot slot,
                                            TagKey<Item> ingredient, Item result, String hasString) {
                switch (slot) {
                    case HEAD -> shaped(RecipeCategory.COMBAT, result, 1)
                            .define('d', ingredient)
                            .pattern("ddd")
                            .pattern("d d")
                            .unlockedBy(hasString, has(ingredient))
                            .save(exporter, ResourceKey.create(
                                    Registries.RECIPE, Identifier.parse(getSimpleRecipeName(result))));
                    case CHEST -> shaped(RecipeCategory.COMBAT, result, 1)
                            .define('d', ingredient)
                            .pattern("d d")
                            .pattern("ddd")
                            .pattern("ddd")
                            .unlockedBy(hasString, has(ingredient))
                            .save(exporter, ResourceKey.create(
                                    Registries.RECIPE, Identifier.parse(getSimpleRecipeName(result))));
                    case LEGS -> shaped(RecipeCategory.COMBAT, result, 1)
                            .define('d', ingredient)
                            .pattern("ddd")
                            .pattern("d d")
                            .pattern("d d")
                            .unlockedBy(hasString, has(ingredient))
                            .save(exporter, ResourceKey.create(
                                    Registries.RECIPE, Identifier.parse(getSimpleRecipeName(result))));
                    case FEET -> shaped(RecipeCategory.COMBAT, result, 1)
                            .define('d', ingredient)
                            .pattern("d d")
                            .pattern("d d")
                            .unlockedBy(hasString, has(ingredient))
                            .save(exporter, ResourceKey.create(
                                    Registries.RECIPE, Identifier.parse(getSimpleRecipeName(result))));
                    default -> {
                        //this will never be reached, can be left empty
                    }
                }
            }

            /**
             * Helper to automatically generate shaped recipes for axe, battleaxe, hoe, paxel, pickaxe, shovel, and sword.
             * @param exporter RecipeExporter that exports the recipe
             * @param toolType Tool type of the item being crafted
             * @param ingredient Crafting ingredient
             * @param result Crafting result
             * @param hasString String in "has_[item]" format that defines how the recipe is unlocked
             */
            private void toolRecipeBuilder(RecipeOutput exporter, ToolType toolType,
                                           TagKey<Item> ingredient, Item result, String hasString) {
                switch (toolType) {
                    case AXE -> shaped(RecipeCategory.TOOLS, result, 1)
                            .define('d', ingredient)
                            .define('i', ModTags.Items.COMMON_WOODEN_RODS)
                            .pattern("dd")
                            .pattern("di")
                            .pattern(" i")
                            .unlockedBy(hasString, has(ingredient))
                            .save(exporter, ResourceKey.create(
                                    Registries.RECIPE, Identifier.parse(getSimpleRecipeName(result))));
                    case BATTLEAXE -> shaped(RecipeCategory.COMBAT, result, 1)
                            .define('d', ingredient)
                            .define('i', ModTags.Items.COMMON_WOODEN_RODS)
                            .pattern("ddd")
                            .pattern("did")
                            .pattern(" i ")
                            .unlockedBy(hasString, has(ingredient))
                            .save(exporter, ResourceKey.create(
                                    Registries.RECIPE, Identifier.parse(getSimpleRecipeName(result))));
                    case HOE -> shaped(RecipeCategory.TOOLS, result, 1)
                            .define('d', ingredient)
                            .define('i', ModTags.Items.COMMON_WOODEN_RODS)
                            .pattern("dd")
                            .pattern(" i")
                            .pattern(" i")
                            .unlockedBy(hasString, has(ingredient))
                            .save(exporter, ResourceKey.create(
                                    Registries.RECIPE, Identifier.parse(getSimpleRecipeName(result))));
                    case PAXEL -> shaped(RecipeCategory.TOOLS, result, 1)
                            .define('d', ingredient)
                            .define('i', ModTags.Items.COMMON_WOODEN_RODS)
                            .pattern("ddd")
                            .pattern("di ")
                            .pattern(" i ")
                            .unlockedBy(hasString, has(ingredient))
                            .save(exporter, ResourceKey.create(
                                    Registries.RECIPE, Identifier.parse(getSimpleRecipeName(result))));
                    case PICKAXE -> shaped(RecipeCategory.TOOLS, result, 1)
                            .define('d', ingredient)
                            .define('i', ModTags.Items.COMMON_WOODEN_RODS)
                            .pattern("ddd")
                            .pattern(" i ")
                            .pattern(" i ")
                            .unlockedBy(hasString, has(ingredient))
                            .save(exporter, ResourceKey.create(
                                    Registries.RECIPE, Identifier.parse(getSimpleRecipeName(result))));
                    case SHOVEL -> shaped(RecipeCategory.TOOLS, result, 1)
                            .define('d', ingredient)
                            .define('i', ModTags.Items.COMMON_WOODEN_RODS)
                            .pattern("d")
                            .pattern("i")
                            .pattern("i")
                            .unlockedBy(hasString, has(ingredient))
                            .save(exporter, ResourceKey.create(
                                    Registries.RECIPE, Identifier.parse(getSimpleRecipeName(result))));
                    case SWORD -> shaped(RecipeCategory.COMBAT, result, 1)
                            .define('d', ingredient)
                            .define('i', ModTags.Items.COMMON_WOODEN_RODS)
                            .pattern("d")
                            .pattern("d")
                            .pattern("i")
                            .unlockedBy(hasString, has(ingredient))
                            .save(exporter, ResourceKey.create(
                                    Registries.RECIPE, Identifier.parse(getSimpleRecipeName(result))));
                    //default case not needed?
                }
            }

            /**
             * Helper to automatically generate smithing recipes (1.20+). NOTE: Smithing recipes are
             *  specifically item-to-item, and should never use tags.
             * @param exporter RecipeExporter that exports the recipe
             * @param template Required upgrade template Item
             * @param upgradeTarget Item being upgraded
             * @param ingredient Upgrade ingredient Item
             * @param category Recipe category
             * @param result Smithing result Item
             * @param hasStringTemplate String in "has_[item]" format corresponding to the upgrade template
             * @param hasStringIngredient String in "has_[item]" format corresponding to the upgrade ingredient
             */
            private void smithingUpgradeRecipeBuilder(RecipeOutput exporter, Item template, Item upgradeTarget,
                                                      Item ingredient, RecipeCategory category, Item result,
                                                      String hasStringTemplate, String hasStringIngredient) {
                SmithingTransformRecipeBuilder.smithing(Ingredient.of(template), Ingredient.of(upgradeTarget),
                                Ingredient.of(ingredient), category, result)
                        .unlocks(hasStringTemplate, has(template))
                        .unlocks(hasStringIngredient, has(ingredient))
                        .save(exporter, Identifier.parse(getSimpleRecipeName(result)) + "_smithing");
            }
        };
    }
}
