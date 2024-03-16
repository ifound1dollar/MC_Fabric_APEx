package net.dollar.simplegear.mixin;

import net.dollar.simplegear.item.custom.cobaltsteel.*;
import net.minecraft.item.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.Slice;

/**
 * Mixin for overwriting vanilla item registries.
 */
@Deprecated
@Mixin(Items.class)
public class MixinVanillaItems {
    //Slicing redirects will overwrite a specific method call at a pre-defined point, corresponding to each
    //  item that should be overwritten.
    //IMPORTANT: Must override the Item instantiation, NOT the registry function. The registration will be
    //  automatically done, but overriding just the registration will not properly instantiate the item and
    //  generate an error on load-in.


    @Redirect(
            method = "<clinit>",
            at = @At(
                    value = "NEW",
                    target = "(Lnet/minecraft/item/Item$Settings;)Lnet/minecraft/item/Item;",
                    ordinal = 0
            ),
            slice = @Slice(
                    from = @At(
                            value = "CONSTANT",
                            args = {
                                    "stringValue=netherite_ingot"
                            },
                            ordinal = 0
                    )
            )
    )
    private static Item overwriteNetheriteIngot(Item.Settings settings) {
        return new ModCobaltSteelIngotItem(settings);
    }



    //ARMOR (@At target must match ArmorItem constructor)
    @Redirect(
            method = "<clinit>",
            at = @At(
                    value = "NEW",
                    target = "(Lnet/minecraft/item/ArmorMaterial;Lnet/minecraft/item/ArmorItem$Type;Lnet/minecraft/item/Item$Settings;)Lnet/minecraft/item/ArmorItem;",
                    ordinal = 0
            ),
            slice = @Slice(
                    from = @At(
                            value = "CONSTANT",
                            args = {
                                    "stringValue=netherite_boots"
                            },
                            ordinal = 0
                    )
            )
    )
    private static ArmorItem overwriteNetheriteBoots(ArmorMaterial material, ArmorItem.Type type, Item.Settings settings) {
        return new ModCobaltSteelArmorItem(material, type, settings);
    }
    @Redirect(
            method = "<clinit>",
            at = @At(
                    value = "NEW",
                    target = "(Lnet/minecraft/item/ArmorMaterial;Lnet/minecraft/item/ArmorItem$Type;Lnet/minecraft/item/Item$Settings;)Lnet/minecraft/item/ArmorItem;",
                    ordinal = 0
            ),
            slice = @Slice(
                    from = @At(
                            value = "CONSTANT",
                            args = {
                                    "stringValue=netherite_leggings"
                            },
                            ordinal = 0
                    )
            )
    )
    private static ArmorItem overwriteNetheriteLeggings(ArmorMaterial material, ArmorItem.Type type, Item.Settings settings) {
        return new ModCobaltSteelArmorItem(material, type, settings);
    }
    @Redirect(
            method = "<clinit>",
            at = @At(
                    value = "NEW",
                    target = "(Lnet/minecraft/item/ArmorMaterial;Lnet/minecraft/item/ArmorItem$Type;Lnet/minecraft/item/Item$Settings;)Lnet/minecraft/item/ArmorItem;",
                    ordinal = 0
            ),
            slice = @Slice(
                    from = @At(
                            value = "CONSTANT",
                            args = {
                                    "stringValue=netherite_chestplate"
                            },
                            ordinal = 0
                    )
            )
    )
    private static ArmorItem overwriteNetheriteChestplate(ArmorMaterial material, ArmorItem.Type type, Item.Settings settings) {
        return new ModCobaltSteelArmorItem(material, type, settings);
    }
    @Redirect(
            method = "<clinit>",
            at = @At(
                    value = "NEW",
                    target = "(Lnet/minecraft/item/ArmorMaterial;Lnet/minecraft/item/ArmorItem$Type;Lnet/minecraft/item/Item$Settings;)Lnet/minecraft/item/ArmorItem;",
                    ordinal = 0
            ),
            slice = @Slice(
                    from = @At(
                            value = "CONSTANT",
                            args = {
                                    "stringValue=netherite_helmet"
                            },
                            ordinal = 0
                    )
            )
    )
    private static ArmorItem overwriteNetheriteHelmet(ArmorMaterial material, ArmorItem.Type type, Item.Settings settings) {
        return new ModCobaltSteelArmorItem(material, type, settings);
    }



