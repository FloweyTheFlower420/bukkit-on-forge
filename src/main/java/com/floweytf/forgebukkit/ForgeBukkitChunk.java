package com.floweytf.forgebukkit;

import com.google.common.base.Preconditions;
import com.google.common.base.Predicates;
import net.minecraft.block.Blocks;
import net.minecraft.dispenser.IBlockSource;
import net.minecraft.util.SharedSeedRandom;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.palette.IPalette;
import net.minecraft.util.palette.IdentityPalette;
import net.minecraft.util.palette.PalettedContainer;
import net.minecraft.world.biome.BiomeContainer;
import net.minecraft.world.chunk.ChunkSection;
import net.minecraft.world.server.ServerWorld;
import org.bukkit.Chunk;
import org.bukkit.ChunkSnapshot;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.block.data.BlockData;
import org.bukkit.entity.Entity;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.plugin.Plugin;

import java.lang.ref.WeakReference;
import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;
import java.util.function.Predicate;

public class ForgeBukkitChunk implements Chunk {
    public static final IPalette<net.minecraft.block.BlockState> REGISTRY_PALETTE = new IdentityPalette<>(net.minecraft.block.Block.BLOCK_STATE_IDS, Blocks.AIR.getDefaultState());
    private WeakReference<net.minecraft.world.chunk.Chunk> weakChunk;
    private final ServerWorld worldServer;
    private final ChunkPos pos;
    private static final PalettedContainer<net.minecraft.block.BlockState> emptyBlockIDs = new ChunkSection(0).getData();
    private static final byte[] emptyLight = new byte[2048];
    private PersistentDataContainer container;

    public ForgeBukkitChunk(net.minecraft.world.chunk.Chunk chunk) {
        this.weakChunk = new WeakReference<>(chunk);

        worldServer = (ServerWorld) getHandle().getWorld();
        pos = getHandle().getPos();
    }

    @Override
    public World getWorld() {
        return Objects.requireNonNull(ForgeBukkitWorld.getWorldWrapper(worldServer.getWorld()));
    }

    public net.minecraft.world.chunk.Chunk getHandle() {
        net.minecraft.world.chunk.Chunk c = weakChunk.get();

        if (c == null) {
            c = worldServer.getChunk(pos.x, pos.z);

            weakChunk = new WeakReference<>(c);
        }

        return c;
    }

    void breakLink() {
        weakChunk.clear();
    }

    @Override
    public int getX() {
        return pos.x;
    }

    @Override
    public int getZ() {
        return pos.z;
    }

    @Override
    public String toString() {
        return "CraftChunk{" + "x=" + getX() + "z=" + getZ() + '}';
    }

    @Override
    public Block getBlock(int x, int y, int z) {
        validateChunkCoordinates(x, y, z);

        return new ForgeBukkitBlock(worldServer, new BlockPos((pos.x << 4) | x, y, (pos.z << 4) | z));
    }

    @Override
    public Entity[] getEntities() {
        if (!isLoaded())
            getWorld().getChunkAt(pos.x, pos.z); // Transient load for this tick

        int count = 0, index = 0;
        net.minecraft.world.chunk.Chunk chunk = getHandle();

        for (int i = 0; i < 16; i++) {
            count += chunk.getEntityLists()[i].size();
        }

        Entity[] entities = new Entity[count];

        for (int i = 0; i < 16; i++) {

            for (Object obj : chunk.getEntityLists()[i].toArray()) {
                if (!(obj instanceof net.minecraft.entity.Entity))
                    continue;

                //entities[index++] = ((net.minecraft.server.Entity) obj).getBukkitEntity();
            }
        }

        return entities;
    }

