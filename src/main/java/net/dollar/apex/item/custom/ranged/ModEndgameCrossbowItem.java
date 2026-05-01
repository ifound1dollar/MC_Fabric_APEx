package net.dollar.apex.item.custom.ranged;

import net.dollar.apex.util.ModItemUtils;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.FireworkRocketEntity;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.arrow.AbstractArrow;
import net.minecraft.world.item.CrossbowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.level.Level;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

/**
 * Corresponds to an endgame-tier Crossbow item. Spawns a custom ArrowEntity which
 *  deals bonus damage and applies an effect on-hit.
 */
public class ModEndgameCrossbowItem extends CrossbowItem {
    private final ModItemUtils.EndgameTier endgameTier;
    private final BiConsumer<Consumer<Component>, ModItemUtils.EquipmentType> tooltipMethod;

    /**
     * Instantiates a new endgame-tier Crossbow item for the passed-in EndgameTier. Spawns
     *  a custom ArrowEntity when fired which deals bonus damage and applies an effect on-hit.
     * @param tier EndgameTier for this Crossbow item
     * @param settings Item.Settings for this Crossbow item
     */
    public ModEndgameCrossbowItem(ModItemUtils.EndgameTier tier, Properties settings) {
        super(settings);

        // Set EndgameTier field (for use in creating custom arrow) and assign tooltip BiConsumer.
        this.endgameTier = tier;
        switch (tier) {
            case COBALT_STEEL -> tooltipMethod = ModItemUtils::appendCobaltSteelEquipmentTooltip;
            case INFUSED_GEMSTONE -> tooltipMethod = ModItemUtils::appendInfusedGemstoneEquipmentTooltip;
            case TUNGSTEN_CARBIDE -> tooltipMethod = ModItemUtils::appendTungstenCarbideEquipmentTooltip;
            default -> throw new IllegalStateException("Unexpected value: " + tier);
        }
    }



    //ONLY OVERRIDDEN METHODS ARE createArrowEntity() and appendTooltip(). Minecraft 1.20.5 largely fixed Crossbow
    //  implementation so the base methods do exactly what they need to. The customArrowEntity() method below
    //  is a new method that actually spawns the Entity by calling ArrowUtil.createCustomArrow().

    /**
     * Creates an arrow ProjectileEntity when the crossbow is fired. Is used to override vanilla functionality
     *  and spawn a custom ArrowEntity.
     * @param world Active world
     * @param shooter LivingEntity firing the crossbow
     * @param weaponStack ItemStack corresponding to this Crossbow
     * @param projectileStack ItemStack corresponding to the (now unused) Arrow stack
     * @param critical Whether the
     * @return The generated ProjectileEntity
     */
    @Override
    protected Projectile createProjectile(Level world, LivingEntity shooter, ItemStack weaponStack, ItemStack projectileStack, boolean critical) {
        if (projectileStack.is(Items.FIREWORK_ROCKET)) {
            return new FireworkRocketEntity(world, projectileStack, shooter, shooter.getX(), shooter.getEyeY() - (double)0.15f, shooter.getZ(), true);
        }

        // Vanilla functionality replaced from here on.
        AbstractArrow projectileEntity = customArrowEntity(world, shooter, projectileStack,
                weaponStack, critical);
        projectileEntity.setSoundEvent(SoundEvents.CROSSBOW_HIT);
        return projectileEntity;
    }

    /**
     * Creates a custom arrow Entity using ArrowUtil that effectively replaces the method in RangedWeaponItem.
     * @param world Active world
     * @param entity LivingEntity firing the Crossbow
     * @param projectileStack ItemStack corresponding to the projectile being fired
     * @param critical Whether the arrow will be critical
     * @return The generated custom PersistentProjectileEntity
     */
    private AbstractArrow customArrowEntity(Level world, LivingEntity entity,
                                                                ItemStack projectileStack, ItemStack weaponStack,
                                                                boolean critical) {
        //Replace vanilla functionality to get the ArrowItem from the found ItemStack with this function. Will
        //  automatically handle Spectral Arrow and Tipped Arrow functionality in-method.
        AbstractArrow persistentProjectileEntity = ModItemUtils.createCustomArrow(world, entity,
                projectileStack, weaponStack, endgameTier);

        //Remainder of original function (with arrow creation omitted) is below.
        if (critical) {
            persistentProjectileEntity.setCritArrow(true);
        }

        return persistentProjectileEntity;
    }

    /**
     * Appends text to the Item's hover tooltip.
     * @param stack ItemStack corresponding to this item
     * @param context TooltipContext
     * @param displayComponent TooltipDisplayComponent associated with this tooltip
     * @param textConsumer Consumer of tooltip texts to render
     * @param type TooltipType determining data like simple or advanced
     */
    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, TooltipDisplay displayComponent, Consumer<Component> textConsumer, TooltipFlag type) {
        tooltipMethod.accept(textConsumer, ModItemUtils.EquipmentType.RANGED);

        //Call super function because it has return statement if not charged.
        super.appendHoverText(stack, context, displayComponent, textConsumer, type);
    }
}
