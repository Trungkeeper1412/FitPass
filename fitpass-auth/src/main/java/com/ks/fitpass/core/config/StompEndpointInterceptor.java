package com.ks.fitpass.core.config;

import com.ks.fitpass.core.entity.User;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import java.util.Map;

@Component
public class StompEndpointInterceptor implements HandshakeInterceptor {

    private final Logger LOG = LoggerFactory.getLogger(StompEndpointInterceptor.class);

    @Override
    public boolean beforeHandshake(ServerHttpRequest request, @NonNull ServerHttpResponse response,
                                   @NonNull WebSocketHandler wsHandler, @NonNull Map<String, Object> attributes){
        // Log important information before the handshake
        LOG.info("Before handshake - Request URI: " + request.getURI());
        LOG.info("Before handshake - Headers: " + request.getHeaders());
        LOG.info("Before handshake - Session attributes: " + attributes);

        if (request instanceof ServletServerHttpRequest servletRequest) {
            try {
                HttpSession session = servletRequest.getServletRequest().getSession(false);
                if (session != null) {
                    User user = (User) session.getAttribute("userInfo");
                    if (user != null) {
                        LOG.info(String.valueOf(user.getUserId()));
                    } else {
                        LOG.warn("No user found in session.");
                    }
                } else {
                    LOG.warn("No active session found.");
                }
            } catch (Exception e) {
                LOG.error("Error during handshake", e);
            }
        }
        return true;
    }

    @Override
    public void afterHandshake(ServerHttpRequest request, @NonNull ServerHttpResponse response,
                               @NonNull WebSocketHandler wsHandler, Exception exception) {
        // Log important information after the handshake
        LOG.info("After handshake - Request URI: " + request.getURI());
        LOG.info("After handshake - Headers: " + request.getHeaders());

        if (request instanceof ServletServerHttpRequest servletRequest) {
            try {
                HttpSession session = servletRequest.getServletRequest().getSession(false);
                if (session != null) {
                    User user = (User) session.getAttribute("userInfo");
                    if (user != null) {
                        LOG.info(String.valueOf(user.getUserId()));
                    } else {
                        LOG.warn("No user found in session.");
                    }
                } else {
                    LOG.warn("No active session found.");
                }
            } catch (Exception e) {
                LOG.error("Error after handshake", e);
            }
        }
    }
}
