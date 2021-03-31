package com.floweytf.forgebukkit.entity.memory;

import com.floweytf.forgebukkit.util.ForgeBukkitNamespacedKey;
import net.minecraft.entity.ai.brain.memory.MemoryModuleType;
import net.minecraft.util.registry.Registry;
import org.bukkit.entity.memory.MemoryKey;

public final class ForgeBukkitMemoryKey {

    private ForgeBukkitMemoryKey() {}

    public static <T, U> MemoryModuleType<U> fromMemoryKey(MemoryKey<T> memoryKey) {
        return (MemoryModuleType<U>) Registry.MEMORY_MODULE_TYPE.getOrDefault(ForgeBukkitNamespacedKey.toMinecraft(memoryKey.getKey()));
    }

    public static <T, U> MemoryKey<U> toMemoryKey(MemoryModuleType<T> memoryModuleType) {
        return MemoryKey.getByKey(ForgeBukkitNamespacedKey.fromMinecraft(Registry.MEMORY_MODULE_TYPE.getKey(memoryModuleType)));
    }
}
