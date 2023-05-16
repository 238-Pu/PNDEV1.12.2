package net.lepidodendron.entity.render.entity;

import net.lepidodendron.LepidodendronMod;
import net.lepidodendron.entity.EntityPrehistoricFloraJellyfish2;
import net.lepidodendron.entity.model.entity.ModelJellyfish;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderJellyfish2 extends RenderLiving<EntityPrehistoricFloraJellyfish2> {
    public static final ResourceLocation TEXTURE = new ResourceLocation(LepidodendronMod.MODID + ":textures/entities/jellyfish_2.png");
    public static final ModelBase MODEL_BASE = new ModelJellyfish();

    public RenderJellyfish2(RenderManager mgr) {
        super(mgr, MODEL_BASE, 0.0f);
    }

    @Override
    public ResourceLocation getEntityTexture(EntityPrehistoricFloraJellyfish2 entity) {
        return RenderJellyfish2.TEXTURE;
    }

    @Override
    protected void applyRotations(EntityPrehistoricFloraJellyfish2 entityLiving, float ageInTicks, float rotationYaw, float partialTicks) {
        super.applyRotations(entityLiving, ageInTicks, rotationYaw, partialTicks);
    }

}