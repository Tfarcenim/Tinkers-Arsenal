package io.github.redstoneparadox.tinkersarsenal.tools.ranged;

import io.github.redstoneparadox.tinkersarsenal.init.TAToolDefinitions;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ArrowItem;
import net.minecraft.world.item.ItemStack;
import slimeknights.tconstruct.library.tools.definition.ToolDefinition;
import slimeknights.tconstruct.library.tools.helper.ToolBuildHandler;
import slimeknights.tconstruct.library.tools.item.IModifiableDisplay;
import slimeknights.tconstruct.library.tools.item.ModifiableItem;
import slimeknights.tconstruct.library.tools.nbt.ToolStack;

/**
 * Created by RedstoneParadox on 8/3/2018.
 */
public class AmmoBoomstickShot extends ModifiableItem {

    public AmmoBoomstickShot(Properties properties) {
        super(properties,TAToolDefinitions.BOOMSTICK_SHOT);
    }
}
