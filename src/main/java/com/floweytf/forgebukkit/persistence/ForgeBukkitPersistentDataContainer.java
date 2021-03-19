package com.floweytf.forgebukkit.persistence;

import com.floweytf.forgebukkit.util.ForgeBukkitNBTTagConfigSerializer;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import org.apache.commons.lang.Validate;
import org.bukkit.NamespacedKey;
import org.bukkit.persistence.PersistentDataAdapterContext;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.util.*;

public final class ForgeBukkitPersistentDataContainer implements PersistentDataContainer {

    private final Map<String, INBT> customDataTags = new HashMap<>();
    private final ForgeBukkitPersistentDataTypeRegistry registry;
    private final ForgeBukkitPersistentDataAdapterContext adapterContext;

    public ForgeBukkitPersistentDataContainer(Map<String, INBT> customTags, ForgeBukkitPersistentDataTypeRegistry registry) {
        this(registry);
        this.customDataTags.putAll(customTags);
    }

    public ForgeBukkitPersistentDataContainer(ForgeBukkitPersistentDataTypeRegistry registry) {
        this.registry = registry;
        this.adapterContext = new ForgeBukkitPersistentDataAdapterContext(this.registry);
    }

    @Override
    public <T, Z> void set(NamespacedKey key, PersistentDataType<T, Z> type, Z value) {
        Validate.notNull(key, "The provided key for the custom value was null");
        Validate.notNull(type, "The provided type for the custom value was null");
        Validate.notNull(value, "The provided value for the custom value was null");

        this.customDataTags.put(key.toString(), registry.wrap(type.getPrimitiveType(), type.toPrimitive(value, adapterContext)));
    }

    @Override
    public <T, Z> boolean has(NamespacedKey key, PersistentDataType<T, Z> type) {
        Validate.notNull(key, "The provided key for the custom value was null");
        Validate.notNull(type, "The provided type for the custom value was null");

        INBT value = this.customDataTags.get(key.toString());
        if (value == null) {
            return false;
        }

        return registry.isInstanceOf(type.getPrimitiveType(), value);
    }

    @Override
    public <T, Z> Z get(NamespacedKey key, PersistentDataType<T, Z> type) {
        Validate.notNull(key, "The provided key for the custom value was null");
        Validate.notNull(type, "The provided type for the custom value was null");

        INBT value = this.customDataTags.get(key.toString());
        if (value == null) {
            return null;
        }

        return type.fromPrimitive(registry.extract(type.getPrimitiveType(), value), adapterContext);
    }

    @Override
    public <T, Z> Z getOrDefault(NamespacedKey key, PersistentDataType<T, Z> type, Z defaultValue) {
        Z z = get(key, type);
        return z != null ? z : defaultValue;
    }

    @Override
    public Set<NamespacedKey> getKeys() {
        Set<NamespacedKey> keys = new HashSet<>();

        this.customDataTags.keySet().forEach(key -> {
            String[] keyData = key.split(":", 2);
            if (keyData.length == 2) {
                keys.add(new NamespacedKey(keyData[0], keyData[1]));
            }
        });

        return keys;
    }

    @Override
    public void remove(NamespacedKey key) {
        Validate.notNull(key, "The provided key for the custom value was null");

        this.customDataTags.remove(key.toString());
    }

    @Override
    public boolean isEmpty() {
        return this.customDataTags.isEmpty();
    }

    @Override
    public PersistentDataAdapterContext getAdapterContext() {
        return this.adapterContext;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof ForgeBukkitPersistentDataContainer)) {
            return false;
        }

        Map<String, INBT> myRawMap = getRaw();
        Map<String, INBT> theirRawMap = ((ForgeBukkitPersistentDataContainer) obj).getRaw();

        return Objects.equals(myRawMap, theirRawMap);
    }

    public CompoundNBT toTagCompound() {
        CompoundNBT tag = new CompoundNBT();
        for (Map.Entry<String, INBT> entry : this.customDataTags.entrySet())
            tag.put(entry.getKey(), entry.getValue());

        return tag;
    }

    public void put(String key, INBT base) {
        this.customDataTags.put(key, base);
    }

    public void putAll(Map<String, INBT> map) {
        this.customDataTags.putAll(map);
    }

    public void putAll(CompoundNBT compound) {
        for (String key : compound.keySet()) {
            this.customDataTags.put(key, compound.get(key));
        }
    }

    public Map<String, INBT> getRaw() {
        return this.customDataTags;
    }

    public ForgeBukkitPersistentDataTypeRegistry getDataTagTypeRegistry() {
        return registry;
    }

    @Override
    public int hashCode() {
        int hashCode = 3;
        hashCode += this.customDataTags.hashCode(); // We will simply add the maps hashcode
        return hashCode;
    }

    public Map<String, Object> serialize() {
        return (Map<String, Object>) ForgeBukkitNBTTagConfigSerializer.serialize(toTagCompound());
    }
}
