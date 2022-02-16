package com.steampunknation.primalchests;

import com.steampunknation.primalchests.common.blocks.tileentities.TileEntityRegister;
import com.steampunknation.primalchests.common.container.ContainerTypes;
import com.steampunknation.primalchests.common.items.ItemRegister;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import com.steampunknation.primalchests.common.blocks.BlockRegister;


@Mod("primalchests")
@Mod.EventBusSubscriber(modid = PrimalChests.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class PrimalChests {
    public static final String MOD_ID = "primalchests";

    public PrimalChests() {
        IEventBus modBus = FMLJavaModLoadingContext.get().getModEventBus();
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientStuff);

        //Registers
        BlockRegister.BLOCKS.register(modBus);
        BlockRegister.ITEMS.register(modBus);
        ItemRegister.ITEMS.register(modBus);
        TileEntityRegister.TILE_ENTITY.register(modBus);
        ContainerTypes.CONTAINER_TYPES.register(modBus);

        MinecraftForge.EVENT_BUS.register(this);
    }

    public void doClientStuff(final FMLClientSetupEvent event) {
        //Render Transparent
        //Primal Chest
        RenderTypeLookup.setRenderLayer(BlockRegister.PRIMAL_CHEST.get(), (layer) -> layer == RenderType.translucent());
        //Advanced Primal Chest
        RenderTypeLookup.setRenderLayer(BlockRegister.ADVANCED_PRIMAL_CHEST.get(), (layer) -> layer == RenderType.translucent());
    }
}
