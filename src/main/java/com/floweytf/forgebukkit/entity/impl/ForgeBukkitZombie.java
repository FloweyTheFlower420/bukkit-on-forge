package com.floweytf.forgebukkit.entity.impl;

import com.floweytf.forgebukkit.ForgeBukkitServer;
import com.floweytf.forgebukkit.entity.ForgeBukkitMonster;
import com.google.common.base.Preconditions;
import net.minecraft.entity.monster.ZombieEntity;
import net.minecraft.entity.monster.ZombieVillagerEntity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Villager;
import org.bukkit.entity.Zombie;

public class ForgeBukkitZombie extends ForgeBukkitMonster implements Zombie {

    public ForgeBukkitZombie(ForgeBukkitServer server, ZombieEntity entity) {
        super(server, entity);
    }

    @Override
    public ZombieEntity getHandle() {
        return (ZombieEntity) super.getHandle();
    }

    @Override
    public String toString() {
        return "ForgeBukkitZombie";
    }

    @Override
    public EntityType getType() {
        return EntityType.ZOMBIE;
    }

    @Override
    public boolean isBaby() {
        return getHandle().isChild();
    }

    @Override
    public void setBaby(boolean flag) {
        getHandle().setChild(flag);
    }

    @Override
    public boolean isVillager() {
        return getHandle() instanceof ZombieVillagerEntity;
    }

    @Override
    public void setVillager(boolean flag) {
        throw new UnsupportedOperationException("Not supported.");
    }

    @Override
    public void setVillagerProfession(Villager.Profession profession) {
        throw new UnsupportedOperationException("Not supported.");
    }

    @Override
    public Villager.Profession getVillagerProfession() {
        return null;
    }

    @Override
    public boolean isConverting() {
        return getHandle().isDrowning();
    }

    @Override
    public int getConversionTime() {
        Preconditions.checkState(isConverting(), "Entity not converting");
        return getHandle().drownedConversionTime;
    }

    @Override
    public void setConversionTime(int time) {
        if (time < 0) {
            getHandle().drownedConversionTime = -1;
            getHandle().getDataManager().set(ZombieEntity.DROWNING, false);
        }
        else
            getHandle().startDrowning(time);
    }

    @Override
    public int getAge() {
        return getHandle().isChild() ? -1 : 0;
    }

    @Override
    public void setAge(int i) {
        getHandle().setChild(i < 0);
    }

    @Override
    public void setAgeLock(boolean b) {
    }

    @Override
    public boolean getAgeLock() {
        return false;
    }

    @Override
    public void setBaby() {
        getHandle().setChild(true);
    }

    @Override
    public void setAdult() {
        getHandle().setChild(false);
    }

    @Override
    public boolean isAdult() {
        return !getHandle().isChild();
    }

    @Override
    public boolean canBreed() {
        return false;
    }

    @Override
    public void setBreed(boolean b) {
    }
}