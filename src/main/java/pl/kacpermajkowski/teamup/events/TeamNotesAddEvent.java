package pl.kacpermajkowski.teamup.events;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import pl.kacpermajkowski.teamup.basic.Team;
import pl.kacpermajkowski.teamup.managers.TeamManager;

import java.util.HashMap;

public class TeamNotesAddEvent extends Event {
	private static final HandlerList handlers = new HandlerList();
	private final Player player;
	private final String note;
	private final Integer noteID;
	private final Team team;

	public TeamNotesAddEvent(Player player, String note){
		this.player = player;
		this.note = note;
		Team team = TeamManager.getPlayerTeam(player);
		this.team = team;

		team.getNotes().add(note);
		this.noteID = team.getNotes().size();
	}

	private Integer getAvailableID(HashMap<Integer, String> notes) {
		for(int i = 1; i <= notes.size()+1; i++){
			if(notes.get(i) == null) return i;
		}
		return null;
	}

	public Player getPlayer() {
		return player;
	}

	public String getNote() {
		return note;
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
