package pl.kacpermajkowski.teamup.commands.team.subcommands;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.kacpermajkowski.teamup.basic.Team;
import pl.kacpermajkowski.teamup.basic.TeamRole;
import pl.kacpermajkowski.teamup.commands.team.TeamSubcommand;
import pl.kacpermajkowski.teamup.managers.MessageManager;
import pl.kacpermajkowski.teamup.managers.TeamManager;
import pl.kacpermajkowski.teamup.utils.ChatUtils;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TeamInfoCommand implements TeamSubcommand {
    private final String name = "info";
    private final String description = "wyświetla informacje od team'ie, w którym jesteś";
    private final String syntax = "/team info";
    private final String permission = "teamup.team.basic";
    private final TeamRole minimumRequiredTeamRole = TeamRole.MEMBER;

    public void execute(Player player, String[] args) {
        Team team = TeamManager.getPlayerTeam(player);
        if (team != null) {
            String prefixMessage = MessageFormat.format("&8[&2&m--------&a&l»»&r {0} &a&m&l««&2&m--------&8]", MessageManager.getPrefix());
            String colouredPrefixMessage = ChatUtils.fixColors(prefixMessage);
            player.sendMessage(colouredPrefixMessage);
            player.sendMessage(ChatUtils.fixColors(" &eNazwa drużyny&7: &r" +team.getName()));
            player.sendMessage(ChatUtils.fixColors(" &ePrefix drużyny&7: &r" +team.getPrefix()));
            player.sendMessage(ChatUtils.fixColors(" &eLider&7: &r" +getNickFromUUID(team.getLeaderUUID())));
            player.sendMessage(ChatUtils.fixColors(" &eCzłonkowie&7: &r" +getNicksFromUUIDs(team.getMemberList().keySet().stream().toList())));
            player.sendMessage(ChatUtils.fixColors(" &eZaproszeni&7: &r" +getNicksFromUUIDs(team.getInvitedPlayers())));
            player.sendMessage(colouredPrefixMessage);
        }
    }

    private String getNicksFromUUIDs(List<UUID> playerUUIDs) {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i<playerUUIDs.size(); i++){
            UUID uuid = playerUUIDs.get(i);
            OfflinePlayer player = Bukkit.getOfflinePlayer(uuid);
            if(player.isOnline()){
                sb.append("&a");
                sb.append(player.getName());
            } else {
                sb.append("&c");
                sb.append(player.getName());
            }
            if(playerUUIDs.size()-1 != i)sb.append("&r, ");
        }
        return sb.toString();
    }

    private String getNickFromUUID(UUID uuid){
        OfflinePlayer player = Bukkit.getOfflinePlayer(uuid);
        if(player.isOnline()){
            return "&a"+player.getName();
        } else {
            return "&c"+player.getName();
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
