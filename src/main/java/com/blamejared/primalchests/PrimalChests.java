package com.blamejared.primalchests;

import com.blamejared.primalchests.blocks.*;
import com.blamejared.primalchests.proxies.CommonProxy;
import com.blamejared.primalchests.tileentities.*;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.init.Blocks;
import net.minecraft.item.*;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.*;
import net.minecraftforge.fml.common.event.*;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.*;
import net.minecraftforge.registries.ObjectHolderRegistry;

import static com.blamejared.primalchests.reference.Reference.*;

@Mod(modid = MOD_ID, name = MOD_NAME, version = VERSION)
public class PrimalChests {
    
    
    @Mod.Instance(MOD_ID)
    public static PrimalChests INSTANCE;
    
    @SidedProxy(clientSide = "com.blamejared.primalchests.proxies.ClientProxy", serverSide = "com.blamejared.primalchests.proxies.CommonProxy")
    public static CommonProxy PROXY;
    
    @Mod.EventHandler
    public void preinit(FMLPreInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(new PBlocks());
        PBlocks.preInit();
        GameRegistry.registerTileEntity(TileEntityPrimalChest.class, "tile_primal_chest");
        GameRegistry.registerTileEntity(TileEntityPrimalChestAdvanced.class, "tile_primal_chest_advanced");
        
        
    }
    
    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        PROXY.registerGuis();
        PROXY.registerRenders();
    }
    
    @Mod.EventHandler
    public void postinit(FMLPostInitializationEvent event) {
    }
    
}
