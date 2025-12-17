package net.dollar.apex.entity.client;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.*;
import net.minecraft.client.render.RenderLayers;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.render.entity.model.EntityModelPartNames;

@Environment(EnvType.CLIENT)
public class MysteriousSpecterModel<T extends MysteriousSpecterRenderState> extends BipedEntityModel<T> {
    public MysteriousSpecterModel(ModelPart root) {
        super(root, RenderLayers::entityTranslucent);
    }



    @Override
    public void setAngles(T bipedEntityRenderState) {
        super.setAngles(bipedEntityRenderState);
    }

    public static TexturedModelData getTexturedModelData() {
        //Make dilation and pivotOffsetY variables (effectively copied from BipedEntityModel) and set to defaults.
        Dilation dilation = Dilation.NONE;
        float pivotOffsetY = 0.0f;

        ModelData modelData = getModelData(dilation, pivotOffsetY);    //I am clueless about these values.
        ModelPartData modelPartData = modelData.getRoot();
        modelPartData.addChild(EntityModelPartNames.HEAD, ModelPartBuilder.create().uv(0, 0).cuboid(-4.0f, -8.0f, -4.0f, 8.0f, 8.0f, 8.0f, dilation), ModelTransform.origin(0.0f, 0.0f + pivotOffsetY, 0.0f));
        modelPartData.addChild(EntityModelPartNames.HAT, ModelPartBuilder.create().uv(32, 0).cuboid(-4.0f, -8.0f, -4.0f, 8.0f, 8.0f, 8.0f, dilation.add(0.5f)), ModelTransform.origin(0.0f, 0.0f + pivotOffsetY, 0.0f));
        modelPartData.addChild(EntityModelPartNames.BODY, ModelPartBuilder.create().uv(16, 16).cuboid(-4.0f, 0.0f, -2.0f, 8.0f, 12.0f, 4.0f, dilation), ModelTransform.origin(0.0f, 0.0f + pivotOffsetY, 0.0f));
        modelPartData.addChild(EntityModelPartNames.RIGHT_ARM, ModelPartBuilder.create().uv(40, 16).cuboid(-3.0f, -2.0f, -2.0f, 4.0f, 12.0f, 4.0f, dilation), ModelTransform.origin(-5.0f, 2.0f + pivotOffsetY, 0.0f));
        modelPartData.addChild(EntityModelPartNames.LEFT_ARM, ModelPartBuilder.create().uv(40, 16).mirrored().cuboid(-1.0f, -2.0f, -2.0f, 4.0f, 12.0f, 4.0f, dilation), ModelTransform.origin(5.0f, 2.0f + pivotOffsetY, 0.0f));
        modelPartData.addChild(EntityModelPartNames.RIGHT_LEG, ModelPartBuilder.create().uv(0, 16).cuboid(-2.0f, 0.0f, -2.0f, 4.0f, 12.0f, 4.0f, dilation), ModelTransform.origin(-1.9f, 12.0f + pivotOffsetY, 0.0f));
        modelPartData.addChild(EntityModelPartNames.LEFT_LEG, ModelPartBuilder.create().uv(0, 16).mirrored().cuboid(-2.0f, 0.0f, -2.0f, 4.0f, 12.0f, 4.0f, dilation), ModelTransform.origin(1.9f, 12.0f + pivotOffsetY, 0.0f));
        return TexturedModelData.of(modelData, 64, 64);
    }
}
