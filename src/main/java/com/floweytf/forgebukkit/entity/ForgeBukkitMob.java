package com.floweytf.forgebukkit.entity;

import com.floweytf.forgebukkit.ForgeBukkit;
import com.floweytf.forgebukkit.ForgeBukkitServer;
import com.floweytf.forgebukkit.entity.ForgeBukkitEntity;
import com.floweytf.forgebukkit.entity.ForgeBukkitLivingEntity;
import com.floweytf.forgebukkit.util.ForgeBukkitNamespacedKey;
import net.minecraft.entity.MobEntity;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Mob;
import org.bukkit.event.entity.EntityTargetEvent;
import org.bukkit.event.entity.EntityTargetLivingEntityEvent;
import org.bukkit.loot.LootTable;

public abstract class ForgeBukkitMob extends ForgeBukkitLivingEntity implements Mob {
    boolean isAware;
    // janky
    public static boolean setTargetGoal(MobEntity entity, net.minecraft.entity.LivingEntity target, EntityTargetEvent.TargetReason reason, boolean fireEvent) {
        if (entity.getAttackTarget() == target)
            return false;

        if (fireEvent) {
            if (reason == EntityTargetEvent.TargetReason.UNKNOWN && entity.getAttackTarget() != null && target == null)
                reason = entity.getAttackTarget().isAlive() ? EntityTargetEvent.TargetReason.FORGOT_TARGET : EntityTargetEvent.TargetReason.TARGET_DIED;

            if (reason == EntityTargetEvent.TargetReason.UNKNOWN)
                ForgeBukkit.logger.warn("Unknown target reason, please report on the issue tracker (This is a issue with craftbukkit, not me!)", new Exception());

            ForgeBukkitLivingEntity fbtarget = null;
            if (target != null)
                fbtarget = (ForgeBukkitLivingEntity) ForgeBukkitEntity.wrap(target);

            EntityTargetLivingEntityEvent event = new EntityTargetLivingEntityEvent(ForgeBukkitEntity.wrap(entity), fbtarget, reason);
            ForgeBukkitEntity.wrap(entity).getServer().getPluginManager().callEvent(event);
            if (event.isCancelled())
                return false;

            if (event.getTarget() != null)
                entity = (MobEntity) ((ForgeBukkitLivingEntity) event.getTarget()).getHandle();
            else {
                entity = null;
            }
        }
        entity.setAttackTarget(target);
        return true;
    }
    public ForgeBukkitMob(ForgeBukkitServer server, MobEntity entity) {
        super(server, entity);
    }

    @Override
    public void setTarget(LivingEntity target) {
        MobEntity entity = getHandle();
        if (target == null) {
            setTargetGoal(entity, null, null, false);
        } else if (target instanceof ForgeBukkitLivingEntity) {
            setTargetGoal(entity, ((ForgeBukkitLivingEntity) target).getHandle(), null, false);
        }
    }

    @Override
    public ForgeBukkitLivingEntity getTarget() {
        if (getHandle().getAttackTarget() == null)
            return null;

        return (ForgeBukkitLivingEntity)ForgeBukkitEntity.wrap(getHandle().getAttackTarget());
    }

    @Override
    public void setAware(boolean aware) {
        isAware = aware;
    }

    @Override
    public boolean isAware() {
        return isAware;
    }

    @Override
    public MobEntity getHandle() {
        return (MobEntity) super.getHandle();
    }

    @Override
    public String toString() {
        return "ForgeBukkitMob";
    }

    @Override
    public void setLootTable(LootTable table) {
        getHandle().deathLootTable = ((table == null) ? null : ForgeBukkitNamespacedKey.toMinecraft(table.getKey()));
    }

    @Override
    public LootTable getLootTable() {
        NamespacedKey key = ForgeBukkitNamespacedKey.fromMinecraft(getHandle().getLootTableResourceLocation());
        return Bukkit.getLootTable(key);
    }

    @Override
    public void setSeed(long seed) {
        getHandle().deathLootTableSeed = seed;
    }

    public long getSeed() {
        return getHandle().deathLootTableSeed;
    }
}
