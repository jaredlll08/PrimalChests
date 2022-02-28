package com.blamejared.primalchests.common.event;

import com.blamejared.primalchests.PrimalChests;
import com.blamejared.primalchests.client.gui.screen.AdvancedPrimalChestScreen;
import com.blamejared.primalchests.client.gui.screen.PrimalChestScreen;
import com.blamejared.primalchests.common.container.ContainerTypes;
import net.minecraft.client.gui.ScreenManager;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = PrimalChests.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientEventBusSubscriber {

    @SubscribeEvent
    public static void clientSetup(FMLClientSetupEvent event){
        ScreenManager.register(ContainerTypes.PRIMAL_CHEST_CONTAINER.get(), PrimalChestScreen::new);
        ScreenManager.register(ContainerTypes.ADVANCED_PRIMAL_CHEST_CONTAINER.get(), AdvancedPrimalChestScreen::new);
    }

}
