package io.github.redstoneparadox.tinkersarsenal.init;

import io.github.redstoneparadox.tinkersarsenal.entities.BoomstickShotEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;

/**
 * Created by RedstoneParadox on 8/3/2018.
 */
public class ArsenalEntities {

    public static final EntityType<BoomstickShotEntity> BOOMSTICK_SHOT = EntityType.Builder.<BoomstickShotEntity>of(BoomstickShotEntity::new, MobCategory.MISC)
            .updateInterval(1)
            .sized(.125f,.125f)
            .build("");
}
