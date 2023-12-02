package com.ks.fitpass.core.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    private final StompEndpointInterceptor stompEndpointInterceptor;

    private final StompChannelInterceptor stompChannelInterceptor;

    private final UserHandshakeHandler userHandshakeHandler;


    public WebSocketConfig(StompEndpointInterceptor stompEndpointInterceptor, StompChannelInterceptor stompChannelInterceptor, UserHandshakeHandler userHandshakeHandler) {
        this.stompEndpointInterceptor = stompEndpointInterceptor;
        this.stompChannelInterceptor = stompChannelInterceptor;
        this.userHandshakeHandler = userHandshakeHandler;
    }

    @Override
    public void registerStompEndpoints(final StompEndpointRegistry registry) {
        registry.addEndpoint("/ws")
                .setHandshakeHandler(userHandshakeHandler)
                .addInterceptors(stompEndpointInterceptor)
                .withSockJS();
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        config.enableSimpleBroker("/all", "/specific");
        config.setApplicationDestinationPrefixes("/app");
        config.setUserDestinationPrefix("/user");
    }

    @Override
    public void configureClientInboundChannel(ChannelRegistration registration) {
        registration.interceptors(stompChannelInterceptor);
    }
}
