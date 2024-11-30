package pl.kacpermajkowski.teamup.managers;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import pl.kacpermajkowski.teamup.utils.ChatUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class MessageManager {
    public static final List<UUID> playersWithTeamChatActive = new ArrayList<>();
    public static final String prefix = "&7[&6&lTeamUP&r&7] ";

    public static void sendSyntaxMessage(String syntax, Player player) {
        sendMessage("&cPoprawne użycie: &e" + syntax, player);
    }

    public static void sendMessage(String message, UUID playerUUID) {
        Player player = Bukkit.getServer().getPlayer(playerUUID);
        if (player != null) {
            sendMessage(message, player);
        }
    }

    public static void sendMessage(String message, Player player) {
        String colouredMessage = ChatUtils.fixColors("&7[&6&lTeamUP&r&7] " + message);
        player.sendMessage(colouredMessage);
    }

    public static String getPrefix() {
        return "&7[&6&lTeamUP&r&7] ";
    }
}
