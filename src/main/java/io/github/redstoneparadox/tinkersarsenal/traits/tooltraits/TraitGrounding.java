package io.github.redstoneparadox.tinkersarsenal.traits.tooltraits;

import slimeknights.tconstruct.library.modifiers.Modifier;

public class TraitGrounding extends Modifier {
    //super("grounding", 0x554B45)

    public TraitGrounding() {
        ;
    }

    /*@Override
    public void afterHit(EntityProjectileBase projectile, World world, ItemStack ammoStack, EntityLivingBase attacker, Entity target, double impactSpeed) {
        super.afterHit(projectile, world, ammoStack, attacker, target, impactSpeed);

        if (target.isEntityAlive() && target instanceof EntityLiving) {
            ((EntityLiving) target).addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 101));
        }
    }*/
}
