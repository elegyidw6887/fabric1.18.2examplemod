package firstfabricmod.block;

import firstfabricmod.FirstFabricMod;
import firstfabricmod.block.customBlock.AmethystLampBlock;
import firstfabricmod.block.customBlock.GrapeVineBlock;
import firstfabricmod.block.customBlock.InjectionBenchBlock;
import firstfabricmod.fluid.ModFluids;
import firstfabricmod.util.ModItemGroup;
import firstfabricmod.util.publicConstructor.ModFluidBlock;
import firstfabricmod.util.publicConstructor.ModSaplingBlock;
import firstfabricmod.world.feature.tree.JacarandaSaplingGenerator;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;

import java.util.List;

public class ModBlocks {

    // 紫水晶块与矿石
    public static final Block AMETHYST_BLOCK = registerBlock("amethyst_block",
            new Block(FabricBlockSettings.of(Material.METAL).hardness(4.0F).requiresTool()), ModItemGroup.LOSTsMOD);
    public static final Block AMETHYST_ORE = registerBlock("amethyst_ore",
            new OreBlock(FabricBlockSettings.of(Material.STONE).hardness(3.0F).requiresTool(), UniformIntProvider.create(2, 6)), ModItemGroup.LOSTsMOD);
    public static final Block DEEPSLATE_AMETHYST_ORE = registerBlock("deepslate_amethyst_ore",
            new OreBlock(FabricBlockSettings.of(Material.STONE).hardness(4.5F).requiresTool(), UniformIntProvider.create(2, 6)), ModItemGroup.LOSTsMOD);
    // 花方块
    public static final Block LILAC_FLOWER = registerBlock("lilac_flower",
            new FlowerBlock(StatusEffects.FIRE_RESISTANCE, 12,
                    FabricBlockSettings.copy(Blocks.DANDELION).strength(4.0F).nonOpaque()), ModItemGroup.LOSTsMOD);
    // 紫水晶灯方块
    public static final Block AMETHYST_LAMP_BLOCK = registerBlock("amethyst_lamp_block",
            new AmethystLampBlock(FabricBlockSettings.of(Material.STONE).hardness(4.5F).requiresTool()
                    .luminance((state) -> (state.get(AmethystLampBlock.CLICKED) ? 15 : 0)) // 三目运算符
            ), ModItemGroup.LOSTsMOD);
    // 葡萄作物方块
    public static final Block GRAPE_VINE = registerBlockWithoutBlockItem("grape_vine",
            new GrapeVineBlock(FabricBlockSettings.copy(Blocks.WHEAT).nonOpaque()));
    // 血流体方块
    public static final Block BLOOD = registerBlockWithoutBlockItem("blood_block",
            new ModFluidBlock(ModFluids.STILL_BLOOD, FabricBlockSettings.of(Material.WATER).noCollision().nonOpaque().dropsNothing()));
    // 原木、木头、去皮原木以及去皮木头方块
    public static final Block JACARANDA_LOG = registerBlock("jacaranda_log",
            new PillarBlock(FabricBlockSettings.copy(Blocks.OAK_LOG).strength(4.0F).requiresTool()), ModItemGroup.LOSTsMOD);
    public static final Block JACARANDA_WOOD = registerBlock("jacaranda_wood",
            new PillarBlock(FabricBlockSettings.copy(Blocks.OAK_WOOD).strength(4.0F).requiresTool()), ModItemGroup.LOSTsMOD);
    public static final Block STRIPPED_JACARANDA_LOG = registerBlock("stripped_jacaranda_log",
            new PillarBlock(FabricBlockSettings.copy(Blocks.STRIPPED_OAK_LOG).strength(4.0F).requiresTool()), ModItemGroup.LOSTsMOD);
    public static final Block STRIPPED_JACARANDA_WOOD = registerBlock("stripped_jacaranda_wood",
            new PillarBlock(FabricBlockSettings.copy(Blocks.STRIPPED_OAK_WOOD).strength(4.0F).requiresTool()), ModItemGroup.LOSTsMOD);
    // 木板方块
    public static final Block JACARANDA_PLANKS = registerBlock("jacaranda_planks",
            new Block(FabricBlockSettings.copy(Blocks.OAK_PLANKS).strength(4.0F).requiresTool()), ModItemGroup.LOSTsMOD);
    // 树苗方块
    public static final Block JACARANDA_SAPLING = registerBlock("jacaranda_sapling",
            new ModSaplingBlock(new JacarandaSaplingGenerator(),
                    FabricBlockSettings.copy(Blocks.OAK_SAPLING).strength(4.0F).nonOpaque()), ModItemGroup.LOSTsMOD);
    // 注入工作台方块
    public static final Block INJECTION_BENCH = registerBlock("injection_bench",
            new InjectionBenchBlock(FabricBlockSettings.of(Material.METAL).requiresTool().nonOpaque()), ModItemGroup.LOSTsMOD);




    private static Block registerBlock(String name, Block block, ItemGroup itemGroup){ // 注册方块的同时注册一个对应的物品
        // 调用类中方块相关物品对象注册方法，同时做到方块与方块相关物品对象的注册
        registerBlockItem(name, block, itemGroup);
        return Registry.register(Registry.BLOCK, new Identifier(FirstFabricMod.MOD_ID, name), block);
    }

    private static Block registerBlockWithTooltip(String name, Block block, ItemGroup itemGroup, String tooltips){ // 注册方块的同时注册一个有物品提示的对应物品
        // 调用类中方块相关物品对象注册方法，同时做到方块与方块相关物品对象的注册
        registerBlockItemWithTooltip(name, block, itemGroup, tooltips);
        return Registry.register(Registry.BLOCK, new Identifier(FirstFabricMod.MOD_ID, name), block);
    }

    private static Block registerBlockWithoutBlockItem(String name, Block block){ // 注册方块的同时不进行物品的注册
        return Registry.register(Registry.BLOCK, new Identifier(FirstFabricMod.MOD_ID, name), block);
    }

    private static void registerBlockItem(String name, Block block, ItemGroup itemGroup){ // 注册与方块对应的物品
        Registry.register(Registry.ITEM, new Identifier(FirstFabricMod.MOD_ID, name),
                new BlockItem(block, new FabricItemSettings().group(itemGroup)));
    }

    private static void registerBlockItemWithTooltip(String name, Block block, ItemGroup itemGroup, String tooltips){ // 注册与方块对应并且有物品提示的物品
        Registry.register(Registry.ITEM, new Identifier(FirstFabricMod.MOD_ID, name),
                new BlockItem(block, new FabricItemSettings().group(itemGroup)){
                    public void appendTooltip(ItemStack itemStack, World world, List<Text> tooltip, TooltipContext tooltipContext){
                        tooltip.add(new TranslatableText(tooltips));
                    }
                });
    }

    public static void registerModBlocks(){ // 外部调用方法，提供给主类调用
        FirstFabricMod.LOGGER.info("Registering ModBlocks for " + FirstFabricMod.MOD_ID);
    }
}
