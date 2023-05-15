package examplefabricmod;

import examplefabricmod.block.ModBlocks;
import examplefabricmod.fluid.ModFluids;
import examplefabricmod.screen.InjectionBenchScreen;
import examplefabricmod.screen.ModScreenHandlers;
import examplefabricmod.util.ModModelPredicateProvider;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.render.fluid.v1.FluidRenderHandlerRegistry;
import net.fabricmc.fabric.api.client.render.fluid.v1.SimpleFluidRenderHandler;
import net.fabricmc.fabric.api.client.screenhandler.v1.ScreenRegistry;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.util.Identifier;

import static examplefabricmod.ExampleFabricMod.MOD_ID;

public class ExampleFabricModClient implements ClientModInitializer {

    public static final EntityModelLayer MODEL_CUBE_LAYER = new EntityModelLayer(new Identifier(MOD_ID, "cube"), "main");

    @Override
    public void onInitializeClient() {

        // 花方块的渲染
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.LILAC_FLOWER, RenderLayer.getCutout());
        // 葡萄作物方块渲染
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.GRAPE_VINE, RenderLayer.getCutout());
        // 弓的渲染
        ModModelPredicateProvider.registerModModels();
        // 血流体的渲染
        FluidRenderHandlerRegistry.INSTANCE.register(ModFluids.STILL_BLOOD,
                new SimpleFluidRenderHandler(SimpleFluidRenderHandler.WATER_STILL,
                        SimpleFluidRenderHandler.WATER_FLOWING,
                        SimpleFluidRenderHandler.WATER_OVERLAY,
                        0xDC143C));
        FluidRenderHandlerRegistry.INSTANCE.register(ModFluids.FLOWING_BLOOD,
                new SimpleFluidRenderHandler(SimpleFluidRenderHandler.WATER_STILL,
                        SimpleFluidRenderHandler.WATER_FLOWING,
                        SimpleFluidRenderHandler.WATER_OVERLAY,
                        0xDC143C));
        // 树叶的渲染
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.JACARANDA_LEAVES, RenderLayer.getCutout());
        // 树苗的渲染
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.JACARANDA_SAPLING, RenderLayer.getCutout());
        // 注入工作台的渲染
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.INJECTION_BENCH, RenderLayer.getCutout());
        // 注入工作台GUI渲染
        ScreenRegistry.register(ModScreenHandlers.INJECTION_BENCH_SCREEN_HANDLER, InjectionBenchScreen::new);
    }
}
