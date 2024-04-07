package yt.roro.simple.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import yt.roro.simple.utils.customConfig;

public class fly implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        boolean commandEnabled = customConfig.getInstance().get().getBoolean("commandEnabled");
        boolean targetOthers = customConfig.getInstance().get().getBoolean("targetOthers");
        boolean opRestricted = customConfig.getInstance().get().getBoolean("opRestricted");
        Bukkit.getLogger().info(String.valueOf(commandEnabled));

        if(!commandEnabled) {
            sender.sendMessage(ChatColor.GOLD + "[Simple Fly] " + ChatColor.RED + "This command is disabled!");
            return true;
        }
        if(!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.GOLD + "[Simple Fly] " + ChatColor.RED + "Only players can use this command!");
            return true;
        }

        Player player = (Player) sender;

        if(opRestricted) {
            if(!(sender.isOp())) {
                sender.sendMessage(ChatColor.GOLD + "[Simple Fly] " + ChatColor.RED + "You do not have permission to use this command!");
                return true;
            }
        }


        if(args.length == 0) {
            if(player.getAllowFlight()) {
                sender.sendMessage(ChatColor.GOLD + "[Simple Fly] " + ChatColor.WHITE + "Flight Disabled");
                Bukkit.getLogger().info(ChatColor.GOLD + "[Simple Fly] " + ChatColor.WHITE + player.getName() + " Disabled Flight for themself!");
                player.setAllowFlight(false);
                return true;
            } else {
                sender.sendMessage(ChatColor.GOLD + "[Simple Fly] " + ChatColor.WHITE + "Flight Enabled");
                Bukkit.getLogger().info(ChatColor.GOLD + "[Simple Fly] " + ChatColor.WHITE + player.getName() + " Enabled Flight for themself!");
                player.setAllowFlight(true);
                return true;
            }
        } else {
            if(targetOthers) {
                String targetName = args[0];
                Player target = Bukkit.getServer().getPlayerExact(targetName);

                if (target == null) {
                    sender.sendMessage(ChatColor.GOLD + "[Simple Fly] " + ChatColor.RED + "Player Offline");
                    return true;
                }

                if (target.getAllowFlight()) {
                    sender.sendMessage(ChatColor.GOLD + "[Simple Fly] " + ChatColor.WHITE + "Flight Disabled for " + target.getName());
                    target.sendMessage(ChatColor.GOLD + "[Simple Fly] " + ChatColor.WHITE + "Flight Disabled by " + player.getName());
                    target.setAllowFlight(false);
                    //Log to console
                    Bukkit.getLogger().info(ChatColor.GOLD + "[Simple Fly] " + ChatColor.WHITE + "Flight Disabled by " + player.getName() + " for " + target.getName());
                    return true;
                } else {
                    sender.sendMessage(ChatColor.GOLD + "[Simple Fly] " + ChatColor.WHITE + "Flight Enabled for " + target.getName());
                    target.sendMessage(ChatColor.GOLD + "[Simple Fly] " + ChatColor.WHITE + "Flight Enabled by " + player.getName());
                    target.setAllowFlight(true);
                    //Log to console
                    Bukkit.getLogger().info(ChatColor.GOLD + "[Simple Fly] " + ChatColor.WHITE + "Flight Enabled by " + player.getName() + " for " + target.getName());
                    return true;
                }
            } else {
                sender.sendMessage(ChatColor.GOLD + "[Simple Fly] " + ChatColor.RED + "You may not target other players!");
                return true;
            }
        }
    }
}
