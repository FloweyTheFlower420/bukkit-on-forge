package com.floweytf.forgebukkit.entity;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import org.bukkit.*;
import org.bukkit.advancement.Advancement;
import org.bukkit.advancement.AdvancementProgress;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.PistonMoveReaction;
import org.bukkit.block.data.BlockData;
import org.bukkit.conversations.Conversation;
import org.bukkit.conversations.ConversationAbandonedEvent;
import org.bukkit.entity.*;
import org.bukkit.entity.memory.MemoryKey;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.inventory.*;
import org.bukkit.map.MapView;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.permissions.Permission;
import org.bukkit.permissions.PermissionAttachment;
import org.bukkit.permissions.PermissionAttachmentInfo;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;
import org.bukkit.util.BoundingBox;
import org.bukkit.util.RayTraceResult;

import java.net.InetSocketAddress;
import java.util.*;

public class ForgeBukkitPlayer implements Player {
    ServerPlayerEntity handle;

    @Override
    public String getDisplayName() {
        return null;
    }

    @Override
    public void setDisplayName(String name) {

    }

    /**
     * Gets the name that is shown on the player list.
     *
     * @return the player list name
     */
    @Override
    public String getPlayerListName() {
        return null;
    }

    /**
     * Sets the name that is shown on the in-game player list.
     * <p>
     * If the value is null, the name will be identical to {@link #getName()}.
     *
     * @param name new player list name
     */
    @Override
    public void setPlayerListName(String name) {

    }

    /**
     * Gets the currently displayed player list header for this player.
     *
     * @return player list header or null
     */
    @Override
    public String getPlayerListHeader() {
        return null;
    }

    /**
     * Gets the currently displayed player list footer for this player.
     *
     * @return player list header or null
     */
    @Override
    public String getPlayerListFooter() {
        return null;
    }

    /**
     * Sets the currently displayed player list header for this player.
     *
     * @param header player list header, null for empty
     */
    @Override
    public void setPlayerListHeader(String header) {

    }

    /**
     * Sets the currently displayed player list footer for this player.
     *
     * @param footer player list footer, null for empty
     */
    @Override
    public void setPlayerListFooter(String footer) {

    }

    /**
     * Sets the currently displayed player list header and footer for this
     * player.
     *
     * @param header player list header, null for empty
     * @param footer player list footer, null for empty
     */
    @Override
    public void setPlayerListHeaderFooter(String header, String footer) {

    }

    /**
     * Set the target of the player's compass.
     *
     * @param loc Location to point to
     */
    @Override
    public void setCompassTarget(Location loc) {

    }

    /**
     * Get the previously set compass target.
     *
     * @return location of the target
     */
    @Override
    public Location getCompassTarget() {
        return null;
    }

    /**
     * Gets the socket address of this player
     *
     * @return the player's address
     */
    @Override
    public InetSocketAddress getAddress() {
        return null;
    }

    /**
     * Tests to see of a Conversable object is actively engaged in a
     * conversation.
     *
     * @return True if a conversation is in progress
     */
    @Override
    public boolean isConversing() {
        return false;
    }

    /**
     * Accepts input into the active conversation. If no conversation is in
     * progress, this method does nothing.
     *
     * @param input The input message into the conversation
     */
    @Override
    public void acceptConversationInput(String input) {

    }

    /**
     * Enters into a dialog with a Conversation object.
     *
     * @param conversation The conversation to begin
     * @return True if the conversation should proceed, false if it has been
     * enqueued
     */
    @Override
    public boolean beginConversation(Conversation conversation) {
        return false;
    }

    /**
     * Abandons an active conversation.
     *
     * @param conversation The conversation to abandon
     */
    @Override
    public void abandonConversation(Conversation conversation) {

    }

    /**
     * Abandons an active conversation.
     *
     * @param conversation The conversation to abandon
     * @param details      Details about why the conversation was abandoned
     */
    @Override
    public void abandonConversation(Conversation conversation, ConversationAbandonedEvent details) {

    }

    /**
     * Sends this sender a message raw
     *
     * @param message Message to be displayed
     */
    @Override
    public void sendRawMessage(String message) {

    }

    /**
     * Sends this sender a message raw
     *
     * @param sender  The sender of this message
     * @param message Message to be displayed
     */
    @Override
    public void sendRawMessage(UUID sender, String message) {

    }

    /**
     * Kicks player with custom kick message.
     *
     * @param message kick message
     */
    @Override
    public void kickPlayer(String message) {

    }

    /**
     * Says a message (or runs a command).
     *
     * @param msg message to print
     */
    @Override
    public void chat(String msg) {

    }

    /**
     * Makes the player perform the given command
     *
     * @param command Command to perform
     * @return true if the command was successful, otherwise false
     */
    @Override
    public boolean performCommand(String command) {
        return false;
    }

    /**
     * Gets the entity's current position
     *
     * @return a new copy of Location containing the position of this entity
     */
    @Override
    public Location getLocation() {
        return null;
    }

    /**
     * Stores the entity's current position in the provided Location object.
     * <p>
     * If the provided Location is null this method does nothing and returns
     * null.
     *
     * @param loc the location to copy into
     * @return The Location object provided or null
     */
    @Override
    public Location getLocation(Location loc) {
        return null;
    }

    /**
     * Sets this entity's velocity
     *
     * @param velocity New velocity to travel with
     */
    @Override
    public void setVelocity(org.bukkit.util.Vector velocity) {

    }

    /**
     * Gets this entity's current velocity
     *
     * @return Current traveling velocity of this entity
     */
    @Override
    public org.bukkit.util.Vector getVelocity() {
        return null;
    }

    /**
     * Gets the entity's height
     *
     * @return height of entity
     */
    @Override
    public double getHeight() {
        return 0;
    }

    /**
     * Gets the entity's width
     *
     * @return width of entity
     */
    @Override
    public double getWidth() {
        return 0;
    }

    /**
     * Gets the entity's current bounding box.
     * <p>
     * The returned bounding box reflects the entity's current location and
     * size.
     *
     * @return the entity's current bounding box
     */
    @Override
    public BoundingBox getBoundingBox() {
        return null;
    }

    /**
     * Returns true if the entity is supported by a block.
     * <p>
     * This value is a state updated by the client after each movement.
     *
     * @return True if entity is on ground.
     * @deprecated This value is controlled only by the client and is therefore
     * unreliable and vulnerable to spoofing and/or desync depending on the
     * context/time which it is accessed
     */
    @Override
    public boolean isOnGround() {
        return false;
    }

    /**
     * Returns true if the entity is in water.
     *
     * @return <code>true</code> if the entity is in water.
     */
    @Override
    public boolean isInWater() {
        return false;
    }

    /**
     * Gets the current world this entity resides in
     *
     * @return World
     */
    @Override
    public World getWorld() {
        return null;
    }

    /**
     * Sets the entity's rotation.
     * <p>
     * Note that if the entity is affected by AI, it may override this rotation.
     *
     * @param yaw   the yaw
     * @param pitch the pitch
     * @throws UnsupportedOperationException if used for players
     */
    @Override
    public void setRotation(float yaw, float pitch) {

    }

    /**
     * Teleports this entity to the given location. If this entity is riding a
     * vehicle, it will be dismounted prior to teleportation.
     *
     * @param location New location to teleport this entity to
     * @return <code>true</code> if the teleport was successful
     */
    @Override
    public boolean teleport(Location location) {
        return false;
    }

    /**
     * Teleports this entity to the given location. If this entity is riding a
     * vehicle, it will be dismounted prior to teleportation.
     *
     * @param location New location to teleport this entity to
     * @param cause    The cause of this teleportation
     * @return <code>true</code> if the teleport was successful
     */
    @Override
    public boolean teleport(Location location, PlayerTeleportEvent.TeleportCause cause) {
        return false;
    }

    /**
     * Teleports this entity to the target Entity. If this entity is riding a
     * vehicle, it will be dismounted prior to teleportation.
     *
     * @param destination Entity to teleport this entity to
     * @return <code>true</code> if the teleport was successful
     */
    @Override
    public boolean teleport(Entity destination) {
        return false;
    }

    /**
     * Teleports this entity to the target Entity. If this entity is riding a
     * vehicle, it will be dismounted prior to teleportation.
     *
     * @param destination Entity to teleport this entity to
     * @param cause       The cause of this teleportation
     * @return <code>true</code> if the teleport was successful
     */
    @Override
    public boolean teleport(Entity destination, PlayerTeleportEvent.TeleportCause cause) {
        return false;
    }

    /**
     * Returns a list of entities within a bounding box centered around this
     * entity
     *
     * @param x 1/2 the size of the box along x axis
     * @param y 1/2 the size of the box along y axis
     * @param z 1/2 the size of the box along z axis
     * @return {@code List<Entity>} List of entities nearby
     */
    @Override
    public List<Entity> getNearbyEntities(double x, double y, double z) {
        return null;
    }

    /**
     * Returns a unique id for this entity
     *
     * @return Entity id
     */
    @Override
    public int getEntityId() {
        return 0;
    }

    /**
     * Returns the entity's current fire ticks (ticks before the entity stops
     * being on fire).
     *
     * @return int fireTicks
     */
    @Override
    public int getFireTicks() {
        return 0;
    }

    /**
     * Returns the entity's maximum fire ticks.
     *
     * @return int maxFireTicks
     */
    @Override
    public int getMaxFireTicks() {
        return 0;
    }

    /**
     * Sets the entity's current fire ticks (ticks before the entity stops
     * being on fire).
     *
     * @param ticks Current ticks remaining
     */
    @Override
    public void setFireTicks(int ticks) {

    }

    /**
     * Mark the entity's removal.
     */
    @Override
    public void remove() {

    }

    /**
     * Returns true if this entity has been marked for removal.
     *
     * @return True if it is dead.
     */
    @Override
    public boolean isDead() {
        return false;
    }

    /**
     * Returns false if the entity has died or been despawned for some other
     * reason.
     *
     * @return True if valid.
     */
    @Override
    public boolean isValid() {
        return false;
    }

    /**
     * Sends this sender a message
     *
     * @param message Message to be displayed
     */
    @Override
    public void sendMessage(String message) {

    }

    /**
     * Sends this sender multiple messages
     *
     * @param messages An array of messages to be displayed
     */
    @Override
    public void sendMessage(String[] messages) {

    }

    /**
     * Sends this sender a message
     *
     * @param sender  The sender of this message
     * @param message Message to be displayed
     */
    @Override
    public void sendMessage(UUID sender, String message) {

    }

    /**
     * Sends this sender multiple messages
     *
     * @param sender   The sender of this message
     * @param messages An array of messages to be displayed
     */
    @Override
    public void sendMessage(UUID sender, String[] messages) {

    }

    /**
     * Gets the {@link Server} that contains this Entity
     *
     * @return Server instance running this Entity
     */
    @Override
    public Server getServer() {
        return null;
    }

    /**
     * Returns true if the entity gets persisted.
     * <p>
     * By default all entities are persistent. An entity will also not get
     * persisted, if it is riding an entity that is not persistent.
     * <p>
     * The persistent flag on players controls whether or not to save their
     * playerdata file when they quit. If a player is directly or indirectly
     * riding a non-persistent entity, the vehicle at the root and all its
     * passengers won't get persisted.
     * <p>
     * <b>This should not be confused with
     * {@link LivingEntity#setRemoveWhenFarAway(boolean)} which controls
     * despawning of living entities. </b>
     *
     * @return true if this entity is persistent
     */
    @Override
    public boolean isPersistent() {
        return false;
    }

    /**
     * Sets whether or not the entity gets persisted.
     *
     * @param persistent the persistence status
     * @see #isPersistent()
     */
    @Override
    public void setPersistent(boolean persistent) {

    }

    /**
     * Gets the primary passenger of a vehicle. For vehicles that could have
     * multiple passengers, this will only return the primary passenger.
     *
     * @return an entity
     * @deprecated entities may have multiple passengers, use
     * {@link #getPassengers()}
     */
    @Override
    public Entity getPassenger() {
        return null;
    }

    /**
     * Set the passenger of a vehicle.
     *
     * @param passenger The new passenger.
     * @return false if it could not be done for whatever reason
     * @deprecated entities may have multiple passengers, use
     * {@link #addPassenger(Entity)}
     */
    @Override
    public boolean setPassenger(Entity passenger) {
        return false;
    }

    /**
     * Gets a list of passengers of this vehicle.
     * <p>
     * The returned list will not be directly linked to the entity's current
     * passengers, and no guarantees are made as to its mutability.
     *
     * @return list of entities corresponding to current passengers.
     */
    @Override
    public List<Entity> getPassengers() {
        return null;
    }

    /**
     * Add a passenger to the vehicle.
     *
     * @param passenger The passenger to add
     * @return false if it could not be done for whatever reason
     */
    @Override
    public boolean addPassenger(Entity passenger) {
        return false;
    }

    /**
     * Remove a passenger from the vehicle.
     *
     * @param passenger The passenger to remove
     * @return false if it could not be done for whatever reason
     */
    @Override
    public boolean removePassenger(Entity passenger) {
        return false;
    }

    /**
     * Check if a vehicle has passengers.
     *
     * @return True if the vehicle has no passengers.
     */
    @Override
    public boolean isEmpty() {
        return false;
    }

    /**
     * Eject any passenger.
     *
     * @return True if there was a passenger.
     */
    @Override
    public boolean eject() {
        return false;
    }

    /**
     * Returns the distance this entity has fallen
     *
     * @return The distance.
     */
    @Override
    public float getFallDistance() {
        return 0;
    }

