package com.blamejared.primalchests.client.gui;

import com.blamejared.primalchests.reference.Reference;
import com.blamejared.primalchests.tileentities.TileEntityPrimalChest;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.*;

@SideOnly(Side.CLIENT)
public class GuiPrimalChest extends GuiContainer {
    
    /**
     * The ResourceLocation containing the chest GUI texture.
     */
    private static final ResourceLocation CHEST_GUI_TEXTURE = new ResourceLocation(Reference.MOD_ID, "textures/guis/primal_chest.png");
    
    
    private TileEntityPrimalChest tile;
    
    private InventoryPlayer player;
    
    public GuiPrimalChest(InventoryPlayer invPlayer, TileEntityPrimalChest tile2) {
        super(new ContainerPrimalChest(invPlayer, tile2));
        this.tile = tile2;
        this.player = invPlayer;
    }
    
    /**
     * Draw the foreground layer for the GuiContainer (everything in front of the items)
     */
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        this.fontRenderer.drawString("Primal Chest", 8, 6, 4210752);
        this.fontRenderer.drawString(player.getDisplayName().getUnformattedText(), 8, this.ySize - 96 + 2, 4210752);
    }
    
    /**
     * Draws the background layer of this container (behind the items).
     */
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(CHEST_GUI_TEXTURE);
        int i = (this.width - this.xSize) / 2;
        int j = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(i, j, 0, 0, this.xSize, 166);
    }
}