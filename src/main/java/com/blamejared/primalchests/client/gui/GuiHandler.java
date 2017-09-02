package com.blamejared.primalchests.client.gui;

import com.blamejared.primalchests.PrimalChests;
import com.blamejared.primalchests.client.gui.advanced.*;
import com.blamejared.primalchests.tileentities.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.*;

/**
 * Created by Jared.
 */
public class GuiHandler implements IGuiHandler {
    
    public GuiHandler() {
        NetworkRegistry.INSTANCE.registerGuiHandler(PrimalChests.INSTANCE, this);
    }
    
    
    @Override
    public Object getServerGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {
        BlockPos pos = new BlockPos(x, y, z);
        TileEntity te = world.getTileEntity(pos);
        if(te == null) {
            return null;
        }
        switch(id) {
            case 0:
                if(te instanceof TileEntityPrimalChest)
                    return new ContainerPrimalChest(player.inventory, (TileEntityPrimalChest) te);
                break;
            case 1:
                if(te instanceof TileEntityPrimalChestAdvanced)
                    return new ContainerPrimalChestAdvanced(player.inventory, (TileEntityPrimalChestAdvanced) te);
                break;
    
        }
        return null;
    }
    
    @Override
    public Object getClientGuiElement(int id, EntityPlayer player, World world, int x, int y, final int z) {
        BlockPos pos = new BlockPos(x, y, z);
        TileEntity te = world.getTileEntity(pos);
        if(te == null) {
            return null;
        }
        switch(id) {
            case 0:
                if(te instanceof TileEntityPrimalChest)
                    return new GuiPrimalChest(player.inventory, (TileEntityPrimalChest) te);
                break;
            case 1:
                if(te instanceof TileEntityPrimalChestAdvanced)
                    return new GuiPrimalChestAdvanced(player.inventory, (TileEntityPrimalChestAdvanced) te);
                break;
        
        }
        return null;
    }
    
}
