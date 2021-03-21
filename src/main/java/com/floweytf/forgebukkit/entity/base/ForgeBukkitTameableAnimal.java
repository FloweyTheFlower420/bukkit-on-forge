package com.floweytf.forgebukkit.entity.base;

import com.floweytf.forgebukkit.ForgeBukkitServer;
import com.floweytf.forgebukkit.entity.ForgeBukkitAnimals;
import com.floweytf.forgebukkit.entity.ForgeBukkitEntity;
import com.floweytf.forgebukkit.entity.ForgeBukkitMob;
import net.minecraft.entity.passive.TameableEntity;
import org.bukkit.entity.AnimalTamer;
import org.bukkit.entity.Creature;
import org.bukkit.entity.Tameable;

import java.util.UUID;

public class ForgeBukkitTameableAnimal extends ForgeBukkitAnimals implements Tameable, Creature {
    public ForgeBukkitTameableAnimal(ForgeBukkitServer server, TameableEntity entity) {
        super(server, entity);
    }

    @Override
    public TameableEntity getHandle() {
        return (TameableEntity) super.getHandle();
    }

    public UUID getOwnerUUID() {
        try {
            return getHandle().getOwnerId();
        }
        catch (IllegalArgumentException e) {
            return null;
        }
    }

    public void setOwnerUUID(UUID uuid) {
        getHandle().setOwnerId(uuid);
    }

    @Override
    public AnimalTamer getOwner() {
        if (getOwnerUUID() == null)
            return null;

        AnimalTamer owner = getServer().getPlayer(getOwnerUUID());
        if (owner == null)
            owner = getServer().getOfflinePlayer(getOwnerUUID());

        return owner;
    }

    @Override
    public boolean isTamed() {
        return getHandle().isTamed();
    }

    @Override
    public void setOwner(AnimalTamer tamer) {
        if (tamer != null) {
            setTamed(true);
            ForgeBukkitMob.setTargetGoal(getHandle(), null, null, false);
            setOwnerUUID(tamer.getUniqueId());
        } else {
            setTamed(false);
            setOwnerUUID(null);
        }
    }

    @Override
    public void setTamed(boolean tame) {
        getHandle().setTamed(tame);
        if (!tame) {
            setOwnerUUID(null);
        }
    }

    public boolean isSitting() {
        return getHandle().isSitting();
    }

    public void setSitting(boolean sitting) {
        getHandle().func_233687_w_(sitting); // FunC_1201723_W-)!!!!11!11!
        getHandle().setTamed(sitting); // AAAAAAAAAAAAAA
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{owner=" + getOwner() + ",tamed=" + isTamed() + "}";
    }
}
