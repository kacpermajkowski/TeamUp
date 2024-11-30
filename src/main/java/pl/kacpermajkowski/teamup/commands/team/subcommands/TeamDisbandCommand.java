package pl.kacpermajkowski.teamup.commands.team.subcommands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.kacpermajkowski.teamup.basic.Team;
import pl.kacpermajkowski.teamup.basic.TeamRole;
import pl.kacpermajkowski.teamup.commands.team.TeamSubcommand;
import pl.kacpermajkowski.teamup.events.TeamDisbandEvent;
import pl.kacpermajkowski.teamup.managers.MessageManager;
import pl.kacpermajkowski.teamup.managers.TeamManager;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

public class TeamDisbandCommand implements TeamSubcommand {
    private final String name = "disband";
    private final String description = "pozwala rozwiązać drużynę";
    private final String syntax = "/team disband";
    private final String permission = "teamup.team.manage";
    private final TeamRole minimumRequiredTeamRole = TeamRole.LEADER;

    public void execute(Player player, String[] args) {
        Team team = TeamManager.getPlayerTeam(player);
        if (team != null) {
            TeamRole playerRole = team.getPlayerRole(player.getUniqueId());
            if (playerRole.equals(TeamRole.LEADER)) {
                String formattedMessage = MessageFormat.format("&aPomyślnie rozwiązałeś drużynę o nazwie &e{0}.", team.getName());
                Bukkit.getServer().getPluginManager().callEvent(new TeamDisbandEvent(team));
                MessageManager.sendMessage(formattedMessage, player);
            } else {
                MessageManager.sendMessage("&cNie jesteś właścicielem drużyny, więc nie możesz jej rozwiązać!", player);
            }
        } else {
            MessageManager.sendMessage("&cNie należysz do żadnej drużyny, więc nie możesz jej rozwiązać.", player);
        }
    }

    public String getName() {
        return this.name;
    }
    public String getDescription() {
        return this.description;
    }
    public String getSyntax() {
        return this.syntax;
    }
    public String getPermission() {
        return this.permission;
    }
    public TeamRole getMinimumRequiredTeamRole() {
        return this.minimumRequiredTeamRole;
    }

    public List<String> getTabCompletion(CommandSender sender, Command command, String alias, String[] args) {
        return new ArrayList<>();
    }
}
