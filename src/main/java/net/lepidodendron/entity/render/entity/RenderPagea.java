package net.lepidodendron.entity.render.entity;

import net.lepidodendron.LepidodendronMod;
import net.lepidodendron.entity.EntityPrehistoricFloraPagea;
import net.lepidodendron.entity.model.entity.ModelPagea;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderPagea extends RenderLiving<EntityPrehistoricFloraPagea> {
    public static final ResourceLocation TEXTURE = new ResourceLocation(LepidodendronMod.MODID + ":textures/entities/pagea.png");
    private static final ResourceLocation TEXTURE_YOUNG = new ResourceLocation(LepidodendronMod.MODID + ":textures/entities/pagea_young.png");
    private static final ResourceLocation TEXTURE_BABY = new ResourceLocation(LepidodendronMod.MODID + ":textures/entities/pagea_baby.png");
    public static final ModelBase MODEL_BASE = new ModelPagea();

    public RenderPagea(RenderManager mgr) {
        super(mgr, MODEL_BASE, 0.3f);
    }

    @Override
    public ResourceLocation getEntityTexture(EntityPrehistoricFloraPagea entity) {
        float scale = entity.getAgeScale();
        //System.err.println("AgeScale: " + scale);
        if (entity.isSmall()) {
            return RenderPagea.TEXTURE_BABY;
        }
        else if (scale < 0.7F) {
            return RenderPagea.TEXTURE_YOUNG;
        }
        return RenderPagea.TEXTURE;
    }

    @Override
    protected void applyRotations(EntityPrehistoricFloraPagea entityLiving, float ageInTicks, float rotationYaw, float partialTicks) {
        super.applyRotations(entityLiving, ageInTicks, rotationYaw, partialTicks);
    }

    @Override
    protected void preRenderCallback(EntityPrehistoricFloraPagea entity, float f) {
        float scale = entity.getAgeScale();
        GlStateManager.scale(scale, scale, scale);
        this.shadowSize = entity.width * scale * 0.45F;
    }

}