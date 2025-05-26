package io.github.redstoneparadox.tinkersarsenal.client;

import io.github.redstoneparadox.tinkersarsenal.client.rendering.RenderBoomstickShot;
import io.github.redstoneparadox.tinkersarsenal.init.ArsenalEntities;
import io.github.redstoneparadox.tinkersarsenal.init.ArsenalItems;
import io.github.redstoneparadox.tinkersarsenal.tools.ranged.ToolBoomstickItem;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import slimeknights.tconstruct.library.tools.part.IToolPart;

/**
 * Created by RedstoneParadox on 6/10/2018.
 */
public class TinkersArsenalClient {

    public static void init(IEventBus bus) {
        bus.addListener(TinkersArsenalClient::setup);
        bus.addListener(TinkersArsenalClient::renderers);
        MinecraftForge.EVENT_BUS.addListener(TinkersArsenalClient::renderPlayer);
    }

    protected static final ResourceLocation PROPERTY_IS_LOADED = new ResourceLocation("loaded");

    static void renderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(ArsenalEntities.BOOMSTICK_SHOT, RenderBoomstickShot::new);
    }

    static void setup(FMLClientSetupEvent event) {
        ArsenalItems.initToolGUIs();

        ItemProperties.register(ArsenalItems.boomstick,PROPERTY_IS_LOADED,(pStack, pLevel, pEntity, pSeed) -> {
            return 1;//todo
        });

        /*this.addPropertyOverride(PROPERTY_PULL_PROGRESS, pullProgressPropertyGetter);
        this.addPropertyOverride(PROPERTY_IS_PULLING, isPullingPropertyGetter);
        this.addPropertyOverride(PROPERTY_IS_LOADED, new BooleanItemPropertyGetter() {
            @Override
            public boolean applyIf(ItemStack stack, @Nullable World worldIn, @Nullable EntityLivingBase entityIn) {
                return entityIn != null && isLoaded(stack);
            }
        });*/
    }

    public static  <T extends Item & IToolPart> void registerToolPartModel(T part) {
        //ModelRegisterUtil.registerPartModel(part);
    }

    //public void registerToolModel(ToolCore toolCore) {
   //     ModelRegisterUtil.registerToolModel(toolCore);
   // }

    public static void renderPlayer(RenderPlayerEvent.Pre event) {

        Player player = event.getEntity();
        InteractionHand right = InteractionHand.MAIN_HAND;
        InteractionHand left = InteractionHand.OFF_HAND;

        //todo check arm?

        if (isCarryingLoadedBoomstick(player, right)) {
            (event.getRenderer().getModel()).rightArmPose = HumanoidModel.ArmPose.BOW_AND_ARROW;
        } else if (isCarryingLoadedBoomstick(player, left)) {
            (event.getRenderer().getModel()).leftArmPose = HumanoidModel.ArmPose.BOW_AND_ARROW;
        }
    }

    private static boolean isCarryingLoadedBoomstick(Player player, InteractionHand hand) {
        if (player.getItemInHand(hand).getItem() == ArsenalItems.boomstick) {
            return ToolBoomstickItem.isLoaded(player.getItemInHand(hand));
        }
        else {
            return false;
        }
    }
}
