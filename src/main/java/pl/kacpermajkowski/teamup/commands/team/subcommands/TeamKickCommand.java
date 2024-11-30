package pl.kacpermajkowski.teamup.commands.team.subcommands;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.kacpermajkowski.teamup.basic.Team;
import pl.kacpermajkowski.teamup.basic.TeamRole;
import pl.kacpermajkowski.teamup.commands.team.TeamSubcommand;
import pl.kacpermajkowski.teamup.events.TeamKickEvent;
import pl.kacpermajkowski.teamup.managers.MessageManager;
import pl.kacpermajkowski.teamup.managers.TeamManager;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TeamKickCommand implements TeamSubcommand {
    private final String name = "kick";
    private final String description = "pozwala wyrzucić gracza z drużyny";
    private final String syntax = "/team kick <nick gracza>";
    private final String permission = "teamup.team.manage";
    private final TeamRole minimumRequiredTeamRole = TeamRole.LEADER;

    public void execute(Player player, String[] args) {
        if (args.length == 2) {
            Team team = TeamManager.getPlayerTeam(player);
            UUID kickedPlayerUUID = Bukkit.getOfflinePlayer(args[1]).getUniqueId();
            if (team.isPlayerMember(kickedPlayerUUID)) {
                Bukkit.getServer().getPluginManager().callEvent(new TeamKickEvent(team, kickedPlayerUUID));
                String formattedMessage = MessageFormat.format("&aGracz &e{0} &azostał pomyślnie wyrzucony z twojej drużyny!", args[1]);
                MessageManager.sendMessage(formattedMessage, player);
            } else {
                String formattedMessage = MessageFormat.format("&cGracz &e{0} &cnie jest członkiem twojej drużyny.", args[1]);
                MessageManager.sendMessage(formattedMessage, player);
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
        List<String> autocomParams = new ArrayList<>();
        if(sender instanceof Player) {
            Player player = (Player) sender;
            Team team = TeamManager.getPlayerTeam(player);
            if (args.length == 2) {
                if (team != null) {
                    for (UUID memberUUID : team.getMemberList().keySet()) {
                        OfflinePlayer invitedPlayer = Bukkit.getOfflinePlayer(memberUUID);
                        autocomParams.add(invitedPlayer.getName());
                    }
                }

            }
        }
        return autocomParams;
    }
}
