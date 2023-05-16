package net.lepidodendron.entity.render.entity;

import net.lepidodendron.LepidodendronMod;
import net.lepidodendron.entity.EntityPrehistoricFloraHelmetia;
import net.lepidodendron.entity.model.entity.ModelHelmetia;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderHelmetia extends RenderLiving<EntityPrehistoricFloraHelmetia> {
    public static final ResourceLocation TEXTURE = new ResourceLocation(LepidodendronMod.MODID + ":textures/entities/helmetia.png");
    public static final ModelBase MODEL_BASE = new ModelHelmetia();

    public RenderHelmetia(RenderManager mgr) {
        super(mgr, MODEL_BASE, 0.08f);
    }

    @Override
    public ResourceLocation getEntityTexture(EntityPrehistoricFloraHelmetia entity) {
        return RenderHelmetia.TEXTURE;
    }

    @Override
    protected void applyRotations(EntityPrehistoricFloraHelmetia entityLiving, float ageInTicks, float rotationYaw, float partialTicks) {
        super.applyRotations(entityLiving, ageInTicks, rotationYaw, partialTicks);
    }

}