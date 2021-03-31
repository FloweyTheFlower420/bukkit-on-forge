package com.floweytf.forgebukkit.entity.impl;

import com.floweytf.forgebukkit.ForgeBukkitServer;
import com.floweytf.forgebukkit.entity.ForgeBukkitMonster;
import net.minecraft.entity.monster.SpiderEntity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Spider;

public class ForgeBukkitSpider extends ForgeBukkitMonster implements Spider {

    public ForgeBukkitSpider(ForgeBukkitServer server, SpiderEntity entity) {
        super(server, entity);
    }

    @Override
    public SpiderEntity getHandle() {
        return (SpiderEntity) super.getHandle();
    }

    @Override
    public String toString() {
        return "ForgeBukkitSpider";
    }

    @Override
    public EntityType getType() {
        return EntityType.SPIDER;
    }
}
