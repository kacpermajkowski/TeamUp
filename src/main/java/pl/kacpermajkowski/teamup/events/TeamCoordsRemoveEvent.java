package pl.kacpermajkowski.teamup.events;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import pl.kacpermajkowski.teamup.basic.Team;
import pl.kacpermajkowski.teamup.managers.TeamManager;

public class TeamCoordsRemoveEvent extends Event {
	private static final HandlerList handlers = new HandlerList();
	private final Player player;
	private final String coordsName;

	public TeamCoordsRemoveEvent(Player player, String coordsName){
		this.player = player;
		this.coordsName = coordsName;

		Team team = TeamManager.getPlayerTeam(player);
		team.getCoords().remove(coordsName);
	}

	public Player getPlayer() {
		return player;
	}
	public String getCoordsName() {
		return coordsName;
	}

	public HandlerList getHandlers() {
		return handlers;
	}
	public static HandlerList getHandlerList() {
		return handlers;
	}
}
