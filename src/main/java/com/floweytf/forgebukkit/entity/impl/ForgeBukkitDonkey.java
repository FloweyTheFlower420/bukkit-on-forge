package com.floweytf.forgebukkit.entity.impl;

import net.minecraft.entity.passive.horse.DonkeyEntity;
import org.bukkit.entity.Donkey;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Horse.Variant;
import com.floweytf.forgebukkit.ForgeBukkitServer;

public class ForgeBukkitDonkey extends ForgeBukkitChestedHorse implements Donkey {

    public ForgeBukkitDonkey(ForgeBukkitServer server, DonkeyEntity entity) {
        super(server, entity);
    }

    @Override
    public String toString() {
        return "ForgeBukkitDonkey";
    }

    @Override
    public EntityType getType() {
        return EntityType.DONKEY;
    }

    @Override
    public Variant getVariant() {
        return Variant.DONKEY;
    }
}
