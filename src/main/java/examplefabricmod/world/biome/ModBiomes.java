package examplefabricmod.world.biome;

import examplefabricmod.ExampleFabricMod;
import examplefabricmod.world.biome.customBiome.TheSameAsPlain;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.biome.Biome;
import terrablender.api.RegionType;
import terrablender.api.Regions;

public class ModBiomes {

    public static final RegistryKey<Biome> THE_SAME_AS_PLAIN = RegistryKey.of(Registry.BIOME_KEY,
            new Identifier(ExampleFabricMod.MOD_ID, "the_same_as_plain"));

    public static void registerModBiomes() {

        Registry.register(BuiltinRegistries.BIOME, THE_SAME_AS_PLAIN.getValue(), TheSameAsPlain.THE_SAME_AS_PLAIN);

        Regions.register(new ModRegions(new Identifier(ExampleFabricMod.MOD_ID, "example_mod_custom_biome"),
                RegionType.OVERWORLD, 2));

        ExampleFabricMod.LOGGER.info("Registering ModBiomes for " + ExampleFabricMod.MOD_ID);
    }
}
