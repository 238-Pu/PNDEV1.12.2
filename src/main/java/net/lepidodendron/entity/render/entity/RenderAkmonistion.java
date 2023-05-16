package net.lepidodendron.entity.render.entity;

import net.lepidodendron.LepidodendronMod;
import net.lepidodendron.entity.EntityPrehistoricFloraAkmonistion;
import net.lepidodendron.entity.model.entity.ModelAkmonistion;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderAkmonistion extends RenderLiving<EntityPrehistoricFloraAkmonistion> {
    public static final ResourceLocation TEXTURE = new ResourceLocation(LepidodendronMod.MODID + ":textures/entities/akmonistion.png");
    public static final ModelBase MODEL_BASE = new ModelAkmonistion();

    public RenderAkmonistion(RenderManager mgr) {
        super(mgr, MODEL_BASE, 0.0f);
    }

    public static float getScaler() {
        return 0.32F;
    }

    @Override
    public ResourceLocation getEntityTexture(EntityPrehistoricFloraAkmonistion entity) {
        return RenderAkmonistion.TEXTURE;
    }

    @Override
    protected void applyRotations(EntityPrehistoricFloraAkmonistion entityLiving, float ageInTicks, float rotationYaw, float partialTicks) {
        super.applyRotations(entityLiving, ageInTicks, rotationYaw, partialTicks);
    }

    @Override
    protected void preRenderCallback(EntityPrehistoricFloraAkmonistion entity, float f) {
        float scale = entity.getAgeScale() * this.getScaler();
        GlStateManager.scale(scale, scale, scale);
    }

}