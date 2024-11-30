package pl.kacpermajkowski.teamup.events;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import pl.kacpermajkowski.teamup.basic.Team;

import java.util.UUID;

public class TeamLeaveEvent extends Event {
    private static final HandlerList handlers = new HandlerList();

    private final Team team;

    private final UUID playerUUID;

    public TeamLeaveEvent(Team team, UUID playerUUID) {
        this.team = team;
        this.playerUUID = playerUUID;
        team.removeTeamMember(playerUUID);
    }

    public UUID getPlayerUUID() {
        return this.playerUUID;
    }

    public Team getTeam() {
        return this.team;
    }

    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
