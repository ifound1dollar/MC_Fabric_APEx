package net.dollar.simplegear.item.custom.carbide;

import net.minecraft.item.Item;

@Deprecated
public class ModMoltenCoreItem extends Item {
    public ModMoltenCoreItem(Settings settings) {
        super(settings);
    }



    /**
     * Gets whether Entities of this Item are fireproof (true).
     * @return Whether this Item is fireproof
     */
    @Override
    public boolean isFireproof() {
        return true;
    }
}
