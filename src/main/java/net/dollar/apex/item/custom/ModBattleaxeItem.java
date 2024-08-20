package net.dollar.apex.item.custom;

import net.minecraft.block.BlockState;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.AttributeModifierSlot;
import net.minecraft.component.type.AttributeModifiersComponent;
import net.minecraft.component.type.ToolComponent;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.List;

/**
 * Defines behavior for all Battleaxe items, which cannot mine anything and are meant as weapons (derives
 *  from SwordItem).
 */
public class ModBattleaxeItem extends ToolItem {

    public ModBattleaxeItem(ToolMaterial toolMaterial, Item.Settings settings) {
        super(toolMaterial, settings.component(DataComponentTypes.TOOL, createToolComponent()));
    }

    /**
     * Creates a tool component for this item, which determines mineable blocks, mining speed, and damage per mine.
     * @return The generated ToolComponent
     */
    private static ToolComponent createToolComponent() {
        return new ToolComponent(List.of(ToolComponent.Rule.ofAlwaysDropping(List.of(), 1.0F),
                    ToolComponent.Rule.of(List.of(), 1.0F)),
                1.0F,
                2);
    }

    /**
     * Creates attribute modifiers for this item, which determines the tool's functionality.
     * @param material ToolMaterial for this Item
     * @param baseAttackDamage This Item's base Attack Damage
     * @param attackSpeed This Item's Attack Speed
     * @return The generated AttributeModifiersComponent
     */
    public static AttributeModifiersComponent createAttributeModifiers(ToolMaterial material, int baseAttackDamage, float attackSpeed) {
        return AttributeModifiersComponent.builder().add(EntityAttributes.GENERIC_ATTACK_DAMAGE,
                new EntityAttributeModifier(BASE_ATTACK_DAMAGE_MODIFIER_ID,
                        (float)baseAttackDamage + material.getAttackDamage(),
                        EntityAttributeModifier.Operation.ADD_VALUE), AttributeModifierSlot.MAINHAND)
                .add(EntityAttributes.GENERIC_ATTACK_SPEED, new EntityAttributeModifier(BASE_ATTACK_SPEED_MODIFIER_ID,
                        attackSpeed, EntityAttributeModifier.Operation.ADD_VALUE),
                        AttributeModifierSlot.MAINHAND)
                .build();
    }

    @Override
    public boolean canMine(BlockState state, World world, BlockPos pos, PlayerEntity miner) {
        return !miner.isCreative();
    }

    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        stack.damage(1, attacker, EquipmentSlot.MAINHAND);
        return true;
    }
}
