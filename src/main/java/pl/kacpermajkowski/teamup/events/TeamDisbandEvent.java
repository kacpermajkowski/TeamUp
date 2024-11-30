package pl.kacpermajkowski.teamup.events;

import org.bukkit.Bukkit;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import pl.kacpermajkowski.teamup.basic.Team;
import pl.kacpermajkowski.teamup.managers.TeamManager;

import java.util.UUID;

public class TeamDisbandEvent extends Event {
    private static final HandlerList handlers = new HandlerList();

    private final Team disbandedTeam;

    public TeamDisbandEvent(Team disbandedTeam) {
        this.disbandedTeam = disbandedTeam;
        TeamManager.disbandTeam(disbandedTeam);
        for(UUID invitedPlayerUUID:disbandedTeam.getInvitedPlayers()){
            Bukkit.getServer().getPluginManager().callEvent(new TeamCancelinviteEvent(disbandedTeam, invitedPlayerUUID));
        }
    }

    public Team getTeam() {
        return this.disbandedTeam;
    }

    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
