package com.floweytf.forgebukkit;

import net.minecraft.server.dedicated.DedicatedServer;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.server.*;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(ForgeBukkit.MODID)
public class ForgeBukkit
{
    public static final Logger logger = LogManager.getLogger("ForgeBukkit");

    public static DedicatedServer server = null;
    public static final String MODID = "forgebukkit";


    public static String version = "1.0";

    public static void invokeFatal(Throwable e) {
        logger.fatal("Failed to invoke method!", e);
    }

    public ForgeBukkit() {
        logger.info("ForgeBukkit started!");
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::serverStarted);
    }
    private void serverStarted(final FMLServerStartedEvent event) {
        if(event.getServer() instanceof DedicatedServer)
            server = (DedicatedServer) event.getServer();
    }

}