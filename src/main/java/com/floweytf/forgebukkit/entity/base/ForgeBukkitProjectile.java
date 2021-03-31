package com.floweytf.forgebukkit.entity.base;

import com.floweytf.forgebukkit.ForgeBukkitServer;
import com.floweytf.forgebukkit.entity.ForgeBukkitLivingEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import org.bukkit.entity.Projectile;
import org.bukkit.projectiles.ProjectileSource;

public abstract class ForgeBukkitProjectile extends AbstractProjectile implements Projectile {
    public ForgeBukkitProjectile(ForgeBukkitServer server, ProjectileEntity entity) {
        super(server, entity);
    }

    @Override
    public ProjectileSource getShooter() {
        return getHandle().projectileSource;
    }

    @Override
    public void setShooter(ProjectileSource shooter) {
        if (shooter instanceof CraftLivingEntity) {
            getHandle().setShooter(((ForgeBukkitLivingEntity) shooter).getHandle());
        } else {
            getHandle().setShooter(null);
        }
        getHandle().projectileSource = shooter;
    }

    @Override
    public ProjectileEntity getHandle() {
        return (ProjectileEntity) super.getHandle();
    }

    @Override
    public String toString() {
        return "ForgeBukkitProjectile";
    }
}
