package com.ks.fitpass.checkInHistory.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CheckInHistoryPage {
    private List<CheckInHistoryFlexible> listFlexible;
    private List<CheckInHistoryFixed> listFixed;
    private int totalPages;
    private int currentPage;
    private int departmentId;
}
