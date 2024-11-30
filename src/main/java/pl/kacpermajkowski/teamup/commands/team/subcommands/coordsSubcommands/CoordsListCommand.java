package pl.kacpermajkowski.teamup.commands.team.subcommands.coordsSubcommands;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.kacpermajkowski.teamup.basic.Team;
import pl.kacpermajkowski.teamup.basic.TeamRole;
import pl.kacpermajkowski.teamup.commands.team.TeamSubcommand;
import pl.kacpermajkowski.teamup.managers.TeamManager;
import pl.kacpermajkowski.teamup.utils.ChatUtils;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CoordsListCommand implements TeamSubcommand {

	private final String name = "list";
	private final String description = "pozwala wyświetlić listę zapisanych koordynatów";
	private final String syntax = "/team coords list";
	private final String permission = "teamup.team.basic";
	private final TeamRole permittedTeamRole = TeamRole.MEMBER;

	public void execute(Player player, String[] args) {
		if(args.length == 2) {
			Team team = TeamManager.getPlayerTeam(player);
			HashMap<String, Location> coords = team.getCoords();

			String prefixMessage = "&8[&2&m--------&a&l»»&r &bLista koordów &a&m&l««&2&m--------&8]";
			String colouredPrefixMessage = ChatUtils.fixColors(prefixMessage);
			player.sendMessage(colouredPrefixMessage);

			for(String coordsName:coords.keySet()){
				Location location = coords.get(coordsName);
				double x = location.getBlockX();
				double y = location.getBlockY();
				double z = location.getBlockZ();
				String message = " &e{0} &8-> &eX&7:&f{1}&8, &eY&7:&f{2}&8, &eZ&7:&f{3}";
				String formattedMessage = MessageFormat.format(message, coordsName, x, y, z);
				player.sendMessage(ChatUtils.fixColors(formattedMessage));
			}

			player.sendMessage(colouredPrefixMessage);
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
