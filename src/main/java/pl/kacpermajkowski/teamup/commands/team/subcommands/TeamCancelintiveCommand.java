package pl.kacpermajkowski.teamup.commands.team.subcommands;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.kacpermajkowski.teamup.basic.Team;
import pl.kacpermajkowski.teamup.basic.TeamRole;
import pl.kacpermajkowski.teamup.commands.team.TeamSubcommand;
import pl.kacpermajkowski.teamup.events.TeamCancelinviteEvent;
import pl.kacpermajkowski.teamup.managers.MessageManager;
import pl.kacpermajkowski.teamup.managers.TeamManager;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TeamCancelintiveCommand implements TeamSubcommand {
  private final String name = "cancelinvite";
  private final String description = "pozwala cofnąć zaproszenie gracza do drużyny";
  private final String syntax = "/team cancelinvite <nick>";
  private final String permission = "teamup.team.manage";
  private final TeamRole minimumRequiredTeamRole = TeamRole.LEADER;
  
  public void execute(Player player, String[] args) {
    if (args.length == 2) {
      Team team = TeamManager.getPlayerTeam(player);
      UUID invitedPlayerUUID = Bukkit.getOfflinePlayer(args[1]).getUniqueId();
      if (team.getInvitedPlayers().contains(invitedPlayerUUID)) {
        Bukkit.getServer().getPluginManager().callEvent(new TeamCancelinviteEvent(team, invitedPlayerUUID));
        String formattedMessage = MessageFormat.format("&aZaproszenie gracza &f{0} &azostało cofnięte!", args[1]);
        MessageManager.sendMessage(formattedMessage, player);
      } else {
        String formattedMessage = MessageFormat.format("&cGracz &f{0} &cnie jest zaproszony do twojej drużyny.", args[1]);
        MessageManager.sendMessage(formattedMessage, player);
      } 
    } else {
      MessageManager.sendMessage("&cPoprawne użycie: &e/team cancelinvite <nick gracza>", player);
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
    Player player = (Player) sender;
    Team team = TeamManager.getPlayerTeam(player);
    if(args.length == 2){
      if(team != null){
        for(UUID invitedPlayerUUID:team.getInvitedPlayers()){
          OfflinePlayer invitedPlayer = Bukkit.getOfflinePlayer(invitedPlayerUUID);
          autocomParams.add(invitedPlayer.getName());
        }
      }
    }
    return autocomParams;
  }

}
