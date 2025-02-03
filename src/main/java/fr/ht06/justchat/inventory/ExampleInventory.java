package fr.ht06.justchat.inventory;

import fr.ht06.justchat.CreateItem;
import fr.ht06.justchat.JustChat;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
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

        //decoration
        blockDecoration();

        //Another cool thing
        List<Component> lore = new ArrayList<>();
        lore.add(miniMessage.deserialize("<white><shadow:red:1>Use <shadow:blue:1>Shadow <shadow:green:1>color <shadow:yellow:1>!!!").decoration(TextDecoration.ITALIC, false));
        lore.add(Component.text("Use <shadow:_colorNameOrHex_:[alpha_as_float]> (or just <shadow:colorName:1>)", NamedTextColor.GRAY).decoration(TextDecoration.ITALIC, false));
        lore.add(Component.text(""));
        lore.add(miniMessage.deserialize("<pride>Pride flag colors").decoration(TextDecoration.ITALIC, false));
        lore.add(miniMessage.deserialize("<pride:Gay>gay flag colors").decoration(TextDecoration.ITALIC, false));
        lore.add(miniMessage.deserialize("<pride:Lesbian>lesbian flag colors").decoration(TextDecoration.ITALIC, false));
        lore.add(Component.text("use <pride:flag> to use a gradient corresponding to a pride flag.", NamedTextColor.WHITE).decoration(TextDecoration.ITALIC, false));

        inventory.setItem(15, CreateItem.createItem(miniMessage.deserialize("<!i><shadow:red:1>Other cool features"), 1, Material.COOKIE, lore));
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
        }
    }


    private void blockColor(){
        //color
        int randomColor = rand.nextInt(0x0, 0xFFFFFF);
        List<Material> randomWool = Arrays.stream(Material.values())
                .filter(material -> material.name().contains("WOOL")).toList();

        List<Component> lore = new ArrayList<Component>();
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
}
