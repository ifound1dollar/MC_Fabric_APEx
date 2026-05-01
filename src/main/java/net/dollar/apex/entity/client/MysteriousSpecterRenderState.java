package net.dollar.apex.entity.client;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.renderer.entity.state.HumanoidRenderState;

@Environment(EnvType.CLIENT)
public class MysteriousSpecterRenderState extends HumanoidRenderState {
    private final int textureID;

    public MysteriousSpecterRenderState() {
        //Set textureID to a value between 0-4, which is used to determine which texture to render.
        //textureID = Random.create().nextInt(5);

        // Above was causing rapid swapping between textures for some reason, change to always 0.
        this.textureID = 0;
    }


    /**
     * Gets the textureID field (in range of 0-4), which will determine which texture to load for the
     *  Entity associated with this RenderState instance.
     * @return The textureID field value
     */
    public int getTextureID() {
        return textureID;
    }
}
