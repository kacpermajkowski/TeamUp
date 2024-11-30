package pl.kacpermajkowski.teamup.commands.team.subcommands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.kacpermajkowski.teamup.basic.TeamRole;
import pl.kacpermajkowski.teamup.commands.team.TeamSubcommand;

import java.util.ArrayList;
import java.util.List;

public class TeamAlertCommand implements TeamSubcommand {
    private final String name = "alert";
    private final String description = "pomoc dotycząca podkomendy /team alert";
    private final String syntax = "/team alert";
    private final String permission = "teamup.team.alert";
    private final TeamRole minimumRequiredTeamRole = TeamRole.MEMBER;

    public void execute(Player player, String[] args) {

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
