package com.floweytf.forgebukkit.boss;

import com.floweytf.forgebukkit.ForgeBukkit;
import com.floweytf.forgebukkit.Wrapper;
import com.floweytf.forgebukkit.entity.ForgeBukkitEntity;
import com.floweytf.forgebukkit.entity.ForgeBukkitPlayer;
import com.floweytf.forgebukkit.util.ForgeBukkitChatMessage;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.play.server.SUpdateBossInfoPacket;
import net.minecraft.world.BossInfo;
import net.minecraft.world.server.ServerBossInfo;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarFlag;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;

import javax.annotation.Nonnull;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class ForgeBukkitBossBar extends Wrapper<ServerBossInfo> implements BossBar {
    private Map<BarFlag, FlagContainer> flags;

    public ForgeBukkitBossBar(String title, BarColor color, BarStyle style, BarFlag... flags) {
        super(null);

        super.setHandle(new ServerBossInfo(
                ForgeBukkitChatMessage.fromString(title, true)[0],
                convertColor(color),
                convertStyle(style)
        ));

        this.initialize();

        for (BarFlag flag : flags) {
            this.addFlag(flag);
        }

        this.setColor(color);
        this.setStyle(style);
    }

    public ForgeBukkitBossBar(ServerBossInfo handle) {
        super(handle);
        this.initialize();
    }

    private void initialize() {
        this.flags = new HashMap<>();
        this.flags.put(BarFlag.DARKEN_SKY, new FlagContainer(getHandle()::shouldDarkenSky, getHandle()::setDarkenSky));
        this.flags.put(BarFlag.PLAY_BOSS_MUSIC, new FlagContainer(getHandle()::shouldPlayEndBossMusic, getHandle()::setPlayEndBossMusic));
        this.flags.put(BarFlag.CREATE_FOG, new FlagContainer(getHandle()::shouldCreateFog, getHandle()::setCreateFog));
    }

    private BarColor convertColor(BossInfo.Color color) {
        BarColor bukkitColor = BarColor.valueOf(color.name());
        return (bukkitColor == null) ? BarColor.WHITE : bukkitColor;
    }

    private BossInfo.Color convertColor(BarColor color) {
        BossInfo.Color mcColor = BossInfo.Color.valueOf(color.name());
        return (mcColor == null) ? BossInfo.Color.WHITE : mcColor;
    }

    private BossInfo.Overlay convertStyle(BarStyle style) {
        switch (style) {
            default:
            case SOLID:
                return BossInfo.Overlay.PROGRESS;
            case SEGMENTED_6:
                return BossInfo.Overlay.NOTCHED_6;
            case SEGMENTED_10:
                return BossInfo.Overlay.NOTCHED_10;
            case SEGMENTED_12:
                return BossInfo.Overlay.NOTCHED_12;
            case SEGMENTED_20:
                return BossInfo.Overlay.NOTCHED_20;
        }
    }

    private BarStyle convertStyle(BossInfo.Overlay style) {
        switch (style) {
            default:
            case PROGRESS:
                return BarStyle.SOLID;
            case NOTCHED_6:
                return BarStyle.SEGMENTED_6;
            case NOTCHED_10:
                return BarStyle.SEGMENTED_10;
            case NOTCHED_12:
                return BarStyle.SEGMENTED_12;
            case NOTCHED_20:
                return BarStyle.SEGMENTED_20;
        }
    }

    @Override
    @Nonnull
    public String getTitle() {
        return ForgeBukkitChatMessage.fromComponent(getHandle().getName());
    }

    @Override
    public void setTitle(String title) {
        getHandle().setName(ForgeBukkitChatMessage.fromString(title, true)[0]);
        try {
            ForgeBukkit.sendUpdate.invoke(getHandle(), SUpdateBossInfoPacket.Operation.UPDATE_NAME);
        } catch (IllegalAccessException | InvocationTargetException e) {
            ForgeBukkit.logger.fatal("Unable to invoke", e);
        }
    }

    @Override
    @Nonnull
    public BarColor getColor() {
        return convertColor(getHandle().getColor());
    }

    @Override
    public void setColor(@Nonnull BarColor color) {
        getHandle().setColor(convertColor(color));
        try {
            ForgeBukkit.sendUpdate.invoke(getHandle(), SUpdateBossInfoPacket.Operation.UPDATE_NAME);
        } catch (IllegalAccessException | InvocationTargetException e) {
            ForgeBukkit.logger.fatal("Unable to invoke", e);
        }
    }

    @Override
    @Nonnull
    public BarStyle getStyle() {
        return convertStyle(getHandle().getOverlay());
    }

    @Override
    public void setStyle(@Nonnull BarStyle style) {
        getHandle().setOverlay(convertStyle(style));
        try {
            ForgeBukkit.sendUpdate.invoke(getHandle(), SUpdateBossInfoPacket.Operation.UPDATE_NAME);
        } catch (IllegalAccessException | InvocationTargetException e) {
            ForgeBukkit.logger.fatal("Unable to invoke", e);
        }
    }

    @Override
    public void addFlag(@Nonnull BarFlag flag) {
        FlagContainer flagContainer = flags.get(flag);
        if (flagContainer != null) {
            flagContainer.set.accept(true);
        }
    }

    @Override
    public void removeFlag(@Nonnull BarFlag flag) {
        FlagContainer flagContainer = flags.get(flag);
        if (flagContainer != null) {
            flagContainer.set.accept(false);
        }
    }

    @Override
    public boolean hasFlag(@Nonnull BarFlag flag) {
        FlagContainer flagContainer = flags.get(flag);
        if (flagContainer != null) {
            return flagContainer.get.get();
        }
        return false;
    }

    @Override
    public void setProgress(double progress) {
        Preconditions.checkArgument(progress >= 0.0 && progress <= 1.0, "Progress must be between 0.0 and 1.0 (%s)", progress);
        getHandle().setPercent((float) progress);
    }

    @Override
    public double getProgress() {
        return getHandle().getPercent();
    }

    @Override
    public void addPlayer(@Nonnull Player player) {
        Preconditions.checkArgument(player != null, "player == null");
        Preconditions.checkArgument(((ForgeBukkitPlayer) player).getHandle().connection != null, "player is not fully connected (wait for PlayerJoinEvent)");

        getHandle().addPlayer(((ForgeBukkitPlayer) player).getHandle());
    }

    @Override
    public void removePlayer(@Nonnull Player player) {
        Preconditions.checkArgument(player != null, "player == null");

        getHandle().removePlayer(((ForgeBukkitPlayer) player).getHandle());
    }

    @Override
    @Nonnull
    public List<Player> getPlayers() {
        ImmutableList.Builder<Player> players = ImmutableList.builder();
        for (ServerPlayerEntity p : getHandle().getPlayers()) {
            players.add((Player) ForgeBukkitEntity.wrap(p));
        }
        return players.build();
    }

    @Override
    public void setVisible(boolean visible) {
        getHandle().setVisible(visible);
    }

    @Override
    public boolean isVisible() {
        return getHandle().isVisible();
    }

    @Override
    public void show() {
        getHandle().setVisible(true);
    }

    @Override
    public void hide() {
        getHandle().setVisible(false);
    }

    @Override
    public void removeAll() {
        for (Player player : getPlayers()) {
            removePlayer(player);
        }
    }

    private final class FlagContainer {

        private Supplier<Boolean> get;
        private Consumer<Boolean> set;

        private FlagContainer(Supplier<Boolean> get, Consumer<Boolean> set) {
            this.get = get;
            this.set = set;
        }
    }
}