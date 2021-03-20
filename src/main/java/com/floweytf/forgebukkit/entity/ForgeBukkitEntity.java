package com.floweytf.forgebukkit.entity;

import com.floweytf.forgebukkit.ForgeBukkit;
import com.floweytf.forgebukkit.ForgeBukkitServer;
import com.floweytf.forgebukkit.ForgeBukkitWorld;
import com.floweytf.forgebukkit.Wrapper;
import com.floweytf.forgebukkit.access.EntityMixinAccess;
import com.floweytf.forgebukkit.persistence.ForgeBukkitPersistentDataContainer;
import com.floweytf.forgebukkit.persistence.ForgeBukkitPersistentDataTypeRegistry;
import com.floweytf.forgebukkit.util.ForgeBukkitChatMessage;
import com.google.common.base.Preconditions;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import org.bukkit.EntityEffect;
import org.bukkit.Location;
import org.bukkit.Server;
import org.bukkit.World;
import org.bukkit.block.BlockFace;
import org.bukkit.block.PistonMoveReaction;
import org.bukkit.entity.Pose;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerTeleportEvent.TeleportCause;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.permissions.*;
import org.bukkit.plugin.Plugin;
import org.bukkit.util.BoundingBox;
import org.bukkit.util.NumberConversions;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.stream.Collectors;

public abstract class ForgeBukkitEntity extends Wrapper<Entity> implements org.bukkit.entity.Entity {
    public static Method getFlag = ObfuscationReflectionHelper.findMethod(Entity.class, "func_70083_f", int.class);
    public static Method setFlag = ObfuscationReflectionHelper.findMethod(Entity.class, "func_70052_a", int.class, boolean.class);


    private static PermissibleBase perm;
    private static final ForgeBukkitPersistentDataTypeRegistry DATA_TYPE_REGISTRY = new ForgeBukkitPersistentDataTypeRegistry();

    protected final ForgeBukkitServer server;
    private EntityDamageEvent lastDamageEvent;
    private final ForgeBukkitPersistentDataContainer persistentDataContainer = new ForgeBukkitPersistentDataContainer(DATA_TYPE_REGISTRY);

    public ForgeBukkitEntity(final ForgeBukkitServer server, final Entity handle) {
        super(handle);
        this.server = server;
    }

    public static ForgeBukkitEntity wrap(Entity entity) {
        ForgeBukkitServer server = ForgeBukkitServer.wrap(entity.world.getServer());
        return null;
    }

