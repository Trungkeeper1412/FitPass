package com.ks.fitpass.employee.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CheckOutFlexibleDTO {
    private int orderDetailId;
    private String username;
    private String productName;
    private String phoneNumber;
    private double pricePerHours;
    private double price;
    private String imageUrl;
}
