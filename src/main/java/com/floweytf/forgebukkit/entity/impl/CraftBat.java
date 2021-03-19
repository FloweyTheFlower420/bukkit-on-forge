package com.floweytf.forgebukkit.entity.impl;

import com.floweytf.forgebukkit.ForgeBukkitServer;
import net.minecraft.entity.passive.BatEntity;
import net.minecraft.server.EntityBat;
import org.bukkit.craftbukkit.CraftServer;
import org.bukkit.entity.Bat;
import org.bukkit.entity.EntityType;

public class CraftBat extends CraftAmbient implements Bat {
    public CraftBat(ForgeBukkitServer server, BatEntity entity) {
        super(server, entity);
    }

    @Override
    public BatEntity getHandle() {
        return (BatEntity) super.getHandle();
    }

    @Override
    public String toString() {
        return "CraftBat";
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
