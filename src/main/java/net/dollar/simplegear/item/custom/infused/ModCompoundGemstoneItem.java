package net.dollar.simplegear.item.custom.infused;

import net.minecraft.item.Item;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.util.ActionResult;

/**
 * Used specifically for the Compound Gemstone, which NO LONGER has custom interaction when used on a Spectral Lantern.
 */
@Deprecated
public class ModCompoundGemstoneItem extends Item {
    public ModCompoundGemstoneItem(Settings settings) {
        super(settings);
    }



    /**
     * Handles interaction when a player right clicks something with this Item. Specifically,
     *  when a player interacts with a Spectral Lantern while holding this Item.
     * NOTE: This is the first method called regarding this specific interaction.
     * @param context Generated UseOnContext
     * @return The corresponding InteractionResult; if CONSUME, prevents all further operations
     *  on this interaction
     */
    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
//        if (context.getWorld().getBlockEntity(context.getBlockPos()) instanceof ModSpectralLanternBlockEntity tile) {
//            //CONSUME only if method within ShrineBlockTile returned true, otherwise PASS below.
//            if (tile.attemptSpawnBoss(context)) {
//                return ActionResult.CONSUME;
//            }
//        }
//
//        //If not SpectralLanternBlockEntity OR mob could not be spawned, PASS.
//        return ActionResult.PASS;
        return ActionResult.PASS;
    }
}
