package com.floweytf.forgebukkit;

import com.floweytf.forgebukkit.entity.ForgeBukkitPlayer;
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
import org.bukkit.entity.Boss;
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

    static {
        String result = "Unknown-Version";

        InputStream stream = Bukkit.class.getClassLoader().getResourceAsStream("META-INF/maven/org.bukkit/bukkit/pom.properties");
        Properties properties = new Properties();

        if (stream != null) {
            try {
                properties.load(stream);

                result = properties.getProperty("version");
            } catch (IOException ex) {
                ForgeBukkit.LOGGER.info("Cannot");
            }
        }

        bukkitVersion = result;
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

    /**
     * Gets a player object by the given username.
     * <p>
     * This method may not return objects for offline players.
     *
     * @param name the name to look up
     * @return a player if one was found, null otherwise
     */
    @Override
    public Player getPlayer(String name) {
        return ForgeBukkitPlayer.wrap(server.getPlayerList().getPlayerByUsername(name);
    }

    /**
     * Gets the player with the exact given name, case insensitive.
     *
     * @param name Exact name of the player to retrieve
     * @return a player object if one was found, null otherwise
     */
    @Override
    public Player getPlayerExact(String name) {
        return null;
    }

    /**
     * Attempts to match any players with the given name, and returns a list
     * of all possibly matches.
     * <p>
     * This list is not sorted in any particular order. If an exact match is
     * found, the returned list will only contain a single result.
     *
     * @param name the (partial) name to match
     * @return list of all possible players
     */
    @Override
    public List<Player> matchPlayer(String name) {
    }

    /**
     * Gets the player with the given UUID.
     *
     * @param id UUID of the player to retrieve
     * @return a player object if one was found, null otherwise
     */
    @Override
    public Player getPlayer(UUID id) {
        return ForgeBukkitPlayer.wrap(server.getPlayerList().getPlayerByUUID(name);
    }

    /**
     * Gets the plugin manager for interfacing with plugins.
     *
     * @return a plugin manager for this Server instance
     */
    @Override
    public PluginManager getPluginManager() {
        return null;
    }

    /**
     * Gets the scheduler for managing scheduled events.
     *
     * @return a scheduling service for this server
     */
    @Override
    public BukkitScheduler getScheduler() {
        return null;
    }

    /**
     * Gets a services manager.
     *
     * @return s services manager
     */
    @Override
    public ServicesManager getServicesManager() {
        return null;
    }

    /**
     * Gets a list of all worlds on this server.
     *
     * @return a list of worlds
     */
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
    @Override
    public World createWorld(WorldCreator creator) {
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
    public boolean unloadWorld(String name, boolean save) {
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
    public boolean unloadWorld(World world, boolean save) {
        return false;
    }

    /**
     * Gets the world with the given name.
     *
     * @param name the name of the world to retrieve
     * @return a world with the given name, or null if none exists
     */
    @Override
    public World getWorld(String name) {
        return null;
    }

    /**
     * Gets the world from the given Unique ID.
     *
     * @param uid a unique-id of the world to retrieve
     * @return a world with the given Unique ID, or null if none exists
     */
    @Override
    public World getWorld(UUID uid) {
        return null;
    }

    /**
     * Gets the map from the given item ID.
     *
     * @param id the id of the map to get
     * @return a map view if it exists, or null otherwise
     * @deprecated Magic value
     */
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
    @Override
    public MapView createMap(World world) {
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
    @Override
    public ItemStack createExplorerMap(World world, Location location, StructureType structureType) {
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
    @Override
    public ItemStack createExplorerMap(World world, Location location, StructureType structureType, int radius, boolean findUnexplored) {
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
    @Override
    public PluginCommand getPluginCommand(String name) {
        return null;
    }

    /**
     * Writes loaded players to disk.
     */
    @Override
    public void savePlayers() {

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
    public boolean dispatchCommand(CommandSender sender, String commandLine) throws CommandException {
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
        return false;
    }

    /**
     * Get a list of all recipes for a given item. The stack size is ignored
     * in comparisons. If the durability is -1, it will match any data value.
     *
     * @param result the item to match against recipe results
     * @return a list of recipes with the given result
     */
    @Override
    public List<Recipe> getRecipesFor(ItemStack result) {
        return null;
    }

    /**
     * Get the {@link Recipe} for the given key.
     *
     * @param recipeKey the key of the recipe to return
     * @return the recipe for the given key or null.
     */
    @Override
    public Recipe getRecipe(NamespacedKey recipeKey) {
        return null;
    }

    /**
     * Get an iterator through the list of crafting recipes.
     *
     * @return an iterator
     */
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
    public boolean removeRecipe(NamespacedKey key) {
        return false;
    }

    /**
     * Gets a list of command aliases defined in the server properties.
     *
     * @return a map of aliases to command names
     */
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
    public int broadcast(String message, String permission) {
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
    @Override
    public OfflinePlayer getOfflinePlayer(String name) {
        return null;
    }

    /**
     * Gets the player by the given UUID, regardless if they are offline or
     * online.
     * <p>
     * This will return an object even if the player does not exist. To this
     * method, all players will exist.
     *
     * @param id the UUID of the player to retrieve
     * @return an offline player
     */
    @Override
    public OfflinePlayer getOfflinePlayer(UUID id) {
        return null;
    }

    /**
     * Gets a set containing all current IPs that are banned.
     *
     * @return a set containing banned IP addresses
     */
    @Override
    public Set<String> getIPBans() {
        return null;
    }

    /**
     * Bans the specified address from the server.
     *
     * @param address the IP address to ban
     */
    @Override
    public void banIP(String address) {

    }

    /**
     * Unbans the specified address from the server.
     *
     * @param address the IP address to unban
     */
    @Override
    public void unbanIP(String address) {

    }

    /**
     * Gets a set containing all banned players.
     *
     * @return a set containing banned players
     */
    @Override
    public Set<OfflinePlayer> getBannedPlayers() {
        return null;
    }

    /**
     * Gets a ban list for the supplied type.
     * <p>
     * Bans by name are no longer supported and this method will return
     * null when trying to request them. The replacement is bans by UUID.
     *
     * @param type the type of list to fetch, cannot be null
     * @return a ban list of the specified type
     */
    @Override
    public BanList getBanList(BanList.Type type) {
        return null;
    }

    /**
     * Gets a set containing all player operators.
     *
     * @return a set containing player operators
     */
    @Override
    public Set<OfflinePlayer> getOperators() {
        return null;
    }

    /**
     * Gets the default {@link GameMode} for new players.
     *
     * @return the default game mode
     */
    @Override
    public GameMode getDefaultGameMode() {
        return null;
    }

    /**
     * Sets the default {@link GameMode} for new players.
     *
     * @param mode the new game mode
     */
    @Override
    public void setDefaultGameMode(GameMode mode) {

    }

    /**
     * Gets a {@link ConsoleCommandSender} that may be used as an input source
     * for this server.
     *
     * @return a console command sender
     */
    @Override
    public ConsoleCommandSender getConsoleSender() {
        return null;
    }

    /**
     * Gets the folder that contains all of the various {@link World}s.
     *
     * @return folder that contains all worlds
     */
    @Override
    public File getWorldContainer() {
        return null;
    }

    /**
     * Gets every player that has ever played on this server.
     *
     * @return an array containing all previous players
     */
    @Override
    public OfflinePlayer[] getOfflinePlayers() {
        return new OfflinePlayer[0];
    }

    /**
     * Gets the {@link Messenger} responsible for this server.
     *
     * @return messenger responsible for this server
     */
    @Override
    public Messenger getMessenger() {
        return null;
    }

    /**
     * Gets the {@link HelpMap} providing help topics for this server.
     *
     * @return a help map for this server
     */
    @Override
    public HelpMap getHelpMap() {
        return null;
    }

    /**
     * Creates an empty inventory with the specified type. If the type
     * is {@link InventoryType#CHEST}, the new inventory has a size of 27;
     * otherwise the new inventory has the normal size for its type.
     * <br>
     * {@link InventoryType#WORKBENCH} will not process crafting recipes if
     * created with this method. Use
     * {@link Player#openWorkbench(Location, boolean)} instead.
     * <br>
     * {@link InventoryType#ENCHANTING} will not process {@link ItemStack}s
     * for possible enchanting results. Use
     * {@link Player#openEnchanting(Location, boolean)} instead.
     *
     * @param owner the holder of the inventory, or null to indicate no holder
     * @param type  the type of inventory to create
     * @return a new inventory
     * @throws IllegalArgumentException if the {@link InventoryType} cannot be
     *                                  viewed.
     * @see InventoryType#isCreatable()
     */
    @Override
    public Inventory createInventory(InventoryHolder owner, InventoryType type) {
        return null;
    }

    /**
     * Creates an empty inventory with the specified type and title. If the type
     * is {@link InventoryType#CHEST}, the new inventory has a size of 27;
     * otherwise the new inventory has the normal size for its type.<br>
     * It should be noted that some inventory types do not support titles and
     * may not render with said titles on the Minecraft client.
     * <br>
     * {@link InventoryType#WORKBENCH} will not process crafting recipes if
     * created with this method. Use
     * {@link Player#openWorkbench(Location, boolean)} instead.
     * <br>
     * {@link InventoryType#ENCHANTING} will not process {@link ItemStack}s
     * for possible enchanting results. Use
     * {@link Player#openEnchanting(Location, boolean)} instead.
     *
     * @param owner The holder of the inventory; can be null if there's no holder.
     * @param type  The type of inventory to create.
     * @param title The title of the inventory, to be displayed when it is viewed.
     * @return The new inventory.
     * @throws IllegalArgumentException if the {@link InventoryType} cannot be
     *                                  viewed.
     * @see InventoryType#isCreatable()
     */
    @Override
    public Inventory createInventory(InventoryHolder owner, InventoryType type, String title) {
        return null;
    }

    /**
     * Creates an empty inventory of type {@link InventoryType#CHEST} with the
     * specified size.
     *
     * @param owner the holder of the inventory, or null to indicate no holder
     * @param size  a multiple of 9 as the size of inventory to create
     * @return a new inventory
     * @throws IllegalArgumentException if the size is not a multiple of 9
     */
    @Override
    public Inventory createInventory(InventoryHolder owner, int size) throws IllegalArgumentException {
        return null;
    }

    /**
     * Creates an empty inventory of type {@link InventoryType#CHEST} with the
     * specified size and title.
     *
     * @param owner the holder of the inventory, or null to indicate no holder
     * @param size  a multiple of 9 as the size of inventory to create
     * @param title the title of the inventory, displayed when inventory is
     *              viewed
     * @return a new inventory
     * @throws IllegalArgumentException if the size is not a multiple of 9
     */
    @Override
    public Inventory createInventory(InventoryHolder owner, int size, String title) throws IllegalArgumentException {
        return null;
    }

    /**
     * Creates an empty merchant.
     *
     * @param title the title of the corresponding merchant inventory, displayed
     *              when the merchant inventory is viewed
     * @return a new merchant
     */
    @Override
    public Merchant createMerchant(String title) {
        return null;
    }

    /**
     * Gets user-specified limit for number of monsters that can spawn in a
     * chunk.
     *
     * @return the monster spawn limit
     */
    @Override
    public int getMonsterSpawnLimit() {
        return 0;
    }

    /**
     * Gets user-specified limit for number of animals that can spawn in a
     * chunk.
     *
     * @return the animal spawn limit
     */
    @Override
    public int getAnimalSpawnLimit() {
        return 0;
    }

    /**
     * Gets user-specified limit for number of water animals that can spawn in
     * a chunk.
     *
     * @return the water animal spawn limit
     */
    @Override
    public int getWaterAnimalSpawnLimit() {
        return 0;
    }

    /**
     * Gets user-specified limit for number of water ambient mobs that can spawn
     * in a chunk.
     *
     * @return the water ambient spawn limit
     */
    @Override
    public int getWaterAmbientSpawnLimit() {
        return 0;
    }

    /**
     * Gets user-specified limit for number of ambient mobs that can spawn in
     * a chunk.
     *
     * @return the ambient spawn limit
     */
    @Override
    public int getAmbientSpawnLimit() {
        return 0;
    }

    /**
     * Checks the current thread against the expected primary thread for the
     * server.
     * <p>
     * <b>Note:</b> this method should not be used to indicate the current
     * synchronized state of the runtime. A current thread matching the main
     * thread indicates that it is synchronized, but a mismatch <b>does not
     * preclude</b> the same assumption.
     *
     * @return true if the current thread matches the expected primary thread,
     * false otherwise
     */
    @Override
    public boolean isPrimaryThread() {
        return false;
    }

    /**
     * Gets the message that is displayed on the server list.
     *
     * @return the servers MOTD
     */
    @Override
    public String getMotd() {
        return null;
    }

    /**
     * Gets the default message that is displayed when the server is stopped.
     *
     * @return the shutdown message
     */
    @Override
    public String getShutdownMessage() {
        return null;
    }

    /**
     * Gets the current warning state for the server.
     *
     * @return the configured warning state
     */
    @Override
    public Warning.WarningState getWarningState() {
        return null;
    }

    /**
     * Gets the instance of the item factory (for {@link ItemMeta}).
     *
     * @return the item factory
     * @see ItemFactory
     */
    @Override
    public ItemFactory getItemFactory() {
        return null;
    }

    /**
     * Gets the instance of the scoreboard manager.
     * <p>
     * This will only exist after the first world has loaded.
     *
     * @return the scoreboard manager or null if no worlds are loaded.
     */
    @Override
    public ScoreboardManager getScoreboardManager() {
        return null;
    }

    /**
     * Gets an instance of the server's default server-icon.
     *
     * @return the default server-icon; null values may be used by the
     * implementation to indicate no defined icon, but this behavior is
     * not guaranteed
     */
    @Override
    public CachedServerIcon getServerIcon() {
        return null;
    }

    /**
     * Loads an image from a file, and returns a cached image for the specific
     * server-icon.
     * <p>
     * Size and type are implementation defined. An incompatible file is
     * guaranteed to throw an implementation-defined {@link Exception}.
     *
     * @param file the file to load the from
     * @return a cached server-icon that can be used for a {@link
     * ServerListPingEvent#setServerIcon(CachedServerIcon)}
     * @throws IllegalArgumentException if image is null
     * @throws Exception                if the image does not meet current server server-icon
     *                                  specifications
     */
    @Override
    public CachedServerIcon loadServerIcon(File file) throws IllegalArgumentException, Exception {
        return null;
    }

    /**
     * Creates a cached server-icon for the specific image.
     * <p>
     * Size and type are implementation defined. An incompatible file is
     * guaranteed to throw an implementation-defined {@link Exception}.
     *
     * @param image the image to use
     * @return a cached server-icon that can be used for a {@link
     * ServerListPingEvent#setServerIcon(CachedServerIcon)}
     * @throws IllegalArgumentException if image is null
     * @throws Exception                if the image does not meet current server
     *                                  server-icon specifications
     */
    @Override
    public CachedServerIcon loadServerIcon(BufferedImage image) throws IllegalArgumentException, Exception {
        return null;
    }

    /**
     * Set the idle kick timeout. Any players idle for the specified amount of
     * time will be automatically kicked.
     * <p>
     * A value of 0 will disable the idle kick timeout.
     *
     * @param threshold the idle timeout in minutes
     */
    @Override
    public void setIdleTimeout(int threshold) {

    }

    /**
     * Gets the idle kick timeout.
     *
     * @return the idle timeout in minutes
     */
    @Override
    public int getIdleTimeout() {
        return 0;
    }

    /**
     * Create a ChunkData for use in a generator.
     * <p>
     * See {@link ChunkGenerator#generateChunkData(World, Random, int, int, ChunkGenerator.BiomeGrid)}
     *
     * @param world the world to create the ChunkData for
     * @return a new ChunkData for the world
     */
    @Override
    public ChunkGenerator.ChunkData createChunkData(World world) {
        return null;
    }

    /**
     * Creates a boss bar instance to display to players. The progress
     * defaults to 1.0
     *
     * @param title the title of the boss bar
     * @param color the color of the boss bar
     * @param style the style of the boss bar
     * @param flags an optional list of flags to set on the boss bar
     * @return the created boss bar
     */
    @Override
    public BossBar createBossBar(String title, BarColor color, BarStyle style, BarFlag... flags) {
        return null;
    }

    /**
     * Creates a boss bar instance to display to players. The progress defaults
     * to 1.0.
     * <br>
     * This instance is added to the persistent storage of the server and will
     * be editable by commands and restored after restart.
     *
     * @param key   the key of the boss bar that is used to access the boss bar
     * @param title the title of the boss bar
     * @param color the color of the boss bar
     * @param style the style of the boss bar
     * @param flags an optional list of flags to set on the boss bar
     * @return the created boss bar
     */
    @Override
    public KeyedBossBar createBossBar(NamespacedKey key, String title, BarColor color, BarStyle style, BarFlag... flags) {
        return null;
    }

    /**
     * Gets an unmodifiable iterator through all persistent bossbars.
     * <ul>
     *   <li><b>not</b> bound to a {@link Boss}</li>
     *   <li>
     *     <b>not</b> created using
     *     {@link #createBossBar(String, BarColor, BarStyle, BarFlag...)}
     *   </li>
     * </ul>
     * <p>
     * e.g. bossbars created using the bossbar command
     *
     * @return a bossbar iterator
     */
    @Override
    public Iterator<KeyedBossBar> getBossBars() {
        return null;
    }

    /**
     * Gets the {@link KeyedBossBar} specified by this key.
     * <ul>
     *   <li><b>not</b> bound to a {@link Boss}</li>
     *   <li>
     *     <b>not</b> created using
     *     {@link #createBossBar(String, BarColor, BarStyle, BarFlag...)}
     *   </li>
     * </ul>
     * <p>
     * e.g. bossbars created using the bossbar command
     *
     * @param key unique bossbar key
     * @return bossbar or null if not exists
     */
    @Override
    public KeyedBossBar getBossBar(NamespacedKey key) {
        return null;
    }

    /**
     * Removes a {@link KeyedBossBar} specified by this key.
     * <ul>
     *   <li><b>not</b> bound to a {@link Boss}</li>
     *   <li>
     *     <b>not</b> created using
     *     {@link #createBossBar(String, BarColor, BarStyle, BarFlag...)}
     *   </li>
     * </ul>
     * <p>
     * e.g. bossbars created using the bossbar command
     *
     * @param key unique bossbar key
     * @return true if removal succeeded or false
     */
    @Override
    public boolean removeBossBar(NamespacedKey key) {
        return false;
    }

    /**
     * Gets an entity on the server by its UUID
     *
     * @param uuid the UUID of the entity
     * @return the entity with the given UUID, or null if it isn't found
     */
    @Override
    public Entity getEntity(UUID uuid) {
        return null;
    }

    /**
     * Get the advancement specified by this key.
     *
     * @param key unique advancement key
     * @return advancement or null if not exists
     */
    @Override
    public Advancement getAdvancement(NamespacedKey key) {
        return null;
    }

    /**
     * Get an iterator through all advancements. Advancements cannot be removed
     * from this iterator,
     *
     * @return an advancement iterator
     */
    @Override
    public Iterator<Advancement> advancementIterator() {
        return null;
    }

    /**
     * Creates a new {@link BlockData} instance for the specified Material, with
     * all properties initialized to unspecified defaults.
     *
     * @param material the material
     * @return new data instance
     */
    @Override
    public BlockData createBlockData(Material material) {
        return null;
    }

    /**
     * Creates a new {@link BlockData} instance for the specified Material, with
     * all properties initialized to unspecified defaults.
     *
     * @param material the material
     * @param consumer consumer to run on new instance before returning
     * @return new data instance
     */
    @Override
    public BlockData createBlockData(Material material, Consumer<BlockData> consumer) {
        return null;
    }

    /**
     * Creates a new {@link BlockData} instance with material and properties
     * parsed from provided data.
     *
     * @param data data string
     * @return new data instance
     * @throws IllegalArgumentException if the specified data is not valid
     */
    @Override
    public BlockData createBlockData(String data) throws IllegalArgumentException {
        return null;
    }

    /**
     * Creates a new {@link BlockData} instance for the specified Material, with
     * all properties initialized to unspecified defaults, except for those
     * provided in data.
     * <br>
     * If <code>material</code> is specified, then the data string must not also
     * contain the material.
     *
     * @param material the material
     * @param data     data string
     * @return new data instance
     * @throws IllegalArgumentException if the specified data is not valid
     */
    @Override
    public BlockData createBlockData(Material material, String data) throws IllegalArgumentException {
        return null;
    }

    /**
     * Gets a tag which has already been defined within the server. Plugins are
     * suggested to use the concrete tags in {@link Tag} rather than this method
     * which makes no guarantees about which tags are available, and may also be
     * less performant due to lack of caching.
     * <br>
     * Tags will be searched for in an implementation specific manner, but a
     * path consisting of namespace/tags/registry/key is expected.
     * <br>
     * Server implementations are allowed to handle only the registries
     * indicated in {@link Tag}.
     *
     * @param registry the tag registry to look at
     * @param tag      the name of the tag
     * @param clazz    the class of the tag entries
     * @return the tag or null
     */
    @Override
    public <T extends Keyed> Tag<T> getTag(String registry, NamespacedKey tag, Class<T> clazz) {
        return null;
    }

    /**
     * Gets a all tags which have been defined within the server.
     * <br>
     * Server implementations are allowed to handle only the registries
     * indicated in {@link Tag}.
     * <br>
     * No guarantees are made about the mutability of the returned iterator.
     *
     * @param registry the tag registry to look at
     * @param clazz    the class of the tag entries
     * @return all defined tags
     */
    @Override
    public <T extends Keyed> Iterable<Tag<T>> getTags(String registry, Class<T> clazz) {
        return null;
    }

    /**
     * Gets the specified {@link LootTable}.
     *
     * @param key the name of the LootTable
     * @return the LootTable, or null if no LootTable is found with that name
     */
    @Override
    public LootTable getLootTable(NamespacedKey key) {
        return null;
    }

    /**
     * Selects entities using the given Vanilla selector.
     * <br>
     * No guarantees are made about the selector format, other than they match
     * the Vanilla format for the active Minecraft version.
     * <br>
     * Usually a selector will start with '@', unless selecting a Player in
     * which case it may simply be the Player's name or UUID.
     * <br>
     * Note that in Vanilla, elevated permissions are usually required to use
     * '@' selectors, but this method should not check such permissions from the
     * sender.
     *
     * @param sender   the sender to execute as, must be provided
     * @param selector the selection string
     * @return a list of the selected entities. The list will not be null, but
     * no further guarantees are made.
     * @throws IllegalArgumentException if the selector is malformed in any way
     *                                  or a parameter is null
     */
    @Override
    public List<Entity> selectEntities(CommandSender sender, String selector) throws IllegalArgumentException {
        return null;
    }

    /**
     * @return the unsafe values instance
     * @see UnsafeValues
     */
    @Override
    public UnsafeValues getUnsafe() {
        return null;
    }

    @Override
    public Spigot spigot() {
        return null;
    }

    /**
     * Sends this recipient a Plugin Message on the specified outgoing
     * channel.
     * <p>
     * The message may not be larger than {@link Messenger#MAX_MESSAGE_SIZE}
     * bytes, and the plugin must be registered to send messages on the
     * specified channel.
     *
     * @param source  The plugin that sent this message.
     * @param channel The channel to send this message on.
     * @param message The raw message to send.
     * @throws IllegalArgumentException      Thrown if the source plugin is
     *                                       disabled.
     * @throws IllegalArgumentException      Thrown if source, channel or message
     *                                       is null.
     * @throws MessageTooLargeException      Thrown if the message is too big.
     * @throws ChannelNotRegisteredException Thrown if the channel is not
     *                                       registered for this plugin.
     */
    @Override
    public void sendPluginMessage(Plugin source, String channel, byte[] message) {

    }

    /**
     * Gets a set containing all the Plugin Channels that this client is
     * listening on.
     *
     * @return Set containing all the channels that this client may accept.
     */
    @Override
    public Set<String> getListeningPluginChannels() {
        return null;
    }
}
