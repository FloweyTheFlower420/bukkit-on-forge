package com.floweytf.forgebukkit.scheduler;

import org.bukkit.plugin.Plugin;

class ForgeBukkitAsyncDebugger {
    private ForgeBukkitAsyncDebugger next = null;
    private final int expiry;
    private final Plugin plugin;
    private final Class<?> clazz;

    ForgeBukkitAsyncDebugger(final int expiry, final Plugin plugin, final Class<?> clazz) {
        this.expiry = expiry;
        this.plugin = plugin;
        this.clazz = clazz;

    }

    final ForgeBukkitAsyncDebugger getNextHead(final int time) {
        ForgeBukkitAsyncDebugger next, current = this;
        while (time > current.expiry && (next = current.next) != null) {
            current = next;
        }
        return current;
    }

    final ForgeBukkitAsyncDebugger setNext(final ForgeBukkitAsyncDebugger next) {
        return this.next = next;
    }

    StringBuilder debugTo(final StringBuilder string) {
        for (ForgeBukkitAsyncDebugger next = this; next != null; next = next.next) {
            string.append(next.plugin.getDescription().getName()).append(':').append(next.clazz.getName()).append('@').append(next.expiry).append(',');
        }
        return string;
    }
}
