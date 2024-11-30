package pl.kacpermajkowski.teamup.commands.team.subcommands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.kacpermajkowski.teamup.basic.TeamRole;
import pl.kacpermajkowski.teamup.commands.team.HelpCommand;
import pl.kacpermajkowski.teamup.commands.team.Subcommand;
import pl.kacpermajkowski.teamup.commands.team.TeamSubcommand;
import pl.kacpermajkowski.teamup.commands.team.subcommands.coordsSubcommands.*;
import pl.kacpermajkowski.teamup.managers.MessageManager;
import pl.kacpermajkowski.teamup.managers.TeamManager;

import java.util.ArrayList;
import java.util.List;

public class TeamCoordsCommand implements TeamSubcommand {
  private final List<TeamSubcommand> subcommands = new ArrayList<>();

  public TeamCoordsCommand() {
    this.subcommands.add(new CoordsAddCommand());
    this.subcommands.add(new CoordsRemoveCommand());
    this.subcommands.add(new CoordsListCommand());
    this.subcommands.add(new CoordsCheckCommand());
    this.subcommands.add(new CoordsShareCommand());
  }

  private final String name = "coords";
  private final String description = "pomoc dotycząca podkomendy /team coords";
  private final String syntax = "/team coords";
  private final String permission = "teamup.team.coords";
  private final TeamRole minimumRequiredTeamRole = TeamRole.MEMBER;

  public void execute(Player player, String[] args) {
      HelpCommand helpCommand = new HelpCommand("/team coords help", permission, this.subcommands);
      if (args.length < 2) {
        helpCommand.execute(player, args);
      } else {
        TeamSubcommand subcommand = getSubcommandByName(args[1]);
          if(subcommand != null) {
            TeamRole requiredPlayerRole = subcommand.getMinimumRequiredTeamRole();
            TeamRole playerTeamRole = TeamManager.getPlayerTeamRole(player.getUniqueId());
              if (playerTeamRole.getPermissionRank() >= requiredPlayerRole.getPermissionRank()) {
                subcommand.execute(player, args);
              } else {
                MessageManager.sendMessage("&cNie masz dość wysokiej rangi, aby to zrobić. Wymagana ranga to &e" + requiredPlayerRole.name(), player);
              }
          } else {
            helpCommand.execute(player, args);
          }
      }
  }

  private TeamSubcommand getSubcommandByName(String subcommandName) {
    for (TeamSubcommand ts : this.subcommands) {
      if (subcommandName.equalsIgnoreCase(ts.getName())) {
        return ts;
      }
    }
    return null;
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
      List<String> autocomParams = null;
      if(args.length == 3){
        autocomParams = new ArrayList<>();
        for(Subcommand subcommand:subcommands){
          autocomParams.add(subcommand.getName());
        }
      }
      return autocomParams;
  }
}