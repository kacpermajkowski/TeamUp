package pl.kacpermajkowski.teamup.events;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import pl.kacpermajkowski.teamup.basic.Team;
import pl.kacpermajkowski.teamup.managers.MessageManager;

import java.text.MessageFormat;

public class TeamJoinEvent extends Event {
    private static final HandlerList handlers = new HandlerList();

    private final Team team;

    private final Player player;

    public TeamJoinEvent(Team team, Player player) {
        this.team = team;
        this.player = player;
        team.addTeamMember(player.getUniqueId());
        team.revokePlayerInvitation(player.getUniqueId());
        String formattedMessage = MessageFormat.format("&aDołączyłeś do drużyny &e{0}&a. Miłej gry :)", team.getName());
        MessageManager.sendMessage(formattedMessage, player);
    }

    public Team getTeam() {
        return this.team;
    }

    public Player getPlayer() {
        return this.player;
    }

    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
