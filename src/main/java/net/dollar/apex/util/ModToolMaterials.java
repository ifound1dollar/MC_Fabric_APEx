package net.dollar.apex.util;

import net.dollar.apex.item.ModItems;
import net.minecraft.block.Block;
import net.minecraft.item.Items;
import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.TagKey;

import java.util.function.Supplier;

public enum ModToolMaterials implements ToolMaterial {
    //NETHERITE: 4, 2031, 9, 4.0f, 15
    BRONZE(BlockTags.INCORRECT_FOR_IRON_TOOL,
            250,
            6.0f,
            2.0f,
            14,
            () -> Ingredient.ofItems(ModItems.BRONZE_INGOT)),
    GILDED_BRONZE(BlockTags.INCORRECT_FOR_DIAMOND_TOOL,
            666,
            12,
            2.0f,
            22,
            () -> Ingredient.ofItems(Items.GOLD_INGOT)),
    INFUSED_GEMSTONE(BlockTags.INCORRECT_FOR_NETHERITE_TOOL,
            2031,
            10,
            4.0f,
            22,
            () -> Ingredient.ofItems(ModItems.INFUSED_GEMSTONE)),
    COBALT_STEEL(BlockTags.INCORRECT_FOR_NETHERITE_TOOL,
             2031,
             20,
             3.0f,
             18,
             () -> Ingredient.ofItems(ModItems.COBALT_STEEL_INGOT)),
    TUNGSTEN_CARBIDE(BlockTags.INCORRECT_FOR_NETHERITE_TOOL,
            2501,
            7,
            6.0f,
            15,
            () -> Ingredient.ofItems(ModItems.TUNGSTEN_CARBIDE_INGOT));



    private final TagKey<Block> inverseTag;
    private final int itemDurability;
    private final float miningSpeed;
    private final float attackDamage;
    private final int enchantability;
    private final Supplier<Ingredient> repairIngredient;

    ModToolMaterials(final TagKey<Block> inverseTag, final int itemDurability, final float miningSpeed,
                     final float attackDamage, final int enchantability, final Supplier<Ingredient> repairIngredient) {
        this.inverseTag = inverseTag;
        this.itemDurability = itemDurability;
        this.miningSpeed = miningSpeed;
        this.attackDamage = attackDamage;
        this.enchantability = enchantability;
        this.repairIngredient = repairIngredient;
    }


    @Override
    public int getDurability() {
        return this.itemDurability;
    }

    @Override
    public float getMiningSpeedMultiplier() {
        return this.miningSpeed;
    }

    @Override
    public float getAttackDamage() {
        return this.attackDamage;
    }

    @Override
    public TagKey<Block> getInverseTag() {
        return this.inverseTag;
    }

    @Override
    public int getEnchantability() {
        return this.enchantability;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return this.repairIngredient.get();
    }
}
