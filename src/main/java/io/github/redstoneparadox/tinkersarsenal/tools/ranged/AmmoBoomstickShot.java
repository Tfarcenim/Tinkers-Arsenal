package io.github.redstoneparadox.tinkersarsenal.tools.ranged;

import io.github.redstoneparadox.tinkersarsenal.init.TAToolDefinitions;
import net.minecraft.world.item.ArrowItem;
import net.minecraft.world.item.ItemStack;
import slimeknights.tconstruct.library.tools.definition.ToolDefinition;
import slimeknights.tconstruct.library.tools.helper.ToolBuildHandler;
import slimeknights.tconstruct.library.tools.item.IModifiableDisplay;

/**
 * Created by RedstoneParadox on 8/3/2018.
 */
public class AmmoBoomstickShot extends ArrowItem implements IModifiableDisplay {

    public AmmoBoomstickShot(Properties properties) {
        super(properties);
    }

    private ItemStack toolForRendering;


    @Override
    public ToolDefinition getToolDefinition() {
        return TAToolDefinitions.BOOMSTICK_SHOT;
    }

    @Override
    public ItemStack getRenderTool() {
        if (toolForRendering == null) {
            toolForRendering = ToolBuildHandler.buildToolForRendering(this, this.getToolDefinition());
        }
        return toolForRendering;
    }

}