    /**
     * Sets the fall distance for this entity
     *
     * @param distance The new distance.
     */
    @Override
    public void setFallDistance(float distance) {

    }

    /**
     * Record the last {@link EntityDamageEvent} inflicted on this entity
     *
     * @param event a {@link EntityDamageEvent}
     */
    @Override
    public void setLastDamageCause(EntityDamageEvent event) {

    }

    /**
     * Retrieve the last {@link EntityDamageEvent} inflicted on this entity.
     * This event may have been cancelled.
     *
     * @return the last known {@link EntityDamageEvent} or null if hitherto
     * unharmed
     */
    @Override
    public EntityDamageEvent getLastDamageCause() {
        return null;
    }

    /**
     * Returns a unique and persistent id for this entity
     *
     * @return unique id
     */
    @Override
    public UUID getUniqueId() {
        return null;
    }

    /**
     * Gets the amount of ticks this entity has lived for.
     * <p>
     * This is the equivalent to "age" in entities.
     *
     * @return Age of entity
     */
    @Override
    public int getTicksLived() {
        return 0;
    }

    /**
     * Sets the amount of ticks this entity has lived for.
     * <p>
     * This is the equivalent to "age" in entities. May not be less than one
     * tick.
     *
     * @param value Age of entity
     */
    @Override
    public void setTicksLived(int value) {

    }

    /**
     * Performs the specified {@link EntityEffect} for this entity.
     * <p>
     * This will be viewable to all players near the entity.
     * <p>
     * If the effect is not applicable to this class of entity, it will not play.
     *
     * @param type Effect to play.
     */
    @Override
    public void playEffect(EntityEffect type) {

    }

    /**
     * Get the type of the entity.
     *
     * @return The entity type.
     */
    @Override
    public EntityType getType() {
        return null;
    }

    /**
     * Returns whether this entity is inside a vehicle.
     *
     * @return True if the entity is in a vehicle.
     */
    @Override
    public boolean isInsideVehicle() {
        return false;
    }

    /**
     * Leave the current vehicle. If the entity is currently in a vehicle (and
     * is removed from it), true will be returned, otherwise false will be
     * returned.
     *
     * @return True if the entity was in a vehicle.
     */
    @Override
    public boolean leaveVehicle() {
        return false;
    }

    /**
     * Get the vehicle that this player is inside. If there is no vehicle,
     * null will be returned.
     *
     * @return The current vehicle.
     */
    @Override
    public Entity getVehicle() {
        return null;
    }

    /**
     * Sets whether or not to display the mob's custom name client side. The
     * name will be displayed above the mob similarly to a player.
     * <p>
     * This value has no effect on players, they will always display their
     * name.
     *
     * @param flag custom name or not
     */
    @Override
    public void setCustomNameVisible(boolean flag) {

    }

    /**
     * Gets whether or not the mob's custom name is displayed client side.
     * <p>
     * This value has no effect on players, they will always display their
     * name.
     *
     * @return if the custom name is displayed
     */
    @Override
    public boolean isCustomNameVisible() {
        return false;
    }

    /**
     * Sets whether the entity has a team colored (default: white) glow.
     *
     * <b>nb: this refers to the 'Glowing' entity property, not whether a
     * glowing potion effect is applied</b>
     *
     * @param flag if the entity is glowing
     */
    @Override
    public void setGlowing(boolean flag) {

    }

    /**
     * Gets whether the entity is glowing or not.
     *
     * <b>nb: this refers to the 'Glowing' entity property, not whether a
     * glowing potion effect is applied</b>
     *
     * @return whether the entity is glowing
     */
    @Override
    public boolean isGlowing() {
        return false;
    }

    /**
     * Sets whether the entity is invulnerable or not.
     * <p>
     * When an entity is invulnerable it can only be damaged by players in
     * creative mode.
     *
     * @param flag if the entity is invulnerable
     */
    @Override
    public void setInvulnerable(boolean flag) {

    }

    /**
     * Gets whether the entity is invulnerable or not.
     *
     * @return whether the entity is
     */
    @Override
    public boolean isInvulnerable() {
        return false;
    }

    /**
     * Gets whether the entity is silent or not.
     *
     * @return whether the entity is silent.
     */
    @Override
    public boolean isSilent() {
        return false;
    }

    /**
     * Sets whether the entity is silent or not.
     * <p>
     * When an entity is silent it will not produce any sound.
     *
     * @param flag if the entity is silent
     */
    @Override
    public void setSilent(boolean flag) {

    }

    /**
     * Returns whether gravity applies to this entity.
     *
     * @return whether gravity applies
     */
    @Override
    public boolean hasGravity() {
        return false;
    }

    /**
     * Sets whether gravity applies to this entity.
     *
     * @param gravity whether gravity should apply
     */
    @Override
    public void setGravity(boolean gravity) {

    }

    /**
     * Gets the period of time (in ticks) before this entity can use a portal.
     *
     * @return portal cooldown ticks
     */
    @Override
    public int getPortalCooldown() {
        return 0;
    }

    /**
     * Sets the period of time (in ticks) before this entity can use a portal.
     *
     * @param cooldown portal cooldown ticks
     */
    @Override
    public void setPortalCooldown(int cooldown) {

    }

    /**
     * Returns a set of tags for this entity.
     * <br>
     * Entities can have no more than 1024 tags.
     *
     * @return a set of tags for this entity
     */
    @Override
    public Set<String> getScoreboardTags() {
        return null;
    }

    /**
     * Add a tag to this entity.
     * <br>
     * Entities can have no more than 1024 tags.
     *
     * @param tag the tag to add
     * @return true if the tag was successfully added
     */
    @Override
    public boolean addScoreboardTag(String tag) {
        return false;
    }

    /**
     * Removes a given tag from this entity.
     *
     * @param tag the tag to remove
     * @return true if the tag was successfully removed
     */
    @Override
    public boolean removeScoreboardTag(String tag) {
        return false;
    }

    /**
     * Returns the reaction of the entity when moved by a piston.
     *
     * @return reaction
     */
    @Override
    public PistonMoveReaction getPistonMoveReaction() {
        return null;
    }

    /**
     * Get the closest cardinal {@link BlockFace} direction an entity is
     * currently facing.
     * <br>
     * This will not return any non-cardinal directions such as
     * {@link BlockFace#UP} or {@link BlockFace#DOWN}.
     * <br>
     * {@link Hanging} entities will override this call and thus their behavior
     * may be different.
     *
     * @return the entity's current cardinal facing.
     * @see Hanging
     * @see Directional#getFacing()
     */
    @Override
    public BlockFace getFacing() {
        return null;
    }

    /**
     * Gets the entity's current pose.
     *
     * <b>Note that the pose is only updated at the end of a tick, so may be
     * inconsistent with other methods. eg {@link Player#isSneaking()} being
     * true does not imply the current pose will be {@link Pose#SNEAKING}</b>
     *
     * @return current pose
     */
    @Override
    public Pose getPose() {
        return null;
    }

    /**
     * Returns if the player is in sneak mode
     *
     * @return true if player is in sneak mode
     */
    @Override
    public boolean isSneaking() {
        return false;
    }

    /**
     * Sets the sneak mode the player
     *
     * @param sneak true if player should appear sneaking
     */
    @Override
    public void setSneaking(boolean sneak) {

    }

    /**
     * Gets whether the player is sprinting or not.
     *
     * @return true if player is sprinting.
     */
    @Override
    public boolean isSprinting() {
        return false;
    }

    /**
     * Sets whether the player is sprinting or not.
     *
     * @param sprinting true if the player should be sprinting
     */
    @Override
    public void setSprinting(boolean sprinting) {

    }

    /**
     * Saves the players current location, health, inventory, motion, and
     * other information into the username.dat file, in the world/player
     * folder
     */
    @Override
    public void saveData() {

    }

    /**
     * Loads the players current location, health, inventory, motion, and
     * other information from the username.dat file, in the world/player
     * folder.
     * <p>
     * Note: This will overwrite the players current inventory, health,
     * motion, etc, with the state from the saved dat file.
     */
    @Override
    public void loadData() {

    }

    /**
     * Sets whether the player is ignored as not sleeping. If everyone is
     * either sleeping or has this flag set, then time will advance to the
     * next day. If everyone has this flag set but no one is actually in bed,
     * then nothing will happen.
     *
     * @param isSleeping Whether to ignore.
     */
    @Override
    public void setSleepingIgnored(boolean isSleeping) {

    }

    /**
     * Returns whether the player is sleeping ignored.
     *
     * @return Whether player is ignoring sleep.
     */
    @Override
    public boolean isSleepingIgnored() {
        return false;
    }

    /**
     * Checks if this player is currently online
     *
     * @return true if they are online
     */
    @Override
    public boolean isOnline() {
        return false;
    }

    /**
     * Checks if this player is banned or not
     *
     * @return true if banned, otherwise false
     */
    @Override
    public boolean isBanned() {
        return false;
    }

    /**
     * Checks if this player is whitelisted or not
     *
     * @return true if whitelisted
     */
    @Override
    public boolean isWhitelisted() {
        return false;
    }

    /**
     * Sets if this player is whitelisted or not
     *
     * @param value true if whitelisted
     */
    @Override
    public void setWhitelisted(boolean value) {

    }

    /**
     * Gets a {@link Player} object that this represents, if there is one
     * <p>
     * If the player is online, this will return that player. Otherwise,
     * it will return null.
     *
     * @return Online player
     */
    @Override
    public Player getPlayer() {
        return null;
    }

    /**
     * Gets the first date and time that this player was witnessed on this
     * server.
     * <p>
     * If the player has never played before, this will return 0. Otherwise,
     * it will be the amount of milliseconds since midnight, January 1, 1970
     * UTC.
     *
     * @return Date of first log-in for this player, or 0
     */
    @Override
    public long getFirstPlayed() {
        return 0;
    }

    /**
     * Gets the last date and time that this player was witnessed on this
     * server.
     * <p>
     * If the player has never played before, this will return 0. Otherwise,
     * it will be the amount of milliseconds since midnight, January 1, 1970
     * UTC.
     *
     * @return Date of last log-in for this player, or 0
     */
    @Override
    public long getLastPlayed() {
        return 0;
    }

    /**
     * Checks if this player has played on this server before.
     *
     * @return True if the player has played before, otherwise false
     */
    @Override
    public boolean hasPlayedBefore() {
        return false;
    }

    /**
     * Gets the Location where the player will spawn at their bed, null if
     * they have not slept in one or their current bed spawn is invalid.
     *
     * @return Bed Spawn Location if bed exists, otherwise null.
     */
    @Override
    public Location getBedSpawnLocation() {
        return null;
    }

    /**
     * Increments the given statistic for this player.
     * <p>
     * This is equivalent to the following code:
     * <code>incrementStatistic(Statistic, 1)</code>
     *
     * @param statistic Statistic to increment
     * @throws IllegalArgumentException if statistic is null
     * @throws IllegalArgumentException if the statistic requires an
     *                                  additional parameter
     */
    @Override
    public void incrementStatistic(Statistic statistic) throws IllegalArgumentException {

    }

    /**
     * Decrements the given statistic for this player.
     * <p>
     * This is equivalent to the following code:
     * <code>decrementStatistic(Statistic, 1)</code>
     *
     * @param statistic Statistic to decrement
     * @throws IllegalArgumentException if statistic is null
     * @throws IllegalArgumentException if the statistic requires an
     *                                  additional parameter
     */
    @Override
    public void decrementStatistic(Statistic statistic) throws IllegalArgumentException {

    }

    /**
     * Increments the given statistic for this player.
     *
     * @param statistic Statistic to increment
     * @param amount    Amount to increment this statistic by
     * @throws IllegalArgumentException if statistic is null
     * @throws IllegalArgumentException if amount is negative
     * @throws IllegalArgumentException if the statistic requires an
     *                                  additional parameter
     */
    @Override
    public void incrementStatistic(Statistic statistic, int amount) throws IllegalArgumentException {

    }

    /**
     * Decrements the given statistic for this player.
     *
     * @param statistic Statistic to decrement
     * @param amount    Amount to decrement this statistic by
     * @throws IllegalArgumentException if statistic is null
     * @throws IllegalArgumentException if amount is negative
     * @throws IllegalArgumentException if the statistic requires an
     *                                  additional parameter
     */
    @Override
    public void decrementStatistic(Statistic statistic, int amount) throws IllegalArgumentException {

    }

    /**
     * Sets the given statistic for this player.
     *
     * @param statistic Statistic to set
     * @param newValue  The value to set this statistic to
     * @throws IllegalArgumentException if statistic is null
     * @throws IllegalArgumentException if newValue is negative
     * @throws IllegalArgumentException if the statistic requires an
     *                                  additional parameter
     */
    @Override
    public void setStatistic(Statistic statistic, int newValue) throws IllegalArgumentException {

    }

    /**
     * Gets the value of the given statistic for this player.
     *
     * @param statistic Statistic to check
     * @return the value of the given statistic
     * @throws IllegalArgumentException if statistic is null
     * @throws IllegalArgumentException if the statistic requires an
     *                                  additional parameter
     */
    @Override
    public int getStatistic(Statistic statistic) throws IllegalArgumentException {
        return 0;
    }

