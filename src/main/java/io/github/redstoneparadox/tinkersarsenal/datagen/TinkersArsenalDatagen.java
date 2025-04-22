package io.github.redstoneparadox.tinkersarsenal.datagen;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraftforge.data.event.GatherDataEvent;
import slimeknights.tconstruct.library.data.material.AbstractMaterialDataProvider;

public class TinkersArsenalDatagen {
    public static void gather(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        PackOutput output = generator.getPackOutput();
        AbstractMaterialDataProvider abstractMaterialDataProvider = new TAMaterialDataProvider(output);
        generator.addProvider(event.includeServer(),abstractMaterialDataProvider);
        generator.addProvider(event.includeServer(),new TAMaterialStatsDataProvider(output,abstractMaterialDataProvider));
    }
}
