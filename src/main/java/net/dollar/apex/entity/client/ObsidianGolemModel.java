/*
 * Decompiled with CFR 0.2.1 (FabricMC 53fa44c9).
 */
package net.dollar.apex.entity.client;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.*;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartNames;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.util.Mth;

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



    public static LayerDefinition getTexturedModelData() {
        MeshDefinition modelData = new MeshDefinition();
        PartDefinition modelPartData = modelData.getRoot();
        modelPartData.addOrReplaceChild(PartNames.HEAD, CubeListBuilder.create().texOffs(0, 0).addBox(-4.0f, -12.0f, -5.5f, 8.0f, 10.0f, 8.0f).texOffs(24, 0).addBox(-1.0f, -5.0f, -7.5f, 2.0f, 4.0f, 2.0f), PartPose.offset(0.0f, -7.0f, -2.0f));
        modelPartData.addOrReplaceChild(PartNames.BODY, CubeListBuilder.create().texOffs(0, 40).addBox(-9.0f, -2.0f, -6.0f, 18.0f, 12.0f, 11.0f).texOffs(0, 70).addBox(-4.5f, 10.0f, -3.0f, 9.0f, 5.0f, 6.0f, new CubeDeformation(0.5f)), PartPose.offset(0.0f, -7.0f, 0.0f));
        modelPartData.addOrReplaceChild(PartNames.RIGHT_ARM, CubeListBuilder.create().texOffs(60, 21).addBox(-13.0f, -2.5f, -3.0f, 4.0f, 30.0f, 6.0f), PartPose.offset(0.0f, -7.0f, 0.0f));
        modelPartData.addOrReplaceChild(PartNames.LEFT_ARM, CubeListBuilder.create().texOffs(60, 58).addBox(9.0f, -2.5f, -3.0f, 4.0f, 30.0f, 6.0f), PartPose.offset(0.0f, -7.0f, 0.0f));
        modelPartData.addOrReplaceChild(PartNames.RIGHT_LEG, CubeListBuilder.create().texOffs(37, 0).addBox(-3.5f, -3.0f, -3.0f, 6.0f, 16.0f, 5.0f), PartPose.offset(-4.0f, 11.0f, 0.0f));
        modelPartData.addOrReplaceChild(PartNames.LEFT_LEG, CubeListBuilder.create().texOffs(60, 0).mirror().addBox(-3.5f, -3.0f, -3.0f, 6.0f, 16.0f, 5.0f), PartPose.offset(5.0f, 11.0f, 0.0f));
        return LayerDefinition.create(modelData, 128, 128);
    }

    public void setAngles(ObsidianGolemRenderState renderState) {
        super.setupAnim(renderState);
        float f = renderState.attackTicksLeft;
        float g = renderState.walkAnimationSpeed;
        float h = renderState.walkAnimationPos;
        if (f > 0.0F) {
            this.rightArm.xRot = -2.0F + 1.5F * Mth.triangleWave(f, 10.0F);
            this.leftArm.xRot = -2.0F + 1.5F * Mth.triangleWave(f, 10.0F);
        } else {
            this.rightArm.xRot = (-0.2F + 1.5F * Mth.triangleWave(h, 13.0F)) * g;
            this.leftArm.xRot = (-0.2F - 1.5F * Mth.triangleWave(h, 13.0F)) * g;

        }

        this.head.yRot = renderState.yRot * ((float)Math.PI / 180F);
        this.head.xRot = renderState.xRot * ((float)Math.PI / 180F);
        this.rightLeg.xRot = -1.5F * Mth.triangleWave(h, 13.0F) * g;
        this.leftLeg.xRot = 1.5F * Mth.triangleWave(h, 13.0F) * g;
        this.rightLeg.yRot = 0.0F;
        this.leftLeg.yRot = 0.0F;
    }

//    public ModelPart getRightArm() {
//        return this.rightArm;
//    }
}

