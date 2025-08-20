package org.coffeelake.naturecode.worldgen;

import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.neoforged.neoforge.common.world.BiomeModifier;
import net.neoforged.neoforge.common.world.BiomeModifiers;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import org.coffeelake.naturecode.NatureCode;

public class ModBiomeModifiers {
    public static final ResourceKey<BiomeModifier> ADD_CURVE_BIRCH = registerKey("add_curve_birch");
    public static final ResourceKey<BiomeModifier> ADD_CURVE_BIRCH_RARE = registerKey("add_curve_birch_rare");
    public static final ResourceKey<BiomeModifier> ADD_OLD_BIRCH = registerKey("add_old_birch");

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
        ctx.register(ADD_OLD_BIRCH, new BiomeModifiers.AddFeaturesBiomeModifier(
                HolderSet.direct(biomes.getOrThrow(Biomes.FOREST), biomes.getOrThrow(Biomes.FLOWER_FOREST), biomes.getOrThrow(Biomes.DARK_FOREST), biomes.getOrThrow(Biomes.BIRCH_FOREST)),
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.OLD_BIRCH)),
                GenerationStep.Decoration.VEGETAL_DECORATION
        ));
    }

    private static ResourceKey<BiomeModifier> registerKey(String name) {
        return ResourceKey.create(NeoForgeRegistries.Keys.BIOME_MODIFIERS, ResourceLocation.fromNamespaceAndPath(NatureCode.MODID, name));
    }
}
