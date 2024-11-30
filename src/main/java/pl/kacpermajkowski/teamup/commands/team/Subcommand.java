package pl.kacpermajkowski.teamup.commands.team;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public interface Subcommand {
    void execute(Player paramPlayer, String[] paramArrayOfString);

    String getName();
    String getDescription();
    String getSyntax();
    String getPermission();

    List<String> getTabCompletion(CommandSender sender, Command command, String alias, String[] args);
}
