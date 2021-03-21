package com.floweytf.forgebukkit.entity;

import com.floweytf.forgebukkit.ForgeBukkitServer;
import net.minecraft.entity.monster.MonsterEntity;
import org.bukkit.entity.Monster;

public class ForgeBukkitMonster extends ForgeBukkitCreature implements Monster {

    public ForgeBukkitMonster(ForgeBukkitServer server, MonsterEntity entity) {
        super(server, entity);
    }

    @Override
    public MonsterEntity getHandle() {
        return (MonsterEntity) super.getHandle();
    }

    @Override
    public String toString() {
        return "ForgeBukkitMonster";
    }
}
