package com.floweytf.forgebukkit.mixins;

import com.floweytf.forgebukkit.access.EntityMixinAccess;
import com.floweytf.forgebukkit.entity.ForgeBukkitEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.nbt.CompoundNBT;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Entity.class)
public abstract class EntityMixin implements EntityMixinAccess {
    private boolean persist = false;
    private boolean persistentInvisibility = false;

    private static boolean isLevelAtLeast(CompoundNBT tag, int level) {
        return tag.contains("Bukkit.updateLevel") && tag.getInt("Bukkit.updateLevel") >= level;
    }

    @Shadow
    public boolean removed;

    @Shadow
    public float rotationYaw;

    @Shadow
    public float rotationPitch;

    @Shadow
    public String getEntityString() {
        throw new IllegalStateException("Mixin injection clearly failed!");
    }

    @Shadow
    public CompoundNBT writeWithoutTypeId(CompoundNBT nbt) {
        throw new IllegalStateException("Mixin injection clearly failed!");
    }

    @Shadow public abstract void setInvisible(boolean invisible);

    @Inject(method = "writeUnlessRemoved(Lnet/minecraft/nbt/CompoundNBT;)Z", at = @At("HEAD"), cancellable = true)
    private void writeUnlessRemove(CompoundNBT nbttagcompound, CallbackInfoReturnable<Boolean> cir) {
        String s = this.getEntityString();

        if (this.persist && !removed && s != null) {
            nbttagcompound.putString("id", s);
            writeWithoutTypeId(nbttagcompound);
            cir.setReturnValue(true);
        } else
            cir.setReturnValue(false);
    }

    @Inject(method = "writeWithoutTypeId(Lnet/minecraft/nbt/CompoundNBT;)Lnet/minecraft/nbt/CompoundNBT;", at = @At(value = "INVOKE_ASSIGN", target = "Lnet/minecraft/nbt/CompoundNBT;put(Ljava/lang/String;Lnet/minecraft/nbt/INBT;)Lnet/minecraft/nbt/INBT;", ordinal = 3))
    private void writeWithoutTypeId(CompoundNBT nbttagcompound, CallbackInfoReturnable<CompoundNBT> cir) {
        if (Float.isNaN(this.rotationYaw))
            this.rotationYaw = 0;

        if (Float.isNaN(this.rotationPitch))
            this.rotationPitch = 0;

        nbttagcompound.putBoolean("Bukkit.persist", this.persist);
        nbttagcompound.putBoolean("Bukkit.invisible", this.persistentInvisibility);
    }

    @Inject(method = "read(Lnet/minecraft/nbt/CompoundNBT;)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/nbt/CompoundNBT;getList(Ljava/lang/String;I)Lnet/minecraft/nbt/ListNBT;"))
    public void load(CompoundNBT nbttagcompound, CallbackInfo ci) {
        // ...HAH its not always false!
        if ((Object)this instanceof LivingEntity) {
            LivingEntity entity = (LivingEntity)(Object) this;

            // Reset the persistence for tamed animals
            if (entity instanceof TameableEntity && !isLevelAtLeast(nbttagcompound, 2) && !nbttagcompound.getBoolean("PersistenceRequired")) {
                MobEntity mobEntity = (MobEntity) entity;
                mobEntity.persistenceRequired = !mobEntity.canDespawn(0);
            }
        }

        this.persist = !nbttagcompound.contains("Bukkit.persist") || nbttagcompound.getBoolean("Bukkit.persist");
        ForgeBukkitEntity.wrap((Entity)(Object) this).readBukkitValues(nbttagcompound);

        if (nbttagcompound.contains("Bukkit.invisible")) {
            boolean bukkitInvisible = nbttagcompound.getBoolean("Bukkit.invisible");
            this.setInvisible(bukkitInvisible);
            this.persistentInvisibility = bukkitInvisible;
        }
    }

    @Override
    public boolean getPersist() {
        return persist;
    }

    @Override
    public void setPersist(boolean b) {
        persist = b;
    }

    @Override
    public boolean getPersistentInvisibility() {
        return persistentInvisibility;
    }

    @Override
    public void setPersistentInvisibility(boolean b) {
        persistentInvisibility = b;
    }
}
