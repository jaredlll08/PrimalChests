package com.blamejared.primalchests.client.render;

import com.blamejared.primalchests.reference.Reference;
import com.blamejared.primalchests.tileentities.*;
import net.minecraft.client.model.ModelChest;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.util.*;

import java.util.Random;

public class RenderTileEntityPrimalChestAdvanced extends TileEntitySpecialRenderer<TileEntityPrimalChestAdvanced> {
    
    private Random random;
    
    private ModelChest model;
    
    private static float halfPI = (float) (Math.PI / 2D);
    
    public RenderTileEntityPrimalChestAdvanced() {
        this.model = new ModelChest();
        this.random = new Random();
    }
    
    @Override
    public void render(TileEntityPrimalChestAdvanced te, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {
        if(te == null || te.isInvalid()) {
            return;
        }
    
        EnumFacing facing = EnumFacing.SOUTH;
    
        if(te.hasWorld()) {
            facing = te.getFacing();
        }
    
        if(destroyStage >= 0) {
            this.bindTexture(DESTROY_STAGES[destroyStage]);
            GlStateManager.matrixMode(5890);
            GlStateManager.pushMatrix();
            GlStateManager.scale(4F, 4F, 1F);
            GlStateManager.translate(0.0625F, 0.0625F, 0.0625F);
            GlStateManager.matrixMode(5888);
        } else {
            this.bindTexture(new ResourceLocation(Reference.MOD_ID, "textures/models/primal_chest_advanced.png"));
        }
    
        GlStateManager.pushMatrix();
        GlStateManager.color(1F, 1F, 1F, 1F);
        GlStateManager.translate((float) x, (float) y + 1F, (float) z + 1F);
        GlStateManager.scale(1F, -1F, -1F);
        GlStateManager.translate(0.5F, 0.5F, 0.5F);
    
        switch(facing) {
            case NORTH: {
                GlStateManager.rotate(180F, 0F, 1F, 0F);
                break;
            }
            case SOUTH: {
                GlStateManager.rotate(0F, 0F, 1F, 0F);
                break;
            }
            case WEST: {
                GlStateManager.rotate(90F, 0F, 1F, 0F);
                break;
            }
            case EAST: {
                GlStateManager.rotate(270F, 0F, 1F, 0F);
                break;
            }
            default: {
                GlStateManager.rotate(0F, 0F, 1F, 0F);
                break;
            }
        }
    
        GlStateManager.translate(-0.5F, -0.5F, -0.5F);
    
        //        float lidangle = te.prevLidAngle + (te.lidAngle - te.prevLidAngle) * partialTicks;
        //
        //        lidangle = 1F - lidangle;
        //        lidangle = 1F - lidangle * lidangle * lidangle;
        //
        //        this.model.chestLid.rotateAngleX = -lidangle * halfPI;
        // Render the chest itself
        this.model.renderAll();
    
        if(destroyStage >= 0) {
            GlStateManager.matrixMode(5890);
            GlStateManager.popMatrix();
            GlStateManager.matrixMode(5888);
        }
    
    
        GlStateManager.popMatrix();
        GlStateManager.color(1F, 1F, 1F, 1F);
    }
}
