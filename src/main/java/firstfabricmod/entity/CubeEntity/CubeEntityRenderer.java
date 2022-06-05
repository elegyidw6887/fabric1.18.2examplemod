package firstfabricmod.entity.CubeEntity;

import firstfabricmod.FirstFabricModClient;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.util.Identifier;

import static firstfabricmod.FirstFabricMod.MOD_ID;

public class CubeEntityRenderer extends MobEntityRenderer<CubeEntity, CubeEntityModel> {

    public CubeEntityRenderer(EntityRendererFactory.Context context) {
        super(context, new CubeEntityModel(context.getPart(FirstFabricModClient.MODEL_CUBE_LAYER)), 0.5F);
    }

    @Override
    public Identifier getTexture(CubeEntity entity) {
        return new Identifier(MOD_ID, "textures/entity/cube.png");
    }
}
