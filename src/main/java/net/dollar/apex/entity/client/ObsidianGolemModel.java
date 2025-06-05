/*
 * Decompiled with CFR 0.2.1 (FabricMC 53fa44c9).
 */
package net.dollar.apex.entity.client;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.*;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.EntityModelPartNames;
import net.minecraft.util.math.MathHelper;

@Environment(value=EnvType.CLIENT)
public class ObsidianGolemModel extends EntityModel<ObsidianGolemRenderState> {
    private final ModelPart head;
    private final ModelPart rightArm;
    private final ModelPart leftArm;
    private final ModelPart rightLeg;
    private final ModelPart leftLeg;

    public ObsidianGolemModel(ModelPart modelPart) {
        super(modelPart);
        this.head = modelPart.getChild("head");
        this.rightArm = modelPart.getChild("right_arm");
        this.leftArm = modelPart.getChild("left_arm");
        this.rightLeg = modelPart.getChild("right_leg");
        this.leftLeg = modelPart.getChild("left_leg");
    }



    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        modelPartData.addChild(EntityModelPartNames.HEAD, ModelPartBuilder.create().uv(0, 0).cuboid(-4.0f, -12.0f, -5.5f, 8.0f, 10.0f, 8.0f).uv(24, 0).cuboid(-1.0f, -5.0f, -7.5f, 2.0f, 4.0f, 2.0f), ModelTransform.origin(0.0f, -7.0f, -2.0f));
        modelPartData.addChild(EntityModelPartNames.BODY, ModelPartBuilder.create().uv(0, 40).cuboid(-9.0f, -2.0f, -6.0f, 18.0f, 12.0f, 11.0f).uv(0, 70).cuboid(-4.5f, 10.0f, -3.0f, 9.0f, 5.0f, 6.0f, new Dilation(0.5f)), ModelTransform.origin(0.0f, -7.0f, 0.0f));
        modelPartData.addChild(EntityModelPartNames.RIGHT_ARM, ModelPartBuilder.create().uv(60, 21).cuboid(-13.0f, -2.5f, -3.0f, 4.0f, 30.0f, 6.0f), ModelTransform.origin(0.0f, -7.0f, 0.0f));
        modelPartData.addChild(EntityModelPartNames.LEFT_ARM, ModelPartBuilder.create().uv(60, 58).cuboid(9.0f, -2.5f, -3.0f, 4.0f, 30.0f, 6.0f), ModelTransform.origin(0.0f, -7.0f, 0.0f));
        modelPartData.addChild(EntityModelPartNames.RIGHT_LEG, ModelPartBuilder.create().uv(37, 0).cuboid(-3.5f, -3.0f, -3.0f, 6.0f, 16.0f, 5.0f), ModelTransform.origin(-4.0f, 11.0f, 0.0f));
        modelPartData.addChild(EntityModelPartNames.LEFT_LEG, ModelPartBuilder.create().uv(60, 0).mirrored().cuboid(-3.5f, -3.0f, -3.0f, 6.0f, 16.0f, 5.0f), ModelTransform.origin(5.0f, 11.0f, 0.0f));
        return TexturedModelData.of(modelData, 128, 128);
    }

    public void setAngles(ObsidianGolemRenderState renderState) {
        super.setAngles(renderState);
        float f = renderState.attackTicksLeft;
        float g = renderState.limbSwingAmplitude;
        float h = renderState.limbSwingAnimationProgress;
        if (f > 0.0F) {
            this.rightArm.pitch = -2.0F + 1.5F * MathHelper.wrap(f, 10.0F);
            this.leftArm.pitch = -2.0F + 1.5F * MathHelper.wrap(f, 10.0F);
        } else {
            this.rightArm.pitch = (-0.2F + 1.5F * MathHelper.wrap(h, 13.0F)) * g;
            this.leftArm.pitch = (-0.2F - 1.5F * MathHelper.wrap(h, 13.0F)) * g;

        }

        this.head.yaw = renderState.relativeHeadYaw * ((float)Math.PI / 180F);
        this.head.pitch = renderState.pitch * ((float)Math.PI / 180F);
        this.rightLeg.pitch = -1.5F * MathHelper.wrap(h, 13.0F) * g;
        this.leftLeg.pitch = 1.5F * MathHelper.wrap(h, 13.0F) * g;
        this.rightLeg.yaw = 0.0F;
        this.leftLeg.yaw = 0.0F;
    }

//    public ModelPart getRightArm() {
//        return this.rightArm;
//    }
}

