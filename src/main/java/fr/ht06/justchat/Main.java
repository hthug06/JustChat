package fr.ht06.justchat;

import fr.ht06.justchat.listeners.PlayerListener;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import me.clip.placeholderapi.*;

public final class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        getCommand("test").setExecutor(this);
        getServer().getPluginManager().registerEvents(new PlayerListener(), this);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        Player player = (Player) sender;

        if (command.getName().equalsIgnoreCase("test")){
            Component test =  Component.text(PlaceholderAPI.setPlaceholders(player, "%player_name%"))  ;
            Bukkit.broadcast(test);


        }
        return true;
    }
}
