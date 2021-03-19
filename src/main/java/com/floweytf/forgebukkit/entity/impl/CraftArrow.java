package com.floweytf.forgebukkit.entity.impl;

import com.floweytf.forgebukkit.ForgeBukkitServer;
import com.floweytf.forgebukkit.entity.AbstractProjectile;
import com.floweytf.forgebukkit.entity.ForgeBukkitEntity;
import com.google.common.base.Preconditions;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.server.BlockPosition;
import net.minecraft.server.EntityArrow;
import net.minecraft.util.math.BlockPos;
import org.apache.commons.lang.Validate;
import org.bukkit.block.Block;
import org.bukkit.craftbukkit.CraftServer;
import org.bukkit.entity.AbstractArrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.projectiles.ProjectileSource;
import org.jetbrains.annotations.NotNull;

public class CraftArrow extends AbstractProjectile implements AbstractArrow {

    public CraftArrow(ForgeBukkitServer server, ArrowEntity entity) {
        super(server, entity);
    }

    @Override
    public void setKnockbackStrength(int knockbackStrength) {
        Validate.isTrue(knockbackStrength >= 0, "Knockback cannot be negative");
        getHandle().setKnockbackStrength(knockbackStrength);
    }

    @Override
    public int getKnockbackStrength() {
        return getHandle().knockbackStrength;
    }

    @Override
    public double getDamage() {
        return getHandle().getDamage();
    }

    @Override
    public void setDamage(double damage) {
        Preconditions.checkArgument(damage >= 0, "Damage must be positive");
        getHandle().setDamage(damage);
    }

    @Override
    public int getPierceLevel() {
        return getHandle().getPierceLevel();
    }

    @Override
    public void setPierceLevel(int pierceLevel) {
        Preconditions.checkArgument(0 <= pierceLevel && pierceLevel <= Byte.MAX_VALUE, "Pierce level out of range, expected 0 < level < 127");

        getHandle().setPierceLevel((byte) pierceLevel);
    }

    @Override
    public boolean isCritical() {
        return getHandle().getIsCritical();
    }

    @Override
    public void setCritical(boolean critical) {
        getHandle().setIsCritical(critical);
    }

    @Override
    public ProjectileSource getShooter() {
        return getHandle().func_234616_v_();
    }

    @Override
    public void setShooter(ProjectileSource shooter) {
        if (shooter instanceof Entity)
            getHandle().setShooter(((ForgeBukkitEntity) shooter).getHandle());
        else
            getHandle().setShooter(null);
    }

    @Override
    public boolean isInBlock() {
        return getHandle().isOnGround();
    }

    @Override
    public Block getAttachedBlock() {
        if (!isInBlock())
            return null;

        BlockPos pos = getHandle().getPosition();
        return getWorld().getBlockAt(pos.getX(), pos.getY(), pos.getZ());
    }

    @Override
    public PickupStatus getPickupStatus() {
        return PickupStatus.values()[getHandle().pickupStatus.ordinal()];
    }

    @Override
    public void setPickupStatus(PickupStatus status) {
        Preconditions.checkNotNull(status);
        getHandle().pickupStatus = ArrowEntity.PickupStatus.getByOrdinal(status.ordinal());
    }

    @Override
    public void setTicksLived(int value) {
        super.setTicksLived(value);

        // Second field for EntityArrow
        getHandle().despawnCounter = value;
    }

    @Override
    public boolean isShotFromCrossbow() {
        return getHandle().getShotFromCrossbow();
    }

    @Override
    public void setShotFromCrossbow(boolean shotFromCrossbow) {
        getHandle().setShotFromCrossbow(shotFromCrossbow);
    }

    @Override
    public ArrowEntity getHandle() {
        return (ArrowEntity) super.getHandle();
    }

    @Override
    public String toString() {
        return "ForgeBukkitArrow";
    }

    @Override
    public EntityType getType() {
        return EntityType.UNKNOWN;
    }

    @NotNull
    @Override
    public Spigot spigot() {
        return null;
    }
}
