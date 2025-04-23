package io.github.redstoneparadox.tinkersarsenal.datagen;

import io.github.redstoneparadox.tinkersarsenal.materials.ArsenalToolMaterials;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;
import slimeknights.tconstruct.library.client.data.material.AbstractMaterialRenderInfoProvider;
import slimeknights.tconstruct.library.client.data.material.AbstractMaterialSpriteProvider;

public class TAMaterialRenderInfoProvider extends AbstractMaterialRenderInfoProvider {
    public TAMaterialRenderInfoProvider(PackOutput packOutput, @Nullable AbstractMaterialSpriteProvider materialSprites, @Nullable ExistingFileHelper existingFileHelper) {
        super(packOutput, materialSprites, existingFileHelper);
    }

    @Override
    protected void addMaterialRenderInfo() {
        buildRenderInfo(ArsenalToolMaterials.DIAMOND).color(0x33ebcb);
    }

    @Override
    public String getName() {
        return "Tinker's Boomstick Material Render Info Provider";
    }
}
