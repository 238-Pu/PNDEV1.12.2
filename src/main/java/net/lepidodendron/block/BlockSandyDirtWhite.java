
package net.lepidodendron.block;

import net.lepidodendron.ElementsLepidodendronMod;
import net.lepidodendron.LepidodendronSorter;
import net.lepidodendron.creativetab.TabLepidodendronMisc;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.oredict.OreDictionary;

import java.util.Random;

@ElementsLepidodendronMod.ModElement.Tag
public class BlockSandyDirtWhite extends ElementsLepidodendronMod.ModElement {
	@GameRegistry.ObjectHolder("lepidodendron:sandy_dirt_white")
	public static final Block block = null;
	public BlockSandyDirtWhite(ElementsLepidodendronMod instance) {
		super(instance, LepidodendronSorter.sandy_dirt_white);
	}

	@Override
	public void init(FMLInitializationEvent event) {
		super.init(event);
		OreDictionary.registerOre("dirt", BlockSandyDirtWhite.block);
	}

	@Override
	public void initElements() {
		elements.blocks.add(() -> new BlockCustom().setRegistryName("sandy_dirt_white"));
		elements.items.add(() -> new ItemBlock(block).setRegistryName(block.getRegistryName()));
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void registerModels(ModelRegistryEvent event) {
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block), 0,
				new ModelResourceLocation("lepidodendron:sandy_dirt_white", "inventory"));
	}

	public static class BlockCustom extends Block {
		public BlockCustom() {
			super(Material.GROUND);
			setTranslationKey("pf_sandy_dirt_white");
			this.setTickRandomly(true);
			this.setHardness(0.5F);
			this.setHarvestLevel("shovel", 0);
			this.setSoundType(SoundType.GROUND);
			setCreativeTab(TabLepidodendronMisc.tab);
		}

		@Override
		public MapColor getMapColor(IBlockState state, IBlockAccess blockAccess, BlockPos pos) {
			return MapColor.WHITE_STAINED_HARDENED_CLAY;
		}

		@Override
		public boolean canSustainPlant(IBlockState state, IBlockAccess world, BlockPos pos, EnumFacing direction, IPlantable plantable) {
			net.minecraftforge.common.EnumPlantType plantType = plantable.getPlantType(world, pos.offset(direction));
			if (plantable == Blocks.MELON_STEM || plantable == Blocks.PUMPKIN_STEM)
			{
				return true;
			}
			switch (plantType)
			{
				case Desert: return true;
				case Cave:   return state.isSideSolid(world, pos, EnumFacing.UP);
				case Plains: return true;
				case Beach:
					boolean hasWater = (world.getBlockState(pos.east()).getMaterial() == Material.WATER ||
							world.getBlockState(pos.west()).getMaterial() == Material.WATER ||
							world.getBlockState(pos.north()).getMaterial() == Material.WATER ||
							world.getBlockState(pos.south()).getMaterial() == Material.WATER);
					return hasWater;
			}

			return super.canSustainPlant(state, world, pos, direction, plantable);
		}

		@Override
		public void updateTick(World world, BlockPos pos, IBlockState state, Random rand) {
			IBlockState grassState = Blocks.GRASS.getDefaultState();

			if (grassState != null)
			{
				pullGrassFromNeighbors(world, pos, grassState, rand, 4, 1, 3, 1);
			}
		}

		public void pullGrassFromNeighbors(World world, BlockPos pos, IBlockState grassState, Random rand, int tries, int xzSpread, int downSpread, int upSpread)
		{
			IBlockState upState = world.getBlockState(pos.up());
			// if there's not enough light then there's no chance of this block becoming grassy
			if (world.getLightFromNeighbors(pos.up()) < 4 || upState.getLightOpacity(world, pos.up()) > 2) {return;}

			int numNearbyGrassSpreadingBlocks = 0;
			BlockPos pos1;
			for (int dy = -downSpread; dy <= upSpread; dy++)
			{
				for (int dx = -xzSpread; dx <= xzSpread; dx++)
				{
					for (int dz = -xzSpread; dz <= xzSpread; dz++)
					{
						// count only vanilla grass blocks with enough light
						pos1 = pos.add(dx, dy, dz);
						if (world.getBlockState(pos1).getBlock() == Blocks.GRASS && world.getLightFromNeighbors(pos1.up()) >= 9)
						{
							numNearbyGrassSpreadingBlocks++;
						}
					}
				}
			}
			if (numNearbyGrassSpreadingBlocks == 0) {return;}

			// each grass block gets 4 tries to spread grass, chance of this block being chosen each time is 1 / volume of blocks close enough
			// overall chance of spread = 1 - chance of not spreading
			int vol = (xzSpread * 2 + 1) * (xzSpread * 2 + 1) * (upSpread + downSpread + 1);
			double chanceOfSpread = 1.0D - Math.pow(1.0D - 1.0D / vol, tries * numNearbyGrassSpreadingBlocks);
			if (rand.nextDouble() < chanceOfSpread)
			{
				world.setBlockState(pos, grassState);
			}
		}

	}
}
