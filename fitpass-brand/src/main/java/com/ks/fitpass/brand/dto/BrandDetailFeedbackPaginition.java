package com.ks.fitpass.brand.dto;

import lombok.Data;

import java.util.List;

@Data
public class BrandDetailFeedbackPaginition {
    List<BrandDetailFeedback> feedbackList;
    int currentPage;
    int totalPage;
}
