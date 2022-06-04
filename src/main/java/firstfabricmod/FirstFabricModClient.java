package firstfabricmod;

import firstfabricmod.block.ModBlocks;
import firstfabricmod.entity.CubeEntity.CubeEntity;
import firstfabricmod.entity.CubeEntity.CubeEntityModel;
import firstfabricmod.entity.CubeEntity.CubeEntityRenderer;
import firstfabricmod.fluid.ModFluids;
import firstfabricmod.screen.InjectionBenchScreen;
import firstfabricmod.screen.ModScreenHandlers;
import firstfabricmod.util.ModModelPredicateProvider;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.render.fluid.v1.FluidRenderHandlerRegistry;
import net.fabricmc.fabric.api.client.render.fluid.v1.SimpleFluidRenderHandler;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.client.screenhandler.v1.ScreenRegistry;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.util.Identifier;

import static firstfabricmod.FirstFabricMod.MOD_ID;

public class FirstFabricModClient implements ClientModInitializer {

    public static final EntityModelLayer MODEL_CUBE_LAYER = new EntityModelLayer(new Identifier(MOD_ID,"cube"),"main");

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
        // 树苗的渲染
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.JACARANDA_SAPLING, RenderLayer.getCutout());
        // 注入工作台的渲染
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.INJECTION_BENCH, RenderLayer.getCutout());
        // 注入工作台gui渲染
        ScreenRegistry.register(ModScreenHandlers.INJECTION_BENCH_SCREEN_HANDLER, InjectionBenchScreen::new);

        EntityRendererRegistry.register(CubeEntity.CUBE_ENTITY, CubeEntityRenderer::new);
        EntityModelLayerRegistry.registerModelLayer(MODEL_CUBE_LAYER, CubeEntityModel::getTexturedModelData);
    }
}
