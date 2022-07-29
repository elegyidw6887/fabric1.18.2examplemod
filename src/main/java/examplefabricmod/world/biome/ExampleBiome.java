package examplefabricmod.world.biome;

import net.minecraft.sound.BiomeMoodSound;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeEffects;
import net.minecraft.world.biome.GenerationSettings;
import net.minecraft.world.biome.SpawnSettings;
import net.minecraft.world.gen.feature.DefaultBiomeFeatures;

public class ExampleBiome {

    public static Biome createExampleBiome() {

        // 设置对象创建
        SpawnSettings.Builder builder = new SpawnSettings.Builder();
        GenerationSettings.Builder builder2 = new GenerationSettings.Builder();

        // 生物生成
        DefaultBiomeFeatures.addPlainsMobs(builder);
        DefaultBiomeFeatures.addMonsters(builder, 100, 20, 100, false);

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

        // 返回具体参数
        return (new Biome.Builder())
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
                .spawnSettings(builder.build())
                .generationSettings(builder2.build())
                .build();
    }
}
