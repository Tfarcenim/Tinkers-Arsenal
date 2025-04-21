package io.github.redstoneparadox.tinkersarsenal.tools.utility;

import io.github.redstoneparadox.tinkersarsenal.init.TAToolDefinitions;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ShearsItem;
import slimeknights.tconstruct.library.tools.definition.ToolDefinition;
import slimeknights.tconstruct.library.tools.helper.ToolBuildHandler;
import slimeknights.tconstruct.library.tools.helper.ToolDamageUtil;
import slimeknights.tconstruct.library.tools.item.IModifiableDisplay;

import java.util.function.Consumer;

/**
 * Created by RedstoneParadox on 10/8/2018.
 */
public class ToolShearsItem extends ShearsItem implements IModifiableDisplay {
    private ItemStack toolForRendering;

    public ToolShearsItem(Properties properties) {
        super(properties); //PartMaterialType.handle(TinkerTools.binding), PartMaterialType.head(TinkerTools.knifeBlade));
    }

    @Override
    public ItemStack getRenderTool() {
        if (toolForRendering == null) {
            toolForRendering = ToolBuildHandler.buildToolForRendering(this, this.getToolDefinition());
        }
        return toolForRendering;
    }

    @Override
    public <T extends LivingEntity> int damageItem(ItemStack stack, int amount, T damager, Consumer<T> onBroken) {
        ToolDamageUtil.handleDamageItem(stack, amount, damager, onBroken);
        return 0;
    }

    @Override
    public ToolDefinition getToolDefinition() {
        return TAToolDefinitions.SHEARS;
    }
}
