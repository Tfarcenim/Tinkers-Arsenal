package io.github.redstoneparadox.tinkersarsenal;

import io.github.redstoneparadox.tinkersarsenal.client.TinkersArsenalClient;
import io.github.redstoneparadox.tinkersarsenal.datagen.TinkersArsenalDatagen;
import io.github.redstoneparadox.tinkersarsenal.init.ArsenalEntities;
import io.github.redstoneparadox.tinkersarsenal.materials.ArsenalToolMaterials;
import io.github.redstoneparadox.tinkersarsenal.misc.ArsenalConfig;
import io.github.redstoneparadox.tinkersarsenal.misc.ArsenalSounds;
import io.github.redstoneparadox.tinkersarsenal.tools.ArsenalTools;
import io.github.redstoneparadox.tinkersarsenal.traits.ArsenalToolTraits;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLEnvironment;
import net.minecraftforge.registries.RegisterEvent;
import org.apache.logging.log4j.Logger;

@Mod(TinkersArsenal.MOD_ID)
public class TinkersArsenal {
    public static Logger logger;

    public static final String MOD_ID = "tinkersarsenal";

    public TinkersArsenal() {
        ModLoadingContext.get().registerConfig(ModConfig.Type.SERVER, ArsenalConfig.SERVER_SPEC);
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        bus.addListener(this::register);
        bus.addListener(TinkersArsenalDatagen::gather);
        ArsenalToolMaterials.initToolMaterials();

        if (FMLEnvironment.dist.isClient()) {
            TinkersArsenalClient.init(bus);
        }

        if (ModIntegration.conarm.loaded) {
            //  ArsenalArmorTraits.initArmorTraits();
            // ArsenalArmorMaterials.initArmorMaterials();
        }
    }


    void register(RegisterEvent event) {
        if (event.getVanillaRegistry() == (Registry<?>)BuiltInRegistries.ITEM) {
            ArsenalTools.initToolParts(event);
        }
        event.register(Registries.ENTITY_TYPE,id("boomstick_shot"),() -> ArsenalEntities.BOOMSTICK_SHOT);
        event.register(Registries.SOUND_EVENT,id("boomstick_shot"),() -> ArsenalSounds.BOOMSTICK_SHOT);
    }

    public static ResourceLocation id(String path) {
        return new ResourceLocation(MOD_ID,path);
    }
}
