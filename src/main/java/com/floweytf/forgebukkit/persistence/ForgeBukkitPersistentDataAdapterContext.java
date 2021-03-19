package com.floweytf.forgebukkit.persistence;

import org.bukkit.persistence.PersistentDataAdapterContext;

public final class ForgeBukkitPersistentDataAdapterContext implements PersistentDataAdapterContext {

    private final ForgeBukkitPersistentDataTypeRegistry registry;

    public ForgeBukkitPersistentDataAdapterContext(ForgeBukkitPersistentDataTypeRegistry registry) {
        this.registry = registry;
    }

    /**
     * Creates a new and empty tag container instance
     *
     * @return the fresh container instance
     */
    @Override
    public ForgeBukkitPersistentDataContainer newPersistentDataContainer() {
        return new ForgeBukkitPersistentDataContainer(this.registry);
    }
}
