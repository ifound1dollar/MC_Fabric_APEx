package net.dollar.apex.item.custom.arrow;

import net.dollar.apex.util.ModArrowUtils;
import net.dollar.apex.util.ModItemUtils;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SpectralArrowItem;
import net.minecraft.world.World;

public class ModCustomArrowEntity extends ArrowEntity {
    private boolean isSpectral;
    private final ModArrowUtils.ArrowType arrowType;

    public ModCustomArrowEntity(World world, LivingEntity owner, ItemStack arrowStack, ItemStack weaponStack,
                                ModArrowUtils.ArrowType arrowType) {
        super(world, owner, arrowStack, weaponStack);
        this.arrowType = arrowType;
        setDamage(3.0f);
    }



    /**
     * Checks whether the passed-in ItemStack's corresponding Item is a SpectralArrowItem, setting
     *  the local isSpectral variable if so (affects onHit() behavior).
     * @param arrow ItemStack of the ArrowItem used to spawn this ArrowEntity
     */
    public void checkIsSpectral(ItemStack arrow) {
        if (arrow.getItem() instanceof SpectralArrowItem) { isSpectral = true; }
    }

    /**
     * Performs operations as the arrow hits a target LivingEntity.
     * @param target The collided LivingEntity
     */
    @Override
    protected void onHit(LivingEntity target) {
        super.onHit(target);

        //If the arrow is spectral, make the target glowing (same functionality as actual Spectral Arrow).
        if (isSpectral) {
            StatusEffectInstance statusEffectInstance = new StatusEffectInstance(
                    StatusEffects.GLOWING, 200, 0); //10 seconds
            target.addStatusEffect(statusEffectInstance, this.getEffectCause());
        }

        // Apply special on-hit effect when this arrow entity hits a LivingEntity.
        switch (arrowType) {
            case COBALT_STEEL -> ModItemUtils.applyCobaltSteelOnHit(target);
            case INFUSED_GEMSTONE -> ModItemUtils.applyInfusedGemstoneOnHit(target);
            case TUNGSTEN_CARBIDE -> ModItemUtils.applyTungstenCarbideOnHit(target);
        }
    }
}
