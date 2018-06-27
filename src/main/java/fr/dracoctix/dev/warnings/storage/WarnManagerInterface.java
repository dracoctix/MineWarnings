package fr.dracoctix.dev.warnings.storage;

import fr.dracoctix.dev.warnings.exceptions.UnknownWarningException;
import fr.dracoctix.dev.warnings.warnings.Warning;
import org.bukkit.entity.Player;

import java.util.HashMap;

public interface WarnManagerInterface {
    void addWarning(Warning warning);
    void removeWarning(int id) throws UnknownWarningException;
    void editWarning(int id, Warning warning) throws UnknownWarningException;

    HashMap<Integer, Warning> listWarningsModerator(Player moderator);
    HashMap<Integer, Warning> listWarningsPlayer(Player player);
}