    /*
    public static ForgeBukkitEntity wrap(ForgeBukkitServer server, Entity entity) {
        if (entity instanceof EntityLiving) {
            // Players
            if (entity instanceof EntityHuman) {
                if (entity instanceof EntityPlayer) { return new CraftPlayer(server, (EntityPlayer) entity); }
                else { return new CraftHumanEntity(server, (EntityHuman) entity); }
            }
            // Water Animals
            else if (entity instanceof EntityWaterAnimal) {
                if (entity instanceof EntitySquid) { return new CraftSquid(server, (EntitySquid) entity); }
                else if (entity instanceof EntityFish) {
                    if (entity instanceof EntityCod) { return new CraftCod(server, (EntityCod) entity); }
                    else if (entity instanceof EntityPufferFish) { return new CraftPufferFish(server, (EntityPufferFish) entity); }
                    else if (entity instanceof EntitySalmon) { return new CraftSalmon(server, (EntitySalmon) entity); }
                    else if (entity instanceof EntityTropicalFish) { return new CraftTropicalFish(server, (EntityTropicalFish) entity); }
                    else { return new CraftFish(server, (EntityFish) entity); }
                }
                else if (entity instanceof EntityDolphin) { return new CraftDolphin(server, (EntityDolphin) entity); }
                else { return new CraftWaterMob(server, (EntityWaterAnimal) entity); }
            }
            else if (entity instanceof EntityCreature) {
                // Animals
                if (entity instanceof EntityAnimal) {
                    if (entity instanceof EntityChicken) { return new CraftChicken(server, (EntityChicken) entity); }
                    else if (entity instanceof EntityCow) {
                        if (entity instanceof EntityMushroomCow) { return new CraftMushroomCow(server, (EntityMushroomCow) entity); }
                        else { return new CraftCow(server, (EntityCow) entity); }
                    }
                    else if (entity instanceof EntityPig) { return new CraftPig(server, (EntityPig) entity); }
                    else if (entity instanceof EntityTameableAnimal) {
                        if (entity instanceof EntityWolf) { return new CraftWolf(server, (EntityWolf) entity); }
                        else if (entity instanceof EntityCat) { return new CraftCat(server, (EntityCat) entity); }
                        else if (entity instanceof EntityParrot) { return new CraftParrot(server, (EntityParrot) entity); }
                    }
                    else if (entity instanceof EntitySheep) { return new CraftSheep(server, (EntitySheep) entity); }
                    else if (entity instanceof EntityHorseAbstract) {
                        if (entity instanceof EntityHorseChestedAbstract){
                            if (entity instanceof EntityHorseDonkey) { return new CraftDonkey(server, (EntityHorseDonkey) entity); }
                            else if (entity instanceof EntityHorseMule) { return new CraftMule(server, (EntityHorseMule) entity); }
                            else if (entity instanceof EntityLlamaTrader) { return new CraftTraderLlama(server, (EntityLlamaTrader) entity); }
                            else if (entity instanceof EntityLlama) { return new CraftLlama(server, (EntityLlama) entity); }
                        } else if (entity instanceof EntityHorse) { return new CraftHorse(server, (EntityHorse) entity); }
                        else if (entity instanceof EntityHorseSkeleton) { return new CraftSkeletonHorse(server, (EntityHorseSkeleton) entity); }
                        else if (entity instanceof EntityHorseZombie) { return new CraftZombieHorse(server, (EntityHorseZombie) entity); }
                    }
                    else if (entity instanceof EntityRabbit) { return new CraftRabbit(server, (EntityRabbit) entity); }
                    else if (entity instanceof EntityPolarBear) { return new CraftPolarBear(server, (EntityPolarBear) entity); }
                    else if (entity instanceof EntityTurtle) { return new CraftTurtle(server, (EntityTurtle) entity); }
                    else if (entity instanceof EntityOcelot) { return new CraftOcelot(server, (EntityOcelot) entity); }
                    else if (entity instanceof EntityPanda) { return new CraftPanda(server, (EntityPanda) entity); }
                    else if (entity instanceof EntityFox) { return new CraftFox(server, (EntityFox) entity); }
                    else if (entity instanceof EntityBee) { return new CraftBee(server, (EntityBee) entity); }
                    else if (entity instanceof EntityHoglin) { return new CraftHoglin(server, (EntityHoglin) entity); }
                    else if (entity instanceof EntityStrider) { return new CraftStrider(server, (EntityStrider) entity); }
                    else  { return new CraftAnimals(server, (EntityAnimal) entity); }
                }
                // Monsters
                else if (entity instanceof EntityMonster) {
                    if (entity instanceof EntityZombie) {
                        if (entity instanceof EntityPigZombie) { return new CraftPigZombie(server, (EntityPigZombie) entity); }
                        else if (entity instanceof EntityZombieHusk) { return new CraftHusk(server, (EntityZombieHusk) entity); }
                        else if (entity instanceof EntityZombieVillager) { return new CraftVillagerZombie(server, (EntityZombieVillager) entity); }
                        else if (entity instanceof EntityDrowned) { return new CraftDrowned(server, (EntityDrowned) entity); }
                        else { return new CraftZombie(server, (EntityZombie) entity); }
                    }
                    else if (entity instanceof EntityCreeper) { return new CraftCreeper(server, (EntityCreeper) entity); }
                    else if (entity instanceof EntityEnderman) { return new CraftEnderman(server, (EntityEnderman) entity); }
                    else if (entity instanceof EntitySilverfish) { return new CraftSilverfish(server, (EntitySilverfish) entity); }
                    else if (entity instanceof EntityGiantZombie) { return new CraftGiant(server, (EntityGiantZombie) entity); }
                    else if (entity instanceof EntitySkeletonAbstract) {
                        if (entity instanceof EntitySkeletonStray) { return new CraftStray(server, (EntitySkeletonStray) entity); }
                        else if (entity instanceof EntitySkeletonWither) { return new CraftWitherSkeleton(server, (EntitySkeletonWither) entity); }
                        else { return new CraftSkeleton(server, (EntitySkeletonAbstract) entity); }
                    }
                    else if (entity instanceof EntityBlaze) { return new CraftBlaze(server, (EntityBlaze) entity); }
                    else if (entity instanceof EntityWitch) { return new CraftWitch(server, (EntityWitch) entity); }
                    else if (entity instanceof EntityWither) { return new CraftWither(server, (EntityWither) entity); }
                    else if (entity instanceof EntitySpider) {
                        if (entity instanceof EntityCaveSpider) { return new CraftCaveSpider(server, (EntityCaveSpider) entity); }
                        else { return new CraftSpider(server, (EntitySpider) entity); }
                    }
                    else if (entity instanceof EntityEndermite) { return new CraftEndermite(server, (EntityEndermite) entity); }
                    else if (entity instanceof EntityGuardian) {
                        if (entity instanceof EntityGuardianElder) { return new CraftElderGuardian(server, (EntityGuardianElder) entity); }
                        else { return new CraftGuardian(server, (EntityGuardian) entity); }
                    }
                    else if (entity instanceof EntityVex) { return new CraftVex(server, (EntityVex) entity); }
                    else if (entity instanceof EntityIllagerAbstract) {
                        if (entity instanceof EntityIllagerWizard) {
                            if (entity instanceof EntityEvoker) { return new CraftEvoker(server, (EntityEvoker) entity); }
                            else if (entity instanceof EntityIllagerIllusioner) { return new CraftIllusioner(server, (EntityIllagerIllusioner) entity); }
                            else {  return new CraftSpellcaster(server, (EntityIllagerWizard) entity); }
                        }
                        else if (entity instanceof EntityVindicator) { return new CraftVindicator(server, (EntityVindicator) entity); }
                        else if (entity instanceof EntityPillager) { return new CraftPillager(server, (EntityPillager) entity); }
                        else { return new CraftIllager(server, (EntityIllagerAbstract) entity); }
                    }
                    else if (entity instanceof EntityRavager) { return new CraftRavager(server, (EntityRavager) entity); }
                    else if (entity instanceof EntityPiglinAbstract) {
                        if (entity instanceof EntityPiglin) return new CraftPiglin(server, (EntityPiglin) entity);
                        else if (entity instanceof EntityPiglinBrute) { return new CraftPiglinBrute(server, (EntityPiglinBrute) entity); }
                        else { return new CraftPiglinAbstract(server, (EntityPiglinAbstract) entity); }
                    }
                    else if (entity instanceof EntityZoglin) { return new CraftZoglin(server, (EntityZoglin) entity); }

                    else  { return new CraftMonster(server, (EntityMonster) entity); }
                }
                else if (entity instanceof EntityGolem) {
                    if (entity instanceof EntitySnowman) { return new CraftSnowman(server, (EntitySnowman) entity); }
                    else if (entity instanceof EntityIronGolem) { return new CraftIronGolem(server, (EntityIronGolem) entity); }
                    else if (entity instanceof EntityShulker) { return new CraftShulker(server, (EntityShulker) entity); }
                }
                else if (entity instanceof EntityVillagerAbstract) {
                    if (entity instanceof EntityVillager) { return new CraftVillager(server, (EntityVillager) entity); }
                    else if (entity instanceof EntityVillagerTrader) { return new CraftWanderingTrader(server, (EntityVillagerTrader) entity); }
                    else { return new CraftAbstractVillager(server, (EntityVillagerAbstract) entity); }
                }
                else { return new CraftCreature(server, (EntityCreature) entity); }
            }
            // Slimes are a special (and broken) case
            else if (entity instanceof EntitySlime) {
                if (entity instanceof EntityMagmaCube) { return new CraftMagmaCube(server, (EntityMagmaCube) entity); }
                else { return new CraftSlime(server, (EntitySlime) entity); }
            }
            // Flying
            else if (entity instanceof EntityFlying) {
                if (entity instanceof EntityGhast) { return new CraftGhast(server, (EntityGhast) entity); }
                else if (entity instanceof EntityPhantom) { return new CraftPhantom(server, (EntityPhantom) entity); }
                else { return new CraftFlying(server, (EntityFlying) entity); }
            }
            else if (entity instanceof EntityEnderDragon) {
                return new CraftEnderDragon(server, (EntityEnderDragon) entity);
            }
            // Ambient
            else if (entity instanceof EntityAmbient) {
                if (entity instanceof EntityBat) { return new CraftBat(server, (EntityBat) entity); }
                else { return new CraftAmbient(server, (EntityAmbient) entity); }
            }
            else if (entity instanceof EntityArmorStand) { return new CraftArmorStand(server, (EntityArmorStand) entity); }
            else  { return new CraftLivingEntity(server, (EntityLiving) entity); }
        }
        else if (entity instanceof EntityComplexPart) {
            EntityComplexPart part = (EntityComplexPart) entity;
            if (part.owner instanceof EntityEnderDragon) { return new CraftEnderDragonPart(server, (EntityComplexPart) entity); }
            else { return new CraftComplexPart(server, (EntityComplexPart) entity); }
        }
        else if (entity instanceof EntityExperienceOrb) { return new CraftExperienceOrb(server, (EntityExperienceOrb) entity); }
        else if (entity instanceof EntityTippedArrow) { return new CraftTippedArrow(server, (EntityTippedArrow) entity); }
        else if (entity instanceof EntitySpectralArrow) { return new CraftSpectralArrow(server, (EntitySpectralArrow) entity); }
        else if (entity instanceof EntityArrow) {
            if (entity instanceof EntityThrownTrident) { return new CraftTrident(server, (EntityThrownTrident) entity); }
            else { return new CraftArrow(server, (EntityArrow) entity); }
        }
        else if (entity instanceof EntityBoat) { return new CraftBoat(server, (EntityBoat) entity); }
        else if (entity instanceof EntityProjectile) {
            if (entity instanceof EntityEgg) { return new CraftEgg(server, (EntityEgg) entity); }
            else if (entity instanceof EntitySnowball) { return new CraftSnowball(server, (EntitySnowball) entity); }
            else if (entity instanceof EntityPotion) { return new CraftThrownPotion(server, (EntityPotion) entity); }
            else if (entity instanceof EntityEnderPearl) { return new CraftEnderPearl(server, (EntityEnderPearl) entity); }
            else if (entity instanceof EntityThrownExpBottle) { return new CraftThrownExpBottle(server, (EntityThrownExpBottle) entity); }
        }
        else if (entity instanceof EntityFallingBlock) { return new CraftFallingBlock(server, (EntityFallingBlock) entity); }
        else if (entity instanceof EntityFireball) {
            if (entity instanceof EntitySmallFireball) { return new CraftSmallFireball(server, (EntitySmallFireball) entity); }
            else if (entity instanceof EntityLargeFireball) { return new CraftLargeFireball(server, (EntityLargeFireball) entity); }
            else if (entity instanceof EntityWitherSkull) { return new CraftWitherSkull(server, (EntityWitherSkull) entity); }
            else if (entity instanceof EntityDragonFireball) { return new CraftDragonFireball(server, (EntityDragonFireball) entity); }
            else { return new CraftFireball(server, (EntityFireball) entity); }
        }
        else if (entity instanceof EntityEnderSignal) { return new CraftEnderSignal(server, (EntityEnderSignal) entity); }
        else if (entity instanceof EntityEnderCrystal) { return new CraftEnderCrystal(server, (EntityEnderCrystal) entity); }
        else if (entity instanceof EntityFishingHook) { return new CraftFishHook(server, (EntityFishingHook) entity); }
        else if (entity instanceof EntityItem) { return new CraftItem(server, (EntityItem) entity); }
        else if (entity instanceof EntityLightning) { return new CraftLightningStrike(server, (EntityLightning) entity); }
        else if (entity instanceof EntityMinecartAbstract) {
            if (entity instanceof EntityMinecartFurnace) { return new CraftMinecartFurnace(server, (EntityMinecartFurnace) entity); }
            else if (entity instanceof EntityMinecartChest) { return new CraftMinecartChest(server, (EntityMinecartChest) entity); }
            else if (entity instanceof EntityMinecartTNT) { return new CraftMinecartTNT(server, (EntityMinecartTNT) entity); }
            else if (entity instanceof EntityMinecartHopper) { return new CraftMinecartHopper(server, (EntityMinecartHopper) entity); }
            else if (entity instanceof EntityMinecartMobSpawner) { return new CraftMinecartMobSpawner(server, (EntityMinecartMobSpawner) entity); }
            else if (entity instanceof EntityMinecartRideable) { return new CraftMinecartRideable(server, (EntityMinecartRideable) entity); }
            else if (entity instanceof EntityMinecartCommandBlock) { return new CraftMinecartCommand(server, (EntityMinecartCommandBlock) entity); }
        } else if (entity instanceof EntityHanging) {
            if (entity instanceof EntityPainting) { return new CraftPainting(server, (EntityPainting) entity); }
            else if (entity instanceof EntityItemFrame) { return new CraftItemFrame(server, (EntityItemFrame) entity); }
            else if (entity instanceof EntityLeash) { return new CraftLeash(server, (EntityLeash) entity); }
            else { return new CraftHanging(server, (EntityHanging) entity); }
        }
        else if (entity instanceof EntityTNTPrimed) { return new CraftTNTPrimed(server, (EntityTNTPrimed) entity); }
        else if (entity instanceof EntityFireworks) { return new CraftFirework(server, (EntityFireworks) entity); }
        else if (entity instanceof EntityShulkerBullet) { return new CraftShulkerBullet(server, (EntityShulkerBullet) entity); }
        else if (entity instanceof EntityAreaEffectCloud) { return new CraftAreaEffectCloud(server, (EntityAreaEffectCloud) entity); }
        else if (entity instanceof EntityEvokerFangs) { return new CraftEvokerFangs(server, (EntityEvokerFangs) entity); }
        else if (entity instanceof EntityLlamaSpit) { return new CraftLlamaSpit(server, (EntityLlamaSpit) entity); }
        // CHECKSTYLE:ON

        throw new AssertionError("Unknown entity " + (entity == null ? null : entity.getClass()));
    }
     */

