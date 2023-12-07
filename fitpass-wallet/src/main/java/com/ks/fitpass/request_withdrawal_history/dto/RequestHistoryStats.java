package com.ks.fitpass.request_withdrawal_history.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RequestHistoryStats {
    private int totalRequest;
    private int totalApproved;
    private int totalPending;
    private Long totalCredit;
    private Long totalMoney;
}
