package com.ks.fitpass.become_a_partner.dto;

import lombok.Data;

@Data
public class BecomePartnerUpdateStatus {
    private int becomeAPartnerRequestId;
    private String status;
    private String cancelReason;
}
