package io.github.redstoneparadox.tinkersarsenal.traits.tooltraits;

import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.utils.TagUtil;

/**
 * Yes, this is {@link TraitWritable} with a few minor changes
 */
public class TraitMalleable extends Modifier {
    //"malleable", String.valueOf(levels), 0xdc7613, 2, 1


    //todo figure out modifiers
   /* @Override
    public void applyModifierEffect(NBTTagCompound rootCompound) {
        // yaaay, modifiers
        NBTTagCompound toolTag = TagUtil.getToolTag(rootCompound);
        int modifiers = toolTag.getInteger(Tags.FREE_MODIFIERS) + levels;
        toolTag.setInteger(Tags.FREE_MODIFIERS, Math.max(0, modifiers));
        TagUtil.setToolTag(rootCompound, toolTag);
    }*/
}
