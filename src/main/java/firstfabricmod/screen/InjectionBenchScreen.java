package firstfabricmod.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import firstfabricmod.FirstFabricMod;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class InjectionBenchScreen extends HandledScreen<InjectionBenchScreenHandler> {

    private static final Identifier TEXTURE = new Identifier(FirstFabricMod.MOD_ID, "textures/gui/injection_bench_gui.png");

    public InjectionBenchScreen(InjectionBenchScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
    }

    @Override
    protected void init() {
        // 用于初始化的方法
        super.init();
        titleX = (backgroundWidth - textRenderer.getWidth(title)) / 2;
    }

    @Override
    protected void drawBackground(MatrixStack matrices, float delta, int mouseX, int mouseY) {
        // 用于UI校正的方法
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f);
        RenderSystem.setShaderTexture(0, TEXTURE);
        int x = (width - backgroundWidth) / 2;
        int y = (height - backgroundHeight) / 2;
        this.drawTexture(matrices, x, y, 0, 0, backgroundWidth, backgroundHeight);

        if (handler.isCrafting()) {
            drawTexture(matrices, x + 84, y + 22, 176, 14, handler.getScaledProgress(), 36);
            // x的84与y的22就是在GUI中进度条最左上角的像素点位置（我们想要进度条开始进行的位置）
            // u的176与v的14就是我们在GUI中为其准备的进度条的最左上角的像素点位置（我们准备好的进度条）
        }

        if (handler.hasFuel()) {
            drawTexture(matrices, x + 18, y + 23 + 14 - handler.getScaledFuelProgress(), 176,
                    14 - handler.getScaledFuelProgress(), 14, handler.getScaledFuelProgress());
            //
        }
    }

    @Override
    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        // 用于渲染的方法
        // 方法实现参考“AbstractFurnaceScreen”中的实现
        this.renderBackground(matrices);
        super.render(matrices, mouseX, mouseY, delta);
        this.drawMouseoverTooltip(matrices, mouseX, mouseY);
    }
}
