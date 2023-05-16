package net.lepidodendron.entity.render.entity;

import net.lepidodendron.LepidodendronMod;
import net.lepidodendron.entity.EntityPrehistoricFloraHeterosteus;
import net.lepidodendron.entity.model.entity.ModelHeterosteus;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderHeterosteus extends RenderLiving<EntityPrehistoricFloraHeterosteus> {
    public static final ResourceLocation TEXTURE = new ResourceLocation(LepidodendronMod.MODID + ":textures/entities/heterosteus.png");
    private static final ResourceLocation TEXTURE_BABY = new ResourceLocation(LepidodendronMod.MODID + ":textures/entities/heterosteus_baby.png");
    public static final ModelBase MODEL_BASE = new ModelHeterosteus();

    public static float getScaler() {return 1.25F;}
    public RenderHeterosteus(RenderManager mgr) {
        super(mgr, MODEL_BASE, 1.0f);
    }

    @Override
    public ResourceLocation getEntityTexture(EntityPrehistoricFloraHeterosteus entity) {
        float scale = entity.getAgeScale();
        //System.err.println("AgeScale: " + scale);
        if (entity.isSmall()) {
            return RenderHeterosteus.TEXTURE_BABY;
        }
        return RenderHeterosteus.TEXTURE;
    }

    @Override
    protected void applyRotations(EntityPrehistoricFloraHeterosteus entityLiving, float ageInTicks, float rotationYaw, float partialTicks) {
        super.applyRotations(entityLiving, ageInTicks, rotationYaw, partialTicks);
    }

    @Override
    protected void preRenderCallback(EntityPrehistoricFloraHeterosteus entity, float f) {
        float scale = entity.getAgeScale()*getScaler();
        if (scale < 0.1f) {scale = 0.1f;}
        GlStateManager.scale(scale, scale, scale);
        this.shadowSize = entity.width * scale * 0.45F;
    }

}