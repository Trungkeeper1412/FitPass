package com.ks.fitpass.become_a_partner.service;

import com.ks.fitpass.become_a_partner.dto.BecomePartnerRequest;
import com.ks.fitpass.become_a_partner.repository.BecomePartnerRepository;

import java.sql.Timestamp;
import java.util.List;

public interface BecomePartnerService {
    int create(BecomePartnerRequest becomePartnerRequest);

    List<BecomePartnerRequest> getAllBecomePartnerRequestByStatus(String status);

    BecomePartnerRequest getById(int becomeAPartnerRequestId);

    int updateStatus(int becomeAPartnerRequestId, String status);

    int updateStartRequestTime(int becomeAPartnerRequestId, Timestamp startHandleRequestTime);
    int updateCancelRequestTime(int becomeAPartnerRequestId, Timestamp cancelHandleRequestTime, String cancelReason);
    int updateApproveRequestTime(int becomeAPartnerRequestId, Timestamp approveHandleRequestTime);
}
