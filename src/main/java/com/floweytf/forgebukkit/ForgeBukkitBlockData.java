package com.floweytf.forgebukkit;

import org.bukkit.Material;
import org.bukkit.SoundGroup;
import org.bukkit.block.data.BlockData;

public class ForgeBukkitBlockData implements BlockData {
    /**
     * Get the Material represented by this block data.
     *
     * @return the material
     */
    @Override
    public Material getMaterial() {
        return null;
    }

    /**
     * Gets a string, which when passed into a method such as
     * {@link Server#createBlockData(String)} will unambiguously
     * recreate this instance.
     *
     * @return serialized data string for this block
     */
    @Override
    public String getAsString() {
        return null;
    }

    /**
     * Gets a string, which when passed into a method such as
     * {@link Server#createBlockData(String)} will recreate this or a
     * similar instance where unspecified states (if any) may be optionally
     * omitted. If this instance was parsed and states are omitted, this exact
     * instance will be creatable when parsed again, else their equality cannot
     * be guaranteed.
     * <p>
     * This method will only take effect for BlockData instances created by
     * methods such as {@link Server#createBlockData(String)} or any similar
     * method whereby states are optionally defined. If otherwise, the result of
     * {@link #getAsString()} will be returned. The following behaviour would be
     * expected:
     * <pre>{@code
     * String dataString = "minecraft:chest[waterlogged=true]"
     * BlockData data = Bukkit.createBlockData(dataString);
     * dataString.equals(data.getAsString(true)); // This would return true
     * dataString.equals(data.getAsString(false)); // This would return false as all states are present
     * dataString.equals(data.getAsString()); // This is equivalent to the above, "getAsString(false)"
     * }</pre>
     *
     * @param hideUnspecified true if unspecified states should be omitted,
     *                        false if they are to be shown as performed by {@link #getAsString()}.
     * @return serialized data string for this block
     */
    @Override
    public String getAsString(boolean hideUnspecified) {
        return null;
    }

    /**
     * Merges all explicitly set states from the given data with this BlockData.
     * <br>
     * Note that the given data MUST have been created from one of the String
     * parse methods, e.g. {@link Server#createBlockData(String)} and
     * not have been subsequently modified.
     * <br>
     * Note also that the block types must match identically.
     *
     * @param data the data to merge from
     * @return a new instance of this blockdata with the merged data
     */
    @Override
    public BlockData merge(BlockData data) {
        return null;
    }

    /**
     * Checks if the specified BlockData matches this block data.
     * <br>
     * The semantics of this method are such that for manually created or
     * modified BlockData it has the same effect as
     * {@link Object#equals(Object)}, whilst for parsed data (that to
     * which {@link #merge(BlockData)} applies, it will
     * return true when the type and all explicitly set states match.
     * <br>
     * <b>Note that these semantics mean that a.matches(b) may not be the same
     * as b.matches(a)</b>
     *
     * @param data the data to match against (normally a parsed constant)
     * @return if there is a match
     */
    @Override
    public boolean matches(BlockData data) {
        return false;
    }

    /**
     * Returns a copy of this BlockData.
     *
     * @return a copy of the block data
     */
    @Override
    public BlockData clone() {
        return null;
    }

    /**
     * Gets the block's {@link SoundGroup} which can be used to get its step
     * sound, hit sound, and others.
     *
     * @return the sound effect group
     */
    @Override
    public SoundGroup getSoundGroup() {
        return null;
    }
}
