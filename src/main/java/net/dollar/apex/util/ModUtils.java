package net.dollar.apex.util;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.text.Text;

import java.util.List;

/**
 * Contains a handful of misc. helper methods used in various parts of the mod.
 */
public class ModUtils {
    public enum EquipmentType { ARMOR, TOOL, RANGED }

    /**
     * Applies special effect on attack using Cobalt-Steel tools/weapons.
     * @param target Attacked (target) entity
     */
    public static void applyCobaltSteelOnHit(LivingEntity target) {
        //Apply Weakness effect to target for configurable duration in seconds.
        //TODO: RE-IMPLEMENT CONFIGS
        //Level 1 (third argument) for 4 heart melee damage reduction.
//            target.addStatusEffect(new StatusEffectInstance(StatusEffects.WEAKNESS,
//                    ModCommonConfigs.ENDGAME_TIER_EFFECT_SECONDS.get() * 20, 0));
        target.addStatusEffect(new StatusEffectInstance(StatusEffects.WEAKNESS,
                4 * 20, 0));
    }
    /**
     * Generates special tooltip for all Cobalt-Steel equipment and appends to Text list,
     *  different for armor and tools/weapons.
     * @param tooltip List of Texts to be appended
     * @param type What type of equipment to generate the tooltip for (different for each)
     */
    public static void appendCobaltSteelEquipmentTooltip(List<Text> tooltip, EquipmentType type) {
        //This method should only ever be called client-side, so no null risk here.
        //If the player is holding shift, show detailed info.
        if (Screen.hasShiftDown()) {
            tooltip.add(Text.translatable("tooltip.cobalt_steel_details_0"));
            tooltip.add(Text.translatable("tooltip.cobalt_steel_details_1"));
            tooltip.add(Text.translatable("tooltip.cobalt_steel_details_2"));
            tooltip.add(Text.translatable("tooltip.cobalt_steel_details_3"));
            tooltip.add(Text.translatable("tooltip.cobalt_steel_details_4"));

            //TODO: RE-IMPLEMENT CONFIGS
            if (type == EquipmentType.ARMOR) {
                tooltip.add(Text.translatable("tooltip.cobalt_steel_armor"));
            } else if (type == EquipmentType.TOOL) {
//            tooltip.add(Text.literal(String.format("§8> On-hit: Slow target for %ss",
//                    ModCommonConfigs.ENDGAME_TIER_EFFECT_SECONDS.get())));
                tooltip.add(Text.translatable("tooltip.cobalt_steel_onhit"));
            } else {
                tooltip.add(Text.translatable("tooltip.cobalt_steel_bow_crossbow"));
                tooltip.add(Text.translatable("tooltip.cobalt_steel_onhit"));
            }
        } else {
            tooltip.add(Text.translatable("tooltip.cobalt_steel_hold_shift"));
        }
    }



    /**
     * Applies special effect on attack using Infused Gemstone tools/weapons.
     * @param target Attacked (target) entity
     */
    public static void applyInfusedGemstoneOnHit(LivingEntity target) {
        //Apply Wither effect to target for configurable duration in seconds.
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
     * @param type What type of equipment to generate the tooltip for (different for each)
     */
    public static void appendInfusedGemstoneEquipmentTooltip(List<Text> tooltip, EquipmentType type) {
        //This method should only ever be called client-side, so no null risk here.
        //If the player is holding shift, show detailed info.
        if (Screen.hasShiftDown()) {
            tooltip.add(Text.translatable("tooltip.infused_gemstone_details_0"));
            tooltip.add(Text.translatable("tooltip.infused_gemstone_details_1"));
            tooltip.add(Text.translatable("tooltip.infused_gemstone_details_2"));
            tooltip.add(Text.translatable("tooltip.infused_gemstone_details_3"));
            tooltip.add(Text.translatable("tooltip.infused_gemstone_details_4"));

            //TODO: RE-IMPLEMENT CONFIGS
            if (type == EquipmentType.ARMOR) {
                tooltip.add(Text.translatable("tooltip.infused_gemstone_armor"));
            } else if (type == EquipmentType.TOOL) {
//            tooltip.add(Text.literal(String.format("§8> On-hit: Slow target for %ss",
//                    ModCommonConfigs.ENDGAME_TIER_EFFECT_SECONDS.get())));
                tooltip.add(Text.translatable("tooltip.infused_gemstone_onhit"));
            } else {
                tooltip.add(Text.translatable("tooltip.infused_gemstone_bow_crossbow"));
                tooltip.add(Text.translatable("tooltip.infused_gemstone_onhit"));
            }
        } else {
            tooltip.add(Text.translatable("tooltip.infused_gemstone_hold_shift"));
        }
    }



    /**
     * Applies special effect on attack using Tungsten-Carbide tools/weapons.
     * @param target Attacked (target) entity
     */
    public static void applyTungstenCarbideOnHit(LivingEntity target) {
        //Apply Slowness effect to target for configurable duration in seconds.
        //TODO: RE-IMPLEMENT CONFIGS
        //Level 2 slow (third argument) for 30% reduction, 15%/level.
//            target.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS,
//                    ModCommonConfigs.ENDGAME_TIER_EFFECT_SECONDS.get() * 20, 1));
        target.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS,
                4 * 20, 1));
    }
    /**
     * Generates special tooltip for all Tungsten-Carbide equipment and appends to Text list,
     *  different for armor and tools/weapons.
     * @param tooltip List of Texts to be appended
     * @param type What type of equipment to generate the tooltip for (different for each)
     */
    public static void appendTungstenCarbideEquipmentTooltip(List<Text> tooltip, EquipmentType type) {
        //This method should only ever be called client-side, so no null risk here.
        //If the player is holding shift, show detailed info.
        if (Screen.hasShiftDown()) {
            tooltip.add(Text.translatable("tooltip.tungsten_carbide_details_0"));
            tooltip.add(Text.translatable("tooltip.tungsten_carbide_details_1"));
            tooltip.add(Text.translatable("tooltip.tungsten_carbide_details_2"));
            tooltip.add(Text.translatable("tooltip.tungsten_carbide_details_3"));
            tooltip.add(Text.translatable("tooltip.tungsten_carbide_details_4"));

            //TODO: RE-IMPLEMENT CONFIGS
            if (type == EquipmentType.ARMOR) {
                tooltip.add(Text.translatable("tooltip.tungsten_carbide_armor"));
            } else if (type == EquipmentType.TOOL) {
//            tooltip.add(Text.literal(String.format("§8> On-hit: Slow target for %ss",
//                    ModCommonConfigs.ENDGAME_TIER_EFFECT_SECONDS.get())));
                tooltip.add(Text.translatable("tooltip.tungsten_carbide_onhit"));
            } else {
                tooltip.add(Text.translatable("tooltip.tungsten_carbide_bow_crossbow"));
                tooltip.add(Text.translatable("tooltip.tungsten_carbide_onhit"));
            }
        } else {
            tooltip.add(Text.translatable("tooltip.tungsten_carbide_hold_shift"));
        }
    }
}
