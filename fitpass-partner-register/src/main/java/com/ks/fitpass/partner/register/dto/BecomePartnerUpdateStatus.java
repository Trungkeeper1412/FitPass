package com.ks.fitpass.partner.register.dto;

import lombok.Data;

@Data
public class BecomePartnerUpdateStatus {
    private int becomeAPartnerRequestId;
    private String status;
    private String cancelReason;
}
