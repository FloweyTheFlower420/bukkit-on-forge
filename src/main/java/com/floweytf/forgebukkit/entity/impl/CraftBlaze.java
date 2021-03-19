package com.floweytf.forgebukkit.entity.impl;

import com.floweytf.forgebukkit.ForgeBukkitServer;
import net.minecraft.entity.monster.BlazeEntity;
import org.bukkit.entity.Blaze;
import org.bukkit.entity.EntityType;

public class CraftBlaze extends ForgeBukkitMonster implements Blaze {
    public CraftBlaze(ForgeBukkitServer server, BlazeEntity entity) {
        super(server, entity);
    }

    @Override
    public BlazeEntity getHandle() {
        return (BlazeEntity) entity;
    }

    @Override
    public String toString() {
        return "ForgeBukkitBlaze";
    }

    @Override
    public EntityType getType() {
        return EntityType.BLAZE;
    }
}
