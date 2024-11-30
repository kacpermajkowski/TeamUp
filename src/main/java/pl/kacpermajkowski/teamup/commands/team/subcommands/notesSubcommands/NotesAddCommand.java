package pl.kacpermajkowski.teamup.commands.team.subcommands.notesSubcommands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.kacpermajkowski.teamup.basic.Team;
import pl.kacpermajkowski.teamup.basic.TeamRole;
import pl.kacpermajkowski.teamup.commands.team.TeamSubcommand;
import pl.kacpermajkowski.teamup.events.TeamNotesAddEvent;
import pl.kacpermajkowski.teamup.managers.MessageManager;
import pl.kacpermajkowski.teamup.managers.TeamManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class NotesAddCommand implements TeamSubcommand{

	private final String name = "add";
	private final String description = "pozwala dodać notatkę do listy";
	private final String syntax = "/team notes add <treść notatki>";
	private final String permission = "teamup.team.basic";
	private final TeamRole permittedTeamRole = TeamRole.MEMBER;

	public void execute(Player player, String[] args) {
		if(args.length >= 3){
			Team team = TeamManager.getPlayerTeam(player);
			StringBuilder sb = new StringBuilder();
			for(int i = 2; i < args.length; i++){
				sb.append(args[i]);
				sb.append(" ");
			}
			Bukkit.getServer().getPluginManager().callEvent(new TeamNotesAddEvent(player, sb.toString()));
			MessageManager.sendMessage("&aPomyślnie dodałeś notatkę!", player);
		} else {
			MessageManager.sendSyntaxMessage(syntax, player);
		}
	}

	private Integer getAvailableID(HashMap<Integer, String> notes) {
		for(int i = 1; i <= notes.size()+1; i++){
			if(notes.get(i) == null) return i;
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
		return this.permittedTeamRole;
	}

	public List<String> getTabCompletion(CommandSender sender, Command command, String alias, String[] args) {
		return new ArrayList<>();
	}
}
