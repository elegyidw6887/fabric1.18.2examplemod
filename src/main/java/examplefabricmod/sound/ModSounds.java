package examplefabricmod.sound;

import examplefabricmod.ExampleFabricMod;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModSounds {

    public static SoundEvent BAR_BRAWL = registerSoundEvent("bar_brawl");

    private static SoundEvent registerSoundEvent(String name) {
        Identifier id = new Identifier(ExampleFabricMod.MOD_ID, name);
        return Registry.register(Registry.SOUND_EVENT, id, new SoundEvent(id));
    }
}
