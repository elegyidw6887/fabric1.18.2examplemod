package firstfabricmod.entity.customBlockEntity;

import firstfabricmod.entity.ModBlockEntities;
import firstfabricmod.util.ImplementedInventory;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.item.ItemStack;
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
        return null;
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        Inventories.readNbt(nbt, inventory);
        // “readNbt”方法要先执行“Inventories.readNbt()”方法
        super.readNbt(nbt);
    }

    @Override
    protected void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        Inventories.writeNbt(nbt, inventory);
        // “writeNbt”方法要后执行“Inventories.writeNbt()”方法
    }

    public static void tick(World world, BlockPos pos, BlockState state, InjectionBenchBlockEntity entity){

    }

    private static boolean hasRecipe(InjectionBenchBlockEntity entity) {
        return false;
    }

    private static void craftItem(InjectionBenchBlockEntity entity) {

    }


}
