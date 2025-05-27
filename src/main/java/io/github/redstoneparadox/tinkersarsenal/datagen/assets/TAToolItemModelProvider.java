package io.github.redstoneparadox.tinkersarsenal.datagen.assets;

import com.google.gson.JsonObject;
import io.github.redstoneparadox.tinkersarsenal.TinkersArsenal;
import io.github.redstoneparadox.tinkersarsenal.init.ArsenalItems;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.data.ExistingFileHelper;
import slimeknights.mantle.registration.object.IdAwareObject;
import slimeknights.tconstruct.library.data.AbstractToolItemModelProvider;
import slimeknights.tconstruct.tools.TinkerTools;

import java.io.IOException;

import static slimeknights.tconstruct.TConstruct.getResource;

public class TAToolItemModelProvider extends AbstractToolItemModelProvider {
    public TAToolItemModelProvider(PackOutput packOutput, ExistingFileHelper existingFileHelper) {
        super(packOutput, existingFileHelper, TinkersArsenal.MOD_ID);
    }

    @Override
    protected void addModels() throws IOException {
        JsonObject toolBlocking = readJson(getResource("base/tool_blocking"));
        //JsonObject shieldBlocking = readJson(getResource("base/shield_blocking"));

        //tool(ArsenalItems.shears, toolBlocking, "head");

        charged(ArsenalItems.boomstick, toolBlocking, "barrel");
    }

    /** Creates models for blocking and broken for the given tool */
    protected void tool(Item tool, JsonObject properties, String... brokenParts) throws IOException {
        ResourceLocation id = BuiltInRegistries.ITEM.getKey(tool);
        String name = id.getPath();
        withDisplay("tool/" + name + "/blocking", id, properties);
        transformTool("tool/" + name + "/broken", readJson(id), "", false, "broken", brokenParts);
    }

    /** Creates models for blocking, broken and fully charged for the given tool */
    protected void charged(Item bow, JsonObject properties, String... brokenParts) throws IOException {
        ResourceLocation id =  BuiltInRegistries.ITEM.getKey(bow);
        String name = id.getPath();
        JsonObject base = readJson(id);
        base.remove("overrides");
        withDisplay("tool/" + name + "/blocking", id, properties);
        transformTool("tool/" + name + "/broken", base, "", false, "broken", brokenParts);

        addPart(base, "overlay", name, "overlay");

        String charged = "tool/" + name + "/charged";
        transformTool(charged, base, "", false, "charged", "overlay");
        withDisplay("tool/" + name + "/blocking_charged", resource(charged), properties);
    }

    @Override
    public String getName() {
        return "Tinkers' Arsenal Tool Definition Data Generator";
    }
}
