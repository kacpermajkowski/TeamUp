package pl.kacpermajkowski.teamup.listeners;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import pl.kacpermajkowski.teamup.basic.Team;
import pl.kacpermajkowski.teamup.events.TeamLeaveEvent;
import pl.kacpermajkowski.teamup.managers.MessageManager;

import java.text.MessageFormat;
import java.util.UUID;

public class TeamLeaveListener implements Listener {
    @EventHandler
    public void onTeamKick(TeamLeaveEvent event) {
        OfflinePlayer player = Bukkit.getOfflinePlayer(event.getPlayerUUID());
        String formattedMessage2 = MessageFormat.format("&cGracz &e{0} &copuścił waszą drużynę.", player.getName());
        Team team = event.getTeam();
        for (UUID memberUUID : team.getMemberList().keySet()) {
            if (!memberUUID.equals(event.getPlayerUUID()))
                MessageManager.sendMessage(formattedMessage2, memberUUID);
        }
    }
}
