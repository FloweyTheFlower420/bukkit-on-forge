package com.floweytf.forgebukkit.entity.impl;

import com.floweytf.forgebukkit.ForgeBukkitServer;
import net.minecraft.entity.monster.CaveSpiderEntity;
import org.bukkit.entity.CaveSpider;
import org.bukkit.entity.EntityType;

public class ForgeBukkitCaveSpider extends ForgeBukkitSpider implements CaveSpider {
    public ForgeBukkitCaveSpider(ForgeBukkitServer server, CaveSpiderEntity entity) {
        super(server, entity);
    }

    @Override
    public CaveSpiderEntity getHandle() {
        return (CaveSpiderEntity) super.getHandle();
    }

    @Override
    public String toString() {
        return "ForgeBukkitCaveSpider";
    }

    @Override
    public EntityType getType() {
        return EntityType.CAVE_SPIDER;
    }
}
