package org.coffeelake.naturecode.worldgen.tree;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;

public class BirchOldGrowth extends Feature<TreeConfiguration> {

    public BirchOldGrowth(Codec<TreeConfiguration> codec) {
        super(codec);
    }
    @Override

    public boolean place(FeaturePlaceContext<TreeConfiguration> context) {
        WorldGenLevel level = context.level();
        BlockPos basePos = context.origin();
        RandomSource random = context.random();

        // if not ground -> exit
        if (!level.getBlockState(basePos.below()).is(BlockTags.DIRT))
            return false;

        // tree config
        int height = 10 + random.nextInt(30);
        int crownRadius = (height > 20) ? 6 : 4;

        // main part
        for (int y = 0; y < height; y++) {
            BlockPos pos = basePos.above(y);

            // main part
            level.setBlock(pos, Blocks.BIRCH_LOG.defaultBlockState(), 2);

            // Branches
            if (y > height * 0.67) {
                for (Direction dir : Direction.Plane.HORIZONTAL) {
                    if (random.nextFloat() < 0.3) {
                        BlockPos branchPos = pos.relative(dir);
                        if (level.isEmptyBlock(branchPos)) {
                            level.setBlock(branchPos, Blocks.BIRCH_LOG.defaultBlockState()
                                    .setValue(RotatedPillarBlock.AXIS, dir.getAxis()), 2);
                        }
                    }
                }
            }
        }

        // crown
        BlockPos crownCenter = basePos.above(height - 3);
        for (int dy = -2; dy <= 3; dy++) {
            int radius = crownRadius - Math.abs(dy)/2;
            generateCrownLayer(level, crownCenter.above(dy), radius, random);
        }

        return true;
    }

    private void generateCrownLayer(WorldGenLevel level, BlockPos center, int radius, RandomSource random) {
        for (int dx = -radius; dx <= radius; dx++) {
            for (int dz = -radius; dz <= radius; dz++) {
                if (dx*dx + dz*dz <= radius*radius) {
                    BlockPos pos = center.offset(dx, 0, dz);
                    if (level.isEmptyBlock(pos) && random.nextFloat() < 0.85) {
                        level.setBlock(pos, Blocks.BIRCH_LEAVES.defaultBlockState(), 2);
                    }
                }
            }
        }
    }
}
