package net.lepidodendron.entity.render.entity;

import net.lepidodendron.LepidodendronMod;
import net.lepidodendron.entity.EntityPrehistoricFloraFadeniaPermotriassic;
import net.lepidodendron.entity.model.entity.ModelFadenia;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderFadeniaPermotriassic extends RenderLiving<EntityPrehistoricFloraFadeniaPermotriassic> {
    public static final ResourceLocation TEXTURE = new ResourceLocation(LepidodendronMod.MODID + ":textures/entities/fadenia_permotriassic.png");
    public static final ModelBase MODEL_BASE = new ModelFadenia();

    public RenderFadeniaPermotriassic(RenderManager mgr) {
        super(mgr, MODEL_BASE, 0.5f);
    }

    public static float getScaler() {
        return 0.27F;
    }

    @Override
    public ResourceLocation getEntityTexture(EntityPrehistoricFloraFadeniaPermotriassic entity) {
        return RenderFadeniaPermotriassic.TEXTURE;
    }

    @Override
    protected void applyRotations(EntityPrehistoricFloraFadeniaPermotriassic entityLiving, float ageInTicks, float rotationYaw, float partialTicks) {
        super.applyRotations(entityLiving, ageInTicks, rotationYaw, partialTicks);
    }

    @Override
    protected void preRenderCallback(EntityPrehistoricFloraFadeniaPermotriassic entity, float f) {
        float scale = entity.getAgeScale() * this.getScaler();
        if (scale < 0.1f) {scale = 0.1f;}
        GlStateManager.scale(scale, scale, scale);
        this.shadowSize = entity.width * scale * 0.75F;
    }

}