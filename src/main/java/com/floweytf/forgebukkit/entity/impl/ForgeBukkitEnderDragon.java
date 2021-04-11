package com.floweytf.forgebukkit.entity.impl;

import com.floweytf.forgebukkit.ForgeBukkitServer;
import com.floweytf.forgebukkit.boss.ForgeBukkitDragonBattle;
import com.floweytf.forgebukkit.entity.ForgeBukkitComplexLivingEntity;
import com.floweytf.forgebukkit.entity.ForgeBukkitEntity;
import com.floweytf.forgebukkit.util.Converter;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.ImmutableSet.Builder;
import net.minecraft.entity.boss.dragon.EnderDragonEntity;
import net.minecraft.entity.boss.dragon.EnderDragonPartEntity;
import net.minecraft.entity.boss.dragon.phase.PhaseType;
import org.bukkit.boss.BossBar;
import org.bukkit.boss.DragonBattle;
import org.bukkit.entity.ComplexEntityPart;
import org.bukkit.entity.EnderDragon;
import org.bukkit.entity.EntityType;

import java.util.Set;

public class ForgeBukkitEnderDragon extends ForgeBukkitComplexLivingEntity implements EnderDragon {

    public ForgeBukkitEnderDragon(ForgeBukkitServer server, EnderDragonEntity entity) {
        super(server, entity);
    }

    @Override
    public Set<ComplexEntityPart> getParts() {
        Builder<ComplexEntityPart> builder = ImmutableSet.builder();

        for (EnderDragonPartEntity part : getHandle().getDragonParts())
            builder.add((ComplexEntityPart) ForgeBukkitEntity.wrap(part));

        return builder.build();
    }

    @Override
    public EnderDragonEntity getHandle() {
        return (EnderDragonEntity) super.getHandle();
    }

    @Override
    public String toString() {
        return "ForgeBukkitEnderDragon";
    }

    @Override
    public EntityType getType() {
        return EntityType.ENDER_DRAGON;
    }

    @Override
    public Phase getPhase() {
        return Phase.values()[getHandle().getDataManager().get(EnderDragonEntity.PHASE)];
    }

    @Override
    public void setPhase(Phase phase) {
        getHandle().getPhaseManager().setPhase(PhaseConverter.toMinecraft(phase));
    }

    @Override
    public BossBar getBossBar() {
        return getDragonBattle().getBossBar();
    }

    @Override
    public DragonBattle getDragonBattle() {
        return new ForgeBukkitDragonBattle(getHandle().getFightManager());
    }

    @Override
    public int getDeathAnimationTicks() {
        return getHandle().deathTicks;
    }

    @Converter
    public static class PhaseConverter {
        public static Phase toBukkit(PhaseType phase) {
            return Phase.values()[phase.getId()];
        }

        public static PhaseType toMinecraft(Phase phase) {
            return PhaseType.getById(phase.ordinal());
        }
    }
}
