package net.lepidodendron.world.gen;

import net.lepidodendron.block.BlockOtozamites;
import net.lepidodendron.util.Functions;
import net.minecraft.block.material.Material;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

import java.util.Random;

public class WorldGenOtozamites extends WorldGenerator
{
    public boolean generate(World worldIn, Random rand, BlockPos position) {
        return generate(worldIn, rand, position, 0, 255);
    }

    public boolean generate(World worldIn, Random rand, BlockPos position, int minHeight, int maxHeight)
    {
        if (minHeight == 0) {
            minHeight = Functions.getAdjustedSeaLevel(worldIn, position) - 4;
        }
        boolean flag = false;

        for (int i = 0; i < 16; ++i)
        {
            BlockPos blockpos = position.add(rand.nextInt(8) - rand.nextInt(8), rand.nextInt(4) - rand.nextInt(4), rand.nextInt(8) - rand.nextInt(8));
            if (blockpos.getY() > minHeight + (rand.nextInt(5) - 2) && worldIn.isAirBlock(blockpos) && (!worldIn.provider.isNether() || blockpos.getY() < 254) && BlockOtozamites.block.canPlaceBlockAt(worldIn, blockpos) &&
                (
                    (worldIn.getBlockState(blockpos.down()).getMaterial() == Material.GROUND)
                        || (worldIn.getBlockState(blockpos.down()).getMaterial() == Material.SAND)
                )
                && (blockpos.getY() > minHeight + (rand.nextInt(5) - 2))
                && (blockpos.getY() < maxHeight + (rand.nextInt(5) - 2))
            )
            {

                Functions.setBlockStateAndCheckForDoublePlant(worldIn,blockpos, BlockOtozamites.block.getDefaultState(), 2);
                BlockOtozamites.block.onBlockAdded(worldIn, blockpos, BlockOtozamites.block.getDefaultState());

                flag = true;
            }
        }

        return flag;
    }
}
