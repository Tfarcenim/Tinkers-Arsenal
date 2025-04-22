package io.github.redstoneparadox.tinkersarsenal.datagen;

import io.github.redstoneparadox.tinkersarsenal.materials.ArsenalToolMaterials;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.Tiers;
import slimeknights.tconstruct.library.data.material.AbstractMaterialDataProvider;
import slimeknights.tconstruct.library.data.material.AbstractMaterialStatsDataProvider;
import slimeknights.tconstruct.library.materials.definition.Material;
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
        addMaterialStats(ArsenalToolMaterials.GOLD,
                new HeadMaterialStats(31, 12, Tiers.GOLD, 2),
                HandleMaterialStats.multipliers().durability(.1f).build(),
                StatlessMaterialStats.BINDING,
                new LimbMaterialStats(31,.5f,1.5f,0));
        //addStats(gold, 14, 12.00f, 2.00f, HV0ROCK, 0.1f, -10, -7, 0.5f, 1.5f, 1f);
        addMaterialStats(ArsenalToolMaterials.DIAMOND,new HeadMaterialStats(1561,8,Tiers.DIAMOND,5),
                HandleMaterialStats.multipliers().durability(1.1f).build(),
                StatlessMaterialStats.BINDING,
                new LimbMaterialStats(31,.5f,1.75f,.1f));

        //addStats(diamond, 1270, 6.00f, 5.00f, HV3OBI, 1.1f, 100, 269, 0.5f, 1.5f, 1.75f);


    }


        //Finally, we add the stats here.
        private static void addStats(Material material, int dur, float spd, float dmg, int harvest, float mod, int handleDur, int exDur, float draw, float range, float bonusDmg) {
    //        TinkerRegistry.addMaterialStats(material,
    //                new HeadMaterialStats(dur, spd, dmg, harvest),
    //                new HandleMaterialStats(mod, handleDur),
    //                new ExtraMaterialStats(exDur),
    //                new BowMaterialStats(draw, range, bonusDmg)
    //        );
        }
}
