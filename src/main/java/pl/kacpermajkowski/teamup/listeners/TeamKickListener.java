package pl.kacpermajkowski.teamup.listeners;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import pl.kacpermajkowski.teamup.basic.Team;
import pl.kacpermajkowski.teamup.events.TeamKickEvent;
import pl.kacpermajkowski.teamup.managers.MessageManager;

import java.text.MessageFormat;
import java.util.UUID;

public class TeamKickListener implements Listener {
    @EventHandler
    public void onTeamKick(TeamKickEvent event) {
        String formattedMessage = MessageFormat.format("&cZostałeś wyrzucony z drużyny &e{0}&c. Więcęj szczęścia następnym razem :/", event.getTeam().getName());
        MessageManager.sendMessage(formattedMessage, event.getKickedPlayerUUID());
        OfflinePlayer kickedPlayer = Bukkit.getPlayer(event.getKickedPlayerUUID());
        String formattedMessage2 = MessageFormat.format("&cGracz &e{0} &azostał wyrzucony z waszej drużyny.", kickedPlayer.getName());
        Team team = event.getTeam();
        for (UUID memberUUID : team.getMemberList().keySet()) {
            if (!memberUUID.equals(event.getKickedPlayerUUID()))
                MessageManager.sendMessage(formattedMessage2, memberUUID);
        }
    }
}
