package net.dollar.apex.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import net.dollar.apex.ModMain;
import net.dollar.apex.entity.custom.MysteriousSpecterEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.HumanoidMobRenderer;
import net.minecraft.resources.Identifier;
import org.jetbrains.annotations.NotNull;

@Environment(value= EnvType.CLIENT)
public class MysteriousSpecterRenderer
        extends HumanoidMobRenderer<@NotNull MysteriousSpecterEntity, @NotNull MysteriousSpecterRenderState,
        @NotNull MysteriousSpecterModel<@NotNull MysteriousSpecterRenderState>> {
    private static final String TEXTURE_BASE = "textures/entity/mysterious_specter";

    public MysteriousSpecterRenderer(EntityRendererProvider.Context context) {
        super(context, new MysteriousSpecterModel<>(context.bakeLayer(ModModelLayers.MYSTERIOUS_SPECTER)),
                0.0f);
    }



    @Override
    public MysteriousSpecterRenderState createRenderState() {
        return new MysteriousSpecterRenderState();  // Assigns random texture string here, used in getTexture()
    }

    @Override
    protected void scale(MysteriousSpecterRenderState state, PoseStack matrices) {
        matrices.scale(0.9375f, 0.9375f, 0.9375f);
    }

    @Override
    public @NotNull Identifier getTextureLocation(MysteriousSpecterRenderState renderState) {
        //Generate and return a new identifier using the TEXTURE_BASE string appended with the texture
        //  ID from the RenderState instance (plus the .png extension).
        return Identifier.fromNamespaceAndPath(ModMain.MOD_ID, TEXTURE_BASE + renderState.getTextureID() + ".png");
    }
}
