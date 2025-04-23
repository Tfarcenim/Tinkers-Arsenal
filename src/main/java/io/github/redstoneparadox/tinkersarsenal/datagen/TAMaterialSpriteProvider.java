package io.github.redstoneparadox.tinkersarsenal.datagen;

import io.github.redstoneparadox.tinkersarsenal.materials.ArsenalToolMaterials;
import slimeknights.tconstruct.library.client.data.material.AbstractMaterialSpriteProvider;
import slimeknights.tconstruct.library.client.data.spritetransformer.GreyToColorMapping;
import slimeknights.tconstruct.tools.data.material.MaterialIds;

public class TAMaterialSpriteProvider extends AbstractMaterialSpriteProvider {
    @Override
    public String getName() {
        return "Tinker's Boomstick Material Sprites";
    }

    @Override
    protected void addAllMaterials() {
        final int a = 0xFF000000;

        buildMaterial(ArsenalToolMaterials.DIAMOND)
                .meleeHarvest().armor()
                .fallbacks("metal").ranged()
                .colorMapper(GreyToColorMapping.builderFromBlack()
                        .addARGB(63, a|0x003535)
                        .addARGB(102, a|0x005E5E)
                        .addARGB(140, a|0x008282)
                        .addARGB(178, a|0x00A8A8)
                        .addARGB(216, a|0x00D8D8)
                        .addARGB(255, a|0x00FFFF).build());
    }
}
