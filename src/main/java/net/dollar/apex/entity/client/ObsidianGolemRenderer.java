package net.dollar.apex.entity.client;

import net.dollar.apex.ModMain;
import net.dollar.apex.entity.custom.ObsidianGolemEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.RotationAxis;

@Environment(value= EnvType.CLIENT)
public class ObsidianGolemRenderer
        extends MobEntityRenderer<ObsidianGolemEntity, ObsidianGolemRenderState, ObsidianGolemModel> {
    private static final Identifier TEXTURE = Identifier.of(
            ModMain.MOD_ID, "textures/entity/obsidian_golem.png");

    public ObsidianGolemRenderer(EntityRendererFactory.Context context) {
        super(context, new ObsidianGolemModel(context.getPart(ModModelLayers.OBSIDIAN_GOLEM)), 0.7f);
        this.addFeature(new ObsidianGolemCrackRenderer(this));  //Add custom crack layer
    }



    /**
     * Sets the scale of this rendered Entity.
     * @param state The ObsidianGolemRenderState corresponding to this renderer
     * @param matrices MatrixStack corresponding to this renderer
     */
    @Override
    protected void scale(ObsidianGolemRenderState state, MatrixStack matrices) {
        //Scale the Entity's matrices by 1.25 on each axis (super.scale() function is empty, ignore).
        matrices.scale(1.25f, 1.25f, 1.25f);    }

    /**
     * Gets texture Identifier defined in top of class.
     * @param renderState The ObsidianGolemRenderState corresponding to this renderer
     * @return The corresponding Identifier
     */
    @Override
    public Identifier getTexture(ObsidianGolemRenderState renderState) {
        return TEXTURE;
    }

    public ObsidianGolemRenderState createRenderState() {
        return new ObsidianGolemRenderState();
    }

    public void updateRenderState(ObsidianGolemEntity entity, ObsidianGolemRenderState renderState, float f) {
        super.updateRenderState(entity, renderState, f);
        renderState.attackTicksLeft = (float)entity.getAttackTicksLeft() > 0.0F ? (float)entity.getAttackTicksLeft() - f : 0.0F;
        renderState.crackLevel = entity.getCrack();
    }

    @Override
    protected void setupTransforms(ObsidianGolemRenderState renderState, MatrixStack matrixStack,
                                   float f, float g) {
        super.setupTransforms(renderState, matrixStack, f, g);
        if (!((double)renderState.limbAmplitudeMultiplier < 0.01)) {
            float i = renderState.limbFrequency + 6.0F;
            float j = (Math.abs(i % 13.0F - 6.5F) - 3.25F) / 3.25F;
            matrixStack.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(6.5F * j));
        }
    }
}