package pl.kacpermajkowski.teamup.events;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import pl.kacpermajkowski.teamup.basic.Team;

public class TeamCreateEvent extends Event {
    private static final HandlerList handlers = new HandlerList();

    private final Team createdTeam;

    public TeamCreateEvent(Team createdTeam) {
        this.createdTeam = createdTeam;
    }

    public Team getTeam() {
        return this.createdTeam;
    }

    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
