package com.floweytf.forgebukkit.entity.base;

import com.floweytf.forgebukkit.ForgeBukkitServer;
import com.floweytf.forgebukkit.entity.ForgeBukkitEntity;
import net.minecraft.entity.Entity;
import org.bukkit.entity.Projectile;

public abstract class AbstractProjectile extends ForgeBukkitEntity implements Projectile {

    private boolean doesBounce;

    public AbstractProjectile(ForgeBukkitServer server, Entity entity) {
        super(server, entity);
        doesBounce = false;
    }

    @Override
    public boolean doesBounce() {
        return doesBounce;
    }

    @Override
    public void setBounce(boolean doesBounce) {
        this.doesBounce = doesBounce;
    }

}
