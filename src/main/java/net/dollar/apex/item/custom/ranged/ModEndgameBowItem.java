package net.dollar.apex.item.custom.ranged;

import net.dollar.apex.util.ModItemUtils;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.arrow.AbstractArrow;
import net.minecraft.world.item.BowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.level.Level;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

/**
 * Corresponds to an endgame-tier Bow item. Spawns a custom ArrowEntity which
 *  deals bonus damage and applies an effect on-hit.
 */
public class ModEndgameBowItem extends BowItem {
    private final ModItemUtils.EndgameTier endgameTier;
    private final BiConsumer<Consumer<Component>, ModItemUtils.EquipmentType> tooltipMethod;

    /**
     * Instantiates a new endgame-tier Bow item for the passed-in EndgameTier. Spawns
     *  a custom ArrowEntity when fired which deals bonus damage and applies an effect on-hit.
     * @param tier EndgameTier for this Bow item
     * @param settings Item.Settings for this Bow item
     */
    public ModEndgameBowItem(ModItemUtils.EndgameTier tier, Properties settings) {
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



    /**
     * Handles firing of the arrow once the user stops using the Bow.
     * @param stack ItemStack corresponding to this Item
     * @param world Active world
     * @param user LivingEntity firing the Bow
     * @param remainingUseTicks Remaining use ticks
     */
    @Override
    public boolean releaseUsing(ItemStack stack, Level world, LivingEntity user, int remainingUseTicks) {
        if (!(user instanceof Player playerEntity)) {
            return false;
        }

        ItemStack itemStack = playerEntity.getProjectile(stack);
        if (itemStack.isEmpty()) {
            return false;
        }

        int i = this.getUseDuration(stack, user) - remainingUseTicks;
        float f = BowItem.getPowerForTime(i);
        if ((double)f < 0.1) {
            return false;
        }

        List<ItemStack> list = BowItem.draw(stack, itemStack, playerEntity);
        if (world instanceof ServerLevel serverWorld) {
            if (!list.isEmpty()) {
                this.shoot(serverWorld, playerEntity, playerEntity.getUsedItemHand(), stack, list, f * 3.0F, 1.0F, f == 1.0F, null);
            }
        }

        world.playSound(null, playerEntity.getX(), playerEntity.getY(), playerEntity.getZ(), SoundEvents.ARROW_SHOOT, SoundSource.PLAYERS, 1.0f, 1.0f / (world.getRandom().nextFloat() * 0.4f + 1.2f) + f * 0.5f);
        playerEntity.awardStat(Stats.ITEM_USED.get(this));
        return true;
    }

    /**
     * Creates the ArrowEntity when fired, called automatically outside this class.
     * @param world Active World
     * @param shooter Shooter LivingEntity
     * @param weaponStack ItemStack corresponding to this weapon
     * @param projectileStack ItemStack corresponding to the arrow to be fired
     * @param critical Whether the arrow will be a critical hit
     * @return The generated ProjectileEntity
     */
    @Override
    protected Projectile createProjectile(Level world, LivingEntity shooter, ItemStack weaponStack, ItemStack projectileStack, boolean critical) {
        //Replace vanilla functionality to get the ArrowItem from the found ItemStack with this function. Will
        //  automatically handle Spectral Arrow and Tipped Arrow functionality in-method.
        AbstractArrow persistentProjectileEntity = ModItemUtils.createCustomArrow(world, shooter,
                projectileStack, weaponStack, endgameTier);

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
    }
}
