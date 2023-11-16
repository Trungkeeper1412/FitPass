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

    public static String generateRandomPassword() {
        // Độ dài của mật khẩu ngẫu nhiên
        int passwordLength = 10;

        // Ký tự có thể xuất hiện trong mật khẩu
        String allowedChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

        StringBuilder password = new StringBuilder();

        for (int i = 0; i < passwordLength; i++) {
            // Chọn ngẫu nhiên một ký tự từ chuỗi allowedChars
            int randomIndex = (int) (Math.random() * allowedChars.length());
            char randomChar = allowedChars.charAt(randomIndex);

            // Thêm ký tự ngẫu nhiên vào mật khẩu
            password.append(randomChar);
        }

        return password.toString();
    }
}
