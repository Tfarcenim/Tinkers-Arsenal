package io.github.redstoneparadox.tinkersarsenal.datagen;

import io.github.redstoneparadox.tinkersarsenal.datagen.assets.TAItemModelProvider;
import io.github.redstoneparadox.tinkersarsenal.datagen.assets.TALangProvider;
import io.github.redstoneparadox.tinkersarsenal.datagen.data.*;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import slimeknights.tconstruct.library.client.data.material.AbstractMaterialSpriteProvider;
import slimeknights.tconstruct.library.data.material.AbstractMaterialDataProvider;

public class TinkersArsenalDatagen {
    public static void gather(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        PackOutput output = generator.getPackOutput();
        ExistingFileHelper helper = event.getExistingFileHelper();
        var lookup = event.getLookupProvider();
        AbstractMaterialDataProvider abstractMaterialDataProvider = new TAMaterialDataProvider(output);
        generator.addProvider(event.includeServer(),abstractMaterialDataProvider);
        generator.addProvider(event.includeServer(),new TAMaterialStatsDataProvider(output,abstractMaterialDataProvider));
        generator.addProvider(event.includeServer(),new TAMaterialTraitsDataProvider(output,abstractMaterialDataProvider));

        AbstractMaterialSpriteProvider abstractMaterialSpriteProvider = new TAMaterialSpriteProvider();
        generator.addProvider(event.includeClient(),new TAMaterialRenderInfoProvider(output,abstractMaterialSpriteProvider,helper));

        generator.addProvider(event.includeClient(),new TALangProvider(output));
        generator.addProvider(event.includeClient(),new TAItemModelProvider(output,helper));

        generator.addProvider(event.includeServer(),new TAModifierProvider(output));

        generator.addProvider(event.includeServer(),new TARecipeProvider(output));
        generator.addProvider(event.includeServer(),new TAToolDefinitionDataProvider(output));

        BlockTagsProvider blockTagsProvider = new TABlockTagProvider(output,lookup,helper);
        generator.addProvider(event.includeServer(),blockTagsProvider);
        generator.addProvider(event.includeServer(),new TAItemTagsProvider(output,lookup,blockTagsProvider.contentsGetter(),helper));

        generator.addProvider(event.includeServer(),new TAStationSlotLayoutProvider(output));

    }
}
