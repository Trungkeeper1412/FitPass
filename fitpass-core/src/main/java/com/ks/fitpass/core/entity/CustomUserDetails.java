package com.ks.fitpass.core.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

@Getter
@Setter
@ToString
public class CustomUserDetails extends org.springframework.security.core.userdetails.User {

    private int id;
    private long userCreateTime;
    private boolean userDeleted;

    public CustomUserDetails(String username, String password, Collection<? extends GrantedAuthority> authorities,
                             int id,
                             long userCreateTime, boolean userDeleted) {
        super(username, password, authorities);
        this.id = id;
        this.userCreateTime = userCreateTime;
        this.userDeleted = userDeleted;
    }
}
