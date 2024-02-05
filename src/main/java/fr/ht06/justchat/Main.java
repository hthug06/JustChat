package fr.ht06.justchat;

import fr.ht06.justchat.listeners.PlayerListener;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.event.ClickEvent;
import net.kyori.adventure.text.event.HoverEvent;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import me.clip.placeholderapi.*;

public final class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        getCommand("justChat").setExecutor(this);
        getServer().getPluginManager().registerEvents(new PlayerListener(), this);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        Player player = (Player) sender;

        if (command.getName().equalsIgnoreCase("justChat")){
            if (args.length != 1){
                Component component = Component.text("[", TextColor.color(0xAED6F1))
                        .append(Component.text("Just").color(TextColor.color(0xA05FB9)))
                        .append(Component.text("Chat").color(TextColor.color(0x3E95CE)))
                        .append(Component.text("]").color(TextColor.color(0xAED6F1)))
                        .append(Component.text(" Plugin in v1.0 create by ht06"));

                player.sendMessage(component);

                if (player.hasPermission("Just.chat.color")){
                    Component component1 = Component.text("You can see how to use color in the chat ")
                            .append(Component.text("HERE").color(TextColor.color(0xCB4335))
                                    .hoverEvent(HoverEvent.showText(Component.text("Redirect to the documentation of the colors")))
                                    .clickEvent(ClickEvent.clickEvent(ClickEvent.Action.OPEN_URL,"https://docs.advntr.dev/minimessage/format.html")));
                    ;
                    player.sendMessage(component1);
                }
            }
            if (args.length == 1){
                if (args[0].equalsIgnoreCase("example")){
                    Component component = Component.text("Just").color(TextColor.color(0xA05FB9))
                            .append(Component.text("Chat").color(TextColor.color(0x3E95CE)))
                            .append(Component.text(" example").color(TextColor.color(0x808B96)));

                    player.openInventory(Bukkit.createInventory(null, 27, component));
                }
                else {
                    Component component = Component.text("[", TextColor.color(0xAED6F1))
                            .append(Component.text("Just").color(TextColor.color(0xA05FB9)))
                            .append(Component.text("Chat").color(TextColor.color(0x3E95CE)))
                            .append(Component.text("]").color(TextColor.color(0xAED6F1)))
                            .append(Component.text(" Plugin in v1.0 create by ht06"));

                    player.sendMessage(component);

                    if (player.hasPermission("Just.chat.color")){
                        Component component1 = Component.text("You can see how to use color in the chat ")
                                .append(Component.text("HERE").color(TextColor.color(0xCB4335))
                                        .hoverEvent(HoverEvent.showText(Component.text("Redirect to the documentation of the colors")))
                                        .clickEvent(ClickEvent.clickEvent(ClickEvent.Action.OPEN_URL,"https://docs.advntr.dev/minimessage/format.html")));
                        ;
                        player.sendMessage(component1);
                    }
                }
            }



        }
        return true;
    }
}
