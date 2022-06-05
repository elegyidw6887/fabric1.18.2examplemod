package firstfabricmod.item;

import firstfabricmod.FirstFabricMod;
import firstfabricmod.block.ModBlocks;
import firstfabricmod.entity.CubeEntity.CubeEntity;
import firstfabricmod.fluid.ModFluids;
import firstfabricmod.sound.ModSounds;
import firstfabricmod.util.ModItemGroup;
import firstfabricmod.util.publicConstructor.ModAxeItem;
import firstfabricmod.util.publicConstructor.ModHoeItem;
import firstfabricmod.util.publicConstructor.ModMusicDiscItem;
import firstfabricmod.util.publicConstructor.ModPickaxeItem;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.*;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;

import java.util.List;

public class ModItems {

    // 紫水晶物品
    public static final Item AMETHYST = registerItem("amethyst",
            new Item(new FabricItemSettings().group(ModItemGroup.LOSTsMOD).maxCount(64)){
                public void appendTooltip(ItemStack itemStack, World world, List<Text> tooltip, TooltipContext tooltipContext){
                    tooltip.add(new TranslatableText("item.firstfabricmod.amethyst.tooltip"));
                }
            });
    // 紫水晶碎片燃料物品
    public static final Item FRAGMENT_OF_AMETHYST = registerItem("fragment_of_amethyst",
            new Item(new FabricItemSettings().group(ModItemGroup.LOSTsMOD).maxCount(64)));
    // 紫水晶工具
    public static final Item AMETHYST_SHOVEL = registerItem("amethyst_shovel",
            new ShovelItem(ModToolMaterials.AMETHYST, 1, -3F, new FabricItemSettings().group(ModItemGroup.LOSTsMOD)));
    public static final Item AMETHYST_SWORD = registerItem("amethyst_sword",
            new SwordItem(ModToolMaterials.AMETHYST, 3, -2.4F, new FabricItemSettings().group(ModItemGroup.LOSTsMOD)));
    public static final Item AMETHYST_AXE = registerItem("amethyst_axe",
            new ModAxeItem(ModToolMaterials.AMETHYST,4, -3F, new FabricItemSettings().group(ModItemGroup.LOSTsMOD)));
    public static final Item AMETHYST_HOE = registerItem("amethyst_hoe",
            new ModHoeItem(ModToolMaterials.AMETHYST,1 ,0F, new FabricItemSettings().group(ModItemGroup.LOSTsMOD)));
    public static final Item AMETHYST_PICKAXE = registerItem("amethyst_pickaxe",
            new ModPickaxeItem(ModToolMaterials.AMETHYST,2,-2.8F, new FabricItemSettings().group(ModItemGroup.LOSTsMOD)));
    // 紫水晶盔甲
    public static final Item AMETHYST_HELMET = registerItem("amethyst_helmet",
            new ArmorItem(ModArmorMaterials.AMETHYST, EquipmentSlot.HEAD, new FabricItemSettings().group(ModItemGroup.LOSTsMOD)));
    public static final Item AMETHYST_CHESTLAPTE = registerItem("amethyst_chestplate",
            new ArmorItem(ModArmorMaterials.AMETHYST, EquipmentSlot.CHEST, new FabricItemSettings().group(ModItemGroup.LOSTsMOD)));
    public static final Item AMETHYST_LEGGINGS = registerItem("amethyst_leggings",
            new ArmorItem(ModArmorMaterials.AMETHYST, EquipmentSlot.LEGS, new FabricItemSettings().group(ModItemGroup.LOSTsMOD)));
    public static final Item AMETHYST_BOOTS = registerItem("amethyst_boots",
            new ArmorItem(ModArmorMaterials.AMETHYST, EquipmentSlot.FEET, new FabricItemSettings().group(ModItemGroup.LOSTsMOD)));
    // 葡萄食物物品
    public static final Item GRAPE = registerItem("grape",
            new Item(new FabricItemSettings().group(ModItemGroup.LOSTsMOD).food(ModFoodComponents.GRAPE)));
    // 葡萄作物种子物品
    public static final Item GRAPE_SEEDS = registerItem("grape_seeds",
            new AliasedBlockItem(ModBlocks.GRAPE_VINE, new FabricItemSettings().group(ModItemGroup.LOSTsMOD)));
    // 唱片物品
    public static final Item BAR_BRAWL_MUSIC_DISC = registerItem("bar_brawl_music_disc",
            new ModMusicDiscItem(7, ModSounds.BAR_BRAWL, new FabricItemSettings().group(ModItemGroup.LOSTsMOD).maxCount(1)));
    // 紫水晶弓物品
    public static final Item AMETHYST_BOW = registerItem("amethyst_bow",
            new BowItem(new FabricItemSettings().group(ModItemGroup.LOSTsMOD).maxCount(1).maxDamage(640)));
    // 血桶物品
    public static final Item BLOOD_BUCKET = registerItem("blood_bucket",
            new BucketItem(ModFluids.STILL_BLOOD, new FabricItemSettings().recipeRemainder(Items.BUCKET).maxCount(1).group(ModItemGroup.LOSTsMOD)));
    // 实体刷怪蛋物品
    public static final Item CUBE_ENTITY_EGG = registerItem("cube_entity_egg",
            new SpawnEggItem(CubeEntity.CUBE_ENTITY,
                    12895428, 11382189, new Item.Settings().group(ModItemGroup.LOSTsMOD)));



    private static Item registerItem(String name, Item item){ // 注册物品
        return Registry.register(Registry.ITEM, new Identifier(FirstFabricMod.MOD_ID, name), item);
    }

    public static void registerModItems(){ // 外部调用方法，提供给主类调用
        FirstFabricMod.LOGGER.info("Registering ModItems for " + FirstFabricMod.MOD_ID);
    }
}
