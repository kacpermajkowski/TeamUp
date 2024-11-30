package pl.kacpermajkowski.teamup.commands.team.subcommands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.kacpermajkowski.teamup.basic.TeamRole;
import pl.kacpermajkowski.teamup.commands.team.HelpCommand;
import pl.kacpermajkowski.teamup.commands.team.Subcommand;
import pl.kacpermajkowski.teamup.commands.team.TeamSubcommand;
import pl.kacpermajkowski.teamup.commands.team.subcommands.notesSubcommands.NotesAddCommand;
import pl.kacpermajkowski.teamup.commands.team.subcommands.notesSubcommands.NotesListCommand;
import pl.kacpermajkowski.teamup.commands.team.subcommands.notesSubcommands.NotesRemoveCommand;
import pl.kacpermajkowski.teamup.managers.MessageManager;
import pl.kacpermajkowski.teamup.managers.TeamManager;

import java.util.ArrayList;
import java.util.List;

public class TeamNotesCommand implements TeamSubcommand {
    private final String name = "notes";
    private final String description = "pomoc dotycząca podkomendy /team notes";
    private final String syntax = "/team notes";
    private final String permission = "teamup.team.notes";
    private final TeamRole minimumRequiredTeamRole = TeamRole.MEMBER;
    private final List<TeamSubcommand> subcommands = new ArrayList<>();

    public TeamNotesCommand() {
        this.subcommands.add(new NotesAddCommand());
        this.subcommands.add(new NotesRemoveCommand());
        this.subcommands.add(new NotesListCommand());
    }

    public void execute(Player player, String[] args) {
        HelpCommand helpCommand = new HelpCommand("/team notes help", permission, this.subcommands);
        if (args.length < 2) {
            helpCommand.execute(player, args);
        } else {
            TeamSubcommand subcommand = getSubcommandByName(args[1]);
            if(subcommand != null) {
                TeamRole requiredPlayerRole = subcommand.getMinimumRequiredTeamRole();
                TeamRole playerTeamRole = TeamManager.getPlayerTeamRole(player.getUniqueId());
                if (playerTeamRole.getPermissionRank() >= requiredPlayerRole.getPermissionRank()) {
                    subcommand.execute(player, args);
                } else {
                    MessageManager.sendMessage("&cNie masz dość wysokiej rangi, aby to zrobić. Wymagana ranga to &e" + requiredPlayerRole.name(), player);
                }
            } else {
                helpCommand.execute(player, args);
            }
        }
    }

    private TeamSubcommand getSubcommandByName(String subcommandName) {
        for (TeamSubcommand ts : this.subcommands) {
            if (subcommandName.equalsIgnoreCase(ts.getName())) {
                return ts;
            }
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
        return this.minimumRequiredTeamRole;
    }

    public List<String> getTabCompletion(CommandSender sender, Command command, String alias, String[] args) {
        List<String> autocomParams = new ArrayList<>();
        if(args.length == 3){
            for(Subcommand subcommand:subcommands){
                autocomParams.add(subcommand.getName());
            }
        }
        return autocomParams;
    }
}
