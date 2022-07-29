package examplefabricmod.world.biome;

import net.minecraft.sound.BiomeMoodSound;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeEffects;
import net.minecraft.world.biome.GenerationSettings;
import net.minecraft.world.biome.SpawnSettings;
import net.minecraft.world.gen.feature.DefaultBiomeFeatures;
import net.minecraft.world.gen.surfacebuilder.MaterialRules;

public class ExampleBiome {

    private static int getSkyColor() {
        float f = MathHelper.clamp(0.8f, -1.0f, 1.0f);
        return MathHelper.hsvToRgb(0.62222224f - f * 0.05f, 0.5f + f * 0.1f, 1.0f);
    }

    private static final MaterialRules EXAMPLE_RULE = null;

    public static Biome createExampleBiome() {

        SpawnSettings.Builder builder = new SpawnSettings.Builder();
        GenerationSettings.Builder builder2 = new GenerationSettings.Builder();

        DefaultBiomeFeatures.addPlainsMobs(builder);
        DefaultBiomeFeatures.addMonsters(builder, 100, 20, 100, false);

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

        return (new Biome.Builder())
                .precipitation(Biome.Precipitation.RAIN)
                .category(Biome.Category.PLAINS)
                .temperature(0.8f)
                .downfall(0.5f)
                .effects(new BiomeEffects.Builder()
                        .waterColor(4159204)
                        .waterFogColor(329011)
                        .fogColor(12638463)
                        .skyColor(getSkyColor())
                        .moodSound(BiomeMoodSound.CAVE)
                        .music(null)
                        .build())
                .spawnSettings(builder.build())
                .generationSettings(builder2.build())
                .build();
    }
}