    /**
     * Increments the given statistic for this player for the given material.
     * <p>
     * This is equivalent to the following code:
     * <code>incrementStatistic(Statistic, Material, 1)</code>
     *
     * @param statistic Statistic to increment
     * @param material  Material to offset the statistic with
     * @throws IllegalArgumentException if statistic is null
     * @throws IllegalArgumentException if material is null
     * @throws IllegalArgumentException if the given parameter is not valid
     *                                  for the statistic
     */
    @Override
    public void incrementStatistic(Statistic statistic, Material material) throws IllegalArgumentException {

    }

    /**
     * Decrements the given statistic for this player for the given material.
     * <p>
     * This is equivalent to the following code:
     * <code>decrementStatistic(Statistic, Material, 1)</code>
     *
     * @param statistic Statistic to decrement
     * @param material  Material to offset the statistic with
     * @throws IllegalArgumentException if statistic is null
     * @throws IllegalArgumentException if material is null
     * @throws IllegalArgumentException if the given parameter is not valid
     *                                  for the statistic
     */
    @Override
    public void decrementStatistic(Statistic statistic, Material material) throws IllegalArgumentException {

    }

    /**
     * Gets the value of the given statistic for this player.
     *
     * @param statistic Statistic to check
     * @param material  Material offset of the statistic
     * @return the value of the given statistic
     * @throws IllegalArgumentException if statistic is null
     * @throws IllegalArgumentException if material is null
     * @throws IllegalArgumentException if the given parameter is not valid
     *                                  for the statistic
     */
    @Override
    public int getStatistic(Statistic statistic, Material material) throws IllegalArgumentException {
        return 0;
    }

    /**
     * Increments the given statistic for this player for the given material.
     *
     * @param statistic Statistic to increment
     * @param material  Material to offset the statistic with
     * @param amount    Amount to increment this statistic by
     * @throws IllegalArgumentException if statistic is null
     * @throws IllegalArgumentException if material is null
     * @throws IllegalArgumentException if amount is negative
     * @throws IllegalArgumentException if the given parameter is not valid
     *                                  for the statistic
     */
    @Override
    public void incrementStatistic(Statistic statistic, Material material, int amount) throws IllegalArgumentException {

    }

    /**
     * Decrements the given statistic for this player for the given material.
     *
     * @param statistic Statistic to decrement
     * @param material  Material to offset the statistic with
     * @param amount    Amount to decrement this statistic by
     * @throws IllegalArgumentException if statistic is null
     * @throws IllegalArgumentException if material is null
     * @throws IllegalArgumentException if amount is negative
     * @throws IllegalArgumentException if the given parameter is not valid
     *                                  for the statistic
     */
    @Override
    public void decrementStatistic(Statistic statistic, Material material, int amount) throws IllegalArgumentException {

    }

    /**
     * Sets the given statistic for this player for the given material.
     *
     * @param statistic Statistic to set
     * @param material  Material to offset the statistic with
     * @param newValue  The value to set this statistic to
     * @throws IllegalArgumentException if statistic is null
     * @throws IllegalArgumentException if material is null
     * @throws IllegalArgumentException if newValue is negative
     * @throws IllegalArgumentException if the given parameter is not valid
     *                                  for the statistic
     */
    @Override
    public void setStatistic(Statistic statistic, Material material, int newValue) throws IllegalArgumentException {

    }

    /**
     * Increments the given statistic for this player for the given entity.
     * <p>
     * This is equivalent to the following code:
     * <code>incrementStatistic(Statistic, EntityType, 1)</code>
     *
     * @param statistic  Statistic to increment
     * @param entityType EntityType to offset the statistic with
     * @throws IllegalArgumentException if statistic is null
     * @throws IllegalArgumentException if entityType is null
     * @throws IllegalArgumentException if the given parameter is not valid
     *                                  for the statistic
     */
    @Override
    public void incrementStatistic(Statistic statistic, EntityType entityType) throws IllegalArgumentException {

    }

    /**
     * Decrements the given statistic for this player for the given entity.
     * <p>
     * This is equivalent to the following code:
     * <code>decrementStatistic(Statistic, EntityType, 1)</code>
     *
     * @param statistic  Statistic to decrement
     * @param entityType EntityType to offset the statistic with
     * @throws IllegalArgumentException if statistic is null
     * @throws IllegalArgumentException if entityType is null
     * @throws IllegalArgumentException if the given parameter is not valid
     *                                  for the statistic
     */
    @Override
    public void decrementStatistic(Statistic statistic, EntityType entityType) throws IllegalArgumentException {

    }

    /**
     * Gets the value of the given statistic for this player.
     *
     * @param statistic  Statistic to check
     * @param entityType EntityType offset of the statistic
     * @return the value of the given statistic
     * @throws IllegalArgumentException if statistic is null
     * @throws IllegalArgumentException if entityType is null
     * @throws IllegalArgumentException if the given parameter is not valid
     *                                  for the statistic
     */
    @Override
    public int getStatistic(Statistic statistic, EntityType entityType) throws IllegalArgumentException {
        return 0;
    }

    /**
     * Increments the given statistic for this player for the given entity.
     *
     * @param statistic  Statistic to increment
     * @param entityType EntityType to offset the statistic with
     * @param amount     Amount to increment this statistic by
     * @throws IllegalArgumentException if statistic is null
     * @throws IllegalArgumentException if entityType is null
     * @throws IllegalArgumentException if amount is negative
     * @throws IllegalArgumentException if the given parameter is not valid
     *                                  for the statistic
     */
    @Override
    public void incrementStatistic(Statistic statistic, EntityType entityType, int amount) throws IllegalArgumentException {

    }

    /**
     * Decrements the given statistic for this player for the given entity.
     *
     * @param statistic  Statistic to decrement
     * @param entityType EntityType to offset the statistic with
     * @param amount     Amount to decrement this statistic by
     * @throws IllegalArgumentException if statistic is null
     * @throws IllegalArgumentException if entityType is null
     * @throws IllegalArgumentException if amount is negative
     * @throws IllegalArgumentException if the given parameter is not valid
     *                                  for the statistic
     */
    @Override
    public void decrementStatistic(Statistic statistic, EntityType entityType, int amount) {

    }

    /**
     * Sets the given statistic for this player for the given entity.
     *
     * @param statistic  Statistic to set
     * @param entityType EntityType to offset the statistic with
     * @param newValue   The value to set this statistic to
     * @throws IllegalArgumentException if statistic is null
     * @throws IllegalArgumentException if entityType is null
     * @throws IllegalArgumentException if newValue is negative
     * @throws IllegalArgumentException if the given parameter is not valid
     *                                  for the statistic
     */
    @Override
    public void setStatistic(Statistic statistic, EntityType entityType, int newValue) {

    }

    /**
     * Sets the Location where the player will spawn at their bed.
     *
     * @param location where to set the respawn location
     */
    @Override
    public void setBedSpawnLocation(Location location) {

    }

    /**
     * Sets the Location where the player will spawn at their bed.
     *
     * @param location where to set the respawn location
     * @param force    whether to forcefully set the respawn location even if a
     */
    @Override
    public void setBedSpawnLocation(Location location, boolean force) {

    }

    /**
     * Play a note for a player at a location. This requires a note block
     * at the particular location (as far as the client is concerned). This
     * will not work without a note block. This will not work with cake.
     *
     * @param loc        The location of a note block.
     * @param instrument The instrument ID.
     * @param note       The note ID.
     * @deprecated Magic value
     */
    @Override
    public void playNote(Location loc, byte instrument, byte note) {

    }

    /**
     * Play a note for a player at a location. This requires a note block
     * at the particular location (as far as the client is concerned). This
     * will not work without a note block. This will not work with cake.
     *
     * @param loc        The location of a note block
     * @param instrument The instrument
     * @param note       The note
     */
    @Override
    public void playNote(Location loc, Instrument instrument, Note note) {

    }

    /**
     * Play a sound for a player at the location.
     * <p>
     * This function will fail silently if Location or Sound are null.
     *
     * @param location The location to play the sound
     * @param sound    The sound to play
     * @param volume   The volume of the sound
     * @param pitch    The pitch of the sound
     */
    @Override
    public void playSound(Location location, Sound sound, float volume, float pitch) {

    }

    /**
     * Play a sound for a player at the location.
     * <p>
     * This function will fail silently if Location or Sound are null. No
     * sound will be heard by the player if their client does not have the
     * respective sound for the value passed.
     *
     * @param location the location to play the sound
     * @param sound    the internal sound name to play
     * @param volume   the volume of the sound
     * @param pitch    the pitch of the sound
     */
    @Override
    public void playSound(Location location, String sound, float volume, float pitch) {

    }

    /**
     * Play a sound for a player at the location.
     * <p>
     * This function will fail silently if Location or Sound are null.
     *
     * @param location The location to play the sound
     * @param sound    The sound to play
     * @param category The category of the sound
     * @param volume   The volume of the sound
     * @param pitch    The pitch of the sound
     */
    @Override
    public void playSound(Location location, Sound sound, SoundCategory category, float volume, float pitch) {

    }

    /**
     * Play a sound for a player at the location.
     * <p>
     * This function will fail silently if Location or Sound are null. No sound
     * will be heard by the player if their client does not have the respective
     * sound for the value passed.
     *
     * @param location the location to play the sound
     * @param sound    the internal sound name to play
     * @param category The category of the sound
     * @param volume   the volume of the sound
     * @param pitch    the pitch of the sound
     */
    @Override
    public void playSound(Location location, String sound, SoundCategory category, float volume, float pitch) {

    }

    /**
     * Stop the specified sound from playing.
     *
     * @param sound the sound to stop
     */
    @Override
    public void stopSound(Sound sound) {

    }

    /**
     * Stop the specified sound from playing.
     *
     * @param sound the sound to stop
     */
    @Override
    public void stopSound(String sound) {

    }

    /**
     * Stop the specified sound from playing.
     *
     * @param sound    the sound to stop
     * @param category the category of the sound
     */
    @Override
    public void stopSound(Sound sound, SoundCategory category) {

    }

    /**
     * Stop the specified sound from playing.
     *
     * @param sound    the sound to stop
     * @param category the category of the sound
     */
    @Override
    public void stopSound(String sound, SoundCategory category) {

    }

    /**
     * Plays an effect to just this player.
     *
     * @param loc    the location to play the effect at
     * @param effect the {@link Effect}
     * @param data   a data bit needed for some effects
     * @deprecated Magic value
     */
    @Override
    public void playEffect(Location loc, Effect effect, int data) {

    }

    /**
     * Plays an effect to just this player.
     *
     * @param loc    the location to play the effect at
     * @param effect the {@link Effect}
     * @param data   a data bit needed for some effects
     */
    @Override
    public <T> void playEffect(Location loc, Effect effect, T data) {

    }

    /**
     * Send a block change. This fakes a block change packet for a user at a
     * certain location. This will not actually change the world in any way.
     *
     * @param loc      The location of the changed block
     * @param material The new block
     * @param data     The block data
     * @deprecated Magic value
     */
    @Override
    public void sendBlockChange(Location loc, Material material, byte data) {

    }

    /**
     * Send a block change. This fakes a block change packet for a user at a
     * certain location. This will not actually change the world in any way.
     *
     * @param loc   The location of the changed block
     * @param block The new block
     */
    @Override
    public void sendBlockChange(Location loc, BlockData block) {

    }

    /**
     * Send block damage. This fakes block break progress for a user at a
     * certain location. This will not actually change the block's break
     * progress in any way.
     *
     * @param loc      the location of the damaged block
     * @param progress the progress from 0.0 - 1.0 where 0 is no damage and
     */
    @Override
    public void sendBlockDamage(Location loc, float progress) {

    }

    /**
     * Send a chunk change. This fakes a chunk change packet for a user at a
     * certain location. The updated cuboid must be entirely within a single
     * chunk. This will not actually change the world in any way.
     * <p>
     * At least one of the dimensions of the cuboid must be even. The size of
     * the data buffer must be 2.5*sx*sy*sz and formatted in accordance with
     * the Packet51 format.
     *
     * @param loc  The location of the cuboid
     * @param sx   The x size of the cuboid
     * @param sy   The y size of the cuboid
     * @param sz   The z size of the cuboid
     * @param data The data to be sent
     * @return true if the chunk change packet was sent
     * @deprecated Magic value
     */
    @Override
    public boolean sendChunkChange(Location loc, int sx, int sy, int sz, byte[] data) {
        return false;
    }

    /**
     * Send a sign change. This fakes a sign change packet for a user at
     * a certain location. This will not actually change the world in any way.
     * This method will use a sign at the location's block or a faked sign
     * sent via
     * {@link #sendBlockChange(Location, Material, byte)}.
     * <p>
     * If the client does not have a sign at the given location it will
     * display an error message to the user.
     *
     * @param loc   the location of the sign
     * @param lines the new text on the sign or null to clear it
     * @throws IllegalArgumentException if location is null
     * @throws IllegalArgumentException if lines is non-null and has a length less than 4
     */
    @Override
    public void sendSignChange(Location loc, String[] lines) throws IllegalArgumentException {

    }

    /**
     * Send a sign change. This fakes a sign change packet for a user at
     * a certain location. This will not actually change the world in any way.
     * This method will use a sign at the location's block or a faked sign
     * sent via
     * {@link #sendBlockChange(Location, Material, byte)}.
     * <p>
     * If the client does not have a sign at the given location it will
     * display an error message to the user.
     *
     * @param loc      the location of the sign
     * @param lines    the new text on the sign or null to clear it
     * @param dyeColor the color of the sign
     * @throws IllegalArgumentException if location is null
     * @throws IllegalArgumentException if dyeColor is null
     * @throws IllegalArgumentException if lines is non-null and has a length less than 4
     */
    @Override
    public void sendSignChange(Location loc, String[] lines, DyeColor dyeColor) throws IllegalArgumentException {

    }

