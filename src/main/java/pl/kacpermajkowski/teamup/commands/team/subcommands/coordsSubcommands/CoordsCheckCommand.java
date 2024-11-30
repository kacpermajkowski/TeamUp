package pl.kacpermajkowski.teamup.commands.team.subcommands.coordsSubcommands;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
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

public class CoordsCheckCommand implements TeamSubcommand{

	private final String name = "check";
	private final String description = "pozwala sprawdzić na jakich koordynatach znajduje się członek twojej drużyny";
	private final String syntax = "/team coords check <nick gracza>";
	private final String permission = "teamup.team.basic";
	private final TeamRole permittedTeamRole = TeamRole.MEMBER;

	public void execute(Player player, String[] args) {
		if (args.length == 3){
			Team team = TeamManager.getPlayerTeam(player);
			Player member = Bukkit.getPlayer(args[2]);
			if(member != null){
				if(team.getMemberList().containsKey(member.getUniqueId())){
					Location location = member.getLocation();
					double x = location.getBlockX();
					double y = location.getBlockY();
					double z = location.getBlockZ();
					String message = "&aKoordynaty &e{0} &ato &eX&7:&f{1}&8, &eY&7:&f{2}&8, &eZ&7:&f{3}";
					String formattedMessage = MessageFormat.format(message, member.getName(), x, y, z);
					MessageManager.sendMessage(formattedMessage, player);
				} else {
					MessageManager.sendMessage("&cGracz &e"+args[2]+" &cnie należy do twojej drużyny.", player);
				}
			} else {
				MessageManager.sendMessage("&cGracz &e"+args[2]+" &cnie jest online.", player);
			}
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
