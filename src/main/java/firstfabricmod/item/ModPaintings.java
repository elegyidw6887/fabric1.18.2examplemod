package firstfabricmod.item;

import firstfabricmod.FirstFabricMod;
import net.minecraft.entity.decoration.painting.PaintingMotive;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModPaintings {

    public static final PaintingMotive MARATHON = registerPaintings("marathon", new PaintingMotive(16, 16));
    public static final PaintingMotive FAMILY = registerPaintings("family", new PaintingMotive(16, 32));

    private static PaintingMotive registerPaintings(String name, PaintingMotive paintingMotive){
        return Registry.register(Registry.PAINTING_MOTIVE, new Identifier(FirstFabricMod.MOD_ID, name), paintingMotive);
    }

    public static void registerModPaintings(){
        FirstFabricMod.LOGGER.info("Registering Paintings for " + FirstFabricMod.MOD_ID);
    }
}
