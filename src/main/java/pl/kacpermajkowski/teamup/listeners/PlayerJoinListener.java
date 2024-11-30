package pl.kacpermajkowski.teamup.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import pl.kacpermajkowski.teamup.managers.MessageManager;
import pl.kacpermajkowski.teamup.managers.TeamManager;

import java.text.MessageFormat;
import java.util.List;

public class PlayerJoinListener implements Listener {
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event){
		Player player = event.getPlayer();
		List<String> invitingTeams = TeamManager.getInvitations().get(player.getUniqueId());
		if(invitingTeams != null) {
			if (invitingTeams.size() > 0) {
				StringBuilder sb = new StringBuilder();
				for (int i = 0; i < invitingTeams.size(); i++) {
					String teamName = invitingTeams.get(i);
					sb.append(teamName);
					if (i != invitingTeams.size() - 1) sb.append("&r, &e");
				}
				String formattedMessage = MessageFormat.format("&aMasz oczekujące zaproszenia do drużyn: &e{0}&r&a.", sb.toString());
				MessageManager.sendMessage(formattedMessage, player);
			}
		}
	}
}
