
package net.lepidodendron.entity;

import net.ilexiconn.llibrary.client.model.tools.ChainBuffer;
import net.ilexiconn.llibrary.server.animation.AnimationHandler;
import net.lepidodendron.LepidodendronConfig;
import net.lepidodendron.LepidodendronMod;
import net.lepidodendron.entity.ai.EatFishFoodAIAgeable;
import net.lepidodendron.entity.ai.EntityMateAIAgeableBase;
import net.lepidodendron.entity.ai.SwimmingBottomWalkingSwimBottomDweller;
import net.lepidodendron.entity.ai.SwimmingBottomWalkingWalk;
import net.lepidodendron.entity.base.EntityPrehistoricFloraSwimmingBottomWalkingWaterBase;
import net.lepidodendron.entity.render.entity.RenderProteroctopus;
import net.lepidodendron.entity.render.tile.RenderDisplays;
import net.minecraft.client.model.ModelBase;
import net.lepidodendron.item.ItemFishFood;
import net.lepidodendron.item.entities.ItemNautiloidEggsProteroctopus;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;

public class EntityPrehistoricFloraProteroctopus extends EntityPrehistoricFloraSwimmingBottomWalkingWaterBase {

	public BlockPos currentTarget;
	@SideOnly(Side.CLIENT)
	public ChainBuffer tailBuffer;
	@SideOnly(Side.CLIENT)
	public ChainBuffer chainBuffer;

	public EntityPrehistoricFloraProteroctopus(World world) {
		super(world);
		setSize(0.5F, 0.3F);
		minWidth = 0.1F;
		maxWidth = 0.5F;
		maxHeight = 0.3F;
		maxHealthAgeable = 4.0D;
		if (FMLCommonHandler.instance().getSide().isClient()) {
			tailBuffer = new ChainBuffer();
		}
	}
	@Override
	public void onLivingUpdate() {
		super.onLivingUpdate();

		if (this.getAnimation() == ATTACK_ANIMATION && this.getAnimationTick() == 10 && this.getAttackTarget() != null) {
			launchAttack();
			if (this.getOneHit()) {
				this.setAttackTarget(null);
				this.setRevengeTarget(null);
			}
		}

		AnimationHandler.INSTANCE.updateAnimations(this);

	}

	@Override
	public void onUpdate() {
		super.onUpdate();
		if (world.isRemote && !this.isAIDisabled()) {
			tailBuffer.calculateChainSwingBuffer(120, 10, 5F, this);
		}
	}

	@Override
	public int swimTransitionLength() {
		return 20;
	}

	@Override
	public int unswimTransitionLength() {
		return 20;
	}

	@Override
	public int swimLength() {
		return 1200;
	}

	@Override
	public int walkLength() {
		return 1200;
	}

	@Override
	public boolean isSmall() {
		return true;
	}

	public static String getPeriod() {return "Jurassic";}

	//public static String getHabitat() {return "Aquatic";}

	@Override
	public boolean dropsEggs() {
		return false;
	}
	
	@Override
	public boolean laysEggs() {
		return false;
	}

	@Override
	public int getAdultAge() {
		return 48000;
	}

	@Override
	protected double getAISpeedSwim() {
		if (!this.isReallySwimming()) {
			return 0.10f;
		} else {
			return 0.075f;
		}
	}

	protected void initEntityAI() {
		tasks.addTask(0, new EntityMateAIAgeableBase(this, 1));
		tasks.addTask(1, new SwimmingBottomWalkingSwimBottomDweller(this, NO_ANIMATION));
		tasks.addTask(2, new SwimmingBottomWalkingWalk(this, NO_ANIMATION));
		tasks.addTask(3, new EntityAILookIdle(this));
		this.targetTasks.addTask(0, new EatFishFoodAIAgeable(this));
	}

	@Override
	public boolean isBreedingItem(ItemStack stack)
	{
		return (stack.getItem() == new ItemStack(ItemFishFood.block, (int) (1)).getItem());
	}

	@Override
	public SoundEvent getAmbientSound() {
		return (SoundEvent) SoundEvent.REGISTRY.getObject(new ResourceLocation(""));
	}

	@Override
	public SoundEvent getHurtSound(DamageSource ds) {
		return (SoundEvent) SoundEvent.REGISTRY.getObject(new ResourceLocation("entity.generic.hurt"));
	}

	@Override
	public SoundEvent getDeathSound() {
		return (SoundEvent) SoundEvent.REGISTRY.getObject(new ResourceLocation("entity.generic.death"));
	}

	public void onEntityUpdate() {
		super.onEntityUpdate();
		//Drop an egg perhaps:
		if (!world.isRemote && this.isPFAdult() && this.getCanBreed() && (LepidodendronConfig.doMultiplyMobs || this.getLaying())) {
			if (Math.random() > 0.5) {
				ItemStack itemstack = new ItemStack(ItemNautiloidEggsProteroctopus.block, (int) (1));
				EntityItem entityToSpawn = new EntityItem(world, this.getPosition().getX(), this.getPosition().getY(), this.getPosition().getZ(), itemstack);
				entityToSpawn.setPickupDelay(10);
				world.spawnEntity(entityToSpawn);
			}
			this.setTicks(0);
		}
	}

	@Nullable
	protected ResourceLocation getLootTable() {
		if (!this.isPFAdult()) {
			return LepidodendronMod.PROTEROCTOPUS_LOOT_YOUNG;
		}
		return LepidodendronMod.PROTEROCTOPUS_LOOT;
	}
	//Rendering taxidermy:
	//--------------------
	public static double offsetWall() {
		return 0.01;
	}
	public static double upperfrontverticallinedepth() {
		return 0;
	}
	public static double upperbackverticallinedepth() {
		return 0.9;
	}
	public static double upperfrontlineoffset() {
		return 0.4;
	}
	public static double upperfrontlineoffsetperpendiular() {
		return -0F;
	}
	public static double upperbacklineoffset() {
		return 0.02;
	}
	public static double upperbacklineoffsetperpendiular() {
		return 0.0F;
	}
	public static double lowerfrontverticallinedepth() {
		return 0;
	}
	public static double lowerbackverticallinedepth() {
		return 0;
	}
	public static double lowerfrontlineoffset() {
		return 0.4;
	}
	public static double lowerfrontlineoffsetperpendiular() {
		return -0F;
	}
	public static double lowerbacklineoffset() {
		return 0.4;
	}
	public static double lowerbacklineoffsetperpendiular() {
		return -0.15F;
	}
	@SideOnly(Side.CLIENT)
	public static ResourceLocation textureDisplay() {
		return RenderDisplays.TEXTURE_PROTEROCTOPUS;
	}
	@SideOnly(Side.CLIENT)
	public static ModelBase modelDisplay() {
		return RenderDisplays.modelProteroctopus;
	}
	public static float getScaler() {
		return RenderProteroctopus.getScaler();
	}

}