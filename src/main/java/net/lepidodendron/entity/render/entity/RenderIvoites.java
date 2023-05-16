package net.lepidodendron.entity.render.entity;

import net.lepidodendron.LepidodendronMod;
import net.lepidodendron.entity.EntityPrehistoricFloraIvoites;
import net.lepidodendron.entity.model.entity.ModelIvoites;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderIvoites extends RenderLiving<EntityPrehistoricFloraIvoites> {
    public static final ResourceLocation TEXTURE = new ResourceLocation(LepidodendronMod.MODID + ":textures/entities/ivoites.png");
    public static final ModelBase MODEL_BASE = new ModelIvoites();

    public RenderIvoites(RenderManager mgr) {
        super(mgr, MODEL_BASE, 0.1f);
    }

    public static float getScaler() {
        return 0.12F;
    }

    @Override
    public ResourceLocation getEntityTexture(EntityPrehistoricFloraIvoites entity) {
        return RenderIvoites.TEXTURE;
    }

    @Override
    protected void applyRotations(EntityPrehistoricFloraIvoites entityLiving, float ageInTicks, float rotationYaw, float partialTicks) {
        super.applyRotations(entityLiving, ageInTicks, rotationYaw, partialTicks);
    }

    @Override
    protected void preRenderCallback(EntityPrehistoricFloraIvoites entity, float f) {
        float scale = entity.getAgeScale() * getScaler();
        GlStateManager.scale(scale, scale, scale);
        this.shadowSize = entity.width * scale * 0.45F;
    }

}