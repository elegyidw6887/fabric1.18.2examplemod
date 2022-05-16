package firstfabricmod.item;

import firstfabricmod.FirstFabricMod;
import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.minecraft.item.Item;

public class ModFuels {

    private static void registerFuel(Item item, Integer integer){
        FuelRegistry registry = FuelRegistry.INSTANCE;
        registry.add(item, integer);
    }

    public static void registerModFuels(){
        FirstFabricMod.LOGGER.info("Registering ModFuels for " + FirstFabricMod.MOD_ID);
        registerFuel(ModItems.FRAGMENT_OF_AMETHYST, 2000);
    }
}
