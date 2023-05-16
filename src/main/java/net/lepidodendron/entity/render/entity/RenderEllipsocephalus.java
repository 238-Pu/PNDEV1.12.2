package net.lepidodendron.entity.render.entity;

import net.lepidodendron.LepidodendronMod;
import net.lepidodendron.entity.EntityPrehistoricFloraEllipsocephalus;
import net.lepidodendron.entity.model.entity.ModelEllipsocephalus;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderEllipsocephalus extends RenderLiving<EntityPrehistoricFloraEllipsocephalus> {
    public static final ResourceLocation TEXTURE = new ResourceLocation(LepidodendronMod.MODID + ":textures/entities/ellipsocephalus.png");
    public static final ModelBase MODEL_BASE = new ModelEllipsocephalus();

    public RenderEllipsocephalus(RenderManager mgr) {
        super(mgr, MODEL_BASE, 0.0f);
    }

    @Override
    public ResourceLocation getEntityTexture(EntityPrehistoricFloraEllipsocephalus entity) {
        return RenderEllipsocephalus.TEXTURE;
    }

    @Override
    protected void applyRotations(EntityPrehistoricFloraEllipsocephalus entityLiving, float ageInTicks, float rotationYaw, float partialTicks) {
        super.applyRotations(entityLiving, ageInTicks, rotationYaw, partialTicks);
    }

}