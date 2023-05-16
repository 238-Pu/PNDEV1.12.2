package net.lepidodendron.entity.render.entity;

import net.lepidodendron.LepidodendronMod;
import net.lepidodendron.entity.EntityPrehistoricFloraEbenaqua;
import net.lepidodendron.entity.model.entity.ModelEbenaqua;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderEbenaqua extends RenderLiving<EntityPrehistoricFloraEbenaqua> {
    public static final ResourceLocation TEXTURE = new ResourceLocation(LepidodendronMod.MODID + ":textures/entities/ebenaqua.png");
    public static final ModelBase MODEL_BASE = new ModelEbenaqua();

    public RenderEbenaqua(RenderManager mgr) {
        super(mgr, MODEL_BASE, 0.0f);
    }

    @Override
    public ResourceLocation getEntityTexture(EntityPrehistoricFloraEbenaqua entity) {
        return RenderEbenaqua.TEXTURE;
    }

    @Override
    protected void applyRotations(EntityPrehistoricFloraEbenaqua entityLiving, float ageInTicks, float rotationYaw, float partialTicks) {
        super.applyRotations(entityLiving, ageInTicks, rotationYaw, partialTicks);
    }

}