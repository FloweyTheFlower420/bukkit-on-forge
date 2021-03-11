package com.floweytf.forgebukkit.advancement;

import com.floweytf.forgebukkit.Wrapper;
import com.floweytf.forgebukkit.util.ForgeBukkitNamespacedKey;
import org.bukkit.NamespacedKey;
import org.bukkit.advancement.Advancement;

import java.util.Collection;
import java.util.Collections;

public class ForgeBukkitAdvancement extends Wrapper<net.minecraft.advancements.Advancement> implements Advancement {
    public ForgeBukkitAdvancement(net.minecraft.advancements.Advancement handle) {
        super(handle);
    }

    @Override
    public NamespacedKey getKey() {
        return ForgeBukkitNamespacedKey.fromMinecraft(getHandle().getId());
    }

    @Override
    public Collection<String> getCriteria() {
        return Collections.unmodifiableCollection(getHandle().getCriteria().keySet());
    }
}