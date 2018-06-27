package fr.dracoctix.dev.minewarnings.commands;

import fr.dracoctix.dev.minewarnings.MineWarnings;
import fr.dracoctix.dev.minewarnings.storage.WarnManagerInterface;
import fr.dracoctix.dev.minewarnings.warnings.Cause;
import fr.dracoctix.dev.minewarnings.warnings.Warning;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class DefaultWarnCommandListener extends AbstractUsageCommandListener {
    private WarnManagerInterface warnManager;

    public DefaultWarnCommandListener(WarnManagerInterface warnManager) {
        this.warnManager = warnManager;
    }

    @Override
    public boolean commandExecution(CommandSender sender, boolean senderIsPlayer, Command command, String label, String[] args) {
        Cause cause = MineWarnings.getPlugin().getConf().getCause(args[1]);
        Player player = MineWarnings.getPlugin().getServer().getPlayer(args[0]);

        if(player == null) {
            sender.sendMessage(ChatColor.RED + "Player \"" + args[0] + "\" not found.");
            return false;
        }

        if(cause == null) {
            sender.sendMessage(ChatColor.RED + "Cause \"" + args[1] + "\" not found.");
            return false;
        }

        Player moderator = (sender instanceof Player) ? (Player)sender : null;

        String justification = "";

        for(int i = 2; i < args.length; ++i) {
            justification += args[i] + " ";
        }

        if(justification.equals("")) {
            justification = null;
        }

        Warning avertissement = new Warning(player,moderator,cause.getExpirationTime(),cause.getPoints(),cause.getDescription(), justification);
        boolean reussite = warnManager.addWarning(avertissement);

        if(!reussite) {
            sender.sendMessage(ChatColor.RED + "The warning cannot be registered in storage system. Please retry, and contact system administrator if the problem persists.");
            return false;
        }

        return true;
    }
}
