package com.steampunknation.primalchests.common.blocks.tileentities;

import com.steampunknation.primalchests.common.blocks.BlockRegister;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import static com.steampunknation.primalchests.PrimalChests.MOD_ID;

public class TileEntityRegister {

    public static final DeferredRegister<TileEntityType<?>> TILE_ENTITY = DeferredRegister
            .create(ForgeRegistries.TILE_ENTITIES, MOD_ID);

    //Primal Chest
    public static final RegistryObject<TileEntityType<PrimalChestTileEntity>> PRIMAL_CHEST_TILE_ENTITY = TILE_ENTITY
            .register("primal_chest",
                    () -> TileEntityType.Builder.of(PrimalChestTileEntity::new, BlockRegister.PRIMAL_CHEST.get()).build(null));
    //Advanced Primal Chest
    public static final RegistryObject<TileEntityType<AdvancedPrimalChestTileEntity>> ADVANCED_PRIMAL_CHEST_TILE_ENTITY = TILE_ENTITY
            .register("advanced_primal_chest",
                    () -> TileEntityType.Builder.of(AdvancedPrimalChestTileEntity::new, BlockRegister.ADVANCED_PRIMAL_CHEST.get()).build(null));

}
