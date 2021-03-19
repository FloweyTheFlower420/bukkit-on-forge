package com.floweytf.forgebukkit.entity;

import com.floweytf.forgebukkit.ForgeBukkit;
import com.floweytf.forgebukkit.ForgeBukkitServer;
import com.google.common.base.Preconditions;
import com.google.common.collect.Sets;
import net.minecraft.entity.CreatureAttribute;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.boss.WitherEntity;
import net.minecraft.entity.item.ArmorStandEntity;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Hand;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import org.apache.commons.lang.Validate;
import org.bukkit.FluidCollisionMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.block.Block;
import org.bukkit.entity.*;
import org.bukkit.entity.memory.MemoryKey;
import org.bukkit.event.entity.EntityPotionEffectEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionData;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.potion.PotionType;
import org.bukkit.util.BlockIterator;
import org.bukkit.util.RayTraceResult;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.PreparedStatement;
import java.util.*;

public class ForgeBukkitLivingEntity extends ForgeBukkitEntity implements LivingEntity {
    private static final Method damageEntity = ObfuscationReflectionHelper.findMethod(LivingEntity.class, "func_70665_d", DamageSource.class, float.class);
    private CraftEntityEquipment equipment;
    boolean canPickUpLoot = false;
    public boolean collides = true;
    public Set<UUID> collidableExemptions = new HashSet<>();

    public ForgeBukkitLivingEntity(final ForgeBukkitServer server, final net.minecraft.entity.LivingEntity entity) {
        super(server, entity);

        if (entity instanceof MobEntity || entity instanceof ArmorStandEntity) {
            equipment = new CraftEntityEquipment(this);
        }
    }

    @Override
    public double getHealth() {
        return Math.min(Math.max(0, getHandle().getHealth()), getMaxHealth());
    }

    @Override
    public void setHealth(double health) {
        health = (float) health;
        if ((health < 0) || (health > getMaxHealth()))
            throw new IllegalArgumentException("Health must be between 0 and " + getMaxHealth() + "(" + health + ")");

        getHandle().setHealth((float) health);

        // my personal favorite way to die
        if (health == 0)
            getHandle().onDeath(DamageSource.GENERIC);
    }

    @Override
    public double getAbsorptionAmount() {
        return getHandle().getAbsorptionAmount();
    }

    @Override
    public void setAbsorptionAmount(double amount) {
        Preconditions.checkArgument(amount >= 0 && Double.isFinite(amount), "amount < 0 or non-finite");

        getHandle().setAbsorptionAmount((float) amount);
    }

    @Override
    public double getMaxHealth() {
        return getHandle().getMaxHealth();
    }

    @Override
    public void setMaxHealth(double amount) {
        Validate.isTrue(amount > 0, "Max health must be greater than 0");

        // how tf does getAttribute on a must-have attribute null??
        getHandle().getAttribute(Attributes.MAX_HEALTH).setBaseValue(amount);

        if (getHealth() > amount)
            setHealth(amount);
    }

    @Override
    public void resetMaxHealth() {
        setMaxHealth(getHandle().getAttribute(Attributes.MAX_HEALTH).getAttribute().getDefaultValue());
    }

    @Override
    public double getEyeHeight() {
        return getHandle().getEyeHeight();
    }

    @Override
    public double getEyeHeight(boolean ignorePose) {
        return getEyeHeight();
    }

    private List<Block> getLineOfSight(Set<Material> transparent, int maxDistance, int maxLength) {
        if (transparent == null)
            transparent = Sets.newHashSet(Material.AIR, Material.CAVE_AIR, Material.VOID_AIR);

        if (maxDistance > 120)
            maxDistance = 120;

        ArrayList<Block> blocks = new ArrayList<>();
        Iterator<Block> itr = new BlockIterator(this, maxDistance);

        while (itr.hasNext()) {
            Block block = itr.next();
            blocks.add(block);
            if (maxLength != 0 && blocks.size() > maxLength)
                blocks.remove(0);
            Material material = block.getType();

            if (!transparent.contains(material))
                break;
        }
        return blocks;
    }

    @Override
    @NotNull
    public List<Block> getLineOfSight(Set<Material> transparent, int maxDistance) {
        return getLineOfSight(transparent, maxDistance, 0);
    }

    @Override
    @NotNull
    public Block getTargetBlock(Set<Material> transparent, int maxDistance) {
        List<Block> blocks = getLineOfSight(transparent, maxDistance, 1);
        return blocks.get(0);
    }

