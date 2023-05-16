package net.lepidodendron.entity.render.entity;

import net.lepidodendron.LepidodendronMod;
import net.lepidodendron.entity.EntityPrehistoricFloraKokomopterus;
import net.lepidodendron.entity.model.entity.ModelKokomopterus;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderKokomopterus extends RenderLiving<EntityPrehistoricFloraKokomopterus> {
    public static final ResourceLocation TEXTURE = new ResourceLocation(LepidodendronMod.MODID + ":textures/entities/kokomopterus.png");
    private static final ResourceLocation TEXTURE_BABY = new ResourceLocation(LepidodendronMod.MODID + ":textures/entities/kokomopterus_baby.png");
    public static final ModelBase MODEL_BASE = new ModelKokomopterus();

    public RenderKokomopterus(RenderManager mgr) {
        super(mgr, MODEL_BASE, 0.0f);
    }

    @Override
    public ResourceLocation getEntityTexture(EntityPrehistoricFloraKokomopterus entity) {
        float scale = entity.getAgeScale();
        if (entity.isSmall()) {
            return RenderKokomopterus.TEXTURE_BABY;
        }
        return RenderKokomopterus.TEXTURE;
    }

    @Override
    protected void applyRotations(EntityPrehistoricFloraKokomopterus entityLiving, float ageInTicks, float rotationYaw, float partialTicks) {
        super.applyRotations(entityLiving, ageInTicks, rotationYaw, partialTicks);
    }

    @Override
    protected void preRenderCallback(EntityPrehistoricFloraKokomopterus entity, float f) {
        float scale = entity.getAgeScale();
        GlStateManager.scale(scale, scale, scale);
        this.shadowSize = entity.width * scale * 0.45F;
    }

}