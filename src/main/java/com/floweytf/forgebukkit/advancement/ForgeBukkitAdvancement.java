package com.floweytf.forgebukkit.advancement;

import com.floweytf.forgebukkit.Wrapper;
import com.floweytf.forgebukkit.util.ForgeBukkitNamespacedKey;
import net.minecraft.client.Minecraft;
import org.bukkit.NamespacedKey;
import org.bukkit.advancement.Advancement;

import javax.annotation.Nonnull;
import java.util.Collection;
import java.util.Collections;

public class ForgeBukkitAdvancement extends Wrapper<net.minecraft.advancements.Advancement> implements Advancement {
    public ForgeBukkitAdvancement(net.minecraft.advancements.Advancement handle) {
        super(handle);
    }

    @Override
    @Nonnull
    public NamespacedKey getKey() {
        return ForgeBukkitNamespacedKey.toBukkit(getHandle().getId());
    }

    @Override
    @Nonnull
    public Collection<String> getCriteria() {
        return Collections.unmodifiableCollection(getHandle().getCriteria().keySet());
    }
}