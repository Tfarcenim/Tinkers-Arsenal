package io.github.redstoneparadox.tinkersarsenal.client;

import io.github.redstoneparadox.tinkersarsenal.entities.ArsenalEntities;
import io.github.redstoneparadox.tinkersarsenal.events.ArsenalRenderEvents;
import io.github.redstoneparadox.tinkersarsenal.tools.ArsenalTools;
import net.minecraft.world.item.Item;
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
    }

    static void setup(FMLClientSetupEvent event) {
        ArsenalEntities.initModels();
        MinecraftForge.EVENT_BUS.register(new ArsenalRenderEvents());
        ArsenalTools.initToolGUIs();
    }

    public static  <T extends Item & IToolPart> void registerToolPartModel(T part) {
        //ModelRegisterUtil.registerPartModel(part);
    }

    //public void registerToolModel(ToolCore toolCore) {
   //     ModelRegisterUtil.registerToolModel(toolCore);
   // }
}
