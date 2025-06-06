package net.dollar.apex.entity.client;

import com.google.common.collect.ImmutableMap;
import net.dollar.apex.ModMain;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.passive.Cracks;
import net.minecraft.util.Identifier;

import java.util.Map;

@Environment(value=EnvType.CLIENT)
public class ObsidianGolemCrackRenderer
        extends FeatureRenderer<ObsidianGolemRenderState, ObsidianGolemModel> {
    private static final Map<Cracks.CrackLevel, Identifier> DAMAGE_TO_TEXTURE = ImmutableMap.of(
            Cracks.CrackLevel.LOW, Identifier.of(ModMain.MOD_ID, "textures/entity/obsidian_golem_crackiness_low.png"),
            Cracks.CrackLevel.MEDIUM, Identifier.of(ModMain.MOD_ID, "textures/entity/obsidian_golem_crackiness_medium.png"),
            Cracks.CrackLevel.HIGH, Identifier.of(ModMain.MOD_ID, "textures/entity/obsidian_golem_crackiness_high.png"));

    public ObsidianGolemCrackRenderer(FeatureRendererContext<ObsidianGolemRenderState,
            ObsidianGolemModel> featureRendererContext) {
        super(featureRendererContext);
    }



    @Override
    public void render(MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, ObsidianGolemRenderState renderState, float limbAngle, float limbDistance) {
        if (!renderState.invisible) {
            Cracks.CrackLevel crackLevel = renderState.crackLevel;
            if (crackLevel != Cracks.CrackLevel.NONE) {
                Identifier identifier = DAMAGE_TO_TEXTURE.get(crackLevel);
                renderModel(this.getContextModel(), identifier, matrices, vertexConsumers, light, renderState, -1);
            }
        }
    }
}