    @Override
    @NotNull
    public List<Block> getLastTwoTargetBlocks(Set<Material> transparent, int maxDistance) {
        return getLineOfSight(transparent, maxDistance, 2);
    }

    @Override
    public Block getTargetBlockExact(int maxDistance) {
        return this.getTargetBlockExact(maxDistance, FluidCollisionMode.NEVER);
    }

    @Override
    public Block getTargetBlockExact(int maxDistance, @NotNull FluidCollisionMode fluidCollisionMode) {
        RayTraceResult hitResult = this.rayTraceBlocks(maxDistance, fluidCollisionMode);
        return (hitResult != null ? hitResult.getHitBlock() : null);
    }

    @Override
    public RayTraceResult rayTraceBlocks(double maxDistance) {
        return this.rayTraceBlocks(maxDistance, FluidCollisionMode.NEVER);
    }

    @Override
    public RayTraceResult rayTraceBlocks(double maxDistance, @NotNull FluidCollisionMode fluidCollisionMode) {
        Location eyeLocation = this.getEyeLocation();
        Vector direction = eyeLocation.getDirection();
        return this.getWorld().rayTraceBlocks(eyeLocation, direction, maxDistance, fluidCollisionMode, false);
    }

    @Override
    public int getRemainingAir() {
        return getHandle().getAir();
    }

    @Override
    public void setRemainingAir(int ticks) {
        getHandle().setAir(ticks);
    }

    @Override
    public int getMaximumAir() {
        return getHandle().getMaxAir();
    }

    @Override
    public void setMaximumAir(int ticks) {
        // um what
        //getHandle().setAir(); = ticks;
    }

    @Override
    public int getArrowCooldown() {
        return getHandle().arrowHitTimer;
    }

    @Override
    public void setArrowCooldown(int ticks) {
        getHandle().arrowHitTimer = ticks;
    }

    @Override
    public int getArrowsInBody() {
        return getHandle().getArrowCountInEntity();
    }

    @Override
    public void setArrowsInBody(int count) {
        Preconditions.checkArgument(count >= 0, "New arrow amount must be >= 0");
        // its even easier with forge!
        getHandle().setArrowCountInEntity(count);
    }

    @Override
    public void damage(double amount) {
        damage(amount, null);
    }

    @Override
    public void damage(double amount, Entity source) {
        // <generic funny comment here>
        DamageSource reason = DamageSource.GENERIC;

        if (source instanceof HumanEntity)
            reason = DamageSource.causePlayerDamage(((ForgeBukkitPlayer) source).getHandle());
        else if (source instanceof LivingEntity)
            reason = DamageSource.causeMobDamage(((ForgeBukkitLivingEntity) source).getHandle());

        try {
            damageEntity.invoke(getHandle(), reason, (float) amount);
        }
        catch (IllegalAccessException | InvocationTargetException e) {
            ForgeBukkit.logger.fatal(e);
        }
    }

    @Override
    @NotNull
    public Location getEyeLocation() {
        Location loc = getLocation();
        loc.setY(loc.getY() + getEyeHeight());
        return loc;
    }

    @Override
    public int getMaximumNoDamageTicks() {
        return getHandle().maxHurtResistantTime;
    }

    @Override
    public void setMaximumNoDamageTicks(int ticks) {
        getHandle().maxHurtResistantTime = ticks;
    }

    @Override
    public double getLastDamage() {
        return getHandle().lastDamage;
    }

    @Override
    public void setLastDamage(double damage) {
        getHandle().lastDamage = (float) damage;
    }

    @Override
    public int getNoDamageTicks() {
        return getHandle().hurtResistantTime;
    }

    @Override
    public void setNoDamageTicks(int ticks) {
        getHandle().hurtResistantTime = ticks;
    }

    @Override
    public net.minecraft.entity.LivingEntity getHandle() {
        return (net.minecraft.entity.LivingEntity) super.getHandle();
    }

    public void setHandle(final LivingEntity entity) {
        super.setHandle((net.minecraft.entity.Entity) entity);
    }

    @Override
    public String toString() {
        return "CraftLivingEntity{" + "id=" + getEntityId() + '}';
    }

    @Override
    public Player getKiller() {
        return getHandle().attackingPlayer == null ? null : (Player) wrap(getHandle().attackingPlayer);
    }

    @Override
    public boolean addPotionEffect(@NotNull PotionEffect effect) {
        return addPotionEffect(effect, false);
    }

    @Override
    public boolean addPotionEffect(PotionEffect effect, boolean force) {
        getHandle().addPotionEffect(new EffectInstance(Effect.get(effect.getType().getId()), effect.getDuration(), effect.getAmplifier(), effect.isAmbient(), effect.hasParticles()), EntityPotionEffectEvent.Cause.PLUGIN);
        return true;
    }

