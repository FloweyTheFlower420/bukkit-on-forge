package com.floweytf.forgebukkit.entity.memory;

import com.floweytf.forgebukkit.ForgeBukkitServer;
import net.minecraft.server.BlockPosition;
import net.minecraft.server.GlobalPos;
import net.minecraft.util.math.GlobalPos;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.craftbukkit.CraftServer;
import org.bukkit.craftbukkit.CraftWorld;

import java.util.UUID;

public final class CraftMemoryMapper {

    private CraftMemoryMapper() {}

    public static Object toBukkit(Object object) {
        if (object instanceof GlobalPos)
            return toBukkit((GlobalPos) object);
        else if (object instanceof Long)
            return object;
        else if (object instanceof UUID)
            return object;
        else if (object instanceof Boolean)
            return object;

        throw new UnsupportedOperationException("Do not know how to map " + object);
    }

    public static Object toMinecraft(Object object) {
        if (object == null)
            return null;
        else if (object instanceof Location)
            return toMinecraft((Location) object);
        else if (object instanceof Long)
            return object;
        else if (object instanceof UUID)
            return object;
        else if (object instanceof Boolean)
            return object;

        throw new UnsupportedOperationException("Do not know how to map " + object);
    }

    public static Location toBukkit(GlobalPos globalPos) {
        return new Location(((ForgeBukkitServer) Bukkit.getServer()).getServer().getWorldServer(globalPos.getDimensionManager()).getWorld(), globalPos.getBlockPosition().getX(), globalPos.getBlockPosition().getY(), globalPos.getBlockPosition().getZ());
    }

    public static GlobalPos toMinecraft(Location location) {
        return GlobalPos.create(((CraftWorld) location.getWorld()).getHandle().getDimensionKey(), new BlockPosition(location.getX(), location.getY(), location.getZ()));
    }
}
