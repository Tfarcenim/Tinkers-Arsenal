package io.github.redstoneparadox.tinkersarsenal.datagen.data;

import io.github.redstoneparadox.tinkersarsenal.init.ArsenalItems;
import net.minecraft.data.PackOutput;
import slimeknights.tconstruct.library.data.tinkering.AbstractStationSlotLayoutProvider;
import slimeknights.tconstruct.tools.TinkerToolParts;

public class TAStationSlotLayoutProvider extends AbstractStationSlotLayoutProvider {
    public TAStationSlotLayoutProvider(PackOutput packOutput) {
        super(packOutput);
    }

    @Override
    protected void addLayouts() {
        defineModifiable(ArsenalItems.shears)
                .sortIndex(SORT_HARVEST)
                .addInputItem(TinkerToolParts.toolBinding,    53, 22)
                .addInputItem(TinkerToolParts.smallBlade, 33, 42)
                .build();

        defineModifiable(ArsenalItems.boomstick)
                .sortIndex(SORT_RANGED)
                .addInputItem(ArsenalItems.boomstickBarrel,   10, 20)
                .addInputItem(ArsenalItems.boomstick_stock,   46, 56)
                .addInputItem(TinkerToolParts.smallBlade, 28, 38)
                .build();

        defineModifiable(ArsenalItems.boomstickShot)
                .sortIndex(SORT_RANGED)
                .addInputItem(ArsenalItems.bullet_head,   10, 20)
                .addInputItem(ArsenalItems.bulletShell,   46, 56)
                .build();
    }

    @Override
    public String getName() {
        return "Tinker's Arsenal Layout Provider";
    }
}
