package com.floweytf.forgebukkit.entity.impl;

import com.floweytf.forgebukkit.ForgeBukkitServer;
import com.floweytf.forgebukkit.entity.base.ForgeBukkitAbstractHorse;
import net.minecraft.entity.passive.horse.AbstractChestedHorseEntity;
import org.bukkit.entity.ChestedHorse;

public abstract class CraftChestedHorse extends ForgeBukkitAbstractHorse implements ChestedHorse {

    public CraftChestedHorse(ForgeBukkitServer server, AbstractChestedHorseEntity entity) {
        super(server, entity);
    }

    @Override
    public AbstractChestedHorseEntity getHandle() {
        return (AbstractChestedHorseEntity) super.getHandle();
    }

    @Override
    public boolean isCarryingChest() {
        return getHandle().hasChest();
    }

    @Override
    public void setCarryingChest(boolean chest) {
        if (chest == isCarryingChest())
            return;

        getHandle().setChested(chest);
        getHandle().initHorseChest();
    }
}
