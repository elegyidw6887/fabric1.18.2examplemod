package firstfabricmod.entity.customBlockEntity;

import firstfabricmod.entity.ModBlockEntities;
import firstfabricmod.recipe.InjectionBenchRecipe;
import firstfabricmod.screen.InjectionBenchScreenHandler;
import firstfabricmod.util.ImplementedInventory;
import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class InjectionBenchBlockEntity extends BlockEntity implements NamedScreenHandlerFactory, ImplementedInventory {

    private final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(4, ItemStack.EMPTY);
    // size表示不同的插槽个数

    protected final PropertyDelegate propertyDelegate;
    private int progress = 0;
    private int maxProgress = 64;
    private int fuelTime = 0;
    private int maxFuelTime = 0;

    public InjectionBenchBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.INJECTION_BENCH, pos, state);
        this.propertyDelegate = new PropertyDelegate() {
            @Override
            public int get(int index) {
                return switch (index) {
                    case 0 -> InjectionBenchBlockEntity.this.progress;
                    case 1 -> InjectionBenchBlockEntity.this.maxProgress;
                    case 2 -> InjectionBenchBlockEntity.this.fuelTime;
                    case 3 -> InjectionBenchBlockEntity.this.maxFuelTime;
                    default -> 0;
                };
            }
            @Override
            public void set(int index, int value) {
                switch (index) {
                    case 0 -> InjectionBenchBlockEntity.this.progress = value;
                    case 1 -> InjectionBenchBlockEntity.this.maxProgress = value;
                    case 2 -> InjectionBenchBlockEntity.this.fuelTime = value;
                    case 3 -> InjectionBenchBlockEntity.this.maxFuelTime = value;
                }
            }
            @Override
            public int size() {
                return 4;
            }
        };
    }

    @Override
    public DefaultedList<ItemStack> getItems() {
        return inventory;
    }

    @Override
    public Text getDisplayName() {
        return new LiteralText("injection_bench");
    }

    @Nullable @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory inv, PlayerEntity player) {
        return new InjectionBenchScreenHandler(syncId, inv, this, this.propertyDelegate);
    }

    @Override
    protected void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        Inventories.writeNbt(nbt, inventory); // 直接调用“Inventories”类中的方法
        // “writeNbt”方法要后执行“Inventories.writeNbt()”方法
        nbt.putInt("bench.progress", progress);
        nbt.putInt("bench.fuelTime", fuelTime);
        nbt.putInt("bench.maxFuelTime", maxFuelTime);
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        Inventories.readNbt(nbt, inventory); // 直接调用“Inventories”类中的方法
        // “readNbt”方法要先执行“Inventories.readNbt()”方法
        super.readNbt(nbt);
        progress = nbt.getInt("bench.progress");
        fuelTime = nbt.getInt("bench.fuelTime");
        maxFuelTime = nbt.getInt("bench.maxFuelTime");
    }

    private void consumeFuel() {
        // 燃料消耗方法
        if(!getStack(0).isEmpty()) {
            this.fuelTime = FuelRegistry.INSTANCE.get(this.removeStack(0, 1).getItem());
            this.maxFuelTime = this.fuelTime;
        }
    }

    public static void tick(World world, BlockPos pos, BlockState state, InjectionBenchBlockEntity entity) {
        // 执行本体
        if(isConsumingFuel(entity)) {
            entity.fuelTime--;
        }

        if(hasRecipe(entity)) {
            if(hasFuelInFuelSlot(entity) && !isConsumingFuel(entity)) {
                entity.consumeFuel();
            }
            if(isConsumingFuel(entity)) {
                entity.progress++;
                if(entity.progress > entity.maxProgress) {
                    craftItem(entity);
                }
            }
        } else {
            entity.resetProgress();
        }
    }

    private static boolean hasFuelInFuelSlot(InjectionBenchBlockEntity entity) {
        // 判断燃料槽中是否有燃料
        return !entity.getStack(0).isEmpty();
    }

    private static boolean isConsumingFuel(InjectionBenchBlockEntity entity) {
        // 判断是否需要消耗燃料
        return entity.fuelTime > 0;
    }

    private static boolean hasRecipe(InjectionBenchBlockEntity entity) {
        // 判断配方是否正确
        World world = entity.world;
        SimpleInventory inventory = new SimpleInventory(entity.inventory.size());
        for (int i = 0; i < entity.inventory.size(); i++) {
            inventory.setStack(i, entity.getStack(i));
        }

        assert world != null;
        Optional<InjectionBenchRecipe> match = world.getRecipeManager()
                .getFirstMatch(InjectionBenchRecipe.Type.INSTANCE, inventory, world);

        return match.isPresent() && canInsertAmountIntoOutputSlot(inventory)
                && canInsertItemIntoOutputSlot(inventory, match.get().getOutput());
    }

    private static void craftItem(InjectionBenchBlockEntity entity) {
        // 进行物品制作
        World world = entity.world;
        SimpleInventory inventory = new SimpleInventory(entity.inventory.size());
        for (int i = 0; i < entity.inventory.size(); i++) {
            inventory.setStack(i, entity.getStack(i));
        }

        assert world != null;
        Optional<InjectionBenchRecipe> match = world.getRecipeManager()
                .getFirstMatch(InjectionBenchRecipe.Type.INSTANCE, inventory, world);

        if(match.isPresent()) {
            entity.removeStack(1,1);
            entity.removeStack(2,1);

            entity.setStack(3, new ItemStack(match.get().getOutput().getItem(),
                    entity.getStack(3).getCount() + 1));

            entity.resetProgress();
        }
    }

    private void resetProgress() {
        // 重置进程
        this.progress = 0;
    }

    private static boolean canInsertItemIntoOutputSlot(SimpleInventory inventory, ItemStack output) {
        // 是否可以将物品放入输出槽
        return inventory.getStack(3).getItem() == output.getItem() || inventory.getStack(3).isEmpty();
    }

    private static boolean canInsertAmountIntoOutputSlot(SimpleInventory inventory) {
        //
        return inventory.getStack(3).getMaxCount() > inventory.getStack(3).getCount();
    }
}
