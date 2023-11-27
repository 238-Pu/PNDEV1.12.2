package net.lepidodendron.entity.render.tile;

import net.lepidodendron.ClientProxyLepidodendronMod;
import net.lepidodendron.LepidodendronConfig;
import net.lepidodendron.block.BlockDisplayWallMount;
import net.lepidodendron.item.ItemTaxidermyDisplayItem;
import net.minecraft.block.BlockDirectional;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.client.ForgeHooksClient;
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import org.lwjgl.opengl.GL11;

import javax.annotation.Nullable;
import java.lang.reflect.Method;

public class RenderDisplayWallMount extends TileEntitySpecialRenderer<BlockDisplayWallMount.TileEntityDisplayWallMount> {

    public static final float scaler = 6.0F;
    private static final Class[] clazzArr = new Class[]{};
    private static final Object[] objArr = new Object[]{};
    private static final Class[] params = new Class[1];

    public static final PropertyDirection FACING = BlockDirectional.FACING;

    public RenderDisplayWallMount() {
    }

    public void setRotations(EnumFacing facing, double x, double y, double z, double voffset, double offset, double hoffset, float currentRotation) {
        if (facing == EnumFacing.UP || facing == EnumFacing.DOWN) {
            GlStateManager.translate(x + 0.5, y + 0.5, z + 0.5);
            GlStateManager.rotate(currentRotation, 0F, 1F, 0F);
        }
        else if (facing == EnumFacing.NORTH)
        {
            GlStateManager.translate(x + 0.5 - hoffset, y + 0.5 + voffset, z + (1 - offset));
            GlStateManager.rotate(180, 0F, 0F, 1F);
            GlStateManager.rotate(currentRotation, 0F, 0F, 1F);
        }
        else if (facing == EnumFacing.SOUTH)
        {
            GlStateManager.translate(x + 0.5 + hoffset, y + 0.5 + voffset, z + offset);
            GlStateManager.rotate(180, 0F, 0F, 1F);
            GlStateManager.rotate(180, 0F, 1F, 0F);
            GlStateManager.rotate(currentRotation, 0F, 0F, 1F);
        }
        else if (facing == EnumFacing.WEST)
        {
            GlStateManager.translate(x + (1 - offset), y + 0.5 + voffset, z + 0.5 - hoffset);
            GlStateManager.rotate(180, 0F, 0F, 1F);
            GlStateManager.rotate(270, 0F, 1F, 0F);
            GlStateManager.rotate(currentRotation, 0F, 0F, 1F);
        }
        else if (facing == EnumFacing.EAST)
        {
            GlStateManager.translate(x + offset, y + 0.5 + voffset, z + 0.5 + hoffset);
            GlStateManager.rotate(180, 0F, 0F, 1F);
            GlStateManager.rotate(90, 0F, 1F, 0F);
            GlStateManager.rotate(currentRotation, 0F, 0F, 1F);
        }
    }

