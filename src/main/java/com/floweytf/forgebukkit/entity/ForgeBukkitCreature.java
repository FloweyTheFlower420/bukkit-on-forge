package com.floweytf.forgebukkit.entity;

import com.floweytf.forgebukkit.ForgeBukkitServer;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.server.EntityCreature;
import org.bukkit.craftbukkit.CraftServer;
import org.bukkit.entity.Creature;

public class ForgeBukkitCreature extends ForgeBukkitMob implements Creature {
    public ForgeBukkitCreature(ForgeBukkitServer server, CreatureEntity entity) {
        super(server, entity);
    }

    @Override
    public CreatureEntity getHandle() {
        return (CreatureEntity) super.getHandle();
    }

    @Override
    public String toString() {
        return "ForgeBukkitCreature";
    }
}
