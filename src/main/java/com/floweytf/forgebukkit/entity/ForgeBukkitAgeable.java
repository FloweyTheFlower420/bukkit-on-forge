package com.floweytf.forgebukkit.entity;

import com.floweytf.forgebukkit.ForgeBukkitServer;
import net.minecraft.entity.AgeableEntity;
import org.bukkit.entity.Ageable;

public class ForgeBukkitAgeable extends ForgeBukkitCreature implements Ageable {
    boolean ageLocked = false;
    public ForgeBukkitAgeable(ForgeBukkitServer server, AgeableEntity entity) {
        super(server, entity);
    }

    @Override
    public int getAge() {
        return getHandle().getGrowingAge();
    }

    @Override
    public void setAge(int age) {
        getHandle().setGrowingAge(age);
    }

    @Override
    public void setAgeLock(boolean lock) {
        ageLocked = lock;
    }

    @Override
    public boolean getAgeLock() {
        return ageLocked;
    }

    @Override
    public void setBaby() {
        if (isAdult()) {
            setAge(-24000);
        }
    }

    @Override
    public void setAdult() {
        if (!isAdult()) {
            setAge(0);
        }
    }

    @Override
    public boolean isAdult() {
        return getAge() >= 0;
    }


    @Override
    public boolean canBreed() {
        return getAge() == 0;
    }

    @Override
    public void setBreed(boolean breed) {
        if (breed) {
            setAge(0);
        } else if (isAdult()) {
            setAge(6000);
        }
    }

    @Override
    public AgeableEntity getHandle() {
        return (AgeableEntity) super.getHandle();
    }

    @Override
    public String toString() {
        return "ForgeBukkitAgeable";
    }
}
