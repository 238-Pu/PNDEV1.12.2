package net.lepidodendron.entity.render.entity;

import net.lepidodendron.LepidodendronMod;
import net.lepidodendron.entity.EntityPrehistoricFloraPotanichthys;
import net.lepidodendron.entity.model.entity.ModelPotanichthys;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderPotanichthys extends RenderLiving<EntityPrehistoricFloraPotanichthys> {
    public static final ResourceLocation TEXTURE = new ResourceLocation(LepidodendronMod.MODID + ":textures/entities/potanichthys.png");
    public static final ModelBase MODEL_BASE = new ModelPotanichthys();

    public RenderPotanichthys(RenderManager mgr) {
        super(mgr, MODEL_BASE, 0.0f);
    }

    @Override
    public ResourceLocation getEntityTexture(EntityPrehistoricFloraPotanichthys entity) {
        return RenderPotanichthys.TEXTURE;
    }

    @Override
    protected void applyRotations(EntityPrehistoricFloraPotanichthys entityLiving, float ageInTicks, float rotationYaw, float partialTicks) {
        super.applyRotations(entityLiving, ageInTicks, rotationYaw, partialTicks);
    }

}




