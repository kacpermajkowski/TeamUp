package pl.kacpermajkowski.teamup.commands.team.subcommands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.kacpermajkowski.teamup.basic.Team;
import pl.kacpermajkowski.teamup.basic.TeamRole;
import pl.kacpermajkowski.teamup.commands.team.TeamSubcommand;
import pl.kacpermajkowski.teamup.events.TeamLeaveEvent;
import pl.kacpermajkowski.teamup.managers.MessageManager;
import pl.kacpermajkowski.teamup.managers.TeamManager;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

public class TeamLeaveCommand implements TeamSubcommand {
    private final String name = "leave";
    private final String description = "pozwala opuścić drużynę";
    private final String syntax = "/team leave";
    private final String permission = "teamup.team.basic";
    private final TeamRole minimumRequiredTeamRole = TeamRole.MEMBER;

    public void execute(Player player, String[] args) {
        if (args.length == 1) {
            Team team = TeamManager.getPlayerTeam(player);
            if (team.isPlayerMember(player.getUniqueId())) {
                if (!team.getPlayerRole(player.getUniqueId()).equals(TeamRole.LEADER)) {
                    Bukkit.getServer().getPluginManager().callEvent(new TeamLeaveEvent(team, player.getUniqueId()));
                    String formattedMessage = MessageFormat.format("&aPomyślne opuściłeś drużynę &e{0}&a.", team.getName());
                    MessageManager.sendMessage(formattedMessage, player);
                } else {
                    MessageManager.sendMessage("&cNie możesz opuścić drużyny, ponieważ jesteś liderem. Możesz usunąć drużynę komendą &e/team disband", player);
                }
            } else {
                MessageManager.sendMessage("&cNie jesteś członkiem żadnej drużyny!", player);
            }
        } else {
            MessageManager.sendSyntaxMessage(this.syntax, player);
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
