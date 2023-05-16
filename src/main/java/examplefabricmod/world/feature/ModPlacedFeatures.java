package examplefabricmod.world.feature;

import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.feature.PlacedFeature;
import net.minecraft.world.gen.feature.PlacedFeatures;
import net.minecraft.world.gen.feature.VegetationPlacedFeatures;
import net.minecraft.world.gen.placementmodifier.BiomePlacementModifier;
import net.minecraft.world.gen.placementmodifier.HeightRangePlacementModifier;
import net.minecraft.world.gen.placementmodifier.RarityFilterPlacementModifier;
import net.minecraft.world.gen.placementmodifier.SquarePlacementModifier;

public class ModPlacedFeatures {

    // 蓝花楹树的配置（4/4）
    public static final RegistryEntry<PlacedFeature> JACARANDA_PLACED =
            PlacedFeatures.register("jacaranda_vegetation", ModConfiguredFeatures.JACARANDA_SPAWN,
                    VegetationPlacedFeatures.modifiers(PlacedFeatures.createCountExtraModifier(1, 0.1F, 2)));

    // 丁香花的配置（2/2）
    public static final RegistryEntry<PlacedFeature> LILAC_PLACED =
            PlacedFeatures.register("lilac_placed",
                    ModConfiguredFeatures.LILAC_FLOWER,
                    RarityFilterPlacementModifier.of(4),
                    SquarePlacementModifier.of(),
                    PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP,
                    BiomePlacementModifier.of());

    // 血晶矿石的配置（3/3）
    public static final RegistryEntry<PlacedFeature> AMETHYST_ORE_PLACED =
            // 主世界放置
            PlacedFeatures.register("amethyst_ore_placed",
                    ModConfiguredFeatures.AMETHYST_ORE,
                    ModOreFeatures.modifiersWithCount(7,
                            HeightRangePlacementModifier.trapezoid(YOffset.aboveBottom(-88), YOffset.aboveBottom(80))));
    public static final RegistryEntry<PlacedFeature> NETHER_AMETHYST_ORE_PLACED =
            // 下界放置
            PlacedFeatures.register("nether_amethyst_ore_placed",
                    ModConfiguredFeatures.NETHER_AMETHYST_ORE,
                    ModOreFeatures.modifiersWithCount(16,
                            HeightRangePlacementModifier.uniform(YOffset.aboveBottom(-88), YOffset.aboveBottom(80))));
    public static final RegistryEntry<PlacedFeature> END_AMETHYST_ORE_PLACED =
            // 末地放置
            PlacedFeatures.register("end_amethyst_ore_placed",
                    ModConfiguredFeatures.END_AMETHYST_ORE,
                    ModOreFeatures.modifiersWithCount(16,
                            HeightRangePlacementModifier.uniform(YOffset.aboveBottom(-88), YOffset.aboveBottom(80))));
}
