package net.dollar.apex.util;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.HumanoidArm;

public class MixinUtils {
    /**
     * Implements applyEquipOffset() method from HeldItemRenderer class, allowing usage in MixinHeldItemRenderer.
     * @param matrices MatrixStack determining transform
     * @param arm Arm holding the item
     * @param equipProgress Progress of equipping function
     */
    public static void applyEquipOffset(PoseStack matrices, HumanoidArm arm, float equipProgress) {
        int i = arm == HumanoidArm.RIGHT ? 1 : -1;
        matrices.translate((float)i * 0.56f, -0.52f + equipProgress * -0.6f, -0.72f);
    }

    /**
     * Implements applySwingOffset() method from HeldItemRenderer class, allowing usage in MixinHeldItemRenderer.
     * @param matrices MatrixStack determining transform
     * @param arm Arm holding the item
     * @param swingProgress Progress of swing function
     */
    public static void applySwingOffset(PoseStack matrices, HumanoidArm arm, float swingProgress) {
        int i = arm == HumanoidArm.RIGHT ? 1 : -1;
        float f = Mth.sin(swingProgress * swingProgress * (float)Math.PI);
        matrices.mulPose(Axis.YP.rotationDegrees((float)i * (45.0f + f * -20.0f)));
        float g = Mth.sin(Mth.sqrt(swingProgress) * (float)Math.PI);
        matrices.mulPose(Axis.ZP.rotationDegrees((float)i * g * -20.0f));
        matrices.mulPose(Axis.XP.rotationDegrees(g * -80.0f));
        matrices.mulPose(Axis.YP.rotationDegrees((float)i * -45.0f));
    }
}
