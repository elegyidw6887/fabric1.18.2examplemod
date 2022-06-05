package firstfabricmod.enchantment;

import firstfabricmod.FirstFabricMod;
import firstfabricmod.enchantment.customEnchantment.FreezeEnchantment;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModEnchantments {

    public static final Enchantment FROST = registerEnchantment("freeze",
            new FreezeEnchantment());

    public static Enchantment registerEnchantment(String name, Enchantment enchantment) {
        return Registry.register(Registry.ENCHANTMENT, new Identifier(FirstFabricMod.MOD_ID, name), enchantment);
    }

    public static void registerModEnchantments() {
        FirstFabricMod.LOGGER.info("Registering ModEnchantments for " + FirstFabricMod.MOD_ID);
    }
}
