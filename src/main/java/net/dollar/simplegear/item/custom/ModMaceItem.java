package net.dollar.simplegear.item.custom;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.item.Vanishable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

@Deprecated
public class ModMaceItem extends ToolItem implements Vanishable {
    private final float attackDamage;
    private final Multimap<EntityAttribute, EntityAttributeModifier> attributeModifiers;



    //TODO: FIGURE OUT HOW TO DETERMINE WHAT ENCHANTMENTS CAN BE APPLIED



    /**
     * Constructs a new ModMaceItem object.
     * @param material Equipment material (tier)
     * @param attackDamage Base attack damage (before material modification)
     * @param attackSpeed Attack speed (NOT mining speed)
     * @param settings Item settings (properties)
     */
    public ModMaceItem(ToolMaterial material, float attackDamage, float attackSpeed, Settings settings) {
        super(material, settings);
        this.attackDamage = attackDamage + material.getAttackDamage();
        ImmutableMultimap.Builder<EntityAttribute, EntityAttributeModifier> builder = ImmutableMultimap.builder();
        builder.put(EntityAttributes.GENERIC_ATTACK_DAMAGE, new EntityAttributeModifier(ATTACK_DAMAGE_MODIFIER_ID, "Weapon modifier", (double)this.attackDamage, EntityAttributeModifier.Operation.ADDITION));
        builder.put(EntityAttributes.GENERIC_ATTACK_SPEED, new EntityAttributeModifier(ATTACK_SPEED_MODIFIER_ID, "Weapon modifier", (double)attackSpeed, EntityAttributeModifier.Operation.ADDITION));
        this.attributeModifiers = builder.build();
    }

    /**
     * Getter for attackDamage field.
     * @return Value of attackDamage field
     */
    public float getAttackDamage() {
        return this.attackDamage;
    }

    /**
     * Checks whether a player can attack a specific block with this item.
     * @param state Target BlockState
     * @param world Active world
     * @param pos Position of targeted Block
     * @param miner PlayerEntity doing the mining
     * @return Whether the player can attack the Block
     */
    @Override
    public boolean canMine(BlockState state, World world, BlockPos pos, PlayerEntity miner) {
        return !miner.isCreative();
    }

    /**
     * Calculate mining speed of a specific Block using this Item (always 1.0f).
     * @param stack ItemStack corresponding to this Item
     * @param state BlockState of target block
     * @return Calculated mining speed
     */
    @Override
    public float getMiningSpeedMultiplier(ItemStack stack, BlockState state) {
        return 1.0f;
    }

    /**
     * Performs Item-specific post-attack operations (ex. deal durability damage).
     * @param stack ItemStack corresponding to this Item
     * @param target Target LivingEntity
     * @param attacker Attacker LivingEntity
     * @return Whether the attack was successful
     */
    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        stack.damage(1, attacker, e -> e.sendEquipmentBreakStatus(EquipmentSlot.MAINHAND));
        return true;
    }

    /**
     * Performs Item-specific mining operations (ex. deal durability damage).
     * @param stack ItemStack corresponding to this Item
     * @param world Active world
     * @param state BlockState of block being mined
     * @param pos Position of block being mined
     * @param miner User LivingEntity
     * @return Whether the mining was successful
     */
    @Override
    public boolean postMine(ItemStack stack, World world, BlockState state, BlockPos pos, LivingEntity miner) {
        if (state.getHardness(world, pos) != 0.0f) {
            //Change durability damage to 1 instead of 2 when mining a block.
            stack.damage(1, miner, e -> e.sendEquipmentBreakStatus(EquipmentSlot.MAINHAND));
        }
        return true;
    }

    /**
     * Checks whether this item is the correct tool for a given Block.
     * @param state BlockState of the Block to check
     * @return Whether this is the correct tool for drops
     */
    @Override
    public boolean isSuitableFor(BlockState state) {
        return state.isOf(Blocks.COBWEB);
    }

    /**
     * Gets Map of default attribute modifiers for the corresponding EquipmentSlot.
     * @param slot EquipmentSlot corresponding to this Item
     * @return Multimap of Attributes, AttributeModifiers
     */
    @Override
    public Multimap<EntityAttribute, EntityAttributeModifier> getAttributeModifiers(EquipmentSlot slot) {
        if (slot == EquipmentSlot.MAINHAND) {
            return this.attributeModifiers;
        }
        return super.getAttributeModifiers(slot);
    }
}
