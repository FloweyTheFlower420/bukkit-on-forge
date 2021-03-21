package com.floweytf.forgebukkit.entity.impl;

import com.floweytf.forgebukkit.ForgeBukkitServer;
import com.floweytf.forgebukkit.entity.base.ForgeBukkitTameableAnimal;
import com.google.common.base.Preconditions;
import net.minecraft.entity.passive.CatEntity;
import org.bukkit.DyeColor;
import org.bukkit.entity.Cat;
import org.bukkit.entity.EntityType;
import org.jetbrains.annotations.NotNull;

public class ForgeBukkitCat extends ForgeBukkitTameableAnimal implements Cat {

    public ForgeBukkitCat(ForgeBukkitServer server, CatEntity entity) {
        super(server, entity);
    }

    @Override
    public CatEntity getHandle() {
        return (CatEntity) super.getHandle();
    }

    @Override
    @NotNull
    public EntityType getType() {
        return EntityType.CAT;
    }

    @Override
    public String toString() {
        return "ForgeBukkitCat";
    }

    @Override
    @NotNull
    public Type getCatType() {
        return Type.values()[getHandle().getCatType()];
    }

    @Override
    public void setCatType(Type type) {
        Preconditions.checkArgument(type != null, "Cannot have null Type");

        getHandle().setCatType(type.ordinal());
    }

    @Override
    public DyeColor getCollarColor() {
        return DyeColor.getByWoolData((byte) getHandle().getCollarColor().getColorValue());
    }

    @Override
    public void setCollarColor(DyeColor color) {
        getHandle().setCollarColor(net.minecraft.item.DyeColor.byId(color.getWoolData()));
    }
}
