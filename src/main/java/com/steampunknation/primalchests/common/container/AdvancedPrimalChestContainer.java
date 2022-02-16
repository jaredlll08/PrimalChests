package com.steampunknation.primalchests.common.container;

import com.steampunknation.primalchests.common.blocks.BlockRegister;
import com.steampunknation.primalchests.common.blocks.tileentities.AdvancedPrimalChestTileEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IWorldPosCallable;

import java.util.Objects;

public class AdvancedPrimalChestContainer extends Container {
    public final AdvancedPrimalChestTileEntity te;
    private final IWorldPosCallable canInteractWithCallable;

    public AdvancedPrimalChestContainer(final int windowId, final PlayerInventory playerInv, final AdvancedPrimalChestTileEntity te) {
        super(ContainerTypes.ADVANCED_PRIMAL_CHEST_CONTAINER.get(), windowId);
        this.te = te;
        this.canInteractWithCallable = IWorldPosCallable.create(te.getLevel(), te.getBlockPos());

        //Tile entity slots
        for (int col = 0; col < 9; col++) {
            this.addSlot(new Slot((IInventory) te, col, 8 + col * 18, 18));
        }
        for (int col = 0; col < 5; col++) {
            this.addSlot(new Slot((IInventory) te, col + 9, 44 + col * 18, 36));
        }

        //Player Inventory
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 9; col++) {
                this.addSlot(new Slot(playerInv, col + row * 9 + 9, 8 + col * 18, 166 - (4 - row) * 18 - 10));
            }
        }

        //Player hot bar
        for (int col = 0; col < 9; col++) {
            this.addSlot(new Slot(playerInv, col, 8 + col * 18, 142));
        }
    }

    public AdvancedPrimalChestContainer(final int windowId, final PlayerInventory playerInv, final PacketBuffer data) {
        this(windowId, playerInv, getTileEntity(playerInv, data));
    }

    private static AdvancedPrimalChestTileEntity getTileEntity(final PlayerInventory playerInv, final PacketBuffer data) {
        Objects.requireNonNull(playerInv, "Player Inventory cannot be null.");
        Objects.requireNonNull(data, "Packet Buffer cannot be null.");
        final TileEntity te = playerInv.player.getCommandSenderWorld().getBlockEntity(data.readBlockPos());
        if (te instanceof AdvancedPrimalChestTileEntity) {
            return (AdvancedPrimalChestTileEntity) te;
        }
        throw new IllegalStateException("Tile Entity is not correct");
    }

    @Override
    public boolean stillValid(PlayerEntity p_75145_1_) {
        return stillValid(canInteractWithCallable, p_75145_1_, BlockRegister.ADVANCED_PRIMAL_CHEST.get());
    }

    @Override
    public ItemStack quickMoveStack(PlayerEntity playerIn, int index) {
        ItemStack stack = ItemStack.EMPTY;
        Slot slot = this.slots.get(index);
        if (slot != null && slot.hasItem()) {
            ItemStack stack1 = slot.getItem();
            stack = stack1.copy();
            if (index < AdvancedPrimalChestTileEntity.slots
                    && !this.moveItemStackTo(stack1, AdvancedPrimalChestTileEntity.slots, this.slots.size(), true)) {
                return ItemStack.EMPTY;
            }
            if (!this.moveItemStackTo(stack1, 0, AdvancedPrimalChestTileEntity.slots, false)) {
                return ItemStack.EMPTY;
            }
            if (stack1.isEmpty()) {
                slot.set(ItemStack.EMPTY);
            } else {
                slot.setChanged();
            }
        }
        return stack;
    }
}

