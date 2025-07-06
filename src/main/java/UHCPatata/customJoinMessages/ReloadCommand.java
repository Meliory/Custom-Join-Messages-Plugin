package UHCPatata.customJoinMessages;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class ReloadCommand implements CommandExecutor {

    private final CustomJoinMessages plugin;


    public ReloadCommand(CustomJoinMessages plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args){
        plugin.reloadConfig();
        plugin.loadMessages();
        sender.sendMessage(ChatColor.AQUA + "CustomJoinMessages has been reloaded!");
        return true;
    }
}
