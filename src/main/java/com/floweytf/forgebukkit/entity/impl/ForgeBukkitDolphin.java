package com.floweytf.forgebukkit.entity.impl;

import com.floweytf.forgebukkit.ForgeBukkitServer;
import com.floweytf.forgebukkit.entity.base.ForgeBukkitWaterMob;
import net.minecraft.entity.passive.DolphinEntity;
import org.bukkit.entity.Dolphin;
import org.bukkit.entity.EntityType;

public class ForgeBukkitDolphin extends ForgeBukkitWaterMob implements Dolphin {

    public ForgeBukkitDolphin(ForgeBukkitServer server, DolphinEntity entity) {
        super(server, entity);
    }

    @Override
    public DolphinEntity getHandle() {
        return (DolphinEntity) super.getHandle();
    }

    @Override
    public String toString() {
        return "ForgeBukkitDolphin";
    }

    @Override
    public EntityType getType() {
        return EntityType.DOLPHIN;
    }
}
