package org.coffeelake.naturecode.worldgen;

import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import org.coffeelake.naturecode.NatureCode;
import org.coffeelake.naturecode.worldgen.tree.BirchCurve;
import org.coffeelake.naturecode.worldgen.tree.BirchOldGrowth;

public class ModConfiguredFeatures {
    public static final ResourceKey<ConfiguredFeature<?, ?>> CURVE_BIRCH_SMALL = registerKey("curve_birch_small");
    public static final ResourceKey<ConfiguredFeature<?, ?>> CURVE_BIRCH = registerKey("curve_birch");
    public static final ResourceKey<ConfiguredFeature<?, ?>> CURVE_BIRCH_RARE = registerKey("curve_birch_rare");
    public static final ResourceKey<ConfiguredFeature<?, ?>> CURVE_DEAD_BIRCH = registerKey("curve_dead_birch"); // dead tree
    public static final ResourceKey<ConfiguredFeature<?, ?>> OLD_GROWTH_BIRCH = registerKey("old_growth_birch"); // relic
    public static final ResourceKey<ConfiguredFeature<?, ?>> OLD_GROWTH_DEAD_BIRCH = registerKey("old_growth_dead_birch");
    /**
     * Makes record of object in Minecraft registries
     * @param name name of object
     * @return prepared ResourceKey
     */
    public static ResourceKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, ResourceLocation.fromNamespaceAndPath(NatureCode.MODID, name));
    }

    public static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> ctx) {
        // how to build my tree
        // Feature<TreeConfiguration> oldGrowthBirch = new OldGrowthBirchFeature(TreeConfiguration.CODEC);

        // Берёза обыкновенная (кривая).
        register(ctx,
                CURVE_BIRCH,
                Feature.TREE,
                BirchCurve.Build()); // 6, 3, 4
        register(ctx,
                CURVE_BIRCH_RARE,
                Feature.TREE,
                BirchCurve.Build()); // 5, 3, 4
        register(ctx,
                CURVE_DEAD_BIRCH,
                Feature.TREE,
                BirchCurve.BuildDead()); // 10, 3, 5
        register(ctx,
                OLD_GROWTH_BIRCH,
                Feature.TREE,
                BirchOldGrowth.Build());
        register(ctx,
                OLD_GROWTH_DEAD_BIRCH,
                Feature.TREE,
                BirchOldGrowth.BuildDead());

    }

    private static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(BootstrapContext<ConfiguredFeature<?, ?>> ctx, ResourceKey<ConfiguredFeature<?, ?>> key, F feature, FC configuration) {
        ctx.register(key, new ConfiguredFeature<>(feature, configuration));
    }
}
