package io.github.redstoneparadox.tinkersarsenal.datagen.assets;

import com.google.gson.JsonObject;
import io.github.redstoneparadox.tinkersarsenal.TinkersArsenal;
import io.github.redstoneparadox.tinkersarsenal.init.ArsenalItems;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.data.ExistingFileHelper;
import slimeknights.tconstruct.library.data.AbstractToolItemModelProvider;

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

        // blocking //

        pulling(ArsenalItems.boomstick, toolBlocking, AmmoType.NONE, "barrel", 2, "barrel");
    }

    /** Creates models for blocking and broken for the given tool */
    protected void tool(Item tool, JsonObject properties, String... brokenParts) throws IOException {
        ResourceLocation id = BuiltInRegistries.ITEM.getKey(tool);
        String name = id.getPath();
        withDisplay("tool/" + name + "/blocking", id, properties);
        transformTool("tool/" + name + "/broken", readJson(id), "", false, "broken", brokenParts);
    }

    /** Creates a model in the blocking folder with the given copied display */
    protected void pulling(Item bow, JsonObject properties, AmmoType ammo, String brokenPart, int pullingCount, String... pullingParts) throws IOException {
        ResourceLocation id = BuiltInRegistries.ITEM.getKey(bow);
        String name = id.getPath();
        JsonObject base = readJson(id);
        base.remove("overrides"); // don't need them anywhere, notably ditching for the sake of ammo models
        transformTool("tool/" + name + "/broken", base, "", false, "broken", brokenPart);
        withDisplay("tool/" + name + "/blocking", id, properties);
        switch(ammo) {
            case CROSSBOW -> {
                // crossbows have two ammo states
                String arrowName = "tool/" + name + "/arrow";
                String fireworkName = "tool/" + name + "/firework";
                JsonObject ammoBase = suffixTextures(base.deepCopy(), "3", pullingParts);
                models.put(arrowName, addPart(ammoBase.deepCopy(), "ammo", name, "arrow"));
                models.put(fireworkName, addPart(ammoBase.deepCopy(), "ammo", name, "firework"));
                withDisplay("tool/" + name + "/arrow_blocking", resource(arrowName), properties);
                withDisplay("tool/" + name + "/firework_blocking", resource(fireworkName), properties);
            }
            case BOW -> {
                // bows have an arrow part that pulls back
                addPart(base, "arrow", name, "arrow");
            }
        }
        for (int i = 1; i <= pullingCount; i++) {
            String pulling = "tool/" + name + "/pulling_" + i;
            transformTool(pulling, base, "", false, Integer.toString(i), pullingParts);
            withDisplay("tool/" + name + "/blocking_" + i, resource(pulling), properties);
        }
    }

    @Override
    public String getName() {
        return "Tinkers' Arsenal Tool Definition Data Generator";
    }
}
