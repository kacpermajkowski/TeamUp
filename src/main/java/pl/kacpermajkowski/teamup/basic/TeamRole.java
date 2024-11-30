package pl.kacpermajkowski.teamup.basic;

public enum TeamRole {
    LEADER(4),
    COLEADER(3),
    ELDER(2),
    MEMBER(1);

    private final int permissionRank;

    TeamRole(int permissionRank) {
        this.permissionRank = permissionRank;
    }

    public int getPermissionRank() {
        return this.permissionRank;
    }
}
