package com.blamejared.primalchests.client.gui.advanced;

import com.blamejared.primalchests.tileentities.TileEntityPrimalChestAdvanced;
import net.minecraft.entity.player.*;
import net.minecraft.inventory.*;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.SlotItemHandler;

public class ContainerPrimalChestAdvanced extends Container {
    
    public TileEntityPrimalChestAdvanced tile;
    
    public ContainerPrimalChestAdvanced(InventoryPlayer invPlayer, TileEntityPrimalChestAdvanced tile) {
        this.tile = tile;
        for(int i = 0; i < 9; i++) {
            addSlotToContainer(new SlotItemHandler(tile.itemStackHandler, i, 8 + 18 * i, 18));
        }
        for(int i = 9; i < tile.itemStackHandler.getSlots(); i++) {
            addSlotToContainer(new SlotItemHandler(tile.itemStackHandler, i, 8 + 18 * (i - 9) + 36, 36));
        }
        
        for(int x = 0; x < 9; x++) {
            addSlotToContainer(new Slot(invPlayer, x, 8 + 18 * x, 142));
        }
        
        for(int y = 0; y < 3; y++) {
            for(int x = 0; x < 9; x++) {
                addSlotToContainer(new Slot(invPlayer, x + y * 9 + 9, 8 + 18 * x, 84 + y * 18));
            }
        }
        
    }
    
    
    /**
     * Determines whether supplied player can use this container
     */
    public boolean canInteractWith(EntityPlayer player) {
        if(player.world.getTileEntity(tile.getPos()) != tile) {
            return false;
        } else {
            return player.getDistanceSq((double) tile.getPos().getX() + 0.5D, (double) tile.getPos().getY() + 0.5D, (double) tile.getPos().getZ() + 0.5D) <= 64.0D;
        }
    }
    
    /**
     * Handle when the stack in slot {@code index} is shift-clicked. Normally this moves the stack between the player
     * inventory and the other inventory(s).
     *
     * @param playerIn Player that interacted with this {@code Container}.
     * @param index    Index of the {@link Slot}. This index is relative to the list of slots in this {@code Container},
     *                 {@link #inventorySlots}.
     */
    public ItemStack transferStackInSlot(EntityPlayer playerIn, int index) {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = (Slot) this.inventorySlots.get(index);
        
        if(slot != null && slot.getHasStack()) {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();
            
            if(index < tile.itemStackHandler.getSlots()) {
                if(!this.mergeItemStack(itemstack1, tile.itemStackHandler.getSlots(), this.inventorySlots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else if(!this.mergeItemStack(itemstack1, 0, tile.itemStackHandler.getSlots(), false)) {
                return ItemStack.EMPTY;
            }
            
            if(itemstack1.isEmpty()) {
                slot.putStack(ItemStack.EMPTY);
            } else {
                slot.onSlotChanged();
            }
        }
        
        return itemstack;
    }
    
    /**
     * Called when the container is closed.
     */
    public void onContainerClosed(EntityPlayer playerIn) {
        super.onContainerClosed(playerIn);
    }
    
}