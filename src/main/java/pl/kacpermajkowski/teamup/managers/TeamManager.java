package pl.kacpermajkowski.teamup.managers;

import org.bukkit.entity.Player;
import pl.kacpermajkowski.teamup.basic.Team;
import pl.kacpermajkowski.teamup.basic.TeamRole;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class TeamManager {

    private static final List<Team> teamList = new ArrayList<>();
    private static final HashMap<UUID, List<String>> invitations = new HashMap<>();

    public static Team createTeam(Player owner, String teamName, String teamPrefix) {
        Team createdTeam = new Team(owner, teamName, teamPrefix);
        teamList.add(createdTeam);
        return createdTeam;
    }

    public static void disbandTeam(Team team) {
        teamList.remove(team);
    }

    public static Team getPlayerTeam(Player player) {
        return getPlayerTeam(player.getUniqueId());
    }

    public static Team getPlayerTeam(UUID playerUUID) {
        for (Team team : teamList) {
            for (UUID uuid : team.getMemberList().keySet()) {
                if (uuid.equals(playerUUID))
                    return team;
            }
        }
        return null;
    }

    public static TeamRole getPlayerTeamRole(UUID playerUUID) {
        for (Team team : teamList) {
            for (UUID uuid : team.getMemberList().keySet()) {
                if (uuid.equals(playerUUID))
                    return team.getPlayerRole(playerUUID);
            }
        }
        return null;
    }

    public static Team getTeamByName(String teamName) {
        for (Team team : teamList) {
            if (team.getName().equals(teamName))
                return team;
        }
        return null;
    }

    public static HashMap<UUID, List<String>> getInvitations() {
        return invitations;
    }
    public static void addInvitation(UUID uuid, String teamName){
        List<String> invitingTeams = invitations.get(uuid);
        if(invitingTeams == null){
            invitations.put(uuid, new ArrayList<>());
        }
        invitingTeams = invitations.get(uuid);
        invitingTeams.add(teamName);
    }
    public static void revokeInvitation(UUID uuid, String teamName){
        invitations.get(uuid).remove(teamName);
    }
}
