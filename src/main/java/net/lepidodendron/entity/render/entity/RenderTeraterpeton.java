package net.lepidodendron.entity.render.entity;

import net.lepidodendron.LepidodendronMod;
import net.lepidodendron.entity.EntityPrehistoricFloraTeraterpeton;
import net.lepidodendron.entity.model.entity.ModelTeraterpeton;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderTeraterpeton extends RenderLiving<EntityPrehistoricFloraTeraterpeton> {
    public static final ResourceLocation TEXTURE = new ResourceLocation(LepidodendronMod.MODID + ":textures/entities/teraterpeton.png");
    public static final ModelBase MODEL_BASE = new ModelTeraterpeton();

    public RenderTeraterpeton(RenderManager mgr) {
        super(mgr, MODEL_BASE, 0.0f);
    }

    @Override
    public ResourceLocation getEntityTexture(EntityPrehistoricFloraTeraterpeton entity) {
        return RenderTeraterpeton.TEXTURE;
    }

    @Override
    protected void applyRotations(EntityPrehistoricFloraTeraterpeton entityLiving, float ageInTicks, float rotationYaw, float partialTicks) {
        super.applyRotations(entityLiving, ageInTicks, rotationYaw, partialTicks);
    }

}