    /**
     * Render a map and send it to the player in its entirety. This may be
     * used when streaming the map in the normal manner is not desirable.
     *
     * @param map The map to be sent
     */
    @Override
    public void sendMap(MapView map) {

    }

    /**
     * Forces an update of the player's entire inventory.
     */
    @Override
    public void updateInventory() {

    }

    /**
     * Sets the current time on the player's client. When relative is true the
     * player's time will be kept synchronized to its world time with the
     * specified offset.
     * <p>
     * When using non relative time the player's time will stay fixed at the
     * specified time parameter. It's up to the caller to continue updating
     * the player's time. To restore player time to normal use
     * resetPlayerTime().
     *
     * @param time     The current player's perceived time or the player's time
     *                 offset from the server time.
     * @param relative When true the player time is kept relative to its world
     */
    @Override
    public void setPlayerTime(long time, boolean relative) {

    }

    /**
     * Returns the player's current timestamp.
     *
     * @return The player's time
     */
    @Override
    public long getPlayerTime() {
        return 0;
    }

    /**
     * Returns the player's current time offset relative to server time, or
     * the current player's fixed time if the player's time is absolute.
     *
     * @return The player's time
     */
    @Override
    public long getPlayerTimeOffset() {
        return 0;
    }

    /**
     * Returns true if the player's time is relative to the server time,
     * otherwise the player's time is absolute and will not change its current
     * time unless done so with setPlayerTime().
     *
     * @return true if the player's time is relative to the server time.
     */
    @Override
    public boolean isPlayerTimeRelative() {
        return false;
    }

    /**
     * Restores the normal condition where the player's time is synchronized
     * with the server time.
     * <p>
     * Equivalent to calling setPlayerTime(0, true).
     */
    @Override
    public void resetPlayerTime() {

    }

    /**
     * Sets the type of weather the player will see.  When used, the weather
     * status of the player is locked until {@link #resetPlayerWeather()} is
     * used.
     *
     * @param type The WeatherType enum type the player should experience
     */
    @Override
    public void setPlayerWeather(WeatherType type) {

    }

    /**
     * Returns the type of weather the player is currently experiencing.
     *
     * @return The WeatherType that the player is currently experiencing or
     * null if player is seeing server weather.
     */
    @Override
    public WeatherType getPlayerWeather() {
        return null;
    }

    /**
     * Restores the normal condition where the player's weather is controlled
     * by server conditions.
     */
    @Override
    public void resetPlayerWeather() {

    }

    /**
     * Gives the player the amount of experience specified.
     *
     * @param amount Exp amount to give
     */
    @Override
    public void giveExp(int amount) {

    }

    /**
     * Gives the player the amount of experience levels specified. Levels can
     * be taken by specifying a negative amount.
     *
     * @param amount amount of experience levels to give or take
     */
    @Override
    public void giveExpLevels(int amount) {

    }

    /**
     * Gets the players current experience points towards the next level.
     * <p>
     * This is a percentage value. 0 is "no progress" and 1 is "next level".
     *
     * @return Current experience points
     */
    @Override
    public float getExp() {
        return 0;
    }

    /**
     * Sets the players current experience points towards the next level
     * <p>
     * This is a percentage value. 0 is "no progress" and 1 is "next level".
     *
     * @param exp New experience points
     */
    @Override
    public void setExp(float exp) {

    }

    /**
     * Gets the players current experience level
     *
     * @return Current experience level
     */
    @Override
    public int getLevel() {
        return 0;
    }

    /**
     * Sets the players current experience level
     *
     * @param level New experience level
     */
    @Override
    public void setLevel(int level) {

    }

    /**
     * Gets the players total experience points.
     * <br>
     * This refers to the total amount of experience the player has collected
     * over time and is not currently displayed to the client.
     *
     * @return Current total experience points
     */
    @Override
    public int getTotalExperience() {
        return 0;
    }

    /**
     * Sets the players current experience points.
     * <br>
     * This refers to the total amount of experience the player has collected
     * over time and is not currently displayed to the client.
     *
     * @param exp New total experience points
     */
    @Override
    public void setTotalExperience(int exp) {

    }

    /**
     * Send an experience change.
     * <p>
     * This fakes an experience change packet for a user. This will not actually
     * change the experience points in any way.
     *
     * @param progress Experience progress percentage (between 0.0 and 1.0)
     * @see #setExp(float)
     */
    @Override
    public void sendExperienceChange(float progress) {

    }

    /**
     * Send an experience change.
     * <p>
     * This fakes an experience change packet for a user. This will not actually
     * change the experience points in any way.
     *
     * @param progress New experience progress percentage (between 0.0 and 1.0)
     * @param level    New experience level
     * @see #setExp(float)
     * @see #setLevel(int)
     */
    @Override
    public void sendExperienceChange(float progress, int level) {

    }

    /**
     * Determines if the Player is allowed to fly via jump key double-tap like
     * in creative mode.
     *
     * @return True if the player is allowed to fly.
     */
    @Override
    public boolean getAllowFlight() {
        return false;
    }

    /**
     * Sets if the Player is allowed to fly via jump key double-tap like in
     * creative mode.
     *
     * @param flight If flight should be allowed.
     */
    @Override
    public void setAllowFlight(boolean flight) {

    }

    /**
     * Hides a player from this player
     *
     * @param player Player to hide
     * @deprecated see {@link #hidePlayer(Plugin, Player)}
     */
    @Override
    public void hidePlayer(Player player) {

    }

    /**
     * Hides a player from this player
     *
     * @param plugin Plugin that wants to hide the player
     * @param player Player to hide
     */
    @Override
    public void hidePlayer(Plugin plugin, Player player) {

    }

    /**
     * Allows this player to see a player that was previously hidden
     *
     * @param player Player to show
     * @deprecated see {@link #showPlayer(Plugin, Player)}
     */
    @Override
    public void showPlayer(Player player) {

    }

    /**
     * Allows this player to see a player that was previously hidden. If
     * another another plugin had hidden the player too, then the player will
     * remain hidden until the other plugin calls this method too.
     *
     * @param plugin Plugin that wants to show the player
     * @param player Player to show
     */
    @Override
    public void showPlayer(Plugin plugin, Player player) {

    }

    /**
     * Checks to see if a player has been hidden from this player
     *
     * @param player Player to check
     * @return True if the provided player is not being hidden from this
     * player
     */
    @Override
    public boolean canSee(Player player) {
        return false;
    }

    /**
     * Checks to see if this player is currently flying or not.
     *
     * @return True if the player is flying, else false.
     */
    @Override
    public boolean isFlying() {
        return false;
    }

    /**
     * Makes this player start or stop flying.
     *
     * @param value True to fly.
     */
    @Override
    public void setFlying(boolean value) {

    }

    /**
     * Sets the speed at which a client will fly. Negative values indicate
     * reverse directions.
     *
     * @param value The new speed, from -1 to 1.
     * @throws IllegalArgumentException If new speed is less than -1 or
     *                                  greater than 1
     */
    @Override
    public void setFlySpeed(float value) throws IllegalArgumentException {

    }

    /**
     * Sets the speed at which a client will walk. Negative values indicate
     * reverse directions.
     *
     * @param value The new speed, from -1 to 1.
     * @throws IllegalArgumentException If new speed is less than -1 or
     *                                  greater than 1
     */
    @Override
    public void setWalkSpeed(float value) throws IllegalArgumentException {

    }

    /**
     * Gets the current allowed speed that a client can fly.
     *
     * @return The current allowed speed, from -1 to 1
     */
    @Override
    public float getFlySpeed() {
        return 0;
    }

    /**
     * Gets the current allowed speed that a client can walk.
     *
     * @return The current allowed speed, from -1 to 1
     */
    @Override
    public float getWalkSpeed() {
        return 0;
    }

    /**
     * Request that the player's client download and switch texture packs.
     * <p>
     * The player's client will download the new texture pack asynchronously
     * in the background, and will automatically switch to it once the
     * download is complete. If the client has downloaded and cached the same
     * texture pack in the past, it will perform a file size check against
     * the response content to determine if the texture pack has changed and
     * needs to be downloaded again. When this request is sent for the very
     * first time from a given server, the client will first display a
     * confirmation GUI to the player before proceeding with the download.
     * <p>
     * Notes:
     * <ul>
     * <li>Players can disable server textures on their client, in which
     *     case this method will have no affect on them. Use the
     *     {@link PlayerResourcePackStatusEvent} to figure out whether or not
     *     the player loaded the pack!
     * <li>There is no concept of resetting texture packs back to default
     *     within Minecraft, so players will have to relog to do so or you
     *     have to send an empty pack.
     * <li>The request is send with "null" as the hash. This might result
     *     in newer versions not loading the pack correctly.
     * </ul>
     *
     * @param url The URL from which the client will download the texture
     *            pack. The string must contain only US-ASCII characters and should
     *            be encoded as per RFC 1738.
     * @throws IllegalArgumentException Thrown if the URL is null.
     * @throws IllegalArgumentException Thrown if the URL is too long.
     * @deprecated Minecraft no longer uses textures packs. Instead you
     * should use {@link #setResourcePack(String)}.
     */
    @Override
    public void setTexturePack(String url) {

    }

    /**
     * Request that the player's client download and switch resource packs.
     * <p>
     * The player's client will download the new resource pack asynchronously
     * in the background, and will automatically switch to it once the
     * download is complete. If the client has downloaded and cached the same
     * resource pack in the past, it will perform a file size check against
     * the response content to determine if the resource pack has changed and
     * needs to be downloaded again. When this request is sent for the very
     * first time from a given server, the client will first display a
     * confirmation GUI to the player before proceeding with the download.
     * <p>
     * Notes:
     * <ul>
     * <li>Players can disable server resources on their client, in which
     *     case this method will have no affect on them. Use the
     *     {@link PlayerResourcePackStatusEvent} to figure out whether or not
     *     the player loaded the pack!
     * <li>There is no concept of resetting resource packs back to default
     *     within Minecraft, so players will have to relog to do so or you
     *     have to send an empty pack.
     * <li>The request is send with "null" as the hash. This might result
     *     in newer versions not loading the pack correctly.
     * </ul>
     *
     * @param url The URL from which the client will download the resource
     *            pack. The string must contain only US-ASCII characters and should
     *            be encoded as per RFC 1738.
     * @throws IllegalArgumentException Thrown if the URL is null.
     * @throws IllegalArgumentException Thrown if the URL is too long. The
     *                                  length restriction is an implementation specific arbitrary value.
     */
    @Override
    public void setResourcePack(String url) {

    }

    /**
     * Request that the player's client download and switch resource packs.
     * <p>
     * The player's client will download the new resource pack asynchronously
     * in the background, and will automatically switch to it once the
     * download is complete. If the client has downloaded and cached a
     * resource pack with the same hash in the past it will not download but
     * directly apply the cached pack. When this request is sent for the very
     * first time from a given server, the client will first display a
     * confirmation GUI to the player before proceeding with the download.
     * <p>
     * Notes:
     * <ul>
     * <li>Players can disable server resources on their client, in which
     *     case this method will have no affect on them. Use the
     *     {@link PlayerResourcePackStatusEvent} to figure out whether or not
     *     the player loaded the pack!
     * <li>There is no concept of resetting resource packs back to default
     *     within Minecraft, so players will have to relog to do so or you
     *     have to send an empty pack.
     * </ul>
     *
     * @param url  The URL from which the client will download the resource
     *             pack. The string must contain only US-ASCII characters and should
     *             be encoded as per RFC 1738.
     * @param hash The sha1 hash sum of the resource pack file which is used
     *             to apply a cached version of the pack directly without downloading
     *             if it is available. Hast to be 20 bytes long!
     * @throws IllegalArgumentException Thrown if the URL is null.
     * @throws IllegalArgumentException Thrown if the URL is too long. The
     *                                  length restriction is an implementation specific arbitrary value.
     * @throws IllegalArgumentException Thrown if the hash is null.
     * @throws IllegalArgumentException Thrown if the hash is not 20 bytes
     *                                  long.
     */
    @Override
    public void setResourcePack(String url, byte[] hash) {

    }

    /**
     * Gets the Scoreboard displayed to this player
     *
     * @return The current scoreboard seen by this player
     */
    @Override
    public Scoreboard getScoreboard() {
        return null;
    }

    /**
     * Sets the player's visible Scoreboard.
     *
     * @param scoreboard New Scoreboard for the player
     * @throws IllegalArgumentException if scoreboard is null
     * @throws IllegalArgumentException if scoreboard was not created by the
     *                                  {@link ScoreboardManager scoreboard manager}
     * @throws IllegalStateException    if this is a player that is not logged
     *                                  yet or has logged out
     */
    @Override
    public void setScoreboard(Scoreboard scoreboard) throws IllegalArgumentException, IllegalStateException {

    }

    /**
     * Gets if the client is displayed a 'scaled' health, that is, health on a
     * scale from 0-{@link #getHealthScale()}.
     *
     * @return if client health display is scaled
     * @see Player#setHealthScaled(boolean)
     */
    @Override
    public boolean isHealthScaled() {
        return false;
    }

    /**
     * Sets if the client is displayed a 'scaled' health, that is, health on a
     * scale from 0-{@link #getHealthScale()}.
     * <p>
     * Displayed health follows a simple formula <code>displayedHealth =
     * getHealth() / getMaxHealth() * getHealthScale()</code>.
     *
     * @param scale if the client health display is scaled
     */
    @Override
    public void setHealthScaled(boolean scale) {

    }

