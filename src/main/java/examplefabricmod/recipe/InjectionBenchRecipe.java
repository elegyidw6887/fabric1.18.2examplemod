package examplefabricmod.recipe;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.recipe.*;
import net.minecraft.util.Identifier;
import net.minecraft.util.JsonHelper;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;

public class InjectionBenchRecipe implements Recipe<SimpleInventory> {

    private final Identifier id;
    private final ItemStack output;
    private final DefaultedList<Ingredient> recipeItems;

    // 构造方法
    public InjectionBenchRecipe(Identifier id, ItemStack output, DefaultedList<Ingredient> recipeItems) {
        this.id = id;
        this.output = output;
        this.recipeItems = recipeItems;
    }

    // 匹配方法
    @Override
    public boolean matches(SimpleInventory inventory, World world) { // 如果要修改配方，此处为需要修改的方法1
        if (world.isClient) {
            return false;
        }
        if (recipeItems.get(0).test(inventory.getStack(1))) {
            return recipeItems.get(1).test(inventory.getStack(2));
        }
        return false;
    }

    // 制作方法
    @Override
    public ItemStack craft(SimpleInventory inventory) {
        return output;
    }

    // 适配方法
    @Override
    public boolean fits(int width, int height) {
        return true;
    }

    // 获取输出方法
    @Override
    public ItemStack getOutput() {
        return output.copy();
    }

    // 获取ID方法
    @Override
    public Identifier getId() {
        return id;
    }

    // 获取串行器方法
    @Override
    public RecipeSerializer<?> getSerializer() {
        return Serializer.INSTANCE;
    }

    // 获取类型方法
    @Override
    public RecipeType<?> getType() {
        return Type.INSTANCE;
    }

    public static class Type implements RecipeType<InjectionBenchRecipe> {
        private Type() {}
        public static final Type INSTANCE = new Type();
        public static final String ID = "injection_bench";
    }

    public static class Serializer implements RecipeSerializer<InjectionBenchRecipe> { // 如果要修改配方，此处为需要修改的方法2

        public static final Serializer INSTANCE = new Serializer();
        public static final String ID = "injection_bench";

        @Override
        public InjectionBenchRecipe read(Identifier id, JsonObject json) { // 读取json文件的方法

            ItemStack output = ShapedRecipe.outputFromJson(JsonHelper.getObject(json, "output"));
            JsonArray ingredients = JsonHelper.getArray(json, "ingredients");
            DefaultedList<Ingredient> inputs = DefaultedList.ofSize(2, Ingredient.EMPTY);

            for (int i = 0; i < inputs.size(); i++) {
                inputs.set(i, Ingredient.fromJson(ingredients.get(i)));
            }

            return new InjectionBenchRecipe(id, output, inputs);
        }

        @Override
        public InjectionBenchRecipe read(Identifier id, @NotNull PacketByteBuf buf) { // 针对网络，PacketByteBuf

            DefaultedList<Ingredient> inputs = DefaultedList.ofSize(buf.readInt(), Ingredient.EMPTY);

            inputs.replaceAll(ignored -> Ingredient.fromPacket(buf));

            ItemStack output = buf.readItemStack();
            return new InjectionBenchRecipe(id, output, inputs);
        }

        @Override
        public void write(@NotNull PacketByteBuf buf, @NotNull InjectionBenchRecipe recipe) { // 针对网络，PacketByteBuf

            buf.writeInt(recipe.getIngredients().size());

            for (Ingredient ing : recipe.getIngredients()) {
                ing.write(buf);
            }

            buf.writeItemStack(recipe.getOutput());
        }
    }
}
