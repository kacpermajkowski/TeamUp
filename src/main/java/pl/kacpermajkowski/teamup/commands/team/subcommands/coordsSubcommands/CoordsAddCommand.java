package pl.kacpermajkowski.teamup.commands.team.subcommands.coordsSubcommands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.kacpermajkowski.teamup.basic.Team;
import pl.kacpermajkowski.teamup.basic.TeamRole;
import pl.kacpermajkowski.teamup.commands.team.TeamSubcommand;
import pl.kacpermajkowski.teamup.events.TeamCoordsAddEvent;
import pl.kacpermajkowski.teamup.managers.MessageManager;
import pl.kacpermajkowski.teamup.managers.TeamManager;

import java.util.ArrayList;
import java.util.List;

public class CoordsAddCommand implements TeamSubcommand{

	private final String name = "add";
	private final String description = "pozwala dodać twoje obecne koordynaty do listy";
	private final String syntax = "/team coords add <nazwa>";
	private final String permission = "teamup.team.coords";
	private final TeamRole permittedTeamRole = TeamRole.MEMBER;

	public void execute(Player player, String[] args) {
		if(args.length == 3){
			Team team = TeamManager.getPlayerTeam(player);
			if(!team.getCoords().containsKey(args[2].toLowerCase())){
				Bukkit.getServer().getPluginManager().callEvent(new TeamCoordsAddEvent(player, args[2].toLowerCase()));
				MessageManager.sendMessage("&aPomyślnie dodałeś swoją obecną lokalizację na listę koordów pod nazwą &e"+args[2].toLowerCase()+"&a.", player);
			} else {
				MessageManager.sendMessage("&cKoordy o nazwie &e"+args[2].toLowerCase()+" &csą już zapisane na liście. Wybierz inną nazwę.", player);
			}
		} else {
			MessageManager.sendSyntaxMessage(syntax, player);
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
		return this.permittedTeamRole;
	}

	public List<String> getTabCompletion(CommandSender sender, Command command, String alias, String[] args) {
		return new ArrayList<>();
	}
}
