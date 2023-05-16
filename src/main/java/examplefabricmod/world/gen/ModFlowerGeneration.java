package examplefabricmod.world.gen;

import examplefabricmod.world.feature.ModPlacedFeatures;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.world.gen.GenerationStep;

public class ModFlowerGeneration {

    public static void generateFlowers() {

        // 丁香花的生成
        BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Feature.VEGETAL_DECORATION,
                ModPlacedFeatures.LILAC_PLACED.getKey().get());
    }
}
