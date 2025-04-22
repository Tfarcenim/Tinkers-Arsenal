package io.github.redstoneparadox.tinkersarsenal.datagen;

import io.github.redstoneparadox.tinkersarsenal.TinkersArsenal;
import io.github.redstoneparadox.tinkersarsenal.materials.ArsenalToolMaterials;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.LanguageProvider;
import slimeknights.tconstruct.library.client.materials.MaterialTooltipCache;
import slimeknights.tconstruct.library.materials.definition.MaterialVariantId;

public class TALangProvider extends LanguageProvider {
    public TALangProvider(PackOutput output) {
        super(output, TinkersArsenal.MOD_ID, "en_us");
    }

    @Override
    protected void addTranslations() {
        addMaterialTranslation(ArsenalToolMaterials.GOLD,"Gold");
        addMaterialTranslation(ArsenalToolMaterials.DIAMOND,"Diamond");
    }

    void addMaterialTranslation(MaterialVariantId id,String text) {
        String materialKey = MaterialTooltipCache.getKey(id);
        add(materialKey,text);
    }
}
