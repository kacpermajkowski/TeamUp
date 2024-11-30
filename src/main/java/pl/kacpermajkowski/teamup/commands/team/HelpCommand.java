package pl.kacpermajkowski.teamup.commands.team;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.kacpermajkowski.teamup.managers.MessageManager;
import pl.kacpermajkowski.teamup.utils.ChatUtils;

import java.text.MessageFormat;
import java.util.List;

public class HelpCommand implements Subcommand {
    private final String name = "help";

    private final String description = "wyświetla listę dostępnych komend";

    private final String syntax;

    private final String permission;

    private final List<? extends Subcommand> subcommands;

    public HelpCommand(String syntax, String permission, List<? extends Subcommand> subcommands) {
        this.syntax = syntax;
        this.permission = permission;
        this.subcommands = subcommands;
    }

    public void execute(Player player, String[] args) {
        String prefixMessage = MessageFormat.format("&8[&2&m--------&a&l»»&r {0} &a&m&l««&2&m--------&8]", MessageManager.getPrefix());
        String colouredPrefixMessage = ChatUtils.fixColors(prefixMessage);
        player.sendMessage(colouredPrefixMessage);
        for (Subcommand subcommand : this.subcommands) {
            String formattedMessage = MessageFormat.format(" &e{0} &8- &7{1}", subcommand.getSyntax(), subcommand.getDescription());
            String colouredMessage = ChatUtils.fixColors(formattedMessage);
            player.sendMessage(colouredMessage);
        }
        player.sendMessage(colouredPrefixMessage);
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

    public List<String> getTabCompletion(CommandSender sender, Command command, String alias, String[] args) {
        return null;
    }

}
