package com.ks.fitpass.employee.dto;

import com.ks.fitpass.notification.entity.Notification;
import com.ks.fitpass.order.dto.OrderDetailConfirmCheckOut;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ConfirmCheckOutDTO {
    private OrderDetailConfirmCheckOut orderDetailConfirmCheckOut;
    private DataSendCheckOutFlexibleDTO dataSendCheckOutFlexible;
    Notification notification;
}