    //TOOLS/WEAPONS (@At target must match tool type, ex. AxeItem)
    @Redirect(
            method = "<clinit>",
            at = @At(
                    value = "NEW",
                    target = "(Lnet/minecraft/item/ToolMaterial;FFLnet/minecraft/item/Item$Settings;)Lnet/minecraft/item/AxeItem;",
                    ordinal = 0
            ),
            slice = @Slice(
                    from = @At(
                            value = "CONSTANT",
                            args = {
                                    "stringValue=netherite_axe"
                            },
                            ordinal = 0
                    )
            )
    )
    private static AxeItem overwriteNetheriteAxe(ToolMaterial material, float attackDamage, float attackSpeed, Item.Settings settings) {
        return new ModCobaltSteelAxeItem(ToolMaterials.NETHERITE, 6, -3.0f, settings);
    }

    @Redirect(
            method = "<clinit>",
            at = @At(
                    value = "NEW",
                    target = "(Lnet/minecraft/item/ToolMaterial;IFLnet/minecraft/item/Item$Settings;)Lnet/minecraft/item/HoeItem;",
                    ordinal = 0
            ),
            slice = @Slice(
                    from = @At(
                            value = "CONSTANT",
                            args = {
                                    "stringValue=netherite_hoe"
                            },
                            ordinal = 0
                    )
            )
    )
    private static HoeItem overwriteNetheriteHoe(ToolMaterial material, int attackDamage, float attackSpeed, Item.Settings settings) {
        return new ModCobaltSteelHoeItem(ToolMaterials.NETHERITE, -2, 1.0f, settings);
    }

    @Redirect(
            method = "<clinit>",
            at = @At(
                    value = "NEW",
                    target = "(Lnet/minecraft/item/ToolMaterial;IFLnet/minecraft/item/Item$Settings;)Lnet/minecraft/item/PickaxeItem;",
                    ordinal = 0
            ),
            slice = @Slice(
                    from = @At(
                            value = "CONSTANT",
                            args = {
                                    "stringValue=netherite_pickaxe"
                            },
                            ordinal = 0
                    )
            )
    )
    private static PickaxeItem overwriteNetheritePickaxe(ToolMaterial material, int attackDamage, float attackSpeed, Item.Settings settings) {
        return new ModCobaltSteelPickaxeItem(ToolMaterials.NETHERITE, 1, -2.8f, settings);
    }

    @Redirect(
            method = "<clinit>",
            at = @At(
                    value = "NEW",
                    target = "(Lnet/minecraft/item/ToolMaterial;FFLnet/minecraft/item/Item$Settings;)Lnet/minecraft/item/ShovelItem;",
                    ordinal = 0
            ),
            slice = @Slice(
                    from = @At(
                            value = "CONSTANT",
                            args = {
                                    "stringValue=netherite_shovel"
                            },
                            ordinal = 0
                    )
            )
    )
    private static ShovelItem overwriteNetheriteShovel(ToolMaterial material, float attackDamage, float attackSpeed, Item.Settings settings) {
        return new ModCobaltSteelShovelItem(ToolMaterials.NETHERITE, 2.0f, -3.0f, settings);
    }

    @Redirect(
            method = "<clinit>",
            at = @At(
                    value = "NEW",
                    target = "(Lnet/minecraft/item/ToolMaterial;IFLnet/minecraft/item/Item$Settings;)Lnet/minecraft/item/SwordItem;",
                    ordinal = 0
            ),
            slice = @Slice(
                    from = @At(
                            value = "CONSTANT",
                            args = {
                                    "stringValue=netherite_sword"
                            },
                            ordinal = 0
                    )
            )
    )
    private static SwordItem overwriteNetheriteSword(ToolMaterial toolMaterial, int attackDamage, float attackSpeed, Item.Settings settings) {
        return new ModCobaltSteelSwordItem(ToolMaterials.NETHERITE, 4, -2.4f, settings);
    }
}
