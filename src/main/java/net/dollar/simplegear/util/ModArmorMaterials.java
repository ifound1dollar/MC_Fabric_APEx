package net.dollar.simplegear.util;

import net.dollar.simplegear.ModMain;
import net.dollar.simplegear.item.ModItems;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import java.util.function.Supplier;

public enum ModArmorMaterials implements ArmorMaterial {
    //NETHERITE: 37, {3,8,6,3}, 15, 3.0, 0.1
    BRONZE("bronze",
            15,
            new int[] { 2, 6, 5, 2 },
            9,
            SoundEvents.ITEM_ARMOR_EQUIP_IRON,
            0.0f,
            0.0f,
            () -> Ingredient.ofItems(ModItems.BRONZE_INGOT)),
    GILDED_BRONZE("gilded_bronze",
            23,
            new int[] { 3, 6, 5, 3 },
            25,
            SoundEvents.ITEM_ARMOR_EQUIP_GOLD,
            1.0f,
            0.0f,
            () -> Ingredient.ofItems(Items.GOLD_INGOT)),
    INFUSED_GEMSTONE("infused_gemstone",
            37,
            new int[] { 3, 8, 6, 3 },
            25,
            SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND,
            2.5f,
            0.05f,
            () -> Ingredient.ofItems(ModItems.INFUSED_GEMSTONE)),
    COBALT_STEEL("cobalt_steel",
            37,
            new int[] { 3, 8, 6, 3 },
            20,
            SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND,
            2.5f,
            0.1f,
            () -> Ingredient.ofItems(ModItems.COBALT_STEEL_INGOT)),
    TUNGSTEN_CARBIDE("tungsten_carbide",
            41,
            new int[] { 3, 8, 6, 3 },
            15,
            SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE,
            3.0f,
            0.1f,
            () -> Ingredient.ofItems(ModItems.TUNGSTEN_CARBIDE_INGOT));


    private final String name;
    private final int durabilityMultiplier;
    private final int[] protectionAmounts;
    private final int enchantability;
    private final SoundEvent equipSound;
    private final float toughness;
    private final float knockbackResistance;
    private final Supplier<Ingredient> repairIngredient;

    private static final int[] BASE_DURABILITY = { 11, 16, 15, 13 };    //Helmet, Chestplate, Leggings, Boots



    ModArmorMaterials(String name, int durabilityMultiplier, int[] protectionAmounts, int enchantability,
                      SoundEvent equipSound, float toughness, float knockbackResistance,
                      Supplier<Ingredient> repairIngredientSupplier) {
        this.name = name;
        this.durabilityMultiplier = durabilityMultiplier;
        this.protectionAmounts = protectionAmounts;
        this.enchantability = enchantability;
        this.equipSound = equipSound;
        this.toughness = toughness;
        this.knockbackResistance = knockbackResistance;
        this.repairIngredient = repairIngredientSupplier;
    }

    @Override
    public int getDurability(ArmorItem.Type type) {
        return BASE_DURABILITY[type.ordinal()] * this.durabilityMultiplier;
    }

    @Override
    public int getProtection(ArmorItem.Type type) {
        return this.protectionAmounts[type.ordinal()];
    }

    @Override
    public int getEnchantability() {
        return this.enchantability;
    }

    @Override
    public SoundEvent getEquipSound() {
        return this.equipSound;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return this.repairIngredient.get();
    }

    @Override
    public String getName() {
        return ModMain.MOD_ID + ":" + this.name;
    }

    @Override
    public float getToughness() {
        return this.toughness;
    }

    @Override
    public float getKnockbackResistance() {
        return this.knockbackResistance;
    }
}