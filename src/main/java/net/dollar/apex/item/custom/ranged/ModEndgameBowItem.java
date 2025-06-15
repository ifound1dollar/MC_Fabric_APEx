package net.dollar.apex.item.custom.ranged;

import net.dollar.apex.util.ModItemUtils;
import net.minecraft.client.item.TooltipType;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.item.BowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.text.Text;
import net.minecraft.world.World;

import java.util.List;
import java.util.function.BiConsumer;

/**
 * Corresponds to an endgame-tier Bow item. Spawns a custom ArrowEntity which
 *  deals bonus damage and applies an effect on-hit.
 */
public class ModEndgameBowItem extends BowItem {
    private final ModItemUtils.EndgameTier endgameTier;
    private final BiConsumer<List<Text>, ModItemUtils.EquipmentType> tooltipMethod;

    /**
     * Instantiates a new endgame-tier Bow item for the passed-in EndgameTier. Spawns
     *  a custom ArrowEntity when fired which deals bonus damage and applies an effect on-hit.
     * @param tier EndgameTier for this Bow item
     * @param settings Item.Settings for this Bow item
     */
    public ModEndgameBowItem(ModItemUtils.EndgameTier tier, Settings settings) {
        super(settings);

        // Set endgameTier field and tooltip method reference based on passed-in EndgameTier.
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
    public void onStoppedUsing(ItemStack stack, World world, LivingEntity user, int remainingUseTicks) {
        if (!(user instanceof PlayerEntity playerEntity)) {
            return;
        }
        ItemStack itemStack = playerEntity.getProjectileType(stack);
        if (itemStack.isEmpty()) {
            return;
        }
        int i = this.getMaxUseTime(stack) - remainingUseTicks;
        float f = BowItem.getPullProgress(i);
        if ((double)f < 0.1) {
            return;
        }
        List<ItemStack> list = BowItem.load(stack, itemStack, playerEntity);
        if (!world.isClient() && !list.isEmpty()) {
            this.shootAll(world, playerEntity, playerEntity.getActiveHand(), stack, list, f * 3.0f, 1.0f, f == 1.0f, null);
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
        int k;
        int j;
        int i;

        //Replace vanilla functionality to get the ArrowItem from the found ItemStack with this function. Will
        //  automatically handle Spectral Arrow and Tipped Arrow functionality in-method.
        PersistentProjectileEntity persistentProjectileEntity = ModItemUtils.createCustomArrow(world, shooter,
                projectileStack, endgameTier);

        if (critical) {
            persistentProjectileEntity.setCritical(true);
        }
        if ((i = EnchantmentHelper.getLevel(Enchantments.POWER, weaponStack)) > 0) {
            persistentProjectileEntity.setDamage(persistentProjectileEntity.getDamage() + (double)i * 0.5 + 0.5);
        }
        if ((j = EnchantmentHelper.getLevel(Enchantments.PUNCH, weaponStack)) > 0) {
            persistentProjectileEntity.setPunch(j);
        }
        if (EnchantmentHelper.getLevel(Enchantments.FLAME, weaponStack) > 0) {
            persistentProjectileEntity.setOnFireFor(100);
        }
        if ((k = EnchantmentHelper.getLevel(Enchantments.PIERCING, weaponStack)) > 0) {
            persistentProjectileEntity.setPierceLevel((byte)k);
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
        tooltipMethod.accept(tooltip, ModItemUtils.EquipmentType.RANGED);
    }
}
