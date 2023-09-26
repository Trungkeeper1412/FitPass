package com.ks.fitpass.web.util;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public class WebUtil {

    public static String toString(User user) {
        StringBuilder sb = new StringBuilder();

        sb.append("UserName: ").append(user.getUsername());

        Collection<GrantedAuthority> authorities = user.getAuthorities();
        if (authorities != null && !authorities.isEmpty()) {
            sb.append(" (");
            boolean first = true;
            for (GrantedAuthority a : authorities) {
                if (first) {
                    sb.append(a.getAuthority());
                    first = false;
                } else {
                    sb.append(", ").append(a.getAuthority());
                }
            }
            sb.append(")");
        }
        return sb.toString();
    }

    public static int getCoin(int coin) {
        return switch (coin) {
            case 1 -> 1;
            case 2 -> 3;
            case 3 -> 5;
            case 4 -> 7;
            case 5 -> 9;
            case 6 -> 11;
            case 7 -> 13;
            case 8 -> 15;
            case 9 -> 17;
            case 10 -> 19;
            default -> 0;
        };
    }
}
