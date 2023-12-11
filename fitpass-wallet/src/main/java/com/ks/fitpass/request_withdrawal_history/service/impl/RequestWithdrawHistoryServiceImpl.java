package com.ks.fitpass.request_withdrawal_history.service.impl;

import com.ks.fitpass.request_withdrawal_history.dto.*;
import com.ks.fitpass.request_withdrawal_history.repository.RequestWithdrawHistoryRepository;
import com.ks.fitpass.request_withdrawal_history.service.RequestWithdrawHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
        List<RequestWithdrawHistory> list = requestWithdrawHistoryRepository.getAllByStatus(status);
        return (list != null) ? list : new ArrayList<>();
    }

    @Override
    public List<RequestWithdrawHistoryWithBrandName> getAllByStatusWithBrandName(String status) {
        List<RequestWithdrawHistoryWithBrandName> list = requestWithdrawHistoryRepository.getAllByStatusWithBrandName(status);
        return (list != null) ? list : new ArrayList<>();
    }

    @Override
    public List<RequestWithdrawHistoryWithBrandName> getAllWithBrandName() {
        List<RequestWithdrawHistoryWithBrandName> list = requestWithdrawHistoryRepository.getAllWithBrandName();
        return (list != null) ? list : new ArrayList<>();
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

    @Override
    public int getUserIdByRequestHistoryId(int requestHistoryId) {
        return requestWithdrawHistoryRepository.getUserIdByRequestHistoryId(requestHistoryId);
    }

    @Override
    public double countAllBrandCredit() {
        Double count = requestWithdrawHistoryRepository.countAllBrandCredit();
        return (count != null) ? count : 0;
    }

    @Override
    public List<RequestHistoryBrandAdmin> getAllRequestHistoryBrandAdmin() {
        return requestWithdrawHistoryRepository.getAllRequestHistoryBrandAdmin();
    }

    @Override
    public int countRequestIsPending(int cardId) {
        return requestWithdrawHistoryRepository.countRequestIsPending(cardId);
    }
}
