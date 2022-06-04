package firstfabricmod.entity.customBlockEntity;

import firstfabricmod.entity.ModBlockEntities;
import firstfabricmod.item.ModItems;
import firstfabricmod.screen.InjectionBenchScreenHandler;
import firstfabricmod.util.ImplementedInventory;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class InjectionBenchBlockEntity extends BlockEntity implements NamedScreenHandlerFactory, ImplementedInventory {

    private final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(4, ItemStack.EMPTY);
    // size表示不同的插槽个数

    public InjectionBenchBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.INJECTION_BENCH, pos, state);
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
        return new InjectionBenchScreenHandler(syncId, inv, this);
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        Inventories.readNbt(nbt, inventory); // 直接调用“Inventories”类中的方法
        // “readNbt”方法要先执行“Inventories.readNbt()”方法
        super.readNbt(nbt);
    }

    @Override
    protected void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        Inventories.writeNbt(nbt, inventory); // 直接调用“Inventories”类中的方法
        // “writeNbt”方法要后执行“Inventories.writeNbt()”方法
    }

    public static void tick(World world, BlockPos pos, BlockState state, InjectionBenchBlockEntity entity){
        // 通过条件判断语句控制合成的方法
        if (hasRecipe(entity) && hasNotReachedStackLimit(entity)){
            craftItem(entity);
        }
    }

    private static boolean hasRecipe(InjectionBenchBlockEntity entity) {
        // 判断配方是否正确的方法
        boolean hasItemInFirstSlot = entity.getStack(0).getItem() == ModItems.FRAGMENT_OF_AMETHYST;
        boolean hasItemInSecondSlot = entity.getStack(1).getItem() == Items.IRON_INGOT;
        boolean hasItemInThirdSlot = entity.getStack(2).getItem() == ModItems.AMETHYST;

        return hasItemInFirstSlot && hasItemInSecondSlot && hasItemInThirdSlot;
    }

    private static boolean hasNotReachedStackLimit(InjectionBenchBlockEntity entity) {
        // 判断合成结果物品槽是否达到上限的方法
        return entity.getStack(3).getCount() < entity.getStack(3).getMaxCount();
    }

    private static void craftItem(InjectionBenchBlockEntity entity) {
        // 进行合成的方法
        entity.removeStack(0, 1);
        entity.removeStack(1, 1);
        entity.removeStack(2, 1);

        entity.setStack(3, new ItemStack(ModItems.CUBE_ENTITY_EGG, entity.getStack(3).getCount() + 1));
    }
}