    @Override
    @NotNull
    public Location getLocation() {
        return new Location(
                getWorld(),
                getHandle().getPosX(),
                getHandle().getPosY(),
                getHandle().getPosZ(),
                getHandle().getYaw(1.0F),
                getHandle().getPitch(1.0F)
        );
    }

    @Override
    public Location getLocation(Location loc) {
        if (loc != null) {
            loc.setWorld(getWorld());
            loc.setX(getHandle().getPosX());
            loc.setY(getHandle().getPosY());
            loc.setZ(getHandle().getPosZ());
            loc.setYaw(getHandle().getYaw(1.0F));
            loc.setPitch(getHandle().getPitch(1.0F));
        }

        return loc;
    }

    @Override
    @NotNull
    public Vector getVelocity() {
        return new Vector(getHandle().getMotion().x, getHandle().getMotion().y, getHandle().getMotion().z);
    }

    @Override
    public void setVelocity(@NotNull Vector velocity) {
        Preconditions.checkNotNull(velocity );
        velocity.checkFinite();
        getHandle().setMotion(velocity.getX(), velocity.getY(), velocity.getZ());
        getHandle().velocityChanged = true;
    }

    @Override
    public double getHeight() {
        return getHandle().getHeight();
    }

    @Override
    public double getWidth() {
        return getHandle().getWidth();
    }

