package com.floweytf.forgebukkit.entity.base;

import com.floweytf.forgebukkit.ForgeBukkitServer;
import net.minecraft.entity.passive.fish.AbstractFishEntity;
import org.bukkit.entity.Fish;

public class ForgeBukkitFish extends ForgeBukkitWaterMob implements Fish {

    public ForgeBukkitFish(ForgeBukkitServer server, AbstractFishEntity entity) {
        super(server, entity);
    }

    @Override
    public AbstractFishEntity getHandle() {
        return (AbstractFishEntity) super.getHandle();
    }

    @Override
    public String toString() {
        return "ForgeBukkitFish";
    }
}
