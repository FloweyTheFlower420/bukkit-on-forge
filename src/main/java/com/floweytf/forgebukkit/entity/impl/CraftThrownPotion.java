package com.floweytf.forgebukkit.entity.impl;

import com.google.common.collect.ImmutableList;
import net.minecraft.server.EntityPotion;
import net.minecraft.server.MobEffect;
import net.minecraft.server.PotionUtil;
import org.apache.commons.lang.Validate;
import org.bukkit.Material;
import org.bukkit.craftbukkit.CraftServer;
import org.bukkit.craftbukkit.inventory.CraftItemStack;
import org.bukkit.craftbukkit.potion.CraftPotionUtil;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.ThrownPotion;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;

import java.util.Collection;

public class CraftThrownPotion extends org.bukkit.craftbukkit.entity.CraftProjectile implements ThrownPotion {
    public CraftThrownPotion(CraftServer server, EntityPotion entity) {
        super(server, entity);
    }

    @Override
    public Collection<PotionEffect> getEffects() {
        ImmutableList.Builder<PotionEffect> builder = ImmutableList.builder();
        for (MobEffect effect : PotionUtil.getEffects(getHandle().getItem())) {
            builder.add(CraftPotionUtil.toBukkit(effect));
        }
        return builder.build();
    }

    @Override
    public ItemStack getItem() {
        return CraftItemStack.asBukkitCopy(getHandle().getItem());
    }

    @Override
    public void setItem(ItemStack item) {
        // The ItemStack must not be null.
        Validate.notNull(item, "ItemStack cannot be null.");

        // The ItemStack must be a potion.
        Validate.isTrue(item.getType() == Material.LINGERING_POTION || item.getType() == Material.SPLASH_POTION, "ItemStack must be a lingering or splash potion. This item stack was " + item.getType() + ".");

        getHandle().setItem(CraftItemStack.asNMSCopy(item));
    }

    @Override
    public EntityPotion getHandle() {
        return (EntityPotion) entity;
    }

    @Override
    public EntityType getType() {
        return EntityType.SPLASH_POTION;
    }
}
