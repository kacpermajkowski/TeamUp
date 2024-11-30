package pl.kacpermajkowski.teamup.commands.team.subcommands.coordsSubcommands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.kacpermajkowski.teamup.basic.Team;
import pl.kacpermajkowski.teamup.basic.TeamRole;
import pl.kacpermajkowski.teamup.commands.team.TeamSubcommand;
import pl.kacpermajkowski.teamup.events.TeamCoordsRemoveEvent;
import pl.kacpermajkowski.teamup.managers.MessageManager;
import pl.kacpermajkowski.teamup.managers.TeamManager;

import java.util.ArrayList;
import java.util.List;

public class CoordsRemoveCommand implements TeamSubcommand{

	private final String name = "remove";
	private final String description = "pozwala usunąć zapisane koordynaty z listy";
	private final String syntax = "/team coords remove <nazwa>";
	private final String permission = "teamup.team.basic";
	private final TeamRole permittedTeamRole = TeamRole.MEMBER;

	public void execute(Player player, String[] args) {
		if(args.length == 3){
			Team team = TeamManager.getPlayerTeam(player);
			if(team.getCoords().containsKey(args[2].toLowerCase())) {
				Bukkit.getServer().getPluginManager().callEvent(new TeamCoordsRemoveEvent(player, args[2]));
				MessageManager.sendMessage("&aPomyślnie usunąłeś z listy koordy o nazwie &e" + args[2] + "&a.", player);
			} else {
				MessageManager.sendMessage("&cKoordy o nazwie &e"+args[2].toLowerCase()+" &cnie są zapisane na liście.", player);
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
		List<String> autocomParams = new ArrayList<>();
		if(sender instanceof Player) {
			Player player = (Player) sender;
			Team team = TeamManager.getPlayerTeam(player);
			if (args.length == 2) {
				if (team != null) {
					autocomParams.addAll(team.getCoords().keySet());
				}
			}
		}
		return autocomParams;
	}
}
