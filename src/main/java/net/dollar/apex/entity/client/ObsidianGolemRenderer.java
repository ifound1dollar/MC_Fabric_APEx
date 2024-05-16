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
        extends MobEntityRenderer<ObsidianGolemEntity, ObsidianGolemModel<ObsidianGolemEntity>> {
    private static final Identifier TEXTURE = new Identifier(
            ModMain.MOD_ID, "textures/entities/obsidian_golem.png");

    public ObsidianGolemRenderer(EntityRendererFactory.Context context) {
        super(context, new ObsidianGolemModel<>(context.getPart(ModModelLayers.OBSIDIAN_GOLEM)), 0.7f);
        this.addFeature(new ObsidianGolemCrackRenderer(this));  //Add custom crack layer
    }

    /**
     * Sets the scale of this rendered Entity.
     * @param entity The ObsidianGolemEntity being rendered
     * @param matrices MatrixStack corresponding to this renderer
     * @param amount Default scale amount (ignored here)
     */
    @Override
    protected void scale(ObsidianGolemEntity entity, MatrixStack matrices, float amount) {
        //Scale the Entity's matrices by 1.25 on each axis (super.scale() function is empty, ignore).
        matrices.scale(1.25f, 1.25f, 1.25f);
    }

    /**
     * Gets texture Identifier defined in top of class.
     * @param obsidianGolemEntity ObsidianGolemEntity being rendered
     * @return The corresponding Identifier
     */
    @Override
    public Identifier getTexture(ObsidianGolemEntity obsidianGolemEntity) {
        return TEXTURE;
    }

    @Override
    protected void setupTransforms(ObsidianGolemEntity obsidianGolemEntity, MatrixStack matrixStack, float f, float g, float h) {
        super.setupTransforms(obsidianGolemEntity, matrixStack, f, g, h);
        if ((double)obsidianGolemEntity.limbAnimator.getSpeed() < 0.01) {
            return;
        }
        float j = obsidianGolemEntity.limbAnimator.getPos(h) + 6.0f;
        float k = (Math.abs(j % 13.0f - 6.5f) - 3.25f) / 3.25f;
        matrixStack.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(6.5f * k));
    }
}