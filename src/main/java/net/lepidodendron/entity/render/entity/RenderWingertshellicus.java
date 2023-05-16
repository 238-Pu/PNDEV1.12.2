package net.lepidodendron.entity.render.entity;

import net.lepidodendron.LepidodendronMod;
import net.lepidodendron.entity.EntityPrehistoricFloraWingertshellicus;
import net.lepidodendron.entity.model.entity.ModelWingertshellicus;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderWingertshellicus extends RenderLiving<EntityPrehistoricFloraWingertshellicus> {
    public static final ResourceLocation TEXTURE = new ResourceLocation(LepidodendronMod.MODID + ":textures/entities/wingertshellicus.png");
    public static final ModelBase MODEL_BASE = new ModelWingertshellicus();

    public RenderWingertshellicus(RenderManager mgr) {
        super(mgr, MODEL_BASE, 0.0f);
    }

    @Override
    public ResourceLocation getEntityTexture(EntityPrehistoricFloraWingertshellicus entity) {
        return RenderWingertshellicus.TEXTURE;
    }

    @Override
    protected void applyRotations(EntityPrehistoricFloraWingertshellicus entityLiving, float ageInTicks, float rotationYaw, float partialTicks) {
        super.applyRotations(entityLiving, ageInTicks, rotationYaw, partialTicks);
    }
    @Override
    protected void preRenderCallback(EntityPrehistoricFloraWingertshellicus entity, float f) {
        float scale = 0.7F;
        GlStateManager.scale(scale, scale, scale);
        this.shadowSize = 0;
    }

}