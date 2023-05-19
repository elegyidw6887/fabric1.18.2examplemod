package examplefabricmod.block;

import examplefabricmod.ExampleFabricMod;
import examplefabricmod.block.customBlockEntity.InjectionBenchBlockEntity;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModBlockEntities {

    // 我们使用泛型来创建一个“InjectionBenchBlockEntity”类型的【方块实体类型】对象
    public static BlockEntityType<InjectionBenchBlockEntity> INJECTION_BENCH;

    public static void registerBlockEntities() {

        // 注入工作台对象的注册
        INJECTION_BENCH = Registry.register(Registry.BLOCK_ENTITY_TYPE,
                new Identifier(ExampleFabricMod.MOD_ID, "injection_bench"),
                FabricBlockEntityTypeBuilder.create(InjectionBenchBlockEntity::new, ModBlocks.INJECTION_BENCH).build(null));

        ExampleFabricMod.LOGGER.info("Registering BlockEntities for " + ExampleFabricMod.MOD_ID);
    }
}
