package net.dollar.simplegear.util;

import net.dollar.simplegear.item.ModItems;
import net.minecraft.client.item.ModelPredicateProviderRegistry;
import net.minecraft.item.*;
import net.minecraft.util.Identifier;

public class ModModelPredicateProvider {
    public static void registerModModels() {
        //TODO: IMPLEMENT ALL BOWS AND CROSSBOWS
        registerBow(ModItems.STEEL_BOW);
//        registerBow(ModItems.INFUSED_GEMSTONE_BOW);
//        registerBow(ModItems.NETHERITE_BOW);
//        registerBow(ModItems.TUNGSTEN_CARBIDE_BOW);
        registerCrossbow(ModItems.STEEL_CROSSBOW);
//        registerCrossbow(ModItems.INFUSED_GEMSTONE_CROSSBOW);
//        registerCrossbow(ModItems.NETHERITE_CROSSBOW);
//        registerCrossbow(ModItems.TUNGSTEN_CARBIDE_CROSSBOW);
    }



    /**
     * Handles creating model predicate registries for the passed-in bow Item.
     * @param bow Bow Item to create model predicates for
     */
    private static void registerBow(Item bow) {
        ModelPredicateProviderRegistry.register(bow, new Identifier("pull"),
                (stack, world, entity, seed) -> {
                    if (entity == null) {
                        return 0.0f;
                    }
                    if (entity.getActiveItem() != stack) {
                        return 0.0f;
                    }
                    return (float)(stack.getMaxUseTime() - entity.getItemUseTimeLeft()) / 20.0f;
                });

        ModelPredicateProviderRegistry.register(bow, new Identifier("pulling"),
                (stack, world, entity, seed) ->
                        entity != null && entity.isUsingItem() && entity.getActiveItem() == stack ? 1.0f : 0.0f);
    }

    /**
     * Handles creating model predicate registries for the passed-in bow Item.
     * @param crossbow Bow Item to create model predicates for
     */
    private static void registerCrossbow(Item crossbow) {
        ModelPredicateProviderRegistry.register(crossbow, new Identifier("pull"),
                (stack, world, entity, seed) -> {
                    if (entity == null) {
                        return 0.0f;
                    }
                    if (CrossbowItem.isCharged((ItemStack)stack)) {
                        return 0.0f;
                    }
                    return (float)(stack.getMaxUseTime() - entity.getItemUseTimeLeft()) / (float)CrossbowItem.getPullTime((ItemStack)stack);
                });

        ModelPredicateProviderRegistry.register(crossbow, new Identifier("pulling"),
                (stack, world, entity, seed) ->
                        entity != null && entity.isUsingItem() && entity.getActiveItem() == stack
                                && !CrossbowItem.isCharged((ItemStack)stack) ? 1.0f : 0.0f);

        ModelPredicateProviderRegistry.register(crossbow, new Identifier("charged"),
                (stack, world, entity, seed) ->
                        CrossbowItem.isCharged((ItemStack)stack) ? 1.0f : 0.0f);

        ModelPredicateProviderRegistry.register(crossbow, new Identifier("firework"),
                (stack, world, entity, seed) ->
                        CrossbowItem.isCharged((ItemStack)stack) &&
                                CrossbowItem.hasProjectile((ItemStack)stack, (Item)Items.FIREWORK_ROCKET) ? 1.0f : 0.0f);
    }
}
