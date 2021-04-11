package com.floweytf.forgebukkit.entity.impl;

import com.floweytf.forgebukkit.entity.base.CraftHanging;
import net.minecraft.server.EntityLeash;
import org.bukkit.craftbukkit.CraftServer;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LeashHitch;

public class CraftLeash extends CraftHanging implements LeashHitch {
    public CraftLeash(CraftServer server, EntityLeash entity) {
        super(server, entity);
    }

    @Override
    public EntityLeash getHandle() {
        return (EntityLeash) entity;
    }

    @Override
    public String toString() {
        return "CraftLeash";
    }

    @Override
    public EntityType getType() {
        return EntityType.LEASH_HITCH;
    }
}