    @Override
    public void render(BlockDisplayWallMount.TileEntityDisplayWallMount entity, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {
        BlockPos pos = entity.getPos();
        World world = entity.getWorld();
        EnumFacing facing;
        params[0] = String.class;
        boolean itemRender  = true;
        int currentRotation = 0;
        String PNVariant = null;

        if (entity != null && entity.hasWorld()) {
            currentRotation = entity.getTileData().getInteger("rotation");
            facing = entity.getWorld().getBlockState(entity.getPos()).getValue(FACING);

            TileEntity te = world.getTileEntity(pos);
            if (te != null) {
                if (te instanceof BlockDisplayWallMount.TileEntityDisplayWallMount) {
                    BlockDisplayWallMount.TileEntityDisplayWallMount tee = (BlockDisplayWallMount.TileEntityDisplayWallMount) te;
                    ItemStack itemstack = tee.getStackInSlot(0);
                    if (itemstack.isEmpty()) {
                        return;
                    }

                    GlStateManager.pushMatrix();
                    GlStateManager.disableCull();
                    GlStateManager.enableRescaleNormal();
                    GlStateManager.enableAlpha();

                    if (itemstack.getItem() == ItemTaxidermyDisplayItem.block) {
                        Class classEntity = getEntityFromNBT(itemstack);
                        if (classEntity != null) {
                            itemRender = false;
                            double offsetWall = 0;
                            double upperfrontverticallinedepth = 0;
                            double upperbackverticallinedepth = 0;
                            double upperfrontlineoffset = 0;
                            double upperfrontlineoffsetperpendiular = 0;
                            double upperbacklineoffset = 0;
                            double upperbacklineoffsetperpendiular = 0;
                            double lowerfrontverticallinedepth = 0;
                            double lowerbackverticallinedepth = 0;
                            double lowerfrontlineoffset = 0;
                            double lowerfrontlineoffsetperpendiular = 0;
                            double lowerbacklineoffset = 0;
                            double lowerbacklineoffsetperpendiular = 0F;
                            ResourceLocation textureDisplay = null;
                            ResourceLocation textureDisplayTransparent = null;
                            ModelBase modelDisplay = null;
                            float getScaler = 0;

                            //Is there a variant included?
                            if (itemstack.hasTagCompound()) {
                                if (itemstack.getTagCompound().hasKey("PNVariant")) {
                                    PNVariant = itemstack.getTagCompound().getString("PNVariant");
                                }
                            }

                            Method method = testAndGetMethod(classEntity, "offsetWall", params);
                            if (method != null) {
                                try {
                                    offsetWall = (double) method.invoke(null, PNVariant);
                                } catch (Exception e) {
                                    itemRender = true;
                                }
                            } else {
                                itemRender = true;
                            }
                            method = testAndGetMethod(classEntity, "upperfrontverticallinedepth", params);
                            if (method != null) {
                                try {
                                    upperfrontverticallinedepth = (double) method.invoke(null, PNVariant);
                                } catch (Exception e) {
                                    itemRender = true;
                                }
                            } else {
                                itemRender = true;
                            }
                            method = testAndGetMethod(classEntity, "upperbackverticallinedepth", params);
                            if (method != null) {
                                try {
                                    upperbackverticallinedepth = (double) method.invoke(null, PNVariant);
                                } catch (Exception e) {
                                    itemRender = true;
                                }
                            } else {
                                itemRender = true;
                            }
                            method = testAndGetMethod(classEntity, "upperfrontlineoffset", params);
                            if (method != null) {
                                try {
                                    upperfrontlineoffset = (double) method.invoke(null, PNVariant);
                                } catch (Exception e) {
                                    itemRender = true;
                                }
                            } else {
                                itemRender = true;
                            }
                            method = testAndGetMethod(classEntity, "upperfrontlineoffsetperpendiular", params);
                            if (method != null) {
                                try {
                                    upperfrontlineoffsetperpendiular = (double) method.invoke(null, PNVariant);
                                } catch (Exception e) {
                                    itemRender = true;
                                }
                            } else {
                                itemRender = true;
                            }
                            method = testAndGetMethod(classEntity, "upperbacklineoffset", params);
                            if (method != null) {
                                try {
                                    upperbacklineoffset = (double) method.invoke(null, PNVariant);
                                } catch (Exception e) {
                                    itemRender = true;
                                }
                            } else {
                                itemRender = true;
                            }
                            method = testAndGetMethod(classEntity, "upperbacklineoffsetperpendiular", params);
                            if (method != null) {
                                try {
                                    upperbacklineoffsetperpendiular = (double) method.invoke(null, PNVariant);
                                } catch (Exception e) {
                                    itemRender = true;
                                }
                            } else {
                                itemRender = true;
                            }
                            method = testAndGetMethod(classEntity, "lowerfrontverticallinedepth", params);
                            if (method != null) {
                                try {
                                    lowerfrontverticallinedepth = (double) method.invoke(null, PNVariant);
                                } catch (Exception e) {
                                    itemRender = true;
                                }
                            } else {
                                itemRender = true;
                            }
                            method = testAndGetMethod(classEntity, "lowerbackverticallinedepth", params);
                            if (method != null) {
                                try {
                                    lowerbackverticallinedepth = (double) method.invoke(null, PNVariant);
                                } catch (Exception e) {
                                    itemRender = true;
                                }
                            } else {
                                itemRender = true;
                            }
                            method = testAndGetMethod(classEntity, "lowerfrontlineoffset", params);
                            if (method != null) {
                                try {
                                    lowerfrontlineoffset = (double) method.invoke(null, PNVariant);
                                } catch (Exception e) {
                                    itemRender = true;
                                }
                            } else {
                                itemRender = true;
                            }
                            method = testAndGetMethod(classEntity, "lowerfrontlineoffsetperpendiular", params);
                            if (method != null) {
                                try {
                                    lowerfrontlineoffsetperpendiular = (double) method.invoke(null, PNVariant);
                                } catch (Exception e) {
                                    itemRender = true;
                                }
                            } else {
                                itemRender = true;
                            }
                            method = testAndGetMethod(classEntity, "lowerbacklineoffset", params);
                            if (method != null) {
                                try {
                                    lowerbacklineoffset = (double) method.invoke(null, PNVariant);
                                } catch (Exception e) {
                                    itemRender = true;
                                }
                            } else {
                                itemRender = true;
                            }
                            method = testAndGetMethod(classEntity, "lowerbacklineoffsetperpendiular", params);
                            if (method != null) {
                                try {
                                    lowerbacklineoffsetperpendiular = (double) method.invoke(null, PNVariant);
                                } catch (Exception e) {
                                    itemRender = true;
                                }
                            } else {
                                itemRender = true;
                            }
                            method = testAndGetMethod(classEntity, "textureDisplay", params);
                            if (method != null) {
                                try {
                                    textureDisplay = (ResourceLocation) method.invoke(null, PNVariant);
                                } catch (Exception e) {
                                    itemRender = true;
                                }
                            } else {
                                itemRender = true;
                            }
                            method = testAndGetMethod(classEntity, "textureDisplayTransparent", params);
                            if (method != null) {
                                try {
                                    textureDisplayTransparent = (ResourceLocation) method.invoke(null, PNVariant);
                                } catch (Exception e) {
                                    //Do not worry if there is no transparent layer
                                }
                            } else {
                                //Do not worry if there is no transparent layer
                            }
                            method = testAndGetMethod(classEntity, "modelDisplay", params);
                            if (method != null) {
                                try {
                                    modelDisplay = (ModelBase) method.invoke(null, PNVariant);
                                } catch (Exception e) {
                                    itemRender = true;
                                }
                            } else {
                                itemRender = true;
                            }
                            method = testAndGetMethod(classEntity, "getScaler", params);
                            if (method != null) {
                                try {
                                    getScaler = (float) method.invoke(null, PNVariant);
                                } catch (Exception e) {
                                    itemRender = true;
                                }
                            } else {
                                itemRender = true;
                            }


                            if (!itemRender) {
                                try {
                                    itemRender = !renderTaxidermy(facing, (float) x, (float) y, (float) z, currentRotation,
                                        textureDisplay, getScaler, modelDisplay,
                                        offsetWall, 0, 0,
                                        upperfrontverticallinedepth, upperbackverticallinedepth, upperfrontlineoffset, upperfrontlineoffsetperpendiular, upperbacklineoffset, upperbacklineoffsetperpendiular,
                                        lowerfrontverticallinedepth, lowerbackverticallinedepth, lowerfrontlineoffset, lowerfrontlineoffsetperpendiular, lowerbacklineoffset, lowerbacklineoffsetperpendiular,
                                        false);
                                } catch (Exception e) {
                                    itemRender = true;
                                }
                                if (textureDisplayTransparent != null) {
                                    try { //Add in any transaprency
                                        itemRender = itemRender || !renderTaxidermy(facing, (float) x, (float) y, (float) z, currentRotation,
                                            textureDisplay, getScaler, modelDisplay,
                                            offsetWall, 0, 0,
                                            upperfrontverticallinedepth, upperbackverticallinedepth, upperfrontlineoffset, upperfrontlineoffsetperpendiular, upperbacklineoffset, upperbacklineoffsetperpendiular,
                                            lowerfrontverticallinedepth, lowerbackverticallinedepth, lowerfrontlineoffset, lowerfrontlineoffsetperpendiular, lowerbacklineoffset, lowerbacklineoffsetperpendiular,
                                            true);
                                    } catch (Exception e) {
                                        //Ignore this if there are no transparent parts
                                    }
                                }
                            }

                        }
                        else {
                            itemRender = true;
                        }
                    }


                    // ********************************************************************
                    if (itemRender) { //standard items
                        itemRender = true;

                        GlStateManager.alphaFunc(516, 0.1F);
                        GlStateManager.enableBlend();
                        RenderHelper.enableStandardItemLighting();
                        GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
                        //GlStateManager.pushMatrix();

                        float scale = 0.6666F;
                        if (facing == EnumFacing.UP) {
                            GlStateManager.translate(x + 0.5, y + 0.02, z + 0.5);
                            GlStateManager.scale(scale, scale, scale);
                        }
                        if (facing == EnumFacing.DOWN) {
                            GlStateManager.translate(x + 0.5, y + 0.98, z + 0.5);
                            GlStateManager.scale(scale, scale, scale);
                        }
                        if (facing == EnumFacing.NORTH) {
                            GlStateManager.translate(x + 0.5, y + 0.5, z + 0.98);
                            GlStateManager.scale(scale, scale, scale);
                        }
                        if (facing == EnumFacing.SOUTH) {
                            GlStateManager.translate(x + 0.5, y + 0.5, z + 0.02);
                            GlStateManager.scale(scale, scale, scale);
                        }
                        if (facing == EnumFacing.WEST) {
                            GlStateManager.translate(x + 0.98, y + 0.5, z + 0.5);
                            GlStateManager.scale(scale, scale, scale);
                            GlStateManager.rotate(90, 0F, 1F, 0F);
                        }
                        if (facing == EnumFacing.EAST) {
                            GlStateManager.translate(x + 0.02, y + 0.5, z + 0.5);
                            GlStateManager.scale(scale, scale, scale);
                            GlStateManager.rotate(90, 0F, 1F, 0F);
                        }
                        GlStateManager.rotate(currentRotation, 0F, 0F, 1F);
                        IBakedModel model = Minecraft.getMinecraft().getRenderItem().getItemModelWithOverrides(itemstack, world, null);
                        //model = ForgeHooksClient.handleCameraTransforms(model, ItemCameraTransforms.TransformType.FIXED, false);
                        model = ForgeHooksClient.handleCameraTransforms(model, ItemCameraTransforms.TransformType.GROUND, false);
                        Minecraft.getMinecraft().getTextureManager().bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
                        Minecraft.getMinecraft().getRenderItem().renderItem(itemstack, model);

                    }

                    if (itemRender) {
                        GlStateManager.disableRescaleNormal();
                        GlStateManager.disableAlpha();
                        GlStateManager.disableBlend();
                        GlStateManager.enableCull();
                        GlStateManager.disableRescaleNormal();
                        RenderHelper.disableStandardItemLighting();
                        GlStateManager.popMatrix();
                    } else {
                        GlStateManager.disableAlpha();
                        GlStateManager.disableRescaleNormal();
                        GlStateManager.enableCull();
                        GlStateManager.popMatrix();
                    }

                }
            }
        }
    }

    @Nullable
    public Class getEntityFromNBT(ItemStack stack) {
        if (!stack.hasTagCompound()) {
            return null;
        }
        if (!stack.getTagCompound().hasKey("PFMob")) {
            return null;
        }
        Class classOut = null;
        NBTTagCompound blockNBT = (NBTTagCompound) stack.getTagCompound().getTag("PFMob");
        String stringDNA = (blockNBT.getString("id"));
        if (stringDNA != null) {
            //int ii = stringDNA.indexOf("@");
            if (stringDNA.indexOf("@") >= 1) {
                stringDNA = stringDNA.substring(0, stringDNA.indexOf("@"));
            }
            classOut = findEntity(stringDNA);
        }
        return classOut;
    }

    @Nullable
    private static Class<? extends Entity> findEntity(String entity) {
        Class<? extends Entity> entityClass;
        EntityEntry ee = ForgeRegistries.ENTITIES.getValue(new ResourceLocation(entity));
        entityClass = ee == null ? null : ee.getEntityClass();
        if (entityClass == null) {
            return null;
        }
        return entityClass;
    }

    public boolean renderTaxidermy(EnumFacing facing, float x, float y, float z,
           float currentRotation,
           ResourceLocation TEXTURE,
           float scalerModel,
           ModelBase model,
           double offset,
           double voffset,
           double hoffset,
           double upperfrontverticallinedepth,
           double upperbackverticallinedepth,
           double upperfrontlineoffset,
           double upperfrontlineoffsetperpendiular,
           double upperbacklineoffset,
           double upperbacklineoffsetperpendiular,
           double lowerfrontverticallinedepth,
           double lowerbackverticallinedepth,
           double lowerfrontlineoffset,
           double lowerfrontlineoffsetperpendiular,
           double lowerbacklineoffset,
           double lowerbacklineoffsetperpendiular,
           boolean transparent
    ) {

        //net.minecraft.util.math.Vec3d cameraPos = rendererDispatcher.entity.getPositionVector();
        float renderLimit = LepidodendronConfig.taxidermyRenderRange;
        if (renderLimit < 16) {
            renderLimit = 16;
        }
        if (renderLimit > 254) {
            renderLimit = 254;
        }
        if ((Math.pow(x, 2) + Math.pow(y, 2) + Math.pow(z, 2)) > Math.pow(renderLimit, 2)) {
            return false;
        }

        boolean flag1 = true;
        boolean flag2 = true;
        boolean flag3 = true;

        if (transparent) {
            GlStateManager.pushMatrix();
            GlStateManager.color(1.0F, 1.0F, 1.0F, 0.9F);
            GlStateManager.enableNormalize();
            GlStateManager.enableBlend();
            GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
        }

        if (facing == EnumFacing.DOWN) {
            Method renderMethod = testAndGetMethod(model.getClass(), "renderStaticSuspended", new Class[]{float.class});
            if (renderMethod != null) {
                if (!transparent) {
                    if (upperfrontverticallinedepth > 0) {
                        GL11.glPushMatrix();
                        GL11.glLineWidth(5);
                        GL11.glDisable(GL11.GL_TEXTURE_2D);
                        GL11.glColor3ub((byte) 128, (byte) 128, (byte) 128);
                        GL11.glBegin(GL11.GL_LINES);
                        float xoffsetter = (float) ((upperfrontlineoffset * Math.cos(Math.toRadians(-currentRotation))) + (upperfrontlineoffsetperpendiular * Math.sin(Math.toRadians(currentRotation))));
                        float zoffsetter = (float) ((upperfrontlineoffset * Math.sin(Math.toRadians(-currentRotation))) + (upperfrontlineoffsetperpendiular * Math.cos(Math.toRadians(currentRotation))));
                        GL11.glVertex3f((float) x + 0.5F + xoffsetter, (float) y + 1, (float) z + 0.5F + zoffsetter);
                        GL11.glVertex3f((float) x + 0.5F + xoffsetter, (float) y + 1 - (float) upperfrontverticallinedepth, (float) z + 0.5F + zoffsetter);
                        GL11.glEnd();
                        GL11.glEnable(GL11.GL_TEXTURE_2D);
                        GL11.glPopMatrix();
                    }

                    if (upperbackverticallinedepth > 0) {
                        GL11.glPushMatrix();
                        GL11.glLineWidth(5);
                        GL11.glDisable(GL11.GL_TEXTURE_2D);
                        GL11.glColor3ub((byte) 128, (byte) 128, (byte) 128);
                        GL11.glBegin(GL11.GL_LINES);
                        float xoffsetter = (float) ((upperbacklineoffset * Math.cos(Math.toRadians(-currentRotation))) - (upperbacklineoffsetperpendiular * Math.sin(Math.toRadians(currentRotation))));
                        float zoffsetter = (float) ((upperbacklineoffset * Math.sin(Math.toRadians(-currentRotation))) - (upperbacklineoffsetperpendiular * Math.cos(Math.toRadians(currentRotation))));
                        GL11.glVertex3f((float) x + 0.5F - (float) xoffsetter, (float) y + 1, (float) z + 0.5F - zoffsetter);
                        GL11.glVertex3f((float) x + 0.5F - (float) xoffsetter, (float) y + 1 - (float) upperbackverticallinedepth, (float) z + 0.5F - zoffsetter);
                        GL11.glEnd();
                        GL11.glEnable(GL11.GL_TEXTURE_2D);
                        GL11.glPopMatrix();
                    }

                    setRotations(facing, x, y, z, voffset, 0, 0, currentRotation);
                    GlStateManager.rotate(180, 0, 0, 1);
                    GlStateManager.scale(this.scaler * scalerModel, this.scaler * scalerModel, this.scaler * scalerModel);
                    GlStateManager.enableLighting();
                    GL11.glColor3ub((byte) 255, (byte) 255, (byte) 255);
                }
                this.bindTexture(TEXTURE);
                if (renderMethod != null) {
                    try {
                        renderMethod.invoke(model, Minecraft.getMinecraft().player.ticksExisted);
                    } catch (Exception e) {
                        flag1 = false;
                    }
                    flag2 = true;
                } else {
                    flag3 = false;
                }
            }
            else {
                flag3 = false;
            }
        } else if (facing == EnumFacing.UP) {
            if (!transparent) {
                //Arrow to show location:
                if (ClientProxyLepidodendronMod.keyB.isKeyDown()) {
                    GL11.glPushMatrix();
                    GL11.glLineWidth(12);
                    GL11.glDisable(GL11.GL_TEXTURE_2D);
                    GL11.glColor3ub((byte) 255, (byte) 0, (byte) 0);
                    GL11.glBegin(GL11.GL_LINES);
                    GL11.glVertex3f((float) x + 0.5F, (float) y + 0.125F, (float) z + 0.5F);
                    GL11.glVertex3f((float) x + 0.5F, (float) y + 1.5F, (float) z + 0.5F);
                    GL11.glEnd();
                    GL11.glBegin(GL11.GL_LINES);
                    GL11.glVertex3f((float) x + 0.5F, (float) y + 0.125F, (float) z + 0.5F);
                    GL11.glVertex3f((float) x + 0.5F + 0.5F, (float) y + 0.5F, (float) z + 0.5F);
                    GL11.glEnd();
                    GL11.glBegin(GL11.GL_LINES);
                    GL11.glVertex3f((float) x + 0.5F, (float) y + 0.125F, (float) z + 0.5F);
                    GL11.glVertex3f((float) x + 0.5F - 0.5F, (float) y + 0.5F, (float) z + 0.5F);
                    GL11.glEnd();
                    GL11.glEnable(GL11.GL_TEXTURE_2D);
                    GL11.glPopMatrix();
                }
            }
            Method renderMethod = testAndGetMethod(model.getClass(), "renderStaticFloor", new Class[]{float.class});
            if (renderMethod != null) {
                if (!transparent) {
                    if (lowerfrontverticallinedepth > 0) {
                        GL11.glPushMatrix();
                        GL11.glLineWidth(5);
                        GL11.glDisable(GL11.GL_TEXTURE_2D);
                        GL11.glColor3ub((byte) 128, (byte) 128, (byte) 128);
                        GL11.glBegin(GL11.GL_LINES);
                        float xoffsetter = (float) ((lowerfrontlineoffset * Math.cos(Math.toRadians(-currentRotation))) + (lowerfrontlineoffsetperpendiular * Math.sin(Math.toRadians(currentRotation))));
                        float zoffsetter = (float) ((lowerfrontlineoffset * Math.sin(Math.toRadians(-currentRotation))) + (lowerfrontlineoffsetperpendiular * Math.cos(Math.toRadians(currentRotation))));
                        GL11.glVertex3f((float) x + 0.5F + (float) xoffsetter, (float) y, (float) z + 0.5F + zoffsetter);
                        GL11.glVertex3f((float) x + 0.5F + (float) xoffsetter, (float) y + (float) lowerfrontverticallinedepth, (float) z + 0.5F + zoffsetter);
                        GL11.glEnd();
                        GL11.glEnable(GL11.GL_TEXTURE_2D);
                        GL11.glPopMatrix();
                    }

                    if (lowerbackverticallinedepth > 0) {
                        GL11.glPushMatrix();
                        GL11.glLineWidth(5);
                        GL11.glDisable(GL11.GL_TEXTURE_2D);
                        GL11.glColor3ub((byte) 128, (byte) 128, (byte) 128);
                        GL11.glBegin(GL11.GL_LINES);
                        float xoffsetter = (float) ((lowerbacklineoffset * Math.cos(Math.toRadians(-currentRotation))) - (lowerbacklineoffsetperpendiular * Math.sin(Math.toRadians(currentRotation))));
                        float zoffsetter = (float) ((lowerbacklineoffset * Math.sin(Math.toRadians(-currentRotation))) - (lowerbacklineoffsetperpendiular * Math.cos(Math.toRadians(currentRotation))));
                        GL11.glVertex3f((float) x + 0.5F - (float) xoffsetter, (float) y, (float) z + 0.5F - zoffsetter);
                        GL11.glVertex3f((float) x + 0.5F - (float) xoffsetter, (float) y + (float) lowerbackverticallinedepth, (float) z + 0.5F - zoffsetter);
                        GL11.glEnd();
                        GL11.glEnable(GL11.GL_TEXTURE_2D);
                        GL11.glPopMatrix();
                    }

                    setRotations(facing, x, y, z, voffset, 0, 0, currentRotation);
                    GlStateManager.rotate(180, 0, 0, 1);
                    GlStateManager.scale(this.scaler * scalerModel, this.scaler * scalerModel, this.scaler * scalerModel);
                    GlStateManager.enableLighting();
                    GL11.glColor3ub((byte) 255, (byte) 255, (byte) 255);
                }
                this.bindTexture(TEXTURE);
                if (renderMethod != null) {
                    try {
                        renderMethod.invoke(model, Minecraft.getMinecraft().player.ticksExisted);
                    } catch (Exception e) {
                        flag1 = false;
                    }
                    flag2 = true;
                } else {
                    flag3 = false;
                }
            }
            else {
                flag3 = false;
            }
        }
        else if (facing == EnumFacing.NORTH || facing == EnumFacing.SOUTH || facing == EnumFacing.EAST || facing == EnumFacing.WEST) {
            Method renderMethod = testAndGetMethod(model.getClass(), "renderStaticWall", new Class[]{float.class});
            if (renderMethod != null) {
                if (!transparent) {
                    setRotations(facing, x, y, z, voffset, offset, hoffset, currentRotation);
                    //GlStateManager.rotate(90, 0, 0, 1);
                    GlStateManager.scale(this.scaler * scalerModel, this.scaler * scalerModel, this.scaler * scalerModel);
                    GlStateManager.enableLighting();
                    GL11.glColor3ub((byte) 255, (byte) 255, (byte) 255);
                }
                this.bindTexture(TEXTURE);
                try {
                    renderMethod.invoke(model, Minecraft.getMinecraft().player.ticksExisted);
                } catch (Exception e) {
                    flag1 = false;
                }
                flag2 = true;
            } else {
                flag3 = false;
            }
        }
        if (transparent) {
            GlStateManager.disableBlend();
            GlStateManager.disableNormalize();
            GlStateManager.popMatrix();
        }
        return flag1 && flag2 && flag3;
    }

    @Nullable
    public static Method testAndGetMethod(Class clazz, String methodname, Class[] params) {
        Method methodToFind = null;
        try {
            methodToFind = clazz.getMethod(methodname, params);
        } catch (NoSuchMethodException | SecurityException e) {
        }
        return methodToFind;
    }
}