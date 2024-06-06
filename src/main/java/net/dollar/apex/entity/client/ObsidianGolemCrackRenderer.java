package net.dollar.apex.entity.client;

import com.google.common.collect.ImmutableMap;
import net.dollar.apex.ModMain;
import net.dollar.apex.entity.custom.ObsidianGolemEntity;
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
        extends FeatureRenderer<ObsidianGolemEntity, ObsidianGolemModel<ObsidianGolemEntity>> {
    private static final Map<Cracks.CrackLevel, Identifier> DAMAGE_TO_TEXTURE = ImmutableMap.of(
            Cracks.CrackLevel.LOW, new Identifier(ModMain.MOD_ID, "textures/entities/obsidian_golem_crackiness_low.png"),
            Cracks.CrackLevel.MEDIUM, new Identifier(ModMain.MOD_ID, "textures/entities/obsidian_golem_crackiness_medium.png"),
            Cracks.CrackLevel.HIGH, new Identifier(ModMain.MOD_ID, "textures/entities/obsidian_golem_crackiness_high.png"));

    public ObsidianGolemCrackRenderer(FeatureRendererContext<ObsidianGolemEntity,
            ObsidianGolemModel<ObsidianGolemEntity>> featureRendererContext) {
        super(featureRendererContext);
    }



    @Override
    public void render(MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i,
                       ObsidianGolemEntity obsidianGolemEntity, float f, float g, float h, float j, float k, float l) {
        if (obsidianGolemEntity.isInvisible()) {
            return;
        }
        Cracks.CrackLevel crack = obsidianGolemEntity.getCrack();
        if (crack == Cracks.CrackLevel.NONE) {
            return;
        }
        Identifier identifier = DAMAGE_TO_TEXTURE.get(crack);
        net.minecraft.client.render.entity.feature.IronGolemCrackFeatureRenderer.renderModel(
                this.getContextModel(), identifier, matrixStack, vertexConsumerProvider, i,
                obsidianGolemEntity, 1.0f, 1.0f, 1.0f);
    }
}