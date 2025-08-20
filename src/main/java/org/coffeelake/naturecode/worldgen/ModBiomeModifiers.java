package org.coffeelake.naturecode.worldgen;

import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.Resource;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.neoforged.neoforge.common.world.BiomeModifier;
import net.neoforged.neoforge.common.world.BiomeModifiers;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import org.coffeelake.naturecode.NatureCode;

public class ModBiomeModifiers {
    public static final ResourceKey<BiomeModifier> ADD_CURVE_BIRCH_SMALL = registerKey("add_curve_birch_small");
    public static final ResourceKey<BiomeModifier> ADD_CURVE_BIRCH = registerKey("add_curve_birch");
    public static final ResourceKey<BiomeModifier> ADD_CURVE_BIRCH_RARE = registerKey("add_curve_birch_rare");
    public static final ResourceKey<BiomeModifier> ADD_CURVE_DEAD_BIRCH = registerKey("add_curve_dead_birch");
    public static final ResourceKey<BiomeModifier> ADD_OLD_GROWTH_BIRCH = registerKey("add_old_growth_birch");
    public static final ResourceKey<BiomeModifier> ADD_OLD_GROWTH_DEAD_BIRCH = registerKey("add_old_growth_dead_birch");

    public static void bootstrap(BootstrapContext<BiomeModifier> ctx) {
        var placedFeatures = ctx.lookup(Registries.PLACED_FEATURE);
        var biomes = ctx.lookup(Registries.BIOME);

        // curve birch spawn-rate for birch forest
        ctx.register(ADD_CURVE_BIRCH, new BiomeModifiers.AddFeaturesBiomeModifier(
                HolderSet.direct(biomes.getOrThrow(Biomes.BIRCH_FOREST), biomes.getOrThrow(Biomes.OLD_GROWTH_BIRCH_FOREST)),
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.CURVE_BIRCH)),
                GenerationStep.Decoration.VEGETAL_DECORATION));

        // curve birch spawn-rate for non-birch forest
        ctx.register(ADD_CURVE_BIRCH_RARE, new BiomeModifiers.AddFeaturesBiomeModifier(
                HolderSet.direct(biomes.getOrThrow(Biomes.FOREST), biomes.getOrThrow(Biomes.FLOWER_FOREST), biomes.getOrThrow(Biomes.DARK_FOREST)),
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.CURVE_BIRCH)),
                GenerationStep.Decoration.VEGETAL_DECORATION
        ));
        ctx.register(ADD_CURVE_DEAD_BIRCH, new BiomeModifiers.AddFeaturesBiomeModifier(
                HolderSet.direct(biomes.getOrThrow(Biomes.FOREST), biomes.getOrThrow(Biomes.FLOWER_FOREST), biomes.getOrThrow(Biomes.DARK_FOREST), biomes.getOrThrow(Biomes.BIRCH_FOREST), biomes.getOrThrow(Biomes.SWAMP)),
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.CURVE_DEAD_BIRCH)),
                GenerationStep.Decoration.VEGETAL_DECORATION
        ));
        ctx.register(ADD_OLD_GROWTH_BIRCH, new BiomeModifiers.AddFeaturesBiomeModifier(
                HolderSet.direct(biomes.getOrThrow(Biomes.OLD_GROWTH_BIRCH_FOREST), biomes.getOrThrow(Biomes.OLD_GROWTH_PINE_TAIGA)),
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.OLD_GROWTH_BIRCH)),
                GenerationStep.Decoration.VEGETAL_DECORATION
        ));
        ctx.register(ADD_OLD_GROWTH_DEAD_BIRCH, new BiomeModifiers.AddFeaturesBiomeModifier(
                HolderSet.direct(biomes.getOrThrow(Biomes.OLD_GROWTH_BIRCH_FOREST), biomes.getOrThrow(Biomes.OLD_GROWTH_PINE_TAIGA), biomes.getOrThrow(Biomes.SWAMP)),
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.OLD_GROWTH_DEAD_BIRCH)),
                GenerationStep.Decoration.VEGETAL_DECORATION
        ));

    }

    private static ResourceKey<BiomeModifier> registerKey(String name) {
        return ResourceKey.create(NeoForgeRegistries.Keys.BIOME_MODIFIERS, ResourceLocation.fromNamespaceAndPath(NatureCode.MODID, name));
    }
}
