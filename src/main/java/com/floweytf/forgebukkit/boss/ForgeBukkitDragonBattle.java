package com.floweytf.forgebukkit.boss;

import com.floweytf.forgebukkit.ForgeBukkit;
import com.floweytf.forgebukkit.ForgeBukkitWorld;
import com.floweytf.forgebukkit.Wrapper;
import com.floweytf.forgebukkit.entity.ForgeBukkitEntity;
import com.floweytf.forgebukkit.mixins.DragonFightManagerMixin;
import com.google.common.base.Preconditions;
import net.minecraft.entity.Entity;
import net.minecraft.world.end.DragonFightManager;
import net.minecraft.world.end.DragonSpawnState;
import org.bukkit.Location;
import org.bukkit.boss.BossBar;
import org.bukkit.boss.DragonBattle;
import org.bukkit.entity.EnderDragon;

import javax.annotation.Nonnull;
import java.lang.reflect.InvocationTargetException;

public class ForgeBukkitDragonBattle extends Wrapper<DragonFightManager> implements DragonBattle {
    public ForgeBukkitDragonBattle(DragonFightManager handle) {
        super(handle);
    }

    @Override
    public EnderDragon getEnderDragon() {
        Entity entity = getHandle().entity.getEntityByUuid(getHandle().dragonUniqueId);
        return (entity != null) ? (EnderDragon) ForgeBukkitEntity.wrap(entity) : null;
    }

    @Override
    @Nonnull
    public BossBar getBossBar() {
        return new ForgeBukkitBossBar(getHandle().bossInfo);
    }

    @Override
    public Location getEndPortalLocation() {
        if (getHandle().exitPortalLocation == null)
            return null;

        return new Location(
            ForgeBukkitWorld.getWorldWrapper(getHandle().world),
            getHandle().exitPortalLocation.getX(),
            getHandle().exitPortalLocation.getY(),
            getHandle().exitPortalLocation.getZ()
        );
    }

    @Override
    public boolean generateEndPortal(boolean withPortals) {
        if (getHandle().exitPortalLocation != null || getHandle().portalPattern != null) {
            return false;
        }

        return getHandle().generatePortal(withPortals);

        return true;
    }

    @Override
    public boolean hasBeenPreviouslyKilled() {
        return getHandle().hasPreviouslyKilledDragon();
    }

    @Override
    public void initiateRespawn() {
        this.getHandle().tryRespawnDragon();
    }

    @Override
    @Nonnull
    public RespawnPhase getRespawnPhase() {
        return toBukkitRespawnPhase(getHandle().respawnState);
    }

    @Override
    public boolean setRespawnPhase(@Nonnull RespawnPhase phase) {
        Preconditions.checkArgument(phase != null && phase != RespawnPhase.NONE, "Invalid respawn phase provided: %s", phase);

        if (getHandle().respawnState == null)
            return false;

        getHandle().respawnState = toMcRespawnPhase(phase);
        return true;
    }

    @Override
    public void resetCrystals() {
        this.getHandle().resetSpikeCrystals();
    }

    @Override
    public int hashCode() {
        return getHandle().hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof ForgeBukkitDragonBattle && ((ForgeBukkitDragonBattle) obj).getHandle() == this.getHandle();
    }

    private RespawnPhase toBukkitRespawnPhase(DragonSpawnState phase) {
        return (phase != null) ? RespawnPhase.values()[phase.ordinal()] : RespawnPhase.NONE;
    }

    private DragonSpawnState toMcRespawnPhase(RespawnPhase phase) {
        return (phase != RespawnPhase.NONE) ? DragonSpawnState.values()[phase.ordinal()] : null;
    }
}
