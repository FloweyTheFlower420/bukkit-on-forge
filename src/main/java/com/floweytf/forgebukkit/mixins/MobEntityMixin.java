package com.floweytf.forgebukkit.mixins;

import net.minecraft.entity.MobEntity;
import net.minecraft.util.ResourceLocation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(MobEntity.class)
public interface MobEntityMixin {
    @Accessor("deathLootTableSeed")
    public long getLootTableSeed();

    @Accessor("deathLootTableSeed")
    public void setLootTableSeed(long s);

    @Accessor("lootTableKey")
    public void setLootTableKey(ResourceLocation location);

}
