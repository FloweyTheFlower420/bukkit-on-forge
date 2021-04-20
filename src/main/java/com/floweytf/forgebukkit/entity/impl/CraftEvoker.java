package com.floweytf.forgebukkit.entity.impl;

import net.minecraft.server.EntityEvoker;
import net.minecraft.server.EntityIllagerWizard;
import org.bukkit.craftbukkit.CraftServer;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Evoker;

public class CraftEvoker extends CraftSpellcaster implements Evoker {

    public CraftEvoker(CraftServer server, EntityEvoker entity) {
        super(server, entity);
    }

    @Override
    public EntityEvoker getHandle() {
        return (EntityEvoker) super.getHandle();
    }

    @Override
    public String toString() {
        return "CraftEvoker";
    }

    @Override
    public EntityType getType() {
        return EntityType.EVOKER;
    }

    @Override
    public Spell getCurrentSpell() {
        return Spell.values()[getHandle().getSpell().ordinal()];
    }

    @Override
    public void setCurrentSpell(Spell spell) {
        getHandle().setSpell(spell == null ? EntityIllagerWizard.Spell.NONE : EntityIllagerWizard.Spell.a(spell.ordinal()));
    }
}
