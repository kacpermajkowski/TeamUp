package pl.kacpermajkowski.teamup;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import pl.kacpermajkowski.teamup.commands.team.TeamCommandManager;
import pl.kacpermajkowski.teamup.expansions.PlaceholderAPIExpansion;
import pl.kacpermajkowski.teamup.listeners.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;

public final class TeamUP extends JavaPlugin {
    public void onEnable() {
        setCommandExecutors();
        registerListeners();
        if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
            new PlaceholderAPIExpansion(this).register();
        } else {
            getLogger().log(Level.WARNING, "Could not find PlaceholderAPI! Some functions might not be available.");
        }
    }

    public void onDisable() {
    }

    private void setCommandExecutors() {
        TeamCommandManager teamCommandManager = new TeamCommandManager();
        getCommand("team").setExecutor(teamCommandManager);
        getCommand("team").setTabCompleter(teamCommandManager);
    }

    private void registerListeners() {
        List<Listener> listeners = new ArrayList<>(Arrays.asList(
                new TeamCreateListener(),
                new TeamDisbandListener(),
                new TeamJoinListener(),
                new TeamInviteListener(),
                new TeamKickListener(),
                new TeamLeaveListener(),
                new ChatListener(),
                new PlayerDangerListner(),
                new PlayerJoinListener()
        ));
        for (Listener listener : listeners)
            getServer().getPluginManager().registerEvents(listener, this);
    }
}
