package net.dollar.apex.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.CompletableFuture;

/**
 * Handles world generation setup and registries relating to ConfiguredFeatures and PlacedFeatures.
 */
public class ModWorldGenerator extends FabricDynamicRegistryProvider {
    public ModWorldGenerator(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }



    /**
     * Configures new registries using the mod's Configured and Placed Features.
     * @param registries Existing RegistryKeys to be added to entries
     * @param entries World generation entries to be added to
     */
    @Override
    protected void configure(HolderLookup.Provider registries, Entries entries) {
        entries.addAll(registries.lookupOrThrow(Registries.CONFIGURED_FEATURE));
        entries.addAll(registries.lookupOrThrow(Registries.PLACED_FEATURE));
    }

    @Override
    public @NotNull String getName() {
        return "World Gen";
    }
}