    @Override
    public BlockState[] getTileEntities() {
        if (!isLoaded())
            getWorld().getChunkAt(pos.x, pos.z); // Transient load for this tick
        
        int index = 0;
        net.minecraft.world.chunk.Chunk chunk = getHandle();

        BlockState[] entities = new BlockState[chunk.getTileEntityMap().size()];

        for (Object obj : chunk.getTileEntityMap().keySet().toArray()) {
            if (!(obj instanceof BlockPos))
                continue;

            BlockPos pos = (BlockPos) obj;
            entities[index++] = ForgeBukkitWorld.getWorldWrapper(worldServer.getWorld()).getBlockAt(pos.getX(),pos.getY(),pos.getZ()).getState();
        }

        return entities;
    }

    @Override
    public boolean isLoaded() {
        return getWorld().isChunkLoaded(this);
    }

    @Override
    public boolean load() {
        return getWorld().loadChunk(getX(), getZ(), true);
    }

    @Override
    public boolean load(boolean generate) {
        return getWorld().loadChunk(getX(), getZ(), generate);
    }

    @Override
    public boolean unload() {
        return getWorld().unloadChunk(getX(), getZ());
    }

    @Override
    public boolean isSlimeChunk() {
        // 987234911L is deterimined in EntitySlime when seeing if a slime can spawn in a chunk
        return SharedSeedRandom.seedSlimeChunk(getX(), getZ(), getWorld().getSeed(), 987234911L).nextInt(10) == 0;
    }

    @Override
    public boolean unload(boolean save) {
        return getWorld().unloadChunk(getX(), getZ(), save);
    }

    @Override
    public boolean isForceLoaded() {
        return getWorld().isChunkForceLoaded(getX(), getZ());
    }

    @Override
    public void setForceLoaded(boolean forced) {
        getWorld().setChunkForceLoaded(getX(), getZ(), forced);
    }

    @Override
    public boolean addPluginChunkTicket(Plugin plugin) {
        return getWorld().addPluginChunkTicket(getX(), getZ(), plugin);
    }

    @Override
    public boolean removePluginChunkTicket(Plugin plugin) {
        return getWorld().removePluginChunkTicket(getX(), getZ(), plugin);
    }

    @Override
    public Collection<Plugin> getPluginChunkTickets() {
        return getWorld().getPluginChunkTickets(getX(), getZ());
    }

    @Override
    public long getInhabitedTime() {
        return getHandle().getInhabitedTime();
    }

    @Override
    public void setInhabitedTime(long ticks) {
        Preconditions.checkArgument(ticks >= 0, "ticks cannot be negative");

        getHandle().setInhabitedTime(ticks);
    }

