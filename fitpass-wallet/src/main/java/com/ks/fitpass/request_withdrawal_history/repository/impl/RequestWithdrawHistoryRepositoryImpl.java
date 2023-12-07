package com.ks.fitpass.request_withdrawal_history.repository.impl;

import com.ks.fitpass.request_withdrawal_history.dto.*;
import com.ks.fitpass.request_withdrawal_history.repository.IRepositoryQuery;
import com.ks.fitpass.request_withdrawal_history.repository.RequestWithdrawHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class RequestWithdrawHistoryRepositoryImpl implements RequestWithdrawHistoryRepository, IRepositoryQuery {
    private final JdbcTemplate jdbcTemplate;

    @Override
    public List<RequestWithdrawHistory> getAll() {
        return jdbcTemplate.query(GET_ALL, (resultSet, i) -> {
            RequestWithdrawHistory requestWithdrawHistory = new RequestWithdrawHistory();
            requestWithdrawHistory.setRequestHistoryId(resultSet.getInt("request_withdrawal_history_id"));
            requestWithdrawHistory.setCreditCardId(resultSet.getInt("credit_card_id"));
            requestWithdrawHistory.setWithdrawalCode(resultSet.getString("withdrawal_code"));
            requestWithdrawHistory.setWithdrawalTime(resultSet.getTimestamp("withdrawal_time"));
            requestWithdrawHistory.setAmountCredit(resultSet.getLong("amount_credit"));
            requestWithdrawHistory.setActualMoney(resultSet.getLong("actual_money"));
            requestWithdrawHistory.setStatus(resultSet.getString("status"));
            return requestWithdrawHistory;
        });
    }

    @Override
    public List<RequestWithdrawHistory> getAllByUserId(int userId) {
        return jdbcTemplate.query(GET_ALL_BY_USER_ID, (resultSet, i) -> {
            RequestWithdrawHistory requestWithdrawHistory = new RequestWithdrawHistory();
            requestWithdrawHistory.setRequestHistoryId(resultSet.getInt("request_withdrawal_history_id"));
            requestWithdrawHistory.setCreditCardId(resultSet.getInt("credit_card_id"));
            requestWithdrawHistory.setWithdrawalCode(resultSet.getString("withdrawal_code"));
            requestWithdrawHistory.setWithdrawalTime(resultSet.getTimestamp("withdrawal_time"));
            requestWithdrawHistory.setAmountCredit(resultSet.getLong("amount_credit"));
            requestWithdrawHistory.setActualMoney(resultSet.getLong("actual_money"));
            requestWithdrawHistory.setStatus(resultSet.getString("status"));
            return requestWithdrawHistory;
        }, userId);
    }

    @Override
    public List<RequestWithdrawHistory> getAllByUserIdAndStatus(int userId, String status) {
        return jdbcTemplate.query(GET_ALL_BY_USER_ID_AND_STATUS, (resultSet, i) -> {
            RequestWithdrawHistory requestWithdrawHistory = new RequestWithdrawHistory();
            requestWithdrawHistory.setRequestHistoryId(resultSet.getInt("request_withdrawal_history_id"));
            requestWithdrawHistory.setCreditCardId(resultSet.getInt("credit_card_id"));
            requestWithdrawHistory.setWithdrawalCode(resultSet.getString("withdrawal_code"));
            requestWithdrawHistory.setWithdrawalTime(resultSet.getTimestamp("withdrawal_time"));
            requestWithdrawHistory.setAmountCredit(resultSet.getLong("amount_credit"));
            requestWithdrawHistory.setActualMoney(resultSet.getLong("actual_money"));
            requestWithdrawHistory.setStatus(resultSet.getString("status"));
            return requestWithdrawHistory;
        }, userId, status);
    }

    @Override
    public RequestHistoryStats getStatsByUserId(int userId) {
        return jdbcTemplate.queryForObject(GET_STATS_BY_USER_ID, (resultSet, i) -> {
            RequestHistoryStats requestHistoryStats = new RequestHistoryStats();
            requestHistoryStats.setTotalRequest(resultSet.getInt("total_request"));
            requestHistoryStats.setTotalApproved(resultSet.getInt("total_approved"));
            requestHistoryStats.setTotalPending(resultSet.getInt("total_pending"));
            requestHistoryStats.setTotalCredit(resultSet.getLong("total_amount_credit"));
            requestHistoryStats.setTotalMoney(resultSet.getLong("total_actual_money"));
            return requestHistoryStats;
        }, userId);
    }

    @Override
    public RequestHistoryStats getAllStats() {
        return jdbcTemplate.queryForObject(GET_ALL_STATS, (resultSet, i) -> {
            RequestHistoryStats requestHistoryStats = new RequestHistoryStats();
            requestHistoryStats.setTotalRequest(resultSet.getInt("total_request"));
            requestHistoryStats.setTotalApproved(resultSet.getInt("total_approved"));
            requestHistoryStats.setTotalPending(resultSet.getInt("total_pending"));
            requestHistoryStats.setTotalCredit(resultSet.getLong("total_amount_credit"));
            requestHistoryStats.setTotalMoney(resultSet.getLong("total_actual_money"));
            return requestHistoryStats;
        });
    }

    @Override
    public int create(RequestWithdrawHistory requestWithdrawHistory) {
        return jdbcTemplate.update(CREATE, requestWithdrawHistory.getCreditCardId(), requestWithdrawHistory.getWithdrawalCode(),
                requestWithdrawHistory.getWithdrawalTime(), requestWithdrawHistory.getAmountCredit(), requestWithdrawHistory.getActualMoney(),
                requestWithdrawHistory.getStatus()
                );
    }

    @Override
    public List<RequestWithdrawHistory> getAllByStatus(String status) {
        return jdbcTemplate.query(GET_ALL_BY_STATUS, (resultSet, i) -> {
            RequestWithdrawHistory requestWithdrawHistory = new RequestWithdrawHistory();
            requestWithdrawHistory.setRequestHistoryId(resultSet.getInt("request_withdrawal_history_id"));
            requestWithdrawHistory.setCreditCardId(resultSet.getInt("credit_card_id"));
            requestWithdrawHistory.setWithdrawalCode(resultSet.getString("withdrawal_code"));
            requestWithdrawHistory.setWithdrawalTime(resultSet.getTimestamp("withdrawal_time"));
            requestWithdrawHistory.setAmountCredit(resultSet.getLong("amount_credit"));
            requestWithdrawHistory.setActualMoney(resultSet.getLong("actual_money"));
            requestWithdrawHistory.setStatus(resultSet.getString("status"));
            return requestWithdrawHistory;
        }, status);
    }

    @Override
    public List<RequestWithdrawHistoryWithBrandName> getAllByStatusWithBrandName(String status) {
        return jdbcTemplate.query(GET_ALL_BY_STATUS_WITH_BRAND_NAME, (resultSet, i) -> {
            RequestWithdrawHistoryWithBrandName requestWithdrawHistoryWithBrandName = new RequestWithdrawHistoryWithBrandName();
            requestWithdrawHistoryWithBrandName.setRequestHistoryId(resultSet.getInt("request_withdrawal_history_id"));
            requestWithdrawHistoryWithBrandName.setCreditCardId(resultSet.getInt("credit_card_id"));
            requestWithdrawHistoryWithBrandName.setWithdrawalCode(resultSet.getString("withdrawal_code"));
            requestWithdrawHistoryWithBrandName.setWithdrawalTime(resultSet.getTimestamp("withdrawal_time"));
            requestWithdrawHistoryWithBrandName.setAmountCredit(resultSet.getLong("amount_credit"));
            requestWithdrawHistoryWithBrandName.setActualMoney(resultSet.getLong("actual_money"));
            requestWithdrawHistoryWithBrandName.setStatus(resultSet.getString("status"));
            requestWithdrawHistoryWithBrandName.setBrandName(resultSet.getString("name"));
            return requestWithdrawHistoryWithBrandName;
        }, status);
    }

    @Override
    public List<RequestWithdrawHistoryWithBrandName> getAllWithBrandName() {
        return jdbcTemplate.query(GET_ALL_WITH_BRAND_NAME, (resultSet, i) -> {
            RequestWithdrawHistoryWithBrandName requestWithdrawHistoryWithBrandName = new RequestWithdrawHistoryWithBrandName();
            requestWithdrawHistoryWithBrandName.setRequestHistoryId(resultSet.getInt("request_withdrawal_history_id"));
            requestWithdrawHistoryWithBrandName.setCreditCardId(resultSet.getInt("credit_card_id"));
            requestWithdrawHistoryWithBrandName.setWithdrawalCode(resultSet.getString("withdrawal_code"));
            requestWithdrawHistoryWithBrandName.setWithdrawalTime(resultSet.getTimestamp("withdrawal_time"));
            requestWithdrawHistoryWithBrandName.setAmountCredit(resultSet.getLong("amount_credit"));
            requestWithdrawHistoryWithBrandName.setActualMoney(resultSet.getLong("actual_money"));
            requestWithdrawHistoryWithBrandName.setStatus(resultSet.getString("status"));
            requestWithdrawHistoryWithBrandName.setBrandName(resultSet.getString("name"));
            return requestWithdrawHistoryWithBrandName;
        });
    }

    @Override
    public RequestHistoryAdmin getById(int requestHistoryId) {
        return jdbcTemplate.queryForObject(GET_BY_ID, (resultSet, i) -> {
            RequestHistoryAdmin requestHistoryAdmin = new RequestHistoryAdmin();
            requestHistoryAdmin.setRequestHistoryId(resultSet.getInt("request_withdrawal_history_id"));
            requestHistoryAdmin.setAmountCredit(resultSet.getLong("amount_credit"));
            requestHistoryAdmin.setActualMoney(resultSet.getLong("actual_money"));
            requestHistoryAdmin.setBrandName(resultSet.getString("name"));
            requestHistoryAdmin.setCardBank(resultSet.getString("bank_name"));
            requestHistoryAdmin.setCardNumber(resultSet.getString("card_number"));
            requestHistoryAdmin.setCardHolder(resultSet.getString("card_owner_name"));
            return requestHistoryAdmin;
        }, requestHistoryId);
    }

    @Override
    public int updateStatus(int requestHistoryId, String status) {
        return jdbcTemplate.update(UPDATE_STATUS, status, requestHistoryId);
    }

    @Override
    public Integer getNumberPercentage(int requestHistoryId) {
        return jdbcTemplate.queryForObject(GET_NUMBER_PERCENTAGE, Integer.class, requestHistoryId);
    }

    @Override
    public Integer getUserIdByRequestHistoryId(int requestHistoryId) {
        return jdbcTemplate.queryForObject(GET_USER_ID_BY_REQUEST_HISTORY_ID, Integer.class, requestHistoryId);
    }

    @Override
    public Double countAllBrandCredit() {
        return jdbcTemplate.queryForObject(COUNT_ALL_BRAND_CREDIT, Double.class);
    }

    @Override
    public List<RequestHistoryBrandAdmin> getAllRequestHistoryBrandAdmin() {
        return jdbcTemplate.query(GET_ALL_REQUEST_HISTORY_BRAND_ADMIN, (rs, i) -> {
            RequestHistoryBrandAdmin re = new RequestHistoryBrandAdmin();
            re.setBrandName(rs.getString("name"));
            re.setTotalRequest(rs.getInt("total_request"));
            re.setAmountCredit(rs.getLong("amount_credit"));
            re.setActualMoney(rs.getLong("actual_money"));
            return re;
        });
    }
}
