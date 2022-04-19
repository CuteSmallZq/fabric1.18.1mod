package firstfabricmod;

import firstfabricmod.entity.CubeEntity;
import firstfabricmod.entity.CubeEntityModel;
import firstfabricmod.entity.CubeEntityRenderer;
import firstfabricmod.fluid.BloodFluid;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.render.fluid.v1.FluidRenderHandlerRegistry;
import net.fabricmc.fabric.api.client.render.fluid.v1.SimpleFluidRenderHandler;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.util.Identifier;

public class FirstFabricModClient implements ClientModInitializer {

    public static final EntityModelLayer MODEL_CUBE_LAYER = new EntityModelLayer(new Identifier("firstfabricmod","cube"),"main");

    @Override
    public void onInitializeClient() {

        FluidRenderHandlerRegistry.INSTANCE.register(BloodFluid.STILL_BLOOD, BloodFluid.FLOWING_BLOOD, new SimpleFluidRenderHandler(
                new Identifier("minecraft:block/water_still"),
                new Identifier("minecraft:block/water_flow"),
                0xDC143C
        ));
        BlockRenderLayerMap.INSTANCE.putFluids(RenderLayer.getTranslucent(), BloodFluid.STILL_BLOOD, BloodFluid.FLOWING_BLOOD);

        EntityRendererRegistry.register(CubeEntity.CUBE_ENTITY, CubeEntityRenderer::new);
        EntityModelLayerRegistry.registerModelLayer(MODEL_CUBE_LAYER, CubeEntityModel::getTexturedModelData);
    }
}
