package fr.ht06.justchat.inventory;

import fr.ht06.justchat.CreateItem;
import fr.ht06.justchat.JustChat;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.event.ClickEvent;
import net.kyori.adventure.text.event.HoverEvent;
import net.kyori.adventure.text.format.*;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class ExampleInventory implements InventoryHolder, Listener {

    Inventory inventory;
    Random rand = new Random();
    MiniMessage miniMessage = MiniMessage.miniMessage();

    public ExampleInventory(boolean register) {
        inventory = Bukkit.createInventory(this, 27, Component.text("Example Inventory"));

        if (!register) {
            init();
        }
    }

    private void init() {
        //for the colors
        blockColor();

        //for the &
        List<Component> components = new ArrayList<>();
        components.add(Component.text("You can also use the & char in the chat", NamedTextColor.WHITE).decoration(TextDecoration.ITALIC, false));
        components.add(Component.text("Example: &cHello &6everyone", NamedTextColor.WHITE).decoration(TextDecoration.ITALIC, false));
        components.add(Component.text("= <red>Hello <gold>everyone", NamedTextColor.WHITE).decoration(TextDecoration.ITALIC, false));
        components.add(Component.text("= &cHello <gold>everyone", NamedTextColor.WHITE).decoration(TextDecoration.ITALIC, false));
        components.add(Component.text("= <red>Hello &6everyone", NamedTextColor.WHITE).decoration(TextDecoration.ITALIC, false));
        components.add(miniMessage.deserialize("<white>= <red>Hello <gold>everyone").decoration(TextDecoration.ITALIC, false));
        ItemStack ancienColor = CreateItem.createItem(Component.text("& Ancien Color system &").decoration(TextDecoration.ITALIC, false), 1, Material.COBWEB, components);
        inventory.setItem(20, ancienColor);

        //decoration
        blockDecoration();

        //Another cool thing
        itemCool();

        //for the api website
        List<Component> loreWeb = new ArrayList<>();
        loreWeb.add(Component.text("If you are a player, keep in mind some tags are disabled ", NamedTextColor.GRAY).decoration(TextDecoration.ITALIC, false));
        loreWeb.add(Component.text("For safety reason (Example: execute commands)", NamedTextColor.GRAY).decoration(TextDecoration.ITALIC, false));
        loreWeb.add(Component.text(""));
        loreWeb.add(Component.text("Click here to got to the web editor", NamedTextColor.GRAY).decoration(TextDecoration.ITALIC, false));
        loreWeb.add(Component.text("(this can be useful if you don't understand)", NamedTextColor.GRAY).decoration(TextDecoration.ITALIC, false));
        ItemStack webItem = CreateItem.createItem(Component.text("More information").decoration(TextDecoration.ITALIC, false), 1, Material.REDSTONE_TORCH, loreWeb);
        inventory.setItem(0, webItem);
    }



    @Override
    public org.bukkit.inventory.@NotNull Inventory getInventory() {
        return inventory;
    }

    @EventHandler
    public void onClick(InventoryClickEvent event) {

        if (event.getClickedInventory() == null) return;

        if (event.getClickedInventory().getHolder() instanceof ExampleInventory) {
            event.setCancelled(true);

            //for the website
            if (event.getSlot() == 0) {
                event.getWhoClicked().closeInventory();
                event.getWhoClicked().sendMessage(miniMessage.deserialize("<gradient:#"+rand.nextInt(0x0, 0xFFFFFF)+":#"+rand.nextInt(0x0, 0xFFFFFF)+">Click here to go to the web editor (random gradient color :o)")
                        .asComponent()
                        .clickEvent(ClickEvent.openUrl("https://webui.advntr.dev/")));
            }

            else if (event.getSlot() == 15) {
                List<String> flagsNames = new ArrayList<>(List.of("pride", "progress", "trans","bi", "pan",
                        "nb", "lesbian", "ace", "agender", "demisexual", "genderqueer", "genderfluid", "intersex", "aro",
                        "baker", "philly", "queer", "gay", "bigender", "demigender"));

                TextComponent.Builder flagMsg = Component.text("Here are all the flags:")
                        .decorate(TextDecoration.BOLD)
                        .hoverEvent(HoverEvent.showText(Component.text("Hover all the flag name :)", TextColor.color(0x681C4))))
                        .toBuilder();
                flagMsg.append(miniMessage.deserialize("<!b> "));

                for (String flag : flagsNames) {
                    flagMsg.append(miniMessage.deserialize("<!b>"+flag)
                            .hoverEvent(HoverEvent.showText(miniMessage.deserialize("<pride:"+flag+">These are the colors for the "+flag+" flag"))));

                    flagMsg.append(miniMessage.deserialize("<!b> ").hoverEvent(HoverEvent.showText(Component.text(""))));
                }

                flagMsg.append(miniMessage.deserialize("<!b><i><gray>(hover the flag name) "));
                event.getWhoClicked().closeInventory();
                event.getWhoClicked().sendMessage(flagMsg.asComponent());
            }
        }
    }


    private void blockColor(){
        //color
        int randomColor = rand.nextInt(0x0, 0xFFFFFF);
        List<Material> randomWool = Arrays.stream(Material.values())
                .filter(material -> material.name().contains("WOOL")).toList();

        List<Component> lore = new ArrayList<>();
        lore.add(Component.text("How to talk in COLOR ?")
                .decorate(TextDecoration.BOLD)
                .decorate(TextDecoration.UNDERLINED).decoration(TextDecoration.ITALIC, false)
                .color(TextColor.color(0xCB4335)));
        lore.add(Component.text("<blue>This text is blue</blue>").color(TextColor.color(0xFFFFFF)).decoration(TextDecoration.ITALIC, false));
        lore.add(Component.text("<dark_red>and this one dark red</dark_red>").color(TextColor.color(0xFFFFFF)).decoration(TextDecoration.ITALIC, false));
        lore.add(miniMessage.deserialize("<blue>This text is blue</blue><dark_red> and this one dark red</dark_red>").decoration(TextDecoration.ITALIC, false));
        lore.add(Component.text("To color text, use <nameofthecolor> your text </nameofthecolor>").color(TextColor.color(0xFFFFFF)).decoration(TextDecoration.ITALIC, TextDecoration.State.FALSE));
        lore.add(Component.text(""));

        lore.add(Component.text("<#5DADE2>WOWOWOW NICE COLOR,<reset>").color(TextColor.color(0xFFFFFF)).decoration(TextDecoration.ITALIC, false));
        lore.add(Component.text("<#1D8348>This one too <reset> and this is reset now").color(TextColor.color(0xFFFFFF)).decoration(TextDecoration.ITALIC, false));
        lore.add(miniMessage.deserialize("<#5DADE2>WOWOWOW NICE COLOR<white><#1D8348> This one too <white>and this is reset now").decoration(TextDecoration.ITALIC, false));
        lore.add(Component.text(""));
        lore.add(miniMessage.deserialize("<rainbow>You can also write in RAINBOW (wow) in the chat,").decoration(TextDecoration.ITALIC, false).append(Component.text(" <rainbow>text").color(TextColor.color(0xFFFFFF)).decoration(TextDecoration.ITALIC, false)));
        lore.add(Component.text(""));
        lore.add(miniMessage.deserialize("<gradient:blue:#FF374A>Last but not least, use gradient!").decoration(TextDecoration.ITALIC, false));
        lore.add(Component.text("<gradient:blue:#FF374A>Last but not least, use gradient!", NamedTextColor.WHITE).decoration(TextDecoration.ITALIC, false));
        lore.add(Component.text("", NamedTextColor.WHITE).decoration(TextDecoration.ITALIC, false));
        lore.add(Component.text("That like html if you know", TextColor.color(0x5D5D5A)));




        ItemStack colorWool = CreateItem.createItem(Component.text("Colors", TextColor.color(randomColor)).decoration(TextDecoration.ITALIC, false),
                1,
                randomWool.get(rand.nextInt(0, randomWool.size()-1)),
                lore);
        inventory.setItem(11, colorWool);

        new BukkitRunnable() {
            List<Material> randomWool = Arrays.stream(Material.values())
                    .filter(material -> material.name().contains("WOOL")).toList();
            @Override
            public void run() {
                if (inventory.getViewers().isEmpty()) {
                    cancel();
                }
                else {
                    ItemStack itemwool = inventory.getItem(11);
                    Component name = Component.text("Colors", TextColor.color(rand.nextInt(0x0, 0xFFFFFF))).decoration(TextDecoration.ITALIC, false);
                    ItemStack finalItem = CreateItem.createItem(name,
                            1 ,
                            randomWool.get(rand.nextInt(0, randomWool.size())),
                            itemwool.lore());
                    inventory.setItem(11, finalItem);
                }
            }
        }.runTaskTimer(JustChat.getInstance(), 0, 20L);
    }

    private void blockDecoration(){
        List<Material> blocks = new ArrayList<>(List.of(Material.COMMAND_BLOCK, Material.CHAIN_COMMAND_BLOCK, Material.REPEATING_COMMAND_BLOCK));

        List<Component> lore = new ArrayList<>();
        lore.add(miniMessage.deserialize("<gray><b>Bold: <reset>").append(Component.text("<b>Bold</b>").decoration(TextDecoration.BOLD, false)));
        lore.add(miniMessage.deserialize("<gray><i>Italic: <reset>").append(Component.text("<i>Italic</i>").decoration(TextDecoration.ITALIC, false)));
        lore.add(miniMessage.deserialize("<gray><u>Underlined:<reset> ").append(Component.text("<u>Underlined</u>", NamedTextColor.GRAY).decoration(TextDecoration.UNDERLINED, false)));
        lore.add(miniMessage.deserialize("<gray><st>Strikethrough:<reset> ").append(Component.text("<st>Strikethrough</st>", NamedTextColor.GRAY).decoration(TextDecoration.STRIKETHROUGH, false)));
        lore.add(miniMessage.deserialize("<gray><obf>obfuscated: <reset>").append(Component.text("<obf>Obfuscated</obf>").decoration(TextDecoration.OBFUSCATED, false)));
        lore.add(Component.text(""));
        lore.add(Component.text("You can also reset your the tag using <reset>", NamedTextColor.GRAY).decoration(TextDecoration.ITALIC, false));

        ItemStack fontItem = CreateItem.createItem(Component.text("Decorations", NamedTextColor.WHITE).decoration(TextDecoration.values()[rand.nextInt(0, TextDecoration.values().length-1)], true).decoration(TextDecoration.ITALIC, false),
                1,
                blocks.get(rand.nextInt(blocks.size())), lore);
        inventory.setItem(13, fontItem);

        new BukkitRunnable() {
            List<Material> blocks = new ArrayList<>(List.of(Material.COMMAND_BLOCK, Material.CHAIN_COMMAND_BLOCK, Material.REPEATING_COMMAND_BLOCK));
            @Override
            public void run() {
                if (inventory.getViewers().isEmpty()) {
                    cancel();
                }
                else {
                    ItemStack itemFont = inventory.getItem(13);
                    Component name = Component.text("Decorations", NamedTextColor.WHITE).decorate(TextDecoration.values()[rand.nextInt(0, TextDecoration.values().length)]).decoration(TextDecoration.ITALIC, false);
                    ItemStack finalItem = CreateItem.createItem(name,
                            1 ,
                            blocks.get(rand.nextInt(0, blocks.size())),
                            itemFont.lore());
                    inventory.setItem(13, finalItem);
                }
            }
        }.runTaskTimer(JustChat.getInstance(), 0, 20L);
    }

    private void itemCool(){

        int randomColor = rand.nextInt(0x0, 0xFFFFFF);
        List<Component> lore = new ArrayList<>();
        lore.add(miniMessage.deserialize("<white><shadow:red:1>Use <shadow:blue:1>Shadow <shadow:green:1>color <shadow:yellow:1>!!!").decoration(TextDecoration.ITALIC, false));
        lore.add(Component.text("Use <shadow:_colorNameOrHex_:[alpha_as_float]>", NamedTextColor.GRAY).decoration(TextDecoration.ITALIC, false));
        lore.add(Component.text("(or just <shadow:colorName:1>)", NamedTextColor.GRAY).decoration(TextDecoration.ITALIC, false));
        lore.add(Component.text(""));
        lore.add(miniMessage.deserialize("<pride:pride>Pride flag colors").decoration(TextDecoration.ITALIC, false));
        lore.add(miniMessage.deserialize("<pride:gay>Gay flag colors").decoration(TextDecoration.ITALIC, false));
        lore.add(miniMessage.deserialize("<pride:lesbian>Lesbian flag colors").decoration(TextDecoration.ITALIC, false));
        lore.add(Component.text("Use <pride:flag> to use a gradient", NamedTextColor.WHITE).decoration(TextDecoration.ITALIC, false));
        lore.add(Component.text("Corresponding to a pride flag.", NamedTextColor.WHITE).decoration(TextDecoration.ITALIC, false));
        lore.add(Component.text("", NamedTextColor.WHITE));
        lore.add(Component.text("Click here to see all the implemented flags", NamedTextColor.GRAY));

        inventory.setItem(15, CreateItem.createItem(miniMessage.deserialize("<!i><shadow:#"+ randomColor+":1>Other cool features"), 1, Material.COOKIE, lore));

        new BukkitRunnable() {
            @Override
            public void run() {
                if (inventory.getViewers().isEmpty()) {
                    cancel();
                }
                else {
                    int randomColor = rand.nextInt(0x0, 0xFFFFFF);
                    ItemStack itemCool = inventory.getItem(15);
                    Component name = miniMessage.deserialize("<!i><shadow:#"+ randomColor+":1>Other cool features");
                    ItemStack finalItem = CreateItem.createItem(name,
                            1 ,Material.COOKIE,itemCool.lore());
                    inventory.setItem(15, finalItem);
                }
            }
        }.runTaskTimer(JustChat.getInstance(), 0, 20L);

    }
}
