package pl.kacpermajkowski.teamup.listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import pl.kacpermajkowski.teamup.basic.Team;
import pl.kacpermajkowski.teamup.events.TeamDisbandEvent;
import pl.kacpermajkowski.teamup.managers.MessageManager;

import java.text.MessageFormat;
import java.util.UUID;

public class TeamDisbandListener implements Listener {
    @EventHandler
    public void onTeamDisbandment(TeamDisbandEvent event) {
        messageDisbandedTeamMembers(event.getTeam());
    }

    private void messageDisbandedTeamMembers(Team team) {
        String formattedMessage = MessageFormat.format("&cDrużyna &e{0} &czostała rozwiązana. Nie należysz teraz do żadnej drużyny.", team.getName());
        for (UUID playerUUID : team.getMemberList().keySet()) {
            Player player = Bukkit.getServer().getPlayer(playerUUID);
            if (player != null)
                MessageManager.sendMessage(formattedMessage, player);
        }
    }
}
