package io.github.redstoneparadox.tinkersarsenal.tools.utility;

import io.github.redstoneparadox.tinkersarsenal.init.TAToolDefinitions;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.ShearsItem;
import net.minecraft.world.level.Level;
import slimeknights.tconstruct.library.tools.IndestructibleItemEntity;
import slimeknights.tconstruct.library.tools.definition.ToolDefinition;
import slimeknights.tconstruct.library.tools.helper.ModifierUtil;
import slimeknights.tconstruct.library.tools.helper.ToolBuildHandler;
import slimeknights.tconstruct.library.tools.helper.ToolDamageUtil;
import slimeknights.tconstruct.library.tools.item.IModifiableDisplay;
import slimeknights.tconstruct.library.tools.item.ModifiableItem;
import slimeknights.tconstruct.library.tools.nbt.ToolStack;

import java.util.function.Consumer;

/**
 * Created by RedstoneParadox on 10/8/2018.
 */
public class ToolShearsItem extends ModifiableItem {

    public ToolShearsItem(Properties properties) {
        super(properties,null/*TAToolDefinitions.SHEARS*/); //PartMaterialType.handle(TinkerTools.binding), PartMaterialType.head(TinkerTools.knifeBlade));
    }

}
