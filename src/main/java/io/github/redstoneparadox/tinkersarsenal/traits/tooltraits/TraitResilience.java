package io.github.redstoneparadox.tinkersarsenal.traits.tooltraits;

import slimeknights.tconstruct.library.modifiers.Modifier;

/**
 * Created by RedstoneParadox on 8/15/2018.
 */
public class TraitResilience extends Modifier {

    //        super("resilience", 0x33ebcb);
    public TraitResilience() {
    }

    /*@Override
    public int onToolDamage(ItemStack tool, int damage, int newDamage, EntityLivingBase entity) {
        if(entity.getEntityWorld().isRemote) {
            return 0;
        }

        NBTTagCompound tag = tool.getTagCompound();
        int baseDurability = tag.getCompoundTag("Stats").getInteger("Durability");
        int damageTaken = tag.getInteger("Damage");

        int remainingDurability = baseDurability - damageTaken;

        Random damageChance = new Random();

        if ((damageChance.nextInt(baseDurability * 2) + 1) <= remainingDurability) {
            newDamage = 0;
            return newDamage;
        }
        else {
            newDamage = damage;
            return newDamage;
        }
    }*/
}
