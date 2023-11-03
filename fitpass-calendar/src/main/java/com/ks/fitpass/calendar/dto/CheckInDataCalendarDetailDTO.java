package com.ks.fitpass.calendar.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CheckInDataCalendarDetailDTO {
    private int checkInHistoryId;
    private int gymDepartmentId;
    private String gymDepartmentName;
    private String address;
    private Timestamp checkInTime;
    private String date;
    private String time;
    private String gymPlanName;
    private int feedBackId;
}