    @Override
    @NotNull
    public BoundingBox getBoundingBox() {
        return new BoundingBox(
                getHandle().getBoundingBox().minX,
                getHandle().getBoundingBox().minY,
                getHandle().getBoundingBox().minZ,
                getHandle().getBoundingBox().maxX,
                getHandle().getBoundingBox().maxY,
                getHandle().getBoundingBox().maxZ
        );
    }

    @Override
    public boolean isOnGround() {
        return getHandle().isOnGround();
    }

    @Override
    public boolean isInWater() {
        return getHandle().isInWater();
    }

    @Override
    @NotNull
    public World getWorld() {
        return Objects.requireNonNull(ForgeBukkitWorld.getWorldWrapper(getHandle().world));
    }

    @Override
    public void setRotation(float yaw, float pitch) {
        NumberConversions.checkFinite(pitch, "pitch not finite");
        NumberConversions.checkFinite(yaw, "yaw not finite");

        yaw = Location.normalizeYaw(yaw);
        pitch = Location.normalizePitch(pitch);

        getHandle().rotationYaw = yaw;
        getHandle().rotationPitch = pitch;
        getHandle().prevRotationYaw = yaw;
        getHandle().prevRotationPitch = pitch;

        // ???
        // getHandle().setHeadRotation(yaw);
    }

