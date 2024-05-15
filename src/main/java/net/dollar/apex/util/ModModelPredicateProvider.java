package net.dollar.apex.util;

import net.dollar.apex.item.ModItems;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.item.ModelPredicateProviderRegistry;
import net.minecraft.item.*;
import net.minecraft.util.Identifier;

/**
 * Handles setting up complex model predicates that are used for Bows and Crossbows (which render different
 *  textures depending on duration of use).
 */
@Environment(EnvType.CLIENT)
public class ModModelPredicateProvider {
    public static void registerModModels() {
        registerBow(ModItems.INFUSED_GEMSTONE_BOW);
        registerBow(ModItems.COBALT_STEEL_BOW);
        registerBow(ModItems.TUNGSTEN_CARBIDE_BOW);
        registerCrossbow(ModItems.INFUSED_GEMSTONE_CROSSBOW);
        registerCrossbow(ModItems.COBALT_STEEL_CROSSBOW);
        registerCrossbow(ModItems.TUNGSTEN_CARBIDE_CROSSBOW);
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
                    if (CrossbowItem.isCharged(stack)) {
                        return 0.0f;
                    }
                    return (float)(stack.getMaxUseTime() - entity.getItemUseTimeLeft()) / (float)CrossbowItem.getPullTime(stack);
                });

        ModelPredicateProviderRegistry.register(crossbow, new Identifier("pulling"),
                (stack, world, entity, seed) ->
                        entity != null && entity.isUsingItem() && entity.getActiveItem() == stack
                                && !CrossbowItem.isCharged(stack) ? 1.0f : 0.0f);

        ModelPredicateProviderRegistry.register(crossbow, new Identifier("charged"),
                (stack, world, entity, seed) ->
                        CrossbowItem.isCharged(stack) ? 1.0f : 0.0f);

        ModelPredicateProviderRegistry.register(crossbow, new Identifier("firework"),
                (stack, world, entity, seed) ->
                        CrossbowItem.isCharged(stack) &&
                                CrossbowItem.hasProjectile(stack, Items.FIREWORK_ROCKET) ? 1.0f : 0.0f);
    }
}
