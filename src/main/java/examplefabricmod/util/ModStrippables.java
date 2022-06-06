package examplefabricmod.util;

import examplefabricmod.ExampleFabricMod;
import examplefabricmod.block.ModBlocks;
import net.fabricmc.fabric.api.registry.StrippableBlockRegistry;

public class ModStrippables {

    public static void registerStrippables() {
        StrippableBlockRegistry.register(ModBlocks.JACARANDA_LOG, ModBlocks.STRIPPED_JACARANDA_LOG);
        StrippableBlockRegistry.register(ModBlocks.JACARANDA_WOOD, ModBlocks.STRIPPED_JACARANDA_WOOD);
    }

    public static void registerModStrippables() {
        ExampleFabricMod.LOGGER.info("Registering ModStrippables for " + ExampleFabricMod.MOD_ID);
        registerStrippables();
    }
}
