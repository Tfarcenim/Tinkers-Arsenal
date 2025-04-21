package io.github.redstoneparadox.tinkersarsenal.client.rendering;

import io.github.redstoneparadox.tinkersarsenal.entities.BoomstickShotEntity;
import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;

/**
 * Created by RedstoneParadox on 8/3/2018.
 */
public class RenderBoomstickShot extends ArrowRenderer<BoomstickShotEntity> {


    public RenderBoomstickShot(EntityRendererProvider.Context pContext) {
        super(pContext);
    }


    @Override
    public ResourceLocation getTextureLocation(BoomstickShotEntity pEntity) {
        return null;//todo
    }
}