    @Override
    public boolean addPotionEffects(Collection<PotionEffect> effects) {
        boolean success = true;
        for (PotionEffect effect : effects) {
            success &= addPotionEffect(effect);
        }
        return success;
    }

    @Override
    public boolean hasPotionEffect(PotionEffectType type) {
        Effect effect = Effect.get(type.getId());
        Preconditions.checkNotNull(effect);
        return getHandle().isPotionActive(effect);
    }

    @Override
    public PotionEffect getPotionEffect(PotionEffectType type) {
        Effect effect = Effect.get(type.getId());
        Preconditions.checkNotNull(effect);

        EffectInstance handle = getHandle().getActivePotionEffect(effect);
        if(handle == null)
            return null;

        PotionEffectType potionEffectType = PotionEffectType.getById(Effect.getId(handle.getPotion()));
        Preconditions.checkNotNull(potionEffectType);

        return new PotionEffect(potionEffectType, handle.getDuration(), handle.getAmplifier(), handle.isAmbient(), handle.doesShowParticles());
    }

    @Override
    public void removePotionEffect(PotionEffectType type) {
        getHandle().removePotionEffect(Effect.get(type.getId()), EntityPotionEffectEvent.Cause.PLUGIN);
    }

    @Override
    @NotNull
    public Collection<PotionEffect> getActivePotionEffects() {
        List<PotionEffect> effects = new ArrayList<>();
        for (EffectInstance handle : getHandle().getActivePotionMap().values()) {
            PotionEffectType potionEffectType = PotionEffectType.getById(Effect.getId(handle.getPotion()));
            Preconditions.checkNotNull(potionEffectType);
            effects.add(new PotionEffect(
                potionEffectType,
                handle.getDuration(),
                handle.getAmplifier(),
                handle.isAmbient(),
                handle.doesShowParticles())
            );
        }
        return effects;
    }

