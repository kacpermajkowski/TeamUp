package pl.kacpermajkowski.teamup.events;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import pl.kacpermajkowski.teamup.basic.Team;
import pl.kacpermajkowski.teamup.managers.TeamManager;

import java.util.UUID;

public class TeamInviteEvent extends Event {
    private static final HandlerList handlers = new HandlerList();

    private final Team invitingTeam;

    private final UUID invitedPlayerUUID;

    public TeamInviteEvent(Team invitingTeam, UUID invitedPlayerUUID) {
        this.invitingTeam = invitingTeam;
        this.invitedPlayerUUID = invitedPlayerUUID;
        invitingTeam.invitePlayer(invitedPlayerUUID);
        TeamManager.addInvitation(invitedPlayerUUID, invitingTeam.getName());
    }

    public Team getTeam() {
        return this.invitingTeam;
    }

    public UUID getInvitedPlayerUUID() {
        return this.invitedPlayerUUID;
    }

    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
