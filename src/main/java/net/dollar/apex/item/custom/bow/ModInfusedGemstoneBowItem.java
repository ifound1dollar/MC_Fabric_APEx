package net.dollar.apex.item.custom.bow;

import net.dollar.apex.item.custom.arrow.ArrowUtil;
import net.dollar.apex.util.IInfusedGemstoneItem;
import net.dollar.apex.util.ModUtils;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.item.BowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.text.Text;
import net.minecraft.world.World;

import java.util.List;

public class ModInfusedGemstoneBowItem extends BowItem implements IInfusedGemstoneItem {
    public ModInfusedGemstoneBowItem(Settings settings) {
        super(settings);
    }



    /**
     * Handles firing of the arrow once the user stops using the Bow.
     * @param stack ItemStack corresponding to this Item
     * @param world Active world
     * @param user LivingEntity firing the Bow
     * @param remainingUseTicks Remaining use ticks
     */
    @Override
    public void onStoppedUsing(ItemStack stack, World world, LivingEntity user, int remainingUseTicks) {
        if (!(user instanceof PlayerEntity playerEntity)) {
            return;
        }

        ItemStack itemStack = playerEntity.getProjectileType(stack);
        if (itemStack.isEmpty()) {
            return;
        }

        int i = this.getMaxUseTime(stack, user) - remainingUseTicks;
        float f = BowItem.getPullProgress(i);
        if ((double)f < 0.1) {
            return;
        }

        List<ItemStack> list = BowItem.load(stack, itemStack, playerEntity);
        if (world instanceof ServerWorld serverWorld) {
            if (!list.isEmpty()) {
                this.shootAll(serverWorld, playerEntity, playerEntity.getActiveHand(), stack, list, f * 3.0F, 1.0F, f == 1.0F, null);
            }
        }

        world.playSound(null, playerEntity.getX(), playerEntity.getY(), playerEntity.getZ(), SoundEvents.ENTITY_ARROW_SHOOT, SoundCategory.PLAYERS, 1.0f, 1.0f / (world.getRandom().nextFloat() * 0.4f + 1.2f) + f * 0.5f);
        playerEntity.incrementStat(Stats.USED.getOrCreateStat(this));
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
    protected ProjectileEntity createArrowEntity(World world, LivingEntity shooter, ItemStack weaponStack, ItemStack projectileStack, boolean critical) {
        //Replace vanilla functionality to get the ArrowItem from the found ItemStack with this function. Will
        //  automatically handle Spectral Arrow and Tipped Arrow functionality in-method.
        PersistentProjectileEntity persistentProjectileEntity = ArrowUtil.createCustomArrow(world, shooter,
                projectileStack, ArrowUtil.ARROW_TYPE.INFUSED);

        if (critical) {
            persistentProjectileEntity.setCritical(true);
        }

        return persistentProjectileEntity;
    }



    /**
     * Appends text to the Item's hover tooltip.
     * @param stack ItemStack corresponding to this item
     * @param context TooltipContext
     * @param tooltip List of tooltip texts to render
     * @param type TooltipType determining data like simple or advanced
     */
    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        ModUtils.appendInfusedGemstoneEquipmentTooltip(tooltip, ModUtils.EquipmentType.RANGED);
    }
}
