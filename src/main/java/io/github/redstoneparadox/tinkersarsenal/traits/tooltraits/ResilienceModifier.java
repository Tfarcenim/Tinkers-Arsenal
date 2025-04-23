package io.github.redstoneparadox.tinkersarsenal.traits.tooltraits;

import net.minecraft.world.entity.LivingEntity;
import org.jetbrains.annotations.Nullable;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.hook.behavior.ToolDamageModifierHook;
import slimeknights.tconstruct.library.modifiers.impl.NoLevelsModifier;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;

/**
 * Created by RedstoneParadox on 8/15/2018.
 */
public class ResilienceModifier extends NoLevelsModifier implements ToolDamageModifierHook {

    //        super("resilience", 0x33ebcb);
    public ResilienceModifier() {
    }

    @Override
    public int onDamageTool(IToolStackView tool, ModifierEntry modifier, int amount, @Nullable LivingEntity holder) {

        int baseDurability = tool.getCurrentDurability();//tag.getCompoundTag("Stats").getInteger("Durability");
        int damageTaken = tool.getDamage();//tag.getInteger("Damage");

        int remainingDurability = baseDurability - damageTaken;


        if ((RANDOM.nextInt(baseDurability * 2) + 1) <= remainingDurability) {
            return 0;
        }
        else {
            return amount;
        }
    }

    /*@Override
    public int onToolDamage(ItemStack tool, int damage, int newDamage, EntityLivingBase entity) {

    }*/
}
