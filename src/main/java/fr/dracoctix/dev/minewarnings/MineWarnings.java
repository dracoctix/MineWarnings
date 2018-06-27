package fr.dracoctix.dev.minewarnings;

import fr.dracoctix.dev.minewarnings.commands.DefaultWarnCommandListener;
import fr.dracoctix.dev.minewarnings.commands.WarnListCommandListener;
import fr.dracoctix.dev.minewarnings.configuration.DefaultConfigReader;
import fr.dracoctix.dev.minewarnings.storage.WarnManagerInterface;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.Connection;
import java.sql.SQLException;

public final class MineWarnings extends JavaPlugin {
    private static MineWarnings plugin;
    private DefaultConfigReader conf;
    private Connection db;
    private WarnManagerInterface warnManager;

    @Override
    public void onEnable() {
        plugin = this;

        getLogger().info("Chargement du plugin de gestion des avertissements.");
        saveDefaultConfig();

        conf = new DefaultConfigReader(getConfig());

        db = conf.getDatabase();
        warnManager = conf.getWarnManager();

        registerCommands();

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

    public static MineWarnings getPlugin()
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
        getCommand("warn").setExecutor(new DefaultWarnCommandListener(warnManager));
    }

    public WarnManagerInterface getWarnManager() {
        return warnManager;
    }
}
