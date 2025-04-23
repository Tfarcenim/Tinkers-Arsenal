package io.github.redstoneparadox.tinkersarsenal.datagen.data;

import io.github.redstoneparadox.tinkersarsenal.materials.ArsenalToolMaterials;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.Tiers;
import slimeknights.tconstruct.library.data.material.AbstractMaterialDataProvider;
import slimeknights.tconstruct.library.data.material.AbstractMaterialStatsDataProvider;
import slimeknights.tconstruct.library.materials.definition.Material;
import slimeknights.tconstruct.tools.data.material.MaterialIds;
import slimeknights.tconstruct.tools.stats.HandleMaterialStats;
import slimeknights.tconstruct.tools.stats.HeadMaterialStats;
import slimeknights.tconstruct.tools.stats.LimbMaterialStats;
import slimeknights.tconstruct.tools.stats.StatlessMaterialStats;


public class TAMaterialStatsDataProvider extends AbstractMaterialStatsDataProvider {


    public TAMaterialStatsDataProvider(PackOutput packOutput, AbstractMaterialDataProvider materials) {
        super(packOutput, materials);
    }

    @Override
    public String getName() {
        return "Tinker's Boomstick Material Stats";
    }

    @Override
    protected void addMaterialStats() {
        addMaterialStats(ArsenalToolMaterials.DIAMOND,new HeadMaterialStats(1561,8,Tiers.DIAMOND,5),
                HandleMaterialStats.multipliers().durability(1.1f).build(),
                StatlessMaterialStats.BINDING,
                new LimbMaterialStats(31,.5f,1.75f,.1f));
    }
}
