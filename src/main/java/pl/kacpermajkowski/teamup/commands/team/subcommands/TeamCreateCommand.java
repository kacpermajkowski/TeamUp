package pl.kacpermajkowski.teamup.commands.team.subcommands;

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

public class TeamCreateCommand implements TeamSubcommand {
	private final String name = "create";
	private final String description = "pozwala na utworzenie team'u";
	private final String syntax = "/team create <nazwa drużyny> <prefix drużyny>";
	private final String permission = "teamup.team.manage";
	private final TeamRole minimumRequiredTeamRole = null;

	public void execute(Player player, String[] args) {
		if (args.length >= 3) {
			if (TeamManager.getTeamByName(args[1]) == null) {
				StringBuilder sb = new StringBuilder();
				for (int i = 2; i < args.length; i++) {
					sb.append(args[i]);
					if (i != args.length - 1) sb.append(" ");
				}
				args[1] = filterUnwantedChars(args[1]);
				Team createdTeam = TeamManager.createTeam(player, args[1], sb.toString());
				String formattedMessage = MessageFormat.format("&aPomyślnie utworzono drużynę o nazwie &e{0} &ao prefixie &r{1}&a.", createdTeam.getName(), createdTeam.getPrefix());
				MessageManager.sendMessage(formattedMessage, player);
			} else {
				String formattedMessage = MessageFormat.format("&cIstnieje już drużyna o nazwie &e{0}&c. Wybierz inną nazwę.", args[1]);
				MessageManager.sendMessage(formattedMessage, player);
			}
		} else {
			MessageManager.sendSyntaxMessage(this.syntax, player);
		}
	}

	private String filterUnwantedChars(String arg) {
		return arg.replaceAll("[^a-zA-Z0-9]/-_","");
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
		if (sender instanceof Player) {
		  if(args.length == 2) {
            autocomParams.add("<nazwa_teamu>");
          } else if(args.length == 3){
		    autocomParams.add("<prefix_teamu>");
          }
		}
		return autocomParams;
	}
}
