package fr.ht06.justchat.listeners;

import io.papermc.paper.chat.ChatRenderer;
import io.papermc.paper.event.player.AsyncChatEvent;
import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.ComponentLike;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.TextReplacementConfig;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.minimessage.tag.resolver.Formatter;
import net.kyori.adventure.text.minimessage.tag.resolver.Placeholder;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import me.clip.placeholderapi.*;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryInteractEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class PlayerListener implements Listener {


    @EventHandler
    public void playerPrefix(AsyncChatEvent event){
        /*  event.viewers(= = get tout les joueur (c'est une liste)
        for (Audience audience : event.viewers()){
            System.out.println(audience);
        }*/

        Player player = event.getPlayer();
        String prefix = PlaceholderAPI.setPlaceholders(player, "%luckperms_prefix%") + player.getName();
        String suffix = PlaceholderAPI.setPlaceholders(player, "%luckperms_suffix%");

        MiniMessage miniMessage = MiniMessage.miniMessage();

        @NotNull Component PrefixReplacer = miniMessage.deserialize(prefix);
        @NotNull Component SuffixReplacer = miniMessage.deserialize(suffix);

        TextComponent message = Component.text("")
                .append(PrefixReplacer)
                .append(SuffixReplacer)
                .append(Component.text(": "));

        event.setCancelled(true);

        if (player.hasPermission("Just.chat.color")){
            String text =  PlainTextComponentSerializer.plainText().serialize(event.message());
            Component finale=  miniMessage.deserialize(text);
            Bukkit.broadcast(message.append(finale));
        }
        else {
            Bukkit.broadcast(message.append(event.message()));
        }
    }


    @EventHandler
    public void onInteractInventory(InventoryClickEvent event){
        Inventory inv = event.getInventory();
        Player player = (Player) event.getWhoClicked();
        ItemStack item = event.getCurrentItem();

        if (item == null) return;

        if (item.getType().name().equals("COLORS")){
            inv.close();
            player.sendMessage(MiniMessage.miniMessage().deserialize("<blue>This is blue</blue><yellow>And i'm yellow</yellow><#EB984E>WOW NOICE COLOR"));
        }
    }

}
