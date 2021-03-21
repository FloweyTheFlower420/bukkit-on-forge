package com.floweytf.forgebukkit;

import com.floweytf.forgebukkit.entity.impl.ForgeBukkitPlayer;
import com.floweytf.forgebukkit.metadata.EntityMetadataStore;
import com.google.common.base.Preconditions;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.server.MinecraftServer;
import org.bukkit.*;
import org.bukkit.advancement.Advancement;
import org.bukkit.block.data.BlockData;
import org.bukkit.boss.*;
import org.bukkit.command.CommandException;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.command.PluginCommand;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.help.HelpMap;
import org.bukkit.inventory.*;
import org.bukkit.loot.LootTable;
import org.bukkit.map.MapView;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.ServicesManager;
import org.bukkit.plugin.messaging.Messenger;
import org.bukkit.scheduler.BukkitScheduler;
import org.bukkit.scoreboard.ScoreboardManager;
import org.bukkit.util.CachedServerIcon;

import javax.annotation.Nonnull;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.function.Consumer;
import java.util.logging.Logger;

public class ForgeBukkitServer implements Server {
    private static final String serverName = "ForgeBukkit";
    private static final String bukkitVersion;
    private YamlConfiguration configuration;
    private final MinecraftServer server;
    private EntityMetadataStore entityMetadata;

    public static ForgeBukkitServer wrap(MinecraftServer server) {
        return new ForgeBukkitServer(server);
    }

    static {
        String result = "Unknown-Version";

        InputStream stream = Bukkit.class.getClassLoader().getResourceAsStream("META-INF/maven/org.bukkit/bukkit/pom.properties");
        Properties properties = new Properties();

        if (stream != null) {
            try {
                properties.load(stream);

                result = properties.getProperty("version");
            } catch (IOException ex) {
                ForgeBukkit.logger.info("Cannot");
            }
        }

        bukkitVersion = result;
    }

    public EntityMetadataStore getEntityMetadata() {
        return entityMetadata;
    }

    ForgeBukkitServer(MinecraftServer server) {
        this.server = server;
    }

    @Override
    @Nonnull
    public String getName() {
        return serverName;
    }

    @Override
    @Nonnull
    public String getVersion() {
        Preconditions.checkNotNull(ForgeBukkit.server, "Server has not been initialized yet");
        return ForgeBukkit.version + " (MC: " + ForgeBukkit.server.getMinecraftVersion() + ")";
    }

    @Override
    @Nonnull
    public String getBukkitVersion() {
        return bukkitVersion;
    }

    @Override
    public Collection<? extends Player> getOnlinePlayers() {
        Preconditions.checkNotNull(ForgeBukkit.server, "Server has not been initialized yet");
        Set<Player> players = new HashSet<>();
        for(ServerPlayerEntity entity : ForgeBukkit.server.getPlayerList().getPlayers())
            players.add(ForgeBukkitPlayer.wrap(entity));
        return players;
    }

    @Override
    public int getMaxPlayers() {
        Preconditions.checkNotNull(ForgeBukkit.server, "Server has not been initialized yet");
        return ForgeBukkit.server.getMaxPlayers();
    }

    @Override
    public int getPort() {
        Preconditions.checkNotNull(ForgeBukkit.server, "Server has not been initialized yet");
        return ForgeBukkit.server.getServerPort();
    }

    @Override
    public int getViewDistance() {
        Preconditions.checkNotNull(ForgeBukkit.server, "Server has not been initialized yet");
        return 10;
    }

    @Override
    @Nonnull
    public String getIp() {
        Preconditions.checkNotNull(ForgeBukkit.server, "Server has not been initialized yet");
        return ForgeBukkit.server.getServerHostname();
    }

    @Override
    public String getWorldType() {
        Preconditions.checkNotNull(ForgeBukkit.server, "Server has not been initialized yet");
        return "ASDF";
    }

    @Override
    public boolean getGenerateStructures() {
        return true;
    }

    @Override
    public int getMaxWorldSize() {
        Preconditions.checkNotNull(ForgeBukkit.server, "Server has not been initialized yet");
        return ForgeBukkit.server.getMaxWorldSize();
    }

    @Override
    public boolean getAllowEnd() {
        return true;
    }

    @Override
    public boolean getAllowNether() {
        Preconditions.checkNotNull(ForgeBukkit.server, "Server has not been initialized yet");
        return ForgeBukkit.server.getAllowNether();
    }

    @Override
    public boolean hasWhitelist() {
        Preconditions.checkNotNull(ForgeBukkit.server, "Server has not been initialized yet");
        return ForgeBukkit.server.isWhitelistEnabled();
    }

    @Override
    public void setWhitelist(boolean value) {
        Preconditions.checkNotNull(ForgeBukkit.server, "Server has not been initialized yet");
        ForgeBukkit.server.setWhitelistEnabled(value);
    }

