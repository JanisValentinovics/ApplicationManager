package lv.team3.botcovidlab.manager.model;

public enum Permission {
    USERS_READ("reservations:read"),
    USERS_WRITE("reservations:write");

    private final String permission;

    Permission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
