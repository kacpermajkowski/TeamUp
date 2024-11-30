package pl.kacpermajkowski.teamup.events;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import pl.kacpermajkowski.teamup.basic.Team;
import pl.kacpermajkowski.teamup.managers.TeamManager;

import java.util.UUID;

public class TeamCancelinviteEvent extends Event {
    private static final HandlerList handlers = new HandlerList();

    private final Team invitingTeam;

    private final UUID invitedPlayerUUID;

    public TeamCancelinviteEvent(Team invitingTeam, UUID invitedPlayerUUID) {
        this.invitingTeam = invitingTeam;
        this.invitedPlayerUUID = invitedPlayerUUID;
        invitingTeam.revokePlayerInvitation(invitedPlayerUUID);
        TeamManager.revokeInvitation(invitedPlayerUUID, invitingTeam.getName());
    }

    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
