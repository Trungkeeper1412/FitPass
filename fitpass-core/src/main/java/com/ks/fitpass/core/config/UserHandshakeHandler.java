package com.ks.fitpass.core.config;

import com.ks.fitpass.core.entity.User;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.DefaultHandshakeHandler;

import java.security.Principal;
import java.util.Map;

@Component
public class UserHandshakeHandler extends DefaultHandshakeHandler {

    @Override
    protected Principal determineUser(@NonNull ServerHttpRequest request, @NonNull WebSocketHandler wsHandler, @NonNull Map<String, Object> attributes) {
        try {
            if (request instanceof ServletServerHttpRequest servletRequest) {
                HttpSession session = servletRequest.getServletRequest().getSession(false);
                if (session != null) {
                    User user = (User) session.getAttribute("userInfo");
                    if (user != null) {
                        return () -> String.valueOf(user.getUserId()); //Returned Principal
                    } else {
                        throw new Exception("No user found in session.");
                    }
                } else {
                    throw new Exception("No active session found.");
                }
            } else {
                throw new Exception("Request is not an instance of ServletServerHttpRequest.");
            }
        } catch (Exception e) {
            throw new RuntimeException("Error during determineUser", e);
        }
    }
}

