package com.blamejared.primalchests.common.blocks;

import com.blamejared.primalchests.common.items.ModItemGroup;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import static com.blamejared.primalchests.PrimalChests.MOD_ID;

public class BlockRegister {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MOD_ID);
    public static DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MOD_ID);


    //Blocks
    public static final RegistryObject<Block> PRIMAL_CHEST = BLOCKS.register("primal_chest", PrimalChestBlock::new);
    public static final RegistryObject<Block> ADVANCED_PRIMAL_CHEST = BLOCKS.register("advanced_primal_chest", AdvancedPrimalChestBlock::new);


    //Block Items
    //Primal Chest
    public static final RegistryObject<Item> PRIMAL_CHEST_ITEM = ITEMS.register("primal_chest",
            () -> new BlockItem(PRIMAL_CHEST.get(), new Item.Properties().tab(ModItemGroup.PRIMAL_CHESTS_GROUP)));
    //Adv Primal Chest
    public static final RegistryObject<Item> ADVANCED_PRIMAL_CHEST_ITEM = ITEMS.register("advanced_primal_chest",
            () -> new BlockItem(ADVANCED_PRIMAL_CHEST.get(), new Item.Properties().tab(ModItemGroup.PRIMAL_CHESTS_GROUP)));
}
