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
            //can reload
            if (player.hasPermission("justchat.reload") || player.isOp()) {
                player.sendMessage(Component.text("§c/justchat <example | reload>"));
            }

            //cannot reload
            else{
                player.sendMessage(Component.text("§c/justchat example"));
            }

            return true;
        }

        //args
        else {
            //example
            if (args[0].equalsIgnoreCase("example")) {
                player.openInventory(new ExampleInventory(false).getInventory());
            }

            //reload
            else if (args[0].equalsIgnoreCase("reload")) {
                if (player.hasPermission("justchat.reload") || player.isOp()) {
                    JustChat.getInstance().reloadConfig();
                    player.sendMessage("§cConfig reloaded");
                }
                else {
                    player.sendMessage("§You don't have the permission to use this command");
                }

            }
            else if (args[0].equalsIgnoreCase("colorchart")) {
                sendColorChart(player);
            }

            //not a good command
            else {
                //if can reload
                if (player.hasPermission("justchat.reload") || player.isOp()) {
                    player.sendMessage(Component.text("§c/justchat <colorchart | example | reload>"));
                }
                //cannot reload
                else{
                    player.sendMessage(Component.text("§c/justchat <colorchart | example>"));
                }
            }
        }
        return true;
    }

    private void sendColorChart(Player player){
        player.sendMessage("§c===============================");
        player.sendMessage("&0 = §0Black§r             &1 = §1Dark Blue");
        player.sendMessage("&2 = §2Dark Green§r     &3 = §3Dark Aqua");
        player.sendMessage("&4 = §4Dark Red§r        &5 = §5Purple");
        player.sendMessage("&6 = §6Orange§r          &7 = §7Gray");
        player.sendMessage("&8 = §8Dark gray§r      &9 = §9Blue");
        player.sendMessage("&a = §aGreen§r           &b = §bAqua");
        player.sendMessage("&c = §cRed§r              &d = §dPink");
        player.sendMessage("&e = §eYellow§r           &f = §fWhite");
        player.sendMessage("§c===============================");
        player.sendMessage("&k = §kvfcq§r             &l = §lBold");
        player.sendMessage("&m = §mStrikethrough§r &n = §nUnderline");
        player.sendMessage("&o = §oItalic§r            &r = §rReset");
    }

}
