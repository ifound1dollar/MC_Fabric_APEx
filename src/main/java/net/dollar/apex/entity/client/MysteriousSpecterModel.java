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
import net.minecraft.client.renderer.rendertype.RenderTypes;

@Environment(EnvType.CLIENT)
public class MysteriousSpecterModel<T extends MysteriousSpecterRenderState> extends HumanoidModel<T> {
    public MysteriousSpecterModel(ModelPart root) {
        super(root, RenderTypes::entityTranslucent);
    }



    public static LayerDefinition getTexturedModelData() {
        //Make dilation and pivotOffsetY variables (effectively copied from BipedEntityModel) and set to defaults.
        CubeDeformation dilation = CubeDeformation.NONE;
        float pivotOffsetY = 0.0f;

        MeshDefinition modelData = createMesh(dilation, pivotOffsetY);    //I am clueless about these values.
        PartDefinition modelPartData = modelData.getRoot();
        modelPartData.addOrReplaceChild(PartNames.HEAD, CubeListBuilder.create().texOffs(0, 0).addBox(-4.0f, -8.0f, -4.0f, 8.0f, 8.0f, 8.0f, dilation), PartPose.offset(0.0f, 0.0f + pivotOffsetY, 0.0f));
        modelPartData.addOrReplaceChild(PartNames.HAT, CubeListBuilder.create().texOffs(32, 0).addBox(-4.0f, -8.0f, -4.0f, 8.0f, 8.0f, 8.0f, dilation.extend(0.5f)), PartPose.offset(0.0f, 0.0f + pivotOffsetY, 0.0f));
        modelPartData.addOrReplaceChild(PartNames.BODY, CubeListBuilder.create().texOffs(16, 16).addBox(-4.0f, 0.0f, -2.0f, 8.0f, 12.0f, 4.0f, dilation), PartPose.offset(0.0f, 0.0f + pivotOffsetY, 0.0f));
        modelPartData.addOrReplaceChild(PartNames.RIGHT_ARM, CubeListBuilder.create().texOffs(40, 16).addBox(-3.0f, -2.0f, -2.0f, 4.0f, 12.0f, 4.0f, dilation), PartPose.offset(-5.0f, 2.0f + pivotOffsetY, 0.0f));
        modelPartData.addOrReplaceChild(PartNames.LEFT_ARM, CubeListBuilder.create().texOffs(40, 16).mirror().addBox(-1.0f, -2.0f, -2.0f, 4.0f, 12.0f, 4.0f, dilation), PartPose.offset(5.0f, 2.0f + pivotOffsetY, 0.0f));
        modelPartData.addOrReplaceChild(PartNames.RIGHT_LEG, CubeListBuilder.create().texOffs(0, 16).addBox(-2.0f, 0.0f, -2.0f, 4.0f, 12.0f, 4.0f, dilation), PartPose.offset(-1.9f, 12.0f + pivotOffsetY, 0.0f));
        modelPartData.addOrReplaceChild(PartNames.LEFT_LEG, CubeListBuilder.create().texOffs(0, 16).mirror().addBox(-2.0f, 0.0f, -2.0f, 4.0f, 12.0f, 4.0f, dilation), PartPose.offset(1.9f, 12.0f + pivotOffsetY, 0.0f));
        return LayerDefinition.create(modelData, 64, 64);
    }
}
