package com.steampunknation.primalchests.client.gui.screen;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import com.steampunknation.primalchests.PrimalChests;
import com.steampunknation.primalchests.common.container.AdvancedPrimalChestContainer;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class AdvancedPrimalChestScreen extends ContainerScreen<AdvancedPrimalChestContainer> {

    private static final ResourceLocation ADVANCED_PRIMAL_CHEST_GUI = new ResourceLocation(PrimalChests.MOD_ID,
            "textures/gui/advanced_primal_chest.png");

    public AdvancedPrimalChestScreen(AdvancedPrimalChestContainer screenContainer, PlayerInventory inv, ITextComponent titleIn) {
        super(screenContainer, inv, titleIn);

        this.leftPos = 0;
        this.topPos = 0;
        this.imageWidth = 176;
        this.imageHeight = 166;
    }

    @Override
    public void render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(matrixStack);
        super.render(matrixStack, mouseX, mouseY, partialTicks);
        this.renderTooltip(matrixStack, mouseX, mouseY);
    }

    @Override
    protected void renderLabels(MatrixStack matrixStack, int x, int y) {
        this.font.draw(matrixStack, this.inventory.getDisplayName(),
                (float)this.inventoryLabelX, (float)this.inventoryLabelY, 4210752);
        this.font.draw(matrixStack, this.title,
                (float) this.inventoryLabelX, (float) this.inventoryLabelY - 64, 4210752);

    }

    @Override
    protected void renderBg(MatrixStack matrixStack, float partialTicks, int mouseX, int mouseY) {
        RenderSystem.color4f(1f,1f,1f,1f);
        this.minecraft.textureManager.bind(ADVANCED_PRIMAL_CHEST_GUI);
        int x = (this.width - this.imageWidth) / 2;
        int y = (this.height - this.imageHeight) / 2;
        this.blit(matrixStack, x, y, 0,0, this.imageWidth, this.imageHeight);
    }

}
