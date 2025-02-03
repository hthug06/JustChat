package fr.ht06.justchat.listeners;

import fr.ht06.justchat.JustChat;
import io.papermc.paper.event.player.AsyncChatEvent;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import me.clip.placeholderapi.*;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;

public class PlayerListener implements Listener {

    JustChat main;
    public PlayerListener(JustChat main) {
        this.main = main;
    }

    @EventHandler
    public void playerPrefix(AsyncChatEvent event){

        Player player = event.getPlayer();
        //String prefix = "["+PlaceholderAPI.setPlaceholders(player, "%justskyblock_test%")+"]";
        //prefix += PlaceholderAPI.setPlaceholders(player, "%luckperms_prefix%") + player.getName();
        //String suffix = PlaceholderAPI.setPlaceholders(player, "%luckperms_suffix%");

        StringBuilder message1 = new StringBuilder();

        List<String> list = new ArrayList<>();
        int i = 0;
        String[] msgconfig = JustChat.getInstance().getConfig().getString("Message").split(" ");

        for (String s : msgconfig){
            if (s.equals("(PLAYER)")){
                if (msgconfig.length-1 == i) message1.append(player.getName());
                else message1.append(player.getName() + " ");

                i++;

            }
            //if placeHolder
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

}
