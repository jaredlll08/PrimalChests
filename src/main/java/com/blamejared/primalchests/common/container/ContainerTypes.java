package com.blamejared.primalchests.common.container;

import com.blamejared.primalchests.PrimalChests;
import net.minecraft.inventory.container.ContainerType;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ContainerTypes {

    public static final DeferredRegister<ContainerType<?>> CONTAINER_TYPES = DeferredRegister
            .create(ForgeRegistries.CONTAINERS, PrimalChests.MOD_ID);

    public static final RegistryObject<ContainerType<PrimalChestContainer>> PRIMAL_CHEST_CONTAINER = CONTAINER_TYPES
            .register("primal_chest", () -> IForgeContainerType.create(PrimalChestContainer::new));

    public static final RegistryObject<ContainerType<AdvancedPrimalChestContainer>> ADVANCED_PRIMAL_CHEST_CONTAINER = CONTAINER_TYPES
            .register("advanced_primal_chest", () -> IForgeContainerType.create(AdvancedPrimalChestContainer::new));

}
