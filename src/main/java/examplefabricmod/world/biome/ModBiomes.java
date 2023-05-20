package examplefabricmod.world.biome;

import examplefabricmod.ExampleFabricMod;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.biome.Biome;

public class ModBiomes {

    private static final RegistryKey<Biome> EXAMPLE_BIOME = RegistryKey.of(Registry.BIOME_KEY,
            new Identifier(ExampleFabricMod.MOD_ID, "example_biome"));

    public static void registerModBiomes() {

        Registry.register(BuiltinRegistries.BIOME, EXAMPLE_BIOME.getValue(), ExampleBiome.EXAMPLE_BIOME);

        ExampleFabricMod.LOGGER.info("Registering ModBiomes for " + ExampleFabricMod.MOD_ID);
    }
}
