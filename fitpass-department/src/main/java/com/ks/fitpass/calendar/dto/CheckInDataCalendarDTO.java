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
public class CheckInDataCalendarDTO {
    private int checkInHistoryId;
    private String gymDepartmentName;
    private Timestamp checkInTime;

}
