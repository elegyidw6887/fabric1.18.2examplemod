package firstfabricmod.world.feature;

import firstfabricmod.FirstFabricMod;
import firstfabricmod.block.ModBlocks;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.intprovider.ConstantIntProvider;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.feature.size.TwoLayersFeatureSize;
import net.minecraft.world.gen.foliage.BlobFoliagePlacer;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;
import net.minecraft.world.gen.trunk.StraightTrunkPlacer;

import java.util.List;

public class ModConfiguredFeatures {

    // CF -> PF -> CF -> PF
    public static final RegistryEntry<ConfiguredFeature<TreeFeatureConfig, ?>> JACARANDA_TREE =
            ConfiguredFeatures.register("jacaranda_tree", Feature.TREE,
                    new TreeFeatureConfig
                            .Builder(
                            BlockStateProvider.of(ModBlocks.JACARANDA_LOG),
                            new StraightTrunkPlacer(5, 6, 3),
                            BlockStateProvider.of(Blocks.ACACIA_LEAVES),
                            new BlobFoliagePlacer(ConstantIntProvider.create(2), ConstantIntProvider.create(0), 4),
                            new TwoLayersFeatureSize(1, 0, 2))
                            .build());
    public static final RegistryEntry<PlacedFeature> JACARANDA_CHECKED =
            PlacedFeatures.register("jacaranda_checked", JACARANDA_TREE, PlacedFeatures.wouldSurvive(ModBlocks.JACARANDA_SAPLING));
    public static final RegistryEntry<ConfiguredFeature<RandomFeatureConfig, ?>> JACARANDA_SPAWN =
            ConfiguredFeatures.register("jacaranda_spawn", Feature.RANDOM_SELECTOR,
                    new RandomFeatureConfig(List.of(new RandomFeatureEntry(JACARANDA_CHECKED, 0.5F)), JACARANDA_CHECKED));



    public static void registerModConfiguredFeatures(){
        FirstFabricMod.LOGGER.info("Registering ModConfiguredFeatures for " + FirstFabricMod.MOD_ID);
    }
}
