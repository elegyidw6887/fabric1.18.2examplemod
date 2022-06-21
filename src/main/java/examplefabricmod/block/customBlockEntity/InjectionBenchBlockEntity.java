package examplefabricmod.block.customBlockEntity;

import examplefabricmod.block.ModBlockEntities;
import examplefabricmod.recipe.InjectionBenchRecipe;
import examplefabricmod.screen.InjectionBenchScreenHandler;
import examplefabricmod.util.ImplementedInventory;
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

    protected final PropertyDelegate propertyDelegate; // 委托属性
    private int progress = 0; // 制作进度
    private int maxProgress = 64; // 最大制作进度（影响物品制作速度，数据绑定整个工作台）
    private int fuelTime = 0; // 燃料时间
    private int maxFuelTime = 0; // 最大燃料时间

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
        // 翻译键
        return new LiteralText("injection_bench");
    }

    @Nullable
    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory inv, PlayerEntity player) {
        // 创建屏幕菜单
        return new InjectionBenchScreenHandler(syncId, inv, this, this.propertyDelegate);
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        // 读取nbt数据
        // 读取玩家的物品栏
        Inventories.readNbt(nbt, inventory); // 直接调用“Inventories”类中的方法
        super.readNbt(nbt);
        // 读取方块实体中对应的数据
        progress = nbt.getInt("bench.progress");
        fuelTime = nbt.getInt("bench.fuelTime");
        maxFuelTime = nbt.getInt("bench.maxFuelTime");
    }

    @Override
    protected void writeNbt(NbtCompound nbt) {
        // 写入nbt数据
        super.writeNbt(nbt);
        // 将读取到的玩家物品栏保存到标签中
        Inventories.writeNbt(nbt, inventory); // 直接调用“Inventories”类中的方法
        // 向方块实体中写入对应的数据
        nbt.putInt("bench.progress", progress);
        nbt.putInt("bench.fuelTime", fuelTime);
        nbt.putInt("bench.maxFuelTime", maxFuelTime);
    }

    private void consumeFuel() {
        // 燃料消耗方法
        if (!getStack(0).isEmpty()) {
            // 获取燃料的燃料值
            this.fuelTime = FuelRegistry.INSTANCE.get(this.removeStack(0, 1).getItem());
            // 将获取到的燃料值设置为最大燃料值
            this.maxFuelTime = this.fuelTime;
        }
    }

    public static void tick(World world, BlockPos pos, BlockState state, InjectionBenchBlockEntity entity) {
        // 配方合成执行代码本体（在物品制作过程中会一直调用该方法，直至其中任何一个if判断结果为false）
        // 判断是否消耗燃料
        if (isConsumingFuel(entity)) {
            entity.fuelTime--;
        }
        // 判断是否存在配方
        if (hasRecipe(entity)) {
            // 判断燃料槽中是否有燃料以及是否消耗燃料
            if (hasFuelInFuelSlot(entity) && !isConsumingFuel(entity)) {
                // 判断结果为ture则进行燃料的消耗
                entity.consumeFuel();
            }
            if (isConsumingFuel(entity)) {
                // 制作进度++
                entity.progress++;
                // 如果制作进度大于最大进度则完成制作
                if (entity.progress > entity.maxProgress) {
                    // 调用制作代码来完成制作
                    craftItem(entity);
                }
            }
        } else {
            // 配方不存在则直接重置进度
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
        // 设置物品栈
        for (int i = 0; i < entity.inventory.size(); i++) {
            inventory.setStack(i, entity.getStack(i));
        }
        // 获取配方并保存到一个容器对象中
        assert world != null;
        Optional<InjectionBenchRecipe> match = world.getRecipeManager()
                .getFirstMatch(InjectionBenchRecipe.Type.INSTANCE, inventory, world);
        // 返回获取到的结果
        /*
            1.是否获取到对象
            2.输出槽中存在的物品是否是目标配方的产物
            3.输出槽是否还能够继续存储物品
         */
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
        // 获取配方并保存到一个容器对象中
        assert world != null;
        Optional<InjectionBenchRecipe> match = world.getRecipeManager()
                .getFirstMatch(InjectionBenchRecipe.Type.INSTANCE, inventory, world);
        // 判断是否获取到配方
        if (match.isPresent()) {
            // 移除插槽1与2中的物品
            entity.removeStack(1, 1);
            entity.removeStack(2, 1);
            // 在插槽3中放置目标产物
            entity.setStack(3, new ItemStack(match.get().getOutput().getItem(),
                    entity.getStack(3).getCount() + 1));
            // 完成一次制作之后重置进度
            entity.resetProgress();
        }
    }

    private void resetProgress() {
        // 重置进程
        this.progress = 0;
    }

    private static boolean canInsertItemIntoOutputSlot(SimpleInventory inventory, ItemStack output) {
        // 是否可以将产物放入输出槽，判断输出槽中的物品是否为目标配方的产物
        return inventory.getStack(3).getItem() == output.getItem() || inventory.getStack(3).isEmpty();
    }

    private static boolean canInsertAmountIntoOutputSlot(SimpleInventory inventory) {
        // 是否可以将多个产物放入输出槽
        return inventory.getStack(3).getMaxCount() > inventory.getStack(3).getCount();
    }
}
