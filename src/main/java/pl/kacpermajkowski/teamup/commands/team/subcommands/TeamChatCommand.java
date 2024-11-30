package pl.kacpermajkowski.teamup.commands.team.subcommands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.kacpermajkowski.teamup.basic.Team;
import pl.kacpermajkowski.teamup.basic.TeamRole;
import pl.kacpermajkowski.teamup.commands.team.TeamSubcommand;
import pl.kacpermajkowski.teamup.managers.MessageManager;
import pl.kacpermajkowski.teamup.managers.TeamManager;

import java.util.ArrayList;
import java.util.List;

public class TeamChatCommand implements TeamSubcommand {
	private final String name = "chat";
	private final String description = "pozwala włączyć i wyłączyć tryb czatu drużynowego";
	private final String syntax = "/team chat";
	private final String permission = "teamup.team.chat";
	private final TeamRole minimumRequiredTeamRole = TeamRole.MEMBER;

	public void execute(Player player, String[] args) {
		Team team = TeamManager.getPlayerTeam(player);
		boolean isTeamChatEnabled = MessageManager.playersWithTeamChatActive.contains(player.getUniqueId());
		if(isTeamChatEnabled){
			MessageManager.sendMessage("&fTryb czatu teamowego został &cWYŁĄCZONY&f.", player);
			MessageManager.playersWithTeamChatActive.remove(player.getUniqueId());
		} else {
			MessageManager.sendMessage("&fTryb czatu teamowego został &aWŁĄCZONY&f.", player);
			MessageManager.playersWithTeamChatActive.add(player.getUniqueId());
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
		return new ArrayList<>();
	}
}
