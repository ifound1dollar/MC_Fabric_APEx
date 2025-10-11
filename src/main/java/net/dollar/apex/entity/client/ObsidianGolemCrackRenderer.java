package net.dollar.apex.entity.client;

import com.google.common.collect.ImmutableMap;
import net.dollar.apex.ModMain;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.command.OrderedRenderCommandQueue;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.passive.Cracks;
import net.minecraft.util.Identifier;

import java.util.Map;

@Environment(value=EnvType.CLIENT)
public class ObsidianGolemCrackRenderer
        extends FeatureRenderer<ObsidianGolemRenderState, ObsidianGolemModel> {
    private static final Map<Cracks.CrackLevel, Identifier> CRACK_TEXTURES = ImmutableMap.of(
            Cracks.CrackLevel.LOW, Identifier.of(ModMain.MOD_ID, "textures/entity/obsidian_golem_crackiness_low.png"),
            Cracks.CrackLevel.MEDIUM, Identifier.of(ModMain.MOD_ID, "textures/entity/obsidian_golem_crackiness_medium.png"),
            Cracks.CrackLevel.HIGH, Identifier.of(ModMain.MOD_ID, "textures/entity/obsidian_golem_crackiness_high.png"));

    public ObsidianGolemCrackRenderer(FeatureRendererContext<ObsidianGolemRenderState,
            ObsidianGolemModel> featureRendererContext) {
        super(featureRendererContext);
    }



    @Override
    public void render(MatrixStack matrices, OrderedRenderCommandQueue orderedRenderCommandQueue, int light,
                       ObsidianGolemRenderState renderState, float limbAngle, float limbDistance) {
        if (!renderState.invisible) {
            Cracks.CrackLevel crackLevel = renderState.crackLevel;
            if (crackLevel != Cracks.CrackLevel.NONE) {
                Identifier identifier = CRACK_TEXTURES.get(crackLevel);
                renderModel(this.getContextModel(), identifier, matrices, orderedRenderCommandQueue, light,
                        renderState, -1, 1);        // Last argument is for render order, leave default.
            }
        }
    }
}