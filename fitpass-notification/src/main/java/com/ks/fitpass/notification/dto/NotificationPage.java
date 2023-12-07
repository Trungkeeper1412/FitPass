package com.ks.fitpass.notification.dto;

import com.ks.fitpass.notification.entity.Notification;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NotificationPage {
    private List<Notification> notifications;
    private int totalPages;
    private int currentPage;
}
