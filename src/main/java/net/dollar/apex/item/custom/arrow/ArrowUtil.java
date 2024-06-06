package net.dollar.apex.item.custom.arrow;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ArrowUtil {
    public enum ARROW_TYPE { INFUSED, COBALT, CARBIDE }

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
                                                               ItemStack arrowStack, ARROW_TYPE type) {
        ArrowEntity arrowEntity;

        //For each case, first generate the correct type of ArrowEntity. Then, check whether it is
        //  spectral. Finally, implicitly cast it to the ArrowEntity class by assigning it to arrowEntity.
        switch (type) {
            case INFUSED -> {
                InfusedGemstoneArrowEntity temp = new InfusedGemstoneArrowEntity(world, shooter, arrowStack);
                temp.checkIsSpectral(arrowStack);
                arrowEntity = temp;
            }
            case CARBIDE -> {
                TungstenCarbideArrowEntity temp = new TungstenCarbideArrowEntity(world, shooter, arrowStack);
                temp.checkIsSpectral(arrowStack);
                arrowEntity = temp;
            }
            default -> {    //Guaranteed to be COBALT
                CobaltSteelArrowEntity temp = new CobaltSteelArrowEntity(world, shooter, arrowStack);
                temp.checkIsSpectral(arrowStack);
                arrowEntity = temp;
            }
        }

        //Before returning and implicitly casting the ArrowEntity to a PersistentProjectileEntity, initialize
        //  the ArrowEntity from the ItemStack (for Tipped Arrow behaviors).
        arrowEntity.initFromStack(arrowStack);
        return arrowEntity;
    }
}
