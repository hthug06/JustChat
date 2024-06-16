package fr.ht06.justchat.listeners;

import fr.ht06.justchat.Main;
import fr.ht06.justchat.commands.JustChatCommand;
import io.papermc.paper.chat.ChatRenderer;
import io.papermc.paper.event.player.AsyncChatEvent;
import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.ComponentLike;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.TextReplacementConfig;
import net.kyori.adventure.text.format.TextColor;
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
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryInteractEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;

public class PlayerListener implements Listener {

    Main main;
    JustChatCommand justChatCommand;
    BukkitRunnable bukkitRunnable;
    public Map<Player, Boolean> inventoryOpen = new HashMap<>();
    public PlayerListener(Main main) {
        this.main = main;
    }
    public PlayerListener(JustChatCommand justChatCommand) {
        this.justChatCommand = justChatCommand;
    }
    public PlayerListener(BukkitRunnable main) {
        this.bukkitRunnable = main;
    }

    @EventHandler
    public void playerPrefix(AsyncChatEvent event){
        /*  event.viewers(= = get tout les joueur (c'est une liste)
        for (Audience audience : event.viewers()){
            System.out.println(audience);
        }*/

        Player player = event.getPlayer();
        //String prefix = "["+PlaceholderAPI.setPlaceholders(player, "%justskyblock_test%")+"]";
        //prefix += PlaceholderAPI.setPlaceholders(player, "%luckperms_prefix%") + player.getName();
        //String suffix = PlaceholderAPI.setPlaceholders(player, "%luckperms_suffix%");

        StringBuilder message1 = new StringBuilder();

        List<String> list = new ArrayList<>();
        int i = 0;
        String[] msgconfig = main.getConfig().getString("Message").split(" ");

        for (String s : msgconfig){
            if (s.equals("(PLAYER)")){
                if (msgconfig.length-1 == i) message1.append(player.getName());
                else message1.append(player.getName() + " ");

                i++;

            }
            //si placeHolder
            else if (PlaceholderAPI.containsPlaceholders(s)) {

                message1.append(PlaceholderAPI.setPlaceholders(player, s));

                i++;

            } else {
                if (msgconfig.length-1 == i) message1.append(s);
                else  message1.append(s +  " ");


                player.sendMessage(String.valueOf(i));

                i++;
            }

        }



        MiniMessage miniMessage = MiniMessage.miniMessage();

        //@NotNull Component PrefixReplacer = miniMessage.deserialize(prefix);
        //@NotNull Component SuffixReplacer = miniMessage.deserialize(suffix);
        @NotNull Component MessageReplacer = miniMessage.deserialize(message1.toString());

        /*TextComponent message = Component.text("")
                .append(PrefixReplacer)
                .append(SuffixReplacer)
                .append(Component.text(": "));*/

        TextComponent message = Component.text("")
                .append(MessageReplacer)
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
        Random random = new Random();

        @Nullable Inventory inv = event.getClickedInventory();
        InventoryView inventoryView = event.getView();
        Player player = (Player) event.getWhoClicked();
        ItemStack item = event.getCurrentItem();
        MiniMessage miniMessage = MiniMessage.miniMessage();
        List<Material> woolMaterials = new ArrayList<>();

        for (Material material : Material.values()) {
            if (material.name().contains("WOOL")) {
                woolMaterials.add(material);
            }
        }

        if (item == null) return;

        Component component = Component.text("Just").color(TextColor.color(0xA05FB9))
                .append(Component.text("Chat").color(TextColor.color(0x3E95CE)))
                .append(Component.text(" example").color(TextColor.color(0x808B96)));
        if (inventoryView.title().equals(component)){

            if (woolMaterials.contains(item.getType())){
                event.setCancelled(true);
                player.sendMessage(miniMessage.deserialize("<blue>This<red> is<yellow> a<green> colored<light_purple> text,<#F2D7D5> it's<#212F3C> FUN, ")
                        .append(miniMessage.deserialize("<rainbow>and its a rainbow text, also fun"))

                );
            }
        }
    }

    @EventHandler
    public void onCloseInv(InventoryCloseEvent event){
        Player player = (Player) event.getPlayer();
        main.inventoryClosed.put(player, false);
        //player.sendMessage(player.getName() + " à fermé l'inv");
    }

}
