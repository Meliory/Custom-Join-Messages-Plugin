package UHCPatata.customJoinMessages;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public final class CustomJoinMessages extends JavaPlugin implements Listener {

    List<String> mensajesUnirse;
    List<String> mensajesSalirse;
    List<String> jugadoresInvisibles;

    @Override
    public void onEnable() {
        saveDefaultConfig();
        loadMessages();
        getLogger().info("CustomJoinMessages plugin has been enabled!");
        getServer().getPluginManager().registerEvents(this, this);
        Objects.requireNonNull(getCommand("customjoinmessages")).setExecutor(new ReloadCommand(this));
    }

    public void loadMessages() { //Cargar config.yml
        mensajesUnirse = getConfig().getStringList("join-messages");
        mensajesSalirse = getConfig().getStringList("exit-messages");
        jugadoresInvisibles = getConfig().getStringList("vanish-players");
    }

    @Override
    public @NotNull Path getDataPath() {
        return super.getDataPath();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e){
        String playername = e.getPlayer().getName();
        if (mensajesUnirse.isEmpty() || jugadoresInvisibles.contains(playername)){
            e.setJoinMessage(null);
            return; 
        }
        String msg = mensajesUnirse.get(new Random().nextInt(mensajesUnirse.size()));
        msg = msg.replace("%player%", playername);
        msg = ChatColor.GREEN + "+ " + ChatColor.YELLOW + msg;
        e.setJoinMessage(msg);
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent e){
        String playername = e.getPlayer().getName();
        if (mensajesSalirse.isEmpty() || jugadoresInvisibles.contains(playername)){
            e.setQuitMessage(null);
            return;
        }
        String msg = mensajesSalirse.get(new Random().nextInt(mensajesSalirse.size()));
        msg = msg.replace("%player%", e.getPlayer().getName());
        msg = ChatColor.RED + "- " + ChatColor.YELLOW + msg;
        e.setQuitMessage(msg);
    }
}
