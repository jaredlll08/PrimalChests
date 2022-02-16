package com.steampunknation.primalchests.common.items;

import com.steampunknation.primalchests.common.blocks.BlockRegister;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class ModItemGroup {

    public static final ItemGroup PRIMAL_CHESTS_GROUP = new ItemGroup("primalChestsTab") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(BlockRegister.PRIMAL_CHEST.get());
        }
    };

}
