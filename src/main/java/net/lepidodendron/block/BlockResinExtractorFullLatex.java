
package net.lepidodendron.block;

import net.lepidodendron.ElementsLepidodendronMod;
import net.lepidodendron.LepidodendronSorter;
import net.lepidodendron.item.ItemBottleOfLatex;
import net.minecraft.block.Block;
import net.minecraft.block.BlockDirectional;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.Explosion;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidUtil;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.ItemHandlerHelper;
import net.minecraftforge.oredict.OreDictionary;

import javax.annotation.Nullable;
import java.util.Random;

@ElementsLepidodendronMod.ModElement.Tag
public class BlockResinExtractorFullLatex extends ElementsLepidodendronMod.ModElement {
	@GameRegistry.ObjectHolder("lepidodendron:resin_extractor_full_latex")
	public static final Block block = null;

	public BlockResinExtractorFullLatex(ElementsLepidodendronMod instance) {
		super(instance, LepidodendronSorter.resin_extractor_full_latex);
	}

	@Override
	public void initElements() {
		elements.blocks.add(() -> new BlockCustom().setRegistryName("resin_extractor_full_latex"));
		//elements.items.add(() -> new ItemBlock(block).setRegistryName(block.getRegistryName()));
	}

	@Override
	public void init(FMLInitializationEvent event) {
		GameRegistry.registerTileEntity(BlockResinExtractorFullLatex.TileEntityCustom.class, "lepidodendron:tileentityresin_extractor_full_latex");
	}

	public static class BlockCustom extends Block {
		public static final PropertyDirection FACING = BlockDirectional.FACING;
		public static final PropertyInteger EXTRACTING = PropertyInteger.create("extracting", 0, 2);

