package net.dollar.apex.util;

import net.dollar.apex.item.custom.ranged.ModCustomArrowEntity;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.world.World;

import java.util.List;

/**
 * Contains a handful of misc. helper methods used in various parts of the mod.
 */
public class ModItemUtils {
    public enum EquipmentType { ARMOR, TOOL, RANGED }
    public enum EndgameTier { COBALT_STEEL, INFUSED_GEMSTONE, TUNGSTEN_CARBIDE }



    /**
     * Creates a custom arrow entity specific to the Cobalt-Steel, Infused Gemstone, or Tungsten-Carbide
     *  Bow/Crossbow. Each is of a custom ArrowEntity class with special onHit() functionality.
     * @param world Active world the arrow is being fired within
     * @param shooter LivingEntity firing the weapon
     * @param arrowStack ItemStack where the arrow is pulled from (used for Spectral/Tipped behavior)
     * @param tier Enum determining which endgame tier type the arrow corresponds to
     * @return The newly created custom PersistentProjectileEntity.
     */
    public static PersistentProjectileEntity createCustomArrow(World world, LivingEntity shooter,
                                                               ItemStack arrowStack, EndgameTier tier) {
        // Create a custom arrow entity with the proper endgame-tier-based behavior.
        ModCustomArrowEntity arrowEntity = new ModCustomArrowEntity(world, shooter, tier);
        arrowEntity.checkIsSpectral(arrowStack);
        return arrowEntity;
    }



