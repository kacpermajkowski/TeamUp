package pl.kacpermajkowski.teamup.commands.team.subcommands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.kacpermajkowski.teamup.basic.Team;
import pl.kacpermajkowski.teamup.basic.TeamRole;
import pl.kacpermajkowski.teamup.commands.team.TeamSubcommand;
import pl.kacpermajkowski.teamup.events.TeamJoinEvent;
import pl.kacpermajkowski.teamup.managers.MessageManager;
import pl.kacpermajkowski.teamup.managers.TeamManager;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

public class TeamJoinCommand implements TeamSubcommand {
    private final String name = "join";
    private final String description = "pozwala zaakceptować zaproszenie do drużyny";
    private final String syntax = "/team join <nazwa drużyny>";
    private final String permission = "teamup.team.basic";
    private final TeamRole minimumRequiredTeamRole = null;

    public void execute(Player player, String[] args) {
        if (args.length == 2) {
            String teamName = args[1];
            Team team = TeamManager.getPlayerTeam(player);
            if (team == null) {
                Team teamToJoin = TeamManager.getTeamByName(teamName);
                if (teamToJoin != null) {
                    if (teamToJoin.isPlayerInvited(player.getUniqueId())) {
                        Bukkit.getPluginManager().callEvent(new TeamJoinEvent(teamToJoin, player));
                    } else {
                        MessageManager.sendMessage("&cNie możesz dołączyć do tej drużyny, bo nie zostałeś do niej zaproszony.", player);
                    }
                } else {
                    String formattedMessage = MessageFormat.format("&cDrużyna o nazwie &e{0} &c nie istnieje", args[1]);
                    MessageManager.sendMessage(formattedMessage, player);
                }
            } else {
                MessageManager.sendMessage("&cNależysz juz do drużyny, nie możesz więc przyjmować zaproszeń.", player);
            }
        } else {
            MessageManager.sendMessage("&cPoprawne użycie: &c/team join <nazwa drużyny>", player);
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
        List<String> autocomParams = new ArrayList<>();
        if(sender instanceof Player) {
            Player player = (Player) sender;
            if (args.length == 2) {
                autocomParams.addAll(TeamManager.getInvitations().get(player.getUniqueId()));
            }
        }
        return autocomParams;
    }
}
