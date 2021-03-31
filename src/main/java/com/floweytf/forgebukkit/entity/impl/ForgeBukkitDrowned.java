package com.floweytf.forgebukkit.entity.impl;

import com.floweytf.forgebukkit.ForgeBukkitServer;
import net.minecraft.entity.monster.DrownedEntity;
import org.bukkit.entity.Drowned;
import org.bukkit.entity.EntityType;

public class ForgeBukkitDrowned extends ForgeBukkitZombie implements Drowned {

    public ForgeBukkitDrowned(ForgeBukkitServer server, DrownedEntity entity) {
        super(server, entity);
    }

    @Override
    public DrownedEntity getHandle() {
        return (DrownedEntity) super.getHandle();
    }

    @Override
    public String toString() {
        return "ForgeBukkitDrowned";
    }

    @Override
    public EntityType getType() {
        return EntityType.DROWNED;
    }
}
