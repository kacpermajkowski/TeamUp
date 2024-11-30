package pl.kacpermajkowski.teamup.listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import pl.kacpermajkowski.teamup.basic.Team;
import pl.kacpermajkowski.teamup.events.TeamInviteEvent;
import pl.kacpermajkowski.teamup.managers.MessageManager;

import java.text.MessageFormat;
import java.util.UUID;

public class TeamInviteListener implements Listener {
    @EventHandler
    public void onTeamInvite(TeamInviteEvent event) {
        Team invitingTeam = event.getTeam();
        UUID invitedPlayerUUID = event.getInvitedPlayerUUID();
        Player invitedPlayer = Bukkit.getPlayer(invitedPlayerUUID);
        if (invitedPlayer != null && invitedPlayer.isOnline())
            sendInvitationMessage(invitedPlayer, invitingTeam.getName());
    }

    private void sendInvitationMessage(Player invitedPlayer, String invitingTeamName) {
        String formattedMessage = MessageFormat.format("&aZostałeś zaproszony do drużyny &e{0}&r&a. Aby do niej dołączyć wpisz &e/team join {1}", invitingTeamName, invitingTeamName);
        MessageManager.sendMessage(formattedMessage, invitedPlayer);
    }
}
