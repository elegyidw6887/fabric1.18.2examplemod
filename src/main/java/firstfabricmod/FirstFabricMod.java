package firstfabricmod;

import firstfabricmod.block.ModBlocks;
import firstfabricmod.effect.ModEffects;
import firstfabricmod.enchantment.ModEnchantments;
import firstfabricmod.entity.CubeEntity.CubeEntity;
import firstfabricmod.entity.ModBlockEntities;
import firstfabricmod.fluid.ModFluids;
import firstfabricmod.item.ModFuels;
import firstfabricmod.item.ModItems;
import firstfabricmod.item.ModPaintings;
import firstfabricmod.item.ModPotions;
import firstfabricmod.recipe.ModRecipes;
import firstfabricmod.screen.ModScreenHandlers;
import firstfabricmod.util.ModCustomTrades;
import firstfabricmod.util.ModFlammableBlocks;
import firstfabricmod.util.ModStrippables;
import firstfabricmod.world.feature.ModConfiguredFeatures;
import firstfabricmod.world.gen.ModWorldGen;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FirstFabricMod implements ModInitializer {

	public static final String MOD_ID = "firstfabricmod";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {

		ModConfiguredFeatures.registerModConfiguredFeatures();

		ModItems.registerModItems();

		ModBlocks.registerModBlocks();

		ModFuels.registerModFuels();

		ModEnchantments.registerModEnchantments();

		ModPaintings.registerModPaintings();

		ModCustomTrades.registerCustomTrades();

		ModEffects.registerModEffects();

		ModPotions.registerModPotions();

		ModFluids.registerModFluids();

		ModFlammableBlocks.registerModFlammableBlocks();

		ModStrippables.registerModStrippables();

		ModBlockEntities.registerBlockEntities();

		ModScreenHandlers.registerScreenHandlers();

		ModRecipes.registerModRecipes();

		ModWorldGen.generateModWorldGen();

		CubeEntity.r_CubeEntity();

		LOGGER.info("Hello Fabric world!");

		/*

		Fabric学习过程中出现的问题

		1.2022.6.4
			注入工作台在使用BB制作的模型后放在左手进行放置的时候游戏会弹出并报错
			模组开发中使用仿熔炉设计时可以放置左手进行放置
			未解决

		 */
	}
}
