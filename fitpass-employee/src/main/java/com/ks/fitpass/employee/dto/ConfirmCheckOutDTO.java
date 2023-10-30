package com.ks.fitpass.employee.dto;

import com.ks.fitpass.notification.entity.Notification;
import com.ks.fitpass.order.dto.OrderDetailConfirmCheckOut;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ConfirmCheckOutDTO {
    OrderDetailConfirmCheckOut orderDetailConfirmCheckOut;
    Notification notification;
}
