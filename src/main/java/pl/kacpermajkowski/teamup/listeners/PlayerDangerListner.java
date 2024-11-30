package pl.kacpermajkowski.teamup.listeners;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import pl.kacpermajkowski.teamup.basic.Team;
import pl.kacpermajkowski.teamup.managers.MessageManager;
import pl.kacpermajkowski.teamup.managers.TeamManager;

import java.text.MessageFormat;
import java.util.UUID;

public class PlayerDangerListner implements Listener {
	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent event){
		Player player = event.getPlayer();
		Location loc = player.getLocation();
		loc.subtract(0 ,1 ,0);
		Location loc2 = player.getLocation();
		loc2.add(0 ,1 ,0);
		Location loc3 = player.getLocation();
		if(player.getHealth() <= 8){
			String message = "&cGracz &e{0} &cwyszedł z serwera mając mało HP. Jego koordynaty to &eX&7:&f{1}&8, &eY&7:&f{2}&8, &eZ&7:&f{3}";
			sendWarningToTeamMembers(player, message);
		} else if(loc2.getBlock().getType().equals(Material.WATER)){
			String message = "&cGracz &e{0} &cwyszedł z serwera będąc pod wodą. Jego koordynaty to &eX&7:&f{1}&8, &eY&7:&f{2}&8, &eZ&7:&f{3}";
			sendWarningToTeamMembers(player, message);
		} else if(player.getFireTicks() > 0){
			String message = "&cGracz &e{0} &cwyszedł z serwera będąc podpalonym. Jego koordynaty to &eX&7:&f{1}&8, &eY&7:&f{2}&8, &eZ&7:&f{3}";
			sendWarningToTeamMembers(player, message);
		} else if(loc.getBlock().getType().equals(Material.AIR)){
			String message = "&cGracz &e{0} &cwyszedł z serwera będąc w powietrzu. Jego koordynaty to &eX&7:&f{1}&8, &eY&7:&f{2}&8, &eZ&7:&f{3}";
			sendWarningToTeamMembers(player, message);
		} else if(loc3.getBlock().getType().equals(Material.LAVA)){
			String message = "&cGracz &e{0} &cwyszedł z serwera będąc w lavie. Jego koordynaty to &eX&7:&f{1}&8, &eY&7:&f{2}&8, &eZ&7:&f{3}";
			sendWarningToTeamMembers(player, message);
		}
	}

	private void sendWarningToTeamMembers(Player player, String unformattedMessage){
		Team team = TeamManager.getPlayerTeam(player);
		if(team != null) {
			Location location = player.getLocation();
			double x = location.getBlockX();
			double y = location.getBlockY();
			double z = location.getBlockZ();
			String formattedMessage = MessageFormat.format(unformattedMessage, player.getName(), x, y, z);
			for (UUID playerUUID : team.getMemberList().keySet()) {
				Player member = Bukkit.getServer().getPlayer(playerUUID);
				if (player != null)
					MessageManager.sendMessage(formattedMessage, member);
			}
		}
	}
}
