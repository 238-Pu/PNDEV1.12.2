
package net.lepidodendron.entity;

import net.ilexiconn.llibrary.client.model.tools.ChainBuffer;
import net.ilexiconn.llibrary.server.animation.Animation;
import net.lepidodendron.LepidodendronConfig;
import net.lepidodendron.LepidodendronMod;
import net.lepidodendron.entity.ai.AmphibianWander;
import net.lepidodendron.entity.ai.EatFishFoodAIAmphibian;
import net.lepidodendron.entity.ai.EntityMateAIAgeableBase;
import net.lepidodendron.entity.base.EntityPrehistoricFloraSwimmingAmphibianBase;
import net.lepidodendron.entity.render.entity.RenderCeratodus;
import net.lepidodendron.entity.render.entity.RenderEastmanosteus;
import net.lepidodendron.entity.render.tile.RenderDisplays;
import net.minecraft.client.model.ModelBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;

public class EntityPrehistoricFloraCeratodus extends EntityPrehistoricFloraSwimmingAmphibianBase {

	public BlockPos currentTarget;
	@SideOnly(Side.CLIENT)
	public ChainBuffer chainBuffer;
	private int animationTick;
	private Animation animation = NO_ANIMATION;

	public EntityPrehistoricFloraCeratodus(World world) {
		super(world);
		setSize(0.5F, 0.3F);
		minWidth = 0.1F;
		maxWidth = 0.50F;
		maxHeight = 0.30F;
		maxHealthAgeable = 8.0D;
	}

	@Override
	public boolean canJumpOutOfWater() {
		return false;
	}

	@Override
	public boolean dropsEggs() {
		return true;
	}

	@Override
	public boolean laysEggs() {
		return false;
	}

	@Override
	public boolean isSmall() {
		return true;
	}

	public static String getPeriod() {return "Triassic - Jurassic - Cretaceous - Palaeogene";}

	public static String getSize() {return "L";}
	//public static String getHabitat() {return "Aquatic";}

	//@Override
	//protected float getAISpeedFish() {
	//	return 0.185f;
	//}

	@Override
	protected float getAISpeedSwimmingAmphibian() {
		if (this.isReallyInWater()) {
			return 0.185f;
		}
		return 0.22F;
	}

	@Override
	public int WaterDist() {
		int i = (int) LepidodendronConfig.waterCeratodus;
		if (i > 16) {i = 16;}
		if (i < 1) {i = 1;}
		return i;
	}

	@Override
	public int getAdultAge() {
		return 0;
	}

	@Override
	public boolean isBase() {
		return true;
	}

	@Override
	public int getAnimationTick() {
		return getAnimationTick();
	}

	@Override
	public void setAnimationTick(int tick) {
		animationTick = tick;
	}

	@Override
	public Animation getAnimation() {
		return null;
	}

	@Override
	public void setAnimation(Animation animation) {
		this.animation = animation;
	}

	@Override
	public Animation[] getAnimations() {
		return null;
	}

	protected void initEntityAI() {
		tasks.addTask(0, new EntityMateAIAgeableBase(this, 1));
		tasks.addTask(1, new AmphibianWander(this, NO_ANIMATION,1, 20));
		this.targetTasks.addTask(0, new EatFishFoodAIAmphibian(this));
	}

	@Override
	public boolean isAIDisabled() {
		return false;
	}

	@Override
	public EnumCreatureAttribute getCreatureAttribute() {
		return EnumCreatureAttribute.UNDEFINED;
	}

	@Override
	protected boolean canDespawn() {
		return false;
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(20.0D);
		this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.25D);
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

	@Override
	protected float getSoundVolume() {
		return 1.0F;
	}

	@Override
	public void onLivingUpdate() {
		super.onLivingUpdate();
		this.renderYawOffset = this.rotationYaw;
	}

	@Override
	public int airTime() {
		return 10000;
	}

	@Nullable
	protected ResourceLocation getLootTable() {
		return LepidodendronMod.CERATODUS_LOOT;
	}
	//Rendering taxidermy:
	//--------------------
	public static double offsetWall() {
		return 0.01;
	}
	public static double upperfrontverticallinedepth() {
		return 1.4;
	}
	public static double upperbackverticallinedepth() {
		return 0.8;
	}
	public static double upperfrontlineoffset() {
		return 0.4;
	}
	public static double upperfrontlineoffsetperpendiular() {
		return -0F;
	}
	public static double upperbacklineoffset() {
		return 0.4;
	}
	public static double upperbacklineoffsetperpendiular() {
		return -0.15F;
	}
	public static double lowerfrontverticallinedepth() {
		return 1;
	}
	public static double lowerbackverticallinedepth() {
		return 1;
	}
	public static double lowerfrontlineoffset() {
		return -0.04;
	}
	public static double lowerfrontlineoffsetperpendiular() {
		return 0.25F;
	}
	public static double lowerbacklineoffset() {
		return 0;
	}
	public static double lowerbacklineoffsetperpendiular() {
		return -0.35F;
	}
	@SideOnly(Side.CLIENT)
	public static ResourceLocation textureDisplay() {
		return RenderDisplays.TEXTURE_CERATODUS;
	}
	@SideOnly(Side.CLIENT)
	public static ModelBase modelDisplay() {
		return RenderDisplays.modelCeratodus;
	}
	public static float getScaler() {
		return RenderCeratodus.getScaler();
	}

}