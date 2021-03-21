package com.floweytf.forgebukkit.entity.base;

import com.floweytf.forgebukkit.ForgeBukkitServer;
import com.floweytf.forgebukkit.entity.ForgeBukkitCreature;
import net.minecraft.entity.passive.WaterMobEntity;
import org.bukkit.entity.WaterMob;

public class ForgeBukkitWaterMob extends ForgeBukkitCreature implements WaterMob {

    public ForgeBukkitWaterMob(ForgeBukkitServer server, WaterMobEntity entity) {
        super(server, entity);
    }

    @Override
    public WaterMobEntity getHandle() {
        return (WaterMobEntity) super.getHandle();
    }

    @Override
    public String toString() {
        return "ForgeBukkitWaterMob";
    }
}
