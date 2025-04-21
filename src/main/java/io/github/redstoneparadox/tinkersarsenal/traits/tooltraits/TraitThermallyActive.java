package io.github.redstoneparadox.tinkersarsenal.traits.tooltraits;

import slimeknights.tconstruct.library.modifiers.Modifier;

public class TraitThermallyActive extends Modifier {
    //        super("thermally_active", 0xff8080, 3, levels);

    public TraitThermallyActive(int levels) {
    }

   /* @Override
    public void afterHit(ItemStack tool, EntityLivingBase player, EntityLivingBase target, float damageDealt, boolean wasCritical, boolean wasHit) {
        if (wasHit) {
            ModifierNBT data = new ModifierNBT(TinkerUtil.getModifierTag(tool, name));

            target.attackEntityFrom(DamageSource.HOT_FLOOR, damageDealt * 0.1f * data.level);
        }
    }*/
}
