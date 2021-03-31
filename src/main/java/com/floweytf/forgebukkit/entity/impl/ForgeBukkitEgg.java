package com.floweytf.forgebukkit.entity.impl;

import com.floweytf.forgebukkit.ForgeBukkitServer;
import com.floweytf.forgebukkit.entity.base.ForgeBukkitThrowableProjectile;
import net.minecraft.entity.projectile.EggEntity;
import org.bukkit.entity.Egg;
import org.bukkit.entity.EntityType;

public class ForgeBukkitEgg extends ForgeBukkitThrowableProjectile implements Egg {
    public ForgeBukkitEgg(ForgeBukkitServer server, EggEntity entity) {
        super(server, entity);
    }

    @Override
    public EggEntity getHandle() {
        return (EggEntity) super.getHandle();
    }

    @Override
    public String toString() {
        return "ForgeBukkitEgg";
    }

    @Override
    public EntityType getType() {
        return EntityType.EGG;
    }
}
