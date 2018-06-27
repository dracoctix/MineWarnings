package fr.dracoctix.dev.warnings;

import fr.dracoctix.dev.warnings.commands.WarnListCommandListener;
import fr.dracoctix.dev.warnings.configuration.DefaultConfigReader;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.Connection;
import java.sql.SQLException;

public final class Warnings extends JavaPlugin {
    private static Warnings plugin;
    private DefaultConfigReader conf;
    private Connection db;

    @Override
    public void onEnable() {
        plugin = this;

        getLogger().info("Chargement du plugin de gestion des avertissements.");
        saveDefaultConfig();

        conf = new DefaultConfigReader(getConfig());

        registerCommands();

        db = conf.getDatabase();

        getLogger().info("stop");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        try {
            if (db != null && !db.isClosed()) {
                db.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Warnings getPlugin()
    {
        return plugin;
    }

    public DefaultConfigReader getConf()
    {
        return conf;
    }

    private void registerCommands()
    {
        getCommand("warn-causes").setExecutor(new WarnListCommandListener());
    }
}
