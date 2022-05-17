package firstfabricmod.world.feature;

import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.world.gen.feature.PlacedFeature;
import net.minecraft.world.gen.feature.PlacedFeatures;
import net.minecraft.world.gen.feature.VegetationPlacedFeatures;
import net.minecraft.world.gen.placementmodifier.BiomePlacementModifier;
import net.minecraft.world.gen.placementmodifier.RarityFilterPlacementModifier;
import net.minecraft.world.gen.placementmodifier.SquarePlacementModifier;

public class ModPlacedFeatures {

    public static final RegistryEntry<PlacedFeature> JACARANDA_PLACED =
            PlacedFeatures.register("jacaranda_vegetation", ModConfiguredFeatures.JACARANDA_SPAWN,
                    VegetationPlacedFeatures.modifiers(PlacedFeatures.createCountExtraModifier(1, 0.1F, 2)));

    public static final RegistryEntry<PlacedFeature> LILAC_PLACED =
            PlacedFeatures.register("lilac_placed",
                    ModConfiguredFeatures.LILAC_FLOWER,
                    RarityFilterPlacementModifier.of(4),
                    SquarePlacementModifier.of(),
                    PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP,
                    BiomePlacementModifier.of());
}
