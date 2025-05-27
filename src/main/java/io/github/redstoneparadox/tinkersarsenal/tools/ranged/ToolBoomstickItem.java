package io.github.redstoneparadox.tinkersarsenal.tools.ranged;

import io.github.redstoneparadox.tinkersarsenal.init.ArsenalItems;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;
import slimeknights.tconstruct.library.modifiers.hook.ranged.BowAmmoModifierHook;
import slimeknights.tconstruct.library.tools.definition.ToolDefinition;
import slimeknights.tconstruct.library.tools.item.ranged.ModifiableCrossbowItem;
import slimeknights.tconstruct.library.tools.nbt.ToolStack;
import slimeknights.tconstruct.tools.modifiers.ability.interaction.BlockingModifier;

import java.util.function.Predicate;

/**
 * Created by RedstoneParadox on 7/31/2018.
 */
public class ToolBoomstickItem extends ModifiableCrossbowItem {
    private static final String TAG_Loaded = "Loaded";

    public static final Predicate<ItemStack> BOOMSTICK_SHOT = stack -> stack.is(ArsenalItems.boomstick_shot);

    public static final Predicate<ItemStack> GUNPOWDER = stack -> stack.is(Items.GUNPOWDER);

    public ToolBoomstickItem(Properties properties, ToolDefinition toolDefinition) {
        super(properties, toolDefinition,BOOMSTICK_SHOT);


        //   (PartMaterialType.handle(ArsenalItems.boomstickHandle),
        //          PartMaterialType.bow(ArsenalItems.boomstickBarrel),
        //          PartMaterialType.head(ArsenalItems.bayonet));


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
        return stack.hasTag() && stack.getTag().getBoolean(TAG_Loaded);
    }

    public static void setLoaded(ItemStack stack, boolean isLoaded) {
        stack.getOrCreateTag().putBoolean(TAG_Loaded, isLoaded);
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
        ItemStack stack = player.getItemInHand(hand);
        ToolStack tool = ToolStack.from(stack);
        if (tool.isBroken()) {
            return InteractionResultHolder.fail(stack);
        }

        if (!BowAmmoModifierHook.hasAmmo(tool, stack, player, GUNPOWDER)) {
            return InteractionResultHolder.fail(stack);
        }

        if (isLoaded(stack)) {
            super.onStopUsing(stack, player, 0);
            setLoaded(stack, false);
        } else {
            return super.use(level, player, hand);
        }
        return InteractionResultHolder.sidedSuccess(stack, level.isClientSide);
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
