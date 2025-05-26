package io.github.redstoneparadox.tinkersarsenal;

import io.github.redstoneparadox.tinkersarsenal.client.TinkersArsenalClient;
import io.github.redstoneparadox.tinkersarsenal.datagen.TinkersArsenalDatagen;
import io.github.redstoneparadox.tinkersarsenal.init.ArsenalEntities;
import io.github.redstoneparadox.tinkersarsenal.materials.ArsenalToolMaterials;
import io.github.redstoneparadox.tinkersarsenal.misc.ArsenalSounds;
import io.github.redstoneparadox.tinkersarsenal.init.ArsenalItems;
import io.github.redstoneparadox.tinkersarsenal.traits.ArsenalToolTraits;
import io.github.redstoneparadox.tinkersarsenal.traits.tooltraits.ResilienceModifier;
import io.github.redstoneparadox.tinkersarsenal.traits.tooltraits.DiamondEdgeModifier;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLEnvironment;
import net.minecraftforge.registries.RegisterEvent;
import org.apache.logging.log4j.Logger;
import slimeknights.tconstruct.library.modifiers.ModifierManager;
import slimeknights.tconstruct.library.tools.part.IMaterialItem;
import slimeknights.tconstruct.tools.TinkerToolParts;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;

@Mod(TinkersArsenal.MOD_ID)
public class TinkersArsenal {
    public static Logger logger;

    public static final String MOD_ID = "tinkersarsenal";

    public TinkersArsenal() {
        //ModLoadingContext.get().registerConfig(ModConfig.Type.SERVER, ArsenalConfig.SERVER_SPEC);
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        bus.addListener(this::register);
        bus.addListener(TinkersArsenalDatagen::gather);
        bus.addListener(this::modifierRegister);
        bus.addListener(this::creativeTabs);
        ArsenalToolMaterials.initToolMaterials();

        if (FMLEnvironment.dist.isClient()) {
            TinkersArsenalClient.init(bus);
        }

        if (ModIntegration.conarm.loaded) {
            //  ArsenalArmorTraits.initArmorTraits();
            // ArsenalArmorMaterials.initArmorMaterials();
        }
    }

    void modifierRegister(ModifierManager.ModifierRegistrationEvent event) {
        event.registerStatic(ArsenalToolTraits.resilience,new ResilienceModifier());
        event.registerStatic(ArsenalToolTraits.diamond_edge,new DiamondEdgeModifier());
    }

    void register(RegisterEvent event) {
        if (event.getVanillaRegistry() == (Registry<?>)BuiltInRegistries.ITEM) {
            ArsenalItems.initToolParts(event);
        }
        event.register(Registries.ENTITY_TYPE,id("boomstick_shot"),() -> ArsenalEntities.BOOMSTICK_SHOT);
        event.register(Registries.SOUND_EVENT,id("boomstick_shot"),() -> ArsenalSounds.BOOMSTICK_SHOT);
    }

    void creativeTabs(BuildCreativeModeTabContentsEvent event) {
        if (event.getTab() == TinkerToolParts.tabToolParts.get()) {
            List<ItemStack> stacks = new ArrayList<>();

            Consumer<ItemStack> adder = stacks::add;
            ArsenalItems.boomstick_stock.addVariants(adder,"");
            ArsenalItems.boomstickBarrel.addVariants(adder,"");
            ArsenalItems.bayonet.addVariants(adder,"");
            ArsenalItems.bulletShell.addVariants(adder,"");
            ArsenalItems.bullet_head.addVariants(adder,"");

            stacks.forEach(event::accept);
        }
    }
    /** Adds a tool part to the tab */
    private static void accept(Consumer<ItemStack> output, Supplier<? extends IMaterialItem> item) {
        item.get().addVariants(output, "");
    }


    public static ResourceLocation id(String path) {
        return new ResourceLocation(MOD_ID,path);
    }
}
