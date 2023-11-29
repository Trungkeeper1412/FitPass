package com.ks.fitpass.request_withdrawal_history.service.impl;

import com.ks.fitpass.request_withdrawal_history.dto.RequestHistoryAdmin;
import com.ks.fitpass.request_withdrawal_history.dto.RequestHistoryStats;
import com.ks.fitpass.request_withdrawal_history.dto.RequestWithdrawHistory;
import com.ks.fitpass.request_withdrawal_history.dto.RequestWithdrawHistoryWithBrandName;
import com.ks.fitpass.request_withdrawal_history.repository.RequestWithdrawHistoryRepository;
import com.ks.fitpass.request_withdrawal_history.service.RequestWithdrawHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RequestWithdrawHistoryServiceImpl implements RequestWithdrawHistoryService {
    private final RequestWithdrawHistoryRepository requestWithdrawHistoryRepository;

    @Override
    public List<RequestWithdrawHistory> getAll() {
        return requestWithdrawHistoryRepository.getAll();
    }

    @Override
    public List<RequestWithdrawHistory> getAllByUserId(int userId) {
        return requestWithdrawHistoryRepository.getAllByUserId(userId);
    }

    @Override
    public List<RequestWithdrawHistory> getAllByUserIdAndStatus(int userId, String status) {
        return requestWithdrawHistoryRepository.getAllByUserIdAndStatus(userId, status);
    }

    @Override
    public RequestHistoryStats getStatsByUserId(int userId) {
        return requestWithdrawHistoryRepository.getStatsByUserId(userId);
    }

    @Override
    public RequestHistoryStats getAllStats() {
        return requestWithdrawHistoryRepository.getAllStats();
    }

    @Override
    public int create(RequestWithdrawHistory requestWithdrawHistory) {
        return requestWithdrawHistoryRepository.create(requestWithdrawHistory);
    }

    @Override
    public List<RequestWithdrawHistory> getAllByStatus(String status) {
        return requestWithdrawHistoryRepository.getAllByStatus(status);
    }

    @Override
    public List<RequestWithdrawHistoryWithBrandName> getAllByStatusWithBrandName(String status) {
        return requestWithdrawHistoryRepository.getAllByStatusWithBrandName(status);
    }

    @Override
    public List<RequestWithdrawHistoryWithBrandName> getAllWithBrandName() {
        return requestWithdrawHistoryRepository.getAllWithBrandName();
    }

    @Override
    public RequestHistoryAdmin getById(int requestHistoryId) {
        return requestWithdrawHistoryRepository.getById(requestHistoryId);
    }

    @Override
    public int updateStatus(int requestHistoryId, String status) {
        return requestWithdrawHistoryRepository.updateStatus(requestHistoryId, status);
    }

    @Override
    public Integer getNumberPercentage(int requestHistoryId) {
        return requestWithdrawHistoryRepository.getNumberPercentage(requestHistoryId);
    }
}
