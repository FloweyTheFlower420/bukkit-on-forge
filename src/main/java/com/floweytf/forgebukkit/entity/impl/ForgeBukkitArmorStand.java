package com.floweytf.forgebukkit.entity.impl;

import com.floweytf.forgebukkit.ForgeBukkitServer;
import com.floweytf.forgebukkit.entity.ForgeBukkitLivingEntity;
import net.minecraft.entity.item.ArmorStandEntity;
import net.minecraft.util.math.Rotations;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.EulerAngle;
import com.floweytf.forgebukkit.ForgeBukkitEquipmentSlot;

public class ForgeBukkitArmorStand extends ForgeBukkitLivingEntity implements ArmorStand {

    public ForgeBukkitArmorStand(ForgeBukkitServer server, ArmorStandEntity entity) {
        super(server, entity);
    }

    @Override
    public String toString() {
        return "ForgeBukkitLivingEntity";
    }

    @Override
    public EntityType getType() {
        return EntityType.ARMOR_STAND;
    }

    @Override
    public ArmorStandEntity getHandle() {
        return (ArmorStandEntity) super.getHandle();
    }

    @Override
    public ItemStack getItemInHand() {
        return getEquipment().getItemInHand();
    }

    @Override
    public void setItemInHand(ItemStack item) {
        getEquipment().setItemInHand(item);
    }

    @Override
    public ItemStack getBoots() {
        return getEquipment().getBoots();
    }

    @Override
    public void setBoots(ItemStack item) {
        getEquipment().setBoots(item);
    }

    @Override
    public ItemStack getLeggings() {
        return getEquipment().getLeggings();
    }

    @Override
    public void setLeggings(ItemStack item) {
        getEquipment().setLeggings(item);
    }

    @Override
    public ItemStack getChestplate() {
        return getEquipment().getChestplate();
    }

    @Override
    public void setChestplate(ItemStack item) {
        getEquipment().setChestplate(item);
    }

    @Override
    public ItemStack getHelmet() {
        return getEquipment().getHelmet();
    }

    @Override
    public void setHelmet(ItemStack item) {
        getEquipment().setHelmet(item);
    }

    @Override
    public EulerAngle getBodyPose() {
        return toBukkit(getHandle().getBodyRotation());
    }

    @Override
    public void setBodyPose(EulerAngle pose) {
        getHandle().setBodyRotation(fromBukkit(pose));
    }

    @Override
    public EulerAngle getLeftArmPose() {
        return toBukkit(getHandle().getLeftArmRotation());
    }

    @Override
    public void setLeftArmPose(EulerAngle pose) {
        getHandle().setLeftArmRotation(fromBukkit(pose));
    }

    @Override
    public EulerAngle getRightArmPose() {
        return toBukkit(getHandle().getRightArmRotation());
    }

    @Override
    public void setRightArmPose(EulerAngle pose) {
        getHandle().setRightArmRotation(fromBukkit(pose));
    }

    @Override
    public EulerAngle getLeftLegPose() {
        return toBukkit(getHandle().getLeftLegRotation());
    }

    @Override
    public void setLeftLegPose(EulerAngle pose) {
        getHandle().setLeftLegRotation(fromBukkit(pose));
    }

    @Override
    public EulerAngle getRightLegPose() {
        return toBukkit(getHandle().getRightLegRotation());
    }

    @Override
    public void setRightLegPose(EulerAngle pose) {
        getHandle().setRightLegRotation(fromBukkit(pose));
    }

    @Override
    public EulerAngle getHeadPose() {
        return toBukkit(getHandle().getHeadRotation());
    }

    @Override
    public void setHeadPose(EulerAngle pose) {
        getHandle().setHeadRotation(fromBukkit(pose));
    }

    @Override
    public boolean hasBasePlate() {
        return !getHandle().hasNoBasePlate();
    }

    @Override
    public void setBasePlate(boolean basePlate) {
        getHandle().setNoBasePlate(!basePlate);
    }

    @Override
    public void setGravity(boolean gravity) {
        super.setGravity(gravity);
        // Armor stands are special
        getHandle().noClip = !gravity;
    }

    @Override
    public boolean isVisible() {
        return !getHandle().isInvisible();
    }

    @Override
    public void setVisible(boolean visible) {
        getHandle().setInvisible(!visible);
    }

    @Override
    public boolean hasArms() {
        return getHandle().getShowArms();
    }

    @Override
    public void setArms(boolean arms) {
        getHandle().setShowArms(arms);
    }

    @Override
    public boolean isSmall() {
        return getHandle().isSmall();
    }

    @Override
    public void setSmall(boolean small) {
        getHandle().setSmall(small);
    }

    private static EulerAngle toBukkit(Rotations old) {
        return new EulerAngle(
            Math.toRadians(old.getX()),
            Math.toRadians(old.getY()),
            Math.toRadians(old.getZ())
        );
    }

    private static Rotations fromBukkit(EulerAngle old) {
        return new Rotations(
            (float) Math.toDegrees(old.getX()),
            (float) Math.toDegrees(old.getY()),
            (float) Math.toDegrees(old.getZ())
        );
    }

    @Override
    public boolean isMarker() {
        return getHandle().hasMarker();
    }

    @Override
    public void setMarker(boolean marker) {
        getHandle().setMarker(marker);
    }

    @Override
    public void addEquipmentLock(EquipmentSlot equipmentSlot, LockType lockType) {
        getHandle().disabledSlots |= (1 << ForgeBukkitEquipmentSlot.fromBukkit(equipmentSlot).getSlotIndex() + lockType.ordinal() * 8);
    }

    @Override
    public void removeEquipmentLock(EquipmentSlot equipmentSlot, LockType lockType) {
        getHandle().disabledSlots &= ~(1 << ForgeBukkitEquipmentSlot.fromBukkit(equipmentSlot).getSlotIndex() + lockType.ordinal() * 8);
    }

    @Override
    public boolean hasEquipmentLock(EquipmentSlot equipmentSlot, LockType lockType) {
        return (getHandle().disabledSlots & (1 << ForgeBukkitEquipmentSlot.fromBukkit(equipmentSlot).getSlotIndex() + lockType.ordinal() * 8)) != 0;
    }
}
