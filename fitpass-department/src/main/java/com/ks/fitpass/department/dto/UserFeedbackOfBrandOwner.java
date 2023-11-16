package com.ks.fitpass.department.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserFeedbackOfBrandOwner {
    private int feedbackId;

    private int userId;

    private String userName;

    private int departmentId;

    private int rating;

    private String comments;

    private LocalDateTime feedbackTime;

    private int feedbackStatus;

    private String email;
    private String phoneNumber;
}
