package io.github.redstoneparadox.tinkersarsenal.traits;

import io.github.redstoneparadox.tinkersarsenal.traits.tooltraits.*;
import slimeknights.tconstruct.library.modifiers.Modifier;

/**
 * Created by RedstoneParadox on 7/29/2018.
 */
public class ArsenalToolTraits {
    public static final Modifier DIAMOND_EDGE = new TraitDiamondEdge();
    public static final Modifier ENDURING = new TraitEnduring();
    public static final Modifier MALLEABLE = new TraitMalleable();
    public static final Modifier MISSINGNO = new TraitMissingno();
    public static final Modifier RESILIENCE = new TraitResilience();
    public static final Modifier THERMALLY_ACTIVE_1 = new TraitThermallyActive(1);
    public static final Modifier THERMALLY_ACTIVE_2 = new TraitThermallyActive(2);
    // Shaft traits
    public static final Modifier GROUNDING = new TraitGrounding();
    public static final Modifier HARD_HITTING = new TraitHardHitting();
    public static final Modifier SWIFT_FLIGHT = new TraitSwiftFlight();

}
