package pl.kacpermajkowski.teamup.commands.team.subcommands.coordsSubcommands;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.kacpermajkowski.teamup.basic.Team;
import pl.kacpermajkowski.teamup.basic.TeamRole;
import pl.kacpermajkowski.teamup.commands.team.TeamSubcommand;
import pl.kacpermajkowski.teamup.managers.MessageManager;
import pl.kacpermajkowski.teamup.managers.TeamManager;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CoordsShareCommand implements TeamSubcommand{

	private final String name = "share";
	private final String description = "pozwala udostępnić twoją obecną lokalizację członkom zepsołu";
	private final String syntax = "/team coords share";
	private final String permission = "teamup.team.basic";
	private final TeamRole permittedTeamRole = TeamRole.MEMBER;

	public void execute(Player player, String[] args) {
		if(args.length == 2){
			Team team = TeamManager.getPlayerTeam(player);
			for (UUID memberUUID : team.getMemberList().keySet()) {
				Location location = player.getLocation();
				double x = location.getBlockX();
				double y = location.getBlockY();
				double z = location.getBlockZ();
				String message = "&aGracz &e{0} &audostępnił wam swoje koordynaty. Jego koordynaty to &eX&7:&f{1}&8, &eY&7:&f{2}&8, &eZ&7:&f{3}";
				String formattedMessage = MessageFormat.format(message, player.getName(), x, y, z);
				MessageManager.sendMessage(formattedMessage, memberUUID);
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
