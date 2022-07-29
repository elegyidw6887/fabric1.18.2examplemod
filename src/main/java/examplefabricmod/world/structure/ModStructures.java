package examplefabricmod.world.structure;

import examplefabricmod.ExampleFabricMod;
import examplefabricmod.mixin.StructureFeatureAccessor;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.StructureFeature;

public class ModStructures {

    public static StructureFeature<?> SKY_STRUCTURE = new SkyStructures();

    public static void registerStructureFeatures() {

        StructureFeatureAccessor.callRegister(ExampleFabricMod.MOD_ID + ":sky_structure", SKY_STRUCTURE, GenerationStep.Feature.SURFACE_STRUCTURES);

        ExampleFabricMod.LOGGER.info("Registering ModStructures for " + ExampleFabricMod.MOD_ID);
    }
}
