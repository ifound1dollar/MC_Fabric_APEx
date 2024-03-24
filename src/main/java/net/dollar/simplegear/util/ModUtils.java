package net.dollar.simplegear.util;

import net.minecraft.block.Block;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageTypes;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.AxeItem;
import net.minecraft.item.Item;
import net.minecraft.item.SwordItem;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.util.List;

/**
 * Contains a handful of misc. helper methods used in various parts of the mod.
 */
public class ModUtils {
    public static final TagKey<Block> TAG_PAXEL_MINEABLE = TagKey.of(RegistryKeys.BLOCK,
            new Identifier("simplegear", "paxel_mineable"));

    @Deprecated
    public enum DamageCategory { NONE, BLUNT, SHARP, MAGIC, FIRE, EXPLOSION }

    /**
     * Takes a vanilla DamageSource and determines which custom DamageCategory it should fall under.
     * @param source Vanilla DamageSource
     * @return The determined DamageCategory (NONE if inconclusive)
     */
    @Deprecated
    public static DamageCategory getDamageCategory(DamageSource source)
    {

        //FIRST, check if source is from a mob or player (will check for held item in this case)
        if (source.isOf(DamageTypes.MOB_ATTACK) || source.isOf(DamageTypes.MOB_ATTACK_NO_AGGRO)
                || source.isOf(DamageTypes.PLAYER_ATTACK)) {
            //access attacker LivingEntity if valid, checking its held item
            if (source.getSource() instanceof LivingEntity attacker) {
                //only Swords and Axes should be considered sharp weapons
                Item heldItem = attacker.getEquippedStack(EquipmentSlot.MAINHAND).getItem();
                if (heldItem instanceof SwordItem || heldItem instanceof AxeItem) {
                    return DamageCategory.SHARP;
                }
            }
            //If attacked directly from mob or player BUT IS NOT SHARP, assume BLUNT.
            return DamageCategory.BLUNT;
        }

        //THEN, if damage is not directly coming from a mob or player attack, can check other categories
        if (source.isOf(DamageTypes.ARROW) || source.isOf(DamageTypes.CACTUS)
                || source.isOf(DamageTypes.FALLING_STALACTITE) || source.isOf(DamageTypes.STALAGMITE)
                || source.isOf(DamageTypes.STING) || source.isOf(DamageTypes.SWEET_BERRY_BUSH)
                || source.isOf(DamageTypes.THORNS) || source.isOf(DamageTypes.TRIDENT)) {
            return DamageCategory.SHARP;
        } else if (source.isOf(DamageTypes.FALLING_ANVIL) || source.isOf(DamageTypes.FALLING_BLOCK)
                || source.isOf(DamageTypes.FLY_INTO_WALL) || source.isOf(DamageTypes.MOB_PROJECTILE)
                || source.isOf(DamageTypes.THROWN)) {
            return DamageCategory.BLUNT;
        } else if (source.isOf(DamageTypes.DRAGON_BREATH) || source.isOf(DamageTypes.INDIRECT_MAGIC)
                || source.isOf(DamageTypes.MAGIC) || source.isOf(DamageTypes.SONIC_BOOM)
                || source.isOf(DamageTypes.WITHER)) {
            return DamageCategory.MAGIC;
        } else if (source.isOf(DamageTypes.FIREBALL) || source.isOf(DamageTypes.HOT_FLOOR)
                || source.isOf(DamageTypes.IN_FIRE) || source.isOf(DamageTypes.LAVA)
                || source.isOf(DamageTypes.ON_FIRE) || source.isOf(DamageTypes.UNATTRIBUTED_FIREBALL)) {
            return DamageCategory.FIRE;
        } else if (source.isOf(DamageTypes.EXPLOSION) || source.isOf(DamageTypes.FIREWORKS)
                || source.isOf(DamageTypes.PLAYER_EXPLOSION) || source.isOf(DamageTypes.WITHER_SKULL)) {
            return DamageCategory.EXPLOSION;
        }

        //FINALLY, if not specific above, return NONE
        return DamageCategory.NONE;
    }



