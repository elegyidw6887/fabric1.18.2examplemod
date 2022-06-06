package examplefabricmod.effect;

import examplefabricmod.ExampleFabricMod;
import examplefabricmod.effect.customEffect.FreezeEffect;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModEffects {

    public static StatusEffect FREEZE = registerStatusEffects("freeze", new FreezeEffect(StatusEffectCategory.HARMFUL, 3124687));

    private static StatusEffect registerStatusEffects(String name, StatusEffect statusEffect) {
        return Registry.register(Registry.STATUS_EFFECT, new Identifier(ExampleFabricMod.MOD_ID, name), statusEffect);
    }

    public static void registerModEffects() {
        ExampleFabricMod.LOGGER.info("Registering ModEffects for " + ExampleFabricMod.MOD_ID);
    }
}
