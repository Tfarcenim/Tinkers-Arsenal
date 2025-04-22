package io.github.redstoneparadox.tinkersarsenal.materials;

import io.github.redstoneparadox.tinkersarsenal.TinkersArsenal;

import slimeknights.tconstruct.library.materials.definition.MaterialId;

public class ArsenalToolMaterials {


    public static final MaterialId GOLD = id("gold");
    public static final MaterialId DIAMOND = id("diamond");

    private static MaterialId id(String name) {
        return new MaterialId(TinkersArsenal.MOD_ID, name);
    }


    public static void initToolMaterials() {
        /*ArsenalMaterial gold = new ArsenalMaterial("gold", 0xeaee57);
        setCraftability(gold, "ingotGold", TinkerFluids.gold);
        gold.addTrait(ArsenalToolTraits.MALLEABLE, PART_HEAD);
        addTraits(gold, ArsenalToolTraits.MALLEABLE);
        gold.setRepresentativeItem(new ItemStack(Items.GOLD_INGOT));
        TinkerRegistry.addMaterial(gold);

        ArsenalMaterial diamond = new ArsenalMaterial("diamond", 0x33ebcb);
        setCraftability(diamond, "gemDiamond");
        diamond.addTrait(ArsenalToolTraits.DIAMOND_EDGE, PART_HEAD);
        diamond.addTrait(ArsenalToolTraits.RESILIENCE, PART_HANDLE);
        diamond.addTrait(ArsenalToolTraits.RESILIENCE, PART_EXTRA);
        diamond.setRepresentativeItem(new ItemStack(Items.DIAMOND));
        TinkerRegistry.addMaterial(diamond);

        if (isRegistrable("tin") && OreDictionary.doesOreNameExist("ingotTin")) {
            ArsenalMaterial tin = new ArsenalMaterial("tin", 0xBDD1DE);
            setCraftability(tin, "ingotTin", TinkerFluids.tin);
            tin.addTrait(TinkerTraits.cheap);
            addStats(tin, 85, 4.50f, 4.00f, HV1FE, 0.5f, -25, 75, 0.5f, 1.5f, 1.5f);
            tin.setRepresentativeItem(OreDictionary.getOres("ingotTin").get(0));
            TinkerRegistry.addMaterial(tin);
        }

        if (isRegistrable("aluminum") && OreDictionary.doesOreNameExist("ingotAluminum")) {
            ArsenalMaterial aluminum = new ArsenalMaterial("aluminum", 0xE6E6F2);
            setCraftability(aluminum, "ingotAluminum", TinkerFluids.aluminum);
            aluminum.addTrait(TinkerTraits.lightweight, PART_HEAD);
            addTraits(aluminum, TinkerTraits.lightweight);
            addStats(aluminum, 150, 10, 4.00f, HV1FE, 0.5f, -75, 50, 0.5f, 1.5f, 1.75f);
            TinkerRegistry.addMaterial(aluminum);
        }

        if (isRegistrable("constantan") && OreDictionary.doesOreNameExist("ingotConstantan")) {
            ArsenalMaterial constantan = new ArsenalMaterial("constantan", 0xE4BB4D);
            setCraftability(constantan, "ingotConstantan");
            constantan.addTrait(ArsenalToolTraits.THERMALLY_ACTIVE_2, PART_HEAD);
            addTraits(constantan, ArsenalToolTraits.THERMALLY_ACTIVE_1);
            addStats(constantan, 175, 6, 4.50f, HV2DIA, 1.3f, 150, 100, 0.5f, 1.5f, 1.75f);
            constantan.setRepresentativeItem("ingotConstantan");
            TinkerRegistry.addMaterial(constantan);
        }

        if (isRegistrable("nickel") && OreDictionary.doesOreNameExist("ingotNickle")) {
            ArsenalMaterial nickel = new ArsenalMaterial("nickel", 0xF6F8BD);
            setCraftability(nickel, "ingotNickel");
            addTraits(nickel, ArsenalToolTraits.MISSINGNO);
            addStats(nickel, 100, 6.5f, 5.5f, HV2DIA, 1.3f, 100, 200, 0.5f, 1.5f, 1.75f);
            //TinkerRegistry.addMaterial(nickel);
        }

        if (isRegistrable("platinum") && OreDictionary.doesOreNameExist("ingotPlatinum")) {
            ArsenalMaterial platinum = new ArsenalMaterial("platinum", 0x6FEAEF);
            setCraftability(platinum, "ingotPlatinum");
            addTraits(platinum, ArsenalToolTraits.MISSINGNO);
            addStats(platinum, 650, 9, 6.50f, HV4CO, 1.6f, 250, 750, 0.5f, 1.5f, 1.75f);
            platinum.setRepresentativeItem(OreDictionary.getOres("ingotPlatinum").get(0));
            //TinkerRegistry.addMaterial(platinum);
        }

        if (isRegistrable("iridium") && OreDictionary.doesOreNameExist("ingotIridium")) {
            ArsenalMaterial iridium = new ArsenalMaterial("iridium", 0xD4D2E2);
            setCraftability(iridium, "ingotIridium");
            iridium.addTrait(ArsenalToolTraits.HARD_HITTING);
            iridium.addTrait(TinkerTraits.heavy, PART_HEAD);
            addTraits(iridium, TinkerTraits.heavy);
            addStats(iridium, 650, 5, 5.5f, HV2DIA, 1.1f, 300, 650, 0.3f, 1.5f, 1.9f);
            iridium.setRepresentativeItem("ingotIridium");
            TinkerRegistry.addMaterial(iridium);
        }

        if (isRegistrable("mithril") && OreDictionary.doesOreNameExist("ingotMithril")) {
            ArsenalMaterial mithril = new ArsenalMaterial("mithril", 0x6DA7C9);
            setCraftability(mithril, "ingotMithril");
            addTraits(mithril, ArsenalToolTraits.MISSINGNO);
            //TinkerRegistry.addMaterial(mithril);
        }

        if (isRegistrable("invar") && OreDictionary.doesOreNameExist("ingotInvar")) {
            ArsenalMaterial invar = new ArsenalMaterial("invar", 0xB4BCB9);
            setCraftability(invar, "ingotInvar");
            invar.addTrait(ArsenalToolTraits.ENDURING, PART_HEAD);
            addTraits(invar, ArsenalToolTraits.ENDURING);
            addStats(invar, 175, 6.50f, 5.50f, HV2DIA, 1.5f, 50, 250, 0.5f, 1.5f, 1.75f);
            invar.setRepresentativeItem(OreDictionary.getOres("ingotInvar").get(0));
            TinkerRegistry.addMaterial(invar);
        }

        if (ModIntegration.thermalfoundation.loaded) {
            Item materialItem = BuiltInRegistries.ITEM.get(new ResourceLocation("thermalfoundation","material"));

            if (isRegistrable("blizz_rod")) {
                ArsenalMaterial blizz = new ArsenalMaterial("blizz_rod", 0xC6F2F2);
                ItemStack blizzStack = new ItemStack(materialItem, 1, 2048);
                setCraftability(blizz, blizzStack);
                blizz.addTrait(TinkerTraits.hovering, PART_SHAFT);
                blizz.addTrait(TinkerTraits.freezing, PART_SHAFT);
                addShaftStats(blizz, 0.8f, 1);
                blizz.setRepresentativeItem(blizzStack);
                TinkerRegistry.addMaterial(blizz);
            }

            if (isRegistrable("blitz_rod")) {
                ArsenalMaterial blitz = new ArsenalMaterial("blitz_rod", 0xD1DD4E);
                ItemStack blitzStack = new ItemStack(materialItem, 1, 2050);
                setCraftability(blitz, blitzStack);
                blitz.addTrait(ArsenalToolTraits.SWIFT_FLIGHT);
                addShaftStats(blitz, 0.9f, 1);
                blitz.setRepresentativeItem(blitzStack);
                TinkerRegistry.addMaterial(blitz);
            }

            if (isRegistrable("basalz_rod")) {
                ArsenalMaterial basalz = new ArsenalMaterial("basalz_rod", 0x696055);
                ItemStack basalzStack = new ItemStack(materialItem, 1, 2052);
                setCraftability(basalz, basalzStack);
                basalz.addTrait(TinkerTraits.hovering, PART_SHAFT);
                basalz.addTrait(ArsenalToolTraits.HARD_HITTING, PART_SHAFT);
                addShaftStats(basalz, 0.8f, 1);
                basalz.setRepresentativeItem(basalzStack);
                TinkerRegistry.addMaterial(basalz);
            }
        }*/
    }
}
