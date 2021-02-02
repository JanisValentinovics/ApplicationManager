package lv.team3.botcovidlab.manager.model;

import lombok.Getter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

@Getter
public enum Roles {
    USER(Set.of(Permission.USERS_READ)),
    ADMIN(Set.of(Permission.USERS_WRITE,Permission.USERS_READ));

    private final Set<Permission> permissionSet;

    Roles(Set<Permission> permissions) {
        this.permissionSet=permissions;
    }
    public Set<SimpleGrantedAuthority> getAuthorities(){
        return getPermissionSet().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());
    }

}
