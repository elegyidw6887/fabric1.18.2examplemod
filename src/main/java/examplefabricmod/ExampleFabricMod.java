package examplefabricmod;

import examplefabricmod.block.ModBlockEntities;
import examplefabricmod.block.ModBlocks;
import examplefabricmod.effect.ModEffects;
import examplefabricmod.enchantment.ModEnchantments;
import examplefabricmod.entity.CubeEntity.CubeEntity;
import examplefabricmod.fluid.ModFluids;
import examplefabricmod.item.ModFuels;
import examplefabricmod.item.ModItems;
import examplefabricmod.item.ModPaintings;
import examplefabricmod.item.ModPotions;
import examplefabricmod.recipe.ModRecipes;
import examplefabricmod.screen.ModScreenHandlers;
import examplefabricmod.util.ModCustomTrades;
import examplefabricmod.util.ModFlammableBlocks;
import examplefabricmod.util.ModStrippables;
import examplefabricmod.world.feature.ModConfiguredFeatures;
import examplefabricmod.world.gen.ModWorldGen;
import examplefabricmod.world.structure.ModStructures;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExampleFabricMod implements ModInitializer {

    public static final String MOD_ID = "examplefabricmod";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {

        // 模组配置功能注册
        ModConfiguredFeatures.registerModConfiguredFeatures();
        // 模组物品注册
        ModItems.registerModItems();
        // 模组方块注册
        ModBlocks.registerModBlocks();
        // 模组燃料注册
        ModFuels.registerModFuels();
        // 模组附魔注册
        ModEnchantments.registerModEnchantments();
        // 模组画注册
        ModPaintings.registerModPaintings();
        // 模组自定义交易注册
        ModCustomTrades.registerCustomTrades();
        // 模组自定义效果注册
        ModEffects.registerModEffects();
        // 模组药水注册
        ModPotions.registerModPotions();
        // 模组流体注册
        ModFluids.registerModFluids();
        // 模组可燃方块注册
        ModFlammableBlocks.registerModFlammableBlocks();
        // 模组去皮木材注册
        ModStrippables.registerModStrippables();
        // 模组方块实体注册
        ModBlockEntities.registerBlockEntities();
        // 模组屏幕处理器注册
        ModScreenHandlers.registerScreenHandlers();
        // 模组配方注册
        ModRecipes.registerModRecipes();
        // 模组世界生成配置
        ModWorldGen.generateModWorldGen();
        // 模组建筑物生成
        ModStructures.registerStructureFeatures();

        CubeEntity.r_CubeEntity();

        LOGGER.info("Hello Fabric world!");

		/*

		Fabric学习过程中出现的问题

		1.2022.5.16
			在添加自定义树叶的时候，树叶无法正常进行渲染，在游戏内会显示紫黑色方块
			未解决

		2.2022.6.4
			注入工作台在使用BB制作的模型后放在左手进行放置的时候游戏会弹出并报错
			模组开发中使用仿熔炉设计时可以放置左手进行放置
			未解决

		 */

        /*

        后续学习计划

        2.进阶内容
            -nbt数据扩展
            -mixin扩展

        3.世界生成
            -自定义建筑
            -单个建筑结构
            -拼图建筑结构

        4.实体添加
            -使用GeckoLib自定义生物实体
            -幼年动物实体
            -生物实体变体
            -生物实体世界生成
            -自定义村民
            -使用使用GeckoLib制作3D盔甲

        5.维度添加
            -自定义维度添加

        6.个人计划
            -套装盔甲效果添加
            -使用Trinkets添加遗物槽
            -添加拥有多种效果的遗物

         */

        /*

        WIKI与教程链接

        1.Fabric官方wiki
            https://fabricmc.net/wiki/start

        2.Kaupenjoe油管教程
            https://www.youtube.com/playlist?list=PLKGarocXCE1ECgBI1_Z_Yr4qMdhjVi3kg

        3.GeckoLib官方github wiki
            https://github.com/bernie-g/geckolib/wiki

        4.Trinkets官方github wiki
            https://github.com/emilyploszaj/trinkets/wiki

         */
    }
}
