package com.floweytf.forgebukkit.mixins;

import net.minecraft.block.pattern.BlockPattern;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.end.DragonFightManager;
import net.minecraft.world.end.DragonSpawnState;
import net.minecraft.world.server.ServerBossInfo;
import net.minecraft.world.server.ServerWorld;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.UUID;

@Mixin(DragonFightManager.class)
public interface DragonFightManagerMixin {
    @Accessor("world")
    public ServerWorld getWorld();

    @Accessor("exitPortalLocation")
    public BlockPos getExitPortal();

    @Accessor("portalPattern")
    public BlockPattern getPortalPattern();

    @Accessor("respawnState")
    public DragonSpawnState getSpawnState();

    @Accessor("respawnState")
    public void setSpawnState(DragonSpawnState state);

    @Accessor("dragonUniqueId")
    public UUID getDragonUUID();

    @Accessor("bossInfo")
    public ServerBossInfo getBossInfo();
}
