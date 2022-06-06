package examplefabricmod.block;

import examplefabricmod.ExampleFabricMod;
import examplefabricmod.block.customBlockEntity.InjectionBenchBlockEntity;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModBlockEntities {

    public static BlockEntityType<InjectionBenchBlockEntity> INJECTION_BENCH;

    public static void registerBlockEntities() {

        INJECTION_BENCH = Registry.register(Registry.BLOCK_ENTITY_TYPE,
                new Identifier(ExampleFabricMod.MOD_ID, "injection_bench"),
                FabricBlockEntityTypeBuilder.create(InjectionBenchBlockEntity::new, ModBlocks.INJECTION_BENCH).build(null));

        ExampleFabricMod.LOGGER.info("Registering BlockEntities for " + ExampleFabricMod.MOD_ID);
    }
}
