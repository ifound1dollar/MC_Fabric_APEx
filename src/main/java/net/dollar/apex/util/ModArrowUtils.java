package net.dollar.apex.util;

import net.dollar.apex.item.custom.arrow.ModCustomArrowEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ModArrowUtils {
    public enum ArrowType { COBALT_STEEL, INFUSED_GEMSTONE, TUNGSTEN_CARBIDE }



    /**
     * Creates a custom arrow entity specific to the Steel, Infused Gemstone, Netherite, or Tungsten-Carbide
     *  bows/crossbows. Each is of a custom ArrowEntity class with special onHit() functionality.
     * @param world Active world
     * @param shooter LivingEntity firing the weapon
     * @param arrowStack ItemStack where the arrow is pulled from (used for Spectral/Tipped behavior)
     * @param type Enum determining which of the four bow/crossbow types to spawn the ArrowEntity for
     * @return The newly created custom PersistentProjectileEntity
     */
    public static PersistentProjectileEntity createCustomArrow(World world, LivingEntity shooter,
                                                               ItemStack arrowStack, ItemStack weaponStack,
                                                               ArrowType type) {
        // Create custom arrow entity, then check for spectral and return the initialized entity.
        ModCustomArrowEntity arrowEntity = new ModCustomArrowEntity(world, shooter, arrowStack, weaponStack, type);
        arrowEntity.checkIsSpectral(arrowStack);
        return arrowEntity;
    }
}
