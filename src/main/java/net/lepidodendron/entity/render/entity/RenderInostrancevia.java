package net.lepidodendron.entity.render.entity;

import net.lepidodendron.LepidodendronMod;
import net.lepidodendron.entity.EntityPrehistoricFloraInostrancevia;
import net.lepidodendron.entity.model.entity.ModelInostrancevia;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderInostrancevia extends RenderLiving<EntityPrehistoricFloraInostrancevia> {
    public static final ResourceLocation TEXTURE = new ResourceLocation(LepidodendronMod.MODID + ":textures/entities/inostrancevia.png");
    public static final ModelBase MODEL_BASE = new ModelInostrancevia();

    public RenderInostrancevia(RenderManager mgr) {
        super(mgr, MODEL_BASE, 0.5f);
    }

    public static float getScaler() {return 0.775f; }

    @Override
    public ResourceLocation getEntityTexture(EntityPrehistoricFloraInostrancevia entity) {
        return RenderInostrancevia.TEXTURE;
    }

    @Override
    protected void applyRotations(EntityPrehistoricFloraInostrancevia entityLiving, float ageInTicks, float rotationYaw, float partialTicks) {
        super.applyRotations(entityLiving, ageInTicks, rotationYaw, partialTicks);
    }

    @Override
    protected void preRenderCallback(EntityPrehistoricFloraInostrancevia entity, float f) {
        float scale = entity.getAgeScale() * this.getScaler();
        GlStateManager.scale(scale, scale, scale);
        this.shadowSize = entity.width * scale * 0.45F;
    }

}