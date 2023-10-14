package com.ks.fitpass.department.entity;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentSchedule {

    private int departmentScheduleId;

    private int departmentId;

    private String departmentScheduleDay;

    private String departmentScheduleOpenTime;

    private String departmentScheduleCloseTime;
}
