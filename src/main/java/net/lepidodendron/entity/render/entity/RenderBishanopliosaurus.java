package net.lepidodendron.entity.render.entity;

import net.lepidodendron.LepidodendronMod;
import net.lepidodendron.entity.EntityPrehistoricFloraBishanopliosaurus;
import net.lepidodendron.entity.model.entity.ModelBishanopliosaurus;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderBishanopliosaurus extends RenderLiving<EntityPrehistoricFloraBishanopliosaurus> {
    private static final ResourceLocation TEXTURE = new ResourceLocation(LepidodendronMod.MODID + ":textures/entities/bishanopliosaurus.png");

    public static float getScaler() {
        return 0.8F;
    }
    public RenderBishanopliosaurus(RenderManager mgr) {
        super(mgr, new ModelBishanopliosaurus(), 1.5f);
    }

    @Override
    public ResourceLocation getEntityTexture(EntityPrehistoricFloraBishanopliosaurus entity) {
        return RenderBishanopliosaurus.TEXTURE;
    }

    @Override
    protected void applyRotations(EntityPrehistoricFloraBishanopliosaurus entityLiving, float ageInTicks, float rotationYaw, float partialTicks) {
        super.applyRotations(entityLiving, ageInTicks, rotationYaw, partialTicks);
    }

    @Override
    protected void preRenderCallback(EntityPrehistoricFloraBishanopliosaurus entity, float f) {
        float scale = entity.getAgeScale()*this.getScaler();
        if (scale < 0.1f) {scale = 0.1f;}
        GlStateManager.scale(scale, scale, scale);
        this.shadowSize = entity.width * scale * 0.35F;
    }

}