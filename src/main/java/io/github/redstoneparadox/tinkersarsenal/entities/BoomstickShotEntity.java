package io.github.redstoneparadox.tinkersarsenal.entities;

import io.github.redstoneparadox.tinkersarsenal.init.ArsenalEntities;
import io.github.redstoneparadox.tinkersarsenal.tools.ranged.BoomstickShotItem;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
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
    private static final EntityDataAccessor<ItemStack> DATA_ITEM = SynchedEntityData.defineId(BoomstickShotEntity.class, EntityDataSerializers.ITEM_STACK);


    public BoomstickShotEntity(EntityType<? extends BoomstickShotEntity> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public BoomstickShotEntity(EntityType<? extends BoomstickShotEntity> pEntityType, double d, double d1, double d2,Level world) {
        super(pEntityType,d2, d, d1, world);
    }

    public BoomstickShotEntity(Level pLevel, LivingEntity pShooter,ItemStack ammo) {
        super(ArsenalEntities.BOOMSTICK_SHOT, pShooter, pLevel);
        setItem(ammo);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.getEntityData().define(DATA_ITEM, ItemStack.EMPTY);
    }

    /**
     * Gets the item that this entity represents.
     */
    public ItemStack getItem() {
        return this.getEntityData().get(DATA_ITEM);
    }

    /**
     * Sets the item that this entity represents.
     */
    public void setItem(ItemStack pStack) {
        this.getEntityData().set(DATA_ITEM, pStack);
    }

    @Override
    public void onSyncedDataUpdated(EntityDataAccessor<?> pKey) {
        super.onSyncedDataUpdated(pKey);
        if (DATA_ITEM.equals(pKey)) {
            this.getItem().setEntityRepresentation(this);
        }

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
    public void addAdditionalSaveData(CompoundTag pCompound) {
        if (!this.getItem().isEmpty()) {
            pCompound.put("Item", this.getItem().save(new CompoundTag()));
        }
    }

    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    @Override
    public void readAdditionalSaveData(CompoundTag pCompound) {
        CompoundTag compoundtag = pCompound.getCompound("Item");
        this.setItem(ItemStack.of(compoundtag));
        if (this.getItem().isEmpty()) {
            this.discard();
        }
    }

    @Override
    protected void onHitBlock(BlockHitResult pResult) {
        super.onHitBlock(pResult);
    }

    //        this.playSound(SoundEvents.ENTITY_SHULKER_HURT_CLOSED, 1.0F, 1.2F / (this.rand.nextFloat() * 0.2F + 0.9F));

    @Override
    protected boolean tryPickup(Player player) {
        if (pickup == Pickup.ALLOWED) {
            ItemStack stack = getItem();
            if (stack.getItem() instanceof BoomstickShotItem) {
                for (int i = 0;i < player.getInventory().getContainerSize(); i++) {
                    ItemStack other = player.getInventory().getItem(i);
                    if (areMergable(other,stack)) {
                        if (other.isDamaged()) {
                            other.setDamageValue(other.getDamageValue() - 1);
                            discard();
                            return true;
                        }
                    }
                }
            }
            return super.tryPickup(player);
        } else {
            return super.tryPickup(player);
        }
    }

    static boolean areMergable(ItemStack stackA,ItemStack stackB) {
        if (!stackA.hasTag() || !stackB.hasTag()) {
            return false;
        }

        ItemStack stackACopy = stackA.copy();
        stackACopy.getTag().remove(ItemStack.TAG_DAMAGE);

        ItemStack stackBCopy = stackB.copy();
        stackBCopy.getTag().remove(ItemStack.TAG_DAMAGE);
        return ItemStack.isSameItemSameTags(stackACopy,stackBCopy);
    }

   /* @Override
    public void readSpawnData(ByteBuf data) {
        super.readSpawnData(data);

        // animation stuff, it sometimes rotates left
        int rollDir = rand.nextBoolean() ? -1 : 1;
        rollSpeed = (int)((getSpeed() * 80) / 3) * rollDir;
    }*/

    @Override
    protected ItemStack getPickupItem() {
        return getItem();
    }
}