    @Override
    public boolean teleport(@NotNull Location location) {
        return teleport(location, TeleportCause.PLUGIN);
    }

    @Override
    public boolean teleport(Location location, @NotNull TeleportCause cause) {
        Preconditions.checkArgument(location != null, "location");
        location.checkFinite();

        if (getHandle().isBeingRidden() || !getHandle().isAlive())
            return false;

        // If this entity is riding another entity, we must dismount before teleporting.
        getHandle().stopRiding();

        // Let the server handle cross world teleports
        if (!location.getWorld().equals(getWorld())) {
            //getHandle().teleportTo(((ForgeBukkitWorld) location.getWorld()).getHandle(), new BlockPos(location.getX(), location.getY(), location.getZ()));
            return true;
        }

        // entity.setLocation() throws no event, and so cannot be cancelled
        getHandle().setLocationAndAngles(location.getX(), location.getY(), location.getZ(), location.getYaw(), location.getPitch());
        // SPIGOT-619: Force sync head rotation also
        getHandle().setHeadRotation(location.getYaw(), 0);

        return true;
    }

    @Override
    public boolean teleport(org.bukkit.entity.Entity destination) {
        return teleport(destination.getLocation());
    }

    @Override
    public boolean teleport(org.bukkit.entity.Entity destination, @NotNull TeleportCause cause) {
        return teleport(destination.getLocation(), cause);
    }

