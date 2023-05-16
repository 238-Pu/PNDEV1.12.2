package net.lepidodendron.entity.render.entity;

import net.lepidodendron.LepidodendronMod;
import net.lepidodendron.entity.EntityPrehistoricFloraScaumenacia;
import net.lepidodendron.entity.model.entity.ModelScaumenacia;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderScaumenacia extends RenderLiving<EntityPrehistoricFloraScaumenacia> {
    public static final ResourceLocation TEXTURE = new ResourceLocation(LepidodendronMod.MODID + ":textures/entities/scaumenacia.png");
    public static final ModelBase MODEL_BASE = new ModelScaumenacia();

    public RenderScaumenacia(RenderManager mgr) {
        super(mgr, MODEL_BASE, 0.0f);
    }

    @Override
    public ResourceLocation getEntityTexture(EntityPrehistoricFloraScaumenacia entity) {
        return RenderScaumenacia.TEXTURE;
    }

    @Override
    protected void applyRotations(EntityPrehistoricFloraScaumenacia entityLiving, float ageInTicks, float rotationYaw, float partialTicks) {
        super.applyRotations(entityLiving, ageInTicks, rotationYaw, partialTicks);
    }

}