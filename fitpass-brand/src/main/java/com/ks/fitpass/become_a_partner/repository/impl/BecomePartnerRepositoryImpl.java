package com.ks.fitpass.become_a_partner.repository.impl;

import com.ks.fitpass.become_a_partner.dto.BecomePartnerRequest;
import com.ks.fitpass.become_a_partner.repository.BecomePartnerRepository;
import com.ks.fitpass.become_a_partner.repository.IRepositoryQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class BecomePartnerRepositoryImpl implements BecomePartnerRepository, IRepositoryQuery {
    private final JdbcTemplate jdbcTemplate;

    @Override
    public int create(BecomePartnerRequest becomePartnerRequest) {
        return jdbcTemplate.update(CREATE_BECOME_PARTNER_REQUEST,
                becomePartnerRequest.getBrandName(),
                becomePartnerRequest.getBrandOwnerName(),
                becomePartnerRequest.getContactNumber(),
                becomePartnerRequest.getAddress(),
                becomePartnerRequest.getWebUrl(),
                becomePartnerRequest.getContactEmail(),
                becomePartnerRequest.getSendRequestTime(),
                becomePartnerRequest.getStatus());
    }

    @Override
    public List<BecomePartnerRequest> getAllBecomePartnerRequestByStatus(String status) {
        return jdbcTemplate.query(GET_ALL_BECOME_PARTNER_REQUEST_BY_STATUS, (rs, rowNum) -> {
            BecomePartnerRequest becomePartnerRequest = new BecomePartnerRequest();
            becomePartnerRequest.setBecomeAPartnerRequestId(rs.getInt("become_a_partner_request_history_id"));
            becomePartnerRequest.setBrandName(rs.getString("brand_name"));
            becomePartnerRequest.setBrandOwnerName(rs.getString("brand_owner_name"));
            becomePartnerRequest.setContactNumber(rs.getString("contact_number"));
            becomePartnerRequest.setAddress(rs.getString("address"));
            becomePartnerRequest.setWebUrl(rs.getString("web_url"));
            becomePartnerRequest.setContactEmail(rs.getString("contact_email"));
            becomePartnerRequest.setSendRequestTime(rs.getTimestamp("send_request_time"));
            becomePartnerRequest.setStartHandleRequestTime(rs.getTimestamp("start_handle_request_time"));
            becomePartnerRequest.setCancelHandleRequestTime(rs.getTimestamp("cancel_request_time"));
            becomePartnerRequest.setApproveHandleRequestTime(rs.getTimestamp("approve_request_time"));
            becomePartnerRequest.setCancelReason(rs.getString("cancel_reason"));
            becomePartnerRequest.setStatus(rs.getString("status"));
            return becomePartnerRequest;
        }, status);
    }

    @Override
    public BecomePartnerRequest getById(int becomeAPartnerRequestId) {
        return jdbcTemplate.queryForObject(GET_BECOME_PARTNER_REQUEST_BY_ID, (rs, rowNum) -> {
            BecomePartnerRequest becomePartnerRequest = new BecomePartnerRequest();
            becomePartnerRequest.setBecomeAPartnerRequestId(rs.getInt("become_a_partner_request_history_id"));
            becomePartnerRequest.setBrandName(rs.getString("brand_name"));
            becomePartnerRequest.setBrandOwnerName(rs.getString("brand_owner_name"));
            becomePartnerRequest.setContactNumber(rs.getString("contact_number"));
            becomePartnerRequest.setAddress(rs.getString("address"));
            becomePartnerRequest.setWebUrl(rs.getString("web_url"));
            becomePartnerRequest.setContactEmail(rs.getString("contact_email"));
            becomePartnerRequest.setSendRequestTime(rs.getTimestamp("send_request_time"));
            becomePartnerRequest.setStartHandleRequestTime(rs.getTimestamp("start_handle_request_time"));
            becomePartnerRequest.setCancelHandleRequestTime(rs.getTimestamp("cancel_request_time"));
            becomePartnerRequest.setApproveHandleRequestTime(rs.getTimestamp("approve_request_time"));
            becomePartnerRequest.setCancelReason(rs.getString("cancel_reason"));
            becomePartnerRequest.setStatus(rs.getString("status"));
            return becomePartnerRequest;
        }, becomeAPartnerRequestId);
    }

    @Override
    public int updateStatus(int becomeAPartnerRequestId, String status) {
        return jdbcTemplate.update(UPDATE_BECOME_PARTNER_REQUEST_STATUS, status, becomeAPartnerRequestId);
    }

    @Override
    public int updateStartRequestTime(int becomeAPartnerRequestId, Timestamp startHandleRequestTime) {
        return jdbcTemplate.update(UPDATE_START_HANDLE_REQUEST_TIME, startHandleRequestTime, becomeAPartnerRequestId);
    }

    @Override
    public int updateCancelRequestTime(int becomeAPartnerRequestId, Timestamp cancelHandleRequestTime, String cancelReason) {
        return jdbcTemplate.update(UPDATE_CANCEL_HANDLE_REQUEST_TIME, cancelHandleRequestTime, cancelReason, becomeAPartnerRequestId);
    }

    @Override
    public int updateApproveRequestTime(int becomeAPartnerRequestId, Timestamp approveHandleRequestTime) {
        return jdbcTemplate.update(UPDATE_APPROVE_HANDLE_REQUEST_TIME, approveHandleRequestTime, becomeAPartnerRequestId);
    }
}
