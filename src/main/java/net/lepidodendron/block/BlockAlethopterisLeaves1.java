
package net.lepidodendron.block;

import net.lepidodendron.ElementsLepidodendronMod;
import net.lepidodendron.LepidodendronConfig;
import net.lepidodendron.LepidodendronSorter;
import net.lepidodendron.block.base.BlockLeavesPF;
import net.lepidodendron.procedure.ProcedureTreeLeaf;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.statemap.StateMap;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@ElementsLepidodendronMod.ModElement.Tag
public class BlockAlethopterisLeaves1 extends ElementsLepidodendronMod.ModElement {
	@GameRegistry.ObjectHolder("lepidodendron:alethopteris_leaves_1")
	public static final Block block = null;
	public BlockAlethopterisLeaves1(ElementsLepidodendronMod instance) {
		super(instance, LepidodendronSorter.alethopteris_leaves_1);
	}

	@Override
	public void initElements() {
		elements.blocks.add(() -> new BlockCustom().setRegistryName("alethopteris_leaves_1"));
		//elements.items.add(() -> new ItemBlock(block).setRegistryName(block.getRegistryName()));
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void registerModels(ModelRegistryEvent event) {
		//ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block), 0,
		//		new ModelResourceLocation("lepidodendron:alethopteris_leaves_1", "inventory"));
		ModelLoader.setCustomStateMapper(block, (new StateMap.Builder()).ignore(BlockLeaves.DECAYABLE, BlockLeaves.CHECK_DECAY).build());
	}
	public static class BlockCustom extends BlockLeavesPF {
		public BlockCustom() {
			setTranslationKey("pf_alethopteris_leaves_1");
		}

		@Override
		public NonNullList<ItemStack> onSheared(ItemStack item, net.minecraft.world.IBlockAccess world, BlockPos pos, int fortune) {
			return NonNullList.withSize(1, new ItemStack(BlockAlethopterisLeaves.block, 1));
		}

		@Override
		protected ItemStack getSilkTouchDrop(IBlockState state) {
			return new ItemStack(BlockAlethopterisLeaves.block, 1);
		}

		@Override
		public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player) {
			return new ItemStack(BlockAlethopterisLeaves.block, (int) (1));
		}

		@Override
		protected int getSaplingDropChance(IBlockState state) {
			return 20;
		}

		@Override
		public Item getItemDropped(IBlockState state, java.util.Random rand, int fortune) {
			if (LepidodendronConfig.doPropagation) {
				// Drop air and use the seeds method instead:
				return new ItemStack(Blocks.AIR, (int) (1)).getItem();
			}
			else {
				return Item.getItemFromBlock(BlockAlethopterisSapling.block);
			}
		}
		
		@Override
		public void breakBlock(World worldIn, BlockPos pos, IBlockState state)  {
			super.breakBlock(worldIn, pos, state);
			if (state.getBlock() != this
			&& state.getBlock() != BlockAlethopterisLeaves.block)
			{
				//Replace surrounding blocks with decayable variants:
				int k = pos.getX();
		        int l = pos.getY();
		        int i1 = pos.getZ();
		
		        if (worldIn.isAreaLoaded(new BlockPos(k - 3, l - 3, i1 - 3), new BlockPos(k + 3, l + 3, i1 + 3)))
		        {
		            for (int j1 = -2; j1 <= 2; ++j1)
		            {
		                for (int k1 = -2; k1 <= 2; ++k1)
		                {
		                    for (int l1 = -2; l1 <= 2; ++l1)
		                    {
		                        BlockPos blockpos = pos.add(j1, k1, l1);
		                        IBlockState iblockstate = worldIn.getBlockState(blockpos);
		
		                        if (iblockstate.getBlock() == this)
		                        {
		                        	ProcedureTreeLeaf.executeProcedure((int) blockpos.getX(), (int) blockpos.getY(), (int) blockpos.getZ(), worldIn, BlockAlethopterisLeaves.block);
		                        }
		                    }
		                }
		            }
		        }
			}
		}

		@Override
		public void onBlockHarvested(World worldIn, BlockPos pos, IBlockState state, EntityPlayer player) {
			super.onBlockHarvested(worldIn, pos, state, player);
			//Replace surrounding blocks with decayable variants:
			int k = pos.getX();
	        int l = pos.getY();
	        int i1 = pos.getZ();
	
	        if (worldIn.isAreaLoaded(new BlockPos(k - 3, l - 3, i1 - 3), new BlockPos(k + 3, l + 3, i1 + 3)))
	        {
	            for (int j1 = -2; j1 <= 2; ++j1)
	            {
	                for (int k1 = -2; k1 <= 2; ++k1)
	                {
	                    for (int l1 = -2; l1 <= 2; ++l1)
	                    {
	                        BlockPos blockpos = pos.add(j1, k1, l1);
	                        IBlockState iblockstate = worldIn.getBlockState(blockpos);
	
	                        if (iblockstate.getBlock() == this)
	                        {
	                        	ProcedureTreeLeaf.executeProcedure((int) blockpos.getX(), (int) blockpos.getY(), (int) blockpos.getZ(), worldIn, BlockAlethopterisLeaves.block);
	                        }
	                    }
	                }
	            }
	        }
		}

	}
}
