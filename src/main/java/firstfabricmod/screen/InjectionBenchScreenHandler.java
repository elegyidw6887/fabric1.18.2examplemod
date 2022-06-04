package firstfabricmod.screen;

import firstfabricmod.screen.slot.ModFuelSlot;
import firstfabricmod.screen.slot.ModResultSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.Slot;

public class InjectionBenchScreenHandler extends ScreenHandler {

    private final Inventory inventory;

    public InjectionBenchScreenHandler(int syncId, PlayerInventory playerInventory) {
        // 构造方法
        this(syncId, playerInventory, new SimpleInventory(4));
    }

    public InjectionBenchScreenHandler(int syncId, PlayerInventory playerInventory, Inventory inventory) {
        // 构造方法
        super(ModScreenHandlers.INJECTION_BENCH_SCREEN_HANDLER, syncId);
        checkSize(inventory, 4);
        this.inventory = inventory;
        inventory.onOpen(playerInventory.player);
        // 在完成插槽类后添加的代码
        this.addSlot(new ModFuelSlot(inventory, 0, 18, 50));
        this.addSlot(new Slot(inventory, 1, 66, 16));
        this.addSlot(new Slot(inventory, 2, 66, 50));
        this.addSlot(new ModResultSlot(inventory, 3, 114, 33));
    }

    @Override
    public boolean canUse(PlayerEntity player) {
        // 判断实体能否被使用的方法
        return this.inventory.canPlayerUse(player);
    }

    @Override
    public ItemStack transferSlot(PlayerEntity player, int invSlot) {
        // 传输槽方法
        // 方法实现参考“AbstractFurnaceScreenHandler”方法中的实现，但是没有进行“originalStack”与“newStack”大小比较判断
        ItemStack newStack = ItemStack.EMPTY;
        Slot slot = this.slots.get(invSlot);
        if (slot != null && slot.hasStack()) {
            ItemStack originalStack = slot.getStack();
            newStack = originalStack.copy();
            if (invSlot < this.inventory.size()) {
                if (!this.insertItem(originalStack, this.inventory.size(), this.slots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.insertItem(originalStack, 0, this.inventory.size(), false)) {
                return ItemStack.EMPTY;
            }
            if (originalStack.isEmpty()) {
                slot.setStack(ItemStack.EMPTY);
            } else {
                slot.markDirty();
            }
        }
        return newStack;
    }
}
