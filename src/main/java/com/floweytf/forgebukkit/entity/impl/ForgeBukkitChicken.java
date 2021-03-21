package com.floweytf.forgebukkit.entity.impl;

import com.floweytf.forgebukkit.ForgeBukkitServer;
import com.floweytf.forgebukkit.entity.ForgeBukkitAnimals;
import net.minecraft.entity.passive.ChickenEntity;
import org.bukkit.entity.Chicken;
import org.bukkit.entity.EntityType;

public class ForgeBukkitChicken extends ForgeBukkitAnimals implements Chicken {

    public ForgeBukkitChicken(ForgeBukkitServer server, ChickenEntity entity) {
        super(server, entity);
    }

    @Override
    public ChickenEntity getHandle() {
        return (ChickenEntity) super.getHandle();
    }

    @Override
    public String toString() {
        return "ForgeBukkitChicken";
    }

    @Override
    public EntityType getType() {
        return EntityType.CHICKEN;
    }
}
