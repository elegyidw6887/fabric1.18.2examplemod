package firstfabricmod.util;

import firstfabricmod.FirstFabricMod;
import firstfabricmod.item.ModItems;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

public class ModItemGroup {

    public static final ItemGroup LOSTsMOD = FabricItemGroupBuilder.build(
            new Identifier(FirstFabricMod.MOD_ID, "group1"),
            () -> new ItemStack(ModItems.AMETHYST));

}
