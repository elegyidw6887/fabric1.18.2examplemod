package examplefabricmod.world.biome;

import examplefabricmod.ExampleFabricMod;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.biome.Biome;

import java.util.HashMap;
import java.util.Map;

public class ModBiomes {

    public static final Map<RegistryKey<Biome>, Biome> BIOMES = new HashMap<>();

    public static final RegistryKey<Biome> EXAMPLE_BIOME = add("example_biome", ExampleBiome.EXAMPLE_BIOME);

    private static RegistryKey<Biome> add(String string, Biome biome) {

        RegistryKey<Biome> key = RegistryKey.of(Registry.BIOME_KEY, new Identifier(ExampleFabricMod.MOD_ID, string));
        // 将生物群系的“key”与“biome”参数存储到Map中
        BIOMES.put(key, biome);

        return key;
    }

    public static void registerModBiomes() {

        // 使用循环来简化注册多个生物群系的代码量
        for (RegistryKey<Biome> key : BIOMES.keySet()) {

            BuiltinRegistries.add(BuiltinRegistries.BIOME, key, BIOMES.get(key));
        }

        ExampleFabricMod.LOGGER.info("Registering ModBiomes for " + ExampleFabricMod.MOD_ID);
    }
}
