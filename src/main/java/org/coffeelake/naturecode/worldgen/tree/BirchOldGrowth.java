package org.coffeelake.naturecode.worldgen.tree;

import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.MegaPineFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.trunkplacers.GiantTrunkPlacer;

public class BirchOldGrowth {
    public static TreeConfiguration Build() {
        return new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(Blocks.BIRCH_LOG),
                new GiantTrunkPlacer(15, 2, 10),
                BlockStateProvider.simple(Blocks.BIRCH_LEAVES),
                new MegaPineFoliagePlacer(ConstantInt.of(0), ConstantInt.of(0), UniformInt.of(3, 4)), // это для сосны
                new TwoLayersFeatureSize(1, 1, 2))
                .ignoreVines() // ???
                .build();
    }
    public static TreeConfiguration BuildDead() {
        return new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(Blocks.BIRCH_LOG),
                new GiantTrunkPlacer(10, 2, 10),
                //new ForkingTrunkPlacer(0, 3, 5),
                BlockStateProvider.simple(Blocks.AIR),
                new MegaPineFoliagePlacer(ConstantInt.of(0), ConstantInt.of(0), UniformInt.of(3, 4)), // но это для сосны
                new TwoLayersFeatureSize(1, 1, 2))
                .ignoreVines()
                .build();
    }
}