    /**
     * Applies special effect on attack using Cobalt-Steel tools/weapons.
     * @param target Attacked (target) entity
     * @param attacker Attacking (user) entity
     */
    public static void applyCobaltSteelOnHit(LivingEntity target, LivingEntity attacker) {
        //Apply Slowness effect to target (attackedEntity) for configurable duration in seconds.
        //TODO: RE-IMPLEMENT CONFIGS
        //Level 2 slow (third argument) for 30% reduction, 15%/level.
//            target.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS,
//                    ModCommonConfigs.ENDGAME_TIER_EFFECT_SECONDS.get() * 20, 1));
        target.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS,
                4 * 20, 1));
    }
    /**
     * Generates special tooltip for all Cobalt-Steel equipment and appends to Text list,
     *  different for armor and tools/weapons.
     * @param tooltip List of Texts to be appended
     * @param isArmor What type of equipment to generate the tooltip for (different for each)
     */
    public static void appendCobaltSteelEquipmentTooltip(List<Text> tooltip, boolean isArmor) {
        //This method should only ever be called client-side, so no null risk here.
        //If the player is holding shift, show detailed info.
        if (Screen.hasShiftDown()) {
            tooltip.add(Text.translatable("tooltip.cobalt_steel_details_0"));
            tooltip.add(Text.translatable("tooltip.cobalt_steel_details_1"));
            tooltip.add(Text.translatable("tooltip.cobalt_steel_details_2"));
            tooltip.add(Text.translatable("tooltip.cobalt_steel_details_3"));
        } else {
            tooltip.add(Text.translatable("tooltip.hold_shift"));
        }

        //TODO: RE-IMPLEMENT CONFIGS
        if (isArmor) {
            tooltip.add(Text.translatable("tooltip.cobalt_steel_armor"));
        } else {
//            tooltip.add(Text.literal(String.format("§4> On-hit: Wither target for %ss",
//                    ModCommonConfigs.ENDGAME_TIER_EFFECT_SECONDS.get())));
            tooltip.add(Text.translatable("tooltip.cobalt_steel_onhit"));
        }
    }



    /**
     * Applies special effect on attack using Infused Gemstone tools/weapons.
     * @param target Attacked (target) entity
     * @param attacker Attacking (user) entity
     */
    public static void applyInfusedGemstoneOnHit(LivingEntity target, LivingEntity attacker) {
        //Apply Wither effect to target (attackedEntity) for configurable duration in seconds.
        //TODO: RE-IMPLEMENT CONFIGS
        //Level 2 wither for once-per-second damage tick (duration +1 tick so ticks 4 times).
//            target.addStatusEffect(new StatusEffectInstance(StatusEffects.WITHER,
//                    (ModCommonConfigs.ENDGAME_TIER_EFFECT_SECONDS.get() * 20) + 1, 1));
        target.addStatusEffect(new StatusEffectInstance(StatusEffects.WITHER,
                (4 * 20) + 1, 1));
    }
    /**
     * Generates special tooltip for all Infused Gemstone equipment and appends to Text list,
     *  different for armor and tools/weapons.
     * @param tooltip List of Texts to be appended
     * @param isArmor What type of equipment to generate the tooltip for (different for each)
     */
    public static void appendInfusedGemstoneEquipmentTooltip(List<Text> tooltip, boolean isArmor) {
        //This method should only ever be called client-side, so no null risk here.
        //If the player is holding shift, show detailed info.
        if (Screen.hasShiftDown()) {
            tooltip.add(Text.translatable("tooltip.infused_gemstone_details_0"));
            tooltip.add(Text.translatable("tooltip.infused_gemstone_details_1"));
            tooltip.add(Text.translatable("tooltip.infused_gemstone_details_2"));
        } else {
            tooltip.add(Text.translatable("tooltip.hold_shift"));
        }

        //TODO: RE-IMPLEMENT CONFIGS
        if (isArmor) {
            tooltip.add(Text.translatable("tooltip.infused_gemstone_armor"));
        } else {
//            tooltip.add(Text.literal(String.format("§5> Withers target for %ss",
//                    ModCommonConfigs.ENDGAME_TIER_EFFECT_SECONDS.get())));
            tooltip.add(Text.translatable("tooltip.infused_gemstone_onhit"));
        }
    }



    /**
     * Applies special effect on attack using Tungsten-Carbide tools/weapons.
     * @param target Attacked (target) entity
     * @param attacker Attacking (user) entity
     */
    public static void applyTungstenCarbideOnHit(LivingEntity target, LivingEntity attacker) {
        //Apply Weakness effect to target for configurable duration in seconds.
        //TODO: RE-IMPLEMENT CONFIGS
        //Level 1 (third argument) for 4 heart melee damage reduction.
//            target.addStatusEffect(new StatusEffectInstance(StatusEffects.WEAKNESS,
//                    ModCommonConfigs.ENDGAME_TIER_EFFECT_SECONDS.get() * 20, 0));
        target.addStatusEffect(new StatusEffectInstance(StatusEffects.WEAKNESS,
                4 * 20, 0));
    }
    /**
     * Generates special tooltip for all Tungsten-Carbide equipment and appends to Text list,
     *  different for armor and tools/weapons.
     * @param tooltip List of Texts to be appended
     * @param isArmor What type of equipment to generate the tooltip for (different for each)
     */
    public static void appendTungstenCarbideEquipmentTooltip(List<Text> tooltip, boolean isArmor) {
        //This method should only ever be called client-side, so no null risk here.
        //If the player is holding shift, show detailed info.
        if (Screen.hasShiftDown()) {
            tooltip.add(Text.translatable("tooltip.tungsten_carbide_details_0"));
            tooltip.add(Text.translatable("tooltip.tungsten_carbide_details_1"));
            tooltip.add(Text.translatable("tooltip.tungsten_carbide_details_2"));
            tooltip.add(Text.translatable("tooltip.tungsten_carbide_details_3"));
        } else {
            tooltip.add(Text.translatable("tooltip.hold_shift"));
        }

        //TODO: RE-IMPLEMENT CONFIGS
        if (isArmor) {
            tooltip.add(Text.translatable("tooltip.tungsten_carbide_armor"));
        } else {
//            tooltip.add(Text.literal(String.format("§8> On-hit: Slow target for %ss",
//                    ModCommonConfigs.ENDGAME_TIER_EFFECT_SECONDS.get())));
            tooltip.add(Text.translatable("tooltip.tungsten_carbide_onhit"));
        }
    }
}
