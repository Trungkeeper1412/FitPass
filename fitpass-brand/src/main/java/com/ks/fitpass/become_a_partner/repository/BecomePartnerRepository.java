package com.ks.fitpass.become_a_partner.repository;

import com.ks.fitpass.become_a_partner.dto.BecomePartnerRequest;

import java.sql.Timestamp;
import java.util.List;

public interface BecomePartnerRepository {
    int create(BecomePartnerRequest becomePartnerRequest);

    List<BecomePartnerRequest> getAllBecomePartnerRequestByStatus(String status);

    BecomePartnerRequest getById(int becomeAPartnerRequestId);

    int updateStatus(int becomeAPartnerRequestId, String status);

    int updateStartRequestTime(int becomeAPartnerRequestId, Timestamp startHandleRequestTime);

    int updateCancelRequestTime(int becomeAPartnerRequestId, Timestamp cancelHandleRequestTime, String cancelReason);

    int updateApproveRequestTime(int becomeAPartnerRequestId, Timestamp approveHandleRequestTime);
}
