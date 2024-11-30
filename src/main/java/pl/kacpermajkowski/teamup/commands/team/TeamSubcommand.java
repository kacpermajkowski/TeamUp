package pl.kacpermajkowski.teamup.commands.team;

import pl.kacpermajkowski.teamup.basic.TeamRole;

public interface TeamSubcommand extends Subcommand {
    TeamRole getMinimumRequiredTeamRole();
}
