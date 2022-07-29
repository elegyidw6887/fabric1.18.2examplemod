package examplefabricmod.world.dimension;

import examplefabricmod.ExampleFabricMod;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionType;

public class ModDimensions {

    public static final RegistryKey<World> EXAMPLE_DIMENSION_KEY = RegistryKey.of(Registry.WORLD_KEY,
            new Identifier(ExampleFabricMod.MOD_ID, "example"));
    public static final RegistryKey<DimensionType> EXAMPLE_DIMENSION_TYPE = RegistryKey.of(Registry.DIMENSION_TYPE_KEY,
            EXAMPLE_DIMENSION_KEY.getValue());

    public static void registerModDimensions() {

        ExampleFabricMod.LOGGER.info("Registering ModDimensions for " + ExampleFabricMod.MOD_ID);
    }
}
