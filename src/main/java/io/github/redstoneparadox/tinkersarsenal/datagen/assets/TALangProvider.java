package io.github.redstoneparadox.tinkersarsenal.datagen.assets;

import io.github.redstoneparadox.tinkersarsenal.TinkersArsenal;
import io.github.redstoneparadox.tinkersarsenal.materials.ArsenalToolMaterials;
import io.github.redstoneparadox.tinkersarsenal.init.ArsenalItems;
import io.github.redstoneparadox.tinkersarsenal.traits.ArsenalToolTraits;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.data.LanguageProvider;
import slimeknights.tconstruct.library.client.materials.MaterialTooltipCache;
import slimeknights.tconstruct.library.materials.definition.MaterialVariantId;
import slimeknights.tconstruct.library.modifiers.ModifierId;
import slimeknights.tconstruct.library.utils.Util;

public class TALangProvider extends LanguageProvider {
    public TALangProvider(PackOutput output) {
        super(output, TinkersArsenal.MOD_ID, "en_us");
    }

    @Override
    protected void addTranslations() {
        addMaterialTranslation(ArsenalToolMaterials.DIAMOND,"Diamond");
        addModifier(ArsenalToolTraits.resilience,"Resilience","placeholder","§oTougher than Tough!§r\\nThe resilience is strong with this one.");
        addModifier(ArsenalToolTraits.diamond_edge,"Diamond Edge","placeholder","§oSo sharp!§r\\nCan better cut through armor if you hit it at just the right angle.") ;

        add(ArsenalItems.boomstick_stock,"Boomstick Stock");
        add(ArsenalItems.boomstickBarrel,"Boomstick Barrel");
        add(ArsenalItems.bullet_head,"Bullet Head");
        add(ArsenalItems.bullet_shell,"Bullet Shell");

        add(ArsenalItems.boomstick,"Boomstick");
        add(ArsenalItems.boomstick_shot,"Boomstick Shot");
       // add(ArsenalItems.shears,"Shears");
        addItemDesc(ArsenalItems.boomstick,
                "The Boomstick is a gun capable of firing powerful shots. It has to be loaded beforehand by using right click, but can be used at any moment afterwards. Uses Boomstick shots and gunpowder.");

        addItemDesc(ArsenalItems.boomstick_shot,
                "The Boomstick Shots are used as ammo for the Boomstick (alongside gunpowder). One stack provides many shots, and they can be modified as any tool.");
    }

    void addItemDesc(Item item,String value) {
        add(item.getDescriptionId()+".description",value);
    }

    void addMaterialTranslation(MaterialVariantId id,String text) {
        String materialKey = MaterialTooltipCache.getKey(id);
        add(materialKey,text);
    }

    //  "modifier.tconstruct.cultivated": "Cultivated",
    //  "modifier.tconstruct.cultivated.flavor": "Economical!",
    //  "modifier.tconstruct.cultivated.description": "Tool practically grows more material when repairing",

    void addModifier(ModifierId id,String name,String flavor,String desc) {
        String base = Util.makeTranslationKey("modifier", id);
        String flavorKey = base + ".flavor";
        String descKey = base + ".description";
        add(base,name);
        add(flavorKey,flavor);
        add(descKey,desc);
    }
}
//modifier.diamond_edge.name=Diamond Edge
//modifier.diamond_edge.desc=§oSo sharp!§r\nCan better cut through armor if you hit it at just the right angle.
//modifier.enduring.name=Enduring
//modifier.enduring.desc=§oDurability Marathon!§r\nTakes less damage over long periods of continuous use.
//modifier.grounding.name=Grounding
//modifier.grounding.desc=§oDown to earth!§r\n
//modifier.hard_hitting.name=Hard Hitting
//modifier.hard_hitting.desc=§oHuge Impact!§r\nDoesn't pull its punches.

//modifier.missingno.name=Missingno!
//modifier.missingno.desc=§oNot a glitch...§r\n...just a placeholder.

//modifier.swift_flight.name=Swift Flight
//modifier.swift_flight.desc=§oSoars like a bird!§r\nMoves faster than other projectiles.
//modifier.thermally_active.name=Thermally Active
//modifier.thermally_active.desc=§oFeel the Burn!§r\nLeftover heat from the forging process resides in this tool.
