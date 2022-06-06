package examplefabricmod.enchantment;

import examplefabricmod.ExampleFabricMod;
import examplefabricmod.enchantment.customEnchantment.FreezeEnchantment;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModEnchantments {

    public static final Enchantment FROST = registerEnchantment("freeze",
            new FreezeEnchantment());

    public static Enchantment registerEnchantment(String name, Enchantment enchantment) {
        return Registry.register(Registry.ENCHANTMENT, new Identifier(ExampleFabricMod.MOD_ID, name), enchantment);
    }

    public static void registerModEnchantments() {
        ExampleFabricMod.LOGGER.info("Registering ModEnchantments for " + ExampleFabricMod.MOD_ID);
    }
}
