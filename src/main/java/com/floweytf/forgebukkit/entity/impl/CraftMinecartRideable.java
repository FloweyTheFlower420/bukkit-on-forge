package com.floweytf.forgebukkit.entity.impl;

import net.minecraft.server.EntityMinecartAbstract;
import org.bukkit.craftbukkit.CraftServer;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.minecart.RideableMinecart;

public class CraftMinecartRideable extends org.bukkit.craftbukkit.entity.CraftMinecart implements RideableMinecart {
    public CraftMinecartRideable(CraftServer server, EntityMinecartAbstract entity) {
        super(server, entity);
    }

    @Override
    public String toString() {
        return "CraftMinecartRideable";
    }

    @Override
    public EntityType getType() {
        return EntityType.MINECART;
    }
}
