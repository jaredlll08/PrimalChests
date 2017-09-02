package com.blamejared.primalchests.blocks;

import com.blamejared.primalchests.PrimalChests;
import com.blamejared.primalchests.client.gui.*;
import com.blamejared.primalchests.client.gui.advanced.*;
import com.blamejared.primalchests.tileentities.*;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.gui.Gui;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.*;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.*;
import net.minecraft.util.math.*;
import net.minecraft.world.*;
import net.minecraftforge.fml.relauncher.*;

import javax.annotation.Nullable;

public class BlockPrimalChestAdvanced extends Block {
    
    public BlockPrimalChestAdvanced() {
        super(Material.WOOD);
        setUnlocalizedName("primal_chest_advanced");
        setCreativeTab(CreativeTabs.DECORATIONS);
        setHardness(2.5F);
        setSoundType(SoundType.WOOD);
    }
    
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
        return new AxisAlignedBB(0.0625D, 0.0D, 0.0625D, 0.9375D, 0.875D, 0.9375D);
    }
    
    @Override
    public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
        
        if(!worldIn.isRemote) {
            TileEntityPrimalChestAdvanced tile = (TileEntityPrimalChestAdvanced) worldIn.getTileEntity(pos);
            if(tile == null) {
                return;
            }
            for(int i = 0; i < tile.itemStackHandler.getSlots(); i++) {
                InventoryHelper.spawnItemStack(worldIn, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, tile.itemStackHandler.getStackInSlot(i));
            }
        }
        super.breakBlock(worldIn, pos, state);
    }
    
    /**
     * Used to determine ambient occlusion and culling when rebuilding chunks for render
     */
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }
    
    public boolean isFullCube(IBlockState state) {
        return false;
    }
    
    @SideOnly(Side.CLIENT)
    public boolean hasCustomBreakingProgress(IBlockState state) {
        return true;
    }
    
    @Override
    public boolean hasTileEntity(IBlockState state) {
        return true;
    }
    
    @Override
    public EnumBlockRenderType getRenderType(IBlockState state) {
        return EnumBlockRenderType.ENTITYBLOCK_ANIMATED;
    }
    
    @Nullable
    @Override
    public TileEntity createTileEntity(World world, IBlockState state) {
        return new TileEntityPrimalChestAdvanced();
    }
    
    
    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if(!worldIn.isRemote) {
            playerIn.openGui(PrimalChests.INSTANCE, 1, worldIn, pos.getX(), pos.getY(), pos.getZ());
        }
        return true;
    }
    
    @Override
    public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
        super.onBlockPlacedBy(worldIn, pos, state, placer, stack);
        TileEntityPrimalChestAdvanced tile = (TileEntityPrimalChestAdvanced) worldIn.getTileEntity(pos);
        tile.facing = placer.getHorizontalFacing().getOpposite();
    }
    
}
