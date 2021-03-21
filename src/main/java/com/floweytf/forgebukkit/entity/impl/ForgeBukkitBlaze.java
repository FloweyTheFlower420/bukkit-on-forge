package com.floweytf.forgebukkit.entity.impl;

import com.floweytf.forgebukkit.ForgeBukkitServer;
import com.floweytf.forgebukkit.entity.ForgeBukkitMonster;
import net.minecraft.entity.monster.BlazeEntity;
import org.bukkit.entity.Blaze;
import org.bukkit.entity.EntityType;

public class ForgeBukkitBlaze extends ForgeBukkitMonster implements Blaze {
    public ForgeBukkitBlaze(ForgeBukkitServer server, BlazeEntity entity) {
        super(server, entity);
    }

    @Override
    public BlazeEntity getHandle() {
        return (BlazeEntity) super.getHandle();
    }

    @Override
    public String toString() {
        return "ForgeBukkitBlaze";
    }

    @Override
    public EntityType getType() {
        return EntityType.BLAZE;
    }
}
