package io.github.redstoneparadox.tinkersarsenal.datagen.data;

import io.github.redstoneparadox.tinkersarsenal.TinkersArsenal;
import io.github.redstoneparadox.tinkersarsenal.tools.ArsenalTools;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;
import slimeknights.tconstruct.common.TinkerTags;

import java.util.concurrent.CompletableFuture;

public class TAItemTagsProvider extends ItemTagsProvider {
    public TAItemTagsProvider(PackOutput pOutput, CompletableFuture<HolderLookup.Provider> pLookupProvider, CompletableFuture<TagLookup<Block>> pBlockTags, @Nullable ExistingFileHelper existingFileHelper) {
        super(pOutput, pLookupProvider, pBlockTags, TinkersArsenal.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {

        tag(Tags.Items.SHEARS).add(ArsenalTools.shears);

        tag(TinkerTags.Items.BONUS_SLOTS).add(ArsenalTools.boomstick,ArsenalTools.boomstickShot,ArsenalTools.shears);

        tag(TinkerTags.Items.DURABILITY).add(ArsenalTools.boomstick,ArsenalTools.shears);
        tag(TinkerTags.Items.HARVEST_PRIMARY).add(ArsenalTools.shears);
        tag(TinkerTags.Items.INTERACTABLE_RIGHT).add(ArsenalTools.shears);


        tag(TinkerTags.Items.MULTIPART_TOOL).add(ArsenalTools.shears,ArsenalTools.boomstick,ArsenalTools.boomstickShot);
        tag(TinkerTags.Items.RANGED).add(ArsenalTools.boomstick);
        tag(TinkerTags.Items.SMALL_TOOLS).add(ArsenalTools.shears);
        tag(TinkerTags.Items.TOOL_PARTS).add(ArsenalTools.bayonet,ArsenalTools.boomstickBarrel,ArsenalTools.boomstick_stock,
                ArsenalTools.bullet_head,ArsenalTools.bulletShell);
    }
}
