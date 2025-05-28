package io.github.redstoneparadox.tinkersarsenal.client.rendering;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import io.github.redstoneparadox.tinkersarsenal.entities.BoomstickShotEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemDisplayContext;

/**
 * Created by RedstoneParadox on 8/3/2018.
 */
public class RenderBoomstickShot extends EntityRenderer<BoomstickShotEntity> {


    private final ItemRenderer itemRenderer;

    public RenderBoomstickShot(EntityRendererProvider.Context pContext) {
        super(pContext);
        this.itemRenderer = pContext.getItemRenderer();
    }


    @Override
    public void render(BoomstickShotEntity pEntity, float pEntityYaw, float pPartialTicks, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight) {
        pPoseStack.pushPose();

        pPoseStack.mulPose(Axis.YP.rotationDegrees(pEntityYaw+90));
        pPoseStack.mulPose(Axis.ZP.rotationDegrees(45));
        itemRenderer.renderStatic(pEntity.getItem(), ItemDisplayContext.FIXED,pPackedLight, OverlayTexture.NO_OVERLAY,pPoseStack,pBuffer,pEntity.level(),0);
        pPoseStack.popPose();
    }

    @Override
    public ResourceLocation getTextureLocation(BoomstickShotEntity pEntity) {
        return TextureAtlas.LOCATION_BLOCKS;//todo
    }
}
