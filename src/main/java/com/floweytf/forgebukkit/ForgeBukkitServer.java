package com.floweytf.forgebukkit;

import com.floweytf.forgebukkit.command.ForgeBukkitCommandMap;
import com.floweytf.forgebukkit.entity.ForgeBukkitEntity;
import com.floweytf.forgebukkit.entity.impl.ForgeBukkitPlayer;
import com.floweytf.forgebukkit.inventory.crafting.*;
import com.floweytf.forgebukkit.potion.ForgeBukkitPotionBrewer;
import com.floweytf.forgebukkit.scheduler.ForgeBukkitScheduler;
import com.floweytf.forgebukkit.util.Utils;
import com.google.common.base.Charsets;
import com.google.common.base.Preconditions;
import com.google.common.collect.MapMaker;
import com.mojang.authlib.GameProfile;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.potion.Effects;
import net.minecraft.server.dedicated.DedicatedServer;
import net.minecraft.server.management.PlayerList;
import net.minecraft.server.management.UserListEntry;
import org.apache.commons.lang.Validate;
import org.bukkit.*;
import org.bukkit.advancement.Advancement;
import org.bukkit.block.data.BlockData;
import org.bukkit.boss.*;
import org.bukkit.command.CommandException;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.command.PluginCommand;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.configuration.serialization.ConfigurationSerialization;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.help.HelpMap;
import org.bukkit.inventory.*;
import org.bukkit.loot.LootTable;
import org.bukkit.map.MapView;
import org.bukkit.plugin.*;
import org.bukkit.plugin.messaging.Messenger;
import org.bukkit.potion.Potion;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitScheduler;
import org.bukkit.scoreboard.ScoreboardManager;
import org.bukkit.util.CachedServerIcon;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.*;
import java.util.function.Consumer;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class ForgeBukkitServer extends Wrapper<DedicatedServer> implements Server {
    public static String bukkitVersion = Utils.getBukkitVersion();
    private final PlayerList playerList;
    private final List<ForgeBukkitPlayer> playerView;
    private YamlConfiguration configuration;
    private final Map<UUID, OfflinePlayer> offlinePlayers = new MapMaker().weakValues().makeMap();
    private final ForgeBukkitCommandMap commandMap = new ForgeBukkitCommandMap(this);
    private final SimplePluginManager pluginManager = new SimplePluginManager(this, commandMap);
    private final ForgeBukkitScheduler scheduler = new ForgeBukkitScheduler();
    private final ServicesManager servicesManager = new SimpleServicesManager();

    static {
        ConfigurationSerialization.registerClass(ForgeBukkitOfflinePlayer.class);
        // CraftItemFactory.instance();
    }

    public PlayerList getPlayerList() {
        return playerList;
    }

    protected ForgeBukkitServer(DedicatedServer handle, PlayerList list) {
        super(handle);
        playerList = list;
        playerView = Collections.unmodifiableList(playerList.getPlayers().stream().map(player -> (ForgeBukkitPlayer) ForgeBukkitEntity.wrap(player)).collect(Collectors.toList()));
        Bukkit.setServer(this);
        Enchantments.SHARPNESS.getClass();
        org.bukkit.enchantments.Enchantment.stopAcceptingRegistrations();

        Potion.setPotionBrewer(new ForgeBukkitPotionBrewer());
        Effects.BLINDNESS.getClass();
        PotionEffectType.stopAcceptingRegistrations();
    }

    @NotNull
    @Override
    public String getName() {
        return getHandle().getName();
    }

    @NotNull
    @Override
    public String getVersion() {
        return ForgeBukkit.version + " (MC: " + getHandle().getMinecraftVersion() + ")";
    }

    @NotNull
    @Override
    public String getBukkitVersion() {
        return bukkitVersion;
    }

    @NotNull
    @Override
    public Collection<? extends Player> getOnlinePlayers() {
        return playerView;
    }

    @Override
    public int getMaxPlayers() {
        return playerList.getMaxPlayers();
    }

    @Override
    public int getPort() {
        return getHandle().getServerPort();
    }

    @Override
    public int getViewDistance() {
        return getHandle().getServerProperties().viewDistance;
    }

    @NotNull
    @Override
    public String getIp() {
        return getHandle().getServerProperties().serverIp;
    }

    @NotNull
    @Override
    public String getWorldType() {
        return getHandle().getServerProperties().serverProperties.getProperty("level-type");
    }

    @Override
    public boolean getGenerateStructures() {
        return this.getHandle().getServerProperties().field_241082_U_.doesGenerateFeatures();
    }

    @Override
    public int getMaxWorldSize() {
        return getHandle().getServerProperties().maxWorldSize;
    }

    public boolean getAllowEnd() {
        return configuration.getBoolean("settings.allow-end");
    }

    @Override
    public boolean getAllowNether() {
        return getHandle().getAllowNether();
    }

    @Override
    public boolean hasWhitelist() {
        return getHandle().isWhitelistEnabled();
    }

    @Override
    public void setWhitelist(boolean value) {
        getHandle().setWhitelistEnabled(value);
    }

    @NotNull
    @Override
    public Set<OfflinePlayer> getWhitelistedPlayers() {
        // why not hashset???
        Set<OfflinePlayer> result = new HashSet<>();

        for (UserListEntry entry : playerList.getWhitelistedPlayers().values.values())
            result.add(getOfflinePlayer((GameProfile) entry.getValue()));

        return result;
    }

    public OfflinePlayer getOfflinePlayer(GameProfile profile) {
        OfflinePlayer player = new ForgeBukkitOfflinePlayer(this, profile);
        offlinePlayers.put(profile.getId(), player);
        return player;
    }

    @Override
    public void reloadWhitelist() {
        playerList.reloadWhitelist();
    }

    @Override
    public int broadcastMessage(@NotNull String message) {
        return broadcast(message, BROADCAST_CHANNEL_USERS);
    }

    @NotNull
    @Override
    public String getUpdateFolder() {
        return this.configuration.getString("settings.update-folder", "update");
    }

    @NotNull
    @Override
    public File getUpdateFolderFile() {
        return new File((File) getHandle().options.valueOf("plugins"), this.configuration.getString("settings.update-folder", "update"));
    }

    @Override
    public long getConnectionThrottle() {
        return this.configuration.getInt("settings.connection-throttle");
    }

    @Override
    public int getTicksPerAnimalSpawns() {
        return this.configuration.getInt("ticks-per.animal-spawns");
    }

    @Override
    public int getTicksPerMonsterSpawns() {
        return this.configuration.getInt("ticks-per.monster-spawns");
    }

    @Override
    public int getTicksPerWaterSpawns() {
        return this.configuration.getInt("ticks-per.water-spawns");
    }

    @Override
    public int getTicksPerWaterAmbientSpawns() {
        return this.configuration.getInt("ticks-per.water-ambient-spawns");
    }

    @Override
    public int getTicksPerAmbientSpawns() {
        return this.configuration.getInt("ticks-per.ambient-spawns");
    }

    @Override
    @Deprecated
    public Player getPlayer(final String name) {
        Validate.notNull(name, "Name cannot be null");

        Player found = getPlayerExact(name);
        if (found != null)
            return found;

        String lowerName = name.toLowerCase(java.util.Locale.ENGLISH);
        int delta = Integer.MAX_VALUE;
        for (Player player : getOnlinePlayers()) {
            if (player.getName().toLowerCase(java.util.Locale.ENGLISH).startsWith(lowerName)) {
                int curDelta = Math.abs(player.getName().length() - lowerName.length());
                if (curDelta < delta) {
                    found = player;
                    delta = curDelta;
                }
                if (curDelta == 0) break;
            }
        }
        return found;
    }

    @Nullable
    @Override
    public Player getPlayer(@NotNull UUID id) {
        Preconditions.checkNotNull(id);
        ServerPlayerEntity player = playerList.getPlayerByUUID(id);

        return (player != null) ? (Player) ForgeBukkitEntity.wrap(player) : null;
    }

    @Nullable
    @Override
    @Deprecated
    public Player getPlayerExact(@NotNull String name) {
        Preconditions.checkNotNull(name);
        ServerPlayerEntity player = playerList.getPlayerByUsername(name);

        return (player != null) ? (Player) ForgeBukkitEntity.wrap(player) : null;
    }

    @NotNull
    @Override
    public List<Player> matchPlayer(@NotNull String name) {
        Preconditions.checkNotNull(name);

        List<Player> matchedPlayers = new ArrayList<>();

        for (Player iterPlayer : this.getOnlinePlayers()) {
            String iterPlayerName = iterPlayer.getName();

            if (name.equalsIgnoreCase(iterPlayerName)) {
                matchedPlayers.clear();
                matchedPlayers.add(iterPlayer);
                break;
            }
            if (iterPlayerName.toLowerCase(java.util.Locale.ENGLISH).contains(name.toLowerCase(java.util.Locale.ENGLISH)))
                matchedPlayers.add(iterPlayer);
        }

        return matchedPlayers;
    }

    @NotNull
    @Override
    public PluginManager getPluginManager() {
        return pluginManager;
    }

    @NotNull
    @Override
    public BukkitScheduler getScheduler() {
        return scheduler;
    }

    @NotNull
    @Override
    public ServicesManager getServicesManager() {
        return servicesManager;
    }

    @NotNull
    @Override
    public List<World> getWorlds() {
        return null;
    }

    /**
     * Creates or loads a world with the given name using the specified
     * options.
     * <p>
     * If the world is already loaded, it will just return the equivalent of
     * getWorld(creator.name()).
     *
     * @param creator the options to use when creating the world
     * @return newly created or loaded world
     */
    @Nullable
    @Override
    public World createWorld(@NotNull WorldCreator creator) {
        return null;
    }

    /**
     * Unloads a world with the given name.
     *
     * @param name Name of the world to unload
     * @param save whether to save the chunks before unloading
     * @return true if successful, false otherwise
     */
    @Override
    public boolean unloadWorld(@NotNull String name, boolean save) {
        return false;
    }

    /**
     * Unloads the given world.
     *
     * @param world the world to unload
     * @param save  whether to save the chunks before unloading
     * @return true if successful, false otherwise
     */
    @Override
    public boolean unloadWorld(@NotNull World world, boolean save) {
        return false;
    }

    /**
     * Gets the world with the given name.
     *
     * @param name the name of the world to retrieve
     * @return a world with the given name, or null if none exists
     */
    @Nullable
    @Override
    public World getWorld(@NotNull String name) {
        return null;
    }

    /**
     * Gets the world from the given Unique ID.
     *
     * @param uid a unique-id of the world to retrieve
     * @return a world with the given Unique ID, or null if none exists
     */
    @Nullable
    @Override
    public World getWorld(@NotNull UUID uid) {
        return null;
    }

    /**
     * Gets the map from the given item ID.
     *
     * @param id the id of the map to get
     * @return a map view if it exists, or null otherwise
     * @deprecated Magic value
     */
    @Nullable
    @Override
    public MapView getMap(int id) {
        return null;
    }

    /**
     * Create a new map with an automatically assigned ID.
     *
     * @param world the world the map will belong to
     * @return a newly created map view
     */
    @NotNull
    @Override
    public MapView createMap(@NotNull World world) {
        return null;
    }

    /**
     * Create a new explorer map targeting the closest nearby structure of a
     * given {@link StructureType}.
     * <br>
     * This method uses implementation default values for radius and
     * findUnexplored (usually 100, true).
     *
     * @param world         the world the map will belong to
     * @param location      the origin location to find the nearest structure
     * @param structureType the type of structure to find
     * @return a newly created item stack
     * @see World#locateNearestStructure(Location,
     * StructureType, int, boolean)
     */
    @NotNull
    @Override
    public ItemStack createExplorerMap(@NotNull World world, @NotNull Location location, @NotNull StructureType structureType) {
        return null;
    }

    /**
     * Create a new explorer map targeting the closest nearby structure of a
     * given {@link StructureType}.
     * <br>
     * This method uses implementation default values for radius and
     * findUnexplored (usually 100, true).
     *
     * @param world          the world the map will belong to
     * @param location       the origin location to find the nearest structure
     * @param structureType  the type of structure to find
     * @param radius         radius to search, see World#locateNearestStructure for more
     *                       information
     * @param findUnexplored whether to find unexplored structures
     * @return the newly created item stack
     * @see World#locateNearestStructure(Location,
     * StructureType, int, boolean)
     */
    @NotNull
    @Override
    public ItemStack createExplorerMap(@NotNull World world, @NotNull Location location, @NotNull StructureType structureType, int radius, boolean findUnexplored) {
        return null;
    }

    /**
     * Reloads the server, refreshing settings and plugin information.
     */
    @Override
    public void reload() {

    }

    /**
     * Reload only the Minecraft data for the server. This includes custom
     * advancements and loot tables.
     */
    @Override
    public void reloadData() {

    }

    /**
     * Returns the primary logger associated with this server instance.
     *
     * @return Logger associated with this server
     */
    @NotNull
    @Override
    public Logger getLogger() {
        return null;
    }

    /**
     * Gets a {@link PluginCommand} with the given name or alias.
     *
     * @param name the name of the command to retrieve
     * @return a plugin command if found, null otherwise
     */
    @Nullable
    @Override
    public PluginCommand getPluginCommand(@NotNull String name) {
        return null;
    }

    /**
     * Writes loaded players to disk.
     */
    @Override
    public void savePlayers() {
        checkSaveState();
        playerList.saveAllPlayerData();
    }

    public void checkSaveState() {
        if (this.playerCommandState || this.printSaveWarning || this.console.autosavePeriod <= 0) {
            return;
        }
        this.printSaveWarning = true;
        getLogger().log(Level.WARNING, "A manual (plugin-induced) save has been detected while server is configured to auto-save. This may affect performance.", warningState == Warning.WarningState.ON ? new Throwable() : null);
    }

    /**
     * Dispatches a command on this server, and executes it if found.
     *
     * @param sender      the apparent sender of the command
     * @param commandLine the command + arguments. Example: <code>test abc
     *                    123</code>
     * @return returns false if no target is found
     * @throws CommandException thrown when the executor for the given command
     *                          fails with an unhandled exception
     */
    @Override
    public boolean dispatchCommand(@NotNull CommandSender sender, @NotNull String commandLine) throws CommandException {
        return false;
    }

    /**
     * Adds a recipe to the crafting manager.
     *
     * @param recipe the recipe to add
     * @return true if the recipe was added, false if it wasn't for some
     * reason
     */
    @Override
    public boolean addRecipe(Recipe recipe) {
        CraftRecipe toAdd;
        if (recipe instanceof CraftRecipe)
            toAdd = (CraftRecipe) recipe;
        else {
            if (recipe instanceof ShapedRecipe)
                toAdd = CraftShapedRecipe.fromBukkitRecipe((ShapedRecipe) recipe);
            else if (recipe instanceof ShapelessRecipe)
                toAdd = CraftShapelessRecipe.fromBukkitRecipe((ShapelessRecipe) recipe);
            else if (recipe instanceof FurnaceRecipe)
                toAdd = CraftFurnaceRecipe.fromBukkitRecipe((FurnaceRecipe) recipe);
            else if (recipe instanceof BlastingRecipe)
                toAdd = CraftBlastingRecipe.fromBukkitRecipe((BlastingRecipe) recipe);
            else if (recipe instanceof CampfireRecipe)
                toAdd = CraftCampfireRecipe.fromBukkitRecipe((CampfireRecipe) recipe);
            else if (recipe instanceof SmokingRecipe)
                toAdd = CraftSmokingRecipe.fromBukkitRecipe((SmokingRecipe) recipe);
            else if (recipe instanceof StonecuttingRecipe)
                toAdd = CraftStonecuttingRecipe.fromBukkitRecipe((StonecuttingRecipe) recipe);
            else if (recipe instanceof SmithingRecipe)
                toAdd = CraftSmithingRecipe.fromBukkitRecipe((SmithingRecipe) recipe);
            else if (recipe instanceof ComplexRecipe)
                throw new UnsupportedOperationException("Cannot add custom complex recipe");
            else
                return false;
        }
        toAdd.addToCraftingManager();
        return true;
    }

    /**
     * Get a list of all recipes for a given item. The stack size is ignored
     * in comparisons. If the durability is -1, it will match any data value.
     *
     * @param result the item to match against recipe results
     * @return a list of recipes with the given result
     */
    @NotNull
    @Override
    public List<Recipe> getRecipesFor(@NotNull ItemStack result) {
        return null;
    }

    /**
     * Get the {@link Recipe} for the given key.
     *
     * @param recipeKey the key of the recipe to return
     * @return the recipe for the given key or null.
     */
    @Nullable
    @Override
    public Recipe getRecipe(@NotNull NamespacedKey recipeKey) {
        return null;
    }

    /**
     * Get an iterator through the list of crafting recipes.
     *
     * @return an iterator
     */
    @NotNull
    @Override
    public Iterator<Recipe> recipeIterator() {
        return null;
    }

    /**
     * Clears the list of crafting recipes.
     */
    @Override
    public void clearRecipes() {

    }

    /**
     * Resets the list of crafting recipes to the default.
     */
    @Override
    public void resetRecipes() {

    }

    /**
     * Remove a recipe from the server.
     *
     * <b>Note that removing a recipe may cause permanent loss of data
     * associated with that recipe (eg whether it has been discovered by
     * players).</b>
     *
     * @param key NamespacedKey of recipe to remove.
     * @return True if recipe was removed
     */
    @Override
    public boolean removeRecipe(@NotNull NamespacedKey key) {
        return false;
    }

    /**
     * Gets a list of command aliases defined in the server properties.
     *
     * @return a map of aliases to command names
     */
    @NotNull
    @Override
    public Map<String, String[]> getCommandAliases() {
        return null;
    }

    /**
     * Gets the radius, in blocks, around each worlds spawn point to protect.
     *
     * @return spawn radius, or 0 if none
     */
    @Override
    public int getSpawnRadius() {
        return 0;
    }

    /**
     * Sets the radius, in blocks, around each worlds spawn point to protect.
     *
     * @param value new spawn radius, or 0 if none
     */
    @Override
    public void setSpawnRadius(int value) {

    }

    /**
     * Gets whether the Server is in online mode or not.
     *
     * @return true if the server authenticates clients, false otherwise
     */
    @Override
    public boolean getOnlineMode() {
        return false;
    }

    /**
     * Gets whether this server allows flying or not.
     *
     * @return true if the server allows flight, false otherwise
     */
    @Override
    public boolean getAllowFlight() {
        return false;
    }

    /**
     * Gets whether the server is in hardcore mode or not.
     *
     * @return true if the server mode is hardcore, false otherwise
     */
    @Override
    public boolean isHardcore() {
        return false;
    }

    /**
     * Shutdowns the server, stopping everything.
     */
    @Override
    public void shutdown() {

    }

    /**
     * Broadcasts the specified message to every user with the given
     * permission name.
     *
     * @param message    message to broadcast
     * @param permission the required permission {@link Permissible
     *                   permissibles} must have to receive the broadcast
     * @return number of message recipients
     */
    @Override
    public int broadcast(@NotNull String message, @NotNull String permission) {
        return 0;
    }

    /**
     * Gets the player by the given name, regardless if they are offline or
     * online.
     * <p>
     * This method may involve a blocking web request to get the UUID for the
     * given name.
     * <p>
     * This will return an object even if the player does not exist. To this
     * method, all players will exist.
     *
     * @param name the name the player to retrieve
     * @return an offline player
     * @see #getOfflinePlayer(UUID)
     * @deprecated Persistent storage of users should be by UUID as names are no longer
     * unique past a single session.
     */
    @NotNull
    @Override
    public OfflinePlayer getOfflinePlayer(String name) {
        Validate.notNull(name, "Name cannot be null");
        Validate.notEmpty(name, "Name cannot be empty");

        OfflinePlayer result = getPlayerExact(name);
        if (result == null) {
            GameProfile profile = getHandle().getPlayerProfileCache().getGameProfileForUsername(name);

            if (profile == null)
                result = getOfflinePlayer(new GameProfile(UUID.nameUUIDFromBytes(("OfflinePlayer:" + name).getBytes(Charsets.UTF_8)), name));
            else
                result = getOfflinePlayer(profile);
        }
        else
            offlinePlayers.remove(result.getUniqueId());

        return result;
    }

    @NotNull
    @Override
    public OfflinePlayer getOfflinePlayer(UUID id) {
        Validate.notNull(id, "UUID cannot be null");

        OfflinePlayer result = getPlayer(id);
        if (result == null) {
            result = offlinePlayers.get(id);

            if (result == null) {
                result = new ForgeBukkitOfflinePlayer(this, new GameProfile(id, null));
                offlinePlayers.put(id, result);
            }
        }
        else
            offlinePlayers.remove(id);

        return result;
    }

    @NotNull
    @Override
    public Set<String> getIPBans() {
        return null;
    }

    @Override
    public void banIP(@NotNull String address) {

    }

    @Override
    public void unbanIP(@NotNull String address) {

    }

    @NotNull
    @Override
    public Set<OfflinePlayer> getBannedPlayers() {
        return null;
    }

    @NotNull
    @Override
    public BanList getBanList(@NotNull BanList.Type type) {
        return null;
    }

    @NotNull
    @Override
    public Set<OfflinePlayer> getOperators() {
        return null;
    }

    @NotNull
    @Override
    public GameMode getDefaultGameMode() {
        return null;
    }

    @Override
    public void setDefaultGameMode(@NotNull GameMode mode) {

    }

    @NotNull
    @Override
    public ConsoleCommandSender getConsoleSender() {
        return null;
    }

    @NotNull
    @Override
    public File getWorldContainer() {
        return null;
    }

    @NotNull
    @Override
    public OfflinePlayer[] getOfflinePlayers() {
        return new OfflinePlayer[0];
    }

    @NotNull
    @Override
    public Messenger getMessenger() {
        return null;
    }

    @NotNull
    @Override
    public HelpMap getHelpMap() {
        return null;
    }

    @NotNull
    @Override
    public Inventory createInventory(@Nullable InventoryHolder owner, @NotNull InventoryType type) {
        return null;
    }

    @NotNull
    @Override
    public Inventory createInventory(@Nullable InventoryHolder owner, @NotNull InventoryType type, @NotNull String title) {
        return null;
    }

    @NotNull
    @Override
    public Inventory createInventory(@Nullable InventoryHolder owner, int size) throws IllegalArgumentException {
        return null;
    }

    @NotNull
    @Override
    public Inventory createInventory(@Nullable InventoryHolder owner, int size, @NotNull String title) throws IllegalArgumentException {
        return null;
    }

    @NotNull
    @Override
    public Merchant createMerchant(@Nullable String title) {
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

    @NotNull
    @Override
    public String getMotd() {
        return null;
    }

    @Nullable
    @Override
    public String getShutdownMessage() {
        return null;
    }

    @NotNull
    @Override
    public Warning.WarningState getWarningState() {
        return null;
    }

    @NotNull
    @Override
    public ItemFactory getItemFactory() {
        return null;
    }

    @Nullable
    @Override
    public ScoreboardManager getScoreboardManager() {
        return null;
    }

    @Nullable
    @Override
    public CachedServerIcon getServerIcon() {
        return null;
    }

    @NotNull
    @Override
    public CachedServerIcon loadServerIcon(@NotNull File file) throws IllegalArgumentException, Exception {
        return null;
    }

    @NotNull
    @Override
    public CachedServerIcon loadServerIcon(@NotNull BufferedImage image) throws IllegalArgumentException, Exception {
        return null;
    }

    @Override
    public void setIdleTimeout(int threshold) {

    }

    @Override
    public int getIdleTimeout() {
        return 0;
    }

    @NotNull
    @Override
    public ChunkGenerator.ChunkData createChunkData(@NotNull World world) {
        return null;
    }

    @NotNull
    @Override
    public BossBar createBossBar(@Nullable String title, @NotNull BarColor color, @NotNull BarStyle style, @NotNull BarFlag... flags) {
        return null;
    }

    @NotNull
    @Override
    public KeyedBossBar createBossBar(@NotNull NamespacedKey key, @Nullable String title, @NotNull BarColor color, @NotNull BarStyle style, @NotNull BarFlag... flags) {
        return null;
    }

    @NotNull
    @Override
    public Iterator<KeyedBossBar> getBossBars() {
        return null;
    }

    @Nullable
    @Override
    public KeyedBossBar getBossBar(@NotNull NamespacedKey key) {
        return null;
    }

    @Override
    public boolean removeBossBar(@NotNull NamespacedKey key) {
        return false;
    }

    @Nullable
    @Override
    public Entity getEntity(@NotNull UUID uuid) {
        return null;
    }

    @Nullable
    @Override
    public Advancement getAdvancement(@NotNull NamespacedKey key) {
        return null;
    }

    @NotNull
    @Override
    public Iterator<Advancement> advancementIterator() {
        return null;
    }

    @NotNull
    @Override
    public BlockData createBlockData(@NotNull Material material) {
        return null;
    }

    @NotNull
    @Override
    public BlockData createBlockData(@NotNull Material material, @Nullable Consumer<BlockData> consumer) {
        return null;
    }

    @NotNull
    @Override
    public BlockData createBlockData(@NotNull String data) throws IllegalArgumentException {
        return null;
    }

    @NotNull
    @Override
    public BlockData createBlockData(@Nullable Material material, @Nullable String data) throws IllegalArgumentException {
        return null;
    }

    @Nullable
    @Override
    public <T extends Keyed> Tag<T> getTag(@NotNull String registry, @NotNull NamespacedKey tag, @NotNull Class<T> clazz) {
        return null;
    }

    @NotNull
    @Override
    public <T extends Keyed> Iterable<Tag<T>> getTags(@NotNull String registry, @NotNull Class<T> clazz) {
        return null;
    }

    @Nullable
    @Override
    public LootTable getLootTable(@NotNull NamespacedKey key) {
        return null;
    }

    @NotNull
    @Override
    public List<Entity> selectEntities(@NotNull CommandSender sender, @NotNull String selector) throws IllegalArgumentException {
        return null;
    }

    @NotNull
    @Override
    public UnsafeValues getUnsafe() {
        return null;
    }

    @NotNull
    @Override
    public Spigot spigot() {
        return null;
    }

    @Override
    public void sendPluginMessage(@NotNull Plugin source, @NotNull String channel, @NotNull byte[] message) {

    }

    @NotNull
    @Override
    public Set<String> getListeningPluginChannels() {
        return null;
    }
}