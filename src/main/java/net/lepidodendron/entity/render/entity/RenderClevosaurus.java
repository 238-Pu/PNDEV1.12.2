package net.lepidodendron.entity.render.entity;

import net.lepidodendron.LepidodendronMod;
import net.lepidodendron.entity.EntityPrehistoricFloraClevosaurus;
import net.lepidodendron.entity.model.entity.ModelClevosaurus;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderClevosaurus extends RenderLiving<EntityPrehistoricFloraClevosaurus> {
    public static final ResourceLocation TEXTURE = new ResourceLocation(LepidodendronMod.MODID + ":textures/entities/clevosaurus.png");
    public static final ModelBase MODEL_BASE = new ModelClevosaurus();

    public RenderClevosaurus(RenderManager mgr) {
        super(mgr, MODEL_BASE, 0.0f);
    }

    @Override
    public ResourceLocation getEntityTexture(EntityPrehistoricFloraClevosaurus entity) {
        return RenderClevosaurus.TEXTURE;
    }

    @Override
    protected void applyRotations(EntityPrehistoricFloraClevosaurus entityLiving, float ageInTicks, float rotationYaw, float partialTicks) {
        super.applyRotations(entityLiving, ageInTicks, rotationYaw, partialTicks);
    }

    @Override
    protected void preRenderCallback(EntityPrehistoricFloraClevosaurus entity, float f) {
        float scale = entity.getAgeScale() * 1.00F;
        GlStateManager.scale(scale, scale, scale);
        this.shadowSize = entity.width * scale * 0.05F;
    }

}