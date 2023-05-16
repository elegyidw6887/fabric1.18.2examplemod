package examplefabricmod.world.feature;

import examplefabricmod.ExampleFabricMod;
import examplefabricmod.block.ModBlocks;
import net.minecraft.block.Blocks;
import net.minecraft.structure.rule.BlockMatchRuleTest;
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
    // 蓝花楹树的配置（1/4）
    public static final RegistryEntry<ConfiguredFeature<TreeFeatureConfig, ?>> JACARANDA_TREE =
            ConfiguredFeatures.register("jacaranda_tree", Feature.TREE,
                    new TreeFeatureConfig
                            .Builder(
                            BlockStateProvider.of(ModBlocks.JACARANDA_LOG),
                            new StraightTrunkPlacer(5, 6, 3),
                            BlockStateProvider.of(Blocks.OAK_LEAVES),
                            new BlobFoliagePlacer(ConstantIntProvider.create(2), ConstantIntProvider.create(0), 4),
                            new TwoLayersFeatureSize(1, 0, 2))
                            .dirtProvider(BlockStateProvider.of(ModBlocks.AMETHYST_BLOCK))
                            .build());
    // 蓝花楹树的配置（2/4）
    public static final RegistryEntry<PlacedFeature> JACARANDA_CHECKED =
            PlacedFeatures.register("jacaranda_checked", JACARANDA_TREE, PlacedFeatures.wouldSurvive(ModBlocks.JACARANDA_SAPLING));
    // 蓝花楹树的配置（3/4）
    public static final RegistryEntry<ConfiguredFeature<RandomFeatureConfig, ?>> JACARANDA_SPAWN =
            ConfiguredFeatures.register("jacaranda_spawn", Feature.RANDOM_SELECTOR,
                    new RandomFeatureConfig(List.of(new RandomFeatureEntry(JACARANDA_CHECKED, 0.5F)), JACARANDA_CHECKED));

    // CF -> PF
    // 丁香花的配置（1/2）
    public static final RegistryEntry<ConfiguredFeature<RandomPatchFeatureConfig, ?>> LILAC_FLOWER =
            ConfiguredFeatures.register("lilac_flower", Feature.FLOWER,
                    ConfiguredFeatures.createRandomPatchFeatureConfig(64, PlacedFeatures.createEntry(Feature.SIMPLE_BLOCK,
                            new SimpleBlockFeatureConfig(BlockStateProvider.of(ModBlocks.LILAC_FLOWER)))));

    // List -> CF -> PF
    // 血晶矿石的配置（1/3）
    public static final List<OreFeatureConfig.Target> OVERWORLD_AMETHYST_ORES = List.of(
            // 主世界配置
            OreFeatureConfig.createTarget(OreConfiguredFeatures.STONE_ORE_REPLACEABLES, ModBlocks.AMETHYST_ORE.getDefaultState()),
            OreFeatureConfig.createTarget(OreConfiguredFeatures.DEEPSLATE_ORE_REPLACEABLES, ModBlocks.DEEPSLATE_AMETHYST_ORE.getDefaultState()));
    public static final List<OreFeatureConfig.Target> NETHER_AMETHYST_ORES = List.of(
            // 下界配置
            OreFeatureConfig.createTarget(OreConfiguredFeatures.BASE_STONE_NETHER, ModBlocks.AMETHYST_ORE.getDefaultState()));
    public static final List<OreFeatureConfig.Target> END_AMETHYST_ORES = List.of(
            // 末地配置
            OreFeatureConfig.createTarget(new BlockMatchRuleTest(Blocks.END_STONE), ModBlocks.AMETHYST_ORE.getDefaultState()));
    // 血晶矿石的配置（2/3）
    public static final RegistryEntry<ConfiguredFeature<OreFeatureConfig, ?>> AMETHYST_ORE =
            // 主世界配置
            ConfiguredFeatures.register("amethyst_ore", Feature.ORE,
                    new OreFeatureConfig(OVERWORLD_AMETHYST_ORES, 9));
    public static final RegistryEntry<ConfiguredFeature<OreFeatureConfig, ?>> NETHER_AMETHYST_ORE =
            // 下界配置
            ConfiguredFeatures.register("nether_amethyst_ore", Feature.ORE,
                    new OreFeatureConfig(NETHER_AMETHYST_ORES, 16));
    public static final RegistryEntry<ConfiguredFeature<OreFeatureConfig, ?>> END_AMETHYST_ORE =
            // 末地配置
            ConfiguredFeatures.register("end_amethyst_ore", Feature.ORE,
                    new OreFeatureConfig(END_AMETHYST_ORES, 16));


    public static void registerModConfiguredFeatures() {
        ExampleFabricMod.LOGGER.info("Registering ModConfiguredFeatures for " + ExampleFabricMod.MOD_ID);
    }
}
