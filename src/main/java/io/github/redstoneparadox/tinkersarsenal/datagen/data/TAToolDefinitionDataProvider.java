package io.github.redstoneparadox.tinkersarsenal.datagen.data;

import io.github.redstoneparadox.tinkersarsenal.TinkersArsenal;
import io.github.redstoneparadox.tinkersarsenal.init.TAToolDefinitions;
import io.github.redstoneparadox.tinkersarsenal.init.ArsenalItems;
import net.minecraft.data.PackOutput;
import slimeknights.tconstruct.library.data.tinkering.AbstractToolDefinitionDataProvider;
import slimeknights.tconstruct.library.materials.RandomMaterial;
import slimeknights.tconstruct.library.tools.definition.module.build.MultiplyStatsModule;
import slimeknights.tconstruct.library.tools.definition.module.build.SetStatsModule;
import slimeknights.tconstruct.library.tools.definition.module.material.DefaultMaterialsModule;
import slimeknights.tconstruct.library.tools.definition.module.material.PartStatsModule;
import slimeknights.tconstruct.library.tools.nbt.MultiplierNBT;
import slimeknights.tconstruct.library.tools.nbt.StatsNBT;
import slimeknights.tconstruct.library.tools.stat.ToolStats;
import slimeknights.tconstruct.tools.TinkerToolParts;

public class TAToolDefinitionDataProvider extends AbstractToolDefinitionDataProvider {
    public TAToolDefinitionDataProvider(PackOutput packOutput) {
        super(packOutput, TinkersArsenal.MOD_ID);
    }

    @Override
    protected void addToolDefinitions() {

        RandomMaterial tier1Material = RandomMaterial.random().tier(1).build();
        RandomMaterial randomMaterial = RandomMaterial.random().build();

        DefaultMaterialsModule defaultTwoParts = DefaultMaterialsModule.builder().material(tier1Material, tier1Material).build();
        DefaultMaterialsModule defaultThreeParts = DefaultMaterialsModule.builder().material(tier1Material, tier1Material, tier1Material).build();

        // boomsticks
        define(TAToolDefinitions.BOOMSTICK)
                // parts
                .module(PartStatsModule.parts()
                        .part(ArsenalItems.boomstick_stock)
                        .part(ArsenalItems.boomstickBarrel)
                        .part(TinkerToolParts.smallBlade).build())
                .module(defaultThreeParts)
                // stats
                .module(new SetStatsModule(StatsNBT.builder()
                        .set(ToolStats.ATTACK_DAMAGE, 0f)
                        .set(ToolStats.ATTACK_SPEED, 1.0f).build()))
                .module(new MultiplyStatsModule(MultiplierNBT.builder()
                        .set(ToolStats.DURABILITY, 2f).build()))
                .smallToolStartingSlots();

        // boomstick shot
        define(TAToolDefinitions.BOOMSTICK_SHOT)
                // parts
                .module(PartStatsModule.parts()
                        .part(ArsenalItems.bullet_shell)
                        .part(ArsenalItems.bullet_head).build())
                .module(defaultTwoParts)
                // stats
                .module(new SetStatsModule(StatsNBT.builder()
                        .set(ToolStats.ATTACK_DAMAGE, 0f)
                        .set(ToolStats.ATTACK_SPEED, 1.0f).build()))
                .module(new MultiplyStatsModule(MultiplierNBT.builder()
                        .set(ToolStats.DURABILITY, 2f).build()))
                .smallToolStartingSlots();

        // shears
        define(TAToolDefinitions.SHEARS)
                // parts
                .module(PartStatsModule.parts()
                        .part(TinkerToolParts.toolBinding.get())
                        .part(TinkerToolParts.smallBlade).build())
                .module(defaultTwoParts)
                // stats
                .module(new SetStatsModule(StatsNBT.builder()
                        .set(ToolStats.ATTACK_DAMAGE, 0f)
                        .set(ToolStats.ATTACK_SPEED, 1.0f).build()))
                .module(new MultiplyStatsModule(MultiplierNBT.builder()
                        .set(ToolStats.DURABILITY, 2f).build()))
                .smallToolStartingSlots();
    }

    @Override
    public String getName() {
        return "";
    }
}
