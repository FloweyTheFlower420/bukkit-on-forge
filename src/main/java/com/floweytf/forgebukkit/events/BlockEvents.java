package com.floweytf.forgebukkit.events;

import com.floweytf.forgebukkit.ForgeBukkitBlock;
import com.floweytf.forgebukkit.entity.ForgeBukkitEntity;
import com.floweytf.forgebukkit.entity.impl.ForgeBukkitPlayer;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import org.bukkit.event.block.*;

/*
BlockBreakEvent
BlockBurnEvent
BlockCanBuildEvent
BlockCookEvent
BlockDamageEvent
BlockDispenseArmorEvent
BlockDispenseEvent
BlockDropItemEvent
BlockEvent
BlockExpEvent
BlockExplodeEvent
BlockFadeEvent
BlockFertilizeEvent
BlockFormEvent
BlockFromToEvent
BlockGrowEvent
BlockIgniteEvent
BlockMultiPlaceEvent
BlockPhysicsEvent
BlockPistonEvent
BlockPistonExtendEvent
BlockPistonRetractEvent
BlockPlaceEvent
BlockRedstoneEvent
BlockShearEntityEvent
BlockSpreadEvent
CauldronLevelChangeEvent
EntityBlockFormEvent
FluidLevelChangeEvent
LeavesDecayEvent
MoistureChangeEvent
NotePlayEvent
SignChangeEvent
SpongeAbsorbEvent
 */


public class BlockEvents {
    @SubscribeEvent
    public void onBlockBreak(BlockEvent.BreakEvent event) {
        // wrap
        new BlockBreakEvent(ForgeBukkitBlock.wrap(event.getState().getBlock()), (ForgeBukkitPlayer)ForgeBukkitEntity.wrap(event.getPlayer()));
    }

    @SubscribeEvent
    public void onBlockBurn(BlockEvent.)

}
