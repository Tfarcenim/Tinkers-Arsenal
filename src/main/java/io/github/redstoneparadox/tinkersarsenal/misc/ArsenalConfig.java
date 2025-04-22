package io.github.redstoneparadox.tinkersarsenal.misc;

import io.github.redstoneparadox.tinkersarsenal.TinkersArsenal;

import net.minecraftforge.common.ForgeConfigSpec;
import org.apache.commons.lang3.tuple.Pair;

/**
 * Created by RedstoneParadox on 8/23/2018.
 */
public class ArsenalConfig {

    public static final ArsenalConfig CONFIG;
    public static final ForgeConfigSpec SERVER_SPEC;

    static {
        final Pair<ArsenalConfig, ForgeConfigSpec> specPair = new ForgeConfigSpec.Builder().configure(ArsenalConfig::new);
        SERVER_SPEC = specPair.getRight();
        CONFIG = specPair.getLeft();
    }



    public ArsenalConfig(ForgeConfigSpec.Builder builder) {
        builder.push("base_category");
        builder.pop();
    }

    //Misc:
    private static int version = 1;

    private static final String disableText = "Set to false to disable ";
    private static final String shaftText = " as a shaft material.";

    private static final String notImp = " (Not implemented yet!)";

    private static final String setCompat = "Enable/disable material compatibility for ";

   // @Config.LangKey("category.base.name")
  //  @Config.Comment("Enable/disable materials added by Tinker's Arsenal")

    //Config access stuff:
    public static boolean isMaterialAllowed(String material) {
      //  if (getMaterialTypes().containsKey(material)) {
      //      return getMaterialTypes().get(material);
      //  }
        TinkersArsenal.logger.error("Could not find config option for " + material + "!");
        return false;
    }

    /*private static Map<String, Boolean> getMaterialTypes() {
        Map<String, Boolean> map = new HashMap<>();
        //Base materials:
        map.put("gold", BASE_CATEGORY.gold);
        map.put("diamond", BASE_CATEGORY.diamond);
        //Common ore-dict materials:
        map.put("tin", ORE_DICTIONARY_CATEGORY.tin);
        map.put("aluminum", ORE_DICTIONARY_CATEGORY.aluminum);
        map.put("constantan", ORE_DICTIONARY_CATEGORY.constantan);
        map.put("nickel", ORE_DICTIONARY_CATEGORY.nickel);
        map.put("platinum", ORE_DICTIONARY_CATEGORY.platinum);
        map.put("iridium", ORE_DICTIONARY_CATEGORY.iridium);
        map.put("manaInfusedMetal", ORE_DICTIONARY_CATEGORY.mithril);
        map.put("mithril", ORE_DICTIONARY_CATEGORY.mithril);
        map.put("invar", ORE_DICTIONARY_CATEGORY.invar);
        //Thermal Foundation Materials:
        map.put("luminum", THERMAL_EXPANSION_CATEGORY.luminum);
        map.put("signalium", THERMAL_EXPANSION_CATEGORY.signalium);
        map.put("enderium", THERMAL_EXPANSION_CATEGORY.enderium);
        //Thermal Foundation Shaft Materials:
        map.put("blizz_rod", THERMAL_EXPANSION_CATEGORY.blizz);
        map.put("blitz_rod", THERMAL_EXPANSION_CATEGORY.blitz);
        map.put("basalz_rod", THERMAL_EXPANSION_CATEGORY.basalz);
        return map;
    }*/
}
