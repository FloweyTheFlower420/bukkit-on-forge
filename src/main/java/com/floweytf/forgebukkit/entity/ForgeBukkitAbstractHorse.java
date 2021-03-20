package com.floweytf.forgebukkit.entity;

import com.floweytf.forgebukkit.ForgeBukkitServer;
import com.floweytf.forgebukkit.access.AbstractHorseEntityMixinAccess;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.passive.horse.AbstractHorseEntity;
import org.apache.commons.lang.Validate;
import org.bukkit.entity.AbstractHorse;
import org.bukkit.entity.AnimalTamer;
import org.bukkit.entity.Horse;
import org.bukkit.inventory.AbstractHorseInventory;

import java.util.UUID;

public abstract class ForgeBukkitAbstractHorse extends ForgeBukkitAnimals implements AbstractHorse {

    public ForgeBukkitAbstractHorse(ForgeBukkitServer server, AbstractHorseEntity entity) {
        super(server, entity);
    }

    @Override
    public AbstractHorseEntity getHandle() {
        return (AbstractHorseEntity) super.getHandle();
    }

    @Override
    public void setVariant(Horse.Variant variant) {
        throw new UnsupportedOperationException("Not supported.");
    }

    @Override
    public int getDomestication() {
        return getHandle().getTemper();
    }

    @Override
    public void setDomestication(int value) {
        Validate.isTrue(value >= 0, "Domestication cannot be less than zero");
        Validate.isTrue(value <= getMaxDomestication(), "Domestication cannot be greater than the max domestication");
        getHandle().setTemper(value);
    }

    @Override
    public int getMaxDomestication() {
        return ((AbstractHorseEntityMixinAccess)getHandle()).getMaxDomestication();
    }

    @Override
    public void setMaxDomestication(int value) {
        Validate.isTrue(value > 0, "Max domestication cannot be zero or less");
        ((AbstractHorseEntityMixinAccess)getHandle()).setMaxDomestication(value);
    }

    @Override
    public double getJumpStrength() {
        return getHandle().getHorseJumpStrength();
    }

    @Override
    public void setJumpStrength(double strength) {
        Validate.isTrue(strength >= 0, "Jump strength cannot be less than zero");
        getHandle().getAttribute(Attributes.HORSE_JUMP_STRENGTH).setBaseValue(strength);
    }

    @Override
    public boolean isTamed() {
        return getHandle().isTame();
    }

    @Override
    public void setTamed(boolean tamed) {
        getHandle().setHorseTamed(tamed);
    }

    @Override
    public AnimalTamer getOwner() {
        if (getOwnerUUID() == null) return null;
        return getServer().getOfflinePlayer(getOwnerUUID());
    }

    @Override
    public void setOwner(AnimalTamer owner) {
        if (owner != null) {
            setTamed(true);
            ForgeBukkitMob.setTargetGoal(getHandle(), null, null, false);
            setOwnerUUID(owner.getUniqueId());
        } else {
            setTamed(false);
            setOwnerUUID(null);
        }
    }

    public UUID getOwnerUUID() {
        return getHandle().getOwnerUniqueId();
    }

    public void setOwnerUUID(UUID uuid) {
        getHandle().setOwnerUniqueId(uuid);
    }

    @Override
    public AbstractHorseInventory getInventory() {
        return new CraftInventoryAbstractHorse(getHandle().horseChest);
    }
}
