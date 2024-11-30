package pl.kacpermajkowski.teamup.listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import pl.kacpermajkowski.teamup.basic.Team;
import pl.kacpermajkowski.teamup.managers.MessageManager;
import pl.kacpermajkowski.teamup.managers.TeamManager;
import pl.kacpermajkowski.teamup.utils.ChatUtils;

import java.text.MessageFormat;
import java.util.UUID;

public class ChatListener implements Listener {
	@EventHandler
	public void onChat(AsyncPlayerChatEvent event) {
		Player player = event.getPlayer();
		if(MessageManager.playersWithTeamChatActive.contains(player.getUniqueId())){
			event.setCancelled(true);
			Team team = TeamManager.getPlayerTeam(player);
			String message = "&7[&bCzat drużynowy&7] &r{0}&8: &r{1}";
			String formattedMessage = MessageFormat.format(message, player.getName(), event.getMessage());
			for (UUID playerUUID : team.getMemberList().keySet()) {
				Player member = Bukkit.getServer().getPlayer(playerUUID);
				if (member != null) {
					member.sendMessage(ChatUtils.fixColors(formattedMessage));
				}
			}
		}
	}
}
