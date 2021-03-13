package com.floweytf.forgebukkit.attribute;

import com.floweytf.forgebukkit.Wrapper;
import com.floweytf.forgebukkit.util.ForgeBukkitNamespacedKey;
import com.google.common.base.Preconditions;
import net.minecraft.entity.ai.attributes.AttributeModifierManager;
import net.minecraft.entity.ai.attributes.ModifiableAttributeInstance;
import org.bukkit.Registry;
import org.bukkit.attribute.Attributable;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;

import javax.annotation.Nonnull;

public class ForgeBukkitAttributeMap extends Wrapper<AttributeModifierManager> implements Attributable {
    public ForgeBukkitAttributeMap(AttributeModifierManager handle) {
        super(handle);
    }

    @Override
    public AttributeInstance getAttribute(@Nonnull Attribute attribute) {
        Preconditions.checkArgument(attribute != null, "attribute");
        ModifiableAttributeInstance mc = getHandle().createInstanceIfAbsent(toMinecraft(attribute));

        return (mc == null) ? null : new ForgeBukkitAttributeInstance(mc, attribute);
    }

    public static net.minecraft.entity.ai.attributes.Attribute toMinecraft(Attribute attribute) {
        return net.minecraft.util.registry.Registry.ATTRIBUTE.getOrDefault(ForgeBukkitNamespacedKey.toMinecraft(attribute.getKey()));
    }

    public static Attribute fromMinecraft(String mc) {
        return Registry.ATTRIBUTE.get(ForgeBukkitNamespacedKey.fromString(mc));
    }
}