package io.github.redstoneparadox.tinkersarsenal.tools.ranged;

import io.github.redstoneparadox.tinkersarsenal.entities.BoomstickShotEntity;
import io.github.redstoneparadox.tinkersarsenal.init.ArsenalEntities;
import io.github.redstoneparadox.tinkersarsenal.init.ArsenalItems;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.FireworkRocketEntity;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.ArrowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.ToolActions;
import net.minecraftforge.items.ItemHandlerHelper;
import org.joml.Quaternionf;
import org.joml.Vector3f;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.ModifierHooks;
import slimeknights.tconstruct.library.modifiers.hook.build.ConditionalStatModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.interaction.GeneralInteractionModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.ranged.BowAmmoModifierHook;
import slimeknights.tconstruct.library.tools.capability.EntityModifierCapability;
import slimeknights.tconstruct.library.tools.capability.PersistentDataCapability;
import slimeknights.tconstruct.library.tools.definition.ToolDefinition;
import slimeknights.tconstruct.library.tools.helper.ModifierUtil;
import slimeknights.tconstruct.library.tools.helper.ToolDamageUtil;
import slimeknights.tconstruct.library.tools.item.ranged.ModifiableCrossbowItem;
import slimeknights.tconstruct.library.tools.item.ranged.ModifiableLauncherItem;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import slimeknights.tconstruct.library.tools.nbt.ModDataNBT;
import slimeknights.tconstruct.library.tools.nbt.ModifierNBT;
import slimeknights.tconstruct.library.tools.nbt.ToolStack;
import slimeknights.tconstruct.library.tools.stat.ToolStats;
import slimeknights.tconstruct.tools.TinkerModifiers;
import slimeknights.tconstruct.tools.modifiers.ability.interaction.BlockingModifier;
import slimeknights.tconstruct.tools.modifiers.upgrades.ranged.ScopeModifier;

import java.util.function.Predicate;

import static slimeknights.tconstruct.library.modifiers.hook.interaction.GeneralInteractionModifierHook.KEY_DRAWTIME;

/**
 * Created by RedstoneParadox on 7/31/2018.
 */
public class ToolBoomstickItem extends ModifiableLauncherItem {
    private static final String TAG_Loaded = "Loaded";

    public static final Predicate<ItemStack> BOOMSTICK_SHOT = stack -> stack.is(ArsenalItems.boomstick_shot);

    public static final Predicate<ItemStack> GUNPOWDER = stack -> stack.is(Items.GUNPOWDER);

    public ToolBoomstickItem(Properties properties, ToolDefinition toolDefinition) {
        super(properties, toolDefinition);


        //   (PartMaterialType.handle(ArsenalItems.boomstickHandle),
        //          PartMaterialType.bow(ArsenalItems.boomstickBarrel),
        //          PartMaterialType.head(ArsenalItems.bayonet));


    }

    @Override
    public int getDefaultProjectileRange() {
        return 12;
    }

