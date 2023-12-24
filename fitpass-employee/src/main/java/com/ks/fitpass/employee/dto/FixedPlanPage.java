package com.ks.fitpass.employee.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FixedPlanPage {
    private List<CheckInFixedDTO> listCheckInFixed;
    private List<CheckedInFixedDTO> listCheckedInFixed;
    private int totalPages;
    private int currentPage;
    private int departmentId;
}
