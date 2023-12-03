package com.ks.fitpass.become_a_partner.service.impl;

import com.ks.fitpass.become_a_partner.dto.BecomePartnerRequest;
import com.ks.fitpass.become_a_partner.repository.BecomePartnerRepository;
import com.ks.fitpass.become_a_partner.service.BecomePartnerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BecomePartnerServiceImpl implements BecomePartnerService {
    private final BecomePartnerRepository becomePartnerRepository;

    @Override
    public int create(BecomePartnerRequest becomePartnerRequest) {
        return becomePartnerRepository.create(becomePartnerRequest);
    }

    @Override
    public List<BecomePartnerRequest> getAllBecomePartnerRequestByStatus(String status) {
        return becomePartnerRepository.getAllBecomePartnerRequestByStatus(status);
    }

    @Override
    public BecomePartnerRequest getById(int becomeAPartnerRequestId) {
        return becomePartnerRepository.getById(becomeAPartnerRequestId);
    }

    @Override
    public int updateStatus(int becomeAPartnerRequestId, String status) {
        return becomePartnerRepository.updateStatus(becomeAPartnerRequestId, status);
    }

    @Override
    public int updateStartRequestTime(int becomeAPartnerRequestId, Timestamp startHandleRequestTime) {
        return becomePartnerRepository.updateStartRequestTime(becomeAPartnerRequestId, startHandleRequestTime);
    }

    @Override
    public int updateCancelRequestTime(int becomeAPartnerRequestId, Timestamp cancelHandleRequestTime, String cancelReason) {
        return becomePartnerRepository.updateCancelRequestTime(becomeAPartnerRequestId, cancelHandleRequestTime, cancelReason);
    }

    @Override
    public int updateApproveRequestTime(int becomeAPartnerRequestId, Timestamp approveHandleRequestTime) {
        return becomePartnerRepository.updateApproveRequestTime(becomeAPartnerRequestId, approveHandleRequestTime);
    }
}
