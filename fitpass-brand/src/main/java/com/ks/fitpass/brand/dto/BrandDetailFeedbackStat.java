package com.ks.fitpass.brand.dto;

import lombok.Data;

@Data
public class BrandDetailFeedbackStat {
    private int totalFeedback;
    private int fiveStar;
    private int fourStar;
    private int threeStar;
    private int twoStar;
    private int oneStar;
}
