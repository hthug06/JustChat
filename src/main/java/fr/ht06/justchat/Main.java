package fr.ht06.justchat;

import fr.ht06.justchat.listeners.PlayerListener;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.event.ClickEvent;
import net.kyori.adventure.text.event.HoverEvent;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import me.clip.placeholderapi.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

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
                    MiniMessage miniMessage = MiniMessage.miniMessage();

                    Component component = Component.text("Just").color(TextColor.color(0xA05FB9))
                            .append(Component.text("Chat").color(TextColor.color(0x3E95CE)))
                            .append(Component.text(" example").color(TextColor.color(0x808B96)));

                    Inventory inv = Bukkit.createInventory(null, 27, component);
                    ItemStack ItemColor = new ItemStack(Material.WHITE_WOOL, 1);
                    ItemMeta ItemColorM = ItemColor.getItemMeta();

                    ItemColorM.displayName(Component.text("COLORS").color(TextColor.color(0x2E86C1)).decorate(TextDecoration.BOLD));
                    List<Component> lore = new ArrayList<Component>();
                    lore.add(Component.text("How to talk in color?")
                            .decorate(TextDecoration.BOLD)
                            .decorate(TextDecoration.UNDERLINED)
                            .color(TextColor.color(0xCB4335)));
                    lore.add(Component.text("<blue>This text is blue</blue> and this one white <dark_red>and this one dark red</dark_red>").color(TextColor.color(0xFFFFFF)));
                    lore.add(miniMessage.deserialize("<blue>This text is blue</blue> and this one white <dark_red>and this one dark red</dark_red>"));
                    lore.add(Component.text("To color text, you must make '<nameofthecolor> your text </nameofthecolor>', but that not all :D").color(TextColor.color(0xFFFFFF)).decoration(TextDecoration.ITALIC, TextDecoration.State.FALSE));
                    lore.add(Component.text(""));
                    lore.add(Component.text("<#5DADE2>WOWOWOW NOICE COLOR<reset> and also <#1D8348>This one too <reset> and this is reset now").color(TextColor.color(0xFFFFFF)));
                    lore.add(miniMessage.deserialize("<#5DADE2>WOWOWOW NOICE COLOR<white> and also <#1D8348>This one too <white> and this is reset now"));
                    lore.add(Component.text(""));
                    lore.add(Component.text("Click on the block to see an example in the chat").decorate(TextDecoration.ITALIC).color(TextColor.color(178, 186, 187)));

                    ItemColorM.lore(lore);
                    ItemColor.setItemMeta(ItemColorM);

                    inv.setItem(11, ItemColor);

                    player.openInventory(inv);
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
