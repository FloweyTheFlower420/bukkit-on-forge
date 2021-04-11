package com.floweytf.forgebukkit.entity.impl;

import com.floweytf.forgebukkit.ForgeBukkitServer;
import net.minecraft.entity.monster.ElderGuardianEntity;
import org.bukkit.entity.ElderGuardian;
import org.bukkit.entity.EntityType;

public class ForgeBukkitElderGuardian extends ForgeBukkitGuardian implements ElderGuardian {

    public ForgeBukkitElderGuardian(ForgeBukkitServer server, ElderGuardianEntity entity) {
        super(server, entity);
    }

    @Override
    public String toString() {
        return "ForgeBukkitElderGuardian";
    }

    @Override
    public EntityType getType() {
        return EntityType.ELDER_GUARDIAN;
    }

    @Override
    public boolean isElder() {
        return true;
    }
}