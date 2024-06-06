package net.dollar.apex.item.custom;

import net.dollar.apex.util.ModTags;
import net.minecraft.item.*;

/**
 * Defines behavior for all Paxel items, which can mine all Axe, Pickaxe, and Shovel-mineable items.
 */
public class ModPaxelItem extends MiningToolItem {
    public ModPaxelItem(ToolMaterial material, Settings settings) {
        super(material, ModTags.Blocks.MOD_PAXEL_MINEABLE, settings);
    }
}
