package com.floweytf.forgebukkit.entity;

import com.floweytf.forgebukkit.ForgeBukkitServer;
import com.google.common.base.Preconditions;
import net.minecraft.entity.passive.AnimalEntity;
import org.bukkit.entity.Animals;

import java.util.UUID;

public class ForgeBukkitAnimals extends ForgeBukkitAgeable implements Animals {

    public ForgeBukkitAnimals(ForgeBukkitServer server, AnimalEntity entity) {
        super(server, entity);
    }

    @Override
    public AnimalEntity getHandle() {
        return (AnimalEntity) super.getHandle();
    }

    @Override
    public String toString() {
        return "CraftAnimals";
    }

    @Override
    public UUID getBreedCause() {
        return getHandle().playerInLove;
    }

    @Override
    public void setBreedCause(UUID uuid) {
        getHandle().playerInLove = uuid;
    }

    @Override
    public boolean isLoveMode() {
        return getHandle().isInLove();
    }

    @Override
    public void setLoveModeTicks(int ticks) {
        Preconditions.checkArgument(ticks >= 0, "Love mode ticks must be positive or 0");
        getHandle().setInLove(ticks);
    }

    @Override
    public int getLoveModeTicks() {
        return getHandle().inLove;
    }
}
