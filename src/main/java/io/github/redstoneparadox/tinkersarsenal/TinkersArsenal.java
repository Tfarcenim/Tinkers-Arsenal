package io.github.redstoneparadox.tinkersarsenal;

import io.github.redstoneparadox.tinkersarsenal.client.TinkersArsenalClient;
import io.github.redstoneparadox.tinkersarsenal.entities.ArsenalEntities;
import io.github.redstoneparadox.tinkersarsenal.events.ArsenalRegistryEvents;
import io.github.redstoneparadox.tinkersarsenal.materials.ArsenalToolMaterials;
import io.github.redstoneparadox.tinkersarsenal.tools.ArsenalTools;
import io.github.redstoneparadox.tinkersarsenal.traits.ArsenalToolTraits;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLEnvironment;
import net.minecraftforge.registries.RegisterEvent;
import org.apache.logging.log4j.Logger;

@Mod(TinkersArsenal.MOD_ID)
public class TinkersArsenal {
    public static Logger logger;

    public static final String MOD_ID = "tinkersarsenal";

    public TinkersArsenal() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        bus.addListener(this::register);
        MinecraftForge.EVENT_BUS.register(new ArsenalRegistryEvents());
        ArsenalToolTraits.initToolTraits();
        ArsenalToolMaterials.initToolMaterials();

        if (FMLEnvironment.dist.isClient()) {
            TinkersArsenalClient.init(bus);
        }

        if (ModList.get().isLoaded("conarm")) {
            //  ArsenalArmorTraits.initArmorTraits();
            // ArsenalArmorMaterials.initArmorMaterials();
        }

        ArsenalEntities.init();
    }


    void register(RegisterEvent event) {
        IForgeRegistry<Item> registry = event.getRegistry();
        ArsenalTools.initToolParts(registry);
    }

    /**
     * This is the final initialization event. Register actions from other mods here
     */
    @Mod.EventHandler
    public void postinit(FMLPostInitializationEvent event) {
        proxy.postInit(event);
    }
}
