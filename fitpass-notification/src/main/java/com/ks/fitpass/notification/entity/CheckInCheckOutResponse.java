package com.ks.fitpass.notification.entity;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class CheckInCheckOutResponse {
    private int userId;
    private int notificationId;
    private boolean confirmed;
}
