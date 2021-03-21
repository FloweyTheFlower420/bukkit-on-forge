package com.floweytf.forgebukkit.entity.base;

import com.floweytf.forgebukkit.ForgeBukkitServer;
import com.floweytf.forgebukkit.entity.ForgeBukkitMob;
import net.minecraft.entity.passive.AmbientEntity;
import org.bukkit.entity.Ambient;
import org.bukkit.entity.EntityType;

public class ForgeBukkitAmbient extends ForgeBukkitMob implements Ambient {
    public ForgeBukkitAmbient(ForgeBukkitServer server, AmbientEntity entity) {
        super(server, entity);
    }

    @Override
    public AmbientEntity getHandle() {
        return (AmbientEntity) super.getHandle();
    }

    @Override
    public String toString() {
        return "CraftAmbient";
    }

    @Override
    public EntityType getType() {
        return EntityType.UNKNOWN;
    }
}
