package net.lepidodendron.entity.render.entity;

import net.lepidodendron.LepidodendronMod;
import net.lepidodendron.entity.EntityPrehistoricFloraGroenlandaspis;
import net.lepidodendron.entity.model.entity.ModelGroenlandaspis;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderGroenlandaspis extends RenderLiving<EntityPrehistoricFloraGroenlandaspis> {
    public static final ResourceLocation TEXTURE = new ResourceLocation(LepidodendronMod.MODID + ":textures/entities/groenlandaspis.png");
    public static final ModelBase MODEL_BASE = new ModelGroenlandaspis();

    public RenderGroenlandaspis(RenderManager mgr) {
        super(mgr, MODEL_BASE, 0.4f);
    }

    @Override
    public ResourceLocation getEntityTexture(EntityPrehistoricFloraGroenlandaspis entity) {
        return RenderGroenlandaspis.TEXTURE;
    }

    @Override
    protected void applyRotations(EntityPrehistoricFloraGroenlandaspis entityLiving, float ageInTicks, float rotationYaw, float partialTicks) {
        super.applyRotations(entityLiving, ageInTicks, rotationYaw, partialTicks);
    }

    @Override
    protected void preRenderCallback(EntityPrehistoricFloraGroenlandaspis entity, float f) {
        float scale = entity.getAgeScale();
        if (scale < 0.1f) {scale = 0.1f;}
        GlStateManager.scale(scale, scale, scale);
        this.shadowSize = entity.width * scale * 0.4f;
    }

}