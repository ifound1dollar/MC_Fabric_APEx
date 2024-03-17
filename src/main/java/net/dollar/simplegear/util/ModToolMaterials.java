package net.dollar.simplegear.util;

import net.dollar.simplegear.item.ModItems;
import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;

import java.util.function.Supplier;

public enum ModToolMaterials implements ToolMaterial {
    //NETHERITE: 4, 2031, 9, 4.0f, 15
    INFUSED_GEMSTONE(4,
            2031,
            10,
            4.0f,
            22,
            () -> Ingredient.ofItems(ModItems.INFUSED_GEMSTONE)),
    COBALT_STEEL(4,
             2031,
             20,
             3.0f,
             18,
             () -> Ingredient.ofItems(ModItems.COBALT_STEEL_INGOT)),
    TUNGSTEN_CARBIDE(4,
            2031,
            7,
            6.0f,
            15,
            () -> Ingredient.ofItems(ModItems.TUNGSTEN_CARBIDE_INGOT));



    private final int miningLevel;
    private final int itemDurability;
    private final float miningSpeed;
    private final float attackDamage;
    private final int enchantability;
    private final Supplier<Ingredient> repairIngredient;

    ModToolMaterials(int miningLevel, int itemDurability, float miningSpeed, float attackDamage, int enchantability, Supplier<Ingredient> repairIngredient) {
        this.miningLevel = miningLevel;
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
    public int getMiningLevel() {
        return this.miningLevel;
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
