package com.ks.fitpass.notification.service;

public interface WebSocketService {
    void notifyFrontend(String message);

    void notifyUser(final String id, final String message);
}
