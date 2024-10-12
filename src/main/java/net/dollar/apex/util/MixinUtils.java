package net.dollar.apex.util;

import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.Item;
import net.minecraft.util.Arm;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RotationAxis;

import java.util.ArrayList;

public class MixinUtils {
    /**
     * Implements applyEquipOffset() method from HeldItemRenderer class, allowing usage in MixinHeldItemRenderer.
     * @param matrices MatrixStack determining transform
     * @param arm Arm holding the item
     * @param equipProgress Progress of equipping function
     */
    public static void applyEquipOffset(MatrixStack matrices, Arm arm, float equipProgress) {
        int i = arm == Arm.RIGHT ? 1 : -1;
        matrices.translate((float)i * 0.56f, -0.52f + equipProgress * -0.6f, -0.72f);
    }

    /**
     * Implements applySwingOffset() method from HeldItemRenderer class, allowing usage in MixinHeldItemRenderer.
     * @param matrices MatrixStack determining transform
     * @param arm Arm holding the item
     * @param swingProgress Progress of swing function
     */
    public static void applySwingOffset(MatrixStack matrices, Arm arm, float swingProgress) {
        int i = arm == Arm.RIGHT ? 1 : -1;
        float f = MathHelper.sin(swingProgress * swingProgress * (float)Math.PI);
        matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees((float)i * (45.0f + f * -20.0f)));
        float g = MathHelper.sin(MathHelper.sqrt(swingProgress) * (float)Math.PI);
        matrices.multiply(RotationAxis.POSITIVE_Z.rotationDegrees((float)i * g * -20.0f));
        matrices.multiply(RotationAxis.POSITIVE_X.rotationDegrees(g * -80.0f));
        matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees((float)i * -45.0f));
    }

    /**
     * Generates an ArrayList of valid Enchantments for the passed-in armor item. Checks the armor item's
     *  type and selects only valid enchantments for that type (ex. Aqua Affinity only for helmets).
     * @param item Relevant ArmorItem
     * @return Generated list of Enchantments for the ArmorItem
     */
    public static ArrayList<Enchantment> getEnchantmentsForArmorItem(Item item) {
        if (item instanceof ArmorItem armorItem) {
            if (armorItem.getType() == ArmorItem.Type.HELMET) {
                return new ArrayList<>() {
                    {
                        add(Enchantments.AQUA_AFFINITY);
                        add(Enchantments.BLAST_PROTECTION);
                        add(Enchantments.FIRE_PROTECTION);
                        add(Enchantments.PROJECTILE_PROTECTION);
                        add(Enchantments.PROTECTION);
                        add(Enchantments.RESPIRATION);
                        add(Enchantments.UNBREAKING);
                    }
                };
            } else if (armorItem.getType() == ArmorItem.Type.CHESTPLATE) {
                return new ArrayList<>() {
                    {
                        add(Enchantments.BLAST_PROTECTION);
                        add(Enchantments.FIRE_PROTECTION);
                        add(Enchantments.PROJECTILE_PROTECTION);
                        add(Enchantments.PROTECTION);
                        add(Enchantments.THORNS);
                        add(Enchantments.UNBREAKING);
                    }
                };
            } else if (armorItem.getType() == ArmorItem.Type.LEGGINGS) {
                return new ArrayList<>() {
                    {
                        add(Enchantments.BLAST_PROTECTION);
                        add(Enchantments.FIRE_PROTECTION);
                        add(Enchantments.PROJECTILE_PROTECTION);
                        add(Enchantments.PROTECTION);
                        add(Enchantments.SWIFT_SNEAK);
                        add(Enchantments.UNBREAKING);
                    }
                };
            } else if (armorItem.getType() == ArmorItem.Type.BOOTS) {
                return new ArrayList<>() {
                    {
                        add(Enchantments.BLAST_PROTECTION);
                        add(Enchantments.DEPTH_STRIDER);
                        add(Enchantments.FEATHER_FALLING);
                        add(Enchantments.FROST_WALKER);
                        add(Enchantments.FIRE_PROTECTION);
                        add(Enchantments.PROJECTILE_PROTECTION);
                        add(Enchantments.PROTECTION);
                        add(Enchantments.UNBREAKING);
                    }
                };
            }
        }

        return new ArrayList<>();    //Will only reach here if not a valid armor item. Returns empty ArrayList.
    }
}
