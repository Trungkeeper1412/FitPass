package com.ks.fitpass.employee.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CheckInFixedDTO {
    private int orderDetailId;
    private String username;
    private String productName;
    private String phoneNumber;
    private int duration;
    private double price;
    private String imageUrl;
}


