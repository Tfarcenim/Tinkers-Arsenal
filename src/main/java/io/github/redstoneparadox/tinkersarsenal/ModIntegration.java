package io.github.redstoneparadox.tinkersarsenal;

import net.minecraftforge.fml.ModList;

public enum ModIntegration {
    conarm,
    thermalfoundation;
    public final boolean loaded;
    ModIntegration() {
        loaded = ModList.get().isLoaded(name());
    }
}