    /*
    @Override
    public <T extends Projectile> T launchProjectile(Class<? extends T> projectile) {
        return launchProjectile(projectile, null);
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T extends Projectile> T launchProjectile(Class<? extends T> projectile, Vector velocity) {
        net.minecraft.server.World world = ((CraftWorld) getWorld()).getHandle();
        net.minecraft.server.Entity launch = null;

        if (Snowball.class.isAssignableFrom(projectile)) {
            launch = new EntitySnowball(world, getHandle());
            ((EntityProjectile) launch).a(getHandle(), getHandle().pitch, getHandle().yaw, 0.0F, 1.5F, 1.0F); // ItemSnowball
        } else if (Egg.class.isAssignableFrom(projectile)) {
            launch = new EntityEgg(world, getHandle());
            ((EntityProjectile) launch).a(getHandle(), getHandle().pitch, getHandle().yaw, 0.0F, 1.5F, 1.0F); // ItemEgg
        } else if (EnderPearl.class.isAssignableFrom(projectile)) {
            launch = new EntityEnderPearl(world, getHandle());
            ((EntityProjectile) launch).a(getHandle(), getHandle().pitch, getHandle().yaw, 0.0F, 1.5F, 1.0F); // ItemEnderPearl
        } else if (AbstractArrow.class.isAssignableFrom(projectile)) {
            if (TippedArrow.class.isAssignableFrom(projectile)) {
                launch = new EntityTippedArrow(world, getHandle());
                ((EntityTippedArrow) launch).setType(CraftPotionUtil.fromBukkit(new PotionData(PotionType.WATER, false, false)));
            } else if (SpectralArrow.class.isAssignableFrom(projectile)) {
                launch = new EntitySpectralArrow(world, getHandle());
            } else if (Trident.class.isAssignableFrom(projectile)) {
                launch = new EntityThrownTrident(world, getHandle(), new net.minecraft.server.ItemStack(net.minecraft.server.Items.TRIDENT));
            } else {
                launch = new EntityTippedArrow(world, getHandle());
            }
            ((EntityArrow) launch).a(getHandle(), getHandle().pitch, getHandle().yaw, 0.0F, 3.0F, 1.0F); // ItemBow
        } else if (ThrownPotion.class.isAssignableFrom(projectile)) {
            if (LingeringPotion.class.isAssignableFrom(projectile)) {
                launch = new EntityPotion(world, getHandle());
                ((EntityPotion) launch).setItem(CraftItemStack.asNMSCopy(new ItemStack(Material.LINGERING_POTION, 1)));
            } else {
                launch = new EntityPotion(world, getHandle());
                ((EntityPotion) launch).setItem(CraftItemStack.asNMSCopy(new ItemStack(Material.SPLASH_POTION, 1)));
            }
            ((EntityProjectile) launch).a(getHandle(), getHandle().pitch, getHandle().yaw, -20.0F, 0.5F, 1.0F); // ItemSplashPotion
        } else if (ThrownExpBottle.class.isAssignableFrom(projectile)) {
            launch = new EntityThrownExpBottle(world, getHandle());
            ((EntityProjectile) launch).a(getHandle(), getHandle().pitch, getHandle().yaw, -20.0F, 0.7F, 1.0F); // ItemExpBottle
        } else if (FishHook.class.isAssignableFrom(projectile) && getHandle() instanceof EntityHuman) {
            launch = new EntityFishingHook((EntityHuman) getHandle(), world, 0, 0);
        } else if (Fireball.class.isAssignableFrom(projectile)) {
            Location location = getEyeLocation();
            Vector direction = location.getDirection().multiply(10);

            if (SmallFireball.class.isAssignableFrom(projectile)) {
                launch = new EntitySmallFireball(world, getHandle(), direction.getX(), direction.getY(), direction.getZ());
            } else if (WitherSkull.class.isAssignableFrom(projectile)) {
                launch = new EntityWitherSkull(world, getHandle(), direction.getX(), direction.getY(), direction.getZ());
            } else if (DragonFireball.class.isAssignableFrom(projectile)) {
                launch = new EntityDragonFireball(world, getHandle(), direction.getX(), direction.getY(), direction.getZ());
            } else {
                launch = new EntityLargeFireball(world, getHandle(), direction.getX(), direction.getY(), direction.getZ());
            }

            ((EntityFireball) launch).projectileSource = this;
            launch.setPositionRotation(location.getX(), location.getY(), location.getZ(), location.getYaw(), location.getPitch());
        } else if (LlamaSpit.class.isAssignableFrom(projectile)) {
            Location location = getEyeLocation();
            Vector direction = location.getDirection();

            launch = EntityTypes.LLAMA_SPIT.a(world);

            ((EntityLlamaSpit) launch).setShooter(getHandle());
            ((EntityLlamaSpit) launch).shoot(direction.getX(), direction.getY(), direction.getZ(), 1.5F, 10.0F); // EntityLlama
            launch.setPositionRotation(location.getX(), location.getY(), location.getZ(), location.getYaw(), location.getPitch());
        } else if (ShulkerBullet.class.isAssignableFrom(projectile)) {
            Location location = getEyeLocation();

            launch = new EntityShulkerBullet(world, getHandle(), null, null);
            launch.setPositionRotation(location.getX(), location.getY(), location.getZ(), location.getYaw(), location.getPitch());
        } else if (Firework.class.isAssignableFrom(projectile)) {
            Location location = getEyeLocation();

            launch = new EntityFireworks(world, net.minecraft.server.ItemStack.b, getHandle());
            launch.setPositionRotation(location.getX(), location.getY(), location.getZ(), location.getYaw(), location.getPitch());
        }

        Validate.notNull(launch, "Projectile not supported");

        if (velocity != null) {
            ((T) launch.getBukkitEntity()).setVelocity(velocity);
        }

        world.addEntity(launch);
        return (T) launch.getBukkitEntity();
    }
    */

    @Override
    @NotNull
    public EntityType getType() {
        return EntityType.UNKNOWN;
    }

    @Override
    @NotNull
    public Spigot spigot() {
        return null;
    }

    @Override
    public boolean hasLineOfSight(@NotNull Entity other) {
        return getHandle().hasLineOfSight(((CraftEntity) other).getHandle());
    }

    @Override
    public boolean getRemoveWhenFarAway() {
        return getHandle() instanceof MobEntity && !((MobEntity) getHandle()).persistent;
    }

    @Override
    public void setRemoveWhenFarAway(boolean remove) {
        if (getHandle() instanceof MobEntity) {
            ((MobEntity) getHandle()).persistent = !remove;
        }
    }

    @Override
    public EntityEquipment getEquipment() {
        return equipment;
    }

    @Override
    public void setCanPickupItems(boolean pickup) {
        if(getHandle() instanceof MobEntity)
            ((MobEntity) getHandle()).setCanPickUpLoot(pickup);
        canPickUpLoot = pickup; // appease to gods
    }

    @Override
    public boolean getCanPickupItems() {
        if(getHandle() instanceof MobEntity)
            return ((MobEntity) getHandle()).canPickUpLoot();
        return canPickUpLoot;
    }

