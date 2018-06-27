package fr.dracoctix.dev.minewarnings.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public class DefaultWarnCommandListener extends AbstractUsageCommandListener {
    @Override
    public boolean commandExecution(CommandSender sender, boolean senderIsPlayer, Command command, String label, String[] args) {
        return false;
    }
}
