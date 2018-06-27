package fr.dracoctix.dev.minewarnings.commands;

import fr.dracoctix.dev.minewarnings.MineWarnings;
import fr.dracoctix.dev.minewarnings.warnings.Cause;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class WarnListCommandListener implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        String message = "The predefined causes are :\n";
        for(String id : MineWarnings.getPlugin().getConf().getCauses().keySet()) {
            Cause c = MineWarnings.getPlugin().getConf().getCause(id);
            message += "Id : \"" + id + "\" Points : " + c.getPoints() + " Expiration time (in days) : " + c.getExpirationTime() + "\nDescription :" + c.getDescription() + "\n";
        }
        sender.sendMessage(message);

        return true;
    }
}