    @Override
    public boolean contains(BlockData block) {
        Preconditions.checkArgument(block != null, "Block cannot be null");

        Predicate<net.minecraft.block.BlockState> nms = Predicates.equalTo(((ForgeBukkitBlockData) block).getState());
        for (ChunkSection section : getHandle().getSections()) {
            if (section != null && section.getData().func_235963_a_(nms)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public ChunkSnapshot getChunkSnapshot() {
        return getChunkSnapshot(true, false, false);
    }

    @Override
    public ChunkSnapshot getChunkSnapshot(boolean includeMaxBlockY, boolean includeBiome, boolean includeBiomeTempRain) {
        /*net.minecraft.world.chunk.Chunk chunk = getHandle();

        ChunkSection[] cs = chunk.getSections();
        PalettedContainer[] sectionBlockIDs = new PalettedContainer[cs.length];
        byte[][] sectionSkyLights = new byte[cs.length][];
        byte[][] sectionEmitLights = new byte[cs.length][];
        boolean[] sectionEmpty = new boolean[cs.length];

        for (int i = 0; i < cs.length; i++) {
            if (cs[i] == null) { // Section is empty?
                sectionBlockIDs[i] = emptyBlockIDs;
                sectionSkyLights[i] = emptyLight;
                sectionEmitLights[i] = emptyLight;
                sectionEmpty[i] = true;
            } else { // Not empty
                CompoundNBT data = new CompoundNBT();
                cs[i].getData().writeChunkPalette(data, "Palette", "BlockStates");

                PalettedContainer<net.minecraft.block.BlockState> blockids = new PalettedContainer<net.minecraft.block.BlockState>(REGISTRY_PALETTE, net.minecraft.block.Block.BLOCK_STATE_IDS, NBTUtil::writeBlockState, NBTUtil::readBlockState, Blocks.AIR); // TODO: snapshot whole ChunkSection
                blockids.writeChunkPalette(data.getList("Palette", Constants.NBT.TAG_COMPOUND), data.getLongArray("BlockStates"));

                sectionBlockIDs[i] = blockids;

                LightEngine lightengine = ForgeBukkitWorld.getWorldWrapper(chunk.getWorld()).getChunkProvider().getLightEngine();
                NibbleArray skyLightArray = lightengine.a(EnumSkyBlock.SKY).a(SectionPosition.a(x, i, z));
                if (skyLightArray == null) {
                    sectionSkyLights[i] = emptyLight;
                } else {
                    sectionSkyLights[i] = new byte[2048];
                    System.arraycopy(skyLightArray.asBytes(), 0, sectionSkyLights[i], 0, 2048);
                }
                NibbleArray emitLightArray = lightengine.a(EnumSkyBlock.BLOCK).a(SectionPosition.a(x, i, z));
                if (emitLightArray == null) {
                    sectionEmitLights[i] = emptyLight;
                } else {
                    sectionEmitLights[i] = new byte[2048];
                    System.arraycopy(emitLightArray.asBytes(), 0, sectionEmitLights[i], 0, 2048);
                }
            }
        }

        HeightMap hmap = null;

        if (includeMaxBlockY) {
            hmap = new HeightMap(null, HeightMap.Type.MOTION_BLOCKING);
            hmap.a(chunk.heightMap.get(HeightMap.Type.MOTION_BLOCKING).a());
        }

        BiomeStorage biome = null;

        if (includeBiome || includeBiomeTempRain) {
            biome = chunk.getBiomeIndex();
        }

        World world = getWorld();
        return new CraftChunkSnapshot(getX(), getZ(), world.getName(), world.getFullTime(), sectionBlockIDs, sectionSkyLights, sectionEmitLights, sectionEmpty, hmap, biome);
    */}

    @Override
    public PersistentDataContainer getPersistentDataContainer() {
        return container;
    }

    public static ChunkSnapshot getEmptyChunkSnapshot(int x, int z, CraftWorld world, boolean includeBiome, boolean includeBiomeTempRain) {
        BiomeContainer biome = null;

        if (includeBiome || includeBiomeTempRain) {
            WorldChunkManager wcm = world.getHandle().getChunkProvider().getChunkGenerator().getWorldChunkManager();
            biome = new BiomeStorage(world.getHandle().r().b(IRegistry.ay), new ChunkCoordIntPair(x, z), wcm);
        }

        /* Fill with empty data */
        int hSection = world.getMaxHeight() >> 4;
        PalettedContainer[] blockIDs = new PalettedContainer[hSection];
        byte[][] skyLight = new byte[hSection][];
        byte[][] emitLight = new byte[hSection][];
        boolean[] empty = new boolean[hSection];

        for (int i = 0; i < hSection; i++) {
            blockIDs[i] = emptyBlockIDs;
            skyLight[i] = emptyLight;
            emitLight[i] = emptyLight;
            empty[i] = true;
        }

        return new CraftChunkSnapshot(x, z, world.getName(), world.getFullTime(), blockIDs, skyLight, emitLight, empty, new HeightMap(null, HeightMap.Type.MOTION_BLOCKING), biome);
    }

    static void validateChunkCoordinates(int x, int y, int z) {
        Preconditions.checkArgument(0 <= x && x <= 15, "x out of range (expected 0-15, got %s)", x);
        Preconditions.checkArgument(0 <= y && y <= 255, "y out of range (expected 0-255, got %s)", y);
        Preconditions.checkArgument(0 <= z && z <= 15, "z out of range (expected 0-15, got %s)", z);
    }

    static {
        Arrays.fill(emptyLight, (byte) 0xFF);
    }
}