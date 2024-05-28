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
    private static final Identifier TEXTURE = new Identifier(
            ModMain.MOD_ID, "textures/entities/mysterious_specter0.png");

    public MysteriousSpecterRenderer(EntityRendererFactory.Context context) {
        super(context, new MysteriousSpecterModel<>(context.getPart(ModModelLayers.MYSTERIOUS_SPECTER)), 0.6f);
    }



    @Override
    protected void scale(MysteriousSpecterEntity entity, MatrixStack matrices, float amount) {
        matrices.scale(0.9375f, 0.9375f, 0.9375f);
    }

    @Override
    public Identifier getTexture(MysteriousSpecterEntity entity) {
        //TODO: Get value from Entity class which determines which texture to return.
        return TEXTURE;
    }
}
