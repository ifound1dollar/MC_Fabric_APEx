package net.dollar.apex.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.dollar.apex.ModMain;
import net.dollar.apex.entity.custom.ObsidianGolemEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.Identifier;
import org.jetbrains.annotations.NotNull;

@Environment(value= EnvType.CLIENT)
public class ObsidianGolemRenderer
        extends MobRenderer<@NotNull ObsidianGolemEntity, @NotNull ObsidianGolemRenderState, @NotNull ObsidianGolemModel> {
    private static final Identifier TEXTURE = Identifier.fromNamespaceAndPath(
            ModMain.MOD_ID, "textures/entity/obsidian_golem.png");

    public ObsidianGolemRenderer(EntityRendererProvider.Context context) {
        super(context, new ObsidianGolemModel(context.bakeLayer(ModModelLayers.OBSIDIAN_GOLEM)), 0.7f);
        this.addLayer(new ObsidianGolemCrackRenderer(this));  //Add custom crack layer
    }



    /**
     * Sets the scale of this rendered Entity.
     * @param state The ObsidianGolemRenderState corresponding to this renderer
     * @param matrices MatrixStack corresponding to this renderer
     */
    @Override
    protected void scale(ObsidianGolemRenderState state, PoseStack matrices) {
        //Scale the Entity's matrices by 1.25 on each axis (super.scale() function is empty, ignore).
        matrices.scale(1.25f, 1.25f, 1.25f);    }

    /**
     * Gets texture Identifier defined in top of class.
     * @param renderState The ObsidianGolemRenderState corresponding to this renderer
     * @return The corresponding Identifier
     */
    @Override
    public @NotNull Identifier getTextureLocation(ObsidianGolemRenderState renderState) {
        return TEXTURE;
    }

    public ObsidianGolemRenderState createRenderState() {
        return new ObsidianGolemRenderState();
    }

    public void updateRenderState(ObsidianGolemEntity entity, ObsidianGolemRenderState renderState, float f) {
        super.extractRenderState(entity, renderState, f);
        renderState.attackTicksLeft = (float)entity.getAttackTicksLeft() > 0.0F ? (float)entity.getAttackTicksLeft() - f : 0.0F;
        renderState.crackLevel = entity.getCrack();
    }

    @Override
    protected void setupRotations(ObsidianGolemRenderState renderState, @NotNull PoseStack matrixStack,
                                  float f, float g) {
        super.setupRotations(renderState, matrixStack, f, g);
        if (!((double)renderState.walkAnimationSpeed < 0.01)) {
            float i = renderState.walkAnimationPos + 6.0F;
            float j = (Math.abs(i % 13.0F - 6.5F) - 3.25F) / 3.25F;
            matrixStack.mulPose(Axis.ZP.rotationDegrees(6.5F * j));
        }
    }
}