    /**
     * Sets the number to scale health to for the client; this will also
     * {@link #setHealthScaled(boolean) setHealthScaled(true)}.
     * <p>
     * Displayed health follows a simple formula <code>displayedHealth =
     * getHealth() / getMaxHealth() * getHealthScale()</code>.
     *
     * @param scale the number to scale health to
     * @throws IllegalArgumentException if scale is &lt;0
     * @throws IllegalArgumentException if scale is {@link Double#NaN}
     * @throws IllegalArgumentException if scale is too high
     */
    @Override
    public void setHealthScale(double scale) throws IllegalArgumentException {

    }

    /**
     * Gets the number that health is scaled to for the client.
     *
     * @return the number that health would be scaled to for the client if
     * HealthScaling is set to true
     * @see Player#setHealthScale(double)
     * @see Player#setHealthScaled(boolean)
     */
    @Override
    public double getHealthScale() {
        return 0;
    }

    /**
     * Gets the entity which is followed by the camera when in
     * {@link GameMode#SPECTATOR}.
     *
     * @return the followed entity, or null if not in spectator mode or not
     * following a specific entity.
     */
    @Override
    public Entity getSpectatorTarget() {
        return null;
    }

    /**
     * Sets the entity which is followed by the camera when in
     * {@link GameMode#SPECTATOR}.
     *
     * @param entity the entity to follow or null to reset
     * @throws IllegalStateException if the player is not in
     *                               {@link GameMode#SPECTATOR}
     */
    @Override
    public void setSpectatorTarget(Entity entity) {

    }

    /**
     * Sends a title and a subtitle message to the player. If either of these
     * values are null, they will not be sent and the display will remain
     * unchanged. If they are empty strings, the display will be updated as
     * such. If the strings contain a new line, only the first line will be
     * sent. The titles will be displayed with the client's default timings.
     *
     * @param title    Title text
     * @param subtitle Subtitle text
     * @deprecated API behavior subject to change
     */
    @Override
    public void sendTitle(String title, String subtitle) {

    }

    /**
     * Sends a title and a subtitle message to the player. If either of these
     * values are null, they will not be sent and the display will remain
     * unchanged. If they are empty strings, the display will be updated as
     * such. If the strings contain a new line, only the first line will be
     * sent. All timings values may take a value of -1 to indicate that they
     * will use the last value sent (or the defaults if no title has been
     * displayed).
     *
     * @param title    Title text
     * @param subtitle Subtitle text
     * @param fadeIn   time in ticks for titles to fade in. Defaults to 10.
     * @param stay     time in ticks for titles to stay. Defaults to 70.
     * @param fadeOut  time in ticks for titles to fade out. Defaults to 20.
     */
    @Override
    public void sendTitle(String title, String subtitle, int fadeIn, int stay, int fadeOut) {

    }

    /**
     * Resets the title displayed to the player. This will clear the displayed
     * title / subtitle and reset timings to their default values.
     */
    @Override
    public void resetTitle() {

    }

    /**
     * Spawns the particle (the number of times specified by count)
     * at the target location.
     *
     * @param particle the particle to spawn
     * @param location the location to spawn at
     * @param count    the number of particles
     */
    @Override
    public void spawnParticle(Particle particle, Location location, int count) {

    }

    /**
     * Spawns the particle (the number of times specified by count)
     * at the target location.
     *
     * @param particle the particle to spawn
     * @param x        the position on the x axis to spawn at
     * @param y        the position on the y axis to spawn at
     * @param z        the position on the z axis to spawn at
     * @param count    the number of particles
     */
    @Override
    public void spawnParticle(Particle particle, double x, double y, double z, int count) {

    }

    /**
     * Spawns the particle (the number of times specified by count)
     * at the target location.
     *
     * @param particle the particle to spawn
     * @param location the location to spawn at
     * @param count    the number of particles
     * @param data     the data to use for the particle or null,
     *                 the type of this depends on {@link Particle#getDataType()}
     */
    @Override
    public <T> void spawnParticle(Particle particle, Location location, int count, T data) {

    }

    /**
     * Spawns the particle (the number of times specified by count)
     * at the target location.
     *
     * @param particle the particle to spawn
     * @param x        the position on the x axis to spawn at
     * @param y        the position on the y axis to spawn at
     * @param z        the position on the z axis to spawn at
     * @param count    the number of particles
     * @param data     the data to use for the particle or null,
     *                 the type of this depends on {@link Particle#getDataType()}
     */
    @Override
    public <T> void spawnParticle(Particle particle, double x, double y, double z, int count, T data) {

    }

    /**
     * Spawns the particle (the number of times specified by count)
     * at the target location. The position of each particle will be
     * randomized positively and negatively by the offset parameters
     * on each axis.
     *
     * @param particle the particle to spawn
     * @param location the location to spawn at
     * @param count    the number of particles
     * @param offsetX  the maximum random offset on the X axis
     * @param offsetY  the maximum random offset on the Y axis
     * @param offsetZ  the maximum random offset on the Z axis
     */
    @Override
    public void spawnParticle(Particle particle, Location location, int count, double offsetX, double offsetY, double offsetZ) {

    }

    /**
     * Spawns the particle (the number of times specified by count)
     * at the target location. The position of each particle will be
     * randomized positively and negatively by the offset parameters
     * on each axis.
     *
     * @param particle the particle to spawn
     * @param x        the position on the x axis to spawn at
     * @param y        the position on the y axis to spawn at
     * @param z        the position on the z axis to spawn at
     * @param count    the number of particles
     * @param offsetX  the maximum random offset on the X axis
     * @param offsetY  the maximum random offset on the Y axis
     * @param offsetZ  the maximum random offset on the Z axis
     */
    @Override
    public void spawnParticle(Particle particle, double x, double y, double z, int count, double offsetX, double offsetY, double offsetZ) {

    }

    /**
     * Spawns the particle (the number of times specified by count)
     * at the target location. The position of each particle will be
     * randomized positively and negatively by the offset parameters
     * on each axis.
     *
     * @param particle the particle to spawn
     * @param location the location to spawn at
     * @param count    the number of particles
     * @param offsetX  the maximum random offset on the X axis
     * @param offsetY  the maximum random offset on the Y axis
     * @param offsetZ  the maximum random offset on the Z axis
     * @param data     the data to use for the particle or null,
     *                 the type of this depends on {@link Particle#getDataType()}
     */
    @Override
    public <T> void spawnParticle(Particle particle, Location location, int count, double offsetX, double offsetY, double offsetZ, T data) {

    }

    /**
     * Spawns the particle (the number of times specified by count)
     * at the target location. The position of each particle will be
     * randomized positively and negatively by the offset parameters
     * on each axis.
     *
     * @param particle the particle to spawn
     * @param x        the position on the x axis to spawn at
     * @param y        the position on the y axis to spawn at
     * @param z        the position on the z axis to spawn at
     * @param count    the number of particles
     * @param offsetX  the maximum random offset on the X axis
     * @param offsetY  the maximum random offset on the Y axis
     * @param offsetZ  the maximum random offset on the Z axis
     * @param data     the data to use for the particle or null,
     *                 the type of this depends on {@link Particle#getDataType()}
     */
    @Override
    public <T> void spawnParticle(Particle particle, double x, double y, double z, int count, double offsetX, double offsetY, double offsetZ, T data) {

    }

    /**
     * Spawns the particle (the number of times specified by count)
     * at the target location. The position of each particle will be
     * randomized positively and negatively by the offset parameters
     * on each axis.
     *
     * @param particle the particle to spawn
     * @param location the location to spawn at
     * @param count    the number of particles
     * @param offsetX  the maximum random offset on the X axis
     * @param offsetY  the maximum random offset on the Y axis
     * @param offsetZ  the maximum random offset on the Z axis
     * @param extra    the extra data for this particle, depends on the
     */
    @Override
    public void spawnParticle(Particle particle, Location location, int count, double offsetX, double offsetY, double offsetZ, double extra) {

    }

    /**
     * Spawns the particle (the number of times specified by count)
     * at the target location. The position of each particle will be
     * randomized positively and negatively by the offset parameters
     * on each axis.
     *
     * @param particle the particle to spawn
     * @param x        the position on the x axis to spawn at
     * @param y        the position on the y axis to spawn at
     * @param z        the position on the z axis to spawn at
     * @param count    the number of particles
     * @param offsetX  the maximum random offset on the X axis
     * @param offsetY  the maximum random offset on the Y axis
     * @param offsetZ  the maximum random offset on the Z axis
     * @param extra    the extra data for this particle, depends on the
     */
    @Override
    public void spawnParticle(Particle particle, double x, double y, double z, int count, double offsetX, double offsetY, double offsetZ, double extra) {

    }

    /**
     * Spawns the particle (the number of times specified by count)
     * at the target location. The position of each particle will be
     * randomized positively and negatively by the offset parameters
     * on each axis.
     *
     * @param particle the particle to spawn
     * @param location the location to spawn at
     * @param count    the number of particles
     * @param offsetX  the maximum random offset on the X axis
     * @param offsetY  the maximum random offset on the Y axis
     * @param offsetZ  the maximum random offset on the Z axis
     * @param extra    the extra data for this particle, depends on the
     *                 particle used (normally speed)
     * @param data     the data to use for the particle or null,
     *                 the type of this depends on {@link Particle#getDataType()}
     */
    @Override
    public <T> void spawnParticle(Particle particle, Location location, int count, double offsetX, double offsetY, double offsetZ, double extra, T data) {

    }

    /**
     * Spawns the particle (the number of times specified by count)
     * at the target location. The position of each particle will be
     * randomized positively and negatively by the offset parameters
     * on each axis.
     *
     * @param particle the particle to spawn
     * @param x        the position on the x axis to spawn at
     * @param y        the position on the y axis to spawn at
     * @param z        the position on the z axis to spawn at
     * @param count    the number of particles
     * @param offsetX  the maximum random offset on the X axis
     * @param offsetY  the maximum random offset on the Y axis
     * @param offsetZ  the maximum random offset on the Z axis
     * @param extra    the extra data for this particle, depends on the
     *                 particle used (normally speed)
     * @param data     the data to use for the particle or null,
     *                 the type of this depends on {@link Particle#getDataType()}
     */
    @Override
    public <T> void spawnParticle(Particle particle, double x, double y, double z, int count, double offsetX, double offsetY, double offsetZ, double extra, T data) {

    }

    /**
     * Return the player's progression on the specified advancement.
     *
     * @param advancement advancement
     * @return object detailing the player's progress
     */
    @Override
    public AdvancementProgress getAdvancementProgress(Advancement advancement) {
        return null;
    }

    /**
     * Get the player's current client side view distance.
     * <br>
     * Will default to the server view distance if the client has not yet
     * communicated this information,
     *
     * @return client view distance as above
     */
    @Override
    public int getClientViewDistance() {
        return 0;
    }

    /**
     * Gets the player's current locale.
     * <p>
     * The value of the locale String is not defined properly.
     * <br>
     * The vanilla Minecraft client will use lowercase language / country pairs
     * separated by an underscore, but custom resource packs may use any format
     * they wish.
     *
     * @return the player's locale
     */
    @Override
    public String getLocale() {
        return null;
    }

    /**
     * Update the list of commands sent to the client.
     * <br>
     * Generally useful to ensure the client has a complete list of commands
     * after permission changes are done.
     */
    @Override
    public void updateCommands() {

    }

    /**
     * Open a {@link Material#WRITTEN_BOOK} for a Player
     *
     * @param book The book to open for this player
     */
    @Override
    public void openBook(ItemStack book) {

    }

    @Override
    public Spigot spigot() {
        return null;
    }

    /**
     * Creates a Map representation of this class.
     * <p>
     * This class must provide a method to restore this class, as defined in
     * the {@link ConfigurationSerializable} interface javadocs.
     *
     * @return Map containing the current state of this class
     */
    @Override
    public Map<String, Object> serialize() {
        return null;
    }

    /**
     * Returns the name of this player
     *
     * @return Player name
     */
    @Override
    public String getName() {
        return null;
    }

    /**
     * Get the player's inventory.
     *
     * @return The inventory of the player, this also contains the armor
     * slots.
     */
    @Override
    public PlayerInventory getInventory() {
        return null;
    }

    /**
     * Get the player's EnderChest inventory
     *
     * @return The EnderChest of the player
     */
    @Override
    public Inventory getEnderChest() {
        return null;
    }

    /**
     * Gets the player's selected main hand
     *
     * @return the players main hand
     */
    @Override
    public MainHand getMainHand() {
        return null;
    }

    /**
     * If the player currently has an inventory window open, this method will
     * set a property of that window, such as the state of a progress bar.
     *
     * @param prop  The property.
     * @param value The value to set the property to.
     * @return True if the property was successfully set.
     */
    @Override
    public boolean setWindowProperty(InventoryView.Property prop, int value) {
        return false;
    }

    /**
     * Gets the inventory view the player is currently viewing. If they do not
     * have an inventory window open, it returns their internal crafting view.
     *
     * @return The inventory view.
     */
    @Override
    public InventoryView getOpenInventory() {
        return null;
    }

    /**
     * Opens an inventory window with the specified inventory on the top and
     * the player's inventory on the bottom.
     *
     * @param inventory The inventory to open
     * @return The newly opened inventory view
     */
    @Override
    public InventoryView openInventory(Inventory inventory) {
        return null;
    }