    @Override
    @NotNull
    public List<org.bukkit.entity.Entity> getNearbyEntities(double x, double y, double z) {
        List<Entity> notchEntityList = getHandle().world.getEntitiesInAABBexcluding(getHandle(), getHandle().getBoundingBox().grow(x, y, z), null);
        List<org.bukkit.entity.Entity> bukkitEntityList = new ArrayList<>(notchEntityList.size());

        for (Entity e : notchEntityList)
            bukkitEntityList.add(wrap(e));

        return bukkitEntityList;
    }

    @Override
    public int getEntityId() {
        return getHandle().getEntityId();
    }

    @Override
    public int getFireTicks() {
        return getHandle().getFireTimer();
    }

    @Override
    public int getMaxFireTicks() {
        return 1;
    }

    @Override
    public void setFireTicks(int ticks) {
        getHandle().setFire(ticks);
    }

    @Override
    public void remove() {
        getHandle().remove();
    }

    @Override
    public boolean isDead() {
        // doesnt work on Schrodinger's cat
        return !getHandle().isAlive();
    }

    @Override
    public boolean isValid() {
        return getHandle().isAlive();
    }

    @Override
    @NotNull
    public Server getServer() {
        return server;
    }

    @Override
    public boolean isPersistent() {
        return ((EntityMixinAccess)getHandle()).getPersist();
    }

    @Override
    public void setPersistent(boolean persistent) {
        ((EntityMixinAccess)getHandle()).setPersist(persistent);
    }

    public Vector getMomentum() {
        return getVelocity();
    }

    public void setMomentum(Vector value) {
        setVelocity(value);
    }

    @Override
    public org.bukkit.entity.Entity getPassenger() {
        return isEmpty() ? null : wrap(getHandle().getPassengers().get(0));
    }

    @Override
    public boolean setPassenger(@NotNull org.bukkit.entity.Entity passenger) {
        Preconditions.checkArgument(!this.equals(passenger), "Entity cannot ride itself.");
        if (passenger instanceof ForgeBukkitEntity) {
            eject();
            return ((ForgeBukkitEntity) passenger).getHandle().startRiding(getHandle());
        }
        return false;
    }

    @Override
    @NotNull
    public List<org.bukkit.entity.Entity> getPassengers() {
        return getHandle().getPassengers().stream().map(ForgeBukkitEntity::wrap).collect(Collectors.toList());
    }

    @Override
    public boolean addPassenger(@NotNull org.bukkit.entity.Entity passenger) {
        Preconditions.checkArgument(passenger != null, "passenger == null");

        return ((ForgeBukkitEntity) passenger).getHandle().startRiding(getHandle(), true);
    }

    @Override
    public boolean removePassenger(@NotNull org.bukkit.entity.Entity passenger) {
        Preconditions.checkArgument(passenger != null, "passenger == null");

        ((ForgeBukkitEntity) passenger).getHandle().stopRiding();
        return true;
    }

    @Override
    public boolean isEmpty() {
        return !getHandle().isBeingRidden();
    }

    @Override
    public boolean eject() {
        if (isEmpty()) {
            return false;
        }

        getHandle().removePassengers();
        return true;
    }

    @Override
    public float getFallDistance() {
        return getHandle().fallDistance;
    }

    @Override
    public void setFallDistance(float distance) {
        getHandle().fallDistance = distance;
    }

    @Override
    public void setLastDamageCause(EntityDamageEvent event) {
        lastDamageEvent = event;
    }

    @Override
    public EntityDamageEvent getLastDamageCause() {
        return lastDamageEvent;
    }

    @Override
    @NotNull
    public UUID getUniqueId() {
        return getHandle().getUniqueID();
    }

