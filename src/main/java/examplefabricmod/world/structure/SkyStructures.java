package examplefabricmod.world.structure;

import examplefabricmod.ExampleFabricMod;
import net.minecraft.structure.*;
import net.minecraft.structure.pool.StructurePoolBasedGenerator;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.Heightmap;
import net.minecraft.world.gen.feature.StructureFeature;
import net.minecraft.world.gen.feature.StructurePoolFeatureConfig;

import java.util.Optional;

public class SkyStructures extends StructureFeature<StructurePoolFeatureConfig> {

    public SkyStructures() {
        super(StructurePoolFeatureConfig.CODEC, SkyStructures::createPiecesGenerator, PostPlacementProcessor.EMPTY);
    }

    /**
     * 这是可以用于额外检查来决定我们的建筑物能否生成的方法,只有我们需要添加额外的生成条件的时候再去重载这个方法。
     *
     * （只有我们将建筑物间隔设置为0/1的时候该方法才会在世界上某些区块坐标返回true）
     *
     * 基本上这个方法基于确定区块是否满足我们需要的高度，或者距离其他建筑物太近，又或者有其他的限制条件。
     *
     * 比如，“Pillager Outposts”添加了一个生成条件来让建筑物不会生成在村庄的10个区块之内。
     *
     * This is where extra checks can be done to determine if the structure can spawn here.
     * This only needs to be overridden if you're adding additional spawn conditions.
     *
     * Fun fact, if you set your structure separation/spacing to be 0/1, you can use
     * isFeatureChunk to return ture only if certain chunk coordinates in the world.
     *
     * Basically, this method is use for determining if the land is at a suitable height,
     * if certain other structures are toot close or not, or some other restrictive condition.
     *
     * For example, Pillager Outposts added a check to make sure it cannot spawn within 10 chunk of a Village.
     * (Bedrock Edition seems to not have the same check)
     *
     * If you are doing  Nether structure,you'll probably want to spawn your structure on top of ledges.
     * Beat way to do that is to use getBaseColum to grab a colum of blocks at the structure's x/z position.
     * The loop through it and look for land with air above it and set blockpos's Y value to it.
     * Make sure to set the final boolean in JigsawPlacement.addPieces to false so that the structure spawns at
     * blockpos's y value instead of placing the structure on the Bedrock roof!
     */
    private static boolean isFeatureChunk(StructureGeneratorFactory.Context<StructurePoolFeatureConfig> context) {

        // 获取所在的区块位置
        ChunkPos chunkPos = context.chunkPos();

        // 检查并确保我们的建筑物不会生成在海洋纪念碑附近10区块之内（演示如何添加额外的生成条件）
        return !context.chunkGenerator().method_41053(StructureSetKeys.OCEAN_MONUMENTS, context.seed(), chunkPos.x, chunkPos.z, 10);
    }

    private static Optional<StructurePiecesGenerator<StructurePoolFeatureConfig>> createPiecesGenerator(StructureGeneratorFactory.Context<StructurePoolFeatureConfig> context) {

        // 检查该区域对我们的建筑物来说是否有效
        // 当返回empty的时候就会告诉程序不要在此处生成我们的建筑物
        if (!SkyStructures.isFeatureChunk(context)) {
            return Optional.empty();
        }

        // 获取区块中心Y坐标
        BlockPos blockPos = context.chunkPos().getCenterAtY(0);

        // 寻找该区块中最高的Y坐标，并将我们的建筑物生成在此坐标60个方块以上
        // “WORLD_SURFACE_WG”将不会让我们的建筑物生成在海洋中，以第一个非空气方块处停止计算
        int topLandY = context.chunkGenerator().getHeightOnGround(blockPos.getX(), blockPos.getZ(), Heightmap.Type.WORLD_SURFACE_WG, context.world());
        blockPos = blockPos.up(topLandY + 60);

        Optional<StructurePiecesGenerator<StructurePoolFeatureConfig>> structurePiecesGenerator =
                StructurePoolBasedGenerator.generate(
                        context, // 用于JigsawPlacement以完成所有正确的行为
                        PoolStructurePiece::new, // 在制作结构布局时创建拼图块列表需要
                        blockPos, // 建筑物的位置，如果方法调用中最后一个参数设置为“true”，则会忽略Y轴的值。
                        false, // 村庄边界的调整，当参数设置为false的时候，会避免村庄相互交叉
                        // 让村庄之间不产生交互交叉的情况或者让村庄之间产生包含情况是相对比较好的处理方式
                        false // 用于将建筑物放置在目标区块的最高处，如果传入的参数为“false”，则会将结构放置在传入的“blockpos”参数的Y轴值处
                        // 当你的建筑物要放置在下界时，一定要保证该参数为“false”，否则建筑物会生成在下界顶部基岩层上方
                );

        if (structurePiecesGenerator.isPresent()) {

            ExampleFabricMod.LOGGER.info("Sky Structure at " + blockPos);
        }

        return structurePiecesGenerator;
    }
}