    @Override
    public boolean teleport(Location location, PlayerTeleportEvent.TeleportCause cause) {
        if (getHealth() == 0) {
            return false;
        }

        return super.teleport(location, cause);
    }

    @Override
    public boolean isLeashed() {
        if (!(getHandle() instanceof MobEntity))
            return false;
        return ((MobEntity) getHandle()).getLeashHolder() != null;
    }

    @Override
    public Entity getLeashHolder() throws IllegalStateException {
        if (!isLeashed())
            throw new IllegalStateException("Entity not leashed");

        // suppress this dumbass
        return wrap(((MobEntity) getHandle()).getLeashHolder());
    }

    private boolean unleash() {
        if (!isLeashed())

            return false;
        ((MobEntity) getHandle()).setU(true, false);
        return true;
    }

    @Override
    public boolean setLeashHolder(Entity holder) {
        if ((getHandle() instanceof WitherEntity) || !(getHandle() instanceof MobEntity))
            return false;

        if (holder == null)
            return unleash();

        if (holder.isDead())
            return false;

        unleash();
        ((MobEntity) getHandle()).setLeashHolder(((ForgeBukkitEntity) holder).getHandle(), true);
        return true;
    }

    @Override
    public boolean isGliding() {
        return getHandle().getFlag(7);
    }

    @Override
    public void setGliding(boolean gliding) {
        getHandle().setFlag(7, gliding);
    }

    @Override
    public boolean isSwimming() {
        return getHandle().isSwimming();
    }

    @Override
    public void setSwimming(boolean swimming) {
        getHandle().setSwimming(swimming);
    }

    @Override
    public boolean isRiptiding() {
        return getHandle().isSpinAttacking();
    }

    @Override
    public boolean isSleeping() {
        return getHandle().isSleeping();
    }

    @Override
    public AttributeInstance getAttribute(Attribute attribute) {
        return getHandle().craftAttributes.getAttribute(attribute);
    }

    @Override
    public void setAI(boolean ai) {
        if (getHandle() instanceof MobEntity)
            ((MobEntity) this.getHandle()).setNoAI(!ai);
    }

    @Override
    public boolean hasAI() {
        return this.getHandle() instanceof MobEntity && !((MobEntity) this.getHandle()).isAIDisabled();
    }

    @Override
    public void attack(Entity target) {
        Preconditions.checkArgument(target != null, "target == null");
        getHandle().setLastAttackedEntity(((ForgeBukkitEntity) target).getHandle());
    }

    @Override
    public void swingMainHand() {
        getHandle().swing(Hand.MAIN_HAND, true);
    }

    @Override
    public void swingOffHand() {
        getHandle().swing(Hand.OFF_HAND, true);
    }

    @Override
    public void setCollidable(boolean collidable) {
        collides = collidable;
    }

    @Override
    public boolean isCollidable() {
        return collides;
    }

    @Override
    public Set<UUID> getCollidableExemptions() {
        return collidableExemptions;
    }

    @Override
    public <T> T getMemory(MemoryKey<T> memoryKey) {
        return (T) getHandle().getBrain().getMemory(CraftMemoryKey.fromMemoryKey(memoryKey)).map(CraftMemoryMapper::fromNms).orElse(null);
    }

    @Override
    public <T> void setMemory(MemoryKey<T> memoryKey, T t) {
        getHandle().getBrain().setMemory(CraftMemoryKey.fromMemoryKey(memoryKey), CraftMemoryMapper.toNms(t));
    }

    @Override
    public EntityCategory getCategory() {
        CreatureAttribute type = getHandle().getCreatureAttribute(); // Not actually an enum?

        // *yanderedev intensifies* not my fault btw
        if (type == CreatureAttribute.UNDEFINED)
            return EntityCategory.NONE;
        else if (type == CreatureAttribute.UNDEAD)
            return EntityCategory.UNDEAD;
        else if (type == CreatureAttribute.ARTHROPOD)
            return EntityCategory.ARTHROPOD;
        else if (type == CreatureAttribute.ILLAGER)
            return EntityCategory.ILLAGER;
        else if (type == CreatureAttribute.WATER)
            return EntityCategory.WATER;

        throw new UnsupportedOperationException("Unsupported monster type: " + type + ". This is a bug, report this to NOT SPIGOT ME.");
    }

    @Override
    public boolean isInvisible() {
        return getHandle().isInvisible();
    }

    @Override
    public void setInvisible(boolean invisible) {
        getHandle().persistentInvisibility = invisible;
        getHandle().setFlag(5, invisible);
    }
}
