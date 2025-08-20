package org.coffeelake.naturecode.worldgen;

import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;
import org.coffeelake.naturecode.NatureCode;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ModPlacedFeatures {
    public static final ResourceKey<PlacedFeature> CURVE_BIRCH = registerKey("curve_birch_placed");
    public static final ResourceKey<PlacedFeature> CURVE_BIRCH_RARE = registerKey("curve_birch_rare");
    public static final ResourceKey<PlacedFeature> CURVE_DEAD_BIRCH = registerKey("curve_dead_birch"); // dead tree
    public static final ResourceKey<PlacedFeature> OLD_GROWTH_BIRCH = registerKey("old_growth_birch"); // relic
    public static final ResourceKey<PlacedFeature> OLD_GROWTH_DEAD_BIRCH = registerKey("old_growth_dead_birch");

    private static ResourceKey<PlacedFeature> registerKey(@NotNull String name) {
        return ResourceKey.create(Registries.PLACED_FEATURE, ResourceLocation.fromNamespaceAndPath(NatureCode.MODID, name));
    }

    private static void register(BootstrapContext<PlacedFeature> ctx, ResourceKey<PlacedFeature> key, Holder<ConfiguredFeature<?, ?>> config, List<PlacementModifier> modifiers) {
        ctx.register(key, new PlacedFeature(config, List.copyOf(modifiers)));
    }

    /**
     * Calls by the NeoForge @EventBus and places ready structures
     * in the world.
     * @param ctx BootStrap context and NeoForge registries bound with ready-to-Place structures
     */
    public static void bootstrap(BootstrapContext<PlacedFeature> ctx) {
        var configuredFeatures = ctx.lookup(Registries.CONFIGURED_FEATURE);

        // warning! those strings is a limit of trees growing
        register(ctx, CURVE_BIRCH, configuredFeatures.getOrThrow(ModConfiguredFeatures.CURVE_BIRCH),
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(3, 0.1f, 2),
                        Blocks.BIRCH_SAPLING));
        register(ctx, CURVE_BIRCH_RARE, configuredFeatures.getOrThrow(ModConfiguredFeatures.CURVE_BIRCH),
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(2, 0.25f, 2),
                        Blocks.BIRCH_SAPLING));
        register(ctx, CURVE_DEAD_BIRCH, configuredFeatures.getOrThrow(ModConfiguredFeatures.CURVE_DEAD_BIRCH),
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(2, 0.01f, 2),
                        Blocks.BIRCH_SAPLING));
        register(ctx, OLD_GROWTH_BIRCH, configuredFeatures.getOrThrow(ModConfiguredFeatures.OLD_GROWTH_BIRCH),
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(2, 0.04f, 2),
                        Blocks.BIRCH_SAPLING));
        register(ctx, OLD_GROWTH_DEAD_BIRCH, configuredFeatures.getOrThrow(ModConfiguredFeatures.OLD_GROWTH_DEAD_BIRCH),
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(2, 0.02f, 2),
                        Blocks.BIRCH_SAPLING));

    }
}
