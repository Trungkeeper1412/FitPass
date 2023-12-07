package com.ks.fitpass.department.entity;

import lombok.*;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserFeedback {

    private int feedbackId;

    private int userId;

    private String userName;

    private int departmentId;

    private int rating;

    private String comments;

    private LocalDateTime feedbackTime;

    private int feedbackStatus;

    private String imageUrl;
}