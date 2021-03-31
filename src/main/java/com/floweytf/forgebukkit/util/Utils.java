package com.floweytf.forgebukkit.util;

import org.apache.logging.log4j.LogManager;
import org.bukkit.Bukkit;
import org.bukkit.Utility;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;

public class Utils {
    public static String getBukkitVersion() {
        String result = "Unknown-Version";

        InputStream stream = Bukkit.class.getClassLoader().getResourceAsStream("META-INF/maven/org.bukkit/bukkit/pom.properties");
        Properties properties = new Properties();

        if (stream != null) {
            try {
                properties.load(stream);

                result = properties.getProperty("version");
            }
            catch (IOException ex) {
                LogManager.getLogger(Utils.class.getName()).log(Level.SEVERE, "Could not get Bukkit version!", ex);
            }
        }

        return result;
    }
}
