package com.floweytf.forgebukkit.advancement;

import com.floweytf.forgebukkit.Wrapper;
import com.google.common.collect.Lists;
import net.minecraft.advancements.CriterionProgress;
import net.minecraft.advancements.PlayerAdvancements;
import org.bukkit.advancement.Advancement;
import org.bukkit.advancement.AdvancementProgress;

import javax.annotation.Nonnull;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;

public class ForgeBukkitAdvancementProgress extends Wrapper<net.minecraft.advancements.AdvancementProgress> implements AdvancementProgress {

    private final ForgeBukkitAdvancement advancement;
    private final PlayerAdvancements playerData;

    public ForgeBukkitAdvancementProgress(ForgeBukkitAdvancement advancement, PlayerAdvancements player, net.minecraft.advancements.AdvancementProgress handle) {
        super(handle);
        this.advancement = advancement;
        this.playerData = player;
    }

    @Override
    @Nonnull
    public Advancement getAdvancement() {
        return advancement;
    }

    @Override
    public boolean isDone() {
        return getHandle().isDone();
    }

    @Override
    public boolean awardCriteria(@Nonnull String criteria) {
        return playerData.grantCriterion(advancement.getHandle(), criteria);
    }

    @Override
    public boolean revokeCriteria(@Nonnull String criteria) {
        return playerData.grantCriterion(advancement.getHandle(), criteria);
    }

    @Override
    public Date getDateAwarded(@Nonnull String criteria) {
        CriterionProgress criterion = getHandle().getCriterionProgress(criteria);
        return (criterion == null) ? null : criterion.getObtained();
    }

    @Override
    @Nonnull
    public Collection<String> getRemainingCriteria() {
        return Collections.unmodifiableCollection(Lists.newArrayList(getHandle().getRemaningCriteria()));
    }

    @Override
    @Nonnull
    public Collection<String> getAwardedCriteria() {
        return Collections.unmodifiableCollection(Lists.newArrayList(getHandle().getCompletedCriteria()));
    }
}
