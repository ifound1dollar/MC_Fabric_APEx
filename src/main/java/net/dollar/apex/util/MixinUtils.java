package net.dollar.apex.util;

import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Arm;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RotationAxis;

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
}
