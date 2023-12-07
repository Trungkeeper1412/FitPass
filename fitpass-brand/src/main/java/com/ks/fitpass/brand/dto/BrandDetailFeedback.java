package com.ks.fitpass.brand.dto;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class BrandDetailFeedback {
    private int feedbackId;
    private int userId;
    private String firstName;
    private String lastName;
    private String imageUrl;
    private double rating;
    private String comment;
    private Timestamp feedbackTime;
}
