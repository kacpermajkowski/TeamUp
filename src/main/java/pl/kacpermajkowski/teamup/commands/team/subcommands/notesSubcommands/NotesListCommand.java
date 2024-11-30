package pl.kacpermajkowski.teamup.commands.team.subcommands.notesSubcommands;

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
import java.util.List;

public class NotesListCommand implements TeamSubcommand{

	private final String name = "list";
	private final String description = "pozwala wyświelić listę notatek";
	private final String syntax = "/team notes list";
	private final String permission = "teamup.team.basic";
	private final TeamRole permittedTeamRole = TeamRole.MEMBER;

	public void execute(Player player, String[] args) {
		if(args.length == 2) {
			Team team = TeamManager.getPlayerTeam(player);
			List<String> notes = team.getNotes();

			String prefixMessage = "&8[&2&m--------&a&l»»&r &bNotatki &a&m&l««&2&m--------&8]";
			String colouredPrefixMessage = ChatUtils.fixColors(prefixMessage);
			player.sendMessage(colouredPrefixMessage);

			for(int i = 0; i < notes.size(); i++){
				String message = " &e{0}&8. &f{1}";
				String formattedMessage = MessageFormat.format(message, (i+1), notes.get(i));
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
