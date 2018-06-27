package fr.dracoctix.dev.minewarnings.storage;

import fr.dracoctix.dev.minewarnings.exceptions.UnknownWarningException;
import fr.dracoctix.dev.minewarnings.warnings.Warning;
import org.bukkit.entity.Player;

import java.util.HashMap;

public interface WarnManagerInterface {
    boolean addWarning(Warning warning);
    void removeWarning(int id) throws UnknownWarningException;
    void editWarning(int id, Warning warning) throws UnknownWarningException;

    HashMap<Integer, Warning> listWarningsModerator(Player moderator);
    HashMap<Integer, Warning> listWarningsPlayer(Player player);
}
