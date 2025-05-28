package io.github.redstoneparadox.tinkersarsenal.datagen.data;

import io.github.redstoneparadox.tinkersarsenal.TinkersArsenal;
import io.github.redstoneparadox.tinkersarsenal.init.ArsenalItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.world.level.block.Block;
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

      //  tag(Tags.Items.SHEARS).add(ArsenalItems.shears);

        tag(TinkerTags.Items.BONUS_SLOTS).add(ArsenalItems.boomstick, ArsenalItems.boomstick_shot);

        tag(TinkerTags.Items.DURABILITY).add(ArsenalItems.boomstick,ArsenalItems.boomstick_shot);
        //tag(TinkerTags.Items.HARVEST_PRIMARY).add(Arse);
     //   tag(TinkerTags.Items.INTERACTABLE_RIGHT).add(ArsenalItems.shears);

        tag(TinkerTags.Items.MULTIPART_TOOL).add(ArsenalItems.boomstick, ArsenalItems.boomstick_shot);
        tag(TinkerTags.Items.RANGED).add(ArsenalItems.boomstick);
      //  tag(TinkerTags.Items.SMALL_TOOLS).add(ArsenalItems.shears);
        tag(TinkerTags.Items.TOOL_PARTS).add( ArsenalItems.boomstickBarrel, ArsenalItems.boomstick_stock,
                ArsenalItems.bullet_head, ArsenalItems.bullet_shell);
    }
}
