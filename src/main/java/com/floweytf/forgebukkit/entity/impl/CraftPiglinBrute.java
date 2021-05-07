package com.floweytf.forgebukkit.entity.impl;

import net.minecraft.server.EntityPiglinBrute;
import org.bukkit.craftbukkit.CraftServer;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.PiglinBrute;

public class CraftPiglinBrute extends org.bukkit.craftbukkit.entity.CraftPiglinAbstract implements PiglinBrute {

    public CraftPiglinBrute(CraftServer server, EntityPiglinBrute entity) {
        super(server, entity);
    }

    @Override
    public EntityPiglinBrute getHandle() {
        return (EntityPiglinBrute) super.getHandle();
    }

    @Override
    public EntityType getType() {
        return EntityType.PIGLIN_BRUTE;
    }

    @Override
    public String toString() {
        return "CraftPiglinBrute";
    }
}
