package net.dollar.apex.entity.client;

import net.dollar.apex.ModMain;
import net.dollar.apex.entity.custom.MysteriousSpecterEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.BipedEntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

@Environment(value= EnvType.CLIENT)
public class MysteriousSpecterRenderer
        extends BipedEntityRenderer<MysteriousSpecterEntity, MysteriousSpecterRenderState, MysteriousSpecterModel<MysteriousSpecterRenderState>> {
    private static final String TEXTURE_BASE = "textures/entity/mysterious_specter";

    public MysteriousSpecterRenderer(EntityRendererFactory.Context context) {
        super(context, new MysteriousSpecterModel<>(context.getPart(ModModelLayers.MYSTERIOUS_SPECTER)),
                0.6f);
    }



    @Override
    public MysteriousSpecterRenderState createRenderState() {
        return new MysteriousSpecterRenderState();  // Assigns random texture string here, used in getTexture()
    }

    @Override
    protected void scale(MysteriousSpecterRenderState state, MatrixStack matrices) {
        matrices.scale(0.9375f, 0.9375f, 0.9375f);
    }

    @Override
    public Identifier getTexture(MysteriousSpecterRenderState renderState) {
        //Generate and return a new identifier using the TEXTURE_BASE string appended with the texture
        //  ID from the RenderState instance (plus the .png extension).
        return Identifier.of(ModMain.MOD_ID, TEXTURE_BASE + renderState.getTextureID() + ".png");
    }
}
