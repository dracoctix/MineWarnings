package fr.dracoctix.dev.minewarnings.configuration;

import fr.dracoctix.dev.minewarnings.MineWarnings;
import fr.dracoctix.dev.minewarnings.warnings.Cause;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class DefaultConfigReader {
    private FileConfiguration config;
    private HashMap<String, Cause> causes;
    private ArrayList<Integer> banStages;
    private int maxWarningPoints;
    private int pardonTime;
    private boolean hiddenModerator;
    private boolean consoleWarning;
    private boolean mysql;

    private String prefix;
    private Connection database;

    public DefaultConfigReader(FileConfiguration config) {
        this.config = config;
        this.causes = new HashMap<>();
        this.banStages = new ArrayList<>();
        parse();
    }

    public void parse() {
        parseCauses();
        parseSettings();
        if(mysql) {
            parseDatabase();
        }
    }

    private void parseSettings() {
        ConfigurationSection settingsSection = config.getConfigurationSection("warning-settings");

        maxWarningPoints = settingsSection.getInt("max-minewarnings-points-before-ban",10);
        pardonTime = settingsSection.getInt("pardon-time",-1);
        hiddenModerator = settingsSection.getBoolean("hidden-moderator",false);
        consoleWarning = settingsSection.getBoolean("console-can-warn",true);
        banStages = (ArrayList<Integer>)settingsSection.getIntegerList("ban-times");
        mysql = settingsSection.getBoolean("use-mysql",false);

        if(banStages.isEmpty()) {
            banStages.add(-1);
        }
    }

    private void parseCauses() {
        causes.clear();
        ConfigurationSection causesSection = config.getConfigurationSection("default-warning-causes");

        for(String id : causesSection.getKeys(false))
        {
            ConfigurationSection section = causesSection.getConfigurationSection(id);
            causes.put(id,new Cause(id,section.getInt("points",0),section.getInt("expiration", -1), section.getString("description","")));
        }
    }

    private void parseDatabase() {
        ConfigurationSection dbSection = config.getConfigurationSection("mysql");
        String username = dbSection.getString("username");
        String password = dbSection.getString("password");
        String server = dbSection.getString("server");
        int port = dbSection.getInt("port");
        String name = dbSection.getString("name");
        prefix = dbSection.getString("prefix");

        try {
            database = DriverManager.getConnection("jdbc:mysql://" + server + ":" + port + "/" + name,username,password);
            tablesInitialisation();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void tablesInitialisation() throws SQLException {
        String warningsTableQuery = "CREATE TABLE IF NOT EXISTS $tableName(id INTEGER PRIMARY KEY AUTO_INCREMENT, user VARCHAR(255) NOT NULL, moderator VARCHAR(255) NOT NULL, start DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL, points INTEGER NOT NULL, days INTEGER NOT NULL, description TEXT NOT NULL, justification TEXT);";
        String bansTableQuery = "CREATE TABLE IF NOT EXISTS $tableName(id INTEGER PRIMARY KEY AUTO_INCREMENT, start DATETIME NOT NULL, days INTEGER NOT NULL, stage INTEGER NOT NULL);";

        warningsTableQuery = warningsTableQuery.replace("$tableName", prefix+"minewarnings");
        bansTableQuery = bansTableQuery.replace("$tableName",prefix+"bans");

        PreparedStatement wtStatement = database.prepareStatement(warningsTableQuery);
        PreparedStatement btStatement = database.prepareStatement(bansTableQuery);

        wtStatement.executeUpdate();
        btStatement.executeUpdate();
    }

    public void persist() {
        ConfigurationSection settingsSection = config.getConfigurationSection("warning-settings");

        settingsSection.set("max-minewarnings-points-before-ban", 255);
        settingsSection.set("ban-times",banStages);
        settingsSection.set("pardon-time",pardonTime);
        settingsSection.set("hidden-moderator",hiddenModerator);
        settingsSection.set("console-can-warn",consoleWarning);
        settingsSection.set("use-mysql",mysql);

        config.set("default-warning-causes",null);

        ConfigurationSection causeSection = config.createSection("default-warning-causes");

        for(String id : causes.keySet()) {
            Cause c = causes.get(id);
            ConfigurationSection cause = causeSection.createSection(id);
            cause.set("points",c.getPoints());
            cause.set("expiration",c.getExpirationTime());
            cause.set("description",c.getDescription());
        }

        MineWarnings.getPlugin().saveConfig();

    }

    // GETTERS/SETTERS
    public HashMap<String, Cause> getCauses() {
        return causes;
    }

    public Cause getCause(String id) {
        return causes.get(id);
    }

    private void addCause(String id, int points, int expiration, String description) {
        causes.put(id, new Cause(id, points, expiration, description));
    }

    private void addCause(Cause c) {
        causes.put(c.getId(), c);
    }

    public FileConfiguration getConfig() {
        return config;
    }

    public void setConfig(FileConfiguration config) {
        this.config = config;
    }

    public ArrayList<Integer> getBanStages() {
        return banStages;
    }

    public int getMaxWarningPoints() {
        return maxWarningPoints;
    }

    public void setMaxWarningPoints(int maxWarningPoints) {
        this.maxWarningPoints = maxWarningPoints;
    }

    public int getPardonTime() {
        return pardonTime;
    }

    public void setPardonTime(int pardonTime) {
        this.pardonTime = pardonTime;
    }

    public boolean isHiddenModerator() {
        return hiddenModerator;
    }

    public void setHiddenModerator(boolean hiddenModerator) {
        this.hiddenModerator = hiddenModerator;
    }

    public boolean isConsoleWarning() {
        return consoleWarning;
    }

    public void setConsoleWarning(boolean consoleWarning) {
        this.consoleWarning = consoleWarning;
    }

    public Connection getDatabase() {
        return database;
    }

    public void setDatabase(Connection database) {
        this.database = database;
    }


    public boolean isMysql() {
        return mysql;
    }

    public void setMysql(boolean mysql) {
        this.mysql = mysql;
    }
}
