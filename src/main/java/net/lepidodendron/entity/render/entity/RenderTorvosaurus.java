package net.lepidodendron.entity.render.entity;

import net.lepidodendron.LepidodendronMod;
import net.lepidodendron.entity.EntityPrehistoricFloraTorvosaurus;
import net.lepidodendron.entity.model.entity.ModelTorvosaurus;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderTorvosaurus extends RenderLiving<EntityPrehistoricFloraTorvosaurus> {
    public static final ResourceLocation TEXTURE = new ResourceLocation(LepidodendronMod.MODID + ":textures/entities/torvosaurus.png");

    public RenderTorvosaurus(RenderManager mgr) {
        super(mgr, new ModelTorvosaurus(), 0.3f);
    }

    public static float getScaler() {
        return 1.05F;
    }

    @Override
    public ResourceLocation getEntityTexture(EntityPrehistoricFloraTorvosaurus entity) {
        return RenderTorvosaurus.TEXTURE;
    }

    @Override
    protected void applyRotations(EntityPrehistoricFloraTorvosaurus entityLiving, float ageInTicks, float rotationYaw, float partialTicks) {
        super.applyRotations(entityLiving, ageInTicks, rotationYaw, partialTicks);
    }

    @Override
    protected void preRenderCallback(EntityPrehistoricFloraTorvosaurus entity, float f) {
        float scale = entity.getAgeScale() * this.getScaler();
        GlStateManager.scale(scale, scale, scale);
        this.shadowSize = entity.width * scale * 0.35F;
    }

}