package firstfabricmod.util;

import firstfabricmod.FirstFabricMod;
import firstfabricmod.block.ModBlocks;
import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;

public class ModFlammableBlocks {

    private static void registerFlammableBlock(){
        FlammableBlockRegistry instance = FlammableBlockRegistry.getDefaultInstance();

        instance.add(ModBlocks.JACARANDA_LOG, 5, 5);
        instance.add(ModBlocks.JACARANDA_WOOD, 5, 5);
        instance.add(ModBlocks.STRIPPED_JACARANDA_LOG, 5, 5);
        instance.add(ModBlocks.STRIPPED_JACARANDA_WOOD, 5, 5);
        instance.add(ModBlocks.JACARANDA_PLANKS, 5, 20);
        instance.add(ModBlocks.JACARANDA_LEAVES, 20, 50);
    }

    public static void registerModFlammableBlocks(){
        FirstFabricMod.LOGGER.info("Registering ModFlammableBlocks for " + FirstFabricMod.MOD_ID);
        registerFlammableBlock();
    }
}
