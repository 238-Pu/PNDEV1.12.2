package net.lepidodendron.entity.render.entity;

import net.lepidodendron.LepidodendronMod;
import net.lepidodendron.entity.EntityPrehistoricFloraHadronector;
import net.lepidodendron.entity.model.entity.ModelHadronector;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderHadronector extends RenderLiving<EntityPrehistoricFloraHadronector> {
    public static final ResourceLocation TEXTURE = new ResourceLocation(LepidodendronMod.MODID + ":textures/entities/hadronector.png");
    public static final ModelBase MODEL_BASE = new ModelHadronector();

    public static float getScaler() {
        return 0.7F * 0.395F;
    }
    public RenderHadronector(RenderManager mgr) {
        super(mgr, MODEL_BASE, 0.0f);
    }

    @Override
    public ResourceLocation getEntityTexture(EntityPrehistoricFloraHadronector entity) {
        return RenderHadronector.TEXTURE;
    }

    @Override
    protected void applyRotations(EntityPrehistoricFloraHadronector entityLiving, float ageInTicks, float rotationYaw, float partialTicks) {
        super.applyRotations(entityLiving, ageInTicks, rotationYaw, partialTicks);
    }
    @Override
    protected void preRenderCallback(EntityPrehistoricFloraHadronector entity, float f) {
        float scale = this.getScaler();
        if (scale < 0.1f) {
            scale = 0.1f;
        }
        GlStateManager.scale(scale, scale, scale);
        this.shadowSize = 0;
    }

}