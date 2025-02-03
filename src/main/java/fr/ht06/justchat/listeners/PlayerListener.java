package fr.ht06.justchat.listeners;

import fr.ht06.justchat.JustChat;
import io.papermc.paper.event.player.AsyncChatEvent;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextReplacementConfig;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.minimessage.tag.resolver.TagResolver;
import net.kyori.adventure.text.minimessage.tag.standard.StandardTags;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import me.clip.placeholderapi.*;

public class PlayerListener implements Listener {

    MiniMessage miniMessage = MiniMessage.miniMessage();

    @EventHandler
    public void playerPrefix(AsyncChatEvent event){
        //get the player
        Player player = event.getPlayer();

        //cancel the message send | the event
        event.setCancelled(true);

        //replace PLAYER by the name of the player
        TextReplacementConfig.Builder configPlayer = TextReplacementConfig.builder().match("PLAYER").replacement(player.getName());

        //prefix
        Component prefix = JustChat.getInstance().getConfig().getRichMessage("prefix").replaceText(configPlayer.build());
        String prefixStr = miniMessage.serialize(prefix);

        //message
        Component message;

        //suffix
        Component suffix = JustChat.getInstance().getConfig().getRichMessage("suffix").replaceText(configPlayer.build());
        String suffixStr = miniMessage.serialize(suffix);


        //op can't do whatever they want
        if (player.isOp()){
            message = miniMessage.deserialize(PlainTextComponentSerializer.plainText().serialize(event.message()));
        }

        //player cannot use tag like <newline> or <click> (because you can run commands with it)
        else {
            message = getCustomMM().deserialize(PlainTextComponentSerializer.plainText().serialize(event.message()));
        }

        //get everything before the message
        String finale = JustChat.getInstance().getConfig().getString("format")
                .replace("prefix", prefixStr)
                .replace("PLAYER", player.getName())
                .replace("suffix", suffixStr);

        //replace the placeholder
        if (Bukkit.getPluginManager().isPluginEnabled("PlaceholderAPI")){
            if (PlaceholderAPI.containsPlaceholders(finale)){
                finale = PlaceholderAPI.setPlaceholders(player, finale);
            }
        }

        //put everything together
        Component finalComponent = miniMessage.deserialize(finale).append(message);

        //if the message is just <newline>, cancel
        if (message.toString().equals(Component.newline().toString())){
            player.sendMessage(Component.text("You need to enter a message, not only a tag", NamedTextColor.RED));
            return;
        }
        //if the message is just a tag
        if(PlainTextComponentSerializer.plainText().serialize(message).isEmpty()) {
            player.sendMessage(Component.text("You need to enter a message, not only a tag", NamedTextColor.RED));
            return;
        }

        //this might be more bug with tag, but for the moment I think that's all

        //send the message like ti was nothing :o
        Bukkit.broadcast(finalComponent);

    }

    //the minimessage build for the non-op players
    public MiniMessage getCustomMM(){
        return MiniMessage.builder().tags(
                TagResolver.builder()
                        .resolver(StandardTags.color())
                        .resolver(StandardTags.font())   //remove if bug in inventory with the name
                        .resolver(StandardTags.decorations())
                        .resolver(StandardTags.gradient())
                        .resolver(StandardTags.hoverEvent())
                        .resolver(StandardTags.pride())
                        .resolver(StandardTags.rainbow())
                        .resolver(StandardTags.reset())
                        .resolver(StandardTags.shadowColor())
                        .resolver(StandardTags.translatable())
                        .resolver(StandardTags.translatableFallback())
                        .build()
        ).build();
    }
}
