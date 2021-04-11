package com.floweytf.forgebukkit.entity.impl;

import com.floweytf.forgebukkit.ForgeBukkitServer;
import com.floweytf.forgebukkit.entity.ForgeBukkitComplexPart;
import net.minecraft.entity.boss.dragon.EnderDragonPartEntity;
import org.bukkit.entity.EnderDragon;
import org.bukkit.entity.EnderDragonPart;
import org.bukkit.entity.Entity;

public class ForgeBukkitEnderDragonPart extends ForgeBukkitComplexPart implements EnderDragonPart {
    public ForgeBukkitEnderDragonPart(ForgeBukkitServer server, EnderDragonPartEntity entity) {
        super(server, entity);
    }

    @Override
    public EnderDragon getParent() {
        return (EnderDragon) super.getParent();
    }

    @Override
    public EnderDragonPartEntity getHandle() {
        return (EnderDragonPartEntity) super.getHandle();
    }

    @Override
    public String toString() {
        return "ForgeBukkitEnderDragonPart";
    }

    @Override
    public void damage(double amount) {
        getParent().damage(amount);
    }

    @Override
    public void damage(double amount, Entity source) {
        getParent().damage(amount, source);
    }

    @Override
    public double getHealth() {
        return getParent().getHealth();
    }

    @Override
    public void setHealth(double health) {
        getParent().setHealth(health);
    }

    @Override
    public double getAbsorptionAmount() {
        return getParent().getAbsorptionAmount();
    }

    @Override
    public void setAbsorptionAmount(double amount) {
        getParent().setAbsorptionAmount(amount);
    }

    @Override
    public double getMaxHealth() {
        return getParent().getMaxHealth();
    }

    @Override
    public void setMaxHealth(double health) {
        getParent().setMaxHealth(health);
    }

    @Override
    public void resetMaxHealth() {
        getParent().resetMaxHealth();
    }
}
