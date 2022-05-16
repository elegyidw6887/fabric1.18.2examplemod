package firstfabricmod.entity.CubeEntity;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;

import static firstfabricmod.FirstFabricMod.MOD_ID;

public class CubeEntity extends PathAwareEntity {

    public static final EntityType<CubeEntity> CUBE_ENTITY = Registry.register(
            Registry.ENTITY_TYPE,
            new Identifier(MOD_ID,"cube_entity"),
            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, CubeEntity::new).dimensions(EntityDimensions.fixed(0.75f, 0.75f)).build()
    );

    protected CubeEntity(EntityType<? extends PathAwareEntity> entityType, World world) {
        super(entityType, world);
    }

    public static void r_CubeEntity(){
        FabricDefaultAttributeRegistry.register(CUBE_ENTITY, CubeEntity.createMobAttributes());
    }
}
