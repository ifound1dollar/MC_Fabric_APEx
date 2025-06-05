package net.dollar.apex.item.custom;

import net.dollar.apex.util.ModTags;
import net.minecraft.item.*;

/**
 * Defines behavior for all Paxel items, which can mine all Axe, Pickaxe, and Shovel-mineable items.
 */
public class ModPaxelItem extends Item {
    public ModPaxelItem(ToolMaterial material, float attackDamage, float attackSpeed, Settings settings) {
        super(settings.tool(
                material, ModTags.Blocks.MOD_PAXEL_MINEABLE, attackDamage, attackSpeed, 0.0f));
    }
}
