package com.ks.fitpass.calendar.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CalendarFeedbackDTO {

    private int feedbackId;

    private int userId;

    private int departmentId;

    private int rating;

    private String comments;

    private Timestamp feedbackTime;

    private int feedbackStatus;

}