		public BlockCustom() {
			super(Material.WOOD);
			setTranslationKey("pf_resin_extractor_full_latex");
			setSoundType(SoundType.WOOD);
			setHardness(5F);
			setResistance(5F);
			setLightLevel(0F);
			setLightOpacity(0);
			setCreativeTab(null);
			setTickRandomly(true);
			this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.DOWN).withProperty(EXTRACTING, 1));
		}

		@Override
		public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
			if (worldIn.getTileEntity(pos) != null) {
				if (worldIn.getTileEntity(pos) instanceof BlockResinExtractorFullLatex.TileEntityCustom) {
					Boolean extractable = isBlockActive((World) worldIn, pos, state.getValue(BlockResinExtractorFullLatex.BlockCustom.FACING));
					if (extractable) {
						return super.getActualState(state, worldIn, pos).withProperty(EXTRACTING, 2);
					}
				}
			}
			return super.getActualState(state, worldIn, pos).withProperty(EXTRACTING, 0);
		}

		@Override
		public boolean hasTileEntity(IBlockState state) {
			return true;
		}

		@Nullable
		@Override
		public TileEntity createTileEntity(World world, IBlockState state) {
			return new BlockResinExtractorFullLatex.TileEntityCustom();
		}

		public BlockResinExtractorFullLatex.TileEntityCustom createNewTileEntity(World worldIn, int meta) {
			return new BlockResinExtractorFullLatex.TileEntityCustom();
		}

		@Override
		public void breakBlock(World world, BlockPos pos, IBlockState state) {
			TileEntity tileentity = world.getTileEntity(pos);
			//if (tileentity instanceof TileEntityNest)
			//InventoryHelper.dropInventoryItems(world, pos, (TileEntityNest) tileentity);
			world.removeTileEntity(pos);
			super.breakBlock(world, pos, state);
		}

		@Override
		public boolean eventReceived(IBlockState state, World worldIn, BlockPos pos, int eventID, int eventParam) {
			super.eventReceived(state, worldIn, pos, eventID, eventParam);
			TileEntity tileentity = worldIn.getTileEntity(pos);
			return tileentity == null ? false : tileentity.receiveClientEvent(eventID, eventParam);
		}

		@Override
		public boolean hasComparatorInputOverride(IBlockState state) {
			return true;
		}

		@Override
		public int getComparatorInputOverride(IBlockState blockState, World worldIn, BlockPos pos) {
			return 15;
		}

		@SideOnly(Side.CLIENT)
		@Override
		public BlockRenderLayer getRenderLayer() {
			return BlockRenderLayer.CUTOUT;
		}

		@Override
		public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player) {
			return new ItemStack(BlockResinExtractor.block, (int) (1));
		}

		@Override
		protected boolean canSilkHarvest() {
			return false;
		}

		@Override
		public void getDrops(NonNullList<ItemStack> drops, IBlockAccess world, BlockPos pos, IBlockState state, int fortune) {
			drops.removeAll(drops);
			drops.add(new ItemStack(BlockResinExtractor.block, (int) (1)));
		}

		@Override
		public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer entity, EnumHand hand, EnumFacing direction,
										float hitX, float hitY, float hitZ) {
			if (entity.getHeldItemMainhand().getItem() == new ItemStack(Items.GLASS_BOTTLE, (int) (1)).getItem()) {
				SoundEvent soundevent = SoundEvents.ITEM_BOTTLE_FILL;
				entity.getEntityWorld().playSound(entity, entity.getPosition(), soundevent, SoundCategory.BLOCKS, 1.0F, 1.0F);
				entity.inventory.clearMatchingItems(new ItemStack(Items.GLASS_BOTTLE, (int) (1)).getItem(), -1, (int) 1, null);
				ItemStack _setstack = new ItemStack(ItemBottleOfLatex.block, (int) (1));
				_setstack.setCount(1);
				ItemHandlerHelper.giveItemToPlayer(entity, _setstack);
				//New fill level:
				if (!world.isRemote) {
					IBlockState _bs = world.getBlockState(pos);
					world.setBlockState(pos, BlockResinExtractor.block.getDefaultState().withProperty(FACING, _bs.getValue(FACING)));
					TileEntity _tileEntity = world.getTileEntity(pos);
					if (_tileEntity != null) {
						_tileEntity.getTileData().setInteger("mb", 1667);
						_tileEntity.getTileData().setInteger("type", 1);
					}
					world.notifyBlockUpdate(pos, _bs, _bs, 3);
				}
				world.updateComparatorOutputLevel(pos, BlockResinExtractor.block);
				world.updateComparatorOutputLevel(pos, this);
				return true;
			} else if (entity.getHeldItemMainhand().getItem() == new ItemStack(Items.BUCKET, (int) (1)).getItem()) {
				SoundEvent soundevent = SoundEvents.ITEM_BUCKET_FILL;
				entity.getEntityWorld().playSound(entity, entity.getPosition(), soundevent, SoundCategory.BLOCKS, 1.0F, 1.0F);
				entity.inventory.clearMatchingItems(new ItemStack(Items.BUCKET, (int) (1)).getItem(), -1, (int) 1, null);
				ItemStack _setstack = FluidUtil.getFilledBucket(new FluidStack(FluidRegistry.getFluid("pn_latex"), 1000));
				_setstack.setCount(1);
				ItemHandlerHelper.giveItemToPlayer(entity, _setstack);
				//New fill level:
				if (!world.isRemote) {
					IBlockState _bs = world.getBlockState(pos);
					world.setBlockState(pos, BlockResinExtractor.block.getDefaultState().withProperty(FACING, _bs.getValue(FACING)));
					TileEntity _tileEntity = world.getTileEntity(pos);
					if (_tileEntity != null) {
						_tileEntity.getTileData().setInteger("mb", 1000);
						_tileEntity.getTileData().setInteger("type", 1);
					}
					world.notifyBlockUpdate(pos, _bs, _bs, 3);
				}
				world.updateComparatorOutputLevel(pos, BlockResinExtractor.block);
				world.updateComparatorOutputLevel(pos, this);
				return true;
			}
			return super.onBlockActivated(world, pos, state, entity, hand, direction, hitX, hitY, hitZ);
		}

		@Override
		protected net.minecraft.block.state.BlockStateContainer createBlockState() {
			return new net.minecraft.block.state.BlockStateContainer(this, new IProperty[]{FACING, EXTRACTING});
		}

		@Override
		public IBlockState withRotation(IBlockState state, Rotation rot) {
			return state.withProperty(FACING, rot.rotate((EnumFacing) state.getValue(FACING)));
		}

		@Override
		public IBlockState withMirror(IBlockState state, Mirror mirrorIn) {
			return state.withRotation(mirrorIn.toRotation((EnumFacing) state.getValue(FACING)));
		}

		@Override
		public IBlockState getStateFromMeta(int meta) {
			return this.getDefaultState().withProperty(FACING, EnumFacing.byIndex(meta));
		}

		@Override
		public void harvestBlock(World worldIn, EntityPlayer player, BlockPos pos, IBlockState state, @Nullable TileEntity te, ItemStack stack) {
			super.harvestBlock(worldIn, player, pos, state, te, stack);
			worldIn.setBlockState(pos, FluidRegistry.getFluid("pn_latex").getBlock().getDefaultState());
		}

		@Override
		public void onExplosionDestroy(World worldIn, BlockPos pos, Explosion explosionIn) {
			super.onExplosionDestroy(worldIn, pos, explosionIn);
			worldIn.setBlockState(pos, FluidRegistry.getFluid("pn_latex").getBlock().getDefaultState());
		}

		@Override
		public BlockFaceShape getBlockFaceShape(IBlockAccess world, IBlockState state, BlockPos pos, EnumFacing face) {
			return BlockFaceShape.UNDEFINED;
		}

		@Override
		public boolean isOpaqueCube(IBlockState state) {
			return false;
		}

		@Override
		public boolean isFullCube(IBlockState state) {
			return false;
		}

		@Override
		public int getMetaFromState(IBlockState state) {
			return ((EnumFacing) state.getValue(FACING)).getIndex();
		}

		@Override
		public IBlockState getStateForPlacement(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {
			if (canPlaceAt(worldIn, pos, facing)) {
				return this.getDefaultState().withProperty(FACING, facing);
			} else {
				for (EnumFacing enumfacing : EnumFacing.Plane.HORIZONTAL) {
					if (canPlaceAt(worldIn, pos, enumfacing)) {
						return this.getDefaultState().withProperty(FACING, enumfacing);
					}
				}

				return this.getDefaultState();
			}
		}

		@Override
		public boolean canPlaceBlockAt(World worldIn, BlockPos pos) {
			for (EnumFacing enumfacing : FACING.getAllowedValues()) {
				if (canPlaceAt(worldIn, pos, enumfacing)) {
					return true;
				}
			}
			return false;
		}

		public static boolean canPlaceAt(World worldIn, BlockPos pos, EnumFacing facing) {
			BlockPos blockpos = pos.offset(facing.getOpposite());
			IBlockState iblockstate = worldIn.getBlockState(blockpos);
			Block block = iblockstate.getBlock();
			BlockFaceShape blockfaceshape = iblockstate.getBlockFaceShape(worldIn, blockpos, facing);

			if (facing.equals(EnumFacing.UP) || facing.equals(EnumFacing.DOWN)) {
				return false;
			} else if (facing != EnumFacing.UP && facing != EnumFacing.DOWN) {
				return !isExceptBlockForAttachWithPiston(block) && blockfaceshape == BlockFaceShape.SOLID;
			} else {
				return false;
			}
		}

		@Override
		public void updateTick(World world, BlockPos pos, IBlockState state, Random random) {
			super.updateTick(world, pos, state, random);
			//Flood if left too long and uncovered!
			if (isBlockActive(world, pos, state.getValue(FACING))) {
				if ((Math.random() > 0.85) && world.isAirBlock(pos.up())) {
					world.setBlockState(pos.up(), FluidRegistry.getFluid("pn_latex").getBlock().getDefaultState());
				}
			}
		}

		public static boolean isBlockActive(World world, BlockPos pos, EnumFacing facing) {
			//On a valid log which is "planted" and of sufficient height:
			BlockPos position = pos;
			if (facing == EnumFacing.NORTH) {
				position = pos.south();
			}
			if (facing == EnumFacing.SOUTH) {
				position = pos.north();
			}
			if (facing == EnumFacing.EAST) {
				position = pos.west();
			}
			if (facing == EnumFacing.WEST) {
				position = pos.east();
			}

			IBlockState blockstate = world.getBlockState(position);
			Block block = blockstate.getBlock();
			//Is the block "growing"?
			if (
					(world.getBlockState(position.down()).getMaterial() != Material.GROUND)
							&& (world.getBlockState(position.down()).getMaterial() != Material.GRASS)
							&& (world.getBlockState(position.down()).getMaterial() != Material.SAND)
							&& (world.getBlockState(position.down()).getMaterial() != Material.CLAY)
							&& (world.getBlockState(position.down()).getMaterial() != Material.ROCK)
			) {
				return false;
			}
			//Is the block sufficiently high to look tree-ish?
			int i = 1;
			while (i <= 4) {
				if (world.getBlockState(position.up(i)).getBlock() != block) {
					return false;
				}
				i += 1;
			}
			//Is this a resinable block?
			try {
				if (
						block.getPickBlock(blockstate, null, world, position, null) != null
				) {
					if (OreDictionary.containsMatch(false, OreDictionary.getOres("logLatex"),
							block.getPickBlock(blockstate, null, world, position, null))) {
						return true;
					}
				}
			} catch (Exception e) {
			}
			return false;
		}
	}

	public static class TileEntityCustom extends TileEntity implements ITickable {

		@Override
		public void update() {
			//Check the extractable blockstate:
			IBlockState state = this.world.getBlockState(this.getPos());
			Boolean extractable = BlockResinExtractorFullLatex.BlockCustom.isBlockActive(world, pos, state.getValue(BlockResinExtractorFullLatex.BlockCustom.FACING));
			if (state.getValue(BlockResinExtractorFullLatex.BlockCustom.EXTRACTING) > 0 && !extractable) {
				if (!world.isRemote) {
					world.setBlockState(this.getPos(), BlockResinExtractorFullLatex.block.getDefaultState().withProperty(BlockResinExtractorFullLatex.BlockCustom.FACING, state.getValue(BlockResinExtractorFullLatex.BlockCustom.FACING)).withProperty(BlockResinExtractorFullLatex.BlockCustom.EXTRACTING, 0));
				}
			} else if (state.getValue(BlockResinExtractorFullLatex.BlockCustom.EXTRACTING) == 0 && extractable) {
				if (!world.isRemote) {
					world.setBlockState(this.getPos(), BlockResinExtractorFullLatex.block.getDefaultState().withProperty(BlockResinExtractorFullLatex.BlockCustom.FACING, state.getValue(BlockResinExtractorFullLatex.BlockCustom.FACING)).withProperty(BlockResinExtractorFullLatex.BlockCustom.EXTRACTING, 2));
				}
			}
		}
	}
}
