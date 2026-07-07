package com.mart.docheio.common.menu;

import com.mart.docheio.common.registry.menu.MenuRegistry;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

public class PotteryWheelMenu extends AbstractContainerMenu {

    public PotteryWheelMenu(int containerId, Inventory playerInventory) {
        super(MenuRegistry.POTTERY_WHEEL.get(), containerId);
        // No slots yet: this menu currently only carries the pottery wheel screen.
        // Input/output/pattern slots will be added here once the crafting flow exists.
    }

    @Override
    public @NotNull ItemStack quickMoveStack(@NotNull Player player, int index) {
        // No slots to shift-click between yet.
        return ItemStack.EMPTY;
    }

    @Override
    public boolean stillValid(@NotNull Player player) {
        return true;
    }
}
