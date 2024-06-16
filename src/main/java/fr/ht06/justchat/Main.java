package fr.ht06.justchat;

import fr.ht06.justchat.TabCompleter.JustChatTab;
import fr.ht06.justchat.commands.JustChatCommand;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main extends JavaPlugin {

    public Map<Player, Boolean> inventoryClosed = new HashMap<>();

    @Override
    public void onEnable() {
        getCommand("justChat").setExecutor(new JustChatCommand(this));
        getCommand("justChat").setTabCompleter(new JustChatTab());
        getServer().getPluginManager().registerEvents(new PlayerListener(this), this);

        saveDefaultConfig();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }


}
