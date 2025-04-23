package io.github.redstoneparadox.tinkersarsenal.datagen.data;

import io.github.redstoneparadox.tinkersarsenal.materials.ArsenalToolMaterials;
import net.minecraft.data.PackOutput;
import slimeknights.tconstruct.library.data.material.AbstractMaterialDataProvider;

public class TAMaterialDataProvider extends AbstractMaterialDataProvider {
    public TAMaterialDataProvider(PackOutput packOutput) {
        super(packOutput);
    }

    @Override
    protected void addMaterials() {
        addMaterial(ArsenalToolMaterials.GOLD,1,ORDER_GENERAL,true);
        addMaterial(ArsenalToolMaterials.DIAMOND,3,ORDER_GENERAL,true);
    }

    @Override
    public String getName() {
        return "Tinker's Boomstick Materials";
    }
}
