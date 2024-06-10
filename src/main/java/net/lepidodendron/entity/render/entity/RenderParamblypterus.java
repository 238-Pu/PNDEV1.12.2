package net.lepidodendron.entity.render.entity;

import net.lepidodendron.LepidodendronMod;
import net.lepidodendron.entity.EntityPrehistoricFloraParamblypterus;
import net.lepidodendron.entity.model.entity.ModelParamblypterus;
import net.lepidodendron.entity.render.RenderLivingBaseWithBook;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderParamblypterus extends RenderLivingBaseWithBook<EntityPrehistoricFloraParamblypterus> {
    public static final ResourceLocation TEXTURE = new ResourceLocation(LepidodendronMod.MODID + ":textures/entities/paramblypterus.png");

    public static float getScaler() {
        return 0.55F * 0.495F;
    }
    public RenderParamblypterus(RenderManager mgr) {
        super(mgr, new ModelParamblypterus(), 0.0f);
    }

    @Override
    public ResourceLocation getEntityTexture(EntityPrehistoricFloraParamblypterus entity) {
        return RenderParamblypterus.TEXTURE;
    }

    @Override
    protected void applyRotations(EntityPrehistoricFloraParamblypterus entityLiving, float ageInTicks, float rotationYaw, float partialTicks) {
        super.applyRotations(entityLiving, ageInTicks, rotationYaw, partialTicks);
    }
    @Override
    protected void preRenderCallback(EntityPrehistoricFloraParamblypterus entity, float f) {
        float scale = this.getScaler();
        if (scale < 0.1f) {
            scale = 0.1f;
        }
        GlStateManager.scale(scale, scale, scale);
        this.shadowSize = 0;
    }

}