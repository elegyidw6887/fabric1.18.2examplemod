package firstfabricmod.recipe;

import firstfabricmod.FirstFabricMod;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModRecipes {

    public static void registerModRecipes(){

        Registry.register(Registry.RECIPE_SERIALIZER, new Identifier(FirstFabricMod.MOD_ID, InjectionBenchRecipe.Serializer.ID),
                InjectionBenchRecipe.Serializer.INSTANCE);
        Registry.register(Registry.RECIPE_TYPE, new Identifier(FirstFabricMod.MOD_ID, InjectionBenchRecipe.Type.ID),
                InjectionBenchRecipe.Type.INSTANCE);

        FirstFabricMod.LOGGER.info("Registering ModRecipes for " + FirstFabricMod.MOD_ID);
    }
}
