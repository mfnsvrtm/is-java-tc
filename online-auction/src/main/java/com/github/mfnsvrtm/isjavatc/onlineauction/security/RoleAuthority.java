package com.github.mfnsvrtm.isjavatc.onlineauction.security;

import com.github.mfnsvrtm.isjavatc.onlineauction.entity.Group;
import org.springframework.security.core.GrantedAuthority;

public class RoleAuthority implements GrantedAuthority {

    private final String authority;

    public RoleAuthority(String role) {
        this.authority = "ROLE_" + role.toUpperCase();
    }

    public RoleAuthority(Group group) {
        this(group.getName());
    }

    @Override
    public String getAuthority() {
        return authority;
    }

}
