package com.floweytf.forgebukkit.entity.impl;

import com.floweytf.forgebukkit.ForgeBukkitServer;
import com.floweytf.forgebukkit.entity.ForgeBukkitMonster;
import net.minecraft.entity.monster.EndermiteEntity;
import org.bukkit.entity.Endermite;
import org.bukkit.entity.EntityType;

public class ForgeBukkitEndermite extends ForgeBukkitMonster implements Endermite {

    public ForgeBukkitEndermite(ForgeBukkitServer server, EndermiteEntity entity) {
        super(server, entity);
    }

    @Override
    public EndermiteEntity getHandle() {
        return (EndermiteEntity) super.getHandle();
    }

    @Override
    public String toString() {
        return "ForgeBukkitEndermite";
    }

    @Override
    public EntityType getType() {
        return EntityType.ENDERMITE;
    }

    @Override
    public boolean isPlayerSpawned() {
        return getHandle().isSpawnedByPlayer();
    }

    @Override
    public void setPlayerSpawned(boolean playerSpawned) {
        getHandle().setSpawnedByPlayer(playerSpawned);
    }
}
