package firstfabricmod.world.feature;

import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.world.gen.feature.PlacedFeature;
import net.minecraft.world.gen.feature.PlacedFeatures;
import net.minecraft.world.gen.feature.VegetationPlacedFeatures;

public class ModPlacedFeatures {

    public static final RegistryEntry<PlacedFeature> JACARANDA_PLACED =
            PlacedFeatures.register("jacaranda_vegetation", ModConfiguredFeatures.JACARANDA_SPAWN,
                    VegetationPlacedFeatures.modifiers(PlacedFeatures.createCountExtraModifier(1, 0.1F, 2)));
}