    /**
     * Fires the crossbow
     *
     * @param tool     Tool instance
     * @param player   Player firing
     * @param hand     Hand fired from
     * @param heldAmmo Ammo used to fire, should be non-empty
     */
    public static void fireBoomstick(IToolStackView tool, Player player, InteractionHand hand, CompoundTag heldAmmo) {
        // ammo already loaded? time to fire
        Level level = player.level();
        if (!level.isClientSide) {
            // shoot the projectile

            // don't need to calculate these multiple times
            float velocity = ConditionalStatModifierHook.getModifiedStat(tool, player, ToolStats.VELOCITY);
            float inaccuracy = ModifierUtil.getInaccuracy(tool, player);
            boolean creative = player.getAbilities().instabuild;

            // when creating the ammo stacks, we use split, so its getting smaller each time
            ItemStack ammo = ItemStack.of(heldAmmo);

            ammo.setDamageValue(ammo.getMaxDamage() - 1);

            float startAngle = getAngleStart(ammo.getCount());
            int primaryIndex = ammo.getCount() / 2;
            // setup projectile
            
            AbstractArrow arrow = new BoomstickShotEntity(level,player,ammo);
            arrow.setCritArrow(true);
            arrow.setSoundEvent(SoundEvents.CROSSBOW_HIT);
            arrow.setShotFromCrossbow(true);

            // vanilla arrows have a base damage of 2, cancel that out then add in our base damage to account for custom arrows with higher base damage
            float baseArrowDamage = (float) (arrow.getBaseDamage() + tool.getStats().get(ToolStats.PROJECTILE_DAMAGE));
            arrow.setBaseDamage(ConditionalStatModifierHook.getModifiedStat(tool, player, ToolStats.PROJECTILE_DAMAGE, baseArrowDamage));

            // fortunately, don't need to deal with vanilla infinity here, our infinity was dealt with during loading
            if (creative) {
                arrow.pickup = AbstractArrow.Pickup.CREATIVE_ONLY;
            }

            // TODO: can we get piglins/illagers to use our crossbow?

            // setup projectile
            Vec3 upVector = player.getUpVector(1.0f);
            Vector3f targetVector = player.getViewVector(1.0f).toVector3f().rotate((new Quaternionf()).setAngleAxis(startAngle * Math.PI / 180F, upVector.x, upVector.y, upVector.z));
            float speed = 3f;
            arrow.shoot(targetVector.x(), targetVector.y(), targetVector.z(), velocity * speed, inaccuracy);

            // add modifiers to the projectile, will let us use them on impact
            ModifierNBT modifiers = tool.getModifiers();
            arrow.getCapability(EntityModifierCapability.CAPABILITY).ifPresent(cap -> cap.setModifiers(modifiers));

            // fetch the persistent data for the arrow as modifiers may want to store data
            ModDataNBT projectileData = PersistentDataCapability.getOrWarn(arrow);

            // let modifiers set properties
            for (ModifierEntry entry : modifiers.getModifiers()) {
                entry.getHook(ModifierHooks.PROJECTILE_LAUNCH).onProjectileLaunch(tool, entry, player, arrow, arrow, projectileData, 0 == primaryIndex);
            }

            // finally, fire the projectile
            level.addFreshEntity(arrow);
            level.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.CROSSBOW_SHOOT, SoundSource.PLAYERS, 1.0F, getRandomShotPitch(startAngle, player.getRandom()));

            // clear the ammo, damage the bow
            tool.getPersistentData().remove(ModifiableCrossbowItem.KEY_CROSSBOW_AMMO);
            int damage = 1;
            ToolDamageUtil.damageAnimated(tool, damage, player, hand);

            // stats
            if (player instanceof ServerPlayer serverPlayer) {
                CriteriaTriggers.SHOT_CROSSBOW.trigger(serverPlayer, player.getItemInHand(hand));
                serverPlayer.awardStat(Stats.ITEM_USED.get(tool.getItem()));
            }
        }
    }

    /**
     * Gets the arrow pitch
     */
    private static float getRandomShotPitch(float angle, RandomSource pRandom) {
        if (angle == 0) {
            return 1.0f;
        }
        return 1.0F / (pRandom.nextFloat() * 0.5F + 1.8F) + 0.53f + (angle / 10f);
    }

    /* @Override
    public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> subItems) {
        if(this.getCreativeTab() == tab) {
            addDefaultSubItems(subItems, null, null, null);
        }
    }

    @Override
    public float damagePotential() {
        return 0.8f;
    }

    @Override
    public double attackSpeed() {
        return 2;
    }

    @Override
    public float baseProjectileDamage() {
        return 5f;
    }

    @Override
    protected float baseProjectileSpeed() {
        return 9f;
    }

    @Override
    public float projectileDamageModifier() {
        return 1.5f;
    }

    @Override
    public int getDrawTime() {
        return 45;
    }*/

    public static boolean isLoaded(ItemStack stack) {
        ToolStack tool = ToolStack.from(stack);
        ModDataNBT persistentData = tool.getPersistentData();
        CompoundTag heldAmmo = persistentData.getCompound(ModifiableCrossbowItem.KEY_CROSSBOW_AMMO);
        return !heldAmmo.isEmpty();
    }

    @Override
    public void inventoryTick(ItemStack stack, Level worldIn, net.minecraft.world.entity.Entity entityIn, int itemSlot, boolean isSelected) {
        super.inventoryTick(stack, worldIn, entityIn, itemSlot, isSelected);
        //        preventSlowDown(entityIn, 0.195f);
    }

    @Override
    public UseAnim getUseAnimation(ItemStack stack) {
        // crossbow is superhardcoded to crossbows, so use none and rely on the model
        return BlockingModifier.blockWhileCharging(ToolStack.from(stack), UseAnim.NONE);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack bow = player.getItemInHand(hand);

        ToolStack tool = ToolStack.from(bow);
        if (tool.isBroken()) {
            return InteractionResultHolder.fail(bow);
        }

        // no ammo? not charged;
        if (isLoaded(bow)) {
            ModDataNBT persistentData = tool.getPersistentData();
            CompoundTag heldAmmo = persistentData.getCompound(ModifiableCrossbowItem.KEY_CROSSBOW_AMMO);
            fireBoomstick(tool,player,hand,heldAmmo);
            return InteractionResultHolder.consume(bow);
        }

        if (!BowAmmoModifierHook.hasAmmo(tool, bow, player, GUNPOWDER)) {
            return InteractionResultHolder.fail(bow);
        }

        // yeah, its hardcoded, I cannot see a need to not hardcode this, request it if you need it
        boolean sinistral = hand == InteractionHand.MAIN_HAND && tool.getModifierLevel(TinkerModifiers.sinistral.getId()) > 0;

        // do not charge if sneaking and we have sinistral, gives you a way to activate the offhand when the crossbow is not charged
        if (sinistral && !player.getOffhandItem().isEmpty() && player.isCrouching()) {
            return InteractionResultHolder.pass(bow);
        }

        // if we have ammo, start charging
        if (BowAmmoModifierHook.hasAmmo(tool, bow, player, getSupportedHeldProjectiles())) {
            GeneralInteractionModifierHook.startDrawtime(tool, player, 1);
            player.startUsingItem(hand);
            if (!level.isClientSide) {
                level.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.CROSSBOW_QUICK_CHARGE_1, SoundSource.PLAYERS, 0.75F, 1.0F);
            }
            return InteractionResultHolder.consume(bow);
            // no ammo still lets us block
        } else if (ModifierUtil.canPerformAction(tool, ToolActions.SHIELD_BLOCK)) {
            player.startUsingItem(hand);
            return InteractionResultHolder.consume(bow);
        } else {
            return InteractionResultHolder.fail(bow);
        }

        // sinistral shoots on left click when in main hand, and lets us block instead of shooting if the offhand is empty
    }

    /*@Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);
        ToolStack tool = ToolStack.from(stack);
        if (tool.isBroken()) {
            return InteractionResultHolder.fail(stack);
        }



        if (isLoaded(stack)) {
            super.onStopUsing(stack, player, 0);
            setLoaded(stack, false);
        } else {
            return super.use(level, player, hand);
        }
        return InteractionResultHolder.sidedSuccess(stack, level.isClientSide);
    }*/

    @Override
    public boolean useOnRelease(ItemStack pStack) {
        return true;
    }

    @Override
    public void releaseUsing(ItemStack bow, Level level, LivingEntity living, int chargeRemaining) {
        // clear zoom regardless, does not matter if the tool broke, we should not be zooming
        ScopeModifier.stopScoping(living);
        if (!(living instanceof Player player)) {
            return;
        }
        ToolStack tool = ToolStack.from(bow);
        ModDataNBT persistentData = tool.getPersistentData();
        if (tool.isBroken() || persistentData.contains(ModifiableCrossbowItem.KEY_CROSSBOW_AMMO, Tag.TAG_COMPOUND)) {
            return;
        }

        // did we charge enough?
        int drawtime = persistentData.getInt(KEY_DRAWTIME);
        persistentData.remove(KEY_DRAWTIME);
        if ((getUseDuration(bow) - chargeRemaining) < drawtime) {
            return;
        }

        // find ammo and store it on the bow
        ItemStack ammo = findAmmo(tool, bow, player, getSupportedHeldProjectiles());
        if (!ammo.isEmpty()) {
            level.playSound(null, living.getX(), living.getY(), living.getZ(), SoundEvents.CROSSBOW_LOADING_END, SoundSource.PLAYERS, 1.0F, 1.0F / (level.getRandom().nextFloat() * 0.5F + 1.0F) + 0.2F);
            if (!level.isClientSide) {
                CompoundTag ammoNBT = ammo.save(new CompoundTag());
                persistentData.put(ModifiableCrossbowItem.KEY_CROSSBOW_AMMO, ammoNBT);
                // if the crossbow broke during loading, fire immediately
                if (tool.isBroken()) {
                    fireBoomstick(tool, player, player.getUsedItemHand(), ammoNBT);
                }
            }
        }
    }

    /**
     * Finds ammo in the inventory, and consume it if not creative
     * @param tool       Tool instance
     * @param bow        Bow stack instance
     * @param predicate  Predicate for valid ammo
     * @param player     Player to search
     * @return  Found ammo
     */
    static ItemStack findAmmo(IToolStackView tool, ItemStack bow, Player player, Predicate<ItemStack> predicate) {
        int projectilesDesired = 1;
        // treat client side as creative, no need to shrink the stacks clientside
        Level level = player.level();
        boolean creative = player.getAbilities().instabuild || level.isClientSide;

        // first search, find what ammo type we want
        ItemStack standardAmmo = player.getProjectile(bow);
        ItemStack resultStack = ItemStack.EMPTY;
        for (ModifierEntry entry : tool.getModifierList()) {
            BowAmmoModifierHook hook = entry.getHook(ModifierHooks.BOW_AMMO);
            ItemStack ammo = hook.findAmmo(tool, entry, player, standardAmmo, predicate);
            if (!ammo.isEmpty()) {
                // if creative, we are done, just return the ammo with the given size
                if (creative) {
                    return ItemHandlerHelper.copyStackWithSize(ammo, projectilesDesired);
                }

                // not creative, split out the desired amount. We may have to do more work if it is too small
                resultStack = ItemHandlerHelper.copyStackWithSize(ammo, Math.min(projectilesDesired, ammo.getCount()));
                hook.shrinkAmmo(tool, entry, player, ammo, resultStack.getCount());
                break;
            }
        }

        // result stack being empty means no modifier found it, so we use standard ammo
        if (resultStack.isEmpty()) {
            // if standard ammo is empty as well, nothing else to do but give up
            if (standardAmmo.isEmpty()) {
                return ItemStack.EMPTY;
            }
            // with standard ammo, in creative we can just return that
            if (creative) {
                return ItemHandlerHelper.copyStackWithSize(standardAmmo, projectilesDesired);
            }
            // make a copy of the result, up to the desired size
            standardAmmo.setDamageValue(standardAmmo.getDamageValue()+projectilesDesired);
            resultStack = standardAmmo.copy();
        }
        return resultStack;
    }

    /**
     * Looks for a matching item stack in the player inventory
     * @param bow        Bow stack
     * @param living     Entity to search
     * @param predicate  Predicate for finding ammo in modifiers
     * @return  Matching stack in the player inventory
     */
    private static ItemStack findMatchingAmmo(ItemStack bow, LivingEntity living, Predicate<ItemStack> predicate) {
        // start with hands, find one that matches but is not the bow
        for (InteractionHand hand : InteractionHand.values()) {
            ItemStack stack = living.getItemInHand(hand);
            if (stack != bow && predicate.test(stack)) {
                return ForgeHooks.getProjectile(living, bow, stack);
            }
        }

        // was not in hand, search the rest of the inventory
        if (living instanceof Player player) {
            Inventory inventory = player.getInventory();
            for (int i = 0; i < inventory.getContainerSize(); i++) {
                ItemStack stack = inventory.getItem(i);
                if (!stack.isEmpty() && predicate.test(stack)) {
                    return ForgeHooks.getProjectile(player, bow, stack);
                }
            }
        }
        return ItemStack.EMPTY;
    }

    @Override
    public Predicate<ItemStack> getAllSupportedProjectiles() {
        return BOOMSTICK_SHOT;
    }

    @Override
    public void onStopUsing(ItemStack stack, LivingEntity entity, int count) {
        if (entity instanceof Player player) {
            ItemStack stack1 = findGunpowder(player);
            if (!player.getAbilities().instabuild) {
                stack1.shrink(1);
            }
        }

        super.onStopUsing(stack, entity, count);
    }

    public ItemStack findGunpowder(Player player) {
        for (int i = 0; i < player.getInventory().items.size(); i++) {
            ItemStack itemStack = player.getInventory().items.get(i);

            if (itemStack.getItem() == Items.GUNPOWDER) {
                return itemStack;
            }
        }
        return ItemStack.EMPTY;
    }
}

