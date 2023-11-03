package com.ks.fitpass.calendar.dto;

import lombok.*;

import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CheckInDataCalendarDTO {
    private int checkInHistoryId;
    private String gymDepartmentName;
    private Timestamp checkInTime;

}
