package net.dollar.apex.entity.client;

import com.google.common.collect.ImmutableMap;
import com.mojang.blaze3d.vertex.PoseStack;
import net.dollar.apex.ModMain;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.Crackiness;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

@Environment(value=EnvType.CLIENT)
public class ObsidianGolemCrackRenderer
        extends RenderLayer<@NotNull ObsidianGolemRenderState, @NotNull ObsidianGolemModel> {
    private static final Map<Crackiness.Level, Identifier> CRACK_TEXTURES = ImmutableMap.of(
            Crackiness.Level.LOW, Identifier.fromNamespaceAndPath(ModMain.MOD_ID, "textures/entity/obsidian_golem_crackiness_low.png"),
            Crackiness.Level.MEDIUM, Identifier.fromNamespaceAndPath(ModMain.MOD_ID, "textures/entity/obsidian_golem_crackiness_medium.png"),
            Crackiness.Level.HIGH, Identifier.fromNamespaceAndPath(ModMain.MOD_ID, "textures/entity/obsidian_golem_crackiness_high.png"));

    public ObsidianGolemCrackRenderer(RenderLayerParent<@NotNull ObsidianGolemRenderState,
            @NotNull ObsidianGolemModel> featureRendererContext) {
        super(featureRendererContext);
    }



    @Override
    public void submit(@NotNull PoseStack matrices, @NotNull SubmitNodeCollector orderedRenderCommandQueue, int light,
                       ObsidianGolemRenderState renderState, float limbAngle, float limbDistance) {
        if (!renderState.isInvisible) {
            Crackiness.Level crackLevel = renderState.crackLevel;
            if (crackLevel != Crackiness.Level.NONE) {
                Identifier identifier = CRACK_TEXTURES.get(crackLevel);
                renderColoredCutoutModel(this.getParentModel(), identifier, matrices, orderedRenderCommandQueue, light,
                        renderState, -1, 1);        // Last argument is for render order, leave default.
            }
        }
    }
}