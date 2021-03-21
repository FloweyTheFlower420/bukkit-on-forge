package com.floweytf.forgebukkit.entity.base;

import com.floweytf.forgebukkit.ForgeBukkitServer;
import com.floweytf.forgebukkit.entity.ForgeBukkitEntity;
import net.minecraft.entity.Entity;
import org.bukkit.entity.Vehicle;

public abstract class ForgeBukkitVehicle extends ForgeBukkitEntity implements Vehicle {
    public ForgeBukkitVehicle(ForgeBukkitServer server, Entity entity) {
        super(server, entity);
    }

    @Override
    public String toString() {
        return "CraftVehicle{passenger=" + getPassenger() + '}';
    }
}
