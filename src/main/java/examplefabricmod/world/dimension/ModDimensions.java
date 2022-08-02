package examplefabricmod.world.dimension;

import examplefabricmod.ExampleFabricMod;
import examplefabricmod.block.ModBlocks;
import examplefabricmod.item.ModItems;
import net.kyrptonaught.customportalapi.api.CustomPortalBuilder;
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

        CustomPortalBuilder.beginPortal()
                .frameBlock(ModBlocks.AMETHYST_BLOCK)
                .destDimID(EXAMPLE_DIMENSION_KEY.getValue())
                .tintColor(45, 79, 135)
                .lightWithItem(ModItems.AMETHYST)
                .onlyLightInOverworld()
                .registerPortal();

        ExampleFabricMod.LOGGER.info("Registering ModDimensions for " + ExampleFabricMod.MOD_ID);
    }
}
