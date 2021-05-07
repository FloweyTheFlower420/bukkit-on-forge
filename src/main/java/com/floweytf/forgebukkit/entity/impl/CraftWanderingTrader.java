package com.floweytf.forgebukkit.entity.impl;

import net.minecraft.server.EntityVillagerTrader;
import org.bukkit.craftbukkit.CraftServer;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.WanderingTrader;

public class CraftWanderingTrader extends CraftAbstractVillager implements WanderingTrader {

    public CraftWanderingTrader(CraftServer server, EntityVillagerTrader entity) {
        super(server, entity);
    }

    @Override
    public EntityVillagerTrader getHandle() {
        return (EntityVillagerTrader) entity;
    }

    @Override
    public String toString() {
        return "CraftWanderingTrader";
    }

    @Override
    public EntityType getType() {
        return EntityType.WANDERING_TRADER;
    }

    @Override
    public int getDespawnDelay() {
        return getHandle().eX();
    }

    @Override
    public void setDespawnDelay(int despawnDelay) {
        getHandle().u(despawnDelay);
    }
}
