package com.floweytf.forgebukkit.entity.impl;

import com.floweytf.forgebukkit.ForgeBukkitServer;
import net.minecraft.entity.item.BoatEntity;
import org.bukkit.TreeSpecies;
import org.bukkit.entity.Boat;
import org.bukkit.entity.EntityType;

public class CraftBoat extends CraftVehicle implements Boat {
    public double maxSpeed = 0.4D;
    public double occupiedDeceleration = 0.2D;
    public double unoccupiedDeceleration = -1;
    public boolean landBoats = false;

    public CraftBoat(ForgeBukkitServer server, BoatEntity entity) {
        super(server, entity);
    }

    @Override
    public TreeSpecies getWoodType() {
        return getTreeSpecies(getHandle().getBoatType());
    }

    @Override
    public void setWoodType(TreeSpecies species) {
        getHandle().setBoatType(getBoatType(species));
    }

    // these functions are obsolete
    @Override
    public double getMaxSpeed() {
        return maxSpeed;
    }

    @Override
    public void setMaxSpeed(double speed) {
        maxSpeed = speed;
    }

    @Override
    public double getOccupiedDeceleration() {
        return occupiedDeceleration;
    }

    @Override
    public void setOccupiedDeceleration(double speed) {
        occupiedDeceleration = speed;
    }

    @Override
    public double getUnoccupiedDeceleration() {
        return unoccupiedDeceleration;
    }

    @Override
    public void setUnoccupiedDeceleration(double speed) {
        unoccupiedDeceleration = speed;
    }

    @Override
    public boolean getWorkOnLand() {
        return landBoats;
    }

    @Override
    public void setWorkOnLand(boolean workOnLand) {
        landBoats = workOnLand;
    }

    @Override
    public BoatEntity getHandle() {
        return (BoatEntity) super.getHandle();
    }

    @Override
    public String toString() {
        return "CraftBoat";
    }

    @Override
    public EntityType getType() {
        return EntityType.BOAT;
    }

    public static TreeSpecies getTreeSpecies(BoatEntity.Type boatType) {
        switch (boatType) {
            case SPRUCE:
                return TreeSpecies.REDWOOD;
            case BIRCH:
                return TreeSpecies.BIRCH;
            case JUNGLE:
                return TreeSpecies.JUNGLE;
            case ACACIA:
                return TreeSpecies.ACACIA;
            case DARK_OAK:
                return TreeSpecies.DARK_OAK;
            case OAK:
            default:
                return TreeSpecies.GENERIC;
        }
    }

    public static BoatEntity.Type getBoatType(TreeSpecies species) {
        switch (species) {
            case REDWOOD:
                return BoatEntity.Type.SPRUCE;
            case BIRCH:
                return BoatEntity.Type.BIRCH;
            case JUNGLE:
                return BoatEntity.Type.JUNGLE;
            case ACACIA:
                return BoatEntity.Type.ACACIA;
            case DARK_OAK:
                return BoatEntity.Type.DARK_OAK;
            case GENERIC:
            default:
                return BoatEntity.Type.OAK;
        }
    }
}
