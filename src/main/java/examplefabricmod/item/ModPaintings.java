package examplefabricmod.item;

import examplefabricmod.ExampleFabricMod;
import net.minecraft.entity.decoration.painting.PaintingMotive;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModPaintings {

    public static final PaintingMotive MARATHON = registerPaintings("marathon", new PaintingMotive(16, 16));
    public static final PaintingMotive FAMILY = registerPaintings("family", new PaintingMotive(16, 32));

    private static PaintingMotive registerPaintings(String name, PaintingMotive paintingMotive) {
        return Registry.register(Registry.PAINTING_MOTIVE, new Identifier(ExampleFabricMod.MOD_ID, name), paintingMotive);
    }

    public static void registerModPaintings() {
        ExampleFabricMod.LOGGER.info("Registering Paintings for " + ExampleFabricMod.MOD_ID);
    }
}
