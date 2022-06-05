package firstfabricmod.fluid;

import firstfabricmod.FirstFabricMod;
import firstfabricmod.fluid.customFluid.BloodFluid;
import net.minecraft.fluid.FlowableFluid;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModFluids {

    public static FlowableFluid STILL_BLOOD = registerFlowableFluid("still_blood", new BloodFluid.Still());
    public static FlowableFluid FLOWING_BLOOD = registerFlowableFluid("flowing_blood", new BloodFluid.Flowing());

    private static FlowableFluid registerFlowableFluid(String name, FlowableFluid flowableFluid) {
        return Registry.register(Registry.FLUID, new Identifier(FirstFabricMod.MOD_ID, name), flowableFluid);
    }

    public static void registerModFluids() {
        FirstFabricMod.LOGGER.info("Registering ModFluids for " + FirstFabricMod.MOD_ID);
    }
}
