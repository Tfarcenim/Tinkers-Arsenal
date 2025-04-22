package io.github.redstoneparadox.tinkersarsenal.traits.tooltraits;

import slimeknights.tconstruct.library.modifiers.Modifier;

/**
 * Created by RedstoneParadox on 7/29/2018.
 */
public class TraitDiamondEdge extends Modifier {

    //        super("diamond_edge", 0x33ebcb);
    public TraitDiamondEdge() {
    }

   /* @Override
    public float damage(ItemStack tool, EntityLivingBase player, EntityLivingBase target, float damage, float newDamage, boolean isCritical) {
        int armor = target.getTotalArmorValue();

        /*Will make this actually work the way I wanted to at some point; problem is some armors
        add more than 20 armortraits points and the code relies on the 20 point maximum which I don't
         know how to fix*/

    /*
        if (armor > 0) {
            Random pierceChance = new Random();

            /*Calculates the percentage of damage that would be left after the target's armortraits absorbs some damage.
            int armorPercentage = ((armortraits * 80)/20)/100;
            int damagePercentage = 1 - armorPercentage;

            if ((pierceChance.nextInt(20) + 1) == 1) {
                //The damage is then increased so the original damage gets through...but not before being divided in two for balance!
                newDamage = (damage/damagePercentage)/2;
            }*/
    /*
            if ((pierceChance.nextInt(20) + 1) == 1) {
                newDamage = damage * 1.5f;
            }
            else {
                newDamage = damage;
            }
        }
        else {
            newDamage = damage;
        }
        return super.damage(tool, player, target, damage, newDamage, isCritical);
    }*/

    @Override
    public int getPriority() {
        //Lower priority to allow for other damage dealing effects to get processed first.
        return 50;
    }
}
