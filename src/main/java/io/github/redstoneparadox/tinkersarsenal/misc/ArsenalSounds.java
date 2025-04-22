package io.github.redstoneparadox.tinkersarsenal.misc;

import io.github.redstoneparadox.tinkersarsenal.TinkersArsenal;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.Entity;

/**
 * Created by RedstoneParadox on 8/3/2018.
 */
public class ArsenalSounds {
    public static final SoundEvent BOOMSTICK_SHOT = sound("boomstick_shot");

    private static SoundEvent sound(String name) {
        return SoundEvent.createVariableRangeEvent(TinkersArsenal.id(name));
    }

    public static void playSoundForAll(Entity entity, SoundEvent sound, float volume, float pitch) {
        //entity.getEntityWorld().playSound(null, entity.getPosition(), sound, entity.getSoundCategory(), volume, pitch);
    }

    public static void playSoundForPlayer(Entity entity, SoundEvent sound, float volume, float pitch) {
        if(entity instanceof ServerPlayer) {
            //TinkerNetwork.sendPacket(entity, new SPacketSoundEffect(sound, entity.getSoundCategory(), entity.posX, entity.posY, entity.posZ, volume, pitch));
        }
    }
}
