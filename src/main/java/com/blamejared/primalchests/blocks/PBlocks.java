package com.blamejared.primalchests.blocks;

import com.blamejared.primalchests.reference.Reference;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.*;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.*;

import static com.blamejared.primalchests.reference.Reference.MOD_ID;

public class PBlocks {
    
    public static Map<String, Block> blocks = new LinkedHashMap<>();
    
    public static BlockPrimalChest PRIMAL_CHEST = new BlockPrimalChest();
    public static BlockPrimalChestAdvanced PRIMAL_CHEST_ADVANCED = new BlockPrimalChestAdvanced();
    
    public static void preInit() {
        register(PRIMAL_CHEST, "primal_chest");
        register(PRIMAL_CHEST_ADVANCED, "primal_chest_advanced");
    }
    
    private static void register(Block block, String name) {
        blocks.put(name, block);
    }
    
    @SubscribeEvent
    public void addItems(RegistryEvent.Register<Item> event) {
        PBlocks.blocks.forEach((key, val) -> {
            event.getRegistry().register(new ItemBlock(val).setRegistryName(MOD_ID, key));
        });
    }
    
    @SubscribeEvent
    public void addBlocks(RegistryEvent.Register<Block> event) {
        System.out.println("reg");
        PBlocks.blocks.forEach((key, val) -> {
            event.getRegistry().register(val.setRegistryName(new ResourceLocation(Reference.MOD_ID, key)));
        });
        
    }
    @SubscribeEvent
    public void onModelRegistry(ModelRegistryEvent event) {
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(PBlocks.PRIMAL_CHEST), 0, new ModelResourceLocation(Item.getItemFromBlock(PBlocks.PRIMAL_CHEST).getRegistryName(), "inventory"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(PBlocks.PRIMAL_CHEST_ADVANCED), 0, new ModelResourceLocation(Item.getItemFromBlock(PBlocks.PRIMAL_CHEST_ADVANCED).getRegistryName(), "inventory"));
        
    }
}