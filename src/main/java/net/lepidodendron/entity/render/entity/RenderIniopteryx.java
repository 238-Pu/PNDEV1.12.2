package net.lepidodendron.entity.render.entity;

import net.lepidodendron.LepidodendronMod;
import net.lepidodendron.entity.EntityPrehistoricFloraIniopteryx;
import net.lepidodendron.entity.model.entity.ModelIniopteryx;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderIniopteryx extends RenderLiving<EntityPrehistoricFloraIniopteryx> {
    public static final ResourceLocation TEXTURE = new ResourceLocation(LepidodendronMod.MODID + ":textures/entities/iniopteryx.png");
    public static final ModelBase MODEL_BASE = new ModelIniopteryx();

    public RenderIniopteryx(RenderManager mgr) {
        super(mgr, MODEL_BASE, 0.0f);
    }

    @Override
    public ResourceLocation getEntityTexture(EntityPrehistoricFloraIniopteryx entity) {
        return RenderIniopteryx.TEXTURE;
    }

    @Override
    protected void applyRotations(EntityPrehistoricFloraIniopteryx entityLiving, float ageInTicks, float rotationYaw, float partialTicks) {
        super.applyRotations(entityLiving, ageInTicks, rotationYaw, partialTicks);
    }

}