    /**
     * Applies special effect on attack using Cobalt-Steel tools/weapons.
     * @param target Attacked (target) entity
     */
    public static void applyCobaltSteelOnHit(LivingEntity target) {
        //TODO: RE-IMPLEMENT CONFIGS
        //Apply Slowness effect to target for configurable duration in seconds.
//            target.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS,
//                    ModCommonConfigs.ENDGAME_TIER_EFFECT_SECONDS.get() * 20, 1));

        // Do not apply effect to creative mode players.
        if (target instanceof PlayerEntity player && player.isCreative()) return;

        //Level 2 Slowness (third argument) for 30% reduction, 15%/level.
        target.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS,
                4 * 20, 1));
    }
    
    /**
     * Generates special tooltip for all Cobalt-Steel equipment and appends to Text list,
     *  different for armor and tools/weapons.
     * @param tooltip List of Texts to be appended
     * @param equipmentType What type of equipment to generate the tooltip for (different for each)
     */
    public static void appendCobaltSteelEquipmentTooltip(List<Text> tooltip, EquipmentType equipmentType) {
        //This method should only ever be called client-side, so no null risk here.
        
        //If the player is holding shift, show detailed info.
        if (Screen.hasShiftDown()) {
            switch (equipmentType) {
                case ARMOR -> {
                    tooltip.add(Text.translatable("tooltip.cobalt_steel_armor_details_0"));
                    tooltip.add(Text.translatable("tooltip.cobalt_steel_armor_details_1"));
                    tooltip.add(Text.translatable("tooltip.cobalt_steel_armor_details_2"));
                    tooltip.add(Text.translatable("tooltip.cobalt_steel_armor_full_set"));
                }
                case TOOL -> {
                    tooltip.add(Text.translatable("tooltip.cobalt_steel_tool_details_0"));
                    tooltip.add(Text.translatable("tooltip.cobalt_steel_tool_details_1"));
                    tooltip.add(Text.translatable("tooltip.cobalt_steel_tool_details_2"));
                    tooltip.add(Text.translatable("tooltip.cobalt_steel_tool_details_3"));
                    tooltip.add(Text.translatable("tooltip.cobalt_steel_tool_details_4"));
                    tooltip.add(Text.translatable("tooltip.cobalt_steel_on_hit_effect"));
                }
                case RANGED -> {
                    tooltip.add(Text.translatable("tooltip.cobalt_steel_ranged_details_0"));
                    tooltip.add(Text.translatable("tooltip.cobalt_steel_ranged_details_1"));
                    tooltip.add(Text.translatable("tooltip.cobalt_steel_ranged_details_2"));
                    tooltip.add(Text.translatable("tooltip.cobalt_steel_ranged_bonus_damage"));
                    tooltip.add(Text.translatable("tooltip.cobalt_steel_on_hit_effect"));
                }
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
        //TODO: RE-IMPLEMENT CONFIGS
        //Apply Wither effect to target for configurable duration in seconds.
//            target.addStatusEffect(new StatusEffectInstance(StatusEffects.WITHER,
//                    (ModCommonConfigs.ENDGAME_TIER_EFFECT_SECONDS.get() * 20) + 1, 1));

        // Do not apply effect to creative mode players.
        if (target instanceof PlayerEntity player && player.isCreative()) return;

        //Level 2 Wither for once-per-second damage tick (duration +1 tick so ticks 4 times).
        target.addStatusEffect(new StatusEffectInstance(StatusEffects.WITHER,
                (4 * 20) + 1, 1));
    }
    
    /**
     * Generates special tooltip for all Infused Gemstone equipment and appends to Text list,
     *  different for armor and tools/weapons.
     * @param tooltip List of Texts to be appended
     * @param equipmentType What type of equipment to generate the tooltip for (different for each)
     */
    public static void appendInfusedGemstoneEquipmentTooltip(List<Text> tooltip, EquipmentType equipmentType) {
        //This method should only ever be called client-side, so no null risk here.
        
        //If the player is holding shift, show detailed info.
        if (Screen.hasShiftDown()) {
            switch (equipmentType) {
                case ARMOR -> {
                    tooltip.add(Text.translatable("tooltip.infused_gemstone_armor_details_0"));
                    tooltip.add(Text.translatable("tooltip.infused_gemstone_armor_details_1"));
                    tooltip.add(Text.translatable("tooltip.infused_gemstone_armor_details_2"));
                    tooltip.add(Text.translatable("tooltip.infused_gemstone_armor_full_set"));
                }
                case TOOL -> {
                    tooltip.add(Text.translatable("tooltip.infused_gemstone_tool_details_0"));
                    tooltip.add(Text.translatable("tooltip.infused_gemstone_tool_details_1"));
                    tooltip.add(Text.translatable("tooltip.infused_gemstone_tool_details_2"));
                    tooltip.add(Text.translatable("tooltip.infused_gemstone_tool_details_3"));
                    tooltip.add(Text.translatable("tooltip.infused_gemstone_tool_details_4"));
                    tooltip.add(Text.translatable("tooltip.infused_gemstone_on_hit_effect"));
                }
                case RANGED -> {
                    tooltip.add(Text.translatable("tooltip.infused_gemstone_ranged_details_0"));
                    tooltip.add(Text.translatable("tooltip.infused_gemstone_ranged_details_1"));
                    tooltip.add(Text.translatable("tooltip.infused_gemstone_ranged_details_2"));
                    tooltip.add(Text.translatable("tooltip.infused_gemstone_ranged_details_3"));
                    tooltip.add(Text.translatable("tooltip.infused_gemstone_ranged_bonus_damage"));
                    tooltip.add(Text.translatable("tooltip.infused_gemstone_on_hit_effect"));
                }
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
        //TODO: RE-IMPLEMENT CONFIGS
        //Apply Weakness effect to target for configurable duration in seconds.
//            target.addStatusEffect(new StatusEffectInstance(StatusEffects.WEAKNESS,
//                    ModCommonConfigs.ENDGAME_TIER_EFFECT_SECONDS.get() * 20, 0));

        // Do not apply effect to creative mode players.
        if (target instanceof PlayerEntity player && player.isCreative()) return;

        //Level 1 Weakness (third argument) for 4 heart melee damage reduction.
        target.addStatusEffect(new StatusEffectInstance(StatusEffects.WEAKNESS,
                4 * 20, 0));
    }
    
    /**
     * Generates special tooltip for all Tungsten-Carbide equipment and appends to Text list,
     *  different for armor and tools/weapons.
     * @param tooltip List of Texts to be appended
     * @param equipmentType What type of equipment to generate the tooltip for (different for each)
     */
    public static void appendTungstenCarbideEquipmentTooltip(List<Text> tooltip, EquipmentType equipmentType) {
        //This method should only ever be called client-side, so no null risk here.
        
        //If the player is holding shift, show detailed info.
        if (Screen.hasShiftDown()) {
            switch (equipmentType) {
                case ARMOR -> {
                    tooltip.add(Text.translatable("tooltip.tungsten_carbide_armor_details_0"));
                    tooltip.add(Text.translatable("tooltip.tungsten_carbide_armor_details_1"));
                    tooltip.add(Text.translatable("tooltip.tungsten_carbide_armor_details_2"));
                    tooltip.add(Text.translatable("tooltip.tungsten_carbide_armor_full_set"));
                }
                case TOOL -> {
                    tooltip.add(Text.translatable("tooltip.tungsten_carbide_tool_details_0"));
                    tooltip.add(Text.translatable("tooltip.tungsten_carbide_tool_details_1"));
                    tooltip.add(Text.translatable("tooltip.tungsten_carbide_tool_details_2"));
                    tooltip.add(Text.translatable("tooltip.tungsten_carbide_tool_details_3"));
                    tooltip.add(Text.translatable("tooltip.tungsten_carbide_tool_details_4"));
                    tooltip.add(Text.translatable("tooltip.tungsten_carbide_on_hit_effect"));
                }
                case RANGED -> {
                    tooltip.add(Text.translatable("tooltip.tungsten_carbide_ranged_details_0"));
                    tooltip.add(Text.translatable("tooltip.tungsten_carbide_ranged_details_1"));
                    tooltip.add(Text.translatable("tooltip.tungsten_carbide_ranged_details_2"));
                    tooltip.add(Text.translatable("tooltip.tungsten_carbide_ranged_details_3"));
                    tooltip.add(Text.translatable("tooltip.tungsten_carbide_ranged_bonus_damage"));
                    tooltip.add(Text.translatable("tooltip.tungsten_carbide_on_hit_effect"));
                }
            }
        } else {
            tooltip.add(Text.translatable("tooltip.tungsten_carbide_hold_shift"));
        }
    }
}
