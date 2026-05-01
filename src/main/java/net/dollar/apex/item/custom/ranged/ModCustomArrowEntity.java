package net.dollar.apex.item.custom.ranged;

import net.dollar.apex.util.ModItemUtils;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.arrow.Arrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SpectralArrowItem;
import net.minecraft.world.level.Level;
import java.util.function.Consumer;

public class ModCustomArrowEntity extends Arrow {
    private boolean isSpectral;
    private final Consumer<LivingEntity> onHitMethod;

    /**
     * Instantiates a custom ArrowEntity, which allows explicitly overriding damage and on-hit effects.
     * @param world World the arrow entity is being spawned within
     * @param owner LivingEntity which is spawning this ArrowEntity
     * @param arrowStack ItemStack of the arrow Item this arrow entity is spawning from
     * @param weaponStack ItemStack of the weapon shooting this ArrowEntity
     * @param tier EndgameTier this ArrowEntity is being spawned for, determines on-hit effect
     */
    public ModCustomArrowEntity(Level world, LivingEntity owner, ItemStack arrowStack, ItemStack weaponStack,
                                ModItemUtils.EndgameTier tier) {
        super(world, owner, arrowStack, weaponStack);
        setBaseDamage(3.0f);

        switch (tier) {
            case COBALT_STEEL -> onHitMethod = ModItemUtils::applyCobaltSteelOnHit;
            case INFUSED_GEMSTONE -> onHitMethod = ModItemUtils::applyInfusedGemstoneOnHit;
            case TUNGSTEN_CARBIDE -> onHitMethod = ModItemUtils::applyTungstenCarbideOnHit;
            default -> throw new IllegalStateException("Unexpected value: " + tier);
        }
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
    protected void doPostHurtEffects(LivingEntity target) {
        super.doPostHurtEffects(target);

        //If the arrow is spectral, make the target glowing (same functionality as actual Spectral Arrow).
        if (isSpectral) {
            MobEffectInstance statusEffectInstance = new MobEffectInstance(
                    MobEffects.GLOWING, 200, 0); //10 seconds
            target.addEffect(statusEffectInstance, this.getEffectSource());
        }

        // Apply special on-hit effect when this arrow entity hits a LivingEntity.
        onHitMethod.accept(target);
    }
}