    @Override
    public int getTicksLived() {
        return getHandle().ticksExisted;
    }

    @Override
    public void setTicksLived(int value) {
        if (value <= 0)
            throw new IllegalArgumentException("Age must be at least 1 tick");

        getHandle().ticksExisted = value;
    }

    @Override
    public void playEffect(EntityEffect type) {
        Preconditions.checkArgument(type != null, "type");

        if (type.getApplicable().isInstance(this)) {
            this.getHandle().world.setEntityState(getHandle(), type.getData());
        }
    }

    public void setHandle(final Entity entity) {
        super.setHandle(entity);
    }

    @Override
    public String toString() {
        return "ForgeBukkitEntity{" + "id=" + getEntityId() + '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ForgeBukkitEntity other = (ForgeBukkitEntity) obj;
        return (this.getEntityId() == other.getEntityId());
    }

    @Override
    public int hashCode() {
        // how tf does this fucking work???????????????
        int hash = 7;
        hash = 29 * hash + this.getEntityId();
        return hash;
    }

    @Override
    public void setMetadata(@NotNull String metadataKey, @NotNull MetadataValue newMetadataValue) {
        server.getEntityMetadata().setMetadata(this, metadataKey, newMetadataValue);
    }

    @Override
    @NotNull
    public List<MetadataValue> getMetadata(@NotNull String metadataKey) {
        return server.getEntityMetadata().getMetadata(this, metadataKey);
    }

    @Override
    public boolean hasMetadata(@NotNull String metadataKey) {
        return server.getEntityMetadata().hasMetadata(this, metadataKey);
    }

    @Override
    public void removeMetadata(@NotNull String metadataKey, @NotNull Plugin owningPlugin) {
        server.getEntityMetadata().removeMetadata(this, metadataKey, owningPlugin);
    }

    @Override
    public boolean isInsideVehicle() {
        return getHandle().isPassenger();
    }

    @Override
    public boolean leaveVehicle() {
        if (!isInsideVehicle()) {
            return false;
        }

        getHandle().stopRiding();
        return true;
    }

    @Override
    public org.bukkit.entity.Entity getVehicle() {
        if (!isInsideVehicle())
            return null;

        return wrap(getHandle().getRidingEntity());
    }

    @Override
    public void setCustomName(String name) {
        // sane limit for name length
        if (name != null && name.length() > 256) {
            name = name.substring(0, 256);
        }

        getHandle().setCustomName(ForgeBukkitChatMessage.fromStringOrNull(name));
    }

    @Override
    public String getCustomName() {
        ITextComponent name = getHandle().getCustomName();

        if (name == null)
            return null;

        return ForgeBukkitChatMessage.fromComponent(name);
    }

    @Override
    public void setCustomNameVisible(boolean flag) {
        getHandle().setCustomNameVisible(flag);
    }

    @Override
    public boolean isCustomNameVisible() {
        return getHandle().isCustomNameVisible();
    }

    @Override
    public void sendMessage(String message) {

    }

    @Override
    public void sendMessage(String[] messages) {

    }

    @Override
    public void sendMessage(UUID sender, String message) {
        this.sendMessage(message); // Most entities don't know about senders
    }

    @Override
    public void sendMessage(UUID sender, String[] messages) {
        this.sendMessage(messages); // Most entities don't know about senders
    }

    @Override
    @NotNull
    public String getName() {
        return ForgeBukkitChatMessage.fromComponent(getHandle().getDisplayName());
    }

    @Override
    @NotNull
    public boolean isPermissionSet(String name) {
        return getPermissibleBase().isPermissionSet(name);
    }

    @Override
    @NotNull
    public boolean isPermissionSet(Permission perm) {
        return ForgeBukkitEntity.getPermissibleBase().isPermissionSet(perm);
    }

    @Override
    @NotNull
    public boolean hasPermission(String name) {
        return getPermissibleBase().hasPermission(name);
    }

    @Override
    @NotNull
    public boolean hasPermission(Permission perm) {
        return getPermissibleBase().hasPermission(perm);
    }

    @Override
    @NotNull
    public PermissionAttachment addAttachment(@NotNull Plugin plugin, @NotNull String name, boolean value) {
        return getPermissibleBase().addAttachment(plugin, name, value);
    }

    @Override
    @NotNull
    public PermissionAttachment addAttachment(@NotNull Plugin plugin) {
        return getPermissibleBase().addAttachment(plugin);
    }

