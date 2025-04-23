package io.github.redstoneparadox.tinkersarsenal.datagen.data;

import io.github.redstoneparadox.tinkersarsenal.materials.ArsenalToolMaterials;
import io.github.redstoneparadox.tinkersarsenal.traits.ArsenalToolTraits;
import net.minecraft.data.PackOutput;
import slimeknights.tconstruct.library.data.material.AbstractMaterialDataProvider;
import slimeknights.tconstruct.library.data.material.AbstractMaterialTraitDataProvider;

public class TAMaterialTraitsDataProvider extends AbstractMaterialTraitDataProvider {
    public TAMaterialTraitsDataProvider(PackOutput packOutput, AbstractMaterialDataProvider materials) {
        super(packOutput, materials);
    }

    @Override
    protected void addMaterialTraits() {
        addDefaultTraits(ArsenalToolMaterials.GOLD, ArsenalToolTraits.malleable);
        addDefaultTraits(ArsenalToolMaterials.DIAMOND, ArsenalToolTraits.resilience);
    }

    @Override
    public String getName() {
        return "Tinker's Boomstick Material Traits";
    }
}
