package examplefabricmod.item;

import examplefabricmod.ExampleFabricMod;
import examplefabricmod.effect.ModEffects;
import examplefabricmod.mixin.BrewingRecipeRegistryMixin;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.potion.Potion;
import net.minecraft.potion.Potions;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModPotions {

    public static Potion FREEZE_POTION = registerPotion("freeze_potion",
            new Potion(new StatusEffectInstance(ModEffects.FREEZE, 200, 0)));

    private static Potion registerPotion(String name, Potion potion) {
        return Registry.register(Registry.POTION, new Identifier(ExampleFabricMod.MOD_ID, name), potion);
    }

    private static void registerPotionRecipes() {
        BrewingRecipeRegistryMixin.invokeRegisterPotionRecipe(Potions.AWKWARD, ModItems.AMETHYST, ModPotions.FREEZE_POTION);
    }

    public static void registerModPotions() {
        ExampleFabricMod.LOGGER.info("Registering ModPotions for " + ExampleFabricMod.MOD_ID);
        registerPotionRecipes();
    }
}
