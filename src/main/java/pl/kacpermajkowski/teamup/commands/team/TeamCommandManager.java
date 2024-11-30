package pl.kacpermajkowski.teamup.commands.team;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import pl.kacpermajkowski.teamup.basic.TeamRole;
import pl.kacpermajkowski.teamup.commands.team.subcommands.*;
import pl.kacpermajkowski.teamup.managers.MessageManager;
import pl.kacpermajkowski.teamup.managers.TeamManager;

import java.util.ArrayList;
import java.util.List;

public class TeamCommandManager implements TabExecutor {
    private final List<TeamSubcommand> subcommands = new ArrayList<>();

    public TeamCommandManager() {
        this.subcommands.add(new TeamChatCommand());
        this.subcommands.add(new TeamCreateCommand());
        this.subcommands.add(new TeamDisbandCommand());
        this.subcommands.add(new TeamInfoCommand());
        this.subcommands.add(new TeamInviteCommand());
        this.subcommands.add(new TeamCancelintiveCommand());
        this.subcommands.add(new TeamJoinCommand());
        this.subcommands.add(new TeamKickCommand());
        this.subcommands.add(new TeamLeaveCommand());
        this.subcommands.add(new TeamNotesCommand());
        this.subcommands.add(new TeamCoordsCommand());
    }

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            HelpCommand helpCommand = new HelpCommand("/team help", "teamup.team.basic", this.subcommands);
            if (args.length == 0) {
                helpCommand.execute(player, args);
            } else {
                args = filterUnwantedChars(args);
                TeamSubcommand ts = getSubcommandByName(args[0]);
                if (ts != null) {
                    if (player.hasPermission(ts.getPermission())) {
                        TeamRole requiredPlayerRole = ts.getMinimumRequiredTeamRole();
                        TeamRole playerTeamRole = TeamManager.getPlayerTeamRole(player.getUniqueId());
                        if (requiredPlayerRole == null) {
                            if (playerTeamRole == null) {
                                ts.execute(player, args);
                            } else {
                                MessageManager.sendMessage("&cAby wykonać tą komendę musisz najpierw opuścić drużynę.", player);
                            }
                        } else if (playerTeamRole != null) {
                            if (playerTeamRole.getPermissionRank() >= requiredPlayerRole.getPermissionRank()) {
                                ts.execute(player, args);
                            } else {
                                MessageManager.sendMessage("&cNie masz dość wysokiej rangi, aby to zrobić. Wymagana ranga to &e" + requiredPlayerRole.name(), player);
                            }
                        } else {
                            MessageManager.sendMessage("&cMusisz być członkiem drużyny aby wykonać tą komendę.", player);
                        }
                    } else {
                        MessageManager.sendMessage("&cNie masz uprawnień do użycia tej komendy (&e" + ts.getPermission() + "&c).", player);
                    }
                    return true;
                } else {
                    helpCommand.execute(player, args);
                }
            }
        }
        return true;
    }

    private String[] filterUnwantedChars(String[] args) {
        String[] filteredArgs = new String[args.length];
        for(int i = 0; i < args.length; i++){
            filteredArgs[i]=args[i].replaceAll("[^a-zA-Z0-9]/-&_","");
        }
        return filteredArgs;
    }

    private TeamSubcommand getSubcommandByName(String subcommandName) {
        for (TeamSubcommand ts : this.subcommands) {
            if (subcommandName.equalsIgnoreCase(ts.getName())) {
                return ts;
            }
        }
        return null;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        List<String> autocomParams = new ArrayList<>();
        if(sender instanceof Player) {
            Player player = (Player) sender;
            if (args.length == 1) {
                for (TeamSubcommand subcommand : subcommands) {
                    TeamRole playerTeamRole = TeamManager.getPlayerTeamRole(player.getUniqueId());
                    TeamRole requiredTeamRole = subcommand.getMinimumRequiredTeamRole();
                    if(playerTeamRole == null){
                        if(requiredTeamRole == null){
                            autocomParams.add(subcommand.getName());
                        }
                    } else {
                        if (requiredTeamRole != null) {
                            if (playerTeamRole.getPermissionRank() >= requiredTeamRole.getPermissionRank()) {
                                autocomParams.add(subcommand.getName());
                            }
                        }
                    }
                }

            } else if (args.length >= 2) {
                TeamSubcommand teamSubcommand = getSubcommandByName(args[1]);
                if (teamSubcommand != null) {
                    autocomParams = teamSubcommand.getTabCompletion(sender, command, alias, args);
                }
            }
        }
        return autocomParams;
    }
}
