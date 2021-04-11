package com.floweytf.forgebukkit.entity;

import com.floweytf.forgebukkit.ForgeBukkitServer;
import org.bukkit.entity.ComplexLivingEntity;

public abstract class ForgeBukkitComplexLivingEntity extends ForgeBukkitLivingEntity implements ComplexLivingEntity {
    public ForgeBukkitComplexLivingEntity(ForgeBukkitServer server, net.minecraft.entity.LivingEntity entity) {
        super(server, entity);
    }

    @Override
    public String toString() {
        return "ForgeBukkitComplexLivingEntity";
    }
}
