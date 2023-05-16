package net.lepidodendron.entity.render.entity;

import net.lepidodendron.LepidodendronMod;
import net.lepidodendron.entity.EntityPrehistoricFloraSyllipsimopodi;
import net.lepidodendron.entity.model.entity.ModelSyllipsimopodi;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderSyllipsimopodi extends RenderLiving<EntityPrehistoricFloraSyllipsimopodi> {
    public static final ResourceLocation TEXTURE = new ResourceLocation(LepidodendronMod.MODID + ":textures/entities/syllipsimopodi.png");
    public static final ModelBase MODEL_BASE = new ModelSyllipsimopodi();

    public RenderSyllipsimopodi(RenderManager mgr) {
        super(mgr, MODEL_BASE, 0.0f);
    }

    @Override
    public ResourceLocation getEntityTexture(EntityPrehistoricFloraSyllipsimopodi entity) {
        return RenderSyllipsimopodi.TEXTURE;
    }

    @Override
    protected void applyRotations(EntityPrehistoricFloraSyllipsimopodi entityLiving, float ageInTicks, float rotationYaw, float partialTicks) {
        super.applyRotations(entityLiving, ageInTicks, rotationYaw, partialTicks);
    }

    @Override
    protected void preRenderCallback(EntityPrehistoricFloraSyllipsimopodi entity, float f) {
        float scale = entity.getAgeScale();
        GlStateManager.scale(scale, scale, scale);
        this.shadowSize = entity.width * scale * 0.35F;
    }

}