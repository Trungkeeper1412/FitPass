package com.ks.fitpass.request_withdrawal_history.service;

import com.ks.fitpass.request_withdrawal_history.dto.RequestHistoryAdmin;
import com.ks.fitpass.request_withdrawal_history.dto.RequestHistoryStats;
import com.ks.fitpass.request_withdrawal_history.dto.RequestWithdrawHistory;
import com.ks.fitpass.request_withdrawal_history.dto.RequestWithdrawHistoryWithBrandName;

import java.util.List;

public interface RequestWithdrawHistoryService {
    List<RequestWithdrawHistory> getAll();
    List<RequestWithdrawHistory> getAllByUserId(int userId);
    List<RequestWithdrawHistory> getAllByUserIdAndStatus(int userId, String status);

    RequestHistoryStats getStatsByUserId(int userId);
    RequestHistoryStats getAllStats();

    int create(RequestWithdrawHistory requestWithdrawHistory);
    List<RequestWithdrawHistory> getAllByStatus(String status);

    List<RequestWithdrawHistoryWithBrandName> getAllByStatusWithBrandName(String status);
    List<RequestWithdrawHistoryWithBrandName> getAllWithBrandName();
    RequestHistoryAdmin getById(int requestHistoryId);
    int updateStatus(int requestHistoryId, String status);
    Integer getNumberPercentage(int requestHistoryId);
}
