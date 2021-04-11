package com.floweytf.forgebukkit.entity.impl;

import com.floweytf.forgebukkit.ForgeBukkitServer;
import com.floweytf.forgebukkit.entity.ForgeBukkitEntity;
import net.minecraft.entity.item.EnderCrystalEntity;
import net.minecraft.util.math.BlockPos;
import org.bukkit.Location;
import org.bukkit.entity.EnderCrystal;
import org.bukkit.entity.EntityType;

public class ForgeBukkitEnderCrystal extends ForgeBukkitEntity implements EnderCrystal {
    public ForgeBukkitEnderCrystal(ForgeBukkitServer server, EnderCrystalEntity entity) {
        super(server, entity);
    }

    @Override
    public boolean isShowingBottom() {
        return getHandle().shouldShowBottom();
    }

    @Override
    public void setShowingBottom(boolean showing) {
        getHandle().setShowBottom(showing);
    }

    @Override
    public Location getBeamTarget() {
        BlockPos pos = getHandle().getBeamTarget();
        return pos == null ? null : new Location(getWorld(), pos.getX(), pos.getY(), pos.getZ());
    }

    @Override
    public void setBeamTarget(Location location) {
        if (location == null)
            getHandle().setBeamTarget(null);
        else if (location.getWorld() != getWorld())
            throw new IllegalArgumentException("Cannot set beam target location to different world");
        else
            getHandle().setBeamTarget(new BlockPos(location.getBlockX(), location.getBlockY(), location.getBlockZ()));
    }

    @Override
    public EnderCrystalEntity getHandle() {
        return (EnderCrystalEntity) super.getHandle();
    }

    @Override
    public String toString() {
        return "ForgeBukkitEnderCrystal";
    }

    @Override
    public EntityType getType() {
        return EntityType.ENDER_CRYSTAL;
    }
}
