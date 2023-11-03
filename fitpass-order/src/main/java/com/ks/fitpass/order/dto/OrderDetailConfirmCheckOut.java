package com.ks.fitpass.order.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class OrderDetailConfirmCheckOut {
    private int orderDetailId;
    private int historyCheckInId;
    private int notificationId;
    private String departmentName;
    private String gymPlanName;
    private double pricePerHours;
    private int durationHavePractice;
    private double creditInWallet;
    private double creditNeedToPay;
    private double creditAfterPay;
    private Timestamp checkOutTime;

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        OrderDetailConfirmCheckOut other = (OrderDetailConfirmCheckOut) obj;
        return Objects.equals(departmentName, other.departmentName)
                && Objects.equals(gymPlanName, other.gymPlanName)
                && Double.compare(pricePerHours, other.pricePerHours) == 0;
    }
}
