package pl.kacpermajkowski.teamup.basic;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class Team {

    private final HashMap<UUID, TeamRole> memberList = new HashMap<>();
    private final List<UUID> invitedPlayers = new ArrayList<>();
    private final List<String> notes = new ArrayList<>();
    private final HashMap<String, Location> coords = new HashMap<>();

    private final String name;
    private final UUID leaderUUID;
    private final String prefix;

    public Team(Player leader, String name, String prefix) {
        this.name = name;
        this.prefix = "&8(&r"+prefix+"&8)&r ";
        this.leaderUUID = leader.getUniqueId();
        this.memberList.put(this.leaderUUID, TeamRole.LEADER);
    }

    public void addTeamMember(UUID playerUUID) {
        this.memberList.put(playerUUID, TeamRole.MEMBER);
    }
    public void removeTeamMember(UUID playerUUID) {
        this.memberList.remove(playerUUID);
    }

    public void invitePlayer(UUID playerUUID) {
        this.invitedPlayers.add(playerUUID);
    }
    public void revokePlayerInvitation(UUID playerUUID) {
        this.invitedPlayers.remove(playerUUID);
    }

    public boolean isPlayerInvited(UUID playerUUID) {
        return this.invitedPlayers.contains(playerUUID);
    }
    public List<UUID> getInvitedPlayers() {
        return this.invitedPlayers;
    }

    public String getName() {
        return this.name;
    }
    public String getPrefix() {
        return this.prefix;
    }

    public UUID getLeaderUUID() {
        return this.leaderUUID;
    }
    public HashMap<UUID, TeamRole> getMemberList() {
        return this.memberList;
    }

    public boolean isPlayerMember(UUID playerUUID) {
        return (this.memberList.get(playerUUID) != null);
    }
    public TeamRole getPlayerRole(UUID playerUUID) {
        return this.memberList.get(playerUUID);
    }

    public List<String> getNotes() {
        return notes;
    }
    public HashMap<String, Location> getCoords() {
        return coords;
    }
}
