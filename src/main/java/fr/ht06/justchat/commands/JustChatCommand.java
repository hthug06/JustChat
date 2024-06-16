package fr.ht06.justchat.commands;

import fr.ht06.justchat.Main;
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
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;
import org.checkerframework.checker.units.qual.C;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class JustChatCommand implements CommandExecutor {
    Main main;

    public JustChatCommand(Main main) {
        this.main = main;
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

                if (args[0].equalsIgnoreCase("reload")){
                    player.sendMessage("§cconfig reload");
                    main.reloadConfig();
                }

                if (args[0].equalsIgnoreCase("example")){
                    Random random = new Random();
                    MiniMessage miniMessage = MiniMessage.miniMessage();

                    Component component = Component.text("Just").color(TextColor.color(0xA05FB9))
                            .append(Component.text("Chat").color(TextColor.color(0x3E95CE)))
                            .append(Component.text(" example").color(TextColor.color(0x808B96)));

                    //CREATION DE L'INVENTAIRE
                    Inventory inv = Bukkit.createInventory(null, 27, component);

                    //COLOR
                    List<Material> woolMaterials = new ArrayList<>();
                    for (Material material : Material.values()) {
                        if (material.name().contains("WOOL")) {
                            woolMaterials.add(material);
                        }
                    }
                    ItemStack ItemColor= new ItemStack(woolMaterials.get(random.nextInt(woolMaterials.size())), 1);
                    ItemMeta ItemColorM = ItemColor.getItemMeta();

                    List<Component> lore = new ArrayList<Component>();
                    ItemColorM.displayName(Component.text("COLORS").color(TextColor.color(0x2E86C1)).decorate(TextDecoration.BOLD, TextDecoration.UNDERLINED).decoration(TextDecoration.ITALIC, false));
                    lore.add(Component.text("How to talk in COLOR  ?")
                            .decorate(TextDecoration.BOLD)
                            .decorate(TextDecoration.UNDERLINED).decoration(TextDecoration.ITALIC, false)
                            .color(TextColor.color(0xCB4335)));
                    lore.add(Component.text("<blue>This text is blue</blue>").color(TextColor.color(0xFFFFFF)).decoration(TextDecoration.ITALIC, false));
                    lore.add(Component.text("<dark_red>and this one dark red</dark_red>").color(TextColor.color(0xFFFFFF)).decoration(TextDecoration.ITALIC, false));
                    lore.add(miniMessage.deserialize("<blue>This text is blue</blue><dark_red> and this one dark red</dark_red>").decoration(TextDecoration.ITALIC, false));
                    lore.add(Component.text("To color text, use <nameofthecolor> your text </nameofthecolor>").color(TextColor.color(0xFFFFFF)).decoration(TextDecoration.ITALIC, TextDecoration.State.FALSE));
                    lore.add(Component.text(""));

                    lore.add(Component.text("<#5DADE2>WOWOWOW NOICE COLOR,<reset>").color(TextColor.color(0xFFFFFF)).decoration(TextDecoration.ITALIC, false));
                    lore.add(Component.text("<#1D8348>This one too <reset> and this is reset now").color(TextColor.color(0xFFFFFF)).decoration(TextDecoration.ITALIC, false));
                    lore.add(miniMessage.deserialize("<#5DADE2>WOWOWOW NOICE COLOR<white><#1D8348> This one too <white>and this is reset now").decoration(TextDecoration.ITALIC, false));
                    lore.add(Component.text(""));
                    lore.add(miniMessage.deserialize("<rainbow>You can also write in RAINBOW (wow) in the chat,").decoration(TextDecoration.ITALIC, false).append(Component.text(" <rainbow>text").color(TextColor.color(0xFFFFFF)).decoration(TextDecoration.ITALIC, false)));
                    lore.add(Component.text(""));
                    lore.add(Component.text("Click on the block to see an example in the chat").decorate(TextDecoration.ITALIC).color(TextColor.color(178, 186, 187)));
                    ItemColorM.lore(lore);
                    ItemColor.setItemMeta(ItemColorM);
                    inv.setItem(10, ItemColor);        //Ajout de l'item

                    //Cusomise the text
                    ItemStack ItemCustomise = new ItemStack(Material.WRITABLE_BOOK, 1);
                    ItemMeta ItemCustomiseM = ItemCustomise.getItemMeta();
                    ItemCustomiseM.displayName(Component.text("hi ").color(TextColor.color(0x2E86C1)).decorate(TextDecoration.BOLD, TextDecoration.OBFUSCATED).decoration(TextDecoration.ITALIC, false)
                            .append(Component.text("Customisation").decorate(TextDecoration.UNDERLINED, TextDecoration.BOLD).decoration(TextDecoration.OBFUSCATED, false))
                            .append(Component.text(" hi").color(TextColor.color(0x2E86C1)).decorate(TextDecoration.BOLD, TextDecoration.OBFUSCATED).decoration(TextDecoration.ITALIC, false).decoration(TextDecoration.UNDERLINED, false)));
                    ItemCustomiseM.addItemFlags(ItemFlag.HIDE_ITEM_SPECIFICS);  //Pour enlever le "creeperBannerPatern"

                    List<Component> loreCustomise = new ArrayList<Component>();
                    loreCustomise.add(Component.text("How to customise your text?").color(TextColor.color(0x7DBA9E)));

                    ItemCustomiseM.lore(loreCustomise);
                    ItemCustomise.setItemMeta(ItemCustomiseM);
                    inv.setItem(12, ItemCustomise);

                    player.openInventory(inv);

                    /*new BukkitRunnable() {
                        @Override
                        public void run() {
                            ItemStack ItemColor = new ItemStack(woolMaterials.get(random.nextInt(woolMaterials.size())), 1);
                            ItemMeta ItemColorM = ItemColor.getItemMeta();
                            ItemColorM.displayName(Component.text("COLORS").color(TextColor.color(0x2E86C1)).decorate(TextDecoration.BOLD, TextDecoration.UNDERLINED).decoration(TextDecoration.ITALIC, false));
                            ItemColorM.lore(lore);
                            ItemColor.setItemMeta(ItemColorM);

                            inv.setItem(10, ItemColor);
                            player.updateInventory();

                            //Permet de ne pas faire bug tout
                            if (main.inventoryClosed.get(player).equals(false)){
                                cancel();
                            }
                            else {
                                main.inventoryClosed.put(player, true);
                            }}
                    }.runTaskTimer(main, 20, 20);*/

                }
                else {
                    Component component = Component.text("[", TextColor.color(0xAED6F1))
                            .append(Component.text("Just").color(TextColor.color(0xA05FB9)))
                            .append(Component.text("Chat").color(TextColor.color(0x3E95CE)))
                            .append(Component.text("]").color(TextColor.color(0xAED6F1)))
                            .append(Component.text(" Plugin in v1.1 create by ht06"));

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
