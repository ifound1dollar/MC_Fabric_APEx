package net.dollar.simplegear.datagen;

import net.dollar.simplegear.ModMain;
import net.dollar.simplegear.block.ModBlocks;
import net.dollar.simplegear.item.ModItems;
import net.dollar.simplegear.util.ModTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.server.recipe.*;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

import java.util.List;
import java.util.function.Consumer;

public class ModRecipeProvider extends FabricRecipeProvider {
    private enum ToolType { AXE, BATTLEAXE, HOE, PAXEL, PICKAXE, SHOVEL, SWORD }

    private static final List<ItemConvertible> COBALT_SMELTABLES = List.of(
            ModBlocks.COBALT_ORE, ModBlocks.DEEPSLATE_COBALT_ORE);
    private static final List<ItemConvertible> PHOSPHATE_SMELTABLES = List.of(
            ModBlocks.PHOSPHATE_ORE, ModBlocks.DEEPSLATE_PHOSPHATE_ORE);
    private static final List<ItemConvertible> RUBY_SMELTABLES = List.of(
            ModBlocks.RUBY_ORE, ModBlocks.DEEPSLATE_RUBY_ORE);
    private static final List<ItemConvertible> SAPPHIRE_SMELTABLES = List.of(
            ModBlocks.SAPPHIRE_ORE, ModBlocks.DEEPSLATE_SAPPHIRE_ORE);
    private static final List<ItemConvertible> TIN_SMELTABLES = List.of(
            ModItems.RAW_TIN, ModBlocks.TIN_ORE, ModBlocks.DEEPSLATE_TIN_ORE);
    private static final List<ItemConvertible> TUNGSTEN_SMELTABLES = List.of(
            ModItems.RAW_TUNGSTEN, ModBlocks.TUNGSTEN_ORE, ModBlocks.DEEPSLATE_TUNGSTEN_ORE);



    public ModRecipeProvider(FabricDataOutput output) {
        super(output);
    }



