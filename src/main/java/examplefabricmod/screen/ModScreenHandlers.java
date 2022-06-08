package examplefabricmod.screen;

import examplefabricmod.ExampleFabricMod;
import net.fabricmc.fabric.api.screenhandler.v1.ScreenHandlerRegistry;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;

public class ModScreenHandlers {

    public static ScreenHandlerType<InjectionBenchScreenHandler> INJECTION_BENCH_SCREEN_HANDLER;

    public static void registerScreenHandlers() {
        INJECTION_BENCH_SCREEN_HANDLER = ScreenHandlerRegistry.registerSimple(new Identifier(ExampleFabricMod.MOD_ID, "injection_bench"),
                InjectionBenchScreenHandler::new);

        ExampleFabricMod.LOGGER.info("Registering ModScreenHandlers for " + ExampleFabricMod.MOD_ID);
    }
}
