package com.floweytf.forgebukkit.entity.impl;

import com.floweytf.forgebukkit.ForgeBukkitServer;
import com.floweytf.forgebukkit.entity.base.ForgeBukkitThrowableProjectile;
import net.minecraft.entity.item.EnderPearlEntity;
import org.bukkit.entity.EnderPearl;
import org.bukkit.entity.EntityType;

public class ForgeBukkitEnderPearl extends ForgeBukkitThrowableProjectile implements EnderPearl {
    public ForgeBukkitEnderPearl(ForgeBukkitServer server, EnderPearlEntity entity) {
        super(server, entity);
    }

    @Override
    public EnderPearlEntity getHandle() {
        return (EnderPearlEntity) super.getHandle();
    }

    @Override
    public String toString() {
        return "ForgeBukkitEnderPearl";
    }

    @Override
    public EntityType getType() {
        return EntityType.ENDER_PEARL;
    }
}
