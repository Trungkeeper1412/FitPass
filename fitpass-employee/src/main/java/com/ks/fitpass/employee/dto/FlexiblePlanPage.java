package com.ks.fitpass.employee.dto;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FlexiblePlanPage {
    private List<CheckInFlexibleDTO> listCheckInFlexible;
    private List<CheckOutFlexibleDTO> listCheckOutFlexible;
    private int totalPages;
    private int totalRecords;
    private int currentPage;
    private int departmentId;
}
