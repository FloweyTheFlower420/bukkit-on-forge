package com.floweytf.forgebukkit.persistence;

import com.google.common.primitives.Primitives;
import net.minecraft.nbt.*;
import org.apache.commons.lang3.Validate;
import org.bukkit.persistence.PersistentDataContainer;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;

public final class ForgeBukkitPersistentDataTypeRegistry {

    private final Function<Class, TagAdapter> CREATE_ADAPTER = this::createAdapter;
    private final Map<Class, TagAdapter> adapters = new HashMap<>();

    private <T> TagAdapter createAdapter(Class<T> type) {
        if (!Primitives.isWrapperType(type)) {
            type = Primitives.wrap(type); //Make sure we will always "switch" over the wrapper types
        }

        if (Objects.equals(Byte.class, type))
            return createAdapter(Byte.class, ByteNBT.class, ByteNBT::valueOf, ByteNBT::getByte);
        if (Objects.equals(Short.class, type))
            return createAdapter(Short.class, ShortNBT.class, ShortNBT::valueOf, ShortNBT::getShort);
        if (Objects.equals(Integer.class, type))
            return createAdapter(Integer.class, IntNBT.class, IntNBT::valueOf, IntNBT::getInt);
        if (Objects.equals(Long.class, type))
            return createAdapter(Long.class, LongNBT.class, LongNBT::valueOf, LongNBT::getLong);
        if (Objects.equals(Float.class, type))
            return createAdapter(Float.class, FloatNBT.class, FloatNBT::valueOf, FloatNBT::getFloat);
        if (Objects.equals(Double.class, type))
            return createAdapter(Double.class, DoubleNBT.class, DoubleNBT::valueOf, DoubleNBT::getDouble);
        if (Objects.equals(String.class, type))
            return createAdapter(String.class, StringNBT.class, StringNBT::valueOf, StringNBT::getString);
        if (Objects.equals(byte[].class, type))
            return createAdapter(byte[].class, ByteArrayNBT.class, array -> new ByteArrayNBT(Arrays.copyOf(array, array.length)), n -> Arrays.copyOf(n.getByteArray(), n.size()));
        if (Objects.equals(int[].class, type))
            return createAdapter(int[].class, IntArrayNBT.class, array -> new IntArrayNBT(Arrays.copyOf(array, array.length)), n -> Arrays.copyOf(n.getIntArray(), n.size()));
        if (Objects.equals(long[].class, type))
            return createAdapter(long[].class, LongArrayNBT.class, array -> new LongArrayNBT(Arrays.copyOf(array, array.length)), n -> Arrays.copyOf(n.getAsLongArray(), n.size())); // veeeery consistent naming
        if (Objects.equals(PersistentDataContainer[].class, type)) {
            return createAdapter(PersistentDataContainer[].class, ListNBT.class,
                (containerArray) -> {
                    ListNBT list = new ListNBT();
                    for (int i = 0; i < containerArray.length; i++)
                        list.add(((ForgeBukkitPersistentDataContainer) containerArray[i]).toTagCompound());

                    return list;
                },
                (tag) -> {
                    PersistentDataContainer[] containerArray = new ForgeBukkitPersistentDataContainer[tag.size()];
                    for (int i = 0; i < tag.size(); i++) {
                        ForgeBukkitPersistentDataContainer container = new ForgeBukkitPersistentDataContainer(this);
                        CompoundNBT compound = tag.getCompound(i);
                        for (String key : compound.keySet())
                            container.put(key, compound.get(key));

                        containerArray[i] = container;
                    }
                    return containerArray;
                });
        }

        if (Objects.equals(PersistentDataContainer.class, type)) {
            return createAdapter(ForgeBukkitPersistentDataContainer.class, CompoundNBT.class, ForgeBukkitPersistentDataContainer::toTagCompound, tag -> {
                ForgeBukkitPersistentDataContainer container = new ForgeBukkitPersistentDataContainer(this);
                for (String key : tag.keySet())
                    container.put(key, tag.get(key));

                return container;
            });
        }

        throw new IllegalArgumentException("Could not find a valid TagAdapter implementation for the requested type " + type.getSimpleName());
    }

    private <T, Z extends INBT> TagAdapter<T, Z> createAdapter(Class<T> primitiveType, Class<Z> INBTType, Function<T, Z> builder, Function<Z, T> extractor) {
        return new TagAdapter<>(primitiveType, INBTType, builder, extractor);
    }

    public <T> INBT wrap(Class<T> type, T value) {
        return this.adapters.computeIfAbsent(type, CREATE_ADAPTER).build(value);
    }

    public <T> boolean isInstanceOf(Class<T> type, INBT base) {
        return this.adapters.computeIfAbsent(type, CREATE_ADAPTER).isInstance(base);
    }

    public <T> T extract(Class<T> type, INBT tag) throws ClassCastException, IllegalArgumentException {
        TagAdapter adapter = this.adapters.computeIfAbsent(type, CREATE_ADAPTER);
        Validate.isTrue(adapter.isInstance(tag), "`The found tag instance cannot store %s as it is a %s", type.getSimpleName(), tag.getClass().getSimpleName());

        Object foundValue = adapter.extract(tag);
        Validate.isInstanceOf(type, foundValue, "The found object is of the type %s. Expected type %s", foundValue.getClass().getSimpleName(), type.getSimpleName());
        return type.cast(foundValue);
    }

    private static class TagAdapter<T, Z extends INBT> {

        private final Function<T, Z> builder;
        private final Function<Z, T> extractor;

        private final Class<T> primitiveType;
        private final Class<Z> INBTType;

        public TagAdapter(Class<T> primitiveType, Class<Z> INBTType, Function<T, Z> builder, Function<Z, T> extractor) {
            this.primitiveType = primitiveType;
            this.INBTType = INBTType;
            this.builder = builder;
            this.extractor = extractor;
        }

        T extract(INBT base) {
            Validate.isInstanceOf(INBTType, base, "The provided INBT was of the type %s. Expected type %s", base.getClass().getSimpleName(), INBTType.getSimpleName());
            return this.extractor.apply(INBTType.cast(base));
        }

        Z build(Object value) {
            Validate.isInstanceOf(primitiveType, value, "The provided value was of the type %s. Expected type %s", value.getClass().getSimpleName(), primitiveType.getSimpleName());
            return this.builder.apply(primitiveType.cast(value));
        }

        boolean isInstance(INBT base) {
            return this.INBTType.isInstance(base);
        }
    }
}
