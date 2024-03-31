package net.dollar.simplegear.item.custom;

import net.dollar.simplegear.util.ModTags;
import net.dollar.simplegear.util.ModUtils;
import net.minecraft.item.*;

/**
 * Defines behavior for all Paxel items, which can mine all Axe, Pickaxe, and Shovel-mineable items.
 */
public class ModPaxelItem extends MiningToolItem {
    public ModPaxelItem(ToolMaterial material, float attackDamage, float attackSpeed, Settings settings) {
        super(attackDamage, attackSpeed, material, ModTags.Blocks.MOD_PAXEL_MINEABLE, settings);
    }
}