    @Override
    public Set<OfflinePlayer> getWhitelistedPlayers() {
        return null;
    }

    @Override
    public void reloadWhitelist() {
        Preconditions.checkNotNull(ForgeBukkit.server, "Server has not been initialized yet");
        ForgeBukkit.server.getPlayerList().reloadWhitelist();
    }

    @Override
    public int broadcastMessage(String message) {
        return 0;
    }

    @Override
    public String getUpdateFolder() {
        return null;
    }

    @Override
    public File getUpdateFolderFile() {
        return null;
    }

    @Override
    public long getConnectionThrottle() {
        Preconditions.checkNotNull(ForgeBukkit.server, "Server has not been initialized yet");
        return ForgeBukkit.server.
    }

    @Override
    public int getTicksPerAnimalSpawns() {
        Preconditions.checkNotNull(ForgeBukkit.server, "Server has not been initialized yet");
        return ForgeBukkit.server.get
    }

    @Override
    public int getTicksPerMonsterSpawns() {
        return 0;
    }

    @Override
    public int getTicksPerWaterSpawns() {
        return 0;
    }

    @Override
    public int getTicksPerWaterAmbientSpawns() {
        return 0;
    }

    @Override
    public int getTicksPerAmbientSpawns() {
        return 0;
    }


    @Override
    public Player getPlayer(String name) {
        return ForgeBukkitPlayer.wrap(server.getPlayerList().getPlayerByUsername(name);
    }


    @Override
    public Player getPlayerExact(String name) {
        return null;
    }


    @Override
    public List<Player> matchPlayer(String name) {
    }


    @Override
    public Player getPlayer(UUID id) {
        return ForgeBukkitPlayer.wrap(server.getPlayerList().getPlayerByUUID(name);
    }


    @Override
    public PluginManager getPluginManager() {
        return null;
    }


    @Override
    public BukkitScheduler getScheduler() {
        return null;
    }


    @Override
    public ServicesManager getServicesManager() {
        return null;
    }


    @Override
    public List<World> getWorlds() {
        return null;
    }


    @Override
    public World createWorld(WorldCreator creator) {
        return null;
    }


    @Override
    public boolean unloadWorld(String name, boolean save) {
        return false;
    }


    @Override
    public boolean unloadWorld(World world, boolean save) {
        return false;
    }


    @Override
    public World getWorld(String name) {
        return null;
    }


    @Override
    public World getWorld(UUID uid) {
        return null;
    }


    @Override
    public MapView getMap(int id) {
        return null;
    }


    @Override
    public MapView createMap(World world) {
        return null;
    }


    @Override
    public ItemStack createExplorerMap(World world, Location location, StructureType structureType) {
        return null;
    }


    @Override
    public ItemStack createExplorerMap(World world, Location location, StructureType structureType, int radius, boolean findUnexplored) {
        return null;
    }


    @Override
    public void reload() {

    }


    @Override
    public void reloadData() {

    }


    @Override
    public Logger getLogger() {
        return null;
    }


    @Override
    public PluginCommand getPluginCommand(String name) {
        return null;
    }


    @Override
    public void savePlayers() {

    }


    @Override
    public boolean dispatchCommand(CommandSender sender, String commandLine) throws CommandException {
        return false;
    }


    @Override
    public boolean addRecipe(Recipe recipe) {
        return false;
    }


    @Override
    public List<Recipe> getRecipesFor(ItemStack result) {
        return null;
    }


    @Override
    public Recipe getRecipe(NamespacedKey recipeKey) {
        return null;
    }


    @Override
    public Iterator<Recipe> recipeIterator() {
        return null;
    }


    @Override
    public void clearRecipes() {

    }


    @Override
    public void resetRecipes() {

    }


    @Override
    public boolean removeRecipe(NamespacedKey key) {
        return false;
    }


    @Override
    public Map<String, String[]> getCommandAliases() {
        return null;
    }


    @Override
    public int getSpawnRadius() {
        return 0;
    }


    @Override
    public void setSpawnRadius(int value) {

    }


    @Override
    public boolean getOnlineMode() {
        return false;
    }


    @Override
    public boolean getAllowFlight() {
        return false;
    }


    @Override
    public boolean isHardcore() {
        return false;
    }


    @Override
    public void shutdown() {

    }


    @Override
    public int broadcast(String message, String permission) {
        return 0;
    }


    @Override
    public OfflinePlayer getOfflinePlayer(String name) {
        return null;
    }


    @Override
    public OfflinePlayer getOfflinePlayer(UUID id) {
        return null;
    }


    @Override
    public Set<String> getIPBans() {
        return null;
    }


    @Override
    public void banIP(String address) {

    }


    @Override
    public void unbanIP(String address) {

    }


    @Override
    public Set<OfflinePlayer> getBannedPlayers() {
        return null;
    }


    @Override
    public BanList getBanList(BanList.Type type) {
        return null;
    }


    @Override
    public Set<OfflinePlayer> getOperators() {
        return null;
    }


    @Override
    public GameMode getDefaultGameMode() {
        return null;
    }


    @Override
    public void setDefaultGameMode(GameMode mode) {

    }


    @Override
    public ConsoleCommandSender getConsoleSender() {
        return null;
    }


    @Override
    public File getWorldContainer() {
        return null;
    }


    @Override
    public OfflinePlayer[] getOfflinePlayers() {
        return new OfflinePlayer[0];
    }


    @Override
    public Messenger getMessenger() {
        return null;
    }


    @Override
    public HelpMap getHelpMap() {
        return null;
    }


    @Override
    public Inventory createInventory(InventoryHolder owner, InventoryType type) {
        return null;
    }


    @Override
    public Inventory createInventory(InventoryHolder owner, InventoryType type, String title) {
        return null;
    }


    @Override
    public Inventory createInventory(InventoryHolder owner, int size) throws IllegalArgumentException {
        return null;
    }


    @Override
    public Inventory createInventory(InventoryHolder owner, int size, String title) throws IllegalArgumentException {
        return null;
    }


    @Override
    public Merchant createMerchant(String title) {
        return null;
    }


    @Override
    public int getMonsterSpawnLimit() {
        return 0;
    }


    @Override
    public int getAnimalSpawnLimit() {
        return 0;
    }


    @Override
    public int getWaterAnimalSpawnLimit() {
        return 0;
    }


    @Override
    public int getWaterAmbientSpawnLimit() {
        return 0;
    }


    @Override
    public int getAmbientSpawnLimit() {
        return 0;
    }


    @Override
    public boolean isPrimaryThread() {
        return false;
    }


    @Override
    public String getMotd() {
        return null;
    }


    @Override
    public String getShutdownMessage() {
        return null;
    }


    @Override
    public Warning.WarningState getWarningState() {
        return null;
    }


    @Override
    public ItemFactory getItemFactory() {
        return null;
    }


    @Override
    public ScoreboardManager getScoreboardManager() {
        return null;
    }


    @Override
    public CachedServerIcon getServerIcon() {
        return null;
    }


    @Override
    public CachedServerIcon loadServerIcon(File file) throws IllegalArgumentException, Exception {
        return null;
    }


    @Override
    public CachedServerIcon loadServerIcon(BufferedImage image) throws IllegalArgumentException, Exception {
        return null;
    }


    @Override
    public void setIdleTimeout(int threshold) {

    }


    @Override
    public int getIdleTimeout() {
        return 0;
    }


    @Override
    public ChunkGenerator.ChunkData createChunkData(World world) {
        return null;
    }


    @Override
    public BossBar createBossBar(String title, BarColor color, BarStyle style, BarFlag... flags) {
        return null;
    }


    @Override
    public KeyedBossBar createBossBar(NamespacedKey key, String title, BarColor color, BarStyle style, BarFlag... flags) {
        return null;
    }


    @Override
    public Iterator<KeyedBossBar> getBossBars() {
        return null;
    }


    @Override
    public KeyedBossBar getBossBar(NamespacedKey key) {
        return null;
    }


    @Override
    public boolean removeBossBar(NamespacedKey key) {
        return false;
    }


    @Override
    public Entity getEntity(UUID uuid) {
        return null;
    }


    @Override
    public Advancement getAdvancement(NamespacedKey key) {
        return null;
    }


    @Override
    public Iterator<Advancement> advancementIterator() {
        return null;
    }


    @Override
    public BlockData createBlockData(Material material) {
        return null;
    }


    @Override
    public BlockData createBlockData(Material material, Consumer<BlockData> consumer) {
        return null;
    }


    @Override
    public BlockData createBlockData(String data) throws IllegalArgumentException {
        return null;
    }


    @Override
    public BlockData createBlockData(Material material, String data) throws IllegalArgumentException {
        return null;
    }


    @Override
    public <T extends Keyed> Tag<T> getTag(String registry, NamespacedKey tag, Class<T> clazz) {
        return null;
    }


    @Override
    public <T extends Keyed> Iterable<Tag<T>> getTags(String registry, Class<T> clazz) {
        return null;
    }


    @Override
    public LootTable getLootTable(NamespacedKey key) {
        return null;
    }


    @Override
    public List<Entity> selectEntities(CommandSender sender, String selector) throws IllegalArgumentException {
        return null;
    }


    @Override
    public UnsafeValues getUnsafe() {
        return null;
    }

    @Override
    public Spigot spigot() {
        return null;
    }


    @Override
    public void sendPluginMessage(Plugin source, String channel, byte[] message) {

    }


    @Override
    public Set<String> getListeningPluginChannels() {
        return null;
    }
}
