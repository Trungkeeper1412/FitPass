package com.ks.fitpass.department.dto;

import lombok.*;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserFeedbackDTO {

    private int feedbackId;

    private int userId;

    private int departmentId;

    private int rating;

    private String comments;

    private LocalDateTime feedbackTime;

    private int feedbackStatus;

}