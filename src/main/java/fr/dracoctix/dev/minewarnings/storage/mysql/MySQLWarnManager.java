package fr.dracoctix.dev.minewarnings.storage.mysql;

import fr.dracoctix.dev.minewarnings.exceptions.UnknownWarningException;
import fr.dracoctix.dev.minewarnings.storage.WarnManagerInterface;
import fr.dracoctix.dev.minewarnings.warnings.Warning;
import org.bukkit.entity.Player;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;

public class MySQLWarnManager implements WarnManagerInterface {
    private Connection connection;
    private String table;

    public MySQLWarnManager(Connection connection, String prefix) {
        this.connection = connection;
        this.table = prefix + "minewarnings";
    }

    @Override
    public void addWarning(Warning warning) {
        String sql = "INSERT INTO $tableName(user, moderator, start, points, days, description, justification) VALUES(?,?,?,?,?,?,?);";
        sql = sql.replace("$tableName",table);

        try {
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1,warning.getPlayer().getUniqueId().toString());
            statement.setString(2,warning.getModerator().getUniqueId().toString());
            statement.setDate(3,warning.getStart());
            statement.setInt(4,warning.getPoints());
            statement.setInt(5,warning.getDays());
            statement.setString(6,warning.getDescription());
            statement.setString(7,warning.getDescription());
        } catch (SQLException e) {
            e.printStackTrace();
        }
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
