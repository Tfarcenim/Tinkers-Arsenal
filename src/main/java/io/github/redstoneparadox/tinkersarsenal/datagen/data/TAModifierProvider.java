package io.github.redstoneparadox.tinkersarsenal.datagen.data;

import io.github.redstoneparadox.tinkersarsenal.traits.ArsenalToolTraits;
import net.minecraft.data.PackOutput;
import slimeknights.tconstruct.library.data.tinkering.AbstractModifierProvider;
import slimeknights.tconstruct.library.modifiers.modules.build.ModifierSlotModule;
import slimeknights.tconstruct.library.modifiers.util.ModifierLevelDisplay;
import slimeknights.tconstruct.library.tools.SlotType;


public class TAModifierProvider extends AbstractModifierProvider {
    public TAModifierProvider(PackOutput packOutput) {
        super(packOutput);
    }

    @Override
    protected void addModifiers() {
        ModifierSlotModule UPGRADE = new ModifierSlotModule(SlotType.UPGRADE);
        buildModifier(ArsenalToolTraits.malleable).levelDisplay(ModifierLevelDisplay.SINGLE_LEVEL).addModule(UPGRADE);
        buildModifier(ArsenalToolTraits.resilience).levelDisplay(ModifierLevelDisplay.NO_LEVELS);
    }

    @Override
    public String getName() {
        return "Tinkers' Boomstick Modifiers";
    }
}
