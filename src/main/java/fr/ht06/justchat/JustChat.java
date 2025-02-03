package fr.ht06.justchat;

import fr.ht06.justchat.TabCompleter.JustChatTab;
import fr.ht06.justchat.commands.JustChatCommand;
import fr.ht06.justchat.inventory.ExampleInventory;
import fr.ht06.justchat.listeners.PlayerListener;
import org.bukkit.plugin.java.JavaPlugin;

public class JustChat extends JavaPlugin {

    @Override
    public void onEnable() {
        //Commands
        getCommand("justChat").setExecutor(new JustChatCommand());

        //TabCompleter
        getCommand("justChat").setTabCompleter(new JustChatTab());

        //events
        getServer().getPluginManager().registerEvents(new PlayerListener(), this);
        getServer().getPluginManager().registerEvents(new ExampleInventory(true), this);

        saveDefaultConfig();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static JustChat getInstance() {
        return getPlugin(JustChat.class);
    }


}
