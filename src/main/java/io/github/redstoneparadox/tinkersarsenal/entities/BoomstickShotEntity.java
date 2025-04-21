package io.github.redstoneparadox.tinkersarsenal.entities;

import io.netty.buffer.ByteBuf;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;

/**
 * Created by RedstoneParadox on 8/3/2018.
 */
public class BoomstickShotEntity extends AbstractArrow {
    // animation
    public int roll = 0;
    public int rollSpeed = 80;

    public BoomstickShotEntity(EntityType<? extends BoomstickShotEntity> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public BoomstickShotEntity(EntityType<? extends BoomstickShotEntity> pEntityType, double d, double d1, double d2,Level world) {
        super(pEntityType,d2, d, d1, world);
    }

    public static BoomstickShotEntity fire(EntityType<? extends BoomstickShotEntity> pEntityType, Level world, Player player,
                                           float speed, float inaccuracy, float power, ItemStack stack, ItemStack launchingStack) {
        return null;
        //super(pEntityType,world, player, speed, inaccuracy, power, stack, launchingStack);
    }

    @Override
    protected void onHitEntity(EntityHitResult pResult) {
        super.onHitEntity(pResult);
        Entity entityHit = pResult.getEntity();
        if(!level().isClientSide && entityHit instanceof LivingEntity livingEntity) {
            livingEntity.setArrowCount(livingEntity.getArrowCount() + 1);
        }
    }

    @Override
    protected void onHitBlock(BlockHitResult pResult) {
        super.onHitBlock(pResult);
    }

    //        this.playSound(SoundEvents.ENTITY_SHULKER_HURT_CLOSED, 1.0F, 1.2F / (this.rand.nextFloat() * 0.2F + 0.9F));


   /* @Override
    public void readSpawnData(ByteBuf data) {
        super.readSpawnData(data);

        // animation stuff, it sometimes rotates left
        int rollDir = rand.nextBoolean() ? -1 : 1;
        rollSpeed = (int)((getSpeed() * 80) / 3) * rollDir;
    }*/

    @Override
    protected ItemStack getPickupItem() {
        return ItemStack.EMPTY;
    }
}
