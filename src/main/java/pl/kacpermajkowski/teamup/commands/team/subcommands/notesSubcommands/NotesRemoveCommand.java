package pl.kacpermajkowski.teamup.commands.team.subcommands.notesSubcommands;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.kacpermajkowski.teamup.basic.Team;
import pl.kacpermajkowski.teamup.basic.TeamRole;
import pl.kacpermajkowski.teamup.commands.team.TeamSubcommand;
import pl.kacpermajkowski.teamup.events.TeamNotesRemoveEvent;
import pl.kacpermajkowski.teamup.managers.MessageManager;
import pl.kacpermajkowski.teamup.managers.TeamManager;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class NotesRemoveCommand implements TeamSubcommand{

	private final String name = "remove";
	private final String description = "pozwala usunąć notatkę z listy";
	private final String syntax = "/team notes remove <id notatki>";
	private final String permission = "teamup.team.basic";
	private final TeamRole permittedTeamRole = TeamRole.MEMBER;

	public void execute(Player player, String[] args) {
		if(args.length == 3){
			int noteID;
			try {
				noteID = Integer.parseInt(args[2]);
			} catch (NumberFormatException e){
				MessageManager.sendMessage("&cID notatki musi być liczbą!", player);
				return;
			}
			Team team = TeamManager.getPlayerTeam(player);
			if(team.getNotes().size() >= noteID && noteID > 0) {
				Bukkit.getServer().getPluginManager().callEvent(new TeamNotesRemoveEvent(player, noteID));
				MessageManager.sendMessage("&aPomyślnie usunąłeś z listy notatkę o ID &e" + args[2] + "&a.", player);
			} else {
				MessageManager.sendMessage("&cNotatka o ID &e"+noteID+" &cnie jest na liście.", player);
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
					for (UUID invitedPlayerUUID : team.getInvitedPlayers()) {
						OfflinePlayer invitedPlayer = Bukkit.getOfflinePlayer(invitedPlayerUUID);
						autocomParams.add(invitedPlayer.getName());
					}
				}

			}
		}
		return autocomParams;
	}
}
