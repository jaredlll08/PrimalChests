package com.steampunknation.primalchests.common.blocks;

import com.steampunknation.primalchests.common.blocks.tileentities.PrimalChestTileEntity;
import com.steampunknation.primalchests.common.blocks.tileentities.TileEntityRegister;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.IBooleanFunction;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.fml.network.NetworkHooks;

import javax.annotation.Nullable;
import java.util.stream.Stream;

public class PrimalChestBlock extends HorizontalBlock implements IWaterLoggable{

    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

    public PrimalChestBlock() {
        super(AbstractBlock.Properties
                .of(Material.WOOD)
                .sound(SoundType.WOOD)
                .harvestLevel(1)
                .harvestTool(ToolType.AXE)
                .strength(2f));
    }

    //Make collision match model (North, West, South, East)
    //North
    private static final VoxelShape SHAPE_N = Stream.of(
            Block.box(1, 0, 1, 15, 10, 15),
            Block.box(1, 9, 1, 15, 14, 15)
    ).reduce((v1, v2) -> VoxelShapes.join(v1, v2, IBooleanFunction.OR)).get();
    //West
    private static final VoxelShape SHAPE_W = Stream.of(
            Block.box(1, 0, 1, 15, 10, 15),
            Block.box(1, 9, 1, 15, 14, 15)
    ).reduce((v1, v2) -> VoxelShapes.join(v1, v2, IBooleanFunction.OR)).get();
    //South
    private static final VoxelShape SHAPE_S = Stream.of(
            Block.box(1, 0, 1, 15, 10, 15),
            Block.box(1, 9, 1, 15, 14, 15)
    ).reduce((v1, v2) -> VoxelShapes.join(v1, v2, IBooleanFunction.OR)).get();
    //East
    private static final VoxelShape SHAPE_E = Stream.of(
            Block.box(1, 0, 1, 15, 10, 15),
            Block.box(1, 9, 1, 15, 14, 15)
    ).reduce((v1, v2) -> VoxelShapes.join(v1, v2, IBooleanFunction.OR)).get();

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        switch (state.getValue(FACING)) {
            case NORTH:
                return SHAPE_N;
            case WEST:
                return SHAPE_W;
            case SOUTH:
                return SHAPE_S;
            case EAST:
                return SHAPE_E;
            default:
                return SHAPE_N;
        }
    }

    @Override
    protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(FACING, WATERLOGGED);
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        FluidState iFluidstate = context.getLevel().getFluidState(context.getClickedPos());
        return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite()).setValue(WATERLOGGED, iFluidstate.getType() == Fluids.WATER);
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return TileEntityRegister.PRIMAL_CHEST_TILE_ENTITY.get().create();
    }

    @Override
    public void onRemove(BlockState state, World worldIn, BlockPos pos, BlockState p_196243_4_, boolean p_196243_5_) {
        if (!state.is(p_196243_4_.getBlock())) {
            TileEntity tileentity = worldIn.getBlockEntity(pos);
            if (tileentity instanceof IInventory) {
                InventoryHelper.dropContents(worldIn, pos, (IInventory) tileentity);
                worldIn.updateNeighbourForOutputSignal(pos, this);
            }

            super.onRemove(state, worldIn, pos, p_196243_4_, p_196243_5_);
        }
    }

    @Override
    public ActionResultType use(BlockState state, World worldIn, BlockPos pos, PlayerEntity player,
                                Hand handIn, BlockRayTraceResult hit) {
        if (!worldIn.isClientSide()) {
            TileEntity te = worldIn.getBlockEntity(pos);
                if (te instanceof PrimalChestTileEntity) {
                    NetworkHooks.openGui((ServerPlayerEntity) player, (PrimalChestTileEntity) te, te.getBlockPos());

                    //Play sound on open
                    worldIn.playSound(null, pos, SoundEvents.WOODEN_DOOR_OPEN,
                            SoundCategory.BLOCKS, 1, 1);

                }
                else
                {
                    throw new IllegalStateException("Container provider is missing!");
                }
        }
        return ActionResultType.SUCCESS;

    }

    @Override
    public FluidState getFluidState(BlockState state) {
        return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
    }

    @Override
    public BlockState updateShape(BlockState state, Direction facing, BlockState facingState, IWorld worldIn, BlockPos currentPos, BlockPos facingPos) {
        if (state.hasProperty(WATERLOGGED)){
            worldIn.getLiquidTicks().scheduleTick(currentPos, Fluids.WATER, Fluids.WATER.getTickDelay(worldIn));
        }

        return super.updateShape(state, facing, facingState, worldIn, currentPos, facingPos);
    }

}
