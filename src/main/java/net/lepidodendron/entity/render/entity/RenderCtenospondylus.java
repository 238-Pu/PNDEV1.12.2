package net.lepidodendron.entity.render.entity;

import net.lepidodendron.LepidodendronMod;
import net.lepidodendron.entity.EntityPrehistoricFloraCtenospondylus;
import net.lepidodendron.entity.model.entity.ModelCtenospondylus;
import net.lepidodendron.entity.render.RenderLivingBaseWithBook;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderCtenospondylus extends RenderLivingBaseWithBook<EntityPrehistoricFloraCtenospondylus> {
    public static final ResourceLocation TEXTURE = new ResourceLocation(LepidodendronMod.MODID + ":textures/entities/ctenospondylus.png");

    public static float getScaler() {return 0.55f; }

    public RenderCtenospondylus(RenderManager mgr) {
        super(mgr, new ModelCtenospondylus(), 0.5f);
    }

    @Override
    public ResourceLocation getEntityTexture(EntityPrehistoricFloraCtenospondylus entity) {
        return RenderCtenospondylus.TEXTURE;
    }

    @Override
    protected void applyRotations(EntityPrehistoricFloraCtenospondylus entityLiving, float ageInTicks, float rotationYaw, float partialTicks) {
        super.applyRotations(entityLiving, ageInTicks, rotationYaw, partialTicks);
    }

    @Override
    protected void preRenderCallback(EntityPrehistoricFloraCtenospondylus entity, float f) {
        float scale = entity.getAgeScale() * this.getScaler();
        GlStateManager.scale(scale, scale, scale);
        this.shadowSize = entity.width * scale * 0.350F;
    }

}