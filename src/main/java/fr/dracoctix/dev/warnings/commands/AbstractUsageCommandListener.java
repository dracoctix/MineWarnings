package fr.dracoctix.dev.warnings.commands;

import fr.dracoctix.dev.warnings.Warnings;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public abstract class AbstractUsageCommandListener implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player) {
            return commandExecution(sender, true, command, label, args);
        }
        else {
            if(Warnings.getPlugin().getConf().isConsoleWarning()) {
                return commandExecution(sender,false,command,label,args);
            } else {
                sender.sendMessage("You cannot use this command, because the configuration doesn't allow console warnings.");
                return false;
            }
        }
    }

    public abstract boolean commandExecution(CommandSender sender, boolean senderIsPlayer, Command command, String label, String[] args);
}
