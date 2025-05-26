package io.github.redstoneparadox.tinkersarsenal.traits;

import io.github.redstoneparadox.tinkersarsenal.TinkersArsenal;
import io.github.redstoneparadox.tinkersarsenal.traits.tooltraits.*;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.modifiers.ModifierId;

/**
 * Created by RedstoneParadox on 7/29/2018.
 */
public class ArsenalToolTraits {
    public static final Modifier DIAMOND_EDGE = new DiamondEdgeModifier();
    public static final Modifier ENDURING = new TraitEnduring();
    public static final ModifierId malleable = new ModifierId(TinkersArsenal.id("malleable"));//0xdc7613
    public static final Modifier MISSINGNO = new TraitMissingno();
    public static final ModifierId resilience = new ModifierId(TinkersArsenal.id("resilience"));
    public static final ModifierId diamond_edge = new ModifierId(TinkersArsenal.id("diamond_edge"));

    public static final Modifier THERMALLY_ACTIVE_1 = new TraitThermallyActive(1);
    public static final Modifier THERMALLY_ACTIVE_2 = new TraitThermallyActive(2);
    // Shaft traits
    public static final Modifier GROUNDING = new TraitGrounding();
    public static final Modifier HARD_HITTING = new TraitHardHitting();
    public static final Modifier SWIFT_FLIGHT = new TraitSwiftFlight();

}
