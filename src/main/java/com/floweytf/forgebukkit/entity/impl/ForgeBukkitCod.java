package com.floweytf.forgebukkit.entity.impl;

import com.floweytf.forgebukkit.ForgeBukkitServer;
import com.floweytf.forgebukkit.entity.base.ForgeBukkitFish;
import net.minecraft.entity.passive.fish.CodEntity;
import org.bukkit.entity.Cod;
import org.bukkit.entity.EntityType;

public class ForgeBukkitCod extends ForgeBukkitFish implements Cod {

    public ForgeBukkitCod(ForgeBukkitServer server, CodEntity entity) {
        super(server, entity);
    }

    @Override
    public CodEntity getHandle() {
        return (CodEntity) super.getHandle();
    }

    @Override
    public String toString() {
        return "ForgeBukkitCod";
    }

    @Override
    public EntityType getType() {
        return EntityType.COD;
    }
}
