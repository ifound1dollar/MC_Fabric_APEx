package net.dollar.apex.item.custom;

import net.minecraft.block.BlockState;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolMaterial;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

/**
 * Defines behavior for all Battleaxe items, which cannot mine anything and are meant as weapons (derives
 *  from SwordItem).
 */
public class ModBattleaxeItem extends Item {

    public ModBattleaxeItem(ToolMaterial toolMaterial, float attackDamage, float attackSpeed, Item.Settings settings) {
        super(toolMaterial.applySwordSettings(settings, attackDamage, attackSpeed));
    }



    @Override
    public boolean canMine(ItemStack stack, BlockState state, World world, BlockPos pos, LivingEntity user) {
        return !user.isInCreativeMode();
    }

    @Override
    public void postDamageEntity(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        stack.damage(1, attacker, EquipmentSlot.MAINHAND);
    }
}
