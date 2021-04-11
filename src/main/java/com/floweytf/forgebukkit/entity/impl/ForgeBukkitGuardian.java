package com.floweytf.forgebukkit.entity.impl;

import com.floweytf.forgebukkit.ForgeBukkitServer;
import com.floweytf.forgebukkit.entity.ForgeBukkitMonster;
import net.minecraft.entity.monster.GuardianEntity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Guardian;

public class ForgeBukkitGuardian extends ForgeBukkitMonster implements Guardian {

    public ForgeBukkitGuardian(ForgeBukkitServer server, GuardianEntity entity) {
        super(server, entity);
    }

    @Override
    public String toString() {
        return "ForgeBukkitGuardian";
    }

    @Override
    public EntityType getType() {
        return EntityType.GUARDIAN;
    }

    @Override
    public boolean isElder() {
        return false;
    }

    @Override
    public void setElder(boolean shouldBeElder) {
        throw new UnsupportedOperationException("Not supported.");
    }
}
