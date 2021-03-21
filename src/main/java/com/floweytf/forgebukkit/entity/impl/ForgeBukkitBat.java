package com.floweytf.forgebukkit.entity.impl;

import com.floweytf.forgebukkit.ForgeBukkitServer;
import com.floweytf.forgebukkit.entity.base.ForgeBukkitAmbient;
import net.minecraft.entity.passive.BatEntity;
import org.bukkit.entity.Bat;
import org.bukkit.entity.EntityType;

public class ForgeBukkitBat extends ForgeBukkitAmbient implements Bat {
    public ForgeBukkitBat(ForgeBukkitServer server, BatEntity entity) {
        super(server, entity);
    }

    @Override
    public BatEntity getHandle() {
        return (BatEntity) super.getHandle();
    }

    @Override
    public String toString() {
        return "ForgeBukkitBat";
    }

    @Override
    public EntityType getType() {
        return EntityType.BAT;
    }

    @Override
    public boolean isAwake() {
        return !getHandle().getIsBatHanging();
    }

    @Override
    public void setAwake(boolean state) {
        getHandle().setIsBatHanging(!state);
    }
}
