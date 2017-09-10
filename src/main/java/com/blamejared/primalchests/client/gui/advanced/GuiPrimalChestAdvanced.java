package com.blamejared.primalchests.client.gui.advanced;

import com.blamejared.primalchests.reference.Reference;
import com.blamejared.primalchests.tileentities.TileEntityPrimalChestAdvanced;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.*;

@SideOnly(Side.CLIENT)
public class GuiPrimalChestAdvanced extends GuiContainer {
    
    /**
     * The ResourceLocation containing the chest GUI texture.
     */
    private static final ResourceLocation CHEST_GUI_TEXTURE = new ResourceLocation(Reference.MOD_ID, "textures/guis/primal_chest_advanced.png");
    
    
    private TileEntityPrimalChestAdvanced tile;
    
    private InventoryPlayer player;
    
    public GuiPrimalChestAdvanced(InventoryPlayer invPlayer, TileEntityPrimalChestAdvanced tile2) {
        super(new ContainerPrimalChestAdvanced(invPlayer, tile2));
        this.tile = tile2;
        this.player = invPlayer;
    }
    
    /**
     * Draw the foreground layer for the GuiContainer (everything in front of the items)
     */
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        super.drawGuiContainerForegroundLayer(mouseX, mouseY);
        this.fontRenderer.drawString("Advanced Primal Chest", 8, 6, 4210752);
        this.fontRenderer.drawString(player.getDisplayName().getUnformattedText(), 8, this.ySize - 96 + 2, 4210752);
    }
    /**
     * Draws the screen and all the components in it.
     */
    public void drawScreen(int mouseX, int mouseY, float partialTicks)
    {
        this.drawDefaultBackground();
        super.drawScreen(mouseX, mouseY, partialTicks);
        this.renderHoveredToolTip(mouseX, mouseY);
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