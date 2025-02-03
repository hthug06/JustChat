package fr.ht06.justchat.commands;

import fr.ht06.justchat.JustChat;
import fr.ht06.justchat.inventory.ExampleInventory;
import net.kyori.adventure.text.Component;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class JustChatCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("§cYou must be a player to use this command!");
            return true;
        }

        Player player = (Player) sender;

        //no args
        if (args.length == 0) {
            if (player.hasPermission("justchat.reload")) {
                player.sendMessage(Component.text("§c/justchat <example | reload>"));
            }
            else{
                player.sendMessage(Component.text("§c/justchat example"));
            }

            return true;
        }

        //args
        else {
            if (args[0].equalsIgnoreCase("example")) {
                player.openInventory(new ExampleInventory(false).getInventory());
            }
            else if (args[0].equalsIgnoreCase("reload") && player.hasPermission("justchat.reload")) {
                JustChat.getInstance().reloadConfig();
                player.sendMessage("Config reloaded");
            }
            else {
                if (player.hasPermission("justchat.reload")) {
                    player.sendMessage(Component.text("§c/justchat <example | reload>"));
                }
                else{
                    player.sendMessage(Component.text("§c/justchat example"));
                }
            }
        }
        return true;
    }

}
