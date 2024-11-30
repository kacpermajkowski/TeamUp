package pl.kacpermajkowski.teamup.listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import pl.kacpermajkowski.teamup.basic.Team;
import pl.kacpermajkowski.teamup.events.TeamJoinEvent;
import pl.kacpermajkowski.teamup.managers.MessageManager;

import java.text.MessageFormat;
import java.util.UUID;

public class TeamJoinListener implements Listener {
    @EventHandler
    public void onTeamJoin(TeamJoinEvent event) {
        Team team = event.getTeam();
        Player invitedPlayer = event.getPlayer();
        String formattedMessage = MessageFormat.format("&aGracz &e{0} &adołączył do waszej drużyny.", invitedPlayer.getName());
        for (UUID memberUUID : team.getMemberList().keySet()) {
            if (!memberUUID.equals(invitedPlayer.getUniqueId())) {
                Player member = Bukkit.getPlayer(memberUUID);
                if (member != null)
                    MessageManager.sendMessage(formattedMessage, member);
            }
        }
    }
}
