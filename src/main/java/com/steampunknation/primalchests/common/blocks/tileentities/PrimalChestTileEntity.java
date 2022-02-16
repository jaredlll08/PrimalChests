package com.steampunknation.primalchests.common.blocks.tileentities;

import com.steampunknation.primalchests.PrimalChests;
import com.steampunknation.primalchests.common.container.PrimalChestContainer;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.LockableLootTileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;

public class PrimalChestTileEntity extends LockableLootTileEntity {

    public static int slots = 9;

    protected NonNullList<ItemStack> items = NonNullList.withSize(slots, ItemStack.EMPTY);

    protected PrimalChestTileEntity(TileEntityType<?> typeIn){
        super(typeIn);
    }

    @Override
    protected ITextComponent getDefaultName() {
        return new TranslationTextComponent("container." + PrimalChests.MOD_ID + ".primal_chest");
    }

    @Override
    protected Container createMenu(int id, PlayerInventory player) {
        return new PrimalChestContainer(id, player, this);
    }

    @Override
    protected NonNullList<ItemStack> getItems() {
        return this.items;
    }

    @Override
    protected void setItems(NonNullList<ItemStack> p_199721_1_) {
        this.items = p_199721_1_;
    }

    public PrimalChestTileEntity(){
        this(TileEntityRegister.PRIMAL_CHEST_TILE_ENTITY.get());
    }

    @Override
    public int getContainerSize() {
        return slots;
    }

    @Override
    public CompoundNBT save(CompoundNBT compound){
        super.save(compound);
        if(!this.trySaveLootTable(compound)){
            ItemStackHelper.saveAllItems(compound, this.items);
        }
        return compound;
    }

    @Override
    public void load(BlockState state, CompoundNBT nbt){
        super.load(state, nbt);
        this.items = NonNullList.withSize(getContainerSize(), ItemStack.EMPTY);
        if(!this.tryLoadLootTable(nbt)){
            ItemStackHelper.loadAllItems(nbt, this.items);
        }
    }

}
