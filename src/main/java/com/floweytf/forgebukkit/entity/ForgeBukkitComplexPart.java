package com.floweytf.forgebukkit.entity;

import com.floweytf.forgebukkit.ForgeBukkitServer;
import net.minecraft.entity.boss.dragon.EnderDragonPartEntity;
import org.bukkit.entity.ComplexEntityPart;
import org.bukkit.entity.ComplexLivingEntity;
import org.bukkit.entity.EntityType;
import org.bukkit.event.entity.EntityDamageEvent;

public class ForgeBukkitComplexPart extends ForgeBukkitEntity implements ComplexEntityPart {
    public ForgeBukkitComplexPart(ForgeBukkitServer server, EnderDragonPartEntity entity) {
        super(server, entity);
    }

    @Override
    public ComplexLivingEntity getParent() {
        return (ComplexLivingEntity) ForgeBukkitEntity.wrap(getHandle().dragon);
    }

    @Override
    public void setLastDamageCause(EntityDamageEvent cause) {
        getParent().setLastDamageCause(cause);
    }

    @Override
    public EntityDamageEvent getLastDamageCause() {
        return getParent().getLastDamageCause();
    }

    @Override
    public boolean isValid() {
        return getParent().isValid();
    }

    @Override
    public EnderDragonPartEntity getHandle() {
        return (EnderDragonPartEntity) super.getHandle();
    }

    @Override
    public String toString() {
        return "ForgeBukkitComplexPart";
    }

    @Override
    public EntityType getType() {
        return EntityType.UNKNOWN;
    }
}
