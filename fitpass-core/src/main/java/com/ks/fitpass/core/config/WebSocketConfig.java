package com.ks.fitpass.core.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.session.web.socket.config.annotation.AbstractSessionWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.*;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.server.support.DefaultHandshakeHandler;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        config.enableSimpleBroker("/all","/specific");
        config.setApplicationDestinationPrefixes("/app");
    }

    @Override
    public void registerStompEndpoints(final StompEndpointRegistry registry) {
        registry.addEndpoint("/ws")
                .setHandshakeHandler(new UserHandshakeHandler())
                .withSockJS();
    }
}
