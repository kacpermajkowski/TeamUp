package pl.kacpermajkowski.teamup.commands.team.subcommands;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.kacpermajkowski.teamup.basic.Team;
import pl.kacpermajkowski.teamup.basic.TeamRole;
import pl.kacpermajkowski.teamup.commands.team.TeamSubcommand;
import pl.kacpermajkowski.teamup.events.TeamInviteEvent;
import pl.kacpermajkowski.teamup.managers.MessageManager;
import pl.kacpermajkowski.teamup.managers.TeamManager;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TeamInviteCommand implements TeamSubcommand {
    private final String name = "invite";
    private final String description = "pozwala zaprosić gracza do drużyny";
    private final String syntax = "/team invite <nick>";
    private final String permission = "teamup.team.manage";
    private final TeamRole minimumRequiredTeamRole = TeamRole.LEADER;

    public void execute(Player player, String[] args) {
        if (args.length == 2) {
            Team team = TeamManager.getPlayerTeam(player);
            if (team != null) {
                UUID invitedPlayerUUID = Bukkit.getOfflinePlayer(args[1]).getUniqueId();
                if(hasPlayerEverBeenToServer(args[1])) {
                    if (!team.isPlayerMember(invitedPlayerUUID)) {
                        if (!team.getInvitedPlayers().contains(invitedPlayerUUID)) {
                            Bukkit.getServer().getPluginManager().callEvent(new TeamInviteEvent(team, invitedPlayerUUID));
                            String formattedMessage = MessageFormat.format("&aGracz &f{0} &azostał zaproszony do twojej drużyny!", args[1]);
                            MessageManager.sendMessage(formattedMessage, player);
                        } else {
                            String formattedMessage = MessageFormat.format("&cGracz &f{0} &cjest już zaproszony do twojej drużyny.", args[1]);
                            MessageManager.sendMessage(formattedMessage, player);
                        }
                    } else {
                        String formattedMessage = MessageFormat.format("&cGracz &f{0} &cjest już członkiem twojej drużyny.", args[1]);
                        MessageManager.sendMessage(formattedMessage, player);
                    }
                } else {
                    MessageManager.sendMessage("&cGracz &e"+args[1]+" &cnigdy nie był na serwerze.", player);
                }
            } else {
                MessageManager.sendMessage("&cNie należysz do żadnej drużyny, więc nie możesz dodawać członków.", player);
            }
        } else {
            MessageManager.sendMessage("&cPoprawne użycie: &e/team invite <nick gracza>", player);
        }
    }

    private boolean hasPlayerEverBeenToServer(String arg) {
        for(OfflinePlayer player:Bukkit.getServer().getOfflinePlayers()){
            if(player.getName().equals(arg)) return true;
        }
        return false;
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
        if(args.length >= 2){
            return new ArrayList<>();
        }
        return null;
    }
}
