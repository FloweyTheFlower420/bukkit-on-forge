package com.floweytf.forgebukkit.attribute;

import com.floweytf.forgebukkit.Wrapper;
import com.floweytf.forgebukkit.util.Converter;
import com.google.common.base.Preconditions;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import net.minecraft.entity.ai.attributes.ModifiableAttributeInstance;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.attribute.AttributeModifier;

import javax.annotation.Nonnull;

@Converter
public class ForgeBukkitAttributeInstance extends Wrapper<ModifiableAttributeInstance> implements AttributeInstance {
    private final Attribute attribute;

    public ForgeBukkitAttributeInstance(ModifiableAttributeInstance handle, Attribute attribute) {
        super(handle);
        this.attribute = attribute;
    }

    @Override
    @Nonnull
    public Attribute getAttribute() {
        return attribute;
    }

    @Override
    public double getBaseValue() {
        return getHandle().getBaseValue();
    }

    @Override
    public void setBaseValue(double d) {
        getHandle().setBaseValue(d);
    }

    @Override
    @Nonnull
    public Collection<AttributeModifier> getModifiers() {
        List<AttributeModifier> result = new ArrayList<>();
        for (net.minecraft.entity.ai.attributes.AttributeModifier mc : getHandle().getModifierListCopy()) {
            result.add(toBukkit(mc));
        }

        return result;
    }

    @Override
    public void addModifier(@Nonnull AttributeModifier modifier) {
        Preconditions.checkArgument(modifier != null, "modifier");
        getHandle().applyPersistentModifier(toMinecraft(modifier));
    }

    @Override
    public void removeModifier(@Nonnull AttributeModifier modifier) {
        Preconditions.checkArgument(modifier != null, "modifier");
        getHandle().removeModifier(toMinecraft(modifier));
    }

    @Override
    public double getValue() {
        return getHandle().getValue();
    }

    @Override
    public double getDefaultValue() {
        return getHandle().getAttribute().getDefaultValue();
    }

    public static net.minecraft.entity.ai.attributes.AttributeModifier toMinecraft(AttributeModifier bukkit) {
        return new net.minecraft.entity.ai.attributes.AttributeModifier(bukkit.getUniqueId(), bukkit.getName(), bukkit.getAmount(), net.minecraft.entity.ai.attributes.AttributeModifier.Operation.values()[bukkit.getOperation().ordinal()]);
    }

    public AttributeModifier toBukkit(net.minecraft.entity.ai.attributes.AttributeModifier minecraft) {
        return new AttributeModifier(minecraft.getID(), minecraft.getName(), minecraft.getAmount(), AttributeModifier.Operation.values()[minecraft.getOperation().ordinal()]);
    }
}