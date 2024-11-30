package pl.kacpermajkowski.teamup.events;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import pl.kacpermajkowski.teamup.basic.Team;

import java.util.UUID;

public class TeamKickEvent extends Event {
    private static final HandlerList handlers = new HandlerList();

    private final Team team;

    private final UUID kickedPlayerUUID;

    public TeamKickEvent(Team team, UUID kickedPlayerUUID) {
        this.team = team;
        this.kickedPlayerUUID = kickedPlayerUUID;
        team.removeTeamMember(kickedPlayerUUID);
    }

    public Team getTeam() {
        return this.team;
    }

    public UUID getKickedPlayerUUID() {
        return this.kickedPlayerUUID;
    }

    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
