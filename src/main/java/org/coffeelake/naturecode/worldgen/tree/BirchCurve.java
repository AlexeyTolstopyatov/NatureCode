package org.coffeelake.naturecode.worldgen.tree;

import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.BlobFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.trunkplacers.ForkingTrunkPlacer;

public class BirchCurve {
//    public static final TreeConfiguration CONFIG = new TreeConfiguration.TreeConfigurationBuilder(
//            BlockStateProvider.simple(Blocks.BIRCH_LOG),
//            new ForkingTrunkPlacer(6, 4, 3),
//            BlockStateProvider.simple(Blocks.BIRCH_LEAVES),
//            new BlobFoliagePlacer(ConstantInt.of(2), ConstantInt.of(3), 3),
//            new TwoLayersFeatureSize(1, 0, 2)).build();

    /**
     * Configures feature based on main tree parameters
     * @param height main height
     * @param heightFirstBranch height for a 1st branch
     * @param heightSecondBranch height for a 2nd branch
     * @return configured curve birch
     */
    public static TreeConfiguration BuildWith(int height, int heightFirstBranch, int heightSecondBranch) {
        return new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(Blocks.BIRCH_LOG),
                new ForkingTrunkPlacer(height, heightFirstBranch, heightSecondBranch),
                BlockStateProvider.simple(Blocks.BIRCH_LEAVES),
                new BlobFoliagePlacer(ConstantInt.of(2), ConstantInt.of(3), 3),
                new TwoLayersFeatureSize(1, 0, 2)
        ).build();
    }

    /**
     * Configures dead feature (without leaves) based on main tree parameters
     * @param height main height
     * @param heightFirstBranch height for a 1st branch
     * @param heightSecondBranch height for a 2nd branch
     * @return configured curve birch
     */
    public static TreeConfiguration BuildDeadWith(int height, int heightFirstBranch, int heightSecondBranch) {
        return new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(Blocks.BIRCH_LOG),
                new ForkingTrunkPlacer(height, heightFirstBranch, heightSecondBranch),
                BlockStateProvider.simple(Blocks.AIR),
                new BlobFoliagePlacer(ConstantInt.of(2), ConstantInt.of(3), 3),
                new TwoLayersFeatureSize(1, 0, 2)
        ).build();
    }
}
