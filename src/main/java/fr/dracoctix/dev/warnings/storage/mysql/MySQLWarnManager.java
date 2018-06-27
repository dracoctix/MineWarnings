package fr.dracoctix.dev.warnings.storage.mysql;

import fr.dracoctix.dev.warnings.exceptions.UnknownWarningException;
import fr.dracoctix.dev.warnings.storage.WarnManagerInterface;
import fr.dracoctix.dev.warnings.warnings.Warning;
import org.bukkit.entity.Player;

import java.sql.Connection;
import java.util.HashMap;

public class MySQLWarnManager implements WarnManagerInterface {
    private Connection connection;
    private String prefix;

    public MySQLWarnManager(Connection connection, String prefix) {
        this.connection = connection;
        this.prefix = prefix;
    }

    @Override
    public void addWarning(Warning warning) {

    }

    @Override
    public void removeWarning(int id) throws UnknownWarningException {

    }

    @Override
    public void editWarning(int id, Warning warning) throws UnknownWarningException {

    }

    @Override
    public HashMap<Integer, Warning> listWarningsModerator(Player moderator) {
        return null;
    }

    @Override
    public HashMap<Integer, Warning> listWarningsPlayer(Player player) {
        return null;
    }
}
