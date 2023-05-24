package examplefabricmod.world.biome;

import com.mojang.datafixers.util.Pair;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.world.biome.source.util.MultiNoiseUtil;
import terrablender.api.Region;
import terrablender.api.RegionType;

import java.util.function.Consumer;

public class ModRegions extends Region {

    public ModRegions(Identifier name, RegionType type, int weight) {
        super(name, type, weight);
    }

    @Override
    public void addBiomes(Registry<Biome> registry, Consumer<Pair<MultiNoiseUtil.NoiseHypercube, RegistryKey<Biome>>> mapper) {

        this.addModifiedVanillaOverworldBiomes(mapper, builder -> {

            // 将原版游戏中沙漠群系替换为自定义生物群系
            builder.replaceBiome(BiomeKeys.DESERT, ModBiomes.THE_SAME_AS_PLAIN);
        });
    }
}
