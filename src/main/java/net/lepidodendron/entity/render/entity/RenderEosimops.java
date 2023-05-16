package net.lepidodendron.entity.render.entity;

import net.lepidodendron.LepidodendronMod;
import net.lepidodendron.entity.EntityPrehistoricFloraEosimops;
import net.lepidodendron.entity.model.entity.ModelDiictodon;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderEosimops extends RenderLiving<EntityPrehistoricFloraEosimops> {
    public static final ResourceLocation TEXTURE = new ResourceLocation(LepidodendronMod.MODID + ":textures/entities/eosimops.png");
    public static final ModelBase MODEL_BASE = new ModelDiictodon();

    public RenderEosimops(RenderManager mgr) {
        super(mgr, MODEL_BASE, 0.5f);
    }

    public static float getScaler() {
        return 1F * 0.30f;
    }


    @Override
    public ResourceLocation getEntityTexture(EntityPrehistoricFloraEosimops entity) {
        return RenderEosimops.TEXTURE;
    }

    @Override
    protected void applyRotations(EntityPrehistoricFloraEosimops entityLiving, float ageInTicks, float rotationYaw, float partialTicks) {
        super.applyRotations(entityLiving, ageInTicks, rotationYaw, partialTicks);
    }

    @Override
    protected void preRenderCallback(EntityPrehistoricFloraEosimops entity, float f) {
        float scale = entity.getAgeScale() * getScaler();
        GlStateManager.scale(scale, scale, scale);
        this.shadowSize = entity.width * scale * 0.15F;
    }

}