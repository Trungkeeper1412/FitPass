package com.ks.fitpass.gymplan.dto;

import lombok.Data;

@Data
public class GymPlanBuyStat {
    private String name;
    private double price;
    private double pricePerHours;
    private int totalBuy;
}