package com.floweytf.forgebukkit.entity.impl;

import com.floweytf.forgebukkit.ForgeBukkitServer;
import com.floweytf.forgebukkit.block.data.ForgeBukkitBlockData;
import com.floweytf.forgebukkit.entity.ForgeBukkitMonster;
import com.floweytf.forgebukkit.util.ForgeBukkitMagicNumbers;
import net.minecraft.block.BlockState;
import net.minecraft.entity.monster.EndermanEntity;
import org.bukkit.Material;
import org.bukkit.block.data.BlockData;
import org.bukkit.entity.Enderman;
import org.bukkit.entity.EntityType;
import org.bukkit.material.MaterialData;

public class CraftEnderman extends ForgeBukkitMonster implements Enderman {
    public CraftEnderman(ForgeBukkitServer server, EndermanEntity entity) {
        super(server, entity);
    }

    @Override
    public MaterialData getCarriedMaterial() {
        BlockState blockData = getHandle().getHeldBlockState();
        return (blockData == null) ? Material.AIR.getNewData((byte) 0) : ForgeBukkitMagicNumbers.getMaterial(blockData);
    }

    @Override
    public BlockData getCarriedBlock() {
        BlockState blockData = getHandle().getHeldBlockState();
        return (blockData == null) ? null : ForgeBukkitBlockData.fromData(blockData);
    }

    @Override
    public void setCarriedMaterial(MaterialData data) {
        getHandle().setHeldBlockState(ForgeBukkitMagicNumbers.getBlock(data));
    }

    @Override
    public void setCarriedBlock(BlockData blockData) {
        getHandle().setHeldBlockState(blockData == null ? null : ((ForgeBukkitBlockData) blockData).getState());
    }

    @Override
    public EndermanEntity getHandle() {
        return (EndermanEntity) super.getHandle();
    }

    @Override
    public String toString() {
        return "ForgeBukkitEnderman";
    }

    @Override
    public EntityType getType() {
        return EntityType.ENDERMAN;
    }
}
