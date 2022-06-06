package examplefabricmod.recipe;

import examplefabricmod.ExampleFabricMod;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModRecipes {

    public static void registerModRecipes() {

        Registry.register(Registry.RECIPE_SERIALIZER, new Identifier(ExampleFabricMod.MOD_ID, InjectionBenchRecipe.Serializer.ID),
                InjectionBenchRecipe.Serializer.INSTANCE);
        Registry.register(Registry.RECIPE_TYPE, new Identifier(ExampleFabricMod.MOD_ID, InjectionBenchRecipe.Type.ID),
                InjectionBenchRecipe.Type.INSTANCE);

        ExampleFabricMod.LOGGER.info("Registering ModRecipes for " + ExampleFabricMod.MOD_ID);
    }
}