// @Override
// public void playShootSound(float power, World world, EntityPlayer entityPlayer) {
//     world.playSound(null, entityPlayer.posX, entityPlayer.posY, entityPlayer.posZ, ArsenalSounds.BOOMSTICK_SHOT, SoundCategory.NEUTRAL, 1.0F, 0.5f + itemRand.nextFloat() * 0.1f);
// }


   /* private ImmutableList<Item> boltMatches = null;

    @Override
    protected List<Item> getAmmoItems() {
        if(boltMatches == null) {
            ImmutableList.Builder<Item> builder = ImmutableList.builder();
            if(ArsenalItems.boomstickShot != null) {
                builder.add(ArsenalItems.boomstickShot);
            }
            boltMatches = builder.build();
        }
        return boltMatches;
    }

    @Override
    public ProjectileLauncherNBT buildTagData(List<Material> materials) {
        ProjectileLauncherNBT data = new ProjectileLauncherNBT();
        HandleMaterialStats handle = materials.get(0).getStatsOrUnknown(MaterialTypes.HANDLE);
        BowMaterialStats barrel = materials.get(1).getStatsOrUnknown(MaterialTypes.BOW);
        HeadMaterialStats bayonet = materials.get(2).getStatsOrUnknown(MaterialTypes.HEAD);

        data.handle(handle);
        data.limb(barrel);
        data.head(bayonet);

        data.bonusDamage *= 1.5f;

        return data;
    }

    @SideOnly(Side.CLIENT)
    @Override
    public ICrosshair getCrosshair(ItemStack itemStack, EntityPlayer player) {
        return Crosshairs.T;
    }

    @SideOnly(Side.CLIENT)
    @Override
    public float getCrosshairState(ItemStack itemStack, EntityPlayer player) {
        if(isLoaded(itemStack)) {
            return 1f;
        }
        else if(player.getActiveItemStack() != itemStack) {
            return 0f;
        }
        return getDrawbackProgress(itemStack, player);
    }
}*/

/*
super(PartMaterialType.handle(TATools.boomstickHandle),
                PartMaterialType.bow(TATools.boomstickBarrel),
                PartMaterialType.head(TATools.bayonet));

        this.addCategory(Category.WEAPON);
        this.setUnlocalizedName("boomstick").setRegistryName("boomstick");
*/
