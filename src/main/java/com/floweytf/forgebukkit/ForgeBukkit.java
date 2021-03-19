package com.floweytf.forgebukkit;

import net.minecraft.network.play.server.SUpdateBossInfoPacket;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.end.DragonFightManager;
import net.minecraft.world.server.ServerBossInfo;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.server.FMLServerStartedEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.Method;

@Mod("forgebukkit")
public class ForgeBukkit
{
    public static final Logger logger = LogManager.getLogger("ForgeBukkit");
    public static MinecraftServer server = null;
    public static Method generatePortal = ObfuscationReflectionHelper.findMethod(DragonFightManager.class, "func_186094_a ", boolean.class);
    public static Method sendUpdate = ObfuscationReflectionHelper.findMethod(ServerBossInfo.class, "func_186759_a ", SUpdateBossInfoPacket.Operation.class);
    public static String version = "1.0";

    public static void invokeFatal(Throwable e) {
        logger.fatal("Failed to invoke method!", e);
    }

    public ForgeBukkit() {
        logger.info("ForgeBukkit started!");
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::server);
    }

    private void server(final FMLServerStartedEvent event) {
        server = event.getServer();
    }
}