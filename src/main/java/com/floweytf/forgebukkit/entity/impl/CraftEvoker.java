package com.floweytf.forgebukkit.entity.impl;

import net.minecraft.entity.monster.EvokerEntity;
import net.minecraft.server.EntityEvoker;
import net.minecraft.server.EntityIllagerWizard;
import org.bukkit.craftbukkit.CraftServer;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Evoker;

public class CraftEvoker extends CraftSpellcaster implements Evoker {

    public CraftEvoker(CraftServer server, EvokerEntity entity) {
        super(server, entity);
    }

    @Override
    public EvokerEntity getHandle() {
        return (EvokerEntity) super.getHandle();
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
    public Evoker.Spell getCurrentSpell() {
        return Evoker.Spell.values()[getHandle().get().ordinal()];
    }

    @Override
    public void setCurrentSpell(Evoker.Spell spell) {
        getHandle().setSpell(spell == null ? EntityIllagerWizard.Spell.NONE : EntityIllagerWizard.Spell.a(spell.ordinal()));
    }
}
