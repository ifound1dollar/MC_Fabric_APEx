package net.dollar.apex.entity.client;

import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.world.entity.Crackiness;

public class ObsidianGolemRenderState extends LivingEntityRenderState {
    public float attackTicksLeft;
    public Crackiness.Level crackLevel;

    public ObsidianGolemRenderState() {
        this.crackLevel = Crackiness.Level.NONE;
    }
}
