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
        extends BipedEntityRenderer<MysteriousSpecterEntity, MysteriousSpecterModel<MysteriousSpecterEntity>> {
    private static final String TEXTURE_BASE = "textures/entities/mysterious_specter";

    public MysteriousSpecterRenderer(EntityRendererFactory.Context context) {
        super(context, new MysteriousSpecterModel<>(context.getPart(ModModelLayers.MYSTERIOUS_SPECTER)), 0.6f);
    }



    @Override
    protected void scale(MysteriousSpecterEntity entity, MatrixStack matrices, float amount) {
        matrices.scale(0.9375f, 0.9375f, 0.9375f);
    }

    @Override
    public Identifier getTexture(MysteriousSpecterEntity entity) {
        //Generate and return a new identifier using the TEXTURE_BASE string appended with the texture
        //  ID from the Entity instance (plus the .png extension).
        return new Identifier(ModMain.MOD_ID, TEXTURE_BASE + entity.getTextureID() + ".png");
    }
}
