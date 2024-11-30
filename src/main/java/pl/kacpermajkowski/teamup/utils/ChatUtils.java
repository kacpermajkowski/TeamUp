package pl.kacpermajkowski.teamup.utils;

import org.bukkit.ChatColor;

public class ChatUtils {
    public static String fixColors(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }
}
