package net.lepidodendron.entity.render.entity;

import net.lepidodendron.LepidodendronMod;
import net.lepidodendron.entity.EntityPrehistoricFloraPanguraptor;
import net.lepidodendron.entity.model.entity.ModelPanguraptor;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderPanguraptor extends RenderLiving<EntityPrehistoricFloraPanguraptor> {
    public static final ResourceLocation TEXTURE = new ResourceLocation(LepidodendronMod.MODID + ":textures/entities/panguraptor.png");
    public static final ModelBase MODEL_BASE = new ModelPanguraptor();

    public RenderPanguraptor(RenderManager mgr) {
        super(mgr, MODEL_BASE, 0.5f);
    }

    public static float getScaler() {return 0.495f *0.7F; }

    @Override
    public ResourceLocation getEntityTexture(EntityPrehistoricFloraPanguraptor entity) {
        return RenderPanguraptor.TEXTURE;
    }

    @Override
    protected void applyRotations(EntityPrehistoricFloraPanguraptor entityLiving, float ageInTicks, float rotationYaw, float partialTicks) {
        super.applyRotations(entityLiving, ageInTicks, rotationYaw, partialTicks);
    }

    @Override
    protected void preRenderCallback(EntityPrehistoricFloraPanguraptor entity, float f) {
        float scale = entity.getAgeScale() * this.getScaler();
        GlStateManager.scale(scale, scale, scale);
        this.shadowSize = entity.width * scale * 0.38F;
    }

}