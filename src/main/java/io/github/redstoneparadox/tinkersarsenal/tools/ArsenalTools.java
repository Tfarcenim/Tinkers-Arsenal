package io.github.redstoneparadox.tinkersarsenal.tools;

import io.github.redstoneparadox.tinkersarsenal.TinkersArsenal;
import io.github.redstoneparadox.tinkersarsenal.init.TAToolDefinitions;
import io.github.redstoneparadox.tinkersarsenal.tools.ranged.AmmoBoomstickShot;
import io.github.redstoneparadox.tinkersarsenal.tools.ranged.ToolBoomstickItem;
import io.github.redstoneparadox.tinkersarsenal.tools.utility.ToolShearsItem;
import java.util.ArrayList;
import java.util.List;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.RegisterEvent;
import slimeknights.tconstruct.library.materials.stats.MaterialStatsId;
import slimeknights.tconstruct.library.tools.part.IToolPart;
import slimeknights.tconstruct.library.tools.part.ToolPartItem;
import slimeknights.tconstruct.tools.stats.HandleMaterialStats;
import slimeknights.tconstruct.tools.stats.HeadMaterialStats;
import slimeknights.tconstruct.tools.stats.LimbMaterialStats;

/**
 * Created by RedstoneParadox on 7/31/2018.
 */

public class ArsenalTools {
    private static final List<Item> TOOLS = new ArrayList<>();
    private static final List<IToolPart> TOOL_PARTS = new ArrayList<>();

    public static final Item boomstick = new ToolBoomstickItem(new Item.Properties().stacksTo(1), TAToolDefinitions.BOOMSTICK);//needs forge
    public static Item boomstickShot= new AmmoBoomstickShot(new Item.Properties());//needs forge
    public static Item shears = new ToolShearsItem(new Item.Properties());

    //   (PartMaterialType.handle(ArsenalTools.boomstickHandle),
    //          PartMaterialType.bow(ArsenalTools.boomstickBarrel),
    //          PartMaterialType.head(ArsenalTools.bayonet));

    public static final Item boomstickBarrel = registerToolPart(LimbMaterialStats.ID);//cost 3
    public static final Item boomstickHandle = registerToolPart(HandleMaterialStats.ID);//cost 2
    public static final Item bayonet = registerToolPart(HeadMaterialStats.ID); //cost 2
    public static final Item bullet = registerToolPart(HandleMaterialStats.ID);
    public static final Item bulletShell = registerToolPart(HandleMaterialStats.ID);




    public static void initToolParts(RegisterEvent event) {
       event.register(Registries.ITEM,TinkersArsenal.id("boomstick_handle"),() -> boomstickHandle);
        event.register(Registries.ITEM,TinkersArsenal.id("bayonet"), () -> bayonet);
        event.register(Registries.ITEM,TinkersArsenal.id("bullet"),() -> bullet );
        event.register(Registries.ITEM,TinkersArsenal.id("bullet_shell"), () -> bulletShell);

        event.register(Registries.ITEM,TinkersArsenal.id("boomstick"),() -> boomstick);
        event.register(Registries.ITEM,TinkersArsenal.id("boomstick_shot"),() -> boomstickShot);
        event.register(Registries.ITEM,TinkersArsenal.id("shears"),() -> shears);
    }

    protected static ToolPartItem registerToolPart(MaterialStatsId materialStatsId) {
        ToolPartItem part = new ToolPartItem(new Item.Properties(), materialStatsId);//Material.VALUE_Ingot * cost);
        //part.setRegistryName(name).setTranslationKey(name);
        //registry.register(part);
        //TinkerRegistry.registerToolPart(part);
        //TinkersArsenal.proxy.registerToolPartModel(part);
        TOOL_PARTS.add(part);

        return part;
    }

    /*protected static void registerTool(ToolCore toolCore, boolean forge, IForgeRegistry<Item> registry) {
        registry.register(toolCore);
        if (forge) {
            TinkerRegistry.registerToolForgeCrafting(toolCore);
        }
        else {
            TinkerRegistry.registerToolCrafting(toolCore);
        }
        TinkersArsenal.proxy.registerToolModel(toolCore);
        TOOLS.add(toolCore);
    }*/

    public static void initToolGUIs() {
        /*ToolBuildGuiInfo boomstickInfo = new ToolBuildGuiInfo(boomstick);
        boomstickInfo.addSlotPosition(32 + 12, 41 + 12);
        boomstickInfo.addSlotPosition(32 - 12, 41 - 12);
        boomstickInfo.addSlotPosition(32 - 12, 41 + 12);
        TinkerRegistryClient.addToolBuilding(boomstickInfo);

        ToolBuildGuiInfo boomstickShotInfo = new ToolBuildGuiInfo(boomstickShot);
        boomstickShotInfo.addSlotPosition(32 + 20, 41 - 8);
        boomstickShotInfo.addSlotPosition(32, 41 + 12);
        TinkerRegistryClient.addToolBuilding(boomstickShotInfo);

        ToolBuildGuiInfo shearInfo = new ToolBuildGuiInfo(shears);
        shearInfo.addSlotPosition(32 - 12, 41 + 12); // bot left
        shearInfo.addSlotPosition(32 + 12, 41 - 12); // top left
        TinkerRegistryClient.addToolBuilding(shearInfo);*/
    }

   /* protected static void registerToolGUI(ToolCore toolCore, ArrayList<Vector2> vector2s) {
        ToolBuildGuiInfo info = new ToolBuildGuiInfo(toolCore);

        for (Vector2 vector2 : vector2s) {
            int xPos = vector2.getXLength();
            int yPos = vector2.getYLength();

            info.addSlotPosition(xPos, yPos);
        }
        TinkerRegistryClient.addToolBuilding(info);
    }

    protected static void registerToolBuilding() {
        for (final IToolPart part: TOOL_PARTS) {
            for (final ToolCore tool: TOOLS) {
                for (final PartMaterialType pmt: tool.getRequiredComponents()) {
                    if (pmt.getPossibleParts().contains(part)) {
                        TinkerRegistry.registerStencilTableCrafting(Pattern.setTagForPart(new ItemStack(TinkerTools.pattern), (Item)part));
                    }
                }
            }
        }
    }*/
}
