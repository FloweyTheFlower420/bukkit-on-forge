package com.floweytf.forgebukkit.util;

import net.minecraft.util.ResourceLocation;
import org.bukkit.NamespacedKey;

@Converter
public class ForgeBukkitNamespacedKey {
    public ForgeBukkitNamespacedKey() {
    }

    public static NamespacedKey fromStringOrNull(String string) {
        if (string == null || string.isEmpty()) {
            return null;
        }
        ResourceLocation minecraft = null;
        try {
            minecraft = new ResourceLocation(string);
        } catch (Exception ignored) {
        }

        return (minecraft == null) ? null : toBukkit(minecraft);
    }

    public static NamespacedKey fromString(String string) {
        return toBukkit(new ResourceLocation(string));
    }

    public static NamespacedKey toBukkit(ResourceLocation minecraft) {
        return new NamespacedKey(minecraft.getNamespace(), minecraft.getPath());
    }

    public static ResourceLocation toMinecraft(NamespacedKey key) {
        return new ResourceLocation(key.getNamespace(), key.getKey());
    }
}
