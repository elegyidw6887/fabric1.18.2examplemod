package examplefabricmod.block.customBlock;

import examplefabricmod.block.ModBlockEntities;
import examplefabricmod.block.customBlockEntity.InjectionBenchBlockEntity;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.*;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class InjectionBenchBlock extends BlockWithEntity implements BlockEntityProvider {

    public static final DirectionProperty FACING = Properties.HORIZONTAL_FACING;

    public InjectionBenchBlock(Settings settings) {
        super(settings);
    }

    /* 方块类“Block”方法 */
    @Nullable
    @Override
    public BlockState getPlacementState(@NotNull ItemPlacementContext ctx) {
        return this.getDefaultState().with(FACING, ctx.getPlayerFacing().getOpposite());
    }

    // 与“AbstractFurnaceBlock”抽象类中相同
    @Override
    public BlockState rotate(@NotNull BlockState state, @NotNull BlockRotation rotation) {
        return state.with(FACING, rotation.rotate(state.get(FACING)));
    }

    // 与“AbstractFurnaceBlock”抽象类中相同
    @Override
    public BlockState mirror(@NotNull BlockState state, @NotNull BlockMirror mirror) {
        return state.rotate(mirror.getRotation(state.get(FACING)));
    }

    // 与“AbstractFurnaceBlock”抽象类中相同，但少传入一个Boolean参数
    @Override
    protected void appendProperties(StateManager.@NotNull Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

    /* 方块实体类“BlockWithEntity”重载方法 */
    // 创建方块实体方法
    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        // 创建方块实体方法，返回值是一个方块实体
        return new InjectionBenchBlockEntity(pos, state);
    }

    // 获取渲染类型方法
    @Override
    public BlockRenderType getRenderType(BlockState state) {
        // 实现参考“AbstractFurnaceBlock”类中的实现方法
        return BlockRenderType.MODEL;
    }

    // 状态替换方法
    @Override
    public void onStateReplaced(@NotNull BlockState state, World world, BlockPos pos, @NotNull BlockState newState, boolean moved) {
        // 当方块状态出现更改时将其中的物品抛出的方法
        // 实现参考“AbstractFurnaceBlock”类中的实现方法，但是并没有进行“if (world instanceof ServerWorld)”的判断
        if (state.getBlock() != newState.getBlock()) {
            BlockEntity blockEntity = world.getBlockEntity(pos);
            if (blockEntity instanceof InjectionBenchBlockEntity) {
                ItemScatterer.spawn(world, pos, (InjectionBenchBlockEntity) blockEntity);
                world.updateComparators(pos, this);
            }
            super.onStateReplaced(state, world, pos, newState, moved);
        }
    }

    // 实体使用方法
    @Override
    public ActionResult onUse(BlockState state, @NotNull World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        // 用于打开实体GUI的方法
        if (!world.isClient) {
            NamedScreenHandlerFactory screenHandlerFactory = state.createScreenHandlerFactory(world, pos);
            if (screenHandlerFactory != null) {
                player.openHandledScreen(screenHandlerFactory);
            }
        }
        return ActionResult.SUCCESS;
    }

    // 获取“刻”方法
    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        // 实现参考“AbstractFurnaceBlock”类中的实现方法，但是并没有进行世界是否是客户端（world.isClient）的判断
        return checkType(type, ModBlockEntities.INJECTION_BENCH, InjectionBenchBlockEntity::tick);
    }
}
