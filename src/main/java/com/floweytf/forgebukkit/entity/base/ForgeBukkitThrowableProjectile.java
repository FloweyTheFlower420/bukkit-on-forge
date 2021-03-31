package com.floweytf.forgebukkit.entity.base;

import com.floweytf.forgebukkit.ForgeBukkitServer;
import com.floweytf.forgebukkit.inventory.ForgeBukkitItemStack;
import net.minecraft.entity.projectile.ThrowableEntity;
import org.bukkit.entity.ThrowableProjectile;
import org.bukkit.inventory.ItemStack;

public abstract class ForgeBukkitThrowableProjectile extends ForgeBukkitProjectile implements ThrowableProjectile {

    public ForgeBukkitThrowableProjectile(ForgeBukkitServer server, ThrowableEntity entity) {
        super(server, entity);
    }

    @Override
    public ItemStack getItem() {
        if (getHandle().getItem().isEmpty()) {
            return CraftItemStack.asBukkitCopy(new net.minecraft.server.ItemStack(getHandle().getDefaultItemPublic()));
        } else {
            return CraftItemStack.asBukkitCopy(getHandle().getItem());
        }
    }

    @Override
    public void setItem(ItemStack item) {
        getHandle().setItemStackToSlot(ForgeBukkitItemStack.toMcCopy(item));
    }

    @Override
    public ThrowableEntity getHandle() {
        return (ThrowableEntity) super.getHandle();
    }
}
