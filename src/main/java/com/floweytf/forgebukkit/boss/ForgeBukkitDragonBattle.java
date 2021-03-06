package com.floweytf.forgebukkit.boss;

import com.floweytf.forgebukkit.ForgeBukkitWorld;
import com.floweytf.forgebukkit.Wrapper;
import com.floweytf.forgebukkit.mixins.DragonFightManagerMixin;
import com.google.common.base.Preconditions;
import net.minecraft.entity.Entity;
import net.minecraft.world.end.DragonFightManager;
import net.minecraft.world.end.DragonSpawnState;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import org.bukkit.Location;
import org.bukkit.boss.BossBar;
import org.bukkit.boss.DragonBattle;
import org.bukkit.entity.EnderDragon;

import java.lang.reflect.Method;

public class ForgeBukkitDragonBattle extends Wrapper<DragonFightManager> implements DragonBattle {
    public ForgeBukkitDragonBattle(DragonFightManager handle) {
        super(handle);
    }

    @Override
    public EnderDragon getEnderDragon() {
        Entity entity = getAccessor().getWorld().getEntityByUuid(getAccessor().getDragonUUID());
        return (entity != null) ? (EnderDragon) entity.getBukkitEntity() : null;
    }

    @Override
    public BossBar getBossBar() {
        return new ForgeBukkitBossBar(getAccessor().getBossInfo());
    }

    @Override
    public Location getEndPortalLocation() {
        if (getAccessor().getExitPortal() == null) {
            return null;
        }

        return new Location(ForgeBukkitWorld.getWorldWrapper(getAccessor().getWorld()), getAccessor().getExitPortal().getX(), getAccessor().getExitPortal().getY(), getAccessor().getExitPortal().getZ());
    }

    @Override
    public boolean generateEndPortal(boolean withPortals) {
        if (getAccessor().getExitPortal() != null || getAccessor().getPortalPattern() != null) {
            return false;
        }

        ObfuscationReflectionHelper.getPrivateValue(DragonFightManager.class, getHandle(), "func_186094_a");

        this.getHandle().generatePortal(withPortals);
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
    public RespawnPhase getRespawnPhase() {
        return toBukkitRespawnPhase(getAccessor().getSpawnState());
    }

    @Override
    public boolean setRespawnPhase(RespawnPhase phase) {
        Preconditions.checkArgument(phase != null && phase != RespawnPhase.NONE, "Invalid respawn phase provided: %s", phase);

        if (getAccessor().getSpawnState()== null) {
            return false;
        }

        getAccessor().setSpawnState(toMcRespawnPhase(phase));
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

    private DragonFightManagerMixin getAccessor() {
        return (DragonFightManagerMixin) getHandle();
    }
}
