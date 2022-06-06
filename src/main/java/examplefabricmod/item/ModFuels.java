package examplefabricmod.item;

import examplefabricmod.ExampleFabricMod;
import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.minecraft.item.Item;

public class ModFuels {

    private static void registerFuel(Item item, Integer integer) {
        FuelRegistry registry = FuelRegistry.INSTANCE;
        registry.add(item, integer);
    }

    public static void registerModFuels() {
        ExampleFabricMod.LOGGER.info("Registering ModFuels for " + ExampleFabricMod.MOD_ID);
        registerFuel(ModItems.FRAGMENT_OF_AMETHYST, 2000);
    }
}
