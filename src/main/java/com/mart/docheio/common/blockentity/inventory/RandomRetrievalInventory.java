package com.mart.docheio.common.blockentity.inventory;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import team.lodestar.lodestone.systems.blockentity.LodestoneBlockEntityInventory;

import java.util.List;
import java.util.Random;
import java.util.function.Predicate;

public class RandomRetrievalInventory extends LodestoneBlockEntityInventory {

    private Random random = new Random();

    public RandomRetrievalInventory(int slotCount, int allowedItemSize, Predicate<ItemStack> inputPredicate, Predicate<ItemStack> outputPredicate) {
        super(slotCount, allowedItemSize, inputPredicate, outputPredicate);
    }

    public RandomRetrievalInventory(int slotCount, int allowedItemSize, Predicate<ItemStack> inputPredicate) {
        super(slotCount, allowedItemSize, inputPredicate);
    }

    public RandomRetrievalInventory(int slotCount, int allowedItemSize) {
        super(slotCount, allowedItemSize);
    }

    @Override
    public ItemStack extractItem(Level level, ItemStack heldStack, Player player) {
        if (!level.isClientSide) {
            List<ItemStack> nonEmptyStacks = this.nonEmptyItemStacks;
            if (nonEmptyStacks.isEmpty()) {
                return heldStack;
            }
            ItemStack takeOutStack = nonEmptyStacks.get(random.nextInt(nonEmptyStacks.size()));
            int slot = stacks.indexOf(takeOutStack);
            if (extractItem(slot, takeOutStack.getCount(), true).equals(ItemStack.EMPTY)) {
                return heldStack;
            }
            extractItem(player, takeOutStack, slot);
            return takeOutStack;
        }
        return ItemStack.EMPTY;
    }
}
