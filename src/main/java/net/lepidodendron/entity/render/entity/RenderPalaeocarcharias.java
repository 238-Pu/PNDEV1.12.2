package net.lepidodendron.entity.render.entity;

import net.lepidodendron.LepidodendronMod;
import net.lepidodendron.entity.EntityPrehistoricFloraPalaeocarcharias;
import net.lepidodendron.entity.model.entity.ModelPalaeocarcharias;
import net.lepidodendron.entity.render.RenderLivingBaseWithBook;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderPalaeocarcharias extends RenderLivingBaseWithBook<EntityPrehistoricFloraPalaeocarcharias> {
    public static final ResourceLocation TEXTURE = new ResourceLocation(LepidodendronMod.MODID + ":textures/entities/palaeocarcharias.png");

    public static float getScaler() {
        return 0.7F * 0.5F;
    }
    public RenderPalaeocarcharias(RenderManager mgr) {
        super(mgr, new ModelPalaeocarcharias(), 0.0f);
    }

    @Override
    public ResourceLocation getEntityTexture(EntityPrehistoricFloraPalaeocarcharias entity) {
        return RenderPalaeocarcharias.TEXTURE;
    }

    @Override
    protected void applyRotations(EntityPrehistoricFloraPalaeocarcharias entityLiving, float ageInTicks, float rotationYaw, float partialTicks) {
        super.applyRotations(entityLiving, ageInTicks, rotationYaw, partialTicks);
    }
    @Override
    protected void preRenderCallback(EntityPrehistoricFloraPalaeocarcharias entity, float f) {
        float scale = this.getScaler()*entity.getAgeScale();

        if (scale < 0.1f) {
            scale = 0.1f;
        }
        GlStateManager.scale(scale, scale, scale);
        this.shadowSize = 0;
    }

}