    @Override
    public void generate(Consumer<RecipeJsonProvider> exporter) {
        //SMELTING AND BLASTING
        offerSmelting(exporter, COBALT_SMELTABLES, RecipeCategory.MISC, ModItems.COBALT_SHARD,
                0.9f, 200, "cobalt_shard"); //Diamond is 1.0
        offerBlasting(exporter, COBALT_SMELTABLES, RecipeCategory.MISC, ModItems.COBALT_SHARD,
                0.9f, 100, "cobalt_shard");
        offerSmelting(exporter, PHOSPHATE_SMELTABLES, RecipeCategory.MISC, ModItems.PHOSPHATE_POWDER,
                0.2f, 200, "phosphate_powder"); //Coal is 0.1
        offerBlasting(exporter, PHOSPHATE_SMELTABLES, RecipeCategory.MISC, ModItems.PHOSPHATE_POWDER,
                0.2f, 100, "phosphate_powder");
        offerSmelting(exporter, RUBY_SMELTABLES, RecipeCategory.MISC, ModItems.RUBY,
                1.2f, 200, "ruby");         //Diamond/Emerald is 1.0
        offerBlasting(exporter, RUBY_SMELTABLES, RecipeCategory.MISC, ModItems.RUBY,
                1.2f, 100, "ruby");
        offerSmelting(exporter, SAPPHIRE_SMELTABLES, RecipeCategory.MISC, ModItems.SAPPHIRE,
                1.2f, 200, "sapphire");     //Diamond/Emerald is 1.0
        offerBlasting(exporter, SAPPHIRE_SMELTABLES, RecipeCategory.MISC, ModItems.SAPPHIRE,
                1.2f, 100, "sapphire");
        offerSmelting(exporter, TIN_SMELTABLES, RecipeCategory.MISC, ModItems.TIN_INGOT,
                0.7f, 200, "tin_ingot");    //Iron is 0.7
        offerBlasting(exporter, TIN_SMELTABLES, RecipeCategory.MISC, ModItems.TIN_INGOT,
                0.7f, 100, "tin_ingot");
        offerSmelting(exporter, TUNGSTEN_SMELTABLES, RecipeCategory.MISC, ModItems.TUNGSTEN_INGOT,
                1.0f, 200, "tungsten_ingot");   //Diamond is 1.0
        offerBlasting(exporter, TUNGSTEN_SMELTABLES, RecipeCategory.MISC, ModItems.TUNGSTEN_INGOT,
                1.0f, 100, "tungsten_ingot");

        //STORAGE BLOCKS
        //NOTE: FIRST IS FOR BLOCK->ITEM, SECOND IS FOR ITEM->BLOCK
        offerReversibleCompactingRecipes(exporter, RecipeCategory.BUILDING_BLOCKS, ModItems.RUBY,
                RecipeCategory.DECORATIONS, ModBlocks.RUBY_BLOCK);
        offerReversibleCompactingRecipes(exporter, RecipeCategory.BUILDING_BLOCKS, ModItems.SAPPHIRE,
                RecipeCategory.DECORATIONS, ModBlocks.SAPPHIRE_BLOCK);
        offerReversibleCompactingRecipes(exporter, RecipeCategory.BUILDING_BLOCKS, Items.AMETHYST_SHARD,
                RecipeCategory.DECORATIONS, ModBlocks.DECORATIVE_AMETHYST_BLOCK);
        offerReversibleCompactingRecipes(exporter, RecipeCategory.BUILDING_BLOCKS, ModItems.TIN_INGOT,
                RecipeCategory.DECORATIONS, ModBlocks.TIN_BLOCK);
        offerReversibleCompactingRecipes(exporter, RecipeCategory.BUILDING_BLOCKS, ModItems.RAW_TIN,
                RecipeCategory.DECORATIONS, ModBlocks.RAW_TIN_BLOCK);
        offerReversibleCompactingRecipes(exporter, RecipeCategory.BUILDING_BLOCKS, ModItems.TUNGSTEN_INGOT,
                RecipeCategory.DECORATIONS, ModBlocks.TUNGSTEN_BLOCK);
        offerReversibleCompactingRecipes(exporter, RecipeCategory.BUILDING_BLOCKS, ModItems.RAW_TUNGSTEN,
                RecipeCategory.DECORATIONS, ModBlocks.RAW_TUNGSTEN_BLOCK);
        offerReversibleCompactingRecipes(exporter, RecipeCategory.BUILDING_BLOCKS, ModItems.BRONZE_INGOT,
                RecipeCategory.DECORATIONS, ModBlocks.BRONZE_BLOCK);
        offerReversibleCompactingRecipes(exporter, RecipeCategory.BUILDING_BLOCKS, ModItems.STEEL_INGOT,
                RecipeCategory.DECORATIONS, ModBlocks.STEEL_BLOCK);

        //SPECIAL TIN RECIPES
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, Items.BUCKET, 1)
                .input('d', ModTags.Items.COMMON_TIN_INGOTS)
                .pattern("d d")
                .pattern(" d ")
                .criterion("has_tin_ingot", conditionsFromTag(ModTags.Items.COMMON_TIN_INGOTS))
                .offerTo(exporter, new Identifier(ModMain.MOD_ID, "bucket_from_tin"));
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, Items.SHEARS, 1)
                .input('d', ModTags.Items.COMMON_TIN_INGOTS)
                .pattern(" d")
                .pattern("d ")
                .criterion("has_tin_ingot", conditionsFromTag(ModTags.Items.COMMON_TIN_INGOTS))
                .offerTo(exporter, new Identifier(ModMain.MOD_ID, "shears_from_tin_forward"));
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, Items.SHEARS, 1)
                .input('d', ModTags.Items.COMMON_TIN_INGOTS)
                .pattern("d ")
                .pattern(" d")
                .criterion("has_tin_ingot", conditionsFromTag(ModTags.Items.COMMON_TIN_INGOTS))
                .offerTo(exporter, new Identifier(ModMain.MOD_ID, "shears_from_tin_backward"));

        //COMPOUNDS AND ENDGAME INGREDIENT ITEMS (shapeless)
        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.BRONZE_COMPOUND, 3)
                .input(Ingredient.fromTag(ModTags.Items.COMMON_COPPER_INGOTS), 3)
                .input(Ingredient.fromTag(ModTags.Items.COMMON_TIN_INGOTS), 1)
                .criterion("has_copper_ingot", conditionsFromTag(ModTags.Items.COMMON_COPPER_INGOTS))
                .criterion("has_tin_ingot", conditionsFromTag(ModTags.Items.COMMON_TIN_INGOTS))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.BRONZE_COMPOUND)));
        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.STEEL_COMPOUND, 1)
                .input(Ingredient.fromTag(ModTags.Items.COMMON_IRON_INGOTS), 1)
                .input(Items.COAL, 1)
                .criterion("has_iron_ingot", conditionsFromTag(ModTags.Items.COMMON_IRON_INGOTS))
                .criterion("has_coal", conditionsFromItem(Items.COAL))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.STEEL_COMPOUND)));
        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.COMPOUND_GEMSTONE, 1)
                .input(Ingredient.fromTag(ModTags.Items.COMMON_AMETHYST), 1)
                .input(Ingredient.fromTag(ModTags.Items.COMMON_DIAMONDS), 1)
                .input(Ingredient.fromTag(ModTags.Items.COMMON_EMERALDS), 1)
                .input(Ingredient.fromTag(ModTags.Items.COMMON_RUBIES), 1)
                .input(Ingredient.fromTag(ModTags.Items.COMMON_SAPPHIRES), 1)
                .criterion("has_amethyst_shard", conditionsFromTag(ModTags.Items.COMMON_AMETHYST))
                .criterion("has_diamond", conditionsFromTag(ModTags.Items.COMMON_DIAMONDS))
                .criterion("has_emerald", conditionsFromTag(ModTags.Items.COMMON_EMERALDS))
                .criterion("has_ruby", conditionsFromTag(ModTags.Items.COMMON_RUBIES))
                .criterion("has_sapphire", conditionsFromTag(ModTags.Items.COMMON_SAPPHIRES))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.COMPOUND_GEMSTONE)));
        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.INFUSED_GEMSTONE, 1)
                .input(ModItems.COMPOUND_GEMSTONE, 1)
                .input(ModItems.HANDFUL_OF_STARDUST, 1)
                .criterion("has_compound_gemstone", conditionsFromItem(ModItems.COMPOUND_GEMSTONE))
                .criterion("has_handful_of_stardust", conditionsFromItem(ModItems.HANDFUL_OF_STARDUST))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.INFUSED_GEMSTONE)));
        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.COBALT_STEEL_INGOT, 1)
                .input(Ingredient.fromTag(ModTags.Items.COMMON_STEEL_INGOTS), 4)
                .input(ModItems.COBALT_SHARD, 4)
                .input(ModItems.MOLTEN_CORE, 1)
                .criterion("has_steel_ingot", conditionsFromTag(ModTags.Items.COMMON_STEEL_INGOTS))
                .criterion("has_cobalt_shard", conditionsFromItem(ModItems.COBALT_SHARD))
                .criterion("has_molten_core", conditionsFromItem(ModItems.MOLTEN_CORE))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.COBALT_STEEL_INGOT)));
        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.TUNGSTEN_CARBIDE_INGOT, 1)
                .input(Ingredient.fromTag(ModTags.Items.COMMON_TUNGSTEN_INGOTS), 4)
                .input(Items.COAL, 4)
                .input(ModItems.MOLTEN_CORE, 1)
                .criterion("has_tungsten_ingot", conditionsFromTag(ModTags.Items.COMMON_TUNGSTEN_INGOTS))
                .criterion("has_coal", conditionsFromItem(Items.COAL))
                .criterion("has_molten_core", conditionsFromItem(ModItems.MOLTEN_CORE))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.TUNGSTEN_CARBIDE_INGOT)));

        //UPGRADE TEMPLATES
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE, 1)
                .input('d', ModItems.BASIC_UPGRADE_TEMPLATE)
                .input('i', ModTags.Items.COMMON_NETHERRACKS)
                .input('n', ModTags.Items.COMMON_DIAMONDS)
                .pattern(" d ")
                .pattern("nin")
                .pattern(" n ")
                .criterion("has_basic_upgrade_template", conditionsFromItem(ModItems.BASIC_UPGRADE_TEMPLATE))
                .criterion("has_diamond", conditionsFromTag(ModTags.Items.COMMON_DIAMONDS))
                .offerTo(exporter, new Identifier(ModMain.MOD_ID, "netherite_upgrade_smithing_template_from_basic"));
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.COBALT_UPGRADE_TEMPLATE, 2)
                .input('d', ModItems.COBALT_UPGRADE_TEMPLATE)
                .input('i', ModTags.Items.COMMON_STONES)
                .input('n', ModTags.Items.COMMON_STEEL_INGOTS)
                .pattern("ndn")
                .pattern("nin")
                .pattern("nnn")
                .criterion("has_cobalt_upgrade_smithing_template", conditionsFromItem(ModItems.COBALT_UPGRADE_TEMPLATE))
                .criterion("has_steel_ingot", conditionsFromTag(ModTags.Items.COMMON_STEEL_INGOTS))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.COBALT_UPGRADE_TEMPLATE)));
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.COBALT_UPGRADE_TEMPLATE, 1)
                .input('d', ModItems.BASIC_UPGRADE_TEMPLATE)
                .input('i', ModTags.Items.COMMON_STONES)
                .input('n', ModTags.Items.COMMON_STEEL_INGOTS)
                .pattern(" d ")
                .pattern("nin")
                .pattern(" n ")
                .criterion("has_basic_upgrade_template", conditionsFromItem(ModItems.BASIC_UPGRADE_TEMPLATE))
                .criterion("has_steel_ingot", conditionsFromTag(ModTags.Items.COMMON_STEEL_INGOTS))
                .offerTo(exporter, new Identifier(ModMain.MOD_ID, "cobalt_upgrade_smithing_template_from_basic"));
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.INFUSION_UPGRADE_TEMPLATE, 2)
                .input('d', ModItems.INFUSION_UPGRADE_TEMPLATE)
                .input('i', ModTags.Items.COMMON_DEEPSLATES)
                .input('n', ModTags.Items.COMMON_DIAMONDS)
                .pattern("ndn")
                .pattern("nin")
                .pattern("nnn")
                .criterion("has_infusion_upgrade_smithing_template", conditionsFromItem(ModItems.INFUSION_UPGRADE_TEMPLATE))
                .criterion("has_diamond", conditionsFromTag(ModTags.Items.COMMON_DIAMONDS))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.INFUSION_UPGRADE_TEMPLATE)));
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.INFUSION_UPGRADE_TEMPLATE, 1)
                .input('d', ModItems.BASIC_UPGRADE_TEMPLATE)
                .input('i', ModTags.Items.COMMON_DEEPSLATES)
                .input('n', ModTags.Items.COMMON_DIAMONDS)
                .pattern(" d ")
                .pattern("nin")
                .pattern(" n ")
                .criterion("has_basic_upgrade_template", conditionsFromItem(ModItems.BASIC_UPGRADE_TEMPLATE))
                .criterion("has_diamond", conditionsFromTag(ModTags.Items.COMMON_DIAMONDS))
                .offerTo(exporter, new Identifier(ModMain.MOD_ID, "infusion_upgrade_smithing_template_from_basic"));
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.CARBIDE_UPGRADE_TEMPLATE, 2)
                .input('d', ModItems.CARBIDE_UPGRADE_TEMPLATE)
                .input('i', Items.OBSIDIAN)
                .input('n', ModTags.Items.COMMON_TUNGSTEN_INGOTS)
                .pattern("ndn")
                .pattern("nin")
                .pattern("nnn")
                .criterion("has_carbide_upgrade_smithing_template", conditionsFromItem(ModItems.CARBIDE_UPGRADE_TEMPLATE))
                .criterion("has_tungsten_ingot", conditionsFromTag(ModTags.Items.COMMON_TUNGSTEN_INGOTS))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.CARBIDE_UPGRADE_TEMPLATE)));
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.CARBIDE_UPGRADE_TEMPLATE, 1)
                .input('d', ModItems.BASIC_UPGRADE_TEMPLATE)
                .input('i', Items.OBSIDIAN)
                .input('n', ModTags.Items.COMMON_TUNGSTEN_INGOTS)
                .pattern(" d ")
                .pattern("nin")
                .pattern(" n ")
                .criterion("has_basic_upgrade_template", conditionsFromItem(ModItems.BASIC_UPGRADE_TEMPLATE))
                .criterion("has_tungsten_ingot", conditionsFromTag(ModTags.Items.COMMON_TUNGSTEN_INGOTS))
                .offerTo(exporter, new Identifier(ModMain.MOD_ID, "carbide_upgrade_smithing_template_from_basic"));

        //VANILLA TIER BATTLEAXES AND PAXELS
        toolRecipeBuilder(exporter, ToolType.BATTLEAXE, ModTags.Items.COMMON_DIAMONDS, ModItems.DIAMOND_BATTLEAXE,
                "has_diamond");
        toolRecipeBuilder(exporter, ToolType.PAXEL, ModTags.Items.COMMON_DIAMONDS, ModItems.DIAMOND_PAXEL,
                "has_diamond");
        smithingUpgradeRecipeBuilder(exporter, Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE, ModItems.DIAMOND_BATTLEAXE,
                Items.NETHERITE_INGOT, RecipeCategory.COMBAT, ModItems.NETHERITE_BATTLEAXE,
                "has_netherite_upgrade_smithing_template", "has_netherite_ingot");
        smithingUpgradeRecipeBuilder(exporter, Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE, ModItems.DIAMOND_PAXEL,
                Items.NETHERITE_INGOT, RecipeCategory.TOOLS, ModItems.NETHERITE_PAXEL,
                "has_netherite_upgrade_smithing_template", "has_netherite_ingot");

        //BOWS
        smithingUpgradeRecipeBuilder(exporter, ModItems.COBALT_UPGRADE_TEMPLATE, Items.BOW,
                ModItems.COBALT_STEEL_INGOT, RecipeCategory.COMBAT, ModItems.COBALT_STEEL_BOW,
                "has_cobalt_upgrade_smithing_template", "has_cobalt_steel_ingot");
        smithingUpgradeRecipeBuilder(exporter, ModItems.INFUSION_UPGRADE_TEMPLATE, Items.BOW,
                ModItems.INFUSED_GEMSTONE, RecipeCategory.COMBAT, ModItems.INFUSED_GEMSTONE_BOW,
                "has_infusion_upgrade_smithing_template", "has_infused_gemstone");
        smithingUpgradeRecipeBuilder(exporter, ModItems.CARBIDE_UPGRADE_TEMPLATE, Items.BOW,
                ModItems.TUNGSTEN_CARBIDE_INGOT, RecipeCategory.COMBAT, ModItems.TUNGSTEN_CARBIDE_BOW,
                "has_carbide_upgrade_smithing_template", "has_tungsten_carbide_ingot");

        //CROSSBOWS
        smithingUpgradeRecipeBuilder(exporter, ModItems.COBALT_UPGRADE_TEMPLATE, Items.CROSSBOW,
                ModItems.COBALT_STEEL_INGOT, RecipeCategory.COMBAT, ModItems.COBALT_STEEL_CROSSBOW,
                "has_cobalt_upgrade_smithing_template", "has_cobalt_steel_ingot");
        smithingUpgradeRecipeBuilder(exporter, ModItems.INFUSION_UPGRADE_TEMPLATE, Items.CROSSBOW,
                ModItems.INFUSED_GEMSTONE, RecipeCategory.COMBAT, ModItems.INFUSED_GEMSTONE_CROSSBOW,
                "has_infusion_upgrade_smithing_template", "has_infused_gemstone");
        smithingUpgradeRecipeBuilder(exporter, ModItems.CARBIDE_UPGRADE_TEMPLATE, Items.CROSSBOW,
                ModItems.TUNGSTEN_CARBIDE_INGOT, RecipeCategory.COMBAT, ModItems.TUNGSTEN_CARBIDE_CROSSBOW,
                "has_carbide_upgrade_smithing_template", "has_tungsten_carbide_ingot");

        //BRONZE EQUIPMENT
        armorRecipeBuilder(exporter, EquipmentSlot.HEAD, ModTags.Items.COMMON_BRONZE_INGOTS, ModItems.BRONZE_HELMET,
                "has_bronze_ingot");
        armorRecipeBuilder(exporter, EquipmentSlot.CHEST, ModTags.Items.COMMON_BRONZE_INGOTS, ModItems.BRONZE_CHESTPLATE,
                "has_bronze_ingot");
        armorRecipeBuilder(exporter, EquipmentSlot.LEGS, ModTags.Items.COMMON_BRONZE_INGOTS, ModItems.BRONZE_LEGGINGS,
                "has_bronze_ingot");
        armorRecipeBuilder(exporter, EquipmentSlot.FEET, ModTags.Items.COMMON_BRONZE_INGOTS, ModItems.BRONZE_BOOTS,
                "has_bronze_ingot");

        toolRecipeBuilder(exporter, ToolType.AXE, ModTags.Items.COMMON_BRONZE_INGOTS, ModItems.BRONZE_AXE,
                "has_bronze_ingot");
        toolRecipeBuilder(exporter, ToolType.HOE, ModTags.Items.COMMON_BRONZE_INGOTS, ModItems.BRONZE_HOE,
                "has_bronze_ingot");
        toolRecipeBuilder(exporter, ToolType.PICKAXE, ModTags.Items.COMMON_BRONZE_INGOTS, ModItems.BRONZE_PICKAXE,
                "has_bronze_ingot");
        toolRecipeBuilder(exporter, ToolType.SHOVEL, ModTags.Items.COMMON_BRONZE_INGOTS, ModItems.BRONZE_SHOVEL,
                "has_bronze_ingot");
        toolRecipeBuilder(exporter, ToolType.SWORD, ModTags.Items.COMMON_BRONZE_INGOTS, ModItems.BRONZE_SWORD,
                "has_bronze_ingot");

        //GILDED BRONZE EQUIPMENT
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.GILDED_BRONZE_HELMET, 1)
                .input('d', ModTags.Items.COMMON_GOLD_INGOTS)
                .input('i', ModItems.BRONZE_HELMET)
                .pattern("ddd")
                .pattern("did")
                .pattern("ddd")
                .criterion("has_bronze_helmet", conditionsFromItem(ModItems.BRONZE_HELMET))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.GILDED_BRONZE_HELMET)));
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.GILDED_BRONZE_CHESTPLATE, 1)
                .input('d', ModTags.Items.COMMON_GOLD_INGOTS)
                .input('i', ModItems.BRONZE_CHESTPLATE)
                .pattern("ddd")
                .pattern("did")
                .pattern("ddd")
                .criterion("has_bronze_chestplate", conditionsFromItem(ModItems.BRONZE_CHESTPLATE))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.GILDED_BRONZE_CHESTPLATE)));
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.GILDED_BRONZE_LEGGINGS, 1)
                .input('d', ModTags.Items.COMMON_GOLD_INGOTS)
                .input('i', ModItems.BRONZE_LEGGINGS)
                .pattern("ddd")
                .pattern("did")
                .pattern("ddd")
                .criterion("has_bronze_leggings", conditionsFromItem(ModItems.BRONZE_LEGGINGS))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.GILDED_BRONZE_LEGGINGS)));
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.GILDED_BRONZE_BOOTS, 1)
                .input('d', ModTags.Items.COMMON_GOLD_INGOTS)
                .input('i', ModItems.BRONZE_BOOTS)
                .pattern("ddd")
                .pattern("did")
                .pattern("ddd")
                .criterion("has_bronze_boots", conditionsFromItem(ModItems.BRONZE_BOOTS))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.GILDED_BRONZE_BOOTS)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.GILDED_BRONZE_AXE, 1)
                .input('d', ModTags.Items.COMMON_GOLD_INGOTS)
                .input('i', ModItems.BRONZE_AXE)
                .pattern("ddd")
                .pattern("did")
                .pattern("ddd")
                .criterion("has_bronze_axe", conditionsFromItem(ModItems.BRONZE_AXE))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.GILDED_BRONZE_AXE)));
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.GILDED_BRONZE_HOE, 1)
                .input('d', ModTags.Items.COMMON_GOLD_INGOTS)
                .input('i', ModItems.BRONZE_HOE)
                .pattern("ddd")
                .pattern("did")
                .pattern("ddd")
                .criterion("has_bronze_hoe", conditionsFromItem(ModItems.BRONZE_HOE))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.GILDED_BRONZE_HOE)));
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.GILDED_BRONZE_PICKAXE, 1)
                .input('d', ModTags.Items.COMMON_GOLD_INGOTS)
                .input('i', ModItems.BRONZE_PICKAXE)
                .pattern("ddd")
                .pattern("did")
                .pattern("ddd")
                .criterion("has_bronze_pickaxe", conditionsFromItem(ModItems.BRONZE_PICKAXE))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.GILDED_BRONZE_PICKAXE)));
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.GILDED_BRONZE_SHOVEL, 1)
                .input('d', ModTags.Items.COMMON_GOLD_INGOTS)
                .input('i', ModItems.BRONZE_SHOVEL)
                .pattern("ddd")
                .pattern("did")
                .pattern("ddd")
                .criterion("has_bronze_shovel", conditionsFromItem(ModItems.BRONZE_SHOVEL))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.GILDED_BRONZE_SHOVEL)));
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.GILDED_BRONZE_SWORD, 1)
                .input('d', ModTags.Items.COMMON_GOLD_INGOTS)
                .input('i', ModItems.BRONZE_SWORD)
                .pattern("ddd")
                .pattern("did")
                .pattern("ddd")
                .criterion("has_bronze_sword", conditionsFromItem(ModItems.BRONZE_SWORD))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.GILDED_BRONZE_SWORD)));

        //COBALT-STEEL EQUIPMENT
        smithingUpgradeRecipeBuilder(exporter, ModItems.COBALT_UPGRADE_TEMPLATE, Items.DIAMOND_HELMET,
                ModItems.COBALT_STEEL_INGOT, RecipeCategory.COMBAT, ModItems.COBALT_STEEL_HELMET,
                "has_cobalt_upgrade_smithing_template", "has_cobalt_steel_ingot");
        smithingUpgradeRecipeBuilder(exporter, ModItems.COBALT_UPGRADE_TEMPLATE, Items.DIAMOND_CHESTPLATE,
                ModItems.COBALT_STEEL_INGOT, RecipeCategory.COMBAT, ModItems.COBALT_STEEL_CHESTPLATE,
                "has_cobalt_upgrade_smithing_template", "has_cobalt_steel_ingot");
        smithingUpgradeRecipeBuilder(exporter, ModItems.COBALT_UPGRADE_TEMPLATE, Items.DIAMOND_LEGGINGS,
                ModItems.COBALT_STEEL_INGOT, RecipeCategory.COMBAT, ModItems.COBALT_STEEL_LEGGINGS,
                "has_cobalt_upgrade_smithing_template", "has_cobalt_steel_ingot");
        smithingUpgradeRecipeBuilder(exporter, ModItems.COBALT_UPGRADE_TEMPLATE, Items.DIAMOND_BOOTS,
                ModItems.COBALT_STEEL_INGOT, RecipeCategory.COMBAT, ModItems.COBALT_STEEL_BOOTS,
                "has_cobalt_upgrade_smithing_template", "has_cobalt_steel_ingot");

        smithingUpgradeRecipeBuilder(exporter, ModItems.COBALT_UPGRADE_TEMPLATE, Items.DIAMOND_AXE,
                ModItems.COBALT_STEEL_INGOT, RecipeCategory.TOOLS, ModItems.COBALT_STEEL_AXE,
                "has_cobalt_upgrade_smithing_template", "has_cobalt_steel_ingot");
        smithingUpgradeRecipeBuilder(exporter, ModItems.COBALT_UPGRADE_TEMPLATE, ModItems.DIAMOND_BATTLEAXE,
                ModItems.COBALT_STEEL_INGOT, RecipeCategory.COMBAT, ModItems.COBALT_STEEL_BATTLEAXE,
                "has_cobalt_upgrade_smithing_template", "has_cobalt_steel_ingot");
        smithingUpgradeRecipeBuilder(exporter, ModItems.COBALT_UPGRADE_TEMPLATE, Items.DIAMOND_HOE,
                ModItems.COBALT_STEEL_INGOT, RecipeCategory.TOOLS, ModItems.COBALT_STEEL_HOE,
                "has_cobalt_upgrade_smithing_template", "has_cobalt_steel_ingot");
        smithingUpgradeRecipeBuilder(exporter, ModItems.COBALT_UPGRADE_TEMPLATE, ModItems.DIAMOND_PAXEL,
                ModItems.COBALT_STEEL_INGOT, RecipeCategory.TOOLS, ModItems.COBALT_STEEL_PAXEL,
                "has_cobalt_upgrade_smithing_template", "has_cobalt_steel_ingot");
        smithingUpgradeRecipeBuilder(exporter, ModItems.COBALT_UPGRADE_TEMPLATE, Items.DIAMOND_PICKAXE,
                ModItems.COBALT_STEEL_INGOT, RecipeCategory.TOOLS, ModItems.COBALT_STEEL_PICKAXE,
                "has_cobalt_upgrade_smithing_template", "has_cobalt_steel_ingot");
        smithingUpgradeRecipeBuilder(exporter, ModItems.COBALT_UPGRADE_TEMPLATE, Items.DIAMOND_SHOVEL,
                ModItems.COBALT_STEEL_INGOT, RecipeCategory.TOOLS, ModItems.COBALT_STEEL_SHOVEL,
                "has_cobalt_upgrade_smithing_template", "has_cobalt_steel_ingot");
        smithingUpgradeRecipeBuilder(exporter, ModItems.COBALT_UPGRADE_TEMPLATE, Items.DIAMOND_SWORD,
                ModItems.COBALT_STEEL_INGOT, RecipeCategory.COMBAT, ModItems.COBALT_STEEL_SWORD,
                "has_cobalt_upgrade_smithing_template", "has_cobalt_steel_ingot");

        //INFUSED GEMSTONE EQUIPMENT
        smithingUpgradeRecipeBuilder(exporter, ModItems.INFUSION_UPGRADE_TEMPLATE, Items.DIAMOND_HELMET,
                ModItems.INFUSED_GEMSTONE, RecipeCategory.COMBAT, ModItems.INFUSED_GEMSTONE_HELMET,
                "has_infusion_upgrade_smithing_template", "has_infused_gemstone");
        smithingUpgradeRecipeBuilder(exporter, ModItems.INFUSION_UPGRADE_TEMPLATE, Items.DIAMOND_CHESTPLATE,
                ModItems.INFUSED_GEMSTONE, RecipeCategory.COMBAT, ModItems.INFUSED_GEMSTONE_CHESTPLATE,
                "has_infusion_upgrade_smithing_template", "has_infused_gemstone");
        smithingUpgradeRecipeBuilder(exporter, ModItems.INFUSION_UPGRADE_TEMPLATE, Items.DIAMOND_LEGGINGS,
                ModItems.INFUSED_GEMSTONE, RecipeCategory.COMBAT, ModItems.INFUSED_GEMSTONE_LEGGINGS,
                "has_infusion_upgrade_smithing_template", "has_infused_gemstone");
        smithingUpgradeRecipeBuilder(exporter, ModItems.INFUSION_UPGRADE_TEMPLATE, Items.DIAMOND_BOOTS,
                ModItems.INFUSED_GEMSTONE, RecipeCategory.COMBAT, ModItems.INFUSED_GEMSTONE_BOOTS,
                "has_infusion_upgrade_smithing_template", "has_infused_gemstone");

        smithingUpgradeRecipeBuilder(exporter, ModItems.INFUSION_UPGRADE_TEMPLATE, Items.DIAMOND_AXE,
                ModItems.INFUSED_GEMSTONE, RecipeCategory.TOOLS, ModItems.INFUSED_GEMSTONE_AXE,
                "has_infusion_upgrade_smithing_template", "has_infused_gemstone");
        smithingUpgradeRecipeBuilder(exporter, ModItems.INFUSION_UPGRADE_TEMPLATE, ModItems.DIAMOND_BATTLEAXE,
                ModItems.INFUSED_GEMSTONE, RecipeCategory.COMBAT, ModItems.INFUSED_GEMSTONE_BATTLEAXE,
                "has_infusion_upgrade_smithing_template", "has_infused_gemstone");
        smithingUpgradeRecipeBuilder(exporter, ModItems.INFUSION_UPGRADE_TEMPLATE, Items.DIAMOND_HOE,
                ModItems.INFUSED_GEMSTONE, RecipeCategory.TOOLS, ModItems.INFUSED_GEMSTONE_HOE,
                "has_infusion_upgrade_smithing_template", "has_infused_gemstone");
        smithingUpgradeRecipeBuilder(exporter, ModItems.INFUSION_UPGRADE_TEMPLATE, ModItems.DIAMOND_PAXEL,
                ModItems.INFUSED_GEMSTONE, RecipeCategory.TOOLS, ModItems.INFUSED_GEMSTONE_PAXEL,
                "has_infusion_upgrade_smithing_template", "has_infused_gemstone");
        smithingUpgradeRecipeBuilder(exporter, ModItems.INFUSION_UPGRADE_TEMPLATE, Items.DIAMOND_PICKAXE,
                ModItems.INFUSED_GEMSTONE, RecipeCategory.TOOLS, ModItems.INFUSED_GEMSTONE_PICKAXE,
                "has_infusion_upgrade_smithing_template", "has_infused_gemstone");
        smithingUpgradeRecipeBuilder(exporter, ModItems.INFUSION_UPGRADE_TEMPLATE, Items.DIAMOND_SHOVEL,
                ModItems.INFUSED_GEMSTONE, RecipeCategory.TOOLS, ModItems.INFUSED_GEMSTONE_SHOVEL,
                "has_infusion_upgrade_smithing_template", "has_infused_gemstone");
        smithingUpgradeRecipeBuilder(exporter, ModItems.INFUSION_UPGRADE_TEMPLATE, Items.DIAMOND_SWORD,
                ModItems.INFUSED_GEMSTONE, RecipeCategory.COMBAT, ModItems.INFUSED_GEMSTONE_SWORD,
                "has_infusion_upgrade_smithing_template", "has_infused_gemstone");

        //TUNGSTEN-CARBIDE EQUIPMENT
        smithingUpgradeRecipeBuilder(exporter, ModItems.CARBIDE_UPGRADE_TEMPLATE, Items.DIAMOND_HELMET,
                ModItems.TUNGSTEN_CARBIDE_INGOT, RecipeCategory.COMBAT, ModItems.TUNGSTEN_CARBIDE_HELMET,
                "has_carbide_upgrade_smithing_template", "has_tungsten_carbide_ingot");
        smithingUpgradeRecipeBuilder(exporter, ModItems.CARBIDE_UPGRADE_TEMPLATE, Items.DIAMOND_CHESTPLATE,
                ModItems.TUNGSTEN_CARBIDE_INGOT, RecipeCategory.COMBAT, ModItems.TUNGSTEN_CARBIDE_CHESTPLATE,
                "has_carbide_upgrade_smithing_template", "has_tungsten_carbide_ingot");
        smithingUpgradeRecipeBuilder(exporter, ModItems.CARBIDE_UPGRADE_TEMPLATE, Items.DIAMOND_LEGGINGS,
                ModItems.TUNGSTEN_CARBIDE_INGOT, RecipeCategory.COMBAT, ModItems.TUNGSTEN_CARBIDE_LEGGINGS,
                "has_carbide_upgrade_smithing_template", "has_tungsten_carbide_ingot");
        smithingUpgradeRecipeBuilder(exporter, ModItems.CARBIDE_UPGRADE_TEMPLATE, Items.DIAMOND_BOOTS,
                ModItems.TUNGSTEN_CARBIDE_INGOT, RecipeCategory.COMBAT, ModItems.TUNGSTEN_CARBIDE_BOOTS,
                "has_carbide_upgrade_smithing_template", "has_tungsten_carbide_ingot");

        smithingUpgradeRecipeBuilder(exporter, ModItems.CARBIDE_UPGRADE_TEMPLATE, Items.DIAMOND_AXE,
                ModItems.TUNGSTEN_CARBIDE_INGOT, RecipeCategory.TOOLS, ModItems.TUNGSTEN_CARBIDE_AXE,
                "has_carbide_upgrade_smithing_template", "has_tungsten_carbide_ingot");
        smithingUpgradeRecipeBuilder(exporter, ModItems.CARBIDE_UPGRADE_TEMPLATE, ModItems.DIAMOND_BATTLEAXE,
                ModItems.TUNGSTEN_CARBIDE_INGOT, RecipeCategory.COMBAT, ModItems.TUNGSTEN_CARBIDE_BATTLEAXE,
                "has_carbide_upgrade_smithing_template", "has_tungsten_carbide_ingot");
        smithingUpgradeRecipeBuilder(exporter, ModItems.CARBIDE_UPGRADE_TEMPLATE, Items.DIAMOND_HOE,
                ModItems.TUNGSTEN_CARBIDE_INGOT, RecipeCategory.TOOLS, ModItems.TUNGSTEN_CARBIDE_HOE,
                "has_carbide_upgrade_smithing_template", "has_tungsten_carbide_ingot");
        smithingUpgradeRecipeBuilder(exporter, ModItems.CARBIDE_UPGRADE_TEMPLATE, ModItems.DIAMOND_PAXEL,
                ModItems.TUNGSTEN_CARBIDE_INGOT, RecipeCategory.TOOLS, ModItems.TUNGSTEN_CARBIDE_PAXEL,
                "has_carbide_upgrade_smithing_template", "has_tungsten_carbide_ingot");
        smithingUpgradeRecipeBuilder(exporter, ModItems.CARBIDE_UPGRADE_TEMPLATE, Items.DIAMOND_PICKAXE,
                ModItems.TUNGSTEN_CARBIDE_INGOT, RecipeCategory.TOOLS, ModItems.TUNGSTEN_CARBIDE_PICKAXE,
                "has_carbide_upgrade_smithing_template", "has_tungsten_carbide_ingot");
        smithingUpgradeRecipeBuilder(exporter, ModItems.CARBIDE_UPGRADE_TEMPLATE, Items.DIAMOND_SHOVEL,
                ModItems.TUNGSTEN_CARBIDE_INGOT, RecipeCategory.TOOLS, ModItems.TUNGSTEN_CARBIDE_SHOVEL,
                "has_carbide_upgrade_smithing_template", "has_tungsten_carbide_ingot");
        smithingUpgradeRecipeBuilder(exporter, ModItems.CARBIDE_UPGRADE_TEMPLATE, Items.DIAMOND_SWORD,
                ModItems.TUNGSTEN_CARBIDE_INGOT, RecipeCategory.COMBAT, ModItems.TUNGSTEN_CARBIDE_SWORD,
                "has_carbide_upgrade_smithing_template", "has_tungsten_carbide_ingot");
    }



    /**
     * Helper to automatically generate shaped recipes for the four armor slots.
     * @param exporter Consumer of RecipeJsonProvider that exports the recipe
     * @param slot This armor piece's EquipmentSlot
     * @param ingredient Crafting ingredient
     * @param result Crafting result
     * @param hasString String in "has_[item]" format that defines how the recipe is unlocked
     */
    private void armorRecipeBuilder(Consumer<RecipeJsonProvider> exporter, EquipmentSlot slot,
                                    TagKey<Item> ingredient, Item result, String hasString) {
        switch (slot) {
            case HEAD -> ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, result, 1)
                    .input('d', ingredient)
                    .pattern("ddd")
                    .pattern("d d")
                    .criterion(hasString, conditionsFromTag(ingredient))
                    .offerTo(exporter, new Identifier(getRecipeName(result)));
            case CHEST -> ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, result, 1)
                    .input('d', ingredient)
                    .pattern("d d")
                    .pattern("ddd")
                    .pattern("ddd")
                    .criterion(hasString, conditionsFromTag(ingredient))
                    .offerTo(exporter, new Identifier(getRecipeName(result)));
            case LEGS -> ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, result, 1)
                    .input('d', ingredient)
                    .pattern("ddd")
                    .pattern("d d")
                    .pattern("d d")
                    .criterion(hasString, conditionsFromTag(ingredient))
                    .offerTo(exporter, new Identifier(getRecipeName(result)));
            case FEET -> ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, result, 1)
                    .input('d', ingredient)
                    .pattern("d d")
                    .pattern("d d")
                    .criterion(hasString, conditionsFromTag(ingredient))
                    .offerTo(exporter, new Identifier(getRecipeName(result)));
            default -> {
                //this will never be reached, can be left empty
            }
        }
    }

    /**
     * Helper to automatically generate shaped recipes for axe, battleaxe, hoe, paxel, pickaxe, shovel, and sword.
     * @param exporter Consumer of RecipeJsonProvider that exports the recipe
     * @param toolType Tool type of the item being crafted
     * @param ingredient Crafting ingredient
     * @param result Crafting result
     * @param hasString String in "has_[item]" format that defines how the recipe is unlocked
     */
    private void toolRecipeBuilder(Consumer<RecipeJsonProvider> exporter, ToolType toolType,
                                   TagKey<Item> ingredient, Item result, String hasString) {
        switch (toolType) {
            case AXE -> ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, result, 1)
                    .input('d', ingredient)
                    .input('i', ModTags.Items.COMMON_WOODEN_RODS)
                    .pattern("dd")
                    .pattern("di")
                    .pattern(" i")
                    .criterion(hasString, conditionsFromTag(ingredient))
                    .offerTo(exporter, new Identifier(getRecipeName(result)));
            case BATTLEAXE -> ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, result, 1)
                    .input('d', ingredient)
                    .input('i', ModTags.Items.COMMON_WOODEN_RODS)
                    .pattern("ddd")
                    .pattern("did")
                    .pattern(" i ")
                    .criterion(hasString, conditionsFromTag(ingredient))
                    .offerTo(exporter, new Identifier(getRecipeName(result)));
            case HOE -> ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, result, 1)
                    .input('d', ingredient)
                    .input('i', ModTags.Items.COMMON_WOODEN_RODS)
                    .pattern("dd")
                    .pattern(" i")
                    .pattern(" i")
                    .criterion(hasString, conditionsFromTag(ingredient))
                    .offerTo(exporter, new Identifier(getRecipeName(result)));
            case PAXEL -> ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, result, 1)
                    .input('d', ingredient)
                    .input('i', ModTags.Items.COMMON_WOODEN_RODS)
                    .pattern("ddd")
                    .pattern("di ")
                    .pattern(" i ")
                    .criterion(hasString, conditionsFromTag(ingredient))
                    .offerTo(exporter, new Identifier(getRecipeName(result)));
            case PICKAXE -> ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, result, 1)
                    .input('d', ingredient)
                    .input('i', ModTags.Items.COMMON_WOODEN_RODS)
                    .pattern("ddd")
                    .pattern(" i ")
                    .pattern(" i ")
                    .criterion(hasString, conditionsFromTag(ingredient))
                    .offerTo(exporter, new Identifier(getRecipeName(result)));
            case SHOVEL -> ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, result, 1)
                    .input('d', ingredient)
                    .input('i', ModTags.Items.COMMON_WOODEN_RODS)
                    .pattern("d")
                    .pattern("i")
                    .pattern("i")
                    .criterion(hasString, conditionsFromTag(ingredient))
                    .offerTo(exporter, new Identifier(getRecipeName(result)));
            case SWORD -> ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, result, 1)
                    .input('d', ingredient)
                    .input('i', ModTags.Items.COMMON_WOODEN_RODS)
                    .pattern("d")
                    .pattern("d")
                    .pattern("i")
                    .criterion(hasString, conditionsFromTag(ingredient))
                    .offerTo(exporter, new Identifier(getRecipeName(result)));
            //default case not needed?
        }
    }

    /**
     * Helper to automatically generate smithing recipes (1.20+). NOTE: Smithing recipes are
     *  specifically item-to-item, and should never use tags.
     * @param exporter Consumer of RecipeJsonProvider that exports the recipe
     * @param template Required upgrade template Item
     * @param upgradeTarget Item being upgraded
     * @param ingredient Upgrade ingredient Item
     * @param category Recipe category
     * @param result Smithing result Item
     * @param hasStringTemplate String in "has_[item]" format corresponding to the upgrade template
     * @param hasStringIngredient String in "has_[item]" format corresponding to the upgrade ingredient
     */
    private void smithingUpgradeRecipeBuilder(Consumer<RecipeJsonProvider> exporter, Item template, Item upgradeTarget,
                                              Item ingredient, RecipeCategory category, Item result,
                                              String hasStringTemplate, String hasStringIngredient) {
        SmithingTransformRecipeJsonBuilder.create(Ingredient.ofItems(template), Ingredient.ofItems(upgradeTarget),
                Ingredient.ofItems(ingredient), category, result)
                .criterion(hasStringTemplate, conditionsFromItem(template))
                .criterion(hasStringIngredient, conditionsFromItem(ingredient))
                .offerTo(exporter, new Identifier(getRecipeName(result)) + "_smithing");
    }
}
