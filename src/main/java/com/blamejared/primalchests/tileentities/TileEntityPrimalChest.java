package com.blamejared.primalchests.tileentities;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.items.*;

import javax.annotation.Nullable;

public class TileEntityPrimalChest extends TileEntity {
    
    public ItemStackHandler itemStackHandler = new ItemStackHandler(9);
    
    public EnumFacing facing = EnumFacing.NORTH;
    
    public TileEntityPrimalChest() {
    }
    
    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        compound.setTag("items", itemStackHandler.serializeNBT());
        compound.setString("facing", facing.name());
        return super.writeToNBT(compound);
    }
    
    
    @Override
    public void readFromNBT(NBTTagCompound compound) {
        if(itemStackHandler == null) {
            itemStackHandler = new ItemStackHandler();
        }
        itemStackHandler.deserializeNBT((NBTTagCompound) compound.getTag("items"));
        this.facing = EnumFacing.valueOf(compound.getString("facing"));
        super.readFromNBT(compound);
    }
    
    @Override
    public NBTTagCompound getUpdateTag() {
        return writeToNBT(new NBTTagCompound());
    }
    
    @Override
    public void handleUpdateTag(NBTTagCompound tag) {
        readFromNBT(tag);
    }
    
    @Override
    public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt) {
        super.onDataPacket(net, pkt);
        readFromNBT(pkt.getNbtCompound());
    }
    
    @Nullable
    @Override
    public SPacketUpdateTileEntity getUpdatePacket() {
        return new SPacketUpdateTileEntity(getPos(), 0, writeToNBT(new NBTTagCompound()));
    }
    
    @Nullable
    @Override
    public <T> T getCapability(Capability<T> capability, @Nullable EnumFacing facing) {
        if(capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            return (T) itemStackHandler;
        }
        return super.getCapability(capability, facing);
    }
    
    @Override
    public boolean hasCapability(Capability<?> capability, @Nullable EnumFacing facing) {
        return capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY || super.hasCapability(capability, facing);
    }
    
    public EnumFacing getFacing() {
        return facing;
    }
    
    public void setFacing(EnumFacing facing) {
        this.facing = facing;
    }
    
}