    /**
     * Opens an empty workbench inventory window with the player's inventory
     * on the bottom.
     *
     * @param location The location to attach it to. If null, the player's
     *                 location is used.
     * @param force    If false, and there is no workbench block at the location,
     *                 no inventory will be opened and null will be returned.
     * @return The newly opened inventory view, or null if it could not be
     * opened.
     */
    @Override
    public InventoryView openWorkbench(Location location, boolean force) {
        return null;
    }

    /**
     * Opens an empty enchanting inventory window with the player's inventory
     * on the bottom.
     *
     * @param location The location to attach it to. If null, the player's
     *                 location is used.
     * @param force    If false, and there is no enchanting table at the
     *                 location, no inventory will be opened and null will be returned.
     * @return The newly opened inventory view, or null if it could not be
     * opened.
     */
    @Override
    public InventoryView openEnchanting(Location location, boolean force) {
        return null;
    }

    /**
     * Opens an inventory window to the specified inventory view.
     *
     * @param inventory The view to open
     */
    @Override
    public void openInventory(InventoryView inventory) {

    }

    /**
     * Starts a trade between the player and the villager.
     * <p>
     * Note that only one player may trade with a villager at once. You must use
     * the force parameter for this.
     *
     * @param trader The merchant to trade with. Cannot be null.
     * @param force  whether to force the trade even if another player is trading
     * @return The newly opened inventory view, or null if it could not be
     * opened.
     */
    @Override
    public InventoryView openMerchant(Villager trader, boolean force) {
        return null;
    }

    /**
     * Starts a trade between the player and the merchant.
     * <p>
     * Note that only one player may trade with a merchant at once. You must use
     * the force parameter for this.
     *
     * @param merchant The merchant to trade with. Cannot be null.
     * @param force    whether to force the trade even if another player is trading
     * @return The newly opened inventory view, or null if it could not be
     * opened.
     */
    @Override
    public InventoryView openMerchant(Merchant merchant, boolean force) {
        return null;
    }

    /**
     * Force-closes the currently open inventory view for this player, if any.
     */
    @Override
    public void closeInventory() {

    }

    /**
     * Returns the ItemStack currently in your hand, can be empty.
     *
     * @return The ItemStack of the item you are currently holding.
     * @deprecated Humans may now dual wield in their off hand, use explicit
     * methods in {@link PlayerInventory}.
     */
    @Override
    public ItemStack getItemInHand() {
        return null;
    }

    /**
     * Sets the item to the given ItemStack, this will replace whatever the
     * user was holding.
     *
     * @param item The ItemStack which will end up in the hand
     * @deprecated Humans may now dual wield in their off hand, use explicit
     * methods in {@link PlayerInventory}.
     */
    @Override
    public void setItemInHand(ItemStack item) {

    }

    /**
     * Returns the ItemStack currently on your cursor, can be empty. Will
     * always be empty if the player currently has no open window.
     *
     * @return The ItemStack of the item you are currently moving around.
     */
    @Override
    public ItemStack getItemOnCursor() {
        return null;
    }

    /**
     * Sets the item to the given ItemStack, this will replace whatever the
     * user was moving. Will always be empty if the player currently has no
     * open window.
     *
     * @param item The ItemStack which will end up in the hand
     */
    @Override
    public void setItemOnCursor(ItemStack item) {

    }

    /**
     * Check whether a cooldown is active on the specified material.
     *
     * @param material the material to check
     * @return if a cooldown is active on the material
     */
    @Override
    public boolean hasCooldown(Material material) {
        return false;
    }

    /**
     * Get the cooldown time in ticks remaining for the specified material.
     *
     * @param material the material to check
     * @return the remaining cooldown time in ticks
     */
    @Override
    public int getCooldown(Material material) {
        return 0;
    }

    /**
     * Set a cooldown on the specified material for a certain amount of ticks.
     * ticks. 0 ticks will result in the removal of the cooldown.
     * <p>
     * Cooldowns are used by the server for items such as ender pearls and
     * shields to prevent them from being used repeatedly.
     * <p>
     * Note that cooldowns will not by themselves stop an item from being used
     * for attacking.
     *
     * @param material the material to set the cooldown for
     * @param ticks    the amount of ticks to set or 0 to remove
     */
    @Override
    public void setCooldown(Material material, int ticks) {

    }

    /**
     * Get the sleep ticks of the player. This value may be capped.
     *
     * @return slumber ticks
     */
    @Override
    public int getSleepTicks() {
        return 0;
    }

    /**
     * Attempts to make the entity sleep at the given location.
     * <br>
     * The location must be in the current world and have a bed placed at the
     * location. The game may also enforce other requirements such as proximity
     * to bed, monsters, and dimension type if force is not set.
     *
     * @param location the location of the bed
     * @param force    whether to try and sleep at the location even if not
     *                 normally possible
     * @return whether the sleep was successful
     */
    @Override
    public boolean sleep(Location location, boolean force) {
        return false;
    }

    /**
     * Causes the player to wakeup if they are currently sleeping.
     *
     * @param setSpawnLocation whether to set their spawn location to the bed
     *                         they are currently sleeping in
     * @throws IllegalStateException if not sleeping
     */
    @Override
    public void wakeup(boolean setSpawnLocation) {

    }

    /**
     * Gets the location of the bed the player is currently sleeping in
     *
     * @return location
     * @throws IllegalStateException if not sleeping
     */
    @Override
    public Location getBedLocation() {
        return null;
    }

    /**
     * Gets this human's current {@link GameMode}
     *
     * @return Current game mode
     */
    @Override
    public GameMode getGameMode() {
        return null;
    }

    /**
     * Sets this human's current {@link GameMode}
     *
     * @param mode New game mode
     */
    @Override
    public void setGameMode(GameMode mode) {

    }

    /**
     * Check if the player is currently blocking (ie with a shield).
     *
     * @return Whether they are blocking.
     */
    @Override
    public boolean isBlocking() {
        return false;
    }

    /**
     * Check if the player currently has their hand raised (ie about to begin
     * blocking).
     *
     * @return Whether their hand is raised
     */
    @Override
    public boolean isHandRaised() {
        return false;
    }

    /**
     * Get the total amount of experience required for the player to level
     *
     * @return Experience required to level up
     */
    @Override
    public int getExpToLevel() {
        return 0;
    }

    /**
     * Gets the current cooldown for a player's attack.
     * <p>
     * This is used to calculate damage, with 1.0 representing a fully charged
     * attack and 0.0 representing a non-charged attack
     *
     * @return A float between 0.0-1.0 representing the progress of the charge
     */
    @Override
    public float getAttackCooldown() {
        return 0;
    }

    /**
     * Discover a recipe for this player such that it has not already been
     * discovered. This method will add the key's associated recipe to the
     * player's recipe book.
     *
     * @param recipe the key of the recipe to discover
     * @return whether or not the recipe was newly discovered
     */
    @Override
    public boolean discoverRecipe(NamespacedKey recipe) {
        return false;
    }

    /**
     * Discover a collection of recipes for this player such that they have not
     * already been discovered. This method will add the keys' associated
     * recipes to the player's recipe book. If a recipe in the provided
     * collection has already been discovered, it will be silently ignored.
     *
     * @param recipes the keys of the recipes to discover
     * @return the amount of newly discovered recipes where 0 indicates that
     * none were newly discovered and a number equal to {@code recipes.size()}
     * indicates that all were new
     */
    @Override
    public int discoverRecipes(Collection<NamespacedKey> recipes) {
        return 0;
    }

    /**
     * Undiscover a recipe for this player such that it has already been
     * discovered. This method will remove the key's associated recipe from the
     * player's recipe book.
     *
     * @param recipe the key of the recipe to undiscover
     * @return whether or not the recipe was successfully undiscovered (i.e. it
     * was previously discovered)
     */
    @Override
    public boolean undiscoverRecipe(NamespacedKey recipe) {
        return false;
    }

    /**
     * Undiscover a collection of recipes for this player such that they have
     * already been discovered. This method will remove the keys' associated
     * recipes from the player's recipe book. If a recipe in the provided
     * collection has not yet been discovered, it will be silently ignored.
     *
     * @param recipes the keys of the recipes to undiscover
     * @return the amount of undiscovered recipes where 0 indicates that none
     * were undiscovered and a number equal to {@code recipes.size()} indicates
     * that all were undiscovered
     */
    @Override
    public int undiscoverRecipes(Collection<NamespacedKey> recipes) {
        return 0;
    }

    /**
     * Check whether or not this entity has discovered the specified recipe.
     *
     * @param recipe the key of the recipe to check
     * @return true if discovered, false otherwise
     */
    @Override
    public boolean hasDiscoveredRecipe(NamespacedKey recipe) {
        return false;
    }

    /**
     * Get an immutable set of recipes this entity has discovered.
     *
     * @return all discovered recipes
     */
    @Override
    public Set<NamespacedKey> getDiscoveredRecipes() {
        return null;
    }

    /**
     * Gets the entity currently perched on the left shoulder or null if no
     * entity.
     * <br>
     * The returned entity will not be spawned within the world, so most
     * operations are invalid unless the entity is first spawned in.
     *
     * @return left shoulder entity
     * @deprecated There are currently no well defined semantics regarding
     * serialized entities in Bukkit. Use with care.
     */
    @Override
    public Entity getShoulderEntityLeft() {
        return null;
    }

    /**
     * Sets the entity currently perched on the left shoulder, or null to
     * remove. This method will remove the entity from the world.
     * <br>
     * Note that only a copy of the entity will be set to display on the
     * shoulder.
     * <br>
     * Also note that the client will currently only render {@link Parrot}
     * entities.
     *
     * @param entity left shoulder entity
     * @deprecated There are currently no well defined semantics regarding
     * serialized entities in Bukkit. Use with care.
     */
    @Override
    public void setShoulderEntityLeft(Entity entity) {

    }

    /**
     * Gets the entity currently perched on the right shoulder or null if no
     * entity.
     * <br>
     * The returned entity will not be spawned within the world, so most
     * operations are invalid unless the entity is first spawned in.
     *
     * @return right shoulder entity
     * @deprecated There are currently no well defined semantics regarding
     * serialized entities in Bukkit. Use with care.
     */
    @Override
    public Entity getShoulderEntityRight() {
        return null;
    }

    /**
     * Sets the entity currently perched on the right shoulder, or null to
     * remove. This method will remove the entity from the world.
     * <br>
     * Note that only a copy of the entity will be set to display on the
     * shoulder.
     * <br>
     * Also note that the client will currently only render {@link Parrot}
     * entities.
     *
     * @param entity right shoulder entity
     * @deprecated There are currently no well defined semantics regarding
     * serialized entities in Bukkit. Use with care.
     */
    @Override
    public void setShoulderEntityRight(Entity entity) {

    }

    /**
     * Make the entity drop the item in their hand.
     * <br>
     * This will force the entity to drop the item they are holding with
     * an option to drop the entire {@link ItemStack} or just 1 of the items.
     *
     * @param dropAll True to drop entire stack, false to drop 1 of the stack
     * @return True if item was dropped successfully
     */
    @Override
    public boolean dropItem(boolean dropAll) {
        return false;
    }

    /**
     * Gets the players current exhaustion level.
     * <p>
     * Exhaustion controls how fast the food level drops. While you have a
     * certain amount of exhaustion, your saturation will drop to zero, and
     * then your food will drop to zero.
     *
     * @return Exhaustion level
     */
    @Override
    public float getExhaustion() {
        return 0;
    }

    /**
     * Sets the players current exhaustion level
     *
     * @param value Exhaustion level
     */
    @Override
    public void setExhaustion(float value) {

    }

    /**
     * Gets the players current saturation level.
     * <p>
     * Saturation is a buffer for food level. Your food level will not drop if
     * you are saturated {@literal >} 0.
     *
     * @return Saturation level
     */
    @Override
    public float getSaturation() {
        return 0;
    }

    /**
     * Sets the players current saturation level
     *
     * @param value Saturation level
     */
    @Override
    public void setSaturation(float value) {

    }

    /**
     * Gets the players current food level
     *
     * @return Food level
     */
    @Override
    public int getFoodLevel() {
        return 0;
    }

    /**
     * Sets the players current food level
     *
     * @param value New food level
     */
    @Override
    public void setFoodLevel(int value) {

    }

    /**
     * Get the regeneration rate (1 health per x ticks) of
     * the HumanEntity when they have saturation and
     * their food level is {@literal >=} 20. Default is 10.
     *
     * @return the regeneration rate
     */
    @Override
    public int getSaturatedRegenRate() {
        return 0;
    }

    /**
     * Set the regeneration rate (1 health per x ticks) of
     * the HumanEntity when they have saturation and
     * their food level is {@literal >=} 20. Default is 10.
     * Not affected if the world's difficulty is peaceful.
     *
     * @param ticks the amount of ticks to gain 1 health.
     */
    @Override
    public void setSaturatedRegenRate(int ticks) {

    }

    /**
     * Get the regeneration rate (1 health per x ticks) of
     * the HumanEntity when they have no saturation and
     * their food level is {@literal >=} 18. Default is 80.
     *
     * @return the regeneration rate
     */
    @Override
    public int getUnsaturatedRegenRate() {
        return 0;
    }

    /**
     * Get the regeneration rate (1 health per x ticks) of
     * the HumanEntity when they have no saturation and
     * their food level is {@literal >=} 18. Default is 80.
     * Not affected if the world's difficulty is peaceful.
     *
     * @param ticks the amount of ticks to gain 1 health.
     */
    @Override
    public void setUnsaturatedRegenRate(int ticks) {

    }

