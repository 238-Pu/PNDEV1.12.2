package net.lepidodendron.procedure;

import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import net.lepidodendron.ElementsLepidodendronMod;
import net.lepidodendron.block.BlockSynchysidendronLeaves;
import net.lepidodendron.block.BlockSynchysidendronLog;
import net.lepidodendron.util.Functions;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

@ElementsLepidodendronMod.ModElement.Tag
public class ProcedureWorldGenSynchysidendronYoung extends ElementsLepidodendronMod.ModElement {
	public ProcedureWorldGenSynchysidendronYoung(ElementsLepidodendronMod instance) {
		super(instance, 13);
	}

	public static void executeProcedure ( Object2ObjectOpenHashMap <String, Object> dependencies ) {
		if (dependencies.get("x") == null) {
			System.err.println("Failed to load dependency x for procedure WorldGenSynchysidendron!");
			return;
		}
		if (dependencies.get("y") == null) {
			System.err.println("Failed to load dependency y for procedure WorldGenSynchysidendron!");
			return;
		}
		if (dependencies.get("z") == null) {
			System.err.println("Failed to load dependency z for procedure WorldGenSynchysidendron!");
			return;
		}
		if (dependencies.get("world") == null) {
			System.err.println("Failed to load dependency world for procedure WorldGenSynchysidendron!");
			return;
		}
		if (dependencies.get("vines") == null) {
			System.err.println("Failed to load dependency vines for procedure WorldGenSynchysidendron!");
			return;
		}
		if (dependencies.get("vines2") == null) {
			System.err.println("Failed to load dependency vines2 for procedure WorldGenSynchysidendron!");
			return;
		}
		if (dependencies.get("SaplingSpawn") == null) {
			System.err.println("Failed to load dependency SaplingSpawn for procedure WorldGenSynchysidendron!");
			return;
		}
		int x = (int) dependencies.get("x");
		int y = (int) dependencies.get("y");
		int z = (int) dependencies.get("z");
		int xx = x;
		int yy = y;
		int zz = z;
		World world = (World) dependencies.get("world");
		boolean vines = (boolean) dependencies.get("vines");
		boolean vines2 = (boolean) dependencies.get("vines2");
		boolean SaplingSpawn = (boolean) dependencies.get("SaplingSpawn");
		double height = 0;
		double counter = 0;
		int vinecounter = 0;
		
		if (((world.canSeeSky(new BlockPos((int) x, (int) y, (int) z)))) ||
			(((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getMaterial() == Material.WATER)
			 && (world.canSeeSky(new BlockPos((int) x, (int) y + 1, (int) z)))) ||
				(((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getMaterial() == Material.WATER)
						&& ((world.getBlockState(new BlockPos((int) x, (int) y + 1, (int) z))).getMaterial() == Material.WATER)
						&& (world.canSeeSky(new BlockPos((int) x, (int) y + 2, (int) z)))) ||
				(((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getMaterial() == Material.WATER)
						&& ((world.getBlockState(new BlockPos((int) x, (int) y + 1, (int) z))).getMaterial() == Material.WATER)
						&& ((world.getBlockState(new BlockPos((int) x, (int) y + 2, (int) z))).getMaterial() == Material.WATER)
						&& (world.canSeeSky(new BlockPos((int) x, (int) y + 3, (int) z)))) ||
				(((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getMaterial() == Material.WATER)
						&& ((world.getBlockState(new BlockPos((int) x, (int) y + 1, (int) z))).getMaterial() == Material.WATER)
						&& ((world.getBlockState(new BlockPos((int) x, (int) y + 2, (int) z))).getMaterial() == Material.WATER)
						&& ((world.getBlockState(new BlockPos((int) x, (int) y + 3, (int) z))).getMaterial() == Material.WATER)
						&& (world.canSeeSky(new BlockPos((int) x, (int) y + 4, (int) z))))) {
			world.setBlockToAir(new BlockPos((int) x, (int) y, (int) z));
			Functions.setBlockStateAndCheckForDoublePlant(world, new BlockPos((int) x, (int) y, (int) z), BlockSynchysidendronLog.block.getDefaultState(), 3);

			Block block = world.getBlockState(new BlockPos((int) x, (int) (y + 1), (int) z)).getBlock();
			if (block.canBeReplacedByLeaves(world.getBlockState(new BlockPos((int) x, (int) (y + 1), (int) z)), world,
					new BlockPos((int) x, (int) (y + 1), (int) z))
					|| ((world.getBlockState(new BlockPos((int) x, (int) (y + 1), (int) z))).getMaterial() == Material.VINE)
					|| ((world.getBlockState(new BlockPos((int) x, (int) (y + 1), (int) z))).getMaterial() == Material.SNOW)
					|| ((world.getBlockState(new BlockPos((int) x, (int) (y + 1), (int) z))).getMaterial() == Material.WEB)
					|| ((world.getBlockState(new BlockPos((int) x, (int) (y + 1), (int) z))).getMaterial() == Material.WATER)
					|| ((world.getBlockState(new BlockPos((int) x, (int) (y + 1), (int) z))).getMaterial() == Material.PLANTS)
					|| ((world.getBlockState(new BlockPos((int) x, (int) (y + 1), (int) z))).getMaterial() == Material.LEAVES)) {
				Functions.setBlockStateAndCheckForDoublePlant(world, new BlockPos((int) x, (int) (y + 1), (int) z), BlockSynchysidendronLog.block.getDefaultState(), 3);
			}
			block = world.getBlockState(new BlockPos((int) x, (int) y, (int) (z + 1))).getBlock();
			if (block.canBeReplacedByLeaves(world.getBlockState(new BlockPos((int) x, (int) y, (int) (z + 1))), world,
					new BlockPos((int) x, (int) y, (int) (z + 1)))
					|| ((world.getBlockState(new BlockPos((int) x, (int) y, (int) (z + 1)))).getMaterial() == Material.VINE)
					|| ((world.getBlockState(new BlockPos((int) x, (int) y, (int) (z + 1)))).getMaterial() == Material.SNOW)
					|| ((world.getBlockState(new BlockPos((int) x, (int) y, (int) (z + 1)))).getMaterial() == Material.WEB)
					|| ((world.getBlockState(new BlockPos((int) x, (int) y, (int) (z + 1)))).getMaterial() == Material.WATER)
					|| ((world.getBlockState(new BlockPos((int) x, (int) y, (int) (z + 1)))).getMaterial() == Material.PLANTS)
					|| ((world.getBlockState(new BlockPos((int) x, (int) y, (int) (z + 1)))).getMaterial() == Material.LEAVES)) {
				Functions.setBlockStateAndCheckForDoublePlant(world, new BlockPos((int) x, (int) y, (int) (z + 1)), BlockSynchysidendronLog.block.getDefaultState(), 3);
			}
			block = world.getBlockState(new BlockPos((int) x, (int) y, (int) (z + 2))).getBlock();
			if (block.canBeReplacedByLeaves(world.getBlockState(new BlockPos((int) x, (int) y, (int) (z + 2))), world,
					new BlockPos((int) x, (int) y, (int) (z + 2)))
					|| ((world.getBlockState(new BlockPos((int) x, (int) y, (int) (z + 2)))).getMaterial() == Material.VINE)
					|| ((world.getBlockState(new BlockPos((int) x, (int) y, (int) (z + 2)))).getMaterial() == Material.SNOW)
					|| ((world.getBlockState(new BlockPos((int) x, (int) y, (int) (z + 2)))).getMaterial() == Material.WEB)
					|| ((world.getBlockState(new BlockPos((int) x, (int) y, (int) (z + 2)))).getMaterial() == Material.WATER)
					|| ((world.getBlockState(new BlockPos((int) x, (int) y, (int) (z + 2)))).getMaterial() == Material.PLANTS)
					|| ((world.getBlockState(new BlockPos((int) x, (int) y, (int) (z + 2)))).getMaterial() == Material.LEAVES)) {
				Functions.setBlockStateAndCheckForDoublePlant(world, new BlockPos((int) x, (int) y, (int) (z + 2)), BlockSynchysidendronLog.block.getDefaultState(), 3);
				try {
					IBlockState _bs = world.getBlockState(new BlockPos((int) x, (int) y, (int) (z + 2)));
					for (IProperty<?> prop : _bs.getProperties().keySet()) {
						if (prop.getName().equals("facing")) {
							Functions.setBlockStateAndCheckForDoublePlant(world, new BlockPos((int) x, (int) y, (int) (z + 2)),
									_bs.withProperty((PropertyDirection) prop, EnumFacing.WEST), 3);
							break;
						}
					}
				} catch (Exception e) {
				}
			}
			block = world.getBlockState(new BlockPos((int) x, (int) y, (int) (z - 1))).getBlock();
			if (block.canBeReplacedByLeaves(world.getBlockState(new BlockPos((int) x, (int) y, (int) (z - 1))), world,
					new BlockPos((int) x, (int) y, (int) (z - 1)))
					|| ((world.getBlockState(new BlockPos((int) x, (int) y, (int) (z - 1)))).getMaterial() == Material.VINE)
					|| ((world.getBlockState(new BlockPos((int) x, (int) y, (int) (z - 1)))).getMaterial() == Material.SNOW)
					|| ((world.getBlockState(new BlockPos((int) x, (int) y, (int) (z - 1)))).getMaterial() == Material.WEB)
					|| ((world.getBlockState(new BlockPos((int) x, (int) y, (int) (z - 1)))).getMaterial() == Material.WATER)
					|| ((world.getBlockState(new BlockPos((int) x, (int) y, (int) (z - 1)))).getMaterial() == Material.PLANTS)
					|| ((world.getBlockState(new BlockPos((int) x, (int) y, (int) (z - 1)))).getMaterial() == Material.LEAVES)) {
				Functions.setBlockStateAndCheckForDoublePlant(world, new BlockPos((int) x, (int) y, (int) (z - 1)), BlockSynchysidendronLog.block.getDefaultState(), 3);
			}
			block = world.getBlockState(new BlockPos((int) x, (int) y, (int) (z - 2))).getBlock();
			if (block.canBeReplacedByLeaves(world.getBlockState(new BlockPos((int) x, (int) y, (int) (z - 2))), world,
					new BlockPos((int) x, (int) y, (int) (z - 2)))
					|| ((world.getBlockState(new BlockPos((int) x, (int) y, (int) (z - 2)))).getMaterial() == Material.VINE)
					|| ((world.getBlockState(new BlockPos((int) x, (int) y, (int) (z - 2)))).getMaterial() == Material.SNOW)
					|| ((world.getBlockState(new BlockPos((int) x, (int) y, (int) (z - 2)))).getMaterial() == Material.WEB)
					|| ((world.getBlockState(new BlockPos((int) x, (int) y, (int) (z - 2)))).getMaterial() == Material.WATER)
					|| ((world.getBlockState(new BlockPos((int) x, (int) y, (int) (z - 2)))).getMaterial() == Material.PLANTS)
					|| ((world.getBlockState(new BlockPos((int) x, (int) y, (int) (z - 2)))).getMaterial() == Material.LEAVES)) {
				Functions.setBlockStateAndCheckForDoublePlant(world, new BlockPos((int) x, (int) y, (int) (z - 2)), BlockSynchysidendronLog.block.getDefaultState(), 3);
				try {
					IBlockState _bs = world.getBlockState(new BlockPos((int) x, (int) y, (int) (z - 2)));
					for (IProperty<?> prop : _bs.getProperties().keySet()) {
						if (prop.getName().equals("facing")) {
							Functions.setBlockStateAndCheckForDoublePlant(world, new BlockPos((int) x, (int) y, (int) (z - 2)),
									_bs.withProperty((PropertyDirection) prop, EnumFacing.WEST), 3);
							break;
						}
					}
				} catch (Exception e) {
				}
			}
			block = world.getBlockState(new BlockPos((int) (x + 1), (int) y, (int) z)).getBlock();
			if (block.canBeReplacedByLeaves(world.getBlockState(new BlockPos((int) (x + 1), (int) y, (int) z)), world,
					new BlockPos((int) (x + 1), (int) y, (int) z))
					|| ((world.getBlockState(new BlockPos((int) (x + 1), (int) y, (int) z))).getMaterial() == Material.VINE)
					|| ((world.getBlockState(new BlockPos((int) (x + 1), (int) y, (int) z))).getMaterial() == Material.SNOW)
					|| ((world.getBlockState(new BlockPos((int) (x + 1), (int) y, (int) z))).getMaterial() == Material.WEB)
					|| ((world.getBlockState(new BlockPos((int) (x + 1), (int) y, (int) z))).getMaterial() == Material.WATER)
					|| ((world.getBlockState(new BlockPos((int) (x + 1), (int) y, (int) z))).getMaterial() == Material.PLANTS)
					|| ((world.getBlockState(new BlockPos((int) (x + 1), (int) y, (int) z))).getMaterial() == Material.LEAVES)) {
				Functions.setBlockStateAndCheckForDoublePlant(world, new BlockPos((int) (x + 1), (int) y, (int) z), BlockSynchysidendronLog.block.getDefaultState(), 3);
			}
			block = world.getBlockState(new BlockPos((int) (x + 2), (int) y, (int) z)).getBlock();
			if (block.canBeReplacedByLeaves(world.getBlockState(new BlockPos((int) (x + 2), (int) y, (int) z)), world,
					new BlockPos((int) (x + 2), (int) y, (int) z))
					|| ((world.getBlockState(new BlockPos((int) (x + 2), (int) y, (int) z))).getMaterial() == Material.VINE)
					|| ((world.getBlockState(new BlockPos((int) (x + 2), (int) y, (int) z))).getMaterial() == Material.SNOW)
					|| ((world.getBlockState(new BlockPos((int) (x + 2), (int) y, (int) z))).getMaterial() == Material.WEB)
					|| ((world.getBlockState(new BlockPos((int) (x + 2), (int) y, (int) z))).getMaterial() == Material.WATER)
					|| ((world.getBlockState(new BlockPos((int) (x + 2), (int) y, (int) z))).getMaterial() == Material.PLANTS)
					|| ((world.getBlockState(new BlockPos((int) (x + 2), (int) y, (int) z))).getMaterial() == Material.LEAVES)) {
				Functions.setBlockStateAndCheckForDoublePlant(world, new BlockPos((int) (x + 2), (int) y, (int) z), BlockSynchysidendronLog.block.getDefaultState(), 3);
				try {
					IBlockState _bs = world.getBlockState(new BlockPos((int) (x + 2), (int) y, (int) z));
					for (IProperty<?> prop : _bs.getProperties().keySet()) {
						if (prop.getName().equals("facing")) {
							Functions.setBlockStateAndCheckForDoublePlant(world, new BlockPos((int) (x + 2), (int) y, (int) z),
									_bs.withProperty((PropertyDirection) prop, EnumFacing.UP), 3);
							break;
						}
					}
				} catch (Exception e) {
				}
			}
			block = world.getBlockState(new BlockPos((int) (x - 1), (int) y, (int) z)).getBlock();
			if (block.canBeReplacedByLeaves(world.getBlockState(new BlockPos((int) (x - 1), (int) y, (int) z)), world,
					new BlockPos((int) (x - 1), (int) y, (int) z))
					|| ((world.getBlockState(new BlockPos((int) (x - 1), (int) y, (int) z))).getMaterial() == Material.VINE)
					|| ((world.getBlockState(new BlockPos((int) (x - 1), (int) y, (int) z))).getMaterial() == Material.SNOW)
					|| ((world.getBlockState(new BlockPos((int) (x - 1), (int) y, (int) z))).getMaterial() == Material.WEB)
					|| ((world.getBlockState(new BlockPos((int) (x - 1), (int) y, (int) z))).getMaterial() == Material.WATER)
					|| ((world.getBlockState(new BlockPos((int) (x - 1), (int) y, (int) z))).getMaterial() == Material.PLANTS)
					|| ((world.getBlockState(new BlockPos((int) (x - 1), (int) y, (int) z))).getMaterial() == Material.LEAVES)) {
				Functions.setBlockStateAndCheckForDoublePlant(world, new BlockPos((int) (x - 1), (int) y, (int) z), BlockSynchysidendronLog.block.getDefaultState(), 3);
			}
			block = world.getBlockState(new BlockPos((int) (x - 2), (int) y, (int) z)).getBlock();
			if (block.canBeReplacedByLeaves(world.getBlockState(new BlockPos((int) (x - 2), (int) y, (int) z)), world,
					new BlockPos((int) (x - 2), (int) y, (int) z))
					|| ((world.getBlockState(new BlockPos((int) (x - 2), (int) y, (int) z))).getMaterial() == Material.VINE)
					|| ((world.getBlockState(new BlockPos((int) (x - 2), (int) y, (int) z))).getMaterial() == Material.SNOW)
					|| ((world.getBlockState(new BlockPos((int) (x - 2), (int) y, (int) z))).getMaterial() == Material.WEB)
					|| ((world.getBlockState(new BlockPos((int) (x - 2), (int) y, (int) z))).getMaterial() == Material.WATER)
					|| ((world.getBlockState(new BlockPos((int) (x - 2), (int) y, (int) z))).getMaterial() == Material.PLANTS)
					|| ((world.getBlockState(new BlockPos((int) (x - 2), (int) y, (int) z))).getMaterial() == Material.LEAVES)) {
				Functions.setBlockStateAndCheckForDoublePlant(world, new BlockPos((int) (x - 2), (int) y, (int) z), BlockSynchysidendronLog.block.getDefaultState(), 3);
				try {
					IBlockState _bs = world.getBlockState(new BlockPos((int) (x - 2), (int) y, (int) z));
					for (IProperty<?> prop : _bs.getProperties().keySet()) {
						if (prop.getName().equals("facing")) {
							Functions.setBlockStateAndCheckForDoublePlant(world, new BlockPos((int) (x - 2), (int) y, (int) z),
									_bs.withProperty((PropertyDirection) prop, EnumFacing.UP), 3);
							break;
						}
					}
				} catch (Exception e) {
				}
			}
			block = world.getBlockState(new BlockPos((int) (x + 1), (int) (y + 1), (int) z)).getBlock();
			if (block.canBeReplacedByLeaves(world.getBlockState(new BlockPos((int) (x + 1), (int) (y + 1), (int) z)), world,
					new BlockPos((int) (x + 1), (int) (y + 1), (int) z))
					|| ((world.getBlockState(new BlockPos((int) (x + 1), (int) (y + 1), (int) z))).getMaterial() == Material.VINE)
					|| ((world.getBlockState(new BlockPos((int) (x + 1), (int) (y + 1), (int) z))).getMaterial() == Material.SNOW)
					|| ((world.getBlockState(new BlockPos((int) (x + 1), (int) (y + 1), (int) z))).getMaterial() == Material.WEB)
					|| ((world.getBlockState(new BlockPos((int) (x + 1), (int) (y + 1), (int) z))).getMaterial() == Material.WATER)
					|| ((world.getBlockState(new BlockPos((int) (x + 1), (int) (y + 1), (int) z))).getMaterial() == Material.PLANTS)
					|| ((world.getBlockState(new BlockPos((int) (x + 1), (int) (y + 1), (int) z))).getMaterial() == Material.LEAVES)) {
				Functions.setBlockStateAndCheckForDoublePlant(world, new BlockPos((int) (x + 1), (int) (y + 1), (int) z), BlockSynchysidendronLog.block.getDefaultState(), 3);
			}
			block = world.getBlockState(new BlockPos((int) (x - 1), (int) (y + 1), (int) z)).getBlock();
			if (block.canBeReplacedByLeaves(world.getBlockState(new BlockPos((int) (x - 1), (int) (y + 1), (int) z)), world,
					new BlockPos((int) (x - 1), (int) (y + 1), (int) z))
					|| ((world.getBlockState(new BlockPos((int) (x - 1), (int) (y + 1), (int) z))).getMaterial() == Material.VINE)
					|| ((world.getBlockState(new BlockPos((int) (x - 1), (int) (y + 1), (int) z))).getMaterial() == Material.SNOW)
					|| ((world.getBlockState(new BlockPos((int) (x - 1), (int) (y + 1), (int) z))).getMaterial() == Material.WEB)
					|| ((world.getBlockState(new BlockPos((int) (x - 1), (int) (y + 1), (int) z))).getMaterial() == Material.WATER)
					|| ((world.getBlockState(new BlockPos((int) (x - 1), (int) (y + 1), (int) z))).getMaterial() == Material.PLANTS)
					|| ((world.getBlockState(new BlockPos((int) (x - 1), (int) (y + 1), (int) z))).getMaterial() == Material.LEAVES)) {
				Functions.setBlockStateAndCheckForDoublePlant(world, new BlockPos((int) (x - 1), (int) (y + 1), (int) z), BlockSynchysidendronLog.block.getDefaultState(), 3);
			}
			block = world.getBlockState(new BlockPos((int) x, (int) (y + 1), (int) (z + 1))).getBlock();
			if (block.canBeReplacedByLeaves(world.getBlockState(new BlockPos((int) x, (int) (y + 1), (int) (z + 1))), world,
					new BlockPos((int) x, (int) (y + 1), (int) (z + 1)))
					|| ((world.getBlockState(new BlockPos((int) x, (int) (y + 1), (int) (z + 1)))).getMaterial() == Material.VINE)
					|| ((world.getBlockState(new BlockPos((int) x, (int) (y + 1), (int) (z + 1)))).getMaterial() == Material.SNOW)
					|| ((world.getBlockState(new BlockPos((int) x, (int) (y + 1), (int) (z + 1)))).getMaterial() == Material.WEB)
					|| ((world.getBlockState(new BlockPos((int) x, (int) (y + 1), (int) (z + 1)))).getMaterial() == Material.WATER)
					|| ((world.getBlockState(new BlockPos((int) x, (int) (y + 1), (int) (z + 1)))).getMaterial() == Material.PLANTS)
					|| ((world.getBlockState(new BlockPos((int) x, (int) (y + 1), (int) (z + 1)))).getMaterial() == Material.LEAVES)) {
				Functions.setBlockStateAndCheckForDoublePlant(world, new BlockPos((int) x, (int) (y + 1), (int) (z + 1)), BlockSynchysidendronLog.block.getDefaultState(), 3);
			}
			block = world.getBlockState(new BlockPos((int) x, (int) (y + 1), (int) (z - 1))).getBlock();
			if (block.canBeReplacedByLeaves(world.getBlockState(new BlockPos((int) x, (int) (y + 1), (int) (z - 1))), world,
					new BlockPos((int) x, (int) (y + 1), (int) (z - 1)))
					|| ((world.getBlockState(new BlockPos((int) x, (int) (y + 1), (int) (z - 1)))).getMaterial() == Material.VINE)
					|| ((world.getBlockState(new BlockPos((int) x, (int) (y + 1), (int) (z - 1)))).getMaterial() == Material.SNOW)
					|| ((world.getBlockState(new BlockPos((int) x, (int) (y + 1), (int) (z - 1)))).getMaterial() == Material.WEB)
					|| ((world.getBlockState(new BlockPos((int) x, (int) (y + 1), (int) (z - 1)))).getMaterial() == Material.WATER)
					|| ((world.getBlockState(new BlockPos((int) x, (int) (y + 1), (int) (z - 1)))).getMaterial() == Material.PLANTS)
					|| ((world.getBlockState(new BlockPos((int) x, (int) (y + 1), (int) (z - 1)))).getMaterial() == Material.LEAVES)) {
				Functions.setBlockStateAndCheckForDoublePlant(world, new BlockPos((int) x, (int) (y + 1), (int) (z - 1)), BlockSynchysidendronLog.block.getDefaultState(), 3);
			}

			height = (double) (10) + Math.round((Math.random() * 10D) / 2D)
					+ Math.round((Math.random() * 4D) / 2D);
			counter = (double) 1;
			while (((counter) <= (height))) {
				block = world.getBlockState(new BlockPos((int) x, (int) ((y + 1) + (counter)), (int) z)).getBlock();
				if (block.canBeReplacedByLeaves(world.getBlockState(new BlockPos((int) x, (int) ((y + 1) + (counter)), (int) z)), world,
						new BlockPos((int) x, (int) ((y + 1) + (counter)), (int) z))) {
					Functions.setBlockStateAndCheckForDoublePlant(world, new BlockPos((int) x, (int) ((y + 1) + (counter)), (int) z), BlockSynchysidendronLog.block.getDefaultState(), 3);
				} else if (world.getBlockState(new BlockPos((int) x, (int) ((y + 1) + (counter)), (int) z)).getMaterial() == Material.WATER) {
					Functions.setBlockStateAndCheckForDoublePlant(world, new BlockPos((int) x, (int) ((y + 1) + (counter)), (int) z), BlockSynchysidendronLog.block.getDefaultState(), 3);
				}
				counter = (double) ((counter) + 1);
			}

			//Random vines for world-gen only:
			if ((vines) && (Math.random() > 0.6)) {
				xx = x;
				yy = y + (int) height - 7;
				zz = z;
				ProcedureVines.executeProcedure(xx, yy, zz, world);
			}
			//Random vines for world-gen only:
			if ((vines2) && (Math.random() > 0.6)) {
				xx = x;
				yy = y + (int) height - 7;
				zz = z;
				ProcedureVines2.executeProcedure(xx, yy, zz, world);
			}

			counter++;

			//Leaves:
			ProcedureTreeLeaf.executeProcedure(x, y + (int) counter, z, world, BlockSynchysidendronLeaves.block);
			ProcedureTreeLeaf.executeProcedure(x, y + (int) counter + 1, z, world, BlockSynchysidendronLeaves.block);
			ProcedureTreeLeaf.executeProcedure(x + 1, y + (int) counter, z, world, BlockSynchysidendronLeaves.block);
			ProcedureTreeLeaf.executeProcedure(x - 1, y + (int) counter, z, world, BlockSynchysidendronLeaves.block);
			ProcedureTreeLeaf.executeProcedure(x, y + (int) counter, z + 1, world, BlockSynchysidendronLeaves.block);
			ProcedureTreeLeaf.executeProcedure(x, y + (int) counter, z - 1, world, BlockSynchysidendronLeaves.block);
			ProcedureTreeLeaf.executeProcedure(x + 1, y + (int) counter - 1, z, world, BlockSynchysidendronLeaves.block);
			ProcedureTreeLeaf.executeProcedure(x - 1, y + (int) counter - 1, z, world, BlockSynchysidendronLeaves.block);
			ProcedureTreeLeaf.executeProcedure(x, y + (int) counter - 1, z + 1, world, BlockSynchysidendronLeaves.block);
			ProcedureTreeLeaf.executeProcedure(x, y + (int) counter - 1, z - 1, world, BlockSynchysidendronLeaves.block);

			ProcedureTreeLeaf.executeProcedure(x + 1, y + (int) counter - 3, z, world, BlockSynchysidendronLeaves.block);
			ProcedureTreeLeaf.executeProcedure(x - 1, y + (int) counter - 3, z, world, BlockSynchysidendronLeaves.block);
			ProcedureTreeLeaf.executeProcedure(x, y + (int) counter - 3, z + 1, world, BlockSynchysidendronLeaves.block);
			ProcedureTreeLeaf.executeProcedure(x, y + (int) counter - 3, z - 1, world, BlockSynchysidendronLeaves.block);

		}
	}
}
