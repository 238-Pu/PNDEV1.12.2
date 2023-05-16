package net.lepidodendron.entity.render.entity;

import net.lepidodendron.LepidodendronMod;
import net.lepidodendron.entity.EntityPrehistoricFloraSpriggina;
import net.lepidodendron.entity.model.entity.ModelSpriggina;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderSpriggina extends RenderLiving<EntityPrehistoricFloraSpriggina> {
    public static final ResourceLocation TEXTURE = new ResourceLocation(LepidodendronMod.MODID + ":textures/entities/spriggina.png");
    public static final ModelBase MODEL_BASE = new ModelSpriggina();

    public RenderSpriggina(RenderManager mgr) {
        super(mgr, MODEL_BASE, 0.0f);
    }

    @Override
    public ResourceLocation getEntityTexture(EntityPrehistoricFloraSpriggina entity) {
        return RenderSpriggina.TEXTURE;
    }

    @Override
    protected void applyRotations(EntityPrehistoricFloraSpriggina entityLiving, float ageInTicks, float rotationYaw, float partialTicks) {
        super.applyRotations(entityLiving, ageInTicks, rotationYaw, partialTicks);
    }

}