package examplefabricmod.world.biome;

import net.minecraft.sound.BiomeMoodSound;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeEffects;
import net.minecraft.world.biome.GenerationSettings;
import net.minecraft.world.biome.SpawnSettings;
import net.minecraft.world.gen.feature.DefaultBiomeFeatures;

public class ExampleBiome {

    public static Biome EXAMPLE_BIOME = (new Biome.Builder())
            .precipitation(Biome.Precipitation.RAIN)
                .category(Biome.Category.PLAINS)
                .temperature(0.8f)
                .downfall(0.5f)
                .effects(new BiomeEffects.Builder()
                        .waterColor(4159204)
                        .waterFogColor(329011)
                        .fogColor(12638463)
                        .skyColor(7843327)
                        .moodSound(BiomeMoodSound.CAVE)
                        .music(null)
                        .build())
            .spawnSettings(spawnSettings())
            .generationSettings(generationSettings())
            .build();

    private static SpawnSettings spawnSettings() {

        SpawnSettings.Builder builder = new SpawnSettings.Builder();

        // 生物生成
        DefaultBiomeFeatures.addPlainsMobs(builder);
        DefaultBiomeFeatures.addMonsters(builder, 100, 20, 100, false);

        return builder.build();
    }

    private static GenerationSettings generationSettings() {

        GenerationSettings.Builder builder2 = new GenerationSettings.Builder();

        // 生物群系特征添加
        DefaultBiomeFeatures.addLandCarvers(builder2);
        DefaultBiomeFeatures.addAmethystGeodes(builder2);
        DefaultBiomeFeatures.addDungeons(builder2);
        DefaultBiomeFeatures.addMineables(builder2);
        DefaultBiomeFeatures.addSprings(builder2);
        DefaultBiomeFeatures.addFrozenTopLayer(builder2);
        DefaultBiomeFeatures.addPlainsTallGrass(builder2);
        DefaultBiomeFeatures.addDefaultOres(builder2);
        DefaultBiomeFeatures.addDefaultDisks(builder2);
        DefaultBiomeFeatures.addPlainsFeatures(builder2);
        DefaultBiomeFeatures.addDefaultMushrooms(builder2);
        DefaultBiomeFeatures.addDefaultVegetation(builder2);

        return builder2.build();
    }
}
