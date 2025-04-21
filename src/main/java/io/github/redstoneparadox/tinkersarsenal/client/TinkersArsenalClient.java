package io.github.redstoneparadox.tinkersarsenal.client;

import io.github.redstoneparadox.tinkersarsenal.entities.BoomstickShotEntity;
import io.github.redstoneparadox.tinkersarsenal.entities.rendering.RenderBoomstickShot;
import io.github.redstoneparadox.tinkersarsenal.init.ArsenalEntities;
import io.github.redstoneparadox.tinkersarsenal.events.ArsenalRenderEvents;
import io.github.redstoneparadox.tinkersarsenal.tools.ArsenalTools;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.event.EntityRenderersEvent;
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
    }

    protected static final ResourceLocation PROPERTY_IS_LOADED = new ResourceLocation("loaded");

    static void renderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(ArsenalEntities.BOOMSTICK_SHOT, RenderBoomstickShot::new);
    }

    static void setup(FMLClientSetupEvent event) {
        ArsenalEntities.initModels();
        MinecraftForge.EVENT_BUS.register(new ArsenalRenderEvents());
        ArsenalTools.initToolGUIs();

        ItemProperties.register(ArsenalTools.boomstick,PROPERTY_IS_LOADED,(pStack, pLevel, pEntity, pSeed) -> {
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
}
