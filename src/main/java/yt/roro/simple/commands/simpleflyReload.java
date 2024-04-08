package yt.roro.simple.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import yt.roro.simple.utils.customConfig;

public class simpleflyReload implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender.isOp())) {
            sender.sendMessage(ChatColor.GOLD + "[Simple Fly] " + ChatColor.RED + "You do not have permissions to use this command!");
            return true;
        }

        customConfig.getInstance().load();
        sender.sendMessage(ChatColor.GOLD + "[Simple Fly] " + ChatColor.WHITE + "Reloaded the configuration files");

        return true;
    }
}