    /**
     * Get the starvation rate (1 health per x ticks) of
     * the HumanEntity. Default is 80.
     *
     * @return the starvation rate
     */
    @Override
    public int getStarvationRate() {
        return 0;
    }

    /**
     * Get the starvation rate (1 health per x ticks) of
     * the HumanEntity. Default is 80.
     *
     * @param ticks the amount of ticks to lose 1 health
     */
    @Override
    public void setStarvationRate(int ticks) {

    }

    /**
     * Gets the height of the living entity's eyes above its Location.
     *
     * @return height of the living entity's eyes above its location
     */
    @Override
    public double getEyeHeight() {
        return 0;
    }

    /**
     * Gets the height of the living entity's eyes above its Location.
     *
     * @param ignorePose if set to true, the effects of pose changes, eg
     *                   sneaking and gliding will be ignored
     * @return height of the living entity's eyes above its location
     */
    @Override
    public double getEyeHeight(boolean ignorePose) {
        return 0;
    }

    /**
     * Get a Location detailing the current eye position of the living entity.
     *
     * @return a location at the eyes of the living entity
     */
    @Override
    public Location getEyeLocation() {
        return null;
    }

    /**
     * Gets all blocks along the living entity's line of sight.
     * <p>
     * This list contains all blocks from the living entity's eye position to
     * target inclusive. This method considers all blocks as 1x1x1 in size.
     *
     * @param transparent Set containing all transparent block Materials (set to
     *                    null for only air)
     * @param maxDistance this is the maximum distance to scan (may be limited
     *                    by server by at least 100 blocks, no less)
     * @return list containing all blocks along the living entity's line of
     * sight
     */
    @Override
    public List<Block> getLineOfSight(Set<Material> transparent, int maxDistance) {
        return null;
    }

    /**
     * Gets the block that the living entity has targeted.
     * <p>
     * This method considers all blocks as 1x1x1 in size. To take exact block
     * collision shapes into account, see {@link #getTargetBlockExact(int,
     * FluidCollisionMode)}.
     *
     * @param transparent Set containing all transparent block Materials (set to
     *                    null for only air)
     * @param maxDistance this is the maximum distance to scan (may be limited
     *                    by server by at least 100 blocks, no less)
     * @return block that the living entity has targeted
     */
    @Override
    public Block getTargetBlock(Set<Material> transparent, int maxDistance) {
        return null;
    }

    /**
     * Gets the last two blocks along the living entity's line of sight.
     * <p>
     * The target block will be the last block in the list. This method
     * considers all blocks as 1x1x1 in size.
     *
     * @param transparent Set containing all transparent block Materials (set to
     *                    null for only air)
     * @param maxDistance this is the maximum distance to scan. This may be
     *                    further limited by the server, but never to less than 100 blocks
     * @return list containing the last 2 blocks along the living entity's
     * line of sight
     */
    @Override
    public List<Block> getLastTwoTargetBlocks(Set<Material> transparent, int maxDistance) {
        return null;
    }

    /**
     * Gets the block that the living entity has targeted.
     * <p>
     * This takes the blocks' precise collision shapes into account. Fluids are
     * ignored.
     * <p>
     * This may cause loading of chunks! Some implementations may impose
     * artificial restrictions on the maximum distance.
     *
     * @param maxDistance the maximum distance to scan
     * @return block that the living entity has targeted
     * @see #getTargetBlockExact(int, FluidCollisionMode)
     */
    @Override
    public Block getTargetBlockExact(int maxDistance) {
        return null;
    }

    /**
     * Gets the block that the living entity has targeted.
     * <p>
     * This takes the blocks' precise collision shapes into account.
     * <p>
     * This may cause loading of chunks! Some implementations may impose
     * artificial restrictions on the maximum distance.
     *
     * @param maxDistance        the maximum distance to scan
     * @param fluidCollisionMode the fluid collision mode
     * @return block that the living entity has targeted
     * @see #rayTraceBlocks(double, FluidCollisionMode)
     */
    @Override
    public Block getTargetBlockExact(int maxDistance, FluidCollisionMode fluidCollisionMode) {
        return null;
    }

    /**
     * Performs a ray trace that provides information on the targeted block.
     * <p>
     * This takes the blocks' precise collision shapes into account. Fluids are
     * ignored.
     * <p>
     * This may cause loading of chunks! Some implementations may impose
     * artificial restrictions on the maximum distance.
     *
     * @param maxDistance the maximum distance to scan
     * @return information on the targeted block, or <code>null</code> if there
     * is no targeted block in range
     * @see #rayTraceBlocks(double, FluidCollisionMode)
     */
    @Override
    public RayTraceResult rayTraceBlocks(double maxDistance) {
        return null;
    }

    /**
     * Performs a ray trace that provides information on the targeted block.
     * <p>
     * This takes the blocks' precise collision shapes into account.
     * <p>
     * This may cause loading of chunks! Some implementations may impose
     * artificial restrictions on the maximum distance.
     *
     * @param maxDistance        the maximum distance to scan
     * @param fluidCollisionMode the fluid collision mode
     * @return information on the targeted block, or <code>null</code> if there
     * is no targeted block in range
     * @see World#rayTraceBlocks(Location, Vector, double, FluidCollisionMode)
     */
    @Override
    public RayTraceResult rayTraceBlocks(double maxDistance, FluidCollisionMode fluidCollisionMode) {
        return null;
    }

    /**
     * Returns the amount of air that the living entity has remaining, in
     * ticks.
     *
     * @return amount of air remaining
     */
    @Override
    public int getRemainingAir() {
        return 0;
    }

    /**
     * Sets the amount of air that the living entity has remaining, in ticks.
     *
     * @param ticks amount of air remaining
     */
    @Override
    public void setRemainingAir(int ticks) {

    }

    /**
     * Returns the maximum amount of air the living entity can have, in ticks.
     *
     * @return maximum amount of air
     */
    @Override
    public int getMaximumAir() {
        return 0;
    }

    /**
     * Sets the maximum amount of air the living entity can have, in ticks.
     *
     * @param ticks maximum amount of air
     */
    @Override
    public void setMaximumAir(int ticks) {

    }

    /**
     * Gets the time in ticks until the next arrow leaves the entity's body.
     *
     * @return ticks until arrow leaves
     */
    @Override
    public int getArrowCooldown() {
        return 0;
    }

    /**
     * Sets the time in ticks until the next arrow leaves the entity's body.
     *
     * @param ticks time until arrow leaves
     */
    @Override
    public void setArrowCooldown(int ticks) {

    }

    /**
     * Gets the amount of arrows in an entity's body.
     *
     * @return amount of arrows in body
     */
    @Override
    public int getArrowsInBody() {
        return 0;
    }

    /**
     * Set the amount of arrows in the entity's body.
     *
     * @param count amount of arrows in entity's body
     */
    @Override
    public void setArrowsInBody(int count) {

    }

    /**
     * Returns the living entity's current maximum no damage ticks.
     * <p>
     * This is the maximum duration in which the living entity will not take
     * damage.
     *
     * @return maximum no damage ticks
     */
    @Override
    public int getMaximumNoDamageTicks() {
        return 0;
    }

    /**
     * Sets the living entity's current maximum no damage ticks.
     *
     * @param ticks maximum amount of no damage ticks
     */
    @Override
    public void setMaximumNoDamageTicks(int ticks) {

    }

    /**
     * Returns the living entity's last damage taken in the current no damage
     * ticks time.
     * <p>
     * Only damage higher than this amount will further damage the living
     * entity.
     *
     * @return damage taken since the last no damage ticks time period
     */
    @Override
    public double getLastDamage() {
        return 0;
    }

    /**
     * Sets the damage dealt within the current no damage ticks time period.
     *
     * @param damage amount of damage
     */
    @Override
    public void setLastDamage(double damage) {

    }

    /**
     * Returns the living entity's current no damage ticks.
     *
     * @return amount of no damage ticks
     */
    @Override
    public int getNoDamageTicks() {
        return 0;
    }

    /**
     * Sets the living entity's current no damage ticks.
     *
     * @param ticks amount of no damage ticks
     */
    @Override
    public void setNoDamageTicks(int ticks) {

    }

    /**
     * Gets the player identified as the killer of the living entity.
     * <p>
     * May be null.
     *
     * @return killer player, or null if none found
     */
    @Override
    public Player getKiller() {
        return null;
    }

    /**
     * Adds the given {@link PotionEffect} to the living entity.
     *
     * @param effect PotionEffect to be added
     * @return whether the effect could be added
     */
    @Override
    public boolean addPotionEffect(PotionEffect effect) {
        return false;
    }

    /**
     * Adds the given {@link PotionEffect} to the living entity.
     * <p>
     * Only one potion effect can be present for a given {@link
     * PotionEffectType}.
     *
     * @param effect PotionEffect to be added
     * @param force  whether conflicting effects should be removed
     * @return whether the effect could be added
     * @deprecated no need to force since multiple effects of the same type are
     * now supported.
     */
    @Override
    public boolean addPotionEffect(PotionEffect effect, boolean force) {
        return false;
    }

    /**
     * Attempts to add all of the given {@link PotionEffect} to the living
     * entity.
     *
     * @param effects the effects to add
     * @return whether all of the effects could be added
     */
    @Override
    public boolean addPotionEffects(Collection<PotionEffect> effects) {
        return false;
    }

    /**
     * Returns whether the living entity already has an existing effect of
     * the given {@link PotionEffectType} applied to it.
     *
     * @param type the potion type to check
     * @return whether the living entity has this potion effect active on them
     */
    @Override
    public boolean hasPotionEffect(PotionEffectType type) {
        return false;
    }

    /**
     * Returns the active {@link PotionEffect} of the specified type.
     * <p>
     * If the effect is not present on the entity then null will be returned.
     *
     * @param type the potion type to check
     * @return the effect active on this entity, or null if not active.
     */
    @Override
    public PotionEffect getPotionEffect(PotionEffectType type) {
        return null;
    }

    /**
     * Removes any effects present of the given {@link PotionEffectType}.
     *
     * @param type the potion type to remove
     */
    @Override
    public void removePotionEffect(PotionEffectType type) {

    }

    /**
     * Returns all currently active {@link PotionEffect}s on the living
     * entity.
     *
     * @return a collection of {@link PotionEffect}s
     */
    @Override
    public Collection<PotionEffect> getActivePotionEffects() {
        return null;
    }

    /**
     * Checks whether the living entity has block line of sight to another.
     * <p>
     * This uses the same algorithm that hostile mobs use to find the closest
     * player.
     *
     * @param other the entity to determine line of sight to
     * @return true if there is a line of sight, false if not
     */
    @Override
    public boolean hasLineOfSight(Entity other) {
        return false;
    }

    /**
     * Returns if the living entity despawns when away from players or not.
     * <p>
     * By default, animals are not removed while other mobs are.
     *
     * @return true if the living entity is removed when away from players
     */
    @Override
    public boolean getRemoveWhenFarAway() {
        return false;
    }

    /**
     * Sets whether or not the living entity despawns when away from players
     * or not.
     *
     * @param remove the removal status
     */
    @Override
    public void setRemoveWhenFarAway(boolean remove) {

    }

    /**
     * Gets the inventory with the equipment worn by the living entity.
     *
     * @return the living entity's inventory
     */
    @Override
    public EntityEquipment getEquipment() {
        return null;
    }

    /**
     * Sets whether or not the living entity can pick up items.
     *
     * @param pickup whether or not the living entity can pick up items
     */
    @Override
    public void setCanPickupItems(boolean pickup) {

    }

    /**
     * Gets if the living entity can pick up items.
     *
     * @return whether or not the living entity can pick up items
     */
    @Override
    public boolean getCanPickupItems() {
        return false;
    }

    /**
     * Returns whether the entity is currently leashed.
     *
     * @return whether the entity is leashed
     */
    @Override
    public boolean isLeashed() {
        return false;
    }

    /**
     * Gets the entity that is currently leading this entity.
     *
     * @return the entity holding the leash
     * @throws IllegalStateException if not currently leashed
     */
    @Override
    public Entity getLeashHolder() throws IllegalStateException {
        return null;
    }

    /**
     * Sets the leash on this entity to be held by the supplied entity.
     * <p>
     * This method has no effect on EnderDragons, Withers, Players, or Bats.
     * Non-living entities excluding leashes will not persist as leash
     * holders.
     *
     * @param holder the entity to leash this entity to, or null to unleash
     * @return whether the operation was successful
     */
    @Override
    public boolean setLeashHolder(Entity holder) {
        return false;
    }

    /**
     * Checks to see if an entity is gliding, such as using an Elytra.
     *
     * @return True if this entity is gliding.
     */
    @Override
    public boolean isGliding() {
        return false;
    }

    /**
     * Makes entity start or stop gliding. This will work even if an Elytra
     * is not equipped, but will be reverted by the server immediately after
     * unless an event-cancelling mechanism is put in place.
     *
     * @param gliding True if the entity is gliding.
     */
    @Override
    public void setGliding(boolean gliding) {

    }

    /**
     * Checks to see if an entity is swimming.
     *
     * @return True if this entity is swimming.
     */
    @Override
    public boolean isSwimming() {
        return false;
    }

    /**
     * Makes entity start or stop swimming.
     * <p>
     * This may have unexpected results if the entity is not in water.
     *
     * @param swimming True if the entity is swimming.
     */
    @Override
    public void setSwimming(boolean swimming) {

    }

