package net.dollar.apex.entity.client;

import net.dollar.apex.entity.custom.MysteriousSpecterEntity;
import net.minecraft.client.model.*;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.render.entity.model.EntityModelPartNames;

public class MysteriousSpecterModel<T extends MysteriousSpecterEntity> extends BipedEntityModel<T> {
    public MysteriousSpecterModel(ModelPart root) {
        super(root, RenderLayer::getEntityTranslucent);
    }



    @Override
    public void setAngles(T hostileEntity, float f, float g, float h, float i, float j) {
        super.setAngles(hostileEntity, f, g, h, i, j);
    }

    public static TexturedModelData getTexturedModelData() {
        //Make dilation and pivotOffsetY variables (effectively copied from BipedEntityModel) and set to defaults.
        Dilation dilation = Dilation.NONE;
        float pivotOffsetY = 0.0f;

        ModelData modelData = getModelData(dilation, pivotOffsetY);    //I am clueless about these values.
        ModelPartData modelPartData = modelData.getRoot();
        modelPartData.addChild(EntityModelPartNames.HEAD, ModelPartBuilder.create().uv(0, 0).cuboid(-4.0f, -8.0f, -4.0f, 8.0f, 8.0f, 8.0f, dilation), ModelTransform.pivot(0.0f, 0.0f + pivotOffsetY, 0.0f));
        modelPartData.addChild(EntityModelPartNames.HAT, ModelPartBuilder.create().uv(32, 0).cuboid(-4.0f, -8.0f, -4.0f, 8.0f, 8.0f, 8.0f, dilation.add(0.5f)), ModelTransform.pivot(0.0f, 0.0f + pivotOffsetY, 0.0f));
        modelPartData.addChild(EntityModelPartNames.BODY, ModelPartBuilder.create().uv(16, 16).cuboid(-4.0f, 0.0f, -2.0f, 8.0f, 12.0f, 4.0f, dilation), ModelTransform.pivot(0.0f, 0.0f + pivotOffsetY, 0.0f));
        modelPartData.addChild(EntityModelPartNames.RIGHT_ARM, ModelPartBuilder.create().uv(40, 16).cuboid(-3.0f, -2.0f, -2.0f, 4.0f, 12.0f, 4.0f, dilation), ModelTransform.pivot(-5.0f, 2.0f + pivotOffsetY, 0.0f));
        modelPartData.addChild(EntityModelPartNames.LEFT_ARM, ModelPartBuilder.create().uv(40, 16).mirrored().cuboid(-1.0f, -2.0f, -2.0f, 4.0f, 12.0f, 4.0f, dilation), ModelTransform.pivot(5.0f, 2.0f + pivotOffsetY, 0.0f));
        modelPartData.addChild(EntityModelPartNames.RIGHT_LEG, ModelPartBuilder.create().uv(0, 16).cuboid(-2.0f, 0.0f, -2.0f, 4.0f, 12.0f, 4.0f, dilation), ModelTransform.pivot(-1.9f, 12.0f + pivotOffsetY, 0.0f));
        modelPartData.addChild(EntityModelPartNames.LEFT_LEG, ModelPartBuilder.create().uv(0, 16).mirrored().cuboid(-2.0f, 0.0f, -2.0f, 4.0f, 12.0f, 4.0f, dilation), ModelTransform.pivot(1.9f, 12.0f + pivotOffsetY, 0.0f));
        return TexturedModelData.of(modelData, 64, 64);
    }
}
