package net.lepidodendron.entity.render.entity;

import net.lepidodendron.LepidodendronMod;
import net.lepidodendron.entity.EntityPrehistoricFloraParexus;
import net.lepidodendron.entity.model.entity.ModelParexus;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderParexus extends RenderLiving<EntityPrehistoricFloraParexus> {
    public static final ResourceLocation TEXTURE = new ResourceLocation(LepidodendronMod.MODID + ":textures/entities/parexus.png");
    public static final ModelBase MODEL_BASE = new ModelParexus();

    public RenderParexus(RenderManager mgr) {
        super(mgr, MODEL_BASE, 0.0f);
    }

    @Override
    public ResourceLocation getEntityTexture(EntityPrehistoricFloraParexus entity) {
        return RenderParexus.TEXTURE;
    }

    @Override
    protected void applyRotations(EntityPrehistoricFloraParexus entityLiving, float ageInTicks, float rotationYaw, float partialTicks) {
        super.applyRotations(entityLiving, ageInTicks, rotationYaw, partialTicks);
    }

}