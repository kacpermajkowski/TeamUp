package pl.kacpermajkowski.teamup.events;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import pl.kacpermajkowski.teamup.basic.Team;
import pl.kacpermajkowski.teamup.managers.TeamManager;

public class TeamNotesRemoveEvent extends Event {
	private static final HandlerList handlers = new HandlerList();
	private final Player player;
	private final Integer noteID;
	private final Team team;

	public TeamNotesRemoveEvent(Player player, int noteID){
		this.player = player;
		this.noteID = noteID;
		Team team = TeamManager.getPlayerTeam(player);
		this.team = team;

		team.getNotes().remove((noteID-1));
	}

	public Team getTeam() {
		return team;
	}

	public Player getPlayer() {
		return player;
	}
	public Integer getNoteID() {
		return noteID;
	}

	public HandlerList getHandlers() {
		return handlers;
	}
	public static HandlerList getHandlerList() {
		return handlers;
	}
}
