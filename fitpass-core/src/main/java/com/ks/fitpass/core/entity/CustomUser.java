package com.ks.fitpass.core.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

@Getter
@Setter
@ToString
public class CustomUser extends org.springframework.security.core.userdetails.User {

    private int id;
    private String customUserPassword;
    private long userCreateTime;
    private boolean userDeleted;


    public CustomUser(String username, String password, Collection<? extends GrantedAuthority> authorities, int id, String customUserPassword, long userCreateTime, boolean userDeleted) {
        super(username, password, authorities);
        this.id = id;
        this.customUserPassword = customUserPassword;
        this.userCreateTime = userCreateTime;
        this.userDeleted = userDeleted;
    }
}
