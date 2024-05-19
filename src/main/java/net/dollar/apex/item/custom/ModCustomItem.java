package net.dollar.apex.item.custom;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.tag.DamageTypeTags;
import net.minecraft.text.Text;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ModCustomItem extends Item {
    private final String tooltipLang;
    private final boolean hasGlint;
    private final boolean hasImmunities;



    public ModCustomItem(Settings settings, String tooltipLang, boolean hasGlint, boolean isFireAndBlastImmune) {
        super(settings);
        this.tooltipLang = tooltipLang;
        this.hasGlint = hasGlint;
        hasImmunities = isFireAndBlastImmune;
    }



    /**
     * Gets whether Entities of this Item can be damaged by a specific DamageSource (false for fire and explosion).
     * @param source DamageSource being checked
     * @return Whether this Item can be damaged by the DamageSource
     */
    @Override
    public boolean damage(DamageSource source) {
        if (hasImmunities) {
            return !(source.isIn(DamageTypeTags.IS_FIRE) || source.isIn(DamageTypeTags.IS_EXPLOSION));
        } else {
            return super.damage(source);
        }
    }

    /**
     * Gets whether this Item should render with enchantment glint (true).
     * @param stack ItemStack of this Item
     * @return Whether this item has enchantment glint
     */
    @Override
    public boolean hasGlint(ItemStack stack) {
        return this.hasGlint;
    }

    /**
     * Appends text to the Item's hover tooltip (lore).
     * @param stack ItemStack corresponding to this Item
     * @param world Active world
     * @param tooltip List of tooltip texts to show
     * @param context TooltipContext denoting data like simple or advanced
     */
    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        tooltip.add(Text.translatable(tooltipLang));
    }
}
