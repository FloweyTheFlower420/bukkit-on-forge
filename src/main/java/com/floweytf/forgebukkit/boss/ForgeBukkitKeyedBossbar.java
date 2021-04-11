package com.floweytf.forgebukkit.boss;

import com.floweytf.forgebukkit.util.ForgeBukkitNamespacedKey;
import net.minecraft.server.CustomServerBossInfo;
import org.bukkit.NamespacedKey;
import org.bukkit.boss.KeyedBossBar;

import javax.annotation.Nonnull;

public class ForgeBukkitKeyedBossbar extends ForgeBukkitBossBar implements KeyedBossBar {

    public ForgeBukkitKeyedBossbar(CustomServerBossInfo bossBattleCustom) {
        super(bossBattleCustom);
    }

    @Override
    @Nonnull
    public NamespacedKey getKey() {
        return ForgeBukkitNamespacedKey.toBukkit(getHandle().getId());
    }

    @Override
    public CustomServerBossInfo getHandle() {
        return (CustomServerBossInfo) super.getHandle();
    }
}
