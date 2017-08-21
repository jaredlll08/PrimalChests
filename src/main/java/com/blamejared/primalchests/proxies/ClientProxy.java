package com.blamejared.primalchests.proxies;

import com.blamejared.primalchests.PrimalChests;
import com.blamejared.primalchests.blocks.PBlocks;
import com.blamejared.primalchests.client.gui.GuiHandler;
import com.blamejared.primalchests.client.render.*;
import com.blamejared.primalchests.tileentities.*;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.client.registry.ClientRegistry;

public class ClientProxy extends CommonProxy {
    
    @Override
    public void registerRenders() {
        super.registerRenders();
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityPrimalChest.class, new RenderTileEntityPrimalChest());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityPrimalChestAdvanced.class, new RenderTileEntityPrimalChestAdvanced());
    }
    
    @Override
    public void registerGuis() {
        super.registerGuis();
        new GuiHandler();
    }
}