    @Override
    public PermissionAttachment addAttachment(@NotNull Plugin plugin, @NotNull String name, boolean value, int ticks) {
        return getPermissibleBase().addAttachment(plugin, name, value, ticks);
    }

    @Override
    public PermissionAttachment addAttachment(@NotNull Plugin plugin, int ticks) {
        return getPermissibleBase().addAttachment(plugin, ticks);
    }

    @Override
    public void removeAttachment(@NotNull PermissionAttachment attachment) {
        getPermissibleBase().removeAttachment(attachment);
    }

    @Override
    public void recalculatePermissions() {
        getPermissibleBase().recalculatePermissions();
    }

    @Override
    @NotNull
    public Set<PermissionAttachmentInfo> getEffectivePermissions() {
        return getPermissibleBase().getEffectivePermissions();
    }

    @Override
    public boolean isOp() {
        return getPermissibleBase().isOp();
    }

    @Override
    public void setOp(boolean value) {
        getPermissibleBase().setOp(value);
    }

    @Override
    public void setGlowing(boolean flag) {
        getHandle().setGlowing(flag);
        Entity e = getHandle();
        try {
            if ((boolean) getFlag.invoke(e, 6) != flag) {
                setFlag.invoke(e, 6, flag);
            }
        } catch (IllegalAccessException | InvocationTargetException exception) {
            ForgeBukkit.logger.fatal("Unable to exec", exception);
        }
    }

    @Override
    public boolean isGlowing() {
        return getHandle().isGlowing();
    }

    @Override
    public void setInvulnerable(boolean flag) {
        getHandle().setInvulnerable(flag);
    }

    @Override
    public boolean isInvulnerable() {
        return getHandle().isInvulnerableTo(DamageSource.GENERIC);
    }

    @Override
    public boolean isSilent() {
        return getHandle().isSilent();
    }

    @Override
    public void setSilent(boolean flag) {
        getHandle().setSilent(flag);
    }

    @Override
    public boolean hasGravity() {
        return !getHandle().hasNoGravity();
    }

    @Override
    public void setGravity(boolean gravity) {
        getHandle().setNoGravity(!gravity);
    }

    @Override
    public int getPortalCooldown() {
        return getHandle().portalCounter;
    }

    @Override
    public void setPortalCooldown(int cooldown) {
        getHandle().portalCounter = cooldown;
    }

    @Override
    @NotNull
    public Set<String> getScoreboardTags() {
        return getHandle().getTags();
    }

    @Override
    public boolean addScoreboardTag(@NotNull String tag) {
        return getHandle().addTag(tag);
    }

    @Override
    public boolean removeScoreboardTag(@NotNull String tag) {
        return getHandle().removeTag(tag);
    }

    @Override
    @NotNull
    public PistonMoveReaction getPistonMoveReaction() {
        return Objects.requireNonNull(PistonMoveReaction.getById(getHandle().getPushReaction().ordinal()));
    }

    @Override
    @NotNull
    public BlockFace getFacing() {
        // Use this method over getDirection because it handles boats and minecarts.
        return  null; //CraftBlock.notchToBlockFace(getHandle().getAdjustedHorizontalFacing());
    }

    @Override
    @NotNull
    public ForgeBukkitPersistentDataContainer getPersistentDataContainer() {
        return persistentDataContainer;
    }

    @Override
    @NotNull
    public Pose getPose() {
        return Pose.values()[getHandle().getPose().ordinal()];
    }

    public void write(CompoundNBT c) {
        if (!this.persistentDataContainer.isEmpty())
            c.put("BukkitValues", this.persistentDataContainer.toTagCompound());
    }

    public void readBukkitValues(CompoundNBT c) {
        INBT base = c.get("BukkitValues");
        if (base instanceof CompoundNBT)
            this.persistentDataContainer.putAll((CompoundNBT) base);
    }

    protected CompoundNBT save() {
        CompoundNBT compoundNBT = new CompoundNBT();

        compoundNBT.putString("id", getHandle().getEntityString());
        getHandle().writeWithoutTypeId(compoundNBT);

        return compoundNBT;
    }

    private static PermissibleBase getPermissibleBase() {
        if (perm == null) {
            perm = new PermissibleBase(new ServerOperator() {
                @Override
                public boolean isOp() {
                    return false;
                }

                @Override
                public void setOp(boolean value) {

                }
            });
        }
        return perm;
    }
}
