package com.ks.fitpass.notification.service;

import com.ks.fitpass.notification.entity.CheckInCheckOutResponse;
import com.ks.fitpass.notification.entity.Notification;

public interface WebSocketService {
    void notifyFrontend(Notification notification);

    void notifyUser(int id, Notification notification);

    void notifyEmployee(int id, Notification notification);

    void sendPrivateNotification(final String userId);
    void sendGlobalNotification();
}