    /**
     * Checks to see if an entity is currently using the Riptide enchantment.
     *
     * @return True if this entity is currently riptiding.
     */
    @Override
    public boolean isRiptiding() {
        return false;
    }

    /**
     * Returns whether this entity is slumbering.
     *
     * @return slumber state
     */
    @Override
    public boolean isSleeping() {
        return false;
    }

    /**
     * Sets whether an entity will have AI.
     * <p>
     * The entity will be completely unable to move if it has no AI.
     *
     * @param ai whether the mob will have AI or not.
     */
    @Override
    public void setAI(boolean ai) {

    }

    /**
     * Checks whether an entity has AI.
     * <p>
     * The entity will be completely unable to move if it has no AI.
     *
     * @return true if the entity has AI, otherwise false.
     */
    @Override
    public boolean hasAI() {
        return false;
    }

    /**
     * Makes this entity attack the given entity with a melee attack.
     * <p>
     * Attack damage is calculated by the server from the attributes and
     * equipment of this mob, and knockback is applied to {@code target} as
     * appropriate.
     *
     * @param target entity to attack.
     */
    @Override
    public void attack(Entity target) {

    }

    /**
     * Makes this entity swing their main hand.
     * <p>
     * This method does nothing if this entity does not have an animation for
     * swinging their main hand.
     */
    @Override
    public void swingMainHand() {

    }

    /**
     * Makes this entity swing their off hand.
     * <p>
     * This method does nothing if this entity does not have an animation for
     * swinging their off hand.
     */
    @Override
    public void swingOffHand() {

    }

    /**
     * Set if this entity will be subject to collisions with other entities.
     * <p>
     * Exemptions to this rule can be managed with
     * {@link #getCollidableExemptions()}
     *
     * @param collidable collision status
     */
    @Override
    public void setCollidable(boolean collidable) {

    }

    /**
     * Gets if this entity is subject to collisions with other entities.
     * <p>
     * Some entities might be exempted from the collidable rule of this entity.
     * Use {@link #getCollidableExemptions()} to get these.
     * <p>
     * Please note that this method returns only the custom collidable state,
     * not whether the entity is non-collidable for other reasons such as being
     * dead.
     *
     * @return collision status
     */
    @Override
    public boolean isCollidable() {
        return false;
    }

    /**
     * Gets a mutable set of UUIDs of the entities which are exempt from the
     * entity's collidable rule and which's collision with this entity will
     * behave the opposite of it.
     * <p>
     * This set can be modified to add or remove exemptions.
     * <p>
     * For example if collidable is true and an entity is in the exemptions set
     * then it will not collide with it. Similarly if collidable is false and an
     * entity is in this set then it will still collide with it.
     * <p>
     * Note these exemptions are not (currently) persistent.
     *
     * @return the collidable exemption set
     */
    @Override
    public Set<UUID> getCollidableExemptions() {
        return null;
    }

    /**
     * Returns the value of the memory specified.
     * <p>
     * Note that the value is null when the specific entity does not have that
     * value by default.
     *
     * @param memoryKey memory to access
     * @return a instance of the memory section value or null if not present
     */
    @Override
    public <T> T getMemory(MemoryKey<T> memoryKey) {
        return null;
    }

    /**
     * Sets the value of the memory specified.
     * <p>
     * Note that the value will not be persisted when the specific entity does
     * not have that value by default.
     *
     * @param memoryKey   the memory to access
     * @param memoryValue a typed memory value
     */
    @Override
    public <T> void setMemory(MemoryKey<T> memoryKey, T memoryValue) {

    }

    /**
     * Get the category to which this entity belongs.
     * <p>
     * Categories may subject this entity to additional effects, benefits or
     * debuffs.
     *
     * @return the entity category
     */
    @Override
    public EntityCategory getCategory() {
        return null;
    }

    /**
     * Sets whether the entity is invisible or not.
     *
     * @param invisible If the entity is invisible
     */
    @Override
    public void setInvisible(boolean invisible) {

    }

    /**
     * Gets whether the entity is invisible or not.
     *
     * @return Whether the entity is invisible
     */
    @Override
    public boolean isInvisible() {
        return false;
    }

    /**
     * Gets the specified attribute instance from the object. This instance will
     * be backed directly to the object and any changes will be visible at once.
     *
     * @param attribute the attribute to get
     * @return the attribute instance or null if not applicable to this object
     */
    @Override
    public AttributeInstance getAttribute(Attribute attribute) {
        return null;
    }

    /**
     * Deals the given amount of damage to this entity.
     *
     * @param amount Amount of damage to deal
     */
    @Override
    public void damage(double amount) {

    }

    /**
     * Deals the given amount of damage to this entity, from a specified
     * entity.
     *
     * @param amount Amount of damage to deal
     * @param source Entity which to attribute this damage from
     */
    @Override
    public void damage(double amount, Entity source) {

    }

    /**
     * Gets the entity's health from 0 to {@link #getMaxHealth()}, where 0 is dead.
     *
     * @return Health represented from 0 to max
     */
    @Override
    public double getHealth() {
        return 0;
    }

    /**
     * Sets the entity's health from 0 to {@link #getMaxHealth()}, where 0 is
     * dead.
     *
     * @param health New health represented from 0 to max
     * @throws IllegalArgumentException Thrown if the health is {@literal < 0 or >}
     *                                  {@link #getMaxHealth()}
     */
    @Override
    public void setHealth(double health) {

    }

    /**
     * Gets the entity's absorption amount.
     *
     * @return absorption amount from 0
     */
    @Override
    public double getAbsorptionAmount() {
        return 0;
    }

    /**
     * Sets the entity's absorption amount.
     *
     * @param amount new absorption amount from 0
     * @throws IllegalArgumentException thrown if health is {@literal < 0} or
     *                                  non-finite.
     */
    @Override
    public void setAbsorptionAmount(double amount) {

    }

    /**
     * Gets the maximum health this entity has.
     *
     * @return Maximum health
     * @deprecated use {@link Attribute#GENERIC_MAX_HEALTH}.
     */
    @Override
    public double getMaxHealth() {
        return 0;
    }

    /**
     * Sets the maximum health this entity can have.
     * <p>
     * If the health of the entity is above the value provided it will be set
     * to that value.
     * <p>
     * Note: An entity with a health bar ({@link Player}, {@link EnderDragon},
     * {@link Wither}, etc...} will have their bar scaled accordingly.
     *
     * @param health amount of health to set the maximum to
     * @deprecated use {@link Attribute#GENERIC_MAX_HEALTH}.
     */
    @Override
    public void setMaxHealth(double health) {

    }

    /**
     * Resets the max health to the original amount.
     *
     * @deprecated use {@link Attribute#GENERIC_MAX_HEALTH}.
     */
    @Override
    public void resetMaxHealth() {

    }

    /**
     * Gets the custom name on a mob or block. If there is no name this method
     * will return null.
     * <p>
     * This value has no effect on players, they will always use their real
     * name.
     *
     * @return name of the mob/block or null
     */
    @Override
    public String getCustomName() {
        return null;
    }

    /**
     * Sets a custom name on a mob or block. This name will be used in death
     * messages and can be sent to the client as a nameplate over the mob.
     * <p>
     * Setting the name to null or an empty string will clear it.
     * <p>
     * This value has no effect on players, they will always use their real
     * name.
     *
     * @param name the name to set
     */
    @Override
    public void setCustomName(String name) {

    }

    /**
     * Sets a metadata value in the implementing object's metadata store.
     *
     * @param metadataKey      A unique key to identify this metadata.
     * @param newMetadataValue The metadata value to apply.
     * @throws IllegalArgumentException If value is null, or the owning plugin
     *                                  is null
     */
    @Override
    public void setMetadata(String metadataKey, MetadataValue newMetadataValue) {

    }

    /**
     * Returns a list of previously set metadata values from the implementing
     * object's metadata store.
     *
     * @param metadataKey the unique metadata key being sought.
     * @return A list of values, one for each plugin that has set the
     * requested value.
     */
    @Override
    public List<MetadataValue> getMetadata(String metadataKey) {
        return null;
    }

    /**
     * Tests to see whether the implementing object contains the given
     * metadata value in its metadata store.
     *
     * @param metadataKey the unique metadata key being queried.
     * @return the existence of the metadataKey within subject.
     */
    @Override
    public boolean hasMetadata(String metadataKey) {
        return false;
    }

    /**
     * Removes the given metadata value from the implementing object's
     * metadata store.
     *
     * @param metadataKey  the unique metadata key identifying the metadata to
     *                     remove.
     * @param owningPlugin This plugin's metadata value will be removed. All
     *                     other values will be left untouched.
     * @throws IllegalArgumentException If plugin is null
     */
    @Override
    public void removeMetadata(String metadataKey, Plugin owningPlugin) {

    }

    /**
     * Checks if this object contains an override for the specified
     * permission, by fully qualified name
     *
     * @param name Name of the permission
     * @return true if the permission is set, otherwise false
     */
    @Override
    public boolean isPermissionSet(String name) {
        return false;
    }

    /**
     * Checks if this object contains an override for the specified {@link
     * Permission}
     *
     * @param perm Permission to check
     * @return true if the permission is set, otherwise false
     */
    @Override
    public boolean isPermissionSet(Permission perm) {
        return false;
    }

    /**
     * Gets the value of the specified permission, if set.
     * <p>
     * If a permission override is not set on this object, the default value
     * of the permission will be returned.
     *
     * @param name Name of the permission
     * @return Value of the permission
     */
    @Override
    public boolean hasPermission(String name) {
        return false;
    }

    /**
     * Gets the value of the specified permission, if set.
     * <p>
     * If a permission override is not set on this object, the default value
     * of the permission will be returned
     *
     * @param perm Permission to get
     * @return Value of the permission
     */
    @Override
    public boolean hasPermission(Permission perm) {
        return false;
    }

    /**
     * Adds a new {@link PermissionAttachment} with a single permission by
     * name and value
     *
     * @param plugin Plugin responsible for this attachment, may not be null
     *               or disabled
     * @param name   Name of the permission to attach
     * @param value  Value of the permission
     * @return The PermissionAttachment that was just created
     */
    @Override
    public PermissionAttachment addAttachment(Plugin plugin, String name, boolean value) {
        return null;
    }

    /**
     * Adds a new empty {@link PermissionAttachment} to this object
     *
     * @param plugin Plugin responsible for this attachment, may not be null
     *               or disabled
     * @return The PermissionAttachment that was just created
     */
    @Override
    public PermissionAttachment addAttachment(Plugin plugin) {
        return null;
    }

    /**
     * Temporarily adds a new {@link PermissionAttachment} with a single
     * permission by name and value
     *
     * @param plugin Plugin responsible for this attachment, may not be null
     *               or disabled
     * @param name   Name of the permission to attach
     * @param value  Value of the permission
     * @param ticks  Amount of ticks to automatically remove this attachment
     *               after
     * @return The PermissionAttachment that was just created
     */
    @Override
    public PermissionAttachment addAttachment(Plugin plugin, String name, boolean value, int ticks) {
        return null;
    }

    /**
     * Temporarily adds a new empty {@link PermissionAttachment} to this
     * object
     *
     * @param plugin Plugin responsible for this attachment, may not be null
     *               or disabled
     * @param ticks  Amount of ticks to automatically remove this attachment
     *               after
     * @return The PermissionAttachment that was just created
     */
    @Override
    public PermissionAttachment addAttachment(Plugin plugin, int ticks) {
        return null;
    }

    /**
     * Removes the given {@link PermissionAttachment} from this object
     *
     * @param attachment Attachment to remove
     * @throws IllegalArgumentException Thrown when the specified attachment
     *                                  isn't part of this object
     */
    @Override
    public void removeAttachment(PermissionAttachment attachment) {

    }

    /**
     * Recalculates the permissions for this object, if the attachments have
     * changed values.
     * <p>
     * This should very rarely need to be called from a plugin.
     */
    @Override
    public void recalculatePermissions() {

    }

    /**
     * Gets a set containing all of the permissions currently in effect by
     * this object
     *
     * @return Set of currently effective permissions
     */
    @Override
    public Set<PermissionAttachmentInfo> getEffectivePermissions() {
        return null;
    }

    /**
     * Checks if this object is a server operator
     *
     * @return true if this is an operator, otherwise false
     */
    @Override
    public boolean isOp() {
        return false;
    }

    /**
     * Sets the operator status of this object
     *
     * @param value New operator value
     */
    @Override
    public void setOp(boolean value) {

    }

    /**
     * Returns a custom tag container capable of storing tags on the object.
     * <p>
     * Note that the tags stored on this container are all stored under their
     * own custom namespace therefore modifying default tags using this
     * {@link PersistentDataHolder} is impossible.
     *
     * @return the persistent metadata container
     */
    @Override
    public PersistentDataContainer getPersistentDataContainer() {
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

    /**
     * Launches a {@link Projectile} from the ProjectileSource.
     *
     * @param projectile class of the projectile to launch
     * @return the launched projectile
     */
    @Override
    public <T extends Projectile> T launchProjectile(Class<? extends T> projectile) {
        return null;
    }

    /**
     * Launches a {@link Projectile} from the ProjectileSource with an
     * initial velocity.
     *
     * @param projectile class of the projectile to launch
     * @param velocity   the velocity with which to launch
     * @return the launched projectile
     */
    @Override
    public <T extends Projectile> T launchProjectile(Class<? extends T> projectile, org.bukkit.util.Vector velocity) {
        return null;
    }
}
