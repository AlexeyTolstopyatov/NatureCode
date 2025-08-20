package org.coffeelake.naturecode.worldgen.tree;

import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.BlobFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.trunkplacers.ForkingTrunkPlacer;

public class BirchCurve {
    /**
     * Configures tree feature based on required external
     * function calls. All params depends on external part of class API
     * /Want to make random tree's height generation/
     * @param dead Has leaves
     * @param height based height
     * @param heightA minimum bound
     * @param heightB maximum bound
     * @return fuck you
     */
    private static TreeConfiguration BuildWith(boolean dead, int height, int heightA, int heightB) {
        var leaves = dead
                ? Blocks.AIR
                : Blocks.BIRCH_LEAVES;

        return new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(Blocks.BIRCH_LOG),
                new ForkingTrunkPlacer(height, heightA, heightB),
                BlockStateProvider.simple(leaves),
                new BlobFoliagePlacer(ConstantInt.of(2), ConstantInt.of(3), 3),
                new TwoLayersFeatureSize(1, 0, 2)
        ).build();
    }
    /**
     * Configures feature based on main tree parameters
     * @return configured curve birch
     */
    public static TreeConfiguration Build() {
        return BuildWith(false, 6, 3, 5);
    }

    /**
     * Configures dead feature (without leaves) based on main tree parameters
     * @return configured curve birch
     */
    public static TreeConfiguration BuildDead() {
        return BuildWith(true, 10, 3, 5);
    }
}
