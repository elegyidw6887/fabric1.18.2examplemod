package examplefabricmod.util;

import examplefabricmod.ExampleFabricMod;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModTags {

    public static class Items {

        public static final TagKey<Item> AMETHYST = createCommonTag("amethyst");

        private static TagKey<Item> createTag(String name) { // 一个存放于resources/data/MOD_ID/tags文件夹中的标签
            return TagKey.of(Registry.ITEM_KEY, new Identifier(ExampleFabricMod.MOD_ID, name));
        }

        private static TagKey<Item> createCommonTag(String name) { // 一个存放于resources/data/minecraft/c/tags文件夹中的标签
            return TagKey.of(Registry.ITEM_KEY, new Identifier("c", name));
        }
    }

    public static class Blocks {
        private static TagKey<Block> createTag(String name) { // 一个存放于resources/data/MOD_ID/tags文件夹中的标签
            return TagKey.of(Registry.BLOCK_KEY, new Identifier(ExampleFabricMod.MOD_ID, name));
        }

        private static TagKey<Block> createCommonTag(String name) { // 一个存放于resources/data/c/tags文件夹中的标签
            return TagKey.of(Registry.BLOCK_KEY, new Identifier("c", name));
        }
    }
}
