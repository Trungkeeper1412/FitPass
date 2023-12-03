package com.ks.fitpass.become_a_partner.dto;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class BecomePartnerRequest {
    private int becomeAPartnerRequestId;
    private String brandName;
    private String brandOwnerName;
    private String contactNumber;
    private String address;
    private String webUrl;
    private String contactEmail;
    private Timestamp sendRequestTime;
    private Timestamp startHandleRequestTime;
    private Timestamp cancelHandleRequestTime;
    private Timestamp approveHandleRequestTime;
    private String cancelReason;
    private String status;
}
