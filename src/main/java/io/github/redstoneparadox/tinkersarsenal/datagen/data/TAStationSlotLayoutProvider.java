package io.github.redstoneparadox.tinkersarsenal.datagen.data;

import io.github.redstoneparadox.tinkersarsenal.tools.ArsenalTools;
import net.minecraft.data.PackOutput;
import slimeknights.tconstruct.library.data.tinkering.AbstractStationSlotLayoutProvider;
import slimeknights.tconstruct.tools.TinkerToolParts;

public class TAStationSlotLayoutProvider extends AbstractStationSlotLayoutProvider {
    public TAStationSlotLayoutProvider(PackOutput packOutput) {
        super(packOutput);
    }

    @Override
    protected void addLayouts() {
        defineModifiable(ArsenalTools.shears)
                .sortIndex(SORT_HARVEST)
                .addInputItem(TinkerToolParts.toolBinding,    53, 22)
                .addInputItem(TinkerToolParts.smallBlade, 33, 42)
                .build();

        defineModifiable(ArsenalTools.boomstick)
                .sortIndex(SORT_RANGED)
                .addInputItem(ArsenalTools.boomstickBarrel,   10, 20)
                .addInputItem(ArsenalTools.boomstick_stock,   46, 56)
                .addInputItem(ArsenalTools.bayonet, 28, 38)
                .build();

        defineModifiable(ArsenalTools.boomstickShot)
                .sortIndex(SORT_RANGED)
                .addInputItem(ArsenalTools.bullet_head,   10, 20)
                .addInputItem(ArsenalTools.bulletShell,   46, 56)
                .build();
    }

    @Override
    public String getName() {
        return "Tinker's Arsenal Layout Provider";
    }
}
